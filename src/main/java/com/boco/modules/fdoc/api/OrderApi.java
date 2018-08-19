package com.boco.modules.fdoc.api;

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

import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.push.CloudMobilePush;
import com.boco.common.push.PushUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.dao.PatientOrderDao;
import com.boco.modules.fdoc.model.BizOutpatientOrderEntity;
import com.boco.modules.fdoc.model.BizOutpatientSourceEntity;
import com.boco.modules.fdoc.model.BloodSugerRecordEntity;
import com.boco.modules.fdoc.model.FollowUpDictionaryEntity;
import com.boco.modules.fdoc.model.FollowUpOrderEntity;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationDictionaryEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationOrderEntity;
import com.boco.modules.fdoc.service.HospitalService;
import com.boco.modules.fdoc.service.OrderImmuneService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.service.UserService;
import com.boco.modules.fdoc.vo.FollowUpOrderVo;
import com.boco.modules.fdoc.vo.ImmuneVo;
import com.boco.modules.fdoc.vo.OrderImmuneVo;
import com.boco.modules.fdoc.vo.OrderParamVo;
import com.boco.modules.fdoc.vo.PhysicalExaminationOrderVo;
import com.boco.modules.fdoc.vo.UserReportVo;
import com.taobao.api.internal.toplink.embedded.websocket.util.StringUtil;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/order", produces = "text/json;charset=UTF-8")
public class OrderApi {
	@Resource
	HospitalService hospitalService;
	@Resource
	OrderImmuneService orderImmuneService;
	@Resource
	PersonInformationService personService;
	@Resource
	SignService signService;
	@Resource
	UserService userService; 
	
	@RequestMapping(value = "/getHospitalByUid", method = RequestMethod.GET)
	@ApiOperation(value = "获取医院地址(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String getHospitalByUid(@RequestParam String uid){
		HospitalEntity hospitalEntity=hospitalService.getHospitalByUid(uid);
		if(hospitalEntity==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(), ApiStatusEnum.RE_NO_ORDER.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(hospitalEntity));
	}
	@RequestMapping(value = "/getOrderOfDayByUid", method = RequestMethod.GET)
	@ApiOperation(value = "获取某天的上下午号源数(居民端)", notes = "{'uid':'居民uid','clickDate':'就诊日期(时间戳)'}", response = BaseJsonVo.class)
	public String getOrderOfDayByUid(@RequestParam String uid,@RequestParam Long clickDate){
		OrderParamVo vo = new OrderParamVo();
		Date clickdate = new Date(clickDate);
		vo.setUid(uid);
		vo.setClinicDate(clickdate);
		List<BizOutpatientSourceEntity> list =orderImmuneService.getOrderSourceByUid(vo);
		if(list.size()==0){
			//说明当天已没有号源了
			for(int i=0;i<2;i++){
				BizOutpatientSourceEntity by = new BizOutpatientSourceEntity();
				by.setClinicDate(clickdate);
				by.setClinicTime(""+i);
				by.setRemainderNumber(0);
				list.add(by);
			}
			
		}
		return JsonUtils.getJson(BaseJsonVo.success(list));
		 
	}
	@RequestMapping(value = "/getFamilyNumberByUid", method = RequestMethod.GET)
	@ApiOperation(value = "获取家庭成员(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String getFamilyNumberByUid(@RequestParam String uid){
		List<PersonInformationEntity> list =personService.getPersonInformationByUid(uid); 		 
		return JsonUtils.getJson(BaseJsonVo.success(list));
		 
	}
	@RequestMapping(value = "/saveOrderImmune", method = RequestMethod.POST)
	@ApiOperation(value = "确认免疫预约(居民端)", notes = "{'uid':'居民uid','clinicDate':'就诊日期(时间戳)','clinicTime':'上午(0),下午(1)','patientIds':'就诊人id(可以多选)'}", response = BaseJsonVo.class)
	public String saveOrderImmune(@RequestBody String paramBody){		 
		OrderParamVo vo = JsonUtils.getObject(paramBody, OrderParamVo.class);
	 
		//先判断是否签约了
		UserReportVo uVo =userService.getdoctorIdByUid(vo.getUid());	
		if(uVo==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(),ApiStatusEnum.RE_NO_ORDER.getValue()));
		}
		List<String> accounts = signService.getSignTeamUsers(uVo.getPersonId());
		String result= orderImmuneService.saveOrderImmune(vo);
		if("号源不够,预约失败".equals(result)){
			 return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ORDER_INO.getKey(), ApiStatusEnum.ORDER_INO.getValue()));
		}else if("当天还未发布号源".equals(result)){
			 return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ORDER_INO2.getKey(), ApiStatusEnum.ORDER_INO2.getValue()));
		}
		Map<String, Object> pushMap = new HashMap<String, Object>(); 
		pushMap.put("orderNumber",result);
		//UserReportVo uVo =userService.getdoctorIdByUid(vo.getUid());
		PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(uVo.getPersonId());
		// 获取推送账户
		//List<String> accounts = signService.getSignTeamUsers(uVo.getPersonId());
		CloudMobilePush push = new CloudMobilePush();
		String title = "新的预约";
		String content = "您的签约居民"+personInfo.getPersonName()+"新预约了免疫，点击查看！";
		push.androidPush(accounts, title, content, BusinessConstants.PUSH_ACTIVITY, 
				BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_MESSAGE_SENG, pushMap));
		return JsonUtils.getJson(BaseJsonVo.success(result));
		//return JsonUtils.getJson(result);
		 
	}
	@RequestMapping(value = "/cacelOrder", method = RequestMethod.POST)
	@ApiOperation(value = "取消免疫预约(居民端)", notes = "{'orderNumber':'订单编号'}", response = BaseJsonVo.class)
	public String cacelOrder(@RequestBody String paramBody){
		OrderParamVo vo = JsonUtils.getObject(paramBody, OrderParamVo.class);
		 
		BizOutpatientOrderEntity bo=new BizOutpatientOrderEntity();
		bo.setCancleTime(new Date());
		bo.setOrderStatus("2");
		bo.setOrderNumber(vo.getOrderNumber());
		int num =orderImmuneService.cacelOrder(bo);
		return JsonUtils.getJson(BaseJsonVo.success(null));
		 
	}
	@RequestMapping(value = "/judgeOrder", method = RequestMethod.GET)
	@ApiOperation(value = "判断免疫是否预约(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String judgeOrder(@RequestParam String uid){
		BizOutpatientOrderEntity bo=new BizOutpatientOrderEntity();
		bo.setCreatorUid(uid);
		bo.setOrderStatus("1");
		String day=orderImmuneService.getOrderByuid(bo);
		if(StringUtils.isNotEmpty(day)&&!"overdue".equals(day)){
			//返回订单号
			String[] strs = day.split(",");
			OrderImmuneVo vo = new OrderImmuneVo();
			vo.setDay(strs[0]);
			vo.setOrderNumber(strs[1]);
//			return JsonUtils.getJson(BaseJsonVo.success(day));
  		    return JsonUtils.getJson(BaseJsonVo.success(vo));
			
		} 
	    return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ORDER_ERROR.getKey(), ApiStatusEnum.ORDER_ERROR.getValue()));
		 
	}
	
	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	@ApiOperation(value = "获取免疫预约记录(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String getOrderList(@RequestParam String uid, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUid(uid); 
		List<OrderImmuneVo> list=orderImmuneService.getOrderListByuid(vo);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getOrderSumByuid(uid);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		
		
		
		return JsonUtils.getPageJson(lists.size(), pageSize, list);
	   // return JsonUtils.getJson(BaseJsonVo.success(list)); 
	}
	
	@RequestMapping(value = "/getPhysicalListByUid", method = RequestMethod.GET)
	@ApiOperation(value = "获取体检项目列表(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String getPhysicalListByUid(@RequestParam String uid){		 
		List<PhysicalExaminationDictionaryEntity> list= orderImmuneService.getPhysicalListByUid(uid);	 
		return JsonUtils.getJson(BaseJsonVo.success(list)); 
	}
	
	@RequestMapping(value = "/savePhysicalOrder", method = RequestMethod.POST)
	@ApiOperation(value = "确认体检预约(居民端)", notes = "{'uid':'居民uid','clinicDate':'就诊日期(时间戳)','clinicTime':'上午(0),下午(1)','patientIds':'就诊人id(可以多选)','tjName':'体检项目'}", response = BaseJsonVo.class)
	public String savePhysicalOrder(@RequestBody String paramBody){		 
		OrderParamVo vo = JsonUtils.getObject(paramBody, OrderParamVo.class);
		//先判断是否签约了
		UserReportVo uVo =userService.getdoctorIdByUid(vo.getUid());	
		if(uVo==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(),ApiStatusEnum.RE_NO_ORDER.getValue()));
		}
		List<String> accounts = signService.getSignTeamUsers(uVo.getPersonId());
		String result= orderImmuneService.savePhysicalOrder(vo);	
		
		Map<String, Object> pushMap = new HashMap<String, Object>(); 
		pushMap.put("orderNumber",result);
		//UserReportVo uVo =userService.getdoctorIdByUid(vo.getUid());
		PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(uVo.getPersonId());
		// 获取推送账户
		//List<String> accounts = signService.getSignTeamUsers(uVo.getPersonId());
		CloudMobilePush push = new CloudMobilePush();
		String title = "新的预约";
		String content = "您的签约居民"+personInfo.getPersonName()+"新预约了就诊，点击查看！";//personInfo.getPersonName()
		push.androidPush(accounts, title, content, BusinessConstants.PUSH_ACTIVITY, 
				BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_EXMA_SENG, pushMap));
		return JsonUtils.getJson(BaseJsonVo.success(result));
	 
	}
	@RequestMapping(value = "/cacelPhysicalOrder", method = RequestMethod.POST)
	@ApiOperation(value = "取消体检预约(居民端)", notes = "{'orderNumber':'订单编号'}", response = BaseJsonVo.class)
	public String cacelPhysicalOrder(@RequestBody String paramBody){
		OrderParamVo vo = JsonUtils.getObject(paramBody, OrderParamVo.class);	 
		PhysicalExaminationOrderEntity bo=new PhysicalExaminationOrderEntity();
		bo.setCancleTime(new Date());
		bo.setOrderStatus("2");
		bo.setOrderNumber(vo.getOrderNumber());
		int num =orderImmuneService.cacelPhysicalOrder(bo);
		return JsonUtils.getJson(BaseJsonVo.success(null));
		 
	}
	@RequestMapping(value = "/judgePhysicalOrder", method = RequestMethod.GET)
	@ApiOperation(value = "判断体检是否预约(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String judgePhysicalOrder(@RequestParam String uid){
		PhysicalExaminationOrderEntity bo=new PhysicalExaminationOrderEntity();
		bo.setCreatorUid(uid);
		bo.setOrderStatus("1");
		String day=orderImmuneService.getPhysicalOrderByuid(bo);
		if(StringUtils.isNotEmpty(day)&&!"overdue".equals(day)){
			//返回订单号
			String[] strs = day.split(",");
			OrderImmuneVo vo = new OrderImmuneVo();
			vo.setDay(strs[0]);
			vo.setOrderNumber(strs[1]);
  		    return JsonUtils.getJson(BaseJsonVo.success(vo));
			 
		} 
	    return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ORDER_ERROR.getKey(), ApiStatusEnum.ORDER_ERROR.getValue()));		 
	}
	@RequestMapping(value = "/getPhysicalOrderList", method = RequestMethod.GET)
	@ApiOperation(value = "获取体检预约记录(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String getPhysicalOrderList(@RequestParam String uid, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUid(uid); 
		//预约总数
		List<PhysicalExaminationOrderVo> lists=orderImmuneService.getPhysicalOrderListSumByuid(uid);	
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<PhysicalExaminationOrderVo> list=orderImmuneService.getPhysicalOrderListByuid(vo);
			
		return JsonUtils.getPageJson(lists.size(), pageSize, list);

	}
	@RequestMapping(value = "/getFollowUpListByUid", method = RequestMethod.GET)
	@ApiOperation(value = "获取随访项目列表(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String getFollowUpListByUid(@RequestParam String uid){		 
		List<FollowUpDictionaryEntity> list= orderImmuneService.getFollowUpListByUid(uid);	 
		return JsonUtils.getJson(BaseJsonVo.success(list)); 
	}
	@RequestMapping(value = "/saveFollowUpOrder", method = RequestMethod.POST)
	@ApiOperation(value = "确认随访预约(居民端)", notes = "{'uid':'居民uid','clinicDate':'就诊日期(时间戳)','clinicTime':'上午(0),下午(1)','patientIds':'就诊人id(可以多选)','fpName':'随访项目'}", response = BaseJsonVo.class)
	public String saveFollowUpOrder(@RequestBody String paramBody){		 
		OrderParamVo vo = JsonUtils.getObject(paramBody, OrderParamVo.class);
		 
		//先判断是否签约了
		UserReportVo uVo =userService.getdoctorIdByUid(vo.getUid());	
		if(uVo==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.RE_NO_ORDER.getKey(),ApiStatusEnum.RE_NO_ORDER.getValue()));
		}
		List<String> accounts = signService.getSignTeamUsers(uVo.getPersonId());
		String result= orderImmuneService.saveFollowUpOrder(vo);
		
		Map<String, Object> pushMap = new HashMap<String, Object>(); 
		pushMap.put("orderNumber",result);
		//UserReportVo uVo =userService.getdoctorIdByUid(vo.getUid());
		PersonInformationEntity personInfo = personService.getPersonInformationByPesronId(uVo.getPersonId());
		// 获取推送账户
		//List<String> accounts = signService.getSignTeamUsers(uVo.getPersonId());
		CloudMobilePush push = new CloudMobilePush();
		String title = "新的预约";
		String content = "您的签约居民"+personInfo.getPersonName()+"新预约了随访，点击查看！";
		push.androidPush(accounts, title, content, BusinessConstants.PUSH_ACTIVITY, 
				BusinessConstants.PUSH_ITEM_DOC, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_FOLLOW_SENG, pushMap));
		return JsonUtils.getJson(BaseJsonVo.success(result));
	 
	}
	@RequestMapping(value = "/cacelFollowUpOrder", method = RequestMethod.POST)
	@ApiOperation(value = "取消随访预约(居民端)", notes = "{'orderNumber':'订单编号'}", response = BaseJsonVo.class)
	public String cacelFollowUpOrder(@RequestBody String paramBody){
		OrderParamVo vo = JsonUtils.getObject(paramBody, OrderParamVo.class);	 
		FollowUpOrderEntity bo=new FollowUpOrderEntity();
		bo.setCancleTime(new Date());
		bo.setOrderStatus("2");
		bo.setOrderNumber(vo.getOrderNumber());
		int num =orderImmuneService.cacelFollowUpOrder(bo);
		return JsonUtils.getJson(BaseJsonVo.success(null));
		 
	}
	@RequestMapping(value = "/judgeFollowUpOrder", method = RequestMethod.GET)
	@ApiOperation(value = "判断随访是否预约(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String judgeFollowUpOrder(@RequestParam String uid){
		FollowUpOrderEntity bo=new FollowUpOrderEntity();
		bo.setCreatorUid(uid);
		bo.setOrderStatus("1");
		String day=orderImmuneService.getFollowUpOrderByuid(bo);
		if(StringUtils.isNotEmpty(day)&&!"overdue".equals(day)){
			//返回订单号
			String[] strs = day.split(",");
			OrderImmuneVo vo = new OrderImmuneVo();
			vo.setDay(strs[0]);
			vo.setOrderNumber(strs[1]);
  		    return JsonUtils.getJson(BaseJsonVo.success(vo));
		} 
	    return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.ORDER_ERROR.getKey(), ApiStatusEnum.ORDER_ERROR.getValue()));		 
	}
	@RequestMapping(value = "/getFollowUpOrderList", method = RequestMethod.GET)
	@ApiOperation(value = "获取随访预约记录(居民端)", notes = "{'uid':'居民uid'}", response = BaseJsonVo.class)
	public String getFollowUpOrderList(@RequestParam String uid, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUid(uid); 
		//预约总数
		List<FollowUpOrderVo> lists=orderImmuneService.getFollowUpOrderSumByuid(uid);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<FollowUpOrderVo> list=orderImmuneService.getFollowUpOrderList(vo);
				
		return JsonUtils.getPageJson(lists.size(), pageSize, list);
  
	}
	@RequestMapping(value = "/getImmuneOrderListOfHistoryByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "获取历史预约免疫记录(医生端)", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getImmuneOrderListOfHistoryByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getImmuneOrderListOfHistorySumByDoc(userName);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getImmuneOrderListOfHistoryByDoc(vo);
				
		return JsonUtils.getPageJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getImmuneOrderListOfWeekdayByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "获取本周预约免疫记录(医生端)", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getImmuneOrderListOfWeekdayByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getImmuneOrderListOfWeekdaySumByDoc(userName);	
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getImmuneOrderListOfWeekdayByDoc(vo);
			
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getFollowUpOrderListOfHistoryByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "获取历史未取消的随访记录(医生端)", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getFollowUpOrderListOfHistoryByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getFollowUpOrderListOfHistorySumByDoc(userName);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getFollowUpOrderListOfHistoryByDoc(vo);
				
		return JsonUtils.getPageJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getFollowUpOrderListOfWeekdayByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询本周未取消的随访预约列表 (医生端) ", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getFollowUpOrderListOfWeekdayByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getFollowUpOrderListSumOfWeekdayByDoc(userName);	
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getFollowUpOrderListOfWeekdayByDoc(vo);
			
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getPhysicalExaminationOrderListOfWeekdayByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询本周未取消的体检预约列表  (医生端) ", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getPhysicalExaminationOrderListOfWeekdayByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getPhysicalExaminationOrderListSumOfWeekdayByDoc(userName);	
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getPhysicalExaminationOrderListOfWeekdayByDoc(vo);
			
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getPhysicalExaminationOrderListOfHistoryByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询历史未取消的预约体检记录 (医生端)", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getPhysicalExaminationOrderListOfHistoryByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getPhysicalExaminationOrderListSumOfHistoryByDoc(userName);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getPhysicalExaminationOrderListOfHistoryByDoc(vo);
				
		return JsonUtils.getPageJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getImmuneOrderListOfDayByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询今天未取消的免疫预约列表 (医生端)", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getImmuneOrderListOfDayByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getImmuneOrderListSumOfDayByDoc(userName);	
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getImmuneOrderListOfDayByDoc(vo);
			
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getImmuneOrderListOfTomorrowByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询明天未取消的免疫预约列表 (医生端)", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getImmuneOrderListOfTomorrowByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getImmuneOrderListOfTomorrowSumByDoc(userName);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getImmuneOrderListOfTomorrowByDoc(vo);
				
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getPhysicalExaminationOrderListOfDayByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询今天未取消的体检预约列表 (医生端)", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getPhysicalExaminationOrderListOfDayByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getPhysicalExaminationOrderListSumOfDayByDoc(userName);	
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getPhysicalExaminationOrderListOfDayByDoc(vo);
			
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getPhysicalExaminationOrderListOfTomorrowByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询明天未取消的体检预约列表 (医生端)", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getPhysicalExaminationOrderListOfTomorrowByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getPhysicalExaminationOrderListSumOfTomorrowByDoc(userName);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getPhysicalExaminationOrderListOfTomorrowByDoc(vo);
				
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getFollowUpOrderListOfDayByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询今天未取消的随访预约列表 (医生端) ", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getFollowUpOrderListOfDayByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getFollowUpOrderListSumOfDayByDoc(userName);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getFollowUpOrderListOfDayByDoc(vo);
				
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getFollowUpOrderListOfTomorrowByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询明天未取消的随访预约列表 (医生端) ", notes = "{'userName':'医生userName'}", response = BaseJsonVo.class)
	public String getFollowUpOrderListOfTomorrowByDoc(@RequestParam String userName, @RequestParam Integer pageSize,@RequestParam Integer pageNo){
		OrderParamVo vo = new OrderParamVo();
		vo.setUserName(userName);
		//预约总数
		List<OrderImmuneVo> lists=orderImmuneService.getFollowUpOrderListSumOfTomorrowByDoc(userName);
		// 封装分页对象
		Page<OrderParamVo> page = new Page<OrderParamVo>(pageNo,pageSize, lists.size());
		vo.setPage(page);
		List<OrderImmuneVo> list=orderImmuneService.getFollowUpOrderListOfTomorrowByDoc(vo);
				
		return JsonUtils.getPageAndItemSumJson(lists.size(), pageSize, list); 
	}
	@RequestMapping(value = "/getPhysicalExaminationOrderDetailByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询预约体检详情  (通用) ", notes = "{ 'orderNumber':'预约体检订单编号'}", response = BaseJsonVo.class)
	public String getPhysicalExaminationOrderDetailByDoc(@RequestParam String orderNumber ){
		OrderParamVo vo = new OrderParamVo();
		//vo.setUserName(userName);
		vo.setOrderNumber(orderNumber);
		OrderImmuneVo list=orderImmuneService.getPhysicalExaminationOrderDetailByDoc(vo);
		return JsonUtils.getJson(BaseJsonVo.success(list));  
	}
	@RequestMapping(value = "/getFollowUpOrderDetailByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询预约随访详情   (通用) ", notes = "{'orderNumber':'预约体检订单编号'}", response = BaseJsonVo.class)
	public String getFollowUpOrderDetailByDoc(@RequestParam String orderNumber ){
		OrderParamVo vo = new OrderParamVo();
		//vo.setUserName(userName);
		vo.setOrderNumber(orderNumber);
		OrderImmuneVo list=orderImmuneService.getFollowUpOrderDetailByDoc(vo);
		return JsonUtils.getJson(BaseJsonVo.success(list));  
	}
	@RequestMapping(value = "/getImmuneOrderDetailByDoc", method = RequestMethod.GET)
	@ApiOperation(value = "查询预约免疫详情   (通用) ", notes = "{'orderNumber':'预约体检订单编号'}", response = BaseJsonVo.class)
	public String getImmuneOrderDetailByDoc(@RequestParam String orderNumber ){//@RequestParam String userName,
		OrderParamVo vo = new OrderParamVo();
		//vo.setUserName(userName);
		vo.setOrderNumber(orderNumber);
		OrderImmuneVo list=orderImmuneService.getImmuneOrderDetailByDoc(vo);
		return JsonUtils.getJson(BaseJsonVo.success(list));  
	}
}
