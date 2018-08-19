package com.boco.modules.fdoc.api;

import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.FamilyContactInfoEntity;
import com.boco.modules.fdoc.service.FamilyContactService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.vo.FamilyContactInfoVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Description 家庭联系方式接口
 * @author lzz
 * @date 2017年7月12日 下午3:15:43
 */
@RestController
@RequestMapping(value = "/familyContact", produces = "text/json;charset=utf-8")
public class FamilyContactApi {
	
	@Resource
	FamilyContactService fService;
	@Resource
	PersonInformationService personService;
	
	@RequestMapping(value="/addContact" ,method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="新增家庭联系信息",notes="{'idCard':'签约人的身份证号','phoneNumber':'手机号(必填)','qqNumber':'qq号','wechatNumber':'微信号'}",response=BaseJsonVo.class)
	public String addContact(@RequestBody String paramBody){
		
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramBody);
		String idCard=pMap.get("idCard").toString();
		String phoneNumber =pMap.get("phoneNumber").toString();
		String qqNumber=pMap.get("qqNumber").toString();
		String wechatNumber=pMap.get("wechatNumber").toString();
		
		String famiyId=personService.getFamilyId(idCard);
		
		FamilyContactInfoEntity fEntity=new FamilyContactInfoEntity();
		fEntity=fService.getContactInfo(famiyId);
		if(fEntity!=null){
			FamilyContactInfoVo fVo=new FamilyContactInfoVo();
			fVo.setIdCard(idCard);
			if(phoneNumber!=null){
				fVo.setPhoneNumber(phoneNumber);
			}
			if(qqNumber!=null){
				fVo.setQqNumber(qqNumber);
			}
			if(wechatNumber!=null){
				fVo.setWechatNumber(wechatNumber);
			}
			int flag=fService.updateFamilyContact(fVo);
			if(flag>0){
				return JsonUtils.getJsonFormat(BaseJsonVo.success(null));
			}else{
				return JsonUtils.getJsonFormat(BaseJsonVo.empty(ApiStatusEnum.FAMILY_SAVE_ERROR.getKey(), ApiStatusEnum.FAMILY_SAVE_ERROR.getValue()));
			}
		}
		
		FamilyContactInfoVo fVo2=new FamilyContactInfoVo();
		fVo2.setIdCard(idCard);
		if(phoneNumber!=null){
			fVo2.setPhoneNumber(phoneNumber);
		}
		if(qqNumber!=null){
			fVo2.setQqNumber(qqNumber);
		}
		if(wechatNumber!=null){
			fVo2.setWechatNumber(wechatNumber);
		}
		
		int flag=fService.addFamilyContact(fVo2);
		if(flag>0){
			return JsonUtils.getJsonFormat(BaseJsonVo.success(null));
		}else{
			return JsonUtils.getJsonFormat(BaseJsonVo.empty(ApiStatusEnum.FAMILY_SAVE_ERROR.getKey(), ApiStatusEnum.FAMILY_SAVE_ERROR.getValue()));
		}
		
	}
	
	@RequestMapping(value="/updateContact" ,method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value="修改家庭联系信息,其中值如不修改需要传原值，清空传空或不传",notes="{'idcard':'签约人的身份证号','phoneNumber':'手机号','qqNumber':'qq号','wechatNumber':'微信号'}",response=BaseJsonVo.class)
	public String updateContact(@RequestBody String paramBody){
				
		Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramBody);
		String idCard=pMap.get("idCard").toString();
		String phoneNumber =pMap.get("phoneNumber").toString();
		String qqNumber=pMap.get("qqNumber").toString();
		String wechatNumber=pMap.get("wechatNumber").toString();
		
		FamilyContactInfoVo fVo=new FamilyContactInfoVo();
		fVo.setIdCard(idCard);
		if(phoneNumber!=null){
			fVo.setPhoneNumber(phoneNumber);
		}
		if(qqNumber!=null){
			fVo.setQqNumber(qqNumber);
		}
		if(wechatNumber!=null){
			fVo.setWechatNumber(wechatNumber);
		}
		/*if(phoneNumber==null && qqNumber==null && wechatNumber==null){
			return JsonUtils.getJsonFormat(BaseJsonVo.empty(ApiStatusEnum.DATA_EMPTY.getKey(), ApiStatusEnum.DATA_EMPTY.getValue()));
		}*/
		
		int flag=fService.updateFamilyContact(fVo);
		if(flag>0){
			return JsonUtils.getJsonFormat(BaseJsonVo.success(null));
		}else{
			return JsonUtils.getJsonFormat(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
		
	}
	
	@RequestMapping(value="/getContactInfo" ,method=RequestMethod.GET)
	@ApiOperation(value="获取家庭联系信息",notes="{'familyId':'签约人的身份证号'}",response=BaseJsonVo.class)
	public String getContactInfo(@RequestParam String familyId){
		
		//Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramBody);
		//String familyId=pMap.get("familyId").toString();
		
		FamilyContactInfoEntity fEntity=new FamilyContactInfoEntity();
		
		fEntity=fService.getContactInfo(familyId);
		if(fEntity==null){
			return JsonUtils.getJson(BaseJsonVo.empty(ApiStatusEnum.FAMILYCONTACT_NOTEXISTS.getKey(), ApiStatusEnum.FAMILYCONTACT_NOTEXISTS.getValue()));
		}
		
		return JsonUtils.getJson(BaseJsonVo.success(fEntity));
		
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteContactInfo" ,method=RequestMethod.POST)
	@ApiOperation(value="删除家庭联系信息",notes="{'familyId':'签约人的身份证号'}",response=BaseJsonVo.class)
	public String deleteContactInfo(@RequestParam String familyId){
		
		//Map<String, Object> pMap=JsonUtils.getObjectJsonMap(paramBody);
		//String familyId=pMap.get("familyId").toString();
		
		int flag=fService.deleteContactInfo(familyId);
		
		if(flag>0){
			return JsonUtils.getJsonFormat(BaseJsonVo.success(null));
		}else{
			return JsonUtils.getJsonFormat(BaseJsonVo.empty(ApiStatusEnum.FAIL.getKey(), ApiStatusEnum.FAIL.getValue()));
		}
	}
	

}
