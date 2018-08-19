package com.boco.modules.fdoc.service.expert;

import java.util.List;

import com.boco.modules.fdoc.model.expert.ExpertAccountEntity;
import com.boco.modules.fdoc.model.expert.ExpertInformationEntity;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.expert.ExpertInformationVo;

public interface ExpertService {

	/**
	 * Tilte: getExpertInfoByExpertId 
	 * Description:
	 * @param expertId
	 * @return ExpertInformationEntity
	 * @author h
	 */
	ExpertInformationVo getExpertInfoByExpertId(String expertId);

	/**
	 * Tilte: updateExpertUser 
	 * Description:修改专家信息
	 * @param entity
	 * @param object
	 * @return int
	 * @author h
	 */
	int updateExpertUser(ExpertAccountEntity entity, ExpertInformationEntity object);

	/**
	 * Tilte: getSignedCount 
	 * Description:查询总数
	 * @param vo
	 * @return int
	 * @author h
	 */
	int getSignedCount(ExpertInformationVo vo);

	/**
	 * Tilte: getSignedList 
	 * Description:查询签约列表
	 * @param vo
	 * @return List<PersonInformationVo>
	 * @author h
	 */
	List<PersonInformationVo> getSignedList(ExpertInformationVo vo);

	/**
	 * Tilte: getSignedExpertCountOfPersonId 
	 * Description:查询专家列表总数（居民端）
	 * @param vo
	 * @return int
	 * @author h
	 */
	int getSignedExpertCountOfPersonId(ExpertInformationVo vo);

	/**
	 * Tilte: getSignedExpertListOfPersonId 
	 * Description:查询专家列表（居民端）
	 * @param vo
	 * @return List<PersonInformationVo>
	 * @author h
	 */
	List<ExpertInformationVo> getSignedExpertListOfPersonId(ExpertInformationVo vo);

	/**
	 * Tilte: addExpertAndResidentRelationship 
	 * Description:添加关注
	 * @param entity
	 * @return int
	 * @author h
	 */
	int addExpertAndResidentRelationship(ExpertInformationVo entity);

	/**
	 * Tilte: cancelExpertAndResidentRelationship 
	 * Description:取消关注
	 * @param entity
	 * @return int
	 * @author h
	 */
	int cancelExpertAndResidentRelationship(ExpertInformationVo entity);

	/**
	 * Tilte: getRelationship 
	 * Description:查询是否关注
	 * @param vo
	 * @return ExpertInformationVo
	 * @author h
	 */
	ExpertInformationVo getRelationship(ExpertInformationVo vo);

	/**
	 * Tilte: getExpertInfoOfPersonId 
	 * Description:获取专家基本信息
	 * @param vo
	 * @return ExpertInformationVo
	 * @author h
	 */
	ExpertInformationVo getExpertInfoOfPersonId(ExpertInformationVo vo);

}
