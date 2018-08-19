package com.boco.modules.fdoc.api;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.service.BookingService;
import com.boco.modules.fdoc.service.DeptService;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.HospitalService;
import com.boco.modules.fdoc.service.UserService;
import com.boco.modules.fdoc.vo.QuickBookingVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 快速预约 api
 * 
 * @author sufj
 *
 */
@RestController
@RequestMapping(value = "/quickBooking", produces = "text/json;charset=UTF-8")
public class QuickBookingApi {
	
	@Resource
	HospitalService hospitalService;
	@Resource
	DeptService deptService;
	@Resource
	DocService docService;
	@Resource 
	UserService userService;
	@Resource
	BookingService bookingService;
//	/**
//	 *  根据医院id 查科室
//	 * @param hospId
//	 * @return
//	 */
//	@RequestMapping(value = "/getDepts", method = RequestMethod.GET)
//	@ApiOperation(value = "根据医院id、时间段 查能够预约的科室", notes = "{'hospId':'医院ID','period':'时间段1-上午，2-下午，3-夜诊',"
//			+ "'worktime':'时间时间戳(年月日)','uid':'用户id'}", response = BaseJsonVo.class)
//	public String getDeptByHospId(@RequestParam int hospId,@RequestParam int period,@RequestParam Long worktime,@RequestParam String uid) {
//		if (userService.getUser(uid) == null) {
//			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_EMPTY.getKey(), ApiStatusEnum.USER_EMPTY.getValue()));
//		}
//		QuickBookingVo vo = new QuickBookingVo();
//		vo.setPeriod(period);
//		vo.setWorktime(worktime/1000);
//		vo.setHospId(hospId);
//		
//		return JsonUtils.getJson(BaseJsonVo.success(deptService.getBookingDepts(vo)));
//	}
//
//	/**
//	 *  根据时间、查询医生所在的医院
//	 * @param bookingTime
//	 * @return
//	 */
//	@RequestMapping(value = "/getHospitals", method = RequestMethod.GET)
//	@ApiOperation(value = "根据时间段查询能够预约的医院", notes = "{'period':'时间段1-上午，2-下午，3-夜诊','worktime':'时间时间戳(年月日)','uid':'用户id'}", response = BaseJsonVo.class)
//	public String getHospByBookingtime(@RequestParam int period,@RequestParam Long worktime,@RequestParam String uid) {
//		if (userService.getUser(uid) == null) {
//			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_EMPTY.getKey(), ApiStatusEnum.USER_EMPTY.getValue()));
//		}
//		QuickBookingVo vo = new QuickBookingVo();
//		vo.setPeriod(period);
//		vo.setWorktime(worktime/1000);
//		return JsonUtils.getJson(BaseJsonVo.success(hospitalService.getBookingHospList(vo)));
//	}
//	/**
//	 * 快速预约 （当前版本只能预约用户自己，以uid为参数，身份证为关联条件查找用户家属表里面关系为本人的记录）
//	 * @param bookingBody
//	 * @return
//	 */
//	@RequestMapping(value = "/quickBooking", method = RequestMethod.POST)
//	@ApiOperation(value = "快速预约", notes = "{'period':'时间段1-上午，2-下午，3-夜诊','worktime':'时间时间戳(年月日)','uid':'用户ID','docId':'医生ID'}", response = BaseJsonVo.class)
//	public String quickBooking(@RequestBody String bookingBody) {
//		Map<String, String> bodyMap = JsonUtils.getSingleJsonMap(bookingBody);
//		if (bodyMap != null) {
//			String periodStr = bodyMap.get("period");
//			String worktimeStr = bodyMap.get("worktime");
//			String uid = bodyMap.get("uid");
//			String docIdStr = bodyMap.get("docId");
//			
//			String result = bookingService.quickBooking(periodStr, uid, 
//					Integer.parseInt(docIdStr), Long.parseLong(worktimeStr));
//			if ("noUserRelations".equals(result)) {
//				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_USERRELATIONS.getKey(),
//						ApiStatusEnum.NO_USERRELATIONS.getValue()));
//			}
//			if ("reBooking".equals(result)) {
//				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_BOOKING.getKey(),
//						ApiStatusEnum.RE_BOOKING.getValue()));
//			}
//			if ("no number".equals(result)) {
//				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_VACCINENUM.getKey(), ApiStatusEnum.NO_VACCINENUM.getValue()));
//			}
//			return JsonUtils.getJson(BaseJsonVo.success(result));
//		}else {
//			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DATA_FORMAT_ERROR.getKey(),
//					ApiStatusEnum.DATA_FORMAT_ERROR.getValue()));
//		}
//	}
}
