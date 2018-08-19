package com.boco.modules.fdoc.api.outApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.interceptor.BaseInterceptor.Interceptor;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.ReferralHospitalizationEntity;
import com.boco.modules.fdoc.model.ReferralOutpatientEntity;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.ReferralHospitalizationService;
import com.boco.modules.fdoc.service.ReferralOutpatientService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;

/**
 * 绵阳市区域卫生信息平台
 * 数据交换接口
 * @author q
 *
 */
@RestController
@RequestMapping(value = "/outapi", produces = "text/json;charset=UTF-8")
public class DataChangeApi {
	@Resource
	SignService signService;
	@Resource
	PersonInformationService personService;
	@Resource
	ReferralOutpatientService outpatientService;
	@Resource
	ReferralHospitalizationService hospitalizationService;
	
	@Interceptor(name = "ApiVerifyInterceptor")
	@RequestMapping(value = "/getSignDoctorList", method = RequestMethod.POST)
	public String getSignDoctorList(@RequestParam String requestParam) {
		//参数json解析
		Map<String, Object> paramMap = JsonUtils.getObjectJsonMap(requestParam);
		String idCard = (String) paramMap.get("idCard");
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		//获取居民信息
		PersonInformationEntity entity = new PersonInformationEntity();
		entity.setIdCard(idCard);
		PersonInformationVo personInfo = personService.getPersonInfoByIdCard(entity );
		
		if (personInfo == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT.getKey(), ApiStatusEnum.USER_NOT.getValue()));
		}
		//获取签约团队信息
		List<DoctorDetailVo> teamInfo = signService.getSignDoctorTeamInfo(personInfo.getPersonId());
		if (teamInfo == null || teamInfo.size() == 0) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NOT_SIGN.getKey(), ApiStatusEnum.NOT_SIGN.getValue()));
		}
		for (DoctorDetailVo doctorDetailVo : teamInfo) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("YSXM", doctorDetailVo.getDocName());	//医生姓名
			map.put("KSMC", doctorDetailVo.getDeptName());	//科室名称
			map.put("XB", doctorDetailVo.getSex());	//性别
			map.put("SJHM", doctorDetailVo.getPhoneNumber());	//手机号码
			
			returnList.add(map);
		}
		
		return JsonUtils.getJson(BaseJsonVo.success(returnList));
	}
	
	@Interceptor(name = "ApiVerifyInterceptor")
	@RequestMapping(value = "/referralOutpatientStatusUpdate", method = RequestMethod.POST)
	public String referralOutpatientStatusUpdate(@RequestParam String requestParam) {
		try {
			//参数json解析
			Map<String, Object> paramMap = JsonUtils.getObjectJsonMap(requestParam);
			String referralCode = (String) paramMap.get("referralCode");
			String status = (String) paramMap.get("status");
			
			//判断参数不为空
			if (StringUtils.isEmpty(referralCode) || StringUtils.isEmpty(status)) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PARAM_CODE.getKey(), ApiStatusEnum.PARAM_CODE.getValue())); 
			}
			
			//查找此代码对应的转诊信息
			ReferralOutpatientEntity item = outpatientService.getReferralOutpatientInfoByCode(referralCode);
			if (item == null) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.REFERRAL_NOT_FOUND.getKey(), ApiStatusEnum.REFERRAL_NOT_FOUND.getValue())); 
			}
			
			//修改参数
			ReferralOutpatientEntity entity = new ReferralOutpatientEntity();
			entity.setReferralCode(referralCode);
			entity.setStatus(status);
			
			outpatientService.updateReferralOutpatientStatus(entity);
			
			return JsonUtils.getJson(BaseJsonVo.success(referralCode));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
	}
	
	@Interceptor(name = "ApiVerifyInterceptor")
	@RequestMapping(value = "/referralInpatientStatusUpdate", method = RequestMethod.POST)
	public String referralInpatientStatusUpdate(@RequestParam String requestParam) {
		try {
			//参数json解析
			Map<String, Object> paramMap = JsonUtils.getObjectJsonMap(requestParam);
			String referralCode = (String) paramMap.get("referralCode");
			String status = (String) paramMap.get("status");
			
			//判断参数不为空
			if (StringUtils.isEmpty(referralCode) || StringUtils.isEmpty(status)) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.PARAM_CODE.getKey(), ApiStatusEnum.PARAM_CODE.getValue())); 
			}
			
			//查找此代码对应的转诊信息
			ReferralHospitalizationEntity item = hospitalizationService.getReferralHospitalizationInfoByCode(referralCode);
			if (item == null) {
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.REFERRAL_NOT_FOUND.getKey(), ApiStatusEnum.REFERRAL_NOT_FOUND.getValue())); 
			}
			
			//修改参数
			ReferralHospitalizationEntity entity = new ReferralHospitalizationEntity();
			entity.setReferralCode(referralCode);
			entity.setStatus(status);
			
			hospitalizationService.updateReferralHospitalizationStatus(entity);
			
			return JsonUtils.getJson(BaseJsonVo.success(referralCode));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue())); 
		}
	}
	
	/**
	 * 接口调用方用户名、密码验证出错返回
	 * @return
	 */
	@RequestMapping(value = "/verifyError", method = RequestMethod.GET)
	public String verifyError() {
		return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.VERIFY_ERROR.getKey(), ApiStatusEnum.VERIFY_ERROR.getValue()));
	}
	
	/**
	 * 后台报错返回
	 * @return
	 */
	@RequestMapping(value = "/serverError", method = RequestMethod.GET)
	public String serverError() {
		return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ERROR_CODE.getKey(), ApiStatusEnum.ERROR_CODE.getValue()));
	}
	
	/**
	 * 接口参数json解析失败返回
	 * @return
	 */
	@RequestMapping(value = "/jsonParseError", method = RequestMethod.GET)
	public String jsonParseError() {
		return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DATA_FORMAT_ERROR.getKey(), ApiStatusEnum.DATA_FORMAT_ERROR.getValue()));
	}
}
