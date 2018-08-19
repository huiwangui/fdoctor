package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.HealthEduDao;
import com.boco.modules.fdoc.model.HealthEduEntity;
import com.boco.modules.fdoc.service.HealthEduService;
import com.boco.modules.fdoc.vo.HealthEduVo;

@Service
public class HealthEduServiceImpl implements HealthEduService {

	@Resource
	HealthEduDao eduDao;
	
	@Override
	public int getCount(HealthEduVo healthEduVo) {
		return eduDao.getCount(healthEduVo);
	}

	@Override
	public List<HealthEduVo> getEduList(HealthEduVo healthEduVo) {
		return eduDao.getEduList(healthEduVo);
	}

	@Override
	public int getCountALL(HealthEduVo healthEduVo) {
		return eduDao.getCountAll(healthEduVo);
	}

	@Override
	public HealthEduEntity getEduById(Integer id) {
		return eduDao.getEduById(id);
	}


	@Override
	public int deleteEduById(Integer id) {
		return eduDao.deleteEduById(id);
	}

	@Override
	public int saveNewEdu(HealthEduEntity eduEntity) {
		return eduDao.saveNewEdu(eduEntity);
	}

	@Override
	public int updateEdu(HealthEduEntity eduEntity) {
		return eduDao.updateEdu(eduEntity);
	}

	@Override
	public List<HealthEduVo> getEduByTheme(HealthEduVo eduVo) {
		return eduDao.getEduByTheme(eduVo);
	}

	@Override
	public int getCountByTheme(HealthEduVo eduVo) {
		return eduDao.getCountByTheme(eduVo);
	}

}
