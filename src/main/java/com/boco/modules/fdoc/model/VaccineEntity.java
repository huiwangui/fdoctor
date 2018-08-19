package com.boco.modules.fdoc.model;

import java.util.Date;

import com.boco.common.persistence.DataEntity;
/**
 * 疫苗实体类
 * @author q
 *
 */
public class VaccineEntity extends DataEntity<VaccineEntity>{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name; //疫苗名称
	private String typeName; //疫苗类型
	private String unit;//单位
	private String approvaNumber;//批准文号
	private Double money; //接种价格（保留）
	private Date createTime; //创建时间
	private Date updateTime; //修改时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getApprovaNumber() {
		return approvaNumber;
	}
	public void setApprovaNumber(String approvaNumber) {
		this.approvaNumber = approvaNumber;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "VaccineEntity [id=" + id + ", name=" + name + ", typeName="
				+ typeName + ", unit=" + unit + ", approvaNumber="
				+ approvaNumber + ", money=" + money + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
	
}
