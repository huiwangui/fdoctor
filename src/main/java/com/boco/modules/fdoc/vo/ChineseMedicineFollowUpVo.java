package com.boco.modules.fdoc.vo;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.ChineseMedicineFollowUpEntity;

/**
 * 中医随访VO
 * @author q
 *
 */
public class ChineseMedicineFollowUpVo extends  ChineseMedicineFollowUpEntity{
	/**
	 * 居民姓名
	 */
	private String personName;
	
	/**
	 * 居民电话号码
	 */
	private String mobile;
	
	/**
	 * 居民身份证号
	 */
	private String idCard;
	
	/**
	 * 分页对象
	 */
	Page<ChineseMedicineFollowUpVo> page;
	
	/**
	 * 最后修改医生姓名
	 */
	private String updateName;
	
	/**
	 * 性别
	 */
	private String sex;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Page<ChineseMedicineFollowUpVo> getPage() {
		return page;
	}

	public void setPage(Page<ChineseMedicineFollowUpVo> page) {
		this.page = page;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
