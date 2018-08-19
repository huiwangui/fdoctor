package com.boco.common.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * 
 * ClassName: AbnormalSignDateComparator <br/>
 * Reason: 血压、血糖记录按照时间排列的比较器. <br/>
 * date: 2017年2月20日 下午2:20:22 <br/>
 *
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class AbnormalSignDateComparator implements Comparator{

	@Override
	public int compare(Object arg0, Object arg1) {
		Map<String, Object> map1 = (Map<String, Object>) arg0;
		Map<String, Object> map2 = (Map<String, Object>) arg1;
		Long timeStamp1 = (Long) map1.get("timeStamp");
		Long timeStamp2 = (Long) map2.get("timeStamp");
		return timeStamp2.compareTo(timeStamp1);
	}

}
