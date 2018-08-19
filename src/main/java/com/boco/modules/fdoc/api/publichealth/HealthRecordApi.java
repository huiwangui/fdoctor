package com.boco.modules.fdoc.api.publichealth;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.PingYinUtil;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.exception.PersonRecordException;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SysRegionEntity;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.service.SysRegionService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.wordnik.swagger.annotations.ApiOperation;

import java.util.regex.Pattern;

/**
 * 健康档案接口
 * @author lzz
 *
 */
@RestController
@RequestMapping(value = "/publicInfo/healthRecord", produces = "text/json;charset=UTF-8")
public class HealthRecordApi {
	@Resource
	SignService signService;
	@Resource
	DocUserService docUserService;
	@Resource
	PersonInformationService personService;
	@Resource
	SysRegionService regionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHealthRecordByDoctor", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民健康档案列表（医生端）", notes = "{'userName':'登录医生用户名', 'KeyValue' : '关键字内容，身份证号或姓名或健康卡号'}")
	public String getHealthRecordByDoctor(@RequestParam String userName,
			@RequestParam Integer pageSize, @RequestParam Integer pageNo,
			@RequestParam(value="keyValue",required=false) String keyValue){
		
		// 医生端调用，获取该医生的productCode
		DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
		if (docUser == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(
			ApiStatusEnum.NO_SUCH_DOCTOR.getKey(),
			ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
			}
		String productCode = docUser.getProductCode();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		if(keyValue!=null){
			Pattern pattern=Pattern.compile("[0-9]+.*");
			if(pattern.matcher(keyValue).matches()){
				if(MatcherUtiles.idCardMach(keyValue)){
					paramMap.put("KeyCode", "2");
				}else{
					paramMap.put("KeyCode", "3");
				}
			}else{
				paramMap.put("KeyCode", "1");
			}
		paramMap.put("KeyValue", keyValue);
		}
		paramMap.put("PageIndex", pageNo-1);
		paramMap.put("PageSize", pageSize);
		paramMap.put("ProductCode", productCode);
		paramMap.put("RegionCode", docUser.getRegionCode());
		String returnData=RestfulUtils.getPublicData("55-12", paramMap);
		//解析返回JSON
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(returnData);
		if ((Integer) jsonMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
		} else {
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			Integer count = (Integer) jsonMap.get("Total");
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonMap.get("Msg");
			for (Map<String, Object> map : dataList) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("NAME", map.get("NAME"));
				returnMap.put("GENDER", map.get("GENDER"));		 
				returnMap.put("AGE", map.get("AGE"));
				returnMap.put("TELPHONE", map.get("TELPHONE"));
				returnMap.put("ID", map.get("ID"));
				returnMap.put("LASTTIME", map.get("LASTTIME"));
				returnMap.put("CARDID", map.get("CARD_ID"));
				
				returnList.add(returnMap);
			}
			return JsonUtils.getPageJsonWithTotal(count, pageSize, returnList);
			}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHealthRecordById", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民个人健康档案（医生端）", notes = "{'userName':'登录医生用户名', 'ID':'档案号'}")
	public String getHealthRecordById(@RequestParam String userName,@RequestParam String ID){
		DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
		if (docUser == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(
			ApiStatusEnum.NO_SUCH_DOCTOR.getKey(),
			ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
			}
		String productCode = docUser.getProductCode();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("ProductCode", productCode);
		paramMap.put("ID", ID);
		String returnData=RestfulUtils.getPublicData("55-10", paramMap);
		//解析返回JSON
		Map<String, Object> returnMap = JsonUtils.getObjectJsonMap(returnData);
		Map<String, Object> jsonMap=(Map<String, Object>) returnMap.get("Msg");
		return JsonUtils.getJson(BaseJsonVo.success(jsonMap));
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveHealthRecord", method = RequestMethod.POST)
	@ApiOperation(value = "保存居民健康档案（医生端）")
	public String saveHealthRecord(@RequestBody String paramBody){
		//准备调用卫计委接口参数map
		Map<String, Object> itemMap = new HashMap<String, Object>(); 
		
		//准备本地持久化对象
		PersonInformationEntity personEntity = new PersonInformationEntity();
		
		//解析参数json
		Map<String, Object> dataMap = JsonUtils.getObjectJsonMap(paramBody);
        
        String userName = (String) dataMap.get("userName");
        //医生端调用，获取该医生的productCode
        DoctorDetailVo docDetail = docUserService.getDoctorByUsername(userName);
        if (docDetail == null) {
        	return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
        String productCode = docDetail.getProductCode();
        itemMap.put("ProductCode", productCode);
        
        //----------------------------封装Person(居民基本档案信息)部分--------------------------------
        Map<String, Object> personItemMap = new HashMap<String, Object>();	//准备person部分map
        Map<String, Object> personParamMap = (Map<String, Object>) dataMap.get("person");
        
        //处理含ID的字段
        personItemMap.put("CardID", personParamMap.get("idCard"));
        personParamMap.remove("idCard");
        
        personItemMap.put("BuildOrgID", docDetail.getOrgId());
        personItemMap.put("BuildEmployeeID", docDetail.getId());
        personItemMap.put("PUserID", docDetail.getId());
        personItemMap.put("ResponsibilityID", docDetail.getId());
        
        personItemMap.put("FamilyID", personParamMap.get("familyId"));
        personParamMap.remove("familyId");
        
        SysRegionEntity region = regionService.getRegionByCode((String)personParamMap.get("regionCode"));
        if (region == null) {
        	return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.UNKNOWREGION.getKey(), ApiStatusEnum.UNKNOWREGION.getValue()));
		}
        personItemMap.put("RegionID", region.getId());
        personParamMap.remove("regionId");
        
        //处理默认日期
        String nowDateStr = DateUtils.formatDate(new Date());
        personItemMap.put("InFamilyDate", nowDateStr);
        personItemMap.put("InOrgDate", nowDateStr);
        personItemMap.put("HrStatusDate", nowDateStr);
        personItemMap.put("BuildDate", nowDateStr);
        personItemMap.put("Updatedate", nowDateStr);
        personItemMap.put("Lastsynctime", nowDateStr);
        
        //处理名字相关
        personItemMap.put("Name", personParamMap.get("personName"));
        personItemMap.put("NamePinyin", PingYinUtil.cn2py((String)personParamMap.get("personName")));
        
        //处理默认医生信息
        personItemMap.put("ResponsibilityDoctor", docDetail.getDocName());
        personItemMap.put("BuildEmployeeName", docDetail.getDocName());
        
        //批处理：首字母换为大写
        for (Map.Entry<String, Object> entry : personParamMap.entrySet()) {
			// 把key首字母改为大写
        	personItemMap.put(StringUtils.captureUpName(entry.getKey()),
					entry.getValue());
		}
        
        //设置id、personCode
        String itemId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        
        personItemMap.put("ID", itemId);
        itemMap.put("Person", personItemMap);
        
        //设置本地化持久对象属性
        //personEntity.setPersonId(itemId);
        personEntity.setJwPersonId(itemId);
        personEntity.setFamilyId((String)personItemMap.get("FamilyID"));
        personEntity.setCustomNumber((String)personParamMap.get("customNumber"));
        personEntity.setPersonName(((String)personItemMap.get("Name")));
        personEntity.setSex(((String)personParamMap.get("genderCode")));
        personEntity.setDateOfBirth(DateUtils.parseDate(((String)personParamMap.get("birthDay"))));
        personEntity.setIdCard(((String)personItemMap.get("CardID")));
        personEntity.setPhoneNumber(((String)personParamMap.get("personTel")));
        personEntity.setCurrentAddress(((String)personParamMap.get("currentAddress")));
        //判断户主
        if (BusinessConstants.YES.equals((String)personParamMap.get("householderRelationship"))) {
        	personEntity.setMasterFlag(BusinessConstants.YES);
		}
        personEntity.setUnit(((String)personParamMap.get("unit")));
        personEntity.setCreator(userName);
        
        
        //----------------------------封装FamilyHistoryList(家庭既往史)部分--------------------------------
        List<Map<String, Object>> familyHistoryItemList = new ArrayList<Map<String,Object>>();	//准备目标list
        List<Map<String, Object>> familyHistoryParamList = (List<Map<String, Object>>) dataMap.get("familyHistoryList");
        
        for (Map<String, Object> familyHistoryParamMap : familyHistoryParamList) {
        	Map<String, Object> familyHistoryItemMap = new HashMap<String, Object>();
        	//批处理：首字母换为大写
            for (Map.Entry<String, Object> entry : familyHistoryParamMap.entrySet()) {
    			// 把key首字母改为大写
            	familyHistoryItemMap.put(StringUtils.captureUpName(entry.getKey()),
    					entry.getValue());
    		}
            familyHistoryItemList.add(familyHistoryItemMap);
		}
        itemMap.put("FamilyHistoryList", familyHistoryItemList);
        
        //----------------------------封装PersonHistoryList(个人既往史)部分--------------------------------
        List<Map<String, Object>> personHistoryItemList = new ArrayList<Map<String,Object>>();	//准备目标list
        List<Map<String, Object>> personHistoryParamList = (List<Map<String, Object>>) dataMap.get("personHistoryList");
        
        for (Map<String, Object> personHistoryParamMap : personHistoryParamList) {
        	Map<String, Object> personHistoryItemMap = new HashMap<String, Object>();
        	//批处理：首字母换为大写
            for (Map.Entry<String, Object> entry : personHistoryParamMap.entrySet()) {
    			// 把key首字母改为大写
            	personHistoryItemMap.put(StringUtils.captureUpName(entry.getKey()),
    					entry.getValue());
    		}
            personHistoryItemList.add(personHistoryItemMap);
		}
        itemMap.put("PersonHistoryList", personHistoryItemList);
        
        //----------------------------封装CmDataList(个人疾病既往史)部分--------------------------------
        List<Map<String, Object>> cmDataItemList = new ArrayList<Map<String,Object>>();	//准备目标list
        List<Map<String, Object>> cmDataParamList = (List<Map<String, Object>>) dataMap.get("cmDataList");
        
        for (Map<String, Object> cmDataParamMap : cmDataParamList) {
        	Map<String, Object> cmDataItemMap = new HashMap<String, Object>();
        	
        	//处理特殊字段
        	cmDataItemMap.put("RecordDate", nowDateStr);	//默认时间字段
        	cmDataItemMap.put("DiseaseKindID", cmDataParamMap.get("diseaseKindId"));	//疾病ID
        	cmDataParamMap.remove("diseaseKindId");
        	cmDataItemMap.put("DoctorID", docDetail.getId());	//医生ID
        	cmDataItemMap.put("DoctorName", docDetail.getNickName());	//医生姓名
        	cmDataItemMap.put("UserID", docDetail.getId());	//记录人ID
        	cmDataItemMap.put("UserName", docDetail.getNickName());	//记录人姓名
        	cmDataItemMap.put("OrgID", docDetail.getOrgId());	//机构ID
        	
        	//批处理：首字母换为大写
            for (Map.Entry<String, Object> entry : cmDataParamMap.entrySet()) {
    			// 把key首字母改为大写
            	cmDataItemMap.put(StringUtils.captureUpName(entry.getKey()),
    					entry.getValue());
    		}
            cmDataItemList.add(cmDataItemMap);
		}
        itemMap.put("CmDataList", cmDataItemList);
        
        //执行新增操作
		try {
			personService.addPerson(personEntity, itemMap);
		} catch (PersonRecordException e) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), e.getMessage()));
		}
		
		return JsonUtils.getJson(BaseJsonVo.success(personEntity.getPersonId()));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateHealthRecord", method = RequestMethod.POST)
	@ApiOperation(value = "修改居民健康档案（医生端）")
	public String updateHealthRecord(@RequestBody String paramBody){
		Map<String, Object> dataMap = JsonUtils.getObjectJsonMap(paramBody); // 原始数据map
        Map<String, Object> itemMap = new HashMap<String, Object>(); // 调用卫计委接口需要的数据map
        String userName = (String) dataMap.get("userName");
        // 医生端调用，获取该医生的productCode
        DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
        String productCode = docUser.getProductCode();
        itemMap.put("ProductCode", productCode);
        
        if(dataMap.get("Person")!=null && !"".equals(dataMap.get("Person"))){
        	Map<String, Object> personMap=(Map<String, Object>) dataMap.get("Person");
        	itemMap.put("Person", personMap);
        }else{
        	itemMap.put("Person", new HashMap<String,Object>());
        }
        if (dataMap.get("FamilyHistoryList")!=null && !"".equals(dataMap.get("FamilyHistoryList"))) {
			List<Map<String, Object>> fList=(List<Map<String, Object>>) dataMap.get("FamilyHistoryList");
			itemMap.put("FamilyHistoryList", fList);
		}else{
			List<Map<String, Object>> fList=new ArrayList<Map<String,Object>>();
        	itemMap.put("FamilyHistoryList", fList);
        }
        if (dataMap.get("HealthHistory")!=null && !"".equals(dataMap.get("HealthHistory"))) {
			List<Map<String, Object>> pList=(List<Map<String, Object>>) dataMap.get("HealthHistory");
			itemMap.put("PersonHistoryList", pList);
		}else{
			List<Map<String, Object>> pList=new ArrayList<Map<String,Object>>();
        	itemMap.put("PersonHistoryList", pList);
        }
        if (dataMap.get("CmDataList")!=null && !"".equals(dataMap.get("CmDataList"))) {
			List<Map<String, Object>> cList=(List<Map<String, Object>>) dataMap.get("CmDataList");
			itemMap.put("CmDataList", cList);
		}else{
			List<Map<String, Object>> cList=new ArrayList<Map<String,Object>>();
        	itemMap.put("CmDataList", cList);
        }
        
        String returnData=RestfulUtils.getPublicData("55-15", itemMap);
        Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(returnData);
        
        if ((Integer) jsonMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
		}else{
			return JsonUtils.getJson(BaseJsonVo.success(jsonMap));
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addFamilyRecord", method = RequestMethod.POST)
	@ApiOperation(value = "新增家庭档案（医生端）")
	public String addFamilyRecord(@RequestBody String paramBody){
		//准备参数map，以及famlilyList，由于业务需要，familyList每次只装一个
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, Object>> familyList = new ArrayList<>();
		
		//声明每一个famliyList中的map
		Map<String, Object> itemMap = new HashMap<String, Object>();
		
		//json字符串转化为map
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(paramBody);
		
		String userName = (String) jsonMap.get("userName");	//获取用户名
		jsonMap.remove("userName");
		// 医生端调用，获取该医生的productCode
        DoctorDetailVo docDetail = docUserService.getDoctorByUsername(userName);
        if (docDetail == null) {
        	return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
        String productCode = docDetail.getProductCode();
        paramMap.put("ProductCode", productCode);
        
        //封装建档日期
        itemMap.put("BuildDate", DateUtils.formatDate(new Date()));
        itemMap.put("Lastsynctime", DateUtils.formatDate(new Date()));
        
        //处理接口中带ID的字段
        itemMap.put("BuildOrgID", docDetail.getOrgId());
        itemMap.put("BuildEmployeeID", docDetail.getId());
        
        itemMap.put("RegionID", jsonMap.get("regionId"));
        jsonMap.remove("regionId");
        
        itemMap.put("MasterCardID", jsonMap.get("masterIdCard"));
        jsonMap.remove("masterIdCard");
        
        //处理剩下的：改变首字母为大写
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
			// 把key首字母改为大写
        	itemMap.put(StringUtils.captureUpName(entry.getKey()),
					entry.getValue());
		}
        
        familyList.add(itemMap);
        paramMap.put("FamilyList", familyList);
        
        String returnData = RestfulUtils.getPublicData("54-1", paramMap);
        Map<String, Object> returnMap = JsonUtils.getObjectJsonMap(returnData);
        
        if ((Integer) returnMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) returnMap.get("Msg")));
		}else{
			//保存成功，返回familyId
			Map<String, Object> dataMap = (Map<String, Object>) returnMap.get("Msg");
			return JsonUtils.getJson(BaseJsonVo.success(dataMap.get("ID")));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getFamilyRecord", method = RequestMethod.GET)
	@ApiOperation(value = "查看家庭档案（医生端）", notes = "{'userName' : '医生用户名', 'familyId' : '家庭ID'}")
	public String deletePersonRecord(@RequestParam String userName, @RequestParam String familyId){
		//医生端调用，获取该医生的productCode
        DoctorDetailVo docDetail = docUserService.getDoctorByUsername(userName);
        if (docDetail == null) {
        	return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
        //封装调用接口参数map
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ProductCode", docDetail.getProductCode());
        paramMap.put("FamilyID", familyId);
        
        String returnData = RestfulUtils.getPublicData("54-4", paramMap);
        Map<String, Object> returnMap = JsonUtils.getObjectJsonMap(returnData);
        
        if ((Integer) returnMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) returnMap.get("Msg")));
		}
        
        //解析返回json，封装数据返回
        Map<String, Object> dataMap = (Map<String, Object>) returnMap.get("Msg");
        Map<String, Object> itemMap = new HashMap<String, Object>();
        
        //处理ID相关字段
        itemMap.put("familyId", dataMap.get("ID"));	//家庭ID
        dataMap.remove("ID");
        itemMap.put("buildOrgId", dataMap.get("BuildOrgID"));	//创建机构ID
        dataMap.remove("BuildOrgID");
        itemMap.put("creatorId", dataMap.get("BuildEmployeeID"));	//创建者ID
        dataMap.remove("BuildEmployeeID");
        
        SysRegionEntity region = regionService.getRegionById((String)dataMap.get("RegionID"));
        if (region != null) {
        	itemMap.put("regionCode", region.getRegionCode());	//区划code
		}
        itemMap.put("regionId", dataMap.get("RegionID"));	//区划ID
        dataMap.remove("RegionID");
        
        //处理时间相关字段
        if (dataMap.get("BuildDate") != null) {
        	String dateNum = StringUtils.getStringNum((String)dataMap.get("BuildDate"));
        	Long timeStamp = Long.parseLong(dateNum);
    		itemMap.put("buildDate", DateUtils.formatDate(new Date(timeStamp)));
    		dataMap.remove("BuildDate");
		}
		//处理其他字段
		itemMap.put("masterIdCard", dataMap.get("MasterCardID"));	//户主ID
		
		//批处理：首字母小写
		for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
			// 把key首字母改为大写
        	itemMap.put(StringUtils.captureName(entry.getKey()),
					entry.getValue());
		}
		
        return JsonUtils.getJson(BaseJsonVo.success(itemMap));
	}
	
	@RequestMapping(value = "/updateFamilyRecord", method = RequestMethod.POST)
	@ApiOperation(value = "修改家庭档案（医生端）")
	public String updateFamilyRecord(@RequestBody String paramBody){
		//准备参数map，以及famlilyList，由于业务需要，familyList每次只装一个
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, Object>> familyList = new ArrayList<>();
		
		//声明每一个famliyList中的map
		Map<String, Object> itemMap = new HashMap<String, Object>();
		
		//json字符串转化为map
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(paramBody);
		
		String userName = (String) jsonMap.get("userName");	//获取用户名
		jsonMap.remove("userName");
		// 医生端调用，获取该医生的productCode
        DoctorDetailVo docDetail = docUserService.getDoctorByUsername(userName);
        if (docDetail == null) {
        	return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
        String productCode = docDetail.getProductCode();
        paramMap.put("ProductCode", productCode);
        
        //封装建档日期
        itemMap.put("BuildDate", jsonMap.get("buildDate"));
        jsonMap.remove("buildDate");
        itemMap.put("Lastsynctime", DateUtils.formatDate(new Date()));
        
        //处理接口中带ID的字段
        itemMap.put("BuildOrgID", jsonMap.get("buildOrgId"));
        jsonMap.remove("buildOrgId");
        itemMap.put("BuildEmployeeID", jsonMap.get("creatorId"));
        jsonMap.remove("creatorId");
        
        itemMap.put("ID", jsonMap.get("familyId"));
        jsonMap.remove("familyId");
        
        itemMap.put("RegionID", jsonMap.get("regionId"));
        jsonMap.remove("regionId");
        
        itemMap.put("MasterCardID", jsonMap.get("masterIdCard"));
        jsonMap.remove("masterIdCard");
        
        //处理剩下的：改变首字母为大写
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
			// 把key首字母改为大写
        	itemMap.put(StringUtils.captureUpName(entry.getKey()),
					entry.getValue());
		}
        
        familyList.add(itemMap);
        paramMap.put("FamilyList", familyList);
        String returnData = RestfulUtils.getPublicData("54-2", paramMap);
        Map<String, Object> returnMap = JsonUtils.getObjectJsonMap(returnData);
        if ((Integer) returnMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) returnMap.get("Msg")));
		}else{
			//修改家庭成功，将户主进行 更换
			String familyId=(String) jsonMap.get("familyId");
			String masterIdCard=(String) jsonMap.get("masterIdCard");
			if(masterIdCard!=null){
				personService.resetMaster(familyId);
				personService.setMaster(masterIdCard);
			}
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	
	@RequestMapping(value = "/deletePersonRecord", method = RequestMethod.POST)
	@ApiOperation(value = "删除居民个人档案（医生端）", notes = "{'userName' : '医生用户名', 'personId' : '居民ID'}")
	public String deletePersonRecord(@RequestBody String paramBody){
		//json字符串转化为map
		Map<String, String> jsonMap = JsonUtils.getSingleJsonMap(paramBody);
		String userName = jsonMap.get("userName");
		// 医生端调用，获取该医生的productCode
        DoctorDetailVo docDetail = docUserService.getDoctorByUsername(userName);
        if (docDetail == null) {
        	return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
        //验证删除权限
        String personId = jsonMap.get("personId");
        PersonInformationVo personInfo = personService.getPersonDetailByPersonId(personId);
        
        if (!userName.equals(personInfo.getCreator())) {
        	return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_JURISDICTION.getKey(), ApiStatusEnum.NO_JURISDICTION.getValue()));
		}
        
        //执行删除操作
		try {
			personService.deletePerson(personId, docDetail.getProductCode());
		} catch (PersonRecordException e) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), e.getMessage()));
		}
        
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getPersonRecordListInFamily", method = RequestMethod.GET)
	@ApiOperation(value = "通过家庭ID获取公卫居民列表（医生端）", notes = "{'userName' : '医生用户名', 'familyId' : '家庭ID'}")
	public String getPersonRecordListInFamily(@RequestParam String userName, @RequestParam String familyId){
		//医生端调用，获取该医生的productCode
        DoctorDetailVo docDetail = docUserService.getDoctorByUsername(userName);
        if (docDetail == null) {
        	return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
        //封装调用接口参数map
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ProductCode", docDetail.getProductCode());
        paramMap.put("FamilyId", familyId);
        
        String returnData = RestfulUtils.getPublicData("54-3", paramMap);
        Map<String, Object> resultMap = JsonUtils.getObjectJsonMap(returnData);
        
        if ((Integer) resultMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) resultMap.get("Msg")));
		}
        
        //解析返回json，封装数据返回
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) resultMap.get("Msg");
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> dataMap : dataList) {
			Map<String, Object> itemMap = new HashMap<String, Object>();
			
			itemMap.put("personId", dataMap.get("ID"));
			itemMap.put("personCode", dataMap.get("PersonCode"));
			itemMap.put("personName", dataMap.get("Name"));
			itemMap.put("idCard", dataMap.get("CardID"));
			itemMap.put("sex", dataMap.get("GenderCode"));
			itemMap.put("phoneNumber", dataMap.get("Telphone"));
			itemMap.put("age", dataMap.get("Age"));
			itemMap.put("currentAddress", dataMap.get("CurrentAddress"));
			
			returnList.add(itemMap);
		}
		
        return JsonUtils.getJson(BaseJsonVo.success(returnList));
	}
}

