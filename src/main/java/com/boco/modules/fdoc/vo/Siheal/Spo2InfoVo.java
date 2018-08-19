package com.boco.modules.fdoc.vo.Siheal;
/**
 * Description 新海一体机血氧数据
 * @author lzz
 * @date 2017年8月10日 下午3:00:25
 */
public class Spo2InfoVo {
	
	/**
	 * 32唯一标识符
	 */
	private String id;
	
	/**
	 * 18位身份证号
	 */
	private String personId;
	
	/**
	 * 医生Id
	 */
	private int doctorId;
	
	/**
	 * 血氧值
	 */
	private int spo2;
	
	/**
	 * 脉率值
	 */
	private int prbpm;
	
	/**
	 * 血氧结论[00 异常 01正常]
	 */
	private String result;
	
	/**
	 * 时间 String
	 */
	private String mt;
	
	/**
	 * 版本
	 */
	private int Version;
	
	/**
	 * 来源
	 */
	private String source;
	
	/**
	 * 12位机器码
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

	public int getSpo2() {
		return spo2;
	}

	public void setSpo2(int spo2) {
		this.spo2 = spo2;
	}

	public int getPrbpm() {
		return prbpm;
	}

	public void setPrbpm(int prbpm) {
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
		return Version;
	}

	public void setVersion(int version) {
		Version = version;
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
