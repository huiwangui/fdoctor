package com.boco.modules.fdoc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.dao.PersonInformationDao;
import com.boco.modules.fdoc.exception.PersonRecordException;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;

@Service
public class PersonInformationServiceImpl implements PersonInformationService {

	@Resource
	PersonInformationDao personDao;

	@Override
	public PersonInformationVo getPersonInfoByIdCard(
			PersonInformationEntity entity) {
		return personDao.getPersonInfoByIdCard(entity);
	}

	@Override
	public PersonInformationEntity getPersonInformationByPesronId(
			String personId) {
		return personDao.getPersonInformationByPesronId(personId);
	}

	@Override
	public List<PersonInformationVo> getFamilyMember(String familyId) {
		return personDao.getFamilyMember(familyId);
	}

	@Override
	public PersonInformationVo getMasterInfo(String familyId) {
		return personDao.getMasterInfo(familyId);
	}

	@Override
	public PersonInformationVo getPersonDetailByPersonId(String personId) {
		return personDao.getPersonDetailByPersonId(personId);
	}

	@Override
	public List<PersonInformationEntity> getPersonInformationByUid(String uid) {
		return personDao.getPersonInformationByUid(uid);
	}

	@Override
	public Integer insertPerson(PersonInformationVo pVo) {
		return personDao.insertPerson(pVo);
	}

	@Override
	public Integer updatePerson(PersonInformationVo pvo) {
		return personDao.updatePerson(pvo);
	}

	@Override
	@Transactional
	public String addPerson(PersonInformationEntity entity,
			Map<String, Object> paramMap) {
		System.out.println(JsonUtils.getJson(entity));
		// 设置状态为正常、未签约
		entity.setState(Integer.parseInt(BusinessConstants.USER_STATUS_NORMAL));
		entity.setIfSign(Integer.parseInt(BusinessConstants.NO));

		// 插入表
		PersonInformationVo personVo2=personDao.getPersonInfoByIdCard(entity);
		if(personVo2 ==null){
			entity.setPersonId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
			personDao.insert(entity);
		}else{
			personDao.updateByEntity(entity);
		}

		// 调用卫计委接口
		if (paramMap != null) {
			String returnData = RestfulUtils.getPublicData("55-14", paramMap);
			Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(returnData);
			if ((Integer) jsonMap.get("Result") == 0) {
				// 返回result为0，抛出异常，回滚新增操作
				throw new PersonRecordException((String) jsonMap.get("Msg"));
			}
		}
		return BusinessConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String deletePerson(String personId, String productCode) {
		personDao.deletePerson(personId);
		PersonInformationEntity pEntity=personDao.getPersonInformationByPesronId(personId);
		if(!StringUtils.isNotEmpty(pEntity.getJwPersonId())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.HAS_NOT_RECORD.getKey(), ApiStatusEnum.HAS_NOT_RECORD.getValue()));
		}
		//封装调用卫计委接口的参数Map
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PersonID", pEntity.getJwPersonId());
		paramMap.put("ProductCode", productCode);
		
		String returnData = RestfulUtils.getPublicData("55-13", paramMap);
        Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(returnData);
        if ((Integer) jsonMap.get("Result") == 0) {
			// 返回result为0，抛出异常，回滚新增操作
			throw new PersonRecordException((String) jsonMap.get("Msg"));
		}
        
		return BusinessConstants.SUCCESS;
	}

	@Override
	public Integer resetMaster(String familyId) {
		return personDao.resetMaster(familyId);
	}

	@Override
	public Integer setMaster(String masterIdCard) {
		return personDao.setMaster(masterIdCard);
		
	}

	@Override
	public String getFamilyId(String idCard) {
		return personDao.getFamilyId(idCard);
	}

	@Override
	public int updatePerson2(PersonInformationVo pVo) {
		//return personDao.updatePerson2(pVo);
		return personDao.updatePersonInformation(pVo);
	}

	@Override
	public String getPersonIdByJw(String jwPersonId) {
		return personDao.getPersonIdByJw(jwPersonId);
	}

	@Override
	public PersonInformationVo getPersonDetailByJwPersonId(String jwPersonId) {
		return personDao.getPersonDetailByJwPersonId(jwPersonId);
	}
 
	@Override
	public int updateMobile(PersonInformationVo entity) {
		 
		return personDao.updatePersonInformation(entity);
	}
 
	@Override
	public PersonInformationVo getSimplePersonInfoByPersonId(String personId) {
		 
		return personDao.getSimplePersonInfoByPersonId(personId);
	}
 
	@Override
	public List<PersonInformationVo> getPersonInformationVoListByDocTeamId(SurrenderRequestVo vo) {
	 
		return personDao.getPersonInformationVoListByDocTeamId(vo);
	}

	 
	@Override
	public int getPersonInformationVoListCountByDocTeamId(SurrenderRequestVo vo) {
		 
		return personDao.getPersonInformationVoListCountByDocTeamId(vo);
	}
}
