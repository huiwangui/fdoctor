package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.VaccineDao;
import com.boco.modules.fdoc.service.VaccineService;
import com.boco.modules.fdoc.vo.VaccienDetailVo;

@Service
public class VaccineServiceImpl implements VaccineService{
	@Resource
	VaccineDao vaccineDao;
	@Override
	public List<VaccienDetailVo> getVaccineDetail() {
		return vaccineDao.getVaccineDetail();
	}

}
