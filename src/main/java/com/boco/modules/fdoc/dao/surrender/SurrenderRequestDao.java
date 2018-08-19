package com.boco.modules.fdoc.dao.surrender;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;

@MyBatisDao
public interface SurrenderRequestDao extends CrudDao<SurrenderRequestEntity>{
	 /**
	 * Tilte: updateSurrenderRequestById 
	 * Description:根据解约请求表的主键修改
	 * @return int
	 * @author h
	 */
	public int updateSurrenderRequestById(SurrenderRequestEntity entity);
	
	public SurrenderRequestEntity getSurrenderRequestByPersonId(String personId);
	/**
	 * Tilte: getSurrenderRequestVoListByPersonId 
	 * Description:根据personId查询 审核中 、拒绝解约 、已解约记录
	 * @param personId
	 * @return List<SurrenderRequestVo>
	 * @author h
	 */
	public List<SurrenderRequestVo> getSurrenderRequestVoListByPersonId(SurrenderRequestVo vo);
	/**
	 * Tilte: getNoSignSurrenderRequestVoListByPersonId 
	 * Description:查询以往的已解约、解约申请被拒绝的记录
	 * @param personId
	 * @return List<SurrenderRequestVo>
	 * @author h
	 */
	public List<SurrenderRequestVo> getNoSignSurrenderRequestVoListByPersonId(SurrenderRequestVo vo);
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
	 * Tilte: getSurrenderDetailAndServicePackValueByPersonId 
	 * Description:根据personId查询 已解约详情及服务包权值 
	 * @param vo
	 * @return SurrenderRequestVo
	 * @author h
	 */
	SurrenderRequestVo getSurrenderDetailAndServicePackValueByPersonId(SurrenderRequestEntity vo);
	/**
	 * Tilte: getSignSurrenderDetailAndServicePackValueByPersonId 
	 * Description:根据personId查询 签约详情及服务包权值
	 * @param vo
	 * @return SurrenderRequestVo
	 * @author h
	 */
	SurrenderRequestVo getSignSurrenderDetailAndServicePackValueByPersonId(SurrenderRequestEntity vo);
	/**
	 * Tilte: cancelSurrenderDetailByPersonId 
	 * Description:取消解约申请	
	 * @param vo
	 * @return int
	 * @author h
	 */
	int cancelSurrenderDetailByPersonId(SurrenderRequestEntity vo);
	
	/**
	 * Tilte: getNoSignSurrenderRequestVoListCountByPersonId 
	 * Description:根据personId查询拒绝解约 、已解约记录总数
	 * @param vo
	 * @return int
	 * @author h
	 */
	int getNoSignSurrenderRequestVoListCountByPersonId(SurrenderRequestVo vo);
	/**
	 * Tilte: getSurrenderRequestVoListCountByPersonId 
	 * Description:根据personId查询 审核中 、拒绝解约 、已解约记录 总数
	 * @param vo
	 * @return int
	 * @author h
	 */
	int getSurrenderRequestVoListCountByPersonId(SurrenderRequestVo vo);
 
}
