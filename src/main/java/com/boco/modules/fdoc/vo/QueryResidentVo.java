package com.boco.modules.fdoc.vo;

import java.util.Date;

public class QueryResidentVo {
	
	private int id;
	private String personName;  //姓名
	private String sex; 		//性别 0:男  1：女
	private Date dateOfBirth;	//出生日期
	private String type; 		//证件类型  0:默认为身份证
	private String idCard;		//身份证号
	private String phoneNumber;	//电话号码
	private String workunits;	//工作单位
	private String householdaddress;//户籍所在地
	private String permanent;	// 0：常住户口所在  1：不常住户口所在地
	private String nowaddress;	//现居住地址
	private String masterIdCard;//户主身份证号
	private String datasources;	//数据来源方式：0:手动添加  1：同步数据
	private String parentHouseRelation; //与户主关系：0本人 1父亲 2 母亲 3 祖父 4 祖母 5 夫妻 6 其它
	private String status;		//0:同步增加 1：同步删除  2：同步更改  3：同步相同  4：手动修改
	private String createBy;	//
	private Date createTime;	//创建时间
	private String updateBy;	
	private Date updateTime;	//最近修改时间
	private String remarks;		//备注
	private  int  sigId; //签约ID
	private int packid;  //服务包ID
	private String packName;	//服务包名称
	private Double packPrice;	//服务包价格
	private Date signTime;	//签约时间
	private  String fathername;//户主姓名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getWorkunits() {
		return workunits;
	}
	public void setWorkunits(String workunits) {
		this.workunits = workunits;
	}
	public String getHouseholdaddress() {
		return householdaddress;
	}
	public void setHouseholdaddress(String householdaddress) {
		this.householdaddress = householdaddress;
	}
	public String getPermanent() {
		return permanent;
	}
	public void setPermanent(String permanent) {
		this.permanent = permanent;
	}
	public String getNowaddress() {
		return nowaddress;
	}
	public void setNowaddress(String nowaddress) {
		this.nowaddress = nowaddress;
	}
	public String getMasterIdCard() {
		return masterIdCard;
	}
	public void setMasterIdCard(String masterIdCard) {
		this.masterIdCard = masterIdCard;
	}
	public String getDatasources() {
		return datasources;
	}
	public void setDatasources(String datasources) {
		this.datasources = datasources;
	}
	public String getParentHouseRelation() {
		return parentHouseRelation;
	}
	public void setParentHouseRelation(String parentHouseRelation) {
		this.parentHouseRelation = parentHouseRelation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getSigId() {
		return sigId;
	}
	public void setSigId(int sigId) {
		this.sigId = sigId;
	}
	public int getPackid() {
		return packid;
	}
	public void setPackid(int packid) {
		this.packid = packid;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public Double getPackPrice() {
		return packPrice;
	}
	public void setPackPrice(Double packPrice) {
		this.packPrice = packPrice;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	
	

}
