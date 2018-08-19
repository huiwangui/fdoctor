/**
 * @ClassName: SurrenderRequestVo 
 * Description:
 * @author h
 * @date  2017年11月30日上午9:31:27
 * @version 1.0
 *  
 */
package com.boco.modules.fdoc.vo.surrender;

import java.util.Date;
import java.util.List;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.expert.ExpertInformationVo;

/**
 * Tilte: SurrenderRequestVo 
 * Description: 解约请求值类
 * @author h
 * @date  2017年11月30日上午9:31:27
 * @version 1.0
 *  
 */
/**
 * Tilte: SurrenderRequestVo 
 * Description:
 * @author h
 * @date  2017年11月30日上午9:32:51
 * @version 1.0
 *  
 */
public class SurrenderRequestVo extends SurrenderRequestEntity {

	/**
	 * 签约时间
	 */
	public Date signTime;
	/**
	 * 分页对象
	 */
	private Page<SurrenderRequestVo> page;
	
	 
    /**
     * 医生分类:4 专家
     */
    private String expertType;
    
    /**
     * 专家姓名
     */
    private String expertName;
    /**
     * 简介
     */
    private String expertIntroduction;
    /**
     * 头像
     */
    private String expertImg;
    
    /**
     * 医生团队
     */
    private List<DoctorDetailVo> docList;
    
    /**
     * 服务包权值
     */
    private int servicePackValue;
    
    /**
     * 服务包年限
     */
    private int servicePackOfYear;
    
    

	public int getServicePackOfYear() {
		return servicePackOfYear;
	}

	public void setServicePackOfYear(int servicePackOfYear) {
		this.servicePackOfYear = servicePackOfYear;
	}

	public int getServicePackValue() {
		return servicePackValue;
	}

	public void setServicePackValue(int servicePackValue) {
		this.servicePackValue = servicePackValue;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Page<SurrenderRequestVo> getPage() {
		return page;
	}

	public void setPage(Page<SurrenderRequestVo> page) {
		this.page = page;
	}

	 

	public String getExpertType() {
		return expertType;
	}

	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getExpertIntroduction() {
		return expertIntroduction;
	}

	public void setExpertIntroduction(String expertIntroduction) {
		this.expertIntroduction = expertIntroduction;
	}

	public String getExpertImg() {
		return expertImg;
	}

	public void setExpertImg(String expertImg) {
		this.expertImg = expertImg;
	}

	public List<DoctorDetailVo> getDocList() {
		return docList;
	}

	public void setDocList(List<DoctorDetailVo> docList) {
		this.docList = docList;
	}

	 
	
}
