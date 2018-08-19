package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.ServicepackDetailEntity;
import com.boco.modules.fdoc.model.SigServicepackEntity;
import com.boco.modules.fdoc.vo.ServicepackDetailsVo;
import com.boco.modules.fdoc.vo.SigServicepackVo;

public interface SigServicepackService {
	/**
	 * 获取所有服务包信息
	 * @return
	 */
	public List<SigServicepackEntity> getAll();
	/**
	 * 获取所有服务详细信息
	 * @return
	 */
	public List<SigServicepackVo> getAllDetails();
	/**
	 * 
	 * getDetailsByPackId:(根据服务包ID获取服务详情). <br/>
	 * @author q
	 * @param packId
	 * @return
	 */
	public List<ServicepackDetailsVo> getDetailsByPackId(Integer packId);
}
