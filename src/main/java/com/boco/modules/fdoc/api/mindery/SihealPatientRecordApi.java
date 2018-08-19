package com.boco.modules.fdoc.api.mindery;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;
import java.util.Base64.Decoder;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Description
 * @author lzz
 * @date 2017年8月15日 上午9:20:40
 */
@RestController
@RequestMapping(value="/upload_healthfile",produces="text/json;charset=UTF-8")
public class SihealPatientRecordApi {
	
	
	@RequestMapping(value = "/upload_health_records", method = RequestMethod.POST)
	@ApiOperation(value = "个人基本信息上传上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getHealthRecordInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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
	
	@RequestMapping(value = "/upload_health_examination", method = RequestMethod.POST)
	@ApiOperation(value = "健康体检上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getExaminationInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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
	
	@RequestMapping(value = "/upload_elderly_cap", method = RequestMethod.POST)
	@ApiOperation(value = "老年人生活能力评估上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getElderLyInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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
	
	@RequestMapping(value = "/upload_hbp", method = RequestMethod.POST)
	@ApiOperation(value = "高血压患者随访服务记录上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getFollowHbpInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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
	
	@RequestMapping(value = "/upload_diabetes", method = RequestMethod.POST)
	@ApiOperation(value = "2型糖尿病患者随访上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getFollowDiabetesInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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
	
	@RequestMapping(value = "/upload_diabetes_exam", method = RequestMethod.POST)
	@ApiOperation(value = "2型糖尿病患者体检上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getDiabetesExamInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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
	
	@RequestMapping(value = "/upload_patient_record", method = RequestMethod.POST)
	@ApiOperation(value = "病人信息上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getPatientRecordInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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
	
	@RequestMapping(value = "/upload_person_baseinfo", method = RequestMethod.POST)
	@ApiOperation(value = "用户基本信息上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getBaseInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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
	
	@RequestMapping(value = "/upload_new_member_file", method = RequestMethod.POST)
	@ApiOperation(value = "封面档案上传", notes = "{'paramsBody':'请求体'}", response = BaseJsonVo.class)
	public String getFileNewInfo(@RequestBody String paramsBody){
		
		//base64解密
		String paramString=getFormatBase64(paramsBody);
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramString);
		Integer flag=1;
		
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

	public static String getFormatBase64(String s){
		String result=null;
		if(s!=null){
			Decoder decoder= Base64.getDecoder();
			result=new String(decoder.decode(s));   
		}
		System.out.println(result);
		return result;
	}
}
