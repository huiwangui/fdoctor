package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;
/**
 * 体检实体类
 * @author q
 *
 */
public class HealthCheckEntity extends DataEntity<HealthCheckEntity>{

	private static final long serialVersionUID = 1L;
	private int id;
	private String healthcheckNum;  //体检订单号
	private String idCard;          //体检人身份证号
	private String payType;			//体检类型 1.自费 2.免费
	private String checkType;       //1.儿童体检 2.妇女体检 3.孕产妇体检 4.成人体检 5.老年人体检
	private Integer docId;			//放号医生ID
	private Integer hospId;			//体检医院ID
	private Date checkDate;			//体检日期
	private Date createTime;		//创建时间
	private String status;			//0.未体检  1.已体检   -1：已取消
	private String uid;				//预约操作人UID
	private String payStatus;//付款状态： 0.未付款 1.已付款 2.正在退款  3.已退款
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHealthcheckNum() {
		return healthcheckNum;
	}
	public void setHealthcheckNum(String healthcheckNum) {
		this.healthcheckNum = healthcheckNum;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getHospId() {
		return hospId;
	}
	public void setHospId(Integer hospId) {
		this.hospId = hospId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	
	
}
