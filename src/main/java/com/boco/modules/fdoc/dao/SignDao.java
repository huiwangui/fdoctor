package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SignAgreementEntity;
import com.boco.modules.fdoc.model.UserDocSignEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * ClassName: SignDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 签约相关Dao. <br/>
 * date: 2017年2月8日 上午10:47:00 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
@MyBatisDao
public interface SignDao extends CrudDao<UserDocSignEntity>{
	
	/**
	 * 
	 * updateFamilySignFlag:(签约修改居民标识). <br/>
	 * @author q
	 * @param personEntity
	 * @return
	 */
	public Integer updateSignFlag(PersonInformationEntity personEntity);
	/**
	 * 
	 * getSignedList:(查询已签约人员列表). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<PersonInformationVo> getSignedList(SignVo vo);
	/**
	 * 
	 * getSignedCount:(查询已签约人数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Integer getSignedCount(SignVo vo);
	/**
	 * 
	 * getSignDetail:(查看签约详情). <br/>
	 * @author q
	 * @param signId
	 * @return
	 */
	public SignVo getSignDetail(Integer signId);
	/**
	 * 
	 * getSignDetail:(通过身份证号获取签约详情). <br/>
	 * @author q
	 * @param signId
	 * @return
	 */
	public SignVo getSignDetailByIdCard(String idCard);
	/**
	 * 
	 * getSignTeamUsers:(获取同某人签约的医生团队账号列表，用于推送). <br/>
	 * @author q
	 * @return
	 */
	public List<String> getSignTeamUsers(String personId);
	/**
	 * 
	 * getSignFamilyUsers:(获取医生签约下的所有居民账号列表，用于推送). <br/>
	 * @author q
	 * @param userName
	 * @return
	 */
	public List<String> getSignFamilyUsers(String userName);
	/**
	 * 
	 * getSignDoctorTeamInfo:(获取与某人签约的医生团队信息). <br/>
	 * @author q
	 * @param personId
	 * @return
	 */
	public List<DoctorDetailVo> getSignDoctorTeamInfo(String personId);
	
	/**
	 * 
	 * getSignedCountByRegion:(根据区划获取签约人数). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Integer getSignedCountByRegion(SignVo vo);
	
	/**
	 * 
	 * getSignedUserAccound:(获取医生已签约的用户app账号列表). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<UserVo> getSignedUserAccound(SignVo vo);
	
	/**
	 * 
	 * getSignedUserAccoundCount:(获取医生已签约的用户app账号总数). <br/>
	 * @author q
	 * @return
	 */
	public Integer getSignedUserAccoundCount(SignVo vo);
	
	/**
	 * 获取签约信息
	 * @param entity
	 * @return
	 */
	public UserDocSignEntity getSignRecord(UserDocSignEntity entity);
	/**
	 * Tilte: getYearOfSign 
	 * Description:获取签约年限
	 * @param entity
	 * @return int
	 * @author h
	 */
	public int getYearOfSign(UserDocSignEntity entity);
	/**
	 * Tilte: getYearOfSignByPersonId 
	 * Description:根据personID获取签约年限
	 * @param personId
	 * @return int
	 * @author h
	 */
	public int getYearOfSignByPersonId(String personId);
	
	/**
	 * 获取居民列表，包括签约的和未签约的
	 * @param sivnVo
	 * @return
	 */
	public List<PersonInformationVo> getPersonListWithSignInfo(PersonInformationVo vo);
	
	/**
	 * 获取居民人数，包括签约的和未签约的
	 * @param sivnVo
	 * @return
	 */
	public Integer getPersonCountWithSignInfo(PersonInformationVo vo);
	
	/**
	 * 获取某人的签约信息
	 * @param personId
	 * @return
	 */
	public UserDocSignEntity getSignRecordByPersonId(String personId);
	
	/**
	 * 根据居民ID获取签约简略信息
	 * @param personId
	 * @return
	 */
	public SignVo getSimpleSignInfo(String personId);
	/**
	 * Tilte: getPersonInformationDetailByPersonId 
	 * Description:获取居民详情
	 * @param vo
	 * @return SignVo
	 * @author h
	 */
	public SignVo getPersonInformationDetailByPersonId(SignVo vo);
	/**
	 * Tilte: getSurrenderPersonInformationDetailByPersonId 
	 * Description:获取解约居民详情（医生端）
	 * @param vo
	 * @return SignVo
	 * @author h
	 */
	public SignVo getSurrenderPersonInformationDetailByPersonId(SignVo vo);
}
