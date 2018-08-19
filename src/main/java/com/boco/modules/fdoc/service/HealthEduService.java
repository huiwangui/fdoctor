package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.HealthEduEntity;
import com.boco.modules.fdoc.vo.HealthEduVo;

public interface HealthEduService {

	/**
	 * 获取健康教育总条数 团队
	 * @param healthEduVo
	 * @return
	 */
	public int getCount(HealthEduVo healthEduVo);

	/**
	 * 获取健康教育列表
	 * @param healthEduVo
	 * @return
	 */
	public List<HealthEduVo> getEduList(HealthEduVo healthEduVo);

	/**
	 * 获取健康教育总条数
	 * @param healthEduVo 
	 * @param teamId
	 * @return
	 */
	public int getCountALL(HealthEduVo healthEduVo);

	/**
	 * 根据Id查健康教育
	 * @param id
	 * @return
	 */
	public HealthEduEntity getEduById(Integer id);

	/**
	 * 根据活动主题查询健康教育
	 * @param eduEntity
	 * @return
	 */
	public List<HealthEduVo> getEduByTheme(HealthEduVo eduVo);

	/**
	 * 删除健康教育（用id）
	 * @param id
	 * @return
	 */
	public int deleteEduById(Integer id);

	/**
	 * 新建健康教育
	 * @param eduEntity
	 * @return
	 */
	public int saveNewEdu(HealthEduEntity eduEntity);

	/**
	 * 修改健康档案
	 * @param eduEntity
	 * @return
	 */
	public int updateEdu(HealthEduEntity eduEntity);

	/**
	 * 主题获取健康档案总条数
	 * @param eduVo
	 * @return
	 */
	public int getCountByTheme(HealthEduVo eduVo);

}
