package com.boco.modules.fdoc.api;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;






import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.im.IMUtils;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.StringUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.model.UserRealtion;
import com.boco.modules.fdoc.service.UserRelationService;
import com.boco.modules.fdoc.service.UserService;
import com.boco.modules.fdoc.service.UserServiceChange;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.UserRelationVo;
import com.boco.modules.fdoc.vo.UserVo;
import com.wordnik.swagger.annotations.ApiOperation;


/**
 * 用户 api
 * 以户签约更改为以个人签约 
 * @author q
 *
 */
@RestController
@RequestMapping(value = "/userchange", produces = "text/json;charset=UTF-8")
public class UserChangeApi extends BaseController {

	@Resource
	UserServiceChange userServiceChange;
	@Resource
	UserService userService;
    @Resource
    UserRelationService relationService;
    
	
	
	/**
	 * 修改为
	 * 没找到居民信息   如果没有档案则新建 
	 * @param userBody 
	 * @return  
	 *
	 */
	@RequestMapping(value = "/authenChange", method = RequestMethod.POST)
	@ApiOperation(value = "实名认证（居民端）", notes = "{'uid':'用户uid','idCard':'身份证号','personName':'姓名'}")
 	public String authen(@RequestBody String userBody) {
		// 1- 封装对象
		// 1- 封装对象
		UserVo vo = JsonUtils.getObject(userBody, UserVo.class);
	    String uid=vo.getUid();
		//身份证号验证
		if(!MatcherUtiles.idCardMach(vo.getIdCard())){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.IDCARD_OUTLAW.getKey(), ApiStatusEnum.IDCARD_OUTLAW.getValue()));
		}
		UserEntity entity=userService.getUserByUid(uid);
		if(entity!=null){
			vo.setPhoneNumber(entity.getMobile());
			vo.setSex(entity.getSex());
		}else{
			return JsonUtils.getJson(BaseJsonVo.error("uid不存在 ，请先注册！"));
		}
		// 2- 调用 service实名认证
		String authenResult = userServiceChange.authen(vo);
		if (BusinessConstants.SUCCESS.equals(authenResult)) {
			UserVo userDetail = userService.getUserDetail(vo.getUid());
			if(null!=userDetail){
				// 修改IM账号
				vo.setNickname(userDetail.getPersonName());
				vo.setImg(userDetail.getImg());
				vo.setUid(userDetail.getUid());
				IMUtils.changeUser(vo);
			}
			// 返回修改后的完整对象信息
			return JsonUtils.getJson(BaseJsonVo.success(userDetail));
		}
		if (BusinessConstants.FAIL.equals(authenResult)) {
			// 
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
		if (BusinessConstants.AUTHEN_RESULT_REPEAT.equals(authenResult)) {
			// 该居民已绑定了其他账号，无法重复绑定
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.AUTHEN_REPEAT.getKey(), ApiStatusEnum.AUTHEN_REPEAT.getValue()));
		}
		if(authenResult.contains("NOTSAMENAME")){//姓名不匹配
			String name=authenResult.split("-")[1];
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.AUTHEN_NOTSAMENAME.getKey(), ApiStatusEnum.AUTHEN_NOTSAMENAME.getValue()+"："+name));
		}
		return JsonUtils.getJson(BaseJsonVo.success(authenResult));
		
		
	}
	
	/**
	 * 查询所有联系人
	 * @param uid
	 * @return
	 *
	 */
	@RequestMapping(value = "/allRelations", method = RequestMethod.GET)
	@ApiOperation(value = "查询所有联系人（居民端）返回字段status说明： 1:解约审核中  2:已签约  3:未签约 ", notes = "{'uid':'用户uid'}")
	public String allRelations(@RequestParam(required=true) String uid) {
		
		List<PersonInformationVo> list=relationService.selectAllperson(uid);
		return JsonUtils.getJson(BaseJsonVo.success(list));
	}
	
	/**
	 * 新增联系人
	 * @param uid
	 * @param relation
	 * @param idCard
	 * @param personName
	 * @param phoneNumber
	 * @param qq
	 * @param wechat
	 * @return
	 *
	 */
	@RequestMapping(value = "/saveRelations", method = RequestMethod.POST)
	@ApiOperation(value = "新增联系人（居民端）", notes = "{'userUid':'用户uid','relation':'关系数 ','person':{'idCard':'身份证号','phoneNumber':'手机号','personName':'姓名','sex':1,'qq':'qq号','wechat':'微信联系方式'}}")
	public String saveRelations(@RequestBody String bodyParam){
		UserRelationVo vo=	JsonUtils.getObject(bodyParam, UserRelationVo.class);
		if(vo==null){
			return JsonUtils.getJson(BaseJsonVo.error(ApiStatusEnum.DATA_FORMAT_ERROR));
		}
		String  userUid=vo.getUserUid();
		if(StringUtils.isEmpty(userUid)){
			return JsonUtils.getJson(BaseJsonVo.error("userUid必填！"));
		}
		if(vo.getPerson()==null){
			return JsonUtils.getJson(BaseJsonVo.error("字段person不能为空！"));
		}
		String  idCard=vo.getPerson().getIdCard();
		String  phoneNumber=vo.getPerson().getPhoneNumber();
		if(StringUtils.isEmpty(idCard)){
			return JsonUtils.getJson(BaseJsonVo.error("身份证不能为空！"));
		}
		if(!MatcherUtiles.mobileMach(phoneNumber)){
			return JsonUtils.getJson(BaseJsonVo.error("手机号码不能为空！"));
		}
		if(!MatcherUtiles.idCardMach(vo.getPerson().getIdCard())){
			return JsonUtils.getJson(BaseJsonVo.error("身份证不合法！"));//身份证不合法
		}
		if(!MatcherUtiles.mobileMach(vo.getPerson().getPhoneNumber())){
			return JsonUtils.getJson(BaseJsonVo.error("手机号码不合法！"));//电话号码错误
		}
		UserRealtion record=new UserRealtion();
		record.setUserUid(userUid);
		record.setRelation(vo.getRelation());
		
		PersonInformationVo pvo=new PersonInformationVo();
		pvo.setPersonName(vo.getPerson().getPersonName());
		pvo.setIdCard(idCard);
		pvo.setQq(vo.getPerson().getQq());
		pvo.setPhoneNumber(phoneNumber);
		pvo.setWechat(vo.getPerson().getWechat());
		String result=relationService.insert(record, pvo);
		if("SUCCESS".equals(result)){
			return JsonUtils.getJson(BaseJsonVo.success(ApiStatusEnum.SUCCESS));
		}else if("NOTSAMENAME".equals(result)){
			return JsonUtils.getJson(BaseJsonVo.error("身份证号一样但是姓名不同！"));
		}else{
			 return JsonUtils.getJson(BaseJsonVo.error(ApiStatusEnum.FAIL));
		}
		
	  
	}
	
	/**
	 * 删除联系人
	 * @param id
	 * @return
	 *
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除联系人（居民端）", notes = "{'id':'关系表主键id'}")
	public String deletRelation(@PathVariable("id") Integer id){
		int result=	relationService.deleteByPrimaryKey(id);
		if(result>0){
			return JsonUtils.getJson(BaseJsonVo.success(ApiStatusEnum.SUCCESS));
		}
		return JsonUtils.getJson(BaseJsonVo.error(ApiStatusEnum.FAIL));
	}
	
	/**
	 * 更新联系人
	 * @param id
	 * @param uid
	 * @param relation
	 * @param person
	 * @return
	 *
	 */
	@RequestMapping(value = "/updateRelation", method = RequestMethod.POST)
	@ApiOperation(value = "更新联系人（居民端）", notes = "{'id':'关系表主键id','relation':'关系数 ','person'：{'personId':'个人id','idCard':'身份证号','phoneNumber':'手机号','personName':'姓名','qq':'qq号','wechat':'微信联系方式','sex':1}}")
	public String updateRelation(@RequestBody String bodyParam) throws Exception{
		
		UserRelationVo vo=	JsonUtils.getObject(bodyParam, UserRelationVo.class);
		if(vo==null){
			return JsonUtils.getJson(BaseJsonVo.error(ApiStatusEnum.DATA_FORMAT_ERROR));
		}
		int result=relationService.updateRelation(vo.getPerson(), vo);
		
		if(result>0){
			return JsonUtils.getJson(BaseJsonVo.success(ApiStatusEnum.SUCCESS));
		}
		return JsonUtils.getJson(BaseJsonVo.error());
	}
	
	
		
}	