package com.boco.modules.fdoc.web.mobile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.boco.common.utils.RestfulUtils;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SysRegionEntity;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SysRegionService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;

@Controller
@RequestMapping(value="/mobile/diabetes",produces="application/json;charset=UTF-8")
public class DiabetesController {
	@Resource
	SysRegionService sysRegionService;
	@Resource
	DocService docService;
	@Resource
	PersonInformationService personInformationService;
	@Resource
	DocUserService docUserService;
	//主页功能入口
	@RequestMapping(value = "/queryindex", method = RequestMethod.GET)
	public String queryindex(HttpServletRequest request, Model model) {
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
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
		
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		//model.addAttribute("doctorName", doctorName);
		model.addAttribute("androidDataMap", JsonUtils.getJsonFormat(androidDataMap));
		 }
		return  "/mobile/diabetes/index";
		
	}
	//糖尿病随访页面入口
	@RequestMapping(value = "/fuindex", method = RequestMethod.GET)
	public String fuindex(HttpServletRequest request, Model model,String doctorName,String userName,String userId) {
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("userName", userName);
		model.addAttribute("userId", userId);
		return  "/mobile/diabetes/followupIndex";
		
	}
	//建档入口
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model,String doctorName,String userName,String userId) {
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("userName", userName);
		model.addAttribute("userId", userId);
		return  "/mobile/diabetes/diabetesEditCreate";
		
	}
	//个人历史随访记录入口
	@RequestMapping(value="/personalHistory",method=RequestMethod.GET)
	public String personalHistory(HttpServletRequest request,Model model,String PersonID,String doctorName,String userName,String userId){
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("PersonID", PersonID);
		model.addAttribute("userName", userName);
		model.addAttribute("userId", userId);
		return "/mobile/diabetes/diabetesFollowupHistory";
		
	}
	//初始化树
	@ResponseBody
	@RequestMapping(value="/initRegionTree",method=RequestMethod.GET)
	public String  initRegionTree(Model model,String dataJson,HttpServletRequest request){
		
		String regionCode=request.getParameter("regionCode");
		List<Map<String, Object>> rlist=new ArrayList<Map<String,Object>>();
		List<SysRegionEntity> list=sysRegionService.getChildrenRegions(regionCode);
		for(SysRegionEntity s:list){
			Map<String, Object> paraMap=new HashMap<String,Object>();
			paraMap.put("id", s.getRegionCode());
			paraMap.put("text", s.getName());
			paraMap.put("state", "closed");
			rlist.add(paraMap);
		}
		
		return JsonUtils.getJsonFormat(rlist);
	}
	/**
	 * 糖尿病首页
	 * @param request
	 * @param model
	 * @param dataJson
	 * @param RegionCode 区划码
	 * @param KeyValue 自动匹配的value值
	 * @param HasFollowup 是否随访
	 * @param PhoneTel 电话号码
	 * @param IsPoor 是否贫穷
	 * @param IsClose 是否结案
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryDiabetes",method=RequestMethod.GET)
	public String queryDiabetes(HttpServletRequest request,Model model, String dataJson,String  RegionCode,String KeyValue,String HasFollowup,String PhoneTel,String IsPoor,String IsClose,String doctorName){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        paramMap.put("ProductCode", vo.getProductCode());
        if(RegionCode==null){
			RegionCode="51";
		}
		paramMap.put("RegionCode", RegionCode);
        paramMap.put("BuildType","糖尿病"); 
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		paramMap.put("KeyValue",KeyValue );
		paramMap.put("PhoneTel",PhoneTel );
		paramMap.put("IsClose",IsClose );
		paramMap.put("IsPoor",IsPoor );
		paramMap.put("HasFollowup",HasFollowup );
		
		//调用接口
		String response = RestfulUtils.getPublicData("57-1", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		List<Map<String, Object> > list=(List<Map<String, Object> > )responseMap.get("Msg");
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("data",list);
		map.put("iTotalRecords", responseMap.get("Total"));
		map.put("iTotalDisplayRecords",responseMap.get("Total"));
		map.put("recordsTotal",responseMap.get("Total"));
		map.put("recordsFiltered",responseMap.get("Total"));
		return JsonUtils.getJson(map);
	}
	
	/**
	 * 编辑时的回显查询
	 * @param request
	 * @param model
	 * @param id 慢病名册ID
	 * @param personid 个人ID
	 * @return 跳往编辑页面
	 */
	@RequestMapping(value="/loadEdit",method=RequestMethod.GET)
	public String loadEdit(HttpServletRequest request,Model model,String id,String personid,String doctorName,
			String userId){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        paramMap.put("ProductCode", vo.getProductCode());
		paramMap.put("ID", personid);
		String responses = RestfulUtils.getPublicData("55-10", paramMap);
		//System.out.println("++++++++++++"+responses);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(responses);
		Map<String, Object>  list=( Map<String, Object>)responseMap.get("Msg");
		System.out.println(list);
		model.addAttribute("data", list);
		System.out.println("=========="+list);
		
		Map<String, Object> paraMap1=new HashMap<String, Object>();
		paraMap1.put("ProductCode",vo.getProductCode());
		paraMap1.put("ID", id);
		paraMap1.put("BuildType","糖尿病");
		String dataJson=RestfulUtils.getPublicData("57-7", paraMap1);
		//System.out.println(dataJson);
		Map<String, Object> dataMap=JsonUtils.getObjectJsonMap(dataJson);
		//System.out.println(dataMap);
		//model.addAttribute("dataJson", dataJson);
		//model.addAttribute("dataMap",dataMap);
		//if(dataMap.get("Resule")!=null&&(Integer)dataMap.get("Result")!=0){
		Map<String, Object> msgMap=(Map<String, Object>) dataMap.get("Msg");
		model.addAttribute("msgMap", msgMap);
		System.out.println("++++++++++"+msgMap);
		Map<String, Object> cmMap=(Map<String, Object>) msgMap.get("cmPerson");
		model.addAttribute("cmMap", cmMap);
		System.out.println("----------"+cmMap);
		//}
		model.addAttribute("doctorName", vo.getDocName());
		model.addAttribute("userName", userName);
		model.addAttribute("userId", userId);
		return "/mobile/diabetes/diabetesEdit";
	}
	/**
	 * 跳至糖尿病曲线，传递PersonId
	 * @param model
	 * @param personid
	 * @return
	 */
	@RequestMapping(value="/diabetesCurve",method=RequestMethod.GET)
	public String diabetesCurve(Model model,String personid,String doctorName){
		
		model.addAttribute("personid", personid);
		model.addAttribute("doctorName", doctorName);
		return "/mobile/diabetes/diabetesCurve";
		
	}
	/**
	 * 查询糖尿病曲线
	 * @param model
	 * @param dataJson
	 * @param PersonID 个人ID
	 * @param StartTime 时间起
	 * @param EndTime 时间终
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryDiabetesCurve",method=RequestMethod.GET)
	public String queryDiabetesCurve(HttpServletRequest request,Model model, String dataJson,String PersonID,String StartTime,String EndTime,String doctorName){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		Map<String, Object> paraMap=new HashMap<String,Object>();
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		paraMap.put("ProductCode",vo.getProductCode());
		paraMap.put("PersonID", PersonID);
		paraMap.put("StartTime", StartTime);
		paraMap.put("EndTime", EndTime);
		String response=RestfulUtils.getPublicData("59-6", paraMap);
		
		Map<String, Object> rMap = JsonUtils.getObjectJsonMap(response);
		System.out.println("---rmap"+rMap); 
		List<Map<String, Object>>  list=(List<Map<String,Object>>)rMap.get("Msg");
		//当大小为0时。放入map以待JSP校验
		if(list.size()==0){
			Map<String, Object> map = new HashMap<String, Object>();
		    map.put("key","key");
		    list.add(map);
			//System.out.println(JsonUtils.getJsonFormat(list));
			return JsonUtils.getJsonFormat(list);
		} 
		System.out.println("曲线数据："+JsonUtils.getJsonFormat(list));
		return JsonUtils.getJsonFormat(list);
		
	}
	/**
	 * PersonID查询个人慢病，读取健康卡-----------------------------------
	 * @param request
	 * @param model
	 * @param dataJson
	 * @param RegionCode
	 * @param KeyValue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/read",method=RequestMethod.GET)	 
	public String read(HttpServletRequest request,Model model, String dataJson, String idCard,String doctorName){
		
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		//获取前台额外传递过来的查询条件  
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String start = request.getParameter("start");  
		String length = request.getParameter("length");
		
		paramMap.put("KeyValue", idCard);
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        paramMap.put("ProductCode", vo.getProductCode());
        String  RegionCode="51";
		paramMap.put("RegionCode", RegionCode);
        paramMap.put("BuildType","糖尿病"); 
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
	public String readindex(HttpServletRequest request,Model model, String dataJson, String PersonID,String doctorName){
		
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		//获取前台额外传递过来的查询条件  
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		String start = request.getParameter("start");  
		String length = request.getParameter("length");
		String idCard1="";
		if(PersonID!=null){
			PersonInformationEntity pInfo = new PersonInformationEntity();
			pInfo= personInformationService.getPersonInformationByPesronId(PersonID);
			if(pInfo!=null)
			{
				idCard1=pInfo.getIdCard();
				System.out.println(pInfo.getPersonName());
			}else{
				Map<String, Object> idMap = new HashMap<String, Object>();
				String ProductCode=vo.getProductCode();
				idMap.put("ProductCode", ProductCode);
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
		
        paramMap.put("ProductCode", vo.getProductCode());
        
        String  RegionCode="51";
		
		paramMap.put("RegionCode", RegionCode);
        paramMap.put("BuildType","糖尿病"); 
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
	 * 保存修改的信息并删除旧档案
	 * @param request
	 * @param model
	 * @param personTel 
	 * @param PersonID
	 * @param DiseaseKindID
	 * @param Status
	 * @param DiagnosisDate
	 * @param OrgID
	 * @param DoctorID
	 * @param DoctorName
	 * @param UserID
	 * @param UserName
	 * @param RecordDate
	 * @param ID 删除所需ID字段
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value="/updateEdit",method=RequestMethod.POST)
	public String updateEdit(HttpServletRequest request,Model model,String personTel,String PersonID,
			String DiseaseKindID,String Status,String DiagnosisDate,String OrgID,String DoctorID,
			String DoctorName,String UserID,String UserName,String RecordDate,String ID,String doctorName){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		//System.out.println(RecordDate);
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		String productCode=vo.getProductCode();
		Map<String, Object> cmpMap=new HashMap<String,Object>();
		
		cmpMap.put("PersonID", PersonID);
		cmpMap.put("DiseaseKindID", DiseaseKindID);
		cmpMap.put("Status",Status );
		cmpMap.put("DiagnosisDate", DiagnosisDate);
		cmpMap.put("OrgID", OrgID);
		cmpMap.put("DoctorID",DoctorID );
		cmpMap.put("DoctorName",DoctorName );
		cmpMap.put("UserID",UserID );
		cmpMap.put("UserName",UserName );
		cmpMap.put("RecordDate", RecordDate);
		Map<String, Object> paraMap=new HashMap<String,Object>();
		paraMap.put("ProductCode", productCode);
		paraMap.put("BuildType", "糖尿病");
		paraMap.put("PersonTel", personTel);
		paraMap.put("CmPerson", cmpMap);
		paraMap.put("ExamBody", "{}");
		paraMap.put("CmHyLevel", "{}");
		
		String response=RestfulUtils.getPublicData("57-4", paraMap);
		Map<String, Object> returnMap=JsonUtils.getObjectJsonMap(response);		
		
		if(returnMap.get("Result").equals(1)){
			Map<String, Object> deleteMap=new HashMap<String,Object>();
			deleteMap.put("ProductCode", productCode);
			deleteMap.put("BuildType", "糖尿病");
			deleteMap.put("ID", ID);
			String dinfo=RestfulUtils.getPublicData("57-8", deleteMap);
			return JsonUtils.getJson(BaseJsonVo.success(200)); 
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
	}
	
	/**
	 * 通过健康档案接口获取居民信息，调用55-12获取居民信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addIndex",method=RequestMethod.GET)
	public String addIndex(HttpServletRequest request,Model model, String dataJson,String  RegionCode,String KeyCode,String KeyValue,String doctorName){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        paramMap.put("ProductCode", vo.getProductCode());
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
	 * 查询建档人的全布信息，调用55-10接口
	 * @param model
	 * @param dataJson
	 * @param NAME
	 * @param GENDER
	 * @param CARD_ID
	 * @param ID
	 * @param TELPHONE
	 * @param AGE
	 * @param PersonCode
	 * @return
	 */
	@RequestMapping(value="/searchOne",method=RequestMethod.GET)
	public String searchOne(HttpServletRequest request, Model model,String dataJson,String NAME,String GENDER,String CARD_ID,String ID,String TELPHONE,String AGE,String PersonCode,String doctorName){
		String userNameInSession = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		Map<String, Object> paraMap=new HashMap<String,Object>();
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userNameInSession);
		paraMap.put("ProductCode", vo.getProductCode());
		paraMap.put("ID", ID);
		String response=RestfulUtils.getPublicData("55-10", paraMap);
		Map<String, Object> responseMap=JsonUtils.getObjectJsonMap(response);
		Map<String, Object>  map =( Map<String, Object>)  responseMap.get("Msg");
		String DoctorName=(String) map.get("ResponsibilityDoctor");
		String residenceAddress=(String) map.get("ResidenceAddress");
		
		String doctorID=(String) map.get("ResponsibilityID");
		String userID=(String) map.get("PUserID");
		String userName=(String) map.get("PUserName");
		String orgID=(String) map.get("BuildOrgID");
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("HealthHistory");
		String healthHistory ="";  
		for(int i=0;i<list.size();i++){
			if("4".equals(list.get(i).get("RecordType"))){
				healthHistory+=list.get(i).get("Name");
			}
		}
		String DiseaseKindID=request.getParameter("DiseaseKindID");
		String status=request.getParameter("Status");
		
		List<Map<String, Object>> list1 = (List<Map<String, Object>>) map.get("CmDate");
		
		model.addAttribute("PersonID", ID);
		model.addAttribute("PersonCode",PersonCode );
		model.addAttribute("CardID",CARD_ID );
		model.addAttribute("personGender", GENDER);
		model.addAttribute("personName",NAME );
		model.addAttribute("DoctorName",DoctorName );
		model.addAttribute("personTel", TELPHONE);
		model.addAttribute("ResidenceAddress", residenceAddress);
		model.addAttribute("HealthHistory",healthHistory );
		model.addAttribute("buildAge", AGE);
		
		model.addAttribute("DoctorID", doctorID);
		model.addAttribute("UserID", userID);
		model.addAttribute("UserName", userName);
		model.addAttribute("OrgID", orgID);
		model.addAttribute("DiseaseKindID", DiseaseKindID);
		model.addAttribute("Status", status);
		model.addAttribute("doctorName", doctorName);
		
	return "/mobile/diabetes/diabetesEditCreate";
		
	}
	/**
	 * 保存新加入的档案
	 * @param request
	 * @param model
	 * @param personTel
	 * @param PersonID
	 * @param DiseaseKindID
	 * @param Status
	 * @param DiagnosisDate
	 * @param OrgID
	 * @param DoctorID
	 * @param DoctorName
	 * @param UserID
	 * @param UserName
	 * @param RecordDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveNew",method=RequestMethod.POST)
	public String saveNew(HttpServletRequest request,Model model,String personTel,String PersonID,
			String DiseaseKindID,String Status,String DiagnosisDate,String OrgID,String DoctorID,
			String DoctorName,String UserID,String UserName,String RecordDate,String doctorName){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);		
		String productCode=vo.getProductCode();
		Map<String, Object> cmpMap=new HashMap<String,Object>();
		
		
		cmpMap.put("PersonID", PersonID);
		cmpMap.put("DiseaseKindID", DiseaseKindID);
		cmpMap.put("Status",Status );
		cmpMap.put("DiagnosisDate", DiagnosisDate);
		cmpMap.put("OrgID", OrgID);
		cmpMap.put("DoctorID",DoctorID );
		cmpMap.put("DoctorName",DoctorName );
		cmpMap.put("UserID",UserID );
		cmpMap.put("UserName",UserName );
		cmpMap.put("RecordDate", RecordDate);
		Map<String, Object> paraMap=new HashMap<String,Object>();
		paraMap.put("ProductCode", productCode);
		paraMap.put("BuildType", "糖尿病");
		paraMap.put("PersonTel", personTel);
		paraMap.put("CmPerson", cmpMap);
		paraMap.put("ExamBody", "{}");
		paraMap.put("CmHyLevel", "{}");
		
		String response=RestfulUtils.getPublicData("57-4", paraMap);
		Map<String, Object> returnMap=JsonUtils.getObjectJsonMap(response);		
		
		if(returnMap.get("Result").equals(1)){
			return JsonUtils.getJson(BaseJsonVo.success(200)); 
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
		
	}
	/**
	 * 随访查询页面查询事件
	 * @param request
	 * @param model
	 * @param dataJson
	 * @param RegionCode
	 * @param KeyValueType
	 * @param KeyValue
	 * @param StartFollowupDate
	 * @param EndFollowupDate
	 * @param IsFollowup
	 * @param FollowupType
	 * @param Perfect
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/followupList",method=RequestMethod.GET)
	public String followupList(HttpServletRequest request,Model model, String dataJson,String  RegionCode,String KeyValueType,
			String KeyValue,String StartFollowupDate,String EndFollowupDate,String IsFollowup,String FollowupType,String Perfect,String doctorName){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		//获取前台额外传递过来的查询条件  
	    String start = request.getParameter("start");  
		String length = request.getParameter("length");  
        Map<String, Object> paramMap = new HashMap<String, Object>();
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        paramMap.put("ProductCode", vo.getProductCode());
        if(RegionCode==null){
			RegionCode="51";
		}
		paramMap.put("RegionCode", RegionCode);
        paramMap.put("BuildType","糖尿病"); 
		paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		paramMap.put("KeyValue",KeyValue );
		paramMap.put("KeyValueType",KeyValueType );
		paramMap.put("StartFollowupDate", StartFollowupDate);
		paramMap.put("EndFollowupDate",EndFollowupDate );
		paramMap.put("IsFollowup",IsFollowup );
		paramMap.put("FollowupType",FollowupType );
		paramMap.put("Perfect", Perfect);
		
		
		//调用接口
		String response = RestfulUtils.getPublicData("59-7", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		List<Map<String, Object> > list=(List<Map<String, Object> > )responseMap.get("Msg");
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("data",list);
		map.put("iTotalRecords", responseMap.get("Total"));
		map.put("iTotalDisplayRecords",responseMap.get("Total"));
		map.put("recordsTotal",responseMap.get("Total"));
		map.put("recordsFiltered",responseMap.get("Total"));
		return JsonUtils.getJson(map);
	}
	/**
	 * 查询个人历史随访记录
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/personalHistoryNext",method=RequestMethod.GET)
	public String personalHistoryNext(HttpServletRequest request,Model model,String doctorName){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		//获取前台额外传递过来的查询条件  
		String start = request.getParameter("start");  
		String length = request.getParameter("length"); 
		String PersonID =request.getParameter("PersonID");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
        paramMap.put("ProductCode", vo.getProductCode());
        paramMap.put("PersonID", PersonID);
        paramMap.put("PageIndex", start);
		paramMap.put("PageSize", length);
		
		String response = RestfulUtils.getPublicData("59-1", paramMap);
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
	 * 新建随访页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveFollowup",method=RequestMethod.GET)
	public  String saveFollowup(HttpServletRequest request,Model model,String ID,String PersonID,String doctorName,String userId){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		model.addAttribute("PersonID", PersonID);
		 DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		String ProductCode=vo.getProductCode();
		Map<String, Object> lastMap=new HashMap<String,Object>();
		lastMap.put("ProductCode", ProductCode);
		lastMap.put("PersonID", PersonID);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String followUpDate=df.format(new Date());
		model.addAttribute("followUpDate", followUpDate);
		lastMap.put("FollowUpDate", followUpDate);
		String lastresponse=RestfulUtils.getPublicData("59-5", lastMap);
		System.out.println("---上次随访---"+lastresponse);
		Map<String, Object> lastresMap=JsonUtils.getObjectJsonMap(lastresponse);
		Map<String, Object> lmsgMap=(Map<String, Object>) lastresMap.get("Msg");
		
		Map<String, Object> exambodyMap=(Map<String, Object>) lmsgMap.get("ExamBody");
		Map<String, Object> lfupMap=(Map<String, Object>) lmsgMap.get("lastHyFollowup");
		//处理疾病既往史
		List<Map<String , Object>> disHisList=(List<Map<String, Object>>) lmsgMap.get("disHistory");
		String disHisString="";
		for(int i=0;i<disHisList.size();i++){
			if(i==disHisList.size()-1){
				disHisString+=disHisList.get(i).get("NAME");
			}else{
				disHisString+=disHisList.get(i).get("NAME")+" , ";
			}
			
		}
		//处理家族史
		List<Map<String , Object>> faHisList=(List<Map<String, Object>>) lmsgMap.get("familyHistory");
		String motherinfo="";
		String fatherinfo="";
		String sisbroinfo="";
		String soninfo="";
		for(int i=0;i<faHisList.size();i++){
			if(faHisList.get(i).get("RelationshipType").equals(1)){
				motherinfo=faHisList.get(i).get("Disease").toString();
			}
			if(faHisList.get(i).get("RelationshipType").equals(2)){
				fatherinfo=faHisList.get(i).get("Disease").toString();
			}
			if(faHisList.get(i).get("RelationshipType").equals(3)){
				sisbroinfo=faHisList.get(i).get("Disease").toString();
			}
			if(faHisList.get(i).get("RelationshipType").equals(4)){
				soninfo=faHisList.get(i).get("Disease").toString();
			}
		}
		
		model.addAttribute("lfupMap",lfupMap );
		model.addAttribute("exambodyMap", exambodyMap);
		model.addAttribute("disHisString", disHisString);
		
		model.addAttribute("motherinfo", motherinfo);
		model.addAttribute("fatherinfo", fatherinfo);
		model.addAttribute("sisbroinfo", sisbroinfo);
		model.addAttribute("soninfo", soninfo);
		//查出姓名，性别，年龄，电话
		Map<String, Object> personinfoMap=new HashMap<String,Object>();
		personinfoMap.put("ProductCode", ProductCode);
		personinfoMap.put("ID", PersonID);
		String personinfo=RestfulUtils.getPublicData("55-10", personinfoMap);
		Map<String, Object> perMap=JsonUtils.getObjectJsonMap(personinfo);
		System.out.println(perMap);
		Map<String, Object> pmsgMap=(Map<String, Object>) perMap.get("Msg");
		String Name=(String) pmsgMap.get("Name");
		String PersonTel=(String) pmsgMap.get("PersonTel");
		String GenderCode=(String) pmsgMap.get("GenderCode");
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
		String BirthDay=(String) pmsgMap.get("BirthDay");
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = null;
		try {
			date=sdf.parse(BirthDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar now = Calendar.getInstance();
		Calendar birth = Calendar.getInstance();
		birth.setTime(date);
		int age=now.get(Calendar.YEAR)-birth.get(Calendar.YEAR);
		model.addAttribute("Name", Name);
		model.addAttribute("Gender", Gender);
		model.addAttribute("age", age);
		model.addAttribute("PersonTel",PersonTel );
		//转诊信息
		model.addAttribute("baseDate", pmsgMap);
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("userId", userId);
		return "/mobile/diabetes/saveFollowUp";
		
	}
	/**
	 * 修改：获取随访详细信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateFollowup",method=RequestMethod.GET)
	public String updateFollowup(HttpServletRequest request,Model model,String ID,String PersonID,String doctorName,String userId){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		String ProductCode=vo.getProductCode();
		Map<String, Object> paraMap=new HashMap<String,Object>();
		paraMap.put("ProductCode", ProductCode);
		paraMap.put("ID", ID);
		String response=RestfulUtils.getPublicData("59-3", paraMap);
		System.out.println("---随访详细---："+response);
		Map<String, Object> responseMap=JsonUtils.getObjectJsonMap(response);
		Map<String, Object> msgMap=(Map<String, Object>) responseMap.get("Msg");
		Map<String, Object> CmDiab=(Map<String, Object>) msgMap.get("cmDiabetes");
		Integer sumValue=(Integer) CmDiab.get("Symptom");
		if(sumValue!=null && !sumValue.equals("")){
			String sum=NumberUtils.bitand(sumValue);
			model.addAttribute("sum", sum);
		}
		
		//表详细信息
		ArrayList<Map<String, Object>> insulindrug=(ArrayList<Map<String, Object>>) msgMap.get("insulindrug");
		ArrayList<Map<String, Object>> drugJson=(ArrayList<Map<String, Object>>) msgMap.get("drugJson");
		model.addAttribute("drugJson",  JsonUtils.getJsonFormat(drugJson));
		//异常处理
		ArrayList<Map<String, Object>> otherList =(ArrayList<Map<String, Object>>) msgMap.get("otherJson");
		System.out.println(JsonUtils.getJsonFormat(otherList));
		model.addAttribute("otherList",JsonUtils.getJsonFormat(otherList));
		
		model.addAttribute("insulindrug", JsonUtils.getJsonFormat(insulindrug));
		model.addAttribute("data", msgMap);
		System.out.println("----------data:"+msgMap);
		//随访表头
		Map<String,Object> cmDiabetesMap=(Map<String, Object>) msgMap.get("cmDiabetes");
		String followUpDate=(String) cmDiabetesMap.get("FollowUpDate");
		Map<String, Object> lastMap=new HashMap<String,Object>();
		lastMap.put("ProductCode", ProductCode);
		lastMap.put("PersonID", PersonID);
		lastMap.put("FollowUpDate", followUpDate);
		String lastresponse=RestfulUtils.getPublicData("59-5", lastMap);
		System.out.println("---上次随访---"+lastresponse);
		Map<String, Object> lastresMap=JsonUtils.getObjectJsonMap(lastresponse);
		Map<String, Object> lmsgMap=(Map<String, Object>) lastresMap.get("Msg");
		
		Map<String, Object> exambodyMap=(Map<String, Object>) lmsgMap.get("ExamBody");
		Map<String, Object> lfupMap=(Map<String, Object>) lmsgMap.get("lastHyFollowup");
		//处理疾病既往史
		List<Map<String , Object>> disHisList=(List<Map<String, Object>>) lmsgMap.get("disHistory");
		String disHisString="";
		for(int i=0;i<disHisList.size();i++){
			if(i==disHisList.size()-1){
				disHisString+=disHisList.get(i).get("NAME");
			}else{
				disHisString+=disHisList.get(i).get("NAME")+" , ";
			}
			
		}
		//处理家族史
		List<Map<String , Object>> faHisList=(List<Map<String, Object>>) lmsgMap.get("familyHistory");
		String motherinfo="";
		String fatherinfo="";
		String sisbroinfo="";
		String soninfo="";
		for(int i=0;i<faHisList.size();i++){
			if(faHisList.get(i).get("RelationshipType").equals(1)){
				motherinfo=faHisList.get(i).get("Disease").toString();
			}
			if(faHisList.get(i).get("RelationshipType").equals(2)){
				fatherinfo=faHisList.get(i).get("Disease").toString();
			}
			if(faHisList.get(i).get("RelationshipType").equals(3)){
				sisbroinfo=faHisList.get(i).get("Disease").toString();
			}
			if(faHisList.get(i).get("RelationshipType").equals(4)){
				soninfo=faHisList.get(i).get("Disease").toString();
			}
		}
		System.out.println("-------"+motherinfo+fatherinfo+sisbroinfo+soninfo);
		
		model.addAttribute("lfupMap",lfupMap );
		model.addAttribute("exambodyMap", exambodyMap);
		model.addAttribute("disHisString", disHisString);
		
		model.addAttribute("motherinfo", motherinfo);
		model.addAttribute("fatherinfo", fatherinfo);
		model.addAttribute("sisbroinfo", sisbroinfo);
		model.addAttribute("soninfo", soninfo);
		//查出姓名，性别，年龄，电话
		Map<String, Object> personinfoMap=new HashMap<String,Object>();
		personinfoMap.put("ProductCode", ProductCode);
		personinfoMap.put("ID", PersonID);
		String personinfo=RestfulUtils.getPublicData("55-10", personinfoMap);
		Map<String, Object> perMap=JsonUtils.getObjectJsonMap(personinfo);
		System.out.println(perMap);
		Map<String, Object> pmsgMap=(Map<String, Object>) perMap.get("Msg");
		String Name=(String) pmsgMap.get("Name");
		String PersonTel=(String) pmsgMap.get("PersonTel");
		String GenderCode=(String) pmsgMap.get("GenderCode");
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
		String BirthDay=(String) pmsgMap.get("BirthDay");
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = null;
		try {
			date=sdf.parse(BirthDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar now = Calendar.getInstance();
		Calendar birth = Calendar.getInstance();
		birth.setTime(date);
		int age=now.get(Calendar.YEAR)-birth.get(Calendar.YEAR);
		model.addAttribute("Name", Name);
		model.addAttribute("Gender", Gender);
		model.addAttribute("age", age);
		model.addAttribute("PersonTel",PersonTel );
		//转诊信息
		
		model.addAttribute("baseData",pmsgMap);
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("userName", userName);
		model.addAttribute("userId", userId);
		return "/mobile/diabetes/diabetesFollowUp";
		
	}
	/**
	 * 修改随访后保存
	 * @param request
	 * @param model
	 * @param dataJson
	 * @param saveParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateSave",method=RequestMethod.POST)
	public String updateSave(HttpServletRequest request,Model model, String dataJson,String saveParam,String doctorName){
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		 DoctorDetailVo vo =docUserService.getDoctorByUsername(userName);
		String productCode=vo.getProductCode();
		Map  map=JsonUtils.getSingleJsonMap(saveParam);
	    map.put("ProductCode",productCode);
		String response = RestfulUtils.getPublicData("59-2", map);
		System.out.println(response);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);

		if (responseMap.get("Result") == null || (Integer) responseMap.get("Result") == 0) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NULL_DATA_CODE.getKey(), ApiStatusEnum.NULL_DATA_CODE.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(""));
	}
	/**
	 * 医生查询
	 * @param request
	 * @param model
	 * @param KeyValue
	 * @param OrgID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/docSearch",method=RequestMethod.GET)
	public String docSearch(HttpServletRequest request,Model model,String KeyValue){
		Integer  start =Integer.parseInt(request.getParameter("start")) ;  
		Integer  length= Integer.parseInt(request.getParameter("length"));
		String  response=JsonUtils.getJsonFormat(docService.getDoctorList(KeyValue, length, start));
		
		System.out.println(response);
		List<Map> list1=JsonUtils.getObjectList(response, Map.class);
		System.out.println(list1);
	    Map<String, Object> map=new  HashMap<String, Object>();
		map.put("data",list1);
		map.put("iTotalRecords", docService.getDoctorCount(KeyValue));
		map.put("iTotalDisplayRecords",docService.getDoctorCount(KeyValue));
		map.put("recordsTotal",docService.getDoctorCount(KeyValue));
		map.put("recordsFiltered",docService.getDoctorCount(KeyValue));
		return JsonUtils.getJson(map);
		
	}
	
	
}
