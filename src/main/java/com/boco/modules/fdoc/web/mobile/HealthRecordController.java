package com.boco.modules.fdoc.web.mobile;

 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.aspectj.weaver.ast.Var;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.NumberUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.modules.fdoc.model.DiseaseDictionaryEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.service.DiseaseDictionaryService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
 
@Controller
@RequestMapping(value="/mobile/healthRecord",produces = "application/json;charset=UTF-8")
public class HealthRecordController {
	@Resource
	DiseaseDictionaryService diseaseService;
	@Resource
	PersonInformationService personInformationService;
	@Resource
	DocUserService docUserService;
	
	/**
	 * 家庭医生访问地址
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fdoctorIndex", method = RequestMethod.GET)
	public String fdoctorIndex(HttpServletRequest request, Model model) {
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
			String userName = (String) androidDataMap.get("userName");
			request.getSession().setAttribute(BusinessConstants.USERNAME_IN_SESSION, userName);
			model.addAttribute("androidDataMap", JsonUtils.getJsonFormat(androidDataMap));
		 }
		return  "/mobile/index";
		
	}
	
	/**
	 * 
	 * showMenu:(展示左侧菜单栏). <br/>
	 * @author q
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showMenu", method = RequestMethod.GET)
	public String showMenu(HttpServletRequest request, Model model, String androidDataMap) {
		model.addAttribute("androidDataMap", androidDataMap);
		System.out.println(androidDataMap);
		return  "/mobile/leftMenu";
		
	}
	/**
	 * 居民档案首页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/healthIndex", method = RequestMethod.GET)
	public String queryindex(HttpServletRequest request, Model model) {
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
			model.addAttribute("androidDataMap", JsonUtils.getJsonFormat(androidDataMap));
		 }
		return  "/mobile/healthRecord/healthRecordIndex";
		
	}
	/**
	 * 首页查询个人
	 * @param request
	 * @param model
	 * @param dataJson
	 * @param PersonID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/readindex",method=RequestMethod.GET)	 
	public String readindex(HttpServletRequest request,Model model, String dataJson, String PersonID,String userName){
		//获取前台额外传递过来的查询条件  
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String start = request.getParameter("start");  
		String length = request.getParameter("length");
		String idCard1="";
	    DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		if(PersonID!=null){
			PersonInformationEntity pInfo = new PersonInformationEntity();
			pInfo= personInformationService.getPersonInformationByPesronId(PersonID);
			if(pInfo!=null){
			idCard1=pInfo.getIdCard();
			System.out.println(pInfo.getPersonName());
			}else{
				Map<String, Object> idMap = new HashMap<String, Object>();
				//String ProductCode=PropertiesUtils.getValue("produce_code");
				//idMap.put("ProductCode", ProductCode);
				idMap.put("ProductCode", vo.getProductCode());
				idMap.put("ID", PersonID);
				String idresponse = RestfulUtils.getPublicData("55-10", idMap);
				System.out.println(idresponse);
				Map<String, Object> idresponseMap = JsonUtils.getObjectJsonMap(idresponse);
				Map<String, Object> idmsgMap=(Map<String, Object>) idresponseMap.get("Msg");
				idCard1=(String) idmsgMap.get("CardID");
			}
		}
		if (idCard1 == null || "".equals(idCard1)) {
			return null;
		}else{
			paramMap.put("KeyValue", idCard1);
		}      
       // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
        paramMap.put("ProductCode", vo.getProductCode());
		String 	RegionCode="51";
		paramMap.put("KeyCode", "2");
		paramMap.put("RegionCode", RegionCode);
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);	 
		String response = RestfulUtils.getPublicData("55-12", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		List<Map<String, Object> > list=(List<Map<String, Object> > )responseMap.get("Msg");
		Map<String, Object> map = JsonUtils.getObjectJsonMap(response); 
		map.put("data",list);
		map.put("iTotalRecords", responseMap.get("Total"));
		map.put("iTotalDisplayRecords",responseMap.get("Total"));
		map.put("recordsTotal",responseMap.get("Total"));
		map.put("recordsFiltered",responseMap.get("Total"));
		return JsonUtils.getJson(map);
	}
	/**
	 * 居民档案浏览
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/personRecordbrowse", method = RequestMethod.GET)
	public String personRecordbrowse(HttpServletRequest request, Model model) {
		String androidData= request.getParameter("androidData");
		String userName= request.getParameter("userName");
	    DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		if(androidData!=null){
			Map androidDataMap=JsonUtils.getSingleJsonMap(androidData);
			if(androidDataMap.get("personId")==null||"".equals(androidDataMap.get("personId"))){
				androidDataMap.put("personId", "123");
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
		  //  paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		    paramMap.put("ProductCode", vo.getProductCode());
		   // paramMap.put("ID", "00008597-45eb-402d-9462-20acf97daaff");
		    paramMap.put("ID",androidDataMap.get("personId"));
			String response = RestfulUtils.getPublicData("55-10", paramMap);
			Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		    Map<String, Object>  list=   (Map<String, Object>) responseMap.get("Msg");
		    model.addAttribute("data", list);
		    //性别处理
		    String GenderCode= (String) list.get("GenderCode");
		    if("0".equalsIgnoreCase(GenderCode)){
		    	model.addAttribute("Gender", "未知");
		    }else if("1".equalsIgnoreCase(GenderCode)){
		    	model.addAttribute("Gender", "男");
		    }else if("2".equalsIgnoreCase(GenderCode)){
		    	model.addAttribute("Gender", "女");
		    }else if("9".equalsIgnoreCase(GenderCode)){
		    	model.addAttribute("Gender", "未说明");
		    }
		    //年龄处理
		    String BirthDay =(String) list.get("BirthDay");
		    String[] BirthDayList =BirthDay.split("T");
		    SimpleDateFormat bDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    try {
				Date bDate=bDateFormat.parse(BirthDayList[0]);
				Date nDate = new Date();
				int year = nDate.getYear()-bDate.getYear();
				model.addAttribute("year", year);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //药物过敏处理 
			Integer DrugAllergyHistory =  (Integer) list.get("DrugAllergyHistory");		 		
			String DrugAllergyHistorystr = NumberUtils.bitand(DrugAllergyHistory);
			String[] DrugAllergyHistoryList =DrugAllergyHistorystr.split(",");
			StringBuffer dah = new StringBuffer();
			for(int i=0;i<DrugAllergyHistoryList.length;i++){
				String dString =DrugAllergyHistoryList[i];
				if("1".equalsIgnoreCase(dString)){
					dah.append("无");
				}else if("2".equalsIgnoreCase(dString)){
					dah.append("青霉素,");
				}else if("4".equalsIgnoreCase(dString)){
					dah.append("磺胺,");
				}else if("8".equalsIgnoreCase(dString)){
					dah.append("链霉素,");
				}  
			}
			//其它过敏史
			String OtherDrugAllergyHistory =(String) list.get("OtherDrugAllergyHistory");
			if(OtherDrugAllergyHistory!=null&&OtherDrugAllergyHistory!=""){
				dah.append(OtherDrugAllergyHistory);
			}
			model.addAttribute("DrugAllergyHistorystr", dah);
			//查询出疾病既往史
			StringBuffer buffer = new StringBuffer();
			List<Map<String, Object>> personDiseaseList = (List<Map<String, Object>>) list.get("CmData");
			for (Map<String, Object> personDiseaseMap : personDiseaseList) {
				String diseaseId = (String) personDiseaseMap.get("DiseaseKindID");
				DiseaseDictionaryEntity diseaseEntity = diseaseService.getDictionaryById(diseaseId.replace("-", "").toUpperCase());
				buffer.append(diseaseEntity.getDiseaseName() + "、");
			}
			String ill="";
			if(buffer.length()>0){
				ill=buffer.substring(0, buffer.length()-1);
			}
			model.addAttribute("cmData", ill);
			//随访处理
			Map<String, Object> followMap = new HashMap<String, Object>();
			//followMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
			followMap.put("ProductCode", vo.getProductCode());
			//followMap.put("PersonID", "7ACC6D3F992540E1A6B31B80B4D0C1B3");
			followMap.put("PersonID", androidDataMap.get("personId"));	 
			followMap.put("PageIndex", 0);
			followMap.put("PageSize", 10);
			String followResponse = RestfulUtils.getPublicData("58-1", followMap);
			Map<String, Object> followResponseMap = JsonUtils.getObjectJsonMap(followResponse);
		    List<Map<String, Object>>  followlist=   (List<Map<String, Object>>) followResponseMap.get("Msg");
		    model.addAttribute("followlist", JsonUtils.getJsonFormat(followlist));			 
		    model.addAttribute("ProductCode", vo.getProductCode());			 
		 }
		return "/mobile/healthRecord/personRecordbrowse";

	}
	/**
	 * 测试随访回显
	 * @param ID
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/personRecordbrowseFollowup", method = RequestMethod.GET)
	public String personRecordbrowseFollowup(String ID, Model model,String ProductCode) {
		Map<String, Object> followParamMap = new HashMap<String, Object>();
		//followParamMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		followParamMap.put("ProductCode", ProductCode);
		followParamMap.put("ID", ID);
		String followUpesponse = RestfulUtils.getPublicData("58-3", followParamMap);
		Map<String, Object> followesponseMap = JsonUtils.getObjectJsonMap(followUpesponse);
	    Map<String, Object>  lists=   (Map<String, Object>) followesponseMap.get("Msg");
	    model.addAttribute("followData", lists);
	    //症状处理
	    Integer symptom =(Integer)(((Map<String, Object>)lists.get("cmHypertension")).get("Symptom"));
		String symptomstr = NumberUtils.bitand(symptom); 
		String[] symptomList =symptomstr.split(",");
		StringBuffer symptoms = new StringBuffer();
		for(int i=0;i<symptomList.length;i++){
			if("1".equalsIgnoreCase(symptomList[i])){
				symptoms.append("无症状");
			}else if("2".equalsIgnoreCase(symptomList[i])){
				symptoms.append("头痛头晕,");
			}else if("4".equalsIgnoreCase(symptomList[i])){
				symptoms.append("恶心呕吐,");
			}else if("8".equalsIgnoreCase(symptomList[i])){
				symptoms.append("眼花耳鸣,");
			}else if("16".equalsIgnoreCase(symptomList[i])){
				symptoms.append("呼吸困难,");
			}else if("32".equalsIgnoreCase(symptomList[i])){
				symptoms.append("心悸胸闷,");
			}else if("64".equalsIgnoreCase(symptomList[i])){
				symptoms.append("鼻衄,");
			}else if("128".equalsIgnoreCase(symptomList[i])){
				symptoms.append("四肢发麻,");
			}else if("256".equalsIgnoreCase(symptomList[i])){
				symptoms.append("下肢水肿,");
			}else if("512".equalsIgnoreCase(symptomList[i])){
				List<Map<String, Object>>  otherJson=   (List<Map<String, Object>>) lists.get("otherJson");
				for(int j=0;j<otherJson.size();i++){
					String value =(String) otherJson.get(i).get("AttrName");
					if("SymptomOther".equalsIgnoreCase(value)){
						symptoms.append((String) otherJson.get(i).get("OtherText")).append(",");
					}
				}
				
			} 
		}
		if(symptoms.indexOf(",")!=-1){
			String symptomstrs=symptoms.substring(0, symptoms.length()-1);
			model.addAttribute("symptom", symptomstrs);
		}else{
			model.addAttribute("symptom", symptoms);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("followData", lists);
		if(symptoms.indexOf(",")!=-1){
			String symptomstrs=symptoms.substring(0, symptoms.length()-1);
			data.put("symptom", symptomstrs);
		}else{
			data.put("symptom", symptoms);
		}
		return JsonUtils.getJson(data);
	}
	/**
	 * 居民档案浏览 回显随访数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/personRecordbrowseFollow", method = RequestMethod.GET)
	public String personRecordbrowseFollow(String ID, Model model ) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
	    paramMap.put("ID", "00008597-45eb-402d-9462-20acf97daaff");
		String response = RestfulUtils.getPublicData("55-10", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
	    Map<String, Object>  list=   (Map<String, Object>) responseMap.get("Msg");
	    model.addAttribute("data", list);
	    //性别处理
	    String GenderCode= (String) list.get("GenderCode");
	    if("0".equalsIgnoreCase(GenderCode)){
	    	model.addAttribute("Gender", "未知");
	    }else if("1".equalsIgnoreCase(GenderCode)){
	    	model.addAttribute("Gender", "男");
	    }else if("2".equalsIgnoreCase(GenderCode)){
	    	model.addAttribute("Gender", "女");
	    }else if("9".equalsIgnoreCase(GenderCode)){
	    	model.addAttribute("Gender", "未说明");
	    }
	    //年龄处理
	    String BirthDay =(String) list.get("BirthDay");
	    String[] BirthDayList =BirthDay.split("T");
	    SimpleDateFormat bDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
			Date bDate=bDateFormat.parse(BirthDayList[0]);
			Date nDate = new Date();
			int year = nDate.getYear()-bDate.getYear();
			model.addAttribute("year", year);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //药物过敏处理 
		Integer DrugAllergyHistory =  (Integer) list.get("DrugAllergyHistory");		 		
		String DrugAllergyHistorystr = NumberUtils.bitand(DrugAllergyHistory);
		String[] DrugAllergyHistoryList =DrugAllergyHistorystr.split(",");
		StringBuffer dah = new StringBuffer();
		for(int i=0;i<DrugAllergyHistoryList.length;i++){
			String dString =DrugAllergyHistoryList[i];
			if("1".equalsIgnoreCase(dString)){
				dah.append("无");
			}else if("2".equalsIgnoreCase(dString)){
				dah.append("青霉素,");
			}else if("4".equalsIgnoreCase(dString)){
				dah.append("磺胺,");
			}else if("8".equalsIgnoreCase(dString)){
				dah.append("链霉素,");
			}  
		}
		//其它过敏史
		String OtherDrugAllergyHistory =(String) list.get("OtherDrugAllergyHistory");
		if(OtherDrugAllergyHistory!=null&&OtherDrugAllergyHistory!=""){
			dah.append(OtherDrugAllergyHistory);
		}
		model.addAttribute("DrugAllergyHistorystr", dah);
		//查询出疾病既往史
		StringBuffer buffer = new StringBuffer();
		List<Map<String, Object>> personDiseaseList = (List<Map<String, Object>>) list.get("CmData");
		for (Map<String, Object> personDiseaseMap : personDiseaseList) {
			String diseaseId = (String) personDiseaseMap.get("DiseaseKindID");
			DiseaseDictionaryEntity diseaseEntity = diseaseService.getDictionaryById(diseaseId.replace("-", "").toUpperCase());
			buffer.append(diseaseEntity.getDiseaseName() + "、");
		}
		String ill="";
		if(buffer.length()>0){
			ill=buffer.substring(0, buffer.length()-1);
		}
		model.addAttribute("cmData", ill);
		//随访处理
		Map<String, Object> followMap = new HashMap<String, Object>();
		followMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		followMap.put("PersonID", "7ACC6D3F992540E1A6B31B80B4D0C1B3");
		followMap.put("PageIndex", 0);
		followMap.put("PageSize", 10);
		String followResponse = RestfulUtils.getPublicData("58-1", followMap);
		Map<String, Object> followResponseMap = JsonUtils.getObjectJsonMap(followResponse);
	    List<Map<String, Object>>  followlist=   (List<Map<String, Object>>) followResponseMap.get("Msg");
	    model.addAttribute("followlist", JsonUtils.getJsonFormat(followlist));
		Map<String, Object> followParamMap = new HashMap<String, Object>();
		followParamMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		followParamMap.put("ID", ID); 
		String followUpesponse = RestfulUtils.getPublicData("58-3", followParamMap);
		Map<String, Object> followesponseMap = JsonUtils.getObjectJsonMap(followUpesponse);
	    Map<String, Object>  lists=   (Map<String, Object>) followesponseMap.get("Msg");
	    model.addAttribute("followData", lists);	
	    //症状处理
	    Integer symptom =(Integer)(((Map<String, Object>)lists.get("cmHypertension")).get("Symptom"));
		String symptomstr = NumberUtils.bitand(symptom); 
		String[] symptomList =symptomstr.split(",");
		StringBuffer symptoms = new StringBuffer();
		for(int i=0;i<symptomList.length;i++){
			if("1".equalsIgnoreCase(symptomList[i])){
				symptoms.append("无症状");
			}else if("2".equalsIgnoreCase(symptomList[i])){
				symptoms.append("头痛头晕,");
			}else if("4".equalsIgnoreCase(symptomList[i])){
				symptoms.append("恶心呕吐,");
			}else if("8".equalsIgnoreCase(symptomList[i])){
				symptoms.append("眼花耳鸣,");
			}else if("16".equalsIgnoreCase(symptomList[i])){
				symptoms.append("呼吸困难,");
			}else if("32".equalsIgnoreCase(symptomList[i])){
				symptoms.append("心悸胸闷,");
			}else if("64".equalsIgnoreCase(symptomList[i])){
				symptoms.append("鼻衄,");
			}else if("128".equalsIgnoreCase(symptomList[i])){
				symptoms.append("四肢发麻,");
			}else if("256".equalsIgnoreCase(symptomList[i])){
				symptoms.append("下肢水肿,");
			}else if("512".equalsIgnoreCase(symptomList[i])){
				List<Map<String, Object>>  otherJson=   (List<Map<String, Object>>) lists.get("otherJson");
				for(int j=0;j<otherJson.size();i++){
					String value =(String) otherJson.get(i).get("AttrName");
					if("SymptomOther".equalsIgnoreCase(value)){
						symptoms.append((String) otherJson.get(i).get("OtherText")).append(",");
					}
				}
				
			} 
		}
		if(symptoms.indexOf(",")!=-1){
			String symptomstrs=symptoms.substring(0, symptoms.length()-1);
			model.addAttribute("symptom", symptomstrs);
		}else{
			model.addAttribute("symptom", symptoms);
		}
		
		return "/mobile/healthRecord/personRecordbrowse";

	}
	/**
	 * 读健康卡
	 * @param request
	 * @param model
	 * @param RegionCode
	 * @param IsStatus
	 * @param idCard
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public String read(HttpServletRequest request,Model model, String  RegionCode,String IsStatus,String idCard,String userName){
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
       // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",vo.getProductCode());
        if(RegionCode==null){
			RegionCode="51";
		}
		paramMap.put("KeyCode", "2");
		paramMap.put("KeyValue", idCard);
		paramMap.put("RegionCode", RegionCode);
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);	 
		String response = RestfulUtils.getPublicData("55-12", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response); 
		List<Map<String, Object> > list=(List<Map<String, Object> > )responseMap.get("Msg");
		Map<String, Object> map = JsonUtils.getObjectJsonMap(response); 
		map.put("data",list);
		map.put("iTotalRecords", responseMap.get("Total"));
		map.put("iTotalDisplayRecords",responseMap.get("Total"));
		map.put("recordsTotal",responseMap.get("Total"));
		map.put("recordsFiltered",responseMap.get("Total"));
		return JsonUtils.getJson(map);
	}
	/**
	 * 根据条件查询
	 * @param request
	 * @param model
	 * @param RegionCode
	 * @param IsStatus
	 * @param Gender
	 * @param KeyCode
	 * @param KeyValue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(HttpServletRequest request,Model model, String  RegionCode,String IsStatus,String Gender,String KeyCode,String KeyValue,String userName ){
		//获取前台额外传递过来的查询条件  
		System.out.println("userName:-------------"+userName);
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
       // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",vo.getProductCode());
        if(RegionCode==null){
			RegionCode="51";
		}
		paramMap.put("IsStatus", IsStatus);
		paramMap.put("Gender", Gender);
		paramMap.put("KeyCode", KeyCode);
		paramMap.put("KeyValue", KeyValue);
		paramMap.put("RegionCode", RegionCode);
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		String response = RestfulUtils.getPublicData("55-12", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response); 
		List<Map<String, Object> > list=(List<Map<String, Object> > )responseMap.get("Msg");
		Map<String, Object> map = JsonUtils.getObjectJsonMap(response); 
		map.put("data",list);
		map.put("iTotalRecords", responseMap.get("Total"));
		map.put("iTotalDisplayRecords",responseMap.get("Total"));
		map.put("recordsTotal",responseMap.get("Total"));
		map.put("recordsFiltered",responseMap.get("Total"));
		return JsonUtils.getJson(map);
	}
	
	/**
	 * 查询居民的基本信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryPeopleInfomation",method=RequestMethod.GET)
	public String queryPeopleInfomation(Model model, String dataJson,String ID,String FamilyId,String userName){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
	   // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",vo.getProductCode());
		
		paramMap.put("ID", ID);
		String response = RestfulUtils.getPublicData("55-10", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
	    Map<String, Object>  list=   (Map<String, Object>) responseMap.get("Msg");
		//性别处理
	    String GenderCode= (String) list.get("GenderCode");
	    if("0".equalsIgnoreCase(GenderCode)){
	    	model.addAttribute("Gender", "未知");
	    }else if("1".equalsIgnoreCase(GenderCode)){
	    	model.addAttribute("Gender", "男");
	    }else if("2".equalsIgnoreCase(GenderCode)){
	    	model.addAttribute("Gender", "女");
	    }else if("9".equalsIgnoreCase(GenderCode)){
	    	model.addAttribute("Gender", "未说明");
	    }
	    
	    //出生日期处理
	    String BirthDaystr= (String) list.get("BirthDay");
	    String[] BirthDayList =BirthDaystr.split("T");
	    model.addAttribute("BirthDay", BirthDayList[0]);
	    //参加工作时间
	    String WorkDate= (String) list.get("WorkDate");
	    if(WorkDate!=null&&!("".equalsIgnoreCase(WorkDate))){
	        String[] WorkDateList =WorkDate.split("T");
	  	    model.addAttribute("WorkDate", WorkDateList[0]);
	    }
	  
	    //状态变更日期
	    String HrStatusDate= (String) list.get("HrStatusDate");
	    if(HrStatusDate!=null&&!("".equalsIgnoreCase(HrStatusDate))){
	    	String[] HrStatusDateList =HrStatusDate.split("T");
	  	    model.addAttribute("HrStatusDate", HrStatusDateList[0]);
	    }    
		//医疗费用支付方式处理
	    Integer PaymentWaystring =  (Integer) list.get("PaymentWaystring");		 
		if(PaymentWaystring!=0){
			 model.addAttribute("PaymentWaystring", NumberUtils.bitand(PaymentWaystring));
		}
	   
	    //药物过敏处理 
		Integer DrugAllergyHistory =  (Integer) list.get("DrugAllergyHistory");		 		
		if(DrugAllergyHistory!=0){
			model.addAttribute("DrugAllergyHistory", NumberUtils.bitand(DrugAllergyHistory));
		}
		
		//档案信息卡
		String DrugAllergyHistorystr = NumberUtils.bitand(DrugAllergyHistory);
		String[] DrugAllergyHistoryList =DrugAllergyHistorystr.split(",");
		StringBuffer dah = new StringBuffer();
		for(int i=0;i<DrugAllergyHistoryList.length;i++){
			String dString =DrugAllergyHistoryList[i];
			if("1".equalsIgnoreCase(dString)){
				dah.append("无");
			}else if("2".equalsIgnoreCase(dString)){
				dah.append("青霉素,");
			}else if("4".equalsIgnoreCase(dString)){
				dah.append("磺胺,");
			}else if("8".equalsIgnoreCase(dString)){
				dah.append("链霉素,");
			}  
		}
		//其它过敏史
		String OtherDrugAllergyHistory =(String) list.get("OtherDrugAllergyHistory");
		if(OtherDrugAllergyHistory!=null&&OtherDrugAllergyHistory!=""){
			dah.append(OtherDrugAllergyHistory);
		}
		model.addAttribute("DrugAllergyHistorystr", dah);
		//暴露史处理
		Integer ExposureHistory =  (Integer) list.get("ExposureHistory");	
		if(ExposureHistory!=0){
			model.addAttribute("ExposureHistory",NumberUtils.bitand(ExposureHistory));	
		}
		//家族史
		List<Map<String, Object> > FamilyHistoryList=(List<Map<String, Object> > )list.get("FamilyHistory");
		if(FamilyHistoryList.size()>0){
			for(int i=0;i<FamilyHistoryList.size();i++){
				Integer key =(Integer) FamilyHistoryList.get(i).get("RelationshipType");
				Integer Disease =  (Integer) FamilyHistoryList.get(i).get("Disease");
				if(1==key){ 
					model.addAttribute("fillness",NumberUtils.bitand(Disease));	
				}else if(2==key){		 
					model.addAttribute("millness",NumberUtils.bitand(Disease));	
				}else if(3==key){		 
					model.addAttribute("billness",NumberUtils.bitand(Disease));	
				}else if(4==key){	
					model.addAttribute("sillness",NumberUtils.bitand(Disease));	
				}
			}
		}
		//疾病处理
		List<Map<String, Object> > CmDataList=(List<Map<String, Object> > )list.get("CmData");
		model.addAttribute("CmDataList", JsonUtils.getJsonFormat(CmDataList));
		//手术,外伤,输血处理
		List<Map<String, Object> > flist=(List<Map<String, Object> > )list.get("HealthHistory");
		model.addAttribute("flist", JsonUtils.getJsonFormat(flist));
	    //残疾情况处理
		Integer Disability =  (Integer) list.get("Disability");		 
		if(Disability!=0){
			model.addAttribute("Disability", NumberUtils.bitand(Disability));
		}
		model.addAttribute("data", list);
		//model.addAttribute("ProductCode", PropertiesUtils.getValue("produce_code"));
		model.addAttribute("ProductCode", vo.getProductCode());
		//查询出疾病既往史
		StringBuffer buffer = new StringBuffer();
		List<Map<String, Object>> personDiseaseList = (List<Map<String, Object>>) list.get("CmData");
		for (Map<String, Object> personDiseaseMap : personDiseaseList) {
			String diseaseId = (String) personDiseaseMap.get("DiseaseKindID");
			DiseaseDictionaryEntity diseaseEntity = diseaseService.getDictionaryById(diseaseId.replace("-", "").toUpperCase());
			buffer.append(diseaseEntity.getDiseaseName() + "、");
		}
		String ill="";
		if(buffer.length()>0){
			ill=buffer.substring(0, buffer.length()-1);
		}
		model.addAttribute("cmData", ill);
 
		//查询户主地址
		Map<String, Object> pMap = new HashMap<String, Object>();
		//pMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		pMap.put("ProductCode", vo.getProductCode());
		pMap.put("FamilyID", FamilyId);
		String responses = RestfulUtils.getPublicData("54-7", pMap);
		Map<String, Object> rMap = JsonUtils.getObjectJsonMap(responses);
	    String add=   (String) rMap.get("Msg");
		model.addAttribute("add", add);
 
		return "/mobile/healthRecord/editPeopleInformation";
	}
	/**
	 * 查询家庭的基本信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryFamilyInfomation",method=RequestMethod.GET)
	public String queryFamilyInfomation(Model model, String dataJson,String ID,String FamilyId,String userName){
		Map<String, Object> paramMap = new HashMap<String, Object>();
	   // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode", vo.getProductCode());
	    paramMap.put("FamilyID", FamilyId);
		String response = RestfulUtils.getPublicData("54-4", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		 Map<String, Object>  list=   (Map<String, Object>) responseMap.get("Msg");
		System.out.println(list);
		model.addAttribute("data", list);
		//查询家庭成员
		Map<String, Object> pMap = new HashMap<String, Object>();
		//pMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));	     
		pMap.put("ProductCode", vo.getProductCode());	     
		pMap.put("FamilyId", FamilyId);
		String fs = RestfulUtils.getPublicData("54-3", pMap);
		Map<String, Object> fMap = JsonUtils.getObjectJsonMap(fs);
		List<Map<String, Object> > flist=(List<Map<String, Object> > )fMap.get("Msg");
		model.addAttribute("flist", JsonUtils.getJsonFormat(flist));
		//model.addAttribute("ProductCode", PropertiesUtils.getValue("produce_code"));
		model.addAttribute("ProductCode", vo.getProductCode());
 
		return "/mobile/healthRecord/editFamilyInfomation";
	}
	
	/**
	 * 修改家庭档案
	 */
	@ResponseBody
	@RequestMapping(value="/updateFamilyInfomation",method=RequestMethod.POST)//
	public String updateFamilyInfomation(Model model, String saveParam){ 
		Map  map=JsonUtils.getSingleJsonMap(saveParam);	
		String response = RestfulUtils.getPublicData("54-2", map);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NULL_DATA_CODE.getKey(), ApiStatusEnum.NULL_DATA_CODE.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(""));
	}
	 
	/**
	 * 编辑居民档案
	 * @param model
	 * @param dataJson
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateResidentHealthRecord",method=RequestMethod.POST)
	public String updateResidentHealthRecord(Model model, String saveParam){
		Map  map=JsonUtils.getSingleJsonMap(saveParam);
    	
		String response = RestfulUtils.getPublicData("55-15", map);
 
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);

		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NULL_DATA_CODE.getKey(), ApiStatusEnum.NULL_DATA_CODE.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(""));			 
			 
	} 
 
}
