package com.boco.modules.fdoc.dao;

import java.util.List;

import com.boco.common.annotation.MyBatisDao;
import com.boco.common.persistence.CrudDao;
import com.boco.modules.fdoc.model.BizOutpatientSourceEntity;
import com.boco.modules.fdoc.model.FollowUpDictionaryEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationDictionaryEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationOrderEntity;
import com.boco.modules.fdoc.vo.FollowUpOrderVo;
import com.boco.modules.fdoc.vo.ImmuneVo;
import com.boco.modules.fdoc.vo.OrderImmuneVo;
import com.boco.modules.fdoc.vo.OrderParamVo;
import com.boco.modules.fdoc.vo.PhysicalExaminationOrderVo;
@MyBatisDao
public interface OrderImmuneDao extends CrudDao<BizOutpatientSourceEntity>{
	public List<BizOutpatientSourceEntity> getOrderSourceByUid(OrderParamVo vo);
	public BizOutpatientSourceEntity getOrderSourceByOrderParamVo(OrderParamVo vo);
	public BizOutpatientSourceEntity getOrderSourceByOrderNumber(BizOutpatientSourceEntity vo);
	//查询免疫预约列表
	public List<OrderImmuneVo> getOrderListByuid(OrderParamVo vo);
	public OrderImmuneVo getHospitalByuid(String uid);
	public String getPersonNameByPersonid(String vo);
	//查询免疫预约列表总数
	public List<OrderImmuneVo> getOrderSumByuid(String uid);
	//查询体检预约列表总数
	public List<PhysicalExaminationOrderVo> getPhysicalOrderListSumByuid(String uid);
	public List<PhysicalExaminationDictionaryEntity> getPhysicalListByUid(String uid);
	//获取体检预约列表
	public List<PhysicalExaminationOrderVo> getPhysicalOrderListByuid(OrderParamVo vo);
	public List<FollowUpDictionaryEntity> getFollowUpListByUid(String uid);
	//获取随访预约列表
	public List<FollowUpOrderVo> getFollowUpOrderList(OrderParamVo vo);
	//查询随访预约列表总数
	public List<FollowUpOrderVo> getFollowUpOrderSumByuid(String uid);
	//获取免疫历史记录
	public List<OrderImmuneVo> getImmuneOrderListOfHistoryByDoc(OrderParamVo vo);
	//获取免疫历史记录总数
	public List<OrderImmuneVo> getImmuneOrderListOfHistorySumByDoc(String userName);
    //获取本周未取消的预约免疫记录  医生端
	public List<OrderImmuneVo> getImmuneOrderListOfWeekdayByDoc(OrderParamVo vo);
	 //获取本周未取消的预约免疫记录总数  医生端
	public List<OrderImmuneVo> getImmuneOrderListOfWeekdaySumByDoc(String userName);
	//获取随访历史记录
	public List<OrderImmuneVo> getFollowUpOrderListOfHistoryByDoc(OrderParamVo vo);
	//获取随访历史记录总数
	public List<OrderImmuneVo> getFollowUpOrderListOfHistorySumByDoc(String userName);
	//查询历史未取消的预约体检记录 医生端
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfHistoryByDoc(OrderParamVo vo);
	//查询本周未取消的随访预约列表总数 医生端
	public List<OrderImmuneVo> getFollowUpOrderListSumOfWeekdayByDoc(String userName);
	//查询本周未取消的体检预约列表总数 医生端
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfWeekdayByDoc(String userName);
	//查询本周未取消的随访预约列表 医生端 
	public List<OrderImmuneVo> getFollowUpOrderListOfWeekdayByDoc(OrderParamVo vo);
	//查询本周未取消的体检预约列表 医生端
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfWeekdayByDoc(OrderParamVo vo);	
	//查询历史未取消的预约体检记录总数 医生端 
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfHistoryByDoc(String userName);
	//查询今天未取消的免疫预约列表总数 医生端 
	public List<OrderImmuneVo> getImmuneOrderListSumOfDayByDoc(String userName);
	//查询今天未取消的免疫预约列表 医生端 
	public List<OrderImmuneVo> getImmuneOrderListOfDayByDoc(OrderParamVo vo);
	//查询明天未取消的免疫预约列表总数 医生端 
	public List<OrderImmuneVo> getImmuneOrderListOfTomorrowSumByDoc(String userName);
	//查询明天未取消的免疫预约列表 医生端
	public List<OrderImmuneVo> getImmuneOrderListOfTomorrowByDoc(OrderParamVo vo);
	//查询今天未取消的体检预约列表总数 医生端
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfDayByDoc(String userName);
	//查询今天未取消的体检预约列表 医生端 
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfDayByDoc(OrderParamVo vo);
	//查询明天未取消的体检预约列表总数  医生端
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfTomorrowByDoc(String userName);
	//查询明天未取消的体检预约列表  医生端 
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfTomorrowByDoc(OrderParamVo vo);
	//查询今天未取消的随访预约列表总数 医生端
	public List<OrderImmuneVo> getFollowUpOrderListSumOfDayByDoc(String userName);
	//查询今天未取消的随访预约列表 医生端
	public List<OrderImmuneVo> getFollowUpOrderListOfDayByDoc(OrderParamVo vo);
	//查询明天未取消的随访预约列表 医生端 
	public List<OrderImmuneVo> getFollowUpOrderListOfTomorrowByDoc(OrderParamVo vo);
	//查询明天未取消的随访预约列表总数 医生端 
	public List<OrderImmuneVo> getFollowUpOrderListSumOfTomorrowByDoc(String userName);
	//查询预约体检详情 医生端
	public OrderImmuneVo getPhysicalExaminationOrderDetailByDoc(OrderParamVo vo);
	//查询预约随访详情 医生端 
	public OrderImmuneVo getFollowUpOrderDetailByDoc(OrderParamVo vo);
	//查询预约免疫详情 医生端 
	public OrderImmuneVo getImmuneOrderDetailByDoc(OrderParamVo vo);
	//修改免疫号源
	public int update(BizOutpatientSourceEntity be);
}
