package com.boco.modules.fdoc.model;
import java.util.Date;
/**
 * 表sys_organization
 * @author lzz
 *
 */
public class SysOrganizationEntity {

	/**
	 * 主键GUID
	 */
	private String ID;
	/**
	 * 机构代码
	 */
	private String Code;
	/**
	 * 机构名称
	 */
	private String Name;
	/**
	 * 机构地址
	 */
	private String Caption;
	/**
	 * 级别
	 */
	private String Level;
	/**
	 * 联系人
	 */
	private String Contacter;
	/**
	 * 联系电话
	 */
	private String Telephone;
	/**
	 * 创建人即i客户管理员ID GUID
	 */
	private String CreatManID;
	/**
	 * 所属云实力ID，GUID
	 */
	private String SaasID;
	/**
	 * 创建时间
	 */
	private Date CreatTime;
	/**
	 * 树形编码
	 */
	private String TreeCode;
	/**
	 * 负责人
	 */
	private String ResponsiblePerson;
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
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getContacter() {
		return Contacter;
	}
	public void setContacter(String contacter) {
		Contacter = contacter;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getCreatManID() {
		return CreatManID;
	}
	public void setCreatManID(String creatManID) {
		CreatManID = creatManID;
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
	public String getTreeCode() {
		return TreeCode;
	}
	public void setTreeCode(String treeCode) {
		TreeCode = treeCode;
	}
	public String getResponsiblePerson() {
		return ResponsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		ResponsiblePerson = responsiblePerson;
	}
	
	
}
