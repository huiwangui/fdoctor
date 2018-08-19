package com.boco.modules.fdoc.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.DiseaseLabelEntity;
import com.boco.modules.fdoc.model.SigServicepackEntity;

import ch.qos.logback.core.status.Status;

/**
 * 签约相关参数、返回对象
 * 
 * @author q
 *
 */
public class SignVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    /**
    * 团队ID
    */
    private String docTeamId;

    /**
    * 医生所属机构名称
    */
    private String docOrgName;

    /**
    * 医生所属机构地址
    */
    private String docOrgAddress;

    /**
    * 签约居民ID
    */
    private String personId;

    /**
    * 签约图片
    */
    private String signImg;

    /**
    * 签约状态：1.签约有效期内 2.待续约 3.已解约
    */
    private String status;
    
    /**
     * 居民姓名
     */
    private String personName;
    
    /**
     * 居民性别
     */
    private String sex;
    
    /**
     * 分页对象
     */
    private Page<SignVo> page;
    
    
    /**
     * 签约类型：1.医生端主动签约 2.居民端提出申请完成签约
     */
    private String signType;
    
    /**
     * 户主名字
     */
    private String masterName;
    
    /**
     * 家庭人员数量
     */
    private Integer familyNum;
    
    /**
     * 家庭单位
     */
    private String familyUnit;
    
    /**
     * 签约服务包集合
     */
    private List<SigServicepackEntity> packs;
    
    /**
     * 团队中职位为医生的人名
     */
    private String docName; 
    
    /**
     * 人员年龄
     */
    private Integer age;
    
    /**
     * 已签约人数
     */
    private Integer signedCount;
    
    /**
     * 总人数
     */
    private Integer personCount;
    
    /**
     * 区划信息
     */
    private String regionCode;
    
    /**
     * 签约人数百分比
     */
    private Double signPer;

	/**
	 * 签约户数
	 */
	private Integer familyCount;
	
	/**
	 * 居民地址
	 */
	private String currentAddress;
	
	/**
	 * 居民微信号
	 */
	private String wechat;
	
	/**
	 * 居民手机号
	 */
	private String phoneNumber;
	
	/**
	 * 居民QQ
	 */
	private String qq;
	
	/**
	 * 基卫居民ID
	 */
	private String jwPersonId;

	/**
	 * 签约时间
	 */
	private Date signTime;
	
	/**
	 * 签约备注
	 */
	private String remark;
	
	/**
	 * 居民身份证号
	 */
	private String idCard;
	
	/**
	 * 签约居民所在单位
	 */
	private String personUnit;
	/**
	 * 医生ID
	 */
	private String docId;
	 /**
     * 服务包权值
     */
    private Integer servicePackValue;
    /**
     * 签约结束时间
     */
    private Date dueTime;
    
    /**
     * 居民所在标签集合
     */
    private List<DiseaseLabelEntity> labels;
    
    /**
     * 解约状态：1：解约申请中  2：拒绝解约  3：签约 
     */
    private int surrenderStatus; 
    
    
	public int getSurrenderStatus() {
		return surrenderStatus;
	}

	public void setSurrenderStatus(int surrenderStatus) {
		this.surrenderStatus = surrenderStatus;
	}

	public List<DiseaseLabelEntity> getLabels() {
		return labels;
	}

	public void setLabels(List<DiseaseLabelEntity> labels) {
		this.labels = labels;
	}

	public Integer getServicePackValue() {
		return servicePackValue;
	}

	public void setServicePackValue(Integer servicePackValue) {
		this.servicePackValue = servicePackValue;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getPersonUnit() {
		return personUnit;
	}

	public void setPersonUnit(String personUnit) {
		this.personUnit = personUnit;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getJwPersonId() {
		return jwPersonId;
	}

	public void setJwPersonId(String jwPersonId) {
		this.jwPersonId = jwPersonId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocTeamId() {
		return docTeamId;
	}

	public void setDocTeamId(String docTeamId) {
		this.docTeamId = docTeamId;
	}

	public String getDocOrgName() {
		return docOrgName;
	}

	public void setDocOrgName(String docOrgName) {
		this.docOrgName = docOrgName;
	}

	public String getDocOrgAddress() {
		return docOrgAddress;
	}

	public void setDocOrgAddress(String docOrgAddress) {
		this.docOrgAddress = docOrgAddress;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getSignImg() {
		return signImg;
	}

	public void setSignImg(String signImg) {
		this.signImg = signImg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Page<SignVo> getPage() {
		return page;
	}

	public void setPage(Page<SignVo> page) {
		this.page = page;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public Integer getFamilyNum() {
		return familyNum;
	}

	public void setFamilyNum(Integer familyNum) {
		this.familyNum = familyNum;
	}

	public String getFamilyUnit() {
		return familyUnit;
	}

	public void setFamilyUnit(String familyUnit) {
		this.familyUnit = familyUnit;
	}

	public List<SigServicepackEntity> getPacks() {
		return packs;
	}

	public void setPacks(List<SigServicepackEntity> packs) {
		this.packs = packs;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSignedCount() {
		return signedCount;
	}

	public void setSignedCount(Integer signedCount) {
		this.signedCount = signedCount;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public Double getSignPer() {
		return signPer;
	}

	public void setSignPer(Double signPer) {
		this.signPer = signPer;
	}

	public Integer getFamilyCount() {
		return familyCount;
	}

	public void setFamilyCount(Integer familyCount) {
		this.familyCount = familyCount;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	
}
