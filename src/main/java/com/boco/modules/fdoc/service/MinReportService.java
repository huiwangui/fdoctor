package com.boco.modules.fdoc.service;

import java.util.Date;
import java.util.List;

import com.boco.modules.fdoc.model.ReportHealthmonitorEntity;
/**
 * 
 * @author lzz
 *
 */
public interface MinReportService {
	
	/**
	 * findList:(获取某人时间范围内的体检记录). <br/>
	 * @param idCode
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ReportHealthmonitorEntity> findList(String idCode,Date startTime,Date endTime);

	/**
	 * 根据id获取某单条数据
	 * Title MinReportService.java
	 * Description: 
	 * @param id
	 * @return
	 * @author lzz
	 */
	public ReportHealthmonitorEntity getReport(String id);

	/**
	 * 新增血氧数据到一体机数据表中
	 * Title MinReportService.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertSpoInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增血压数据到一体机
	 * Title MinReportService.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertBpInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增血糖数据到一体机
	 * Title MinReportService.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertBsInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增体温数据到一体机
	 * Title MinReportService.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertTempInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增尿常规数据到一体机
	 * Title MinReportService.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertUrineInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增心电数据到一体机
	 * Title MinReportService.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertEcgInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增心率数据到一体机
	 * Title MinReportService.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertHrInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 已有数据的情况下进行数据的修改
	 * Title MinReportService.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer updateSihealData(ReportHealthmonitorEntity reportEntity);

	/**
	 * 查询新海数据有无上传
	 * Title MinReportService.java
	 * Description: 
	 * @param personId
	 * @param sdate
	 * @param edate
	 * @return
	 * @author lzz
	 */
	public ReportHealthmonitorEntity getSihealReport(String personId, Date sdate, Date edate);

}
