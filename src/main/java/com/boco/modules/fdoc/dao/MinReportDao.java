package com.boco.modules.fdoc.dao;

import java.util.List;


import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.ReportHealthmonitorEntity;
import com.boco.modules.fdoc.vo.MinReportVo;
/**
 * mindery体检查询（MinReport）
 * @author lzz
 *
 */
@MyBatisDao
public interface MinReportDao {
	
	/**
	 * 查询
	 * @param vo
	 * @return
	 */
	
	public List<ReportHealthmonitorEntity> findList(MinReportVo vo);

	/**
	 * 根据Id获取单条数据
	 * Title MinReportDao.java
	 * Description: 
	 * @param id
	 * @return
	 * @author lzz
	 */
	public ReportHealthmonitorEntity getReport(String id);

	/**
	 * 新增血氧数据到一体机
	 * Title MinReportDao.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertSpoInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增血压数据到一体机
	 * Title MinReportDao.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertBpInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增血糖数据到一体机
	 * Title MinReportDao.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertBsInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增体温数据到一体机
	 * Title MinReportDao.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertTempInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增尿常规数据到一体机
	 * Title MinReportDao.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertUrineInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增心电数据到一体机
	 * Title MinReportDao.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertEcgInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 新增心率数据到一体机
	 * Title MinReportDao.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer insertHrInfo(ReportHealthmonitorEntity reportEntity);

	/**
	 * 有数据的情况下进行修改
	 * Title MinReportDao.java
	 * Description: 
	 * @param reportEntity
	 * @return
	 * @author lzz
	 */
	public Integer updateSihealDate(ReportHealthmonitorEntity reportEntity);

	/**
	 * 根据条件获取是否有此条新海数据
	 * Title MinReportDao.java
	 * Description: 
	 * @param mVo
	 * @return
	 * @author lzz
	 */
	public ReportHealthmonitorEntity getSihealReport(MinReportVo mVo);

}
