package com.boco.modules.fdoc.vo;
/**
 * Description
 * @author lzz
 * @date 2017年7月12日 下午4:00:29
 */
public class FamilyContactInfoVo {
	
	/**
	 * 家庭号
	 */
	private String familyId;
	/**
	 * 电话号码（必填）
	 */
	private String phoneNumber;
	/**
	 * qq号码
	 */
	private String qqNumber;
	/**
	 * 微信号码
	 */
	private String wechatNumber;
	/**
	 * 身份证号
	 */
	private String idCard;
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getQqNumber() {
		return qqNumber;
	}
	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	public String getWechatNumber() {
		return wechatNumber;
	}
	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	
	
}
