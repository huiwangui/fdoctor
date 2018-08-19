package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.AdEntity;
import com.boco.modules.fdoc.model.ScheduleEntity;
import com.boco.modules.fdoc.vo.ScheduleVo;

/**
 * 
 * @author sunche
 * @Time 2016年8月9日下午2:12:27
 */
@MyBatisDao
public interface ScheDao extends CrudDao<ScheduleEntity>{
	/**
	 * 查询排班列表
	 * @return
	 */
	public List<ScheduleEntity> findScheList(ScheduleVo vo);
	/**
	 * 通过医生ID和排班时间查询排班
	 * @param vo
	 * @return
	 */
	public ScheduleEntity getByDocIdAndWorkTime(ScheduleVo vo);
	/**
	 * 修改剩余挂号数量
	 * @param en
	 * @return
	 */
	public Integer updateNum(ScheduleEntity en);
}
