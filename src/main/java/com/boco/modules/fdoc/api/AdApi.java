package com.boco.modules.fdoc.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.service.AdService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/ad", produces = "text/json;charset=UTF-8")
public class AdApi {

	/**
	 *  首页广告信息
	 * @return
	 */
	
	@Resource
	AdService adService;
	
	@RequestMapping(value = "/getAds", method = RequestMethod.GET)
	@ApiOperation(value = "首页广告信息", notes = "{}", response = BaseJsonVo.class)
	public String getAds() {
		BaseJsonVo vo = new BaseJsonVo();
		BeanUtils.copyProperties(adService.getAds(), vo);
		return JsonUtils.getJson(BaseJsonVo.success(vo));
	}
	
	@RequestMapping(value = "/testUpload", method = RequestMethod.POST, produces = "multipart/form-data")
	@ApiOperation(value = "修改医生用户信息（医生端）", notes = "{'userName':'医生登录用户名','nickName':'昵称''introduction':'简介','img':'图片file对象'}", response = BaseJsonVo.class)
	public String updateUser(@RequestParam String userName,@RequestParam String nickName,@RequestParam String introduction,@RequestParam String strName,@RequestParam Map<String, Object> map, HttpServletRequest request) {
		System.out.println(userName);
		System.out.println(nickName);
		System.out.println(introduction);
		System.out.println(strName);
		System.out.println(map);
		return JsonUtils.getJson(map);
	}
}
