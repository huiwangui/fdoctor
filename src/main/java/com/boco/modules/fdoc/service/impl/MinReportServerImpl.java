package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.MinReportDao;
import com.boco.modules.fdoc.model.ReportHealthmonitorEntity;
import com.boco.modules.fdoc.service.MinReportService;
import com.boco.modules.fdoc.vo.MinReportVo;

@Service
public class MinReportServerImpl implements MinReportService{
	@Resource
	MinReportDao minDao;

	@Override
	public List<ReportHealthmonitorEntity> findList(String idCode, Date startTime, Date endTime) {
		MinReportVo vo=new MinReportVo();
		vo.setIdCode(idCode);
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		
		return minDao.findList(vo);
	}

	@Override
	public ReportHealthmonitorEntity getReport(String id) {
		return minDao.getReport(id);
	}

	@Override
	public Integer insertSpoInfo(ReportHealthmonitorEntity reportEntity) {
		return minDao.insertSpoInfo(reportEntity);
	}

	@Override
	public Integer insertBpInfo(ReportHealthmonitorEntity reportEntity) {
		return minDao.insertBpInfo(reportEntity);
	}

	@Override
	public Integer insertBsInfo(ReportHealthmonitorEntity reportEntity) {
		return minDao.insertBsInfo(reportEntity);
	}

	@Override
	public Integer insertTempInfo(ReportHealthmonitorEntity reportEntity) {
		return minDao.insertTempInfo(reportEntity);
	}

	@Override
	public Integer insertUrineInfo(ReportHealthmonitorEntity reportEntity) {
		return minDao.insertUrineInfo(reportEntity);
	}

	@Override
	public Integer insertEcgInfo(ReportHealthmonitorEntity reportEntity) {
		return minDao.insertEcgInfo(reportEntity);
	}

	@Override
	public Integer insertHrInfo(ReportHealthmonitorEntity reportEntity) {
		return minDao.insertHrInfo(reportEntity);
	}

	@Override
	public Integer updateSihealData(ReportHealthmonitorEntity reportEntity) {
		return minDao.updateSihealDate(reportEntity);
	}

	@Override
	public ReportHealthmonitorEntity getSihealReport(String personId, Date sdate, Date edate) {
		MinReportVo mVo=new MinReportVo();
		mVo.setIdCode(personId);
		mVo.setStartTime(sdate);
		mVo.setEndTime(edate);
		return minDao.getSihealReport(mVo);
	}

}
