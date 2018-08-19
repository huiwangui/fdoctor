package com.boco.modules.fdoc.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.constants.RegionCodeConstants;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.model.SysRegionEntity;
import com.boco.modules.fdoc.service.HospitalService;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.service.SysRegionService;
import com.boco.modules.fdoc.vo.HospitalVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SignVo;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 医院 api
 * 
 * @author sufj
 *
 */
@RestController
@RequestMapping(value = "/hospital", produces = "text/json;charset=UTF-8")
public class HospitalApi extends BaseController {
	
	@Resource
	HospitalService hospitalService;
	@Resource
	PersonInformationService personService ;
	@Resource
	SysRegionService regionService;
	
	@RequestMapping(value = "/getOrgList", method = RequestMethod.GET)
	@ApiOperation(value = "获取该区域下的机构列表，包含团队个数（医生端）", notes = "{'personId':'居民ID','orgName':'机构名称查询条件'}")
	public String getOrgList(@RequestParam String personId, @RequestParam String orgName) {
		// 根据居民ID获取居民信息
		PersonInformationVo person = personService.getPersonDetailByPersonId(personId);
		// 封装查询条件
		HospitalVo vo = new HospitalVo();
		vo.setOrgName(orgName);
		// 获取居民当前所属区划
		List<SysRegionEntity> regionList = new ArrayList<SysRegionEntity>();
		regionList.add(regionService.getRegionByCode(person.getRegionCode()));
		// 调用递归方法获取上级区划，到乡级为止
		regionList = regionService.getAllParentRegion(person.getRegionCode(), regionList, RegionCodeConstants.REGION_CODE_LENGTH_COUNTRY);
		StringBuffer buffer = new StringBuffer();
		for (SysRegionEntity sysRegionEntity : regionList) {
			buffer.append(sysRegionEntity.getRegionCode() + ",");
		}
		vo.setRegions(buffer.substring(0, buffer.length() - 1));
		// 查询结果并返回
		List<HospitalVo> hospitalList = hospitalService.getHospitalListWithTeamNum(vo);
		return JsonUtils.getJson(BaseJsonVo.success(hospitalList));
	}
	
	@RequestMapping(value = "/getTeamInHospital", method = RequestMethod.GET)
	@ApiOperation(value = "获取该区域下的机构列表，包含团队成员个数（医生端）", notes = "{'orgId':'机构ID'}")
	public String getTeamInHospital(@RequestParam String orgId) {
		return JsonUtils.getJson(BaseJsonVo.success(hospitalService.getTeamInHospital(orgId)));
	}
}
