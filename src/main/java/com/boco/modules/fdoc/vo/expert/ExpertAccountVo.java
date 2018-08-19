/**
 * @ClassName: ExpertAccountVo 
 * Description:
 * @author h
 * @date  2017年9月24日下午8:51:51
 * @version 1.0
 *  
 */
package com.boco.modules.fdoc.vo.expert;

import com.boco.modules.fdoc.model.expert.ExpertAccountEntity;

/**
 * Tilte: ExpertAccountVo 
 * Description:
 * @author h
 * @date  2017年9月24日下午8:51:51
 * @version 1.0
 *  
 */
public class ExpertAccountVo extends ExpertAccountEntity {

	/**
	 * 新密码
	 */
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
