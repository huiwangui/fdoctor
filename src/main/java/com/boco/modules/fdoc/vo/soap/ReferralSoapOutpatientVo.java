package com.boco.modules.fdoc.vo.soap;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.utils.DateUtils;
import com.boco.modules.fdoc.model.ReferralOutpatientEntity;

/**
 * 万达门诊转诊接口参数类
 * @author q
 *
 */
public class ReferralSoapOutpatientVo {
	/**
	 * 患者姓名
	 */
	private String mzzz_hzxm;
	
	/**
	 * 性别
	 */
	private String mzzz_hzxb;
	
	/**
	 * 年龄
	 */
	private String mzzz_hznl;
	
	/**
	 * 地址
	 */
	private String mzzz_hzjtdz;
	
	/**
	 * 患者身份证号
	 */
	private String mzzz_hzsfzh;
	
	/**
	 * 患者手机号
	 */
	private String mzzz_hzsjhm;
	
	/**
	 * 紧急程度  1.一般  2.紧急
	 */
	private String mzzz_jjcd;
	
	/**
	 * 卡类型   1.社保卡  2.健康卡
	 */
	private String mzzz_hzklx;
	
	/**
	 * 患者卡号
	 */
	private String mzzz_hzkh;
	
	/**
	 * 患者出生日期
	 */
	private String mzzz_hzcsrq;
	
	/**
	 * 初步诊断
	 */
	private String mzzz_cuzd;
	
	/**
	 * 病史摘要
	 */
	private String mzzz_bszy;
	
	/**
	 * 主要既往史
	 */
	private String mzzz_jws;
	
	/**
	 * 治疗情况
	 */
	private String mzzz_zlqk;
	
	/**
	 * 转诊原因
	 */
	private String mzzz_zzmd;
	
	/**
	 * 医生手机号
	 */
	private String mzzz_yssjh;
	
	/**
	 * 转诊办电话号码
	 */
	private String mzzz_zzbsjh;
	
	/**
	 * 是否贫困
	 */
	private String mzzz_pkrk;
	
	/**
	 * 申请发起时间
	 */
	private String mzzz_zzsj;
	
	/**
	 * 发起医院名称
	 */
	private String mzzz_fqyydm;
	
	/**
	 * 发起医院名称
	 */
	private String mzzz_fqyymc;
	
	/**
	 * 发起科室名称
	 */
	private String mzzz_fqksmc;
	
	/**
	 * 发起医生姓名
	 */
	private String mzzz_fqysxm;
	
	/**
	 * 转入医院名称
	 */
	private String mzzz_zryymc;
	
	/**
	 * 转入医院名称
	 */
	private String mzzz_zryydm;
	
	/**
	 * 发起医生工号
	 */
	private String mzzz_fqysgh;
	
	/**
	 * 发起科室代码
	 */
	private String mzzz_fqksdm;
	
	/**
	 * 操作人身份证号
	 */
	private String mzzz_user;
	
	/**
	 * 操作人姓名
	 */
	private String mzzz_user_name;
	
	/**
	 * 是否补填
	 */
	private String mzzz_fillinglogo;
	
	/**
	 * 补填原因
	 */
	private String mzzz_fillingreason;
	
	/**
	 * 是否家庭医生发起  1.是  0.否   
	 */
	private String mzzz_sfjtysfq;
	
	/**
	 * 构造方法中把本地的entity转化为接口需要的参数
	 * @param entity
	 */
	public ReferralSoapOutpatientVo(ReferralOutpatientEntity entity){
		this.mzzz_hzxm = entity.getPersonName();
		this.mzzz_hzxb = entity.getSex();
		this.mzzz_hznl = String.valueOf(entity.getAge());
		this.mzzz_hzjtdz = entity.getAddress();
		this.mzzz_hzsfzh = entity.getIdCard();
		this.mzzz_hzsjhm = entity.getPhoneNumber();
		this.mzzz_jjcd = entity.getUrgency();
		this.mzzz_hzklx = entity.getPatientCardType();
		this.mzzz_hzkh = entity.getPatientCardNumber();
		
		if (entity.getDateOfBirth() != null) {
			this.mzzz_hzcsrq = DateUtils.formatDate(entity.getDateOfBirth());
		}
		
		this.mzzz_cuzd = entity.getImpression();
		this.mzzz_bszy = entity.getSummary();
		this.mzzz_jws = entity.getHistory();
		this.mzzz_zlqk = entity.getTreatmentSituation();
		this.mzzz_zzmd = entity.getReferralReason();
		this.mzzz_yssjh = entity.getDoctorPhoneNumber();
		this.mzzz_zzbsjh = entity.getReferralOfficePhoneNumber();
		this.mzzz_pkrk = entity.getPoolFlag();
		this.mzzz_zzsj = DateUtils.formatDate(entity.getCreateTime());
		//发起医院名称、发起医院代码都传入医院ID
		this.mzzz_fqyydm = entity.getApplyHospitalCode();
		this.mzzz_fqyymc = entity.getApplyHospitalCode();
		
		this.mzzz_fqksmc = entity.getApplyDepartmentName();
		this.mzzz_fqysxm = entity.getApplyDoctorName();
		
		//目标医院名称、目标医院代码都传入医院ID
		this.mzzz_zryydm = entity.getItemHospitalCode();
		this.mzzz_zryymc = entity.getItemHospitalCode();
		
		this.mzzz_fqysgh = entity.getApplyDoctorEmployeeNumber();
		this.mzzz_fqksdm = entity.getApplyDepartmentCode();
		this.mzzz_user = entity.getApplyDoctorIdCard();
		this.mzzz_user_name = entity.getApplyDoctorName();
		if (BusinessConstants.YES.equals(entity.getFillingFlag())) {
			this.mzzz_fillinglogo = "是";
		}else if (BusinessConstants.NO.equals(entity.getFillingFlag())) {
			this.mzzz_fillinglogo = "否";
		}
		this.mzzz_fillingreason = entity.getFillingReason();
		this.mzzz_sfjtysfq = BusinessConstants.YES;
		
	}

	public String getMzzz_sfjtysfq() {
		return mzzz_sfjtysfq;
	}

	public void setMzzz_sfjtysfq(String mzzz_sfjtysfq) {
		this.mzzz_sfjtysfq = mzzz_sfjtysfq;
	}

	public String getMzzz_hzxm() {
		return mzzz_hzxm;
	}

	public void setMzzz_hzxm(String mzzz_hzxm) {
		this.mzzz_hzxm = mzzz_hzxm;
	}

	public String getMzzz_hzxb() {
		return mzzz_hzxb;
	}

	public void setMzzz_hzxb(String mzzz_hzxb) {
		this.mzzz_hzxb = mzzz_hzxb;
	}

	public String getMzzz_hznl() {
		return mzzz_hznl;
	}

	public void setMzzz_hznl(String mzzz_hznl) {
		this.mzzz_hznl = mzzz_hznl;
	}

	public String getMzzz_hzjtdz() {
		return mzzz_hzjtdz;
	}

	public void setMzzz_hzjtdz(String mzzz_hzjtdz) {
		this.mzzz_hzjtdz = mzzz_hzjtdz;
	}

	public String getMzzz_hzsfzh() {
		return mzzz_hzsfzh;
	}

	public void setMzzz_hzsfzh(String mzzz_hzsfzh) {
		this.mzzz_hzsfzh = mzzz_hzsfzh;
	}

	public String getMzzz_hzsjhm() {
		return mzzz_hzsjhm;
	}

	public void setMzzz_hzsjhm(String mzzz_hzsjhm) {
		this.mzzz_hzsjhm = mzzz_hzsjhm;
	}

	public String getMzzz_jjcd() {
		return mzzz_jjcd;
	}

	public void setMzzz_jjcd(String mzzz_jjcd) {
		this.mzzz_jjcd = mzzz_jjcd;
	}

	public String getMzzz_hzklx() {
		return mzzz_hzklx;
	}

	public void setMzzz_hzklx(String mzzz_hzklx) {
		this.mzzz_hzklx = mzzz_hzklx;
	}

	public String getMzzz_hzkh() {
		return mzzz_hzkh;
	}

	public void setMzzz_hzkh(String mzzz_hzkh) {
		this.mzzz_hzkh = mzzz_hzkh;
	}

	public String getMzzz_hzcsrq() {
		return mzzz_hzcsrq;
	}

	public void setMzzz_hzcsrq(String mzzz_hzcsrq) {
		this.mzzz_hzcsrq = mzzz_hzcsrq;
	}

	public String getMzzz_cuzd() {
		return mzzz_cuzd;
	}

	public void setMzzz_cuzd(String mzzz_cuzd) {
		this.mzzz_cuzd = mzzz_cuzd;
	}

	public String getMzzz_bszy() {
		return mzzz_bszy;
	}

	public void setMzzz_bszy(String mzzz_bszy) {
		this.mzzz_bszy = mzzz_bszy;
	}

	public String getMzzz_jws() {
		return mzzz_jws;
	}

	public void setMzzz_jws(String mzzz_jws) {
		this.mzzz_jws = mzzz_jws;
	}

	public String getMzzz_zlqk() {
		return mzzz_zlqk;
	}

	public void setMzzz_zlqk(String mzzz_zlqk) {
		this.mzzz_zlqk = mzzz_zlqk;
	}

	public String getMzzz_zzmd() {
		return mzzz_zzmd;
	}

	public void setMzzz_zzmd(String mzzz_zzmd) {
		this.mzzz_zzmd = mzzz_zzmd;
	}

	public String getMzzz_yssjh() {
		return mzzz_yssjh;
	}

	public void setMzzz_yssjh(String mzzz_yssjh) {
		this.mzzz_yssjh = mzzz_yssjh;
	}

	public String getMzzz_zzbsjh() {
		return mzzz_zzbsjh;
	}

	public void setMzzz_zzbsjh(String mzzz_zzbsjh) {
		this.mzzz_zzbsjh = mzzz_zzbsjh;
	}

	public String getMzzz_pkrk() {
		return mzzz_pkrk;
	}

	public void setMzzz_pkrk(String mzzz_pkrk) {
		this.mzzz_pkrk = mzzz_pkrk;
	}

	public String getMzzz_zzsj() {
		return mzzz_zzsj;
	}

	public void setMzzz_zzsj(String mzzz_zzsj) {
		this.mzzz_zzsj = mzzz_zzsj;
	}

	public String getMzzz_fqyymc() {
		return mzzz_fqyymc;
	}

	public void setMzzz_fqyymc(String mzzz_fqyymc) {
		this.mzzz_fqyymc = mzzz_fqyymc;
	}

	public String getMzzz_fqksmc() {
		return mzzz_fqksmc;
	}

	public void setMzzz_fqksmc(String mzzz_fqksmc) {
		this.mzzz_fqksmc = mzzz_fqksmc;
	}

	public String getMzzz_fqysxm() {
		return mzzz_fqysxm;
	}

	public void setMzzz_fqysxm(String mzzz_fqysxm) {
		this.mzzz_fqysxm = mzzz_fqysxm;
	}

	public String getMzzz_zryymc() {
		return mzzz_zryymc;
	}

	public void setMzzz_zryymc(String mzzz_zryymc) {
		this.mzzz_zryymc = mzzz_zryymc;
	}

	public String getMzzz_fqysgh() {
		return mzzz_fqysgh;
	}

	public void setMzzz_fqysgh(String mzzz_fqysgh) {
		this.mzzz_fqysgh = mzzz_fqysgh;
	}

	public String getMzzz_fqksdm() {
		return mzzz_fqksdm;
	}

	public void setMzzz_fqksdm(String mzzz_fqksdm) {
		this.mzzz_fqksdm = mzzz_fqksdm;
	}

	public String getMzzz_user() {
		return mzzz_user;
	}

	public void setMzzz_user(String mzzz_user) {
		this.mzzz_user = mzzz_user;
	}

	public String getMzzz_user_name() {
		return mzzz_user_name;
	}

	public void setMzzz_user_name(String mzzz_user_name) {
		this.mzzz_user_name = mzzz_user_name;
	}

	public String getMzzz_fillinglogo() {
		return mzzz_fillinglogo;
	}

	public void setMzzz_fillinglogo(String mzzz_fillinglogo) {
		this.mzzz_fillinglogo = mzzz_fillinglogo;
	}

	public String getMzzz_fillingreason() {
		return mzzz_fillingreason;
	}

	public void setMzzz_fillingreason(String mzzz_fillingreason) {
		this.mzzz_fillingreason = mzzz_fillingreason;
	}

	public String getMzzz_fqyydm() {
		return mzzz_fqyydm;
	}

	public void setMzzz_fqyydm(String mzzz_fqyydm) {
		this.mzzz_fqyydm = mzzz_fqyydm;
	}

	public String getMzzz_zryydm() {
		return mzzz_zryydm;
	}

	public void setMzzz_zryydm(String mzzz_zryydm) {
		this.mzzz_zryydm = mzzz_zryydm;
	}
	
}
