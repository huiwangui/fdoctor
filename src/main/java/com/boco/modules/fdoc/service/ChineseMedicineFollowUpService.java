package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.ChineseMedicineFollowUpEntity;
import com.boco.modules.fdoc.vo.ChineseMedicineFollowUpVo;

/**
 * 中医随访service
 * @author q
 *
 */
public interface ChineseMedicineFollowUpService {
	/**
	 * 获取中医随访列表
	 * @param vo: personName、idCard
	 * @return
	 */
	public List<ChineseMedicineFollowUpVo> getChsConsList(ChineseMedicineFollowUpVo vo);
	
	/**
	 * 获取中医随访总数
	 * @param vo: personName、idCard
	 * @return
	 */
	public Integer getChsConsCount(ChineseMedicineFollowUpVo vo);
	
	/**
	 * 新增中医随访
	 * @param entity
	 * @return
	 */
	public Integer addChsConsRecord(ChineseMedicineFollowUpEntity entity);
	
	/**
	 * 修改中医随访
	 * @param entity
	 * @return
	 */
	public Integer updateChsConsRecord(ChineseMedicineFollowUpEntity entity);
	
	/**
	 * 获取中医随访详情
	 * @param id
	 * @return
	 */
	public ChineseMedicineFollowUpEntity getChsConsInfo(String id);
	
	/**
	 * 获取最后修改的随访信息
	 * @return
	 */
	public ChineseMedicineFollowUpVo getLastChsCons();
}
