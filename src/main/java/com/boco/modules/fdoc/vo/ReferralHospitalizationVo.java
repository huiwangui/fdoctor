package com.boco.modules.fdoc.vo;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.ReferralHospitalizationEntity;
/**
 * Tilte: ReferralHospitalizationVo 
 * Description:住院转诊申请Vo
 * @author h
 * @date  2017年11月2日上午9:59:33
 * @version 1.0
 *  
 */
public class ReferralHospitalizationVo extends ReferralHospitalizationEntity{
	/**
	 * 医生团队ID
	 */
	private String docTeamId;
	
	/**
	 * 分页参数
	 */
	private Page<ReferralHospitalizationVo> page;

	public String getDocTeamId() {
		return docTeamId;
	}

	public void setDocTeamId(String docTeamId) {
		this.docTeamId = docTeamId;
	}

	public Page<ReferralHospitalizationVo> getPage() {
		return page;
	}

	public void setPage(Page<ReferralHospitalizationVo> page) {
		this.page = page;
	}
	
	
}
