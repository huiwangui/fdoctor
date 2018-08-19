package com.boco.modules.fdoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.dao.DeptDao;
import com.boco.modules.fdoc.model.DepartmentEntity;
import com.boco.modules.fdoc.service.DeptService;
import com.boco.modules.fdoc.vo.DepartmentVo;
import com.boco.modules.fdoc.vo.QuickBookingVo;
@Service
public class DeptServiceImpl implements DeptService{
	
	@Resource
	DeptDao deptDao;
	
	@Override
	public List<DepartmentEntity> getDeptByName(String name) {
		return deptDao.findDeptByName(name);
	}

	@Override
	public List<DepartmentVo> getDeptList(int hospId) {
		List<DepartmentEntity> allDept = deptDao.getMainDepts(hospId);
		List<DepartmentVo> voList = new ArrayList<DepartmentVo>();
		for (DepartmentEntity departmentEntity : allDept) {
			departmentEntity.setSubDepts(deptDao.getSubDepts(hospId, departmentEntity.getId()));
			DepartmentVo vo = new DepartmentVo();
			BeanUtils.copyProperties(departmentEntity, vo);
			// 查询主科室医生数
			Integer doctorCount = deptDao.getDoctorsInDept(hospId, vo.getId());
			vo.setDoctors(doctorCount);
			// 循环子科室列表，查询科室内的医生数
			for (DepartmentVo departmentVo : vo.getSubDepts()) {
				Integer doctorSubCount = deptDao.getDoctorsInDept(hospId, departmentVo.getId());
				if (doctorSubCount == null) {
					doctorSubCount = 0;    //若查询结果为null，则设置医生数为0
				}
				departmentVo.setDoctors(doctorSubCount);
			}
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public DepartmentEntity getDeptDetail(int id) {
		return deptDao.getDeptDetail(id);
	}

	@Override
	public List<DepartmentVo> getDefaultDepts(String city) {
		List<DepartmentEntity> list = deptDao.getDefaultDepts(city);
		List<DepartmentVo> voList = new ArrayList<DepartmentVo>();
		for (DepartmentEntity departmentEntity : list) {
			departmentEntity.setSubDepts(deptDao.getSubDeptsByCity(city, departmentEntity.getId()));
			DepartmentVo vo = new DepartmentVo();
			BeanUtils.copyProperties(departmentEntity, vo);
			// 查询主科室医生数
			Integer doctorCount = deptDao.getDoctorsInDeptByCity(city, vo.getId());
			vo.setDoctors(doctorCount);
			// 循环子科室列表，查询科室内的医生数
			for (DepartmentVo departmentVo : vo.getSubDepts()) {
				Integer doctorSubCount = deptDao.getDoctorsInDeptByCity(city, departmentVo.getId());
				if (doctorSubCount == null) {
					doctorSubCount = 0;    //若查询结果为null，则设置医生数为0
				}
				departmentVo.setDoctors(doctorSubCount);
			}
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public List<DepartmentEntity> getBookingDepts(QuickBookingVo vo) {
		return deptDao.getBookingDepts(vo);
	}

}
