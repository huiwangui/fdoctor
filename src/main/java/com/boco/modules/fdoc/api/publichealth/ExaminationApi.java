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

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/publicInfo/examination", produces = "text/json;charset=UTF-8")
public class ExaminationApi {

	@Resource
	SignService signService;
	@Resource
	DocUserService docUserService;
	@Resource
	PersonInformationService personService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getPersonArchivesByDoctor", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民档案列表（医生端）", notes = "{'userName':'登录医生用户名', 'KeyValue' : '关键字内容，身份证号或姓名'}")
	public String getExaminationListByDoctor(@RequestParam String userName,
			@RequestParam Integer pageSize, @RequestParam Integer pageNo,
			@RequestParam String keyValue) {
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
		if (MatcherUtiles.idCardMach(keyValue)) {
			paramMap.put("KeyValueType", "2"); // 符合身份证号格式，当做身份证处理
		} else {
			paramMap.put("KeyValueType", "1"); // 不符合身份证格式，当做姓名处理
		}
		paramMap.put("KeyValue", keyValue);
		paramMap.put("PageIndex", pageNo - 1);
		paramMap.put("PageSize", pageSize);
		paramMap.put("isExamed", 1);
		paramMap.put("RegionCode", docUser.getRegionCode());
		String returnData = RestfulUtils.getPublicData("56-2", paramMap);
		// 解析Json，封装返回参数
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(returnData);
		if ((Integer) jsonMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
		} else {
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			Integer count = (Integer) jsonMap.get("Total");
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonMap
					.get("Msg");
			for (Map<String, Object> map : dataList) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("personId", map.get("ID"));
				returnMap.put("personCode", map.get("PersonCode"));
				returnMap.put("personName", map.get("Name"));
				returnMap.put("idCard", map.get("CardID"));
				returnMap.put("ageChs", map.get("Age"));
				returnMap.put("sexChs", map.get("GenderCode"));
				returnMap.put("mobile", map.get("Telphone"));
				returnMap.put("LastExamDateStr", map.get("Lasthldate"));
				returnList.add(returnMap);
			}
			return JsonUtils.getPageJsonWithTotal(count, pageSize, returnList);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getExaminationListByDoctor", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民体检列表（医生端）", notes = "{'userName':'登录医生用户名', 'personId' : '居民ID'}")
	public String getExaminationByDoctor(@RequestParam String userName,
			@RequestParam Integer pageSize, @RequestParam Integer pageNo,
			@RequestParam String personId) {
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
		
		paramMap.put("PersonID", personId);
		paramMap.put("PageIndex", pageNo - 1);
		paramMap.put("PageSize", pageSize);
		String examListData = RestfulUtils.getPublicData("56-1", paramMap);
		System.out.println(examListData);
		// 解析Json，封装返回参数
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(examListData);
		if ((Integer) jsonMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
		} else {
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			Integer count = Integer.valueOf((String) jsonMap.get("Total"));
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonMap
					.get("Msg");
			for (Map<String, Object> map : dataList) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("examId", map.get("OLDPEOPLEID"));
				returnMap.put("personId", map.get("PERSONID"));
				returnMap.put("personCode", map.get("PERSON_CODE"));
				returnMap.put("personName", map.get("NAME"));
				returnMap.put("idCard", map.get("CARD_ID"));
				returnMap.put("ageChs", map.get("AGE"));
				returnMap.put("sexChs", map.get("GENDER"));
				returnMap.put("mobile", map.get("PERSON_TEL"));
				returnMap.put("followUpDate", map.get("FOLLOW_UP_DATE"));
				returnList.add(returnMap);
			}
			return JsonUtils.getPageJsonWithTotal(count, pageSize, returnList);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getExaminationListByUser", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民体检列表（居民端）", notes = "{'personId' : '居民ID'}")
	public String getExaminationListByUser(@RequestParam Integer pageSize, @RequestParam Integer pageNo,
			@RequestParam String personId) {
		// 居民端调用，获取签约医生的productCode
		String productCode = null;
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
		//通过居民id获取基卫id
		String jwPersonId=null;
		PersonInformationEntity pEntity =personService.getPersonInformationByPesronId(personId);
		if(StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			jwPersonId=pEntity.getJwPersonId();
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		// 封装查询参数，调用卫计委接口
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", productCode);
		
		paramMap.put("PersonID", jwPersonId);
		paramMap.put("PageIndex", pageNo - 1);
		paramMap.put("PageSize", pageSize);
		String examListData = RestfulUtils.getPublicData("56-1", paramMap);
		// 解析Json，封装返回参数
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(examListData);
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
			for (Map<String, Object> map : dataList) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("examId", map.get("OLDPEOPLEID"));
				returnMap.put("personId", map.get("PERSONID"));
				returnMap.put("personCode", map.get("PERSON_CODE"));
				returnMap.put("personName", map.get("NAME"));
				returnMap.put("idCard", map.get("CARD_ID"));
				returnMap.put("ageChs", map.get("AGE"));
				returnMap.put("sexChs", map.get("GENDER"));
				returnMap.put("mobile", map.get("PERSON_TEL"));
				returnMap.put("followUpDate", map.get("FOLLOW_UP_DATE"));
				returnList.add(returnMap);
			}
			return JsonUtils.getPageJsonWithTotal(count, pageSize, returnList);
		}
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveExamination", method = RequestMethod.POST)
	@ApiOperation(value = "保存居民体检（医生端）")
	public String saveExamination(@RequestBody String paramBody) {
		Map<String, Object> dataMap = JsonUtils.getObjectJsonMap(paramBody); // 原始数据map
		Map<String, Object> itemMap = new HashMap<String, Object>(); // 调用卫计委接口需要的数据map
		String userName = (String) dataMap.get("userName");
		// 医生端调用，获取该医生的productCode
		DoctorDetailVo docUser = docUserService.getDoctorByUsername(userName);
		String productCode = docUser.getProductCode();
		itemMap.put("ProductCode", productCode);
		// 获取ID，若为新增，则ID为空
		if (dataMap.get("examId") != null
				&& StringUtils.isNotEmpty((String) dataMap.get("examId"))) {
			itemMap.put("MtID", dataMap.get("examId"));
		}
		/**
		 * 解析体检数据，封装为卫计委需要的数据
		 */
		// 准备other部分map
		List<Map<String, Object>> itemOtherList = new ArrayList<Map<String, Object>>();
		Map<String, Object> itemOtherMap = new HashMap<String, Object>();

		// 封装body部分
		if (dataMap.get("body") != null) {
			Map<String, Object> bodyMap = (Map<String, Object>) dataMap
					.get("body");
			Map<String, Object> itemBodyMap = new HashMap<String, Object>();
			for (Map.Entry<String, Object> entry : bodyMap.entrySet()) {
				// 把key首字母改为大写
				itemBodyMap.put(StringUtils.captureUpName(entry.getKey()),
						entry.getValue());
			}
			itemMap.put("Body", itemBodyMap);
		}

		// 封装Problems部分
		if (dataMap.get("problems") != null) {
			Map<String, Object> problemsMap = (Map<String, Object>) dataMap
					.get("problems");
			Map<String, Object> itemProblemsMap = new HashMap<String, Object>();
			for (Map.Entry<String, Object> entry : problemsMap.entrySet()) {
				// 把key首字母改为大写
				itemProblemsMap.put(StringUtils.captureUpName(entry.getKey()),
						entry.getValue());
			}
			itemOtherMap.put("Heart", problemsMap.get("heartOther")); // 心脏疾病其他
			itemOtherMap.put("Cerebrovascular",
					problemsMap.get("cerebrovascularOther")); // 脑血管疾病其他
			itemOtherMap.put("Kidney", problemsMap.get("kidneyOther")); // 肾脏疾病其他
			itemOtherMap.put("Vascular", problemsMap.get("vascularOther")); // 血管疾病其他
			itemOtherMap.put("Eyes", problemsMap.get("eyesOther")); // 眼部疾病其他
			itemOtherMap.put("Nervoussystems",
					problemsMap.get("nervoussystemsExplain")); // 神经系统疾病描述
			itemOtherMap.put("Others", problemsMap.get("problemOthersExplain")); // 其他系统疾病描述

			itemMap.put("Problems", itemProblemsMap);
		}

		// 封装ScaleScore部分
		if (dataMap.get("scaleScore") != null) {
			Map<String, Object> scaleScoreMap = (Map<String, Object>) dataMap
					.get("scaleScore");
			Map<String, Object> itemScaleScoreMap = new HashMap<String, Object>();
			for (Map.Entry<String, Object> entry : scaleScoreMap.entrySet()) {
				// 把key首字母改为大写
				itemScaleScoreMap.put(
						StringUtils.captureUpName(entry.getKey()),
						entry.getValue());
			}
			itemMap.put("ScaleScore", itemScaleScoreMap);
		}

		// 封装OePostion部分
		if (dataMap.get("oePostion") != null
				&& dataMap.get("oePostion") instanceof List) {
			List<Map<String, Object>> oePositionList = (List<Map<String, Object>>) dataMap
					.get("oePostion");
			List<Map<String, Object>> itemOePositionList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : oePositionList) {
				Map<String, Object> itemOePositionMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					// 把key首字母改为大写
					itemOePositionMap.put(
							StringUtils.captureUpName(entry.getKey()),
							entry.getValue());
				}
				itemOePositionList.add(itemOePositionMap);
			}
			itemMap.put("OePostion", itemOePositionList);
		}

		// 封装Labor部分
		if (dataMap.get("labor") != null) {
			Map<String, Object> laborMap = (Map<String, Object>) dataMap
					.get("labor");
			Map<String, Object> itemLaborScoreMap = new HashMap<String, Object>();
			// 处理用|隔开的部分
			if (laborMap.get("ecg") != null) {
				if("".equals(laborMap.get("ecg"))){
            		laborMap.put("ecg", null);
            		laborMap.remove("ecgExplain");
            	}else{
					laborMap.put(
							"ecg",
							laborMap.get("ecg")
									+ "|"
									+ (laborMap.get("ecgExplain") != null ? laborMap
											.get("ecgExplain") : ""));
					laborMap.remove("ecgExplain"); // 心电图
            	}
			}
			if (laborMap.get("chestXRay") != null) {
				if("".equals(laborMap.get("chestXRay"))){
            		laborMap.put("chestXRay", null);
            		laborMap.remove("chestXRayExplain");
            	}else{
					laborMap.put(
							"chestXRay",
							laborMap.get("chestXRay")
									+ "|"
									+ (laborMap.get("chestXRayExplain") != null ? laborMap
											.get("chestXRayExplain") : ""));
					laborMap.remove("chestXRayExplain"); // X光
            	}
			}
			if (laborMap.get("bUltrasonicWave") != null) {
				if("".equals(laborMap.get("bUltrasonicWave"))){
            		laborMap.put("bUltrasonicWave",null);
            		laborMap.remove("bUltrasonicWaveExplain");
            	}else{
					laborMap.put(
							"bUltrasonicWave",
							laborMap.get("bUltrasonicWave")
									+ "|"
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
									+ "|"
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
			itemMap.put("Labor", itemLaborScoreMap);
		}

		// 封装Woman部分
		if (dataMap.get("woman") != null) {
			Map<String, Object> womanMap = (Map<String, Object>) dataMap
					.get("woman");
			Map<String, Object> itemWomanMap = new HashMap<String, Object>();
			// 处理用|隔开的部分
			if (womanMap.get("vulva") != null) {
				womanMap.put(
						"vulva",
						womanMap.get("vulva")
								+ "|"
								+ (womanMap.get("vulvaExplain") != null ? womanMap
										.get("vulvaExplain") : ""));
				womanMap.remove("vulvaExplain"); // 外阴
			}
			if (womanMap.get("vaginal") != null) {
				womanMap.put(
						"vaginal",
						womanMap.get("vaginal")
								+ "|"
								+ (womanMap.get("vaginalExplain") != null ? womanMap
										.get("vaginalExplain") : ""));
				womanMap.remove("vaginalExplain"); // 阴道
			}
			if (womanMap.get("cervix") != null) {
				womanMap.put(
						"cervix",
						womanMap.get("cervix")
								+ "|"
								+ (womanMap.get("cervixExplain") != null ? womanMap
										.get("cervixExplain") : ""));
				womanMap.remove("cervixExplain"); // 宫颈
			}
			if (womanMap.get("palace") != null) {
				womanMap.put(
						"palace",
						womanMap.get("palace")
								+ "|"
								+ (womanMap.get("palaceExplain") != null ? womanMap
										.get("palaceExplain") : ""));
				womanMap.remove("palaceExplain"); // 宫体
			}
			if (womanMap.get("uterineAdnexa") != null) {
				womanMap.put(
						"uterineAdnexa",
						womanMap.get("uterineAdnexa")
								+ "|"
								+ (womanMap.get("uterineAdnexaExplain") != null ? womanMap
										.get("uterineAdnexaExplain") : ""));
				womanMap.remove("uterineAdnexaExplain"); // 附件
			}

			for (Map.Entry<String, Object> entry : womanMap.entrySet()) {
				// 把key首字母改为大写
				itemWomanMap.put(StringUtils.captureUpName(entry.getKey()),
						entry.getValue());
			}
			itemMap.put("Woman", itemWomanMap);
		}

		// 封装Organ部分
		if (dataMap.get("organ") != null) {
			Map<String, Object> organMap = (Map<String, Object>) dataMap
					.get("organ");
			Map<String, Object> itemOrganMap = new HashMap<String, Object>();

			// 处理用|隔开的部分
			if (organMap.get("fundus") != null) {
				organMap.put(
						"fundus",
						organMap.get("fundus")
								+ "|"
								+ (organMap.get("fundusExplain") != null ? organMap
										.get("fundusExplain") : ""));
				organMap.remove("fundusExplain"); // 眼底
			}
			if (organMap.get("breathSounds") != null) {
				organMap.put(
						"breathSounds",
						organMap.get("breathSounds")
								+ "|"
								+ (organMap.get("breathSoundsExplain") != null ? organMap
										.get("breathSoundsExplain") : ""));
				organMap.remove("breathSoundsExplain"); // 肺 呼吸音
			}
			if (organMap.get("heartMurmur") != null) {
				organMap.put(
						"heartMurmur",
						organMap.get("heartMurmur")
								+ "|"
								+ (organMap.get("heartMurmurExplain") != null ? organMap
										.get("heartMurmurExplain") : ""));
				organMap.remove("heartMurmurExplain"); // 心脏杂音
			}
			if (organMap.get("abdominalTenderness") != null) {
				organMap.put(
						"abdominalTenderness",
						organMap.get("abdominalTenderness")
								+ "|"
								+ (organMap.get("abdominalTendernessExplain") != null ? organMap
										.get("abdominalTendernessExplain") : ""));
				organMap.remove("abdominalTendernessExplain"); // 腹部 压痛
			}
			if (organMap.get("abdominalMass") != null) {
				organMap.put(
						"abdominalMass",
						organMap.get("abdominalMass")
								+ "|"
								+ (organMap.get("abdominalMassExplain") != null ? organMap
										.get("abdominalMassExplain") : ""));
				organMap.remove("abdominalMassExplain"); // 腹部 包块
			}
			if (organMap.get("theAbdomenLiver") != null) {
				organMap.put(
						"theAbdomenLiver",
						organMap.get("theAbdomenLiver")
								+ "|"
								+ (organMap.get("theAbdomenLiverExplain") != null ? organMap
										.get("theAbdomenLiverExplain") : ""));
				organMap.remove("theAbdomenLiverExplain"); // 腹部 肝大
			}
			if (organMap.get("splenomegaly") != null) {
				organMap.put(
						"splenomegaly",
						organMap.get("splenomegaly")
								+ "|"
								+ (organMap.get("splenomegalyExplain") != null ? organMap
										.get("splenomegalyExplain") : ""));
				organMap.remove("splenomegalyExplain"); // 腹部 脾大
			}
			if (organMap.get("shiftingDullness") != null) {
				organMap.put(
						"shiftingDullness",
						organMap.get("shiftingDullness")
								+ "|"
								+ (organMap.get("shiftingDullnessExplain") != null ? organMap
										.get("shiftingDullnessExplain") : ""));
				organMap.remove("shiftingDullnessExplain"); // 腹部 移动性浊音
			}
			// 处理放在other中的部分
			itemOtherMap.put("Skin", organMap.get("skinOther")); // 皮肤其他
			itemOtherMap.put("Sclera", organMap.get("scleraOther")); // 虹膜其他
			itemOtherMap.put("LymphNodes", organMap.get("lymphNodesOther")); // 淋巴结其他
			itemOtherMap.put("Rale", organMap.get("raleOther")); // 肺 罗音其他
			itemOtherMap.put("Dre", organMap.get("dreOther")); // 肛门指诊其他
			itemOtherMap.put("Breast", organMap.get("breastOther")); // 乳腺其他

			for (Map.Entry<String, Object> entry : organMap.entrySet()) {
				// 把key首字母改为大写
				itemOrganMap.put(StringUtils.captureUpName(entry.getKey()),
						entry.getValue());
			}
			itemMap.put("Organ", itemOrganMap);
		}

		// 封装master部分
		if (dataMap.get("master") != null) {
			Map<String, Object> masterMap = (Map<String, Object>) dataMap
					.get("master");
			Map<String, Object> itemMasterMap = new HashMap<String, Object>();
			
			itemMasterMap.put("PersonID",masterMap.get("personId"));

			// 处理放在other中的部分
			itemOtherMap.put("Symptom", masterMap.get("symptomOther")); // 症状其他

			for (Map.Entry<String, Object> entry : masterMap.entrySet()) {
				// 把key首字母改为大写
				itemMasterMap.put(StringUtils.captureUpName(entry.getKey()),
						entry.getValue());
			}
			itemMasterMap.put("UserID", itemMasterMap.get("DoctorId"));
			itemMasterMap.remove("DocUserId"); // 处理userId
			itemMasterMap.put("DoctorID", itemMasterMap.get("DoctorId"));
			itemMasterMap.remove("DoctorId"); // 处理doctorId
			itemMasterMap.remove("IdCard"); // 处理身份证号

			itemMap.put("Master", itemMasterMap);
		}

		// 封装lifestyle部分
		if (dataMap.get("lifestyle") != null) {
			Map<String, Object> lifestyleMap = (Map<String, Object>) dataMap
					.get("lifestyle");
			Map<String, Object> itemLifestyleMap = new HashMap<String, Object>();
			for (Map.Entry<String, Object> entry : lifestyleMap.entrySet()) {
				// 把key首字母改为大写
				itemLifestyleMap.put(StringUtils.captureUpName(entry.getKey()),
						entry.getValue());
			}
			itemMap.put("LifeStyle", itemLifestyleMap);
		}

		// 封装vacc部分
		if (dataMap.get("vacc") != null && dataMap.get("vacc") instanceof List) {
			List<Map<String, Object>> vaccList = (List<Map<String, Object>>) dataMap
					.get("vacc");
			List<Map<String, Object>> itemVaccList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : vaccList) {
				Map<String, Object> itemVaccMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					// 把key首字母改为大写
					itemVaccMap.put(StringUtils.captureUpName(entry.getKey()),
							entry.getValue());
				}
				itemVaccList.add(itemVaccMap);
			}
			itemMap.put("Vacc", itemVaccList);
		}

		// 封装drug部分
		if (dataMap.get("drug") != null && dataMap.get("drug") instanceof List) {
			List<Map<String, Object>> drugList = (List<Map<String, Object>>) dataMap
					.get("drug");
			List<Map<String, Object>> itemDrugList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : drugList) {
				Map<String, Object> itemDrugMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					// 把key首字母改为大写
					itemDrugMap.put(StringUtils.captureUpName(entry.getKey()),
							entry.getValue());
				}
				itemDrugList.add(itemDrugMap);
			}
			itemMap.put("Drug", itemDrugList);
		}

		// 封装Hospt部分
		if (dataMap.get("hospt") != null
				&& dataMap.get("hospt") instanceof List) {
			List<Map<String, Object>> hosptList = (List<Map<String, Object>>) dataMap
					.get("hospt");
			List<Map<String, Object>> itemHosptList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : hosptList) {
				Map<String, Object> itemHosptMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					// 把key首字母改为大写
					itemHosptMap.put(StringUtils.captureUpName(entry.getKey()),
							entry.getValue());
				}
				itemHosptList.add(itemHosptMap);
			}
			itemMap.put("Hospt", itemHosptList);
		}

		// 封装ChsCons部分
		if (dataMap.get("chsCons") != null) {
			Map<String, Object> chsConsMap = (Map<String, Object>) dataMap
					.get("chsCons");
			Map<String, Object> itemChsConsMap = new HashMap<String, Object>();
			for (Map.Entry<String, Object> entry : chsConsMap.entrySet()) {
				// 把key首字母改为大写
				itemChsConsMap.put(StringUtils.captureUpName(entry.getKey()),
						entry.getValue());
			}
			itemMap.put("ChsCons", itemChsConsMap);
		}

		// 其他参数
		itemMap.put("ans1", "[]");
		itemMap.put("ans2", "[]");
		itemMap.put("ans3", "[]");
		itemMap.put("OrgID", docUser.getOrgId());

		for (Map.Entry<String, Object> entry : itemOtherMap.entrySet()) {
			Map<String, Object> otherItem = new HashMap<String, Object>();
			otherItem.put("AttrName", entry.getKey());
			otherItem.put("OtherText", entry.getValue());
			itemOtherList.add(otherItem);
		}
		itemMap.put("Other", itemOtherList);
		// 封装查询参数，调用卫计委接口
		String returnMsg = RestfulUtils.getPublicData("56-3", itemMap);
		System.out.println(JsonUtils.getJson(itemMap));
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

	@RequestMapping(value = "/getExaminationDetailByDoctor", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民体检详情（医生端）", notes = "{'userName':'登录医生用户名', 'examId' : '体检ID'}")
	public String getExaminationDetailByDoctor(@RequestParam String userName,
			@RequestParam String examId) {
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
		paramMap.put("MtID", examId);
		String examListData = RestfulUtils.getPublicData("56-4", paramMap);
		// 解析Json，封装返回参数
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(examListData.trim());
		if ((Integer) jsonMap.get("Result") == 0) {
			// 返回result为0，提示内容
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(), (String) jsonMap.get("Msg")));
		}
		Map<String, Object> returnMap = dealDetailInfo(jsonMap);
		return JsonUtils.getJson(BaseJsonVo.success(returnMap),BusinessConstants.JSON_ALL);
		
	}
	
	@RequestMapping(value = "/getExaminationDetailByUser", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民体检详情（居民端）", notes = "{'personId':'居民ID','examId' : '体检ID'}")
	public String getExaminationDetailByUser(@RequestParam String personId,@RequestParam String examId) {
		// 居民端调用，获取签约医生的productCode
		String productCode = null;
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
		// 封装查询参数，调用卫计委接口
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", productCode);
		paramMap.put("MtID", examId);
		String examListData = RestfulUtils.getPublicData("56-4", paramMap);
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
		Map<String, Object> returnMap = dealDetailInfo(jsonMap);
		return JsonUtils.getJson(BaseJsonVo.success(returnMap),BusinessConstants.JSON_ALL);
		
	}
	
	/**
	 * 
	 * dealExplain:(处理用|隔开的部分). <br/>
	 * @author q
	 * @param field 字段名
	 * @param suffix  字段描述的后缀
	 * @param itemMap  目标map
	 * @return
	 */
	public void dealExplain(String field, String suffix, Map<String, Object> itemMap){
		
		//非空判断
		if (itemMap.get(field) != null && StringUtils.isNotEmpty((String)itemMap.get(field))) {
			if (((String)itemMap.get(field)).contains("|")) {
				if (!((String)itemMap.get(field)).equals("|")) {
					//按照|分割为数组
					String[] split = ((String)itemMap.get(field)).split("\\|");
					//重新填装字段
					if (split.length > 0) {
						itemMap.put(field, split[0]);
					}
					if (split.length > 1) {
						itemMap.put(field + suffix, split[1]);
					}
				}else {
					itemMap.put(field + suffix, "");
					itemMap.put(field, null);
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> dealDetailInfo(Map<String, Object> jsonMap){
		Map<String, Object> dataMap = (Map<String, Object>) jsonMap.get("Msg");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		//处理Other中的信息
		Map<String, Object> otherMap = new HashMap<String, Object>();
		if (dataMap.get("otherText") != null && StringUtils.isNotEmpty(((String) dataMap.get("otherText")))) {
			otherMap = JsonUtils.getObjectJsonMap(((String) dataMap.get("otherText")).replaceAll("'", "\""));
		}
		System.out.println(otherMap);
		//处理body中信息
		if (dataMap.get("body") != null) {
			Map<String, Object> bodyMap = (Map<String, Object>) dataMap.get("body"); 
			bodyMap.remove("ExamDate");
			Map<String, Object> itemBodyMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : bodyMap.entrySet()) {
				// 把key首字母改为小写
				itemBodyMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			returnMap.put("body", itemBodyMap);
		}
		
		//处理organ中信息
		if (dataMap.get("organ") != null) {
			Map<String, Object> organMap = (Map<String, Object>) dataMap.get("organ"); 
			organMap.remove("ExamDate");
			Map<String, Object> itemOrganMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : organMap.entrySet()) {
				// 把key首字母改为小写
				itemOrganMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			//处理用|隔开部分
			dealExplain("fundus", "Explain", itemOrganMap);	//眼底
			dealExplain("breathSounds", "Explain", itemOrganMap);	//肺呼吸音
			dealExplain("heartMurmur", "Explain", itemOrganMap);	//心脏杂音
			dealExplain("abdominalTenderness", "Explain", itemOrganMap);	//腹部压痛
			dealExplain("abdominalMass", "Explain", itemOrganMap);	//腹部包块
			dealExplain("theAbdomenLiver", "Explain", itemOrganMap);	//腹部肝大
			dealExplain("splenomegaly", "Explain", itemOrganMap);	//腹部脾大
			dealExplain("shiftingDullness", "Explain", itemOrganMap);	//腹部移动性浊音
			//处理other中的部分
			itemOrganMap.put("dreOther", otherMap.get("Dre"));	//肛门指诊其他
			itemOrganMap.put("breastOther", otherMap.get("Breast"));	//乳腺其他
			itemOrganMap.put("scleraOther", otherMap.get("Sclera"));	//虹膜其他
			itemOrganMap.put("skinOther", otherMap.get("Skin"));	//皮肤其他
			itemOrganMap.put("lymphNodesOther", otherMap.get("LymphNodes"));	//淋巴结其他
			itemOrganMap.put("raleOther", otherMap.get("Rale"));	//肺罗音结其他
			
			returnMap.put("organ", itemOrganMap);
		}
		
		//处理problems中信息
		if (dataMap.get("problems") != null) {
			Map<String, Object> problemsMap = (Map<String, Object>) dataMap.get("problems"); 
			problemsMap.remove("ExamDate");
			Map<String, Object> itemProblemsMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : problemsMap.entrySet()) {
				// 把key首字母改为小写
				itemProblemsMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			//处理other中的部分
			itemProblemsMap.put("heartOther", otherMap.get("Heart"));	//心脏疾病其他
			itemProblemsMap.put("problemOthersExplain", otherMap.get("Others"));	//其他系统疾病
			itemProblemsMap.put("nervoussystemsExplain", otherMap.get("Nervoussystems"));	//神经系统疾病说明
			itemProblemsMap.put("kidneyOther", otherMap.get("Kidney"));	//肾脏疾病其他
			itemProblemsMap.put("cerebrovascularOther", otherMap.get("Cerebrovascular"));	//脑血管疾病其他
			itemProblemsMap.put("vascularOther", otherMap.get("Vascular"));	//血管疾病其他
			itemProblemsMap.put("eyesOther", otherMap.get("Eyes"));	//眼部疾病其他
			
			returnMap.put("problems", itemProblemsMap);
		}
		
		//处理scaleScore中信息
		if (dataMap.get("scaleScore") != null) {
			Map<String, Object> scaleScoreMap = (Map<String, Object>) dataMap.get("scaleScore"); 
			scaleScoreMap.remove("ExamDate");
			Map<String, Object> itemScaleScoreMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : scaleScoreMap.entrySet()) {
				// 把key首字母改为小写
				itemScaleScoreMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			returnMap.put("scaleScore", itemScaleScoreMap);
		}
		
		//处理oePostion中信息
		if (dataMap.get("exLifeOeList") != null && dataMap.get("exLifeOeList") instanceof List) {
			List<Map<String, Object>> oePositionList = (List<Map<String, Object>>) dataMap.get("exLifeOeList");
			List<Map<String, Object>> itemOePositionList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : oePositionList) {
				Map<String, Object> itemOePositionMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					// 把key首字母改为大写
					itemOePositionMap.put(StringUtils.captureName(entry.getKey()),
							entry.getValue());
				}
				itemOePositionList.add(itemOePositionMap);
			}
			returnMap.put("oePostion", itemOePositionList);
		}
		
		//处理labor中信息
		if (dataMap.get("labora") != null) {
			Map<String, Object> laborMap = (Map<String, Object>) dataMap.get("labora"); 
			laborMap.remove("ExamDate");
			Map<String, Object> itemLaborMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : laborMap.entrySet()) {
				// 把key首字母改为小写
				itemLaborMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			
			//处理用|隔开的部分
			dealExplain("ecg", "Explain", itemLaborMap);	//心电图
			dealExplain("chestXRay", "Explain", itemLaborMap);	//胸部X线片
			dealExplain("bUltrasonicWave", "Explain", itemLaborMap);	//B超
			dealExplain("cervicalSmear", "Explain", itemLaborMap);	//宫颈涂片
			
			returnMap.put("labor", itemLaborMap);
		}
		
		//处理chsCon部分
		if (dataMap.get("chsCon") != null) {
			Map<String, Object> chsConMap = (Map<String, Object>) dataMap.get("chsCon"); 
			chsConMap.remove("ExamDate");
			Map<String, Object> itemChsConMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : chsConMap.entrySet()) {
				// 把key首字母改为小写
				itemChsConMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			
			returnMap.put("chsCons", itemChsConMap);
		}
		
		//处理woman部分
		if (dataMap.get("woman") != null) {
			Map<String, Object> womanMap = (Map<String, Object>) dataMap.get("woman"); 
			womanMap.remove("ExamDate");
			Map<String, Object> itemWomanMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : womanMap.entrySet()) {
				// 把key首字母改为小写
				itemWomanMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			//处理用|隔开的部分
			dealExplain("vulva", "Explain", itemWomanMap);	//外阴
			dealExplain("vaginal", "Explain", itemWomanMap);	//外阴
			dealExplain("cervix", "Explain", itemWomanMap);	//宫颈
			dealExplain("palace", "Explain", itemWomanMap);	//宫体
			dealExplain("uterineAdnexa", "Explain", itemWomanMap);	//附件
			
			returnMap.put("woman", itemWomanMap);
		}
		
		//处理master部分
		if (dataMap.get("master") != null) {
			Map<String, Object> masterMap = (Map<String, Object>) dataMap.get("master"); 
			//处理特殊字段
			//masterMap.put("docUserId", masterMap.get("UserID"));
			masterMap.put("doctorId", masterMap.get("DoctorID"));
			masterMap.put("personId", masterMap.get("PersonID"));
			masterMap.remove("UserID");
			masterMap.remove("DoctorID");
			masterMap.remove("PersonID");
			
			//处理体检日期
			if (masterMap.get("ExamDate") != null && StringUtils.isNotEmpty((String)masterMap.get("ExamDate"))) {
				String dateNum = StringUtils.getStringNum((String)masterMap.get("ExamDate"));
				Long timeStamp = Long.parseLong(dateNum);
				if (StringUtils.isNotEmpty(dateNum)) {
					masterMap.put("ExamDate", DateUtils.formatDate(new Date(timeStamp)));
				}
			}
			
			Map<String, Object> itemMasterMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : masterMap.entrySet()) {
				// 把key首字母改为小写
				itemMasterMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			
			returnMap.put("master", itemMasterMap);
		}
		
		//处理lifestyle中信息
		if (dataMap.get("lifeStyle") != null) {
			Map<String, Object> lifestyleMap = (Map<String, Object>) dataMap.get("lifeStyle"); 
			lifestyleMap.remove("ExamDate");
			Map<String, Object> itemLifestyleMap = new HashMap<String, Object>();
			
			for (Map.Entry<String, Object> entry : lifestyleMap.entrySet()) {
				// 把key首字母改为小写
				itemLifestyleMap.put(StringUtils.captureName(entry.getKey()),
						entry.getValue());
			}
			returnMap.put("lifestyle", itemLifestyleMap);
		}
		
		//处理vacc中信息
		if (dataMap.get("vaccList") != null && dataMap.get("vaccList") instanceof List) {
			List<Map<String, Object>> vaccList = (List<Map<String, Object>>) dataMap.get("vaccList");
			List<Map<String, Object>> itemVaccList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : vaccList) {
				//处理接种日期
				if (map.get("VaccDate") != null && StringUtils.isNotEmpty((String)map.get("VaccDate"))) {
					String dateNum = StringUtils.getStringNum((String)map.get("VaccDate"));
					Long timeStamp = Long.parseLong(dateNum);
					if (StringUtils.isNotEmpty(dateNum)) {
						map.put("VaccDate", DateUtils.formatDate(new Date(timeStamp)));
					}
				}
				map.remove("ExamDate");
				map.remove("DataMtID");
				Map<String, Object> itemVaccMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					// 把key首字母改为大写
					itemVaccMap.put(StringUtils.captureName(entry.getKey()),
							entry.getValue());
				}
				itemVaccList.add(itemVaccMap);
			}
			returnMap.put("vacc", itemVaccList);
		}
		
		//处理drug中信息
		if (dataMap.get("drugUseList") != null && dataMap.get("drugUseList") instanceof List) {
			List<Map<String, Object>> drugList = (List<Map<String, Object>>) dataMap.get("drugUseList");
			List<Map<String, Object>> itemDrugList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : drugList) {
				map.remove("ExamDate");
				map.remove("DataMtID");
				Map<String, Object> itemDrugMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					// 把key首字母改为大写
					itemDrugMap.put(StringUtils.captureName(entry.getKey()),
							entry.getValue());
				}
				itemDrugList.add(itemDrugMap);
			}
			returnMap.put("drug", itemDrugList);
		}
		
		//处理hospt中信息
		if (dataMap.get("hosList") != null && dataMap.get("hosList") instanceof List) {
			List<Map<String, Object>> hosptList = (List<Map<String, Object>>) dataMap.get("hosList");
			List<Map<String, Object>> itemHosptList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : hosptList) {
				map.remove("ExamDate");
				map.remove("DataMtID");
				
				//处理出院入院日期
				if (map.get("InDate") != null && StringUtils.isNotEmpty((String)map.get("InDate"))) {
					String dateNum = StringUtils.getStringNum((String)map.get("InDate"));
					Long timeStamp = Long.parseLong(dateNum);
					if (StringUtils.isNotEmpty(dateNum)) {
						map.put("InDate", DateUtils.formatDate(new Date(timeStamp)));
					}
				}
				if (map.get("OutDate") != null && StringUtils.isNotEmpty((String)map.get("OutDate"))) {
					String dateNum = StringUtils.getStringNum((String)map.get("OutDate"));
					Long timeStamp = Long.parseLong(dateNum);
					if (StringUtils.isNotEmpty(dateNum)) {
						map.put("OutDate", DateUtils.formatDate(new Date(timeStamp)));
					}
				}
				
				Map<String, Object> itemHosptMap = new HashMap<String, Object>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					// 把key首字母改为大写
					itemHosptMap.put(StringUtils.captureName(entry.getKey()),
							entry.getValue());
				}
				itemHosptList.add(itemHosptMap);
			}
			returnMap.put("hospt", itemHosptList);
		}
		return returnMap;
	}
}
