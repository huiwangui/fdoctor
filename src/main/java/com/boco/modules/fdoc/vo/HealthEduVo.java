package com.boco.modules.fdoc.vo;

import java.util.Date;

import com.boco.common.persistence.Page;

public class HealthEduVo {
	
	/*
	 * 表主键
	 */
	private Integer id;
	/*
	 * 活动时间
	 */
	private Date organizeTime;
	/*
	 * 活动形式：
	 * 1，讲座  2，卫生科普宣传栏或展览  3，健康咨询  4，电化教育  5，发放宣传资料  6，知识和技能竞赛
	 * 7，举办健康知识讲座  8，开展公众健康咨询活动  9，设置健康教育宣传栏  10，提供健康教育资料  11，其他
	 */
	private String organizer;
	/*
	 * 组织方式
	 */
	private String organizeWay;
	/*
	 * 活动地址
	 */
	private String address;
	/*
	 * 活动主题
	 */
	private String theme;
	/*
	 * 主题日：
	 * 1，世界防治麻风病日  2，中国爱耳日  3，世界睡眠日  4，世界水日  5，世界气象日  6，世界结核病防治日  7，世界卫生日  8，世界地球日
	 * 9，全国预防接种宣传日  10，世界哮喘日  11，世界红十字日  12，国际护士节  13，全国碘缺乏病宣传日  14，全国助残日  15，中国母乳喂养日/中国学生营养日
	 * 16，世界无烟日  17，世界环境日  18，全国爱眼日  19，国际禁毒日  20，世界人口日  21，世界母乳喂养周  22，乙肝宣传日  23，全国爱牙日
	 * 24，世界老年性痴呆病宣传日  25，国际盲人节  26，国际老人节  27，全国高血压日  28，世界视觉日  29，世界骨质疏松日 30，世界精神/居室卫生日  31，国际口吃日
	 * 32，男性健康日  33，世界传统药日  34，全国食品卫生法宣传周  35，世界糖尿病日  36，世界艾滋日  37，世界残疾人日/世界强化免疫日 38，世界防治哮喘日
	 */
	private String themeDay;
	/*
	 * 人员类别：
	 * 1，亲少年  2，妇女  3，老年人  4，残疾人  5，儿童低于6岁家长  6，农民工  7其他
	 */
	private String crowdType;
	/*
	 * 人员数量
	 */
	private String crowdNum;
	/*
	 * 资料发放种类
	 */
	private String datumType;
	/*
	 * 资料发放数
	 */
	private String datumNum;
	/*
	 * 活动内容
	 */
	private String activityInfo;
	/*
	 * 总结评价
	 */
	private String summary;
	/*
	 * 认知评价
	 */
	private String cognitive;
	/*
	 * 团队Id
	 */
	private String teamId;
	/*
	 * 创建时间
	 */
	private Date createTime;
	/*
	 * 创建人
	 */
	private String createBy;
	/*
	 * 修改时间
	 */
	private Date updateTime;
	/*
	 * 修改人
	 */
	private String updateBy;
	/*
	 * 分页控件
	 */
	private Page<HealthEduVo> page;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getOrganizeTime() {
		return organizeTime;
	}
	public void setOrganizeTime(Date organizeTime) {
		this.organizeTime = organizeTime;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	public String getOrganizeWay() {
		return organizeWay;
	}
	public void setOrganizeWay(String organizeWay) {
		this.organizeWay = organizeWay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getThemeDay() {
		return themeDay;
	}
	public void setThemeDay(String themeDay) {
		this.themeDay = themeDay;
	}
	public String getCrowdType() {
		return crowdType;
	}
	public void setCrowdType(String crowdType) {
		this.crowdType = crowdType;
	}
	public String getCrowdNum() {
		return crowdNum;
	}
	public void setCrowdNum(String crowdNum) {
		this.crowdNum = crowdNum;
	}
	public String getDatumType() {
		return datumType;
	}
	public void setDatumType(String datumType) {
		this.datumType = datumType;
	}
	public String getDatumNum() {
		return datumNum;
	}
	public void setDatumNum(String datumNum) {
		this.datumNum = datumNum;
	}
	public String getActivityInfo() {
		return activityInfo;
	}
	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getCognitive() {
		return cognitive;
	}
	public void setCognitive(String cognitive) {
		this.cognitive = cognitive;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Page<HealthEduVo> getPage() {
		return page;
	}
	public void setPage(Page<HealthEduVo> page) {
		this.page = page;
	}
	

}
