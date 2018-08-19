package com.boco.modules.fdoc.web.sigrecord;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boco.common.persistence.Page;
import com.boco.common.web.ServletController;
import com.boco.modules.fdoc.model.SigRecordEntity;
import com.boco.modules.fdoc.service.SignRecordService;
import com.boco.modules.fdoc.vo.SigRecordVo;

@Controller
@RequestMapping(value = "/signRecord", produces = "text/json;charset=utf-8")
public class SignRecordApi extends ServletController{
	
	@Resource
	SignRecordService signRecordService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return new ModelAndView("/sigrecord/sigrecord");
	}
	
	//查询当前医生下的签约人员信息
	@RequestMapping(value = "/querySignInfo")
	public ModelAndView querySignInfo(HttpServletRequest request,HttpServletResponse response,SigRecordVo vo) {
		Page<SigRecordVo> page = new Page<SigRecordVo>(request,response);
		try {
			page = signRecordService.querySignInfo(page, vo);
			request.setAttribute("list", page.getList());
			return new ModelAndView("/sigrecord/sigrecord")
			.addObject("pageList",page).addObject("page", page)
			.addObject("option", vo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询当前医生下的签约居民信息失败", e);
			//返回界面，提示错误信息
			return new ModelAndView("/sigrecord/sigrecord").addObject("errorInfo", "查询当前医生下的签约居民信息失败");
		}
	}
		
	//查询当前服务包下的服务项目信息
	@RequestMapping(value = "/queryServiceDetail")
	public ModelAndView queryServiceDetail(int sigId,String packName,int resId) {
		try {
			List<SigRecordVo> list = signRecordService.queryServiceDetails(sigId);
			List<SigRecordVo> doctors = signRecordService.queryFamdoctor();
			packName = URLDecoder.decode(packName,"UTF-8");
			return new ModelAndView("/sigrecord/addrecord")
			.addObject("option", list).addObject("sigId", sigId)
			.addObject("packName",packName).addObject("doctor",doctors)
			.addObject("resId",resId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询查询当前服务包下的服务项目信息失败", e);
			//返回界面，提示错误信息
			return new ModelAndView("/sigrecord/addrecord").addObject("errorInfo", "查询当前服务包下的服务项目信息失败");
		}
	}
	
	//新增服务记录信息
	@RequestMapping(value = "/addSigReocrd")
	@ResponseBody
	public String addSigReocrd(SigRecordEntity entity) {
		String jsonData = "";
		try {
			String msg = signRecordService.saveRecord(entity);
			if (msg.startsWith("1")) {
				jsonData = "{\"success\":false,\"result\":\""+msg.substring(1)+"\"}";
			}else{
				jsonData = "{\"success\":true,\"result\":\"新增服务记录成功\"}";
			}
		} catch (Exception e) {
			logger.error("新增服务记录失败", e);
			jsonData = "{\"success\":false,\"result\":\""+e.getCause().toString().replaceAll("###", "").replaceAll("\n", "")+"\"}";
		}
		return jsonData;
	}
	
	//查询当前人员下的服务记录信息
	@RequestMapping(value = "/queryRecordInfo")
	public ModelAndView queryRecordInfo(HttpServletRequest request,HttpServletResponse response,int resId,String personName,String age,String idCard,String sex) {
		Page<SigRecordVo> page = new Page<SigRecordVo>(request,response);
		try {
			SigRecordVo vo = new SigRecordVo();
			vo.setResId(resId);
			page = signRecordService.queryRecord(page, vo);
			personName = URLDecoder.decode(personName,"UTF-8");
			if ("0".equals(sex)||"男".equals(sex)) {
				sex = "男";
			}else{
				sex = "女";
			}
			request.setAttribute("list", page.getList());
			return new ModelAndView("/sigrecord/viewrecord")
			.addObject("pageList",page).addObject("page", page)
			.addObject("personName", personName).addObject("age", age)
			.addObject("idCard", idCard).addObject("sex", sex)
			.addObject("resId", resId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询当前人员下的服务记录信息失败", e);
			//返回界面，提示错误信息
			return new ModelAndView("/sigrecord/viewrecord").addObject("errorInfo", "查询当前人员下的服务记录信息失败");
		}
	}
	
}
