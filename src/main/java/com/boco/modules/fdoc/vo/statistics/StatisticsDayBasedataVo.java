package com.boco.modules.fdoc.vo.statistics;

import java.util.Date;

/**
 * 
 * ClassName: 统计类Vo <br/>
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class StatisticsDayBasedataVo {
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
	 * 糖尿病人数
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
	 * 人数同比增长
	 */
	private double personIncrement;
	
	/**
	 * 签约数同比增长
	 */
	private double signIncrement;
	
	/**
	 * 高血压同比增长
	 */
	private double hyperIncrement;
	
	/**
	 * 糖尿病同比增长
	 */
	private double diabetesIncrement;
	
	/**
	 * 儿童同比增长
	 */
	private double childrenIncrement;
	
	/**
	 * 重性精神病同比增长
	 */
	private double majorPsychosisIncrement;
	
	/**
	 * 机构同比增长
	 */
	private double orgIncrement;
	
	/**
	 * 团队同比增长
	 */
	private double teamIncrement;
	
	/**
	 * 高血压月增长数
	 */
	private int hyperMonthIncNum;
	
	/**
	 * 糖尿病月增长数
	 */
	private int diabetesMonthIncNum;
	
	/**
	 * 儿童月增长数
	 */
	private int childrenMonthIncNum;
	
	/**
	 * 重性精神病月增长数
	 */
	private int majorPsychosisMonthIncNum;
	
	/**
	 * 团队队长姓名
	 */
	private String leaderName;
	
	/**
	 * 签约比
	 */
	private double signPer;
	/**
	 * 签约户数
	 */
	private int familyCount;

	/**
	 * 老年人人数
	 */
	private int oldCount;

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

	public double getPersonIncrement() {
		return personIncrement;
	}

	public void setPersonIncrement(double personIncrement) {
		this.personIncrement = personIncrement;
	}

	public double getSignIncrement() {
		return signIncrement;
	}

	public void setSignIncrement(double signIncrement) {
		this.signIncrement = signIncrement;
	}

	public double getHyperIncrement() {
		return hyperIncrement;
	}

	public void setHyperIncrement(double hyperIncrement) {
		this.hyperIncrement = hyperIncrement;
	}

	public double getDiabetesIncrement() {
		return diabetesIncrement;
	}

	public void setDiabetesIncrement(double diabetesIncrement) {
		this.diabetesIncrement = diabetesIncrement;
	}

	public double getChildrenIncrement() {
		return childrenIncrement;
	}

	public void setChildrenIncrement(double childrenIncrement) {
		this.childrenIncrement = childrenIncrement;
	}

	public double getMajorPsychosisIncrement() {
		return majorPsychosisIncrement;
	}

	public void setMajorPsychosisIncrement(double majorPsychosisIncrement) {
		this.majorPsychosisIncrement = majorPsychosisIncrement;
	}

	public double getOrgIncrement() {
		return orgIncrement;
	}

	public void setOrgIncrement(double orgIncrement) {
		this.orgIncrement = orgIncrement;
	}

	public double getTeamIncrement() {
		return teamIncrement;
	}

	public void setTeamIncrement(double teamIncrement) {
		this.teamIncrement = teamIncrement;
	}

	public int getHyperMonthIncNum() {
		return hyperMonthIncNum;
	}

	public void setHyperMonthIncNum(int hyperMonthIncNum) {
		this.hyperMonthIncNum = hyperMonthIncNum;
	}

	public int getDiabetesMonthIncNum() {
		return diabetesMonthIncNum;
	}

	public void setDiabetesMonthIncNum(int diabetesMonthIncNum) {
		this.diabetesMonthIncNum = diabetesMonthIncNum;
	}

	public int getChildrenMonthIncNum() {
		return childrenMonthIncNum;
	}

	public void setChildrenMonthIncNum(int childrenMonthIncNum) {
		this.childrenMonthIncNum = childrenMonthIncNum;
	}

	public int getMajorPsychosisMonthIncNum() {
		return majorPsychosisMonthIncNum;
	}

	public void setMajorPsychosisMonthIncNum(int majorPsychosisMonthIncNum) {
		this.majorPsychosisMonthIncNum = majorPsychosisMonthIncNum;
	}

	public double getSignPer() {
		return signPer;
	}

	public void setSignPer(double signPer) {
		this.signPer = signPer;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}


	public int getOldCount() {
		return oldCount;
	}

	public void setOldCount(int oldCount) {
		this.oldCount = oldCount;
	}
}
