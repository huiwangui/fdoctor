package com.boco.modules.fdoc.service.impl.expert;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.exception.IMException;
import com.boco.common.im.IMUtils;
import com.boco.modules.fdoc.dao.expert.ExpertAccountDao;
import com.boco.modules.fdoc.dao.expert.ExpertDao;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.model.expert.ExpertAccountEntity;
import com.boco.modules.fdoc.model.expert.ExpertInformationEntity;
import com.boco.modules.fdoc.service.expert.ExpertService;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.expert.ExpertInformationVo;
/**
 * Tilte: ExpertServiceImpl 
 * Description:
 * @author h
 * @date  2017年9月22日下午4:35:27
 * @version 1.0
 *  
 */
@Service
public class ExpertServiceImpl implements ExpertService {
	@Resource
	ExpertDao expertDao;
	@Resource
	ExpertAccountDao expertAccountDao;
	 
	@Override
	public ExpertInformationVo getExpertInfoByExpertId(String expertId) {
		 
		return expertDao.getExpertInfoByExpertId(expertId);
	}

	@Transactional
	@Override
	public int updateExpertUser(ExpertAccountEntity accountEntity, ExpertInformationEntity entity) {
		int returnInt = 0;
		Date updateTime = new Date();
		/**
		 * 如果专家用户对象不为空，则修改用户对象信息
		 */
		if (accountEntity != null) {
			accountEntity.setUpdateTime(updateTime);
			returnInt = expertAccountDao.update(accountEntity);
			Integer changeDocUser = IMUtils.changeExpertUser(accountEntity);
			if (changeDocUser == ApiStatusEnum.FAIL.getKey()) {
				throw new IMException();
			}
		}
		/**
		 * 如果专家对象不为空，则修改专家对象信息
		 */
		if (entity != null) {			 
			entity.setUpdateTime(updateTime);
			//returnInt = docDao.update(docEntity);
			returnInt = expertDao.update(entity);
		}
		return returnInt;
	}

	 
	@Override
	public int getSignedCount(ExpertInformationVo vo) {
	
		return	expertDao.getSignedCount(vo); 
	}
	@Override
	public List<PersonInformationVo> getSignedList(ExpertInformationVo vo) {	 
		List<PersonInformationVo> voList = expertDao.getSignedList(vo);
		//获取个人慢病
		if(voList!=null&&voList.size()>0){
			for (PersonInformationVo personInformationVo : voList) {
				List<String> labelList = expertDao.getLabelListByPersonId(personInformationVo.getPersonId());				 
			    personInformationVo.setChronicDiseaseList(labelList);
			}
		}
		return voList;
	}

	 
	@Override
	public int getSignedExpertCountOfPersonId(ExpertInformationVo vo) {
		 
		return expertDao.getSignedExpertCountOfPersonId(vo);
	}

	 
	@Override
	public List<ExpertInformationVo> getSignedExpertListOfPersonId(ExpertInformationVo vo) {
		 
		return expertDao.getSignedExpertListOfPersonId(vo);
	}

	 
	@Override
	public int addExpertAndResidentRelationship(ExpertInformationVo entity) {
		entity.setCreateTime(new Date()); 
		return expertDao.addExpertAndResidentRelationship(entity);
	}

	 
	@Override
	public int cancelExpertAndResidentRelationship(ExpertInformationVo entity) {
		 
		return expertDao.cancelExpertAndResidentRelationship(entity);
	}

	 
	@Override
	public ExpertInformationVo getRelationship(ExpertInformationVo vo) {
		 
		return expertDao.getRelationship(vo);
	}

	 
	@Override
	public ExpertInformationVo getExpertInfoOfPersonId(ExpertInformationVo vo) {
		 
		return expertDao.getExpertInfoOfPersonId(vo);
	}

}
