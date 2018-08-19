package com.boco.modules.fdoc.vo;

import com.boco.modules.fdoc.vo.PersonInformationVo;

public class UserRelationVo {
	
	private int id;
	private String userUid;
	private String relation;
	private String personId;
	private PersonInformationVo person;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public PersonInformationVo getPerson() {
		return person;
	}
	public void setPerson(PersonInformationVo person) {
		this.person = person;
	}
	
	

}
