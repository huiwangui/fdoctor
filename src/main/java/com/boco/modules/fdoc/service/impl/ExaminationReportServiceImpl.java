package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.ExaminationReportDao;
import com.boco.modules.fdoc.model.ExaminationReportEntity;
import com.boco.modules.fdoc.service.ExaminationReportService;
import com.boco.modules.fdoc.vo.ExaminationReportVo;
@Service
public class ExaminationReportServiceImpl implements ExaminationReportService{
	
	@Resource
	ExaminationReportDao reportDao;

	@Override
	public Integer addExaminationReport(ExaminationReportEntity entity) {
		return reportDao.insert(entity);
	}

	@Override
	public List<ExaminationReportVo> getReportList(ExaminationReportVo vo) {
		return reportDao.getReportList(vo);
	}

	@Override
	public ExaminationReportVo getReportDetail(ExaminationReportVo vo) {
		return reportDao.getReportDetail(vo);
	}

}
