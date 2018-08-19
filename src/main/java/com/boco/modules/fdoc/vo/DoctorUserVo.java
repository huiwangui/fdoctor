package com.boco.modules.fdoc.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.boco.modules.fdoc.model.UserEntity;

/**
 * 医生登录VO
 * 
 * @author q
 *
 */
public class DoctorUserVo {

	/**
	 * 医生用户ID
	 */
	private String doctorUserId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 图片地址
	 */
	private String img;

	/**
	 * 卫计委接口产品代码
	 */
	private String productCode;

	/**
	 * 医生ID
	 */
	private String doctorId;

	private Date createTime;

	private Date updateTime;

	private Double goodComment;
	private Integer bookingNum;
	private Integer allEvaluateNum;
	private Integer goodEvaluateNum;
	private Integer generalEvaluateNum;
	private Integer badEvaluateNum;
	
	/**
	 * 新密码
	 */
	private String newPassword;

	public String getDoctorUserId() {
		return doctorUserId;
	}

	public void setDoctorUserId(String doctorUserId) {
		this.doctorUserId = doctorUserId;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Double getGoodComment() {
		return goodComment;
	}

	public void setGoodComment(Double goodComment) {
		this.goodComment = goodComment;
	}

	public Integer getBookingNum() {
		return bookingNum;
	}

	public void setBookingNum(Integer bookingNum) {
		this.bookingNum = bookingNum;
	}

	public Integer getAllEvaluateNum() {
		return allEvaluateNum;
	}

	public void setAllEvaluateNum(Integer allEvaluateNum) {
		this.allEvaluateNum = allEvaluateNum;
	}

	public Integer getGoodEvaluateNum() {
		return goodEvaluateNum;
	}

	public void setGoodEvaluateNum(Integer goodEvaluateNum) {
		this.goodEvaluateNum = goodEvaluateNum;
	}

	public Integer getGeneralEvaluateNum() {
		return generalEvaluateNum;
	}

	public void setGeneralEvaluateNum(Integer generalEvaluateNum) {
		this.generalEvaluateNum = generalEvaluateNum;
	}

	public Integer getBadEvaluateNum() {
		return badEvaluateNum;
	}

	public void setBadEvaluateNum(Integer badEvaluateNum) {
		this.badEvaluateNum = badEvaluateNum;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
