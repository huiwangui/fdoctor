package com.boco.modules.fdoc.web.moneymanger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boco.common.persistence.Page;
import com.boco.common.web.ServletController;
import com.boco.modules.fdoc.model.MoneyMangerEntity;
import com.boco.modules.fdoc.service.MoneyMangerService;


@Controller
@RequestMapping(value = "/moneymanger")
public class MoneyMangerController extends ServletController{
	
	@Resource
	MoneyMangerService  moneyMangerService;
	
	/**
	 * showMoneyMangerView:单纯跳转界面信息
	 */
	@RequestMapping(value = "/showMoneyMangerView")
	public ModelAndView showMoneyMangerView() {
		return new ModelAndView("/moneymanger/showMoneyMangerView");
	}
	
	
	/**
	 * 获取资金统计信息
	 */
	@RequestMapping(value = "/moneyMangerInfoList.do")
	public ModelAndView moneyMangerInfoList(HttpServletRequest request,
			HttpServletResponse response, MoneyMangerEntity entity) {
		Page<MoneyMangerEntity> page = new Page<MoneyMangerEntity>(request,
				response);
		try {
			// 获取已签约用户
			page = moneyMangerService.getMoneyMangerInfoList(page, entity);
			return new ModelAndView("/moneymanger/moneymangerview")
					.addObject("pageList", page).addObject("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取居民签约信息失败", e);
			// 返回界面，提示错误信息
			return new ModelAndView("/moneymanger/moneymangerview").addObject(
					"errorInfo", "获取居民签约信息失败");
		}
	}
	
	
	/**
	 * 获取资金统计信息医院领导查看
	 */
	@RequestMapping(value = "/capitalTotalMangerByLeader.do")
	public ModelAndView capitalTotalMangerByLeader(HttpServletRequest request,
			HttpServletResponse response, MoneyMangerEntity entity) {
		List<MoneyMangerEntity> page =  null;
		try {
			//点击菜单进入 默认为当前月的第一天到现在
			DateFormat  sdf  =  new SimpleDateFormat("yyyy-MM-dd");
			DateFormat  sdf1  =  new SimpleDateFormat("yyyy-MM");
			// 获取已签约用户
			if(entity.getBeginTime() == null){
				entity.setBeginTime(sdf1.format(new Date())+ "-01 00:00:00");
			}
			if(entity.getEndTime() == null){
				entity.setEndTime(sdf.format(new Date()) + " 23:59:59");
			}
			page = moneyMangerService.capitalTotalMangerByLeader(entity);
			return new ModelAndView("/moneymanger/capitalTotalMangerByLeader")
					.addObject("pageList", page).addObject("begindata",entity.getBeginTime().substring(0, 11))
					.addObject("enddata",entity.getEndTime().substring(0, 11));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取信息失败", e);
			// 返回界面，提示错误信息
			return new ModelAndView("/moneymanger/capitalTotalMangerByLeader").addObject(
					"errorInfo", "获取信息失败");
		}
	}

}
