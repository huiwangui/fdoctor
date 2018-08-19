package com.boco.modules.fdoc.web.mobile;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.NumberUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.taobao.api.internal.toplink.embedded.websocket.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/mobile/examination", produces = "application/json;charset=UTF-8")
public class ExamnationInfoController {
	private static final Logger logger = LoggerFactory.getLogger(ExamnationInfoController.class);
	@Resource
	PersonInformationService personInformationService;
	@Resource
	DocUserService docUserService;
	@Resource
	SignService signService;

	@RequestMapping(value = "/toExaminationList", method = RequestMethod.GET)
	public String toExamnationList(HttpServletRequest request, Model model) {
		String androidData= request.getParameter("androidData");
		if(androidData==null){
		Map androidDataMap=new HashMap<>();
		androidDataMap.put("personId", "123");
		model.addAttribute("androidDataMap", JsonUtils.getJsonFormat(androidDataMap));
		 }else{
		Map androidDataMap=JsonUtils.getSingleJsonMap(androidData);
		if(androidDataMap.get("personId")==null||"".equals(androidDataMap.get("personId"))){
			androidDataMap.put("personId", "123");
		}
		String doctorName=(String) androidDataMap.get("doctorName");
		//DoctorDetailVo vo =docUserService.getDoctorByUsername(doctorName);
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("androidDataMap", JsonUtils.getJsonFormat(androidDataMap));
		 }
		return "/mobile/examination/queryExaminationList";

	}

	/**
	 * 老年人体检入口
	 */
	@RequestMapping(value = "/OldList", method = RequestMethod.GET)
	public String toOldExamnationList(HttpServletRequest request, Model model) {
		String androidData= request.getParameter("androidData");
		if(androidData==null){
		Map androidDataMap=new HashMap<>();
		androidDataMap.put("personId", "123");
		model.addAttribute("androidDataMap", JsonUtils.getJsonFormat(androidDataMap));
		 }else{
		Map androidDataMap=JsonUtils.getSingleJsonMap(androidData);
		if(androidDataMap.get("personId")==null||"".equals(androidDataMap.get("personId"))){
			androidDataMap.put("personId", "123");
		}
		//DoctorDetailVo vo =docUserService.getDoctorByUsername(doctorName);
		String doctorName=(String) androidDataMap.get("doctorName");
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("androidDataMap", JsonUtils.getJsonFormat(androidDataMap));
		 }
		return "/mobile/examination/oldQqueryList";

	}

	/*
	 * 
	 *新增体检 
	 * 
	 * 
	 */
	@RequestMapping(value = "/toAddExamination", method = RequestMethod.GET)
	public String toAddExamination(HttpServletRequest request, Model model, String radte) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		Map remap = JsonUtils.getSingleJsonMap(radte);
		
		String PersonID = (String) remap.get("ID");
		if(PersonID==null){
			PersonID= (String) remap.get("PERSONID");
		}
		if(PersonID==null){
		   return	"/mobile/examination/addExamination";
		}
		// 查体检日期  通过PersonID
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode", vo.getProductCode());
		paramMap.put("PersonID", PersonID);
		String response = RestfulUtils.getPublicData("56-6", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		List listExamDate = new ArrayList<>();
		if (responseMap.get("Result") != null && (Integer) responseMap.get("Result") != 0) {
			List<Map<String, Object>> listMtIdAndExamDate = (List<Map<String, Object>>) responseMap.get("Msg");
			// 体检时间和体检ID
			model.addAttribute("listMtIdAndExamDate", listMtIdAndExamDate);
			// 通过PersonID日期获取最后一次体检信息
			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("ProductCode", vo.getProductCode());
			paramMap2.put("PersonID", PersonID);
			if (listExamDate.size() > 0 && listExamDate != null) {
				paramMap2.put("ExamDate", listExamDate.get(0));
			}
			String response2 = RestfulUtils.getPublicData("56-7", paramMap2);
			Map<String, Object> responseMap2 = JsonUtils.getObjectJsonMap(response2);
			if (responseMap2.get("Result") != null && (Integer) responseMap2.get("Result") != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map = (Map<String, Object>) responseMap2.get("Msg");
				model.addAttribute("lastExamination", map);
			}

		}
		model.addAttribute("PersonID", PersonID);
		model.addAttribute("remap", remap);
		model.addAttribute("doctorName", vo.getDocName());
		model.addAttribute("userName", userName);
		model.addAttribute("ProductCode", vo.getProductCode());
		return "/mobile/examination/addExamination";

	}


	/*
	 * 个人体检页面 需要传递中 姓名 性别 年龄 档案编号
	 */
	@RequestMapping(value = "/toExaminationDetail", method = RequestMethod.GET)
	public String toExamnation(HttpServletRequest request, Model model, String radte) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		//model.addAttribute("productCode",vo.getProductCode());//改动
		Map remap = JsonUtils.getSingleJsonMap(radte);
		String PersonID = (String) remap.get("ID");
		if(PersonID==null){
			PersonID= (String) remap.get("PERSONID");
		}
		if(PersonID==null){
			return null;
		}
		// 查体检日期 "CD8B44CC9AB7411BA693C4EE8524D39F" 通过PersonID
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", vo.getProductCode());
		paramMap.put("PersonID", PersonID);
		String response = RestfulUtils.getPublicData("56-6", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		List listExamDate = new ArrayList<>();
		if (responseMap.get("Result") != null && (Integer) responseMap.get("Result") != 0) {
			List<Map<String, Object>> listMiIdAndExamDate = (List<Map<String, Object>>) responseMap.get("Msg");
			
			// 体检时间
			model.addAttribute("listExamDate", JsonUtils.getJsonFormat(listExamDate));
			// 体检时间和体检ID
			model.addAttribute("listMtIdAndExamDate", listMiIdAndExamDate);
			// 通过PersonID日期获取最后一次体检信息
			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("ProductCode", vo.getProductCode());
			paramMap2.put("PersonID", PersonID);
			if (listExamDate.size() > 0 && listExamDate != null) {
				paramMap2.put("ExamDate", listExamDate.get(0));
			}
			String response2 = RestfulUtils.getPublicData("56-7", paramMap2);
			Map<String, Object> responseMap2 = JsonUtils.getObjectJsonMap(response2);
			if (responseMap2.get("Result") != null && (Integer) responseMap2.get("Result") != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map = (Map<String, Object>) responseMap2.get("Msg");
				model.addAttribute("lastExamination", map);
			}

		}

		model.addAttribute("radte", radte);
		model.addAttribute("doctorName", vo.getDocName());
		model.addAttribute("userName", userName);
		model.addAttribute("ProductCode", vo.getProductCode());
		return "/mobile/examination/physicalEdit";
	}

	/*
	 * 保存居民体检
	 * 
	 */
	@RequestMapping(value = "/saveExamination", method = RequestMethod.POST)
	@ResponseBody
	public String saveExamnation(HttpServletRequest request, Model model, String dataJson) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		logger.info("保存数据"+dataJson);
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		// 保存体检结果
		Map<String, Object> paramMap = JsonUtils.getObjectJsonMap(dataJson);
		paramMap.put("ProductCode", vo.getProductCode());

		String response = RestfulUtils.getPublicData("56-3", paramMap);

		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
        logger.info("体检保存结果"+responseMap);
		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NULL_DATA_CODE.getKey(), ApiStatusEnum.NULL_DATA_CODE.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(""));
	}

	/*
	 * 编辑 居民 体检
	 * 
	 */
	@RequestMapping(value = "/updateExamination", method = RequestMethod.GET)
	public String updateExamnation(HttpServletRequest request, Model model, String radte) {
		DoctorDetailVo vo = new DoctorDetailVo();
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		// session不为空，说明是医生端访问，从session中获取userName
		if (StringUtils.isNotEmpty(userName)) {
			vo =docUserService.getDoctorByUsername(userName);
		}else {
			// session为空，说明为居民端访问，从参数中获取居民ID，查找与居民签约的医生productCode
			Map<String, String> dataMap = JsonUtils.getSingleJsonMap(radte);
			String personId = dataMap.get("PERSONID");
			List<DoctorDetailVo> teamInfo = signService.getSignDoctorTeamInfo(personId);
			
			for (DoctorDetailVo doctorDetailVo : teamInfo) {
				if (StringUtils.isNotEmpty(doctorDetailVo.getProductCode())) {
					vo = doctorDetailVo;
					break;
				}
			}
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("Json", JsonUtils.getJsonFormat(radte));
        model.addAttribute("productCode", vo.getProductCode());
		Map remap = JsonUtils.getSingleJsonMap(radte);
		if(remap==null){
			
		}
		//对remap的建进行替换  GenderCode
		if(remap.containsKey("ID")){
			remap.put("PERSONID",remap.get("ID"));
		}
		if(remap.containsKey("Name")){
			remap.put("NAME",remap.get("Name"));
		}
		if(remap.containsKey("CardID")){
			remap.put("CARD_ID",remap.get("CardID"));
		}
		if(remap.containsKey("Age")){
			remap.put("AGE",remap.get("Age"));
		}
		
		if(remap.containsKey("Telphone")){
			remap.put("PERSON_TEL",remap.get("Telphone"));
		}
		if(remap.containsKey("PersonCode")){
			remap.put("PERSON_CODE",remap.get("PersonCode"));
		}
		if(remap.containsKey("GenderCode")){
			remap.put("GENDER",remap.get("GenderCode"));
		}
		
		String flag=(String) remap.get("flag");
		String PersonID =null;
		System.out.println(remap);
		if(remap.containsKey("ID")){
			PersonID= (String) remap.get("ID");
		}
		if(remap.containsKey("PERSONID")){
		PersonID= (String) remap.get("PERSONID");
		}
		
		String ExamDate=(String) remap.get("FOLLOW_UP_DATE");
		
		String upExamDate=null;
		model.addAttribute("PersonID", PersonID);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", vo.getProductCode());
		paramMap.put("PersonID", PersonID);
		String response = RestfulUtils.getPublicData("56-6", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		List listExamDate = new ArrayList<>();
		List<Map<String, Object>> listMiIdAndExamDate = new ArrayList<Map<String, Object>>();
		if(responseMap.get("Result") != null && (Integer) responseMap.get("Result") != 0) {
			listMiIdAndExamDate = (List<Map<String, Object>>) responseMap.get("Msg");
			if (listMiIdAndExamDate.size() > 0 && listMiIdAndExamDate != null) {
				for (Map<String, Object> map : listMiIdAndExamDate) {
					String examDate = (String) map.get("ExamDate");
					listExamDate.add(examDate);
				}
			}
			if(ExamDate==null){
				if (listExamDate.size() > 0 && listExamDate != null) {
					ExamDate=(String) listExamDate.get(0);
				}
			}
			//personId查询居民信息
			PersonInformationEntity personInformationVo=personInformationService.getPersonInformationByPesronId(PersonID);
			if(personInformationVo==null){
				//本地查找不到 ，调卫计接口查询；
				PersonInformationVo pie=new PersonInformationVo();
				pie.setCurrentAddress(remap.get("CurrentAddress")==null?"":(String) remap.get("CurrentAddress"));
				pie.setSex(remap.get("GENDER")==null?"":(String) remap.get("GENDER"));
				pie.setPersonName(remap.get("NAME")==null?"":(String) remap.get("NAME"));
				pie.setPersonCode(remap.get("PERSON_CODE")==null?"":(String) remap.get("PERSON_CODE"));
				pie.setPhoneNumber(remap.get("PERSON_TEL")==null?"":(String) remap.get("PERSON_TEL"));
				pie.setPersonId(remap.get("PERSONID")==null?"":(String) remap.get("PERSONID"));
				model.addAttribute("ExamAge",(String) remap.get("AGE"));
			    model.addAttribute("pseonInfo", pie);
				model.addAttribute("pseonInfoJson",JsonUtils.getJsonFormat( JsonUtils.getJsonFormat(pie)));
			}else{
			Date date1=personInformationVo.getDateOfBirth();
			if(date1==null||"".equals(date1)){
				model.addAttribute("ExamAge"," ");
			}else{
			 try {
				Date date2=format.parse(ExamDate);
		        String age=String.valueOf(getAge(date1, date2)) ;
		        model.addAttribute("ExamAge",age);
			 } catch (ParseException e) {
				
				e.printStackTrace();
			 }
			}
			if(personInformationVo.getSex().equals("1")){
				personInformationVo.setSex("男");
			}else if(personInformationVo.getSex().equals("2")){
				personInformationVo.setSex("女");
			}else{
				personInformationVo.setSex("未知");
			}
			
			model.addAttribute("pseonInfo", personInformationVo);
			model.addAttribute("pseonInfoJson",JsonUtils.getJsonFormat( JsonUtils.getJsonFormat(personInformationVo)));
			System.out.println("pseonInfo"+JsonUtils.getJsonFormat(personInformationVo));
			}
			//上一次体检时间
			if(listExamDate.lastIndexOf(ExamDate)+1==(listExamDate.size())){
				 upExamDate=(String) listExamDate.get(listExamDate.lastIndexOf(ExamDate));
			}else{
			 upExamDate=(String) listExamDate.get(listExamDate.lastIndexOf(ExamDate)+1);}
			
			// 通过PersonID日期获取最后一次体检信息 获取居民信息
		   Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("ProductCode", vo.getProductCode());
			paramMap2.put("PersonID", PersonID);
			if (listExamDate.size() > 0 && listExamDate != null) {
				paramMap2.put("ExamDate", listExamDate.get(0));
			}
			
			String response2 = RestfulUtils.getPublicData("56-7", paramMap2);
			Map<String, Object> responseMap2 = JsonUtils.getObjectJsonMap(response2);
			Map<String, Object> mapLastExamination = new HashMap<String, Object>();
			if (responseMap2.get("Result") != null && (Integer) responseMap2.get("Result") != 0) {

				mapLastExamination = (Map<String, Object>) responseMap2.get("Msg");
			}
			model.addAttribute("lastExamination", mapLastExamination);
			model.addAttribute("lastExaminationJson", JsonUtils.getJsonFormat(mapLastExamination));

			// 通过随访id 56-4 查询个人健康体检记录
			Map<String, Object> paramMap3 = new HashMap<String, Object>();
			paramMap3.put("ProductCode", vo.getProductCode());
			
			String MtId = null;
			String upMtId=null;
			for (Map<String, Object> map : listMiIdAndExamDate) {
				
			   if(ExamDate.equals(map.get("ExamDate"))){
				   MtId=(String) map.get("MtId");
			   }
			   if(upExamDate.equals(map.get("ExamDate"))){
				 upMtId=(String) map.get("MtId");
			   }
			}
			
			//上次体检结果
			Map<String, Object> paramMap4 = new HashMap<String, Object>();
			paramMap4.put("ProductCode", vo.getProductCode());
			paramMap4.put("MtID", upMtId);
			String response4 = RestfulUtils.getPublicData("56-4", paramMap4);
			Map<String, Object> responseMap4 = JsonUtils.getObjectJsonMap(response4);
			if (responseMap4.get("Result") != null || (Integer) responseMap4.get("Result") != 0) {
				
				Map<String, Object> map4 = new HashMap<String, Object>();
				map4 = (Map<String, Object>) responseMap4.get("Msg");
				if (((Map<String, Object>) map4.get("master")).get("ExamDate") != null
						&& !"".equals(((Map<String, Object>) map4.get("master")).get("ExamDate"))) {
					String adate = (String) (((Map<String, Object>) map4.get("master")).get("ExamDate"));
					String regEx = "[^0-9]";
					Pattern p = Pattern.compile(regEx);
					Matcher m = p.matcher(adate);
					Long ExamDateLong = Long.parseLong(m.replaceAll("").trim());
					String examDate = format.format(new Date(ExamDateLong));
					map4.put("ExamDate", examDate);
					((Map<String, Object>) map4.get("master")).put("ExamDate", examDate);
				}
               model.addAttribute("upResult", map4);
			}
	
			paramMap3.put("MtID", MtId);
			//日期切换 体检结果
			String response3 = RestfulUtils.getPublicData("56-4", paramMap3);
			Map<String, Object> responseMap3 = JsonUtils.getObjectJsonMap(response3);

			if (responseMap3.get("Result") != null || (Integer) responseMap3.get("Result") != 0) {

				Map<String, Object> map = new HashMap<String, Object>();
				map = (Map<String, Object>) responseMap3.get("Msg");
				// 获取随访时间数据，把返回的"Date(时间戳)"的数据格式改成yyyy-MM-dd的时间格式

				if (((Map<String, Object>) map.get("master")).get("ExamDate") != null
						&& !"".equals(((Map<String, Object>) map.get("master")).get("ExamDate"))) {
					String adate = (String) (((Map<String, Object>) map.get("master")).get("ExamDate"));
					String regEx = "[^0-9]";
					Pattern p = Pattern.compile(regEx);
					Matcher m = p.matcher(adate);
					Long ExamDateLong = Long.parseLong(m.replaceAll("").trim());
					String examDate = format.format(new Date(ExamDateLong));
					map.put("ExamDate", examDate);
					((Map<String, Object>) map.get("master")).put("ExamDate", examDate);
				}

				// 去除时间
				if (((List<Map>) map.get("vaccList")) != null && !"".equals(((List<Map>) map.get("vaccList")))) {
					for (int i = 0; i < ((List<Map>) map.get("vaccList")).size(); i++) {
						if (((List<Map>) map.get("vaccList")).get(i).get("VaccDate") != null) {
							((List<Map>) map.get("vaccList")).get(i).remove("VaccDate");
						}
						if (((List<Map>) map.get("vaccList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("vaccList")).get(i).remove("ExamDate");
						}
					}
				}
				//
				if (((List<Map>) map.get("drugUseList")) != null && !"".equals(((List<Map>) map.get("drugUseList")))) {
					for (int i = 0; i < ((List<Map>) map.get("drugUseList")).size(); i++) {
						if (((List<Map>) map.get("drugUseList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("drugUseList")).get(i).remove("ExamDate");
						}
					}
				}

				//
				if (((List<Map>) map.get("hosList")) != null && !"".equals(((List<Map>) map.get("hosList")))) {
					for (int i = 0; i < ((List<Map>) map.get("hosList")).size(); i++) {
						if (((List<Map>) map.get("hosList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("ExamDate");
						}
						if (((List<Map>) map.get("hosList")).get(i).get("InDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("InDate");
						}
						if (((List<Map>) map.get("hosList")).get(i).get("OutDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("OutDate");
						}
					}
				}
				
				//分隔符处理 脏器功能回显 BreathSounds

				Map organ = (Map) map.get("organ");
				if (map.get("organ") != null) {
					String Fundus = (String) organ.get("Fundus");
					if (Fundus != null && !"".equals(Fundus)) {
						String[] FundusArr = Fundus.split("\\|");
						if (FundusArr.length == 1) {
							organ.put("Fundus", FundusArr[0]);
						} else if (FundusArr.length > 1) {
							organ.put("FundusReason", FundusArr[1]);
						}
					}

					String BreathSounds = (String) organ.get("BreathSounds");
					if (BreathSounds != null && !"".equals(BreathSounds)) {
						String[] BreathSoundsArr = BreathSounds.split("\\|");
						if (BreathSoundsArr.length == 1) {
							organ.put("BreathSounds", BreathSoundsArr[0]);
						} else if (BreathSoundsArr.length > 1) {
							organ.put("BreathSoundsReason", BreathSoundsArr[1]);
						}
					}


					String HeartMurmur = (String) organ.get("HeartMurmur");
					if (HeartMurmur != null && !"".equals(HeartMurmur)) {
						String[] HeartMurmurArr = HeartMurmur.split("\\|");
						if (HeartMurmurArr.length == 1) {
							organ.put("HeartMurmur", HeartMurmurArr[0]);
						} else if (HeartMurmurArr.length > 1) {
							organ.put("HeartMurmurReason", HeartMurmurArr[1]);
						}
					}

					String AbdominalTenderness = (String) organ.get("AbdominalTenderness");
					if (AbdominalTenderness != null && !"".equals(AbdominalTenderness)) {
						String[] AbdominalTendernessArr = AbdominalTenderness.split("\\|");
						if (AbdominalTendernessArr.length == 1) {
							organ.put("AbdominalTenderness", AbdominalTendernessArr[0]);
						} else if (AbdominalTendernessArr.length > 1) {
							organ.put("AbdominalTendernessReason", AbdominalTendernessArr[1]);
						}
					}


					String AbdominalMass = (String) organ.get("AbdominalMass");
					if (AbdominalMass != null && !"".equals(AbdominalMass)) {
						String[] AbdominalMassArr = AbdominalMass.split("\\|");
						if (AbdominalMassArr.length == 1) {
							organ.put("AbdominalMass", AbdominalMassArr[0]);
						} else if (AbdominalMassArr.length > 1) {
							organ.put("AbdominalMassReason", AbdominalMassArr[1]);
						}
					}

					String TheAbdomenLiver = (String) organ.get("TheAbdomenLiver");
					if (TheAbdomenLiver != null && !"".equals(TheAbdomenLiver)) {
						String[] TheAbdomenLiverArr = TheAbdomenLiver.split("\\|");
						if (TheAbdomenLiverArr.length == 1) {
							organ.put("TheAbdomenLiver", TheAbdomenLiverArr[0]);
						} else if (TheAbdomenLiverArr.length > 1) {
							organ.put("TheAbdomenLiverReason", TheAbdomenLiverArr[1]);
						}
					}


					String Splenomegaly = (String) organ.get("Splenomegaly");
					if (Splenomegaly != null && !"".equals(Splenomegaly)) {
						String[] SplenomegalyArr = Splenomegaly.split("\\|");
						if (SplenomegalyArr.length == 1) {
							organ.put("Splenomegaly", SplenomegalyArr[0]);
						} else if (SplenomegalyArr.length > 1) {
							organ.put("TheAbdomenLiverReason", SplenomegalyArr[1]);
						}
					}


					String ShiftingDullness = (String) organ.get("ShiftingDullness");
					if (ShiftingDullness != null && !"".equals(ShiftingDullness)) {
						String[] ShiftingDullnessArr = ShiftingDullness.split("\\|");
						if (ShiftingDullnessArr.length == 1) {
							organ.put("ShiftingDullness", ShiftingDullnessArr[0]);
						} else if (ShiftingDullnessArr.length > 1) {
							organ.put("ShiftingDullnessReason", ShiftingDullnessArr[1]);
						}
					}

				}
				////分隔符处理 妇科
				Map women = (Map) map.get("women");
				if (map.get("organ") != null && "".equals(map.get("organ"))) {

					String Vulva = (String) women.get("Vulva");
					if (Vulva != null && !"".equals(Vulva)) {
						String[] VulvaArr = Vulva.split("\\|");
						if (VulvaArr.length == 1) {
							women.put("Vulva", VulvaArr[0]);
						} else if (VulvaArr.length > 1) {
							women.put("VulvaReason", VulvaArr[1]);
						}
					}

					String Vaginal = (String) women.get("Vulva");
					if (Vaginal != null && !"".equals(Vaginal)) {
						String[] VaginalArr = Vaginal.split("\\|");
						if (VaginalArr.length == 1) {
							women.put("Vaginal", VaginalArr[0]);
						} else if (VaginalArr.length > 1) {
							women.put("VaginalReason", VaginalArr[1]);
						}
					}

					String Cervix = (String) women.get("Cervix");
					if (Cervix != null && !"".equals(Cervix)) {
						String[] CervixArr = Cervix.split("\\|");
						if (CervixArr.length == 1) {
							women.put("Cervix", CervixArr[0]);
						} else if (CervixArr.length > 1) {
							women.put("CervixReason", CervixArr[1]);
						}
					}

					String Palace = (String) women.get("Palace");
					if (Palace != null && !"".equals(Palace)) {
						String[] PalaceArr = Palace.split("\\|");
						if (PalaceArr.length == 1) {
							women.put("Vulva", PalaceArr[0]);
						} else if (PalaceArr.length > 1) {
							women.put("VulvaReason", PalaceArr[1]);
						}
					}


					String UterineAdnexa = (String) women.get("UterineAdnexa");
					if (UterineAdnexa != null && !"".equals(UterineAdnexa)) {
						String[] UterineAdnexaArr = UterineAdnexa.split("\\|");
						if (UterineAdnexaArr.length == 1) {
							women.put("Vulva", UterineAdnexaArr[0]);
						} else if (UterineAdnexaArr.length > 1) {
							women.put("UterineAdnexaReason", UterineAdnexaArr[1]);
						}
					}


				}

				// 获取症状信息，把2的幂次表示法分割为逗号隔开的数组 VaccDate
				if (((Map) map.get("master")).get("Symptom") != null) {
					Integer symptomInt = ((Integer) ((Map) map.get("master")).get("Symptom"));

					((Map) map.get("master")).put("Symptom", NumberUtils.bitand(symptomInt));
				}
				// 体检时间
				model.addAttribute("remap", remap);
				// 体检时间和随访ID ProductCode
				model.addAttribute("ProductCode", "8CBE1F526BE144419083D25720106D0E");
				model.addAttribute("listMtIdAndExamDate", listMiIdAndExamDate);
				model.addAttribute("listMtIdAndExamDateJson", JsonUtils.getJsonFormat(listMiIdAndExamDate));
				model.addAttribute("RecordOneJson", JsonUtils.getJsonFormat(map));
				System.out.println(JsonUtils.getJsonFormat(map));
			}
		}
		model.addAttribute("doctorName", vo.getDocName());
		model.addAttribute("ProductCode", vo.getProductCode());
		if("0".equals(flag)){
			
			return "/mobile/examination/inphysicalEdit";
		}
		return "/mobile/examination/physicalEdit";
	}

	/*
	 * 成人健康体检列表查询
	 */
	@RequestMapping(value = "/queryExaminationList", method = RequestMethod.GET)
	@ResponseBody
	public List examinationList(HttpServletRequest request, Model model, String regionCode, String isExamed,
								String KeyValueType, String KeyValue, String FollowUpDateS, String FollowUpDateE, String IsStandard,
								String isperson, String type, String RegionCode, String PageIndex) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 体检列表查询 8CBE1F526BE144419083D25720106D0E
		// Map<String, Object> paramMap = JsonUtils.getObjectJsonMap(dataJson);
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode", vo.getProductCode());
		// paramMap.put("ProductCode", "8CBE1F526BE144419083D25720106D0E");
		paramMap.put("PageIndex", "1");
		paramMap.put("PageSize", "10");
		paramMap.put("KeyValueType", KeyValueType);
		paramMap.put("KeyValue", KeyValue);
		paramMap.put("FollowUpDateS", FollowUpDateS);
		paramMap.put("FollowUpDateE", FollowUpDateE);
		paramMap.put("isperson", isperson);
		paramMap.put("IsStandard", IsStandard);
		paramMap.put("type", type);
		paramMap.put("RegionCode", "51");
		paramMap.put("isExamed", isExamed);
		String response = RestfulUtils.getPublicData("56-2", paramMap);

		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);

		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
		
		}
	
		List list = (List) responseMap.get("Msg");
		return list;
	}

	@RequestMapping(value = "/ExaminationTableList", method = RequestMethod.GET)
	@ResponseBody
	public String ExaminationTableList(HttpServletRequest request, Model model, String regionCode, String isExamed,
									   String KeyValueType, String KeyValue, String FollowUpDateS, String FollowUpDateE, String isperson,
									   String type, String IsStandard) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		// 获取前台额外传递过来的查询条件
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		String RegionCode = request.getParameter("RegionCode");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",vo.getProductCode());
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		paramMap.put("KeyValueType", KeyValueType);
		paramMap.put("KeyValue", KeyValue);
		paramMap.put("FollowUpDateS", FollowUpDateS);
		paramMap.put("FollowUpDateE", FollowUpDateE);
		paramMap.put("isperson", isperson);
		paramMap.put("IsStandard", IsStandard);
		paramMap.put("type", type);
		if (RegionCode == null) {
			RegionCode = "51";
		}
		paramMap.put("RegionCode", RegionCode);
		paramMap.put("isExamed", isExamed);
		String response = RestfulUtils.getPublicData("56-2", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
		}
		List<Map<String, Object>> list = (List<Map<String, Object>>) responseMap.get("Msg");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("iTotalRecords", responseMap.get("Total"));
		map.put("iTotalDisplayRecords", responseMap.get("Total"));
		map.put("recordsTotal", responseMap.get("Total"));
		map.put("recordsFiltered", responseMap.get("Total"));
		return JsonUtils.getJson(map);

	}



	/*
	 * 老年人体检查询
	 */
	@RequestMapping(value = "/OldTableList", method = RequestMethod.GET)
	@ResponseBody
	public String examinationListOld(HttpServletRequest request, String regionCode, String KeyValueType,
									 String KeyValue, String FollowUpDateS, String FollowUpDateE,
									 String RegionCode) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		Map<String, Object> paramMap = new HashMap<>();
		String IsPerfect = request.getParameter("IsPerfect");
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		// 老年人体检列表查询
		if (IsPerfect != null) {
			paramMap.put("IsPerfect", IsPerfect);
		}
		paramMap.put("PageSize", length);
		paramMap.put("PageIndex", start);
		paramMap.put("regionCode", regionCode);

		paramMap.put("KeyValueType", KeyValueType);
		paramMap.put("KeyValue", KeyValue);
		paramMap.put("FollowUpDateS", FollowUpDateS);
		paramMap.put("FollowUpDateE", FollowUpDateE);
		paramMap.put("RegionCode", RegionCode);
		paramMap.put("ProductCode", vo.getProductCode());

		String response = RestfulUtils.getPublicData("56-8", paramMap);

		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);

		if (responseMap.get("Result") == null || responseMap.get("Result").equals("0")) {
		}
		//健康指导和是否完善返回的是数字 转换
		List<Map<String, Object>> list = (List<Map<String, Object>>) responseMap.get("Msg");
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).get("PERFECT") != null && !"".equals(list.get(i).get("PERFECT"))) {
				logger.info("+++++替换前的数据PERFECT=" + list.get(i).get("PERFECT"));
				if (list.get(i).get("PERFECT").equals(0)) {

					list.get(i).put("PERFECT", "是");

				}
				if (list.get(i).get("PERFECT").equals(1)) {
					list.get(i).put("PERFECT", "否");
				}
			}
			// "GUIDANCE": "健康指导(1是1 2是2 3是4，多选就是相加)：0 无1 纳入慢性病患者健康管理 2 建议复查 3 建议转诊",
			if (list.get(i).get("GUIDANCE") != null) {
				logger.info("+++++替换前的数据GUIDANCE=" + list.get(i).get("GUIDANCE"));
				if (list.get(i).get("GUIDANCE").equals(0) || "".equals(list.get(i).get("GUIDANCE"))) {
					list.get(i).put("GUIDANCE", "无");
				}
				if (list.get(i).get("GUIDANCE").equals(1)) {
					list.get(i).put("GUIDANCE", "纳入慢性病患者健康管理");
				}
				if (list.get(i).get("GUIDANCE").equals(2)) {
					list.get(i).put("GUIDANCE", "建议复查");
				}
				if (list.get(i).get("GUIDANCE").equals(3)) {
					list.get(i).put("GUIDANCE", "无");
				}
				if (list.get(i).get("GUIDANCE").equals(4)) {
					list.get(i).put("GUIDANCE", "建议转诊");
				}
			}
		}

		responseMap.put("data", list);
		responseMap.remove("Msg");
		responseMap.put("iTotalRecords", responseMap.get("Total"));
		responseMap.put("iTotalDisplayRecords", responseMap.get("Total"));
		responseMap.put("recordsTotal", responseMap.get("Total"));
		responseMap.put("recordsFiltered", responseMap.get("Total"));
		return JsonUtils.getJson(responseMap);
	}


	/*
	 * 体检报告
	 * 
	 */
	@RequestMapping(value = "/examinationReport", method = RequestMethod.POST)
	public String examinationReport(HttpServletRequest request, Model model, String personId, String MtID) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		// 查询个人基本信息personId
		PersonInformationEntity personInformation = personInformationService.getPersonInformationByPesronId(personId);
		model.addAttribute("personInformation", personInformation);

		// 查询生活方式
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", vo.getProductCode());
		paramMap.put("MtID", MtID);

		String response = RestfulUtils.getPublicData("56-4", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);

		if (responseMap.get("Result") != null || (Integer) responseMap.get("Result") != 0) {

			Map<String, Object> map2 = new HashMap<String, Object>();
			Map<String, Object> maplifeStyle = new HashMap<String, Object>();
			map2 = (Map<String, Object>) responseMap.get("Msg");
			maplifeStyle = (Map<String, Object>) map2.get("lifeStyle");
			model.addAttribute("lifeStyle", maplifeStyle);
		}
		// 体检报告页面
		return null;

	}
	/*
	 * 保存体检报告
	 * 
	 */

	@RequestMapping(value = "/saveExaminationReport", method = RequestMethod.POST)
	@ResponseBody
	public String saveExaminationReport(HttpServletRequest request, Model model, String dateJson) {

		// TODO
		return null;

	}
	/*
	 * 查看体检报告
	 * 
	 */

	@RequestMapping(value = "/getExaminationReport", method = RequestMethod.GET)
	public String getExaminationReport(HttpServletRequest request, Model model, String personId) {

		// TODO
		return null;

	}

	/*
	 * 删除体检
	 * 
	 */
	@RequestMapping(value = "/deletExamination", method = RequestMethod.POST)
	@ResponseBody
	public String deletExamination(HttpServletRequest request, Model model, String MtID) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode", vo.getProductCode());
		paramMap.put("MtID", MtID);

		String response = RestfulUtils.getPublicData("56-5", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);

		return JsonUtils.getJson(BaseJsonVo.success(responseMap.get("Msg")));
	}

	/*
	 *
	 * 个人体检列表查询
	 *
	  */
	@RequestMapping(value = "/personExamination", method = RequestMethod.GET)
	@ResponseBody
	public String personExamination(HttpServletRequest request, Model model, String PersonID) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		String idCard = request.getParameter("idCard");
		paramMap.put("PersonID", PersonID);
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		if (idCard != null) {
			PersonInformationEntity pInfo = new PersonInformationEntity();
			pInfo.setIdCard(idCard);
			PersonInformationVo pvo = personInformationService.getPersonInfoByIdCard(pInfo);
			if (pvo != null) {
				String personID = pvo.getPersonId();
				if (personID != null) {
					paramMap.put("PersonID", personID);
				}
			}
		}
		paramMap.put("ProductCode", vo.getProductCode());

		paramMap.put("PageSize", length);
		paramMap.put("PageIndex", start);

		String response = RestfulUtils.getPublicData("56-1", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
		}

		responseMap.put("data", responseMap.get("Msg"));
		responseMap.remove("Msg");
		responseMap.put("iTotalRecords", responseMap.get("Total"));
		responseMap.put("iTotalDisplayRecords", responseMap.get("Total"));
		responseMap.put("recordsTotal", responseMap.get("Total"));
		responseMap.put("recordsFiltered", responseMap.get("Total"));
		return JsonUtils.getJson(responseMap);

	}

	/*
	 * 编辑 老年人 体检
	 *
	 */
	@RequestMapping(value = "/updateExaminationOld", method = RequestMethod.GET)
	public String updateExamnationOld(HttpServletRequest request, Model model, String radte) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        model.addAttribute("Json", JsonUtils.getJsonFormat(radte));
        model.addAttribute("productCode",vo.getProductCode() );
		Map remap = JsonUtils.getSingleJsonMap(radte);
		if(remap==null){
		}
		//对remap的建进行替换  GenderCode
				if(remap.containsKey("ID")){
					remap.put("PERSONID",remap.get("ID"));
				}
				if(remap.containsKey("Name")){
					remap.put("NAME",remap.get("Name"));
				}
				if(remap.containsKey("CardID")){
					remap.put("CARD_ID",remap.get("CardID"));
				}
				if(remap.containsKey("Age")){
					remap.put("AGE",remap.get("Age"));
				}
				
				if(remap.containsKey("Telphone")){
					remap.put("PERSON_TEL",remap.get("Telphone"));
				}
				if(remap.containsKey("PersonCode")){
					remap.put("PERSON_CODE",remap.get("PersonCode"));
				}
				if(remap.containsKey("GenderCode")){
					remap.put("GENDER",remap.get("GenderCode"));
				}
		String flag=(String) remap.get("flag");
		String PersonID =null;
		System.out.println(remap);
		if(remap.containsKey("ID")){
			PersonID= (String) remap.get("ID");
		}
		if(remap.containsKey("PERSONID")){
		PersonID= (String) remap.get("PERSONID");
		}
		String ExamDate=(String) remap.get("FOLLOW_UP_DATE");
		String upExamDate=null;
		model.addAttribute("PersonID", PersonID);
		// 查体检日期 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", vo.getProductCode());
		paramMap.put("PersonID", PersonID);
		String response = RestfulUtils.getPublicData("56-6", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		List listExamDate = new ArrayList<>();
		List<Map<String, Object>> listMiIdAndExamDate = new ArrayList<Map<String, Object>>();
		if(responseMap.get("Result") != null && (Integer) responseMap.get("Result") != 0) {
			listMiIdAndExamDate = (List<Map<String, Object>>) responseMap.get("Msg");
			if (listMiIdAndExamDate.size() > 0 && listMiIdAndExamDate != null) {
				for (Map<String, Object> map : listMiIdAndExamDate) {
					String examDate = (String) map.get("ExamDate");
					listExamDate.add(examDate);
				}
			}
			if(ExamDate==null){
				if (listExamDate.size() > 0 && listExamDate != null) {
					ExamDate=(String) listExamDate.get(0);
				}
			}
			//personId查询居民信息
			PersonInformationEntity personInformationVo=personInformationService.getPersonInformationByPesronId(PersonID);
			Date date1=personInformationVo.getDateOfBirth();
			try {
				Date date2=format.parse(ExamDate);
		    String age=String.valueOf(getAge(date1, date2)) ;
		    model.addAttribute("ExamAge",age);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			if(personInformationVo.getSex().equals("1")){
				personInformationVo.setSex("男");
			}else if(personInformationVo.getSex().equals("2")){
				personInformationVo.setSex("女");
			}else{
				personInformationVo.setSex("未知");
			}
			
			model.addAttribute("pseonInfo", personInformationVo);
			System.out.println("pseonInfo"+personInformationVo);
			model.addAttribute("pseonInfoJson",JsonUtils.getJsonFormat( JsonUtils.getJsonFormat(personInformationVo)));
			System.out.println("pseonInfo"+JsonUtils.getJsonFormat(personInformationVo));
			
			//上一次体检时间
			if(listExamDate.lastIndexOf(ExamDate)+1==(listExamDate.size())){
				 upExamDate=(String) listExamDate.get(listExamDate.lastIndexOf(ExamDate));
			}else{
			 upExamDate=(String) listExamDate.get(listExamDate.lastIndexOf(ExamDate)+1);}
			
			// 通过PersonID日期获取最后一次体检信息 获取居民信息
		   Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("ProductCode", vo.getProductCode());
			paramMap2.put("PersonID", PersonID);
			if (listExamDate.size() > 0 && listExamDate != null) {
				paramMap2.put("ExamDate", listExamDate.get(0));
			}
			
			String response2 = RestfulUtils.getPublicData("56-7", paramMap2);
			Map<String, Object> responseMap2 = JsonUtils.getObjectJsonMap(response2);
			Map<String, Object> mapLastExamination = new HashMap<String, Object>();
			if (responseMap2.get("Result") != null && (Integer) responseMap2.get("Result") != 0) {

				mapLastExamination = (Map<String, Object>) responseMap2.get("Msg");
			}
			model.addAttribute("lastExamination", mapLastExamination);
			model.addAttribute("lastExaminationJson", JsonUtils.getJsonFormat(mapLastExamination));

			// 通过随访id 56-4 查询个人健康体检记录
			Map<String, Object> paramMap3 = new HashMap<String, Object>();
			paramMap3.put("ProductCode", vo.getProductCode());
			
			String MtId = null;
			String upMtId=null;
			for (Map<String, Object> map : listMiIdAndExamDate) {
				
			   if(ExamDate.equals(map.get("ExamDate"))){
				   MtId=(String) map.get("MtId");
			   }
			   if(upExamDate.equals(map.get("ExamDate"))){
				 upMtId=(String) map.get("MtId");
			   }
			}
			
			//上次体检结果
			Map<String, Object> paramMap4 = new HashMap<String, Object>();
			paramMap4.put("ProductCode", vo.getProductCode());
			paramMap4.put("MtID", upMtId);
			String response4 = RestfulUtils.getPublicData("56-4", paramMap4);
			Map<String, Object> responseMap4 = JsonUtils.getObjectJsonMap(response4);
			if (responseMap4.get("Result") != null || (Integer) responseMap4.get("Result") != 0) {
				
				Map<String, Object> map4 = new HashMap<String, Object>();
				map4 = (Map<String, Object>) responseMap4.get("Msg");
				if (((Map<String, Object>) map4.get("master")).get("ExamDate") != null
						&& !"".equals(((Map<String, Object>) map4.get("master")).get("ExamDate"))) {
					String adate = (String) (((Map<String, Object>) map4.get("master")).get("ExamDate"));
					String regEx = "[^0-9]";
					Pattern p = Pattern.compile(regEx);
					Matcher m = p.matcher(adate);
					Long ExamDateLong = Long.parseLong(m.replaceAll("").trim());
					String examDate = format.format(new Date(ExamDateLong));
					map4.put("ExamDate", examDate);
					((Map<String, Object>) map4.get("master")).put("ExamDate", examDate);
				}
               model.addAttribute("upResult", map4);
			}
			
	
			paramMap3.put("MtID", MtId);
			//日期切换 体检结果
			String response3 = RestfulUtils.getPublicData("56-4", paramMap3);
			Map<String, Object> responseMap3 = JsonUtils.getObjectJsonMap(response3);

			if (responseMap3.get("Result") != null || (Integer) responseMap3.get("Result") != 0) {

				Map<String, Object> map = new HashMap<String, Object>();
				map = (Map<String, Object>) responseMap3.get("Msg");
				// 获取随访时间数据，把返回的"Date(时间戳)"的数据格式改成yyyy-MM-dd的时间格式

				if (((Map<String, Object>) map.get("master")).get("ExamDate") != null
						&& !"".equals(((Map<String, Object>) map.get("master")).get("ExamDate"))) {
					String adate = (String) (((Map<String, Object>) map.get("master")).get("ExamDate"));
					String regEx = "[^0-9]";
					Pattern p = Pattern.compile(regEx);
					Matcher m = p.matcher(adate);
					Long ExamDateLong = Long.parseLong(m.replaceAll("").trim());
					String examDate = format.format(new Date(ExamDateLong));
					map.put("ExamDate", examDate);
					((Map<String, Object>) map.get("master")).put("ExamDate", examDate);
				}

				// 去除时间
				if (((List<Map>) map.get("vaccList")) != null && !"".equals(((List<Map>) map.get("vaccList")))) {
					for (int i = 0; i < ((List<Map>) map.get("vaccList")).size(); i++) {
						if (((List<Map>) map.get("vaccList")).get(i).get("VaccDate") != null) {
							((List<Map>) map.get("vaccList")).get(i).remove("VaccDate");
						}
						if (((List<Map>) map.get("vaccList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("vaccList")).get(i).remove("ExamDate");
						}
					}
				}
				//
				if (((List<Map>) map.get("drugUseList")) != null && !"".equals(((List<Map>) map.get("drugUseList")))) {
					for (int i = 0; i < ((List<Map>) map.get("drugUseList")).size(); i++) {
						if (((List<Map>) map.get("drugUseList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("drugUseList")).get(i).remove("ExamDate");
						}
					}
				}

				//
				if (((List<Map>) map.get("hosList")) != null && !"".equals(((List<Map>) map.get("hosList")))) {
					for (int i = 0; i < ((List<Map>) map.get("hosList")).size(); i++) {
						if (((List<Map>) map.get("hosList")).get(i).get("ExamDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("ExamDate");
						}
						if (((List<Map>) map.get("hosList")).get(i).get("InDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("InDate");
						}
						if (((List<Map>) map.get("hosList")).get(i).get("OutDate") != null) {
							((List<Map>) map.get("hosList")).get(i).remove("OutDate");
						}
					}
				}
				
				//分隔符处理 脏器功能回显 BreathSounds

				Map organ = (Map) map.get("organ");
				if (map.get("organ") != null) {
					String Fundus = (String) organ.get("Fundus");
					if (Fundus != null && !"".equals(Fundus)) {
						String[] FundusArr = Fundus.split("\\|");
						if (FundusArr.length == 1) {
							organ.put("Fundus", FundusArr[0]);
						} else if (FundusArr.length > 1) {
							organ.put("FundusReason", FundusArr[1]);
						}
					}

					String BreathSounds = (String) organ.get("BreathSounds");
					if (BreathSounds != null && !"".equals(BreathSounds)) {
						String[] BreathSoundsArr = BreathSounds.split("\\|");
						if (BreathSoundsArr.length == 1) {
							organ.put("BreathSounds", BreathSoundsArr[0]);
						} else if (BreathSoundsArr.length > 1) {
							organ.put("BreathSoundsReason", BreathSoundsArr[1]);
						}
					}


					String HeartMurmur = (String) organ.get("HeartMurmur");
					if (HeartMurmur != null && !"".equals(HeartMurmur)) {
						String[] HeartMurmurArr = HeartMurmur.split("\\|");
						if (HeartMurmurArr.length == 1) {
							organ.put("HeartMurmur", HeartMurmurArr[0]);
						} else if (HeartMurmurArr.length > 1) {
							organ.put("HeartMurmurReason", HeartMurmurArr[1]);
						}
					}

					String AbdominalTenderness = (String) organ.get("AbdominalTenderness");
					if (AbdominalTenderness != null && !"".equals(AbdominalTenderness)) {
						String[] AbdominalTendernessArr = AbdominalTenderness.split("\\|");
						if (AbdominalTendernessArr.length == 1) {
							organ.put("AbdominalTenderness", AbdominalTendernessArr[0]);
						} else if (AbdominalTendernessArr.length > 1) {
							organ.put("AbdominalTendernessReason", AbdominalTendernessArr[1]);
						}
					}


					String AbdominalMass = (String) organ.get("AbdominalMass");
					if (AbdominalMass != null && !"".equals(AbdominalMass)) {
						String[] AbdominalMassArr = AbdominalMass.split("\\|");
						if (AbdominalMassArr.length == 1) {
							organ.put("AbdominalMass", AbdominalMassArr[0]);
						} else if (AbdominalMassArr.length > 1) {
							organ.put("AbdominalMassReason", AbdominalMassArr[1]);
						}
					}

					String TheAbdomenLiver = (String) organ.get("TheAbdomenLiver");
					if (TheAbdomenLiver != null && !"".equals(TheAbdomenLiver)) {
						String[] TheAbdomenLiverArr = TheAbdomenLiver.split("\\|");
						if (TheAbdomenLiverArr.length == 1) {
							organ.put("TheAbdomenLiver", TheAbdomenLiverArr[0]);
						} else if (TheAbdomenLiverArr.length > 1) {
							organ.put("TheAbdomenLiverReason", TheAbdomenLiverArr[1]);
						}
					}


					String Splenomegaly = (String) organ.get("Splenomegaly");
					if (Splenomegaly != null && !"".equals(Splenomegaly)) {
						String[] SplenomegalyArr = Splenomegaly.split("\\|");
						if (SplenomegalyArr.length == 1) {
							organ.put("Splenomegaly", SplenomegalyArr[0]);
						} else if (SplenomegalyArr.length > 1) {
							organ.put("TheAbdomenLiverReason", SplenomegalyArr[1]);
						}
					}


					String ShiftingDullness = (String) organ.get("ShiftingDullness");
					if (ShiftingDullness != null && !"".equals(ShiftingDullness)) {
						String[] ShiftingDullnessArr = ShiftingDullness.split("\\|");
						if (ShiftingDullnessArr.length == 1) {
							organ.put("ShiftingDullness", ShiftingDullnessArr[0]);
						} else if (ShiftingDullnessArr.length > 1) {
							organ.put("ShiftingDullnessReason", ShiftingDullnessArr[1]);
						}
					}

				}
				////分隔符处理 妇科
				Map women = (Map) map.get("women");
				if (map.get("organ") != null && "".equals(map.get("organ"))) {

					String Vulva = (String) women.get("Vulva");
					if (Vulva != null && !"".equals(Vulva)) {
						String[] VulvaArr = Vulva.split("\\|");
						if (VulvaArr.length == 1) {
							women.put("Vulva", VulvaArr[0]);
						} else if (VulvaArr.length > 1) {
							women.put("VulvaReason", VulvaArr[1]);
						}
					}

					String Vaginal = (String) women.get("Vulva");
					if (Vaginal != null && !"".equals(Vaginal)) {
						String[] VaginalArr = Vaginal.split("\\|");
						if (VaginalArr.length == 1) {
							women.put("Vaginal", VaginalArr[0]);
						} else if (VaginalArr.length > 1) {
							women.put("VaginalReason", VaginalArr[1]);
						}
					}

					String Cervix = (String) women.get("Cervix");
					if (Cervix != null && !"".equals(Cervix)) {
						String[] CervixArr = Cervix.split("\\|");
						if (CervixArr.length == 1) {
							women.put("Cervix", CervixArr[0]);
						} else if (CervixArr.length > 1) {
							women.put("CervixReason", CervixArr[1]);
						}
					}

					String Palace = (String) women.get("Palace");
					if (Palace != null && !"".equals(Palace)) {
						String[] PalaceArr = Palace.split("\\|");
						if (PalaceArr.length == 1) {
							women.put("Vulva", PalaceArr[0]);
						} else if (PalaceArr.length > 1) {
							women.put("VulvaReason", PalaceArr[1]);
						}
					}


					String UterineAdnexa = (String) women.get("UterineAdnexa");
					if (UterineAdnexa != null && !"".equals(UterineAdnexa)) {
						String[] UterineAdnexaArr = UterineAdnexa.split("\\|");
						if (UterineAdnexaArr.length == 1) {
							women.put("Vulva", UterineAdnexaArr[0]);
						} else if (UterineAdnexaArr.length > 1) {
							women.put("UterineAdnexaReason", UterineAdnexaArr[1]);
						}
					}


				}


				// 获取症状信息，把2的幂次表示法分割为逗号隔开的数组 VaccDate
				if (((Map) map.get("master")).get("Symptom") != null) {
					Integer symptomInt = ((Integer) ((Map) map.get("master")).get("Symptom"));

					((Map) map.get("master")).put("Symptom", NumberUtils.bitand(symptomInt));
				}
				// 体检时间
				model.addAttribute("remap", remap);
				// 体检时间和随访ID ProductCode
				model.addAttribute("ProductCode", "8CBE1F526BE144419083D25720106D0E");
				model.addAttribute("listMtIdAndExamDate", listMiIdAndExamDate);
				model.addAttribute("listMtIdAndExamDateJson", JsonUtils.getJsonFormat(listMiIdAndExamDate));
				model.addAttribute("RecordOneJson", JsonUtils.getJsonFormat(map));
				System.out.println(JsonUtils.getJsonFormat(map));
			}
		}
		model.addAttribute("doctorName", vo.getDocName());
		model.addAttribute("ProductCode", vo.getProductCode());
        if("0".equals(flag)){
        	return "/mobile/examination/inphysicalEditOld";
        }
		return "/mobile/examination/physicalEditOld";
	}
	
	public static String getSymptom(String str){
		Map<String, String> mapSymptom = new HashMap<String, String>();
		String result="";
		String str1="无症状,头痛,头晕,心悸,胸闷,胸痛,咳痰,呼吸困难,多饮,多尿,体重下降,乏力,关节肿痛,视力模糊,手脚麻木,尿急,尿痛,便秘,腹泻,恶心呕吐,烟花,耳鸣,乳房胀痛,其他";
		String a[]=str1.split(",");
		for (int i=0;i<a.length;i++) {
			mapSymptom.put(String.valueOf((int)Math.pow(2,i)),a[i]);
		}
		
		String b[]=str.split(",");
		 System.out.println(b[0]);
		for (int i=0;i<b.length;i++) {
		result=result+" "+mapSymptom.get(b[i]);
		}
		return result;
	}
	private  int getAge(Date birthday,Date date){
		Calendar c1 = Calendar.getInstance();  
		c1.setTime(date);
		long nowmillSeconds = c1.getTimeInMillis();  
		
		Calendar c2 = Calendar.getInstance();  
		c2.setTime(birthday);  
		long birmillSeconds = c2.getTimeInMillis();  
		
		Calendar c3 = Calendar.getInstance();  
		long millis = nowmillSeconds - birmillSeconds;  
		c3.setTimeInMillis(millis);  
		
		int year = c3.get(Calendar.YEAR);  
		int month = c3.get(Calendar.MONTH);  
		int day = c3.get(Calendar.DAY_OF_MONTH);  
		int hour = c3.get(Calendar.HOUR_OF_DAY);  
		if (year > 1970) {  
		    return year - 1970;  
		}
		return year;
		
	}
	
}