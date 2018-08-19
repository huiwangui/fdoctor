package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.UserRealtion;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.UserRelationVo;

public interface UserRelationService {
	
	/**
     * 新增联系人
     * @param record
     * @return
     *
     */
	public String insert(UserRealtion record , PersonInformationVo vo);
	
	 /**
	    * 删除联系人
	    * @param id
	    * @return
	    *
	    */
	public int deleteByPrimaryKey(Integer id);
   
	/**
     * 查找所有联系人
     * @param uid 用户id
     * @return
     *
     */
    List<PersonInformationVo> selectAllperson(String uid);
    
    /**
     * 更新联系人
     * @param record
     * @return
     *
     */
	public int updateRelation(PersonInformationVo personvo,UserRelationVo userRelationVo);
}
