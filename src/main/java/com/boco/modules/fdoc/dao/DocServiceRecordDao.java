package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.DocServiceRecordEntity;
import com.boco.modules.fdoc.vo.DocServiceRecordVo;

@MyBatisDao
public interface DocServiceRecordDao {

	/**
	 * 获取居民订购的服务包列表
	 * @param personId
	 * @return
	 */
	public int getServicePackValue(String personId);

	/**
	 * 新增服务记录
	 * @param recordEntity
	 * @return
	 */
	public int addRecord(DocServiceRecordEntity recordEntity);

	/**
	 * 获取服务记录列表
	 * @param recordVo
	 * @return
	 */
	public List<DocServiceRecordVo> getRecordList(DocServiceRecordVo searchVo);

	/**
	 * 获取服务项目详情包括剩余次数
	 * @param recordEntity
	 * @return
	 */
	public Integer getUsedTimes(DocServiceRecordEntity recordEntity);

	/**
	 * 获取服务记录次数
	 * @param searchVo
	 * @return
	 */
	public Integer getRecordCount(DocServiceRecordVo searchVo);

	/**
	 * 获取服务项目的总次数
	 * @param detailsId
	 * @return
	 */
	public Integer getPackDetailsTimes(Integer detailsId);

}
