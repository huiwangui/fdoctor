package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.VaccineSourceEntity;
import com.boco.modules.fdoc.vo.VaccineSourceVo;

@MyBatisDao
public interface VaccineSourceDao extends CrudDao<VaccineSourceEntity>{
	/**
	 * 根据星期信息，获取当天的放号信息
	 * @param vo
	 * @return
	 */
	public VaccineSourceVo getByWeekDay(VaccineSourceVo vo);
	/**
	 * 根据登录医生UID、状态获取列表
	 * @param uid
	 * @param status
	 * @return
	 */
	public List<VaccineSourceVo> getByStatus(String uid,String status);
	/**
	 * 清空已过期的放号信息
	 * @param docUid
	 * @return
	 */
	public int clear(String docUid);
	/**
	 * 查看某医生某天所放号源
	 * @param vo
	 * @return
	 */
	public VaccineSourceEntity getSourceByDocAndDate(VaccineSourceEntity en);
	/**
	 * 修改放号数量
	 * @param en
	 * @return
	 */
	public int updateVaccineNum(VaccineSourceEntity en);
	/**
	 * 获取可预约的列表
	 * @param detailId
	 * @return
	 */
	public List<VaccineSourceVo> getSourceList(Integer detailId);
}
