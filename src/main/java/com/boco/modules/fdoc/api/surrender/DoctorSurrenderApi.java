 
package com.boco.modules.fdoc.api.surrender;

import java.util.Date;
import java.util.List;

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
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;
import com.wordnik.swagger.annotations.ApiOperation;

 
/**
 * Tilte: DoctorSurrenderApi 
 * Description:医生端解约API
 * @author h
 * @date  2017年11月30日下午4:12:56
 * @version 1.0
 *  
 */
@RestController
@RequestMapping(value = "/doctorSurrender", produces = "text/json;charset=UTF-8")
public class DoctorSurrenderApi {
	@Resource
	ExpertService expertService;
	@Resource
	PersonInformationService personService;
	@Resource
	SignService signService;
	@Resource
	SurrenderRequestService surrenderRequestService;
	 
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ApiOperation(value = "解约申请(医生端)", notes = "{'reasonValue':'解约原因权值之和(1:签错了，想选择其他医生。2.医生不负责任，态度不好。4.想选其他更熟识得家庭医生团队。8.其他理由)','reasonOther':'其它解约原因','personId':'居民id','docTeamId':'医生团队id','residentMobile':'居民电话号码','docMobile':'医生电话号码'}", response = BaseJsonVo.class)
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
		//设置解约方为医生
		vo.setOriginator("1");
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
	@RequestMapping(value = "/getSurrenderDetailByPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取解约详情（医生端）", notes = "{'personId':'居民ID'，'status':' 1，审核中 2，拒绝解约 3，已解约','teamId':'医生团队id'}")
	public String getSurrenderDetailByPersonId(@RequestParam String personId,@RequestParam String status,@RequestParam String teamId) {
		//封装参数
		SurrenderRequestEntity vo = new SurrenderRequestEntity();
		vo.setStatus(status);
		vo.setPersonId(personId);
		vo.setDocTeamId(teamId);
		//获取解约详情
		SurrenderRequestVo  svo= surrenderRequestService.getSurrenderDetailByPersonId(vo);
		return JsonUtils.getJson(BaseJsonVo.success(svo));
	}
	@RequestMapping(value = "/getPersonInformationVoListByDocTeamId", method = RequestMethod.GET)
	@ApiOperation(value = "获取解约记录列表（医生端）", notes = "{'docTeamId':'医生团队id'，'status':'1:解约审核中  2:拒绝解约  3:已解约'}")
	public String getPersonInformationVoListByDocTeamId(@RequestParam String docTeamId,@RequestParam(required=false) String status,@RequestParam Integer pageSize, @RequestParam Integer pageNo) {
		//封装参数
		SurrenderRequestVo vo = new SurrenderRequestVo();
		vo.setStatus(status);
		vo.setDocTeamId(docTeamId);
		int count = personService.getPersonInformationVoListCountByDocTeamId(vo);
		vo.setPage(new Page<SurrenderRequestVo>(pageNo, pageSize,Long.valueOf(count)));
		List<PersonInformationVo> voList =personService.getPersonInformationVoListByDocTeamId(vo); 
		return JsonUtils.getPageJson(count, pageSize, voList);
	}
	@RequestMapping(value = "/getPersonInformationDetailByPersonId", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民详情（医生端）", notes = "{'personId':'居民ID'，'status':' 1，审核中 2，拒绝解约 3，已解约','teamId':'医生团队id'}")
	public String getPersonInformationDetailByPersonId(@RequestParam String personId,@RequestParam String status,@RequestParam String teamId) {
		//封装参数
		SignVo vo = new SignVo();
		vo.setStatus(status);
		vo.setPersonId(personId);
		vo.setDocTeamId(teamId);
		//获取居民详情
		SignVo  svo=  signService.getPersonInformationDetailByPersonId(vo);
		return JsonUtils.getJson(BaseJsonVo.success(svo));
	}
}
