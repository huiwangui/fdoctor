package com.boco.modules.fdoc.api.mindery;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.annotation.Resource;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.ReportHealthmonitorEntity;
import com.boco.modules.fdoc.service.MinReportService;
import com.wordnik.swagger.annotations.ApiOperation;

import javassist.expr.NewArray;

/**
 * Description
 * @author lzz
 * @date 2017年8月10日 下午2:50:50
 */
@RestController
@RequestMapping(value="/measure",produces="text/json;charset=UTF-8")
public class SihealDataReceiveApi {
	
	@Resource
	MinReportService minReportService;
	
	@RequestMapping(value = "/upload_check_boxy", method = RequestMethod.POST)
	@ApiOperation(value = "血氧检测数据上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getSpo2Info(@RequestBody String paramsBody) throws ParseException, UnsupportedEncodingException{
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		
		//获取检查数据 据
		String id=(String) pMap.get("id");
		String personId=(String) pMap.get("personId");
		int spo2=Integer.parseInt(pMap.get("spo2")==null?"":pMap.get("spo2").toString());
		int prbpm=Integer.parseInt(pMap.get("prbpm")==null?"":pMap.get("prbpm").toString());
		String mt=(String) pMap.get("mt");
		String machineCode=(String) pMap.get("machine_code");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date time=sdf.parse(mt);
		
		//判断是否已经存在此次检查数
		Calendar startDate=Calendar.getInstance();
		startDate.set(Calendar.YEAR, time.getYear()+1900);
		startDate.set(Calendar.MONTH, time.getMonth());
		startDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		startDate.set(Calendar.HOUR_OF_DAY, 0);  
		startDate.set(Calendar.MINUTE, 0);  
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		Date sdate=new Date(startDate.getTime().getTime());
		
		Calendar endDate=Calendar.getInstance();
		endDate.set(Calendar.YEAR, time.getYear()+1900);
		endDate.set(Calendar.MONTH, time.getMonth());
		endDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		endDate.set(Calendar.HOUR_OF_DAY, 23);  
		endDate.set(Calendar.MINUTE, 59);  
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		Date edate=new Date(endDate.getTime().getTime());
		ReportHealthmonitorEntity rEntity=minReportService.getSihealReport(personId,sdate,edate);
		ReportHealthmonitorEntity reportEntity=new ReportHealthmonitorEntity();
		Integer flag=1;
		if(rEntity == null ){
			reportEntity.setId(id);
			reportEntity.setIdCode(personId);
			reportEntity.setPersonType(0);
			reportEntity.setNation(1);
			reportEntity.setRace(1);
			reportEntity.setSpO2(spo2);
			reportEntity.setPR(prbpm);
			reportEntity.setCreateDate(time);
			reportEntity.setVersion(new Date());
			reportEntity.setDeviceSN(machineCode);
			reportEntity.setSourceType(1);
			reportEntity.setIsSync(0);
			
			//新增血氧数据
			flag=minReportService.insertSpoInfo(reportEntity);
			
		}else{
			reportEntity.setId(rEntity.getId());
			reportEntity.setIdCode(personId);
			reportEntity.setSpO2(spo2);
			reportEntity.setPR(prbpm);
			reportEntity.setVersion(new Date());
			
			//查询此id下有数据，进行修改
			flag=minReportService.updateSihealData(reportEntity);
			
		}
		if(flag==0){
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(0, "获取数据失败")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}else{
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(1, "获取数据成功")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}
		
	}
	
	
	@RequestMapping(value = "/upload_check_bpressure", method = RequestMethod.POST)
	@ApiOperation(value = "血压检测数据上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getBpInfo(@RequestBody String paramsBody) throws ParseException, UnsupportedEncodingException{
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		
		//获取检查数据 据
		String id=(String) pMap.get("id");
		String personId=(String) pMap.get("personId");
		int ssy=Integer.parseInt(pMap.get("ssy")==null?"":pMap.get("ssy").toString());
		int szy=Integer.parseInt(pMap.get("szy")==null?"":pMap.get("szy").toString());
		String mt=(String) pMap.get("mt");
		String machineCode=(String) pMap.get("machine_code");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date time=sdf.parse(mt);
		
		//判断是否已经存在此次检查数
		Calendar startDate=Calendar.getInstance();
		startDate.set(Calendar.YEAR, time.getYear()+1900);
		startDate.set(Calendar.MONTH, time.getMonth());
		startDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		startDate.set(Calendar.HOUR_OF_DAY, 0);  
		startDate.set(Calendar.MINUTE, 0);  
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		Date sdate=new Date(startDate.getTime().getTime());
		
		Calendar endDate=Calendar.getInstance();
		endDate.set(Calendar.YEAR, time.getYear()+1900);
		endDate.set(Calendar.MONTH, time.getMonth());
		endDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		endDate.set(Calendar.HOUR_OF_DAY, 23);  
		endDate.set(Calendar.MINUTE, 59);  
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		Date edate=new Date(endDate.getTime().getTime());
		ReportHealthmonitorEntity rEntity=minReportService.getSihealReport(personId,sdate,edate);
		ReportHealthmonitorEntity reportEntity=new ReportHealthmonitorEntity();
		Integer flag=1;
		if(rEntity == null ){
			reportEntity.setId(id);
			reportEntity.setIdCode(personId);
			reportEntity.setPersonType(0);
			reportEntity.setNation(1);
			reportEntity.setRace(1);
			reportEntity.setLeftSys(ssy);
			reportEntity.setLeftDia(szy);
			reportEntity.setCreateDate(time);
			reportEntity.setVersion(new Date());
			reportEntity.setDeviceSN(machineCode);
			reportEntity.setSourceType(1);
			reportEntity.setIsSync(0);
			
			//新增血氧数据
			flag=minReportService.insertBpInfo(reportEntity);
			
		}else{
			reportEntity.setId(rEntity.getId());
			reportEntity.setIdCode(personId);
			reportEntity.setLeftSys(ssy);
			reportEntity.setLeftDia(szy);
			reportEntity.setVersion(new Date());
			
			//查询此id下有数据，进行修改
			flag=minReportService.updateSihealData(reportEntity);
			
		}
		if(flag==0){
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(0, "获取数据失败")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}else{
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(1, "获取数据成功")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}
		
	}
	
	@RequestMapping(value = "/upload_check_bsugar", method = RequestMethod.POST)
	@ApiOperation(value = "血糖检测数据上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getBsInfo(@RequestBody String paramsBody) throws ParseException, UnsupportedEncodingException{
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		
		//获取检查数据 据
		String id=(String) pMap.get("id");
		String personId=(String) pMap.get("personId");
		String checkType=(String) pMap.get("checkType");
		Float val=Float.parseFloat(pMap.get("val").toString()) ;
		String mt=(String) pMap.get("mt");
		String machineCode=(String) pMap.get("machine_code");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date time=sdf.parse(mt);
		
		//判断是否已经存在此次检查数
		Calendar startDate=Calendar.getInstance();
		startDate.set(Calendar.YEAR, time.getYear()+1900);
		startDate.set(Calendar.MONTH, time.getMonth());
		startDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		startDate.set(Calendar.HOUR_OF_DAY, 0);  
		startDate.set(Calendar.MINUTE, 0);  
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		Date sdate=new Date(startDate.getTime().getTime());
		
		Calendar endDate=Calendar.getInstance();
		endDate.set(Calendar.YEAR, time.getYear()+1900);
		endDate.set(Calendar.MONTH, time.getMonth());
		endDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		endDate.set(Calendar.HOUR_OF_DAY, 23);  
		endDate.set(Calendar.MINUTE, 59);  
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		Date edate=new Date(endDate.getTime().getTime());
		ReportHealthmonitorEntity rEntity=minReportService.getSihealReport(personId,sdate,edate);
		ReportHealthmonitorEntity reportEntity=new ReportHealthmonitorEntity();
		Integer flag=1;
		
		if(rEntity == null ){
			reportEntity.setId(id);
			reportEntity.setIdCode(personId);
			reportEntity.setPersonType(0);
			reportEntity.setNation(1);
			reportEntity.setRace(1);
			if(checkType.equals("0")){
				reportEntity.setBeforeMealFbg(val);
			}else if(checkType.equals("1")){
				reportEntity.setAfterMealFbg(val);
			}else if(checkType.equals("2")){
				reportEntity.setBeforeMealFbg(val);
			}
			reportEntity.setCreateDate(time);
			reportEntity.setVersion(new Date());
			reportEntity.setDeviceSN(machineCode);
			reportEntity.setSourceType(1);
			reportEntity.setIsSync(0);
			
			//新增血氧数据
			flag=minReportService.insertBsInfo(reportEntity);
			
		}else{
			reportEntity.setId(rEntity.getId());
			reportEntity.setIdCode(personId);
			if(checkType.equals("0")){
				reportEntity.setBeforeMealFbg(val);
			}else if(checkType.equals("1")){
				reportEntity.setAfterMealFbg(val);
			}else if(checkType.equals("2")){
				reportEntity.setBeforeMealFbg(val);
			}
			reportEntity.setVersion(new Date());
			
			//查询此id下有数据，进行修改
			flag=minReportService.updateSihealData(reportEntity);
			
		}
		if(flag==0){
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(0, "获取数据失败")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}else{
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(1, "获取数据成功")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}
		
	}
	
	@RequestMapping(value = "/upload_check_temp", method = RequestMethod.POST)
	@ApiOperation(value = "体温检测数据上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getTempInfo(@RequestBody String paramsBody) throws ParseException, UnsupportedEncodingException{
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		
		//获取检查数据 据
		String id=(String) pMap.get("id");
		String personId=(String) pMap.get("personId");
		Float val=Float.parseFloat(pMap.get("val").toString()) ;
		String mt=(String) pMap.get("mt");
		String machineCode=(String) pMap.get("machine_code");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date time=sdf.parse(mt);
		
		//判断是否已经存在此次检查数
		Calendar startDate=Calendar.getInstance();
		startDate.set(Calendar.YEAR, time.getYear()+1900);
		startDate.set(Calendar.MONTH, time.getMonth());
		startDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		startDate.set(Calendar.HOUR_OF_DAY, 0);  
		startDate.set(Calendar.MINUTE, 0);  
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		Date sdate=new Date(startDate.getTime().getTime());
		
		Calendar endDate=Calendar.getInstance();
		endDate.set(Calendar.YEAR, time.getYear()+1900);
		endDate.set(Calendar.MONTH, time.getMonth());
		endDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		endDate.set(Calendar.HOUR_OF_DAY, 23);  
		endDate.set(Calendar.MINUTE, 59);  
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		Date edate=new Date(endDate.getTime().getTime());
		ReportHealthmonitorEntity rEntity=minReportService.getSihealReport(personId,sdate,edate);
		ReportHealthmonitorEntity reportEntity=new ReportHealthmonitorEntity();
		Integer flag=1;
		if(rEntity == null ){
			reportEntity.setId(id);
			reportEntity.setIdCode(personId);
			reportEntity.setPersonType(0);
			reportEntity.setNation(1);
			reportEntity.setRace(1);
			reportEntity.setTemp(val);
			reportEntity.setCreateDate(time);
			reportEntity.setVersion(new Date());
			reportEntity.setDeviceSN(machineCode);
			reportEntity.setSourceType(1);
			reportEntity.setIsSync(0);
			
			//新增血氧数据
			flag=minReportService.insertTempInfo(reportEntity);
			
		}else{
			reportEntity.setId(rEntity.getId());
			reportEntity.setIdCode(personId);
			reportEntity.setTemp(val);
			reportEntity.setVersion(new Date());
			
			//查询此id下有数据，进行修改
			flag=minReportService.updateSihealData(reportEntity);
		}
		if(flag==0){
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(0, "获取数据失败")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}else{
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(1, "获取数据成功")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}
		
	}
	
	@RequestMapping(value = "/upload_check_urine", method = RequestMethod.POST)
	@ApiOperation(value = "尿常规检测数据上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getUrineInfo(@RequestBody String paramsBody) throws ParseException, UnsupportedEncodingException{
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		
		//获取检查数据 据
		String id=(String) pMap.get("id");
		String personId=(String) pMap.get("personId");
		String LEU=(String) pMap.get("leu");
		String NIT=(String) pMap.get("nit");
		String UBG=(String) pMap.get("ubg");
		String PRO=(String) pMap.get("pro");
		String PH=(String) pMap.get("ph");
		String BLD=(String) pMap.get("bld");
		String SG=(String) pMap.get("sg");
		String KET=(String) pMap.get("ket");
		String BIL=(String) pMap.get("bil");
		String GLU=(String) pMap.get("glu");
		String VC=(String) pMap.get("vc");
		String mt=(String) pMap.get("mt");
		String machineCode=(String) pMap.get("machine_code");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date time=sdf.parse(mt);
		
		//判断是否已经存在此次检查数
		Calendar startDate=Calendar.getInstance();
		startDate.set(Calendar.YEAR, time.getYear()+1900);
		startDate.set(Calendar.MONTH, time.getMonth());
		startDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		startDate.set(Calendar.HOUR_OF_DAY, 0);  
		startDate.set(Calendar.MINUTE, 0);  
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		Date sdate=new Date(startDate.getTime().getTime());
		
		Calendar endDate=Calendar.getInstance();
		endDate.set(Calendar.YEAR, time.getYear()+1900);
		endDate.set(Calendar.MONTH, time.getMonth());
		endDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		endDate.set(Calendar.HOUR_OF_DAY, 23);  
		endDate.set(Calendar.MINUTE, 59);  
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		Date edate=new Date(endDate.getTime().getTime());
		ReportHealthmonitorEntity rEntity=minReportService.getSihealReport(personId,sdate,edate);
		ReportHealthmonitorEntity reportEntity=new ReportHealthmonitorEntity();
		Integer flag=1;
		if(rEntity == null ){
			reportEntity.setId(id);
			reportEntity.setIdCode(personId);
			reportEntity.setPersonType(0);
			reportEntity.setNation(1);
			reportEntity.setRace(1);
			reportEntity.setUrineNit(NIT);
			reportEntity.setUrineLeu(LEU);
			reportEntity.setUrineUbg(UBG);
			reportEntity.setUrinePro(PRO);
			reportEntity.setUrinePH(PH);
			reportEntity.setUrineBld(BLD);
			reportEntity.setUrineSG(SG);
			reportEntity.setUrineKet(KET);
			reportEntity.setUrineBil(BIL);
			reportEntity.setUrineGlu(GLU);
			reportEntity.setUrineVC(VC);
			reportEntity.setCreateDate(time);
			reportEntity.setVersion(new Date());
			reportEntity.setDeviceSN(machineCode);
			reportEntity.setSourceType(1);
			reportEntity.setIsSync(0);
			
			//新增血氧数据
			flag=minReportService.insertUrineInfo(reportEntity);
			
		}else{
			reportEntity.setId(rEntity.getId());
			reportEntity.setIdCode(personId);
			reportEntity.setUrineNit(NIT);
			reportEntity.setUrineLeu(LEU);
			reportEntity.setUrineUbg(UBG);
			reportEntity.setUrinePro(PRO);
			reportEntity.setUrinePH(PH);
			reportEntity.setUrineBld(BLD);
			reportEntity.setUrineSG(SG);
			reportEntity.setUrineKet(KET);
			reportEntity.setUrineBil(BIL);
			reportEntity.setUrineGlu(GLU);
			reportEntity.setUrineVC(VC);
			reportEntity.setVersion(new Date());
			
			//查询此id下有数据，进行修改
			flag=minReportService.updateSihealData(reportEntity);
		}
		if(flag==0){
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(0, "获取数据失败")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}else{
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(1, "获取数据成功")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}
		
	}
	
	@RequestMapping(value = "/upload_check_eheart", method = RequestMethod.POST)
	@ApiOperation(value = "心电检测数据上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getEcgInfo(@RequestBody String paramsBody) throws ParseException, UnsupportedEncodingException{
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		
		//获取检查数据 据
		String id=(String) pMap.get("id");
		String personId=(String) pMap.get("personId");
		int hr=Integer.parseInt(pMap.get("hr")==null?"":pMap.get("hr").toString());
		String file=(String) pMap.get("file");
		String mt=(String) pMap.get("mt");
		String machineCode=(String) pMap.get("machine_code");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date time=sdf.parse(mt);
		
		//判断是否已经存在此次检查数
		Calendar startDate=Calendar.getInstance();
		startDate.set(Calendar.YEAR, time.getYear()+1900);
		startDate.set(Calendar.MONTH, time.getMonth());
		startDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		startDate.set(Calendar.HOUR_OF_DAY, 0);  
		startDate.set(Calendar.MINUTE, 0);  
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		Date sdate=new Date(startDate.getTime().getTime());
		
		Calendar endDate=Calendar.getInstance();
		endDate.set(Calendar.YEAR, time.getYear()+1900);
		endDate.set(Calendar.MONTH, time.getMonth());
		endDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		endDate.set(Calendar.HOUR_OF_DAY, 23);  
		endDate.set(Calendar.MINUTE, 59);  
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		Date edate=new Date(endDate.getTime().getTime());
		ReportHealthmonitorEntity rEntity=minReportService.getSihealReport(personId,sdate,edate);
		ReportHealthmonitorEntity reportEntity=new ReportHealthmonitorEntity();
		Integer flag=1;
		if(rEntity == null ){
			reportEntity.setId(id);
			reportEntity.setIdCode(personId);
			reportEntity.setPersonType(0);
			reportEntity.setNation(1);
			reportEntity.setRace(1);
			reportEntity.setEcgReport(file);
			reportEntity.setHR(hr);
			reportEntity.setCreateDate(time);
			reportEntity.setVersion(new Date());
			reportEntity.setDeviceSN(machineCode);
			reportEntity.setSourceType(1);
			reportEntity.setIsSync(0);
			
			//新增血氧数据
			flag=minReportService.insertEcgInfo(reportEntity);
			
		}else{
			reportEntity.setId(rEntity.getId());
			reportEntity.setIdCode(personId);
			reportEntity.setEcgReport(file);
			reportEntity.setHR(hr);
			reportEntity.setVersion(new Date());
			
			//查询此id下有数据，进行修改
			flag=minReportService.updateSihealData(reportEntity);
		}
		if(flag==0){
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(0, "获取数据失败")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}else{
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(1, "获取数据成功")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}
		
	}
	
	@RequestMapping(value = "/upload_check_hr", method = RequestMethod.POST)
	@ApiOperation(value = "心率检测数据上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getHrInfo(@RequestBody String paramsBody) throws ParseException, UnsupportedEncodingException{
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		
		//获取检查数据 据
		String id=(String) pMap.get("id");
		String personId=(String) pMap.get("personId");
		int hr=Integer.parseInt(pMap.get("hr")==null?"":pMap.get("hr").toString());
		String mt=(String) pMap.get("mt");
		String machineCode=(String) pMap.get("machine_code");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date time=sdf.parse(mt);
		
		//判断是否已经存在此次检查数
		Calendar startDate=Calendar.getInstance();
		startDate.set(Calendar.YEAR, time.getYear()+1900);
		startDate.set(Calendar.MONTH, time.getMonth());
		startDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		startDate.set(Calendar.HOUR_OF_DAY, 0);  
		startDate.set(Calendar.MINUTE, 0);  
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);
		Date sdate=new Date(startDate.getTime().getTime());
		
		Calendar endDate=Calendar.getInstance();
		endDate.set(Calendar.YEAR, time.getYear()+1900);
		endDate.set(Calendar.MONTH, time.getMonth());
		endDate.set(Calendar.DAY_OF_MONTH,time.getDate());
		endDate.set(Calendar.HOUR_OF_DAY, 23);  
		endDate.set(Calendar.MINUTE, 59);  
		endDate.set(Calendar.SECOND, 59);
		endDate.set(Calendar.MILLISECOND, 999);
		Date edate=new Date(endDate.getTime().getTime());
		ReportHealthmonitorEntity rEntity=minReportService.getSihealReport(personId,sdate,edate);
		ReportHealthmonitorEntity reportEntity=new ReportHealthmonitorEntity();
		Integer flag=1;
		if(rEntity == null ){
			reportEntity.setId(id);
			reportEntity.setIdCode(personId);
			reportEntity.setPersonType(0);
			reportEntity.setNation(1);
			reportEntity.setRace(1);
			reportEntity.setHR(hr);
			reportEntity.setCreateDate(time);
			reportEntity.setVersion(new Date());
			reportEntity.setDeviceSN(machineCode);
			reportEntity.setSourceType(1);
			reportEntity.setIsSync(0);
			
			//新增血氧数据
			flag=minReportService.insertHrInfo(reportEntity);
			
		}else{
			reportEntity.setId(rEntity.getId());
			reportEntity.setIdCode(personId);
			reportEntity.setHR(hr);
			reportEntity.setVersion(new Date());
			
			//查询此id下有数据，进行修改
			flag=minReportService.updateSihealData(reportEntity);
		}
		if(flag==0){
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(0, "获取数据失败")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}else{
			String encode=null;
			try {
				byte[] bytes = JsonUtils.getJson(BaseJsonVo.empty(1, "获取数据成功")).getBytes("UTF-8");
				encode=Base64.getEncoder().encodeToString(bytes);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encode;
		}
		
	}
	
	public static String getFormatBase64(String s) throws UnsupportedEncodingException{
		String result=null;
		if(s!=null){
			Decoder decoder= Base64.getDecoder();
			result=new String(decoder.decode(s),"utf-8");   
		}
		System.out.println(result);
		return result;
	}

}
