/**
 * @ClassName: ExpertInformationVo 
 * Description:
 * @author h
 * @date  2017年9月22日下午4:55:48
 * @version 1.0
 *  
 */
package com.boco.modules.fdoc.vo.expert;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.model.expert.ExpertInformationEntity;
import com.boco.modules.fdoc.vo.PersonInformationVo;

/**
 * Tilte: ExpertInformationVo 
 * Description:
 * @author h
 * @date  2017年9月22日下午4:55:48
 * @version 1.0
 *  
 */
public class ExpertInformationVo extends ExpertInformationEntity {

	/**
	 * 机构名称
	 */
	private String orgName;
	
	/**
	 * 居民姓名
	 */
	private String personName;
	/**
	 * 居民id
	 */
	private String personId;
	
	/**
	 * 专家登录名称
	 */
	private String userName;
	

	/**
	 * 分页对象
	 */
	private Page<ExpertInformationVo> page;
	
	/**
	 * 4:表示专家
	 */
	private String docType;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
     
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Page<ExpertInformationVo> getPage() {
		return page;
	}

	public void setPage(Page<ExpertInformationVo> page) {
		this.page = page;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
	
}
