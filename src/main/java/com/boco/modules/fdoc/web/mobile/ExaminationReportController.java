package com.boco.modules.fdoc.web.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.ExaminationReportService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;

@Controller
@RequestMapping(value = "/mobile/report", produces = "application/json;charset=UTF-8")
public class ExaminationReportController {
	@Resource
	ExaminationReportService examinationReportService;
	@Resource
	DocUserService docUserService;
	@Resource
	SignService signService;
	
	/**
	 * 查看体检报告  卫计逻辑是先保存后查看  默认查看当前保存的体检报告
	 * @param personId
	 * @param examDate
	 * @param remap 
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String getReportOfExamination(HttpServletRequest request, Model model,String remap){
		DoctorDetailVo vo = new DoctorDetailVo();
		String userName = (String) request.getSession().getAttribute(BusinessConstants.USERNAME_IN_SESSION);
		// session不为空，说明是医生端访问，从session中获取userName
		if (StringUtils.isNotEmpty(userName)) {
			vo =docUserService.getDoctorByUsername(userName);
		}else {
			// session为空，说明为居民端访问，从参数中获取居民ID，查找与居民签约的医生productCode
			Map<String, String> dataMap = JsonUtils.getSingleJsonMap(remap);
			String personId = dataMap.get("personId");
			List<DoctorDetailVo> teamInfo = signService.getSignDoctorTeamInfo(personId);
			for (DoctorDetailVo doctorDetailVo : teamInfo) {
				if (StringUtils.isNotEmpty(doctorDetailVo.getProductCode())) {
					vo = doctorDetailVo;
					break;
				}
			}
		}
		String productCode=vo.getProductCode();
		String personId=JsonUtils.getSingleJsonMap(remap).get("PERSONID");
		if(personId==null){
			personId=JsonUtils.getSingleJsonMap(remap).get("ID");
		}
		if(personId==null){
			personId=JsonUtils.getSingleJsonMap(remap).get("personId");
		}
		if(personId==null){
			 return "/mobile/examination/physicalReport";
		}
		String examDate=JsonUtils.getSingleJsonMap(remap).get("FOLLOW_UP_DATE");
		if(examDate==null){
			examDate=JsonUtils.getSingleJsonMap(remap).get("Lasthldate");
		}
		//调用卫计接口查询 56-6得到随访ID列表和日期
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", productCode);
		paramMap.put("PersonID", personId);
		String response = RestfulUtils.getPublicData("56-6", paramMap);
		Map<String, Object> responseMap = JsonUtils.getObjectJsonMap(response);
		System.out.println(response);
		//获取日期列表
		List listExamDate = new ArrayList<>();
		List<Map<String, Object>> listMiIdAndExamDate = new ArrayList<Map<String, Object>>();
		if (responseMap.get("Result") != null && (Integer) responseMap.get("Result") != 0) {
			listMiIdAndExamDate = (List<Map<String, Object>>) responseMap.get("Msg");
			if (listMiIdAndExamDate.size() > 0 && listMiIdAndExamDate != null) {
				for (Map<String, Object> map : listMiIdAndExamDate) {
					String exDate = (String) map.get("ExamDate");
					listExamDate.add(exDate);
				}
			}
			
		}
         //查询 需要随访时间来获取ID查询 
		 String MtId = null;
			for (Map<String, Object> map : listMiIdAndExamDate) {
				if(map.get("ExamDate").equals(examDate)){
					MtId=(String) map.get("MtId");
				}
			}
		 //56-4  获取体检信息
			if(MtId!=null){
			Map<String, Object> paramMap3 = new HashMap<String, Object>();
			paramMap3.put("ProductCode", productCode);
			// paramMap3.put("MtID", MtID);listMiIdAndExamDate
			paramMap3.put("MtID", MtId);

			String response3 = RestfulUtils.getPublicData("56-4", paramMap3);
			Map<String, Object> responseMap3 = JsonUtils.getObjectJsonMap(response3);
			if (responseMap3.get("Result") != null || (Integer) responseMap3.get("Result") != 0) {

				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap = (Map<String, Object>) responseMap3.get("Msg");
                model.addAttribute("report",JsonUtils.getJsonFormat(returnMap));
                model.addAttribute("reports",returnMap);
               
		        }
			}else{
				
				Map<String, Object> paramMap3 = new HashMap<String, Object>();
				paramMap3.put("ProductCode", productCode);
				MtId=(String) listMiIdAndExamDate.get(0).get("MtId");
				paramMap3.put("MtID", MtId);
				String response3 = RestfulUtils.getPublicData("56-4", paramMap3);
				Map<String, Object> responseMap3 = JsonUtils.getObjectJsonMap(response3);
				if (responseMap3.get("Result") != null || (Integer) responseMap3.get("Result") != 0) {
					Map<String, Object> returnMap = new HashMap<String, Object>();
					returnMap = (Map<String, Object>) responseMap3.get("Msg");
	                model.addAttribute("report",JsonUtils.getJsonFormat(returnMap));
	                model.addAttribute("reports",returnMap);
	               
			        }
				
			}
			
		   //本地获取
			model.addAttribute("doctorName", vo.getDocName());
		   return "/mobile/examination/physicalReport";
		
		}
	
	
}
	
	


