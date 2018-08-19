package com.boco.modules.fdoc.api;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.service.DoctorCommentService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.ScheduleVo;
import com.boco.modules.fdoc.vo.SignVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 医生 api
 * 
 * @author sufj
 *
 */
@RestController
@RequestMapping(value = "/doctor", produces = "text/json;charset=utf-8")
public class DoctorApi extends BaseController {
	
	@Resource
	DocService docService;
	@Resource
	PersonInformationService personService;
	@Resource
	SignService signService;
	
	@RequestMapping(value = "/scanDoctorTeam", method = RequestMethod.GET)
	@ApiOperation(value = "扫医生团队二维码（居民端）", notes = "{'personId':'居民ID','docTeamId':'医生团队ID'}")
	public String scanDoctorTeam(@RequestParam String personId, @RequestParam String docTeamId) {
		//获取医生列表
		List<DoctorDetailVo> docList = docService.getDoctorTeamMemberByTeamId(docTeamId);
		//获取居民信息
		PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(personId);
		SignVo signInfo = signService.getSignDetailByIdCard(personInfo.getIdCard());
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		//判断居民是否签约
		if (signInfo != null) {
			if (docTeamId.equals(signInfo.getDocTeamId())) {
				// 当前居民签约团队为当前团队
				returnMap.put("returnFlag", "1");
			}else {
				// 当前居民签约不为当前团队
				returnMap.put("returnFlag", "2");
			}
		}else {
			if (personInfo.getRegionCode().startsWith(docList.get(0).getRegionCode())) {
				// 没有签约，并且当前居民在当前医生团队区划范围下
				returnMap.put("returnFlag", "3");
			}else {
				// 没有签约，但当前居民不在当前医生团队区划范围下
				returnMap.put("returnFlag", "4");
			}
		}
		return JsonUtils.getJson(BaseJsonVo.success(returnMap));
	}

}
