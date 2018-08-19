package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.HealthCheckEntity;
import com.boco.modules.fdoc.vo.HealthCheckVo;
/**
 * 体检预约记录Dao
 * @author q
 *
 */
@MyBatisDao
public interface HealthCheckDao extends CrudDao<HealthCheckEntity>  {
	/**
	 * 查询预约体检记录
	 * @param vo
	 * @return
	 */
	public List<HealthCheckVo> getHealthCheckList(HealthCheckVo vo);
	/**
	 * 修改订单号
	 */
	public int updateNum(HealthCheckEntity entity);
	/**
	 * 获取预约对象
	 * @param entity
	 * @return
	 */
	public HealthCheckVo getBookedHealthCheck(HealthCheckEntity entity);
	/**
	 * 修改预约状态
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateBookingStatus(Integer id,String status);
}
