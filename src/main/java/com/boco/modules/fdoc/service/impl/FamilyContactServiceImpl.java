package com.boco.modules.fdoc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.FamilyContactDao;
import com.boco.modules.fdoc.dao.PersonInformationDao;
import com.boco.modules.fdoc.model.FamilyContactInfoEntity;
import com.boco.modules.fdoc.service.FamilyContactService;
import com.boco.modules.fdoc.vo.FamilyContactInfoVo;

/**
 * Description 家庭联系方式服务层
 * @author lzz
 * @date 2017年7月12日 下午3:20:08
 */

@Service
public class FamilyContactServiceImpl implements FamilyContactService {

	@Resource
	FamilyContactDao contactDao;
	@Resource
	PersonInformationDao personDao;
	
	@Override
	public int addFamilyContact(FamilyContactInfoVo fVo) {
		String idCard=fVo.getIdCard();
		String familyId=personDao.getFamilyId(idCard);
		fVo.setFamilyId(familyId);
		
		return contactDao.addFamilyContact(fVo);
	}

	@Override
	public int updateFamilyContact(FamilyContactInfoVo fVo) {
		String idCard=fVo.getIdCard();
		String familyId=personDao.getFamilyId(idCard);
		fVo.setFamilyId(familyId);
		return contactDao.updateFamilyContact(fVo);
	}

	@Override
	public FamilyContactInfoEntity getContactInfo(String familyId) {
		return contactDao.getContactInfo(familyId);
	}

	@Override
	public int deleteContactInfo(String familyId) {
		return contactDao.deleteContactInfo(familyId);
	}

}
