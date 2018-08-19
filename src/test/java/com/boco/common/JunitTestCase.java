package com.boco.common;

import com.boco.modules.fdoc.service.StatisticsDayBasedataService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		/*"classpath:mybatis-config.xml",*/
		"classpath:datasource-druid.xml",
		"classpath:spring-context.xml",
		"classpath:spring-mybatis.xml" })
public class JunitTestCase {
	protected Logger logger = Logger.getLogger(getClass());

	@Resource
	StatisticsDayBasedataService statisticsDayBasedataService;

	@Test
	public void testS()throws Exception{
		Object o = statisticsDayBasedataService.getStatisticDayTwoBasedata("119",null, null);
		System.out.println(o);
		System.out.println("test");
	}
}

























