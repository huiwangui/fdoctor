package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;

@MyBatisDao
public interface PersonInformationDao extends CrudDao<PersonInformationEntity>{
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
     * getPersonInformationByPesronId:(根据居民ID获取居民信息单表). <br/>
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
     * getPersonInfoByIdcardAndName:(根据身份证和姓名获取居民信息). <br/>
     * @author q
     * @return
     */
    public PersonInformationEntity getPersonInfoByIdcardAndName(String idCard, String personName);
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
     * 
     * getMemberCountInFamily:(获取家庭人员总数). <br/>
     * @author q
     * @param familyId
     * @return
     */
    public Integer getMemberCountInFamily(String familyId);
    /**
     * 根据居民uid查询其家庭成员
     * @param uid
     * @return
     */
    public List<PersonInformationEntity> getPersonInformationByUid(String uid);
    /**
     * 
     * getPersonCountByRegion:(根据区划查询居民人数). <br/>
     * @author q
     * @param vo
     * @return
     */
    public Integer getPersonCountByRegion(PersonInformationVo vo);
    /**
     * 将基卫一条数据添加至本地
     * @param pVo
     * @return
     */
	public Integer insertPerson(PersonInformationVo pVo);
	/**
	 * 根据基卫接口更新本地数据
	 * @param pvo
	 * @return
	 */
	public Integer updatePerson(PersonInformationVo pvo);
	/**
	 * 删除居民档案
	 * @param personId
	 * @return
	 */
	public Integer deletePerson(String personId);
	/**
	 * 
	 * Title PersonInformationDao.java
	 * Description: 重置户主
	 * @param familyId
	 * @return
	 */
	public Integer resetMaster(String familyId);
	/**
	 * Title PersonInformationDao.java
	 * Description: 设置户主
	 * @param masterIdCard
	 * @return
	 */
	public Integer setMaster(String idCard); 
	/**
	 * Description: 根据身份证获取家庭Id
	 * @param idCard
	 * @return
	 * @author lzz
	 */
	public String getFamilyId(String idCard);
	/**
	 * 根据personid修改信息
	 * Title PersonInformationDao.java
	 * Description: 
	 * @param pVo
	 * @return
	 * @author lzz
	 */
	public int updatePerson2(PersonInformationVo pVo);
	/**
	 * 只根据身份证号码查询
	 * @param entity
	 * @return
	 *
	 */
	PersonInformationEntity getPersonInfoByIdcardOnly(String idCard);
	/**
	 * 通过基卫id获取人员id
	 * Title PersonInformationDao.java
	 * Description: 
	 * @param jwPersonId
	 * @return
	 * @author lzz
	 */
	public String getPersonIdByJw(String jwPersonId);
	/**
	 * 通过基卫id获取人员信息
	 * Title PersonInformationDao.java
	 * Description: 
	 * @param jwPersonId
	 * @return
	 * @author lzz
	 */
	public PersonInformationVo getPersonInformationByJwPesronId(String jwPersonId);
	/**
	 * 通过基卫id获取人员详细信息
	 * Title PersonInformationDao.java
	 * Description: 
	 * @param jwPersonId
	 * @return
	 * @author lzz
	 */
	public PersonInformationVo getPersonDetailByJwPersonId(String jwPersonId);
	/**
	 * 根据身份证修改数据
	 * Title PersonInformationDao.java
	 * Description: 
	 * @param entity
	 * @author lzz
	 */
	public void updateByEntity(PersonInformationEntity entity);
	/**
	 * Tilte: updateMobile 
	 * Description:修改居民信息
	 * @param mobile
	 * @return int
	 * @author h
	 */
	public int updatePersonInformation(PersonInformationVo entity); 
	/**
	 * Tilte: getPersonDetailByUId 
	 * Description:根据uid获取人员详细信息
	 * @param uid
	 * @return PersonInformationVo
	 * @author h
	 */
	public PersonInformationVo getPersonDetailByUId(String uid);
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
