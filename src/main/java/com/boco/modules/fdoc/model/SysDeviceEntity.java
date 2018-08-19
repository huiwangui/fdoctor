package com.boco.modules.fdoc.model;
import java.util.Date;
/**
 * sys_device表实体类
 * @author lzz
 *
 */

public class SysDeviceEntity {
	/**
	 * 主键ID Guid
	 */
	private String ID;
	/**
	 * 设备编号（唯一）
	 */
	private String Code;
	/**
	 * 设备名称
	 */
	private String Name;
	/**
	 * 详细说明
	 */
	private String Caption;
	/**
	 * 设备归属机构编码
	 */
	private String OrgCode;
	/**
	 * 设备发放时间
	 */
	private Date DistributeTime;
	/**
	 * 负责人员身份证号码
	 */
	private String ChargeIdCode;
	/**
	 * 负责人姓名
	 */
	private String ChargeName;
	/**
	 * 硬件版本号
	 */
	private String HardwareVersion;
	/**
	 * 软件版本号
	 */
	private String SoftwareVersion;
	/**
	 * 链接状态
	 */
	private Integer ConnectStatus;
	/**
	 * 创建即客户管理员GUID
	 */
	private String CreatManID;
	/**
	 * 创建人所属机构
	 */
	private String OrgID;
	/**
	 * 创建人所属云实例
	 */
	private String SaasID;
	/**
	 * 创建时间
	 */
	private Date CreatTime;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCaption() {
		return Caption;
	}
	public void setCaption(String caption) {
		Caption = caption;
	}
	public String getOrgCode() {
		return OrgCode;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	public Date getDistributeTime() {
		return DistributeTime;
	}
	public void setDistributeTime(Date distributeTime) {
		DistributeTime = distributeTime;
	}
	public String getChargeIdCode() {
		return ChargeIdCode;
	}
	public void setChargeIdCode(String chargeIdCode) {
		ChargeIdCode = chargeIdCode;
	}
	public String getChargeName() {
		return ChargeName;
	}
	public void setChargeName(String chargeName) {
		ChargeName = chargeName;
	}
	public String getHardwareVersion() {
		return HardwareVersion;
	}
	public void setHardwareVersion(String hardwareVersion) {
		HardwareVersion = hardwareVersion;
	}
	public String getSoftwareVersion() {
		return SoftwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		SoftwareVersion = softwareVersion;
	}
	public Integer getConnectStatus() {
		return ConnectStatus;
	}
	public void setConnectStatus(Integer connectStatus) {
		ConnectStatus = connectStatus;
	}
	public String getCreatManID() {
		return CreatManID;
	}
	public void setCreatManID(String creatManID) {
		CreatManID = creatManID;
	}
	public String getOrgID() {
		return OrgID;
	}
	public void setOrgID(String orgID) {
		OrgID = orgID;
	}
	public String getSaasID() {
		return SaasID;
	}
	public void setSaasID(String saasID) {
		SaasID = saasID;
	}
	public Date getCreatTime() {
		return CreatTime;
	}
	public void setCreatTime(Date creatTime) {
		CreatTime = creatTime;
	}
	
	
	

}
