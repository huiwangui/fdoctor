package com.boco.modules.fdoc.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.utils.NumberUtils;

public class PersonDeseaseInfoEntity {
    /**
    * 主键ID
    */
    private String personId;

    /**
    * 是否为高血压  0.否  1.是
    */
    private String hyperFlag = BusinessConstants.NO;

    /**
    * 是否为糖尿病  0.否  1.是
    */
    private String diabetesFlag = BusinessConstants.NO;

    /**
    * 是否为儿童  0.否  1.是
    */
    private String childFlag = BusinessConstants.NO;
    /**
     * 是否为重性精神病  0.否  1.是
     */
     private String majorPsychosisFlag = BusinessConstants.NO;

    /**
    * 是否为老年人  0.否  1.是
    */
    private String oldmFlag = BusinessConstants.NO;
    /**
     * 是否为普通成人  0.否  1.是
     */
    private String adultFlag = BusinessConstants.NO;

	/**
     * 是否为孕产妇  0.否  1.是
     */
    private String maternalFlag = BusinessConstants.NO;
    
  //新增字段
    /**
     * 是否为特困户   0.否  1.是
     */
    private String poorFlag=BusinessConstants.NO;
    /**
     * 是否为残疾人   0.否  1.是
     */
    private String disabledFlag=BusinessConstants.NO;
    /**
     * 是否为低保   0.否  1.是
     */
    private String subsistenceFlag=BusinessConstants.NO;
    /**
     * 是否为五保   0.否  1.是
     */
    private String fiveproFlag=BusinessConstants.NO;
    /**
     * 是否为计生特户   0.否  1.是
     */
    private String familyplanFlag=BusinessConstants.NO;
    /**
     * 是否为结核病   0.否  1.是
     */
    private String tuberculosisFlag=BusinessConstants.NO;
    /**
     * 是否为流动人口  0.否  1.是
     */
    private String floatFlag=BusinessConstants.NO;
    
    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 最后一次修改时间
    */
    private Date updateTime;
    
    
	public PersonDeseaseInfoEntity(){
    	
    }
    
    public PersonDeseaseInfoEntity(String personId, String hyperFlag,String diabetesFlag,String childFlag,String majorPsychosisFlag ){
    	this.personId = personId;
    	this.hyperFlag = hyperFlag;
    	this.diabetesFlag = diabetesFlag;
    	this.childFlag = childFlag;
    	this.majorPsychosisFlag = majorPsychosisFlag;
    }
    
    public PersonDeseaseInfoEntity(String personId, Integer value){
    	this.personId = personId;
    	List<Integer> bitandArray = NumberUtils.bitandArray(value);
    	for (Integer integer : bitandArray) {
			switch (integer) {
			case 2:
				this.hyperFlag = BusinessConstants.YES;
				break;
			case 4:
				this.diabetesFlag = BusinessConstants.YES;	
				break;
			case 8:
				this.childFlag = BusinessConstants.YES;
				break;
			case 16:
				this.majorPsychosisFlag = BusinessConstants.YES;
				break;
			case 32:
				this.oldmFlag = BusinessConstants.YES;
				break;
			case 64:
				this.adultFlag = BusinessConstants.YES;
				break;
			case 128:
				this.maternalFlag = BusinessConstants.YES;
				break;
			case 256:
				this.poorFlag = BusinessConstants.YES;
				break;
			case 512:
				this.disabledFlag = BusinessConstants.YES;
				break;
			case 1024:
				this.subsistenceFlag = BusinessConstants.YES;
				break;
			case 2048:
				this.fiveproFlag = BusinessConstants.YES;
				break;
			case 4096:
				this.familyplanFlag = BusinessConstants.YES;
				break;
			case 8192:
				this.tuberculosisFlag = BusinessConstants.YES;
				break;
			case 16384:
				this.floatFlag = BusinessConstants.YES;
				break;	
			default:
				break;
			}
		}
    }

   

	public PersonDeseaseInfoEntity(String personId, String hyperFlag, String diabetesFlag, String childFlag,
			String majorPsychosisFlag, String oldmFlag, String adultFlag, String maternalFlag) {
		super();
		this.personId = personId;
		this.hyperFlag = hyperFlag;
		this.diabetesFlag = diabetesFlag;
		this.childFlag = childFlag;
		this.majorPsychosisFlag = majorPsychosisFlag;
		this.oldmFlag = oldmFlag;
		this.adultFlag = adultFlag;
		this.maternalFlag = maternalFlag;
	}

	public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getHyperFlag() {
        return hyperFlag;
    }

    public void setHyperFlag(String hyperFlag) {
        this.hyperFlag = hyperFlag;
    }

    public String getDiabetesFlag() {
        return diabetesFlag;
    }

    public void setDiabetesFlag(String diabetesFlag) {
        this.diabetesFlag = diabetesFlag;
    }

    public String getChildFlag() {
        return childFlag;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    public String getMajorPsychosisFlag() {
        return majorPsychosisFlag;
    }

    public void setMajorPsychosisFlag(String majorPsychosisFlag) {
        this.majorPsychosisFlag = majorPsychosisFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getOldmFlag() {
		return oldmFlag;
	}

	public void setOldmFlag(String oldmFlag) {
		this.oldmFlag = oldmFlag;
	}

	public String getAdultFlag() {
		return adultFlag;
	}

	public void setAdultFlag(String adultFlag) {
		this.adultFlag = adultFlag;
	}

	public String getMaternalFlag() {
		return maternalFlag;
	}

	public void setMaternalFlag(String maternalFlag) {
		this.maternalFlag = maternalFlag;
	}

	public String getPoorFlag() {
		return poorFlag;
	}

	public void setPoorFlag(String poorFlag) {
		this.poorFlag = poorFlag;
	}

	public String getDisabledFlag() {
		return disabledFlag;
	}

	public void setDisabledFlag(String disabledFlag) {
		this.disabledFlag = disabledFlag;
	}

	public String getSubsistenceFlag() {
		return subsistenceFlag;
	}

	public void setSubsistenceFlag(String subsistenceFlag) {
		this.subsistenceFlag = subsistenceFlag;
	}

	public String getFiveproFlag() {
		return fiveproFlag;
	}

	public void setFiveproFlag(String fiveproFlag) {
		this.fiveproFlag = fiveproFlag;
	}

	public String getFamilyplanFlag() {
		return familyplanFlag;
	}

	public void setFamilyplanFlag(String familyplanFlag) {
		this.familyplanFlag = familyplanFlag;
	}

	public String getTuberculosisFlag() {
		return tuberculosisFlag;
	}

	public void setTuberculosisFlag(String tuberculosisFlag) {
		this.tuberculosisFlag = tuberculosisFlag;
	}

	public String getFloatFlag() {
		return floatFlag;
	}

	public void setFloatFlag(String floatFlag) {
		this.floatFlag = floatFlag;
	}
	
	

}
