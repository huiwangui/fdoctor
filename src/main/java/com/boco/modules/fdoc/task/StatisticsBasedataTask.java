
package com.boco.modules.fdoc.task;

import com.boco.modules.fdoc.service.StatisticsDayBasedataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/* *
 * ClassName: 统计数据定时任务 <br/>
 * @author q
 * @version
* @since JDK 1.7+
*/


/*@Component("statisticsBasedataTask")
public class StatisticsBasedataTask {

    @Resource
    StatisticsDayBasedataService statisticsService;

    @Scheduled(cron = "0 0 23 * * ?")    //每天23点的时候触发

    public void basedataTask() {
        statisticsService.callDayBasedataProcedure();
    }

    @Scheduled(cron = "0 0 23 * * ?")    //每天23点的时候触发
    public void teamdataTask() {
        statisticsService.callDayBasedataTeamProcedure();
    }

    @Scheduled(cron = "0 0 23 * * ?")    //每天23点的时候触发
    public void towndataTask() {
        statisticsService.callDayBasedataTownProcedure();
    }

    @Scheduled(cron = "0 0 1 * * ?")    //每天早上1点0的时候触发
    public void twoTeamDataTask() {
        statisticsService.callDayTwoTeamBasedataTeamProcedure();
    }

    @Scheduled(cron = "0 10 1 * * ?")    //每天早上1点10的时候触发
    public void twoBaseDataTask() {
        statisticsService.callDayTwoBasedataProcedure();
    }

    //每天早上1点执行调用接口获取随访数据更新到本地表
    @Scheduled(cron = "0 20 1 * * ?")    //每天1点20的时候触发
    public void updateTwoBaseDataTask() {
        statisticsService.getAllPhysicalExaminationFollowUP();
    }
    //每天早上1点执行调用接口获取随访数据更新到本地表
    @Scheduled(cron = "0 30 1 * * ?")    //每天1点30的时候触发
    public void updateTwoBaseDataFollowUpTask() {
        statisticsService.getTeamData();
    }

}*/

