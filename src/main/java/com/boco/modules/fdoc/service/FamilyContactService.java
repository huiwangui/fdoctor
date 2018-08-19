package com.boco.modules.fdoc.service;

import com.boco.modules.fdoc.model.FamilyContactInfoEntity;
import com.boco.modules.fdoc.vo.FamilyContactInfoVo;

/**
 * Description 家庭联系方式服务层
 * @author lzz
 * @date 2017年7月12日 下午3:19:14
 */
public interface FamilyContactService {

	/**
	 * Title FamilyContactService.java
	 * Description: 新增家庭联系方式
	 * @param fVo
	 * @return
	 * @author lzz
	 */
	public int addFamilyContact(FamilyContactInfoVo fVo);

	/**
	 * 
	 * Title FamilyContactService.java
	 * Description: 修改家庭联系方式
	 * @param fVo
	 * @return
	 * @author lzz
	 */
	public int updateFamilyContact(FamilyContactInfoVo fVo);

	/**
	 * Title FamilyContactService.java
	 * Description: 获取家庭联系方式
	 * @param idCard
	 * @return
	 * @author lzz
	 */
	public FamilyContactInfoEntity getContactInfo(String familyId);

	/**
	 * Title FamilyContactService.java
	 * Description: 删除家庭联系方式
	 * @param idCard
	 * @return
	 * @author lzz
	 */
	public int deleteContactInfo(String familyid);

}
