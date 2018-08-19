 
package com.boco.modules.fdoc.api.surrender;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.surrender.SurrenderReasonEntity;
import com.boco.modules.fdoc.service.surrender.SurrenderReasonService;
import com.wordnik.swagger.annotations.ApiOperation;

 
@RestController
@RequestMapping(value = "/surrenderReason", produces = "text/json;charset=UTF-8")
public class SurrenderReasonApi {
	@Resource
	SurrenderReasonService surrenderReasonService;
	@RequestMapping(value = "/findAllReasonList", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有解约原因 ", notes = "{}")
	public String findAllReasonList() {
		//获取所有解约原因 
		List<SurrenderReasonEntity> allReasonList = surrenderReasonService.findAllReasonList();	 
		return JsonUtils.getJson(BaseJsonVo.success(allReasonList));
	}
	 
}
