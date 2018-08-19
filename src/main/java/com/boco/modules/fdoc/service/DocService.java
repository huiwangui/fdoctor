package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.model.DoctorCommentEntity;
import com.boco.modules.fdoc.model.DoctorDetailScheCommEntity;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.ScheduleEntity;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.QuickBookingVo;
import com.boco.modules.fdoc.vo.ScheduleVo;

/**
 * 
 * @author sunche
 * @Time 2016年8月2日下午3:12:25
 */
public interface DocService {
	/**
	 * 
	 * getDoctorInfo:(获取医生信息). <br/>
	 * @author q
	 * @param id
	 * @return
	 */
	public DoctorDetailVo getDoctorInfo(String id);
	/**
	 * 
	 * getDoctorTeamMemberByTeamId:(根据团队ID获取其团队成员). <br/>
	 * @author q
	 * @param teamId
	 * @return
	 */
	public List<DoctorDetailVo> getDoctorTeamMemberByTeamId(String teamId);
	
	/**
	 * 
	 * getDoctorTeamMemberByDocId:(根据医生ID获取其团队成员，排除自己在外). <br/>
	 * @author q
	 * @param docId
	 * @return
	 */
	public List<DoctorDetailVo> getDoctorTeamMemberByDocId(String docId);
	/**
	 * 
	 * getDoctorInfo:(获取团队队长，即为团队内医生的信息). <br/>
	 * @author q
	 * @param id
	 * @return
	 */
	public DoctorDetailVo getTeamLeaderInfo(String teamId);
	/**
	 * 
	 * getDoctorList:(获取医生列表). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<DoctorDetailVo> getDoctorList(String docName, Integer pageSize, Integer pageNo);
	/**
	 * 
	 * getDoctorCount:(获取医生数量). <br/>
	 * @author q
	 * @param docName
	 * @return
	 */
	public Integer getDoctorCount(String docName);
	/**
	 * 
	 * getRandomProductCode:(获取随机productCode，用于轮询接口). <br/>
	 * @author q
	 * @return
	 */
	public String getRandomProductCode();
	/**
	 * 
	 * getTeamCount:(获取医生团队总数). <br/>
	 * @author q
	 * @return
	 */
	public Integer getTeamCount();
	/**
	 * 
	 * getTeamIdsByHospital:(通过机构ID获取机构下的团队ID集合). <br/>
	 * @author q
	 * @param orgId
	 * @return
	 */
	public List<String> getTeamIdsByHospital(String orgId);
	/**
	 * 
	 * getLeaderListByHospital:(获取团队队长列表). <br/>
	 * @author q
	 * @param orgId
	 * @return
	 */
	public List<DoctorEntity> getLeaderListByHospital(String orgId);
}
