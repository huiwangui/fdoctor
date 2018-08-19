package com.boco.modules.fdoc.api.referral;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.exception.CommonException;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.persistence.Page;
import com.boco.common.utils.FTPUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.ObjectUtils;
import com.boco.common.utils.StringUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.ReferralHospitalizationEntity;
import com.boco.modules.fdoc.service.DocUserService;
import com.boco.modules.fdoc.service.ReferralHospitalizationService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.ReferralHospitalizationVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 双向转诊-住院转诊API
 * @author q
 *
 */
@RestController
@RequestMapping(value = "/referralHospitalization", produces = "text/json;charset=UTF-8")
public class ReferralHospitalizationApi extends BaseController{
	@Resource
	ReferralHospitalizationService referralHospitalizationService;
	@Resource
	DocUserService docUserService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editReferralHospitalizationReply", method = RequestMethod.POST)
	@ApiOperation(value = "新增、修改住院转诊申请（医生端）")
	public String editReferralOutpatientReply(@RequestBody String param) throws Exception{
		//解析参数json
		Map<String, Object> paramMap = JsonUtils.getObjectJsonMap(param);
		String userName = (String) paramMap.get("userName");
		
		Map<String, Object> bodyMap = (Map<String, Object>) paramMap.get("body");
		
		//处理实际转诊日期和出生日期
		Long realReferralDateStamp = null;
		if (bodyMap.get("realReferralDate") != null) {
			realReferralDateStamp = (Long) bodyMap.get("realReferralDate");
			bodyMap.remove("realReferralDate");
		}
		Long dateOfBirthStamp = null;
		if (bodyMap.get("dateOfBirth") != null) {
			dateOfBirthStamp = (Long) bodyMap.get("dateOfBirth");
			bodyMap.remove("dateOfBirth");
		}
		
		//将bodyMap转为ReferralHospitalizationEntity对象
		ReferralHospitalizationEntity entity = (ReferralHospitalizationEntity) ObjectUtils.mapToObject(bodyMap, ReferralHospitalizationEntity.class);
		
		if (realReferralDateStamp != null) {
			entity.setRealReferralDate(new Date(realReferralDateStamp));
		}
		if (dateOfBirthStamp != null) {
			entity.setDateOfBirth(new Date(dateOfBirthStamp));
		}
		
		//根据id判断新增还是修改
		if (bodyMap.get("id") != null) {
			entity.setId((int)bodyMap.get("id"));			
			referralHospitalizationService.updateReferralHospitalizationApply(entity);
		}else {
			entity.setCreator(userName);
			//新增记录		
			try {
				referralHospitalizationService.insertReferralHospitalizationApply(entity);
			} catch (CommonException e) {
				e.printStackTrace();
				return JsonUtils.getJson(BaseJsonVo.empty(
						ApiStatusEnum.INTERFACE_ERROR.getKey(),
						ApiStatusEnum.INTERFACE_ERROR.getValue()));
			}
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
	
	
	@RequestMapping(value = "/getReferralHospitalizationList", method = RequestMethod.GET)
	@ApiOperation(value = "住院转诊申请（医生端）", notes = "{\"param\":\"身份证或姓名\"}}", response = BaseJsonVo.class)
	public String getReferralHospitalizationList(@RequestParam String teamId, @RequestParam String param, @RequestParam Integer pageSize, @RequestParam Integer pageNo) throws Exception{
		//准备查询参数
		ReferralHospitalizationVo vo = new ReferralHospitalizationVo();
		vo.setDocTeamId(teamId);
		
		//判断参数是身份证还是姓名
		if (MatcherUtiles.idCardMach(param)) {
			vo.setIdCard(param);
		}else {
			vo.setPersonName(param);
		}		
		//获取总数		 
		Integer count = referralHospitalizationService.getReferralHospitalizationCountBySign(vo);		
		//封装分页参数
		Page<ReferralHospitalizationVo> page = new Page<ReferralHospitalizationVo>(pageNo, pageSize, count);
		vo.setPage(page);	
		//获取列表		
		List<ReferralHospitalizationVo> list = referralHospitalizationService.getReferralHospitalizationListBySign(vo);
		return JsonUtils.getPageJson(count, pageSize, list);
	}
	
	@RequestMapping(value = "/getReferralHospitalizationInfo", method = RequestMethod.GET)
	@ApiOperation(value = "查看住院转诊申请记录（医生端）", notes = "{ \"id\":\"主键id\"}}", response = BaseJsonVo.class)
	public String getReferralHospitalizationInfo(@RequestParam Integer id) throws Exception{	
		ReferralHospitalizationEntity referralHospitalizationInfo = referralHospitalizationService.getReferralHospitalizationInfo(id);
		return JsonUtils.getJson(BaseJsonVo.success(referralHospitalizationInfo));
	}
	
	@RequestMapping(value = "/uploadImgs", method = RequestMethod.POST)
	@ApiOperation(value = "上传转诊指征（医生端）", notes = "{ \"imgs\":\"转诊指征(可以上传多个)\"}}", response = BaseJsonVo.class)
	public String uploadImgs(@RequestParam("imgs") CommonsMultipartFile imgs[], HttpServletRequest request) {
		StringBuilder addStrc = new StringBuilder(); 
		/**
		 * 上传图片开始
		 */
		try {
			if(imgs.length>0){
				for (int i = 0; i < imgs.length; i++) {
					CommonsMultipartFile img =imgs[i];
					if (img != null && img.getOriginalFilename() != null && StringUtils.isNotEmpty(img.getOriginalFilename())) {					
						InputStream is = img.getInputStream();
						
						byte[] bs = new byte[1024];  
				        int len;
						// 生成jpeg图片
						String headSuffix = StringUtils.getSuffix(img.getOriginalFilename()); // 获取后缀名
						String signImgHeadName = UUID.randomUUID().toString()
								.substring(0, 8)
								+ "." + headSuffix; // 重命名为8位随机数
						
						//复制文件到指定路径 
						File saveFile = new File((request.getContextPath() + "/upload/referral/" + signImgHeadName).substring(9)); 
						FileUtils.copyInputStreamToFile(is, saveFile); 
				        //上传文件到服务器 
						FTPUtils.upload(saveFile, "/upload/referral/"); 
						addStrc.append((request.getContextPath() + "/upload/referral/"+ signImgHeadName).substring(9)).append(",");
				        
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		addStrc.deleteCharAt(addStrc.length()-1);		 
		return JsonUtils.getJson(BaseJsonVo.success(addStrc.toString()));		
	}
	
}
