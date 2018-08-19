package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.ExaminationReportEntity;
import com.boco.modules.fdoc.vo.ExaminationReportVo;

@MyBatisDao
public interface ExaminationReportDao extends CrudDao<ExaminationReportEntity>{
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
