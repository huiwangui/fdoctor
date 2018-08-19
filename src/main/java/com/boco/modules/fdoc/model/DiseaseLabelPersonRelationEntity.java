package com.boco.modules.fdoc.model;

/**
 * 
 * ClassName: DiseaseLabelPersonRelationEntity <br/>
 * Reason: 标签-居民对应实体类. <br/>
 * date: 2017年2月13日 下午1:43:18 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class DiseaseLabelPersonRelationEntity {
    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 标签ID
    */
    private Integer labelId;

    /**
     * 居民ID
     */
    private String personId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}