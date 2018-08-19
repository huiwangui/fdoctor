package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.ReferralHospitalizationEntity;
import com.boco.modules.fdoc.model.ReferralOutpatientEntity;
import com.boco.modules.fdoc.vo.ReferralHospitalizationVo;
import com.boco.modules.fdoc.vo.ReferralOutpatientVo;

public interface ReferralHospitalizationService {
	/**
	 * 新增住院转诊申请
	 * @param entity
	 * @return
	 */
	public String insertReferralHospitalizationApply(ReferralHospitalizationEntity entity);
	
	/**
	 * 修改住院转诊申请
	 * @param entity
	 * @return
	 */
	public String updateReferralHospitalizationApply(ReferralHospitalizationEntity entity);
	
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
	public String updateReferralHospitalizationStatus(ReferralHospitalizationEntity entity);
	
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
