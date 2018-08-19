package com.boco.common.constants;

/**
 * 万达双向转诊接口信息类
 * @author q
 *
 */
public class ReferralSoapConstans {
	public final static String SUCCESS_CODE = "0";	//调用成功返回码
	/**
	 * 门诊转诊
	 */
	public final static String OUTPATIENT_APPLY_NAME = "outpatientApply";	//门诊转诊申请接口名
	public final static String OUTPATIENT_APPLY_CODE = "CDSXZZ0001";	//门诊转诊申请接口代码
	
	/**
	 * 住院转诊
	 */
	public final static String UP_REFERRAL_CHS = "上转";
	public final static String DOWN_REFERRAL_CHS = "下转";
	
	public final static String STATUS_APPLY_CHS = "申请";
	
	public final static String INPATIENT_APPLY_NAME = "inpatientApply";	//住院转诊申请接口名
	public final static String INPATIENT_APPLY_CODE = "CDSXZZ0006";	//住院转诊申请接口代码
}
