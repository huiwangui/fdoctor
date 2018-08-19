package com.boco.modules.fdoc.vo;

import java.io.Serializable;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.persistence.Page;
import com.boco.common.utils.StringUtils;


/**
 * 医生详情查询/返回对象
 * 
 * @author q
 *
 */
public class DoctorDetailVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 医生ID
	 */
	private String id;
	
	/**
	 * 医生用户ID
	 */
	private String doctorUserId;

	/**
	 * 医生姓名
	 */
	private String docName;

	/**
	 * 性别（1男2女）
	 */
	private String sex;

	/**
	 * 所属科室名称
	 */
	private String deptName;

	/**
	 * 执业点（所属医院）ID
	 */
	private String orgId;

	/**
	 * 区划代码
	 */
	private String regionCode;

	/**
	 * 电话号码
	 */
	private String phoneNumber;

	/**
	 * 擅长领域
	 */
	private String specialty;

	/**
	 * 简介
	 */
	private String introduction;

	/**
	 * 所属医生团队ID
	 */
	private String teamId;

	/**
	 * 医生分类。1.医生 2.公卫医生 3.护士
	 */
	private String docType;
	
	/**
	 * 所属机构名称
	 */
	private String orgName;
	
	/**
	 * 医生机构地址
	 */
	private String orgAddress;
	
	/**
	 * 医生头像
	 */
	private String img;
	
	private String userName;
	
	private String password;
	
	private String nickName;
	
	private String productCode;
	/**
	 * 团队队长（医生）姓名
	 */
	private String leaderName;
	
	/**
	 * 团队内成员数量（String表示）
	 */
	private String memberNumInTeam;
	
	/**
	 * 分页对象
	 */
	private Page<DoctorDetailVo> page;
	
	 /**
     * 队长标识，1为队长
     */
    private String leaderFlag;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRegionCode() {
		return regionCode;
	}
	
	public void setRegionCode(String regionCode) {
	    this.regionCode = regionCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getMemberNumInTeam() {
		return memberNumInTeam;
	}

	public void setMemberNumInTeam(String memberNumInTeam) {
		this.memberNumInTeam = memberNumInTeam;
	}

	public Page<DoctorDetailVo> getPage() {
		return page;
	}

	public void setPage(Page<DoctorDetailVo> page) {
		this.page = page;
	}

	public String getLeaderFlag() {
		return leaderFlag;
	}

	public void setLeaderFlag(String leaderFlag) {
		this.leaderFlag = leaderFlag;
	}

	public String getDoctorUserId() {
		return doctorUserId;
	}

	public void setDoctorUserId(String doctorUserId) {
		this.doctorUserId = doctorUserId;
	}
	
	
}
