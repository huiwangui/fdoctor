package com.boco.modules.boco;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.boco.common.JunitTestCase;
import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.model.AdmissionStopEntity;
import com.boco.modules.fdoc.service.AdmissionStopService;

public class AdmissionStopServiceTest extends JunitTestCase{
	
	@Resource
	AdmissionStopService admissionStopService;
	
	@Test
	public void testGetStopList(){
		BaseJsonVo vo = admissionStopService.getStopList();
		logger.info(((List<AdmissionStopEntity>)admissionStopService.getStopList().getData()).toString());
	}
}
