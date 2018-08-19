package com.boco.modules.fdoc.dao;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.SmsCodeEntity;

/**
 * 
 * @author tonysu 
 * 
 * 2015-12-15 09:54:15 
 * 
 * 短信接口定义
 */
@MyBatisDao
public interface SmsCodeDao extends CrudDao<SmsCodeEntity>{


	public SmsCodeEntity validatePhoneCode(String phone, String code);

}
