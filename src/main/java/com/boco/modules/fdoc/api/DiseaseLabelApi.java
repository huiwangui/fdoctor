package com.boco.modules.fdoc.api;

import java.util.ArrayList;
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
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.DiseaseLabelEntity;
import com.boco.modules.fdoc.model.DiseaseLabelPersonRelationEntity;
import com.boco.modules.fdoc.model.GroupMsgTemplateEntity;
import com.boco.modules.fdoc.model.SendMsgEntity;
import com.boco.modules.fdoc.service.DiseaseLabelService;
import com.boco.modules.fdoc.service.SendMsgService;
import com.boco.modules.fdoc.vo.DiseaseLabelVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/diseaseLabel", produces = "text/json;charset=utf-8")
public class DiseaseLabelApi extends BaseController {
	
	@Resource
	DiseaseLabelService diseaseLabelService;
	@Resource
	SendMsgService sendMsgService;
	
	@RequestMapping(value = "/getLabelList", method = RequestMethod.GET)
	@ApiOperation(value = "获取标签列表（医生端）", notes = "{'teamId':'医生团队ID'}")
	public String getLabelList(@RequestParam String teamId){
		return JsonUtils.getJson(BaseJsonVo.success(diseaseLabelService.getLabelList(teamId)));
	}
	
	@RequestMapping(value = "/getPersonNotInLabel", method = RequestMethod.GET)
	@ApiOperation(value = "查看尚未加入此标签的签约人员（医生端）", notes = "{'teamId':'医生团队ID','labelId':'标签ID','personName':'人员姓名'}")
	public String getPersonNotInLabel(@RequestParam String teamId,@RequestParam Integer labelId,@RequestParam String personName,
			@RequestParam Integer pageNo,@RequestParam Integer pageSize){
		//封装参数
		DiseaseLabelVo vo = new DiseaseLabelVo();
		vo.setDocTeamId(teamId);
		vo.setPersonName(personName);
		vo.setId(labelId);
		//获取总数
		Integer personNotInLabelCount = diseaseLabelService.getPersonNotInLabelCount(vo);
		//封装分页参数
		Page<DiseaseLabelVo> page = new Page<DiseaseLabelVo>(pageNo, pageSize, personNotInLabelCount);
		vo.setPage(page);
		//查询返回结果
		List<PersonInformationVo> personNotInLabel = diseaseLabelService.getPersonNotInLabel(vo);
		return JsonUtils.getPageJson(personNotInLabelCount, pageSize, personNotInLabel);
	}
	
	@RequestMapping(value = "/getPersonInLabel", method = RequestMethod.GET)
	@ApiOperation(value = "查看已加入此标签的签约人员（医生端）", notes = "{'labelId':'标签ID'}")
	public String getPersonNotInLabel(@RequestParam Integer labelId, @RequestParam Integer pageNo,@RequestParam Integer pageSize){
		//封装参数
		DiseaseLabelVo vo = new DiseaseLabelVo();
		vo.setId(labelId);
		//获取总数
		Integer personNotInLabelCount = diseaseLabelService.getPersonInLabelCount(vo);
		//封装分页参数
		Page<DiseaseLabelVo> page = new Page<DiseaseLabelVo>(pageNo, pageSize, personNotInLabelCount);
		vo.setPage(page);
		//查询返回结果
		List<PersonInformationVo> personNotInLabel = diseaseLabelService.getPersonInLabel(vo);
		return JsonUtils.getPageJson(personNotInLabelCount, pageSize, personNotInLabel);
	}
	
	@RequestMapping(value = "/editLabel", method = RequestMethod.POST)
	@ApiOperation(value = "编辑标签，包含修改标签、人员，新增标签、人员（医生端）", notes = "{'labelId':'标签ID，若新增则传0，整数类型','teamId':'医生团队ID','labelName':'标签名','defaultFlag':'是否为默认标签：0.否 1.是 ','updateFlag':'新增或删除标识:0删除,1新增,新增标签的情况可传空字符','personIdList':['居民ID数组']}")
	public String editLabel(@RequestBody String paramBody){
		//解析json
		Map<String, Object> objectJsonMap = JsonUtils.getObjectJsonMap(paramBody);
		Integer labelId = (Integer) objectJsonMap.get("labelId");
		String labelName = (String) objectJsonMap.get("labelName");
		String defaultFlag = (String) objectJsonMap.get("defaultFlag");
		String teamId = (String) objectJsonMap.get("teamId");
		String updateFlag=(String) objectJsonMap.get("updateFlag");
		List<String> personIdList = (List<String>) objectJsonMap.get("personIdList");
		
		//封装标签对象
		DiseaseLabelEntity labelEntity = new DiseaseLabelEntity();
		labelEntity.setId(labelId);
		labelEntity.setLabelName(labelName);
		labelEntity.setDefaultFlag(defaultFlag);
		labelEntity.setDocTeamId(teamId);
		switch(labelName){
		 case  "高血压":
			 labelEntity.setDiseaseCode(BusinessConstants.SIGN_HYPER);
			 break;
		 case  "糖尿病":
			 labelEntity.setDiseaseCode(BusinessConstants.SIGN_DIABETES);
			 break;
		 case  "儿童":
			 labelEntity.setDiseaseCode(BusinessConstants.SIGN_CHILD);
			 break;
		 case  "普通成人":
			 labelEntity.setDiseaseCode(BusinessConstants.SIGN_CHENR);
			 break;
		 case  "老年人":
			 labelEntity.setDiseaseCode(BusinessConstants.SIGN_OLDM);
			 break;
		 case  "重型精神病":
			 labelEntity.setDiseaseCode(BusinessConstants.SIGN_MAJOR);
			 break;
		 case  "孕产妇":
			 labelEntity.setDiseaseCode(BusinessConstants.SIGN_YUNCH);
			 break;
		 default:
			 break;
		}
		//封装对应关系集合
		List<DiseaseLabelPersonRelationEntity> relationList = new ArrayList<DiseaseLabelPersonRelationEntity>();
			
		if(personIdList!=null && personIdList.size()>0){
			for (String personId : personIdList) {
					DiseaseLabelPersonRelationEntity relationEntity = new DiseaseLabelPersonRelationEntity();
					relationEntity.setPersonId(personId);
					relationList.add(relationEntity);
				}
		}
		//判断是修改还是新增：若id为0，则为新增，否则为修改
		if (labelId == 0) {
			diseaseLabelService.addLabelAndRelations(labelEntity, relationList);
		}else {
			diseaseLabelService.updateLabelAndRelations(labelEntity, relationList, updateFlag);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/deleteLabel", method = RequestMethod.POST)
	@ApiOperation(value = "删除标签（医生端）", notes = "{'labelId':'标签ID','defaultFlag':'是否为默认标签：0.否 1.是 '}")
	public String deleteLabel(@RequestBody String paramBody){
		//解析json
		Map<String, Object> objectJsonMap = JsonUtils.getObjectJsonMap(paramBody);
		Integer labelId = (Integer) objectJsonMap.get("labelId");
		String defaultFlag = (String) objectJsonMap.get("defaultFlag");
		//判断如果为默认模板，就不让修改
		if (BusinessConstants.DEFAULT_FLAG_DEFAULT.equals(defaultFlag)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DEFAULT_NOT_ALLOW.getKey(), ApiStatusEnum.DEFAULT_NOT_ALLOW.getValue()));
		}else {
			diseaseLabelService.deleteLabel(labelId);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/editGroupMsgTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "编辑群发模板（医生端）", notes = "{'id':'群发模板ID，若新增则传0','teamId':'医生团队ID','templateName':'群发模板名','templateContent':'群发模板内容，300字以内',"
			+ "'defaultFlag':'是否为默认标签：0.否 1.是  若为默认标签，则无法删除，但能修改'}")
	public String editGroupMsgTemplate(@RequestBody String paramBody){
		//解析json
		Map<String, Object> objectJsonMap = JsonUtils.getObjectJsonMap(paramBody);
		Integer id = (Integer) objectJsonMap.get("id");
		String teamId = (String) objectJsonMap.get("teamId");
		String templateName = (String) objectJsonMap.get("templateName");
		String templateContent = (String) objectJsonMap.get("templateContent");
		String defaultFlag = (String) objectJsonMap.get("defaultFlag");
		//封装对象
		GroupMsgTemplateEntity entity = new GroupMsgTemplateEntity();
		entity.setId(id);
		entity.setDocTeamId(teamId);
		entity.setTemplateName(templateName);
		entity.setTemplateContent(templateContent);
		entity.setDefaultFlag(defaultFlag);
		
		//判断执行新增还是修改操作
		if (id == 0) {
			diseaseLabelService.addGroupMsgTemplate(entity);
		}else {
			diseaseLabelService.updateGroupMsgTemplate(entity);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/deleteGroupMsgTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "删除群发模板（医生端）", notes = "{'id':'群发模板ID,'defaultFlag':'是否为默认标签：0.否 1.是  若为默认标签，则无法删除，但能修改'}")
	public String deleteGroupMsgTemplate(@RequestBody String paramBody){
		//解析json
		Map<String, String> objectJsonMap = JsonUtils.getSingleJsonMap(paramBody);
		Integer id = Integer.parseInt(objectJsonMap.get("id"));
		String defaultFlag = objectJsonMap.get("defaultFlag");
		//判断如果为默认模板，就不让修改
		if (BusinessConstants.DEFAULT_FLAG_DEFAULT.equals(defaultFlag)) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.DEFAULT_NOT_ALLOW.getKey(), ApiStatusEnum.DEFAULT_NOT_ALLOW.getValue()));
		}else {
			diseaseLabelService.deleteGroupMsgTemplate(id);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/getTemplateByDocTeam", method = RequestMethod.GET)
	@ApiOperation(value = "获取群发模板列表（医生端）", notes = "{'teamId':'医生团队ID'}")
	public String getTemplateByDocTeam(@RequestParam String teamId){
		return JsonUtils.getJson(BaseJsonVo.success(diseaseLabelService.getTemplateByDocTeam(teamId)));
	}
	
	@RequestMapping(value = "/sendMsgByDiseaseLabel", method = RequestMethod.GET)
	@ApiOperation(value = "群发信息（医生端）", notes = "{'templateContent':'模板内容','labelIDs':'慢病标签ID，用,（小写逗号）隔开'}")
	public String sendMsgByDiseaseLabel(@RequestParam String templateContent,@RequestParam String labelIDs){
		DiseaseLabelVo vo = new DiseaseLabelVo();
		vo.setIds(labelIDs);
		
		/** 签约成功推送给用户**/
		//获取接收推送的用户账号列表
		List<String> accounts = diseaseLabelService.getUsersInLabel(vo);
		CloudMobilePush push = new CloudMobilePush();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String title = "您的家庭医生为您发来关怀信息";
		push.androidPush(accounts, "您的家庭医生为您发来关怀信息", templateContent, BusinessConstants.PUSH_ACTIVITY,
				BusinessConstants.PUSH_ITEM_USER, PushUtils.packPushParam(BusinessConstants.PUSH_TYPE_LABEL_SENG, paramMap));
		/**
		 * 插入推送消息表
		 */
		SendMsgEntity msg = new SendMsgEntity(title,templateContent,BusinessConstants.PUSH_ITEM_USER_INT,
				BusinessConstants.PUSH_TYPE_LABEL_SENG,JsonUtils.getJsonFormat(paramMap));
		for (String account : accounts) {
			msg.setUid(account);
			sendMsgService.addMsg(msg);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/changeSelectStatus", method = RequestMethod.POST)
	@ApiOperation(value = "修改标签选中状态，若本来没有该标签，则打上标签。反之则取消（医生端）", notes = "{'labelId':'慢病标签ID','personId':'居民ID'}")
	public String changeSelectStatus(@RequestBody String paramBody){
		// 解析Json,设置查询对象
		Map<String, Object> jsonMap = JsonUtils.getObjectJsonMap(paramBody);
		Integer labelId = Integer.valueOf((String) jsonMap.get("labelId"));
		String personId = (String) jsonMap.get("personId");
		DiseaseLabelPersonRelationEntity entity = new DiseaseLabelPersonRelationEntity();
		entity.setLabelId(labelId);
		entity.setPersonId(personId);
		
		List<DiseaseLabelPersonRelationEntity> relation = diseaseLabelService.getRelation(entity);
		if (relation.size() > 0) {
			diseaseLabelService.deleteRelation(entity);
		}else {
			diseaseLabelService.addRelation(entity);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
}
