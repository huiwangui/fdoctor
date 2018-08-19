package com.boco.modules.fdoc.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.comparator.AbnormalSignDateComparator;
import com.boco.common.comparator.LongComparator;
import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.push.CloudMobilePush;
import com.boco.common.push.PushUtils;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.BloodSugerRecordEntity;
import com.boco.modules.fdoc.model.HypertensionRecordEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.service.BloodSugerRecordService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.HypertensionRecordService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SendMsgService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.BloodSugerRecordVo;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.HypertensionRecordVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/abnormalSign", produces = "text/json;charset=UTF-8")
public class AbnormalSignApi {
	
	@Resource
	HypertensionRecordService hyperService;
	@Resource
	DocUserService docUserService;
	@Resource
	BloodSugerRecordService bloodSugerService;
	@Resource
	SignService signService;
	@Resource
	PersonInformationService personService;
	@Resource
	SendMsgService sendMsgService;
 
	/**
	 * 血压部分
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHypertensionNum", method = RequestMethod.GET)
	@ApiOperation(value = "查询血压总共记录数、正常数、偏低偏高数（通用）", notes = "{'personId':'居民ID','userName':'医生登录账号'}")
	public String getHypertensionNum(@RequestParam String userName, @RequestParam String personId) {
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
		
		// 查询本地库，获取血压记录总数
		Map<String, Integer> recordCount = hyperService.getRecordCount(personId);
		Integer allCount = recordCount.get("allCount");
		Integer lowCount = recordCount.get("lowCount");
		Integer normalCount = recordCount.get("normalCount");
		Integer highCount = recordCount.get("highCount");
		
		// 调用接口，获取公卫血压数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("PageSize", 100);	//调取前100条记录
		map.put("PageIndex", 0);
		
		String returnData = RestfulUtils.getPublicData("58-1", map);
		if (JsonUtils.getObjectJsonMap(returnData).get("Total")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Total")) > 0) {
			List<Map<String, Object>> hyperList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			for (Map<String, Object> hyperMap : hyperList) {
				if (hyperMap.get("Sbp") != null && hyperMap.get("Dbp") != null) {
					Double systolicPressure = Double.valueOf((Integer) hyperMap.get("Sbp"));	//收缩压
					Double diastolicPressure = Double.valueOf((Integer) hyperMap.get("Dbp"));	//舒张压
					
					if (systolicPressure < 90 || diastolicPressure < 60) {
						lowCount ++;
					}else if (90 <= systolicPressure && systolicPressure <= 139 && 60 <= diastolicPressure && diastolicPressure <= 89) {
						normalCount ++ ;
					}else {
						highCount ++;
					}
					allCount ++;
				}
			}
		}
		recordCount.clear();
		recordCount.put("allCount", allCount);
		recordCount.put("lowCount", lowCount);
		recordCount.put("normalCount", normalCount);
		recordCount.put("highCount", highCount);
		
		return JsonUtils.getJson(BaseJsonVo.success(recordCount));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHypertensionDateList", method = RequestMethod.GET)
	@ApiOperation(value = "查询有血压记录的时期列表（通用）", notes = "{'personId':'居民ID','userName':'医生登录账号'}")
	public String getHypertensionDateList(@RequestParam String userName, @RequestParam String personId) {
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
		// 查询本地库，获取血压记录
		List<HypertensionRecordEntity> recordList = hyperService.getRecordList(personId);
		
		//遍历记录列表，放入日期
		List<Long> timeStampList = new ArrayList<Long>();
		for (HypertensionRecordEntity entity : recordList) {
			timeStampList.add(entity.getMeasuringTime().getTime());
		}
		
		// 调用接口，获取公卫血压数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("PageSize", 100);	//调取前100条记录
		map.put("PageIndex", 0);
		
		try {
			String returnData = RestfulUtils.getPublicData("58-1", map);
			if (JsonUtils.getObjectJsonMap(returnData).get("Total")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Total")) > 0) {
				List<Map<String, Object>> hyperList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
				for (Map<String, Object> hyperMap : hyperList) {
					String followUpDateStr = (String)hyperMap.get("FollowUpDateStr");
					timeStampList.add(DateUtils.parseDate(followUpDateStr).getTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 按照降序排列
		LongComparator comparator = new LongComparator(true);
		Collections.sort(timeStampList,comparator);
		return JsonUtils.getJson(BaseJsonVo.success(timeStampList));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHypertensionList", method = RequestMethod.GET)
	@ApiOperation(value = "查询最近7次血压记录（通用端）", notes = "{'personId':'居民ID','userName':'医生登录账号'}")
	public String getHypertensionList(@RequestParam String userName, @RequestParam String personId) {
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
		// 查询本地库，获取血压记录总数
		List<HypertensionRecordEntity> recordList = hyperService.getRecordList(personId);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (HypertensionRecordEntity entity : recordList) {
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("timeStamp", entity.getMeasuringTime().getTime());
			itemMap.put("systolicPressure",entity.getSystolicPressure());
			itemMap.put("diastolicPressure", entity.getDiastolicPressure());
			list.add(itemMap);
		}
		
		// 调用接口，获取公卫血压数据
		Map<String, Object> map = new HashMap<String, Object>();
	//	map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("PageSize", 7);	//调取前7条记录
		map.put("PageIndex", 0);
		String returnData = RestfulUtils.getPublicData("58-1", map);
		if (JsonUtils.getObjectJsonMap(returnData).get("Total")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Total")) > 0) {
			List<Map<String, Object>> hyperList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			for (Map<String, Object> hyperMap : hyperList) {
				if (hyperMap.get("Sbp") != null && hyperMap.get("Dbp") != null) {
					String followUpDateStr = (String)hyperMap.get("FollowUpDateStr");
					
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap.put("timeStamp", DateUtils.parseDate(followUpDateStr).getTime());
					itemMap.put("systolicPressure",hyperMap.get("Sbp"));
					itemMap.put("diastolicPressure", hyperMap.get("Dbp"));
					list.add(itemMap);
				}
			}
		}
		//把获取到的集合按照时间排序，并取最近7条
		AbnormalSignDateComparator comparator = new AbnormalSignDateComparator();
		Collections.sort(list,comparator);
		if (list.size() > 7) {
			return JsonUtils.getJson(BaseJsonVo.success(list.subList(0, 7)));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(list));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getLastBloodPressure", method = RequestMethod.GET)
	@ApiOperation(value = "查询最近的一次血压记录（通用端）", notes = "{'personId':'居民ID','userName':'医生登录账号'}")
	public String getLastBloodPressure(@RequestParam String userName, @RequestParam String personId) {
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
		// 查询本地库，获取最近一次血压记录
		HypertensionRecordEntity lastRecord = hyperService.getLastRecord(personId);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if (lastRecord != null) {
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("timeStamp", lastRecord.getMeasuringTime().getTime());
			itemMap.put("systolicPressure",lastRecord.getSystolicPressure());
			itemMap.put("diastolicPressure", lastRecord.getDiastolicPressure());
			list.add(itemMap);
		}
		// 调用接口，获取公卫血压数据
		Map<String, Object> map = new HashMap<String, Object>();
	//	map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("PageSize", 1);	//调取最近一条记录
		map.put("PageIndex", 0);
		String returnData = RestfulUtils.getPublicData("58-1", map);
		if (JsonUtils.getObjectJsonMap(returnData).get("Total")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Total")) > 0) {
			List<Map<String, Object>> hyperList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			if (hyperList.size() > 0) {
				Map<String, Object> hyperMap = hyperList.get(0);
				String followUpDateStr = (String)hyperMap.get("FollowUpDateStr");
				
				Map<String, Object> itemMap = new HashMap<String, Object>();
				itemMap.put("timeStamp", DateUtils.parseDate(followUpDateStr).getTime());
				itemMap.put("systolicPressure",hyperMap.get("Sbp"));
				itemMap.put("diastolicPressure", hyperMap.get("Dbp"));
				list.add(itemMap);
			}
			
		}
		//把获取到的集合按照时间排序，并取最近1条
		AbnormalSignDateComparator comparator = new AbnormalSignDateComparator();
		Collections.sort(list,comparator);
		if (list.size() > 0) {
			return JsonUtils.getJson(BaseJsonVo.success(list.get(0)));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBloodPressureByDate", method = RequestMethod.GET)
	@ApiOperation(value = "根据日期获取血压记录（通用端）", notes = "{'personId':'居民ID','userName':'医生登录账号','timeStamp':'查询日期时间戳'}")
	public String getBloodPressureByDate(@RequestParam String userName, @RequestParam String personId, @RequestParam Long timeStamp) {
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
		
		// 调用接口，获取公卫血压数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("StartTime", DateUtils.formatDate(new Date(timeStamp)));	//调取最近一条记录
		map.put("EndTime", DateUtils.formatDate(new Date(timeStamp)));
		String returnData = RestfulUtils.getPublicData("57-9", map);
		
		if (JsonUtils.getObjectJsonMap(returnData).get("Count")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Count")) > 0) {
			// 如果调用接口获取到了数据，则取第一条作为结果返回
			List<Map<String, Object>> hyperList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			Map<String, Object> hyperMap = hyperList.get(0);
			String DateStr = (String)hyperMap.get("Date");
			
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("timeStamp", DateUtils.parseDate(DateStr));
			itemMap.put("systolicPressure",hyperMap.get("Sbp"));
			itemMap.put("diastolicPressure", hyperMap.get("Dbp"));
			return JsonUtils.getJson(BaseJsonVo.success(itemMap));
		}else {
			// 如果接口没有获取到数据，则查询本地库，获取最近一条
			HypertensionRecordVo vo = new HypertensionRecordVo();
			vo.setPersonId(personId);
			vo.setStartDate(new Date(DateUtils.getStartTimeOfDay(new Date(timeStamp))));
			vo.setEndDate(new Date(DateUtils.getEndTimeOfDay(new Date(timeStamp))));
			HypertensionRecordVo lastRecordInDay = hyperService.getLastRecordInDay(vo);
			if (lastRecordInDay != null) {
				Map<String, Object> itemMap = new HashMap<String, Object>();
				itemMap.put("timeStamp", lastRecordInDay.getMeasuringTime().getTime());
				itemMap.put("systolicPressure",lastRecordInDay.getSystolicPressure());
				itemMap.put("diastolicPressure", lastRecordInDay.getDiastolicPressure());
				return JsonUtils.getJson(BaseJsonVo.success(itemMap));
			}else {
				return JsonUtils.getJson(BaseJsonVo.success(null));
			}
		}
	}
	
	/**
	 * 血糖部分
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBloodSugerNum", method = RequestMethod.GET)
	@ApiOperation(value = "查询血糖总共记录数、正常数、偏低偏高数（通用端）", notes = "{'personId':'居民ID','userName':'医生登录账号'}")
	public String getBloodSugerNum(@RequestParam String userName, @RequestParam String personId) {
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
		// 查询本地库，获取血糖记录总数
		Map<String, Integer> recordCount = bloodSugerService.getRecordCount(personId);
		Integer allCount = recordCount.get("allCount");
		Integer lowCount = recordCount.get("lowCount");
		Integer normalCount = recordCount.get("normalCount");
		Integer highCount = recordCount.get("highCount");
		
		// 调用接口，获取公卫血糖数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("StartTime", "2000-01-01");	//调取前100条记录
		map.put("EndTime", DateUtils.formatDate(new Date()));
		
		String returnData = RestfulUtils.getPublicData("59-6", map);
		if (JsonUtils.getObjectJsonMap(returnData).get("Count")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Count")) > 0) {
			List<Map<String, Object>> rawList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			for (Map<String, Object> rawMap : rawList) {
				if (rawMap.get("Data") != null) {
					Double bloodSugerValue = Double.valueOf((String) rawMap.get("Data"));
					if (bloodSugerValue < 3.9) {
						lowCount ++;
					}else if (3.9 <= bloodSugerValue && bloodSugerValue < 7.0) {
						normalCount ++;
					}else {
						highCount ++;
					}
					allCount ++;
				}
			}
		}
		recordCount.clear();
		recordCount.put("allCount", allCount);
		recordCount.put("lowCount", lowCount);
		recordCount.put("normalCount", normalCount);
		recordCount.put("highCount", highCount);
		
		return JsonUtils.getJson(BaseJsonVo.success(recordCount));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBloodSugerDateList", method = RequestMethod.GET)
	@ApiOperation(value = "查询有血糖记录的时期列表（通用端）", notes = "{'personId':'居民ID','userName':'医生登录账号'}")
	public String getBloodSugerDateList(@RequestParam String userName, @RequestParam String personId) {
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
		// 查询本地库，获取血压记录
		List<BloodSugerRecordEntity> recordList = bloodSugerService.getRecordList(personId);
		
		//遍历记录列表，放入日期
		List<Long> timeStampList = new ArrayList<Long>();
		for (BloodSugerRecordEntity entity : recordList) {
			timeStampList.add(entity.getMeasuringTime().getTime());
		}
		
		// 调用接口，获取公卫血糖数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("StartTime", "2000-01-01");	//调取前100条记录
		map.put("EndTime", DateUtils.formatDate(new Date()));
		
		String returnData = RestfulUtils.getPublicData("59-6", map);
	
		if (JsonUtils.getObjectJsonMap(returnData).get("Count")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Count")) > 0) {
			List<Map<String, Object>> rawList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			for (Map<String, Object> rawMap : rawList) {
				if (rawMap.get("Data") != null) {
					String followUpDateStr = (String)rawMap.get("Date");
					timeStampList.add(DateUtils.parseDate(followUpDateStr).getTime());
				}
			}
		}
		// 按照降序排列
		LongComparator comparator = new LongComparator(true);
		Collections.sort(timeStampList,comparator);
		return JsonUtils.getJson(BaseJsonVo.success(timeStampList));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBloodSugerList", method = RequestMethod.GET)
	@ApiOperation(value = "查询最近7次血糖记录（通用端）", notes = "{'personId':'居民ID','userName':'医生登录账号'}")
	public String getBloodSugerList(@RequestParam String userName, @RequestParam String personId) {
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
		// 查询本地库，获取血糖记录列表
		List<BloodSugerRecordEntity> recordList = bloodSugerService.getRecordList(personId);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (BloodSugerRecordEntity entity : recordList) {
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("timeStamp", entity.getMeasuringTime().getTime());
			itemMap.put("bloodSuger",entity.getBloodSuger());
			list.add(itemMap);
		}
		
		// 调用接口，获取公卫血糖数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("StartTime", "2000-01-01");	//调取前100条记录
		map.put("EndTime", DateUtils.formatDate(new Date()));
		
		String returnData = RestfulUtils.getPublicData("59-6", map);
		
		if (JsonUtils.getObjectJsonMap(returnData).get("Count")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Count")) > 0) {
			List<Map<String, Object>> rawList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			for (Map<String, Object> rawMap : rawList) {
				if (rawMap.get("Data") != null) {
					String DateStr = (String)rawMap.get("Date");
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap.put("timeStamp", DateUtils.parseDate(DateStr).getTime());
					itemMap.put("bloodSuger",rawMap.get("Data"));
					list.add(itemMap);
				}
			}
		}
		//把获取到的集合按照时间排序，并取最近7条
		AbnormalSignDateComparator comparator = new AbnormalSignDateComparator();
		Collections.sort(list,comparator);
		if (list.size() >= 7 ) {
			return JsonUtils.getJson(BaseJsonVo.success(list.subList(0, 7)));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(list));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getLastBloodSuger", method = RequestMethod.GET)
	@ApiOperation(value = "查询最近的一次血糖记录（通用）", notes = "{'personId':'居民ID','userName':'医生登录账号'}")
	public String getLastBloodSuger(@RequestParam String userName, @RequestParam String personId) {
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
		// 查询本地库，获取最近一次血压记录
		BloodSugerRecordEntity lastRecord = bloodSugerService.getLastRecord(personId);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if (lastRecord != null) {
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("timeStamp", lastRecord.getMeasuringTime().getTime());
			itemMap.put("bloodSuger",lastRecord.getBloodSuger());
			list.add(itemMap);
		}
		// 调用接口，获取公卫血糖数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("StartTime", "2000-01-01");	//调取前100条记录
		map.put("EndTime", DateUtils.formatDate(new Date()));
		
		String returnData = RestfulUtils.getPublicData("59-6", map);
		
		if (JsonUtils.getObjectJsonMap(returnData).get("Count")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Count")) > 0) {
			List<Map<String, Object>> rawList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
				for (int i = rawList.size() - 1; i >= 0; i--) {
					Map<String, Object> rawMap = rawList.get(i);
					if (rawMap.get("Data") != null) {
						String DateStr = (String)rawMap.get("Date");
						Map<String, Object> itemMap = new HashMap<String, Object>();
						itemMap.put("timeStamp", DateUtils.parseDate(DateStr).getTime());
						itemMap.put("bloodSuger",rawMap.get("Data"));
						list.add(itemMap);
						break;
					}
				}
		}
		//把获取到的集合按照时间排序，并取最近1条
		AbnormalSignDateComparator comparator = new AbnormalSignDateComparator();
		Collections.sort(list,comparator);
		if (list.size() > 0) {
			return JsonUtils.getJson(BaseJsonVo.success(list.get(0)));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBloodSugerByDate", method = RequestMethod.GET)
	@ApiOperation(value = "根据日期获取血糖记录（通用）", notes = "{'personId':'居民ID','userName':'医生登录账号','timeStamp':'查询日期时间戳'}")
	public String getBloodSugerByDate(@RequestParam String userName, @RequestParam String personId, @RequestParam Long timeStamp) {
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
		
		// 调用接口，获取公卫血糖数据
		Map<String, Object> map = new HashMap<String, Object>();
	//	map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("StartTime", DateUtils.formatDate(new Date(timeStamp)));	//调取前100条记录
		map.put("EndTime", DateUtils.formatDate(new Date(timeStamp)));
		
		String returnData = RestfulUtils.getPublicData("59-6", map);
		
		if (JsonUtils.getObjectJsonMap(returnData).get("Count")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Count")) > 0) {
			// 如果调用接口获取到了数据，则取第一条作为结果返回
			List<Map<String, Object>> rawList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			for (Map<String, Object> rawMap : rawList) {
				if (rawMap.get("Data") != null) {
					String DateStr = (String)rawMap.get("Date");
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap.put("timeStamp", DateUtils.parseDate(DateStr));
					itemMap.put("bloodSuger",rawMap.get("Data"));
					return JsonUtils.getJson(BaseJsonVo.success(itemMap));
				}
			}
		}
		// 如果接口没有获取到数据，则查询本地库，获取最近一条
		BloodSugerRecordVo vo = new BloodSugerRecordVo();
		vo.setPersonId(personId);
		vo.setStartDate(new Date(DateUtils.getStartTimeOfDay(new Date(timeStamp))));
		vo.setEndDate(new Date(DateUtils.getEndTimeOfDay(new Date(timeStamp))));
		BloodSugerRecordEntity lastRecordInDay = bloodSugerService.getLastRecordInDay(vo);
		if (lastRecordInDay != null) {
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("timeStamp", lastRecordInDay.getMeasuringTime().getTime());
			itemMap.put("bloodSuger",lastRecordInDay.getBloodSuger());
			return JsonUtils.getJson(BaseJsonVo.success(itemMap));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	@RequestMapping(value = "/addHypertensionRecord", method = RequestMethod.POST)
	@ApiOperation(value = "记录血压信息（居民端）", notes = "{'personId':'居民ID','systolicPressure':'收缩压','diastolicPressure':'舒张压','remarks':'备注'}")
	public String addHypertensionRecord(@RequestBody String paramBody) {
		// 解析json,转化为对象
		HypertensionRecordEntity entity = JsonUtils.getObject(paramBody, HypertensionRecordEntity.class);
		
		Map<String, Object> returnMap = hyperService.addHypertensionRecord(entity);
		String returnMsg = (String) returnMap.get("returnMsg");
		if (BusinessConstants.FAIL.equals(returnMsg)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NOT_SIGN.getKey(),
					ApiStatusEnum.NOT_SIGN.getValue()));
		}
		boolean returnFlag = (boolean) returnMap.get("abnormalFlag");
		Integer assessmentId = (Integer) returnMap.get("assessmentId");
		Long createTime = (Long) returnMap.get("createTime");
		
		// 体征异常，进行推送
		if (returnFlag) {
			// 封装推送信息列表
			Map<String, Object> pushMap = new HashMap<String, Object>();
			pushMap.put("id", assessmentId);
			pushMap.put("personId", entity.getPersonId());
			pushMap.put("systolicPressure", entity.getSystolicPressure());
			pushMap.put("diastolicPressure", entity.getDiastolicPressure());
			pushMap.put("remarks", entity.getRemarks());
			pushMap.put("exceptionType", BusinessConstants.HEALTH_ASSESSMENT_TYPE_HYPER);
			pushMap.put("createTime", createTime);
			PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(entity.getPersonId());
			// 获取推送账户
			List<String> accounts = signService.getSignTeamUsers(entity.getPersonId());
			CloudMobilePush push = new CloudMobilePush();
			String title = "血压异常";
			String content = "您的签约居民"+personInfo.getPersonName()+"出现血压异常，点击查看！";
			push.androidPush(accounts, title, content, BusinessConstants.PUSH_ACTIVITY, 
					BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_ABNORMAL_HYPER, pushMap));
			/**
			 * 插入推送消息表
			 */
			SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_DOC_INT,
					BusinessConstants.PUSH_TYPE_ABNORMAL_HYPER,JsonUtils.getJsonFormat(pushMap));
			for (String account : accounts) {
				msg.setDocUserName(account);
				sendMsgService.addMsg(msg);
			}
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/addBloodSugerRecord", method = RequestMethod.POST)
	@ApiOperation(value = "记录血糖信息（居民端）", notes = "{'personId':'居民ID','bloodSuger':'血糖值','remarks':'备注'}")
	public String addBloodSugerRecord(@RequestBody String paramBody) {
		// 解析json,转化为对象
		BloodSugerRecordEntity entity = JsonUtils.getObject(paramBody, BloodSugerRecordEntity.class);
		
		Map<String, Object> returnMap = bloodSugerService.addBloodSugerRecord(entity);
		String returnMsg = (String) returnMap.get("returnMsg");
		if (BusinessConstants.FAIL.equals(returnMsg)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NOT_SIGN.getKey(),
					ApiStatusEnum.NOT_SIGN.getValue()));
		}
		boolean returnFlag = (boolean) returnMap.get("abnormalFlag");
		Integer assessmentId = (Integer) returnMap.get("assessmentId");
		Long createTime = (Long) returnMap.get("createTime");
		// 若出现体征异常，进行推送
		if (returnFlag) {
			// 封装推送信息列表
			Map<String, Object> pushMap = new HashMap<String, Object>();
			pushMap.put("id", assessmentId);
			pushMap.put("personId", entity.getPersonId());
			pushMap.put("bloodSuger", entity.getBloodSuger());
			pushMap.put("remarks", entity.getRemarks());
			pushMap.put("exceptionType", BusinessConstants.HEALTH_ASSESSMENT_TYPE_BLOODSUGER);
			pushMap.put("createTime", createTime);
			PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(entity.getPersonId());
			// 获取推送账户
			List<String> accounts = signService.getSignTeamUsers(entity.getPersonId());
			CloudMobilePush push = new CloudMobilePush();
			String title = "血糖异常";
			String content = "您的签约居民"+personInfo.getPersonName()+"出现血糖异常，点击查看！";
			push.androidPush(accounts, title, content, 
					BusinessConstants.PUSH_ACTIVITY, BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_ABNORMAL_BLOODSUGAR, pushMap));
			/**
			 * 插入推送消息表
			 */
			SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_DOC_INT,
					BusinessConstants.PUSH_TYPE_ABNORMAL_BLOODSUGAR,JsonUtils.getJsonFormat(pushMap));
			for (String account : accounts) {
				msg.setDocUserName(account);
				sendMsgService.addMsg(msg);
			}
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	
	@RequestMapping(value = "/getLastAbnormalSignRecord", method = RequestMethod.GET)
	@ApiOperation(value = "获取最近一条体征记录（居民端）", notes = "{'personId':'居民ID'}")
	public String getLastAbnormalSignRecord(@RequestParam String personId) {
		Map<String, Object> record = hyperService.getLastAbnormalSignRecord(personId);
		if(record==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NULL_DATA_CODE.getKey(),
					ApiStatusEnum.NULL_DATA_CODE.getValue()));	
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("personId", personId);
		returnMap.put("exceptionType", record.get("exceptionType"));
		returnMap.put("measuringTime", record.get("measuring_time"));
		String detectionResult = (String) record.get("detection_result");
		System.out.println(JsonUtils.getJson(record));
		if (BusinessConstants.HYPERTENSION_RESULT_LOW.equals(detectionResult)) {
			returnMap.put("result", "1");
		}else if (BusinessConstants.HYPERTENSION_RESULT_NORMAL.equals(detectionResult) || BusinessConstants.HYPERTENSION_RESULT_NORMAL_HIGN.equals(detectionResult)) {
			returnMap.put("result", "2");
		}else {
			returnMap.put("result", "3");
		}
		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHypertensionListByTime", method = RequestMethod.GET)
	@ApiOperation(value = "按时间点查询最近7次血压记录（医生端）", notes = "{'qrtime':'查询时间点 毫秒时间戳','userName':'医生登录账号'，‘personId’：‘居民ID’}")
	public String getHypertensionListByTime(@RequestParam String userName,@RequestParam Long qrtime, @RequestParam String personId ) {
		String productCode = null;
		String regionCode = null;
		String PersonName = null;
		String PersonCode = null;
	    PersonInformationEntity pentity=personService.getPersonInformationByPesronId(personId);
	    DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
	    productCode = docUser.getProductCode();
		if (pentity!=null) {
			//userName为空，为居民端调用
			PersonName=pentity.getPersonName();
			regionCode=pentity.getRegionCode();
			PersonCode=pentity.getPersonCode();
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
	   if(regionCode==null){
			//医生端调用，获取该医生的regionCode
			regionCode=docUser.getRegionCode();
	   
		}
		// 查询本地库，获取血压记录总数
		List<HypertensionRecordEntity> recordList = hyperService.getRecordList(personId);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (HypertensionRecordEntity entity : recordList) {
			Map<String, Object> itemMap = new HashMap<String, Object>();
			//放入传入日期之前的记录
				if(entity.getMeasuringTime().getTime()<=qrtime){
				itemMap.put("timeStamp", entity.getMeasuringTime().getTime());
				itemMap.put("systolicPressure",entity.getSystolicPressure());
				itemMap.put("diastolicPressure", entity.getDiastolicPressure());
				list.add(itemMap);
				}
		}
		
		// 调用接口，获取公卫血压数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ProductCode",productCode);
		//区划 获取
		map.put("RegionCode", regionCode);
		map.put("KeyValueType", 2);
		map.put("KeyValue", PersonCode);
		map.put("StartFollowupDate", "2000-01-01");
		map.put("EndFollowupDate",new SimpleDateFormat("yyyy-MM-dd").format(qrtime));
		map.put("PageSize", 37);	//调取前7条记录
		map.put("PageIndex", 0);
		String returnData = RestfulUtils.getPublicData("58-6", map);
		System.out.println(JsonUtils.getJson(JsonUtils.getObjectJsonMap(returnData)));
		if (JsonUtils.getObjectJsonMap(returnData).get("Total")!= null&&(Integer) JsonUtils.getObjectJsonMap(returnData).get("Total")>0 ) {
			List<Map<String, Object>> hyperList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			for (Map<String, Object> hyperMap : hyperList) {
				if (hyperMap.get("Sbp") != null && hyperMap.get("Dbp") != null) {
					String followUpDateStr = (String)hyperMap.get("FollowUpDateStr");
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap.put("timeStamp", DateUtils.parseDate(followUpDateStr).getTime());
					itemMap.put("systolicPressure",hyperMap.get("Sbp"));
					itemMap.put("diastolicPressure", hyperMap.get("Dbp"));
					list.add(itemMap);
				}
			}
		}
		//把获取到的集合按照时间排序，并取最近7条
		AbnormalSignDateComparator comparator = new AbnormalSignDateComparator();
		Collections.sort(list,comparator);
		if (list.size() > 7) {
			return JsonUtils.getJson(BaseJsonVo.success(list.subList(0, 7)));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(list));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBloodSugerListByTime", method = RequestMethod.GET)
	@ApiOperation(value = "按时间点查询最近7次血糖记录（医生端）", notes = "{'personId':'居民ID','userName':'医生登录账号','qrtime':'查询时间戳 毫秒'}")
	public String getBloodSugerListByTime(@RequestParam String userName, @RequestParam String personId,@RequestParam Long qrtime) {
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
					if(value!=null&&!("".equals(value))){
						productCode =value;
					}
				}
			}else{
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
			}
		}else {
			//医生端调用，获取该医生的productCode
			DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
			productCode = docUser.getProductCode();
		}
		// 查询本地库，获取血糖记录列表
		List<BloodSugerRecordEntity> recordList = bloodSugerService.getRecordList(personId);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (BloodSugerRecordEntity entity : recordList) {
			if(entity.getMeasuringTime().getTime()<=qrtime){
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("timeStamp", entity.getMeasuringTime().getTime());
			itemMap.put("bloodSuger",entity.getBloodSuger());
			list.add(itemMap);
			}
		}
		
		// 调用接口，获取公卫血糖数据
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("ProductCode", PropertiesUtils.getValue("produce_code"));//TODO：未完成注册产品之前先用写死的produceCode
		//DoctorDetailVo doc = docUserService.getDoctorByUsername(userName);
		map.put("ProductCode",productCode);
		
		map.put("PersonID", pEntity.getJwPersonId());
		map.put("StartTime", "2000-01-01");	//调取前100条记录
		map.put("EndTime", new SimpleDateFormat("yyyy-MM-dd").format(qrtime));
		
		String returnData = RestfulUtils.getPublicData("59-6", map);
		
		if (JsonUtils.getObjectJsonMap(returnData).get("Count")!= null && Integer.valueOf((String)JsonUtils.getObjectJsonMap(returnData).get("Count")) > 0) {
			List<Map<String, Object>> rawList = (List<Map<String, Object>>) JsonUtils.getObjectJsonMap(returnData).get("Msg");
			for (Map<String, Object> rawMap : rawList) {
				if (rawMap.get("Data") != null) {
					String DateStr = (String)rawMap.get("Date");
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap.put("timeStamp", DateUtils.parseDate(DateStr).getTime());
					itemMap.put("bloodSuger",rawMap.get("Data"));
					list.add(itemMap);
				}
			}
		}
		//把获取到的集合按照时间排序，并取最近7条
		AbnormalSignDateComparator comparator = new AbnormalSignDateComparator();
		Collections.sort(list,comparator);
		if (list.size() >= 7 ) {
			return JsonUtils.getJson(BaseJsonVo.success(list.subList(0, 7)));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(list));
		}
	}
}
