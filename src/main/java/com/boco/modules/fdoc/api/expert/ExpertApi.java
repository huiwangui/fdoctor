package com.boco.modules.fdoc.api.expert;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.im.IMUtils;
import com.boco.common.jedis.JedisUtils;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.utils.FTPUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.model.LoginRecordEntity;
import com.boco.modules.fdoc.model.PersonDeseaseInfoEntity;
import com.boco.modules.fdoc.model.expert.ExpertAccountEntity;
import com.boco.modules.fdoc.model.expert.ExpertInformationEntity;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.LoginRecordService;
import com.boco.modules.fdoc.service.expert.ExpertAccountService;
import com.boco.modules.fdoc.service.expert.ExpertService;
import com.boco.modules.fdoc.service.expert.ExpertUserSignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.DoctorUserVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.UserVo;
import com.boco.modules.fdoc.vo.expert.ExpertAccountVo;
import com.boco.modules.fdoc.vo.expert.ExpertInformationVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/expert", produces = "text/json;charset=UTF-8")
public class ExpertApi {

	@Resource
	ExpertService expertService;
	@Resource
	ExpertAccountService expertAccountService;
	@Resource
	ExpertUserSignService expertUserSignService;
	@Resource
	LoginRecordService recordService;
	
	@RequestMapping(value = "/loginOld", method = RequestMethod.POST)
	@ApiOperation(value = "专家账号登录（医生端）-老版", notes = "{\"userName\":\"用户名\",\"password\":\"密码\"}", response = ExpertAccountEntity.class)
	public String loginOld(@RequestBody String userBody) {
	 
			Map<String, String> jsonMap = JsonUtils.getSingleJsonMap(userBody);
			String userName = jsonMap.get("userName");
			String password = jsonMap.get("password");
			 
			ExpertAccountEntity account = new ExpertAccountEntity();
			account.setUserName(userName);
			account.setPassword(password);
			//登录验证
			account =expertAccountService.verifyAccount(account);
			 
			if (account == null) {
				//若没有查询到该对象，返回用户名或密码错误
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.EXPERT_LOGIN_ERROR.getKey(), ApiStatusEnum.EXPERT_LOGIN_ERROR.getValue()));
			}else {
				ExpertInformationVo expertVo = expertService.getExpertInfoByExpertId(account.getExpertId());		 		
				if (expertVo == null) {
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
				}
				//把用户登录数据存入redis
				Map<String, String> returnDataMap = new HashMap<String, String>();
				returnDataMap.put("userName", userName);
				returnDataMap.put("password", password);
				returnDataMap.put("id", expertVo.getId());
				returnDataMap.put("img", expertVo.getImg());
				 
				returnDataMap.put("expertName", expertVo.getExpertName());
				returnDataMap.put("title", expertVo.getTitle());
				returnDataMap.put("sex", expertVo.getSex());
				//returnDataMap.put("introduction", expertVo.getIntroduction());
				returnDataMap.put("orgId", expertVo.getOrgId());
				returnDataMap.put("orgName", expertVo.getOrgName());
				//returnDataMap.put("specialty", expertVo.getSpecialty());
				returnDataMap.put("phoneNumber", expertVo.getPhoneNumber());
				returnDataMap.put("department", expertVo.getDepartment());
				//returnDataMap.put("personalStyle", expertVo.getPersonalStyle());
				
				for (Map.Entry<String, String> m :returnDataMap.entrySet())  {  
					if (m.getValue() != null && !"".equals(m.getValue())) {
						JedisUtils.set(userName + m.getKey(), m.getValue(),0);
					}
				} 
				 
				/*if (StringUtils.isEmpty(verifyUser.getProductCode())) {
					//如果产品代码为空，则说明是第一次登录，加入字段提示
					returnDataMap.put("firstLogin", "true");
				}else {
					returnDataMap.put("firstLogin", "false");
				}*/
				
				//调用im服务检查是否注册IM，若没有，进行注册
				int flag = IMUtils.expertRegister(returnDataMap);
				if (flag == ApiStatusEnum.FAIL.getKey()) {
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.IM_ERROR.getKey(), ApiStatusEnum.IM_ERROR.getValue()));
				}
				//返回登录结果
				return JsonUtils.getJson(BaseJsonVo.success(returnDataMap));
			}	
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "专家账号登录（医生端）-添加登录信息记录", notes = "{\"userName\":\"用户名\",\"password\":\"密码\" ,\"brandModel\":\"厂商品牌\",\"phoneType\":\"手机类型1安卓2苹果\",\"version\":\"手机系统版本号\",\"imei\":\"IMEI号\"}", response = ExpertAccountEntity.class)
	public String login(@RequestBody String userBody) {
	 
			Map<String, String> jsonMap = JsonUtils.getSingleJsonMap(userBody);
			String userName = jsonMap.get("userName");
			String password = jsonMap.get("password");
			String brandModel =jsonMap.get("brandModel");
			String phoneType=jsonMap.get("phoneType");
			String version=jsonMap.get("version");
			String imei=jsonMap.get("imei");
			
			//登录信息
			LoginRecordEntity lrEntity=new LoginRecordEntity();
			lrEntity.setLoginType(2);
			lrEntity.setLoginAccount(userName);
			lrEntity.setBrandModel(brandModel);
			if(StringUtils.isEmpty(phoneType)){
				lrEntity.setPhoneType(1);
			}else{
				lrEntity.setPhoneType(Integer.valueOf(phoneType));
			}
			lrEntity.setVersion(version);
			lrEntity.setImei(imei);
			lrEntity.setLoginTime(new Date());
			 
			ExpertAccountEntity account = new ExpertAccountEntity();
			account.setUserName(userName);
			account.setPassword(password);
			//登录验证
			account =expertAccountService.verifyAccount(account);
			 
			if (account == null) {
				//若没有查询到该对象，返回用户名或密码错误
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.EXPERT_LOGIN_ERROR.getKey(), ApiStatusEnum.EXPERT_LOGIN_ERROR.getValue()));
			}else {
				ExpertInformationVo expertVo = expertService.getExpertInfoByExpertId(account.getExpertId());		 		
				if (expertVo == null) {
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
				}
				//把用户登录数据存入redis
				Map<String, String> returnDataMap = new HashMap<String, String>();
				returnDataMap.put("userName", userName);
				returnDataMap.put("password", password);
				returnDataMap.put("id", expertVo.getId());
				returnDataMap.put("img", expertVo.getImg());
				 
				returnDataMap.put("expertName", expertVo.getExpertName());
				returnDataMap.put("title", expertVo.getTitle());
				returnDataMap.put("sex", expertVo.getSex());
				//returnDataMap.put("introduction", expertVo.getIntroduction());
				returnDataMap.put("orgId", expertVo.getOrgId());
				returnDataMap.put("orgName", expertVo.getOrgName());
				//returnDataMap.put("specialty", expertVo.getSpecialty());
				returnDataMap.put("phoneNumber", expertVo.getPhoneNumber());
				returnDataMap.put("department", expertVo.getDepartment());
				//returnDataMap.put("personalStyle", expertVo.getPersonalStyle());
				
				for (Map.Entry<String, String> m :returnDataMap.entrySet())  {  
					if (m.getValue() != null && !"".equals(m.getValue())) {
						JedisUtils.set(userName + m.getKey(), m.getValue(),0);
					}
				} 
				 
				/*if (StringUtils.isEmpty(verifyUser.getProductCode())) {
					//如果产品代码为空，则说明是第一次登录，加入字段提示
					returnDataMap.put("firstLogin", "true");
				}else {
					returnDataMap.put("firstLogin", "false");
				}*/
				
				//将登录信息存入登录记录表中
				Integer mark= recordService.insertLoginRecord(lrEntity);
				if(mark<1){
					returnDataMap.put("loginRecord", "fail");
				}else{
					returnDataMap.put("loginRecord", "success");
				}
				
				//调用im服务检查是否注册IM，若没有，进行注册
				int flag = IMUtils.expertRegister(returnDataMap);
				if (flag == ApiStatusEnum.FAIL.getKey()) {
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.IM_ERROR.getKey(), ApiStatusEnum.IM_ERROR.getValue()));
				}
				//返回登录结果
				return JsonUtils.getJson(BaseJsonVo.success(returnDataMap));
			}	
	}
	
	
	@RequestMapping(value = "/getIntroductionOfExpert", method = RequestMethod.GET)
	@ApiOperation(value = "获取专家简介（专家端）", notes = "{'id':'专家id'}")
	public String getIntroductionOfExpert(@RequestParam String id ) {
		ExpertInformationVo vo = new ExpertInformationVo();
	    vo.setId(id);
	    ExpertInformationVo expertVo = expertService.getExpertInfoByExpertId(vo.getId());		 
	    //返回登录结果
		return JsonUtils.getJson(BaseJsonVo.success(expertVo.getIntroduction()));
	}
	@RequestMapping(value = "/getPersonalStyleOfExpert", method = RequestMethod.GET)
	@ApiOperation(value = "获取专家个人风采（专家端）", notes = "{'id':'专家id'}")
	public String getPersonalStyleOfExpert(@RequestParam String id ) {
		ExpertInformationVo vo = new ExpertInformationVo();
	    vo.setId(id);
	    ExpertInformationVo expertVo = expertService.getExpertInfoByExpertId(vo.getId());		 
	    //返回登录结果
		return JsonUtils.getJson(BaseJsonVo.success(expertVo.getPersonalStyle()));
	}
	@RequestMapping(value = "/getSpecialtyOfExpert", method = RequestMethod.GET)
	@ApiOperation(value = "获取专家特长（专家端）", notes = "{'id':'专家id'}")
	public String getSpecialtyOfExpert(@RequestParam String id ) {
		ExpertInformationVo vo = new ExpertInformationVo();
	    vo.setId(id);
	    ExpertInformationVo expertVo = expertService.getExpertInfoByExpertId(vo.getId());		 
	    //返回登录结果
		return JsonUtils.getJson(BaseJsonVo.success(expertVo.getSpecialty()));
	}
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ApiOperation(value = "修改密码（专家端）", notes = "{\"userName\":\"专家登录用户名\",\"password\":\"原密码\",\"newPassword\":\"新密码\"}", response = BaseJsonVo.class)
	public String updateExpertUserPassword(@RequestBody String userBody) {
		ExpertAccountVo vo = JsonUtils.getObject(userBody, ExpertAccountVo.class);	
		//判断新密码是否合法
		if (!MatcherUtiles.noSpecialChar((vo.getNewPassword()))) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PASSWORD_OUTLAW.getKey(), ApiStatusEnum.PASSWORD_OUTLAW.getValue()));
		}
		//判断原密码是否正确
		ExpertAccountEntity entity = new ExpertAccountEntity();
		entity.setUserName(vo.getUserName());
		entity.setPassword(vo.getPassword());
		ExpertAccountEntity verifyUser = expertAccountService.verifyAccount(entity);		 
		if (verifyUser == null) {
			//原密码错误：返回提示
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.OLD_ERROR.getKey(), ApiStatusEnum.OLD_ERROR.getValue()));
		}else {
			//原密码正确：修改密码
			entity.setPassword(vo.getNewPassword());
			int flag = expertService.updateExpertUser(entity, null);
			if(flag==0){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
			}
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	@RequestMapping(value = "/updateIntroduction", method = RequestMethod.POST)
	@ApiOperation(value = "修改个人简介（专家端）", notes = "{\"id\":\"专家id\",\"introduction\":\"简介\"}", response = BaseJsonVo.class)
	public String updateIntroduction(@RequestBody String userBody) {
		ExpertInformationEntity entity = JsonUtils.getObject(userBody, ExpertInformationEntity.class);		 
		int flag = expertService.updateExpertUser(null, entity);
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	@RequestMapping(value = "/updateSpecialty", method = RequestMethod.POST)
	@ApiOperation(value = "修改专长（专家端）", notes = "{\"id\":\"专家id\",\"specialty\":\"特长\"}", response = BaseJsonVo.class)
	public String updateSpecialty(@RequestBody String userBody) {
		ExpertInformationEntity entity = JsonUtils.getObject(userBody, ExpertInformationEntity.class);		 
		int flag = expertService.updateExpertUser(null, entity);
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	@RequestMapping(value = "/updatePhoneNumber", method = RequestMethod.POST)
	@ApiOperation(value = "修改手机号（专家端）", notes = "{\"id\":\"专家id\",\"phoneNumber\":\"手机号码\",\"code\":\"验证码\"}", response = BaseJsonVo.class)
	public String updatePhoneNumber(@RequestBody String userBody) {
		Map<String, String> jsonMap = JsonUtils.getSingleJsonMap(userBody);
		String phoneNumber = jsonMap.get("phoneNumber");	 
		String code = jsonMap.get("code");
		String smsCode = JedisUtils.get("sms"+phoneNumber);
		//对验证码进行判断
		if (StringUtils.isEmpty(smsCode) || !code.equals(smsCode)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.CODE_ERROR.getKey(), ApiStatusEnum.CODE_ERROR.getValue()));
		}
		ExpertInformationEntity entity = new ExpertInformationEntity();
		entity.setId(jsonMap.get("id"));
		entity.setPhoneNumber(phoneNumber);
		//修改手机号
		int flag = expertService.updateExpertUser(null, entity);
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	@RequestMapping(value = "/addPersonalStyle", method = RequestMethod.POST)
	@ApiOperation(value = "添加个人风采（专家端）", notes = "{\"id\":\"'专家id\", \"personalStyle\":\"个人风采(可以上传多个)\"}}", response = BaseJsonVo.class)
	public String addPersonalStyle(@RequestParam String id, @RequestParam("personalStyle") CommonsMultipartFile personalStyle[], HttpServletRequest request) {
		ExpertInformationEntity entity = new ExpertInformationEntity();
		entity.setId(id);
		StringBuilder addStrc = new StringBuilder();
		/**
		 * 上传图片开始
		 */
		try {
			if(personalStyle.length>0){
				for (int i = 0; i < personalStyle.length; i++) {
					//System.out.println(file[i].getOriginalFilename() + "------" + file[i]);
					CommonsMultipartFile img =personalStyle[i];
					if (img != null && img.getOriginalFilename() != null && StringUtils.isNotEmpty(img.getOriginalFilename())) {					
						InputStream is = img.getInputStream();
						
						byte[] bs = new byte[1024];  
				        int len;
						// 生成jpeg图片
						String headSuffix = StringUtils.getSuffix(img.getOriginalFilename()); // 获取后缀名
						String signImgHeadName = UUID.randomUUID().toString()
								.substring(0, 8)
								+ "." + headSuffix; // 重命名为8位随机数
						
						//复制文件到指定路径 
						File saveFile = new File((request.getContextPath() + "/upload/expert/personalStyle/" + signImgHeadName).substring(9)); 
						FileUtils.copyInputStreamToFile(is, saveFile); 
				        //上传文件到服务器 
						FTPUtils.upload(saveFile, "/upload/expert/personalStyle/"); 
						addStrc.append((request.getContextPath() + "/upload/expert/personalStyle/"+ signImgHeadName).substring(9)).append(",");
				        
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		addStrc.deleteCharAt(addStrc.length()-1);
		ExpertInformationVo expertVo = expertService.getExpertInfoByExpertId(entity.getId());	
		
		entity.setPersonalStyle(addStrc.toString()+","+expertVo.getPersonalStyle());
		//修改个人风采
		int flag = expertService.updateExpertUser(null, entity);
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
		
	}
	@RequestMapping(value = "/updateImg", method = RequestMethod.POST)
	@ApiOperation(value = "修改个人头像（专家端）", notes = "{\"id\":\"专家id\", \"img\":\"个人头像\"}}", response = BaseJsonVo.class)
	public String updateImg(@RequestParam String id, @RequestParam("img") CommonsMultipartFile img, HttpServletRequest request) {
		ExpertInformationEntity entity = new ExpertInformationEntity();
		entity.setId(id);
		StringBuilder addStrc = new StringBuilder();
		/**
		 * 上传图片开始
		 */
		try {
			if (img != null && img.getOriginalFilename() != null && StringUtils.isNotEmpty(img.getOriginalFilename())) {
				
				InputStream is = img.getInputStream();
				
				byte[] bs = new byte[1024];  
		        int len;
				// 生成jpeg图片
				String headSuffix = StringUtils.getSuffix(img.getOriginalFilename()); // 获取后缀名
				String signImgHeadName = UUID.randomUUID().toString()
						.substring(0, 8)
						+ "." + headSuffix; // 重命名为8位随机数
				
				//复制文件到指定路径 
				File saveFile = new File((request.getContextPath() + "/upload/expert/img/" + signImgHeadName).substring(9)); 
				FileUtils.copyInputStreamToFile(is, saveFile); 
		        //上传文件到服务器 
				FTPUtils.upload(saveFile, "/upload/expert/img/"); 
		        
		        entity.setImg((request.getContextPath() + "/upload/expert/img/"
						+ signImgHeadName).substring(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//修改个人风采
		int flag = expertService.updateExpertUser(null, entity);
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(entity.getImg()));
		
	}
	@RequestMapping(value = "/getSignedList", method = RequestMethod.GET)
	@ApiOperation(value = "获取与该专家已签约人员列表（专家端）", notes = "{'id':'专家id','personName':'居民姓名'}")
	public String getSignedList(@RequestParam String id,@RequestParam(required=false) String personName,
			@RequestParam Integer pageSize, @RequestParam Integer pageNo) {
		ExpertInformationVo vo = new ExpertInformationVo();
	    vo.setId(id);
	    vo.setPersonName(personName);
		//查询总数
		int count = expertService.getSignedCount(vo);
		vo.setPage(new Page<ExpertInformationVo>(pageNo, pageSize,Long.valueOf(count)));
		//查询列表
		List<PersonInformationVo> signedList = expertService.getSignedList(vo);
		//返回分页对象
		return JsonUtils.getPageJson(count, pageSize, signedList);
	}
	@RequestMapping(value = "/getSignCountOfExpert", method = RequestMethod.GET)
	@ApiOperation(value = "获取专家关注数（专家端）", notes = "{'id':'专家id'}")
	public String getSignCountOfExpert(@RequestParam String id ) {
		ExpertInformationVo vo = new ExpertInformationVo();
	    vo.setId(id);
	    //查询总数
	    int count = expertService.getSignedCount(vo);	 
	    //返回登录结果
	    return JsonUtils.getJson(BaseJsonVo.success(count+""));		 
	}
	@RequestMapping(value = "/deletePersonalStyle", method = RequestMethod.POST)
	@ApiOperation(value = "删除个人风采（专家端）", notes = "{\"id\":\"专家id\", \"personalStyle\":\"个人风采路径\"}}", response = BaseJsonVo.class)
	public String deletePersonalStyle(@RequestBody String userBody) {	
		ExpertInformationEntity entity = JsonUtils.getObject(userBody, ExpertInformationEntity.class);	
		String personalStyle = entity.getPersonalStyle();
		ExpertInformationVo expertVo = expertService.getExpertInfoByExpertId(entity.getId());	
		if(StringUtils.isNoneEmpty(personalStyle)){
			String[] strs =personalStyle.split(",");
		    for (String str : strs) {
		    	String style =expertVo.getPersonalStyle();
				if(style.contains(str)){
					style=style.replace(str, "");
					if(style.contains(",,")){
						style = style.replace(",,",",");
					}
					expertVo.setPersonalStyle(style);
				}
			}
		}
		//处理最后一个字符是，
		String str = expertVo.getPersonalStyle();
		char last = ','	;	
        if(last ==str.charAt(str.length()-1)){
        	str =str.substring(0,str.length()-1);
        	expertVo.setPersonalStyle(str);
        }
        //处理第一个字符是，
        if(",".equalsIgnoreCase(str.substring(0, 1))){
        	str =str.substring(1, str.length());
        	expertVo.setPersonalStyle(str);
        }
		//修改个人风采		 
		entity.setPersonalStyle(expertVo.getPersonalStyle());
		int flag = expertService.updateExpertUser(null, entity);
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));	
	}
	@RequestMapping(value = "/getExpertListOfPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取专家列表（居民端）", notes = "{\"expertName\":\"专家姓名\"}")
	public String getExpertListOfPersonId(@RequestParam(required=false) String expertName,
			@RequestParam Integer pageSize, @RequestParam Integer pageNo) {
		ExpertInformationVo vo = new ExpertInformationVo();
	    vo.setExpertName(expertName);
		//查询总数
		int count = expertService.getSignedExpertCountOfPersonId(vo);
		vo.setPage(new Page<ExpertInformationVo>(pageNo, pageSize,Long.valueOf(count)));
		//查询列表
		List<ExpertInformationVo> signedList = expertService.getSignedExpertListOfPersonId(vo);
		//返回分页对象
		return JsonUtils.getPageJsonWithTotal(count, pageSize, signedList);
	}
	@RequestMapping(value = "/getExpertInfoOfExpertId", method = RequestMethod.GET)
	@ApiOperation(value = "获取专家基本信息（居民端）返回字段说明status：1表示关注 2表示未关注", notes = "{\"id\":\"专家id\",\"personId\":\"居民id\"}")
	public String getExpertInfoOfExpertId(@RequestParam String id,@RequestParam String personId) {
		//基本信息
		ExpertInformationVo expertVo = expertService.getExpertInfoByExpertId(id);	
		//是否关注
		ExpertInformationVo vo = new ExpertInformationVo();
		vo.setPersonId(personId);
		vo.setId(id);
		ExpertInformationVo reVo =expertService.getRelationship(vo);
		
		Map<String, String> returnDataMap = new HashMap<String, String>();
		if(reVo==null){
			returnDataMap.put("status","2");
 		}else{
 			returnDataMap.put("status","1");
 		}
		returnDataMap.put("img", expertVo.getImg());
		
		
		returnDataMap.put("expertName", expertVo.getExpertName());
		returnDataMap.put("title", expertVo.getTitle());
		returnDataMap.put("sex", expertVo.getSex());
		returnDataMap.put("orgName", expertVo.getOrgName());

		returnDataMap.put("department", expertVo.getDepartment());
		//返回登录结果
		return JsonUtils.getJson(BaseJsonVo.success(returnDataMap));
	}
	@RequestMapping(value = "/addExpertAndResidentRelationship", method = RequestMethod.POST)
	@ApiOperation(value = "点击关注（居民端）", notes = "{\"id\":\"专家id\",\"personId\":\"居民id\"}", response = BaseJsonVo.class)
	public String addExpertAndResidentRelationship(@RequestBody String userBody) {
		ExpertInformationVo entity = JsonUtils.getObject(userBody, ExpertInformationVo.class);
		String id= entity.getId();
		entity.setId("");
		//先查询是否关注了
		ExpertInformationVo reVo =expertService.getRelationship(entity);
		if(reVo !=null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.EXPERT_RELATION_ERROR.getKey(), ApiStatusEnum.EXPERT_RELATION_ERROR.getValue()));
		}
		entity.setId(id);
		int flag = expertService.addExpertAndResidentRelationship(entity);
		
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	@RequestMapping(value = "/cancelExpertAndResidentRelationship", method = RequestMethod.POST)
	@ApiOperation(value = "取消关注（居民端）", notes = "{\"id\":\"专家id\",\"personId\":\"居民id\"}", response = BaseJsonVo.class)
	public String cancelExpertAndResidentRelationship(@RequestBody String userBody) {
		ExpertInformationVo entity = JsonUtils.getObject(userBody, ExpertInformationVo.class);		 
		int flag = expertService.cancelExpertAndResidentRelationship(entity);
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	@RequestMapping(value = "/getExpertIdOfPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取专家id（居民端）", notes = "{\"personId\":\"居民id\"}")
	public String getExpertIdOfPersonId(@RequestParam String personId) {		 
		//是否关注
		ExpertInformationVo vo = new ExpertInformationVo();
		vo.setPersonId(personId);		 
		ExpertInformationVo reVo =expertService.getRelationship(vo);
		if(reVo!=null){
			//返回登录结果
			return JsonUtils.getJson(BaseJsonVo.success(reVo.getId()));
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.EXPERT_NO_SIGN.getKey(), ApiStatusEnum.EXPERT_NO_SIGN.getValue())); 
		}
		
	}
	@RequestMapping(value = "/getExpertInfoOfPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取专家基本信息（居民端）", notes = "{\"personId\":\"居民id\"}")
	public String getExpertInfoOfPersonId(@RequestParam String personId) {		 
		//是否关注
		ExpertInformationVo vo = new ExpertInformationVo();
		vo.setPersonId(personId);		 
		ExpertInformationVo reVo =expertService.getExpertInfoOfPersonId(vo);
		if(reVo==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.EXPERT_NO_SIGN.getKey(), ApiStatusEnum.EXPERT_NO_SIGN.getValue())); 
		}else{
			//设置返回的是专家信息
			reVo.setDocType("4");
			//返回登录结果
			return JsonUtils.getJson(BaseJsonVo.success(reVo));	
		}
		
	}
}
