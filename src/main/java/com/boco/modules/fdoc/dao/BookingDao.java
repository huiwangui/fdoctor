package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.BookingEntity;
import com.boco.modules.fdoc.vo.BookingVo;

/**
 * 
 * @author sunche
 * @Time 2016年8月4日下午1:51:19
 */

@MyBatisDao

public interface BookingDao extends CrudDao<BookingEntity>{
	/**
	 * 输入预约信息
	 * @param booking
	 * @return
	 */
	public int booking(BookingEntity booking);
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
	 * 通过医生id获取其预约数
	 * @param docId
	 * @return
	 */
	public int getCountByDocId(int docId);
	/**
	 * 自动生成订单号之后修改数据库的值
	 * @param booking
	 * @return
	 */
	public int updateOrderNum(BookingEntity booking);
	/**
	 * 查询已有的预约数（判断是否重复预约）
	 * @param booking
	 * @return
	 */
	public int getBookingNum(BookingEntity booking);
	/**
	 * 修改订单状态
	 * @param booking
	 * @return
	 */
	public int updateStatus(BookingEntity booking);
}
