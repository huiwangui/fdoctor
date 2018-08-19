package com.boco.modules.fdoc.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.DiseaseDictionaryEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.service.DiseaseDictionaryService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 
 * ClassName: PublicHealthApi 转发公卫数据接口<br/>
 * @author q
 * @version 
 * @since JDK 1.7+
 */
@RestController
@RequestMapping(value = "/publicHealth", produces = "text/json;charset=UTF-8")
public class PublicHealthApi {
	@Resource
	DiseaseDictionaryService diseaseService;
	@Resource
	DocUserService docUserService;
	@Resource
	PersonInformationService personService;
	@Resource
	SignService signService;
	
	@RequestMapping(value = "/getBasicInfo", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民基本档案信息（通用）", notes = "{'userName':'登录医生用户名','personId':'居民ID'}")
	public String getBasicInfo(@RequestParam String userName,@RequestParam String personId) {
		PersonInformationEntity pEntity=personService.getPersonInformationByPesronId(personId);
		if(pEntity!=null){
			if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
			}
		}
		
		String productCode = null;
		if (StringUtils.isEmpty(userName)) {
			//userName为空，为居民端调用
			List<DoctorDetailVo> list =signService.getSignDoctorTeamInfo(personId);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					String value =list.get(i).getProductCode();
					if(StringUtils.isNotEmpty(value) && "1".equals(list.get(i).getLeaderFlag())){
						productCode = value;
					}
				}
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
			if(productCode==null || "".equals(productCode)){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), "该团队队长还未激活账号，请联系该队长"));
			}
		}else {
			//医生端调用，获取该医生的productCode
			DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
			productCode = docUser.getProductCode();
		}
		// 调用接口，获取健康档案封面数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("ID", pEntity.getJwPersonId());
		String returnData = RestfulUtils.getPublicData("55-10", map);
		// 解析返回的json数据，封装返回map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if ((Integer)JsonUtils.getObjectJsonMap(returnData).get("Result") > 0) {
			Map<String, Object> dataMap = (Map<String, Object>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			returnMap.put("personName", dataMap.get("Name"));
			returnMap.put("sex", dataMap.get("GenderCode"));
			returnMap.put("personCode", dataMap.get("PersonCode"));
			returnMap.put("marryStatus", dataMap.get("MarryStatus"));	//婚姻状况
			returnMap.put("idCard", dataMap.get("CardID"));
			returnMap.put("drugAllergyHistory", dataMap.get("DrugAllergyHistory"));	//过敏史
			returnMap.put("otherDrugAllergyHistory", dataMap.get("OtherDrugAllergyHistory"));	//其他过敏史
			returnMap.put("bloodType", dataMap.get("BloodType"));	//血型
			returnMap.put("phoneNumber", dataMap.get("PersonTel"));	//电话号码
			returnMap.put("address", dataMap.get("CurrentAddress"));	//住址
			returnMap.put("paymentWaystring", dataMap.get("PaymentWaystring"));	//医疗费用支付方式
			// 封装家庭病史
			List<Map<String, Object>> familyDiseaseList = (List<Map<String, Object>>) dataMap.get("FamilyHistory");
			List<Map<String, Object>> returnFamilyDiseaseList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> familyDiseaseMap : familyDiseaseList) {
				Map<String, Object> returnFamilyMap = new HashMap<String, Object>();
				returnFamilyMap.put("relationshipType", familyDiseaseMap.get("RelationshipType"));
				returnFamilyMap.put("disease", familyDiseaseMap.get("Disease"));
				returnFamilyDiseaseList.add(returnFamilyMap);
			}
			returnMap.put("familyHistory", returnFamilyDiseaseList);
			// 封装个人疾病史
			StringBuffer buffer = new StringBuffer();
			List<Map<String, Object>> personDiseaseList = (List<Map<String, Object>>) dataMap.get("CmData");
			if (personDiseaseList.size() > 0) {
				for (Map<String, Object> personDiseaseMap : personDiseaseList) {
					String diseaseId = (String) personDiseaseMap.get("DiseaseKindID");
					DiseaseDictionaryEntity diseaseEntity = diseaseService.getDictionaryById(diseaseId.replace("-", "").toUpperCase());
					buffer.append(diseaseEntity.getDiseaseName() + "、");
				}
				returnMap.put("personDiseaseHistory", buffer.toString().subSequence(0, buffer.length() - 1));
			}
			// 封装身高、体重
			PersonInformationVo personDetail = personService.getPersonDetailByPersonId(personId);
			if (personDetail != null) {
				returnMap.put("height", personDetail.getHeight());
				returnMap.put("weight", personDetail.getWeight());
			}
			// 封装最近一次血压信息
			Map<String, Object> hyperMap = new HashMap<String, Object>();
			//hyperMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
			hyperMap.put("ProductCode",productCode);
			hyperMap.put("PersonID", pEntity.getJwPersonId());
			hyperMap.put("PageSize", 1);
			hyperMap.put("PageIndex", 0);
			String hyperMapData = RestfulUtils.getPublicData("58-1", hyperMap);
			
			if (Integer.valueOf((String)JsonUtils.getObjectJsonMap(hyperMapData).get("Total")) > 0) {
				Map<String, Object> hyperData = ((List<Map<String, Object>>)JsonUtils.getObjectJsonMap(hyperMapData).get("Msg")).get(0);
				returnMap.put("systolicPressure", hyperData.get("Sbp"));
				returnMap.put("diastolicPressure", hyperData.get("Dbp"));
			}
			// 封装最近一次血糖信息
			Map<String, Object> bloodSugerMap = new HashMap<String, Object>();
			//bloodSugerMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
			bloodSugerMap.put("ProductCode",productCode);
			
			bloodSugerMap.put("PersonID", pEntity.getJwPersonId());
			bloodSugerMap.put("StartTime", "2000-01-01");
			bloodSugerMap.put("EndTime", DateUtils.formatDate(new Date()));
			String bloodSugerData = RestfulUtils.getPublicData("59-6", bloodSugerMap);
			
			List<Map<String, Object>> bloodSugerList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(bloodSugerData).get("Msg");
			for (int i = bloodSugerList.size() - 1; i >= 0; i--) {
				if (bloodSugerList.get(i).get("Data") != null) {
					returnMap.put("bloodSuger", bloodSugerList.get(i).get("Data"));
					break;
				}
			}
		}
		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}
	
	@RequestMapping(value = "/getHyperFollowUpList", method = RequestMethod.GET)
	@ApiOperation(value = "获取高血压随访列表（通用）", notes = "{'userName':'登录医生用户名','personId':'居民ID'}")
	public String getHyperFollowUpList(@RequestParam String userName,@RequestParam String personId) {
		PersonInformationEntity pEntity=personService.getPersonInformationByPesronId(personId);
		if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		String productCode = null;
		if (StringUtils.isEmpty(userName)) {
			//userName为空，为居民端调用
			List<DoctorDetailVo> list =signService.getSignDoctorTeamInfo(personId);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					String value =list.get(i).getProductCode();
					if(StringUtils.isNotEmpty(value) && "1".equals(list.get(i).getLeaderFlag())){
						productCode = value;
					}
				}
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
			if(productCode==null || "".equals(productCode)){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), "该团队队长还未激活账号，请联系该队长"));
			}		
		}else {
			//医生端调用，获取该医生的productCode
			DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
			productCode = docUser.getProductCode();
		}
		// 调用接口，获取高血压随访列表数据
		Map<String, Object> hyperMap = new HashMap<String, Object>();
		//hyperMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		hyperMap.put("ProductCode",productCode);
		
		hyperMap.put("PersonID", pEntity.getJwPersonId());
		hyperMap.put("PageSize", 100);
		hyperMap.put("PageIndex", 0);
		String hyperMapData = RestfulUtils.getPublicData("58-1", hyperMap);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
 		if (Integer.valueOf((String)JsonUtils.getObjectJsonMap(hyperMapData).get("Total")) > 0) {
			List<Map<String, Object>> hyperList = ((List<Map<String, Object>>)JsonUtils.getObjectJsonMap(hyperMapData).get("Msg"));
			for (Map<String, Object> map : hyperList) {
				Map<String, Object> hyperDataMap = new HashMap<String, Object>();
				hyperDataMap.put("id", map.get("ID"));
				hyperDataMap.put("timeStamp", DateUtils.parseDate(map.get("FollowUpDateStr")).getTime());
				returnList.add(hyperDataMap);
			}
		}
 		return JsonUtils.getJson(BaseJsonVo.success(returnList));
	}
	
	@RequestMapping(value = "/getHyperFollowUpDetail", method = RequestMethod.GET)
	@ApiOperation(value = "获取高血压随访详情（通用）", notes = "{'userName':'登录医生用户名','personId':'居民ID','id':'高血压随访ID，若空则默认查询最近一次随访'}")
	public String getHyperFollowUpDetail(@RequestParam String userName,@RequestParam String personId, @RequestParam String id) {
		PersonInformationEntity pEntity=personService.getPersonInformationByPesronId(personId);
		if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		String productCode = null;
		if (StringUtils.isEmpty(userName)) {
			//userName为空，为居民端调用
			List<DoctorDetailVo> list =signService.getSignDoctorTeamInfo(personId);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					String value =list.get(i).getProductCode();
					if(StringUtils.isNotEmpty(value) && "1".equals(list.get(i).getLeaderFlag())){
						productCode = value;
					}
				}
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
			if(productCode==null || "".equals(productCode)){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), "该团队队长还未激活账号，请联系该队长"));
			}
		}else {
			//医生端调用，获取该医生的productCode
			DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
			productCode = docUser.getProductCode();
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(id)) {
			// id为空，默认查询最近一次随访
			Map<String, Object> hyperMap = new HashMap<String, Object>();
			//hyperMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
			//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
			hyperMap.put("ProductCode",productCode);
			
			hyperMap.put("PersonID", pEntity.getJwPersonId());
			hyperMap.put("PageSize", 1);
			hyperMap.put("PageIndex", 0);
			String hyperMapData = RestfulUtils.getPublicData("58-1", hyperMap);
			if (Integer.valueOf((String)JsonUtils.getObjectJsonMap(hyperMapData).get("Total")) > 0) {
				Map<String, Object> map = ((List<Map<String, Object>>)JsonUtils.getObjectJsonMap(hyperMapData).get("Msg")).get(0);
				id = (String) map.get("ID");
			}else {
				return JsonUtils.getJson(BaseJsonVo.success(returnMap));
			}
		}
		// 调用接口，获取高血压随访数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",productCode);
		
		paramMap.put("ID", id);
		String followUpData = RestfulUtils.getPublicData("58-3", paramMap);
		System.out.println(followUpData);
		// 解析返回json，封装返回数据
		if ((Integer)JsonUtils.getObjectJsonMap(followUpData).get("Result") > 0) {
			Map<String, Object> dataMap = (Map<String, Object>) JsonUtils.getObjectJsonMap(followUpData).get("Msg");
			// 随访信息部分
			Map<String, Object> hyperMap = (Map<String, Object>)dataMap.get("cmHypertension");
			if (hyperMap.get("NextFollowUpDate") != null && hyperMap.get("NextFollowUpDate") instanceof String) {
				returnMap.put("nextFollowUpDate", ((String)hyperMap.get("NextFollowUpDate")).replace("Date(", "").replace("/", "").replace(")", ""));	//下次随访时间
			}
			returnMap.put("symptom", hyperMap.get("Symptom"));	//症状
			returnMap.put("medicationCompliance", hyperMap.get("MedicationCompliance"));	//服药依从性
			returnMap.put("adverseDrugReactions", hyperMap.get("AdverseDrugReactions"));	//药物不良反应
			returnMap.put("fuClassification", hyperMap.get("FuClassification"));	//此次随访分类
			returnMap.put("followUpRemarks", hyperMap.get("FollowUpRemarks"));	//随访结局
			returnMap.put("doctorName", hyperMap.get("DoctorName"));	//随访医生
			returnMap.put("saltIntake", hyperMap.get("SaltIntake"));	//摄盐情况
			returnMap.put("psychologicalAdjustment", hyperMap.get("PsychologicalAdjustment"));	//心理调整
			returnMap.put("complianceBehavior", hyperMap.get("ComplianceBehavior"));	//尊医行为
			
			// 体征部分
			Map<String, Object> bodyMap = (Map<String, Object>)dataMap.get("examBody");
			returnMap.put("leftSystolicPressure", bodyMap.get("LeftSbp"));	//左侧收缩压
			returnMap.put("leftDiastolicPressure", bodyMap.get("LeftDbp"));	//左侧舒张压
			returnMap.put("rightSystolicPressure", bodyMap.get("RightSbp"));	//右侧收缩压
			returnMap.put("rightDiastolicPressure", bodyMap.get("RightDbp"));	//右侧舒张压
			returnMap.put("height", bodyMap.get("Height"));	//身高
			returnMap.put("weight", bodyMap.get("Weight"));	//体重
			returnMap.put("bmi", bodyMap.get("Bmi"));	//体质指数
			returnMap.put("heartRate", bodyMap.get("HeartRate"));	//心率
			// 生活方式部分
			Map<String, Object> lifeStyleMap = (Map<String, Object>)dataMap.get("examLifestyle");
			returnMap.put("smoking", lifeStyleMap.get("Smoking"));	//日吸烟量
			returnMap.put("dailyAlcoholIntake", lifeStyleMap.get("DailyAlcoholIntake"));	//日饮酒量
			returnMap.put("eachExerciseTime", lifeStyleMap.get("EachExerciseTime"));	//每次锻炼时间
			returnMap.put("exerciseWeekTimes", lifeStyleMap.get("ExerciseWeekTimes"));	//每周锻炼次数
			//用药部分
			List<Map<String, Object>> drugList = (List<Map<String, Object>>) dataMap.get("drugJson");
			List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> drugMap : drugList) {
				Map<String, Object> returnDrugMap = new HashMap<String, Object>();
				returnDrugMap.put("drugName", drugMap.get("Drugs"));	//药物名称
				returnDrugMap.put("dailyTimes", drugMap.get("DailyTimes"));	//每天次数
				returnDrugMap.put("eachDose", drugMap.get("EachDose"));	//每次剂量
				returnDrugMap.put("unit", drugMap.get("Remark"));	//单位
				returnList.add(returnDrugMap);
			}
			returnMap.put("medicineRecord", returnList);
		}
		
 		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}
	
	@RequestMapping(value = "/getBloodSugerFollowUpList", method = RequestMethod.GET)
	@ApiOperation(value = "获取糖尿病随访列表（通用）", notes = "{'userName':'登录医生用户名','personId':'居民ID'}")
	public String getBloodSugerFollowUpList(@RequestParam String userName,@RequestParam String personId) {
		PersonInformationEntity pEntity=personService.getPersonInformationByPesronId(personId);
		if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		String productCode = null;
		if (StringUtils.isEmpty(userName)) {
			//userName为空，为居民端调用
			List<DoctorDetailVo> list =signService.getSignDoctorTeamInfo(personId);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					String value =list.get(i).getProductCode();
					if(StringUtils.isNotEmpty(value) && "1".equals(list.get(i).getLeaderFlag())){
						productCode = value;
					}
				}
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
			if(productCode==null || "".equals(productCode)){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), "该团队队长还未激活账号，请联系该队长"));
			}
		}else {
			//医生端调用，获取该医生的productCode
			DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
			productCode = docUser.getProductCode();
		}
		// 调用接口，获取糖尿病随访列表数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",productCode);
		
		paramMap.put("PersonID", pEntity.getJwPersonId());
		paramMap.put("PageSize", 100);
		paramMap.put("PageIndex", 0);
		String hyperMapData = RestfulUtils.getPublicData("59-1", paramMap);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
 		if (Integer.valueOf((String)JsonUtils.getObjectJsonMap(hyperMapData).get("Total")) > 0) {
			List<Map<String, Object>> bloodSugerList = ((List<Map<String, Object>>)JsonUtils.getObjectJsonMap(hyperMapData).get("Msg"));
			for (Map<String, Object> map : bloodSugerList) {
				Map<String, Object> bloodSugerMap = new HashMap<String, Object>();
				bloodSugerMap.put("id", map.get("ID"));
				bloodSugerMap.put("timeStamp", DateUtils.parseDate(map.get("FollowUpDateStr")).getTime());
				returnList.add(bloodSugerMap);
			}
		}
 		return JsonUtils.getJson(BaseJsonVo.success(returnList));
	}
	
	@RequestMapping(value = "/getBloodSugerFollowUpDetail", method = RequestMethod.GET)
	@ApiOperation(value = "获取糖尿病随访详情（通用）", notes = "{'userName':'登录医生用户名','personId':'居民ID','id':'糖尿病随访ID，若空则默认查询最近一次随访'}")
	public String getBloodSugerFollowUpDetail(@RequestParam String userName,@RequestParam String personId, @RequestParam String id) {
		PersonInformationEntity pEntity=personService.getPersonInformationByPesronId(personId);
		if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		String productCode = null;
		if (StringUtils.isEmpty(userName)) {
			//userName为空，为居民端调用
			List<DoctorDetailVo> list =signService.getSignDoctorTeamInfo(personId);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					String value =list.get(i).getProductCode();
					if(StringUtils.isNotEmpty(value) && "1".equals(list.get(i).getLeaderFlag())){
						productCode = value;
					}
				}
				 
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
			if(productCode==null || "".equals(productCode)){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), "该团队队长还未激活账号，请联系该队长"));
			}
		}else {
			//医生端调用，获取该医生的productCode
			DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
			productCode = docUser.getProductCode();
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(id)) {
			// id为空，默认查询最近一次随访
			Map<String, Object> hyperMap = new HashMap<String, Object>();
			//hyperMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
			//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
			hyperMap.put("ProductCode",productCode);
			
			hyperMap.put("PersonID", pEntity.getJwPersonId());
			hyperMap.put("PageSize", 1);
			hyperMap.put("PageIndex", 0);
			String hyperMapData = RestfulUtils.getPublicData("59-1", hyperMap);
			if (JsonUtils.getObjectJsonMap(hyperMapData).get("Total")!=null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(hyperMapData).get("Total")) > 0 ) {
				Map<String, Object> map = ((List<Map<String, Object>>)JsonUtils.getObjectJsonMap(hyperMapData).get("Msg")).get(0);
				id = (String) map.get("ID");
			}else {
				return JsonUtils.getJson(BaseJsonVo.success(returnMap));
			}
		}
		// 调用接口，获取高血压随访数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",productCode);
		
		paramMap.put("ID", id);
		String followUpData = RestfulUtils.getPublicData("59-3", paramMap);
		// 解析返回json，封装返回数据
		System.out.println(followUpData);
		if ((Integer)JsonUtils.getObjectJsonMap(followUpData).get("Result") > 0) {
			Map<String, Object> dataMap = (Map<String, Object>) JsonUtils.getObjectJsonMap(followUpData).get("Msg");
			// 随访信息部分
			Map<String, Object> bloodSugerMap = (Map<String, Object>)dataMap.get("cmDiabetes");
			returnMap.put("followUpDate", ((String)bloodSugerMap.get("FollowUpDate")).replace("Date(", "").replace("/", "").replace(")", ""));//随访日期
			returnMap.put("wayUp",bloodSugerMap.get("WayUp"));	//随访方式
			returnMap.put("symptom",bloodSugerMap.get("Symptom"));	//症状
			returnMap.put("psychologicalAdjustment",bloodSugerMap.get("PsychologicalAdjustment"));	//心理调整
			returnMap.put("complianceBehavior",bloodSugerMap.get("ComplianceBehavior"));	//遵医行为
			returnMap.put("medicationCompliance",bloodSugerMap.get("MedicationCompliance"));	//服药依从性
			returnMap.put("adverseDrugReactions",bloodSugerMap.get("AdverseDrugReactions"));	//药物不良反应
			returnMap.put("lowBloodSugarReactions",bloodSugerMap.get("LowBloodSugarReactions"));	//低血糖反应
			returnMap.put("fuClassification",bloodSugerMap.get("FuClassification"));	//此次随访分类
			returnMap.put("followUpRemarks",bloodSugerMap.get("FollowUpRemarks"));	//随访结局
			returnMap.put("staple",bloodSugerMap.get("Staple"));	//随访结局
			if (bloodSugerMap.get("NextFollowUpDate") != null) {
				returnMap.put("nextFollowUpDate",((String)bloodSugerMap.get("NextFollowUpDate")).replace("Date(", "").replace("/", "").replace(")", ""));	//下次随访日期
			}
			returnMap.put("DoctorName",bloodSugerMap.get("DoctorName"));	//随访医生
			// 体征信息部分
			Map<String, Object> bodyMap = (Map<String, Object>)dataMap.get("examBody");
			returnMap.put("leftSystolicPressure", bodyMap.get("LeftSbp"));	//左侧收缩压
			returnMap.put("leftDiastolicPressure", bodyMap.get("LeftDbp"));	//左侧舒张压
			returnMap.put("rightSystolicPressure", bodyMap.get("RightSbp"));	//右侧收缩压
			returnMap.put("rightDiastolicPressure", bodyMap.get("RightDbp"));	//右侧舒张压
			returnMap.put("height", bodyMap.get("Height"));	//身高
			returnMap.put("weight", bodyMap.get("Weight"));	//体重
			returnMap.put("bmi", bodyMap.get("Bmi"));	//体质指数
			returnMap.put("dorsalisPedisArteryPulse", bodyMap.get("DorsalisPedisArteryPulse"));	//足背动脉搏动
			// 辅助检查部分
			Map<String, Object> laboratoryMap = (Map<String, Object>)dataMap.get("examLaboratory");
			returnMap.put("fastingBloodGlucose", laboratoryMap.get("FastingBloodGlucose"));	//空腹血糖
			returnMap.put("postprandialBloodGlucose", laboratoryMap.get("PostprandialBloodGlucose"));	//餐后血糖
			// 生活方式部分
			Map<String, Object> lifeStyleMap = (Map<String, Object>)dataMap.get("examLifestyle");
			returnMap.put("smoking", lifeStyleMap.get("Smoking"));	//日吸烟量
			returnMap.put("dailyAlcoholIntake", lifeStyleMap.get("DailyAlcoholIntake"));	//日饮酒量
			returnMap.put("eachExerciseTime", lifeStyleMap.get("EachExerciseTime"));	//每次锻炼时间
			returnMap.put("exerciseWeekTimes", lifeStyleMap.get("ExerciseWeekTimes"));	//每周锻炼次数
			returnMap.put("diet", lifeStyleMap.get("Diet"));	//主食
			// 用药部分
			List<Map<String, Object>> drugList = (List<Map<String, Object>>) dataMap.get("drugJson");
			List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> drugMap : drugList) {
				Map<String, Object> returnDrugMap = new HashMap<String, Object>();
				returnDrugMap.put("drugName", drugMap.get("Drugs"));	//药物名称
				returnDrugMap.put("dailyTimes", drugMap.get("DailyTimes"));	//每天次数
				returnDrugMap.put("eachDose", drugMap.get("EachDose"));	//每次剂量
				returnDrugMap.put("unit", drugMap.get("Remark"));	//单位
				returnList.add(returnDrugMap);
			}
			returnMap.put("medicineRecord", returnList);
			// 胰岛素部分
			List<Map<String, Object>> insulinList = (List<Map<String, Object>>) dataMap.get("insulindrug");
			List<Map<String, Object>> returnInsulinList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> drugMap : insulinList) {
				Map<String, Object> returnDrugMap = new HashMap<String, Object>();
				returnDrugMap.put("insulinName", drugMap.get("Drugs"));	//胰岛素名称
				returnDrugMap.put("dailyTimes", drugMap.get("DailyTimes"));	//每天次数
				returnDrugMap.put("eachDose", drugMap.get("EachDose"));	//每次剂量
				returnDrugMap.put("unit", drugMap.get("Remark"));	//单位
				returnInsulinList.add(returnDrugMap);
			}
			returnMap.put("insulinList", returnInsulinList);
		}
		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}
	
	@RequestMapping(value = "/getExaminationList", method = RequestMethod.GET)
	@ApiOperation(value = "获取体检列表（通用）", notes = "{'userName':'登录医生用户名','personId':'居民ID'}")
	public String getExaminationList(@RequestParam String userName,@RequestParam String personId) {
		PersonInformationEntity pEntity=personService.getPersonInformationByPesronId(personId);
		if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		String productCode = null;
		if (StringUtils.isEmpty(userName)) {
			//userName为空，为居民端调用
			List<DoctorDetailVo> list =signService.getSignDoctorTeamInfo(personId);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					String value =list.get(i).getProductCode();
					if(StringUtils.isNotEmpty(value) && "1".equals(list.get(i).getLeaderFlag())){
						productCode = value;
					}
				}
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
			if(productCode==null || "".equals(productCode)){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), "该团队队长还未激活账号，请联系该队长"));
			}
		}else {
			//医生端调用，获取该医生的productCode
			DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
			productCode = docUser.getProductCode();
		}
		// 调用接口，获取体检列表数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",productCode);
		
		paramMap.put("PersonID", pEntity.getJwPersonId());
		String hyperMapData = RestfulUtils.getPublicData("56-6", paramMap);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
 		if ((Integer)JsonUtils.getObjectJsonMap(hyperMapData).get("Result") > 0 ) {
			List<Map<String, Object>> examList = ((List<Map<String, Object>>)JsonUtils.getObjectJsonMap(hyperMapData).get("Msg"));
			for (Map<String, Object> map : examList) {
				Map<String, Object> examDataMap = new HashMap<String, Object>();
				examDataMap.put("id", map.get("MtId"));
				examDataMap.put("timeStamp", DateUtils.parseDate(map.get("ExamDate")).getTime());
				returnList.add(examDataMap);
			}
		}
 		return JsonUtils.getJson(BaseJsonVo.success(returnList));
	}
	
	@RequestMapping(value = "/getExaminationDetail", method = RequestMethod.GET)
	@ApiOperation(value = "获取体检详情（通用）", notes = "{'userName':'登录医生用户名','personId':'居民ID','id':'体检ID，若空则默认查询最近一次体检'}")
	public String getExaminationDetail(@RequestParam String userName,@RequestParam String personId, @RequestParam String id) {
		PersonInformationEntity pEntity=personService.getPersonInformationByPesronId(personId);
		if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		String productCode = null;
		if (StringUtils.isEmpty(userName)) {
			//userName为空，为居民端调用
			List<DoctorDetailVo> list =signService.getSignDoctorTeamInfo(personId);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					String value =list.get(i).getProductCode();
					if(StringUtils.isNotEmpty(value) && "1".equals(list.get(i).getLeaderFlag())){
						productCode = value;
					}
				}
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
			if(productCode==null || "".equals(productCode)){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), "该团队队长还未激活账号，请联系该队长"));
			}
		}else {
			//医生端调用，获取该医生的productCode
			DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
			productCode = docUser.getProductCode();
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(id)) {
			// 调用接口，获取体检列表数据
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
			//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
			paramMap.put("ProductCode",productCode);
			
			paramMap.put("PersonID", pEntity.getJwPersonId());
			String examMapData = RestfulUtils.getPublicData("56-6", paramMap);
			if ((Integer)JsonUtils.getObjectJsonMap(examMapData).get("Result") > 0 && ((List<Map<String, Object>>)JsonUtils.getObjectJsonMap(examMapData).get("Msg")).size() > 0) {
				Map<String, Object> map = ((List<Map<String, Object>>)JsonUtils.getObjectJsonMap(examMapData).get("Msg")).get(0);
				id = (String) map.get("MtId");
			}else {
				return JsonUtils.getJson(BaseJsonVo.success(returnMap));
			}
		}
		// 调用接口，获取体检数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",productCode);
		
		paramMap.put("MtID", id);
		String returnData = RestfulUtils.getPublicData("56-4", paramMap);
		// 解析json数据，封装返回map
		if ((Integer)JsonUtils.getObjectJsonMap(returnData).get("Result") > 0) {
			Map<String, Object> dataMap = (Map<String, Object>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			// 封装体检主信息
			Map<String, Object> masterMap = (Map<String, Object>) dataMap.get("master");
			Map<String, Object> returnMainMap = new HashMap<String, Object>();
			returnMainMap.put("examDate", ((String)masterMap.get("ExamDate")).replace("Date(", "").replace("/", "").replace(")", ""));
			returnMainMap.put("doctorName", masterMap.get("DoctorName"));	//责任医师
			returnMainMap.put("symptom", masterMap.get("Symptom"));	//症状
			returnMainMap.put("assessment", masterMap.get("Assessment"));	//健康评价有无异常
			returnMainMap.put("assessmentAbnormal", masterMap.get("AssessmentAbnormal"));	//健康评价异常情况
			returnMainMap.put("guidance", masterMap.get("Guidance"));	//健康指导
			returnMainMap.put("riskCrtl", masterMap.get("RiskCrtl"));	//危险因素控制
			returnMainMap.put("healthSummary", masterMap.get("HealthSummary"));	//健康摘要
			returnMap.put("mainInfo", returnMainMap);
			// 封装体征信息
			Map<String, Object> bodyMap = (Map<String, Object>) dataMap.get("body");
			Map<String, Object> returnBodyMap = new HashMap<String, Object>();
			returnBodyMap.put("bodyTemperature", bodyMap.get("BodyTemperature"));	//体温
			returnBodyMap.put("pulseRate", bodyMap.get("PulseRate"));	//脉率
			returnBodyMap.put("respiratoryRate", bodyMap.get("RespiratoryRate"));	//呼吸频率
			returnBodyMap.put("leftSystolicPressure", bodyMap.get("LeftSbp"));	//左侧收缩压
			returnBodyMap.put("leftDiastolicPressure", bodyMap.get("LeftDbp"));	//左侧舒张压
			returnBodyMap.put("rightSystolicPressure", bodyMap.get("RightSbp"));	//右侧收缩压
			returnBodyMap.put("rightDiastolicPressure", bodyMap.get("RightDbp"));	//右侧舒张压
			returnBodyMap.put("height", bodyMap.get("Height"));	//身高
			returnBodyMap.put("weight", bodyMap.get("Weight"));	//体重
			returnBodyMap.put("bmi", bodyMap.get("Bmi"));	//体质指数
			returnBodyMap.put("heartRate", bodyMap.get("HeartRate"));	//心率
			returnMap.put("body", returnBodyMap);
			// 封装脏器功能信息
			Map<String, Object> organMap = (Map<String, Object>) dataMap.get("organ");
			Map<String, Object> returnOrganMap = new HashMap<String, Object>();
			if (organMap != null) {
				Iterator organIt = organMap.entrySet().iterator(); 
				while (organIt.hasNext()) { 	//将key转化为首字母小写输出
					Map.Entry entry = (Map.Entry) organIt.next(); 
					String key = (String) entry.getKey(); 
					Object value = entry.getValue(); 
					if (!"ID".equals(key) && !"ExamDate".equals(key)) {	//ID不需要，体检日期重复，因此也不需要
						returnOrganMap.put(StringUtils.captureName(key), value);
					}
				} 
			}
			returnMap.put("organ", returnOrganMap);
			// 封装辅助检查
			Map<String, Object> laboraMap = (Map<String, Object>) dataMap.get("labora");
			Map<String, Object> returnLaboraMap = new HashMap<String, Object>();
			if (laboraMap != null) {
				Iterator laboraIt = laboraMap.entrySet().iterator(); 
				while (laboraIt.hasNext()) { 	//将key转化为首字母小写输出
					Map.Entry entry = (Map.Entry) laboraIt.next(); 
					String key = (String) entry.getKey(); 
					Object value = entry.getValue(); 
					if (!"ID".equals(key) && !"ExamDate".equals(key)) {	//ID不需要，体检日期重复，因此也不需要
						returnLaboraMap.put(StringUtils.captureName(key), value);
					}
				} 
				returnMap.put("labora", returnLaboraMap);
			}
		}
		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}
}
