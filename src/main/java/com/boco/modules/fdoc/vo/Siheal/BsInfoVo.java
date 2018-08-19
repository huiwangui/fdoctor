package com.boco.modules.fdoc.vo.Siheal;
/**
 * Description 新海一体机血糖数据
 * @author lzz
 * @date 2017年8月10日 下午3:49:05
 */
public class BsInfoVo {
	
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
	 * 血糖检验类型,[00 空腹,01  饭后两小时,02 随机]
	 */
	private String checkType;
	
	/**
	 * 血糖值
	 */
	private double val;
	
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

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
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
