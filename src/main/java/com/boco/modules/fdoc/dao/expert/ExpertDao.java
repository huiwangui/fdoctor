package com.boco.modules.fdoc.dao.expert;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.expert.ExpertInformationEntity;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.expert.ExpertInformationVo;
@MyBatisDao
public interface ExpertDao extends CrudDao<ExpertInformationEntity> {

	/**
	 * Tilte: getExpertInfoByExpertId 
	 * Description:
	 * @param expertId
	 * @return ExpertInformationEntity
	 * @author h
	 */
	ExpertInformationVo getExpertInfoByExpertId(String expertId);
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
	 * Tilte: getLabelListByPersonId 
	 * Description:获取个人慢病
	 * @param personId
	 * @return List<String>
	 * @author h
	 */
	List<String> getLabelListByPersonId(String personId);
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
	/**
	 * Tilte: getExpertInfoBySurrenderId 
	 * Description:根据解约请求id查询专家信息
	 * @param surrenderId
	 * @return ExpertInformationVo
	 * @author h
	 */
	ExpertInformationVo getExpertInfoBySurrenderId(Integer surrenderId);

}
