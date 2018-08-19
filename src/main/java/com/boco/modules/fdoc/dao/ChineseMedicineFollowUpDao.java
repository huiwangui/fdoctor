package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.ChineseMedicineFollowUpEntity;
import com.boco.modules.fdoc.vo.ChineseMedicineFollowUpVo;

@MyBatisDao
public interface ChineseMedicineFollowUpDao extends CrudDao<ChineseMedicineFollowUpEntity>{
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
