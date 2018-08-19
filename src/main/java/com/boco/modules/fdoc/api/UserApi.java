package com.boco.modules.fdoc.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.hibernate.jpa.criteria.ValueHandlerFactory.BaseValueHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Decoder;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.exception.IMException;
import com.boco.common.im.IMUtils;
import com.boco.common.jedis.JedisUtils;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.push.CloudMobilePush;
import com.boco.common.push.PushUtils;
import com.boco.common.utils.FTPUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.model.LoginRecordEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.model.UserReportEntity;
import com.boco.modules.fdoc.service.CommonService;
import com.boco.modules.fdoc.service.DiseaseLabelService;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.LoginRecordService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SendMsgService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.service.UserService;
import com.boco.modules.fdoc.vo.DiseaseLabelVo;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.DoctorUserVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.UserParamVo;
import com.boco.modules.fdoc.vo.UserReportParamVo;
import com.boco.modules.fdoc.vo.UserReportVo;
import com.boco.modules.fdoc.vo.UserVo;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * 用户 api
 * 
 * @author sufj
 *
 */
@RestController
@RequestMapping(value = "/user", produces = "text/json;charset=UTF-8")
public class UserApi extends BaseController {

	@Resource
	UserService userService;
	@Resource
	DocService docService;
	@Resource
	DocUserService docUserService;
	@Resource
	DiseaseLabelService labelService;
	@Resource
	SignService signService;
	@Resource
	PersonInformationService personService;
	@Resource
	SendMsgService sendMsgService;
	@Resource
	LoginRecordService recordService;
	
	@RequestMapping(value = "/getAllUserReport", method = RequestMethod.GET)
	@ApiOperation(value = "获取用户上传的所有报告信息（居民端）", notes = "{'personId':'用户personId'}", response = BaseJsonVo.class)
	public String getAllUserReport(@RequestParam String personId,HttpServletRequest request, @RequestParam Integer pageSize,@RequestParam Integer pageNo) {
	    // 封装查询对象
		UserReportVo userReportVo = new UserReportVo();
		userReportVo.setPersonId(personId);
		
		List<UserReportVo> list = userService.getCountByUid(personId);
		// 封装分页对象
		Page<UserReportVo> page = new Page<UserReportVo>(pageNo,pageSize, list.size());
		userReportVo.setPage(page);
		List<UserReportVo> uVo =userService.getAllUserReportByUid(userReportVo);
	    if(uVo.size()>0){
	    	for(int i=0;i<uVo.size();i++){
	    		UserReportVo vo =uVo.get(i);
	    		String status = vo.getStatus();
	    		if("0".equals(status)){
	    			//居民本人上传的报告
	    			UserReportVo rvo = userService.getPersonNameByPersonId(vo.getPersonId());
	    		    uVo.get(i).setPersonName(rvo.getPersonName());
	    		}else if("1".equals(status)){
	    			//医生上传的报告
	    			UserReportVo rvo = userService.getDoctorNameBydoctorId(vo.getDoctorId());
	    			uVo.get(i).setDocName(rvo.getDocName());
	    		}
	    	}
	    	// return JsonUtils.getPageJson(uVo.size(), pageSize, uVo);
	    }
		//return JsonUtils.getJson(BaseJsonVo.success());
	    
	  //返回分页对象
	  return JsonUtils.getPageJson(list.size(), pageSize, uVo);
		 
	}
	@RequestMapping(value = "/getSingleUserReportByUid", method = RequestMethod.GET)
	@ApiOperation(value = "获取用户上传的最近报告信息（居民端）", notes = "{'personId':'用户personId'}", response = BaseJsonVo.class)
	public String getSingleUserReportByUid(@RequestParam String personId,HttpServletRequest request) {
	    UserReportVo uVo =userService.getSingleUserReportByUid(personId);
	    if(uVo!=null){
    	    String status = uVo.getStatus();
    		if("0".equals(status)){
    			//居民本人上传的报告
    			UserReportVo rvo = userService.getPersonNameByPersonId(uVo.getPersonId());
    		    uVo.setPersonName(rvo.getPersonName());
    		}else if("1".equals(status)){
    			//医生上传的报告
    			UserReportVo rvo = userService.getDoctorNameBydoctorId(uVo.getDoctorId());
    			uVo.setDocName(rvo.getDocName());
    		}
    		return JsonUtils.getJson(BaseJsonVo.success(uVo)); 
	    }
	    return JsonUtils.getJson(BaseJsonVo.success(new UserReportVo())); 
		 
	}
	@RequestMapping(value = "/getAllUserReportByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "获取用户上传的所有报告信息（医生端）", notes = "{'userName':'医生登录用户名','personId':'用户personId'}", response = BaseJsonVo.class)
	public String getAllUserReportByDoc(@RequestParam String userName,@RequestParam String personId,HttpServletRequest request, @RequestParam Integer pageSize,@RequestParam Integer pageNo) {
		// 封装查询对象
		UserReportParamVo pvo =new UserReportParamVo();
		pvo.setPersonId(personId);
		pvo.setUserName(userName); 
		
		UserParamVo userParamVo = new UserParamVo();
	    userParamVo.setPersonId(personId);
	    userParamVo.setUserName(userName);
	    List<UserReportVo> list =userService.getCountByPersonId(userParamVo);
	    
		// 封装分页对象
		Page<UserReportParamVo> page = new Page<UserReportParamVo>(pageNo,pageSize, list.size());
		pvo.setPage(page);
		List<UserReportVo> uVo =userService.getAllUserReportByPersonId(pvo);
	    if(uVo.size()>0){
	    	for(int i=0;i<uVo.size();i++){
	    		UserReportVo vo =uVo.get(i);
	    		String status = vo.getStatus();
	    		if("0".equals(status)){
	    			//居民本人上传的报告
	    			UserReportVo rvo = userService.getPersonNameByPersonId(vo.getPersonId());
	    		    uVo.get(i).setPersonName(rvo.getPersonName());
	    		}else if("1".equals(status)){
	    			//医生上传的报告
	    			UserReportVo rvo = userService.getDoctorNameBydoctorId(vo.getDoctorId());
	    			uVo.get(i).setDocName(rvo.getDocName());
	    		}
	    	}
	    	//return JsonUtils.getPageJson(uVo.size(), pageSize, uVo);
	    }
	    
	    return JsonUtils.getPageJson(list.size(), pageSize, uVo);
		 
	}
	@RequestMapping(value = "/getSingleUserReportByPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取用户上传的最近报告信息（医生端）", notes = "{'userName':'医生登录用户名','personId':'用户personId'}", response = BaseJsonVo.class)
	public String getSingleUserReportByPersonId(@RequestParam String userName,@RequestParam String personId,HttpServletRequest request) {
		UserReportParamVo pvo =new UserReportParamVo();
		pvo.setPersonId(personId);
		pvo.setUserName(userName);
		UserReportVo uVo =userService.getSingleUserReportByPersonId(pvo);
		if(uVo!=null){
    	    String status = uVo.getStatus();
    		if("0".equals(status)){
    			//居民本人上传的报告
    			UserReportVo rvo = userService.getPersonNameByPersonId(uVo.getPersonId());
    		    uVo.setPersonName(rvo.getPersonName());
    		}else if("1".equals(status)){
    			//医生上传的报告
    			UserReportVo rvo = userService.getDoctorNameBydoctorId(uVo.getDoctorId());
    			uVo.setDocName(rvo.getDocName());
    		}
    		return JsonUtils.getJson(BaseJsonVo.success(uVo));
	    }else {
			return JsonUtils.getJson(BaseJsonVo.success(new UserReportVo()));
		}
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ApiOperation(value = "修改用户信息（居民端）", notes = "{'uid':'用户uid','nickname':'昵称','sex':'性别','dateOfBirthStamp':'出生年月时间戳','height':'身高','weitht':'体重',"
			+ "'img':'头像图片对象'}}", response = BaseJsonVo.class)
	public String updateUser(@RequestParam String uid,@RequestParam String nickname,@RequestParam String sex,@RequestParam Long dateOfBirthStamp,
			@RequestParam Double height,@RequestParam Double weight,@RequestParam("file") CommonsMultipartFile img, HttpServletRequest request) {
		// 封装对象
		UserEntity entity = new UserEntity();
		entity.setUid(uid);
		entity.setNickname(nickname);
		entity.setSex(sex);
		entity.setDateOfBirth(new Date(dateOfBirthStamp));
		entity.setHeight(height);
		entity.setWeight(weight);
		
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
				File saveFile = new File((request.getContextPath() + "/upload/img/" + signImgHeadName).substring(9)); 
				FileUtils.copyInputStreamToFile(is, saveFile); 
		        //上传文件到服务器 
				FTPUtils.upload(saveFile, "/upload/img/"); 
		        
		        entity.setImg((request.getContextPath() + "/upload/img/"
						+ signImgHeadName).substring(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer updateUser = userService.updateUser(entity);
		if (updateUser == 0) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT.getKey(), ApiStatusEnum.USER_NOT.getValue())); 
		}
		UserVo userDetail = userService.getUserDetail(uid);
		return JsonUtils.getJson(BaseJsonVo.success(userDetail));
	}
	
	@RequestMapping(value = "/uploadUserReport", method = RequestMethod.POST)
	@ApiOperation(value = "上传用户报告信息（居民端）", notes = "{'personId':'用户personId','reportDescription':'报告描述','imgTime':'报告拍片日期时间戳','img':'报告图片对象(可以上传多个)'}}", response = BaseJsonVo.class)
	public String uploadUserReport(@RequestParam String personId,@RequestParam String reportDescription,@RequestParam Long imgTime,@RequestParam("file") CommonsMultipartFile file[], HttpServletRequest request) {
		// 封装对象
		UserReportEntity entity = new UserReportEntity();
		entity.setImgTime(new Date(imgTime)); 
		entity.setUploadTime(new Date());
	    entity.setReportDescription(reportDescription);
	    //查询出居民签约医生的docid及居民personid
	    UserReportVo uVo =userService.getdoctorIdByPersonId(personId);
	    entity.setPersonId(uVo.getPersonId());
	   // entity.setDoctorId(uVo.getDoctorId());
	    entity.setDocTeamId(uVo.getDocTeamId());
	    entity.setStatus("0");//居民上传
		StringBuilder addStrc = new StringBuilder();
		/**
		 * 上传图片开始
		 */
		try {
			if(file.length>0){
				for (int i = 0; i < file.length; i++) {
					//System.out.println(file[i].getOriginalFilename() + "------" + file[i]);
					CommonsMultipartFile img =file[i];
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
						File saveFile = new File((request.getContextPath() + "/upload/img/report/" + signImgHeadName).substring(9)); 
						FileUtils.copyInputStreamToFile(is, saveFile); 
				        //上传文件到服务器 
						FTPUtils.upload(saveFile, "/upload/img/report/"); 
						addStrc.append((request.getContextPath() + "/upload/img/report/"+ signImgHeadName).substring(9)).append(",");
				       // entity.setImg((request.getContextPath() + "/upload/img/report"+ signImgHeadName).substring(9));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		addStrc.deleteCharAt(addStrc.length()-1);
		/*
		 * 1.根据uid查询出此人对应的上传图片记录
		 * 2.有,就添加此人的上传图片,没有此人的记录就增加一条记录
		 * 
		 */
		//UserReportVo rvo =userService.getPersonImgByUid(uVo.getPersonId());		 
		entity.setImg(addStrc.toString());
		Integer flag=1;//判断上传是否成功
		flag =userService.insertUserReportByUser(entity);
		/*if(rvo==null){
			entity.setImg(addStrc.toString());
			flag =userService.insertUserReportByDoc(entity);		
		}else{
			String url =rvo.getImg()+addStrc.toString();
			entity.setImg(url);
			flag =userService.updateUserReport(entity);
		}*/
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DEAL_FAIL.getKey(), ApiStatusEnum.DEAL_FAIL.getValue())); 
		}
		
		 
		//UserReportVo vo = userService.getUserReportByUid(uid);			
		PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(entity.getPersonId());
		Map<String, Object> pushMap = new HashMap<String, Object>(); 
		pushMap.put("uploadTime", new Date().getTime());
		pushMap.put("reportDescription", reportDescription);
		pushMap.put("imgTime", imgTime);
		pushMap.put("img", addStrc.toString());	

		pushMap.put("personName", personInfo.getPersonName());
		// 获取推送账户
		List<String> accounts = signService.getSignTeamUsers(entity.getPersonId());
		CloudMobilePush push = new CloudMobilePush();
		String title = "新的报告";
		String content = "您的签约居民"+personInfo.getPersonName()+"新上传了报告，点击查看！";//personInfo.getPersonName()
		push.androidPush(accounts, title, content, BusinessConstants.PUSH_ACTIVITY, 
				BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_REPORT_SENG, pushMap));
		//UserReportVo vo = new UserReportVo();
		/**
		 * 插入推送消息表
		 */
		SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_DOC_INT,
				BusinessConstants.PUSH_TYPE_REPORT_SENG,JsonUtils.getJsonFormat(pushMap));
		for (String account : accounts) {
			msg.setDocUserName(account);
			sendMsgService.addMsg(msg);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
		 
	}
	
	@RequestMapping(value = "/uploadUserReportByDoc", method = RequestMethod.POST,produces = "multipart/form-data;charset=UTF-8")
	@ApiOperation(value = "上传用户报告信息（医生端）", notes = "{'userName':'医生登录用户名','personId':'用户personId','reportDescription':'报告描述','imgTime':'报告拍片日期时间戳','img':'报告图片对象(可以上传多个)'}", response = BaseJsonVo.class)
	public String uploadUserReportByDoc(@RequestParam String userName,@RequestParam String personId,@RequestParam String reportDescription,@RequestParam Long imgTime,@RequestParam("file") CommonsMultipartFile file[], HttpServletRequest request) {		
		 // 封装对象
		UserReportEntity entity = new UserReportEntity();	
		entity.setPersonId(personId);
		entity.setImgTime(new Date(imgTime)); 
		entity.setUploadTime(new Date());
	    entity.setReportDescription(reportDescription);	
	    entity.setStatus("1");//医生上传
	    StringBuilder addStrc = new StringBuilder();
	    /**
		 * 上传图片开始
		 */
		try {
			if(file.length>0){
				for (int i = 0; i < file.length; i++) {				 
					CommonsMultipartFile img =file[i];
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
						File saveFile = new File((request.getContextPath() + "/upload/img/report/" + signImgHeadName).substring(9)); 
						FileUtils.copyInputStreamToFile(is, saveFile); 
				        //上传文件到服务器 
						FTPUtils.upload(saveFile, "/upload/img/report/"); 
						addStrc.append((request.getContextPath() + "/upload/img/report/"+ signImgHeadName).substring(9)).append(",");
				       // entity.setImg((request.getContextPath() + "/upload/img/report"+ signImgHeadName).substring(9));
					}
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(addStrc.length()>0){
			addStrc.deleteCharAt(addStrc.length()-1);
		}		 
		/*
		 * 1.根据uid查询出此人对应的上传图片记录
		 * 2.有,就添加此人的上传图片,没有此人的记录就增加一条记录
		 * 
		 */
		//UserReportVo vo =userService.getPersonImgByUid(personId);
		//找到登录医生对应的docid
		UserReportVo rVo =userService.getDoctorIdByUsername(userName);
		entity.setDoctorId(rVo.getDoctorId());
		entity.setDocTeamId(rVo.getDocTeamId());
		entity.setImg(addStrc.toString());
		Integer flag=1;//判断上传是否成功
		flag =userService.insertUserReportByDoc(entity);
		 
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DEAL_FAIL.getKey(), ApiStatusEnum.DEAL_FAIL.getValue())); 
		}
	    
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	
	@RequestMapping(value = "/loginOld", method = RequestMethod.POST)
	@ApiOperation(value = "手机号加密码或者验证码登录（居民端）-老版", notes = "{'mobile':'手机号码','password':'密码','code':'验证码'}", response = UserVo.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 2010, message = "登录失败，手机号或密码错误"), @ApiResponse(code = 2011, message = "用户不存在") })
	public String loginOld(@RequestBody String userBody) {
		// 1- 获取用户的手机号和验证码
		Map<String, String> singleJsonMap = JsonUtils.getSingleJsonMap(userBody);
		UserEntity userEntity = new UserEntity();
		userEntity.setMobile(singleJsonMap.get("mobile"));
		userEntity.setPassword(singleJsonMap.get("password"));

		// 2- 调用 service验证登录
		UserEntity loginUser = userService.verifyUser(userEntity);
		
		if (loginUser == null) {
			// 如果验证失败，返回手机号或密码错误
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.LOGIN_ERROR.getKey(), ApiStatusEnum.LOGIN_ERROR.getValue()));
		}else {
			// 把登录用户写入缓存
			Map<String, String> loginUserMap = JsonUtils.getSingleJsonMap(JsonUtils.getJson(loginUser));
			for (Map.Entry<String, String> m :loginUserMap.entrySet())  {  
				if (m.getValue() != null && !"".equals(m.getValue())) {
					JedisUtils.set(loginUser.getUid() + m.getKey(), String.valueOf(m.getValue()),0);
				}
			}
			UserVo userDetail = userService.getUserDetail(loginUser.getUid());
			return JsonUtils.getJson(BaseJsonVo.success(userDetail));
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "手机号加密码或者验证码登录（居民端）-增加登录信息记录", notes = "{'mobile':'手机号码','password':'密码','code':'验证码','brandModel':'厂商品牌','phoneType':'手机类型1 安卓 2 苹果','version':'手机系统版本号','imei':'IMEI号'}", response = UserVo.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 2010, message = "登录失败，手机号或密码错误"), @ApiResponse(code = 2011, message = "用户不存在") })
	public String login(@RequestBody String userBody) {
		// 1- 获取用户的手机号和验证码
		Map<String, String> singleJsonMap = JsonUtils.getSingleJsonMap(userBody);
		String brandModel =singleJsonMap.get("brandModel");
		String phoneType=singleJsonMap.get("phoneType");
		String version=singleJsonMap.get("version");
		String imei=singleJsonMap.get("imei");
		
		UserEntity userEntity = new UserEntity();
		userEntity.setMobile(singleJsonMap.get("mobile"));
		userEntity.setPassword(singleJsonMap.get("password"));
		
		LoginRecordEntity lrEntity=new LoginRecordEntity();
		lrEntity.setLoginType(3);
		lrEntity.setLoginAccount(singleJsonMap.get("mobile"));
		lrEntity.setBrandModel(brandModel);
		if(StringUtils.isEmpty(phoneType)){
			lrEntity.setPhoneType(1);
		}else{
			lrEntity.setPhoneType(Integer.valueOf(phoneType));
		}
		lrEntity.setVersion(version);
		lrEntity.setImei(imei);
		lrEntity.setLoginTime(new Date());
		

		// 2- 调用 service验证登录
		UserEntity loginUser = userService.verifyUser(userEntity);
		
		if (loginUser == null) {
			// 如果验证失败，返回手机号或密码错误
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.LOGIN_ERROR.getKey(), ApiStatusEnum.LOGIN_ERROR.getValue()));
		}else {
			//登录成功，将登录信息写入登录信息表，插入与否都继续操作
			recordService.insertLoginRecord(lrEntity);
			
			// 把登录用户写入缓存
			Map<String, String> loginUserMap = JsonUtils.getSingleJsonMap(JsonUtils.getJson(loginUser));
			for (Map.Entry<String, String> m :loginUserMap.entrySet())  {  
				if (m.getValue() != null && !"".equals(m.getValue())) {
					JedisUtils.set(loginUser.getUid() + m.getKey(), String.valueOf(m.getValue()),0);
				}
			}
			UserVo userDetail = userService.getUserDetail(loginUser.getUid());
			return JsonUtils.getJson(BaseJsonVo.success(userDetail));
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ApiOperation(value = "手机注册（居民端）", notes = "{'mobile':'手机号码','password':'密码','code':'验证码'}", response = UserVo.class)
	@Transactional
	public String register(@RequestBody String userBody,HttpServletRequest request) {
		Map<String, String> singleJsonMap = JsonUtils.getSingleJsonMap(userBody);
		String code = singleJsonMap.get("code");
		String smsCode = JedisUtils.get("sms"+singleJsonMap.get("mobile"));
		if (StringUtils.isEmpty(smsCode) || !code.equals(smsCode)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.CODE_ERROR.getKey(), ApiStatusEnum.CODE_ERROR.getValue()));
		}
		UserEntity entity = new UserEntity();
		entity.setMobile(singleJsonMap.get("mobile"));
		entity.setPassword(singleJsonMap.get("password"));
		try {
			//验证手机号是否合法
			if (!MatcherUtiles.mobileMach(entity.getMobile())) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue()));
			}
			
			String registerResult = userService.register(entity); //注册
			if (BusinessConstants.MOBILE_REPEAT.equals(registerResult)) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PHONE_EXISTS.getKey(), ApiStatusEnum.PHONE_EXISTS.getValue()));
			}
			
			return JsonUtils.getJson(BaseJsonVo.success(null));

		}catch(IMException ime){
			ime.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.IM_ERROR.getKey(), ApiStatusEnum.IM_ERROR.getValue()));
		}catch (Exception e) {
			logger.error("传入的JSON是：", userBody);
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.error());
		}
	}


	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ApiOperation(value = "修改密码（居民端）", notes = "{'uid':'用户uid','password':'原始密码','newPassword':'新密码'}", response = BaseJsonVo.class)
	public String updatePassword(@RequestBody String userBody) {
		// 封装查询参数
		Map<String, String> singleJsonMap = JsonUtils.getSingleJsonMap(userBody);
		UserEntity itemUser = new UserEntity();
		itemUser.setUid(singleJsonMap.get("uid"));
		itemUser.setPassword(singleJsonMap.get("newPassword"));
		// 通过uid获取用户对象
		UserEntity verifyUser = userService.getUserByUid(singleJsonMap.get("uid"));
		// 对比原密码是否正确
		if (!singleJsonMap.get("password").equals(verifyUser.getPassword())) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.OLD_ERROR.getKey(), ApiStatusEnum.OLD_ERROR.getValue()));
		}else {
			userService.updateUser(itemUser);
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	
	@RequestMapping(value = "/updateMobile", method = RequestMethod.POST)
	@ApiOperation(value = "修改手机号（居民端）", notes = "{'uid':'用户UID','mobile':'新手机号','code':'验证码'}", response = BaseJsonVo.class)
	public String updateMobile(@RequestBody String userBody, HttpServletRequest request) {
		Map<String, String> singleJsonMap = JsonUtils.getSingleJsonMap(userBody);
		UserEntity entity = new UserEntity();
		entity.setUid(singleJsonMap.get("uid"));
		entity.setMobile(singleJsonMap.get("mobile"));
		String code = singleJsonMap.get("code");
		String smsCode = JedisUtils.get("sms"+entity.getMobile());
		if (StringUtils.isEmpty(smsCode) || !code.equals(smsCode)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.CODE_ERROR.getKey(), ApiStatusEnum.CODE_ERROR.getValue()));
		}
		
		//验证手机号是否合法
		if (!MatcherUtiles.mobileMach(entity.getMobile())) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue()));
		}
		
		//验证手机号码是否存在
		String mobile=entity.getMobile();
		UserEntity searchUserEntity=userService.getUserByMobile(mobile);
		if(searchUserEntity!=null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PHONE_EXISTS.getKey(), ApiStatusEnum.PHONE_EXISTS.getValue()));
		}
		
		String updateResult = userService.updateMobile(entity);
		
		if (BusinessConstants.MOBILE_REPEAT.equals(updateResult)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PHONE_EXISTS.getKey(), ApiStatusEnum.PHONE_EXISTS.getValue()));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	@RequestMapping(value = "/doctorLoginOld", method = RequestMethod.POST)
	@ApiOperation(value = "医生公卫账号登录（医生端）-老版", notes = "{'userName':'用户名','extInfo':'唯一终端识别码','password':'密码','code':'验证码'}", response = UserVo.class)
	public String doctorLoginOld(@RequestBody String userBody) {
		/*try {*/
			Map<String, String> jsonMap = JsonUtils.getSingleJsonMap(userBody);
			String userName = jsonMap.get("userName");
			String password = jsonMap.get("password");
			String extInfo = jsonMap.get("extInfo");
			DoctorUserEntity userEntity = new DoctorUserEntity(userName, password);
			
			//登录验证
			DoctorUserEntity verifyUser = docUserService.verifyUser(userEntity);
			if (verifyUser == null) {
				//若没有查询到该对象，返回用户名或密码错误
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DOC_LOGIN_ERROR.getKey(), ApiStatusEnum.DOC_LOGIN_ERROR.getValue()));
			}else {
				DoctorDetailVo doctorInfo = docService.getDoctorInfo(verifyUser.getDoctorId());		
				if (doctorInfo == null) {
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
				}
				//把用户登录数据存入redis
				Map<String, String> returnDataMap = new HashMap<String, String>();
				returnDataMap.put("userName", userName);
				returnDataMap.put("password", password);
				returnDataMap.put("doctorUserId", verifyUser.getDoctorUserId());
				returnDataMap.put("img", verifyUser.getImg());
				returnDataMap.put("nickName", verifyUser.getNickName());
				returnDataMap.put("doctorId", doctorInfo.getId());
				returnDataMap.put("docName", doctorInfo.getDocName());
				returnDataMap.put("sex", doctorInfo.getSex());
				returnDataMap.put("deptName", doctorInfo.getDeptName());
				returnDataMap.put("orgId", doctorInfo.getOrgId());
				returnDataMap.put("orgName", doctorInfo.getOrgName());
				returnDataMap.put("regionCode", doctorInfo.getRegionCode());
				returnDataMap.put("teamId", doctorInfo.getTeamId());
				returnDataMap.put("docType", doctorInfo.getDocType());
				
				for (Map.Entry<String, String> m :returnDataMap.entrySet())  {  
					if (m.getValue() != null && !"".equals(m.getValue())) {
						JedisUtils.set(userName + m.getKey(), m.getValue(),0);
					}
				} 
				returnDataMap.put("leaderName", doctorInfo.getLeaderName());
				returnDataMap.put("phoneNumber", doctorInfo.getPhoneNumber());
				returnDataMap.put("specialty", doctorInfo.getSpecialty());
				returnDataMap.put("introduction", doctorInfo.getIntroduction());
				returnDataMap.put("memberNumInTeam", doctorInfo.getMemberNumInTeam());
				returnDataMap.put("orgAddress", doctorInfo.getOrgAddress());
				if (StringUtils.isEmpty(verifyUser.getProductCode())) {
					//如果产品代码为空，则说明是第一次登录，加入字段提示
					returnDataMap.put("firstLogin", "true");
				}else {
					returnDataMap.put("firstLogin", "false");
				}
				
				//调用im服务检查是否注册IM，若没有，进行注册
				int flag = IMUtils.doctorRegister(returnDataMap);
				if (flag == ApiStatusEnum.FAIL.getKey()) {
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.IM_ERROR.getKey(), ApiStatusEnum.IM_ERROR.getValue()));
				}
				//返回登录结果
				return JsonUtils.getJson(BaseJsonVo.success(returnDataMap));
			}
		/*}
		catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ERROR_CODE.getKey(), ApiStatusEnum.ERROR_CODE.getValue()));
		}*/
		
	}
	
	@RequestMapping(value = "/doctorLogin", method = RequestMethod.POST)
	@ApiOperation(value = "医生公卫账号登录（医生端）-添加登录信息记录", notes = "{'userName':'用户名','extInfo':'唯一终端识别码','password':'密码','code':'验证码','brandModel':'厂商品牌',’phoneType‘：’1 安卓 2苹果’,'version':'手机系统版本号','imei':'IMEI号'}", response = UserVo.class)
	public String doctorLogin(@RequestBody String userBody) {
		/*try {*/
			Map<String, String> jsonMap = JsonUtils.getSingleJsonMap(userBody);
			String userName = jsonMap.get("userName");
			String password = jsonMap.get("password");
			String extInfo = jsonMap.get("extInfo");
			String brandModel =jsonMap.get("brandModel");
			String phoneType=jsonMap.get("phoneType");
			String version=jsonMap.get("version");
			String imei=jsonMap.get("imei");
			DoctorUserEntity userEntity = new DoctorUserEntity(userName, password);
			
			//登录信息
			LoginRecordEntity lrEntity=new LoginRecordEntity();
			lrEntity.setLoginType(1);
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
			
			
			//登录验证
			DoctorUserEntity verifyUser = docUserService.verifyUser(userEntity);
			if (verifyUser == null) {
				//若没有查询到该对象，返回用户名或密码错误
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DOC_LOGIN_ERROR.getKey(), ApiStatusEnum.DOC_LOGIN_ERROR.getValue()));
			}else {
				DoctorDetailVo doctorInfo = docService.getDoctorInfo(verifyUser.getDoctorId());		
				if (doctorInfo == null) {
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
				}
				
				//把用户登录数据存入redis
				Map<String, String> returnDataMap = new HashMap<String, String>();
				returnDataMap.put("userName", userName);
				returnDataMap.put("password", password);
				returnDataMap.put("doctorUserId", verifyUser.getDoctorUserId());
				returnDataMap.put("img", verifyUser.getImg());
				returnDataMap.put("nickName", verifyUser.getNickName());
				returnDataMap.put("doctorId", doctorInfo.getId());
				returnDataMap.put("docName", doctorInfo.getDocName());
				returnDataMap.put("sex", doctorInfo.getSex());
				returnDataMap.put("deptName", doctorInfo.getDeptName());
				returnDataMap.put("orgId", doctorInfo.getOrgId());
				returnDataMap.put("orgName", doctorInfo.getOrgName());
				returnDataMap.put("regionCode", doctorInfo.getRegionCode());
				returnDataMap.put("teamId", doctorInfo.getTeamId());
				returnDataMap.put("docType", doctorInfo.getDocType());
				
				for (Map.Entry<String, String> m :returnDataMap.entrySet())  {  
					if (m.getValue() != null && !"".equals(m.getValue())) {
						JedisUtils.set(userName + m.getKey(), m.getValue(),0);
					}
				} 
				returnDataMap.put("leaderName", doctorInfo.getLeaderName());
				returnDataMap.put("phoneNumber", doctorInfo.getPhoneNumber());
				returnDataMap.put("specialty", doctorInfo.getSpecialty());
				returnDataMap.put("introduction", doctorInfo.getIntroduction());
				returnDataMap.put("memberNumInTeam", doctorInfo.getMemberNumInTeam());
				returnDataMap.put("orgAddress", doctorInfo.getOrgAddress());
				if (StringUtils.isEmpty(verifyUser.getProductCode())) {
					//如果产品代码为空，则说明是第一次登录，加入字段提示
					returnDataMap.put("firstLogin", "true");
				}else {
					returnDataMap.put("firstLogin", "false");
				}
				

				//将登录信息存入登录记录表中
				Integer mark= recordService.insertLoginRecord(lrEntity);
				if(mark<1){
					returnDataMap.put("loginRecord", "fail");
				}else{
					returnDataMap.put("loginRecord", "success");
				}
				
				//调用im服务检查是否注册IM，若没有，进行注册
				int flag = IMUtils.doctorRegister(returnDataMap);
				if (flag == ApiStatusEnum.FAIL.getKey()) {
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.IM_ERROR.getKey(), ApiStatusEnum.IM_ERROR.getValue()));
				}
				//返回登录结果
				return JsonUtils.getJson(BaseJsonVo.success(returnDataMap));
			}
		/*}
		catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ERROR_CODE.getKey(), ApiStatusEnum.ERROR_CODE.getValue()));
		}*/
		
	}
	
	@RequestMapping(value = "/updateDocUser", method = RequestMethod.POST,produces = "multipart/form-data;charset=UTF-8")
	@ApiOperation(value = "修改医生用户信息（医生端）", notes = "{'userName':'医生登录用户名','nickName':'昵称''introduction':'简介','img':'图片file对象'}", response = BaseJsonVo.class)
	public String updateUser(@RequestParam String userName,@RequestParam String nickName,@RequestParam String introduction,@RequestParam("file") CommonsMultipartFile img, HttpServletRequest request) {
		
		/**
		 * 设置医生对象和医生用户对象属性
		 */
		DoctorUserEntity userEntity = new DoctorUserEntity();
		userEntity.setNickName(nickName);
		userEntity.setUserName(userName);
		
		DoctorEntity docEntity = new DoctorEntity();
		docEntity.setIntroduction(introduction);
		/**
		 * 上传图片开始
		 */
		try {
			
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
				FTPUtils.upload(saveFile, "/upload/img/"); 
		        
				userEntity.setImg((request.getContextPath() + "/upload/img/"
						+ signImgHeadName).substring(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//修改操作
		int updateUser = docUserService.updateDocUser(userEntity, docEntity);
		
		//如果返回为0，说明修改失败
		if (updateUser == 0) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		
		//修改成功，重新返回医生信息
		DoctorDetailVo doctorUser = docUserService.getDoctorByUsername(userName);
		
		DoctorDetailVo doctorInfo = docService.getDoctorInfo(doctorUser.getId());				 
		//把用户登录数据存入redis
		Map<String, String> returnDataMap = new HashMap<String, String>();
		returnDataMap.put("userName", userName);
		returnDataMap.put("password", doctorInfo.getPassword());
		returnDataMap.put("img", userEntity.getImg());
		returnDataMap.put("nickName", userEntity.getNickName());
		returnDataMap.put("doctorId", doctorInfo.getId());
		returnDataMap.put("docName", doctorInfo.getDocName());
		returnDataMap.put("sex", doctorInfo.getSex());
		returnDataMap.put("deptName", doctorInfo.getDeptName());
		returnDataMap.put("orgId", doctorInfo.getOrgId());
		returnDataMap.put("orgName", doctorInfo.getOrgName());
		returnDataMap.put("regionCode", doctorInfo.getRegionCode());
		returnDataMap.put("teamId", doctorInfo.getTeamId());
		returnDataMap.put("docType", doctorInfo.getDocType());
		
		for (Map.Entry<String, String> m :returnDataMap.entrySet())  {  
			if (m.getValue() != null && !"".equals(m.getValue())) {
				JedisUtils.set(userName + m.getKey(), m.getValue(),0);
			}
		} 
		returnDataMap.put("leaderName", doctorInfo.getLeaderName());
		returnDataMap.put("phoneNumber", doctorInfo.getPhoneNumber());
		returnDataMap.put("specialty", doctorInfo.getSpecialty());
		returnDataMap.put("introduction", doctorInfo.getIntroduction());
		returnDataMap.put("memberNumInTeam", doctorInfo.getMemberNumInTeam());
		returnDataMap.put("orgAddress", doctorInfo.getOrgAddress());
		
		return JsonUtils.getJson(BaseJsonVo.success(returnDataMap));
		//return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/updateDocUserMobile", method = RequestMethod.POST)
	@ApiOperation(value = "修改医生用户手机号（医生端）", notes = "{'userName':'医生登录用户名','phoneNumber':'手机号码','code':'验证码'}")
	public String updateDocUserMobile(@RequestBody String userBody, HttpServletRequest request) {
		Map<String, String> jsonMap = JsonUtils.getSingleJsonMap(userBody);
		String phoneNumber = jsonMap.get("phoneNumber");
		String userName = jsonMap.get("userName");
		String code = jsonMap.get("code");
		String smsCode = JedisUtils.get("sms"+phoneNumber);
		if (StringUtils.isEmpty(smsCode) || !code.equals(smsCode)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.CODE_ERROR.getKey(), ApiStatusEnum.CODE_ERROR.getValue()));
		}
		/**
		 * 设置医生对象和医生用户对象属性
		 */
		DoctorEntity docEntity = new DoctorEntity();
		docEntity.setPhoneNumber(phoneNumber);
		
		DoctorUserEntity userEntity = new DoctorUserEntity();
		userEntity.setUserName(userName);
		
		//修改操作
		int updateUser = docUserService.updateDocUser(userEntity, docEntity);
		
		//如果返回为0，说明修改失败
		if (updateUser == 0) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}

	@RequestMapping(value = "/updateDocUserPassword", method = RequestMethod.POST)
	@ApiOperation(value = "修改密码（医生端）", notes = "{'userName':'医生登录用户名','password':'原密码','newPassword':'新密码'}", response = BaseJsonVo.class)
	public String updateDocUserPassword(@RequestBody String userBody) {
		DoctorUserVo vo = JsonUtils.getObject(userBody, DoctorUserVo.class);
		
		//判断新密码是否合法
		if (!MatcherUtiles.noSpecialChar((vo.getNewPassword()))) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PASSWORD_OUTLAW.getKey(), ApiStatusEnum.PASSWORD_OUTLAW.getValue()));
		}
		//判断原密码是否正确
		DoctorUserEntity entity = new DoctorUserEntity(vo.getUserName(),vo.getPassword());
		DoctorUserEntity verifyUser = docUserService.verifyUser(entity);
		if (verifyUser == null) {
			//原密码错误：返回提示
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.OLD_ERROR.getKey(), ApiStatusEnum.OLD_ERROR.getValue()));
		}else {
			//原密码正确：修改密码
			entity = new DoctorUserEntity(vo.getUserName(), vo.getNewPassword());
			docUserService.updateDocUser(entity, null);
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	@RequestMapping(value = "/initLoginSetting", method = RequestMethod.POST)
	@ApiOperation(value = "医生首次登陆设置（医生端）", notes = "{'userName':'医生登录用户名','publicPassword':'公卫账号密码','extInfo':'终端号'}", response = BaseJsonVo.class)
	public String initLoginSetting(@RequestBody String userBody) {	
		Map<String, String> paramMap = JsonUtils.getSingleJsonMap(userBody);
		String userName = paramMap.get("userName");
		String password = paramMap.get("publicPassword");//前端传进来
		String extInfo = paramMap.get("extInfo");
		DoctorDetailVo docInfo = docUserService.getDoctorByUsername(userName);
		//获取团队标签列表，若为空则添加默认标签
		List<DiseaseLabelVo> labelList = labelService.getLabelList(docInfo.getTeamId());
		if (labelList == null || labelList.size() == 0) {
			labelService.initLabels(docInfo.getTeamId());
		}
		//修改productCode
		DoctorUserEntity userEntity = new DoctorUserEntity();
		userEntity.setUserName(userName);
		
		
		//现在的productCode需要通过卫计委提供的接口来获取
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("CompanyCode", "1000000004");//
		param.put("ProductName", "慧康医生"); 
		param.put("ProductType", "04"); 
		//param.put("ExtInfo", "74-2F-68-6C-51-2F"); 
		param.put("ExtInfo", extInfo); 
		param.put("UserName", userName); 
		param.put("Password", password); 
		String response = RestfulUtils.getPublicData("48-1", param);
		System.out.println(response);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);	
		System.out.println(responseMap);
		Integer result =(Integer) responseMap.get("Result");
		if(result==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.AUTHOR_REPEAT.getKey(), (String)responseMap.get("Msg"))); 
		}
		Map<String, Object>  map=(Map<String, Object> )responseMap.get("Msg");	 
	    String productCode = (String) map.get("ProductCode");
	    userEntity.setProductCode(productCode);
	   
		//userEntity.setProductCode(PropertiesUtils.getValue("produce_code"));
		docUserService.updateDocUser(userEntity, null);
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/notifyLogin", method = RequestMethod.POST)
	@ApiOperation(value = "唤醒账号保持基卫在线", notes = "{'userName':'公卫账号（即医生账号）','password':'公卫账号密码'}", response = BaseJsonVo.class)
	public String notifyLogin(@RequestBody String userBody){
		
		Map<String, String> paramMap = JsonUtils.getSingleJsonMap(userBody);
		String userName = paramMap.get("userName");
		String password = paramMap.get("password");//前端传进来
		
		// 医生端调用，获取该医生的productCode
        DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
        if (docUser == null) {
            return JsonUtils.getJson(BaseJsonVo.empty(
                    ApiStatusEnum.NO_DOCTOR_USERNAME.getKey(),
                    ApiStatusEnum.NO_DOCTOR_USERNAME.getValue()));
        }
        String productCode = docUser.getProductCode();
        //调用接口
        Map<String, Object> pMap=new HashMap<String,Object>();
	    pMap.put("ProductCode", productCode);
	    pMap.put("UserName", userName);
	    pMap.put("Password", password);
	    String response=RestfulUtils.getPublicData("01", pMap);
	    
	    Map<String, Object> resMap=JsonUtils.getObjectJsonMap(response);
	    if((Integer) resMap.get("Result") == 1){
	    	return JsonUtils.getJson(BaseJsonVo.success(null));
	    }else{
	    	return JsonUtils.getJson(BaseJsonVo.empty(
                    ApiStatusEnum.FAIL.getKey(), (String) resMap.get("Msg")));
	    }
		
		
	}
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	@ApiOperation(value = "忘记密码（居民端）", notes = "{'code':'验证码','password':'新密码','mobile':'电话号码'}", response = BaseJsonVo.class)
	public String forgetPassword(@RequestBody String userBody) {
		// 封装查询参数
		Map<String, String> singleJsonMap = JsonUtils.getSingleJsonMap(userBody);
		//判断新密码是否合法
		if (!MatcherUtiles.noSpecialChar((singleJsonMap.get("password")))) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PASSWORD_OUTLAW.getKey(), ApiStatusEnum.PASSWORD_OUTLAW.getValue()));
		}
		UserEntity itemUser = new UserEntity();		 
		itemUser.setPassword(singleJsonMap.get("password"));
		//验证手机号是否合法
		if (!MatcherUtiles.mobileMach(singleJsonMap.get("mobile"))) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue()));
		}
		// 通过mobile获取用户对象
		UserEntity verifyUser = userService.getUserByMobile(singleJsonMap.get("mobile"));
		//验证用户是否存在
		if(verifyUser==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_EMPTY.getKey(), ApiStatusEnum.USER_EMPTY.getValue()));
		}
		// 验证手机号是否与以前注册手机号一致
		if (!singleJsonMap.get("mobile").equals(verifyUser.getMobile())) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PHONE_ATYPISM.getKey(), ApiStatusEnum.PHONE_ATYPISM.getValue()));
		}else {
			//处理验证码
			String code = singleJsonMap.get("code");
			String smsCode = JedisUtils.get("sms"+singleJsonMap.get("mobile"));
			if (StringUtils.isEmpty(smsCode) || !code.equals(smsCode)) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.CODE_ERROR.getKey(), ApiStatusEnum.CODE_ERROR.getValue()));
			}
			//修改密码
            itemUser.setUid(verifyUser.getUid());
			userService.updateUser(itemUser);
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	@RequestMapping(value = "/forgetDocUserPassword", method = RequestMethod.POST)
	@ApiOperation(value = "忘记密码（医生端）", notes = "{'userName':'医生登录用户名','password':'新密码','code':'验证码','phoneNumber':'电话号码'}", response = BaseJsonVo.class)
	public String forgetDocUserPassword(@RequestBody String userBody) {
		// 封装查询参数
		Map<String, String> singleJsonMap = JsonUtils.getSingleJsonMap(userBody);	
		//判断新密码是否合法
		if (!MatcherUtiles.noSpecialChar((singleJsonMap.get("password")))) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PASSWORD_OUTLAW.getKey(), ApiStatusEnum.PASSWORD_OUTLAW.getValue()));
		}
		//验证手机号是否合法
		if (!MatcherUtiles.mobileMach(singleJsonMap.get("phoneNumber"))) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue()));
		}
		// 通过userName获取用户对象
		DoctorDetailVo DoctorDetailVo = docUserService.getDoctorByUsername(singleJsonMap.get("userName"));
		//验证用户是否存在
		if(DoctorDetailVo==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_EMPTY.getKey(), ApiStatusEnum.USER_EMPTY.getValue()));
		}
		// 验证手机号是否与以前注册手机号一致
		if (!singleJsonMap.get("phoneNumber").equals(DoctorDetailVo.getPhoneNumber())) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PHONE_ERROR.getKey(), ApiStatusEnum.PHONE_ERROR.getValue()));
		}else {
			//处理验证码
			String code = singleJsonMap.get("code");
			String smsCode = JedisUtils.get("sms"+singleJsonMap.get("phoneNumber"));
			if (StringUtils.isEmpty(smsCode) || !code.equals(smsCode)) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.CODE_ERROR.getKey(), ApiStatusEnum.CODE_ERROR.getValue()));
			}
			//修改密码
			DoctorUserEntity entity = new DoctorUserEntity(DoctorDetailVo.getUserName(),singleJsonMap.get("password"));
			docUserService.updateDocUser(entity, null);
			return JsonUtils.getJson(BaseJsonVo.success(null));
		} 
	}
}
