package com.boco.modules.fdoc.model;

/**
 * 
 * ClassName: NationEntity <br/>
 * Reason: 民族实体类. <br/>
 * date: 2017年2月17日 上午11:03:57 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class NationEntity {
	/**
	 * 主键ID
	 */
    private Integer id;

    /**
     * 民族代码
     */
    private String nationCode;
    
    /**
     * 民族名称
     */
    private String nationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }
}