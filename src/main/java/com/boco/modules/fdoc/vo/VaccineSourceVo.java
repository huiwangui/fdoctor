package com.boco.modules.fdoc.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VaccineSourceVo {
	private int id;  
	private Integer vaccineId;  //疫苗ID
	private Integer vaccineDetailId;//疫苗详细情况ID
	private Date inoDate;  //接种日期
	private Integer docId;  //医生ID
	private String docUid; //医生登录UID
	private Long timeStamp; //接种日期时间戳
	private Integer vaccineNum; // 疫苗数量
	private Integer weekDay;  //星期
	private Date beginDate; //开始日期
	private Date endDate;   //结束日期
	private String status;  //状态  1.已发布  2.已过期
	private String vaccineName; //疫苗名称
	private String hospName; //医院名称
	private String delFlag;  //删除状态  0.未删除  1.已删除
	private String weekDate;  //星期的中文字符串显示
	private String workDate; //日期的字符串显示
	private Integer number; //疫苗数量返回值
	private Integer hospId;	//接种医院
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
	}
	public Integer getVaccineDetailId() {
		return vaccineDetailId;
	}
	public void setVaccineDetailId(Integer vaccineDetailId) {
		this.vaccineDetailId = vaccineDetailId;
	}
	public Date getInoDate() {
		return inoDate;
	}
	public void setInoDate(Date inoDate) {
		this.inoDate = inoDate;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public String getDocUid() {
		return docUid;
	}
	public void setDocUid(String docUid) {
		this.docUid = docUid;
	}
	
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Integer getVaccineNum() {
		return vaccineNum;
	}
	public void setVaccineNum(Integer vaccineNum) {
		this.vaccineNum = vaccineNum;
	}
	public Integer getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getWeekDate() {
		return weekDate;
	}
	public void setWeekDate(int weekDay) {
		if (weekDay == 1) {
			this.weekDate = "周一";
		}else if (weekDay == 2) {
			this.weekDate = "周二";
		}else if (weekDay == 3) {
			this.weekDate = "周三";
		}else if (weekDay == 4) {
			this.weekDate = "周四";
		}else if (weekDay == 5) {
			this.weekDate = "周五";
		}else if (weekDay == 6) {
			this.weekDate = "周六";
		}else if (weekDay == 7) {
			this.weekDate = "周日";
		}
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Long timeStamp) {
		Date date = new Date(timeStamp);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
		String dateString = formatter.format(date);
		this.workDate = dateString;
	}
	public Integer getHospId() {
		return hospId;
	}
	public void setHospId(Integer hospId) {
		this.hospId = hospId;
	}
	
	
}
