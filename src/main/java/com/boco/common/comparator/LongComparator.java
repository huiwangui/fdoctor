package com.boco.common.comparator;

import java.util.Comparator;
/**
 * 
 * ClassName: 对List<Long>排序 <br/>
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public class LongComparator implements Comparator{
	
	private boolean reverseOrder; // 是否倒序
	
	public LongComparator(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
    }

	@Override
	public int compare(Object arg0, Object arg1) {
		Long long1 = (Long) arg0;
		Long long2 = (Long) arg1;
		if (reverseOrder) {
			return long2.compareTo(long1);
		}else {
			return long1.compareTo(long2);
		}
	}

}
