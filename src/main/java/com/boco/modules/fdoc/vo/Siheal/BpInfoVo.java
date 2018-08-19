package com.boco.modules.fdoc.vo.Siheal;
/**
 * Description 新海一体机血压数据
 * @author lzz
 * @date 2017年8月10日 下午3:27:05
 */
public class BpInfoVo {
	
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
	 * 2位血压检验类型[00 婴儿，01  儿童，02 成人]
	 */
	private String checkType;
	
	/**
	 * 收缩压
	 */
	private int ssy;
	
	/**
	 * 舒展压
	 */
	private int szy;
	
	/**
	 * 脉率值
	 */
	private int prbpm;
	
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

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public int getSsy() {
		return ssy;
	}

	public void setSsy(int ssy) {
		this.ssy = ssy;
	}

	public int getSzy() {
		return szy;
	}

	public void setSzy(int szy) {
		this.szy = szy;
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
