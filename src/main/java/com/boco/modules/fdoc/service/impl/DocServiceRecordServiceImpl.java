package com.boco.modules.fdoc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.utils.NumberUtils;
import com.boco.modules.fdoc.dao.DocServiceRecordDao;
import com.boco.modules.fdoc.dao.SigServicepackDao;
import com.boco.modules.fdoc.dao.SignDao;
import com.boco.modules.fdoc.model.DocServiceRecordEntity;
import com.boco.modules.fdoc.model.SigServicepackEntity;
import com.boco.modules.fdoc.service.DocServiceRecordService;
import com.boco.modules.fdoc.vo.DocServiceRecordVo;

@Service
public class DocServiceRecordServiceImpl implements DocServiceRecordService {

	@Resource
	DocServiceRecordDao recordDao;
	@Resource
	SignDao signDao;
	@Resource
	SigServicepackDao servicePackDao;
	
	@Override
	public List<SigServicepackEntity> getPackList(String personId) {

		String values=NumberUtils.bitand(recordDao.getServicePackValue(personId));
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put("values", values);
		//根据服务包权值查询服务包列表
		List<SigServicepackEntity> servicePacks = servicePackDao.getServicePacksByValues(valuesMap);
		
		return servicePacks;
	}

	@Override
	public int addRecord(DocServiceRecordEntity recordEntity) {
		return recordDao.addRecord(recordEntity);
	}

	@Override
	public List<DocServiceRecordVo> getRecordList(DocServiceRecordVo searchVo) {
		return recordDao.getRecordList(searchVo);
	}

	@Override
	public Integer getUsedTimes(DocServiceRecordEntity recordEntity) {
		return recordDao.getUsedTimes(recordEntity);
	}

	@Override
	public Integer getRecordCount(DocServiceRecordVo searchVo) {
		return recordDao.getRecordCount(searchVo);
	}

	@Override
	public Integer getPackDetailsTimes(Integer detailsId) {
		return recordDao.getPackDetailsTimes(detailsId);
	}

}
