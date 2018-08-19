package com.boco.modules.fdoc.model;

import java.util.Date;

public class ChineseMedicineFollowUpEntity {
    /**
    * 中医随访ID
    */
    private String id;

    /**
    * 居民ID
    */
    private String personId;

    /**
    * 随访方式：1 门诊 2 家庭 3 电话
    */
    private String wayUp;

    /**
    * 随访医生ID
    */
    private String doctorId;

    /**
    * 随访日期
    */
    private Date followUpDate;

    /**
    * 下次随访日期
    */
    private Date nextFollowUpDate;

    /**
    * 随访记录创建日期
    */
    private Date createTime;

    /**
    * 最后一次修改日期
    */
    private Date updateTime;

    /**
    * 最后一次修改医生id
    */
    private String updateId;

    /**
    * 答案列表，用逗号隔开
    */
    private String answers;

    /**
    * 平和质：0.不是 1是 2倾向是
    */
    private String moderateQuality;

    /**
    * 平和质得分
    */
    private Integer moderateQualityScore;

    /**
    * 气虚质：0.不是 1是 2倾向是
    */
    private String qualityDeficiency;

    /**
    * 气虚质得分
    */
    private Integer qualityDeficiencyScore;

    /**
    * 阳虚质：0.不是  1是 2倾向是
    */
    private String yangQuality;

    /**
    * 阳虚质得分
    */
    private Integer yangQualityScore;

    /**
    * 阴虚质：0.不是 1是 2倾向是
    */
    private String yinQuality;

    /**
    * 阴虚质得分
    */
    private Integer yinQualityScore;

    /**
    * 痰湿质：0. 不是 1是 2倾向是
    */
    private String phlegm;

    /**
    * 痰湿质得分
    */
    private Integer phlegmScore;

    /**
    * 湿热质   0. 不是 1是 2倾向是
    */
    private String dampHeat;

    /**
    * 湿热质得分
    */
    private Integer dampHeatScore;

    /**
    * 血瘀质：0. 不是 1是 2倾向是
    */
    private String bloodQuality;

    /**
    * 血瘀质得分
    */
    private Integer bloodQualityScore;

    /**
    * 气郁质：0.不是  1是 2倾向是
    */
    private String qiQuality;

    /**
    * 气郁质得分
    */
    private Integer qiQualityScore;

    /**
    * 特秉质：0.不是 1是 2倾向是
    */
    private String tebingquality;

    /**
    * 特秉质得分
    */
    private Integer tebingqualityScore;

    /**
    * 其他建议
    */
    private String otherSuggestions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getWayUp() {
        return wayUp;
    }

    public void setWayUp(String wayUp) {
        this.wayUp = wayUp;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Date getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(Date followUpDate) {
        this.followUpDate = followUpDate;
    }

    public Date getNextFollowUpDate() {
        return nextFollowUpDate;
    }

    public void setNextFollowUpDate(Date nextFollowUpDate) {
        this.nextFollowUpDate = nextFollowUpDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getModerateQuality() {
        return moderateQuality;
    }

    public void setModerateQuality(String moderateQuality) {
        this.moderateQuality = moderateQuality;
    }

    public Integer getModerateQualityScore() {
        return moderateQualityScore;
    }

    public void setModerateQualityScore(Integer moderateQualityScore) {
        this.moderateQualityScore = moderateQualityScore;
    }

    public String getQualityDeficiency() {
        return qualityDeficiency;
    }

    public void setQualityDeficiency(String qualityDeficiency) {
        this.qualityDeficiency = qualityDeficiency;
    }

    public Integer getQualityDeficiencyScore() {
        return qualityDeficiencyScore;
    }

    public void setQualityDeficiencyScore(Integer qualityDeficiencyScore) {
        this.qualityDeficiencyScore = qualityDeficiencyScore;
    }

    public String getYangQuality() {
        return yangQuality;
    }

    public void setYangQuality(String yangQuality) {
        this.yangQuality = yangQuality;
    }

    public Integer getYangQualityScore() {
        return yangQualityScore;
    }

    public void setYangQualityScore(Integer yangQualityScore) {
        this.yangQualityScore = yangQualityScore;
    }

    public String getYinQuality() {
        return yinQuality;
    }

    public void setYinQuality(String yinQuality) {
        this.yinQuality = yinQuality;
    }

    public Integer getYinQualityScore() {
        return yinQualityScore;
    }

    public void setYinQualityScore(Integer yinQualityScore) {
        this.yinQualityScore = yinQualityScore;
    }

    public String getPhlegm() {
        return phlegm;
    }

    public void setPhlegm(String phlegm) {
        this.phlegm = phlegm;
    }

    public Integer getPhlegmScore() {
        return phlegmScore;
    }

    public void setPhlegmScore(Integer phlegmScore) {
        this.phlegmScore = phlegmScore;
    }

    public String getDampHeat() {
        return dampHeat;
    }

    public void setDampHeat(String dampHeat) {
        this.dampHeat = dampHeat;
    }

    public Integer getDampHeatScore() {
        return dampHeatScore;
    }

    public void setDampHeatScore(Integer dampHeatScore) {
        this.dampHeatScore = dampHeatScore;
    }

    public String getBloodQuality() {
        return bloodQuality;
    }

    public void setBloodQuality(String bloodQuality) {
        this.bloodQuality = bloodQuality;
    }

    public Integer getBloodQualityScore() {
        return bloodQualityScore;
    }

    public void setBloodQualityScore(Integer bloodQualityScore) {
        this.bloodQualityScore = bloodQualityScore;
    }

    public String getQiQuality() {
        return qiQuality;
    }

    public void setQiQuality(String qiQuality) {
        this.qiQuality = qiQuality;
    }

    public Integer getQiQualityScore() {
        return qiQualityScore;
    }

    public void setQiQualityScore(Integer qiQualityScore) {
        this.qiQualityScore = qiQualityScore;
    }

    public String getTebingquality() {
        return tebingquality;
    }

    public void setTebingquality(String tebingquality) {
        this.tebingquality = tebingquality;
    }

    public Integer getTebingqualityScore() {
        return tebingqualityScore;
    }

    public void setTebingqualityScore(Integer tebingqualityScore) {
        this.tebingqualityScore = tebingqualityScore;
    }

    public String getOtherSuggestions() {
        return otherSuggestions;
    }

    public void setOtherSuggestions(String otherSuggestions) {
        this.otherSuggestions = otherSuggestions;
    }
}