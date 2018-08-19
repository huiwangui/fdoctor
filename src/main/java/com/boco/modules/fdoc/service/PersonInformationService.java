package com.boco.modules.fdoc.service;

import java.util.List;
import java.util.Map;

import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;


public interface PersonInformationService {
	/**
	 * 
	 * getPersonInfoByScan:(医生端扫健康卡获取居民信息). <br/>
	 * @author q
	 * @param personCode
	 * @return
	 */
    public PersonInformationVo getPersonInfoByIdCard(PersonInformationEntity entity);
    /**
     * Tilte: getSimplePersonInfoByPersonId 
     * Description:查询居民简单信息及与他签约的医生团队队长电话号码 
     * @param personId
     * @return PersonInformationVo
     * @author h
     */
    public PersonInformationVo getSimplePersonInfoByPersonId(String personId);
    /**
     * 
     * getPersonInformationByPesronId:(根据居民ID获取居民信息). <br/>
     * @author q
     * @param personId
     * @return
     */
    public PersonInformationEntity getPersonInformationByPesronId(String personId);
    /**
     * 
     * getFamilyMember:(根据家庭ID获取家庭成员). <br/>
     * @author q
     * @param familyId
     * @return
     */
    public List<PersonInformationVo> getFamilyMember(String familyId);
    /**
     * 
     * getMasterInfo:(获取户主信息). <br/>
     * @author q
     * @param familyId
     * @return
     */
    public PersonInformationVo getMasterInfo(String familyId);
    /**
     * 
     * getPersonDetailByPersonId:(获取居民详细信息). <br/>
     * @author q
     * @param personId
     * @return
     */
    public PersonInformationVo getPersonDetailByPersonId(String personId);
    /**
     * 根据居民uid查询其家庭成员
     * @param uid
     * @return
     */
    public List<PersonInformationEntity> getPersonInformationByUid(String uid);
    /**
     * 将基卫一条数据添加至本地
     * @param pVo
     * @return
     */
    public Integer insertPerson(PersonInformationVo pVo);
    /**
     * 根据基卫接口身份证更新本地数据
     * @param pvo
     * @return
     */
    public Integer updatePerson(PersonInformationVo pvo);
    
    /**
     * 新增居民，若传入的paramMap为空，则只新增本地，否则新增本地的同时调用卫计委新增接口
     * @param entity
     * @param paramMap
     * @return
     */
    public String addPerson(PersonInformationEntity entity, Map<String, Object> paramMap);
    
    /**
	 * 删除居民档案
	 * @param personId
	 * @return
	 */
	public String deletePerson(String personId, String productCode);
	/**
	 * Title PersonInformationService.java
	 * Description: 重置户主标识
	 * @param familyId
	 * @author lzz
	 */
	public Integer resetMaster(String familyId);
	/**
	 * Title PersonInformationService.java
	 * Description: 设置户主
	 * @param masterIdCard
	 * @author lzz
	 */
	public Integer setMaster(String masterIdCard);
	/**
	 * Title PersonInformationService.java
	 * Description: 根据身份证获取家庭id
	 * @param idCard
	 * @return
	 * @author lzz
	 */
	public String getFamilyId(String idCard);
	/**
	 * 根据personId修改信息
	 * Title PersonInformationService.java
	 * Description: 
	 * @param pVo
	 * @return
	 * @author lzz
	 */
	public int updatePerson2(PersonInformationVo pVo);
	/**
	 * 通过基卫Id获取人员id
	 * Title PersonInformationService.java
	 * Description: 
	 * @param jwPersonId
	 * @return
	 * @author lzz
	 */
	public String getPersonIdByJw(String jwPersonId);
	/**
	 * 通过基卫id获取人员信息
	 * Title PersonInformationService.java
	 * Description: 
	 * @param jwPersonId
	 * @return
	 * @author lzz
	 */
	public PersonInformationVo getPersonDetailByJwPersonId(String jwPersonId);
	/**
	 * Tilte: updateMobile 
	 * Description:修改居民信息
	 * @param mobile
	 * @return int
	 * @author h
	 */
	public int updateMobile(PersonInformationVo entity);
	/**
	 * Tilte: getPersonInformationVoListByDocTeamId 
	 * Description:获取解约记录列表
	 * @param vo
	 * @return List<PersonInformationVo>
	 * @author h
	 */
	public List<PersonInformationVo> getPersonInformationVoListByDocTeamId(SurrenderRequestVo vo); 
	/**
	 * Tilte: getPersonInformationVoListCountByDocTeamId 
	 * Description:获取解约记录列表总数
	 * @param vo
	 * @return int
	 * @author h
	 */
	public int getPersonInformationVoListCountByDocTeamId(SurrenderRequestVo vo);
}
