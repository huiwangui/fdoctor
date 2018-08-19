package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.ArticleEntity;
import com.boco.modules.fdoc.vo.ArticleVo;
@MyBatisDao
public interface ArticleDao {
	
	int insertOne(ArticleEntity entuity);
	List<ArticleVo> findAll(ArticleVo vo);
	List<ArticleVo> findAllByDoc(int topic);
    void updateNum(String articleId);
	List<ArticleVo> findAllByTag(String topic);
	int getArtCount(ArticleVo vo);
	int delArt(ArticleVo vo);
	

}
