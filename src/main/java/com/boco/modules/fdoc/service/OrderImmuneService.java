package com.boco.modules.fdoc.service;

import java.util.List;

import com.boco.modules.fdoc.model.BizOutpatientOrderEntity;
import com.boco.modules.fdoc.model.BizOutpatientSourceEntity;
import com.boco.modules.fdoc.model.FollowUpDictionaryEntity;
import com.boco.modules.fdoc.model.FollowUpOrderEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationDictionaryEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationOrderEntity;
import com.boco.modules.fdoc.vo.FollowUpOrderVo;
import com.boco.modules.fdoc.vo.ImmuneVo;
import com.boco.modules.fdoc.vo.OrderImmuneVo;
import com.boco.modules.fdoc.vo.OrderParamVo;
import com.boco.modules.fdoc.vo.PhysicalExaminationOrderVo;

public interface OrderImmuneService {
	public List<BizOutpatientSourceEntity> getOrderSourceByUid(OrderParamVo vo);

	public String saveOrderImmune(OrderParamVo vo);
    //取消签约
	public int cacelOrder(BizOutpatientOrderEntity bo);

	public String getOrderByuid(BizOutpatientOrderEntity bo);
    //查询预约列表
	public List<OrderImmuneVo> getOrderListByuid(OrderParamVo vo);

	public List<OrderImmuneVo> getOrderSumByuid(String uid);
    //获取体检项目列表
	public List<PhysicalExaminationDictionaryEntity> getPhysicalListByUid(String uid);
    //确认预约体检
	public String savePhysicalOrder(OrderParamVo vo);
    //取消体检预约
	public int cacelPhysicalOrder(PhysicalExaminationOrderEntity bo);
    //判断体检是否预约
	public String getPhysicalOrderByuid(PhysicalExaminationOrderEntity bo);
    //查询体检预约列表
	public List<PhysicalExaminationOrderVo> getPhysicalOrderListByuid(OrderParamVo vo);
 
	//查询体检预约列表总数
	public List<PhysicalExaminationOrderVo> getPhysicalOrderListSumByuid(String uid);
    //获取随访项目列表
	public List<FollowUpDictionaryEntity> getFollowUpListByUid(String uid);
    //确认随访预约
	public String saveFollowUpOrder(OrderParamVo vo);
    //取消随访预约
	public int cacelFollowUpOrder(FollowUpOrderEntity bo);
	//判断随访是否预约
	public String getFollowUpOrderByuid(FollowUpOrderEntity bo);
    //查询随访预约列表
	public List<FollowUpOrderVo> getFollowUpOrderList(OrderParamVo vo);
	//查询随访预约列表总数
	public List<FollowUpOrderVo> getFollowUpOrderSumByuid(String uid);
	//获取历史预约免疫记录(医生端)
	public List<OrderImmuneVo> getImmuneOrderListOfHistoryByDoc(OrderParamVo vo);
	//获取历史预约免疫记录总数(医生端)
	public List<OrderImmuneVo> getImmuneOrderListOfHistorySumByDoc(String userName);
    //获取本周预约免疫记录(医生端)
	public List<OrderImmuneVo> getImmuneOrderListOfWeekdayByDoc(OrderParamVo vo);
	//获取本周预约免疫记录总数(医生端)
	public List<OrderImmuneVo> getImmuneOrderListOfWeekdaySumByDoc(String userName);
    //获取历史随访记录总数(医生端)
	public List<OrderImmuneVo> getFollowUpOrderListOfHistorySumByDoc(String userName);
    //获取历史随访记录(医生端)
	public List<OrderImmuneVo> getFollowUpOrderListOfHistoryByDoc(OrderParamVo vo);
	//查询本周未取消的随访预约列表总数 医生端
	public List<OrderImmuneVo> getFollowUpOrderListSumOfWeekdayByDoc(String userName);
	//查询本周未取消的随访预约列表 医生端 
	public List<OrderImmuneVo> getFollowUpOrderListOfWeekdayByDoc(OrderParamVo vo);
	//查询本周未取消的体检预约列表总数 医生端
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfWeekdayByDoc(String userName);	 
	//查询本周未取消的体检预约列表 医生端
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfWeekdayByDoc(OrderParamVo vo);
	//查询历史未取消的预约体检记录 医生端
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfHistoryByDoc(OrderParamVo vo);
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
}
