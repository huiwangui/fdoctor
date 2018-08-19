package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.DoctorCommentEntity;
import com.boco.modules.fdoc.vo.DoctorDetailVo;

@MyBatisDao
public interface DoctorCommentDao extends CrudDao<DoctorCommentEntity>{
	public int grade(DoctorCommentEntity doctorCommentEntity);
	
	public List<DoctorCommentEntity> getByDocId(String id);
	
	public int getCommentLevelsCount(DoctorDetailVo vo);
	
	public int getCommentCount(int id);
	
	public List<DoctorCommentEntity> getSingleDocComment(DoctorDetailVo vo);
}
