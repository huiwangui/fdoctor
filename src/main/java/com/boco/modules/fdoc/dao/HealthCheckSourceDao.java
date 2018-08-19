package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.HealthCheckSourceEntity;
import com.boco.modules.fdoc.vo.HealthCheckSourceVo;
/**
 * 医生放号Dao
 * @author q
 *
 */
@MyBatisDao
public interface HealthCheckSourceDao extends CrudDao<HealthCheckSourceEntity>  {
	/**
	 * 通过医生、日期、体检类型获取列表（用于判断当天是否已有该医生放的号）
	 * @return
	 */
	public List<HealthCheckSourceEntity> getSourceByDocAndDate(HealthCheckSourceVo vo);
	/**
	 * 修改号源数量
	 * @param entity
	 * @return
	 */
	public int updateSourceNum(HealthCheckSourceEntity entity);
	/**
	 * 根据状态查询该医生已发布、已过期的号源信息
	 * @param vo
	 * @return
	 */
	public List<HealthCheckSourceVo> getByStatus(HealthCheckSourceVo vo);
	/**
	 * 清空已过期的放号记录
	 * @param entity
	 * @return
	 */
	public int clear(HealthCheckSourceEntity entity);
	/**
	 * 居民端查询可进行预约体检的医院
	 * @param vo
	 * @return
	 */
	public List<HealthCheckSourceVo> getBookingHealthCheck(HealthCheckSourceVo vo);
}
