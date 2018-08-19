package com.boco.modules.fdoc.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.ApiCallerEntity;
import com.boco.modules.fdoc.service.ApiCallerService;

/**
 * 对外接口用户名、密码验证拦截器
 * @author q
 *
 */
public class ApiVerifyInterceptor extends BaseInterceptor{
	
	@Resource
	ApiCallerService apiCallerService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean runHandler(HttpServletRequest request, HttpServletResponse response) {
		String requestParam = request.getParameter("requestParam");
		
		try {
			Map<String, Object> objectJsonMap = JsonUtils.getObjectJsonMap(requestParam);
			
			//json解析失败
			if (objectJsonMap == null) {
				super.failed = "/fdoctor/outapi/jsonParseError";
				return true;
			}
			
			Map<String, Object> userMap = (Map<String, Object>) objectJsonMap.get("userInfo");
			if (userMap != null) {
				//获取参数中的用户名、密码
				String userName = (String) userMap.get("userName");
				String password = (String) userMap.get("password");
				
				ApiCallerEntity entity = new ApiCallerEntity(userName, password);
				if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
					super.failed = "/fdoctor/outapi/verifyError";
					return true;
				}else{
					if (apiCallerService.getApiCaller(entity) != null) {
						return false;
					}else{
						super.failed = "/fdoctor/outapi/verifyError";
						return true;
					}
				}
			}else{
				super.failed = "/fdoctor/outapi/verifyError";
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			//json解析出错，返回提示
			super.failed = "/fdoctor/outapi/serverError";
			return true;
		}
	}

}
