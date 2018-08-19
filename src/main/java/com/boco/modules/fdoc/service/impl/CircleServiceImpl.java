package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.ArticleDao;
import com.boco.modules.fdoc.dao.CommentDao;
import com.boco.modules.fdoc.model.ArticleEntity;
import com.boco.modules.fdoc.model.CommentEntity;
import com.boco.modules.fdoc.service.CircleService;
import com.boco.modules.fdoc.vo.ArticleVo;

@Service
public class CircleServiceImpl implements CircleService{
    
	@Resource
	CommentDao commentDao;
	@Resource
	ArticleDao artcleDao;

	
	@Override
	public int insertOne(ArticleEntity entity) {
		return artcleDao.insertOne(entity);
		
		
	}
	@Override
	public List<ArticleVo> selectCommentList(ArticleVo vo) {
		// TODO Auto-generated method stub
		return commentDao.selectCommentList(vo);
	}
	@Override
	public int insertOneComment(CommentEntity entity) {
		return commentDao.insertOneComment(entity);
		
	}
	@Override
	public ArticleEntity findOne(String articleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateNum(String articleId) {
		artcleDao.updateNum(articleId);
		
	}
	@Override
	public ArticleVo selectNameImg(String id) {
		
		return commentDao.selectNameImg(id);
	}
	@Override
	public List<ArticleVo> findAll(ArticleVo vo) {
		
		return artcleDao.findAll(vo);
	}
	@Override
	public List<ArticleVo> findAllByTag(ArticleVo vo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getArtCount(ArticleVo vo) {
		// TODO Auto-generated method stub
		return artcleDao.getArtCount(vo);
	}
	@Override
	public int getCommentCount(ArticleVo vo) {
		// TODO Auto-generated method stub
		return commentDao.getCommentCount(vo);
	}
	@Override
	public int delArt(ArticleVo vo) {
		// TODO Auto-generated method stub
		return artcleDao.delArt(vo);
	}
	
	
	
  
	
}
