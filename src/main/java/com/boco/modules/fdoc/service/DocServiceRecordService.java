package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.DocServiceRecordEntity;
import com.boco.modules.fdoc.model.SigServicepackEntity;
import com.boco.modules.fdoc.vo.DocServiceRecordVo;

public interface DocServiceRecordService {

	/**
	 * 获取订购的服务包列表
	 * @param personId
	 * @return
	 */
	public List<SigServicepackEntity> getPackList(String personId);

	/**
	 * 添加服务记录
	 * @param recordEntity
	 * @return
	 */
	public int addRecord(DocServiceRecordEntity recordEntity);

	/**
	 * 获取服务记录列表
	 * @param searchVo
	 * @return
	 */
	public List<DocServiceRecordVo> getRecordList(DocServiceRecordVo searchVo);

	/**
	 * 获取服务包详情包括余下服务次数
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
	 * 获取服务项目的全部次数
	 * @param detailsId
	 * @return
	 */
	public Integer getPackDetailsTimes(Integer detailsId);


}
