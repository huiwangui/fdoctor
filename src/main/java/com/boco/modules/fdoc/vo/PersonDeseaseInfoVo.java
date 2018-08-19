package com.boco.modules.fdoc.vo;

import java.util.Date;

/**
 * Tilte: PersonDeseaseInfoVo 
 * Description:
 * @author h
 * @date  2018年1月3日下午2:31:49
 * @version 1.0
 *  
 */
public class PersonDeseaseInfoVo {
	  /**
	    * 主键ID
	    */
	    private String personId;

	    /**
	    * 是否为高血压  0.否  1.是
	    */
	    private String hyperFlag;

	    /**
	    * 是否为糖尿病  0.否  1.是
	    */
	    private String diabetesFlag;

	    /**
	    * 是否为儿童  0.否  1.是
	    */
	    private String childFlag;
	    /**
	     * 是否为重性精神病  0.否  1.是
	     */
	     private String majorPsychosisFlag;

	    /**
	    * 是否为老年人  0.否  1.是
	    */
	    private String oldmFlag;
	    /**
	     * 是否为普通成人  0.否  1.是
	     */
	    private String adultFlag;

		/**
	     * 是否为孕产妇  0.否  1.是
	     */
	    private String maternalFlag;
	    /**
	     * 最后一次修改时间
	     */
	    private Date updateTime;
		public String getPersonId() {
			return personId;
		}
		public void setPersonId(String personId) {
			this.personId = personId;
		}
		public String getHyperFlag() {
			return hyperFlag;
		}
		public void setHyperFlag(String hyperFlag) {
			this.hyperFlag = hyperFlag;
		}
		public String getDiabetesFlag() {
			return diabetesFlag;
		}
		public void setDiabetesFlag(String diabetesFlag) {
			this.diabetesFlag = diabetesFlag;
		}
		public String getChildFlag() {
			return childFlag;
		}
		public void setChildFlag(String childFlag) {
			this.childFlag = childFlag;
		}
		public String getMajorPsychosisFlag() {
			return majorPsychosisFlag;
		}
		public void setMajorPsychosisFlag(String majorPsychosisFlag) {
			this.majorPsychosisFlag = majorPsychosisFlag;
		}
		public String getOldmFlag() {
			return oldmFlag;
		}
		public void setOldmFlag(String oldmFlag) {
			this.oldmFlag = oldmFlag;
		}
		public String getAdultFlag() {
			return adultFlag;
		}
		public void setAdultFlag(String adultFlag) {
			this.adultFlag = adultFlag;
		}
		public String getMaternalFlag() {
			return maternalFlag;
		}
		public void setMaternalFlag(String maternalFlag) {
			this.maternalFlag = maternalFlag;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
	    
}
