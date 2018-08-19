/*package com.boco.common.utils;

import java.util.List;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.boco.sp.external.interfaces.common.IUserProcess;

public class RegionUtils {
	
	public static String getRegionListStr(String address) throws BeanCreationException{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:dubbo/dubbo.xml" });
		context.start();
		IUserProcess iUserProcess = (IUserProcess) context.getBean("iUserProcess");
		List<String> searchLowOrgByRegionCode = iUserProcess.searchLowOrgByRegionCode(address);
		String returnStr = "";
		for (String string : searchLowOrgByRegionCode) {
			returnStr = returnStr + string + ",";
		}
		return returnStr.substring(0,returnStr.length()-1);
	}
}
*/