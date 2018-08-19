package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.GroupMsgTemplateEntity;

@MyBatisDao
public interface GroupMsgTemplateDao extends CrudDao<GroupMsgTemplateEntity>{
	/**
	 * 
	 * getTemplateByDocTeam:(根据医生团队ID获取群发模板列表). <br/>
	 * @author q
	 * @return
	 */
	public List<GroupMsgTemplateEntity> getTemplateByDocTeam(String docTeamId);
}
