package com.boco.modules.fdoc.web.mobile;

 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.InterfaceUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.NumberUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.modules.fdoc.common.Page;
import com.boco.modules.fdoc.model.DiseaseDictionaryEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SysRegionEntity;
import com.boco.modules.fdoc.service.DiseaseDictionaryService;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SysRegionService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
 
@Controller
@RequestMapping(value="/mobile/hypertension",produces = "application/json;charset=UTF-8")
public class HypertensionController {
	@Resource
	DiseaseDictionaryService diseaseService;
	@Resource
	SysRegionService sysRegionService ;
	@Resource
	PersonInformationService personInformationService;
	@Resource
	DocService docService;
	@Resource
	DocUserService docUserService;
	/**
	 * 高血压首页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryindex", method = RequestMethod.GET)
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
		return  "/mobile/hypertension/index7";		
	}
	/**
	 * PersonID查询个人慢病，读取健康卡
	 * @param request
	 * @param model
	 * @param dataJson
	 * @param RegionCode
	 * @param KeyValue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/read",method=RequestMethod.GET)	 
	public String read(HttpServletRequest request,Model model, String userName, String idCard){
		//获取前台额外传递过来的查询条件  
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String start = request.getParameter("start");  
		String length = request.getParameter("length");
		paramMap.put("KeyValue", idCard);	
        //paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode",vo.getProductCode());
        String  RegionCode="51";
		paramMap.put("RegionCode", RegionCode);
        paramMap.put("BuildType","高血压"); 
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		String response = RestfulUtils.getPublicData("57-1", paramMap);
		System.out.println(response);
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
	 * 首页查询个人
	 * @param request
	 * @param model
	 * @param dataJson
	 * @param PersonID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/readindex",method=RequestMethod.GET)	 
	public String readindex(HttpServletRequest request,Model model,String PersonID,String userName){
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
				idMap.put("ProductCode", vo.getProductCode());
				idMap.put("ID", PersonID);
				String idresponse = RestfulUtils.getPublicData("55-10", idMap);
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
      //  paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));    
        paramMap.put("ProductCode", vo.getProductCode());    
        String  RegionCode="51";		
		paramMap.put("RegionCode", RegionCode);
        paramMap.put("BuildType","高血压"); 
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		String response = RestfulUtils.getPublicData("57-1", paramMap);
		System.out.println(response);
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
	
	//高血压随访查询开始
	@RequestMapping(value = "/followUpQuery", method = RequestMethod.GET)
	public String followUpQuery(HttpServletRequest request, Model model,String userName) {
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		model.addAttribute("ProductCode", vo.getProductCode());  
		model.addAttribute("doctorName", userName);  
 
        return  "/mobile/hypertension/followUpQuery";	
	}
	/**
	 * 添加高血压随访
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model,String userName) {
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        model.addAttribute("ProductCode", vo.getProductCode());
        return  "/mobile/hypertension/add";		
	}
	/**
	 * 查询个人高血压随访历史
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryPersonFollowUpHistory", method = RequestMethod.GET)
	public String queryPersonFollowUpHistory(HttpServletRequest request, Model model) {
		String PersonID = request.getParameter("PersonID");
		String doctorName = request.getParameter("doctorName");
		DoctorDetailVo vo =docUserService.getDoctorByUsername(doctorName);
		model.addAttribute("PersonID", PersonID);
		model.addAttribute("ProductCode", vo.getProductCode());
		model.addAttribute("doctorName", doctorName);
		return  "/mobile/hypertension/hypertensionFollowhistory";		
	}
	/**
	 * 初始化区划信息
	 * @param model
	 * @param dataJson
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/initRegionTree",method=RequestMethod.GET)
	public String  initRegionTree(Model model, String dataJson,HttpServletRequest request){
		String regionCode = request.getParameter("regionCode");
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		List<SysRegionEntity> list=sysRegionService.getChildrenRegions(regionCode);
		for(SysRegionEntity s:list){
			Map<String, Object> itemMap = new HashMap<>();
			itemMap.put("id", s.getRegionCode());
			itemMap.put("text", s.getName());
			itemMap.put("state", "closed");
			returnList.add(itemMap);
		}
		return JsonUtils.getJsonFormat(returnList);
		 
			 
	}
	
	 
	
	/*@ResponseBody
	@RequestMapping(value="/read",method=RequestMethod.GET)	 
	public String read(HttpServletRequest request,Model model, String dataJson,String  RegionCode, String KeyValue){
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
        if(RegionCode==null){
			RegionCode="51";
		}
		paramMap.put("RegionCode", RegionCode);
        paramMap.put("BuildType","高血压"); 
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		 
		String response = RestfulUtils.getPublicData("57-1", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		 
		List<Map<String, Object> > list=(List<Map<String, Object> > )responseMap.get("Msg");
		Map<String, Object> map = JsonUtils.getObjectJsonMap(response);
		 
		map.put("data",list);
		map.put("iTotalRecords", responseMap.get("Total"));
		map.put("iTotalDisplayRecords",responseMap.get("Total"));
		map.put("recordsTotal",responseMap.get("Total"));
		map.put("recordsFiltered",responseMap.get("Total"));
		return JsonUtils.getJson(map);
	}*/
	
	
	
	
	/**
	 * 条件查询
	 * @param request
	 * @param model
	 * @param RegionCode
	 * @param KeyValue
	 * @param HasFollowup
	 * @param PhoneTel
	 * @param IsPoor
	 * @param IsClose
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(HttpServletRequest request,Model model, String  RegionCode,String KeyValue,String HasFollowup,String PhoneTel,String IsPoor,String IsClose,String userName){
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        paramMap.put("ProductCode",vo.getProductCode());
        if(RegionCode==null){
			RegionCode="51";
		}
		paramMap.put("RegionCode", RegionCode);
		paramMap.put("KeyValue", KeyValue);
		paramMap.put("HasFollowup", HasFollowup);
		paramMap.put("PhoneTel", PhoneTel);
		paramMap.put("IsPoor", IsPoor);
		paramMap.put("IsClose", IsClose);
        paramMap.put("BuildType","高血压"); 
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);		 
		String response = RestfulUtils.getPublicData("57-1", paramMap);
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
	 * 高血压建档获取居民信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addIndex",method=RequestMethod.GET)
	public String addIndex(HttpServletRequest request,Model model, String dataJson,String  RegionCode,String KeyCode,String KeyValue,String ProductCode){
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
       // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
        paramMap.put("ProductCode", ProductCode);
        if(RegionCode==null){
			RegionCode="51";
		}
    	paramMap.put("RegionCode", RegionCode);
		// "KeyCode": "查询方式  1姓名或拼音,2身份证,3档案号,4自定义编码,5联系电话"
	    paramMap.put("KeyCode", KeyCode); 
        paramMap.put("KeyValue",KeyValue); 
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
	 * 高血压建档选择医生信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/chooseDoctor",method=RequestMethod.GET)
	public String chooseDoctor(HttpServletRequest request,Model model, String KeyValue){
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
		List<DoctorDetailVo> list= docService.getDoctorList(KeyValue, Integer.parseInt(length), Integer.parseInt(start));
		int count =docService.getDoctorCount(KeyValue);
		Map<String, Object> map =   new HashMap<>();		 
		map.put("data",list);
		map.put("iTotalRecords", count);
		map.put("iTotalDisplayRecords",count);
		map.put("recordsTotal",count);
		map.put("recordsFiltered",count);
		return JsonUtils.getJson(map);
	}
	
	/**
	 * 高血压随访查询
	 * 
	 * @author h
	 */
	@ResponseBody
	@RequestMapping(value="/queryHypertensionFollowUp",method=RequestMethod.GET)
	public String queryHypertensionFollowUp(Model model, String dataJson,HttpServletRequest request){
		String KeyValueType = request.getParameter("KeyValueType");
		String KeyValue = request.getParameter("KeyValue");
		String StartFollowupDate = request.getParameter("StartFollowupDate");
		String EndFollowupDate = request.getParameter("EndFollowupDate");
		String IsFollowup = request.getParameter("IsFollowup");
		String Perfect = request.getParameter("Perfect");
		String FollowupType = request.getParameter("FollowupType");
		String RegionCode = request.getParameter("RegionCode");
		String userName = request.getParameter("doctorName");
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
       // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
        paramMap.put("ProductCode", vo.getProductCode());
        if(RegionCode==null){
			RegionCode="51";
		}
    	paramMap.put("RegionCode", RegionCode);
    	paramMap.put("KeyValueType", KeyValueType); 
        paramMap.put("KeyValue",KeyValue); 
        paramMap.put("StartFollowupDate",StartFollowupDate); 
        paramMap.put("EndFollowupDate",EndFollowupDate); 
        paramMap.put("IsFollowup",IsFollowup); 
        paramMap.put("Perfect",Perfect); 
        paramMap.put("FollowupType",FollowupType); 
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);		
 		String response = RestfulUtils.getPublicData("58-6", paramMap);
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
	 * 查询个人高血压历史
	 * @author h
	 */
	@ResponseBody
	@RequestMapping(value="/queryFollowUpHistory",method=RequestMethod.GET)
	public  String queryFollowUpHistory(Model model, HttpServletRequest request){
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length"); 
		String PersonID =request.getParameter("PersonID");
		String ProductCode =request.getParameter("ProductCode");
        Map<String, Object> paramMap = new HashMap<String, Object>();
       // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
        paramMap.put("ProductCode", ProductCode);
        paramMap.put("PersonID", PersonID); 
		model.addAttribute("PersonID", PersonID);
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		String response = RestfulUtils.getPublicData("58-1", paramMap);
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
	 * 修改高血压随访记录
	 * @param vo
	 * @return
	 * @author h
	 */
	@ResponseBody
	@RequestMapping(value="/updateHypertensionFollowUpRecord",method=RequestMethod.POST)
	public String updateHypertensionFollowUpRecord(Model model, String dataJson,String saveParam){
		Map  map=JsonUtils.getSingleJsonMap(saveParam);	
		String response = RestfulUtils.getPublicData("58-2", map);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NULL_DATA_CODE.getKey(), ApiStatusEnum.NULL_DATA_CODE.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(""));
	}
 
	 
	/**
	 * 传递个人高血压 personID
	 * @param model
	 * @param dataJson
	 * @return
	 * @author h
	 */
	@RequestMapping(value="/curve",method=RequestMethod.GET)
	public String curve(Model model, String personid,String userName){
		model.addAttribute("personid", personid);	
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		model.addAttribute("ProductCode", vo.getProductCode());	
		return "/mobile/hypertension/curve";
	} 
	
	/**
	 * 传递个人高血压 personID
	 * @param model
	 * @param dataJson
	 * @return
	 * @author h
	 */
	@RequestMapping(value="/personCurve",method=RequestMethod.GET)
	public String personCurve(Model model, String personid,String ProductCode){
		model.addAttribute("personid", personid);		 
		model.addAttribute("ProductCode", ProductCode);		 
		return "/mobile/hypertension/personCurve";
	} 
	
	/**
	 * 查询个人高血压随访曲线图
	 * @param model
	 * @param dataJson
	 * @return
	 * @author h
	 */
	@ResponseBody
	@RequestMapping(value="/queryHypertensionCurve",method=RequestMethod.GET)
	public String queryHypertensionCurve(Model model,String PersonID,String StartTime,String EndTime,String ProductCode){
		Map<String, Object> paramMap = new HashMap<String, Object>();
	   // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
	    paramMap.put("ProductCode",ProductCode);
	    paramMap.put("PersonID", PersonID);
	    paramMap.put("StartTime",StartTime);
	    paramMap.put("EndTime", EndTime);
		String response = RestfulUtils.getPublicData("57-9", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);		 
		List<Map<String, Object>>  list=(List<Map<String,Object>>)  responseMap.get("Msg");
		if(list.size()==0){
			Map<String, Object> map = new HashMap<String, Object>();
		    map.put("key","key");
		    list.add(map);
			return JsonUtils.getJsonFormat(list);
		} 
		return JsonUtils.getJsonFormat(list);
	}
	/**
	 * 增加高血压档案
	 * @param model
	 * @param dataJson
	 * @return
	 * @author h
	 */
	@ResponseBody
	@RequestMapping(value="/addHypertension",method=RequestMethod.POST)
	public String addHypertension(Model model, String dataJson,HttpServletRequest request,String saveParam){	 
		//查询基本信息
		Map  map=JsonUtils.getSingleJsonMap(saveParam);
		String responses = RestfulUtils.getPublicData("57-4", map);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(responses);

		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NULL_DATA_CODE.getKey(), ApiStatusEnum.NULL_DATA_CODE.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success("")); 		 
	}
	/**
	 * 查询某人的基本信息
	 * @param model
	 * @param dataJson
	 * @return
	 */
	@RequestMapping(value="/queryBaseInfomation",method=RequestMethod.GET)
	public String queryBaseInfomation(Model model, String dataJson,String id,String personid,String userName){
		Map<String, Object> paramMap = new HashMap<String, Object>();
	   // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		//获取产品授权码
		DoctorDetailVo docInfo = docUserService.getDoctorByUsername(userName);
		paramMap.put("ProductCode", docInfo.getProductCode());
	    paramMap.put("ID", personid);
		String response = RestfulUtils.getPublicData("55-10", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response); 
		Map<String, Object>  list=( Map<String, Object>)  responseMap.get("Msg");
		model.addAttribute("data", list);
		model.addAttribute("ProductCode", docInfo.getProductCode());		
		//model.addAttribute("ProductCode", PropertiesUtils.getValue("produce_code"));		
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("ProductCode", docInfo.getProductCode());
		//paramMap2.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		paramMap2.put("ID",id);
		paramMap2.put("BuildType","高血压");
		String response2 = RestfulUtils.getPublicData("57-7", paramMap2);
		Map<String, Object> responseMap2 = JsonUtils.getObjectJsonMap(response2); 
		Map<String, Object>  list2=( Map<String, Object>)  responseMap2.get("Msg");
		model.addAttribute("list", list2);
		Map<String, Object>  list21=( Map<String, Object>)  list2.get("cmPerson");
		Map<String, Object>  examBodyMap=( Map<String, Object>)  list2.get("examBody");
		Map<String, Object>  cmHyLevelMap=( Map<String, Object>)  list2.get("cmHyLevel");
		model.addAttribute("examBodyMap", examBodyMap);
		model.addAttribute("cmHyLevelMap", cmHyLevelMap);
        if(cmHyLevelMap!=null){ 
        	Integer HyLevel =(Integer) cmHyLevelMap.get("HyLevel");
        	model.addAttribute("HyLevel", NumberUtils.bitand(HyLevel));
        	Integer OtherRiskFactors =(Integer) cmHyLevelMap.get("OtherRiskFactors");
        	model.addAttribute("OtherRiskFactors", NumberUtils.bitand(OtherRiskFactors));
        	Integer TargetOrganDamage =(Integer) cmHyLevelMap.get("TargetOrganDamage");
        	model.addAttribute("TargetOrganDamage", NumberUtils.bitand(TargetOrganDamage));
        	Integer ClinicalComplications =(Integer) cmHyLevelMap.get("ClinicalComplications");
        	model.addAttribute("ClinicalComplications", NumberUtils.bitand(ClinicalComplications));
        	Integer HyRisk =(Integer) cmHyLevelMap.get("HyRisk");
        	model.addAttribute("HyRisk", HyRisk);
        }
		model.addAttribute("cmPerson", list21);
		return "/mobile/hypertension/edit";
	}
	/**
	 * 增加随访回显基本信息
	 * @param model
	 * @param dataJson
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addFollowupShow",method=RequestMethod.GET)
	public String addFollowupShow(Model model, String PersonID,String userName,HttpServletRequest request){	 
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		//paramMap2.put("ProductCode", PropertiesUtils.getValue("produce_code"));		 
		paramMap2.put("ProductCode", vo.getProductCode());		 
		paramMap2.put("ID",PersonID);
		String response2 = RestfulUtils.getPublicData("55-10", paramMap2);
		Map<String, Object> responseMap2 = JsonUtils.getObjectJsonMap(response2); 
		Map<String, Object>  list2=( Map<String, Object>)  responseMap2.get("Msg");
		model.addAttribute("baseData",list2);//个人基本信息
		//查询出疾病既往史
		StringBuffer buffer = new StringBuffer();
		List<Map<String, Object>> personDiseaseList = (List<Map<String, Object>>) list2.get("CmData");
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
		//查询家族史
		StringBuffer familybuffer = new StringBuffer();
		StringBuffer parentbuffer = new StringBuffer();
		parentbuffer.append("父亲:");
		StringBuffer monthbuffer = new StringBuffer();
		monthbuffer.append("母亲:");
		StringBuffer brotherbuffer = new StringBuffer();
		brotherbuffer.append("兄弟姐妹:");
		StringBuffer sonbuffer = new StringBuffer();
		sonbuffer.append("子女:");
		List<Map<String, Object>> familyDiseaseList = (List<Map<String, Object>>) list2.get("CmData");
		for (Map<String, Object> familyDisease : familyDiseaseList) {
			String RelationshipType = (String) familyDisease.get("RelationshipType");
			String Disease = (String) familyDisease.get("Disease");
			if("1".equals(RelationshipType)){
				parentbuffer.append(Disease+",");
			}else if("2".equals(RelationshipType)){
				monthbuffer.append(Disease+",");
			}else if("3".equals(RelationshipType)){
				brotherbuffer.append(Disease+",");
			}else if("4".equals(RelationshipType)){
				sonbuffer.append(Disease+",");
			}
		}
		int pflag=parentbuffer.indexOf(",");
		String parent ="";
		if(pflag!=-1){
			parent =parentbuffer.substring(0, parentbuffer.length()-1);
		}else{
			parent= parentbuffer.substring(0, parentbuffer.length());
		}
		int mflag=monthbuffer.indexOf(",");
		String month ="";
		if(mflag!=-1){
			month =monthbuffer.substring(0, monthbuffer.length()-1);
		}else{
			month =monthbuffer.substring(0, monthbuffer.length());
		}
		int bflag=brotherbuffer.indexOf(",");
		String brother ="";
		if(bflag!=-1){
			brother =brotherbuffer.substring(0, brotherbuffer.length()-1);
		}else{
			brother =brotherbuffer.substring(0, brotherbuffer.length());
		}
		int sflag=sonbuffer.indexOf(",");
		String son ="";
		if(sflag!=-1){
			son =sonbuffer.substring(0, sonbuffer.length()-1);
		}else{
			son =sonbuffer.substring(0, sonbuffer.length());
		}
		familybuffer.append(parent+"  ").append(month+"  ").append(brother+"  ").append(son);
		model.addAttribute("FamilyHistory", familybuffer);
		String GenderCode =(String)list2.get("GenderCode");
		String Gender = null;
		if("0".equals(GenderCode)){
			Gender ="未知";
		}else if("1".equals(GenderCode)){
			Gender ="男";
		}else if("2".equals(GenderCode)){
			Gender ="女";
		}else if("2".equals(GenderCode)){
			Gender ="未说明";
		}
		String BirthDay =(String)list2.get("BirthDay");
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = null;
		try {
			date=sdf.parse(BirthDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int age=new Date().getYear()-date.getYear();
		String PersonTel =(String) list2.get("PersonTel");		
		model.addAttribute("Gender", Gender); 
 		model.addAttribute("age", age);
		model.addAttribute("PersonTel",PersonTel);
		//model.addAttribute("ProductCode", PropertiesUtils.getValue("produce_code")); 
		model.addAttribute("ProductCode", vo.getProductCode()); 
		//最后一次随访记录
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		//paramMap1.put("ProductCode", PropertiesUtils.getValue("produce_code"));		 
		paramMap1.put("ProductCode", vo.getProductCode());		 
		paramMap1.put("PersonID",PersonID);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String followUpDate=df.format(new Date());	
		//当前时间
		model.addAttribute("followUpDate", followUpDate);
		paramMap1.put("FollowupDate",followUpDate);
		String response1 = RestfulUtils.getPublicData("58-5", paramMap1);
		Map<String, Object> responseMap1 = JsonUtils.getObjectJsonMap(response1); 
		Map<String, Object>  list1=( Map<String, Object>)  responseMap1.get("Msg");
		model.addAttribute("followUpList", list1);
		model.addAttribute("UserID",PersonID);
		return "/mobile/hypertension/addhypertensionFollowform";
	}
	
	
	/**
	 * 回显某人的随访记录
	 * @param model
	 * @param dataJson
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editFollowup",method=RequestMethod.GET)
	public String editFollowup(Model model, String dataJson,HttpServletRequest request){
		String ID =request.getParameter("ID");
		String PersonName =request.getParameter("PersonName");
		String userName =request.getParameter("userName");
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		Map<String, Object> paramMap = new HashMap<String, Object>();
	   // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		paramMap.put("ProductCode", vo.getProductCode());
	    paramMap.put("ID", ID);
		String response = RestfulUtils.getPublicData("58-3", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response); 
		Map<String, Object>  map=( Map<String, Object>)  responseMap.get("Msg");
		Integer symptom =(Integer)(((Map<String, Object>)map.get("cmHypertension")).get("Symptom"));
		if(symptom!=null){
			model.addAttribute("symptom", NumberUtils.bitand(symptom));
		}
		String PersonID =(String)(((Map<String, Object>)map.get("cmHypertension")).get("PersonID"));
		model.addAttribute("data", map);
		//异常处理
		ArrayList<Map<String, Object>> otherList =(ArrayList<Map<String, Object>>) map.get("otherJson");
		System.out.println(JsonUtils.getJsonFormat(otherList));
		model.addAttribute("otherList",JsonUtils.getJsonFormat(otherList));
		//用药处理
		ArrayList<Map<String, Object>> drugUseList =(ArrayList<Map<String, Object>>) map.get("drugJson");
		model.addAttribute("drugUseList",JsonUtils.getJsonFormat(drugUseList));
		//查询上次随访记录
		String FollowUpDate =(String) (( Map<String, Object>)(( Map<String, Object>)  responseMap.get("Msg")).get("cmHypertension")).get("FollowUpDate");
		///Date(1487174400000)/
		FollowUpDate=FollowUpDate.replace("/Date(","").replace(")/", "");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long t=new Long(FollowUpDate);
		Date dt = new Date(t);
		FollowUpDate =simpleDateFormat.format(dt);
		Map<String, Object> lastMap = new HashMap<String, Object>();
		//lastMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		lastMap.put("ProductCode", vo.getProductCode());
		lastMap.put("PersonID", PersonID);
		lastMap.put("FollowUpDate", FollowUpDate);
		String lastResponse = RestfulUtils.getPublicData("58-5", lastMap);
		Map<String, Object> lastMap2 = JsonUtils.getObjectJsonMap(lastResponse); 
		Map<String, Object>  lastList=( Map<String, Object>) lastMap2.get("Msg");
		model.addAttribute("lastData", lastList);
		model.addAttribute("PersonName",PersonName);
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		//paramMap2.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		paramMap2.put("ProductCode", vo.getProductCode());
		paramMap2.put("ID",PersonID);
		String response2 = RestfulUtils.getPublicData("55-10", paramMap2);
		Map<String, Object> responseMap2 = JsonUtils.getObjectJsonMap(response2); 
		Map<String, Object>  list2=( Map<String, Object>)  responseMap2.get("Msg");
		model.addAttribute("baseData",list2);//个人基本信息
		//查询出疾病既往史
		StringBuffer buffer = new StringBuffer();
		List<Map<String, Object>> personDiseaseList = (List<Map<String, Object>>) list2.get("CmData");
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
		//查询家族史
		StringBuffer familybuffer = new StringBuffer();
		StringBuffer parentbuffer = new StringBuffer();
		parentbuffer.append("父亲:");
		StringBuffer monthbuffer = new StringBuffer();
		monthbuffer.append("母亲:");
		StringBuffer brotherbuffer = new StringBuffer();
		brotherbuffer.append("兄弟姐妹:");
		StringBuffer sonbuffer = new StringBuffer();
		sonbuffer.append("子女:");
		List<Map<String, Object>> familyDiseaseList = (List<Map<String, Object>>) list2.get("CmData");
		for (Map<String, Object> familyDisease : familyDiseaseList) {
			String RelationshipType = (String) familyDisease.get("RelationshipType");
			String Disease = (String) familyDisease.get("Disease");
			if("1".equals(RelationshipType)){
				parentbuffer.append(Disease+",");
			}else if("2".equals(RelationshipType)){
				monthbuffer.append(Disease+",");
			}else if("3".equals(RelationshipType)){
				brotherbuffer.append(Disease+",");
			}else if("4".equals(RelationshipType)){
				sonbuffer.append(Disease+",");
			}
		}
		int pflag=parentbuffer.indexOf(",");
		String parent ="";
		if(pflag!=-1){
			parent =parentbuffer.substring(0, parentbuffer.length()-1);
		}else{
			parent= parentbuffer.substring(0, parentbuffer.length());
		}
		int mflag=monthbuffer.indexOf(",");
		String month ="";
		if(mflag!=-1){
			month =monthbuffer.substring(0, monthbuffer.length()-1);
		}else{
			month =monthbuffer.substring(0, monthbuffer.length());
		}
		int bflag=brotherbuffer.indexOf(",");
		String brother ="";
		if(bflag!=-1){
			brother =brotherbuffer.substring(0, brotherbuffer.length()-1);
		}else{
			brother =brotherbuffer.substring(0, brotherbuffer.length());
		}
		int sflag=sonbuffer.indexOf(",");
		String son ="";
		if(sflag!=-1){
			son =sonbuffer.substring(0, sonbuffer.length()-1);
		}else{
			son =sonbuffer.substring(0, sonbuffer.length());
		}
		familybuffer.append(parent+"  ").append(month+"  ").append(brother+"  ").append(son);
		model.addAttribute("FamilyHistory", familybuffer);
		String GenderCode =(String)list2.get("GenderCode");
		String Gender = null;
		if("0".equals(GenderCode)){
			Gender ="未知";
		}else if("1".equals(GenderCode)){
			Gender ="男";
		}else if("2".equals(GenderCode)){
			Gender ="女";
		}else if("2".equals(GenderCode)){
			Gender ="未说明";
		}
		String BirthDay =(String)list2.get("BirthDay");
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = null;
		try {
			date=sdf.parse(BirthDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int age=new Date().getYear()-date.getYear();
		String PersonTel =(String) list2.get("PersonTel");		
		model.addAttribute("Gender", Gender); 
 		model.addAttribute("age", age);
		model.addAttribute("PersonTel",PersonTel);
		//model.addAttribute("ProductCode", PropertiesUtils.getValue("produce_code"));
		model.addAttribute("ProductCode", vo.getProductCode());
		return "/mobile/hypertension/hypertensionFollowform";
	}
	/**
	 * 回显某人的基本信息
	 * @param model
	 * @param dataJson
	 * @return
	 */
	@RequestMapping(value="/echo",method=RequestMethod.GET)
	public String echo(Model model, String dataJson,String NAME,String GENDER,String CARD_ID,String ID,String TELPHONE,String AGE,String PersonCode,String ProductCode  ){
		Map<String, Object> paramMap = new HashMap<String, Object>();
	   // paramMap.put("ProductCode", PropertiesUtils.getValue("produce_code"));    
	    paramMap.put("ProductCode", ProductCode);    
	    paramMap.put("ID", ID);
		String response = RestfulUtils.getPublicData("55-10", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);	
		Map<String, Object>  map =( Map<String, Object>)  responseMap.get("Msg");
		model.addAttribute("data", map);
		String residenceAddress =(String) map.get("ResidenceAddress");
		String PersonID=(String)map.get("ID");
		String BuildOrgID=(String)map.get("BuildOrgID");
		String ResponsibilityID=(String)map.get("ResponsibilityID");
		String PUserID=(String)map.get("PUserID");
		String PUserName=(String)map.get("PUserName");
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("HealthHistory");
		String healthHistory ="";  
		for(int i=0;i<list.size();i++){
			if("4".equals(list.get(i).get("RecordType"))){
				healthHistory+=list.get(i).get("Name");
			}
		}		 
		//model.addAttribute("ProductCode", PropertiesUtils.getValue("produce_code"));
		model.addAttribute("ProductCode", ProductCode);
		model.addAttribute("PersonID", PersonID);
		model.addAttribute("BuildOrgID", BuildOrgID);
		model.addAttribute("ResponsibilityID", ResponsibilityID);
		model.addAttribute("PUserID", PUserID);
		model.addAttribute("PUserName", PUserName);
		model.addAttribute("NAME", NAME); 
		model.addAttribute("GENDER", GENDER); 
		model.addAttribute("CARD_ID", CARD_ID); 
		model.addAttribute("ID", ID); 
		model.addAttribute("TELPHONE",TELPHONE); 
		model.addAttribute("AGE", AGE); 
		model.addAttribute("PersonCode", PersonCode); 
		model.addAttribute("healthHistory", healthHistory); 
		model.addAttribute("residenceAddress", residenceAddress);  
		return "/mobile/hypertension/add";
	}
	/**
	 * 修改高血压档案
	 * @param model
	 * @param dataJson
	 * @return
	 * @author h
	 */
	@ResponseBody
	@RequestMapping(value="/updateHypertension",method=RequestMethod.POST)
	public String updateHypertension(Model model,HttpServletRequest request,String saveParam){
		Map  map=JsonUtils.getSingleJsonMap(saveParam);
		String responses = RestfulUtils.getPublicData("57-4", map);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(responses);
		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NULL_DATA_CODE.getKey(), ApiStatusEnum.NULL_DATA_CODE.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(""));
		 
	} 
}
