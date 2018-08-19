package com.boco.modules.fdoc.model;

import com.boco.common.persistence.DataEntity;

/**
    * t_hospital_dept 实体类
    * Thu Jul 28 10:28:36 CST 2016 sun
    */ 


public class HospitalDeptEntity extends DataEntity<HospitalDeptEntity>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int hospId;//医院ID
	private int deptId;//科室ID
	private byte deptLevel;//医院科室层级
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setHospId(int hospId){
	this.hospId=hospId;
	}
	public int getHospId(){
		return hospId;
	}
	public void setDeptId(int deptId){
	this.deptId=deptId;
	}
	public int getDeptId(){
		return deptId;
	}
	public void setDeptLevel(byte deptLevel){
	this.deptLevel=deptLevel;
	}
	public byte getDeptLevel(){
		return deptLevel;
	}
}

