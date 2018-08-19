package com.boco.modules.fdoc.model;

import java.math.BigDecimal;
import java.util.Date;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.utils.StringUtils;
/**
 * 
 * ClassName: BizOutpatientSourceEntity <br/>
 * Reason: 业务模块-门诊号表信息. <br/>
 * 
 */
public class BizOutpatientSourceEntity {
    private Integer id;

    /**
    * 坐诊日期
    */
    private Date clinicDate;

    /**
    * 医院ID
    */
    private String orgId;

    /**
    * 行政区划
    */
    private String regionCode;

    /**
    * 科室id
    */
    private Integer deptId;

    /**
    * 医生ID
    */
    private Integer staffId;

    /**
    * 门诊类别名称
    */
    private String outpatientType;

    /**
    * 0.上午  1.下午  2.全天  3.晚上  4.休息
    */
    private String clinicTime;

    /**
    * 星期，值为1-7
    */
    private String weekDay;

    /**
    * 挂号限额，指挂号的总限额，包括现场挂号和预约
    */
    private Integer registrationLimit;

    /**
    * 预约限号数，指通过预约途径挂号的限额
    */
    private Integer orderLimit;

    /**
    * 当前号数（已挂多少个）
    */
    private Integer currentNumber;

    /**
    * 剩余号数
    */
    private Integer remainderNumber;

    /**
    * 启用状态：1.正常  0.注销
    */
    private String status;
    
    /**
     * 版本号
     */
    private Integer version;
    
    /**
     * 挂号费
     */
    private BigDecimal registrationFee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(Date clinicDate) {
        this.clinicDate = clinicDate;
    }

     

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRegionCode() {
			return StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = StringUtils.isNotBlank(RegionCodeConstants.DEFAULT_REGION_CODE) ? RegionCodeConstants.DEFAULT_REGION_CODE : regionCode;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getOutpatientType() {
        return outpatientType;
    }

    public void setOutpatientType(String outpatientType) {
        this.outpatientType = outpatientType;
    }

    public String getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(String clinicTime) {
        this.clinicTime = clinicTime;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getRegistrationLimit() {
        return registrationLimit;
    }

    public void setRegistrationLimit(Integer registrationLimit) {
        this.registrationLimit = registrationLimit;
    }

    public Integer getOrderLimit() {
        return orderLimit;
    }

    public void setOrderLimit(Integer orderLimit) {
        this.orderLimit = orderLimit;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Integer getRemainderNumber() {
        return remainderNumber;
    }

    public void setRemainderNumber(Integer remainderNumber) {
        this.remainderNumber = remainderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	 

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BigDecimal getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(BigDecimal registrationFee) {
		this.registrationFee = registrationFee;
	}
    
}