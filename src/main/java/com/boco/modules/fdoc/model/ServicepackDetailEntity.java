package com.boco.modules.fdoc.model;

import java.util.Date;

public class ServicepackDetailEntity {
    private Integer id;

    /**
    * 服务项目
    */
    private String serviceItem;

    /**
    * 服务名称
    */
    private String serviceName;

    /**
    * 服务内容
    */
    private String serviceDetails;

    /**
    * 原始价格
    */
    private Double originalPrice;

    /**
    * 服务类型：1.基础服务  2.增值服务
    */
    private String serviceType;

    /**
    * 说明：  -1：不限次数，其它为1-最终服务次数
    */
    private Integer frequency;

    /**
    * 服务时间 ：  由权限人员填写  不是特定time
    */
    private String serviceTime;

    /**
    * 服务机构
    */
    private String serviceOrg;

    /**
    * 注释
    */
    private String remarks;

    /**
    * 创建者
    */
    private String createby;

    /**
    * 创建日期
    */
    private Date createtime;

    /**
    * 更新者
    */
    private String updateby;

    private Date updatetime;

    /**
    * 0:正常  1：逻辑删除
    */
    private Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    public String getServiceOrg() {
        return serviceOrg;
    }

    public void setServiceOrg(String serviceOrg) {
        this.serviceOrg = serviceOrg;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}