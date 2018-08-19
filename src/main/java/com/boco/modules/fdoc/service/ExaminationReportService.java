package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.ExaminationReportEntity;
import com.boco.modules.fdoc.vo.ExaminationReportVo;

public interface ExaminationReportService {
	/**
	 * 
	 * addExaminationReport:(新增体检报告). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer addExaminationReport(ExaminationReportEntity entity);
	/**
	 * 
	 * getReportList:(获取体检报告列表). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<ExaminationReportVo> getReportList(ExaminationReportVo vo);
	/**
	 * 
	 * getReportList:(获取体检报告详情). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public ExaminationReportVo getReportDetail(ExaminationReportVo vo);
}
