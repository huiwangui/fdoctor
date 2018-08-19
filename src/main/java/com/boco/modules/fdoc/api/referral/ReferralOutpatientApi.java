package com.boco.modules.fdoc.api.referral;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.exception.CommonException;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.ObjectUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.ReferralOutpatientEntity;
import com.boco.modules.fdoc.service.ReferralOutpatientService;
import com.boco.modules.fdoc.vo.ReferralOutpatientVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 双向转诊-门诊转诊API
 * @author q
 *
 */
@RestController
@RequestMapping(value = "/referralOutpatient", produces = "text/json;charset=UTF-8")
public class ReferralOutpatientApi extends BaseController{
	
	@Resource
	ReferralOutpatientService referralOutpatientService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editReferralOutpatientReply", method = RequestMethod.POST)
	@ApiOperation(value = "新增、修改门诊转诊申请（医生端）")
	public String editReferralOutpatientReply(@RequestBody String param) throws Exception{
		//解析参数json
		Map<String, Object> paramMap = JsonUtils.getObjectJsonMap(param);
		String userName = (String) paramMap.get("userName");
		Map<String, Object> bodyMap = (Map<String, Object>) paramMap.get("body");
		
		//处理实际转诊日期和出生日期
		Long realReferralDateStamp = null;
		if (bodyMap.get("realReferralDate") != null) {
			realReferralDateStamp = (Long) bodyMap.get("realReferralDate");
			bodyMap.remove("realReferralDate");
		}
		Long dateOfBirthStamp = null;
		if (bodyMap.get("dateOfBirth") != null) {
			dateOfBirthStamp = (Long) bodyMap.get("dateOfBirth");
			bodyMap.remove("dateOfBirth");
		}
		
		//将bodyMap转为ReferralOutpatientEntity对象
		ReferralOutpatientEntity entity = (ReferralOutpatientEntity) ObjectUtils.mapToObject(bodyMap, ReferralOutpatientEntity.class);
		if (realReferralDateStamp != null) {
			entity.setRealReferralDate(new Date(realReferralDateStamp));
		}
		if (dateOfBirthStamp != null) {
			entity.setDateOfBirth(new Date(dateOfBirthStamp));
		}
		
		//根据id判断新增还是修改
		if (bodyMap.get("id") != null && ((int)bodyMap.get("id")) != 0) {
			entity.setId((int)bodyMap.get("id"));
			referralOutpatientService.updateReferralOutpatientApply(entity);
		}else {
			entity.setCreator(userName);
			//新增记录
			try {
				referralOutpatientService.insertReferralOutpatientApply(entity);
			} catch (CommonException e) {
				e.printStackTrace();
				return JsonUtils.getJson(BaseJsonVo.empty(
						ApiStatusEnum.INTERFACE_ERROR.getKey(),
						ApiStatusEnum.INTERFACE_ERROR.getValue()));
			}
			
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	
	@RequestMapping(value = "/getReferralOutpatientList", method = RequestMethod.GET)
	@ApiOperation(value = "门诊转诊申请（医生端）")
	public String getReferralOutpatientList(@RequestParam String teamId, @RequestParam String param, @RequestParam Integer pageSize, @RequestParam Integer pageNo) throws Exception{
		//准备查询参数
		ReferralOutpatientVo vo = new ReferralOutpatientVo();
		vo.setDocTeamId(teamId);
		
		//判断参数是身份证还是姓名
		if (MatcherUtiles.idCardMach(param)) {
			vo.setIdCard(param);
		}else {
			vo.setPersonName(param);
		}
		
		//获取总数
		Integer count = referralOutpatientService.getReferralOutpatientCountBySign(vo);
		
		//封装分页参数
		Page<ReferralOutpatientVo> page = new Page<ReferralOutpatientVo>(pageNo, pageSize, count);
		vo.setPage(page);
		
		//获取列表
		List<ReferralOutpatientVo> list = referralOutpatientService.getReferralOutpatientListBySign(vo);
		return JsonUtils.getPageJson(count, pageSize, list);
	}
	
	@RequestMapping(value = "/getReferralOutpatientInfo", method = RequestMethod.GET)
	@ApiOperation(value = "查看门诊转诊申请记录（医生端）")
	public String getReferralOutpatientInfo(@RequestParam Integer id) throws Exception{
		ReferralOutpatientEntity referralOutpatientInfo = referralOutpatientService.getReferralOutpatientInfo(id);
		return JsonUtils.getJson(BaseJsonVo.success(referralOutpatientInfo));
	}
}
