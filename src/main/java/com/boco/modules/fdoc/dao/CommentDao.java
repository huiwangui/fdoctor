package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.CommentEntity;
import com.boco.modules.fdoc.vo.ArticleVo;
@MyBatisDao
public interface CommentDao {
	int insertOneComment(CommentEntity entity);
	List<ArticleVo> selectCommentList(ArticleVo vo);
	ArticleVo selectNameImg(String id);
	int getCommentCount(ArticleVo vo);
	
}
