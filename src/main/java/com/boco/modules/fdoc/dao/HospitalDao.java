package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.vo.HospitalVo;
/**
 * 
 * @author sunche
 *
 */
@MyBatisDao
public interface HospitalDao extends CrudDao<HospitalEntity>{
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
	 * @return
	 */
	public HospitalEntity getHospitalInfo(String orgId);


	/**
	 *
	 * getHospitalInfo:(获取所有医院信息). <br/>
	 * @author q
	 * @return
	 */
	public List<HospitalEntity> getAll( );
}
