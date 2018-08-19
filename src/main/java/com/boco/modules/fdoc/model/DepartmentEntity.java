package com.boco.modules.fdoc.model;
import java.util.Date;
import java.util.List;

import com.boco.common.persistence.DataEntity;
import com.boco.modules.fdoc.vo.DepartmentVo;

   /**
    * t_department 实体类
    * Thu Jul 28 10:14:38 CST 2016 sun
    */ 


public class DepartmentEntity extends DataEntity<DepartmentEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Integer hisId;//科室编号（HIS中的编号）
	private String name;//科室名称
	private String code;//科室标准码
	private String detail;//科室描述
	private String ageLimit;//年龄限制
	private Date updateTime;//更新时间
	private String level;//科室级别（0为大分类科室，1为详细科室）
	private String ima;
	private Integer bookingNum;
	private List<DepartmentVo> subDepts; //子科室
	private Date createTime;//创建时间
	public String getIma() {
		return ima;
	}
	public void setIma(String ima) {
		this.ima = ima;
	}
	public Integer getBookingNum() {
		return bookingNum;
	}
	public void setBookingNum(Integer bookingNum) {
		this.bookingNum = bookingNum;
	}
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setHisId(Integer hisId){
	this.hisId=hisId;
	}
	public Integer getHisId(){
		return hisId;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setCode(String code){
	this.code=code;
	}
	public String getCode(){
		return code;
	}
	public void setDetail(String detail){
	this.detail=detail;
	}
	public String getDetail(){
		return detail;
	}
	public void setAgeLimit(String ageLimit){
	this.ageLimit=ageLimit;
	}
	public String getAgeLimit(){
		return ageLimit;
	}
	public void setUpdateTime(Date updateTime){
	this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setLevel(String level){
	this.level=level;
	}
	public String getLevel(){
		return level;
	}
	public List<DepartmentVo> getSubDepts() {
		return subDepts;
	}
	public void setSubDepts(List<DepartmentVo> subDepts) {
		this.subDepts = subDepts;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

