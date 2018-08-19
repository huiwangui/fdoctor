package com.boco.modules.fdoc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.SysRegionEntity;
import com.boco.modules.fdoc.service.SysRegionService;

@Controller
public class SysRegionCodeApi {
	@Resource
	SysRegionService sysRegionService;
	
	@RequestMapping(value="/getorg",method = RequestMethod.GET)
	@ResponseBody
	public  String getRegionCode(String regionCode ){
		List<Map<String, Object>> returnList = new ArrayList<>();
		List<SysRegionEntity> regiocodeList= sysRegionService.getChildrenRegions(regionCode);
		  for (SysRegionEntity sysRegionEntity : regiocodeList) {
			Map<String, Object> itemMap = new HashMap<>();
			itemMap.put("id", sysRegionEntity.getRegionCode());
			itemMap.put("text", sysRegionEntity.getName());
			itemMap.put("state", "closed");
			returnList.add(itemMap);
		  }
		return JsonUtils.getJsonFormat(returnList);
		
	}
	
	

}
