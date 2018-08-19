package com.boco.modules.fdoc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.boco.common.utils.DateUtils;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.NumberUtils;
import com.boco.modules.fdoc.dao.OrderImmuneDao;
import com.boco.modules.fdoc.dao.PatientOrderDao;
import com.boco.modules.fdoc.exception.OrderException;
import com.boco.modules.fdoc.model.BizOutpatientOrderEntity;
import com.boco.modules.fdoc.model.BizOutpatientSourceEntity;
import com.boco.modules.fdoc.model.FollowUpDictionaryEntity;
import com.boco.modules.fdoc.model.FollowUpOrderEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationDictionaryEntity;
import com.boco.modules.fdoc.model.PhysicalExaminationOrderEntity;
import com.boco.modules.fdoc.service.OrderImmuneService;
import com.boco.modules.fdoc.vo.FollowUpOrderVo;
import com.boco.modules.fdoc.vo.ImmuneVo;
import com.boco.modules.fdoc.vo.OrderImmuneVo;
import com.boco.modules.fdoc.vo.OrderParamVo;
import com.boco.modules.fdoc.vo.PhysicalExaminationOrderVo;
@Service
public class OrderImmuneServiceImpl implements OrderImmuneService {
	@Resource
	OrderImmuneDao orderImmuneDao;
	@Resource
	PatientOrderDao patientOrderDao;
	
	@Override
	public List<BizOutpatientSourceEntity> getOrderSourceByUid(OrderParamVo vo) {
		 
		return orderImmuneDao.getOrderSourceByUid(vo);
	}
	@Override
	@Transactional
	public String saveOrderImmune(OrderParamVo vo) {
		//查询某天上午或者下午的号源 
		BizOutpatientSourceEntity be= orderImmuneDao.getOrderSourceByOrderParamVo(vo);
		if(be==null){
			return "当天还未发布号源";
		}
		String[] patientIds=vo.getPatientIds().split(",");
		/**
		 * 查询是否有号，预约号量-1，并发控制
		 */
		int flag = 0;
		while(flag == 0){
			if (be.getRemainderNumber() == 0||be.getRemainderNumber()<patientIds.length) {
				//throw new OrderException("号源不够,预约失败"); //号源不够，返回			 
			    return "号源不够,预约失败";
			}else {
				be.setRemainderNumber(be.getRemainderNumber() - patientIds.length);
				be.setCurrentNumber(be.getCurrentNumber()==null?0:be.getCurrentNumber() +patientIds.length);
				int updateNum = orderImmuneDao.update(be);
				if (updateNum == 1) {   //修改行数为1，说明版本号对应，中间无其他人修改
					flag=1;
				}
			}
		}
		/**在预约记录表中新增记录**/
		BizOutpatientOrderEntity entity=new BizOutpatientOrderEntity();
		StringBuffer orderNumberBuffer = new StringBuffer();
		orderNumberBuffer.append("YM");
		Date createTime = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String dateString = myFmt.format(createTime);
		orderNumberBuffer.append(dateString).append(new Date().getTime()+""); 
		/*if(DateUtils.immuneNumber>9999){
			DateUtils.immuneNumber=0;
		}
	    DateUtils.immuneNumber+=1;
	    int number =DateUtils.immuneNumber;
		orderNumberBuffer.append(DateUtils.addZero(4,number));	*/
		entity.setOrderNumber(orderNumberBuffer.toString());
		entity.setCreateTime(createTime);
		entity.setCreatorUid(vo.getUid());
		entity.setOrderStatus("1");
		entity.setPatientId(vo.getPatientIds());
		entity.setOutpatientSourceId(be.getId());
		entity.setOrderTime(vo.getClinicDate());
		entity.setClinicTime(vo.getClinicTime());
		int suc=patientOrderDao.save(entity);
		return orderNumberBuffer.toString();
	}
	@Override
	@Transactional
	public int cacelOrder(BizOutpatientOrderEntity bo) {
		BizOutpatientOrderEntity bEntity=patientOrderDao.getOrderByNumber(bo);
		String[] patientIds=bEntity.getPatientId().split(",");
		
		BizOutpatientSourceEntity vo = new BizOutpatientSourceEntity();
		vo.setId(bEntity.getOutpatientSourceId());
		vo=orderImmuneDao.getOrderSourceByOrderNumber(vo);
		/**
		 * 查询是否有号，预约号量-1，并发控制
		 */
		int flag = 0;
		while(flag == 0){			 
			vo.setRemainderNumber(vo.getRemainderNumber() +patientIds.length );
			vo.setCurrentNumber(vo.getCurrentNumber()==null?0:vo.getCurrentNumber() -patientIds.length);
			int updateNum = orderImmuneDao.update(vo);
			if (updateNum == 1) {   //修改行数为1，说明版本号对应，中间无其他人修改
				flag=1;
			}
			 
		}
		int num=patientOrderDao.updateOrderStatus(bo);
		return num;
	}
	@Override
	public String getOrderByuid(BizOutpatientOrderEntity bo) {
		BizOutpatientOrderEntity vo = patientOrderDao.getOrderByuid(bo);
		if(vo==null){  //还未预约
			return null;
		}
		//预约的是上午的号
		if("0".equals(vo.getClinicTime())){
			if (new Date().getTime() > (vo.getOrderTime().getTime()+(12*60*60*1000))) {
				//已超过预约时间,表示已完成整个流程
				vo.setOrderStatus("3");
				patientOrderDao.updateImmuneOrderStatusOfFinish(vo);
				return "overdue";
			}
		}else if("1".equals(vo.getClinicTime())){ //说明预约的是下午的号
			if (new Date().getTime() > (vo.getOrderTime().getTime()+(24*60*60*1000))) {
				//已超过预约时间,表示已完成整个流程
				vo.setOrderStatus("3");
				patientOrderDao.updateImmuneOrderStatusOfFinish(vo);
				return "overdue";
			}
		}
		/*if (new Date().getTime() > vo.getOrderTime().getTime()) {
			//已超过预约时间,表示已完成整个流程
			vo.setOrderStatus("3");
			patientOrderDao.updateImmuneOrderStatusOfFinish(vo);
			return "overdue";
		}*/
		//日
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd");
		String orderTime=simpleDateFormat.format(vo.getOrderTime());
		String nowTime=simpleDateFormat.format(new Date());
		//提前一月预约  月
		SimpleDateFormat monthFormat =new SimpleDateFormat("MM");		
		String monthtime =monthFormat.format(vo.getOrderTime());
		String nowmonth =monthFormat.format(new Date());
		int ordermonth =Integer.parseInt(monthtime);
		int month =Integer.parseInt(nowmonth);
		int day=0;
		if(Integer.parseInt(monthtime)>Integer.parseInt(nowmonth)){
			if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				day=31-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}else if(month==2){
				day=28-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}else{
				day=30-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}
		}else{
			day =Integer.parseInt(orderTime)-Integer.parseInt(nowTime);
		}	
		return day+","+vo.getOrderNumber();
	}
	@Override
	public List<OrderImmuneVo> getOrderListByuid(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getOrderListByuid(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				v.setHospitalName(vi.getHospitalName());
				v.setHospitalAddress(vi.getHospitalAddress());
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getOrderSumByuid(String vo) {
		 
		return orderImmuneDao.getOrderSumByuid(vo);
	}
	@Override
	public List<PhysicalExaminationDictionaryEntity> getPhysicalListByUid(String uid) {
		 
		return orderImmuneDao.getPhysicalListByUid(uid);
	}
	@Override
	public String savePhysicalOrder(OrderParamVo vo) {
		/**在预约体检记录表中新增记录**/
		PhysicalExaminationOrderEntity entity=new PhysicalExaminationOrderEntity();
		StringBuffer orderNumberBuffer = new StringBuffer();
		orderNumberBuffer.append("TJ");
		Date createTime = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String dateString = myFmt.format(createTime);
		orderNumberBuffer.append(dateString).append(new Date().getTime()+""); 
		/*if(DateUtils.physicalExaminationNumber>9999){
			DateUtils.physicalExaminationNumber=0;
		}
	    DateUtils.physicalExaminationNumber+=1;
	    int number =DateUtils.physicalExaminationNumber;
		orderNumberBuffer.append(DateUtils.addZero(4,number));	*/
		entity.setOrderNumber(orderNumberBuffer.toString());
		entity.setCreateTime(createTime);
		entity.setCreatorUid(vo.getUid());
		entity.setOrderStatus("1");
		entity.setPatientId(vo.getPatientIds());
		entity.setClinicTime(vo.getClinicTime());
		entity.setOrderTime(vo.getClinicDate());
		entity.setTjName(vo.getTjName());
		int suc=patientOrderDao.savePhysicalOrder(entity);
		return orderNumberBuffer.toString();
	}
	@Override
	public int cacelPhysicalOrder(PhysicalExaminationOrderEntity bo) {
		 
		int num=patientOrderDao.updatePhysicalOrderStatus(bo);
		return num;
	}
	@Override
	public String getPhysicalOrderByuid(PhysicalExaminationOrderEntity bo) {
		
		PhysicalExaminationOrderEntity vo = patientOrderDao.getPhysicalOrderByuid(bo);
		if(vo==null){  //还未预约
			return null;
		}
		//预约的是上午的号
		if("0".equals(vo.getClinicTime())){
			if (new Date().getTime() > (vo.getOrderTime().getTime()+(12*60*60*1000))) {
				//已超过预约时间,表示已完成整个流程
				vo.setOrderStatus("3");
				patientOrderDao.updatePhysicalOrderStatusOfFinish(vo);
				return "overdue";
			}
		}else if("1".equals(vo.getClinicTime())){ //说明预约的是下午的号
			if (new Date().getTime() > (vo.getOrderTime().getTime()+(24*60*60*1000))) {
				//已超过预约时间,表示已完成整个流程
				vo.setOrderStatus("3");
				patientOrderDao.updatePhysicalOrderStatusOfFinish(vo);
				return "overdue";
			}
		}
		/*if (new Date().getTime() > vo.getOrderTime().getTime()) {
			//超过预约时间,表示已完成整个流程
			vo.setOrderStatus("3");
			patientOrderDao.updatePhysicalOrderStatusOfFinish(vo);
			return "overdue";
		}*/
		//日
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd");
		String orderTime=simpleDateFormat.format(vo.getOrderTime());
		String nowTime=simpleDateFormat.format(new Date());
		//提前一月预约  月
		SimpleDateFormat monthFormat =new SimpleDateFormat("MM");		
		String monthtime =monthFormat.format(vo.getOrderTime());
		String nowmonth =monthFormat.format(new Date());
		int ordermonth =Integer.parseInt(monthtime);
		int month =Integer.parseInt(nowmonth);
		int day=0;
		if(Integer.parseInt(monthtime)>Integer.parseInt(nowmonth)){
			if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				day=31-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}else if(month==2){
				day=28-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}else{
				day=30-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}
		}else{
			day =Integer.parseInt(orderTime)-Integer.parseInt(nowTime);
		}	
		return day+","+vo.getOrderNumber();
	}
	@Override
	public List<PhysicalExaminationOrderVo> getPhysicalOrderListByuid(OrderParamVo vo) {
		List<PhysicalExaminationOrderVo> list=orderImmuneDao.getPhysicalOrderListByuid(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(PhysicalExaminationOrderVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				v.setHospitalName(vi.getHospitalName());
				v.setHospitalAddress(vi.getHospitalAddress());
			}
		}
		return list;
	}
	@Override
	public List<PhysicalExaminationOrderVo> getPhysicalOrderListSumByuid(String uid) {
		 
		return orderImmuneDao.getPhysicalOrderListSumByuid(uid);
	}
	@Override
	public List<FollowUpDictionaryEntity> getFollowUpListByUid(String uid) {
	 
		return orderImmuneDao.getFollowUpListByUid(uid);
	}
	@Override
	public String saveFollowUpOrder(OrderParamVo vo) {
		/**在预约随访记录表中新增记录**/
		FollowUpOrderEntity entity=new FollowUpOrderEntity();
		StringBuffer orderNumberBuffer = new StringBuffer();
		orderNumberBuffer.append("SF");
		Date createTime = new Date();
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMdd");
		String dateString = myFmt.format(createTime);
		orderNumberBuffer.append(dateString).append(new Date().getTime()+""); 
		/*if(DateUtils.followUpNumber>9999){
			DateUtils.followUpNumber =0;
		}
	    DateUtils.followUpNumber+=1;
	    int number =DateUtils.followUpNumber;
		orderNumberBuffer.append(DateUtils.addZero(4,number));	*/
		entity.setOrderNumber(orderNumberBuffer.toString());
		entity.setCreateTime(createTime);
		entity.setCreatorUid(vo.getUid());
		entity.setOrderStatus("1");
		entity.setPatientId(vo.getPatientIds());
		entity.setClinicTime(vo.getClinicTime());
		entity.setOrderTime(vo.getClinicDate());
		entity.setFpName(vo.getFpName());
		int suc=patientOrderDao.saveFollowUpOrder(entity);
		return orderNumberBuffer.toString();
	}
	@Override
	public int cacelFollowUpOrder(FollowUpOrderEntity bo) {
		int num=patientOrderDao.updateFollowUpOrderStatus(bo);
		return num;
	}
	@Override
	public String getFollowUpOrderByuid(FollowUpOrderEntity bo) {
		FollowUpOrderEntity vo = patientOrderDao.getFollowUpOrderByuid(bo);
		if(vo==null){  //还未预约
			return null;
		}
		//预约的是上午的号
		if("0".equals(vo.getClinicTime())){
			if (new Date().getTime() > (vo.getOrderTime().getTime()+(12*60*60*1000))) {
				//已超过预约时间,表示已完成整个流程
				vo.setOrderStatus("3");
				patientOrderDao.updateFollowUpOrderStatusOfFinish(vo);
				return "overdue";
			}
		}else if("1".equals(vo.getClinicTime())){ //说明预约的是下午的号
			//12点为分割
			if (new Date().getTime() > (vo.getOrderTime().getTime()+(24*60*60*1000))) {
				//已超过预约时间,表示已完成整个流程
				vo.setOrderStatus("3");
				patientOrderDao.updateFollowUpOrderStatusOfFinish(vo);
				return "overdue";
			}
		}
		/*if (new Date().getTime() > vo.getOrderTime().getTime()) {
			//超过预约时间,表示订单已完成
			vo.setOrderStatus("3");
			patientOrderDao.updateFollowUpOrderStatusOfFinish(vo);
			return "overdue";
		}*/
		//日
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd");
		String orderTime=simpleDateFormat.format(vo.getOrderTime());
		String nowTime=simpleDateFormat.format(new Date());
		//提前一月预约  月
		SimpleDateFormat monthFormat =new SimpleDateFormat("MM");		
		String monthtime =monthFormat.format(vo.getOrderTime());
		String nowmonth =monthFormat.format(new Date());
		int ordermonth =Integer.parseInt(monthtime);
		int month =Integer.parseInt(nowmonth);
		int day=0;
		if(Integer.parseInt(monthtime)>Integer.parseInt(nowmonth)){
			if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
				day=31-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}else if(month==2){
				day=28-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}else{
				day=30-Integer.parseInt(nowTime)+Integer.parseInt(orderTime);
			}
		}else{
			day =Integer.parseInt(orderTime)-Integer.parseInt(nowTime);
		}	
		return day+","+vo.getOrderNumber();
	}
	@Override
	public List<FollowUpOrderVo> getFollowUpOrderList(OrderParamVo vo) {
		List<FollowUpOrderVo> list=orderImmuneDao.getFollowUpOrderList(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(FollowUpOrderVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				v.setHospitalName(vi.getHospitalName());
				v.setHospitalAddress(vi.getHospitalAddress());
			}
		}
		return list;
	}
	@Override
	public List<FollowUpOrderVo> getFollowUpOrderSumByuid(String uid) {
		return orderImmuneDao.getFollowUpOrderSumByuid(uid);
	}
	@Override
	public List<OrderImmuneVo> getImmuneOrderListOfHistoryByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getImmuneOrderListOfHistoryByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getImmuneOrderListOfHistorySumByDoc(String userName) {
		return orderImmuneDao.getImmuneOrderListOfHistorySumByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getImmuneOrderListOfWeekdayByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getImmuneOrderListOfWeekdayByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getImmuneOrderListOfWeekdaySumByDoc(String userName) {
	 
		return  orderImmuneDao.getImmuneOrderListOfWeekdaySumByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getFollowUpOrderListOfHistorySumByDoc(String userName) {
		 
		return orderImmuneDao.getFollowUpOrderListOfHistorySumByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getFollowUpOrderListOfHistoryByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getFollowUpOrderListOfHistoryByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getFollowUpOrderListSumOfWeekdayByDoc(String userName) {
		 
		return  orderImmuneDao.getFollowUpOrderListSumOfWeekdayByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getFollowUpOrderListOfWeekdayByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getFollowUpOrderListOfWeekdayByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfWeekdayByDoc(String userName) {
		 
		return orderImmuneDao.getPhysicalExaminationOrderListSumOfWeekdayByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfWeekdayByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getPhysicalExaminationOrderListOfWeekdayByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfHistoryByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getPhysicalExaminationOrderListOfHistoryByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfHistoryByDoc(String userName) {
		 
		return orderImmuneDao.getPhysicalExaminationOrderListSumOfHistoryByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getImmuneOrderListSumOfDayByDoc(String userName) {
		 
		return orderImmuneDao.getImmuneOrderListSumOfDayByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getImmuneOrderListOfDayByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getImmuneOrderListOfDayByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getImmuneOrderListOfTomorrowSumByDoc(String userName) {
		 
		return orderImmuneDao.getImmuneOrderListOfTomorrowSumByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getImmuneOrderListOfTomorrowByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getImmuneOrderListOfTomorrowByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfDayByDoc(String userName) {
		 
		return orderImmuneDao.getPhysicalExaminationOrderListSumOfDayByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfDayByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getPhysicalExaminationOrderListOfDayByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getPhysicalExaminationOrderListSumOfTomorrowByDoc(String userName) {
		 
		return orderImmuneDao.getPhysicalExaminationOrderListSumOfTomorrowByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getPhysicalExaminationOrderListOfTomorrowByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getPhysicalExaminationOrderListOfTomorrowByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getFollowUpOrderListSumOfDayByDoc(String userName) {
		 
		return orderImmuneDao.getFollowUpOrderListSumOfDayByDoc(userName);
	}
	@Override
	public List<OrderImmuneVo> getFollowUpOrderListOfDayByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getFollowUpOrderListOfDayByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getFollowUpOrderListOfTomorrowByDoc(OrderParamVo vo) {
		List<OrderImmuneVo> list=orderImmuneDao.getFollowUpOrderListOfTomorrowByDoc(vo);
		OrderImmuneVo vi =orderImmuneDao.getHospitalByuid(vo.getUid());
		if(list.size()>0){
			for(OrderImmuneVo v:list){
				StringBuffer personname = new StringBuffer();
				String[] patinetIds =v.getPatientId().split(",");
				for(String str:patinetIds){
					String name =orderImmuneDao.getPersonNameByPersonid(str);
					personname.append(name).append(",");
				}
				v.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
				if(StringUtils.isEmpty(v.getPhoneNumber())){
					v.setPhoneNumber("");
				}
			}
		}
		return list;
	}
	@Override
	public List<OrderImmuneVo> getFollowUpOrderListSumOfTomorrowByDoc(String userName) {
		 
		return orderImmuneDao.getFollowUpOrderListSumOfTomorrowByDoc(userName);
	}
	@Override
	public OrderImmuneVo getPhysicalExaminationOrderDetailByDoc(OrderParamVo vo) {
		OrderImmuneVo list=orderImmuneDao.getPhysicalExaminationOrderDetailByDoc(vo);	 
		StringBuffer personname = new StringBuffer();
		String[] patinetIds =list.getPatientId().split(",");
		for(String str:patinetIds){
			String name =orderImmuneDao.getPersonNameByPersonid(str);
			personname.append(name).append(",");
		}
		list.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
		if(StringUtils.isEmpty(list.getPhoneNumber())){
			list.setPhoneNumber("");
		} 
		return list;
	}
	@Override
	public OrderImmuneVo getFollowUpOrderDetailByDoc(OrderParamVo vo) {
		OrderImmuneVo list=orderImmuneDao.getFollowUpOrderDetailByDoc(vo);	 	 
		StringBuffer personname = new StringBuffer();
		String[] patinetIds =list.getPatientId().split(",");
		for(String str:patinetIds){
			String name =orderImmuneDao.getPersonNameByPersonid(str);
			personname.append(name).append(",");
		}
		list.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
		if(StringUtils.isEmpty(list.getPhoneNumber())){
			list.setPhoneNumber("");
		}
	 
		return list;
	}
	@Override
	public OrderImmuneVo getImmuneOrderDetailByDoc(OrderParamVo vo) {
		OrderImmuneVo list=orderImmuneDao.getImmuneOrderDetailByDoc(vo);	 	
		StringBuffer personname = new StringBuffer();
		String[] patinetIds =list.getPatientId().split(",");
		for(String str:patinetIds){
			String name =orderImmuneDao.getPersonNameByPersonid(str);
			personname.append(name).append(",");
		}
		list.setPatientName(personname.deleteCharAt(personname.length()-1).toString());
		if(StringUtils.isEmpty(list.getPhoneNumber())){
			list.setPhoneNumber("");
		}
	
		return list;
	}
	 

}
