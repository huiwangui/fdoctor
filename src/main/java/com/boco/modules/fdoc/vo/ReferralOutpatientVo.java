package com.boco.modules.fdoc.vo;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.ReferralOutpatientEntity;

/**
 * 门诊转诊申请Vo
 * @author q
 *
 */
public class ReferralOutpatientVo extends ReferralOutpatientEntity{
	/**
	 * 医生团队ID
	 */
	private String docTeamId;
	
	/**
	 * 分页参数
	 */
	private Page<ReferralOutpatientVo> page;

	public String getDocTeamId() {
		return docTeamId;
	}

	public void setDocTeamId(String docTeamId) {
		this.docTeamId = docTeamId;
	}

	public Page<ReferralOutpatientVo> getPage() {
		return page;
	}

	public void setPage(Page<ReferralOutpatientVo> page) {
		this.page = page;
	}
	
	
}
