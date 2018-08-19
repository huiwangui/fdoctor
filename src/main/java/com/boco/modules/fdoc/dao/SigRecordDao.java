package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.SigRecordEntity;
import com.boco.modules.fdoc.vo.SigRecordVo;

@MyBatisDao
public interface SigRecordDao {
	
	/**
	 * 查询该医生下的签约人员信息
	 * @param vo
	 * @return
	 */
	public List<SigRecordVo> querySignInfo(SigRecordVo vo);
	
	/**
	 * 查询当前服务包下的服务项目选项
	 * @param sigId
	 * @return
	 */
	public List<SigRecordVo> queryServiceDetails(int sigId);
	
	/**
	 * 查询可服务的家庭医生
	 * @return
	 */
	public List<SigRecordVo> queryFamdoctor();
	
	/**
	 * 新增服务记录信息
	 * @param record
	 * @return
	 */
	public int saverecord(SigRecordEntity record);
	
	/**
	 * 查询该居民该服务详情的记录次数
	 * @param vo
	 * @return
	 */
	public int queryCount(SigRecordVo vo);
	
	/**
	 * 查询服务记录信息
	 * @param vo
	 * @return
	 */
	public List<SigRecordVo> queryRecord(SigRecordVo vo);

}
