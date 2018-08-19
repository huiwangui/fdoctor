package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.HospitalCommentEntity;

@MyBatisDao
public interface HospitalCommentDao extends CrudDao<HospitalCommentEntity>{
	public int grade(HospitalCommentEntity hospitalCommentEntity);
	public List<HospitalCommentEntity> getByDocId(String id);
}
