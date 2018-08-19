package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.DoctorCommentEntity;

public interface DoctorCommentService {
	/**
	 * 添加评论
	 * @param doctorCommentEntity
	 * @return
	 */
	public int add(DoctorCommentEntity doctorCommentEntity);
	/**
	 * 评级 1、2、3、4、5 星
	 * @param doctorCommentEntity
	 */
	public int grade(DoctorCommentEntity doctorCommentEntity);
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public int delete(String id);
	/**
	 * 查询某医生的评论
	 * @param id
	 * @return
	 */
	public List<DoctorCommentEntity> getByDocId(String id);
	/**
	 * 回复评论
	 * @param doctorCommentEntity
	 * @return
	 */
	public int reply(DoctorCommentEntity doctorCommentEntity);
}
