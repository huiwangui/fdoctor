package com.boco.modules.fdoc.api;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
import com.boco.common.web.BaseController;
import com.boco.modules.fdoc.service.DeptService;
import com.boco.modules.fdoc.vo.DepartmentVo;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 科室 api
 * 
 * @author sufj
 *
 */
@RestController
@RequestMapping(value = "/dept", produces = "text/json;charset=utf-8")
public class DeptApi extends BaseController {
	
	@Resource
	DeptService deptService;

	@RequestMapping(value = "/getDeptList", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有科室", notes = "{'id':'医院id'}", response = DepartmentVo.class)
	public String getDeptList(@RequestParam int id){
		
		return JsonUtils.getJson(BaseJsonVo.success(deptService.getDeptList(id)));
	}
	

	@RequestMapping(value = "/getDeptDetail", method = RequestMethod.GET)
	@ApiOperation(value = "获取科室详情", notes = "{'id':'科室id'}", response = BaseJsonVo.class)
	public String getDeptDetail(@RequestParam int id){
		return JsonUtils.getJson(BaseJsonVo.success(deptService.getDeptDetail(id)));
	}
	
	@RequestMapping(value = "/getDeptByName", method = RequestMethod.GET)
	@ApiOperation(value = "通过名称查询科室", notes = "{'name':'科室名称'}", response = BaseJsonVo.class)
	public String getDeptByName(@RequestParam String name){
		BaseJsonVo vo = new BaseJsonVo();
		/**
		 * 
		服务器编码和本地不一致，上传服务器版本去掉iso8859-1转码
		String newName = null;
		try {
			newName=new String(name.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 */
		return JsonUtils.getJson(BaseJsonVo.success(deptService.getDeptByName(name)));
	}
	
	@RequestMapping(value = "/getDefaultDepts", method = RequestMethod.GET)
	@ApiOperation(value = "根据城市获取科室列表", notes = "{'city':'城市'}", response = BaseJsonVo.class)
	public String getDefaultDepts(@RequestParam String city){
		/**
		//服务器编码和本地不一致，上传服务器版本去掉iso8859-1转码
		String doc = null;
		try {
			doc = new String(city.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		*/
		return JsonUtils.getJson(BaseJsonVo.success(deptService.getDefaultDepts(city)));
	}
}
