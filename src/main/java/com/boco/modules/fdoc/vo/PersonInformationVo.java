package com.boco.modules.fdoc.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.persistence.Page;
import com.boco.common.utils.StringUtils;

public class PersonInformationVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String personId;

	/**
	 * 健康档案编号
	 */
	private String personCode;

	/**
	 * 对应家庭表的主键
	 */
	private String familyId;

	/**
	 * 自定义编号
	 */
	private String customNumber;

	/**
	 * 姓名
	 */
	private String personName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 出生日期
	 */
	private Date dateOfBirth;

	/**
	 * 身份证件号码
	 */
	private String idCard;

	/**
	 * 本人联系电话
	 */
	private String phoneNumber;

	/**
	 * 现住址
	 */
	private String currentAddress;

	/**
	 * 行政区划代码
	 */
	private String regionCode;

	/**
	 * 户主标识，1为户主
	 */
	private String masterFlag;

	/**
	 * 所属组名称
	 */
	private String unit;

	/**
	 * 是否签约 0.未签约 1.已签约
	 */
	private Integer ifSign;

	/**
	 * 0:注销 1:正常 2：封存 3:死亡 4:结案
	 */
	private Integer state;
	
	/**
	 * 家庭成员人数
	 */
	private Integer familyNum;
	
	/**
	 * 分页对象
	 */
	private Page<PersonInformationVo> page;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 身高
	 */
	private Double height;
	
	/**
	 * 体重
	 */
	private Double weight;
	
	/**
	 * 婚姻状况:1未婚,2已婚,3丧偶,4离婚,5未说明的婚姻状况
	 */
	private String marryStatus;

	/**
	 * 医疗费用支付方式:1成长职工基本医疗保险,2城镇居民基本医疗保险,4新型农村合作医疗,8商业医疗保险,16全公费,32全自费,64其他
	 */
	private Integer paymentWaystring;
	
	/**
	 * 民族
	 */
	private String nation;
	
	/**
	 * 头像url
	 */
	private String img;
	
	/**
	 * 账号uid
	 */
	private String uid;
	
	/**
	 * 创建者
	 */
	private String creator;
	/**
	 * 基卫personId
	 */
	private String jwPersonId;
	/**
	 * qq联系方式
	 */
	private String qq;
	/**
	 * 微信联系方式
	 */
	
	private String wechat;
	/**
	 * 慢病
	 */
	private List<String> chronicDiseaseList;
	
	/**
	 * 用户关系表主键ID
	 * @return
	 *
	 */
	private int id;
	/**
	 * 用户关系 1父子2父女3母子4母女5配偶6兄弟7姐妹8其他'
	 */
	private String relation;
	
	/**
	 * 签约ID
	 */
	private String signId;
	
	/**
	 * 签约状态   1:解约审核中  2:未签约  3:已签约
	 */
	private String status;
	
	/**
	 * 签约类型
	 */
	private String signType;
	
	/**
	 * 所属单位
	 */
	private String personUnit;
	
	/**
	 * 签约时间
	 */
	private Date signTime;
	
	/**
	 *医生电话号码 
	 */
	private String docMobile;
	

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getPersonUnit() {
		return personUnit;
	}

	public void setPersonUnit(String personUnit) {
		this.personUnit = personUnit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getJwPersonId() {
		return jwPersonId;
	}

	public void setJwPersonId(String jwPersonId) {
		this.jwPersonId = jwPersonId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getCustomNumber() {
		return customNumber;
	}

	public void setCustomNumber(String customNumber) {
		this.customNumber = customNumber;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getRegionCode() {
		return StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
	}
	
	public void setRegionCode(String regionCode) {
	    /*this.regionCode = StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;*/
		this.regionCode=regionCode;
	}

	public String getMasterFlag() {
		return masterFlag;
	}

	public void setMasterFlag(String masterFlag) {
		this.masterFlag = masterFlag;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getIfSign() {
		return ifSign;
	}

	public void setIfSign(Integer ifSign) {
		this.ifSign = ifSign;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getFamilyNum() {
		return familyNum;
	}

	public void setFamilyNum(Integer familyNum) {
		this.familyNum = familyNum;
	}

	public Page<PersonInformationVo> getPage() {
		return page;
	}

	public void setPage(Page<PersonInformationVo> page) {
		this.page = page;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getMarryStatus() {
		return marryStatus;
	}

	public void setMarryStatus(String marryStatus) {
		this.marryStatus = marryStatus;
	}

	public Integer getPaymentWaystring() {
		return paymentWaystring;
	}

	public void setPaymentWaystring(Integer paymentWaystring) {
		this.paymentWaystring = paymentWaystring;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<String> getChronicDiseaseList() {
		return chronicDiseaseList;
	}

	public void setChronicDiseaseList(List<String> chronicDiseaseList) {
		this.chronicDiseaseList = chronicDiseaseList;
	}

	public String getDocMobile() {
		return docMobile;
	}

	public void setDocMobile(String docMobile) {
		this.docMobile = docMobile;
	}

	 
	
}
