package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.vo.HospitalVo;
import com.boco.modules.fdoc.vo.QuickBookingVo;

/**
 * 
 * @author sunche
 * @since 
 */
public interface HospitalService {
	/**
	 * 
	 * getHospitalListWithTeamNum:(查询机构列表，包含团队个数 ). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<HospitalVo> getHospitalListWithTeamNum(HospitalVo vo);
	/**
	 * 
	 * getTeamInHospital:(获取机构下的医生团队列表 ). <br/>
	 * @author q
	 * @param orgId
	 * @return
	 */
	public List<HospitalVo> getTeamInHospital(String orgId);
	/**
	 * 根据居民uid查询签约医院信息
	 * @param uid
	 * @return
	 */
	public HospitalEntity getHospitalByUid(String uid);
	/**
	 * 
	 * getHospitalInfo:(获取医院信息). <br/>
	 * @author q
	 * @param orgId
	 * @return
	 */
	public HospitalEntity getHospitalInfo(String orgId);
}
