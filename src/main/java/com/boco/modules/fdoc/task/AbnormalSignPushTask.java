package com.boco.modules.fdoc.task;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.push.CloudMobilePush;
import com.boco.common.push.PushUtils;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.modules.fdoc.model.HealthAssessmentEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.HealthAssesmentService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SendMsgService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;

@Component("abnormalSignPushTask")
public class AbnormalSignPushTask {
	
	@Resource
	SignService signService;
	@Resource
	DocService docService;
	@Resource
	PersonInformationService personService;
	@Resource
	SendMsgService sendMsgService;
	@Resource
	HealthAssesmentService assessmentService;
	
	/**
	 * 
	 * pushTask:(轮询方式调用接口获取异常体征数据). <br/>
	 * @author q
	 */
	
	@SuppressWarnings("unchecked")
	//@Scheduled(cron = "0 0/1 * * * ?")	//每分钟触发
	@Scheduled(cron = "0 0 12,18 * * ?")	//每天12点、18点的时候触发
	public void pushTask(){
		//System.out.println("任务进行中..." + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		/**
		 * 拼装查询时间
		 */
		Date nowDate = new Date();
		String dateStr = DateUtils.formatDate(nowDate);
		String queryBeginTime = null;
		String queryEndTime = null;
		if (nowDate.before(DateUtils.parseDate(dateStr + " 13:00:00"))) {
			//当天13点之前，说明是12点钟调用的，查询时间应为昨天18点到当日12点
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			Date yesterday = cal.getTime();
			
			queryBeginTime = DateUtils.formatDate(yesterday) + " 18:00:00";
			queryEndTime = dateStr + " 12:00:00";
		}else {
			//当前13点之后，说明是18点调用的，查询时间为当天12点到当天18点
			queryBeginTime = dateStr + " 12:00:00";
			queryEndTime = dateStr + " 18:00:00";
		}
		
		// 封装查询参数，调用卫计委接口
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ProductCode", docService.getRandomProductCode());
		paramMap.put("startDate", queryBeginTime);
		paramMap.put("endDate", queryEndTime);
		paramMap.put("PageSize", 100);
		paramMap.put("PageIndex", 0);
		
		String returnMsg = RestfulUtils.getPublicData("68-2", paramMap);
		
		Map<String, Object> objectJsonMap = JsonUtils.getObjectJsonMap(returnMsg);
		
		
		
		if (((Integer)objectJsonMap.get("Result")) > 0) {
			if (objectJsonMap.get("Msg") != null && objectJsonMap.get("Msg") instanceof List) {
				List<Map<String, Object>> dataList = (List<Map<String, Object>>) objectJsonMap.get("Msg");
				for (Map<String, Object> map : dataList) {
					String personId = (String) map.get("PERSON_ID");
					String createTimeStr = (String) map.get("CTIME");
					PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(personId);
					if (personInfo != null) {
						//获取签约家庭医生团队信息
						List<DoctorDetailVo> teamInfo = signService.getSignDoctorTeamInfo(personId);
						if (teamInfo != null && teamInfo.size() > 0) {
							String teamId = teamInfo.get(0).getTeamId();
							// 封装推送信息列表
							Map<String, Object> pushMap = new HashMap<String, Object>();
							pushMap.put("personId", personId);
							// 获取推送账户
							List<String> accounts = signService.getSignTeamUsers(personId);
							CloudMobilePush push = new CloudMobilePush();
							String title = (String) map.get("TYPE");
							if ("血糖异常".equals(title)) {
								//血糖异常
								Double bloodSuger = Double.valueOf((String) map.get("ABNORMAL"));
								String detectionResult;
								//血糖危险度定级
								if (bloodSuger < 3.9) {
									//空腹血糖小于3.9，为低血糖
									detectionResult = BusinessConstants.BLOOD_SUGER_LOW;
								}else if (bloodSuger >= 3.9 && bloodSuger < 6.1) {
									//空腹血糖处于[3.9,6.1)，为正常血糖
									detectionResult = BusinessConstants.BLOOD_SUGER_NORMAL;
								}else if (bloodSuger >= 6.1 && bloodSuger < 7.0) {
									//空腹血糖处于[6.1,7)，为正常偏高
									detectionResult = BusinessConstants.BLOOD_SUGER_NORMAL_HIGH;
								}else if (bloodSuger >= 7.0 && bloodSuger < 8.4) {
									//空腹血糖处于[7.0,8.4)，为轻度偏高
									detectionResult = BusinessConstants.BLOOD_SUGER_HIGH_LEVEL1;
								}else if (bloodSuger >= 8.4 && bloodSuger <= 11.1) {
									//空腹血糖处于[8.4,11.1]，为中度偏高
									detectionResult = BusinessConstants.BLOOD_SUGER_HIGH_LEVEL2;
								}else {
									//空腹血糖大于11.1，为重度偏高
									detectionResult = BusinessConstants.BLOOD_SUGER_HIGH_LEVEL3;
								}
								//新增健康评估
								HealthAssessmentEntity assessment = new HealthAssessmentEntity();
								assessment.setDocTeamId(teamId);
								assessment.setPersonId(personId);
								assessment.setExceptionType(BusinessConstants.HEALTH_ASSESSMENT_TYPE_BLOODSUGER);	//类型设置为血糖异常
								assessment.setBloodSuger(bloodSuger);
								assessment.setDetectionResult(detectionResult);
								assessment.setRemark("来自卫计委的血糖数据");
								assessment.setCreateTime(nowDate);
								assessment.setStatus(BusinessConstants.HEALTH_ASSESSMENT_STATUS_WAIT);//设置状态为待评估
								Integer assessmentId = assessmentService.addHealthAssesment(assessment);
								
								
								pushMap.put("id", assessmentId);
								pushMap.put("bloodSuger", bloodSuger);
								pushMap.put("remarks", "来自卫计委的血糖数据");
								pushMap.put("exceptionType", BusinessConstants.HEALTH_ASSESSMENT_TYPE_BLOODSUGER);
								pushMap.put("createTime", DateUtils.parseDate(createTimeStr).getTime());
								
								String content = "您的签约居民"+personInfo.getPersonName()+"出现血糖异常，点击查看！";
								push.androidPush(accounts, title, content, 
										BusinessConstants.PUSH_ACTIVITY, BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_ABNORMAL_BLOODSUGAR, pushMap));
								
								/**
								 * 插入推送消息表
								 */
								SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_DOC_INT,
										BusinessConstants.PUSH_TYPE_ABNORMAL_BLOODSUGAR,JsonUtils.getJsonFormat(pushMap));
								for (String account : accounts) {
									msg.setDocUserName(account);
									sendMsgService.addMsg(msg);
								}
								
							}else if ("血压异常".equals(title)) {
								//血压异常
								String hyper = (String) map.get("ABNORMAL");
								String[] split = hyper.split("/");
								Double systolicPressure = Double.valueOf(split[0]);
								Double diastolicPressure = Double.valueOf(split[1]);
								String detectionResult;
								//血压危险度定级
								
								if (systolicPressure < 90 || diastolicPressure < 60) {
									// 舒张压<60或收缩压<90或为低血压
									detectionResult = BusinessConstants.HYPERTENSION_RESULT_LOW;
								}else if (systolicPressure >= 90 && systolicPressure <= 120 && diastolicPressure >= 60 && diastolicPressure <= 80) {
									// 收缩压在[90,120]且舒张压在[60,80]范围为正常血压
									detectionResult = BusinessConstants.HYPERTENSION_RESULT_NORMAL;
								}else if ((systolicPressure > 120 && systolicPressure < 140)  || (diastolicPressure > 80 && diastolicPressure < 90)) {
									// 收缩压在(120,140)或舒张压在(80,90)为正常高值
									detectionResult = BusinessConstants.HYPERTENSION_RESULT_NORMAL_HIGN;
								}else if ((systolicPressure >= 140 && systolicPressure < 160) || (diastolicPressure >= 90 && systolicPressure <	100) ) {
									// 收缩压在[90,160)或舒张压在[90,100)，为轻度偏高
									detectionResult = BusinessConstants.HYPERTENSION_RESULT_HIGH_LEVEL1;
								}else if ((systolicPressure >= 160 && systolicPressure < 180) || (diastolicPressure >= 100 && systolicPressure < 110) ) {
									// 收缩压在[160,180)或舒张压在[100,110)，为中度偏高
									detectionResult = BusinessConstants.HYPERTENSION_RESULT_HIGH_LEVEL2;
								}else{
									// 收缩压>=180，或舒张压>= 110，为重度偏高
									detectionResult = BusinessConstants.HYPERTENSION_RESULT_HIGH_LEVEL3;
								}
								
								HealthAssessmentEntity assessment = new HealthAssessmentEntity();
								assessment.setDocTeamId(teamId);
								assessment.setPersonId(personId);
								assessment.setExceptionType(BusinessConstants.HEALTH_ASSESSMENT_TYPE_HYPER);	//类型设置为血压异常
								assessment.setSystolicPressure(systolicPressure);
								assessment.setDiastolicPressure(diastolicPressure);
								assessment.setDetectionResult(detectionResult);
								assessment.setRemark("来自卫计委的血压数据");
								assessment.setCreateTime(nowDate);
								assessment.setStatus(BusinessConstants.HEALTH_ASSESSMENT_STATUS_WAIT);//设置状态为待评估
								Integer assessmentId = assessmentService.addHealthAssesment(assessment);
								
								pushMap.put("id", assessmentId);
								pushMap.put("personId", personId);
								pushMap.put("systolicPressure", systolicPressure);
								pushMap.put("diastolicPressure", diastolicPressure);
								pushMap.put("remarks", "来自卫计委的血压数据");
								pushMap.put("exceptionType", BusinessConstants.HEALTH_ASSESSMENT_TYPE_HYPER);
								pushMap.put("createTime", DateUtils.parseDate(createTimeStr).getTime());
								
								String content = "您的签约居民"+personInfo.getPersonName()+"出现血压异常，点击查看！";
								push.androidPush(accounts, title, content, BusinessConstants.PUSH_ACTIVITY, 
										BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_ABNORMAL_HYPER, pushMap));
								
								/**
								 * 插入推送消息表
								 */
								SendMsgEntity msg = new SendMsgEntity(title,content,BusinessConstants.PUSH_ITEM_DOC_INT,
										BusinessConstants.PUSH_TYPE_ABNORMAL_HYPER,JsonUtils.getJsonFormat(pushMap));
								for (String account : accounts) {
									msg.setDocUserName(account);
									sendMsgService.addMsg(msg);
								}
							}
						}
					}
				}
			}
		}
	}
}
