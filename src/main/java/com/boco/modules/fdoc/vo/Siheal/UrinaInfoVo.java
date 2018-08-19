package com.boco.modules.fdoc.vo.Siheal;
/**
 * Description 新海一体机尿常规数据
 * @author lzz
 * @date 2017年8月10日 下午4:07:31
 */
public class UrinaInfoVo {

	/**
	 * 32位唯一标识符
	 */
	private String id;
	
	/**
	 * 18位身份证号
	 */
	private String personId;
	
	/**
	 * 医生id
	 */
	private int doctorId;
	
	/**
	 * SN
	 */
	private String SN;
	
	/**
	 * 
	 */
	private String effective;
	
	/**
	 * 
	 */
	private String TmYear;
	
	/**
	 * 
	 */
	private String TmMon;
	
	/**
	 * 
	 */
	private String TmDay;
	
	/**
	 * 
	 */
	private String TmHour;
	
	/**
	 * 
	 */
	private String TmMinute;
	
	/**
	 * 
	 */
	private String PrintFlag;
	
	/**
	 * 白细胞
	 */
	private String LEU;
	
	/**
	 * 亚硝酸盐
	 */
	private String Nit;
	
	/**
	 * 尿胆原
	 */
	private String UBG;
	
	/**
	 * 尿蛋白
	 */
	private String PRO;
	
	/**
	 * 酸碱度
	 */
	private String PH;
	
	/**
	 * 尿潜血
	 */
	private String BLD;
	
	/**
	 * 尿比重
	 */
	private String SG;
	
	/**
	 * 酮体
	 */
	private String KET;
	
	/**
	 * 胆红素
	 */
	private String BIL;
	
	/**
	 * 葡萄糖
	 */
	private String GLU;
	
	/**
	 * 维生素C
	 */
	private String VC;
	
	/**
	 * 血氧值
	 */
	private String spo2;
	
	/**
	 * 脉率值
	 */
	private String prbpm;
	
	/**
	 * 结论,[00 异常,01 正常]
	 */
	private String result;
	
	/**
	 * 时间 yyyy-MM-ddTHH:mm:ss
	 */
	private String mt;
	
	/**
	 * 版本
	 */
	private int version;
	
	/**
	 * 来源
	 */
	private String source;
	
	/**
	 * 机器码
	 */
	private String machineCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}

	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public String getTmYear() {
		return TmYear;
	}

	public void setTmYear(String tmYear) {
		TmYear = tmYear;
	}

	public String getTmMon() {
		return TmMon;
	}

	public void setTmMon(String tmMon) {
		TmMon = tmMon;
	}

	public String getTmDay() {
		return TmDay;
	}

	public void setTmDay(String tmDay) {
		TmDay = tmDay;
	}

	public String getTmHour() {
		return TmHour;
	}

	public void setTmHour(String tmHour) {
		TmHour = tmHour;
	}

	public String getTmMinute() {
		return TmMinute;
	}

	public void setTmMinute(String tmMinute) {
		TmMinute = tmMinute;
	}

	public String getPrintFlag() {
		return PrintFlag;
	}

	public void setPrintFlag(String printFlag) {
		PrintFlag = printFlag;
	}

	public String getLEU() {
		return LEU;
	}

	public void setLEU(String lEU) {
		LEU = lEU;
	}

	public String getNit() {
		return Nit;
	}

	public void setNit(String nit) {
		Nit = nit;
	}

	public String getUBG() {
		return UBG;
	}

	public void setUBG(String uBG) {
		UBG = uBG;
	}

	public String getPRO() {
		return PRO;
	}

	public void setPRO(String pRO) {
		PRO = pRO;
	}

	public String getPH() {
		return PH;
	}

	public void setPH(String pH) {
		PH = pH;
	}

	public String getBLD() {
		return BLD;
	}

	public void setBLD(String bLD) {
		BLD = bLD;
	}

	public String getSG() {
		return SG;
	}

	public void setSG(String sG) {
		SG = sG;
	}

	public String getKET() {
		return KET;
	}

	public void setKET(String kET) {
		KET = kET;
	}

	public String getBIL() {
		return BIL;
	}

	public void setBIL(String bIL) {
		BIL = bIL;
	}

	public String getGLU() {
		return GLU;
	}

	public void setGLU(String gLU) {
		GLU = gLU;
	}

	public String getVC() {
		return VC;
	}

	public void setVC(String vC) {
		VC = vC;
	}

	public String getSpo2() {
		return spo2;
	}

	public void setSpo2(String spo2) {
		this.spo2 = spo2;
	}

	public String getPrbpm() {
		return prbpm;
	}

	public void setPrbpm(String prbpm) {
		this.prbpm = prbpm;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	
	
	
}
