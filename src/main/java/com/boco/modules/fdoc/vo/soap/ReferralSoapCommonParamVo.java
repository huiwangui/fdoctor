package com.boco.modules.fdoc.vo.soap;
/**
 * 调用万达双向转诊接口共用参数类
 * @author q
 *
 */
public class ReferralSoapCommonParamVo {
	/**
	 * 注册厂商代码
	 */
	private String zcjgdm = "MYDSF004";
	
	/**
	 * 注册厂商名称
	 */
	private String zcjgmc = "亿阳信通股份有限公司";
	
	/**
	 * 绑定机构代码
	 */
	private String bdjgdm = "PDY00001X510700";
	
	/**
	 * 绑定机构名称
	 */
	private String bdjgmc = "绵阳市卫生和计划生育委员会";
	
	/**
	 * 绑定应用代码
	 */
	private String bdyydm = "YYXTFJZL001";
	
	/**
	 * 绑定应用名称
	 */
	private String bdyymc = "亿阳信通分级诊疗系统";
	
	/**
	 * 接口代码
	 */
	private String jkdm;
	
	/**
	 * 接口名称
	 */
	private String jkmc;
	
	/**
	 * 用户名
	 */
	private String username = "yiyangxintong";
	
	/**
	 * 密码
	 */
	private String password = "111111";
	
	/**
	 * 绑定操作系统代码
	 */
	private String bdczxtdm = "windows";
	
	/**
	 * 绑定操作系统名称
	 */
	private String bdczxtmc = "windows";
	
	public ReferralSoapCommonParamVo(String jkdm, String jkmc){
		this.jkdm = jkdm;
		this.jkmc = jkmc;
	}

	public String getZcjgdm() {
		return zcjgdm;
	}

	public void setZcjgdm(String zcjgdm) {
		this.zcjgdm = zcjgdm;
	}

	public String getZcjgmc() {
		return zcjgmc;
	}

	public void setZcjgmc(String zcjgmc) {
		this.zcjgmc = zcjgmc;
	}

	public String getBdjgdm() {
		return bdjgdm;
	}

	public void setBdjgdm(String bdjgdm) {
		this.bdjgdm = bdjgdm;
	}

	public String getBdjgmc() {
		return bdjgmc;
	}

	public void setBdjgmc(String bdjgmc) {
		this.bdjgmc = bdjgmc;
	}

	public String getBdyydm() {
		return bdyydm;
	}

	public void setBdyydm(String bdyydm) {
		this.bdyydm = bdyydm;
	}

	public String getBdyymc() {
		return bdyymc;
	}

	public void setBdyymc(String bdyymc) {
		this.bdyymc = bdyymc;
	}

	public String getJkdm() {
		return jkdm;
	}

	public void setJkdm(String jkdm) {
		this.jkdm = jkdm;
	}

	public String getJkmc() {
		return jkmc;
	}

	public void setJkmc(String jkmc) {
		this.jkmc = jkmc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBdczxtdm() {
		return bdczxtdm;
	}

	public void setBdczxtdm(String bdczxtdm) {
		this.bdczxtdm = bdczxtdm;
	}

	public String getBdczxtmc() {
		return bdczxtmc;
	}

	public void setBdczxtmc(String bdczxtmc) {
		this.bdczxtmc = bdczxtmc;
	}

	
}
