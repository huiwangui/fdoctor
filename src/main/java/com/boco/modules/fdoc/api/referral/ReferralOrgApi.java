package com.boco.modules.fdoc.api.referral;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.persistence.Page;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.ReferralOrgEntity;
import com.boco.modules.fdoc.service.ReferralOrgService;
import com.boco.modules.fdoc.vo.ReferralOutpatientVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 双向转诊转入、转出机构接口类
 * @author q
 *
 */

@RestController
@RequestMapping(value = "/referralOrg", produces = "text/json;charset=UTF-8")
public class ReferralOrgApi extends BaseController{
	
	@Resource
	ReferralOrgService referralOrgService;
	
	@RequestMapping(value = "/getReferralOutpatientList", method = RequestMethod.GET)
	@ApiOperation(value = "门诊转诊申请（医生端）")
	public String getReferralOutpatientList(@RequestParam String orgName, @RequestParam int pageNo, @RequestParam int pageSize){
		//设置查询参数
		ReferralOrgEntity entity = new ReferralOrgEntity();
		entity.setOrgName(orgName);
		
		//获取总数
		Integer count = referralOrgService.getReferralOrgCount(entity);
		
		//封装分页参数
		Page<ReferralOrgEntity> page = new Page<ReferralOrgEntity>(pageNo, pageSize, count);
		entity.setPage(page);
		
		//获取列表
		List<ReferralOrgEntity> list = referralOrgService.getReferralOrgList(entity);
		return JsonUtils.getPageJson(count, pageSize, list);
	}

}
