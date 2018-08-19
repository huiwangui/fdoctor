/**
 * @ClassName: SurrenderReasonServiceImpl 
 * Description:
 * @author h
 * @date  2017年11月30日上午11:21:17
 * @version 1.0
 *  
 */
package com.boco.modules.fdoc.service.impl.surrender;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.modules.fdoc.dao.surrender.SurrenderReasonDao;
import com.boco.modules.fdoc.model.surrender.SurrenderReasonEntity;
import com.boco.modules.fdoc.service.surrender.SurrenderReasonService;

/**
 * Tilte: SurrenderReasonServiceImpl 
 * Description:
 * @author h
 * @date  2017年11月30日上午11:21:17
 * @version 1.0
 *  
 */
@Service
public class SurrenderReasonServiceImpl implements SurrenderReasonService {
    @Resource
	SurrenderReasonDao surrenderReasonDao;
	@Override
	public List<SurrenderReasonEntity> findAllReasonList() {
		 
		return surrenderReasonDao.findAllReasonList();
	}

}
