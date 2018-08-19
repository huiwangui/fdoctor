package com.boco.modules.fdoc.vo.soap;

import com.boco.common.constants.ReferralSoapConstans;
import com.boco.common.utils.DateUtils;
import com.boco.modules.fdoc.model.ReferralHospitalizationEntity;

/**
 * 万达住院转诊接口参数类
 * @author q
 *
 */
public class ReferralSoapHospitalzationVo {
	/**
	 * 患者姓名
	 */
	private String hzxx_hzxm;
	
	/**
	 * 患者身份证号
	 */
	private String hzxx_sfzh;
	
	/**
	 * 患者性别 1:男，2:女
	 */
	private String hzxx_xb;
	
	/**
	 * 患者手机号
	 */
	private String hzxx_sjhm;
	
	/**
	 * 患者年龄
	 */
	private String hzxx_nl;
	
	/**
	 * 患者家庭地址
	 */
	private String hzxx_jtdz;
	
	/**
	 * 紧急程度1一般 2紧急
	 */
	private String referral_jjcd;
	
	/**
	 * 转诊时间，格式为yyyy-MM-dd
	 */
	private String referral_time;
	
	/**
	 * 患者出生日期 ，格式为yyyy-MM-dd
	 */
	private String hzxx_csrq;
	
	/**
	 * 转诊类型(上转/下转)，默认上转
	 */
	private String referral_type;
	
	/**
	 * 转诊状态，默认为"申请"
	 */
	private String referral_state;
	
	/**
	 * 是否贫困病人（0.否   1.是）
	 */
	private String mzzz_pkrk;
	
	/**
	 * 医院转诊编码
	 */
	private String from_referral_code;
	
	/**
	 * 转入医院代码
	 */
	private String toto_hospital;
	
	/**
	 * 申请日期，格式为yyyy-MM-dd
	 */
	private String referral_sqrq;
	
	/**
	 * 初步诊断
	 */
	private String referral_icd;
	
	/**
	 * 转入科室
	 */
	private String toto_ks;
	
	/**
	 * 转入床位
	 */
	private String toto_cw;
	
	/**
	 * 转出医院
	 */
	private String from_hospital;
	
	/**
	 * 是否病区审核
	 */
	private String referral_sfbqsh;
	
	/**
	 * 病史摘要
	 */
	private String referral_bszy;
	
	/**
	 * 主要既往史
	 */
	private String referral_zyjws;
	
	/**
	 * 治疗情况
	 */
	private String referral_zlqk;
	
	/**
	 * 转诊目的
	 */
	private String referral_zzmd;
	
	/**
	 * 转诊原因
	 */
	private String referral_zzyy;
	
	/**
	 * 长期医嘱(文件名)
	 */
	private String referral_cqyz;
	
	/**
	 * 影像报告(文件名)
	 */
	private String referral_yybg;
	
	/**
	 * 检验检查(文件名)
	 */
	private String referral_jyjc;
	
	/**
	 * 入院记录(文件名)
	 */
	private String referral_ryjl;
	
	/**
	 * 病程记录(文件名)
	 */
	private String referral_bcjl;
	
	/**
	 * 手术记录(文件名)
	 */
	private String referral_ssjl;
	
	/**
	 * 临时医嘱(文件名)
	 */
	private String referral_lsyz;
	
	/**
	 * 其他(文件名)
	 */
	private String referral_qt;
	
	/**
	 * 操作人身份证号
	 */
	private String referral_user;
	
	/**
	 * 操作人姓名
	 */
	private String referral_user_name;
	
	public ReferralSoapHospitalzationVo(ReferralHospitalizationEntity entity){
		this.hzxx_hzxm = entity.getPersonName();
		this.hzxx_sfzh = entity.getIdCard();
		this.hzxx_xb = entity.getSex();
		this.hzxx_sjhm = entity.getPhoneNumber();
		this.hzxx_nl = String.valueOf(entity.getAge());
		this.hzxx_jtdz = entity.getAddress();
		this.referral_jjcd = entity.getUrgency();
		this.referral_time = DateUtils.formatDate(entity.getRealReferralDate());
		if (entity.getDateOfBirth() != null) {
			this.hzxx_csrq = DateUtils.formatDate(entity.getDateOfBirth());
		}
		
		//默认转诊方向为上转
		this.referral_type = ReferralSoapConstans.UP_REFERRAL_CHS;
		
		//默认转诊状态为申请
		//this.referral_state = ReferralSoapConstans.STATUS_APPLY_CHS;
		
		this.mzzz_pkrk = entity.getPoolFlag();
		this.toto_hospital = entity.getItemHospitalCode();
		this.referral_sqrq = DateUtils.formatDate(entity.getCreateTime());
		this.referral_icd = entity.getImpression();
		this.from_hospital = entity.getApplyHospitalCode();
		this.referral_bszy = entity.getSummary();
		this.referral_zyjws = entity.getHistory();
		this.referral_zlqk = entity.getTreatmentSituation();
		this.referral_zzmd = entity.getRefferralPurpose();
		this.referral_zzyy = entity.getReferralReason();
		this.referral_user = entity.getApplyDoctorIdCard();
		this.referral_user_name = entity.getApplyDoctorName();
	}

	public String getHzxx_hzxm() {
		return hzxx_hzxm;
	}

	public void setHzxx_hzxm(String hzxx_hzxm) {
		this.hzxx_hzxm = hzxx_hzxm;
	}

	public String getHzxx_sfzh() {
		return hzxx_sfzh;
	}

	public void setHzxx_sfzh(String hzxx_sfzh) {
		this.hzxx_sfzh = hzxx_sfzh;
	}

	public String getHzxx_xb() {
		return hzxx_xb;
	}

	public void setHzxx_xb(String hzxx_xb) {
		this.hzxx_xb = hzxx_xb;
	}

	public String getHzxx_sjhm() {
		return hzxx_sjhm;
	}

	public void setHzxx_sjhm(String hzxx_sjhm) {
		this.hzxx_sjhm = hzxx_sjhm;
	}

	public String getHzxx_nl() {
		return hzxx_nl;
	}

	public void setHzxx_nl(String hzxx_nl) {
		this.hzxx_nl = hzxx_nl;
	}

	public String getHzxx_jtdz() {
		return hzxx_jtdz;
	}

	public void setHzxx_jtdz(String hzxx_jtdz) {
		this.hzxx_jtdz = hzxx_jtdz;
	}

	public String getReferral_jjcd() {
		return referral_jjcd;
	}

	public void setReferral_jjcd(String referral_jjcd) {
		this.referral_jjcd = referral_jjcd;
	}

	public String getReferral_time() {
		return referral_time;
	}

	public void setReferral_time(String referral_time) {
		this.referral_time = referral_time;
	}

	public String getHzxx_csrq() {
		return hzxx_csrq;
	}

	public void setHzxx_csrq(String hzxx_csrq) {
		this.hzxx_csrq = hzxx_csrq;
	}

	public String getReferral_type() {
		return referral_type;
	}

	public void setReferral_type(String referral_type) {
		this.referral_type = referral_type;
	}

	public String getReferral_state() {
		return referral_state;
	}

	public void setReferral_state(String referral_state) {
		this.referral_state = referral_state;
	}

	public String getMzzz_pkrk() {
		return mzzz_pkrk;
	}

	public void setMzzz_pkrk(String mzzz_pkrk) {
		this.mzzz_pkrk = mzzz_pkrk;
	}

	public String getFrom_referral_code() {
		return from_referral_code;
	}

	public void setFrom_referral_code(String from_referral_code) {
		this.from_referral_code = from_referral_code;
	}

	public String getToto_hospital() {
		return toto_hospital;
	}

	public void setToto_hospital(String toto_hospital) {
		this.toto_hospital = toto_hospital;
	}

	public String getReferral_sqrq() {
		return referral_sqrq;
	}

	public void setReferral_sqrq(String referral_sqrq) {
		this.referral_sqrq = referral_sqrq;
	}

	public String getReferral_icd() {
		return referral_icd;
	}

	public void setReferral_icd(String referral_icd) {
		this.referral_icd = referral_icd;
	}

	public String getToto_ks() {
		return toto_ks;
	}

	public void setToto_ks(String toto_ks) {
		this.toto_ks = toto_ks;
	}

	public String getToto_cw() {
		return toto_cw;
	}

	public void setToto_cw(String toto_cw) {
		this.toto_cw = toto_cw;
	}

	public String getFrom_hospital() {
		return from_hospital;
	}

	public void setFrom_hospital(String from_hospital) {
		this.from_hospital = from_hospital;
	}

	public String getReferral_sfbqsh() {
		return referral_sfbqsh;
	}

	public void setReferral_sfbqsh(String referral_sfbqsh) {
		this.referral_sfbqsh = referral_sfbqsh;
	}

	public String getReferral_bszy() {
		return referral_bszy;
	}

	public void setReferral_bszy(String referral_bszy) {
		this.referral_bszy = referral_bszy;
	}

	public String getReferral_zyjws() {
		return referral_zyjws;
	}

	public void setReferral_zyjws(String referral_zyjws) {
		this.referral_zyjws = referral_zyjws;
	}

	public String getReferral_zlqk() {
		return referral_zlqk;
	}

	public void setReferral_zlqk(String referral_zlqk) {
		this.referral_zlqk = referral_zlqk;
	}

	public String getReferral_zzmd() {
		return referral_zzmd;
	}

	public void setReferral_zzmd(String referral_zzmd) {
		this.referral_zzmd = referral_zzmd;
	}

	public String getReferral_zzyy() {
		return referral_zzyy;
	}

	public void setReferral_zzyy(String referral_zzyy) {
		this.referral_zzyy = referral_zzyy;
	}

	public String getReferral_cqyz() {
		return referral_cqyz;
	}

	public void setReferral_cqyz(String referral_cqyz) {
		this.referral_cqyz = referral_cqyz;
	}

	public String getReferral_yybg() {
		return referral_yybg;
	}

	public void setReferral_yybg(String referral_yybg) {
		this.referral_yybg = referral_yybg;
	}

	public String getReferral_jyjc() {
		return referral_jyjc;
	}

	public void setReferral_jyjc(String referral_jyjc) {
		this.referral_jyjc = referral_jyjc;
	}

	public String getReferral_ryjl() {
		return referral_ryjl;
	}

	public void setReferral_ryjl(String referral_ryjl) {
		this.referral_ryjl = referral_ryjl;
	}

	public String getReferral_bcjl() {
		return referral_bcjl;
	}

	public void setReferral_bcjl(String referral_bcjl) {
		this.referral_bcjl = referral_bcjl;
	}

	public String getReferral_ssjl() {
		return referral_ssjl;
	}

	public void setReferral_ssjl(String referral_ssjl) {
		this.referral_ssjl = referral_ssjl;
	}

	public String getReferral_lsyz() {
		return referral_lsyz;
	}

	public void setReferral_lsyz(String referral_lsyz) {
		this.referral_lsyz = referral_lsyz;
	}

	public String getReferral_qt() {
		return referral_qt;
	}

	public void setReferral_qt(String referral_qt) {
		this.referral_qt = referral_qt;
	}

	public String getReferral_user() {
		return referral_user;
	}

	public void setReferral_user(String referral_user) {
		this.referral_user = referral_user;
	}

	public String getReferral_user_name() {
		return referral_user_name;
	}

	public void setReferral_user_name(String referral_user_name) {
		this.referral_user_name = referral_user_name;
	}
	
	
}
