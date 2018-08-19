package com.boco.modules.fdoc.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.BookingRuleEntity;
import com.boco.modules.fdoc.service.BookingRuleService;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/bookingRule", produces = "text/json;charset=UTF-8")
public class BookingRuleApi {

	@Resource
	BookingRuleService bookingRuleService;
	/**
	 * 查询医院预约规则
	 * 
	 * @param hospId
	 * @return
	 */
	@RequestMapping(value = "/getBookingRule", method = RequestMethod.GET)
	@ApiOperation(value = "查询医院预约规则", notes = "'hospId':'医院ID'", response = BaseJsonVo.class)
	public String getBookingRule(@RequestParam String hospId) {
		List<BookingRuleEntity> list = bookingRuleService.getBookingRulesByHospid(String.valueOf(hospId));
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
}
