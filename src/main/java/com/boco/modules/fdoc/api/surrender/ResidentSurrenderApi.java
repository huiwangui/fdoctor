 
package com.boco.modules.fdoc.api.surrender;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.modules.fdoc.model.UserDocSignEntity;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.service.expert.ExpertService;
import com.boco.modules.fdoc.service.surrender.SurrenderRequestService;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SigServicepackVo;
import com.boco.modules.fdoc.vo.expert.ExpertInformationVo;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Tilte: ResidentSurrenderApi 
 * Description:居民端解约API
 * @author h
 * @date  2017年11月28日下午3:48:51
 * @version 1.0
 *  
 */
@RestController
@RequestMapping(value = "/residentSurrender", produces = "text/json;charset=UTF-8")
public class ResidentSurrenderApi {
	@Resource
	ExpertService expertService;
	@Resource
	PersonInformationService personService;
	@Resource
	SignService signService;
	@Resource
	SurrenderRequestService surrenderRequestService;
	@RequestMapping(value = "/getSigServicepackListByPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取服务包列表（公用）", notes = "{'personId':'居民ID','docTeamId':'医生团队ID'}")
	public String getSigServicepackListByPersonId(@RequestParam String personId, @RequestParam String docTeamId) {
		//创建条件对象
		UserDocSignEntity entity = new UserDocSignEntity();
		entity.setPersonId(personId);
		entity.setDocTeamId(docTeamId);
        //获取服务包列表		 
		List<SigServicepackVo> packs =signService.getServicePacksByPersonId(entity);	 
		return JsonUtils.getJson(BaseJsonVo.success(packs));
	}
	@RequestMapping(value = "/getTelephoneByPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "查询居民及医生团队队长电话号码 （居民端）", notes = "{'personId':'居民ID'}")
	public String getTelephoneByPersonId(@RequestParam String personId) {				
		//查询居民简单信息及与他签约的医生团队队长电话号码
		PersonInformationVo simplePersonInfo = personService.getSimplePersonInfoByPersonId(personId);
		//封装返回结果
		Map<String, Object> values = new HashMap<>();	
		values.put("phoneNumber", simplePersonInfo.getPhoneNumber());
		values.put("docMobile", simplePersonInfo.getDocMobile());		
		return JsonUtils.getJson(BaseJsonVo.success(values));
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ApiOperation(value = "解约申请(居民端)", notes = "{'reasonValue':'解约原因权值之和(1:签错了，想选择其他医生。2.医生不负责任，态度不好。4.想选其他更熟识得家庭医生团队。8.其他理由)','reasonOther':'其它解约原因','personId':'居民id','docTeamId':'医生团队id','residentMobile':'居民电话号码','docMobile':'医生电话号码'}", response = BaseJsonVo.class)
	public String insert(@RequestBody String paramBody){
		//获取参数
		SurrenderRequestEntity vo = JsonUtils.getObject(paramBody, SurrenderRequestEntity.class);
		//先判断是否有解约申请
		vo.setStatus("1");
		SurrenderRequestVo svo = surrenderRequestService.getSurrenderDetailByPersonId(vo);
		if(svo!=null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.SURRENDER_REPEAT.getKey(), ApiStatusEnum.SURRENDER_REPEAT.getValue()));
			
		}
		//验证医生手机号是否合法		 
		if (!MatcherUtiles.mobileMach(vo.getDocMobile())) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue()));
		}
		//验证居民手机号是否合法		 
		if (!MatcherUtiles.mobileMach(vo.getResidentMobile())) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue()));
		}
		//设置解约方为居民
		vo.setOriginator("2");
		//设置解约申请时间
		vo.setRequestTime(new Date());
		//解约申请
		int flag = surrenderRequestService.insert(vo); 
		if(flag ==1){
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}else{
			return JsonUtils.getJson(BaseJsonVo.error(null));
		}
		
		 
	}
	@RequestMapping(value = "/getSignDetailListByPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取签约详情列表（居民端）", notes = "{'personId':'居民ID','status':'1:解约审核中 2:已签约   3:未签约'}")
	public String getSignDetailListByPersonId(@RequestParam String personId,@RequestParam String status,@RequestParam Integer pageSize, @RequestParam Integer pageNo) {
		//封装参数
		SurrenderRequestVo vo = new SurrenderRequestVo();
		vo.setStatus(status);
		vo.setPersonId(personId);
		//获取签约详情列表总数
		int count =surrenderRequestService.getSurrenderRequestVoListCountByPersonIdAndStatus(vo);
		vo.setPage(new Page<SurrenderRequestVo>(pageNo, pageSize,Long.valueOf(count)));
		//获取签约详情列表
		List<SurrenderRequestVo> voList = surrenderRequestService.getSurrenderRequestVoListByPersonIdAndStatus(vo);
		return JsonUtils.getPageJson(count, pageSize, voList);
	}
	
	@RequestMapping(value = "/getSurrenderDetailByPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取解约申请中或解约被拒绝详情（公用的）", notes = "{'personId':'居民ID','status':'1:解约审核中  2:拒绝解约  '}")
	public String getSurrenderDetailByPersonId(@RequestParam String personId,@RequestParam String status) {
		//封装参数
		SurrenderRequestEntity vo = new SurrenderRequestEntity();
		vo.setStatus(status);
		vo.setPersonId(personId);
		//获取解约申请中或解约被拒绝详情
		SurrenderRequestVo svo  = surrenderRequestService.getSurrenderDetailByPersonId(vo);
		return JsonUtils.getJson(BaseJsonVo.success(svo));
	}
	@RequestMapping(value = "/cancelSurrenderDetailByPersonId", method = RequestMethod.POST)
	@ApiOperation(value = "取消解约申请（公用的）", notes = "{'personId':'居民ID'}")
	public String cancelSurrenderDetailByPersonId(@RequestBody String paramBody) {
		//获取参数
		SurrenderRequestEntity vo = JsonUtils.getObject(paramBody, SurrenderRequestEntity.class);
        //取消解约	申请	 
		int cancel  = surrenderRequestService.cancelSurrenderDetailByPersonId(vo);
		if(cancel==1){
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(
					ApiStatusEnum.FAIL.getKey(),
					ApiStatusEnum.FAIL.getValue()));
		}
		
	}
}
