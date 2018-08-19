package com.boco.modules.fdoc.vo.Siheal;
/**
 * Description 新海一体机心电数据
 * @author lzz
 * @date 2017年8月10日 下午4:55:42
 */
public class EcgInfoVo {
	
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
	 * 数据文件
	 */
	private String file;
	
	/**
	 * 心率
	 */
	private int rate;
	
	/**
	 * 心率
	 */
	private int hr;
	
	/**
	 * p波
	 */
	private String pa;
	
	/**
	 * QRS波
	 */
	private String qrsd;
	
	/**
	 * RV_SV
	 */
	private String rs;
	
	/**
	 * 诊断结果
	 */
	private String ecode;
	
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getHr() {
		return hr;
	}

	public void setHr(int hr) {
		this.hr = hr;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public String getQrsd() {
		return qrsd;
	}

	public void setQrsd(String qrsd) {
		this.qrsd = qrsd;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
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
