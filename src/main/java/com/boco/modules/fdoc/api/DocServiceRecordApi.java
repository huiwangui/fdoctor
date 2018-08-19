package com.boco.modules.fdoc.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.boco.common.jedis.JedisUtils;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.NumberUtils;
import com.boco.modules.fdoc.model.DocServiceRecordEntity;
import com.boco.modules.fdoc.model.SigServicepackEntity;
import com.boco.modules.fdoc.service.DocServiceRecordService;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.SigServicepackService;
import com.boco.modules.fdoc.vo.DocServiceRecordVo;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.ServicepackDetailsVo;
import com.qiniu.util.Json;
import com.wordnik.swagger.annotations.ApiOperation;

import javassist.expr.Instanceof;

@RestController
@RequestMapping(value = "/docServiceRecord", produces = "text/json;charset=UTF-8")
public class DocServiceRecordApi {
	
	@Resource
	DocServiceRecordService  recordService;
	@Resource
	SigServicepackService packService;
	@Resource
	DocUserService docUserService;
	
	
	@RequestMapping(value = "/getPackList", method = RequestMethod.GET)
	@ApiOperation(value = "获取居民订购的服务包列表", notes = "{'personId':'居民ID'}")
	public String getPackList(@RequestParam String personId){
		//根据居民获取签约时选择的服务包
		List<SigServicepackEntity> packList=recordService.getPackList(personId);
		
		return JsonUtils.getJson(BaseJsonVo.success(packList));
	}
	
	@RequestMapping(value = "/getRecordDetals", method = RequestMethod.GET)
	@ApiOperation(value = "获取服务包下医生服务具体记录", notes = "{'personId':'居民ID','packId':'服务包ID','userName':'医生账号','pageSize':'页面条数','pageIndex':'页码'}")
	public String getRecordDetals(@RequestParam String personId,@RequestParam Integer packId,@RequestParam String userName,
			@RequestParam Integer pageSize, @RequestParam Integer pageIndex){
		
		//使用医生登录账号获取医生id
		DoctorDetailVo doctor = docUserService.getDoctorByUsername(userName);
		if (doctor == null) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
		String doctorId=doctor.getId();
		
		
		DocServiceRecordVo searchVo=new DocServiceRecordVo();
		searchVo.setDoctorId(doctorId);
		searchVo.setPackId(packId);
		searchVo.setPersonId(personId);
		
		//查询总条数
		Integer count=recordService.getRecordCount(searchVo);
		
		Page<DocServiceRecordVo> page=new Page<>(pageIndex, pageSize, count);
		searchVo.setPage(page);
		
		//查询服务记录并将日期处理成字符串 
		List<DocServiceRecordVo> recordList=recordService.getRecordList(searchVo);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for(DocServiceRecordVo vo:recordList){
			vo.setServiceTimeStr(sdf.format(vo.getServiceTime()));
		}
		
		return JsonUtils.getPageJson(count, pageSize, recordList);
	}
	
	@RequestMapping(value = "/getDetailsList", method = RequestMethod.GET)
	@ApiOperation(value = "服务包下详细服务项目详细列表（不含次数用于服务管理）", notes = "{'packId':'包ID'}")
	public String getDetailsList(@RequestParam Integer packId){
		
		//获取服务包下详细的服务项目
		List<ServicepackDetailsVo> detailsList=packService.getDetailsByPackId(packId);
		
		return JsonUtils.getJson(BaseJsonVo.success(detailsList));
	}
	
	@RequestMapping(value = "/addServiceRecord", method = RequestMethod.POST)
	@ApiOperation(value = "新增一条服务记录", notes = "{'packId':'服务包ID','detailsId':'服务项目ID','personId':'居民ID',"+
	"'userName':'医生账号','mobile':'验证使用的手机号码','smsCode':'验证码','serviceTime':'服务时间'}")
	public String addServiceRecord(@RequestBody String userBody/*@RequestParam Integer packId,@RequestParam Integer detailsId,
			@RequestParam String personId,@RequestParam String userName,@RequestParam String mobile,
			@RequestParam String smsCode,@RequestParam String serviceTime*/) throws ParseException{
		
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(userBody);
		
		if(jsonMap.get("packId")==null || jsonMap.get("detailsId")==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), "传入参数缺失"));
		}
		
		Integer packId=Integer.valueOf(jsonMap.get("packId").toString());
		Integer detailsId=Integer.valueOf(jsonMap.get("detailsId").toString());
		
		String personId=(String) jsonMap.get("personId");
		String userName=(String) jsonMap.get("userName");
		String mobile=(String) jsonMap.get("mobile");
		String smsCode=(String) jsonMap.get("smsCode");
		String serviceTime=(String) jsonMap.get("serviceTime");
		
		//通过医生账号查询医生id
		DoctorDetailVo doctor = docUserService.getDoctorByUsername(userName);
		if (doctor == null) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
		String doctorId=doctor.getId();
		
		//手机短信验证
		String code = JedisUtils.get("sms"+mobile);
		if(code==null || !code.equals(smsCode)){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.CODE_ERROR.getKey(), ApiStatusEnum.CODE_ERROR.getValue()));
		}
		//验证是否还有剩余次数
		Integer alltimes=recordService.getPackDetailsTimes(detailsId);
		
		DocServiceRecordEntity searchEntity=new DocServiceRecordEntity();
		searchEntity.setDoctorId(doctorId);
		searchEntity.setPersonId(personId);
		searchEntity.setDetailsId(detailsId);
		Integer usedTimes=recordService.getUsedTimes(searchEntity);
		
		if(alltimes!=-1){
			if(alltimes==usedTimes){
				return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.TIMES_EXHAUSTION.getKey(), ApiStatusEnum.TIMES_EXHAUSTION.getValue()));
			}
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date serviceDate=sdf.parse(serviceTime);
		
		DocServiceRecordEntity recordEntity=new DocServiceRecordEntity();
		recordEntity.setDoctorId(doctorId);
		recordEntity.setPersonId(personId);
		recordEntity.setPackId(packId);
		recordEntity.setDetailsId(detailsId);
		recordEntity.setMobile(mobile);
		recordEntity.setServiceTime(serviceDate);
		recordEntity.setCreateTime(new Date());
		recordEntity.setUpdateTime(new Date());
		
		//插入数据库
		int flag=recordService.addRecord(recordEntity);
		
		if(flag>0){
			return JsonUtils.getJson(BaseJsonVo.success(null));    
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
	}
	
	@RequestMapping(value = "/getRemainder", method = RequestMethod.GET)
	@ApiOperation(value = "获取服务包下详细项目包括剩余次数", notes = "{'personId':'居民ID','packId':'服务包ID','userName':'医生账号'}")
	public String getRemainder(@RequestParam String personId,@RequestParam Integer packId,@RequestParam String userName){
		
		//通过医生账号获取医生id
		DoctorDetailVo doctor = docUserService.getDoctorByUsername(userName);
		if (doctor == null) {
			return JsonUtils.getJson(
					BaseJsonVo.empty(ApiStatusEnum.NO_SUCH_DOCTOR.getKey(), ApiStatusEnum.NO_SUCH_DOCTOR.getValue()));
		}
		String doctorId=doctor.getId();
		
		//获取服务包下全部服务项目
		List<ServicepackDetailsVo> detailsList=packService.getDetailsByPackId(packId);
		//根据服务记录表中记录计算出该项服务项目剩余次数
		for(ServicepackDetailsVo detailsVo:detailsList){
			Integer detailsId=detailsVo.getId();
			
			DocServiceRecordEntity recordEntity=new DocServiceRecordEntity();
			recordEntity.setDoctorId(doctorId);
			recordEntity.setPersonId(personId);
			recordEntity.setDetailsId(detailsId);
			
			if(detailsVo.getFrequency()!=-1){
				
				Integer remainderTimes=detailsVo.getFrequency()-recordService.getUsedTimes(recordEntity);
				detailsVo.setRemainderTimes(remainderTimes);
				
			}else{
				detailsVo.setRemainderTimes(-1);
			}
			
		}
		
		return JsonUtils.getJson(BaseJsonVo.success(detailsList));
	}

}
