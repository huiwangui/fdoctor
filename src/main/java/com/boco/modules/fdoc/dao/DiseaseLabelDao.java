package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.DiseaseLabelEntity;
import com.boco.modules.fdoc.model.DiseaseLabelPersonRelationEntity;
import com.boco.modules.fdoc.vo.DiseaseLabelVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;

@MyBatisDao
public interface DiseaseLabelDao extends CrudDao<DiseaseLabelEntity>{
	/**
	 * 
	 * insertLabel:(新增慢病标签). <br/>
	 * @author q
	 * @param labelEntity
	 * @return
	 */
	public Integer insertLabel(DiseaseLabelEntity labelEntity);
	/**
	 * 
	 * updateLabel:(修改慢病标签). <br/>
	 * @author q
	 * @param labelEntity
	 * @return
	 */
	public Integer updateLabel(DiseaseLabelEntity labelEntity);
	/**
	 * 
	 * deleteLabel:(删除慢病标签). <br/>
	 * @author q
	 * @param id
	 * @return
	 */
	public Integer deleteLabel(Integer labelId);
	/**
	 * 查找标签ID
	 * 
	 */
	public Integer selectlableid(DiseaseLabelEntity labelEntity);
	/**
	 * 
	 * insertRelation:(新增对应关系). <br/>
	 * @author q
	 * @param relationEntity
	 * @return
	 */
	public Integer insertRelation(DiseaseLabelPersonRelationEntity relationEntity);
	/**
	 * 
	 * deleteRelationsInLabel:(清空标签下的人员). <br/>
	 * @author q
	 * @param labelId
	 * @return
	 */
	public Integer deleteRelationsInLabel(Integer labelId);
	/**
	 * 
	 * getLabelList:(获取标签列表). <br/>
	 * @author q
	 * @param docTeamId
	 * @return
	 */
	public List<DiseaseLabelVo> getLabelList(String docTeamId);
	/**
	 * 
	 * getPersonNotInLabel:(查询不在标签中的签约人员). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<PersonInformationVo> getPersonNotInLabel(DiseaseLabelVo vo);
	/**
	 * 
	 * getPersonNotInLabel:(查询不在标签中的签约人员总数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Integer getPersonNotInLabelCount(DiseaseLabelVo vo);
	/**
	 * 
	 * getPersonNotInLabel:(查询标签中的人员). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<PersonInformationVo> getPersonInLabel(DiseaseLabelVo vo);
	/**
	 * 
	 * getPersonNotInLabel:(查询标签中的签约人员总数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Integer getPersonInLabelCount(DiseaseLabelVo vo);
	/**
	 * 
	 * getUsersInLabel:(获取标签中的用户账号). <br/>
	 * @author q
	 * @param labelIds：标签ID集合字符串，用逗号隔开
	 * @return
	 */
	public List<String> getUsersInLabel(DiseaseLabelVo vo);
	/**
	 * 
	 * getLabelListWithSelectFlag:(获取某人的标签list，其中选中的标签用selectFlag标示). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<DiseaseLabelVo> getLabelListWithSelectFlag(DiseaseLabelVo vo);
	/**
	 * 
	 * getRelation:获取对应关系（主要用于判断重复）. <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public List<DiseaseLabelPersonRelationEntity> getRelation(DiseaseLabelPersonRelationEntity entity);
	/**
	 * 
	 * deleteRelation:(通过labelId和personId删除对应关系). <br/>
	 * @author q
	 * @return
	 */
	public Integer deleteRelation(DiseaseLabelPersonRelationEntity entity);
	
	/**
	 * 获取居民所在的标签集合
	 * @param personId
	 * @return
	 */
	public List<DiseaseLabelEntity> getLabelsByPersonId(String personId);
}
