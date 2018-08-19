package com.boco.modules.fdoc.web.mobile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.jedis.JedisUtils;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.NumberUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.service.FamilydoctorUpService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.vo.UpPlanVo;


@Controller
@RequestMapping(value = "/mobile/followup", produces = "application/json;charset=UTF-8")
public class HypertensionFollowUpContorller {
	private static final String Map = null;
	@Resource
	PersonInformationService personService;
	@Resource
	FamilydoctorUpService familydoctorUpService;
	
	@RequestMapping(value = "/hypertension", method = RequestMethod.GET)
	public String startAddhypertension(HttpServletRequest request, Model model, String healthFileCode,Integer planId,String userName){
		String docId = JedisUtils.get(userName + "id");
		String docName = JedisUtils.get(userName + "name");
		String orgId = JedisUtils.get(userName + "orgId");
		System.out.println(orgId);
		String productCode = PropertiesUtils.getValue("produce_code");
//		PersonInformationEntity personInfo = personService.queryPersonByHealFileCode(healthFileCode);
//		model.addAttribute("personInfo",personInfo);
//		model.addAttribute("docId", docId);
//		model.addAttribute("docName", docName);
//		model.addAttribute("planId", planId);
//		model.addAttribute("orgId", orgId);
//		model.addAttribute("productCode", productCode);
		
		return "/mobile/hypertensionFollowUp";
	}
	
	@RequestMapping(value = "/saveHypertension", method = RequestMethod.POST)
	@ResponseBody
	public String saveHypertension(HttpServletRequest request, Model model, String dataJson,Integer planId){
		System.out.println(dataJson);
		System.out.println(planId);
		try {
			
			Client client = ClientBuilder.newClient();
			WebTarget target = client
					.target("http://61.157.75.21:11004/webservice.asmx/HIS_Interface");
			Form form = new Form();
			form.param("TradeCode", "58-2");
			form.param("InputParameter", dataJson);
			javax.ws.rs.core.Response response = target.request().post(
					Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
			String respStr = response.readEntity(String.class);
			
			Document document = DocumentHelper.parseText(respStr);
			Element root = document.getRootElement();
			String data = root.getText();
			System.out.println(data);
			Map<String, Object> returnJsonMap = JsonUtils.getObjectJsonMap(data);
			// 新增成功后修改随访计划的相关信息
			if(returnJsonMap.get("ID") != null && !"".equals((String)returnJsonMap.get("ID"))){
				UpPlanVo vo = new UpPlanVo();
				vo.setId(planId);
				vo.setFollowUpId((String)returnJsonMap.get("ID"));
				Map<String, String> cmHyperMap = (Map<String, String>) JsonUtils.getObjectJsonMap(dataJson).get("CmHyper");
				String FollowUpDateStr = cmHyperMap.get("FollowUpDate");
				String wayUp = cmHyperMap.get("WayUp");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date followUpDate = format.parse(FollowUpDateStr);
				vo.setUpDate(followUpDate);
				vo.setUpWay(wayUp);
				vo.setStatus(1);
				
				familydoctorUpService.updateUpPlan(vo);
			}else {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
			}
			return JsonUtils.getJson(BaseJsonVo.success(null));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
//		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/updateHypertension", method = RequestMethod.GET)
	public String updateHypertension(HttpServletRequest request, Model model,Integer planId,String userName){
		String docId = JedisUtils.get(userName + "id");
		String docName = JedisUtils.get(userName + "name");
		String orgId = JedisUtils.get(userName + "orgId");
		//获取这一条随访计划
		UpPlanVo vo = new UpPlanVo();
		vo.setId(planId);
		vo.setDocId(docId);
		vo.setStatus(1);
		UpPlanVo planVo = familydoctorUpService.getunFinishSignedListById(vo);
		//获取个人信息
		String productCode = PropertiesUtils.getValue("produce_code");
		//PersonInformationEntity personInfo = personService.queryPersonByHealFileCode(planVo.getHealthFileCode());
		//model.addAttribute("personInfo",personInfo);
		model.addAttribute("planId", planId);
		model.addAttribute("orgId", orgId);
		model.addAttribute("productCode", productCode);
		//调用接口获取此次随访详细信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", productCode);
		paramMap.put("ID", planVo.getFollowUpId());
		String dataJson = RestfulUtils.getPublicData("58-3", paramMap).replace("/", "").replace("\\", "");
		Map<String, Object> dataJsonMap = JsonUtils.getObjectJsonMap(dataJson);
		System.out.println(JsonUtils.getJsonFormat(dataJsonMap));
		//获取随访时间数据，把返回的"Date(时间戳)"的数据格式改成yyyy-MM-dd的时间格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).get("FollowUpDate") != null && !"".equals((String)((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).get("FollowUpDate"))) {
			Long followUpDateLong = Long.parseLong(((String)((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).get("FollowUpDate")).replace("Date(", "").replace(")", ""));
			String followUpDate = format.format(new Date(followUpDateLong));
			((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).put("FollowUpDate", followUpDate);
		}
		//获取下次随访时间数据，把返回的"Date(时间戳)"的数据格式改成yyyy-MM-dd的时间格式
		if (((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).get("NextFollowUpDate") != null && !"".equals((String)((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).get("NextFollowUpDate"))) {
			Long nextFollowUpDateLong = Long.parseLong(((String)((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).get("NextFollowUpDate")).replace("Date(", "").replace(")", ""));
			String nextFollowUpDate = format.format(new Date(nextFollowUpDateLong));
			((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).put("NextFollowUpDate", nextFollowUpDate);
		}
		//获取下次体检时间数据，把返回的"Date(时间戳)"的数据格式改成yyyy-MM-dd的时间格式
		if (((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).get("ExamDate") != null && !"".equals((String)((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).get("ExamDate"))) {
			Long examDateLong = Long.parseLong(((String)((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).get("ExamDate")).replace("Date(", "").replace(")", ""));
			String examDate = format.format(new Date(examDateLong));
			((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("ExamDate", examDate);
		}
		
		//获取症状信息，把2的幂次表示法分割为逗号隔开的数组
		if (((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).get("Symptom") != null) {
			Integer symptomInt = ((Integer)((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).get("Symptom"));
			((Map)((Map)dataJsonMap.get("Msg")).get("cmHypertension")).put("Symptom", NumberUtils.bitand(symptomInt));
		}
		
		//获取心电图、胸片异常信息、B超异常信息、宫颈涂片异常信息，并用u0001隔开成下拉框数据以及说明数据
		String ChestXRayStr = ((String)((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).get("ChestXRay"));
		if (ChestXRayStr != null && !"".equals(ChestXRayStr)) {
			String[] ChestXRayArr = ChestXRayStr.split("u0001");
			if (ChestXRayArr.length == 1) {
				((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("ChestXRay", ChestXRayArr[0]);
			}else if (ChestXRayArr.length > 1) {
				((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("ChestXRayReason", ChestXRayArr[1]);
			}
		}
		
		String EcgStr = ((String)((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).get("Ecg"));
		if (EcgStr != null && !"".equals(EcgStr)) {
			String[] EcgArr = EcgStr.split("u0001");
			if (EcgArr.length == 1) {
				((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("Ecg", EcgArr[0]);
			}else if (EcgArr.length > 1) {
				((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("EcgReason", EcgArr[1]);
			}
		}
		
		String BUltrasonicWaveStr = ((String)((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).get("BUltrasonicWave"));
		if (BUltrasonicWaveStr != null && !"".equals(BUltrasonicWaveStr)) {
			String[] BUltrasonicWaveArr = BUltrasonicWaveStr.split("u0001");
			if (BUltrasonicWaveArr.length == 1) {
				((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("BUltrasonicWave", BUltrasonicWaveArr[0]);
			}else if (BUltrasonicWaveArr.length > 1) {
				((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("BUlReason", BUltrasonicWaveArr[1]);
			}
		}
		
		String CervicalSmearStr = ((String)((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).get("CervicalSmear"));
		if (CervicalSmearStr != null && !"".equals(CervicalSmearStr)) {
			String[] CervicalSmearArr = CervicalSmearStr.split("u0001");
			if (CervicalSmearArr.length == 1) {
				((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("CervicalSmear", CervicalSmearArr[0]);
			}else if (CervicalSmearArr.length > 1) {
				((Map)((Map)dataJsonMap.get("Msg")).get("examLaboratory")).put("CervicalSmearReason", CervicalSmearArr[1]);
			}
		}
		
		System.out.println(JsonUtils.getJsonFormat(dataJsonMap));
		model.addAttribute("dataJson", JsonUtils.getJsonFormat(dataJsonMap));
		return "/mobile/hypertensionFollowUp";
	}
}
