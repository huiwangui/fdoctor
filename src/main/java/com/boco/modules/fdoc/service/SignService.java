package com.boco.modules.fdoc.service;

import java.util.List;
import java.util.Map;

import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.model.PersonDeseaseInfoEntity;
import com.boco.modules.fdoc.model.SigServicepackEntity;
import com.boco.modules.fdoc.model.SignAgreementEntity;
import com.boco.modules.fdoc.model.UserDocSignEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SigServicepackVo;
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.UserVo;


/**
 * 签约相关service层
 * @author q
 *
 */
public interface SignService {
	
	/**
	 * 
	 * sign:(新增签约). <br/>
	 * @author q
	 * @param signEntity
	 * @param agreementEntity
	 * @return
	 */
	public Map<String, Object> sign(UserDocSignEntity signEntity, List<PersonDeseaseInfoEntity> deseaseInfo);
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
	 * getSignedCountByRegion:(查询区划范围内的签约人数). <br/>
	 * @author q
	 * @param regionCode
	 * @return
	 */
	public SignVo getSignedCountByRegion(String regionCode);
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
	 * 获取包含签约信息的居民列表
	 * @param sivnVo
	 * @return
	 */
	public List<PersonInformationVo> getPersonListWithSignInfo(PersonInformationVo vo);
	
	/**
	 * 获取居民人数
	 * @param sivnVo
	 * @return
	 */
	public Integer getPersonCountWithSignInfo(PersonInformationVo vo);
	
	/**
	 * 根据居民ID获取签约简略信息
	 * @param personId
	 * @return
	 */
	public SignVo getSimpleSignInfo(String personId);
	 
	/**
	 * Tilte: getServicePacksByPersonId 
	 * Description:查询服务包
	 * @param entity
	 * @return List<SigServicepackEntity>
	 * @author h
	 */
	public List<SigServicepackVo> getServicePacksByPersonId(UserDocSignEntity entity);
	/**
	 * Tilte: getPersonInformationDetailByPersonId 
	 * Description:获取居民详情
	 * @param vo
	 * @return SignVo
	 * @author h
	 */
	public SignVo getPersonInformationDetailByPersonId(SignVo vo);
}
