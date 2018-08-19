package com.boco.modules.fdoc.api;
import java.util.List;

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
import com.boco.modules.fdoc.service.FdoctorUPplanService;
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.UpPlanVo;
import com.wordnik.swagger.annotations.ApiOperation;
/**
 * 医生修改 添加随访计划API
 * @author Jomo
 * 
 */
@RestController
@RequestMapping(value = "/FdoctorUPplan", produces = "text/json;charset=UTF-8")
public class FdoctorUPplanApi {
	
	@Resource 
	FdoctorUPplanService  fdoctorUPplanService;
	
	
	/**
	 * 修改计划
	 * @author Jomo
	 * @param upPlanVoBody
	 * @return 
	 * **/
	@RequestMapping(value = "/updatePlan", method = RequestMethod.POST)
	@ApiOperation(value = "修改随访计划(医生端)", notes = "{\"userName\":\"登录医生用户名\",\"id\":\"随访计划id\",\"upDate\":\"计划时间\",\"taskLevel\":\"随访级别 1,2,3\",\"upWay\":\"随访方式\",\"taskTarget\":\"随访目标\",\"templateName\":\"模板名字\",\"diseaseName\":\"疾病字典ID\"}", response = BaseJsonVo.class)
	public String updatePlan(@RequestBody String upPlanVoBody) {
		UpPlanVo upPlanVo=JsonUtils.getObject(upPlanVoBody, UpPlanVo.class);
	    String userName=upPlanVo.getUserName();
		/**判断是否登录**/
		if (JedisUtils.get(userName + "userName") == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT_LOGIN.getKey(), ApiStatusEnum.USER_NOT_LOGIN.getValue())); 
		}
		
		return fdoctorUPplanService.updatePlan(upPlanVo);
		
		
	} 

	/**
	 * 添加计划
	 * @author Jomo
	 * @param upPlanVoBody
	 * @return 
	 * **/
	@RequestMapping(value = "/savePlan", method = RequestMethod.POST)
	@ApiOperation(value = "添加随访计划(医生端)", notes = "{\"userName\":\"登录医生用户名\",\"healthFileCode\":\"健康档案编号\",\"upDate\":\"计划时间\",\"taskLevel\":\"随访级别 1,2,3\",\"upWay\":\"随访方式\",\"taskTarget\":\"随访目标\",\"templateName\":数字类型1,2,3,\"diseaseName\":\"疾病名称\"}", response = BaseJsonVo.class)
	public String savePlan(@RequestBody String upPlanVoBody) {
		UpPlanVo upPlanVo=JsonUtils.getObject(upPlanVoBody, UpPlanVo.class);
	    String userName=upPlanVo.getUserName();
		/**判断是否登录**/
		if (JedisUtils.get(userName + "userName") == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT_LOGIN.getKey(), ApiStatusEnum.USER_NOT_LOGIN.getValue())); 
		}
		
		return fdoctorUPplanService.savePlan(upPlanVo);
		
		
	} 
    
	/**
	 * 获取未完成的随访计划列表
	 * 
	 * @author jomo
	 * @param userName
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getListWithNonexecution", method = RequestMethod.GET)
	@ApiOperation(value = "获取未完成的随访计划列表（医生端） 已做分页处理", notes = "{'userName':'登录医生用户名'}", response = BaseJsonVo.class)
	public String getFinishSignedListByHealthFileCode(@RequestParam String userName,@RequestParam Integer pageSize,@RequestParam Integer pageNo) {     
		/**判断是否登录**/
		if (JedisUtils.get(userName + "userName") == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT_LOGIN.getKey(), ApiStatusEnum.USER_NOT_LOGIN.getValue())); 
		}
		
		UpPlanVo vo = new UpPlanVo();
		List<String> regionList = JedisUtils.getList(userName + "regionList");
		String docId = JedisUtils.get(userName + "id");
		vo.setDocId(docId);
	    vo.setRegionList(regionList);
	    
	    UpPlanVo vo2 = new UpPlanVo();
		vo2.setDocId(docId);
	    vo2.setRegionList(regionList);
	    List<UpPlanVo> list2 = fdoctorUPplanService.getListWithNonexecution(vo2);
	    
	    if (pageNo != 0 && pageSize != 0) {
	    Page<UpPlanVo> page = new Page<UpPlanVo>(pageNo,pageSize, list2.size());
		vo.setPage(page);
	    }
		List<UpPlanVo> list = fdoctorUPplanService.getListWithNonexecution(vo);
		
		if(list.size()>0&&list!=null){
			return JsonUtils.getPageJson(list2.size(), pageSize, list);    
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT.getKey(), ApiStatusEnum.USER_NOT.getValue()));
		}
		      
	}
	
	
	/**
	 * 获取未完成的随访计划最近一条记录
	 * 
	 * @author jomo
	 * @param userName
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getMaxDateUpdateNonexecution", method = RequestMethod.GET)
	@ApiOperation(value = "获取未完成的随访计划最近一条记录（医生端）", notes = "{'userName':'登录医生用户名'}", response = BaseJsonVo.class)
	public String getMaxDateUpdateNonexecution(@RequestParam String userName) {     
		/**判断是否登录**/
		if (JedisUtils.get(userName + "userName") == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT_LOGIN.getKey(), ApiStatusEnum.USER_NOT_LOGIN.getValue())); 
		}
	    
		UpPlanVo vo = new UpPlanVo();
		List<String> regionList = JedisUtils.getList(userName + "regionList");
		String docId = JedisUtils.get(userName + "id");
		vo.setDocId(docId);
		vo.setRegionList(regionList);
		
		UpPlanVo upPlanVo= fdoctorUPplanService.getMaxDateUpdateNonexecution(vo);
		
			return JsonUtils.getJson(BaseJsonVo.success(upPlanVo)); 
		
		      
	}
	
	
	/**
	 *  处理未完成随访计划
	 * 
	 * @author jomo
	 * @param userName
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/lookover", method = RequestMethod.GET)
	@ApiOperation(value = "处理未完成随访计划（医生端）", notes = "{'userName':'登录医生用户名' ,'id':'随访id'}", response = BaseJsonVo.class)
	public String lookover(@RequestParam String userName,@RequestParam int id) {     
		/**判断是否登录**/
		if (JedisUtils.get(userName + "userName") == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT_LOGIN.getKey(), ApiStatusEnum.USER_NOT_LOGIN.getValue())); 
		}
	
		int i= fdoctorUPplanService.lookoverPlan(id);
		if(i==1){
			return JsonUtils.getJson(BaseJsonVo.success(i)); 
		}else{
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
		      
	}
	
	
	
	/**
	 * 获取 处理或者没处理 已签约未完成随访计划列表 
	 * 
	 * @author jomo
	 * @param userName
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getListWithNonexecutionIsLook", method = RequestMethod.GET)
	@ApiOperation(value = "获取 处理或者没处理 已签约未完成随访计划列表 （医生端） 已做分页处理", notes = "{'userName':'登录医生用户名','lookover':0,1}", response = BaseJsonVo.class)
	public String getListWithNonexecutionIsLook(@RequestParam String userName,@RequestParam int lookover,@RequestParam Integer pageSize,@RequestParam Integer pageNo) {     
		/**判断是否登录**/
		if (JedisUtils.get(userName + "userName") == null) {
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.USER_NOT_LOGIN.getKey(), ApiStatusEnum.USER_NOT_LOGIN.getValue())); 
		}
	 
		UpPlanVo vo = new UpPlanVo();
		List<String> regionList = JedisUtils.getList(userName + "regionList");
		String docId = JedisUtils.get(userName + "id");
		vo.setDocId(docId);
		vo.setRegionList(regionList);
		vo.setLookover(lookover);
		Integer count=fdoctorUPplanService.getCountListWithNonexecutionIsLook(vo);
		if (pageNo != 0 && pageSize != 0) {
		Page<UpPlanVo> page = new Page<UpPlanVo>(pageNo,pageSize, count);
		vo.setPage(page);
		}
		List<UpPlanVo> upPlanVo= fdoctorUPplanService.getListWithNonexecutionIsLook(vo);
//		UpPlanVo vo2 = new UpPlanVo();
//		vo2.setDocId(docId);
//		vo2.setRegionList(regionList);
//		vo2.setLookover(lookover);
//		List<UpPlanVo> upPlanVo2= fdoctorUPplanService.getListWithNonexecutionIsLook(vo2);
		
		
		
		//return JsonUtils.getPageJson(upPlanVo2.size(), pageSize, upPlanVo);    
		return JsonUtils.getPageJson(count, pageSize, upPlanVo);
	}
}
