package com.boco.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	private static Properties propBase = new Properties();

	/**	获取properties文件
	 * @param key 键
	 * @param objs 替换参数
	 * @return
	 */
	public static String getValue(String key, Object... objs) {
		Properties prop = getProps();
		String ret = prop.getProperty(key);
		for (int i = 0; i < objs.length; i++) {
			ret = ret.replace("{" + i + "}", objs[i].toString());
		}
		return ret;
	}

	private static Properties getProps() {
		try {
			String config = "config.properties";
			String path = PropertiesUtils.class.getClassLoader().getResource(config).getPath();
			FileInputStream fis = new FileInputStream(path);
			propBase.load(fis);
			return propBase;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 判断上传位置是否正确
	 * 不正确则返回false
	 * @param directory
	 * @return
	 */
	public static boolean uploadDirectory(String resource){
		String[] filterKey = new String[] { "avatar_path", "goods_path", "sfz_path", "store_path", "yyzz_path","activity_ad" };

		if (!StringUtils.isEmpty(resource)) {
			for (String key : filterKey) {
				if (resource.equals(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断文件类型是否正确
	 * 不正确则返回false
	 * @param fileType
	 * @return
	 */
	public static boolean fileTypeIsRight(String fileType){
		String[] filterKey = PropertiesUtils.getValue("file_type").split(",");

		if (!StringUtils.isEmpty(fileType)) {
			for (String type : filterKey) {
				if (fileType.equals(type)) {
					return true;
				}
			}
		}
		return false;
	}
}
