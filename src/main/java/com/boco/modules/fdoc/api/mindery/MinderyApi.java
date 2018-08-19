package com.boco.modules.fdoc.api.mindery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.ReportHealthmonitorEntity;
import com.boco.modules.fdoc.service.MinReportService;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="MinderyReport",produces="text/json;charset=UTF-8")
public class MinderyApi {

	@Resource
	MinReportService minReportService;
	
	@RequestMapping(value = "/getReportList", method = RequestMethod.GET)
	@ApiOperation(value = "获得某人某时间范围内的体检记录", notes = "{'idCode':'身份证号','queryDate':'查询日期'}", response = BaseJsonVo.class)
	public String getReportList(@RequestParam String idCode,@RequestParam String queryDate) throws ParseException {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date= sdf.parse(queryDate);
		Calendar startDate=Calendar.getInstance();
		startDate.set(Calendar.YEAR, date.getYear()+1900);
		startDate.set(Calendar.MONTH, date.getMonth());
		startDate.set(Calendar.DAY_OF_MONTH,date.getDate());
		startDate.set(Calendar.HOUR_OF_DAY, 0);  
		startDate.set(Calendar.MINUTE, 0);  
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		Date sdate=new Date(startDate.getTime().getTime());
		
		Calendar endDate=Calendar.getInstance();
		endDate.set(Calendar.YEAR, date.getYear()+1900);
		endDate.set(Calendar.MONTH, date.getMonth());
		endDate.set(Calendar.DAY_OF_MONTH,date.getDate());
		endDate.set(Calendar.HOUR_OF_DAY, 23);  
		endDate.set(Calendar.MINUTE, 59);  
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		Date edate=new Date(endDate.getTime().getTime());
		List<ReportHealthmonitorEntity> list =minReportService.findList(idCode, sdate, edate);
		return JsonUtils.getJson(BaseJsonVo.success(list));
		
		
		
	}
}
