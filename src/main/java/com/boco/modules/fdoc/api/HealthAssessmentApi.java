package com.boco.modules.fdoc.api;

import java.util.ArrayList;
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
import com.boco.common.persistence.Page;
import com.boco.common.push.CloudMobilePush;
import com.boco.common.push.PushUtils;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.HealthAssessmentEntity;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.service.HealthAssesmentService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SendMsgService;
import com.boco.modules.fdoc.vo.HealthAssessmentVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/healthAssessment", produces = "text/json;charset=UTF-8")
public class HealthAssessmentApi {

	@Resource
	HealthAssesmentService assessmentService;
	@Resource
	PersonInformationService personService;
	@Resource
	SendMsgService sendMsgService;
	
	@RequestMapping(value = "/getHealthAssessmentList", method = RequestMethod.GET)
	@ApiOperation(value = "获取标签列表（医生端）", notes = "{'teamId':'医生团队ID','status':'状态：1.待评估  2.已评估'}")
	public String getHealthAssessmentList(@RequestParam String teamId, @RequestParam String status, @RequestParam Integer pageNo,@RequestParam Integer pageSize){
		HealthAssessmentVo vo = new HealthAssessmentVo();
		vo.setDocTeamId(teamId);
		vo.setStatus(status);
		//获取总数
		Integer count = assessmentService.getHealthAssessmentCount(vo);
		//封装分页对象
		Page<HealthAssessmentVo> page = new Page<HealthAssessmentVo>(pageNo, pageSize, count);
		vo.setPage(page);
		//查询list并返回
		List<HealthAssessmentVo> list = assessmentService.getHealthAssessmentList(vo);
		return JsonUtils.getPageJson(count, pageSize, list);
	}
	
	@RequestMapping(value = "/saveHealthAssessment", method = RequestMethod.POST)
	@ApiOperation(value = "医生填写健康评估并保存（医生端）", notes = "{'id':'评估主键ID','docId':'医生ID','title':'评估标题','content':'评估内容（300字以内）','medicationGuide':'用药指导（200字以内）','keyWords':'关键字'}")
	public String saveHealthAssessment(@RequestBody String paramBody){
		HealthAssessmentEntity entity = JsonUtils.getObject(paramBody, HealthAssessmentEntity.class);
		assessmentService.updateHealthAssesment(entity);
		// 推送给居民端
		HealthAssessmentVo assessment = assessmentService.getHealthAssessmentDetail(entity.getId());
		PersonInformationVo personVo = personService.getPersonDetailByPersonId(assessment.getPersonId());
		CloudMobilePush push = new CloudMobilePush();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", entity.getId());
		// 异常类型，2为血糖1为血压
		if (assessment.getBloodSuger() == null || assessment.getBloodSuger() == 0) {
			paramMap.put("exceptionType", BusinessConstants.HEALTH_ASSESSMENT_TYPE_HYPER);
		}else {
			paramMap.put("exceptionType", BusinessConstants.HEALTH_ASSESSMENT_TYPE_BLOODSUGER);
		}
		// 判断具体是偏高还是偏低
		if (BusinessConstants.HYPERTENSION_RESULT_LOW.equals(assessment.getDetectionResult())) {
			paramMap.put("result", "1");
		}else {
			paramMap.put("result", "3");
		}
		List<String> accounts = new ArrayList<String>();
		accounts.add(personVo.getUid());
		String title = "您有新的健康评估！";
		String content = "您的家庭医生"+assessment.getDocName()+"给您新增了健康评估！";
		push.androidPush(accounts, title, content, 
				BusinessConstants.PUSH_ACTIVITY, BusinessConstants.PUSH_ITEM_USER, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_HEALTH_ASSESSMENT, paramMap));
		/**
		 * 插入推送消息表
		 */
		SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_USER_INT,
				BusinessConstants.PUSH_TYPE_HEALTH_ASSESSMENT,JsonUtils.getJsonFormat(paramMap));
		for (String account : accounts) {
			msg.setUid(account);
			sendMsgService.addMsg(msg);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/getHealthAssessmentDetailList", method = RequestMethod.GET)
	@ApiOperation(value = "获取评估列表（居民端）", notes = "{'personId':'居民编号','year':'年份'}")
	public String getHealthAssessmentDetailList(@RequestParam String personId, @RequestParam Integer year){
		HealthAssessmentVo vo = new HealthAssessmentVo();
		vo.setPersonId(personId);
		vo.setBeginDate(DateUtils.getStartOfMonth(year, 1));
		vo.setEndDate(DateUtils.getEndOfMonth(year, 12));
		vo.setStatus(BusinessConstants.HEALTH_ASSESSMENT_STATUS_COMPLETED);
		return JsonUtils.getJson(BaseJsonVo.success(assessmentService.getHealthAssessmentListByUser(vo)));
	}
	
	@RequestMapping(value = "/getDealFlag", method = RequestMethod.GET)
	@ApiOperation(value = "判断该条异常信息是否已被评估（医生端）", notes = "{'id':'健康评估ID'}")
	public String getDealFlag(@RequestParam Integer id){
		HealthAssessmentVo detail = assessmentService.getHealthAssessmentDetail(id);
		if (detail == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ASSESSMENT_NOT_FOUND.getKey(),
							ApiStatusEnum.ASSESSMENT_NOT_FOUND.getValue()));
		}else {
			if (detail.getAssessmentTime() != null) {
				return JsonUtils.getJson(BaseJsonVo.success(true));
			}else {
				return JsonUtils.getJson(BaseJsonVo.success(false));
			}
		}
		
	}
	
}
