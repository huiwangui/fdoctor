package com.boco.modules.fdoc.model.statistics;

import java.util.Date;

/**
 * Created by liwenbin on 2017/6/28.
 */
public class StatisticsDayTwoBasedataEntity {


    private String id;                                        //    id varchar(36) NOT NULL,
    private Integer hyperCount;                              //      hyper_count int(255) DEFAULT NULL COMMENT '高血压人次',
    private Integer dabetesCount;                            //      dabetes_count int(255) DEFAULT NULL COMMENT '糖尿病人次',
    private Integer childCount;                              //      child_count int(255) DEFAULT NULL COMMENT '儿童人次',
    private Integer cjCount;                                 //      cj_count int(255) DEFAULT NULL COMMENT '重精人次',
    private Integer profileCount;                           //      profile_count int(255) DEFAULT NULL COMMENT '建档量',
    private Integer healthAsseCount;                        //      health_asse_count int(255) DEFAULT NULL COMMENT '健康评估',
    private Integer interroCount;                           //      interro_count int(255) DEFAULT NULL COMMENT '问诊',
    private Integer healthEduCount;                         //      health_edu_count int(255) DEFAULT NULL COMMENT '健康教育',
    private Integer hyperPersonDeseaseTotal;               //      hyper_person_desease_total int(255) DEFAULT NULL COMMENT '年内高血压已管理',
    private Integer hyperPersonDeseaseEstimatel;           //      hyper_person_desease_estimatel int(255) DEFAULT NULL COMMENT '辖区高血压估算',
    private Integer personOverEighteen;                     //      person_over_eighteen int(255) DEFAULT NULL COMMENT '18岁以上人口',
    private Integer latestHyperNormalCount;                 //      latest_hyper_normal_count int(255) DEFAULT NULL COMMENT '最近一次随血压达标',
    private Integer dabetesPersonDeseaseTotal;            //      dabetes_person_desease_total int(255) DEFAULT NULL COMMENT '年内糖尿病已管理',
    private Integer dabetesPersonDeseaseEstimatel;         //      dabetes_person_desease_estimatel int(255) DEFAULT NULL COMMENT '辖区糖尿病估算',
    private Integer latestDabetesNormalCount;               //      latest_dabetes_normal_count int(255) unsigned zerofill DEFAULT NULL COMMENT '最近一次糖尿病达标',
   // private Integer countTime;                                //      type int(11) DEFAULT NULL COMMENT '宏观统计0；团队统计1',
    private Integer type;                                      //      team_id int(255) DEFAULT NULL,
    private String orgId;                                      //      org_id varchar(255) DEFAULT NULL COMMENT '机构ID',
    private Integer otherExaminCount;                        //      other_examin_count int(11) DEFAULT NULL COMMENT '其他体检',
    private Integer oldManCount;                       // '老人体检'
    //      statistics_date date DEFAULT NULL,
    private String regionCode;//区域id
    private Date createTime;//统计时间
    private Date statisticsDate;//创建时间
    private Integer teamId;//团队id



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHyperCount() {
        return hyperCount;
    }

    public void setHyperCount(Integer hyperCount) {
        this.hyperCount = hyperCount;
    }

    public Integer getDabetesCount() {
        return dabetesCount;
    }

    public void setDabetesCount(Integer dabetesCount) {
        this.dabetesCount = dabetesCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getCjCount() {
        return cjCount;
    }

    public void setCjCount(Integer cjCount) {
        this.cjCount = cjCount;
    }

    public Integer getProfileCount() {
        return profileCount;
    }

    public void setProfileCount(Integer profileCount) {
        this.profileCount = profileCount;
    }

    public Integer getHealthAsseCount() {
        return healthAsseCount;
    }

    public void setHealthAsseCount(Integer healthAsseCount) {
        this.healthAsseCount = healthAsseCount;
    }

    public Integer getInterroCount() {
        return interroCount;
    }

    public void setInterroCount(Integer interroCount) {
        this.interroCount = interroCount;
    }

    public Integer getHealthEduCount() {
        return healthEduCount;
    }

    public void setHealthEduCount(Integer healthEduCount) {
        this.healthEduCount = healthEduCount;
    }

    public Integer getHyperPersonDeseaseTotal() {
        return hyperPersonDeseaseTotal;
    }

    public void setHyperPersonDeseaseTotal(Integer hyperPersonDeseaseTotal) {
        this.hyperPersonDeseaseTotal = hyperPersonDeseaseTotal;
    }

    public Integer getHyperPersonDeseaseEstimatel() {
        return hyperPersonDeseaseEstimatel;
    }

    public void setHyperPersonDeseaseEstimatel(Integer hyperPersonDeseaseEstimatel) {
        this.hyperPersonDeseaseEstimatel = hyperPersonDeseaseEstimatel;
    }

    public Integer getPersonOverEighteen() {
        return personOverEighteen;
    }

    public void setPersonOverEighteen(Integer personOverEighteen) {
        this.personOverEighteen = personOverEighteen;
    }

    public Integer getLatestHyperNormalCount() {
        return latestHyperNormalCount;
    }

    public void setLatestHyperNormalCount(Integer latestHyperNormalCount) {
        this.latestHyperNormalCount = latestHyperNormalCount;
    }

    public Integer getDabetesPersonDeseaseTotal() {
        return dabetesPersonDeseaseTotal;
    }

    public void setDabetesPersonDeseaseTotalc(Integer dabetesPersonDeseaseTotal) {
        this.dabetesPersonDeseaseTotal = dabetesPersonDeseaseTotal;
    }

    public Integer getDabetesPersonDeseaseEstimatel() {
        return dabetesPersonDeseaseEstimatel;
    }

    public void setDabetesPersonDeseaseEstimatel(Integer dabetesPersonDeseaseEstimatel) {
        this.dabetesPersonDeseaseEstimatel = dabetesPersonDeseaseEstimatel;
    }

    public Integer getLatestDabetesNormalCount() {
        return latestDabetesNormalCount;
    }

    public void setLatestDabetesNormalCount(Integer latestDabetesNormalCount) {
        this.latestDabetesNormalCount = latestDabetesNormalCount;
    }

    /*public Integer getCountTime() {
        return countTime;
    }

    public void setCountTime(Integer countTime) {
        this.countTime = countTime;
    }*/

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getOtherExaminCount() {
        return otherExaminCount;
    }

    public void setOtherExaminCount(Integer otherExaminCount) {
        this.otherExaminCount = otherExaminCount;
    }

    public Integer getOldManCount() {
        return oldManCount;
    }

    public void setOldManCount(Integer oldManCount) {
        this.oldManCount = oldManCount;
    }

    public void setDabetesPersonDeseaseTotal(Integer dabetesPersonDeseaseTotal) {
        this.dabetesPersonDeseaseTotal = dabetesPersonDeseaseTotal;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
