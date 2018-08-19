package com.boco.modules.fdoc.vo;

import com.boco.common.persistence.Page;

public class UserReportParamVo {
    private String personId;
    private String userName;
	/**
	 * 分页对象
	 */
	private Page<UserReportParamVo> page;
	
	public Page<UserReportParamVo> getPage() {
		return page;
	}
	public void setPage(Page<UserReportParamVo> page) {
		this.page = page;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
  
}
