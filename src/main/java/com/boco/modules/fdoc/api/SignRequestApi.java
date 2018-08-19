package com.boco.modules.fdoc.api;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.push.CloudMobilePush;
import com.boco.common.push.PushUtils;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.FTPUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.StringUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.PersonDeseaseInfoEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.model.SignAgreementEntity;
import com.boco.modules.fdoc.model.SignRequestEntity;
import com.boco.modules.fdoc.model.UserDocSignEntity;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SendMsgService;
import com.boco.modules.fdoc.service.SignRequestService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.service.UserService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SignRequestVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 签约申请API
 * 
 * @author q
 *
 */

@RestController
@RequestMapping(value = "/signRequest", produces = "text/json;charset=UTF-8")
public class SignRequestApi extends BaseController{
	
	@Resource
	SignRequestService requestService;
	@Resource
	DocUserService docUserService;
	@Resource
	SignService signService;
	@Resource
	UserService userService;
	@Resource
	DocService docService;
	@Resource
	PersonInformationService personService;
	@Resource
	SendMsgService sendMsgService;
	
	@RequestMapping(value = "/getSignRequestList", method = RequestMethod.GET)
	@ApiOperation(value = "获取医生团队下已拒绝、待确认的请求列表（医生端）", notes = "{'docTeamId':'医生团队ID','status':'申请状态：1.待确认  2.已确认 3.已拒绝'}")
	public String getSignRequestList(@RequestParam String docTeamId, @RequestParam String status, 
			@RequestParam Integer pageSize, @RequestParam Integer pageNo) {
		//封装查询对象
		SignRequestVo vo = new SignRequestVo();
		vo.setDocTeamId(docTeamId);
		vo.setStatus(status);
		//获取总数
		Integer requestCount = requestService.getRequestCount(vo);
		//封装分页对象、查询列表
		vo.setPage(new Page<SignRequestVo>(pageNo,pageSize, requestCount));
		List<SignRequestVo> requestList = requestService.getRequestList(vo);
		
		return JsonUtils.getPageJson(requestCount, pageSize, requestList);
	}
	
	@RequestMapping(value = "/getSignRequestListByUser", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民发出的请求列表（居民端）", notes = "{'personId':'居民ID'}")
	public String getSignRequestListByUser(@RequestParam String personId) {
		//封装查询对象
		SignRequestVo vo = new SignRequestVo();
		vo.setPersonId(personId);
		//获取总数
		List<SignRequestVo> requestList = requestService.getRequestList(vo);
		
		return JsonUtils.getJson(BaseJsonVo.success(requestList));
	}
	
	@RequestMapping(value = "/getSignRequestDetail", method = RequestMethod.GET)
	@ApiOperation(value = "获取签约请求详情（通用）", notes = "{'requestId':'请求ID'}")
	public String getSignRequestDetail(@RequestParam Integer requestId) {
		SignRequestVo requestDetail = requestService.getRequestDetail(requestId);
		return JsonUtils.getJson(BaseJsonVo.success(requestDetail));
	}
	
	@RequestMapping(value = "/getLastSignRequest", method = RequestMethod.GET)
	@ApiOperation(value = "获取最后一次签约请求（医生端）", notes = "{'docTeamId':'医生团队ID'}")
	public String getLastSignRequest(@RequestParam String docTeamId) {
		SignRequestVo vo = new SignRequestVo();
		vo.setDocTeamId(docTeamId);
		vo.setStatus(BusinessConstants.SIGN_REQUEST_READY);
		List<SignRequestVo> requestList = requestService.getRequestList(vo);
		if (requestList.size() > 0) {
			return JsonUtils.getJson(BaseJsonVo.success(requestList.get(0)));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dealSignRequest", method = RequestMethod.POST)
	@ApiOperation(value = "处理签约请求（医生端）", notes = "{'userName':'医生登录用户名','requestId':'请求ID','dealWay':'处理方式：1.确认签约 2.拒绝','remark':'备注','labelJson':'人员和标签对应json'}")
	public String dealSignRequest(@RequestBody String requestBody) {
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(requestBody);
		Integer requestId = Integer.parseInt((String)jsonMap.get("requestId"));
		String dealWay = (String) jsonMap.get("dealWay");
		String userName = (String) jsonMap.get("userName");
		String remark = (String) jsonMap.get("remark");
		
		SignRequestEntity entity = new SignRequestEntity();
		entity.setId(requestId);
		
		/** 获取医生ID、医生姓名、医生所属机构名称 **/
		DoctorDetailVo doctor = docUserService.getDoctorByUsername(userName);
		if (doctor == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_SUCH_DOCTOR.getKey(),
					ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
		//获取签约请求对象
		SignRequestEntity signRequest = requestService.getSignRequest(requestId);
		
		if (BusinessConstants.SIGN_REQUEST_DEAL_WAY_AGREE.equals(dealWay)) {
			/**
			 * 同意签约
			 */
			//执行签约逻辑
			UserDocSignEntity en = new UserDocSignEntity();
			
			//把签约请求对象的各个属性赋值给签约对象和签约协议对象
			en.setPersonId(signRequest.getPersonId());
			en.setDocOrgName(signRequest.getDocOrgName());
			en.setDocTeamId(signRequest.getDocTeamId());
			en.setSignImg(signRequest.getSignImg());
			en.setSignType(BusinessConstants.SIGN_TYPE_USER);
			en.setDocOrgAddress(doctor.getOrgAddress());
			en.setRemark(remark);
			en.setDueTime(signRequest.getDueTime());
			en.setServicePackValue(signRequest.getServicePackValue());
			
			//解析人员对应的疾病标识
			List<Map<String, Object>> diseaseList = (List<Map<String, Object>>) jsonMap.get("labelJson");
			
			List<PersonDeseaseInfoEntity> diseaseInfo = new ArrayList<PersonDeseaseInfoEntity>();
			for (Map<String, Object> map : diseaseList) {
				PersonDeseaseInfoEntity desease = null;
				String personId = (String) map.get("personId");
				Integer deseaseInfo = (Integer) map.get("value");
				if (deseaseInfo == 1) {
					desease = new PersonDeseaseInfoEntity(personId, "0", "0", "0", "0");
				}else {
					desease = new PersonDeseaseInfoEntity(personId, deseaseInfo);
				}
				diseaseInfo.add(desease);
			}
			
			Map<String, Object> returnMap = null;
			try {
				returnMap = signService.sign(en, diseaseInfo); // 执行新签约操作
				String signResult = (String) returnMap.get("returnMsg");
				
				if (BusinessConstants.ERROR_RE_SIGN.equals(signResult)) {
					return JsonUtils.getJson(BaseJsonVo.empty(
							ApiStatusEnum.RE_SIGN.getKey(),
							ApiStatusEnum.RE_SIGN.getValue()));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// 签约失败
				return JsonUtils.getJson(BaseJsonVo.empty(
						ApiStatusEnum.FAIL.getKey(),
						ApiStatusEnum.FAIL.getValue()));
			}
			
			
			//修改状态为已同意
			entity.setStatus(BusinessConstants.SIGN_REQUEST_OK);
			requestService.dealSignRequest(entity);
			
			try {
				/** 签约成功推送给用户**/
				Map<String, Object> pushMap = new HashMap<String, Object>();
				pushMap.put("signId", returnMap.get("signId"));
				CloudMobilePush push = new CloudMobilePush();
				//获取推送居民账号列表
				String account = userService.getAccountsByPersonId(signRequest.getPersonId());
				if (StringUtils.isNoneEmpty(account)) {
					//获取医生团队队长信息
					DoctorDetailVo teamLeaderInfo = docService.getTeamLeaderInfo(doctor.getTeamId());
					 //推送安卓端
					List<String> accounts = new ArrayList<String>();
					accounts.add(account);	//将居民账号加入推送列表
					
					String title = "您已成功签约";
					String content = "您已和"+teamLeaderInfo.getDocName()+"医生团队成功签约！";
					push.androidPush(accounts, title, content, 
						 BusinessConstants.PUSH_ACTIVITY, BusinessConstants.PUSH_ITEM_USER, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_SIGN_REQUEST_SUCCESS, pushMap));
					/**
					 * 插入推送消息表
					 */
					SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_USER_INT,
							BusinessConstants.PUSH_TYPE_SIGN_REQUEST_SUCCESS,JsonUtils.getJsonFormat(pushMap));
					msg.setUid(account);
					sendMsgService.addMsg(msg);
				}
			} catch (Exception e) {
				return JsonUtils.getJson(BaseJsonVo.success(null));
			}
			
			return JsonUtils.getJson(BaseJsonVo.success(null));
			
		}else if (BusinessConstants.SIGN_REQUEST_DEAL_WAY_REFUSE.equals(dealWay)) {
			/**
			 * 拒绝
			 */
			//修改状态为已拒绝
			entity.setStatus(BusinessConstants.SIGN_REQUEST_REFUSED);
			requestService.dealSignRequest(entity);
			
			/** 签约成功推送给用户**/
			Map<String, Object> pushMap = new HashMap<String, Object>();
			pushMap.put("requestId", requestId);
			CloudMobilePush push = new CloudMobilePush();
			//获取推送居民账号列表
			String account = userService.getAccountsByPersonId(signRequest.getPersonId());
			//获取医生团队队长信息
			DoctorDetailVo teamLeaderInfo = docService.getTeamLeaderInfo(doctor.getTeamId());
			try {
				 //推送安卓端
				List<String> accounts = new ArrayList<String>();
				accounts.add(account);	//将居民账号加入推送列表
				
				String title = "您的签约请求已被拒绝";
				String content = "很抱歉，您与"+teamLeaderInfo.getDocName()+"医生团队的签约请求已被拒绝！";
				push.androidPush(accounts, title, content, 
					 BusinessConstants.PUSH_ACTIVITY, BusinessConstants.PUSH_ITEM_USER, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_SIGN_REQUEST_FAIL, pushMap));
				/**
				 * 插入推送消息表
				 */
				SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_USER_INT,
						BusinessConstants.PUSH_TYPE_SIGN_REQUEST_FAIL,JsonUtils.getJsonFormat(pushMap));
				msg.setUid(account);
				sendMsgService.addMsg(msg);
			} catch (Exception e) {
				//推送失败，不做处理
				return JsonUtils.getJson(BaseJsonVo.success(null));
			}
			
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}else {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PARAM_CODE.getKey(),
					ApiStatusEnum.PARAM_CODE.getValue()));
		}
	}
	
	@RequestMapping(value = "/sendSignRequest", method = RequestMethod.POST)
	@ApiOperation(value = "发出签约请求（居民端）", notes = "{'personId':'签约居民ID','docTeamId':'医生团队ID','docOrgName':'医生所属机构名称','img':'图片file对象','servicePackValue':'所选服务包权值','endDate':'签约有效期截止时间戳'}")
	public String sendSignRequest(@RequestParam String personId,@RequestParam String docTeamId,@RequestParam String docOrgName,@RequestParam("file") CommonsMultipartFile img,
			 HttpServletRequest request,@RequestParam Integer servicePackValue,@RequestParam Long endDate) {
		try {
			//	封装签约请求对象
			SignRequestEntity entity = new SignRequestEntity();
			entity.setPersonId(personId);
			entity.setDocTeamId(docTeamId);
			entity.setDocOrgName(docOrgName);
			entity.setDueTime(new Date(endDate));
			entity.setServicePackValue(servicePackValue);
			/**
			 * 图片上传部分
			 */
			if (img != null && img.getOriginalFilename() != null && StringUtils.isNotEmpty(img.getOriginalFilename())) {
				InputStream is = img.getInputStream();
				
				// 生成jpeg图片
				String headSuffix = StringUtils.getSuffix(img.getOriginalFilename()); // 获取后缀名
				String signImgHeadName = UUID.randomUUID().toString()
						.substring(0, 8)
						+ "." + headSuffix; // 重命名为8位随机数
				
				//复制文件到指定路径 
				File saveFile = new File((request.getContextPath() + "/upload/img/" + signImgHeadName).substring(9)); 
				FileUtils.copyInputStreamToFile(is, saveFile); 
		        //上传文件到服务器 
				//FTPUtils.upload(saveFile, "/upload/img/"); 
		        
				entity.setSignImg((request.getContextPath() + "/upload/img/"
						+ signImgHeadName).substring(9));
			}
			/**
			 * 设置持久化对象信息（签约对象和签约协议对象）
			 */
			Map<String, Object> returnMap = requestService.addSignRequestService(entity);
			String returnMsg = (String) returnMap.get("returnMsg");
			if (BusinessConstants.ERROR_RE_SIGN.equals(returnMsg)) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_SIGN_REQUEST.getKey(),
								ApiStatusEnum.RE_SIGN_REQUEST.getValue()));
			}
			/** 签约申请保存成功推送给医生团队 **/
			 CloudMobilePush push = new CloudMobilePush();
			 //封装推送参数
			 Map<String, Object> pushMap = new HashMap<String, Object>();
			 pushMap.put("requestId", returnMap.get("requestId"));
			 pushMap.put("personId", personId);
			 //获取医生端接收推送的账号列表
			 List<String> accounts = docUserService.getAccountsByTeamId(docTeamId);
			 //获取户主信息
			 PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(personId);
			 String title = "您有新的签约申请";
			 String content = "居民"+personInfo.getPersonName()+"向您发出签约申请";
			 push.androidPush(accounts, title, content,
					 BusinessConstants.PUSH_ACTIVITY, BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_SIGN_REQUEST, pushMap));
			 
			 /**
			 * 插入推送消息表
			 */
			SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_DOC_INT,
					BusinessConstants.PUSH_TYPE_SIGN_REQUEST,JsonUtils.getJsonFormat(pushMap));
			System.out.println(pushMap);
			for (String account : accounts) {
				msg.setDocUserName(account);
				sendMsgService.addMsg(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils
					.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(),
							ApiStatusEnum.FAIL.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/getSignRequestDealFlag", method = RequestMethod.GET)
	@ApiOperation(value = "查看当前签约请求的状态（医生端）", notes = "{'requestId':'请求ID'}")
	public String getSignRequestDealFlag(@RequestParam Integer requestId) {
		SignRequestVo requestDetail = requestService.getRequestDetail(requestId);
		if (requestDetail != null) {
			return JsonUtils.getJson(BaseJsonVo.success(requestDetail.getStatus()));
		}else {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.REQUEST_NOT_FOUND.getKey(),
					ApiStatusEnum.REQUEST_NOT_FOUND.getValue()));
		}
	}
}
