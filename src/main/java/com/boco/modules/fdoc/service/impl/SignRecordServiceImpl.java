package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.dao.SigRecordDao;
import com.boco.modules.fdoc.model.SigRecordEntity;
import com.boco.modules.fdoc.service.SignRecordService;
import com.boco.modules.fdoc.vo.SigRecordVo;

@Service
public class SignRecordServiceImpl implements SignRecordService {

	@Resource
	SigRecordDao sigRecordDao;
	
	@Override
	public Page<SigRecordVo> querySignInfo(Page<SigRecordVo> page, SigRecordVo vo){
		vo.setPage(page);
		List<SigRecordVo> signList = sigRecordDao.querySignInfo(vo);
		page.setList(signList);
		return page;
	}

	@Override
	public List<SigRecordVo> queryServiceDetails(int sigId) {
		return sigRecordDao.queryServiceDetails(sigId);
	}

	@Override
	public List<SigRecordVo> queryFamdoctor() {
		return sigRecordDao.queryFamdoctor();
	}

	@Override
	public String saveRecord(SigRecordEntity record) {
		String msg  = "0";
		String detail = record.getDetailsId();
		String[] d = detail.split(";");
		if (!"-1".equals(d[1])) {
			SigRecordVo vo = new SigRecordVo();
			vo.setResId(record.getResId());
			vo.setDetailsId(d[0]);
			int count = sigRecordDao.queryCount(vo);
			if (count>=Integer.parseInt(d[1])) {
				msg = "1该服务项目已经达到最大服务次数";
				return msg;
			}
		}
		record.setDetailsId(d[0]);
		record.setCreateTime(new Date());
		sigRecordDao.saverecord(record);
		return msg;
	}

	@Override
	public Page<SigRecordVo> queryRecord(Page<SigRecordVo> page, SigRecordVo vo) {
		vo.setPage(page);
		List<SigRecordVo> recordList = sigRecordDao.queryRecord(vo);
		page.setList(recordList);
		return page;
	}

}
