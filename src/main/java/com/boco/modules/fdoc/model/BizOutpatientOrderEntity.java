package com.boco.modules.fdoc.model;

import java.util.Date;

/**
 * 
 * ClassName: BizOutpatientOrderEntity <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年12月2日 下午4:46:22 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class BizOutpatientOrderEntity {
    /**
    * 预约单主键ID
    */
    private Integer id;
    
    /**
     * 订单号：16位  生成规则为：区别号（1位|四种状态（订单/流水）&（到院/线上））+人员（6位）+交易日期（6位|161201）+人数(三位位|1)
     */
    private String orderNumber;

    /**
    * 支付流水号 ：16位  生成规则为：区别号（1位|四种状态（订单/流水）&（到院/线上））+人员（6位）+交易日期（6位|161201）+人数(三位位|1)
    */
    private String paymentNumber;

    /**
    * 就诊人ID
    */
    private String patientId;

    /**
    * 号表ID
    */
    private Integer outpatientSourceId;

    /**
    * 付款方式代码：10. 到院支付  20.网上支付
    */
    private String payMethod;

    /**
    * 订单状态  0.未付款 1.已付款  2.正在退款  3.已退款  4.已取消 5.未评论 6.已评论
    */
    private String orderStatus;

    /**
    * 就诊状态 0.全部,1.未就诊  2.已就诊,3.已取消
    */
    private String doctorVisitStatus;

    /**
    * 订单创建时间
    */
    private Date createTime;

    /**
    * 订单创建人uid
    */
    private String creatorUid;

    /**
    * 订单取消时间
    */
    private Date cancleTime;
    /**
     * 预约时间
     */
    private Date orderTime;
    
    /**
     * 0.上午  1.下午 
     */
    private String clinicTime;
    

    public String getClinicTime() {
		return clinicTime;
	}

	public void setClinicTime(String clinicTime) {
		this.clinicTime = clinicTime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

     

    public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Integer getOutpatientSourceId() {
        return outpatientSourceId;
    }

    public void setOutpatientSourceId(Integer outpatientSourceId) {
        this.outpatientSourceId = outpatientSourceId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDoctorVisitStatus() {
        return doctorVisitStatus;
    }

    public void setDoctorVisitStatus(String doctorVisitStatus) {
        this.doctorVisitStatus = doctorVisitStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatorUid() {
        return creatorUid;
    }

    public void setCreatorUid(String creatorUid) {
        this.creatorUid = creatorUid;
    }

    public Date getCancleTime() {
        return cancleTime;
    }

    public void setCancleTime(Date cancleTime) {
        this.cancleTime = cancleTime;
    }

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
    
}