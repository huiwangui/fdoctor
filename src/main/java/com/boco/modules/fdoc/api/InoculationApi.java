package com.boco.modules.fdoc.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boco.modules.fdoc.service.InoculationService;
import com.boco.modules.fdoc.service.VaccineService;

@RestController
@RequestMapping(value = "/inoculation", produces = "text/json;charset=utf-8")
public class InoculationApi {
	@Resource
	VaccineService vaccineService;
	@Resource
	InoculationService inoculationService;
	/**
	 * 
	 * @author q
	 * @return
	 * 
	 * @RequestMapping(value = "/getVaccineDetail", method = RequestMethod.GET)
	@ApiOperation(value = "获取疫苗接种详细信息（居民端）", response = BaseJsonVo.class)
	public String getVaccineDetail(){
		List<VaccienDetailVo> list = vaccineService.getVaccineDetail();
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	
//	@RequestMapping(value = "/getNotInoRelations", method = RequestMethod.GET)
//	@ApiOperation(value = "获取当前家庭未接种该种疫苗的人员（居民端）",notes = "{'uid':'当前登录用户','detailId':'接种疫苗详细信息ID'}", response = BaseJsonVo.class)
//	public String getNotInoRelations(@RequestParam String uid,@RequestParam int detailId){
//		List<ResidentVo> list = inoculationService.getNotInoRelations(uid, detailId);
//		return JsonUtils.getJson(BaseJsonVo.success(list));
//	}
	
	@RequestMapping(value = "/vaccinate", method = RequestMethod.POST)
	@ApiOperation(value = "预约接种（居民端）",notes = "{'uid':'当前登录用户','idCard':'接种人身份证号','detailId':'接种疫苗详细信息ID','period':'预约时间段：1.上午 2.下午','vaccineSourceId':'疫苗放号ID'}", response = BaseJsonVo.class)
	public String vaccinate(@RequestBody String body){
		InoculationVo vo = JsonUtils.getObject(body, InoculationVo.class);
		String returnNum = inoculationService.vaccinate(vo);
		if ("reIno".equals(returnNum)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_INO.getKey(), ApiStatusEnum.RE_INO.getValue()));
		}
		else if ("no number".equals(returnNum)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NO_VACCINENUM.getKey(), ApiStatusEnum.NO_VACCINENUM.getValue()));
		}else if("notSign".equals(returnNum)){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.NOT_SIGN.getKey(), ApiStatusEnum.NOT_SIGN.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(returnNum));
	}
	
	@RequestMapping(value = "/getInoNum", method = RequestMethod.GET)
	@ApiOperation(value = "查询已接种和未接种人数（医生端）",notes = "{'docUid':'医生登录UID'}", response = BaseJsonVo.class)
	public String getInoNum(@RequestParam String docUid){
		return JsonUtils.getJson(BaseJsonVo.success(inoculationService.getInoNum(docUid)));
	}
	
	@RequestMapping(value = "/getInoList", method = RequestMethod.GET)
	@ApiOperation(value = "查询已接种人员列表（医生端）",notes = "{'docUid':'医生登录UID'}", response = BaseJsonVo.class)
	public String getInoList(@RequestParam String docUid){
		return JsonUtils.getJson(BaseJsonVo.success(inoculationService.getInoList(docUid)));
	}
	
	@RequestMapping(value = "/getNotInoList", method = RequestMethod.GET)
	@ApiOperation(value = "查询未接种人员列表（医生端）",notes = "{'docUid':'医生登录UID'}", response = BaseJsonVo.class)
	public String getNotInoList(@RequestParam String docUid){
		return JsonUtils.getJson(BaseJsonVo.success(inoculationService.getNotInoList(docUid)));
	}
	
	@RequestMapping(value = "/addVaccineSource", method = RequestMethod.POST)
	@ApiOperation(value = "放号（医生端）",notes = "{'docUid':'医生登录UID','timeStamp':'接种日期时间戳','vaccineDetailId':'疫苗详细情况ID','vaccineNum':'放号数量'}", response = BaseJsonVo.class)
	public String addVaccineSource(@RequestBody String body){
		VaccineSourceVo vo = JsonUtils.getObject(body, VaccineSourceVo.class);
		inoculationService.addVaccineSource(vo);
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/getInoHospitalList", method = RequestMethod.GET)
	@ApiOperation(value = "查询可预约接种的医院（居民端）",notes = "{'city':'城市','detailId':'疫苗详细ID'}", response = BaseJsonVo.class)
	public String getInoHospitalList(@RequestParam String city,@RequestParam int detailId){
		List<HospitalEntity> list = inoculationService.getInoHospList(city,detailId);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	
	@RequestMapping(value = "/getInoDays", method = RequestMethod.GET)
	@ApiOperation(value = "查询可预约接种的时间（居民端）",notes = "{'docId':'医生ID','detailId':'疫苗详情ID','beginDate':'开始日期时间戳','endDate':'结束日期时间戳'}", response = BaseJsonVo.class)
	public String getInoDays(@RequestParam int hospId,@RequestParam int detailId,@RequestParam long beginDate,@RequestParam long endDate){
		VaccineSourceVo vo = new VaccineSourceVo();
		vo.setBeginDate(new Date(beginDate));
		vo.setEndDate(new Date(endDate));
		vo.setHospId(hospId);
		vo.setVaccineDetailId(detailId);
		List<VaccineSourceVo> list = inoculationService.getInoNumListInWeek(vo);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	
	@RequestMapping(value = "/getByStatus", method = RequestMethod.GET)
	@ApiOperation(value = "查询放号记录（医生端）",notes = "{'docUid':'当前医生登录UID','status':'放号状态 1.已发布，2.已过期'}", response = BaseJsonVo.class)
	public String getByStatus(@RequestParam String docUid,@RequestParam String status){
		List<VaccineSourceVo> list = inoculationService.getByStatus(docUid, status);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	
	@RequestMapping(value = "/clear", method = RequestMethod.POST)
	@ApiOperation(value = "清空已过期的放号信息（医生端）",notes = "{'docUid':'当前医生登录UID'}", response = BaseJsonVo.class)
	public String clear(@RequestBody String body){
		Map<String, String> map = JsonUtils.getSingleJsonMap(body);
		inoculationService.clear(map.get("docUid"));
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/getSourceList", method = RequestMethod.GET)
	@ApiOperation(value = "查询未来一周可预约的接种（用户端）",notes = "{'detailId':'疫苗详情ID'}", response = BaseJsonVo.class)
	public String getSourceList(@RequestParam int detailId){
		List<VaccineSourceVo> list = inoculationService.getSourceList(detailId);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	 * 
	 */
	
}
