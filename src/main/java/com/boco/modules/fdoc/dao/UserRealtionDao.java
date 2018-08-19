package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.modules.fdoc.model.UserRealtion;
@MyBatisDao
public interface UserRealtionDao {

   /**
    * 删除联系人
    * @param id
    * @return
    *
    */
    int deleteByPrimaryKey(Integer id);
    /**
     * 新增联系人
     * @param record
     * @return
     *
     */
    int insert(UserRealtion record);

    /**
     * 查找所有联系人
     * @param example
     * @return
     *
     */
    List<UserRealtion> selectRelations(String  uid);
    
    /**
     * 更新联系人 根据id
     * @param record
     * @return
     *
     */
    int updateByPrimaryKeySelective(UserRealtion record);
    /**
     * 更新联系人 根据UID PERSONID
     * @param record
     * @return
     *
     */
    int updateByUidPersonId(UserRealtion record);

}