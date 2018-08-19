package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.HospitalCommentEntity;

public interface HospitalCommentService {
	/**
	 * 添加一条评论
	 * @param hospitalCommentEntity
	 * @return
	 */
	public int add(HospitalCommentEntity hospitalCommentEntity);
	/**
	 * 评级 1、2、3、4、5 星
	 * @param hospitalCommentEntity
	 */
	public int grade(HospitalCommentEntity hospitalCommentEntity);
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public int delete(String id);
	/**
	 * 查询某家医院的评论
	 * @param id
	 * @return
	 */
	public List<HospitalCommentEntity> getByDocId(String id);
	/**
	 * 回复评论
	 * @param hospitalCommentEntity
	 * @return
	 */
	public int reply(HospitalCommentEntity hospitalCommentEntity);
}
