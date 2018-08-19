package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.DiseaseLabelEntity;
import com.boco.modules.fdoc.model.DiseaseLabelPersonRelationEntity;
import com.boco.modules.fdoc.model.GroupMsgTemplateEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.vo.DiseaseLabelVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;

public interface DiseaseLabelService {
	/**
	 * 
	 * addLabelAndRelations:(新增标签和人员). <br/>
	 * @author q
	 * @param labelEntity
	 * @param relations
	 * @return
	 */
	public String addLabelAndRelations(DiseaseLabelEntity labelEntity,List<DiseaseLabelPersonRelationEntity> relations);
	/**
	 * 
	 * updateLabelAndRelations:(修改标签和人员). <br/>
	 * @author q
	 * @param labelEntity
	 * @param relations
	 * @return
	 */
	public String updateLabelAndRelations(DiseaseLabelEntity labelEntity,List<DiseaseLabelPersonRelationEntity> relations);
	/**
	 * 
	 * updateLabelAndRelations:(修改标签和人员). <br/>
	 * @author q
	 * @param labelEntity
	 * @param relations
	 * @return
	 */
	public String  updateLabelAndRelations(DiseaseLabelEntity labelEntity,
			List<DiseaseLabelPersonRelationEntity> relationList, String updateFlag);
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
	 * addGroupMsgTemplate:(新增群发模板). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer addGroupMsgTemplate(GroupMsgTemplateEntity entity);
	/**
	 * 
	 * deleteGroupMsgTemplate:(删除群发模板). <br/>
	 * @author q
	 * @param templateId
	 * @return
	 */
	public Integer deleteGroupMsgTemplate(Integer templateId);
	/**
	 * 
	 * updateGroupMsgTemplate:(修改群发模板). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer updateGroupMsgTemplate(GroupMsgTemplateEntity entity);
	/**
	 * 
	 * getTemplateByDocTeam:(根据医生团队ID获取群发模板列表). <br/>
	 * @author q
	 * @return
	 */
	public List<GroupMsgTemplateEntity> getTemplateByDocTeam(String docTeamId);
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
	 * addRelations:(新增对应关系). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public String addRelation(DiseaseLabelPersonRelationEntity entity);
	/**
	 * 
	 * deleteRelation:(通过labelId和personId删除对应关系). <br/>
	 * @author q
	 * @return
	 */
	public Integer deleteRelation(DiseaseLabelPersonRelationEntity entity);
	/**
	 * 
	 * initLabels:(初始化医生团队标签，增加默认标签). <br/>
	 * @author q
	 * @param teamId
	 * @return
	 */
	public String initLabels(String teamId);
	/**
	 * 
	 * deleteLabel:(删除标签). <br/>
	 * @author q
	 * @return
	 */
	public String deleteLabel(Integer labelId);
	
	/**
	 * 查找标签ID
	 * 
	 */
	public Integer selectlableid(DiseaseLabelEntity labelEntity);
	
}
