package com.boco.modules.fdoc.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.service.AdmissionStopService;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/admissionStop",produces="text/json;charset=utf-8")
public class AdmissionStopApi {
	
	@Resource
	AdmissionStopService admissionStopService;
	
	@RequestMapping(value="/getStopList" ,method=RequestMethod.GET)
	@ApiOperation(value="获取停诊列表",notes="{}",response=BaseJsonVo.class)
	public String getStopList(){
		BaseJsonVo vo = admissionStopService.getStopList();
		return JsonUtils.getJson(BaseJsonVo.success(vo));
	}
	
	@RequestMapping(value="/getStopDetail" ,method=RequestMethod.GET)
	@ApiOperation(value="获取停诊详情",notes="{'id':'id'}",response=BaseJsonVo.class)
	public String getStopDetail(@RequestParam int id){
		BaseJsonVo vo = admissionStopService.getStopDetail(id);
		return JsonUtils.getJson(BaseJsonVo.success(vo));
	}
}
