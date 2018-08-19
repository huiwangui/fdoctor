package com.boco.modules.fdoc.model;
import java.util.Date;


   /**
    * report_healthmonitor 实体类
    * Thu Apr 13 10:26:57 CST 2017 lzz
    */ 


public class ReportHealthmonitorEntity{
	/**
	 * 主键，Guid
	 */
	private String Id;
	/**
	 * 请求号
	 */
	private String RequestNo;
	/**
	 * 第二ID
	 */
	private String PersonCode;
	/**
	 * 类型
	 */
	private Integer PersonType;
	/**
	 * 居民姓名
	 */
	private String PersonName;
	/**
	 * 英文缩写
	 */
	private String EnglishName;
	/**
	 * 性别(1,男)
	 */
	private Integer Gender;
	/**
	 * 身份证号码
	 */
	private String IdCode;
	/**
	 * 档案号
	 */
	private String ArchiveCode;
	/**
	 * 健康卡号
	 */
	private String HealthCode;
	/**
	 * 出生日期
	 */
	private Date Birthday;
	/**
	 * 名族
	 */
	private Integer Nation;
	/**
	 * 种族
	 */
	private Integer Race;
	/**
	 * 照片
	 */
	private String Photo;
	/**
	 * 户籍地址
	 */
	private String Address;
	/**
	 * 身高
	 */
	private Integer Height;
	/**
	 * 体重
	 */
	private Float Weight;
	/**
	 * 腰围
	 */
	private Float WaistLine;
	/**
	 * 体质指数
	 */
	private Float BMI;
	/**
	 * 左侧收缩压
	 */
	private Integer LeftSys;
	/**
	 * 左侧舒展压
	 */
	private Integer LeftDia;
	/**
	 * 右侧收缩压
	 */
	private Integer RightSys;
	/**
	 * 右侧舒张压
	 */
	private Integer RightDia;
	/**
	 * 脉率
	 */
	private Integer PR;
	/**
	 * 心率
	 */
	private Integer HR;
	/**
	 * 血氧饱和度
	 */
	private Integer SpO2;
	/**
	 * 体温
	 */
	private Float Temp;
	/**
	 * 呼吸
	 */
	private Integer Resp;
	/**
	 * 心电数据
	 */
	private String EcgData;
	/**
	 * 心电数据报表地址
	 */
	private String EcgReport;
	/**
	 * 白细胞（尿常规）
	 */
	private String UrineLeu;
	/**
	 * 酸碱度（尿常规）
	 */
	private String UrinePH;
	/**
	 * 亚硝酸呀（尿常规）
	 */
	private String UrineNit;
	/**
	 * 葡萄糖（尿常规）
	 */
	private String UrineGlu;
	/**
	 * 蛋白质（尿常规）
	 */
	private String UrinePro;
	/**
	 * 维生素（尿常规）
	 */
	private String UrineVC;
	/**
	 * 比重（尿常规）
	 */
	private String UrineSG;
	/**
	 * 尿胆原（尿常规）
	 */
	private String UrineUbg;
	/**
	 * 胆红素（尿常规）
	 */
	private String UrineBil;
	/**
	 * 酮体（尿常规）
	 */
	private String UrineKet;
	/**
	 * 隐血（尿常规）
	 */
	private String UrineBld;
	/**
	 * 空腹血糖
	 */
	private Float BeforeMealFbg;
	/**
	 * 餐后血糖
	 */
	private Float AfterMealFbg;
	/**
	 * 白细胞数目
	 */
	private String WBC;
	/**
	 * 淋巴细胞数目
	 */
	private String Lymph;
	/**
	 * 中间细胞数目
	 */
	private String Mid;
	/**
	 * 中性粒细胞数目
	 */
	private String Gran;
	/**
	 * 淋巴细胞百分比
	 */
	private String LymphPercent;
	/**
	 * 中性细胞百分比
	 */
	private String MidPercent;
	/**
	 * 中性粒细胞百分比
	 */
	private String GranPercent;
	/**
	 * 红细胞数目
	 */
	private String RBC;
	/**
	 * 血红蛋白
	 */
	private String HGB;
	/**
	 * 平均红细胞体积
	 */
	private String MCV;
	/**
	 * 平均红细胞血红蛋白含量
	 */
	private String MCH;
	/**
	 * 平均红细胞血红蛋白浓度
	 */
	private String MCHC;
	/**
	 * 平红细胞分页宽度变异系数
	 */
	private String RDWCV;
	/**
	 * 平红细胞分布宽度标准差
	 */
	private String RDWSD;
	/**
	 * 红细胞压积
	 */
	private String HCT;
	/**
	 * 血小板数目
	 */
	private String PLT;
	/**
	 * 平均血小板体积
	 */
	private String MPV;
	/**
	 * 血小板分页宽度
	 */
	private String PDW;
	/**
	 * 血小板压积
	 */
	private String PCT;
	/**
	 * 大血小板比率
	 */
	private String PLCR;
	/**
	 * 白细胞分布直方图
	 */
	private String WBCHistogram;
	/**
	 * 红细胞分布直方图 
	 */
	private String RBCHistogram;
	/**
	 * 血小板分布直方图
	 */
	private String PLTHistogram;
	/**
	 * 尿机类型
	 */
	private Integer UrineType;
	/**
	 * 检验结果的单位制式
	 */
	private Integer U120Unit;
	/**
	 * 白细胞（尿常规）
	 */
	private String U120LEU;
	/**
	 * 白细胞参考（尿常规）
	 */
	private String U120LEUSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120LEUFlag;
	/**
	 * 酸碱度（尿常规）
	 */
	private String U120PH;
	/**
	 * 酸碱度参考（尿常规）
	 */
	private String U120PHSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120PHFlag;
	/**
	 * 亚硝酸盐（尿常规）
	 */
	private String U120NIT;
	/**
	 * 亚硝酸盐参考（尿常规）
	 */
	private String U120NITSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120NITFlag;
	/**
	 * 葡萄糖（尿常规）
	 */
	private String U120GLU;
	/**
	 * 葡萄糖参考（尿常规）
	 */
	private String U120GLUSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120GLUFlag;
	/**
	 * 蛋白质（尿常规）
	 */
	private String U120PRO;
	/**
	 * 蛋白质参考（尿常规）
	 */
	private String U120PROSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120PROFlag;
	/**
	 * 比重（尿常规）
	 */
	private String U120SG;
	/**
	 * 比重参考（尿常规）
	 */
	private String U120SGSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120SGFlag;
	/**
	 * 尿胆原（尿常规）
	 */
	private String U120URO;
	/**
	 * 尿胆原参考（尿常规）
	 */
	private String U120UROSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120UROFlag;
	/**
	 * 胆红素（尿常规）
	 */
	private String U120BIL;
	/**
	 * 胆红素参考（尿常规）
	 */
	private String U120BILSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120BILFlag;
	/**
	 * 酮体（尿常规）
	 */
	private String U120KET;
	/**
	 * 酮体参考（尿常规）
	 */
	private String U120KETSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120KETFlag;
	/**
	 * 隐血（尿常规）
	 */
	private String U120BLO;
	/**
	 * 隐血参考（尿常规）
	 */
	private String U120BLOSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120BLOFlag;
	/**
	 * 尿钙，只有尿常规参数为15项时才存在
	 */
	private String U120CA;
	/**
	 * 尿钙参考，只有尿常规参数为15项时才存在
	 */
	private String U120CASymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120CAFlag;
	/**
	 * 微量白蛋白
	 */
	private String U120ALBU;
	/**
	 * 微量白蛋白参考
	 */
	private String U120ALBUSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120ALBUFlag;
	/**
	 * 抗坏血酸，只有尿常规参数为15项时才存在
	 */
	private String U120ASC;
	/**
	 * 抗坏血酸参考，只有尿常规参数为15项时才存在
	 */
	private String U120ASCSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120ASCFlag;
	/**
	 * 抗坏血酸，只有尿常规参数为15项时才存在
	 */
	private String U120CRE;
	/**
	 * 抗坏血酸参考，只有尿常规参数为15项时才存在
	 */
	private String U120CRESymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120CREFlag;
	/**
	 * p:c
	 */
	private String U120PC;
	/**
	 * p:c参考
	 */
	private String U120PCSymbol;
	/**
	 * 参数值是否 正常    true:正常   false: 异常
	 */
	private boolean U120PCFlag;
	/**
	 * 血红蛋白
	 */
	private Float MissionHB;
	/**
	 * 血红蛋白单位
	 */
	private Integer MissionUnit;
	/**
	 * 红细胞压积
	 */
	private Integer MissionHCT;
	/**
	 * 
	 */
	private Integer Mean;
	/**
	 * 
	 */
	private Float cho;
	/**
	 * 
	 */
	private Float tg;
	/**
	 * 
	 */
	private Float hdl;
	/**
	 * 
	 */
	private Float ldl;
	/**
	 * 所属云实例
	 */
	private String SaaSId;
	/**
	 * 机构
	 */
	private String OrgId;
	/**
	 * 创建人
	 */
	private String Creater;
	/**
	 * 检查日期
	 */
	private Date CreateDate;
	/**
	 * 测量设备序列号
	 */
	private String DeviceSN;
	/**
	 * 数据来源1，仪器上次;2，app上传
	 */
	private Integer SourceType;
	/**
	 * 版本
	 */
	private Date Version;
	/**
	 * 是否同步
	 */
	private Integer IsSync;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getRequestNo() {
		return RequestNo;
	}
	public void setRequestNo(String requestNo) {
		RequestNo = requestNo;
	}
	public String getPersonCode() {
		return PersonCode;
	}
	public void setPersonCode(String personCode) {
		PersonCode = personCode;
	}
	public Integer getPersonType() {
		return PersonType;
	}
	public void setPersonType(Integer personType) {
		PersonType = personType;
	}
	public String getPersonName() {
		return PersonName;
	}
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	public String getEnglishName() {
		return EnglishName;
	}
	public void setEnglishName(String englishName) {
		EnglishName = englishName;
	}
	public Integer getGender() {
		return Gender;
	}
	public void setGender(Integer gender) {
		Gender = gender;
	}
	public String getIdCode() {
		return IdCode;
	}
	public void setIdCode(String idCode) {
		IdCode = idCode;
	}
	public String getArchiveCode() {
		return ArchiveCode;
	}
	public void setArchiveCode(String archiveCode) {
		ArchiveCode = archiveCode;
	}
	public String getHealthCode() {
		return HealthCode;
	}
	public void setHealthCode(String healthCode) {
		HealthCode = healthCode;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public Integer getNation() {
		return Nation;
	}
	public void setNation(Integer nation) {
		Nation = nation;
	}
	public Integer getRace() {
		return Race;
	}
	public void setRace(Integer race) {
		Race = race;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Integer getHeight() {
		return Height;
	}
	public void setHeight(Integer height) {
		Height = height;
	}
	public Float getWeight() {
		return Weight;
	}
	public void setWeight(Float weight) {
		Weight = weight;
	}
	public Float getWaistLine() {
		return WaistLine;
	}
	public void setWaistLine(Float waistLine) {
		WaistLine = waistLine;
	}
	public Float getBMI() {
		return BMI;
	}
	public void setBMI(Float bMI) {
		BMI = bMI;
	}
	public Integer getLeftSys() {
		return LeftSys;
	}
	public void setLeftSys(Integer leftSys) {
		LeftSys = leftSys;
	}
	public Integer getLeftDia() {
		return LeftDia;
	}
	public void setLeftDia(Integer leftDia) {
		LeftDia = leftDia;
	}
	public Integer getRightSys() {
		return RightSys;
	}
	public void setRightSys(Integer rightSys) {
		RightSys = rightSys;
	}
	public Integer getRightDia() {
		return RightDia;
	}
	public void setRightDia(Integer rightDia) {
		RightDia = rightDia;
	}
	public Integer getPR() {
		return PR;
	}
	public void setPR(Integer pR) {
		PR = pR;
	}
	public Integer getHR() {
		return HR;
	}
	public void setHR(Integer hR) {
		HR = hR;
	}
	public Integer getSpO2() {
		return SpO2;
	}
	public void setSpO2(Integer spO2) {
		SpO2 = spO2;
	}
	public Float getTemp() {
		return Temp;
	}
	public void setTemp(Float temp) {
		Temp = temp;
	}
	public Integer getResp() {
		return Resp;
	}
	public void setResp(Integer resp) {
		Resp = resp;
	}
	public String getEcgData() {
		return EcgData;
	}
	public void setEcgData(String ecgData) {
		EcgData = ecgData;
	}
	public String getEcgReport() {
		return EcgReport;
	}
	public void setEcgReport(String ecgReport) {
		EcgReport = ecgReport;
	}
	public String getUrineLeu() {
		return UrineLeu;
	}
	public void setUrineLeu(String urineLeu) {
		UrineLeu = urineLeu;
	}
	public String getUrinePH() {
		return UrinePH;
	}
	public void setUrinePH(String urinePH) {
		UrinePH = urinePH;
	}
	public String getUrineNit() {
		return UrineNit;
	}
	public void setUrineNit(String urineNit) {
		UrineNit = urineNit;
	}
	public String getUrineGlu() {
		return UrineGlu;
	}
	public void setUrineGlu(String urineGlu) {
		UrineGlu = urineGlu;
	}
	public String getUrinePro() {
		return UrinePro;
	}
	public void setUrinePro(String urinePro) {
		UrinePro = urinePro;
	}
	public String getUrineVC() {
		return UrineVC;
	}
	public void setUrineVC(String urineVC) {
		UrineVC = urineVC;
	}
	public String getUrineSG() {
		return UrineSG;
	}
	public void setUrineSG(String urineSG) {
		UrineSG = urineSG;
	}
	public String getUrineUbg() {
		return UrineUbg;
	}
	public void setUrineUbg(String urineUbg) {
		UrineUbg = urineUbg;
	}
	public String getUrineBil() {
		return UrineBil;
	}
	public void setUrineBil(String urineBil) {
		UrineBil = urineBil;
	}
	public String getUrineKet() {
		return UrineKet;
	}
	public void setUrineKet(String urineKet) {
		UrineKet = urineKet;
	}
	public String getUrineBld() {
		return UrineBld;
	}
	public void setUrineBld(String urineBld) {
		UrineBld = urineBld;
	}
	public Float getBeforeMealFbg() {
		return BeforeMealFbg;
	}
	public void setBeforeMealFbg(Float beforeMealFbg) {
		BeforeMealFbg = beforeMealFbg;
	}
	public Float getAfterMealFbg() {
		return AfterMealFbg;
	}
	public void setAfterMealFbg(Float afterMealFbg) {
		AfterMealFbg = afterMealFbg;
	}
	public String getWBC() {
		return WBC;
	}
	public void setWBC(String wBC) {
		WBC = wBC;
	}
	public String getLymph() {
		return Lymph;
	}
	public void setLymph(String lymph) {
		Lymph = lymph;
	}
	public String getMid() {
		return Mid;
	}
	public void setMid(String mid) {
		Mid = mid;
	}
	public String getGran() {
		return Gran;
	}
	public void setGran(String gran) {
		Gran = gran;
	}
	public String getLymphPercent() {
		return LymphPercent;
	}
	public void setLymphPercent(String lymphPercent) {
		LymphPercent = lymphPercent;
	}
	public String getMidPercent() {
		return MidPercent;
	}
	public void setMidPercent(String midPercent) {
		MidPercent = midPercent;
	}
	public String getGranPercent() {
		return GranPercent;
	}
	public void setGranPercent(String granPercent) {
		GranPercent = granPercent;
	}
	public String getRBC() {
		return RBC;
	}
	public void setRBC(String rBC) {
		RBC = rBC;
	}
	public String getHGB() {
		return HGB;
	}
	public void setHGB(String hGB) {
		HGB = hGB;
	}
	public String getMCV() {
		return MCV;
	}
	public void setMCV(String mCV) {
		MCV = mCV;
	}
	public String getMCH() {
		return MCH;
	}
	public void setMCH(String mCH) {
		MCH = mCH;
	}
	public String getMCHC() {
		return MCHC;
	}
	public void setMCHC(String mCHC) {
		MCHC = mCHC;
	}
	public String getRDWCV() {
		return RDWCV;
	}
	public void setRDWCV(String rDWCV) {
		RDWCV = rDWCV;
	}
	public String getRDWSD() {
		return RDWSD;
	}
	public void setRDWSD(String rDWSD) {
		RDWSD = rDWSD;
	}
	public String getHCT() {
		return HCT;
	}
	public void setHCT(String hCT) {
		HCT = hCT;
	}
	public String getPLT() {
		return PLT;
	}
	public void setPLT(String pLT) {
		PLT = pLT;
	}
	public String getMPV() {
		return MPV;
	}
	public void setMPV(String mPV) {
		MPV = mPV;
	}
	public String getPDW() {
		return PDW;
	}
	public void setPDW(String pDW) {
		PDW = pDW;
	}
	public String getPCT() {
		return PCT;
	}
	public void setPCT(String pCT) {
		PCT = pCT;
	}
	public String getPLCR() {
		return PLCR;
	}
	public void setPLCR(String pLCR) {
		PLCR = pLCR;
	}
	public String getWBCHistogram() {
		return WBCHistogram;
	}
	public void setWBCHistogram(String wBCHistogram) {
		WBCHistogram = wBCHistogram;
	}
	public String getRBCHistogram() {
		return RBCHistogram;
	}
	public void setRBCHistogram(String rBCHistogram) {
		RBCHistogram = rBCHistogram;
	}
	public String getPLTHistogram() {
		return PLTHistogram;
	}
	public void setPLTHistogram(String pLTHistogram) {
		PLTHistogram = pLTHistogram;
	}
	public Integer getUrineType() {
		return UrineType;
	}
	public void setUrineType(Integer urineType) {
		UrineType = urineType;
	}
	public Integer getU120Unit() {
		return U120Unit;
	}
	public void setU120Unit(Integer u120Unit) {
		U120Unit = u120Unit;
	}
	public String getU120LEU() {
		return U120LEU;
	}
	public void setU120LEU(String u120leu) {
		U120LEU = u120leu;
	}
	public String getU120LEUSymbol() {
		return U120LEUSymbol;
	}
	public void setU120LEUSymbol(String u120leuSymbol) {
		U120LEUSymbol = u120leuSymbol;
	}
	public boolean isU120LEUFlag() {
		return U120LEUFlag;
	}
	public void setU120LEUFlag(boolean u120leuFlag) {
		U120LEUFlag = u120leuFlag;
	}
	public String getU120PH() {
		return U120PH;
	}
	public void setU120PH(String u120ph) {
		U120PH = u120ph;
	}
	public String getU120PHSymbol() {
		return U120PHSymbol;
	}
	public void setU120PHSymbol(String u120phSymbol) {
		U120PHSymbol = u120phSymbol;
	}
	public boolean isU120PHFlag() {
		return U120PHFlag;
	}
	public void setU120PHFlag(boolean u120phFlag) {
		U120PHFlag = u120phFlag;
	}
	public String getU120NIT() {
		return U120NIT;
	}
	public void setU120NIT(String u120nit) {
		U120NIT = u120nit;
	}
	public String getU120NITSymbol() {
		return U120NITSymbol;
	}
	public void setU120NITSymbol(String u120nitSymbol) {
		U120NITSymbol = u120nitSymbol;
	}
	public boolean isU120NITFlag() {
		return U120NITFlag;
	}
	public void setU120NITFlag(boolean u120nitFlag) {
		U120NITFlag = u120nitFlag;
	}
	public String getU120GLU() {
		return U120GLU;
	}
	public void setU120GLU(String u120glu) {
		U120GLU = u120glu;
	}
	public String getU120GLUSymbol() {
		return U120GLUSymbol;
	}
	public void setU120GLUSymbol(String u120gluSymbol) {
		U120GLUSymbol = u120gluSymbol;
	}
	public boolean isU120GLUFlag() {
		return U120GLUFlag;
	}
	public void setU120GLUFlag(boolean u120gluFlag) {
		U120GLUFlag = u120gluFlag;
	}
	public String getU120PRO() {
		return U120PRO;
	}
	public void setU120PRO(String u120pro) {
		U120PRO = u120pro;
	}
	public String getU120PROSymbol() {
		return U120PROSymbol;
	}
	public void setU120PROSymbol(String u120proSymbol) {
		U120PROSymbol = u120proSymbol;
	}
	public boolean isU120PROFlag() {
		return U120PROFlag;
	}
	public void setU120PROFlag(boolean u120proFlag) {
		U120PROFlag = u120proFlag;
	}
	public String getU120SG() {
		return U120SG;
	}
	public void setU120SG(String u120sg) {
		U120SG = u120sg;
	}
	public String getU120SGSymbol() {
		return U120SGSymbol;
	}
	public void setU120SGSymbol(String u120sgSymbol) {
		U120SGSymbol = u120sgSymbol;
	}
	public boolean isU120SGFlag() {
		return U120SGFlag;
	}
	public void setU120SGFlag(boolean u120sgFlag) {
		U120SGFlag = u120sgFlag;
	}
	public String getU120URO() {
		return U120URO;
	}
	public void setU120URO(String u120uro) {
		U120URO = u120uro;
	}
	public String getU120UROSymbol() {
		return U120UROSymbol;
	}
	public void setU120UROSymbol(String u120uroSymbol) {
		U120UROSymbol = u120uroSymbol;
	}
	public boolean isU120UROFlag() {
		return U120UROFlag;
	}
	public void setU120UROFlag(boolean u120uroFlag) {
		U120UROFlag = u120uroFlag;
	}
	public String getU120BIL() {
		return U120BIL;
	}
	public void setU120BIL(String u120bil) {
		U120BIL = u120bil;
	}
	public String getU120BILSymbol() {
		return U120BILSymbol;
	}
	public void setU120BILSymbol(String u120bilSymbol) {
		U120BILSymbol = u120bilSymbol;
	}
	public boolean isU120BILFlag() {
		return U120BILFlag;
	}
	public void setU120BILFlag(boolean u120bilFlag) {
		U120BILFlag = u120bilFlag;
	}
	public String getU120KET() {
		return U120KET;
	}
	public void setU120KET(String u120ket) {
		U120KET = u120ket;
	}
	public String getU120KETSymbol() {
		return U120KETSymbol;
	}
	public void setU120KETSymbol(String u120ketSymbol) {
		U120KETSymbol = u120ketSymbol;
	}
	public boolean isU120KETFlag() {
		return U120KETFlag;
	}
	public void setU120KETFlag(boolean u120ketFlag) {
		U120KETFlag = u120ketFlag;
	}
	public String getU120BLO() {
		return U120BLO;
	}
	public void setU120BLO(String u120blo) {
		U120BLO = u120blo;
	}
	public String getU120BLOSymbol() {
		return U120BLOSymbol;
	}
	public void setU120BLOSymbol(String u120bloSymbol) {
		U120BLOSymbol = u120bloSymbol;
	}
	public boolean isU120BLOFlag() {
		return U120BLOFlag;
	}
	public void setU120BLOFlag(boolean u120bloFlag) {
		U120BLOFlag = u120bloFlag;
	}
	public String getU120CA() {
		return U120CA;
	}
	public void setU120CA(String u120ca) {
		U120CA = u120ca;
	}
	public String getU120CASymbol() {
		return U120CASymbol;
	}
	public void setU120CASymbol(String u120caSymbol) {
		U120CASymbol = u120caSymbol;
	}
	public boolean isU120CAFlag() {
		return U120CAFlag;
	}
	public void setU120CAFlag(boolean u120caFlag) {
		U120CAFlag = u120caFlag;
	}
	public String getU120ALBU() {
		return U120ALBU;
	}
	public void setU120ALBU(String u120albu) {
		U120ALBU = u120albu;
	}
	public String getU120ALBUSymbol() {
		return U120ALBUSymbol;
	}
	public void setU120ALBUSymbol(String u120albuSymbol) {
		U120ALBUSymbol = u120albuSymbol;
	}
	public boolean isU120ALBUFlag() {
		return U120ALBUFlag;
	}
	public void setU120ALBUFlag(boolean u120albuFlag) {
		U120ALBUFlag = u120albuFlag;
	}
	public String getU120ASC() {
		return U120ASC;
	}
	public void setU120ASC(String u120asc) {
		U120ASC = u120asc;
	}
	public String getU120ASCSymbol() {
		return U120ASCSymbol;
	}
	public void setU120ASCSymbol(String u120ascSymbol) {
		U120ASCSymbol = u120ascSymbol;
	}
	public boolean isU120ASCFlag() {
		return U120ASCFlag;
	}
	public void setU120ASCFlag(boolean u120ascFlag) {
		U120ASCFlag = u120ascFlag;
	}
	public String getU120CRE() {
		return U120CRE;
	}
	public void setU120CRE(String u120cre) {
		U120CRE = u120cre;
	}
	public String getU120CRESymbol() {
		return U120CRESymbol;
	}
	public void setU120CRESymbol(String u120creSymbol) {
		U120CRESymbol = u120creSymbol;
	}
	public boolean isU120CREFlag() {
		return U120CREFlag;
	}
	public void setU120CREFlag(boolean u120creFlag) {
		U120CREFlag = u120creFlag;
	}
	public String getU120PC() {
		return U120PC;
	}
	public void setU120PC(String u120pc) {
		U120PC = u120pc;
	}
	public String getU120PCSymbol() {
		return U120PCSymbol;
	}
	public void setU120PCSymbol(String u120pcSymbol) {
		U120PCSymbol = u120pcSymbol;
	}
	public boolean isU120PCFlag() {
		return U120PCFlag;
	}
	public void setU120PCFlag(boolean u120pcFlag) {
		U120PCFlag = u120pcFlag;
	}
	public Float getMissionHB() {
		return MissionHB;
	}
	public void setMissionHB(Float missionHB) {
		MissionHB = missionHB;
	}
	public Integer getMissionUnit() {
		return MissionUnit;
	}
	public void setMissionUnit(Integer missionUnit) {
		MissionUnit = missionUnit;
	}
	public Integer getMissionHCT() {
		return MissionHCT;
	}
	public void setMissionHCT(Integer missionHCT) {
		MissionHCT = missionHCT;
	}
	public Integer getMean() {
		return Mean;
	}
	public void setMean(Integer mean) {
		Mean = mean;
	}
	public Float getCho() {
		return cho;
	}
	public void setCho(Float cho) {
		this.cho = cho;
	}
	public Float getTg() {
		return tg;
	}
	public void setTg(Float tg) {
		this.tg = tg;
	}
	public Float getHdl() {
		return hdl;
	}
	public void setHdl(Float hdl) {
		this.hdl = hdl;
	}
	public Float getLdl() {
		return ldl;
	}
	public void setLdl(Float ldl) {
		this.ldl = ldl;
	}
	public String getSaaSId() {
		return SaaSId;
	}
	public void setSaaSId(String saaSId) {
		SaaSId = saaSId;
	}
	public String getOrgId() {
		return OrgId;
	}
	public void setOrgId(String orgId) {
		OrgId = orgId;
	}
	public String getCreater() {
		return Creater;
	}
	public void setCreater(String creater) {
		Creater = creater;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public String getDeviceSN() {
		return DeviceSN;
	}
	public void setDeviceSN(String deviceSN) {
		DeviceSN = deviceSN;
	}
	public Integer getSourceType() {
		return SourceType;
	}
	public void setSourceType(Integer sourceType) {
		SourceType = sourceType;
	}
	public Date getVersion() {
		return Version;
	}
	public void setVersion(Date version) {
		Version = version;
	}
	public Integer getIsSync() {
		return IsSync;
	}
	public void setIsSync(Integer isSync) {
		IsSync = isSync;
	}
	
	
	
}

