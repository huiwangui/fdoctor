package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.model.DepartmentEntity;
import com.boco.modules.fdoc.model.DoctorDetailScheCommEntity;
import com.boco.modules.fdoc.vo.DepartmentVo;
import com.boco.modules.fdoc.vo.QuickBookingVo;

/**
 * 
 * @author sunche
 * @Time 2016年8月2日下午2:28:11
 */
public interface DeptService {
	/**
	 * 通过名称查找科室
	 * @param name
	 * @return
	 */
	public List<DepartmentEntity> getDeptByName(String name);
	/**
	 * 通过医院查找科室
	 * @param id
	 * @return
	 */
	public List<DepartmentVo> getDeptList(int hospId);
	/**
	 * 查询科室详情
	 * @param id
	 * @return
	 */
	public DepartmentEntity getDeptDetail(int id);
	/**
	 * 根据城市查询科室
	 * @return
	 */
	public List<DepartmentVo> getDefaultDepts(String city);
	/**
	 * 根据时间段、医院id查询可预约的科室
	 * @param vo
	 * @return
	 */
	public List<DepartmentEntity> getBookingDepts(QuickBookingVo vo);
	
}
