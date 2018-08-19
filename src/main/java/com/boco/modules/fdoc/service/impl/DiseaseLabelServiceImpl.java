package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.constants.BusinessConstants;
import com.boco.modules.fdoc.dao.DiseaseLabelDao;
import com.boco.modules.fdoc.dao.GroupMsgTemplateDao;
import com.boco.modules.fdoc.dao.PersonDeseaseInfoDao;
import com.boco.modules.fdoc.model.DiseaseLabelEntity;
import com.boco.modules.fdoc.model.DiseaseLabelPersonRelationEntity;
import com.boco.modules.fdoc.model.GroupMsgTemplateEntity;
import com.boco.modules.fdoc.service.DiseaseLabelService;
import com.boco.modules.fdoc.vo.DiseaseLabelVo;
import com.boco.modules.fdoc.vo.PersonDeseaseInfoVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;

@Service
public class DiseaseLabelServiceImpl implements DiseaseLabelService{
	
	@Resource
	DiseaseLabelDao diseaseLabelDao;
	@Resource
	GroupMsgTemplateDao templateDao;
	@Resource
	PersonDeseaseInfoDao personDeseaseInfoDao;

	@Override
	@Transactional
	public String addLabelAndRelations(DiseaseLabelEntity labelEntity,
			List<DiseaseLabelPersonRelationEntity> relations) {
		//插入标签表
		diseaseLabelDao.insertLabel(labelEntity);
		//遍历对应关系列表，设置labelID插入
		if(relations!=null && relations.size()>0){
			for (DiseaseLabelPersonRelationEntity diseaseLabelPersonRelationEntity : relations) {
				diseaseLabelPersonRelationEntity.setLabelId(labelEntity.getId());
				diseaseLabelDao.insertRelation(diseaseLabelPersonRelationEntity);
				//修改t_person_desease_info表中记录，便于考核系统统计
				updateTPersonDeseaseInfo(labelEntity, diseaseLabelPersonRelationEntity,1);
			}
		}
		return BusinessConstants.SUCCESS_SIGN;
	}

	

	@Override
	@Transactional
	public String updateLabelAndRelations(DiseaseLabelEntity labelEntity,
			List<DiseaseLabelPersonRelationEntity> relations) {
		//修改标签表
		if (!BusinessConstants.DEFAULT_FLAG_DEFAULT.equals(labelEntity.getDefaultFlag())) {
			diseaseLabelDao.updateLabel(labelEntity);
		}
		//清空标签下的对应关系
		diseaseLabelDao.deleteRelationsInLabel(labelEntity.getId());
		//重新插入标签下的对应关系
		for (DiseaseLabelPersonRelationEntity diseaseLabelPersonRelationEntity : relations) {
			diseaseLabelPersonRelationEntity.setLabelId(labelEntity.getId());
			diseaseLabelDao.insertRelation(diseaseLabelPersonRelationEntity);
		}
		return BusinessConstants.SUCCESS_SIGN;
	}
	
	@Override
	@Transactional
	public String updateLabelAndRelations(DiseaseLabelEntity labelEntity,
			List<DiseaseLabelPersonRelationEntity> relations, String updateFlag) {
		//修改标签表
		if (!BusinessConstants.DEFAULT_FLAG_DEFAULT.equals(labelEntity.getDefaultFlag())) {
			diseaseLabelDao.updateLabel(labelEntity);
		}
		if("0".equals(updateFlag)){
			for (DiseaseLabelPersonRelationEntity diseaseLabelPersonRelationEntity : relations) {
				diseaseLabelPersonRelationEntity.setLabelId(labelEntity.getId());
				diseaseLabelDao.deleteRelation(diseaseLabelPersonRelationEntity);
				//修改t_person_desease_info表中记录，便于考核系统统计
				updateTPersonDeseaseInfo(labelEntity, diseaseLabelPersonRelationEntity,0);
			}
			
		}else if ("1".equals(updateFlag)) {
			for (DiseaseLabelPersonRelationEntity diseaseLabelPersonRelationEntity : relations) {
				diseaseLabelPersonRelationEntity.setLabelId(labelEntity.getId());
				diseaseLabelDao.insertRelation(diseaseLabelPersonRelationEntity);
				//修改t_person_desease_info表中记录，便于考核系统统计
				updateTPersonDeseaseInfo(labelEntity, diseaseLabelPersonRelationEntity,1);
			}
		}
		
		return BusinessConstants.SUCCESS_SIGN;
	}

	@Override
	public List<DiseaseLabelVo> getLabelList(String docTeamId) {
		return diseaseLabelDao.getLabelList(docTeamId);
	}

	@Override
	public List<PersonInformationVo> getPersonNotInLabel(DiseaseLabelVo vo) {
		return diseaseLabelDao.getPersonNotInLabel(vo);
	}

	@Override
	public Integer getPersonNotInLabelCount(DiseaseLabelVo vo) {
		return diseaseLabelDao.getPersonNotInLabelCount(vo);
	}

	@Override
	public Integer addGroupMsgTemplate(GroupMsgTemplateEntity entity) {
		
		return templateDao.insert(entity);
	}

	@Override
	public Integer deleteGroupMsgTemplate(Integer templateId) {
		return templateDao.delete(String.valueOf(templateId));
	}

	@Override
	public Integer updateGroupMsgTemplate(GroupMsgTemplateEntity entity) {
		return templateDao.update(entity);
	}

	@Override
	public List<GroupMsgTemplateEntity> getTemplateByDocTeam(String docTeamId) {
		return templateDao.getTemplateByDocTeam(docTeamId);
	}

	@Override
	public List<PersonInformationVo> getPersonInLabel(DiseaseLabelVo vo) {
		return diseaseLabelDao.getPersonInLabel(vo);
	}

	@Override
	public Integer getPersonInLabelCount(DiseaseLabelVo vo) {
		return diseaseLabelDao.getPersonInLabelCount(vo);
	}

	@Override
	public List<String> getUsersInLabel(DiseaseLabelVo vo) {
		return diseaseLabelDao.getUsersInLabel(vo);
	}

	@Override
	public List<DiseaseLabelVo> getLabelListWithSelectFlag(DiseaseLabelVo vo) {
		return diseaseLabelDao.getLabelListWithSelectFlag(vo);
	}

	@Override
	public List<DiseaseLabelPersonRelationEntity> getRelation(
			DiseaseLabelPersonRelationEntity entity) {
		return diseaseLabelDao.getRelation(entity);
	}

	@Override
	public String addRelation(DiseaseLabelPersonRelationEntity entity) {
		diseaseLabelDao.insertRelation(entity);
		return BusinessConstants.SUCCESS;
	}

	@Override
	public Integer deleteRelation(DiseaseLabelPersonRelationEntity entity) {
		return diseaseLabelDao.deleteRelation(entity);
	}

	@Override
	@Transactional
	public String initLabels(String teamId) {
		//插入慢病标签
		String[] labelNames = {"高血压","糖尿病","重性精神病","孕产妇","儿童","老年人","普通成人","特困户","残疾人","低保","五保","计生特户","结核","流动人口"};
		int[] labelCodes = {1,3,2,29,6,7,5,12,12,12,12,12,4,12};
		DiseaseLabelEntity entity = new DiseaseLabelEntity();
		entity.setDefaultFlag(BusinessConstants.DEFAULT_FLAG_DEFAULT);
		entity.setDocTeamId(teamId);
		for (int i = 0; i < labelNames.length; i++) {
			entity.setLabelName(labelNames[i]);
			entity.setDiseaseCode(labelCodes[i]);
			diseaseLabelDao.insertLabel(entity);
		}
		//插入群发关怀模板
		String[] templateNames = {"高血压关怀","糖尿病关怀","孕产妇关怀","0-6岁儿童关怀","老年人关怀"};
		GroupMsgTemplateEntity template = new GroupMsgTemplateEntity();
		template.setDefaultFlag(BusinessConstants.DEFAULT_FLAG_DEFAULT);
		template.setDocTeamId(teamId);
		for (int i = 0; i < templateNames.length; i++) {
			template.setTemplateName(templateNames[i]);
			templateDao.insert(template);
		}
		return BusinessConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String deleteLabel(Integer labelId) {
		diseaseLabelDao.deleteLabel(labelId);
		diseaseLabelDao.deleteRelationsInLabel(labelId);
		return BusinessConstants.SUCCESS;
	}
    /**查找标签ID*/
	@Override
	public Integer selectlableid(DiseaseLabelEntity labelEntity) {
		
		return diseaseLabelDao.selectlableid(labelEntity);
	}
	/**
	 * Tilte: updateTPersonDeseaseInfo 
	 * Description:修改t_person_desease_info表中记录，便于考核系统统计
	 * @param labelEntity
	 * @param diseaseLabelPersonRelationEntity void
	 * @author h
	 */
	private void updateTPersonDeseaseInfo(DiseaseLabelEntity labelEntity,
			DiseaseLabelPersonRelationEntity diseaseLabelPersonRelationEntity,int flag) {		
		String labelName =labelEntity.getLabelName();
		PersonDeseaseInfoVo vo = new PersonDeseaseInfoVo();
		vo.setUpdateTime(new Date());
		vo.setPersonId(diseaseLabelPersonRelationEntity.getPersonId());
		switch(labelName){
		 case  "高血压":
			 if(flag ==1){
				 vo.setHyperFlag("1");
			 }else{
				 vo.setHyperFlag("0");
			 }			
			 personDeseaseInfoDao.updateFlag(vo);
			 break;
		 case  "糖尿病":
			 if(flag ==1){
				 vo.setDiabetesFlag("1");
			 }else{
				 vo.setDiabetesFlag("0");
			 }		
			 personDeseaseInfoDao.updateFlag(vo);
			 break;
		 case  "儿童":
			 if(flag ==1){
				 vo.setChildFlag("1"); 
			 }else{
				 vo.setChildFlag("0"); 
			 }			
			 personDeseaseInfoDao.updateFlag(vo);
			 break;
		 case  "普通成人":
			 if(flag ==1){
				 vo.setAdultFlag("1");
			 }else{
				 vo.setAdultFlag("0");
			 }		
			 personDeseaseInfoDao.updateFlag(vo);
			 break;
		 case  "老年人":
			 if(flag ==1){
				 vo.setOldmFlag("1");
			 }else{
				 vo.setOldmFlag("0");
			 }		 
			 personDeseaseInfoDao.updateFlag(vo);
			 break;
		 case  "重型精神病":
			 if(flag ==1){
				 vo.setMajorPsychosisFlag("1");
			 }else{
				 vo.setMajorPsychosisFlag("0");
			 }			 
			 personDeseaseInfoDao.updateFlag(vo);
			 break;
		 case  "孕产妇":
			 if(flag ==1){
				 vo.setMaternalFlag("1");
			 }else{
				 vo.setMaternalFlag("0");
			 }			
			 personDeseaseInfoDao.updateFlag(vo);
			 break;
		 default:
			 break;
		}
	}

}
