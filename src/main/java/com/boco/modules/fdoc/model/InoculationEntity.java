package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;
/**
 * 接种表
 * @author q
 *
 */
public class InoculationEntity extends DataEntity<InoculationEntity>{
	private static final long serialVersionUID = 1L;
	private int id;
	private String inoculationNum;  //预约号
	private String mainUserUid;//接种者户主ID
	private String idCard;//接种者身份证
	private int detailId;//疫苗详细ID
	private int docId;//接种医生ID
	private Date inoDate;//预约接种日期
	private String period;//接种时间段 1.上午 2.下午
	private String status;//状态： 0.未接种，1.已接种   -1.已取消
	private Date createTime; //创建时间
	private String payStatus;//付款状态： 0.未付款 1.已付款 2.正在退款  3.已退款
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMainUserUid() {
		return mainUserUid;
	}
	public void setMainUserUid(String mainUserUid) {
		this.mainUserUid = mainUserUid;
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public Date getInoDate() {
		return inoDate;
	}
	public void setInoDate(Date inoDate) {
		this.inoDate = inoDate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getInoculationNum() {
		return inoculationNum;
	}
	public void setInoculationNum(String inoculationNum) {
		this.inoculationNum = inoculationNum;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
}
