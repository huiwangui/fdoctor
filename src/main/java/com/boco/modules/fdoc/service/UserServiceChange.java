package com.boco.modules.fdoc.service;

import com.boco.modules.fdoc.vo.UserVo;

public interface UserServiceChange {
	/**
	 * 验证用户修改
	 * @param vo
	 * @return
	 *
	 */
	public String authen(UserVo vo);

}
