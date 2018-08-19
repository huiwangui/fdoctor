package com.boco.modules.fdoc.api.publichealth;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
import com.boco.common.persistence.Page;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.ChineseMedicineFollowUpEntity;
import com.boco.modules.fdoc.service.ChineseMedicineFollowUpService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.vo.ChineseMedicineFollowUpVo;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 中医随访API
 * @author q
 *
 */

@RestController
@RequestMapping(value = "/publicInfo/chineseMedicine", produces = "text/json;charset=UTF-8")
public class ChineseMedicineApi {
	
	@Resource
	ChineseMedicineFollowUpService followUpService;
	@Resource
	DocUserService docUserService;
	
	@RequestMapping(value = "/getChsConsList", method = RequestMethod.GET)
	@ApiOperation(value = "获取中医随访列表（医生端）", notes = "{'userName':'登录医生用户名', 'keyValue' : '关键字内容，身份证号或姓名'}")
	public String getChsConsList(@RequestParam String userName,@RequestParam Integer pageSize, @RequestParam Integer pageNo,
			@RequestParam String keyValue){
		//封装查询参数
		ChineseMedicineFollowUpVo vo = new ChineseMedicineFollowUpVo();
		
		if (StringUtils.isNotEmpty(keyValue)) {
			//如果keyValue符合身份证规则，则设定为身份证，否则为姓名
			if (MatcherUtiles.idCardMach(keyValue)) {
				vo.setIdCard(keyValue);
			}else {
				vo.setPersonName(keyValue);
			}
		}
		
		//获取记录总数
		Integer count = followUpService.getChsConsCount(vo);
		
		//封装分页参数，获取列表
		Page<ChineseMedicineFollowUpVo> page = new Page<>(pageNo, pageSize, Long.valueOf(count));
		vo.setPage(page);
		List<ChineseMedicineFollowUpVo> chsConsList = followUpService.getChsConsList(vo);
		
		return JsonUtils.getPageJsonWithTotal(count, pageSize, chsConsList);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveChsConsRecord", method = RequestMethod.POST)
	@ApiOperation(value = "保存中医随访（医生端）", notes = "{\"userName\":\"医生登录用户名\",\"id\":\"随访记录ID，新增不传\",\"personId\":\"居民ID\",\"answers\":\"问卷答案，逗号隔开\",\"wayUp\":\"随访方式：1 门诊 2 家庭 3 电话（String）\",\"followUpDate\":\"随访日期时间戳（long）\""
			+ ",\"nextFollowUpDate\":\"下次日期时间戳（long）\",\"result\":[{\"status\":\"判断结果，0.不是 1是 2倾向是（String）\",\"bibuType\":\"体质类别（int）\",\"code\":\"得分（int）\"}],\"otherSuggestions\":\"其他建议\"}")
	public String saveChsConsRecord(@RequestBody String paramBody){
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(paramBody);
		//获取医生信息
		String userName = (String) jsonMap.get("userName");
		DoctorDetailVo doctorInfo = docUserService.getDoctorByUsername(userName);
		if (doctorInfo == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(),
					ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
		//创建保存对象，封装基本信息
		ChineseMedicineFollowUpEntity entity = new ChineseMedicineFollowUpVo();
		entity.setPersonId((String)jsonMap.get("personId"));	//居民ID
		entity.setWayUp((String)jsonMap.get("wayUp"));	//随访方式
		entity.setDoctorId(doctorInfo.getId());		//随访医生ID
		entity.setUpdateId(doctorInfo.getId());		//最后一次修改医生ID
		entity.setAnswers((String)jsonMap.get("answers"));	//答案集合
		entity.setOtherSuggestions((String)jsonMap.get("otherSuggestions"));	//其他建议
		entity.setFollowUpDate(new Date((Long)jsonMap.get("followUpDate")));	//随访日期
		if (jsonMap.get("nextFollowUpDate") != null) {
			entity.setNextFollowUpDate(new Date((Long)jsonMap.get("nextFollowUpDate")));	//下次随访日期
		}
		
		//封装各个详细得分
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) jsonMap.get("result");
		for (Map<String, Object> map : resultList) {
			Integer bibuType = (Integer) map.get("bibuType");	//随访项
			Integer score = (Integer) map.get("code");	//得分
			String status = (String) map.get("status");	//判断结果
			
			switch (bibuType) {
			case 1:		//气虚质
				entity.setQualityDeficiency(status);
				entity.setQualityDeficiencyScore(score);
				break;
			case 2:		//阳虚质
				entity.setYangQuality(status);
				entity.setYangQualityScore(score);
				break;
			case 3:		//阴虚质
				entity.setYinQuality(status);
				entity.setYinQualityScore(score);
				break;
			case 4:		//痰湿质
				entity.setPhlegm(status);
				entity.setPhlegmScore(score);
				break;
			case 5:		//湿热质
				entity.setDampHeat(status);
				entity.setDampHeatScore(score);
				break;
			case 6:		//血瘀质
				entity.setBloodQuality(status);
				entity.setBloodQualityScore(score);
				break;
			case 7:		//气郁质
				entity.setQiQuality(status);
				entity.setQiQualityScore(score);
				break;
			case 8:		//特禀质
				entity.setTebingquality(status);
				entity.setTebingqualityScore(score);
				break;
			case 9:		//平和质
				entity.setModerateQuality(status);
				entity.setModerateQualityScore(score);
				break;

			default:
				break;
			}
		}
		
		//调用保存方法：根据id判断为修改或者新增
		
		if (jsonMap.get("id") != null ) {
			entity.setId((String)jsonMap.get("id"));
			followUpService.updateChsConsRecord(entity);
		}else {
			followUpService.addChsConsRecord(entity);
		}
		
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/getChsConsInfo", method = RequestMethod.GET)
	@ApiOperation(value = "获取中医随访详情（医生端）", notes = "{'userName':'登录医生用户名', 'id' : '中医随访ID'}")
	public String getChsConsInfo(@RequestParam String userName, @RequestParam String id){
		try {
			//获取中医随访对象
			ChineseMedicineFollowUpEntity chsConsInfo = followUpService.getChsConsInfo(id);
			if (chsConsInfo == null) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DATA_EMPTY.getKey(),
						ApiStatusEnum.DATA_EMPTY.getValue()));
			}
			
			//声明返回map
			Map<String, Object> returnMap = new HashMap<String, Object>();
			
			//封装基本信息
			returnMap.put("id", chsConsInfo.getId());
			returnMap.put("personId", chsConsInfo.getPersonId());
			returnMap.put("wayUp", chsConsInfo.getWayUp());
			returnMap.put("doctorId", chsConsInfo.getDoctorId());
			returnMap.put("followUpDate", chsConsInfo.getFollowUpDate());
			returnMap.put("nextFollowUpDate", chsConsInfo.getNextFollowUpDate());
			returnMap.put("answers", chsConsInfo.getAnswers());
			returnMap.put("otherSuggestions", chsConsInfo.getOtherSuggestions());
			
			//封装体质详情
			String fields[] = {"qualityDeficiency","yangQuality","yinQuality","phlegm","dampHeat","bloodQuality","qiQuality","tebingquality","moderateQuality"};
			Class<?> cls = chsConsInfo.getClass();	//获取class
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();	//准备返回的结果集list
			
			for (int i = 0; i < fields.length; i++) {
				Map<String, Object> itemMap = new HashMap<String, Object>();
				//调用get方法，获取体质判定结果
				Method flagMethod = cls.getMethod("get" + StringUtils.captureUpName(fields[i]));
				Object result = flagMethod.invoke(chsConsInfo);
				
				//调用getScore方法，获取体质得分
				Method scoreMethod = cls.getMethod("get" + StringUtils.captureUpName(fields[i]) + "Score");
				Object score = scoreMethod.invoke(chsConsInfo);
				
				itemMap.put("status", result);
				itemMap.put("bibuType", i + 1);
				itemMap.put("code", score);
				
				resultList.add(itemMap);
			}
			returnMap.put("result", resultList);
			
			return JsonUtils.getJson(BaseJsonVo.success(returnMap));
		
		} catch (Exception e) {
			e.printStackTrace();
			
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ERROR_CODE.getKey(),
					ApiStatusEnum.ERROR_CODE.getValue()));
		}
	}
	
	@RequestMapping(value = "/getLastChsCons", method = RequestMethod.GET)
	@ApiOperation(value = "获取最近修改的随访信息（医生端）", notes = "{'userName':'登录医生用户名'}")
	public String getLastChsCons(@RequestParam String userName){
		
		return JsonUtils.getJson(BaseJsonVo.success(followUpService.getLastChsCons()));
	}
}
