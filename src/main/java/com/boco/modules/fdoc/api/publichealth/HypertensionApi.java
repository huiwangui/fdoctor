package com.boco.modules.fdoc.api.publichealth;

import java.util.ArrayList;
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

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/publicInfo/hypertension", produces = "text/json;charset=UTF-8")
public class HypertensionApi {
	@Resource
	SignService signService;
	@Resource
	DocUserService docUserService;
	@Resource
	PersonInformationService personService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHyperensionArchivesByDoctor", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民高血压随访列表（医生端）", notes = "{'userName':'登录医生用户名','followupType':'随访方式(1-门诊 2-家庭 4-电话 -1-全部)', 'KeyValue' : '关键字内容，身份证号或姓名'")
	public String getHyperensionArchivesByDoctor(@RequestParam String userName,@RequestParam String followupType,
			@RequestParam Integer pageSize, @RequestParam Integer pageNo,
			@RequestParam(value="keyValue",required=false) String keyValue ) {
		// 医生端调用，获取该医生的productCode
		DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
		if (docUser == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.NO_SUCH_DOCTOR.getKey(),
					ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
		String productCode = docUser.getProductCode();
		// 封装查询参数，调用卫计委接口
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", productCode);
		if(keyValue!=null){
			if (MatcherUtiles.idCardMach(keyValue)) {
				paramMap.put("KeyValueType", "2"); // 符合身份证号格式，当做身份证处理
			} else {
				paramMap.put("KeyValueType", "1"); // 不符合身份证格式，当做姓名处理
			}
		}
		
		paramMap.put("FollowupType", followupType);
		//paramMap.put("Perfect", perfect);
		paramMap.put("KeyValue", keyValue);
		paramMap.put("PageIndex", pageNo-1);
		paramMap.put("PageSize", pageSize);
		 
		paramMap.put("RegionCode", docUser.getRegionCode());
		String returnData = RestfulUtils.getPublicData("58-6", paramMap);
		// 解析Json，封装返回参数
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(returnData);
		System.out.println("列表:"+jsonMap);
		if ((Integer) jsonMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
		} else {
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			Integer count = (Integer) jsonMap.get("Total");
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonMap.get("Msg");
			System.out.println(dataList);
			for (Map<String, Object> map : dataList) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("hypertensionId", map.get("ID"));
				returnMap.put("personId", map.get("PersonID"));		 
				returnMap.put("personName", map.get("PersonName"));
				returnMap.put("sbp", map.get("Sbp"));
				returnMap.put("dbp", map.get("Dbp"));
				returnMap.put("followUpDate", map.get("FollowUpDateStr"));
				returnList.add(returnMap);
			}
			return JsonUtils.getPageJsonWithTotal(count, pageSize, returnList);
		}
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveHypertension", method = RequestMethod.POST)
	@ApiOperation(value = "保存居民高血压记录（医生端）")
	public String saveHypertension(@RequestBody String paramBody) {
		 Map<String, Object> dataMap = JsonUtils.getObjectJsonMap(paramBody); // 原始数据map
         Map<String, Object> itemMap = new HashMap<String, Object>(); // 调用卫计委接口需要的数据map
         String userName = (String) dataMap.get("userName");
         // 医生端调用，获取该医生的productCode
         DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
         String productCode = docUser.getProductCode();
         itemMap.put("ProductCode", productCode);
         // 获取ID，若为新增，则ID为空
        /* if (dataMap.get("diaId") != null && StringUtils.isNotEmpty((String) dataMap.get("diaId"))) {
            itemMap.put("ID", dataMap.get("diaId"));
         }*/
	        /**
	         * 解析高血压数据，封装为卫计委需要的数据
	         */
	        // 准备other部分map
	        List<Map<String, Object>> itemOtherList = new ArrayList<Map<String, Object>>();
	        Map<String, Object> itemOtherMap = new HashMap<String, Object>();

	        // 封装body部分
	        if (dataMap.get("body") != null) {
	            Map<String, Object> bodyMap = (Map<String, Object>) dataMap.get("body");
	            Map<String, Object> itemBodyMap = new HashMap<String, Object>();
	            for (Map.Entry<String, Object> entry : bodyMap.entrySet()) {
	                // 把key首字母改为大写
	                itemBodyMap.put(StringUtils.captureUpName(entry.getKey()),entry.getValue());
	            }
	            itemMap.put("Body", itemBodyMap);
	        }

	        // 封装Labor部分
	        if (dataMap.get("labora") != null) {
	            Map<String, Object> laborMap = (Map<String, Object>) dataMap.get("labora");
	            Map<String, Object> itemLaborScoreMap = new HashMap<String, Object>();
	            if(StringUtils.isNotEmpty((String)laborMap.get("id"))){
	                itemLaborScoreMap.put("ID",laborMap.get("id"));
	                laborMap.remove("id");
	            }
	            // 处理用|隔开的部分
	            if (laborMap.get("ecg") != null ) {
	            	if("".equals(laborMap.get("ecg"))){
	            		laborMap.put("ecg", null);
	            		laborMap.remove("ecgExplain");
	            	}else{
	            		laborMap.put(
		                        "ecg",
		                        laborMap.get("ecg")
		                                + "\u0001"
		                                + (laborMap.get("ecgExplain") != null ? laborMap
		                                .get("ecgExplain") : ""));
		                laborMap.remove("ecgExplain"); // 心电图
	            	}
	                
	            }
	            if (laborMap.get("chestXRay") != null ) {
	            	if("".equals(laborMap.get("chestXRay"))){
	            		laborMap.put("chestXRay", null);
	            		laborMap.remove("chestXRayExplain");
	            	}else{
	            		laborMap.put(
		                        "chestXRay",
		                        laborMap.get("chestXRay")
		                                + "\u0001"
		                                + (laborMap.get("chestXRayExplain") != null ? laborMap
		                                .get("chestXRayExplain") : ""));
		                laborMap.remove("chestXRayExplain"); // X光
	            	}
	                
	            }
	            if (laborMap.get("bUltrasonicWave") != null ) {
	            	if("".equals(laborMap.get("bUltrasonicWave"))){
	            		laborMap.put("bUltrasonicWave",null);
	            		laborMap.remove("bUltrasonicWaveExplain");
	            	}else{
	            		laborMap.put(
		                        "bUltrasonicWave",
		                        laborMap.get("bUltrasonicWave")
		                                + "\u0001"
		                                + (laborMap.get("bUltrasonicWaveExplain") != null ? laborMap
		                                .get("bUltrasonicWaveExplain") : ""));
		                laborMap.remove("bUltrasonicWaveExplain"); // B超
	            	}
	                
	            }
	            if (laborMap.get("cervicalSmear") != null) {
	            	if("".equals(laborMap.get("cervicalSmear"))){
	            		laborMap.put("cervicalSmear",null);
	            		laborMap.remove("cervicalSmearExplain");
	            	}else{
	            		laborMap.put(
		                        "cervicalSmear",
		                        laborMap.get("cervicalSmear")
		                                + "\u0001"
		                                + (laborMap.get("cervicalSmearExplain") != null ? laborMap
		                                .get("cervicalSmearExplain") : ""));
		                laborMap.remove("cervicalSmearExplain"); // 宫颈涂片
	            	}
	            }

	            for (Map.Entry<String, Object> entry : laborMap.entrySet()) {
	                // 把key首字母改为大写
	                itemLaborScoreMap.put(
	                        StringUtils.captureUpName(entry.getKey()),
	                        entry.getValue());
	            }
	            itemMap.put("Labora", itemLaborScoreMap);
	        }


	        // 封装Organ部分
	        if (dataMap.get("organ") != null) {
	            Map<String, Object> organMap = (Map<String, Object>) dataMap
	                    .get("organ");
	            Map<String, Object> itemOrganMap = new HashMap<String, Object>();
	            for (Map.Entry<String, Object> entry : organMap.entrySet()) {
	                // 把key首字母改为大写
	                itemOrganMap.put(StringUtils.captureUpName(entry.getKey()),
	                        entry.getValue());
	            }
	            itemMap.put("Organ", itemOrganMap);
	        }

	        // 封装lifestyle部分
	        if (dataMap.get("lifeStyle") != null) {
	            Map<String, Object> lifestyleMap = (Map<String, Object>) dataMap
	                    .get("lifeStyle");
	            Map<String, Object> itemLifestyleMap = new HashMap<String, Object>();
	            if(StringUtils.isNotEmpty((String)lifestyleMap.get("id"))){
	                itemLifestyleMap.put("ID",lifestyleMap.get("id"));
	                lifestyleMap.remove("id");
	            }
	            for (Map.Entry<String, Object> entry : lifestyleMap.entrySet()) {
	                // 把key首字母改为大写
	                itemLifestyleMap.put(StringUtils.captureUpName(entry.getKey()),
	                        entry.getValue());
	            }
	            itemMap.put("Lifestyle", itemLifestyleMap);
	        }

	        // 封装drug部分
	        if (dataMap.get("drug") != null && dataMap.get("drug") instanceof List) {
	            List<Map<String, Object>> drugList = (List<Map<String, Object>>) dataMap
	                    .get("drug");
	            List<Map<String, Object>> itemDrugList = new ArrayList<Map<String, Object>>();
	            for (Map<String, Object> map : drugList) {
	                Map<String, Object> itemDrugMap = new HashMap<String, Object>();

	                if(StringUtils.isNotEmpty((String)map.get("id"))){
	                    itemDrugMap.put("ID",map.get("id"));
	                    map.remove("id");
	                }
	                for (Map.Entry<String, Object> entry : map.entrySet()) {
	                    // 把key首字母改为大写
	                    itemDrugMap.put(StringUtils.captureUpName(entry.getKey()),
	                            entry.getValue());
	                }
	                itemDrugList.add(itemDrugMap);
	            }
	            itemMap.put("Drug", itemDrugList);
	        }
	        // 封装transout部分
	        if (dataMap.get("transout") != null) {
	            Map<String, Object> transoutMap = (Map<String, Object>) dataMap
	                    .get("transout");
	            Map<String, Object> itemtransoutMap = new HashMap<String, Object>();
	            for (Map.Entry<String, Object> entry : transoutMap.entrySet()) {
	                // 把key首字母改为大写
	                itemtransoutMap.put(StringUtils.captureUpName(entry.getKey()),
	                        entry.getValue());
	            }
	            itemMap.put("transout", itemtransoutMap);
	        }

	        // 封装cmDiabetes部分
	        if (dataMap.get("cmHyper") != null) {
	            Map<String, Object> cmDiabetesMap = (Map<String, Object>) dataMap
	                    .get("cmHyper");

	            Map<String, Object> itemCmDiabetesMap = new HashMap<String, Object>();
	            if(StringUtils.isNotEmpty((String)cmDiabetesMap.get("id"))){
	                itemCmDiabetesMap.put("ID",cmDiabetesMap.get("id"));
	                cmDiabetesMap.remove("id");
	            }

	            itemCmDiabetesMap.put("PersonID",cmDiabetesMap.get("personId"));

	            // 处理放在other中的部分 AdverseDrugReactions
	            itemOtherMap.put("AdverseDrugReactions", cmDiabetesMap.get("adverseDrugReactionsOther")); 
	            itemOtherMap.put("Symptom", cmDiabetesMap.get("symptomOther"));// 症状其他

	            for (Map.Entry<String, Object> entry : cmDiabetesMap.entrySet()) {
	                // 把key首字母改为大写
	                itemCmDiabetesMap.put(StringUtils.captureUpName(entry.getKey()),
	                        entry.getValue());
	            }
	            itemCmDiabetesMap.put("UserID", itemCmDiabetesMap.get("DocUserId"));
	            itemCmDiabetesMap.remove("DocUserId"); // 处理userId
	            itemCmDiabetesMap.put("DoctorID", itemCmDiabetesMap.get("DoctorId"));
	            itemCmDiabetesMap.remove("DoctorId"); // 处理doctorId


	            itemMap.put("CmHyper", itemCmDiabetesMap);
	        }


	        // 其他参数

	        itemMap.put("OrgID", docUser.getOrgId());

	        for (Map.Entry<String, Object> entry : itemOtherMap.entrySet()) {
	            Map<String, Object> otherItem = new HashMap<String, Object>();
	            otherItem.put("AttrName", entry.getKey());
	            otherItem.put("OtherText", entry.getValue());
	            itemOtherList.add(otherItem);
	        }
	        itemMap.put("Other", itemOtherList);
	        System.out.println("+++++++++++++++++++"+itemMap);
	        // 封装查询参数，调用卫计委接口
	        String returnMsg = RestfulUtils.getPublicData("58-2", itemMap);
	        // 解析Json，封装返回参数
	        Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(returnMsg);
	        if ((Integer) jsonMap.get("Result") == 0) {
	            // 返回result为0，提示内容
	            return JsonUtils.getJson(BaseJsonVo.empty(
	                    ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
	        } else {
	            return JsonUtils.getJson(BaseJsonVo.success(null));
	        }

	
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHypertensionDetailByDoctor", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民高血压详情（医生端）", notes = "{'userName':'登录医生用户名', 'hypertensionId' : '体检ID'}")
	public String getHypertensionDetailByDoctor(@RequestParam String userName,
			@RequestParam String hypertensionId) {
		 // 医生端调用，获取该医生的productCode
        DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
        if (docUser == null) {
            return JsonUtils.getJson(BaseJsonVo.empty(
                    ApiStatusEnum.NO_SUCH_DOCTOR.getKey(),
                    ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
        }
        String productCode = docUser.getProductCode();
        // 封装查询参数，调用卫计委接口
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ProductCode", productCode);
        paramMap.put("ID", hypertensionId);
        String examListData = RestfulUtils.getPublicData("58-3", paramMap);
        System.out.println(examListData);
        // 解析Json，封装返回参数
        Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(examListData.trim());
        
       
        if ((Integer) jsonMap.get("Result") == 0) {
            // 返回result为0，提示内容
            return JsonUtils.getJson(BaseJsonVo.empty(
                    ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
        }
        Map<String, Object> dataMap = (Map<String, Object>) jsonMap.get("Msg");
        System.out.println("58-3++"+dataMap);
        Map<String, Object> returnMap = new HashMap<String, Object>();

        //处理body中信息
        if (dataMap.get("examBody") != null) {
            Map<String, Object> bodyMap = (Map<String, Object>) dataMap.get("examBody");
            Map<String, Object> itemBodyMap = new HashMap<String, Object>();
            bodyMap.remove("ExamDate");
            //处理体检日期
            if (bodyMap.get("ExamDate") != null && StringUtils.isNotEmpty((String)bodyMap.get("ExamDate"))) {
                String dateNum = StringUtils.getStringNum((String)bodyMap.get("ExamDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	itemBodyMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            for (Map.Entry<String, Object> entry : bodyMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		 itemBodyMap.put(StringUtils.captureName(entry.getKey()),
            				 entry.getValue());
            	}else{
            		String value=(String)entry.getValue();
                	if(value==null||"".equals(value.trim())){
                		value=null;
                	}
                    itemBodyMap.put(StringUtils.captureName(entry.getKey()),
                            value);
            	}
            	
            }
            returnMap.put("body", itemBodyMap);
        }

        //处理organ中信息
        if (dataMap.get("examOrgan") != null) {
            Map<String, Object> organMap = (Map<String, Object>) dataMap.get("examOrgan");
            Map<String, Object> itemOrganMap = new HashMap<String, Object>();
            organMap.remove("ExamDate");
            for (Map.Entry<String, Object> entry : organMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		 itemOrganMap.put(StringUtils.captureName(entry.getKey()),
            				 entry.getValue());	
            	}else{
            		String value=(String)entry.getValue();
                	if(value==null||"".equals(value.trim())){
                		value=null;
                	}
                    itemOrganMap.put(StringUtils.captureName(entry.getKey()),
                            value);
            	}
            	
            }

            returnMap.put("organ", itemOrganMap);
        }
       
        //处理cmDiabetes中信息
        Map<String, Object> itemcmDiabetesMap =null;
     
        if (dataMap.get("cmHypertension") != null) {
            Map<String, Object> cmDiabetesMap = (Map<String, Object>) dataMap.get("cmHypertension");
            //处理特殊字段
            cmDiabetesMap.put("docUserId", cmDiabetesMap.get("UserID"));
            cmDiabetesMap.put("doctorId", cmDiabetesMap.get("DoctorID"));
            cmDiabetesMap.put("personId", cmDiabetesMap.get("PersonID"));
            cmDiabetesMap.remove("UserID");
            cmDiabetesMap.remove("DoctorID");
            cmDiabetesMap.remove("PersonID");
            //处理个人信息 
            if(StringUtils.isNotEmpty((String)cmDiabetesMap.get("personId"))){
            	String PersonID=(String)cmDiabetesMap.get("personId");
            	//查询个人信息 调接口处理 55-10
            	 paramMap.put("ID", PersonID);
            	 String personData = RestfulUtils.getPublicData("55-10", paramMap);
                 // 解析Json，封装返回参数
                 Map<String, Object> pjsonMap = JsonUtils.getObjectJsonMap(personData.trim());
                 Map<String, Object> returnpMap = new HashMap<String, Object>();
                 if ((Integer) pjsonMap.get("Result") == 0) {
                     // 返回result为0，提示内容
                	 returnMap.put("personInfo", returnpMap);
                 }
                 Map<String, Object> pMap = (Map<String, Object>) pjsonMap.get("Msg");
                 // "BuildDate": "建档日期",  "CardID": "身份证号",  "PersonCode": "人员编号",  "BirthDay": "出生日期",
                 int age=0;
				try {
					if(StringUtils.isNotEmpty((String)pMap.get("BirthDay"))){
						  Date birthDay = DateUtils.parse((String)pMap.get("BirthDay").toString().substring(0,10));
						  age=  DateUtils.getAgeByCal(birthDay);
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
				 // "GenderCode": "性别代号:0未知,1男,2女,9未说明",
				 String sex=(String)pMap.get("GenderCode");
		         switch (sex) {
					case "0":
						sex="未知";
						break;
					case "1":
						sex="男";
						break;
					case "2":
						sex="女";
						break;
					case "9":
						sex="未说明";
						break;
				
				 }
		         //建档单位
	             returnpMap.put("buildOrgName", pMap.get("BuildOrgName"));
	             //责任医生
	             returnpMap.put("responsibilityDoctor", pMap.get("ResponsibilityDoctor"));
	             returnpMap.put("idCard", pMap.get("CardID"));
	             returnpMap.put("personCode", pMap.get("PersonCode"));
	             returnpMap.put("contactTel", pMap.get("ContactTel"));
	             returnpMap.put("personTel", pMap.get("PersonTel"));
	             returnpMap.put("age",Integer.toString(age));
	             returnpMap.put("personName", pMap.get("Name"));
	             //建档人
	             returnpMap.put("buildEmployeeName", pMap.get("BuildEmployeeName"));
	             //户籍
	             returnpMap.put("residenceAddress", pMap.get("ResidenceAddress"));
	             //地址
	             returnpMap.put("currentAddress", pMap.get("CurrentAddress"));
	             //乡镇名称
	             returnpMap.put("xiangzhen", pMap.get("xiangzhen"));
	             //村名称
	             returnpMap.put("cun", pMap.get("cun"));
	             if(StringUtils.isNotEmpty((String)pMap.get("BuildDate"))){
		             returnpMap.put("buildDate",(String)pMap.get("BuildDate").toString().substring(0,10));
		         }else{
		        	 returnpMap.put("buildDate","不祥"); 
		         }
		             
	             returnpMap.put("genderCode", sex);
	        	 returnMap.put("personInfo", returnpMap);
            }
            
            
            //处理体检日期
            if (cmDiabetesMap.get("ExamDate") != null && StringUtils.isNotEmpty((String)cmDiabetesMap.get("ExamDate"))) {
                String dateNum = StringUtils.getStringNum((String)cmDiabetesMap.get("ExamDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                    cmDiabetesMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            //处理体检日期
            if (cmDiabetesMap.get("FollowUpDate") != null && StringUtils.isNotEmpty((String)cmDiabetesMap.get("FollowUpDate"))) {
                String dateNum = StringUtils.getStringNum((String)cmDiabetesMap.get("FollowUpDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	cmDiabetesMap.put("followUpDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            
            if (cmDiabetesMap.get("NextFollowUpDate") != null && StringUtils.isNotEmpty((String)cmDiabetesMap.get("NextFollowUpDate"))) {
                String dateNum = StringUtils.getStringNum((String)cmDiabetesMap.get("NextFollowUpDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	cmDiabetesMap.put("nextFollowUpDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
           
            itemcmDiabetesMap = new HashMap<String, Object>();

            for (Map.Entry<String, Object> entry : cmDiabetesMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemcmDiabetesMap.put(StringUtils.captureName(entry.getKey()),
            				entry.getValue());
            	}else{
            		String value=(String)entry.getValue();
                	if(value==null||"".equals(value.trim())){
                		value=null;
                	}
                    itemcmDiabetesMap.put(StringUtils.captureName(entry.getKey()),
                            value);
            	}
            	
            }

            returnMap.put("cmHypertension", itemcmDiabetesMap);
        }
        
      //处理master中信息
        if (dataMap.get("master") != null) {
            Map<String, Object> masterMap = (Map<String, Object>) dataMap.get("master");
            //处理特殊字段
          

            //处理体检日期
            if (masterMap.get("followUpDate") != null && StringUtils.isNotEmpty((String)masterMap.get("followUpDate"))) {
                String dateNum = StringUtils.getStringNum((String)masterMap.get("followUpDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	masterMap.put("followUpDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            
            if (masterMap.get("NextFollowUpDate") != null && StringUtils.isNotEmpty((String)masterMap.get("NextFollowUpDate"))) {
                String dateNum = StringUtils.getStringNum((String)masterMap.get("NextFollowUpDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	masterMap.put("nextFollowUpDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }


            Map<String, Object> itemMasterMap = new HashMap<String, Object>();

            for (Map.Entry<String, Object> entry : masterMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemMasterMap.put(StringUtils.captureName(entry.getKey()),
            				entry.getValue());
            	}else{
            		String value=(String)entry.getValue();
                	if(value==null||"".equals(value.trim())){
                		value=null;
                	}
                	itemMasterMap.put(StringUtils.captureName(entry.getKey()),
                            value);
            	}
            	
            }

            returnMap.put("master", itemMasterMap);
        }


        //处理labora中信息
        if (dataMap.get("examLaboratory") != null) {
            Map<String, Object> laboraMap = (Map<String, Object>) dataMap.get("examLaboratory");
            Map<String, Object> itemLaboraMap = new HashMap<String, Object>();
            //处理体检日期
            if (laboraMap.get("ExamDate") != null && StringUtils.isNotEmpty((String) laboraMap.get("ExamDate"))) {
                String dateNum = StringUtils.getStringNum((String) laboraMap.get("ExamDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                    laboraMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            for (Map.Entry<String, Object> entry : laboraMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemLaboraMap.put(StringUtils.captureName(entry.getKey()),
            				entry.getValue());
            	}else{
            		String value=(String)entry.getValue();
                	if(value==null||"".equals(value.trim())){
                		value=null;
                	}
                    itemLaboraMap.put(StringUtils.captureName(entry.getKey()),
                            value);
            	}
            	
            }
            //处理用\u0001隔开部分
            dealExplain("ecg", "Explain", itemLaboraMap);    //心电图
            dealExplain("chestXRay", "Explain", itemLaboraMap);    //胸部X线片
            dealExplain("bUltrasonicWave", "Explain", itemLaboraMap);    //"B超
            dealExplain("cervicalSmear", "Explain", itemLaboraMap);    //宫颈涂片
            returnMap.put("labora", itemLaboraMap);
        }


        //处理lifeStyle中信息
        if (dataMap.get("examLifestyle") != null) {
            Map<String, Object> lifeStyleMap = (Map<String, Object>) dataMap.get("examLifestyle");
            lifeStyleMap.remove("ExamDate");
            Map<String, Object> itemLifeStyleMap = new HashMap<String, Object>();
            //处理体检日期
            if (lifeStyleMap.get("ExamDate") != null && StringUtils.isNotEmpty((String) lifeStyleMap.get("ExamDate"))) {
                String dateNum = StringUtils.getStringNum((String) lifeStyleMap.get("ExamDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	itemLifeStyleMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            for (Map.Entry<String, Object> entry : lifeStyleMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemLifeStyleMap.put(StringUtils.captureName(entry.getKey()),
            				entry.getValue());
            	}else{
            		String value=(String)entry.getValue();
                	if(value==null||"".equals(value.trim())){
                		value=null;
                	}
                    itemLifeStyleMap.put(StringUtils.captureName(entry.getKey()),
                            value);	
            	}
            	
            }

            returnMap.put("lifeStyle", itemLifeStyleMap);
        }


        //处理drug中信息
        if (dataMap.get("drugJson") != null && dataMap.get("drugJson") instanceof List) {
            List<Map<String, Object>> drugList = (List<Map<String, Object>>) dataMap.get("drugJson");
            List<Map<String, Object>> itemDrugList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : drugList) {
                Map<String, Object> itemDrugMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                	if("Drugs".equals(entry.getKey())){
                		itemDrugMap.put("cmDrugName", entry.getValue());
                	}
                    // 把key首字母改为小写
                	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
                		 itemDrugMap.put(StringUtils.captureName(entry.getKey()),
                				 entry.getValue());
                	}else{
                		String value=(String)entry.getValue();
                    	if(value==null||"".equals(value.trim())){
                    		value=null;
                    	}
                        itemDrugMap.put(StringUtils.captureName(entry.getKey()),
                                value);
                	}
                	
                    itemDrugMap.remove("drugs");
                }
                itemDrugList.add(itemDrugMap);
            }
            returnMap.put("drugUseList", itemDrugList);
        }




        //处理insulindrug中信息
        /*if (dataMap.get("insulindrug") != null && dataMap.get("insulindrug") instanceof List) {
            List<Map<String, Object>> insulindrug = (List<Map<String, Object>>) dataMap.get("insulindrug");
            List<Map<String, Object>> iteminsulindrugList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : insulindrug) {

                Map<String, Object> itemInsMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    // 把key首字母改为小写
                    itemInsMap.put(StringUtils.captureName(entry.getKey()),
                            entry.getValue());
                }
                iteminsulindrugList.add(itemInsMap);
            }
            returnMap.put("insulindrug", iteminsulindrugList);
        }
*/

        //处理transout中信息
        if (dataMap.get("transout") != null) {
            Map<String, Object> transoutMap = (Map<String, Object>) dataMap.get("transout");
            Map<String, Object> itemTransoutMap = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : transoutMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemTransoutMap.put(StringUtils.captureName(entry.getKey()),
            				entry.getValue());
            	}else{
            		String value=(String)entry.getValue();
                	if(value==null||"".equals(value.trim())){
                		value=null;
                	}
                    itemTransoutMap.put(StringUtils.captureName(entry.getKey()),
                            value);
            	}
            	
            }
            returnMap.put("transout", itemTransoutMap);
        }


        //处理Other中信息
        if (dataMap.get("otherJson") != null && dataMap.get("otherJson") instanceof List) {
            List<Map<String, Object>> Other = (List<Map<String, Object>>) dataMap.get("otherJson");
            List<Map<String, Object>> itemOtherList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : Other) {
            	
            	if(map.get("AttrName").equals("AdverseDrugReactions")){
            		 itemcmDiabetesMap.put("adverseDrugReactionsOther", map.get("OtherText"));
            	}
            	if(map.get("AttrName").equals("Symptom")){
           		 itemcmDiabetesMap.put("symptomOther", map.get("OtherText"));
           	    }
                Map<String, Object> itemInsMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    // 把key首字母改为小写
                	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
                		itemInsMap.put(StringUtils.captureName(entry.getKey()),
                				entry.getValue());
                	}else{
                		String value=(String)entry.getValue();
                    	if(value==null||"".equals(value.trim())){
                    		value=null;
                    	}
                        itemInsMap.put(StringUtils.captureName(entry.getKey()),
                                value);
                	}
                	
                }
                itemOtherList.add(itemInsMap);
            }
            returnMap.put("Other", itemOtherList);
        }

        return JsonUtils.getJson(BaseJsonVo.success(returnMap));	
	}
	
	
	@RequestMapping(value = "/getHypertensionDetailByPersonId", method = RequestMethod.GET)
    @ApiOperation(value = "获取居民高血压详情（居民端）", notes = "{'personId':'居民ID', 'hypertensionId' : '高血压ID'}")
    public String getHypertensionDetailByPersonId(@RequestParam String personId,
                                       @RequestParam String hypertensionId) {
    	List<DoctorDetailVo>  list=signService.getSignDoctorTeamInfo(personId);
    	String productCode = null;
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
    	// 封装查询参数，调用卫计委接口
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ProductCode", productCode);
        paramMap.put("ID", hypertensionId);
        String examListData = RestfulUtils.getPublicData("58-3", paramMap);
        // 解析Json，封装返回参数
        Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(examListData.trim());
        if ((Integer) jsonMap.get("Result") == 0) {
        	String msgString=(String) jsonMap.get("Msg");
			if(msgString.contains("登陆信息失效")){
				return JsonUtils.getJson(BaseJsonVo.empty(
						ApiStatusEnum.HASNOTNOTIFY.getKey(), ApiStatusEnum.HASNOTNOTIFY.getValue()));
			}
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
        }
        Map<String, Object> dataMap = (Map<String, Object>) jsonMap.get("Msg");
        Map<String, Object> returnMap = new HashMap<String, Object>();

        //处理body中信息
        if (dataMap.get("examBody") != null) {
            Map<String, Object> bodyMap = (Map<String, Object>) dataMap.get("examBody");
            Map<String, Object> itemBodyMap = new HashMap<String, Object>();
            bodyMap.remove("ExamDate");
            //处理体检日期
            if (bodyMap.get("ExamDate") != null && StringUtils.isNotEmpty((String)bodyMap.get("ExamDate"))) {
                String dateNum = StringUtils.getStringNum((String)bodyMap.get("ExamDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	itemBodyMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            for (Map.Entry<String, Object> entry : bodyMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
           		 itemBodyMap.put(StringUtils.captureName(entry.getKey()),
           				 entry.getValue());
	           	}else{
	           		String value=(String)entry.getValue();
	               	if(value==null||"".equals(value.trim())){
	               		value=null;
	               	}
	                   itemBodyMap.put(StringUtils.captureName(entry.getKey()),
	                           value);
	           	}
                
            }
            returnMap.put("body", itemBodyMap);
        }

        //处理organ中信息
        if (dataMap.get("examOrgan") != null) {
            Map<String, Object> organMap = (Map<String, Object>) dataMap.get("examOrgan");
            Map<String, Object> itemOrganMap = new HashMap<String, Object>();
            organMap.remove("ExamDate");
            for (Map.Entry<String, Object> entry : organMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemOrganMap.put(StringUtils.captureName(entry.getKey()),
              				 entry.getValue());
   	           	}else{
   	           		String value=(String)entry.getValue();
   	               	if(value==null||"".equals(value.trim())){
   	               		value=null;
   	               	}
   	                 itemOrganMap.put(StringUtils.captureName(entry.getKey()),
   	                           value);
   	           	}
            	
                 
            }

            returnMap.put("organ", itemOrganMap);
        }
       
        //处理cmDiabetes中信息
        Map<String, Object> itemcmDiabetesMap =new HashMap<String, Object>();
     
        if (dataMap.get("cmHypertension") != null) {
            Map<String, Object> cmDiabetesMap = (Map<String, Object>) dataMap.get("cmHypertension");
            //处理特殊字段
            cmDiabetesMap.put("docUserId", cmDiabetesMap.get("UserID"));
            cmDiabetesMap.put("doctorId", cmDiabetesMap.get("DoctorID"));
            cmDiabetesMap.put("personId", cmDiabetesMap.get("PersonID"));
            cmDiabetesMap.remove("UserID");
            cmDiabetesMap.remove("DoctorID");
            cmDiabetesMap.remove("PersonID");
            //处理个人信息 
            if(StringUtils.isNotEmpty((String)cmDiabetesMap.get("personId"))){
            	String PersonID=(String)cmDiabetesMap.get("personId");
            	//查询个人信息 调接口处理 55-10
            	 paramMap.put("ID", PersonID);
            	 String personData = RestfulUtils.getPublicData("55-10", paramMap);
                 // 解析Json，封装返回参数
                 Map<String, Object> pjsonMap = JsonUtils.getObjectJsonMap(personData.trim());
                 Map<String, Object> returnpMap = new HashMap<String, Object>();
                 if ((Integer) pjsonMap.get("Result") == 0) {
                     // 返回result为0，提示内容
                	 returnMap.put("personInfo", returnpMap);
                 }
                 Map<String, Object> pMap = (Map<String, Object>) pjsonMap.get("Msg");
                 // "BuildDate": "建档日期",  "CardID": "身份证号",  "PersonCode": "人员编号",  "BirthDay": "出生日期",
                 int age=0;
                 try {
 					if(StringUtils.isNotEmpty((String)pMap.get("BirthDay"))){
 						  Date birthDay = DateUtils.parse((String)pMap.get("BirthDay").toString().substring(0,10));
 						  age=  DateUtils.getAgeByCal(birthDay);
 						}
 				 } catch (Exception e) {
 					e.printStackTrace();
 				 }
				 // "GenderCode": "性别代号:0未知,1男,2女,9未说明",
				 String sex=(String)pMap.get("GenderCode");
		         switch (sex) {
					case "0":
						sex="未知";
						break;
					case "1":
						sex="男";
						break;
					case "2":
						sex="女";
						break;
					case "9":
						sex="未说明";
						break;
				
				 }
	             returnpMap.put("buildOrgName", pMap.get("BuildOrgName"));
	             returnpMap.put("idCard", pMap.get("CardID"));
	             returnpMap.put("personCode", pMap.get("PersonCode"));
	             returnpMap.put("contactTel", pMap.get("ContactTel"));
	             returnpMap.put("personTel", pMap.get("PersonTel"));
	             returnpMap.put("age",Integer.toString(age));
	             returnpMap.put("personName", pMap.get("Name"));
	             if(StringUtils.isNotEmpty((String)pMap.get("BuildDate"))){
		             returnpMap.put("buildDate",(String)pMap.get("BuildDate").toString().substring(0,10));
		         }else{
		        	 returnpMap.put("buildDate","不祥"); 
		         }
	             returnpMap.put("genderCode", sex);
	             //建档人
	             returnpMap.put("buildEmployeeName", pMap.get("BuildEmployeeName"));
	             //户籍
	             returnpMap.put("residenceAddress", pMap.get("ResidenceAddress"));
	             //地址
	             returnpMap.put("currentAddress", pMap.get("CurrentAddress"));
	             //乡镇名称
	             returnpMap.put("xiangzhen", pMap.get("xiangzhen"));
	             //村名称
	             returnpMap.put("cun", pMap.get("cun"));
	             //照片
	             returnpMap.put("photo", pMap.get("Photo"));
	        	 returnMap.put("personInfo", returnpMap);
            }
            
            
            //处理体检日期
            if (cmDiabetesMap.get("ExamDate") != null && StringUtils.isNotEmpty((String)cmDiabetesMap.get("ExamDate"))) {
                String dateNum = StringUtils.getStringNum((String)cmDiabetesMap.get("ExamDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                    cmDiabetesMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            //处理体检日期
            if (cmDiabetesMap.get("FollowUpDate") != null && StringUtils.isNotEmpty((String)cmDiabetesMap.get("FollowUpDate"))) {
                String dateNum = StringUtils.getStringNum((String)cmDiabetesMap.get("FollowUpDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	cmDiabetesMap.put("followUpDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            
            if (cmDiabetesMap.get("NextFollowUpDate") != null && StringUtils.isNotEmpty((String)cmDiabetesMap.get("NextFollowUpDate"))) {
                String dateNum = StringUtils.getStringNum((String)cmDiabetesMap.get("NextFollowUpDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	cmDiabetesMap.put("nextFollowUpDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
           
           // itemcmDiabetesMap = new HashMap<String, Object>();

            for (Map.Entry<String, Object> entry : cmDiabetesMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemcmDiabetesMap.put(StringUtils.captureName(entry.getKey()),
              				 entry.getValue());
   	           	}else{
   	           		String value=(String)entry.getValue();
   	               	if(value==null||"".equals(value.trim())){
   	               		value=null;
   	               	}
   	               itemcmDiabetesMap.put(StringUtils.captureName(entry.getKey()),
   	                           value);
   	           	}
            	
                
            }

            returnMap.put("cmHypertension", itemcmDiabetesMap);
        }
        
      //处理master中信息
        if (dataMap.get("master") != null) {
            Map<String, Object> masterMap = (Map<String, Object>) dataMap.get("master");
            //处理特殊字段
          

            //处理体检日期
            if (masterMap.get("followUpDate") != null && StringUtils.isNotEmpty((String)masterMap.get("followUpDate"))) {
                String dateNum = StringUtils.getStringNum((String)masterMap.get("followUpDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	masterMap.put("followUpDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            
            if (masterMap.get("NextFollowUpDate") != null && StringUtils.isNotEmpty((String)masterMap.get("NextFollowUpDate"))) {
                String dateNum = StringUtils.getStringNum((String)masterMap.get("NextFollowUpDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	masterMap.put("nextFollowUpDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }


            Map<String, Object> itemMasterMap = new HashMap<String, Object>();

            for (Map.Entry<String, Object> entry : masterMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemMasterMap.put(StringUtils.captureName(entry.getKey()),
              				 entry.getValue());
   	           	}else{
   	           		String value=(String)entry.getValue();
   	               	if(value==null||"".equals(value.trim())){
   	               		value=null;
   	               	}
   	                 itemMasterMap.put(StringUtils.captureName(entry.getKey()),
   	                           value);
   	           	}
            	
            	 
            }

            returnMap.put("master", itemMasterMap);
        }


        //处理labora中信息
        if (dataMap.get("examLaboratory") != null) {
            Map<String, Object> laboraMap = (Map<String, Object>) dataMap.get("examLaboratory");
            Map<String, Object> itemLaboraMap = new HashMap<String, Object>();
            //处理体检日期
            if (laboraMap.get("ExamDate") != null && StringUtils.isNotEmpty((String) laboraMap.get("ExamDate"))) {
                String dateNum = StringUtils.getStringNum((String) laboraMap.get("ExamDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                    laboraMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            for (Map.Entry<String, Object> entry : laboraMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemLaboraMap.put(StringUtils.captureName(entry.getKey()),
              				 entry.getValue());
   	           	}else{
   	           		String value=(String)entry.getValue();
   	               	if(value==null||"".equals(value.trim())){
   	               		value=null;
   	               	}
   	             itemLaboraMap.put(StringUtils.captureName(entry.getKey()),
   	                           value);
   	           	}
            	
               
            }
            //处理用\u0001隔开部分
            dealExplain("ecg", "Explain", itemLaboraMap);    //心电图
            dealExplain("chestXRay", "Explain", itemLaboraMap);    //胸部X线片
            dealExplain("bUltrasonicWave", "Explain", itemLaboraMap);    //"B超
            dealExplain("cervicalSmear", "Explain", itemLaboraMap);    //宫颈涂片
            returnMap.put("labora", itemLaboraMap);
        }


        //处理lifeStyle中信息
        if (dataMap.get("examLifestyle") != null) {
            Map<String, Object> lifeStyleMap = (Map<String, Object>) dataMap.get("examLifestyle");
            lifeStyleMap.remove("ExamDate");
            Map<String, Object> itemLifeStyleMap = new HashMap<String, Object>();
            //处理体检日期
            if (lifeStyleMap.get("ExamDate") != null && StringUtils.isNotEmpty((String) lifeStyleMap.get("ExamDate"))) {
                String dateNum = StringUtils.getStringNum((String) lifeStyleMap.get("ExamDate"));
                Long timeStamp = Long.parseLong(dateNum);
                if (StringUtils.isNotEmpty(dateNum)) {
                	itemLifeStyleMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
                }
            }
            for (Map.Entry<String, Object> entry : lifeStyleMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemLifeStyleMap.put(StringUtils.captureName(entry.getKey()),
              				 entry.getValue());
   	           	}else{
   	           		String value=(String)entry.getValue();
   	               	if(value==null||"".equals(value.trim())){
   	               		value=null;
   	               	}
   	             itemLifeStyleMap.put(StringUtils.captureName(entry.getKey()),
   	                           value);
   	           	}
            	
                
            }

            returnMap.put("lifeStyle", itemLifeStyleMap);
        }


        //处理drug中信息
        if (dataMap.get("drugJson") != null && dataMap.get("drugJson") instanceof List) {
            List<Map<String, Object>> drugList = (List<Map<String, Object>>) dataMap.get("drugJson");
            List<Map<String, Object>> itemDrugList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : drugList) {
                Map<String, Object> itemDrugMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                	if("Drugs".equals(entry.getKey())){
                		itemDrugMap.put("cmDrugName", entry.getValue());
                	}
                    // 把key首字母改为小写
                	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
                		itemDrugMap.put(StringUtils.captureName(entry.getKey()),
                  				 entry.getValue());
       	           	}else{
       	           		String value=(String)entry.getValue();
       	               	if(value==null||"".equals(value.trim())){
       	               		value=null;
       	               	}
       	             itemDrugMap.put(StringUtils.captureName(entry.getKey()),
       	                           value);
       	           	}
                	
                     
                    itemDrugMap.remove("drugs");
                }
                itemDrugList.add(itemDrugMap);
            }
            returnMap.put("drugUseList", itemDrugList);
        }


        //处理insulindrug中信息
        /*if (dataMap.get("insulindrug") != null && dataMap.get("insulindrug") instanceof List) {
            List<Map<String, Object>> insulindrug = (List<Map<String, Object>>) dataMap.get("insulindrug");
            List<Map<String, Object>> iteminsulindrugList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : insulindrug) {

                Map<String, Object> itemInsMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    // 把key首字母改为小写
                    itemInsMap.put(StringUtils.captureName(entry.getKey()),
                            entry.getValue());
                }
                iteminsulindrugList.add(itemInsMap);
            }
            returnMap.put("insulindrug", iteminsulindrugList);
        }*/


        //处理transout中信息
        if (dataMap.get("transout") != null) {
            Map<String, Object> transoutMap = (Map<String, Object>) dataMap.get("transout");
            Map<String, Object> itemTransoutMap = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : transoutMap.entrySet()) {
                // 把key首字母改为小写
            	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
            		itemTransoutMap.put(StringUtils.captureName(entry.getKey()),
              				 entry.getValue());
   	           	}else{
   	           		String value=(String)entry.getValue();
   	               	if(value==null||"".equals(value.trim())){
   	               		value=null;
   	               	}
   	             itemTransoutMap.put(StringUtils.captureName(entry.getKey()),
   	                           value);
   	           	}
            	
                
            }
            returnMap.put("transout", itemTransoutMap);
        }


        //处理Other中信息
        if (dataMap.get("otherJson") != null && dataMap.get("otherJson") instanceof List) {
            List<Map<String, Object>> Other = (List<Map<String, Object>>) dataMap.get("otherJson");
            List<Map<String, Object>> itemOtherList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : Other) {
            	
            	if(map.get("AttrName").equals("AdverseDrugReactions")){
            	     //System.out.println(1);
            		 itemcmDiabetesMap.put("adverseDrugReactionsOther", map.get("OtherText"));
            	}
            	if(map.get("AttrName").equals("Symptom")){
           		 itemcmDiabetesMap.put("symptomOther", map.get("OtherText"));
           	    }
                Map<String, Object> itemInsMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    // 把key首字母改为小写
                	if(entry.getValue() instanceof Integer||entry.getValue() instanceof Float||entry.getValue() instanceof Double){
                		itemInsMap.put(StringUtils.captureName(entry.getKey()),
                  				 entry.getValue());
       	           	}else{
       	           		String value=(String)entry.getValue();
       	               	if(value==null||"".equals(value.trim())){
       	               		value=null;
       	               	}
       	             itemInsMap.put(StringUtils.captureName(entry.getKey()),
       	                           value);
       	           	}
                	
                    
                }
                itemOtherList.add(itemInsMap);
            }
            returnMap.put("Other", itemOtherList);
        }

        return JsonUtils.getJson(BaseJsonVo.success(returnMap));
    	
    }
    @RequestMapping(value = "/getHypertensionListByPersonId", method = RequestMethod.GET)
    @ApiOperation(value = "获取居民个人高血压随访列表（居民端）", notes = "{ 'personId' : '居民ID'}")
    public String getHypertensionListByPersonId(
                                         @RequestParam Integer pageSize, @RequestParam Integer pageNo,
                                         @RequestParam String personId) {
    	List<DoctorDetailVo>  list=signService.getSignDoctorTeamInfo(personId);
    	String productCode = null;
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
    	
        // 封装查询参数，调用卫计委接口
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ProductCode", productCode);

        paramMap.put("PersonID", personId);
        paramMap.put("PageIndex", pageNo - 1);
        paramMap.put("PageSize", pageSize);
        String diabetesListData = RestfulUtils.getPublicData("58-1", paramMap);
        System.out.println("++++"+diabetesListData);
        // 解析Json，封装返回参数
        Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(diabetesListData);
        if ((Integer) jsonMap.get("Result") == 0) {
        	String msgString=(String) jsonMap.get("Msg");
			if(msgString.contains("登陆信息失效")){
				return JsonUtils.getJson(BaseJsonVo.empty(
						ApiStatusEnum.HASNOTNOTIFY.getKey(), ApiStatusEnum.HASNOTNOTIFY.getValue()));
			}
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
        } else {
            List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
            Integer count = Integer.valueOf((String) jsonMap.get("Total"));
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonMap
                    .get("Msg");
           //返回部分信息
            for (Map<String, Object> map : dataList) {
            	Map<String, Object> returnMap = new HashMap<String, Object>();
            	//处理随访日期
                if (map.get("FollowUpDate") != null && StringUtils.isNotEmpty((String)map.get("FollowUpDate"))) {
                    String dateNum = StringUtils.getStringNum((String)map.get("FollowUpDate"));
                    Long timeStamp = Long.parseLong(dateNum);
                    if (StringUtils.isNotEmpty(dateNum)) {
                    	returnMap.put("FollowUpDateStr", DateUtils.formatDate(new Date(timeStamp)));
                    }
                }         
				returnMap.put("hypertensionId", map.get("ID"));
				returnMap.put("personId", map.get("PersonID"));		 
				returnMap.put("personName", map.get("PersonName"));
				returnMap.put("sbp", map.get("Sbp"));
				returnMap.put("dbp", map.get("Dbp"));
				returnMap.put("followUpDate", map.get("FollowUpDateStr"));
				returnList.add(returnMap);
 
			}
            return JsonUtils.getPageJsonWithTotal(count, pageSize, returnList);
        }
    
    }
	 /**
    *
    * dealExplain:(处理用\u0001隔开的部分). <br/>
    * @author q
    * @param field 字段名
    * @param suffix  字段描述的后缀
    * @param itemMap  目标map
    * @return
    */
   public void dealExplain(String field, String suffix, Map<String, Object> itemMap){
       //非空判断
       if (itemMap.get(field) != null && StringUtils.isNotEmpty((String)itemMap.get(field))) {
           //按照|分割为数组
    	   String[] split =null;
    	   if(((String)itemMap.get(field)).contains("\\|")){
    		    split = ((String)itemMap.get(field)).split("\\|");  
    	   }else{
    		   split=((String)itemMap.get(field)).split("\\\u0001");
    	   }
           if(split.length>0){
        	 //重新填装字段
               itemMap.put(field, split[0]);
               if (split.length > 1) {
                   //填装描述字段
                   itemMap.put(field + suffix, split[1]);
               } 
           }
           
       }
   }
}
