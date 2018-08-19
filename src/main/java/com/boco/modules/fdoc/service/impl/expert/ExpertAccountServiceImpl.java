package com.boco.modules.fdoc.service.impl.expert;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.expert.ExpertAccountDao;
import com.boco.modules.fdoc.model.expert.ExpertAccountEntity;
import com.boco.modules.fdoc.service.expert.ExpertAccountService;
/**
 * Tilte: ExpertAccountServiceImpl 
 * Description:
 * @author h
 * @date  2017年9月22日下午4:22:20
 * @version 1.0
 *  
 */
@Service
public class ExpertAccountServiceImpl implements ExpertAccountService {
  
	@Resource
	ExpertAccountDao expertAccountDao;
	 
 
	 
	 
	@Override
	public ExpertAccountEntity verifyAccount(ExpertAccountEntity account) {
		return expertAccountDao.verifyAccount(account); 
		
	}

}
