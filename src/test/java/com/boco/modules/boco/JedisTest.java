/*
package com.boco.modules.boco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

import com.boco.common.jedis.JedisUtils;
import com.boco.sp.external.interfaces.common.IUserProcess;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring-Jedis.xml"
		})

public class JedisTest{
	
	@Test
	public void testJedis(){
		 Jedis jedis = JedisUtils.getResource();
		 jedis.set("name", "minxr");  
         String ss = jedis.get("name");  
         System.out.println(ss);  
	}
	
	@Test
	public void testDubbo(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:dubbo/dubbo.xml" });
		context.start();
		IUserProcess iUserProcess = (IUserProcess) context.getBean("iUserProcess");
		System.out.println(iUserProcess.searchLowOrgByRegionCode("110000"));
	}

}
*/
