package com.boco.modules.fdoc.vo.statistics;

import com.boco.modules.fdoc.model.statistics.StatisticsDayTwoBasedataEntity;

/**
 * Created by liwenbin on 2017/6/28.
 */
public class StatisticsDayTwoBasedataVo {

    private FollowUpPeopleStatistics followUpPeopleStatistics;
    private EaminationStatistics eaminationStatistics;
    private ServicedNumber servicedNumber;
    private ChronicDiseaseCtrl chronicDiseaseCtrl;
    private ChronicDiseaseManage chronicDiseaseManage;

    public StatisticsDayTwoBasedataVo() {
    }

    public StatisticsDayTwoBasedataVo(StatisticsDayTwoBasedataEntity twoBasedataEntity) {
        followUpPeopleStatistics = new FollowUpPeopleStatistics(twoBasedataEntity);
        eaminationStatistics = new EaminationStatistics(twoBasedataEntity);
        servicedNumber = new ServicedNumber(twoBasedataEntity);
        chronicDiseaseManage = new ChronicDiseaseManage(twoBasedataEntity);
        chronicDiseaseCtrl = new ChronicDiseaseCtrl(twoBasedataEntity);
    }

    public FollowUpPeopleStatistics getFollowUpPeopleStatistics() {
        return followUpPeopleStatistics;
    }

    public EaminationStatistics getEaminationStatistics() {
        return eaminationStatistics;
    }

    public ServicedNumber getServicedNumber() {
        return servicedNumber;
    }

    public ChronicDiseaseCtrl getChronicDiseaseCtrl() {
        return chronicDiseaseCtrl;
    }

    public ChronicDiseaseManage getChronicDiseaseManage() {
        return chronicDiseaseManage;
    }

    /*随访人次*/
    public static class FollowUpPeopleStatistics {
        private Integer hyperCount;                              //      hyper_count int(255) DEFAULT NULL COMMENT '高血压人次',
        private Integer dabetesCount;                            //      dabetes_count int(255) DEFAULT NULL COMMENT '糖尿病人次',
        private Integer childCount;                              //      child_count int(255) DEFAULT NULL COMMENT '儿童人次',
        private Integer cjCount;                                 //      cj_count int(255) DEFAULT NULL COMMENT '重精人次',

        public FollowUpPeopleStatistics(StatisticsDayTwoBasedataEntity twoBasedataEntity) {
            this.hyperCount = twoBasedataEntity.getHyperCount();
            this.dabetesCount = twoBasedataEntity.getDabetesCount();
            this.childCount = twoBasedataEntity.getChildCount();
            this.cjCount = twoBasedataEntity.getCjCount();
        }

        public Integer getHyperCount() {
            return hyperCount;
        }

        public Integer getDabetesCount() {
            return dabetesCount;
        }

        public Integer getChildCount() {
            return childCount;
        }

        public Integer getCjCount() {
            return cjCount;
        }
    }

    /*体检统计*/
    public static class EaminationStatistics {
        private Integer otherExaminCount;                        //      other_examin_count int(11) DEFAULT NULL COMMENT '其他体检'
        private Integer oldManCount;                       // '老人体检'

        public EaminationStatistics(StatisticsDayTwoBasedataEntity twoBasedataEntity) {
            this.otherExaminCount = twoBasedataEntity.getOtherExaminCount();
            this.oldManCount = twoBasedataEntity.getOldManCount();
        }

        public Integer getOtherExaminCount() {
            return otherExaminCount;
        }

        public Integer getOldManCount() {
            return oldManCount;
        }
    }

    /*服务人数*/
    public static class ServicedNumber {
        private Integer profileCount;                           //      profile_count int(255) DEFAULT NULL COMMENT '建档量',
        private Integer healthAsseCount;                        //      health_asse_count int(255) DEFAULT NULL COMMENT '健康评估',
        private Integer interroCount;                           //      interro_count int(255) DEFAULT NULL COMMENT '问诊',
        private Integer healthEduCount;                         //      health_edu_count int(255) DEFAULT NULL COMMENT '健康教育',

        public ServicedNumber(StatisticsDayTwoBasedataEntity twoBasedataEntity) {
            this.profileCount = twoBasedataEntity.getProfileCount();
            this.healthAsseCount = twoBasedataEntity.getHealthAsseCount();
            this.interroCount = twoBasedataEntity.getInterroCount();
            this.healthEduCount = twoBasedataEntity.getHealthEduCount();
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
    }

    /*慢病管理*/
    public static class ChronicDiseaseManage {
        private Integer hyperPersonDeseaseTotal;               //      hyper_person_desease_total int(255) DEFAULT NULL COMMENT '年内高血压已管理',
        private Integer hyperPersonDeseaseEstimatel;           //      hyper_person_desease_estimatel int(255) DEFAULT NULL COMMENT '辖区高血压估算',

        private Integer dabetesPersonDeseaseTotal;            //      dabetes_person_desease_total int(255) DEFAULT NULL COMMENT '年内糖尿病已管理',
        private Integer dabetesPersonDeseaseEstimatel;         //      dabetes_person_desease_estimatel int(255) DEFAULT NULL COMMENT '辖区糖尿病估算',

//        private Integer hyperPersonDeseaseManageRate;
//        private Integer dabetesPersonDeseaseManageRate;

        public ChronicDiseaseManage(StatisticsDayTwoBasedataEntity twoBasedataEntity) {
            this.hyperPersonDeseaseTotal = twoBasedataEntity.getHyperPersonDeseaseTotal();
            this.hyperPersonDeseaseEstimatel = twoBasedataEntity.getHyperPersonDeseaseEstimatel();
            this.dabetesPersonDeseaseTotal = twoBasedataEntity.getDabetesPersonDeseaseTotal();
            this.dabetesPersonDeseaseEstimatel = twoBasedataEntity.getDabetesPersonDeseaseEstimatel();
        }

        /*高血压管理率*/
        /*public Integer getHyperPersonDeseaseManageRate() {
            if (hyperPersonDeseaseTotal==null || hyperPersonDeseaseEstimatel == null ||  hyperPersonDeseaseEstimatel == 0) {
                return null;
            }
            return hyperPersonDeseaseTotal / hyperPersonDeseaseEstimatel;
        }

        *//*糖尿病管理率*//*
        public Integer getDabetesPersonDeseaseManageRate() {
            if (dabetesPersonDeseaseTotalc == null || dabetesPersonDeseaseEstimatel == null || dabetesPersonDeseaseEstimatel == 0) {
                return null;
            }
            return dabetesPersonDeseaseTotalc / dabetesPersonDeseaseEstimatel;
        }*/

        public Integer getHyperPersonDeseaseTotal() {
            return hyperPersonDeseaseTotal;
        }

        public Integer getHyperPersonDeseaseEstimatel() {
            return hyperPersonDeseaseEstimatel;
        }

        public Integer getDabetesPersonDeseaseTotal() {
            return dabetesPersonDeseaseTotal;
        }

        public Integer getDabetesPersonDeseaseEstimatel() {
            return dabetesPersonDeseaseEstimatel;
        }
    }

    /*慢病控制*/
    public static class ChronicDiseaseCtrl {
        public ChronicDiseaseCtrl(StatisticsDayTwoBasedataEntity twoBasedataEntity) {
            //TODO 计算慢病控制比率
        }
        //暂时使用静态数据
//                private Integer hyperPersonDeseaseCtrlRate;
//        private Integer dabetesPersonDeseaseCtrlRate;

        public Integer getHyperPersonDeseaseCtrlRate() {
            return 0;
        }

        public Integer getDabetesPersonDeseaseCtrlRate() {
            return 0;
        }
    }

}
