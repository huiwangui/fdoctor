package com.boco.modules.fdoc.cache;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boco.common.jedis.JedisUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.annotation.SelectCache;

/**
 * 查询缓存切入类
 * @author q
 *
 */
@Component
@Aspect
public class SelectCacheAOP {
	
	private static Logger logger = LoggerFactory.getLogger(JedisUtils.class);

	@Pointcut("@annotation(com.boco.modules.fdoc.annotation.SelectCache)")
	public void selectCache() {
		
	}

	/**
	 * 在所有标注@selectCache的地方切入
	 * 
	 * @param joinPoint
	 * @throws Throwable 
	 */
	@Around("selectCache()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		//获取方法名
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		
		//获取注解参数name，即代表controller访问路径
		String ActionName = method.getAnnotation(SelectCache.class).url();
		
		//获取访问参数
		Object[] args = joinPoint.getArgs();
		
		//封装缓存key值（路径 + 参数）
		Map<String, Object> cacheMap = new HashMap<String, Object>();
		cacheMap.put("actionName", ActionName);
		cacheMap.put("args", args);
		
		if (JedisUtils.get(JsonUtils.getJsonFormat(cacheMap)) != null) {
			//如果缓存中的查询不为空，说明缓存命中，直接返回缓存中的结果
			logger.info("return cache.....");
			return JedisUtils.get(JsonUtils.getJsonFormat(cacheMap));
		}else {
			//如果缓存为空，则执行目标controller
			Object proceed = joinPoint.proceed(args);
			
			//将目标controller中的值放入缓存，并返回
			JedisUtils.set(JsonUtils.getJsonFormat(cacheMap), proceed.toString(), 300);
			logger.info("no cache, return database select....");
			return proceed;
		}
	}
}
