package com.boco.modules.fdoc.vo;

public class ServicepackDetailsVo {
	private int id;
	private String serviceItem;		//服务项目
	private String serviceName;		//服务名称
	private String serviceDetails;	//服务内容
	private Double originalPrice;	//原始价格
	private Integer frequency;		//说明：  -1：不限次数，其它为1-最终服务次数	
	private String serviceTime;		//服务时间 ：  由权限人员填写  不是特定time
	private String serviceOrg;		//服务机构
	private String serviceOrgName;	//服务机构名称
	private String serviceType;	//服务类型：1.基础服务  2.增值服务
	private Integer remainderTimes; //剩余服务次数
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServiceItem() {
		return serviceItem;
	}
	public void setServiceItem(String serviceItem) {
		this.serviceItem = serviceItem;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	public String getServiceOrgName() {
		return serviceOrgName;
	}
	public void setServiceOrgName(String serviceOrgName) {
		this.serviceOrgName = serviceOrgName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceOrg() {
		return serviceOrg;
	}
	public void setServiceOrg(String serviceOrg) {
		this.serviceOrg = serviceOrg;
	}
	public Integer getRemainderTimes() {
		return remainderTimes;
	}
	public void setRemainderTimes(Integer remainderTimes) {
		this.remainderTimes = remainderTimes;
	}
	
}

