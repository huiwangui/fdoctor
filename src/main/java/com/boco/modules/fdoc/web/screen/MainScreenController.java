package com.boco.modules.fdoc.web.screen;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.boco.common.utils.PropertiesUtils;
import com.boco.modules.fdoc.model.statistics.StatisticsDayTwoBasedataEntity;
import com.boco.modules.fdoc.vo.statistics.StatisticsDayTwoBasedataVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.model.SysRegionEntity;
import com.boco.modules.fdoc.model.statistics.StatisticsDayBasedataEntity;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.HospitalService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.service.StatisticsDayBasedataService;
import com.boco.modules.fdoc.service.SysRegionService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.HospitalVo;
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.statistics.StatisticsDayBasedataVo;

/**
 * 
 * ClassName: MainScreenController <br/>
 * Reason: 大屏展示控制器. <br/>
 * date: 2017年4月18日 上午8:50:59 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
@Controller
@RequestMapping(value="/mainScreen",produces="application/json;charset=UTF-8")
public class MainScreenController {
	
	@Resource
	HospitalService hospitalService;
	@Resource
	DocService docService;
	@Resource
	SysRegionService regionService;
	@Resource
	SignService signService;
	@Resource
	StatisticsDayBasedataService statisticsService;


	@RequestMapping(value="/getHospitalList", method = RequestMethod.GET)
	@ResponseBody
	public List<HospitalVo> getHospitalList(){
		HospitalVo vo = new HospitalVo();
		List<HospitalVo> hospitalList = hospitalService.getHospitalListWithTeamNum(vo);
		for (HospitalVo hospitalVo : hospitalList) {
			if (hospitalVo.getTeamNum() == null) {
				hospitalVo.setTeamNum(0);
			}
		}
		return hospitalList;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showScreen", method = RequestMethod.GET)
	public String showScreen(HttpServletRequest request, Model model) {
		//查询机构列表，并过滤没有地址的，转为json放入作用域
		/*HospitalVo vo = new HospitalVo();
		List<HospitalVo> hospitalList = hospitalService.getHospitalListWithTeamNum(vo);
		List<HospitalVo> returnHospitalList = new ArrayList<HospitalVo>();
		for (HospitalVo hospitalVo : hospitalList) {
			if (hospitalVo.getTeamNum() == null) {
				hospitalVo.setTeamNum(0);
			}
			if (StringUtils.isNoneEmpty(hospitalVo.getAddress())) {
				//发送请求获取机构经纬度
				String response = RestfulUtils.sendGetRequest("http://api.map.baidu.com/geocoder/v2/?output=json&address=" + hospitalVo.getAddress().replaceAll(" ", "") + "&ak=QaBCa02gdr5G3klaXIV5HlRKquut3SpH");
				System.out.println(response);
				Map<String, Object> dataMap = JsonUtils.getObjectJsonMap(response);
				if (dataMap.get("result") != null && dataMap.get("result") instanceof Map) {
					Map<String, Double> locationMap = (Map<String, Double>) ((Map<String, Object>)dataMap.get("result")).get("location");
					Double lng = locationMap.get("lng");
					Double lat = locationMap.get("lat");
					Double[] location = {lng,lat};
					hospitalVo.setPoint(location);
					returnHospitalList.add(hospitalVo);
				}
			}
		}*/
		
		//model.addAttribute("hospJson", JsonUtils.getJson(returnHospitalList));
		//封装地区基础数据
		StatisticsDayBasedataEntity basedata = statisticsService.getBasedata();
		Integer a=basedata.getSignCount();
		Integer b=basedata.getPersonCount();
		Double c=Double.valueOf(String.valueOf(a))/Double.valueOf(String.valueOf(b))*100;
		BigDecimal d=new BigDecimal(c);
		Double totalIncrement=d.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();

		String webServerIp = PropertiesUtils.getValue("ftp_web_server_ip");
		model.addAttribute("basedata", basedata);
		model.addAttribute("totalIncrement", totalIncrement);

		//封装地区慢病同比增长率、月增长数
		StatisticsDayBasedataVo incrementData = statisticsService.getIncrementData();
		model.addAttribute("incrementData", JsonUtils.getJson(incrementData));	//json属于用于echarts使用
		model.addAttribute("incrementObject", incrementData);	//对象数据用于jstl标签使用
		
		return "/mainscreen/mainscreen";
	}
	
	@RequestMapping(value = "/getTeamInfoInHospital", method = RequestMethod.GET)
	@ResponseBody
	public String getTeamInfoInHospital(HttpServletRequest request, Model model, String orgId) {
		List<HospitalVo> teamInfo = hospitalService.getTeamInHospital(orgId);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		for (HospitalVo hospitalVo : teamInfo) {
			List<DoctorDetailVo> docList = docService.getDoctorTeamMemberByTeamId(hospitalVo.getDocTeamId());
			if (docList != null && docList.size() >= 3) {
				returnMap.put("docNum", docList.size());
				returnMap.put("docList", docList);
				break;
			}
		}
		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}
	
	@RequestMapping(value = "/getTownInfoByHospital", method = RequestMethod.GET)
	@ResponseBody
	public String getTownInfoByHospital(HttpServletRequest request, Model model, String orgId) {
		try {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			//获取医院信息
			HospitalEntity hospitalInfo = hospitalService.getHospitalInfo(orgId);
			//封装直属乡镇信息
			SysRegionEntity region = regionService.getRegionByCode(hospitalInfo.getRegionCode());
			Map<String, Object> regionMap = new HashMap<String, Object>();
			regionMap.put("name", region.getName());
			//StatisticsDayBasedataVo townInfo = statisticsService.getTownIncrementData(hospitalInfo.getRegionCode());
			StatisticsDayBasedataVo townInfo = statisticsService.getTownIncrementData1(orgId);
			townInfo = (townInfo != null) ? townInfo : new StatisticsDayBasedataVo();
			regionMap.put("dataInfo", townInfo);
			returnMap.put("regionInfo", regionMap);
			//封装团队数量
			List<DoctorEntity> leaderList = docService.getLeaderListByHospital(orgId);
			returnMap.put("leaderList", leaderList);
			//封装子区划信息
			List<SysRegionEntity> childrenRegions = regionService.getChildrenRegions(hospitalInfo.getRegionCode());
			List<Map<String, Object>> childrenList = new ArrayList<Map<String,Object>>();
			for (SysRegionEntity child : childrenRegions) {
				//封装直属乡镇信息
				Map<String, Object> childMap = new HashMap<String, Object>();
				SignVo childVo = signService.getSignedCountByRegion(child.getRegionCode());
				childMap.put("name", child.getName());
				childMap.put("personCount", childVo.getPersonCount());
				childMap.put("signCount", childVo.getSignedCount());
				//childMap.put("familyCount",childVo.getFamilyCount());
				childMap.put("signPer", childVo.getSignPer());
				childrenList.add(childMap);
			}
			returnMap.put("childrenInfo", childrenList);
			returnMap.put("hospitalInfo", hospitalInfo);
			return JsonUtils.getJson(BaseJsonVo.success(returnMap));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.error());
		}
		
	}
	
	@RequestMapping(value = "/getTeamInfo", method = RequestMethod.GET)
	@ResponseBody
	public String getTeamInfo(HttpServletRequest request, Model model, String teamId, Integer personCount) {
		try {
			StatisticsDayBasedataVo teamIncrementData = statisticsService.getTeamIncrementData(teamId);
			DoctorDetailVo teamLeaderInfo = docService.getTeamLeaderInfo(teamId);
			teamIncrementData.setLeaderName(teamLeaderInfo.getDocName());
			if (personCount != 0) {
				Double signPer = Double.valueOf(teamIncrementData.getSignCount()) / Double.valueOf(personCount);
				BigDecimal decimal = new BigDecimal(signPer * 100);
				teamIncrementData.setSignPer(decimal.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
			}else {
				teamIncrementData.setSignPer(0.0);
			}
			return JsonUtils.getJson(BaseJsonVo.success(teamIncrementData));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.error());
		}
		
	}

	@RequestMapping("/getTeamInfoCount")
	@ResponseBody
	public StatisticsDayTwoBasedataVo count(String teamId,String orgId,String type){
		StatisticsDayTwoBasedataVo vo = statisticsService.getStatisticDayTwoBasedata(teamId,orgId,type );
		return vo;
	}

	//通过调用接口获取随访相应的数据
	@RequestMapping("/getPhysicalExaminationFollowUpCount")
	@ResponseBody
	public StatisticsDayTwoBasedataEntity phyCount(){
		return statisticsService.getAllPhysicalExaminationFollowUP();
	}

	//通过调用接口获取随访相应的数据
	@RequestMapping("/getTeamData")
	@ResponseBody
	public void getTeamData(){
		 statisticsService.getTeamData();
	}
}
