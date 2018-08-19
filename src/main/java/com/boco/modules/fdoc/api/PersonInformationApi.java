package com.boco.modules.fdoc.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.jedis.JedisUtils;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.NationEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.service.DiseaseLabelService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.NationService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DiseaseLabelVo;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SignVo;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/personInformation", produces = "text/json;charset=UTF-8")
public class PersonInformationApi {
	
	@Resource
	PersonInformationService personService;
	@Resource
	DocUserService docUserService;
	@Resource
	SignService signService;
	@Resource
	NationService nationService;
	@Resource
	DiseaseLabelService labelService;
	
	@RequestMapping(value = "/getJwPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "通过居民id获取基卫id", notes = "{'personId':'居民id'}", response = BaseJsonVo.class)
	public String getJwPersonId(@RequestParam String personId){
		PersonInformationEntity pEntity =personService.getPersonInformationByPesronId(personId);
		if(StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.success(pEntity.getJwPersonId()));
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
	}
	
	@RequestMapping(value = "/getPersonIdByJw", method = RequestMethod.GET)
	@ApiOperation(value = "通过基卫居民id获取居民id", notes = "{'jwPersonId':'基卫居民id'}", response = BaseJsonVo.class)
	public String getPersonIdByJw(@RequestParam String jwPersonId){
		String personId=personService.getPersonIdByJw(jwPersonId);
		return JsonUtils.getJson(BaseJsonVo.success(personId));
			
	}
	
	@RequestMapping(value = "/getPersonInfoByScan", method = RequestMethod.GET)
	@ApiOperation(value = "医生端扫健康卡获取居民信息（医生端）", notes = "{'idCard':'居民身份证号','userName':'医生登录用户名'}", response = BaseJsonVo.class)
	public String getPersonInfoByScan(@RequestParam String idCard,@RequestParam String userName) throws ParseException{
		
		//判断身份证格式
		if(!MatcherUtiles.idCardMach(idCard)){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.IDCARD_ERROR.getKey(), ApiStatusEnum.IDCARD_ERROR.getValue()));
		}
		// 通过username获取到登录医生信息
		DoctorDetailVo doctor = docUserService.getDoctorByUsername(userName);
		
		//根据userName获取productCode
		DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
		if (docUser == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(
			ApiStatusEnum.NO_SUCH_DOCTOR.getKey(),
			ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
			}
		String productCode = docUser.getProductCode();
		
		//调用方法通过身份证获取基卫的数据
		PersonInformationVo pVo = getPerosonInfo(idCard, productCode);
		
		//String personId=null;
		String jwPersonId = null;
		PersonInformationVo personVo = null;
		//如果基卫有数据，通过基卫返回的基卫ID获取本地的数据放入personVo
		if(pVo != null){
			jwPersonId = pVo.getJwPersonId();
			personVo = personService.getPersonDetailByJwPersonId(jwPersonId);
			
		}
		//调用共卫接口，有数据更改本地库
		if(personVo == null){
			if(pVo == null){
				//基卫无数据时，通过身份证在本地查询，有数据将起反骨personVo
				PersonInformationEntity entity=new PersonInformationEntity();
				entity.setIdCard(idCard);
				PersonInformationVo personInfoByIdcard = personService.getPersonInfoByIdCard(entity);
				if(personInfoByIdcard == null){
					//均无数据
					return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.BASE_NONE.getKey(), ApiStatusEnum.BASE_NONE.getValue()));
				}else{
					personVo = personInfoByIdcard;
				}
			}else{
				//基卫有数据本地无数据，同步，且将基卫数据放入personVo
				PersonInformationEntity entity = new PersonInformationEntity();
				entity.setIdCard(idCard);
				PersonInformationVo personInfoByIdcard = personService.getPersonInfoByIdCard(entity);
				//如果身份证能查出来
				if(personInfoByIdcard!=null){
					//设置签约状态
					pVo.setIfSign(personInfoByIdcard.getIfSign());
					pVo.setPersonId(personInfoByIdcard.getPersonId());
					//电话号码不做修改
					pVo.setPhoneNumber(personInfoByIdcard.getPhoneNumber());
					personService.updatePerson(pVo);
				}else{
					//都查不出来进行新增
					pVo.setPersonId(UUID.randomUUID().toString().replace("-", ""));
					personService.insertPerson(pVo);
				}
				personVo=pVo;
			}
		}else{
			//updatePerson2根据personId修改
			if(personVo.getIfSign()!=null){
				pVo.setIfSign(personVo.getIfSign());
			}
			pVo.setPersonId(personVo.getPersonId());
			personService.updatePerson2(pVo);
			personVo=pVo;
		}
		
		// 查询居民是否有签约医生
		SignVo signInfo = signService.getSignDetailByIdCard(idCard);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (signInfo == null) {
			// 没有签约医生
			returnMap.put("returnFlag", "2");
			returnMap.put("personInfo", personVo);
			PersonInformationVo masterInfo = personService.getMasterInfo(personVo.getFamilyId());
			if(masterInfo==null){
				/*return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HASNOT_HOSHOLDER.getKey(), ApiStatusEnum.HASNOT_HOSHOLDER.getValue()));*/
				returnMap.put("masterInfo", null);
			}else{
				List<PersonInformationVo> familyMember = personService.getFamilyMember(personVo.getFamilyId());
				masterInfo.setFamilyNum(familyMember.size());
				returnMap.put("masterInfo", masterInfo);
			}
			
		}else {
			// 有签约医生
			if (doctor.getTeamId().equals(signInfo.getDocTeamId())) {
				// 签约团队为当前登录医生团队
				returnMap.put("returnFlag", "3");
			}else {
				returnMap.put("returnFlag", "4");
			}
			returnMap.put("personInfo", personVo);
			Map<String, Object> signMap = new HashMap<String, Object>();
			signMap.put("docName", signInfo.getDocName());
			signMap.put("docOrgName", signInfo.getDocOrgName());
			signMap.put("signId", signInfo.getId());
			returnMap.put("signInfo", signMap);
		}
		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}
	
	
	
	@RequestMapping(value = "/getFamilyRecordList", method = RequestMethod.GET)
	@ApiOperation(value = "获取家庭档案列表（通用）", notes = "{}", response = BaseJsonVo.class)
	public String getFamilyRecordList(@RequestParam String  userName,@RequestParam(value="IsEmpty",required=false) String  IsEmpty,@RequestParam(value="masterName",required=false) String  masterName,@RequestParam(value="familyCode",required=false) String  familyCode,@RequestParam Integer pageSize, @RequestParam Integer pageIndex){
		//从session获取登录医生
		DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
		if (docUser == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(
			ApiStatusEnum.NO_SUCH_DOCTOR.getKey(),
			ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
			}
		String productCode = docUser.getProductCode();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("ProductCode", productCode); 
		//paramMap.put("RegionCode", docUser.getRegionCode());
		paramMap.put("RegionCode", PropertiesUtils.getValue("top_region"));
		if(StringUtils.isNotEmpty(masterName)){
			paramMap.put("MasterName", masterName);
		}
		if(StringUtils.isNotEmpty(IsEmpty)){
			paramMap.put("IsEmpty", 1);
		}		
		paramMap.put("FamilyCode", familyCode);
		paramMap.put("PageSize", pageSize);
		paramMap.put("PageIndex", pageIndex-1);
		String returnData=RestfulUtils.getPublicData("54-5", paramMap);
		//解析返回JSON
		Map<String, Object> returnMap = JsonUtils.getObjectJsonMap(returnData);
		if ((Integer) returnMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
				ApiStatusEnum.FAIL.getKey(), (String) returnMap.get("Msg")));
		} 
		List<Map<String, Object>> list=(List<Map<String, Object>>) returnMap.get("Msg");
		System.out.println(JsonUtils.getJson(list));
		Integer total=(Integer) returnMap.get("Total");
		//再次查询
		return JsonUtils.getPageJsonWithTotal(total, pageSize, list);		
	}
	
	@RequestMapping(value = "/getFamilyMember", method = RequestMethod.GET)
	@ApiOperation(value = "获取家庭成员信息（通用）", notes = "{'familyId':'家庭ID'}", response = BaseJsonVo.class)
	public String getFamilyMember(@RequestParam String familyId){
		return JsonUtils.getJson(BaseJsonVo.success(personService.getFamilyMember(familyId)));
	}
	
	@RequestMapping(value = "/getPersonDetail", method = RequestMethod.GET)
	@ApiOperation(value = "查询居民详情（医生端）", notes = "{'personId':'居民ID','userName':'医生登录用户名'}", response = BaseJsonVo.class)
	public String getPersonDetail(@RequestParam String personId, @RequestParam String userName){
		PersonInformationEntity pEntity=personService.getPersonInformationByPesronId(personId);
		if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		// 获取登陆医生信息
		DoctorDetailVo doctorInfo = docUserService.getDoctorByUsername(userName);
		// 获取居民详情
		PersonInformationVo personDetail = personService.getPersonDetailByPersonId(personId);
		
		// 调用卫计委接口获取婚姻状况、医疗费用支付方式、民族
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		map.put("ProductCode",doctorInfo.getProductCode());
		
		map.put("ID", pEntity.getJwPersonId());
		String returnData = RestfulUtils.getPublicData("55-10", map);
		// 解析json
		Map<String, Object> resultMap = JsonUtils.getObjectJsonMap(returnData);
		if ((Integer)resultMap.get("Result") > 0) {
			Map<String, Object> dataMap = (Map<String, Object>) resultMap.get("Msg");
			//获取民族代码
			String nationCode = (String) dataMap.get("NationCode");
			//查询民族表,封装民族名称
			NationEntity nation = nationService.getNationByCode(nationCode);
			if (nation != null) {
				personDetail.setNation(nation.getNationName());
			}
			//获取婚姻状况代码并设置
			personDetail.setMarryStatus((String)dataMap.get("MarryStatus"));
			//获取医疗费用支付方式并设置
			personDetail.setPaymentWaystring((Integer)dataMap.get("PaymentWaystring"));
		}
		
		// 设置居民慢病标签情况
		DiseaseLabelVo labelVo = new DiseaseLabelVo();
		labelVo.setPersonId(personId);
		labelVo.setDocTeamId(doctorInfo.getTeamId());
		List<DiseaseLabelVo> labelList = labelService.getLabelListWithSelectFlag(labelVo);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("personInfo", personDetail);
		returnMap.put("labelList", labelList);
		// 封装签约信息
		SignVo signInfo = signService.getSignDetailByIdCard(personDetail.getIdCard());
		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("signType", signInfo.getSignType());
		signMap.put("signId", signInfo.getId());
		returnMap.put("signInfo", signMap);
		
		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}
	
	//从接口根据idCard获取数据并插入数据库
	public static PersonInformationVo getPerosonInfo(String idCard,String productCode) throws ParseException{
		Map<String , Object> paraMap=new HashMap<String,Object>();
		paraMap.put("ProductCode", productCode);
		paraMap.put("KeyCode", "2");
		paraMap.put("KeyValue", idCard);
		paraMap.put("PageSize", "10");
		paraMap.put("PageIndex", "0");
		paraMap.put("RegionCode", "51");
		
		String returnJson=RestfulUtils.getPublicData("55-12", paraMap);
		Map<String, Object> jsonMap=JsonUtils.getObjectJsonMap(returnJson);
		PersonInformationVo pVo=new PersonInformationVo();
		
		if((Integer) jsonMap.get("Result") == 0){
			return null;
		}else{
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonMap.get("Msg");
			if (dataList.size() == 0) {
				return null;
			}
			if(dataList.size()>1){
				for(Map<String, Object> returnMap:dataList){
					String hrStatus=(String) returnMap.get("HRSTATUS");
					if(!"活动".equals(hrStatus)){
						continue;
					}
					//pVo.setPersonId((String) returnMap.get("ID"));
					pVo.setJwPersonId((String) returnMap.get("ID"));
					pVo.setPersonCode((String) returnMap.get("PERSON_CODE"));
					pVo.setFamilyId((String) returnMap.get("FAMILY_ID"));
					pVo.setCustomNumber((String) returnMap.get("CUSTOM_NUMBER"));
					pVo.setPersonName((String) returnMap.get("NAME"));
					if("男".equals(returnMap.get("GENDER"))){
						pVo.setSex("1");
					}else if ("女".equals(returnMap.get("GENDER"))){
						pVo.setSex("2");
					}else {
						pVo.setSex("9");
					}
					String birhday=idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14);
					SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
					Date dateOfBirth = dateFormat.parse(birhday);
					pVo.setDateOfBirth(dateOfBirth);
					pVo.setIdCard(idCard);
					//pVo.setPhoneNumber((String) returnMap.get("TELPHONE"));
					pVo.setCurrentAddress((String) returnMap.get("ADDRESS"));
					pVo.setRegionCode((String) returnMap.get("REGIONCODE"));
					String unit=((String) returnMap.get("REGION_NAME")).replace("\u003e", ">");
					pVo.setUnit(unit);
					if("活动".equals(hrStatus)){
						pVo.setState(0);
					}else if("迁出".equals(hrStatus)){
						pVo.setState(1);
					}else if("死亡".equals(hrStatus)){
						pVo.setState(2);
					}else if("其他".equals(hrStatus)){
						pVo.setState(3);
					}else if("已删除".equals(hrStatus)){
						pVo.setState(99);
					}else{
						pVo.setState(0);
					}
					//pVo.setState(1);
					pVo.setIfSign(0);
					
					
					Map<String, Object> pMap=new HashMap<String,Object>();
					pMap.put("ProductCode", productCode);
					pMap.put("FamilyID", (String) returnMap.get("FAMILY_ID"));
					String returnJson1=RestfulUtils.getPublicData("54-4", pMap);
					Map<String, Object> jMap=JsonUtils.getObjectJsonMap(returnJson1);
					if((Integer) jMap.get("Result") == 0){
						pVo.setMasterFlag(null);
					}else{
						Map<String, Object> msgMap=(Map<String, Object>) jMap.get("Msg");
						if(((String) returnMap.get("NAME")).equals(msgMap.get("MasterName"))){
							pVo.setMasterFlag("1");
						}else {
							pVo.setMasterFlag(null);
						}
					}
				}
			}else{
				Map<String, Object> returnMap=dataList.get(0);
				//pVo.setPersonId((String) returnMap.get("ID"));
				pVo.setJwPersonId((String) returnMap.get("ID"));
				pVo.setPersonCode((String) returnMap.get("PERSON_CODE"));
				pVo.setFamilyId((String) returnMap.get("FAMILY_ID"));
				pVo.setCustomNumber((String) returnMap.get("CUSTOM_NUMBER"));
				pVo.setPersonName((String) returnMap.get("NAME"));
				if("男".equals(returnMap.get("GENDER"))){
					pVo.setSex("1");
				}else if ("女".equals(returnMap.get("GENDER"))){
					pVo.setSex("2");
				}else {
					pVo.setSex("9");
				}
				String birhday=idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14);
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				Date dateOfBirth = dateFormat.parse(birhday);
				pVo.setDateOfBirth(dateOfBirth);
				pVo.setIdCard(idCard);
				//pVo.setPhoneNumber((String) returnMap.get("TELPHONE"));
				pVo.setCurrentAddress((String) returnMap.get("ADDRESS"));
				pVo.setRegionCode((String) returnMap.get("REGIONCODE"));
				String unit=((String) returnMap.get("REGION_NAME")).replace("\u003e", ">");
				pVo.setUnit(unit);
				String hrStatus=(String) returnMap.get("HRSTATUS");
				if("活动".equals(hrStatus)){
					pVo.setState(0);
				}else if("迁出".equals(hrStatus)){
					pVo.setState(1);
				}else if("死亡".equals(hrStatus)){
					pVo.setState(2);
				}else if("其他".equals(hrStatus)){
					pVo.setState(3);
				}else if("已删除".equals(hrStatus)){
					pVo.setState(99);
				}else{
					pVo.setState(0);
				}
				//pVo.setState(1);
				pVo.setIfSign(0);
				
				
				Map<String, Object> pMap=new HashMap<String,Object>();
				pMap.put("ProductCode", productCode);
				pMap.put("FamilyID", (String) returnMap.get("FAMILY_ID"));
				String returnJson1=RestfulUtils.getPublicData("54-4", pMap);
				Map<String, Object> jMap=JsonUtils.getObjectJsonMap(returnJson1);
				if((Integer) jMap.get("Result") == 0){
					pVo.setMasterFlag(null);
				}else{
					Map<String, Object> msgMap=(Map<String, Object>) jMap.get("Msg");
					if(((String) returnMap.get("NAME")).equals(msgMap.get("MasterName"))){
						pVo.setMasterFlag("1");
					}else {
						pVo.setMasterFlag(null);
					}
				}
			}
			
		}
		return pVo;
		
	}	 
	@RequestMapping(value = "/updatePersonMoblie", method = RequestMethod.POST)
	@ApiOperation(value = "修改居民手机号（居民端）", notes = "{'personId':'居民personId','phoneNumber':'手机号','code':'验证码' }", response = BaseJsonVo.class)
	public String updatePersonMoblie(@RequestBody String userBody, HttpServletRequest request) {
		//判断验证码是否正确
		Map<String, String> singleJsonMap = JsonUtils.getSingleJsonMap(userBody);
		UserEntity entity = new UserEntity();
		entity.setMobile(singleJsonMap.get("phoneNumber"));
		String code = singleJsonMap.get("code");
		String smsCode = JedisUtils.get("sms"+entity.getMobile());
		if (StringUtils.isEmpty(smsCode) || !code.equals(smsCode)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.CODE_ERROR.getKey(), ApiStatusEnum.CODE_ERROR.getValue()));
		}
		// 1- 封装对象
		PersonInformationVo vo = new PersonInformationVo();
		vo.setPersonId(singleJsonMap.get("personId"));
		vo.setPhoneNumber(singleJsonMap.get("phoneNumber"));
		//验证手机号是否合法
		if (!MatcherUtiles.mobileMach(vo.getPhoneNumber())) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue()));
		}
		//修改电话号码
		int flag =personService.updateMobile(vo);
		if(flag==0){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
		 
	}
	
}

