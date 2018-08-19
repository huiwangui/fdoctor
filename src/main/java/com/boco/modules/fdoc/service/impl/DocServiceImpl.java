package com.boco.modules.fdoc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.persistence.Page;
import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.service.DocService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;

@Service

public class DocServiceImpl implements DocService {
	
	@Resource
	DoctorDao doctorDao;

	@Override
	public DoctorDetailVo getDoctorInfo(String id) {
		return doctorDao.getDoctorInfo(id);
	}

	@Override
	public List<DoctorDetailVo> getDoctorTeamMemberByTeamId(String teamId) {
		return doctorDao.getDoctorTeamMemberByTeamId(teamId);
	}

	@Override
	public List<DoctorDetailVo> getDoctorTeamMemberByDocId(String docId) {
		return doctorDao.getDoctorTeamMemberByDocId(docId);
	}

	@Override
	public DoctorDetailVo getTeamLeaderInfo(String teamId) {
		return doctorDao.getTeamLeaderInfo(teamId);
	}

	@Override
	public List<DoctorDetailVo> getDoctorList(String docName,
			Integer pageSize, Integer pageNo) {
		// 设置查询参数
		DoctorDetailVo vo = new DoctorDetailVo();
		vo.setDocName(docName);
		return doctorDao.getDoctorList(vo);
	}

	@Override
	public Integer getDoctorCount(String docName) {
		// 设置查询参数
		DoctorDetailVo vo = new DoctorDetailVo();
		vo.setDocName(docName);
		return doctorDao.getDoctorCount(vo);
	}

	@Override
	public String getRandomProductCode() {
		return doctorDao.getRandomProductCode();
	}

	@Override
	public Integer getTeamCount() {
		return doctorDao.getTeamCount();
	}

	@Override
	public List<String> getTeamIdsByHospital(String orgId) {
		return doctorDao.getTeamIdsByHospital(orgId);
	}

	@Override
	public List<DoctorEntity> getLeaderListByHospital(String orgId) {
		return doctorDao.getLeaderListByHospital(orgId);
	}
}
