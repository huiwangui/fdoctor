package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.ArticleEntity;
import com.boco.modules.fdoc.model.CommentEntity;
import com.boco.modules.fdoc.vo.ArticleVo;

public interface CircleService {
   List<ArticleVo> findAll(ArticleVo vo);
   List<ArticleVo> findAllByTag(ArticleVo vo);
   int insertOne(ArticleEntity entity);
   
   List<ArticleVo> selectCommentList(ArticleVo vo);
   int insertOneComment(CommentEntity entity);
   ArticleEntity findOne(String articleId);
   void updateNum(String articleId);
   ArticleVo selectNameImg(String id);
    int getArtCount(ArticleVo vo);
    int getCommentCount(ArticleVo vo);
    int delArt(ArticleVo vo);
   
}
 