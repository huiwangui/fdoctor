package com.boco.modules.fdoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.SigServicepackDao;
import com.boco.modules.fdoc.model.SigServicepackEntity;
import com.boco.modules.fdoc.service.SigServicepackService;
import com.boco.modules.fdoc.vo.ServicepackDetailsVo;
import com.boco.modules.fdoc.vo.SigServicepackVo;

@Service
public class SigServicepackServiceImpl implements SigServicepackService{
	
	@Resource
	SigServicepackDao sigServicepackDao;

	@Override
	public List<SigServicepackEntity> getAll() {
		return sigServicepackDao.findAllList(null);
	}

	@Override
	public List<SigServicepackVo> getAllDetails() {
		List<SigServicepackEntity> entities = sigServicepackDao.findAllList(null);	//获取所有服务包信息
		List<SigServicepackVo> vos = new ArrayList<SigServicepackVo>();
		
		for (SigServicepackEntity sigServicepackEntity : entities) {
			SigServicepackVo vo = new SigServicepackVo();
			//封装服务包基础信息
			vo.setId(sigServicepackEntity.getId());
			vo.setPackName(sigServicepackEntity.getPackName());
			vo.setPackPrice(sigServicepackEntity.getPackPrice());
			vo.setUserPay(sigServicepackEntity.getUserPay());
			vo.setOrgratio(sigServicepackEntity.getOrgratio());
			vo.setAdviceGroup(sigServicepackEntity.getAdviceGroup());
			vo.setTarget(sigServicepackEntity.getTarget());
			vo.setRemarks(sigServicepackEntity.getRemarks());
			vo.setPackValue(sigServicepackEntity.getPackValue());
			vo.setPackType(sigServicepackEntity.getPackType());
			vo.setDetails(sigServicepackDao.getServicepackDetail(sigServicepackEntity.getId()));
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<ServicepackDetailsVo> getDetailsByPackId(Integer packId) {
		return sigServicepackDao.getServicepackDetail(packId);
	}

}
