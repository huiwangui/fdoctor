package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.ChineseMedicineFollowUpDao;
import com.boco.modules.fdoc.model.ChineseMedicineFollowUpEntity;
import com.boco.modules.fdoc.service.ChineseMedicineFollowUpService;
import com.boco.modules.fdoc.vo.ChineseMedicineFollowUpVo;

/**
 * 中医随访service
 * @author q
 *
 */
@Service
public class ChineseMedicineFollowUpServiceImpl implements ChineseMedicineFollowUpService{
	
	@Resource
	ChineseMedicineFollowUpDao followUpDao;

	@Override
	public List<ChineseMedicineFollowUpVo> getChsConsList(
			ChineseMedicineFollowUpVo vo) {
		return followUpDao.getChsConsList(vo);
	}

	@Override
	public Integer getChsConsCount(ChineseMedicineFollowUpVo vo) {
		return followUpDao.getChsConsCount(vo);
	}

	@Override
	public Integer addChsConsRecord(ChineseMedicineFollowUpEntity entity) {
		
		//设置随机数ID
		entity.setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
		
		//设置最近修改日期、创建日期
		Date nowDate = new Date();
		entity.setCreateTime(nowDate);
		entity.setUpdateTime(nowDate);
		
		return followUpDao.insert(entity);
	}

	@Override
	public Integer updateChsConsRecord(ChineseMedicineFollowUpEntity entity) {
		//设置最新修改日期
		entity.setUpdateTime(new Date());
		
		return followUpDao.update(entity);
	}

	@Override
	public ChineseMedicineFollowUpEntity getChsConsInfo(String id) {
		return followUpDao.getChsConsInfo(id);
	}

	@Override
	public ChineseMedicineFollowUpVo getLastChsCons() {
		return followUpDao.getLastChsCons();
	}

}
