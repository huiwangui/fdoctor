package com.boco.modules.fdoc.model;

import java.util.List;

import com.boco.common.persistence.DataEntity;

public class DoctorDetailScheCommEntity extends DataEntity<DoctorDetailScheCommEntity>{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private DoctorEntity doc;
	private List<DoctorCommentEntity> comm;
	private List<ScheduleEntity> sche;
	public DoctorEntity getDoc() {
		return doc;
	}
	public void setDoc(DoctorEntity doc) {
		this.doc = doc;
	}
	public List<DoctorCommentEntity> getComm() {
		return comm;
	}
	public void setComm(List<DoctorCommentEntity> comm) {
		this.comm = comm;
	}
	public List<ScheduleEntity> getSche() {
		return sche;
	}
	public void setSche(List<ScheduleEntity> sche) {
		this.sche = sche;
	}
}
