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
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.DoctorCommentEntity;
import com.boco.modules.fdoc.model.HospitalCommentEntity;
import com.boco.modules.fdoc.service.DoctorCommentService;
import com.boco.modules.fdoc.service.HospitalCommentService;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/comment", produces = "text/json;charset=UTF-8")
public class CommentApi {
	
	@Resource
	DoctorCommentService doctorCommentService;
	@Resource
	HospitalCommentService hospitalCommentService;
	/**
	 * 评级 1、2、3、4、5 星
	 * 
	 * @param id
	 * @param type
	 * @param stars
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/grade", method = RequestMethod.POST)
	@ApiOperation(value = "评级 1、2、3、4、5 星", notes = "{'objType':'评论对象(1.医院;2.医生)','data':{'id':'评论id','uid':'用户id',"
			+ "'stars':'星级'}} " , response = BaseJsonVo.class)
	public String grade(@RequestBody String commentBody) throws Exception {
		//解析双层json字符串
		Map<String, String> jsonMap = getJsonMap(commentBody);
		String objType = jsonMap.get("objType");
		String entityJsonStr = jsonMap.get("entityJsonStr");
		int updateNum = 0;
		//根据评论对象选择不同的service
		if (BusinessConstants.COMMONT_OBJTYPE_HOSP.equals(objType)) {
			HospitalCommentEntity hospitalCommentEntity = JsonUtils.getObject(entityJsonStr,HospitalCommentEntity.class);
			updateNum = hospitalCommentService.grade(hospitalCommentEntity);
		}else if (BusinessConstants.COMMONT_OBJTYPE_DOCTOR.equals(objType)) {
			DoctorCommentEntity doctorCommentEntity = JsonUtils.getObject(entityJsonStr, DoctorCommentEntity.class);
			updateNum = doctorCommentService.grade(doctorCommentEntity);
		}
		if (updateNum == 0) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.GOODS_COMMENT_NOTFOUND.getKey(), ApiStatusEnum.GOODS_COMMENT_NOTFOUND.getValue()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(updateNum));
	}
	/**
	 * 添加评论
	 * 
	 * @param hospId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加评论", notes = "{'uid':'用户id','docStars':'医生星级','docId':'医生id','docContent':'医生评论内容',"
			+ "'hosStars':'医院星级','hosId':'医院id','hosContent':'医院评论内容','bookingId':'订单id'}", response = BaseJsonVo.class)
	public String add(@RequestBody String commentBody) throws Exception {
		System.out.println(commentBody);
		Map<String, String> jsonMap = getSingleJsonMap(commentBody);
		if (jsonMap.get("hosId") != null && !"".equals(jsonMap.get("hosId"))) { //对医院评论为空，不添加数据
			HospitalCommentEntity hospitalCommentEntity = new HospitalCommentEntity();
			hospitalCommentEntity.setUid(jsonMap.get("uid"));
			hospitalCommentEntity.setStars(jsonMap.get("hosStars"));
			hospitalCommentEntity.setContent(jsonMap.get("hosContent"));
			hospitalCommentEntity.setObjId(Integer.valueOf(jsonMap.get("hosId")));
			hospitalCommentEntity.setDocId(Integer.valueOf(jsonMap.get("hosId")));
			hospitalCommentEntity.setBookingId(Integer.valueOf(jsonMap.get("bookingId")));
			hospitalCommentEntity.setType(1);
			hospitalCommentService.add(hospitalCommentEntity);
			
		}
		if (jsonMap.get("docId") != null && !"".equals(jsonMap.get("docId"))) { //对医生评论为空，不添加数据
			DoctorCommentEntity doctorCommentEntity = new DoctorCommentEntity();
			doctorCommentEntity.setUid(jsonMap.get("uid"));
			doctorCommentEntity.setStars(jsonMap.get("docStars"));
			doctorCommentEntity.setContent(jsonMap.get("docContent"));
			doctorCommentEntity.setObjId(Integer.valueOf(jsonMap.get("docId")));
			doctorCommentEntity.setDocId(Integer.valueOf(jsonMap.get("docId")));
			doctorCommentEntity.setBookingId(Integer.valueOf(jsonMap.get("bookingId")));
			doctorCommentEntity.setType(1);
			doctorCommentService.add(doctorCommentEntity);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
		//根据评论对象选择不同的service
	}

	/**
	 * 删除评论
	 * 
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "删除评论", notes = "{'objType':'评论对象(1为医院,2为医生)','data':{'id':'评论ID','uid':'用户id'}}  ", response = BaseJsonVo.class)
	public String delete(@RequestBody String commentBody) throws Exception {
		Map<String, String> jsonMap = getJsonMap(commentBody);
		String objType = jsonMap.get("objType");
		String entityJsonStr = jsonMap.get("entityJsonStr");
		
		if (BusinessConstants.COMMONT_OBJTYPE_HOSP.equals(objType)) {
			HospitalCommentEntity hospitalCommentEntity = JsonUtils.getObject(entityJsonStr,HospitalCommentEntity.class);
			hospitalCommentService.delete(String.valueOf(hospitalCommentEntity.getId()));
		}else if (BusinessConstants.COMMONT_OBJTYPE_DOCTOR.equals(objType)) {
			DoctorCommentEntity doctorCommentEntity = JsonUtils.getObject(entityJsonStr, DoctorCommentEntity.class);
			doctorCommentService.delete(String.valueOf(doctorCommentEntity.getId()));
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}

	/**
	 * 查询医生或医院所有评论
	 * 
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ApiOperation(value = "查询医生或医院所有评论", notes = "objType':'评论对象(1为医院,2为医生);'docId':'医生/医院ID' ", response = BaseJsonVo.class)
	public String get(@RequestParam String objType,@RequestParam String docId) throws Exception {
		/**
			Map<String, String> jsonMap = getJsonMap(commentBody);
			String objType = jsonMap.get("objType");
			String entityJsonStr = jsonMap.get("entityJsonStr");
			
			if (BusinessConstants.COMMONT_OBJTYPE_HOSP.equals(objType)) {
				HospitalCommentEntity en = JsonUtils.getObject(entityJsonStr,HospitalCommentEntity.class);
				List<HospitalCommentEntity> hospitalCommentEntities = hospitalCommentService.getByDocId(String.valueOf(en.getDocId()));
				return JsonUtils.getJson(BaseJsonVo.success(hospitalCommentEntities));
			}else if (BusinessConstants.COMMONT_OBJTYPE_DOCTOR.equals(objType)) {
				DoctorCommentEntity en = JsonUtils.getObject(entityJsonStr,DoctorCommentEntity.class);
				List<DoctorCommentEntity> docCommentEntities = doctorCommentService.getByDocId(String.valueOf(en.getDocId()));
				return JsonUtils.getJson(BaseJsonVo.success(docCommentEntities));
			}else {
				return JsonUtils.getJson(BaseJsonVo.success(null));
			}
		*/
		if (BusinessConstants.COMMONT_OBJTYPE_HOSP.equals(objType)) {
			List<HospitalCommentEntity> hospitalCommentEntities = hospitalCommentService.getByDocId(String.valueOf(docId));
			
			return JsonUtils.getJson(BaseJsonVo.success(hospitalCommentEntities));
		}else if (BusinessConstants.COMMONT_OBJTYPE_DOCTOR.equals(objType)) {
			List<DoctorCommentEntity> docCommentEntities = doctorCommentService.getByDocId(String.valueOf(docId));
			return JsonUtils.getJson(BaseJsonVo.success(docCommentEntities));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}

	/**
	 * 回复评论
	 * 
	 * @param commentBody
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	@ApiOperation(value = "回复评论", notes = "{'objType':'评论对象(1.医院;2.医生)','data':{'uid':'用户id','stars':'星级',"
			+ "'objId':'评论id','docId':'医院/医生id','content':'回复内容','rUid':'回复人ID'}", response = BaseJsonVo.class)
	public String reply(@RequestBody String commentBody) throws Exception {
		Map<String, String> jsonMap = getJsonMap(commentBody);
		String objType = jsonMap.get("objType");
		String entityJsonStr = jsonMap.get("entityJsonStr");
		if (BusinessConstants.COMMONT_OBJTYPE_HOSP.equals(objType)) {
			HospitalCommentEntity hospitalCommentEntity = JsonUtils.getObject(entityJsonStr,HospitalCommentEntity.class);
			hospitalCommentEntity.setType(2);
			hospitalCommentService.reply(hospitalCommentEntity);
			return JsonUtils.getJson(BaseJsonVo.success(hospitalCommentEntity));
		}else if (BusinessConstants.COMMONT_OBJTYPE_DOCTOR.equals(objType)) {
			DoctorCommentEntity doctorCommentEntity = JsonUtils.getObject(entityJsonStr, DoctorCommentEntity.class);
			doctorCommentEntity.setType(2);
			doctorCommentService.reply(doctorCommentEntity);
			return JsonUtils.getJson(BaseJsonVo.success(doctorCommentEntity));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(null));
		}
	}
	
	/**
	 * 解析双层json，以map返回
	 * @param commentBody
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getJsonMap(String commentBody) throws Exception{
		Map<String, String> reJsonMap = new HashMap<String, String>();
		String objType = null;
		String hospJsonStr = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES,true);
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
		HashMap jsonMap = mapper.readValue(commentBody,HashMap.class);
		objType = String.valueOf(jsonMap.get("objType"));
		hospJsonStr = mapper.writeValueAsString(jsonMap.get("data"));
		reJsonMap.put("objType", objType);
		reJsonMap.put("entityJsonStr", hospJsonStr);
		
		return reJsonMap;
	}
	
	public Map<String, String> getSingleJsonMap(String commentBody) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES,true);
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
		HashMap jsonMap = mapper.readValue(commentBody,HashMap.class);
		return jsonMap;
	}
}
