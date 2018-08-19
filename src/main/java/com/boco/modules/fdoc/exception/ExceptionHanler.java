package com.boco.modules.fdoc.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;
/**
 * 通一异常处理类
 * 
 * @author j
 *
 * @date 2017年9月29日
 */
@ControllerAdvice
public class ExceptionHanler {
	
     private final static Logger loger=LoggerFactory.getLogger(ExceptionHanler.class);

    @ExceptionHandler(UserRelationException.class)
    @ResponseBody
    public String exp(HttpServletRequest request, Exception ex) {  
          
        if(ex instanceof UserRelationException) {  
        	((UserRelationException) ex).getCode();
            return JsonUtils.getJson(new BaseJsonVo(((UserRelationException) ex).getCode(),ex.getMessage()));
         } 
        return JsonUtils.getJson(new BaseJsonVo(((UserRelationException) ex).getCode(),ex.getMessage()));
//            else {  
//        	loger.error("系统异常{}"+ex);
//            return JsonUtils.getJson(BaseJsonVo.error(ApiStatusEnum.ERROR_CODE));  
//        }  
    }  

}
