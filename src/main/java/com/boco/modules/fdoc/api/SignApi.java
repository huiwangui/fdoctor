package com.boco.modules.fdoc.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
import com.boco.common.utils.FTPUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.annotation.SelectCache;
import com.boco.modules.fdoc.model.PersonDeseaseInfoEntity;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.model.UserDocSignEntity;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SendMsgService;
import com.boco.modules.fdoc.service.SigServicepackService;
import com.boco.modules.fdoc.service.SignRequestService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.service.UserService;
import com.boco.modules.fdoc.service.surrender.SurrenderRequestService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SignRequestVo;
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.UserVo;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 家庭医生签约API
 * 
 * @author q
 *
 */

@RestController
@RequestMapping(value = "/sign", produces = "text/json;charset=UTF-8")
public class SignApi extends BaseController {

	@Resource
	SignService signService;
	@Resource
	UserService userService;
	@Resource
	SigServicepackService sigServicepackService;
	@Resource
	PersonInformationService personService;
	@Resource
	DocService docService;
	@Resource
	DocUserService docUserService;
	@Resource
	SignRequestService requestService;
	@Resource
	SendMsgService sendMsgService;
	@Resource
	SurrenderRequestService surrenderRequestService;

	// @SelectCache(url="/sign/getPersonList")
	@RequestMapping(value = "/getPersonList", method = RequestMethod.GET)
	@ApiOperation(value = "获取人员列表（医生端）", notes = "{'personName':'居民姓名'}")
	public String getPersonList(@RequestParam String personName, @RequestParam Integer pageSize,
			@RequestParam Integer pageNo) {
		// 封装查询参数
		PersonInformationVo vo = new PersonInformationVo();
		vo.setPersonName(personName);

		// 获取总数
		Integer count = signService.getPersonCountWithSignInfo(vo);
		// 封装分页参数，获取人员列表
		Page<PersonInformationVo> page = new Page<>(pageNo, pageSize, Long.valueOf(count));
		vo.setPage(page);
		List<PersonInformationVo> list = signService.getPersonListWithSignInfo(vo);
		// 返回分页对象
		return JsonUtils.getPageJson(count, pageSize, list);
	}

	@RequestMapping(value = "/getSimpleSignInfo", method = RequestMethod.GET)
	@ApiOperation(value = "获取人员简单签约信息（医生端）", notes = "{'PersonId':'居民ID'}")
	public String getSimpleSignInfo(@RequestParam String personId) {
		// 封装查询参数
		SignVo signInfo = signService.getSimpleSignInfo(personId);
		// 返回分页对象
		return JsonUtils.getJson(BaseJsonVo.success(signInfo));
	}

	@RequestMapping(value = "/getSignedList", method = RequestMethod.GET)
	@ApiOperation(value = "获取该医生已签约人员列表（医生端）", notes = "{'userName':'登录医生用户名','personName':'居民姓名'}")
	public String getSignedList(@RequestParam String userName, @RequestParam String personName,
			@RequestParam Integer pageSize, @RequestParam Integer pageNo) {
		// 根据用户名获取医生信息并判断医生是否存在
		DoctorDetailVo doctor = docUserService.getDoctorByUsername(userName);
		if (doctor == null) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
		// 封装查询参数
		SignVo vo = new SignVo();
		vo.setPersonName(personName);
		vo.setDocTeamId(doctor.getTeamId());
		// 查询总数
		Integer count = signService.getSignedCount(vo);
		// 封装分页对象
		vo.setPage(new Page<SignVo>(pageNo, pageSize, count));
		// 查询列表
		List<PersonInformationVo> signedList = signService.getSignedList(vo);
		// 返回分页对象
		return JsonUtils.getPageJson(count, pageSize, signedList);
	}

	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	@ApiOperation(value = "签约（医生端）", notes = "{'userName':'医生登录用户名','personId':'签约居民ID','phoneNumber':'手机号','img':'图片file对象','servicePackValue':'所选服务包权值','endDate':'签约有效期截止时间戳','labelValue':'慢病值'}")
	public String sign(@RequestParam String userName, @RequestParam String phoneNumber, @RequestParam String personId,
			@RequestParam String remark, @RequestParam("file") CommonsMultipartFile img,
			@RequestParam Integer labelValue, HttpServletRequest request, @RequestParam Integer servicePackValue,
			@RequestParam Long endDate) {
		// 修改电话号码
		PersonInformationVo vo = new PersonInformationVo();
		vo.setPersonId(personId);
		vo.setPhoneNumber(phoneNumber);
		// 验证手机号是否合法
		if (!MatcherUtiles.mobileMach(phoneNumber)) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue()));
		}
		int flag = personService.updateMobile(vo);
		if (flag == 0) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
		Map<String, Object> returnMap;
		/** 获取医生ID、医生姓名、医生所属机构名称 **/
		DoctorDetailVo doctor = docUserService.getDoctorByUsername(userName);
		if (doctor == null) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}

		try {
			UserDocSignEntity en = new UserDocSignEntity();
			/**
			 * 图片上传部分
			 */
			if (img != null && img.getOriginalFilename() != null && StringUtils.isNotEmpty(img.getOriginalFilename())) {
				InputStream is = img.getInputStream();

				// 生成jpeg图片
				String headSuffix = StringUtils.getSuffix(img.getOriginalFilename()); // 获取后缀名
				String signImgHeadName = UUID.randomUUID().toString().substring(0, 8) + "." + headSuffix; // 重命名为8位随机数

				// 复制文件到指定路径
				File saveFile = new File((request.getContextPath() + "/upload/img/" + signImgHeadName).substring(9));
				FileUtils.copyInputStreamToFile(is, saveFile);
				// 上传文件到服务器
				FTPUtils.upload(saveFile, "/upload/img/");

				en.setSignImg((request.getContextPath() + "/upload/img/" + signImgHeadName).substring(9));
			}
			/**
			 * 解析疾病信息json
			 */
			List<PersonDeseaseInfoEntity> deseaseInfoList = new ArrayList<PersonDeseaseInfoEntity>();

			PersonDeseaseInfoEntity entity = null;
			Integer deseaseInfo = labelValue;
			if (deseaseInfo == 1) {
				entity = new PersonDeseaseInfoEntity(personId, "0", "0", "0", "0", "0", "0", "0");
			} else {
				entity = new PersonDeseaseInfoEntity(personId, deseaseInfo);
			}
			deseaseInfoList.add(entity);

			/**
			 * 设置持久化对象信息（签约对象和签约协议对象）
			 */
			en.setDocTeamId(doctor.getTeamId());
			en.setPersonId(personId);
			en.setDocOrgName(doctor.getOrgName());
			en.setDocOrgAddress(doctor.getOrgAddress());
			en.setSignType(BusinessConstants.SIGN_TYPE_DOC); // 设置签约类型为医生端主动签约
			en.setRemark(remark);
			en.setServicePackValue(servicePackValue);
			en.setDueTime(new Date(endDate));

			returnMap = signService.sign(en, deseaseInfoList); // 执行新签约操作
			String signResult = (String) returnMap.get("returnMsg");
			if (BusinessConstants.ERROR_RE_SIGN.equals(signResult)) {
				// 重复签约
				return JsonUtils
						.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_SIGN.getKey(), ApiStatusEnum.RE_SIGN.getValue()));
			} else if (BusinessConstants.FAIL.equals(signResult)) {
				// 签约失败
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
			}
		} catch (IOException e) {
			// 图片上传失败导致签约失败
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}

		try {
			/** 签约成功推送给用户 **/
			Map<String, Object> pushMap = new HashMap<String, Object>();
			pushMap.put("signId", returnMap.get("signId"));
			CloudMobilePush push = new CloudMobilePush();
			// 获取推送居民账号列表
			String account = userService.getAccountsByPersonId(personId);
			if (StringUtils.isNotEmpty(account)) {
				// 获取医生团队队长信息
				DoctorDetailVo teamLeaderInfo = docService.getTeamLeaderInfo(doctor.getTeamId());

				// 推送安卓端
				List<String> accounts = new ArrayList<String>();
				accounts.add(account); // 将居民账号加入推送列表

				String title = "您已成功签约";
				String content = "您已和" + teamLeaderInfo.getDocName() + "医生团队成功签约！";
				push.androidPush(accounts, title, content, BusinessConstants.PUSH_ACTIVITY,
						BusinessConstants.PUSH_ITEM_USER,
						PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_SIGN, pushMap));
				/**
				 * 插入推送消息表
				 */
				SendMsgEntity msg = new SendMsgEntity(title, content, BusinessConstants.PUSH_ITEM_USER_INT,
						BusinessConstants.PUSH_TYPE_SIGN, JsonUtils.getJsonFormat(pushMap));
				msg.setUid(account);
				sendMsgService.addMsg(msg);
			}

			return JsonUtils.getJson(BaseJsonVo.success(null));
		} catch (Exception e) {
			// 推送信息失败，不影响签约
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}

	}

	@RequestMapping(value = "/getSignDetail", method = RequestMethod.GET)
	@ApiOperation(value = "获取签约详情（医生端）", notes = "{'signId':'签约ID'}")
	public String getSignDetail(@RequestParam Integer signId) {
		SignVo vo = signService.getSignDetail(signId);
		return JsonUtils.getJson(BaseJsonVo.success(vo));

	}

	@RequestMapping(value = "/getSignDetailBySignId", method = RequestMethod.GET)
	@ApiOperation(value = "通过签约ID获取签约详情（医生端）返回surrenderStatus字段说明：1：解约申请中  2：拒绝解约  3：签约", notes = "{'signId':'签约ID'}")
	public String getSignDetailBySignId(@RequestParam Integer signId) {
		// 返回参数封装
		SignVo vo = signService.getSignDetail(signId);
		// 封装参数
		SurrenderRequestEntity entity = new SurrenderRequestEntity();
		entity.setPersonId(vo.getPersonId());
		entity.setStatus("1");
		// 查询是否有解约申请
		SurrenderRequestVo svo = surrenderRequestService.getSurrenderDetailByPersonId(entity);
		if (svo != null) {
			vo.setSurrenderStatus(1);
		} else {
			// 查询拒绝解约
			entity.setStatus("2");
			svo = surrenderRequestService.getSurrenderDetailByPersonId(entity);
			if (svo != null) {
				vo.setSurrenderStatus(2);
			} else {
				vo.setSurrenderStatus(3);
			}

		}
		return JsonUtils.getJson(BaseJsonVo.success(vo));

	}

	@RequestMapping(value = "/getSignDetailByUser", method = RequestMethod.GET)
	@ApiOperation(value = "获取签约详情（居民端）", notes = "{'idCard':'居民身份证号'}")
	public String getSignDetailByUser(@RequestParam String idCard) {
		SignVo vo = signService.getSignDetailByIdCard(idCard);
		if (vo == null) {
			return JsonUtils
					.getJson(BaseJsonVo.empty(ApiStatusEnum.NOT_SIGN.getKey(), ApiStatusEnum.NOT_SIGN.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(vo));
	}

	@RequestMapping(value = "/getDoctorTeam", method = RequestMethod.GET)
	@ApiOperation(value = "获取医生团队成员信息（医生端）", notes = "{\"doctorId\":\"医生ID\"}", response = BaseJsonVo.class)
	public String getDoctorTeam(@RequestParam String doctorId) {
		return JsonUtils.getJson(BaseJsonVo.success(docService.getDoctorTeamMemberByDocId(doctorId)));
	}

	@RequestMapping(value = "/getEveryCount", method = RequestMethod.GET)
	@ApiOperation(value = "获取医生团队下已拒绝、待确认、已签约数量、解约记录（医生端）", notes = "{'docTeamId':'医生团队ID'}")
	public String getEveryCount(@RequestParam String docTeamId) {
		SignRequestVo vo = new SignRequestVo();
		vo.setDocTeamId(docTeamId);
		// 待确认
		vo.setStatus(BusinessConstants.SIGN_REQUEST_READY);
		Integer readyCount = requestService.getRequestCount(vo);
		// 已拒绝
		vo.setStatus(BusinessConstants.SIGN_REQUEST_REFUSED);
		Integer confuseCount = requestService.getRequestCount(vo);
		// 已签约
		SignVo signVo = new SignVo();
		signVo.setDocTeamId(docTeamId);
		Integer signedCount = signService.getSignedCount(signVo);
		// 解约记录
		int surrenderCount = surrenderRequestService.getSurrenderRequestCountByDocTeamId(docTeamId);
		// 封装返回map
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		returnMap.put("readyCount", readyCount);
		returnMap.put("confuseCount", confuseCount);
		returnMap.put("signedCount", signedCount);
		returnMap.put("surrenderCount", surrenderCount);

		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}

	@RequestMapping(value = "/getSignDoctorTeamInfo", method = RequestMethod.GET)
	@ApiOperation(value = "获取签约的医生团队信息（居民端）", notes = "{'personId':'居民ID'}")
	public String getSignDoctorTeamInfo(@RequestParam String personId) {

		List<DoctorDetailVo> doctorInfo = signService.getSignDoctorTeamInfo(personId);

		return JsonUtils.getJson(BaseJsonVo.success(doctorInfo));
	}

	@RequestMapping(value = "/getSignDoctorTeamInfoByPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "通过居民personId获取签约的医生团队信息（居民端）返回status字段说明：1：解约申请中 2：签约", notes = "{'personId':'居民ID'}")
	public String getSignDoctorTeamInfoByPersonId(@RequestParam String personId) {
		// 返回参数封装
		Map<String, Object> values = new HashMap<>();
		/*
		 * List<DoctorDetailVo> doctorInfo =
		 * signService.getSignDoctorTeamInfo(personId);
		 * values.put("SignDoctorInfo",doctorInfo);
		 */
		// 封装参数
		SurrenderRequestEntity entity = new SurrenderRequestEntity();
		entity.setPersonId(personId);
		entity.setStatus("1");
		// 查询是否有解约申请
		SurrenderRequestVo svo = surrenderRequestService.getSurrenderDetailByPersonId(entity);
		int status = 0;
		if (svo != null) {
			// values.put("status", 1);
			status = 1;
		} else {
			status = 2;
			// 查询拒绝解约
			/*
			 * entity.setStatus("2"); svo =
			 * surrenderRequestService.getSurrenderDetailByPersonId(entity); if(svo!=null){
			 * //values.put("status", 2); status=2; }else{ //values.put("status", 3);
			 * status=3; }
			 */

		}
		return JsonUtils.getJson(BaseJsonVo.success(status + ""));
		// return JsonUtils.getJson(BaseJsonVo.success(values));
	}

	@RequestMapping(value = "/getDoctorTeamFromUser", method = RequestMethod.GET)
	@ApiOperation(value = "获取医生团队信息（居民端）", notes = "{'docTeamId':'医生团队ID'}")
	public String getDoctorTeamFromUser(@RequestParam String docTeamId) {
		List<DoctorDetailVo> list = docService.getDoctorTeamMemberByTeamId(docTeamId);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}

	@RequestMapping(value = "/getSignedUserAccound", method = RequestMethod.GET)
	@ApiOperation(value = "获取医生签约的用户APP账号信息（医生端）", notes = "{'docTeamId':'医生团队ID', 'personName':'居民姓名'}")
	public String getSignedUserAccound(@RequestParam String docTeamId, @RequestParam String personName,
			@RequestParam Integer pageSize, @RequestParam Integer pageNo) {
		// 封装查询参数
		SignVo signVo = new SignVo();
		signVo.setDocTeamId(docTeamId);
		signVo.setPersonName(personName);
		// 查询总数
		Integer count = signService.getSignedUserAccoundCount(signVo);
		// 封装分页对象
		signVo.setPage(new Page<SignVo>(pageNo, pageSize, count));
		List<UserVo> list = signService.getSignedUserAccound(signVo);

		return JsonUtils.getPageJsonWithTotal(count, pageSize, list);
	}

	@RequestMapping(value = "/getServicePacks", method = RequestMethod.GET)
	@ApiOperation(value = "查询所有服务包（通用）")
	public String getServicePacks() {
		return JsonUtils.getJson(BaseJsonVo.success(sigServicepackService.getAll()));
	}

	@RequestMapping(value = "/getServicePackDetails", method = RequestMethod.GET)
	@ApiOperation(value = "查询服务包详细信息（通用）", notes = "{'servicepackId':'服务包ID，为0时查询所有服务包'}")
	public String getServicePackDetails(@RequestParam Integer servicepackId) {
		if (servicepackId == null || servicepackId == 0) {
			return JsonUtils.getJson(BaseJsonVo.success(sigServicepackService.getAllDetails()));
		} else {
			return JsonUtils.getJson(BaseJsonVo.success(sigServicepackService.getDetailsByPackId(servicepackId)));
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getLableValue", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民建档标签数值（医生端）", notes = "{'userName':'登录医生用户名',  'personId' : '居民ID'}")
	public String getLableValue(@RequestParam String userName, @RequestParam String personId) {
		int lableValue = 0;
		// 医生端调用，获取该医生的productCode
		DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
		if (docUser == null) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
		String productCode = docUser.getProductCode();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PersonInformationVo vo = personService.getPersonDetailByPersonId(personId);
		paramMap.put("KeyCode", "2");
		paramMap.put("KeyValue", vo.getIdCard());
		paramMap.put("PageIndex", 0);
		paramMap.put("PageSize", 1);
		paramMap.put("ProductCode", productCode);
		paramMap.put("RegionCode", docUser.getRegionCode());
		String returnData = RestfulUtils.getPublicData("55-12", paramMap);
		// 解析返回JSON
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(returnData);
		if ((Integer) jsonMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
		} else {
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonMap.get("Msg");
			if (0 != dataList.size()) {
				if (null != dataList.get(0).get("TT")) {
					lableValue += 4;
				}
				if (null != dataList.get(0).get("TJ")) {
					lableValue += 16;
				}
				if (null != dataList.get(0).get("TG")) {
					lableValue += 2;
				}
				if (vo.getAge() >= 65) {
					lableValue += 32;
				} else if (vo.getAge() <= 6) {
					lableValue += 8;
				}
			}
		}

		Map<String, Object> sParamMap = new HashMap<String, Object>();
		sParamMap.put("ProductCode", productCode);
		sParamMap.put("PersonID", vo.getJwPersonId());
		String sReturnData = RestfulUtils.getPublicData("63-0", sParamMap);
		Map<String, Object> sJsonMap = JsonUtils.getObjectJsonMap(sReturnData);
		Map<String, Object> dataMap = (Map<String, Object>) sJsonMap.get("Msg");
		if ("true".equals(dataMap.get("status"))) {
			lableValue += 128;
		}

		return JsonUtils.getJson(BaseJsonVo.success(lableValue + ""));
	}
}
