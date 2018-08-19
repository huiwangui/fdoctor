package com.boco.modules.fdoc.service;

import java.util.List;
import java.util.Map;

import com.boco.modules.fdoc.model.SignRequestEntity;
import com.boco.modules.fdoc.vo.SignRequestVo;

/**
 * 
 * 签约申请service层
 * ClassName: SignRequestService <br/>
 * @author q
 * @version 
 * @since JDK 1.7+
 */
public interface SignRequestService {
	/**
	 * 
	 * addSignRequestService:(新增签约申请). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Map<String, Object> addSignRequestService(SignRequestEntity entity);
	/**
	 * 
	 * getSignRequest:(单表查询). <br/>
	 * @author q
	 * @param id
	 * @return
	 */
	public SignRequestEntity getSignRequest(Integer id);
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
	 * dealSignRequest:(处理请求，修改状态). <br/>
	 * @author q
	 * @param entity
	 * @return
	 */
	public Integer dealSignRequest(SignRequestEntity entity);
}
