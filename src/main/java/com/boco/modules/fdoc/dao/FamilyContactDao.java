package com.boco.modules.fdoc.dao;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.FamilyContactInfoEntity;
import com.boco.modules.fdoc.vo.FamilyContactInfoVo;

/**
 * Description 家庭联系方式dao层
 * @author lzz
 * @date 2017年7月12日 下午3:21:56
 */

@MyBatisDao
public interface FamilyContactDao extends CrudDao<FamilyContactInfoEntity>{

	/**
	 * Title FamilyContactDao.java
	 * Description: 新增家庭联系方式
	 * @param fVo
	 * @return
	 * @author lzz
	 */
	public int addFamilyContact(FamilyContactInfoVo fVo);

	/**
	 * Title FamilyContactDao.java
	 * Description: 修改家庭联系方式
	 * @param fVo
	 * @return
	 * @author lzz
	 */
	public int updateFamilyContact(FamilyContactInfoVo fVo);

	/**
	 * Title FamilyContactDao.java
	 * Description: 获取家庭联系方式
	 * @param familyId
	 * @return
	 * @author lzz
	 */
	public FamilyContactInfoEntity getContactInfo(String familyId);

	/**
	 * Title FamilyContactDao.java
	 * Description: 删除家庭联系方式
	 * @param familyId
	 * @return
	 * @author lzz
	 */
	public int deleteContactInfo(String familyId);

}
