package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.BookingRuleDao;
import com.boco.modules.fdoc.model.BookingRuleEntity;
import com.boco.modules.fdoc.service.BookingRuleService;

@Service
public class BookingRuleServiceImpl implements BookingRuleService{
	
	@Resource
	BookingRuleDao bookingRuleDao;
	
	@Override
	public List<BookingRuleEntity> getBookingRulesByHospid(String hospid) {
		
		return bookingRuleDao.getBookingRulesByHospid(hospid);
	}

}
