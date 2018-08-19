package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.SignRequestEntity;
import com.boco.modules.fdoc.vo.SignRequestVo;

/**
 * 签约申请Dao
 * ClassName: SignRequestDao <br/>
 * @author q
 * @version 
 * @since JDK 1.7+
 */
@MyBatisDao
public interface SignRequestDao extends CrudDao<SignRequestEntity>{
	/**
	 * 
	 * getRequestList:(获取签约申请列表). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public List<SignRequestVo> getRequestList(SignRequestVo vo);
	/**
	 * 
	 * getRequestCount:(获取签约申请数量). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public Integer getRequestCount(SignRequestVo vo);
	/**
	 * 
	 * getRequestDetail:(根据ID获取签约申请详情). <br/>
	 * @author q
	 * @param vo
	 * @return
	 */
	public SignRequestVo getRequestDetail(Integer id);
	/**
	 * 
	 * getByFamilyAndTeam:(根据家庭、团队和状态获取对象,主要用于判断是否重复申请). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public SignRequestEntity getByPersonAndTeam(SignRequestEntity entity);
}
