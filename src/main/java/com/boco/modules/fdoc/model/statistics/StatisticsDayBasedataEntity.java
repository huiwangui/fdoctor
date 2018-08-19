package com.boco.modules.fdoc.model.statistics;

import java.util.Date;

public class StatisticsDayBasedataEntity {
	/**
	 * 主键ID，自增
	 */
	private Integer id;

	/**
	 * 人员总数
	 */
	private int personCount;

	/**
	 * 签约人数总和
	 */
	private int signCount;

	/**
	 * 高血压人数
	 */
	private int hyperCount;

	/**
	 * diabetes_count
	 */
	private int diabetesCount;

	/**
	 * 儿童人数
	 */
	private int childrenCount;

	/**
	 * 重度精神病人数
	 */
	private int majorPsychosisCount;

	/**
	 * 机构总数
	 */
	private int orgCount;

	/**
	 * 医生团队总数
	 */
	private int teamCount;



	/**
	 * 统计日期
	 */
	private Date statisticsDate;

	/**
	 * 统计记录创建时间
	 */
	private Date createTime;
	/**
	 * 签约户数
	 */
	private int familyCount;

	public int getFamilyCount() {
		return familyCount;
	}

	public void setFamilyCount(int familyCount) {
		this.familyCount = familyCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public int getSignCount() {
		return signCount;
	}

	public void setSignCount(int signCount) {
		this.signCount = signCount;
	}

	public int getHyperCount() {
		return hyperCount;
	}

	public void setHyperCount(int hyperCount) {
		this.hyperCount = hyperCount;
	}

	public int getDiabetesCount() {
		return diabetesCount;
	}

	public void setDiabetesCount(int diabetesCount) {
		this.diabetesCount = diabetesCount;
	}

	public int getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}

	public int getMajorPsychosisCount() {
		return majorPsychosisCount;
	}

	public void setMajorPsychosisCount(int majorPsychosisCount) {
		this.majorPsychosisCount = majorPsychosisCount;
	}

	public int getOrgCount() {
		return orgCount;
	}

	public void setOrgCount(int orgCount) {
		this.orgCount = orgCount;
	}

	public int getTeamCount() {
		return teamCount;
	}

	public void setTeamCount(int teamCount) {
		this.teamCount = teamCount;
	}

	public Date getStatisticsDate() {
		return statisticsDate;
	}

	public void setStatisticsDate(Date statisticsDate) {
		this.statisticsDate = statisticsDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}