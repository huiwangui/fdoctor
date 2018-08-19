package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.BookingRuleEntity;

@MyBatisDao
public interface BookingRuleDao extends CrudDao<BookingRuleEntity>{
	public List<BookingRuleEntity> getBookingRulesByHospid(String hospid);
}
