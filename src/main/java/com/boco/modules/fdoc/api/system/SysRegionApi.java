package com.boco.modules.fdoc.api.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.service.SysRegionService;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 区划相关接口
 * @author lyh
 *
 */
@RestController
@RequestMapping(value = "/sysRegion", produces = "text/json;charset=UTF-8")
public class SysRegionApi {
	
	@Resource
	SysRegionService regionService;
	
	@RequestMapping(value = "/getChildrenRegions", method = RequestMethod.GET)
	@ApiOperation(value = "获取子级区划列表（医生端）", notes = "{'parentCode':'父级机构代码，如果要查询第一级机构，传入空字符串'}")
	public String getHealthRecordByDoctor(@RequestParam String parentCode){
		
		List<Map<String, Object>> accurateChildrenRegions = regionService.getAccurateChildrenRegions(parentCode);
		return JsonUtils.getJson(BaseJsonVo.success(accurateChildrenRegions));
	}
}
