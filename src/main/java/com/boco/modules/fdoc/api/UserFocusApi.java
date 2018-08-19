package com.boco.modules.fdoc.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boco.modules.fdoc.service.UserFocusService;

@RestController
@RequestMapping(value = "/userFocus", produces = "text/json;charset=UTF-8")
public class UserFocusApi {
	
	@Resource
	UserFocusService userFocusService;

	/**
	 * @RequestMapping(value = "/followHospital", method = RequestMethod.POST)
	@ApiOperation(value = "关注医院", notes = "{'uid':'用户ID','objId':'关注对象ID',"
			+ "'remark':'备注'}", response = MessageResult.class)
	public String followHospital(@RequestBody String memberBody){
		UserFocusEntity userFocusEntity = JsonUtils.getObject(memberBody,UserFocusEntity.class);
		userFocusEntity.setType("1");
		userFocusService.userFollow(userFocusEntity);
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/followDoctor", method = RequestMethod.POST)
	@ApiOperation(value = "关注医生", notes = "{'uid':'用户ID','objId':'关注对象ID',"
			+ "'remark':'备注'}", response = MessageResult.class)
	public String followDoctor(@RequestBody String memberBody){
		UserFocusEntity userFocusEntity = JsonUtils.getObject(memberBody,UserFocusEntity.class);
		userFocusEntity.setType("2");
		userFocusService.userFollow(userFocusEntity);
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	
	@RequestMapping(value = "/focusDoctor", method = RequestMethod.POST)
	@ApiOperation(value = "取消关注医生", notes = "{'uid':'用户ID','objId':'关注对象ID'}", response = MessageResult.class)
	public String focusDoctor(@RequestBody String memberBody){
		UserFocusEntity userFocusEntity = JsonUtils.getObject(memberBody,UserFocusEntity.class);
		userFocusEntity.setType("2");
		userFocusService.userFocus(userFocusEntity);
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/focusHospital", method = RequestMethod.POST)
	@ApiOperation(value = "取消关注医院", notes = "{'uid':'用户ID','objId':'关注对象ID'}", response = MessageResult.class)
	public String focusHospital(@RequestBody String memberBody){
		UserFocusEntity userFocusEntity = JsonUtils.getObject(memberBody,UserFocusEntity.class);
		userFocusEntity.setType("1");
		userFocusService.userFocus(userFocusEntity);
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	@RequestMapping(value = "/hasFollowed", method = RequestMethod.POST)
	@ApiOperation(value = "查看是否已关注", notes = "{'uid':'用户ID','objId':'关注对象ID','type':'关注类型（1.医院/2 医生）'}", response = MessageResult.class)
	public String hasFollowed(@RequestBody String memberBody){
		UserFocusEntity userFocusEntity = JsonUtils.getObject(memberBody,UserFocusEntity.class);
		int count = userFocusService.getCount(userFocusEntity);
		//count为0，表示未关注当前对象
		if (count == 0) {
			return JsonUtils.getJson(BaseJsonVo.success(false));
		}else {
			return JsonUtils.getJson(BaseJsonVo.success(true));
		}
	}
	 * @author q
	 * @param memberBody
	 * @return
	 */
	
}
