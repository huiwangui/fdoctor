package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.model.BookingEntity;
import com.boco.modules.fdoc.vo.BookingVo;

/**
 * 
 * @author sunche
 * @Time 2016年8月4日下午1:54:16
 */

public interface BookingService {
	/**
	 * 输入预约信息
	 * @param booking
	 * @return
	 */
	public String booking(BaseJsonVo vo);
	/**
	 * 取消已付款预约
	 * @param id
	 * @return
	 */
	public int cancelPaidBooking(int id);
	/**
	 * 取消未付款预约
	 * @param id
	 * @return
	 */
	public int cancelUnpaidBooking(int id);
	/**
	 * 查询预约
	 * @param uid
	 * @return
	 */
	public List<BookingVo> getBooking(BookingVo vo);
	/**
	 * 退款
	 * @param id
	 * @return
	 */
	public int getRefund(int id);
	/**
	 * 快速预约
	 * @param vo
	 * @return
	 */
	public String quickBooking(String period,String uid,int docId,Long workdate);
}
