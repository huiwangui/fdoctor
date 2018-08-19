package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.InoculationEntity;
import com.boco.modules.fdoc.vo.InoculationVo;

@MyBatisDao
public interface InoculationDao extends CrudDao<InoculationEntity>{
	/**
	 * 预防接种
	 * @return
	 */
	public Integer vaccinate(InoculationVo vo);
	/**
	 * 修改预约订单号
	 * @param inoNum
	 * @param id
	 * @return
	 */
	public Integer updateNum(String inoNum,int id);
	/**
	 * 获取接种的人数
	 * @return
	 */
	public Integer getInoNum(String docUid,String status);
	/**
	 * 获取接种人员列表
	 * @param docUid
	 * @param status
	 * @return
	 */
	public List<InoculationVo> getInoList(InoculationVo vo);
	/**
	 * 查看是否重复预约
	 * @param vo
	 * @return
	 */
	public Integer getNum(InoculationVo vo);
	/**
	 * 修改接种预约状态
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateBookingStatus(Integer id,String status);
}
