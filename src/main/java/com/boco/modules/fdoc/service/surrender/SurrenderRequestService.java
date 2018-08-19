/**
 * @ClassName: SurrenderRequestService 
 * Description:
 * @author h
 * @date  2017年11月29日下午3:19:20
 * @version 1.0
 *  
 */
package com.boco.modules.fdoc.service.surrender;

import java.util.List;

import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;

/**
 * Tilte: SurrenderRequestService 
 * Description:
 * @author h
 * @date  2017年11月29日下午3:19:20
 * @version 1.0
 *  
 */
public interface SurrenderRequestService {

	/**
	 * Tilte: insert 
	 * Description:解约申请
	 * @param entity
	 * @return int
	 * @author h
	 */
	int insert(SurrenderRequestEntity entity);

	/**
	 * Tilte: getSurrenderRequestVoListByPersonIdAndStatus 
	 * Description:根据personid和status获取签约详情列表
	 * @param vo
	 * @return List<SurrenderRequestVo>
	 * @author h
	 */
	List<SurrenderRequestVo> getSurrenderRequestVoListByPersonIdAndStatus(SurrenderRequestVo vo);

	/**
	 * Tilte: getSurrenderRequestCountByDocTeamId 
	 * Description:查询解约记录总数
	 * @param docTeamId
	 * @return int
	 * @author h
	 */
	int getSurrenderRequestCountByDocTeamId(String docTeamId);

	/**
	 * Tilte: getSurrenderDetailByPersonId 
	 * Description:获取解约详情
	 * @param vo
	 * @return SurrenderRequestVo
	 * @author h
	 */
	SurrenderRequestVo getSurrenderDetailByPersonId(SurrenderRequestEntity vo);

	/**
	 * Tilte: cancelSurrenderDetailByPersonId 
	 * Description:取消解约申请	
	 * @param vo
	 * @return int
	 * @author h
	 */
	int cancelSurrenderDetailByPersonId(SurrenderRequestEntity vo);

	/**
	 * Tilte: getSurrenderRequestVoListCountByPersonIdAndStatus 
	 * Description:获取签约详情列表总数
	 * @param vo
	 * @return int
	 * @author h
	 */
	int getSurrenderRequestVoListCountByPersonIdAndStatus(SurrenderRequestVo vo);
}
