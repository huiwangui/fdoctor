package com.boco.modules.fdoc.api.healthEdu;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jboss.netty.handler.timeout.ReadTimeoutException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.HealthEduEntity;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.HealthEduService;
import com.boco.modules.fdoc.vo.HealthEduVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 健康教育接口
 * @author lzz
 *
 */
@RestController
@RequestMapping(value="/healthEdu",produces="text/json;charset=UTF-8")
public class HealthEduAPi {
	
	@Resource
	DocUserService docUserService;
	@Resource
	HealthEduService eduService;
	
	@RequestMapping(value = "/getTeamEduList", method = RequestMethod.GET)
	@ApiOperation(value = "获取健康教育列表(可根据用户名获取其团队发送的教育列表)", notes = "{'userName':'登录医生用户名(可选项)','pageIndex':'页码(int)','pageSize':'页面大小(int)'}")
	public String getTeamEduList(@RequestParam(value="userName",required=false) String userName,
			@RequestParam Integer pageSize, @RequestParam Integer pageIndex){
		
		HealthEduVo healthEduVo=new HealthEduVo();
		healthEduVo.setOrganizeTime(new Date());
		
		int count=eduService.getCountALL(healthEduVo);
		Page<HealthEduVo> page=new Page<HealthEduVo>(pageIndex, pageSize, count);
		
		if(userName==null || userName.equals("")){
			
			
			healthEduVo.setPage(page);
			List<HealthEduVo> list=eduService.getEduList(healthEduVo);
			
			return JsonUtils.getPageJsonWithTotal(count, pageSize, list);
		}else{
			String teamId=docUserService.getTeamIdbyUsername(userName);
			System.out.println("---------------"+teamId);
			healthEduVo.setTeamId(teamId);
			count=0;
			if(teamId==null){
				count=eduService.getCountALL(healthEduVo);
			}else{
				count=eduService.getCount(healthEduVo);
			} 
			healthEduVo.setPage(page);
			List<HealthEduVo> list=eduService.getEduList(healthEduVo);
			return JsonUtils.getPageJsonWithTotal(count, pageSize, list);
		}
		
	}
	@RequestMapping(value = "/getEduById", method = RequestMethod.GET)
	@ApiOperation(value = "获取健康教育(使用Id)", notes = "{'id':'健康教育id(int)'}")
	public String getEduById(@RequestParam Integer id){
		HealthEduEntity eduEntity=eduService.getEduById(id);
		return JsonUtils.getJson(BaseJsonVo.success(eduEntity));
		
	}
	@RequestMapping(value = "/getEduByTheme", method = RequestMethod.GET)
	@ApiOperation(value = "获取健康教育(使用主题)", notes = "{'theme':'健康教育活动主题','userName':'登录医生用户名','pageIndex':'页码(int)','pageSize':'页面大小(int)'}")
	public String getEduByTheme(@RequestParam String theme,@RequestParam String userName,@RequestParam Integer pageSize, @RequestParam Integer pageIndex){
		theme="%"+theme+"%";
		String teamId=docUserService.getTeamIdbyUsername(userName);
		HealthEduVo eduVo = new HealthEduVo();
		eduVo.setTheme(theme);
		eduVo.setTeamId(teamId);
		int count=eduService.getCountByTheme(eduVo);
		Page<HealthEduVo> page=new Page<HealthEduVo>(pageIndex, pageSize, count);
		eduVo.setPage(page);
		List<HealthEduVo> list=eduService.getEduByTheme(eduVo);
		return JsonUtils.getPageJsonWithTotal(count, pageSize, list);
		
	}
	@RequestMapping(value = "/deleteEduById", method = RequestMethod.POST)
	@ApiOperation(value = "删除健康教育(使用Id)", notes = "{'id':'健康教育id(int)'}")
	public String deleteEduById(@RequestParam Integer id){
		int flag=eduService.deleteEduById(id);
		if(flag>0){
			return JsonUtils.getJsonFormat(BaseJsonVo.success(null));
		}else{
			return JsonUtils.getJsonFormat(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
	}
	
	@RequestMapping(value = "/getTeamId", method = RequestMethod.GET)
	@ApiOperation(value = "获取团队id(新建及修改时需要)", notes = "{'userName':'登陆医生用户名'}")
	public String getTeamId(@RequestParam String userName){
		String teamId=docUserService.getTeamIdbyUsername(userName);
		return JsonUtils.getJson(BaseJsonVo.success(teamId));
		
	}
	
	@RequestMapping(value = "/saveNewEdu", method = RequestMethod.POST)
	@ApiOperation(value = "新增健康教育", notes = "{'organizeTime':'活动时间(毫秒级时间戳)','organizer':'组织者',"
			+ "'organizeWay':'活动方式','address':'活动地址','theme':'活动主题','themeDay':'主题日','crowdType':'人员类别',"
			+ "'crowdNum':'健康教育人数','datumType':'资料发放类别','datumNum':'资料发放数量','activityInfo':'活动内容',"
			+ "'summary':'总结评价','cognitive':'认知评价','teamId':'团id','updateBy':'修改人(创建或修改)'}")
	public String saveNewEdu(@RequestBody String params){
		
		HealthEduEntity eduEntity=JsonUtils.getObject(params, HealthEduEntity.class);
		eduEntity.setCreateBy(eduEntity.getUpdateBy());
		eduEntity.setCreateTime(new Date());
		eduEntity.setUpdateTime(new Date());;
		int flag=eduService.saveNewEdu(eduEntity);
		if(flag>0){
			return JsonUtils.getJsonFormat(BaseJsonVo.success(null));
		}else{
			return JsonUtils.getJsonFormat(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
	}
	
	@RequestMapping(value = "/updateEdu", method = RequestMethod.POST)
	@ApiOperation(value = "修改健康教育(使用Id)", notes = "{'id':'健康教育id','organizeTime':'活动时间(date,改为毫秒级时间戳)','organizer':'组织者',"
			+ "'organizeWay':'活动方式','address':'活动地址','theme':'活动主题','themeDay':'主题日','crowdType':'人员类别',"
			+ "'crowdNum':'健康教育人数','datumType':'资料发放类别','datumNum':'资料发放数量','activityInfo':'活动内容',"
			+ "'summary':'总结评价','cognitive':'认知评价','teamId':'团id','updateBy':'修改人'}")
	public String updateEdu(@RequestBody String params){
		HealthEduEntity eduEntity=JsonUtils.getObject(params, HealthEduEntity.class);
		eduEntity.setUpdateTime(new Date());
		int flag=eduService.updateEdu(eduEntity);
		if(flag>0){
			return JsonUtils.getJsonFormat(BaseJsonVo.success(null));
		}else{
			return JsonUtils.getJsonFormat(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
	}

}
