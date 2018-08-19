package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
/**
 * @author sunche
 */
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.DepartmentEntity;
import com.boco.modules.fdoc.vo.DepartmentVo;
import com.boco.modules.fdoc.vo.QuickBookingVo;
@MyBatisDao
public interface DeptDao extends CrudDao<DepartmentEntity>{
	/**
	 * 获取科室列表
	 * @param id
	 * @return
	 */
	public List<DepartmentEntity> findDeptList(int id);
	/**
	 * 通过名称查找科室
	 * @param name
	 * @return
	 */
	public List<DepartmentEntity> findDeptByName(String name);
	/**
	 * 查询科室详情
	 * @param id
	 * @return
	 */
	public DepartmentEntity getDeptDetail(int id);
	/**
	 * 根据预约量查询科室
	 * @return
	 */
	public List<DepartmentEntity> getDefaultDepts(String city);
	/**
	 * 根据时间段、医院id查询可预约的科室
	 * @param vo
	 * @return
	 */
	public List<DepartmentEntity> getBookingDepts(QuickBookingVo vo);
	/**
	 * 获取主科室列表
	 * @param hospId
	 * @return
	 */
	public List<DepartmentEntity> getMainDepts(int hospId);
	/**
	 * 获取副科室列表
	 * @param hospId
	 * @param mainDeptId
	 * @return
	 */
	public List<DepartmentVo> getSubDepts(int hospId, int mainDeptId);
	/**
	 * 根据医院、科室ID获取该科室下有多少医生
	 * @param hospId
	 * @param deptId
	 * @return
	 */
	public Integer getDoctorsInDept(int hospId,int deptId);
	/**
	 * 通过城市获取副科室列表
	 * @param city
	 * @param mainDeptId
	 * @return
	 */
	public List<DepartmentVo> getSubDeptsByCity(String city,int mainDeptId);
	/**
	 * 根据城市、科室ID获取该科室下有多少医生
	 * @param city
	 * @param deptId
	 * @return
	 */
	public Integer getDoctorsInDeptByCity(String city,int deptId);
}
