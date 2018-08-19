 
package com.boco.modules.fdoc.service.surrender;

import java.util.List;

import com.boco.modules.fdoc.model.surrender.SurrenderReasonEntity;

 
/**
 * Tilte: SurrenderReasonService 
 * Description:
 * @author h
 * @date  2017年11月30日上午11:20:04
 * @version 1.0
 *  
 */
public interface SurrenderReasonService {

	/**
	 * 获取所有解约原因
	 * @return
	 */
	List<SurrenderReasonEntity> findAllReasonList();

	 
}
