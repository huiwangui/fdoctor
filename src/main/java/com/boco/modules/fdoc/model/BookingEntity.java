package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;

/**
    * t_booking 实体类
    * Thu Jul 28 10:08:22 CST 2016 sun
    */ 

public class BookingEntity extends DataEntity<BookingEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String orderNum; //订单号
	private int patientId;//患者ID
	private String period;//1-上午，2-下午，3-夜诊
	private int scheId;//排班ID
	private int seq;//预约序号
	private String providerNum;//服务商编码
	private String staffNum;//工号
	private String getTicketNum;//取号代码
	private String cardType;//卡类型（市医保、农医保等）
	private String cardNum;//卡号码
	private String patientCardNum;//本次就诊卡号
	private String payMethod;
	private String payStatus; //订单状态  0.未付款 1.已付款  2.正在退款  3.已退款  4.已取消 5.未评论 6.已评论
	private String seeDocStatus; //就诊状态 0.全部,1.未就诊  2.已就诊,3.已取消
	private Date createTime;//订单创建时间
	
	
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setPatientId(int patientId){
	this.patientId=patientId;
	}
	public int getPatientId(){
		return patientId;
	}
	public void setPeriod(String period){
	this.period=period;
	}
	public String getPeriod(){
		return period;
	}
	public void setScheId(int scheId){
	this.scheId=scheId;
	}
	public int getScheId(){
		return scheId;
	}
	public void setSeq(int seq){
	this.seq=seq;
	}
	public int getSeq(){
		return seq;
	}
	public void setProviderNum(String providerNum){
	this.providerNum=providerNum;
	}
	public String getProviderNum(){
		return providerNum;
	}
	public void setStaffNum(String staffNum){
	this.staffNum=staffNum;
	}
	public String getStaffNum(){
		return staffNum;
	}
	public void setGetTicketNum(String getTicketNum){
	this.getTicketNum=getTicketNum;
	}
	public String getGetTicketNum(){
		return getTicketNum;
	}
	public void setCardType(String cardType){
	this.cardType=cardType;
	}
	public String getCardType(){
		return cardType;
	}
	public void setCardNum(String cardNum){
	this.cardNum=cardNum;
	}
	public String getCardNum(){
		return cardNum;
	}
	public void setPatientCardNum(String patientCardNum){
	this.patientCardNum=patientCardNum;
	}
	public String getPatientCardNum(){
		return patientCardNum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSeeDocStatus() {
		return seeDocStatus;
	}
	public void setSeeDocStatus(String seeDocStatus) {
		this.seeDocStatus = seeDocStatus;
	}
	
}

