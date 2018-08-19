package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.BookingRuleEntity;

public interface BookingRuleService {
	/**
	 * 查询医院预约规则
	 * @param hospid
	 * @return
	 */
	public List<BookingRuleEntity> getBookingRulesByHospid(String hospid);
}
