/**
 * @ClassName: SurrenderInformationEntity 
 * Description:
 * @author h
 * @date  2017年11月28日下午4:32:35
 * @version 1.0
 *  
 */
package com.boco.modules.fdoc.model.surrender;

import java.util.Date;

/**
 * 解约信息实体类
 */
public class SurrenderInformationEntity {
	    /**
	     * 主键id
	     */
	    private Integer id;

	    /**
	     * 签约请求表id
	     */
	    private Integer requestId;

	    /**
	     * 区划码
	     */
	    private String regionCode;

	    /**
	     * 医院id
	     */
	    private String orgId;

	    /**
	     * 服务包权值
	     */
	    private Integer servicePackValue;

	    /**
	     * 签约上传图片地址
	     */
	    private String signImg;

	    /**
	     * 签约类型：1.医生端主动签约 2.居民端提出申请完成签约
	     */
	    private String signType;

	    /**
	     * 签约时备注
	     */
	    private String signRemark;

	    /**
	     * 签约时间
	     */
	    private Date signTime;

	    /**
	     * 签约结束时间
	     */
	    private Date dueTime;
	    
	    /**
	     * 专家iD
	     */
	    private String expertId;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getRequestId() {
			return requestId;
		}

		public void setRequestId(Integer requestId) {
			this.requestId = requestId;
		}

		public String getRegionCode() {
			return regionCode;
		}

		public void setRegionCode(String regionCode) {
			this.regionCode = regionCode;
		}

		public String getOrgId() {
			return orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}

		public Integer getServicePackValue() {
			return servicePackValue;
		}

		public void setServicePackValue(Integer servicePackValue) {
			this.servicePackValue = servicePackValue;
		}

		public String getSignImg() {
			return signImg;
		}

		public void setSignImg(String signImg) {
			this.signImg = signImg;
		}

		public String getSignType() {
			return signType;
		}

		public void setSignType(String signType) {
			this.signType = signType;
		}

		public String getSignRemark() {
			return signRemark;
		}

		public void setSignRemark(String signRemark) {
			this.signRemark = signRemark;
		}

		public Date getSignTime() {
			return signTime;
		}

		public void setSignTime(Date signTime) {
			this.signTime = signTime;
		}

		public Date getDueTime() {
			return dueTime;
		}

		public void setDueTime(Date dueTime) {
			this.dueTime = dueTime;
		}
	    
}
