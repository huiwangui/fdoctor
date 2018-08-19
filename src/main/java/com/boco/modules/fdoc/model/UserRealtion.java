package com.boco.modules.fdoc.model;

import java.io.Serializable;

public class UserRealtion implements Serializable {
    private Integer id;

    private String personId;

    private String userUid;

    private String relation;

    private static final long serialVersionUID = 1L;

    public UserRealtion(Integer id, String personId, String userUid, String relation) {
        this.id = id;
        this.personId = personId;
        this.userUid = userUid;
        this.relation = relation;
    }

    public UserRealtion() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserRealtion other = (UserRealtion) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPersonId() == null ? other.getPersonId() == null : this.getPersonId().equals(other.getPersonId()))
            && (this.getUserUid() == null ? other.getUserUid() == null : this.getUserUid().equals(other.getUserUid()))
            && (this.getRelation() == null ? other.getRelation() == null : this.getRelation().equals(other.getRelation()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPersonId() == null) ? 0 : getPersonId().hashCode());
        result = prime * result + ((getUserUid() == null) ? 0 : getUserUid().hashCode());
        result = prime * result + ((getRelation() == null) ? 0 : getRelation().hashCode());
        return result;
    }
}