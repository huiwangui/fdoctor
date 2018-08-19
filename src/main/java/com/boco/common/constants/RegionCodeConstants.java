package com.boco.common.constants;

import com.boco.common.utils.PropertiesUtils;

public class RegionCodeConstants {
	/**
	 * 各行政单位对应的区划代码位数
	 */
	public static Integer REGION_CODE_LENGTH_PROVINCE = 2;	//省级：2位以下
	public static Integer REGION_CODE_LENGTH_CITY = 4;	//市级：4位
	public static Integer REGION_CODE_LENGTH_COUNTY = 6;	//县级：6位
	public static Integer REGION_CODE_LENGTH_COUNTRY = 9;	//乡级：9位
	public static Integer REGION_CODE_LENGTH_VILLAGE = 12;	//村级：12位
	
	public static String DEFAULT_REGION_CODE = PropertiesUtils.getValue("default_region_code");	//默认区划
}
