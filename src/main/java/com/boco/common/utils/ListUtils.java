package com.boco.common.utils;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * ClassName: ListUtils <br/>
 * Reason: List相关工具类. <br/>
 * date: 2017年2月16日 下午2:58:52 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class ListUtils {
	/**
	 * 
	 * splitList:(分割list为多个list合集). <br/>
	 * @author q
	 * @param list
	 * @param pageSize
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		int listSize = list.size(); // list的大小
		int page = (listSize + (pageSize - 1)) / pageSize; // 页数
		List<List<T>> listArray = new ArrayList<List<T>>(); // 创建list数组
		for (int i = 0; i < page; i++) { // 按照数组大小遍历
			List<T> subList = new ArrayList<T>(); // 数组每一位放入一个分割后的list
			for (int j = 0; j < listSize; j++) { // 遍历待分割的list
				int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize; // 当前记录的页码(第几页)
				if (pageIndex == (i + 1)) { // 当前记录的页码等于要放入的页码时
					subList.add(list.get(j)); // 放入list中的元素到分割后的list(subList)
				}
				if ((j + 1) == ((j + 1) * pageSize)) { // 当放满一页时退出当前循环
					break;
				}
			}
			listArray.add(subList); // 将分割后的list放入对应的数组的位中
		}
		return listArray;
	}
	/**
	 * 
	 * listToString:(把List每个元素用逗号隔开). <br/>
	 * @author q
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list){
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			bf.append(list.get(i) + ",");
		}
		return bf.toString().substring(0, bf.length() - 1);
	}
}
