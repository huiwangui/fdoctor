package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.VaccineEntity;
import com.boco.modules.fdoc.vo.VaccienDetailVo;

@MyBatisDao
public interface VaccineDao extends CrudDao<VaccineEntity>{
	/**
	 * 获取疫苗详情
	 * @return
	 */
	public List<VaccienDetailVo> getVaccineDetail();
	/**
	 * 通过详情ID获取疫苗entity
	 */
	public VaccineEntity getEntityByDetail(int detailId);
}
