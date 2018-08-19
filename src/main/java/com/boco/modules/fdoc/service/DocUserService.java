package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.common.json.BaseJsonVo;
import com.boco.modules.fdoc.model.DoctorCommentEntity;
import com.boco.modules.fdoc.model.DoctorDetailScheCommEntity;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.model.ScheduleEntity;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.QuickBookingVo;
import com.boco.modules.fdoc.vo.ScheduleVo;

/**
 * 
 * @author sunche
 * @Time 2016年8月2日下午3:12:25
 */
public interface DocUserService {
	/**
	 * 
	 * verifyUser:(通过用户名和密码验证登录). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public DoctorUserEntity verifyUser(DoctorUserEntity entity);
	/**
	 * 
	 * updateDocUser:(修改医生账号信息). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public int updateDocUser(DoctorUserEntity userEntity, DoctorEntity docEntity);
	/**
	 * 
	 * getDoctorByUsername:(通过医生用户名获取医生). <br/>
	 * @author q
	 * @param userName
	 * @return
	 */
	public DoctorDetailVo getDoctorByUsername(String userName);
	/**
	 * 
	 * getAccountsByTeamId:(获取医生团队账号列表，用于推送). <br/>
	 * @author q
	 * @param teamId
	 * @return
	 */
	public List<String> getAccountsByTeamId(String teamId);
	/**
	 * getTeamIdbyUsername:(通过医生用户名获取teamId). <br/>
	 * @author lzz
	 * @param userName
	 * @return
	 */
	public String getTeamIdbyUsername(String userName);
}
