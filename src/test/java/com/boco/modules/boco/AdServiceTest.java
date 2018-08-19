package com.boco.modules.boco;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.boco.common.JunitTestCase;
import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.model.AdEntity;
import com.boco.modules.fdoc.service.AdService;

public class AdServiceTest extends JunitTestCase{
	
	@Resource
	AdService adService;
	
	@Test
	public void testAdService(){
		BaseJsonVo vo = adService.getAds();
		logger.info(((List<AdEntity>)vo.getData()).toString());
	}
}
