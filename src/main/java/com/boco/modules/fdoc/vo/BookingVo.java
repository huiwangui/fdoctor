package com.boco.modules.fdoc.vo;

public class BookingVo {
	private int id;
	private String uid;
	private String orderNum;//订单号
	private String name;//就诊人name
	private String docName;//医生name
	private String deptName;//医生科室
	private String title;//医生头衔
	private String hospName;//医院name
	private Long workTime;//就诊日期时间戳
	private int period;//时间段 ：1-上午 2-下午 3-夜诊
	private String payStatus; //订单状态  0.未付款 1.已付款  2.正在退款  3.已退款  4.已取消 5.未评论 6.已评论
	private String seeDocStatus; //就诊状态 0.全部,1.未就诊  2.已就诊,3.已取消
	private Integer docId; //医生ID
	private Integer hospId; //医院ID
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public Long getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Long workTime) {
		this.workTime = workTime;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getSeeDocStatus() {
		return seeDocStatus;
	}
	public void setSeeDocStatus(String seeDocStatus) {
		this.seeDocStatus = seeDocStatus;
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
}
