package com.boco.modules.fdoc.api;

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
import com.boco.common.utils.JsonUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.BookingEntity;
import com.boco.modules.fdoc.service.BookingService;
import com.boco.modules.fdoc.service.HealthCheckService;
import com.boco.modules.fdoc.service.InoculationService;
import com.boco.modules.fdoc.vo.BookingVo;
import com.boco.modules.fdoc.vo.HealthCheckVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/booking", produces = "text/json;charset=UTF-8")
public class BookingApi extends BaseController {
	
	@Resource
	BookingService bookingService;
	@Resource
	InoculationService inoService;
	@Resource
	HealthCheckService healCheckService;
	/**
	 * 
	 * @author q
	 * @param bookingBody
	 * @return
	 * 
	 * 
	 * @RequestMapping(value = "/booking", method = RequestMethod.POST)
	@ApiOperation(value = "预约挂号", notes = "{'patientId':'就诊人ID','scheId':'排班ID','period':'预约时间：1-上午，2-下午，3-夜诊'}", response = BaseJsonVo.class)
	public String booking(@RequestBody String bookingBody) {
		BookingEntity booking = JsonUtils.getObject(bookingBody, BookingEntity.class);
		BaseJsonVo vo = new BaseJsonVo();
		vo.setData(booking);
		String result = bookingService.booking(vo);
		if ("reBooking".equals(result)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_BOOKING.getKey(),
					ApiStatusEnum.RE_BOOKING.getValue()));
		}else if ("no number".equals(result)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_VACCINENUM.getKey(), ApiStatusEnum.NO_VACCINENUM.getValue()));
		}
		
		return JsonUtils.getJson(BaseJsonVo.success(result));
	}
	
	@RequestMapping(value = "/getBooking", method = RequestMethod.GET)
	@ApiOperation(value = "查询我的预约（体检和接种）", notes = "{'uid':'用户ID','bookingType':'预约类型：1.体检  2.接种'}", response = BaseJsonVo.class)
	public String getBookingDetail(@RequestParam String uid,@RequestParam String bookingType) {
		if ("2".equals(bookingType)) {
			return JsonUtils.getJson(BaseJsonVo.success( inoService.getUserBookingInoList(uid)));
		}else if ("1".equals(bookingType)) {
			HealthCheckVo vo = new HealthCheckVo();
			vo.setUid(uid);
			return JsonUtils.getJson(BaseJsonVo.success( healCheckService.getHealthCheckList(vo)));
		}else {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.UNKNOWN_BOOKING_TYPE.getKey(), ApiStatusEnum.UNKNOWN_BOOKING_TYPE.getValue()));
		}
	}
	
	
	@RequestMapping(value = "/cancelBooking", method = RequestMethod.POST)
	@ApiOperation(value = "取消预约", notes = "{'id':'预约id','bookingType':'预约类型：1.体检  2.接种','payStatus':'支付状态： 0.未付款 1.已付款 2.正在退款  3.已退款'}", response = BaseJsonVo.class)
	public String cancelBooking(@RequestBody String bookingBody) {
		Map<String, String> jsonMap = JsonUtils.getSingleJsonMap(bookingBody);
		if ("2".equals(jsonMap.get("bookingType"))) {		//取消接种预约
			return JsonUtils.getJson(BaseJsonVo.success(inoService.updateBookingStatus(Integer.parseInt(jsonMap.get("id")), BusinessConstants.INO_STATUS_CANCLED)));
		}else if ("1".equals(jsonMap.get("bookingType"))) {		//取消体检预约
			return JsonUtils.getJson(BaseJsonVo.success(healCheckService.updateBookingStatus(Integer.parseInt(jsonMap.get("id")), BusinessConstants.CKECK_STATUS_CANCLED)));
		}
		return JsonUtils.getJson(null);
	}
	
	@RequestMapping(value = "/getRefund", method = RequestMethod.POST)
	@ApiOperation(value = " 退款（原路退回）", notes = "{'id':'预约id'}", response = BaseJsonVo.class)
	public String getRefund(@RequestParam int id) {
		int result = bookingService.getRefund(id);
		return JsonUtils.getJson(BaseJsonVo.success(result));
	}
	 */
	
}
