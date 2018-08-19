package com.boco.modules.fdoc.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 查询缓存注释，需要进行缓存的controller上标注此注释
 * @author q
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})
public @interface SelectCache {
	String url() default "";  
}
