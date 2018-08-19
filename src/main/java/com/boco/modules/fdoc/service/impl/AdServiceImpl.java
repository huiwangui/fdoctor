package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.dao.AdDao;
import com.boco.modules.fdoc.model.AdEntity;
import com.boco.modules.fdoc.service.AdService;

/**
 * @author samsung
 * @Time 2016年8月3日下午2:32:51
 */
@Service
public class AdServiceImpl implements AdService{

	@Resource
	AdDao adDao;

	@Override
	public BaseJsonVo getAds() {
		List<AdEntity> list = adDao.findAdList();
		BaseJsonVo vo = new BaseJsonVo();
		vo.setData(list);
		return vo;
	}

}
