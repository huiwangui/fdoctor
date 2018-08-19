package com.boco.modules.fdoc.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boco.modules.fdoc.service.HealthCheckService;

/**
 * 体检相关API
 * @author q
 *
 */
@RestController
@RequestMapping(value = "/healthCheck", produces = "text/json;charset=UTF-8")
public class HealthCheckApi {
	@Resource
	HealthCheckService healthCheckService;
	/**
	 * 
	 * @author q
	 * @param bookingBody
	 * @return
	 * @RequestMapping(value = "/addHealthCheckSource", method = RequestMethod.POST)
	@ApiOperation(value = "体检放号（医生端）", notes = "{'payType':'体检类型 1.自费 2.免费','checkType':'1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检',"
			+ "'timeStamp':'体检日期时间戳(年月日)','docUid':'放号医生登录UID','sourceNum':'放号数量'}", response = BaseJsonVo.class)
	public String addHealthCheckSource(@RequestBody String bookingBody) {
		HealthCheckSourceVo healthCheckSourceVo = JsonUtils.getObject(bookingBody, HealthCheckSourceVo.class);
		int returnInt = healthCheckService.addHealthCheckSource(healthCheckSourceVo);
		if (returnInt == -1) {   //返回值-1：医生UID错误
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DOCTOR_UID_NOTFOUND.getKey(), ApiStatusEnum.DOCTOR_UID_NOTFOUND.getValue()));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(returnInt));
		}
	}
	
	@RequestMapping(value = "/getByStatus", method = RequestMethod.GET)
	@ApiOperation(value = "根据状态查询放号记录（医生端）", notes = "{'checkType':'1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检',"
			+ "'docUid':'放号医生登录UID','status':'状态   1.已发布  2.已过期'}", response = BaseJsonVo.class)
	public String getByStatus(@RequestParam String checkType,@RequestParam String docUid,@RequestParam String status) {
		HealthCheckSourceVo vo = new HealthCheckSourceVo();
		vo.setCheckType(checkType);
		vo.setDocUid(docUid);
		vo.setStatus(status);
		List<HealthCheckSourceVo> list = healthCheckService.getByStatus(vo);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	
	@RequestMapping(value = "/clear", method = RequestMethod.POST)
	@ApiOperation(value = "清空已过期的放号记录（医生端）", notes = "{'checkType':'1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检','docUid':'放号医生登录UID'}", response = BaseJsonVo.class)
	public String clear(@RequestBody String bookingBody) {
		HealthCheckSourceVo healthCheckSourceVo = JsonUtils.getObject(bookingBody, HealthCheckSourceVo.class);
		int returnInt = healthCheckService.clear(healthCheckSourceVo);
		if (returnInt == -1) {   //返回值-1：医生UID错误
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DOCTOR_UID_NOTFOUND.getKey(), ApiStatusEnum.DOCTOR_UID_NOTFOUND.getValue()));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(returnInt));
		}
	}
	
	@RequestMapping(value = "/getHealthCheckList", method = RequestMethod.GET)
	@ApiOperation(value = "查询已预约的体检记录（医生端）", notes = "{'checkType':'1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检',"
			+ "'docUid':'放号医生登录UID'},'status':'状态 0.未体检  1.已体检'", response = BaseJsonVo.class)
	public String getHealthCheckList(@RequestParam String checkType,@RequestParam String docUid,@RequestParam String status) {
		HealthCheckVo vo = new HealthCheckVo();
		vo.setCheckType(checkType);
		vo.setDocUid(docUid);
		if (BusinessConstants.CHECK_STATUS_HASCHECKED.equals(status) || BusinessConstants.CHECK_STATUS_NOTCHECKED.equals(status)) {
			vo.setStatus(status);
		}
		List<HealthCheckVo> list = healthCheckService.getHealthCheckList(vo);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	
	@RequestMapping(value = "/getBookingHealthCheck", method = RequestMethod.GET)
	@ApiOperation(value = "查询可预约体检的医院（居民端）", notes = "{'area':'地区（暂未使用）','checkType':'1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检'}", response = BaseJsonVo.class)
	public String getBookingHealthCheck(@RequestParam String area,@RequestParam String checkType) {
		HealthCheckSourceVo vo = new HealthCheckSourceVo();
		vo.setCheckType(checkType);
		List<HealthCheckSourceVo> list = healthCheckService.getBookingHealthCheck(vo);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	
	@RequestMapping(value = "/bookingHealthCheck", method = RequestMethod.POST)
	@ApiOperation(value = "预约体检（居民端）", notes = "{'sourceId':'号源ID','checkType':'1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检','idCard':'体检人身份证号',"
			+ "'payType':'体检类型 1.自费 2.免费','docId':'放号医生id','hospId':'体检医院ID','timeStamp':'预约日期时间戳','uid':'预约操作人UID'}", response = BaseJsonVo.class)
	public String bookingHealthCheck(@RequestBody String bookingBody) {
		HealthCheckVo healthCheckVo = JsonUtils.getObject(bookingBody, HealthCheckVo.class);
		String returnMsg = healthCheckService.bookingHealthCheck(healthCheckVo);
		if ("reBooking".equals(returnMsg)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_HEALTHCHECK.getKey(), ApiStatusEnum.RE_HEALTHCHECK.getValue()));
		}else if ("no source".equals(returnMsg)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_HEALTHCHECK_NUM.getKey(), ApiStatusEnum.NO_HEALTHCHECK_NUM.getValue()));
		}else if("notSign".equals(returnMsg)){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NOT_SIGN.getKey(), ApiStatusEnum.NOT_SIGN.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(healthCheckService.getChecked(returnMsg)));
	}
	
	@RequestMapping(value = "/getHealthCheckPeopleNum", method = RequestMethod.GET)
	@ApiOperation(value = "查询已预约人之中已完成、未完成体检的人数（医生端）", notes = "{'checkType':'1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检',"
			+ "'docUid':'放号医生登录UID'}", response = BaseJsonVo.class)
	public String getHealthCheckPeopleNum(@RequestParam String checkType,@RequestParam String docUid) {
		HealthCheckVo vo = new HealthCheckVo();
		vo.setCheckType(checkType);
		vo.setDocUid(docUid);
		HealthCheckVo returnVo = healthCheckService.getHealthCheckPeopleNum(vo);
		return JsonUtils.getJson(BaseJsonVo.success(returnVo));
	}
	
	@RequestMapping(value = "/getCheckResident", method = RequestMethod.GET)
	@ApiOperation(value = "获取家庭中可预约此体检的人员（居民端）", notes = "{'checkType':'1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检',"
			+ "'idCard':'登录人身份证号'}", response = BaseJsonVo.class)
	public String getCheckResident(@RequestParam String checkType,@RequestParam String idCard) {
		HealthCheckVo vo = new HealthCheckVo();
		vo.setCheckType(checkType);
		vo.setIdCard(idCard);
		List<ResidentVo> list = healthCheckService.getResidentByType(vo);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	 */
	
}
