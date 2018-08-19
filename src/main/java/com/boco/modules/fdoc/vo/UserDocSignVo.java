package com.boco.modules.fdoc.vo;

public class UserDocSignVo {
	private int id;

    /**
    * 签约医生在基础服务平台中用户id
    */
    private String docId;

    /**
    * 签约医生姓名
    */
    private String docName;

    /**
    * 团队ID
    */
    private Integer docTeamId;

    /**
    * 医生所属机构名称
    */
    private String docOrgName;

    /**
    * 医生所属机构地址
    */
    private String docOrgAddress;

    /**
    * 签约对象身份证号
    */
    private String healthFileCode;

    /**
    * 签约图片
    */
    private String signImg;

    /**
    * 签约状态：1.签约有效期内 2.待续约 3.已解约
    */
    private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Integer getDocTeamId() {
		return docTeamId;
	}

	public void setDocTeamId(Integer docTeamId) {
		this.docTeamId = docTeamId;
	}

	public String getDocOrgName() {
		return docOrgName;
	}

	public void setDocOrgName(String docOrgName) {
		this.docOrgName = docOrgName;
	}

	public String getDocOrgAddress() {
		return docOrgAddress;
	}

	public void setDocOrgAddress(String docOrgAddress) {
		this.docOrgAddress = docOrgAddress;
	}

	public String getHealthFileCode() {
		return healthFileCode;
	}

	public void setHealthFileCode(String healthFileCode) {
		this.healthFileCode = healthFileCode;
	}

	public String getSignImg() {
		return signImg;
	}

	public void setSignImg(String signImg) {
		this.signImg = signImg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
