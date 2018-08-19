package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.exception.CommonException;
import com.boco.modules.fdoc.model.ReferralOutpatientEntity;
import com.boco.modules.fdoc.vo.ReferralOutpatientVo;

public interface ReferralOutpatientService {
	/**
	 * 新增门诊转诊申请
	 * @param entity
	 * @return
	 */
	public String insertReferralOutpatientApply(ReferralOutpatientEntity entity) throws CommonException;
	
	/**
	 * 修改门诊转诊申请
	 * @param entity
	 * @return
	 */
	public String updateReferralOutpatientApply(ReferralOutpatientEntity entity);
	
	/**
	 * 获取门诊转诊信息
	 * @param id
	 * @return
	 */
	public ReferralOutpatientEntity getReferralOutpatientInfo(int id);
	
	/**
	 * 通过referralCode获取门诊转诊信息
	 * @param referralCode
	 * @return
	 */
	public ReferralOutpatientEntity getReferralOutpatientInfoByCode(String referralCode);
	
	/**
	 * 修改门诊转诊流程状态
	 * @param entity
	 * @return
	 */
	public String updateReferralOutpatientStatus(ReferralOutpatientEntity entity);
	
	/**
	 * 获取签约居民的门诊转诊申请记录列表
	 * @param vo
	 * @return
	 */
	public List<ReferralOutpatientVo> getReferralOutpatientListBySign(ReferralOutpatientVo vo);
	
	/**
	 * 获取签约居民的门诊转诊申请记录数量
	 * @param vo
	 * @return
	 */
	public Integer getReferralOutpatientCountBySign(ReferralOutpatientVo vo);
}
