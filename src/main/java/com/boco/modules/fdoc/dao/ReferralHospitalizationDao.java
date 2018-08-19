package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.ReferralHospitalizationEntity;
import com.boco.modules.fdoc.vo.ReferralHospitalizationVo;

@MyBatisDao
public interface ReferralHospitalizationDao extends CrudDao<ReferralHospitalizationEntity>{
	/**
	 * 获取住院转诊信息
	 * @param id
	 * @return
	 */
	public ReferralHospitalizationEntity getReferralHospitalizationInfo(int id);
	
	/**
	 * 通过referralCode获取住院转诊信息
	 * @param referralCode
	 * @return
	 */
	public ReferralHospitalizationEntity getReferralHospitalizationInfoByCode(String referralCode);
	
	/**
	 * 修改住院转诊流程状态
	 * @param entity
	 * @return
	 */
	public Integer updateReferralHospitalizationStatus(ReferralHospitalizationEntity entity);
	
	/**
	 * 获取签约居民的住院转诊申请记录列表
	 * @param vo
	 * @return
	 */
	public List<ReferralHospitalizationVo> getReferralHospitalizationListBySign(ReferralHospitalizationVo vo);
	
	/**
	 * 获取签约居民的住院转诊申请记录数量
	 * @param vo
	 * @return
	 */
	public Integer getReferralHospitalizationCountBySign(ReferralHospitalizationVo vo);
} 
