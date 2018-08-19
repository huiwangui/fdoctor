package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.BizOutpatientOrderEntity;
import com.boco.modules.fdoc.model.BizOutpatientSourceEntity;
import com.boco.modules.fdoc.model.FollowUpOrderEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationDictionaryEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationOrderEntity;
import com.boco.modules.fdoc.vo.OrderParamVo;
@MyBatisDao
public interface PatientOrderDao extends CrudDao<BizOutpatientOrderEntity>{
	 public int save(BizOutpatientOrderEntity vo);
	 public int updateOrderStatus(BizOutpatientOrderEntity vo);
	 public int updatePhysicalOrderStatus(PhysicalExaminationOrderEntity vo);
	 public BizOutpatientOrderEntity getOrderByNumber(BizOutpatientOrderEntity vo);
	 public BizOutpatientOrderEntity getOrderByuid(BizOutpatientOrderEntity vo);
	 public int savePhysicalOrder(PhysicalExaminationOrderEntity vo);
	 /**
	  * 查询体检是否预约
	  * @param vo
	  * @return
	  */
	 public PhysicalExaminationOrderEntity getPhysicalOrderByuid(PhysicalExaminationOrderEntity vo);
	 /**
	  * 查询随访是否预约
	 * @param vo
	 * @return
	 */
	public FollowUpOrderEntity getFollowUpOrderByuid(FollowUpOrderEntity vo);
	 /**
	  * 预约随访
	 * @param vo
	 * @return
	 */
	public int saveFollowUpOrder(FollowUpOrderEntity vo);
	/**
	 * 取消随访
	 * @param vo
	 * @return
	 */
	public int updateFollowUpOrderStatus(FollowUpOrderEntity vo);
	/**
	 * 修改免疫订单状态 已完成
	 * @param vo
	 * @return
	 */
	public int updateImmuneOrderStatusOfFinish(BizOutpatientOrderEntity vo);
	/**
	 * 修改体检订单状态 已完成
	 * @param vo
	 * @return
	 */
	public int updatePhysicalOrderStatusOfFinish(PhysicalExaminationOrderEntity vo);
	/**
	 * 修改随访订单状态 已完成
	 * @param vo
	 * @return
	 */
	public int updateFollowUpOrderStatusOfFinish(FollowUpOrderEntity vo);
}
