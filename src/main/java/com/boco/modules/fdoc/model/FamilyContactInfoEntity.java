package com.boco.modules.fdoc.model;
/**
 * Description t_family_contact_info 实体类
 * @author lzz
 * @date 2017年7月12日 下午3:08:45
 */
public class FamilyContactInfoEntity {
	
	/**
	 * 主键
	 */
	private Integer id;
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	
	

}
