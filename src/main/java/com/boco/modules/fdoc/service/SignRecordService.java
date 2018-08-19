package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.SigRecordEntity;
import com.boco.modules.fdoc.vo.SigRecordVo;

public interface SignRecordService {
	
	/**
	 * 查询该医生下的签约人员信息
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<SigRecordVo> querySignInfo(Page<SigRecordVo> page, SigRecordVo vo);
	
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
	 * 保存服务记录信息
	 * @param record
	 * @return
	 */
	public String saveRecord(SigRecordEntity record);
	
	/**
	 * 查询服务记录信息
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<SigRecordVo> queryRecord(Page<SigRecordVo> page, SigRecordVo vo);

}
