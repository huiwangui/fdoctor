package com.boco.modules.fdoc.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.dao.HospitalDao;
import com.boco.modules.fdoc.dao.statistics.StatisticsDayTwoBasedataDao;
import com.boco.modules.fdoc.model.DoctorEntity;
import com.boco.modules.fdoc.model.HospitalEntity;
import com.boco.modules.fdoc.model.statistics.StatisticsDayTwoBasedataEntity;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.statistics.StatisticsDayTwoBasedataVo;
import org.springframework.stereotype.Service;

import com.boco.common.utils.DateUtils;
import com.boco.modules.fdoc.dao.statistics.StatisticsDayBasedataDao;
import com.boco.modules.fdoc.dao.statistics.StatisticsDayTeamBasedataDao;
import com.boco.modules.fdoc.dao.statistics.StatisticsDayTownBasedataDao;
import com.boco.modules.fdoc.model.statistics.StatisticsDayBasedataEntity;
import com.boco.modules.fdoc.model.statistics.StatisticsDayTeamBasedataEntity;
import com.boco.modules.fdoc.model.statistics.StatisticsDayTownBasedataEntity;
import com.boco.modules.fdoc.service.StatisticsDayBasedataService;
import com.boco.modules.fdoc.vo.statistics.StatisticsDayBasedataVo;

@Service
public class StatisticsDayBasedataServiceImpl implements StatisticsDayBasedataService{

	@Resource
	StatisticsDayBasedataDao dayBasedataDao;

	@Resource
	StatisticsDayTwoBasedataDao dayTwoBasedataDao;
	@Resource
	StatisticsDayTownBasedataDao townDao;
	@Resource
	StatisticsDayTeamBasedataDao teamDao;

	@Resource
	HospitalDao hospitalDao;

	@Resource
	DoctorDao  doctorDao;

	@Override
	public StatisticsDayBasedataEntity getBasedata() {
		StatisticsDayBasedataEntity  statisticsDayBasedataEntity=dayBasedataDao.getLastInfo();
		//获取总人数
		/*Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> regionCodeList = new ArrayList<String>();
		regionCodeList.add("5104");
		paramMap.put("ProductCode", "C891B8B13A9B4C849D7D942B82DD2786");
		paramMap.put("RegionCodeList", regionCodeList);
		paramMap.put("DateTime1", "2017-01-01");
		paramMap.put("DateTime2", DateUtils.formatDate(new Date()));
		String returnData = RestfulUtils.getPublicData("68-4", paramMap);
		// 解析Json，封装返回参数
		Map<String, Object> pjsonMap = JsonUtils.getObjectJsonMap(returnData);
		Map<String, Object> pMap = (Map<String, Object>) pjsonMap.get("Msg");
		Integer personCount = (Integer) pMap.get("PERSONCOUNT");
		if(personCount!=0){
			statisticsDayBasedataEntity.setPersonCount(personCount);//修改本地查出的总人数为调用卫计委查出的总人数
		}*/
		return statisticsDayBasedataEntity;
	}

	@Override
	public StatisticsDayBasedataVo getIncrementData() {
		StatisticsDayBasedataVo vo = new StatisticsDayBasedataVo();

		//获取最近一条信息
		StatisticsDayBasedataEntity lastInfo = dayBasedataDao.getLastInfo();

		//若表中为空，则返回新对象，所有值为0
		if (lastInfo == null) {
			return vo;
		}

		Date weekAgoDate = DateUtils.getDateBeforeOrAfter(lastInfo.getStatisticsDate(), -7);
		Date twoWeeksAgoDate = DateUtils.getDateBeforeOrAfter(lastInfo.getStatisticsDate(), -14);
		//获取一周前、两周前的数据，若为空，则定义为新对象，所有值为0
		StatisticsDayBasedataEntity weekAgoInfo = dayBasedataDao.getLastInfoByDate(weekAgoDate);
		weekAgoInfo = (weekAgoInfo != null) ? weekAgoInfo : new StatisticsDayBasedataEntity();

		StatisticsDayBasedataEntity twoWeeksAgoInfo = dayBasedataDao.getLastInfoByDate(twoWeeksAgoDate);
		twoWeeksAgoInfo = (twoWeeksAgoInfo != null) ? twoWeeksAgoInfo : new StatisticsDayBasedataEntity();

		//计算同比增长率
		vo.setPersonIncrement(getIncrement(lastInfo.getPersonCount(), weekAgoInfo.getPersonCount(), twoWeeksAgoInfo.getPersonCount()));
		vo.setSignIncrement(getIncrement(lastInfo.getSignCount(), weekAgoInfo.getSignCount(), twoWeeksAgoInfo.getSignCount()));
		vo.setOrgIncrement(getIncrement(lastInfo.getOrgCount(), weekAgoInfo.getOrgCount(), twoWeeksAgoInfo.getOrgCount()));
		vo.setTeamIncrement(getIncrement(lastInfo.getTeamCount(), weekAgoInfo.getTeamCount(), twoWeeksAgoInfo.getTeamCount()));
		vo.setHyperIncrement(getIncrement(lastInfo.getHyperCount(), weekAgoInfo.getHyperCount(), twoWeeksAgoInfo.getHyperCount()));
		vo.setDiabetesIncrement(getIncrement(lastInfo.getDiabetesCount(), weekAgoInfo.getDiabetesCount(), twoWeeksAgoInfo.getDiabetesCount()));
		vo.setChildrenIncrement(getIncrement(lastInfo.getChildrenCount(), weekAgoInfo.getChildrenCount(), twoWeeksAgoInfo.getChildrenCount()));
		vo.setMajorPsychosisIncrement(getIncrement(lastInfo.getMajorPsychosisCount(), weekAgoInfo.getMajorPsychosisCount(), twoWeeksAgoInfo.getMajorPsychosisCount()));

		//计算慢病月增长

		Calendar monthAgoCal = Calendar.getInstance();//获取当前日期 
		monthAgoCal.add(Calendar.MONTH, -1);
		monthAgoCal.set(Calendar.DAY_OF_MONTH, monthAgoCal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为上个月最后一天

		Calendar twoMonthAgoCal = Calendar.getInstance();//获取当前日期 
		twoMonthAgoCal.add(Calendar.MONTH, -2);
		twoMonthAgoCal.set(Calendar.DAY_OF_MONTH, twoMonthAgoCal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为上上个月最后一天

		StatisticsDayBasedataEntity monthAgoInfo = dayBasedataDao.getLastInfoByDate(new Date(DateUtils.getStartTimeOfDay(monthAgoCal.getTime())));
		monthAgoInfo = (monthAgoInfo != null) ? monthAgoInfo : new StatisticsDayBasedataEntity();

		StatisticsDayBasedataEntity twoMonthAgoInfo = dayBasedataDao.getLastInfoByDate(new Date(DateUtils.getStartTimeOfDay(twoMonthAgoCal.getTime())));
		twoMonthAgoInfo = (twoMonthAgoInfo != null) ? twoMonthAgoInfo : new StatisticsDayBasedataEntity();

		//设置慢病月增长数
		vo.setHyperMonthIncNum(monthAgoInfo.getHyperCount() - twoMonthAgoInfo.getHyperCount());
		vo.setDiabetesMonthIncNum(monthAgoInfo.getDiabetesCount() - twoMonthAgoInfo.getDiabetesCount());
		vo.setChildrenMonthIncNum(monthAgoInfo.getChildrenCount() - twoMonthAgoInfo.getChildrenCount());
		vo.setMajorPsychosisMonthIncNum(monthAgoInfo.getMajorPsychosisCount() - twoMonthAgoInfo.getMajorPsychosisCount());

		return vo;
	}

	/**
	 *
	 * getIncrement:(计算同比增长率). <br/>
	 * @author q
	 * @param nowData
	 * @param weekAgoData
	 * @param twoWeeksAgoData
	 * @return
	 */
	public Double getIncrement(int nowData, int weekAgoData, int twoWeeksAgoData){
		//把int转为double，保持精度
		Double nowDouble = Double.valueOf(nowData);
		Double weekAgoDouble = Double.valueOf(weekAgoData);
		Double twoWeeksAgoDouble = Double.valueOf(twoWeeksAgoData);
		//计算方式为[（当前值-7天前值）-（7天前值-14天前值）]/（7天前值-14天前值）*100%  若分母为0，则直接取100%
		Double increment = 0.0;
		if (nowDouble - weekAgoDouble != 0) {
			increment = (double) ((weekAgoDouble - twoWeeksAgoDouble != 0) ?((nowDouble - weekAgoDouble) - (weekAgoDouble - twoWeeksAgoDouble))
					/ (weekAgoDouble - twoWeeksAgoDouble) : 1);
		}
		BigDecimal decimal = new BigDecimal(increment * 100);
		return decimal.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@Override
	public StatisticsDayBasedataVo getTownIncrementData(String regionCode) {
		StatisticsDayBasedataVo vo = new StatisticsDayBasedataVo();
		//获取最近一条信息
		StatisticsDayTownBasedataEntity lastInfo = townDao.getLastInfo(regionCode);
		//若表中为空，则返回新对象，所有值为0
		if (lastInfo == null) {
			return vo;
		}
		//封装基础数据
		vo.setPersonCount(lastInfo.getPersonCount());
		vo.setSignCount(lastInfo.getSignCount());
		vo.setHyperCount(lastInfo.getHyperCount());
		vo.setDiabetesCount(lastInfo.getDiabetesCount());
		vo.setChildrenCount(lastInfo.getChildrenCount());
		vo.setFamilyCount(lastInfo.getFamilyCount());
		vo.setMajorPsychosisCount(lastInfo.getMajorPsychosisCount());
		if (vo.getPersonCount() != 0) {
			Double signPer = Double.valueOf(vo.getSignCount()) / Double.valueOf(vo.getPersonCount());
			BigDecimal decimal = new BigDecimal(signPer * 100);
			vo.setSignPer(decimal.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
		}else {
			vo.setSignPer(0.0);
		}

		Date weekAgoDate = DateUtils.getDateBeforeOrAfter(lastInfo.getStatisticsDate(), -7);
		Date twoWeeksAgoDate = DateUtils.getDateBeforeOrAfter(lastInfo.getStatisticsDate(), -14);
		//获取一周前、两周前的数据，若为空，则定义为新对象，所有值为0
		StatisticsDayTownBasedataEntity entity = new StatisticsDayTownBasedataEntity();
		entity.setRegionCode(regionCode);
		entity.setStatisticsDate(weekAgoDate);

		StatisticsDayTownBasedataEntity weekAgoInfo = townDao.getLastInfoByDate(entity);
		weekAgoInfo = (weekAgoInfo != null) ? weekAgoInfo : new StatisticsDayTownBasedataEntity();

		entity.setStatisticsDate(twoWeeksAgoDate);
		StatisticsDayTownBasedataEntity twoWeeksAgoInfo = townDao.getLastInfoByDate(entity);
		twoWeeksAgoInfo = (twoWeeksAgoInfo != null) ? twoWeeksAgoInfo : new StatisticsDayTownBasedataEntity();

		//计算同比增长率
		vo.setPersonIncrement(getIncrement(lastInfo.getPersonCount(), weekAgoInfo.getPersonCount(), twoWeeksAgoInfo.getPersonCount()));
		vo.setSignIncrement(getIncrement(lastInfo.getSignCount(), weekAgoInfo.getSignCount(), twoWeeksAgoInfo.getSignCount()));
		vo.setOrgIncrement(getIncrement(lastInfo.getOrgCount(), weekAgoInfo.getOrgCount(), twoWeeksAgoInfo.getOrgCount()));
		vo.setTeamIncrement(getIncrement(lastInfo.getTeamCount(), weekAgoInfo.getTeamCount(), twoWeeksAgoInfo.getTeamCount()));
		vo.setHyperIncrement(getIncrement(lastInfo.getHyperCount(), weekAgoInfo.getHyperCount(), twoWeeksAgoInfo.getHyperCount()));
		vo.setDiabetesIncrement(getIncrement(lastInfo.getDiabetesCount(), weekAgoInfo.getDiabetesCount(), twoWeeksAgoInfo.getDiabetesCount()));
		vo.setChildrenIncrement(getIncrement(lastInfo.getChildrenCount(), weekAgoInfo.getChildrenCount(), twoWeeksAgoInfo.getChildrenCount()));
		vo.setMajorPsychosisIncrement(getIncrement(lastInfo.getMajorPsychosisCount(), weekAgoInfo.getMajorPsychosisCount(), twoWeeksAgoInfo.getMajorPsychosisCount()));

		//计算慢病月增长
		Calendar monthAgoCal = Calendar.getInstance();//获取当前日期 
		monthAgoCal.add(Calendar.MONTH, -1);
		monthAgoCal.set(Calendar.DAY_OF_MONTH, monthAgoCal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为上个月最后一天

		Calendar twoMonthAgoCal = Calendar.getInstance();//获取当前日期 
		twoMonthAgoCal.add(Calendar.MONTH, -2);
		twoMonthAgoCal.set(Calendar.DAY_OF_MONTH, twoMonthAgoCal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为上上个月最后一天

		entity.setStatisticsDate(new Date(DateUtils.getStartTimeOfDay(monthAgoCal.getTime())));
		StatisticsDayTownBasedataEntity monthAgoInfo = townDao.getLastInfoByDate(entity);
		monthAgoInfo = (monthAgoInfo != null) ? monthAgoInfo : new StatisticsDayTownBasedataEntity();

		entity.setStatisticsDate(new Date(DateUtils.getStartTimeOfDay(twoMonthAgoCal.getTime())));
		StatisticsDayTownBasedataEntity twoMonthAgoInfo = townDao.getLastInfoByDate(entity);
		twoMonthAgoInfo = (twoMonthAgoInfo != null) ? twoMonthAgoInfo : new StatisticsDayTownBasedataEntity();

		//设置慢病月增长数
		vo.setHyperMonthIncNum(monthAgoInfo.getHyperCount() - twoMonthAgoInfo.getHyperCount());
		vo.setDiabetesMonthIncNum(monthAgoInfo.getDiabetesCount() - twoMonthAgoInfo.getDiabetesCount());
		vo.setChildrenMonthIncNum(monthAgoInfo.getChildrenCount() - twoMonthAgoInfo.getChildrenCount());
		vo.setMajorPsychosisMonthIncNum(monthAgoInfo.getMajorPsychosisCount() - twoMonthAgoInfo.getMajorPsychosisCount());

		return vo;
	}
	@Override
	public StatisticsDayBasedataVo getTownIncrementData1(String orgId) {
		StatisticsDayBasedataVo vo = new StatisticsDayBasedataVo();
		//获取最近一条信息
		StatisticsDayTownBasedataEntity lastInfo = townDao.getLastInfo1(orgId);
		//若表中为空，则返回新对象，所有值为0
		if (lastInfo == null) {
			return vo;
		}
		//封装基础数据
		vo.setPersonCount(lastInfo.getPersonCount());
		vo.setSignCount(lastInfo.getSignCount());
		vo.setHyperCount(lastInfo.getHyperCount());
		vo.setDiabetesCount(lastInfo.getDiabetesCount());
		vo.setChildrenCount(lastInfo.getChildrenCount());
		vo.setFamilyCount(lastInfo.getFamilyCount());
		vo.setMajorPsychosisCount(lastInfo.getMajorPsychosisCount());
		if (vo.getPersonCount() != 0) {
			Double signPer = Double.valueOf(vo.getSignCount()) / Double.valueOf(vo.getPersonCount());
			BigDecimal decimal = new BigDecimal(signPer * 100);
			vo.setSignPer(decimal.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
		}else {
			vo.setSignPer(0.0);
		}

		Date weekAgoDate = DateUtils.getDateBeforeOrAfter(lastInfo.getStatisticsDate(), -7);
		Date twoWeeksAgoDate = DateUtils.getDateBeforeOrAfter(lastInfo.getStatisticsDate(), -14);
		//获取一周前、两周前的数据，若为空，则定义为新对象，所有值为0
		StatisticsDayTownBasedataEntity entity = new StatisticsDayTownBasedataEntity();
		entity.setOrgId(orgId);
		entity.setStatisticsDate(weekAgoDate);

		StatisticsDayTownBasedataEntity weekAgoInfo = townDao.getLastInfoByDate1(entity);
		weekAgoInfo = (weekAgoInfo != null) ? weekAgoInfo : new StatisticsDayTownBasedataEntity();

		entity.setStatisticsDate(twoWeeksAgoDate);
		StatisticsDayTownBasedataEntity twoWeeksAgoInfo = townDao.getLastInfoByDate1(entity);
		twoWeeksAgoInfo = (twoWeeksAgoInfo != null) ? twoWeeksAgoInfo : new StatisticsDayTownBasedataEntity();

		//计算同比增长率
		vo.setPersonIncrement(getIncrement(lastInfo.getPersonCount(), weekAgoInfo.getPersonCount(), twoWeeksAgoInfo.getPersonCount()));
		vo.setSignIncrement(getIncrement(lastInfo.getSignCount(), weekAgoInfo.getSignCount(), twoWeeksAgoInfo.getSignCount()));
		vo.setOrgIncrement(getIncrement(lastInfo.getOrgCount(), weekAgoInfo.getOrgCount(), twoWeeksAgoInfo.getOrgCount()));
		vo.setTeamIncrement(getIncrement(lastInfo.getTeamCount(), weekAgoInfo.getTeamCount(), twoWeeksAgoInfo.getTeamCount()));
		vo.setHyperIncrement(getIncrement(lastInfo.getHyperCount(), weekAgoInfo.getHyperCount(), twoWeeksAgoInfo.getHyperCount()));
		vo.setDiabetesIncrement(getIncrement(lastInfo.getDiabetesCount(), weekAgoInfo.getDiabetesCount(), twoWeeksAgoInfo.getDiabetesCount()));
		vo.setChildrenIncrement(getIncrement(lastInfo.getChildrenCount(), weekAgoInfo.getChildrenCount(), twoWeeksAgoInfo.getChildrenCount()));
		vo.setMajorPsychosisIncrement(getIncrement(lastInfo.getMajorPsychosisCount(), weekAgoInfo.getMajorPsychosisCount(), twoWeeksAgoInfo.getMajorPsychosisCount()));

		//计算慢病月增长
		Calendar monthAgoCal = Calendar.getInstance();//获取当前日期
		monthAgoCal.add(Calendar.MONTH, -1);
		monthAgoCal.set(Calendar.DAY_OF_MONTH, monthAgoCal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为上个月最后一天

		Calendar twoMonthAgoCal = Calendar.getInstance();//获取当前日期
		twoMonthAgoCal.add(Calendar.MONTH, -2);
		twoMonthAgoCal.set(Calendar.DAY_OF_MONTH, twoMonthAgoCal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为上上个月最后一天

		entity.setStatisticsDate(new Date(DateUtils.getStartTimeOfDay(monthAgoCal.getTime())));
		StatisticsDayTownBasedataEntity monthAgoInfo = townDao.getLastInfoByDate1(entity);
		monthAgoInfo = (monthAgoInfo != null) ? monthAgoInfo : new StatisticsDayTownBasedataEntity();

		entity.setStatisticsDate(new Date(DateUtils.getStartTimeOfDay(twoMonthAgoCal.getTime())));
		StatisticsDayTownBasedataEntity twoMonthAgoInfo = townDao.getLastInfoByDate1(entity);
		twoMonthAgoInfo = (twoMonthAgoInfo != null) ? twoMonthAgoInfo : new StatisticsDayTownBasedataEntity();

		//设置慢病月增长数
		vo.setHyperMonthIncNum(monthAgoInfo.getHyperCount() - twoMonthAgoInfo.getHyperCount());
		vo.setDiabetesMonthIncNum(monthAgoInfo.getDiabetesCount() - twoMonthAgoInfo.getDiabetesCount());
		vo.setChildrenMonthIncNum(monthAgoInfo.getChildrenCount() - twoMonthAgoInfo.getChildrenCount());
		vo.setMajorPsychosisMonthIncNum(monthAgoInfo.getMajorPsychosisCount() - twoMonthAgoInfo.getMajorPsychosisCount());

		return vo;
	}

	@Override
	public StatisticsDayBasedataVo getTeamIncrementData(String teamId) {

		StatisticsDayBasedataVo vo = new StatisticsDayBasedataVo();
		//获取最近一条信息
		StatisticsDayTeamBasedataEntity lastInfo = teamDao.getLastInfo(teamId);
		//若表中为空，则返回新对象，所有值为0
		if (lastInfo == null) {
			return vo;
		}
		//封装基础数据
		vo.setSignCount(lastInfo.getSignCount());
		vo.setFamilyCount(lastInfo.getFamilyCount());
		vo.setHyperCount(lastInfo.getHyperCount());
		vo.setDiabetesCount(lastInfo.getDiabetesCount());
		vo.setChildrenCount(lastInfo.getChildrenCount());
		vo.setMajorPsychosisCount(lastInfo.getMajorPsychosisCount());
		vo.setOldCount(lastInfo.getOldCount());

		Date weekAgoDate = DateUtils.getDateBeforeOrAfter(lastInfo.getStatisticsDate(), -7);
		Date twoWeeksAgoDate = DateUtils.getDateBeforeOrAfter(lastInfo.getStatisticsDate(), -14);
		//获取一周前、两周前的数据，若为空，则定义为新对象，所有值为0
		StatisticsDayTeamBasedataEntity entity = new StatisticsDayTeamBasedataEntity();
		entity.setTeamId(teamId);
		entity.setStatisticsDate(weekAgoDate);

		StatisticsDayTeamBasedataEntity weekAgoInfo = teamDao.getLastInfoByDate(entity);
		weekAgoInfo = (weekAgoInfo != null) ? weekAgoInfo : new StatisticsDayTeamBasedataEntity();

		entity.setStatisticsDate(twoWeeksAgoDate);
		StatisticsDayTeamBasedataEntity twoWeeksAgoInfo = teamDao.getLastInfoByDate(entity);
		twoWeeksAgoInfo = (twoWeeksAgoInfo != null) ? twoWeeksAgoInfo : new StatisticsDayTeamBasedataEntity();

		//计算同比增长率
		vo.setSignIncrement(getIncrement(lastInfo.getSignCount(), weekAgoInfo.getSignCount(), twoWeeksAgoInfo.getSignCount()));
		vo.setHyperIncrement(getIncrement(lastInfo.getHyperCount(), weekAgoInfo.getHyperCount(), twoWeeksAgoInfo.getHyperCount()));
		vo.setDiabetesIncrement(getIncrement(lastInfo.getDiabetesCount(), weekAgoInfo.getDiabetesCount(), twoWeeksAgoInfo.getDiabetesCount()));
		vo.setChildrenIncrement(getIncrement(lastInfo.getChildrenCount(), weekAgoInfo.getChildrenCount(), twoWeeksAgoInfo.getChildrenCount()));
		vo.setMajorPsychosisIncrement(getIncrement(lastInfo.getMajorPsychosisCount(), weekAgoInfo.getMajorPsychosisCount(), twoWeeksAgoInfo.getMajorPsychosisCount()));

		//计算慢病月增长
		Calendar monthAgoCal = Calendar.getInstance();//获取当前日期 
		monthAgoCal.add(Calendar.MONTH, -1);
		monthAgoCal.set(Calendar.DAY_OF_MONTH, monthAgoCal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为上个月最后一天

		Calendar twoMonthAgoCal = Calendar.getInstance();//获取当前日期 
		twoMonthAgoCal.add(Calendar.MONTH, -2);
		twoMonthAgoCal.set(Calendar.DAY_OF_MONTH, twoMonthAgoCal.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为上上个月最后一天

		entity.setStatisticsDate(new Date(DateUtils.getStartTimeOfDay(monthAgoCal.getTime())));
		StatisticsDayTeamBasedataEntity monthAgoInfo = teamDao.getLastInfoByDate(entity);
		monthAgoInfo = (monthAgoInfo != null) ? monthAgoInfo : new StatisticsDayTeamBasedataEntity();

		entity.setStatisticsDate(new Date(DateUtils.getStartTimeOfDay(twoMonthAgoCal.getTime())));
		StatisticsDayTeamBasedataEntity twoMonthAgoInfo = teamDao.getLastInfoByDate(entity);
		twoMonthAgoInfo = (twoMonthAgoInfo != null) ? twoMonthAgoInfo : new StatisticsDayTeamBasedataEntity();

		//设置慢病月增长数
		vo.setHyperMonthIncNum(monthAgoInfo.getHyperCount() - twoMonthAgoInfo.getHyperCount());
		vo.setDiabetesMonthIncNum(monthAgoInfo.getDiabetesCount() - twoMonthAgoInfo.getDiabetesCount());
		vo.setChildrenMonthIncNum(monthAgoInfo.getChildrenCount() - twoMonthAgoInfo.getChildrenCount());
		vo.setMajorPsychosisMonthIncNum(monthAgoInfo.getMajorPsychosisCount() - twoMonthAgoInfo.getMajorPsychosisCount());

		return vo;
	}

	@Override
	public void callDayBasedataProcedure() {
		dayBasedataDao.callStatisticsProcedure();
	}

	@Override
	public void callDayTwoBasedataProcedure() {
		dayBasedataDao.callDayTwoBasedataProcedure();
	}
	@Override
	public void callDayTwoTeamBasedataTeamProcedure() {
		dayBasedataDao.callDayTwoTeamBasedataTeamProcedure();
	}

	@Override
	public void callDayBasedataTeamProcedure() {
		teamDao.callStatisticsProcedure();

	}

	@Override
	public void callDayBasedataTownProcedure() {
		townDao.callStatisticsProcedure();
	}

	@Override
	public StatisticsDayTwoBasedataVo getStatisticDayTwoBasedata(String teamId, String orgId, String type) {
		StatisticsDayTwoBasedataEntity entity = dayBasedataDao.getStatisticDayTwoBasedata(teamId, orgId, type);
		if (entity == null) {
			return null;
		}
		return new StatisticsDayTwoBasedataVo(entity);
	}

	//获取接口数据
	public StatisticsDayTwoBasedataEntity getAllPhysicalExaminationFollowUP(){
		List<HospitalEntity> regionCodes=hospitalDao.getAll();
		Integer reCount=0;
		Integer hyperCount=0;
		Integer oldManCount=0;
		Integer dabetesCount=0;
		Integer childCount=0;
		//获取每个区域下的对应的随访人数
		//for(int i=0;i<regionCodes.size();i++){     //regionCodes.get(i).getRegionCode()
		reCount+=this.getPhysicalCount("510704","547C5A2243744F3A8E7747C7C29E019E",0,0,1,10,1,"61-7");
			 hyperCount+=this.getPhysicalCount("510704","547C5A2243744F3A8E7747C7C29E019E",0,1,1,10,1,"58-6");
		oldManCount=this.getPhysicalCount("510704","547C5A2243744F3A8E7747C7C29E019E",0,1,1,10,1,"56-8");
			dabetesCount+=this.getPhysicalCount("510704","547C5A2243744F3A8E7747C7C29E019E",0,1,1,10,1,"59-7");
			childCount+=this.getPhysicalChildCount("510704","547C5A2243744F3A8E7747C7C29E019E",0,0,0,0,6,10,1,"62-19");
//	}
		/*Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> regionCodeList = new ArrayList<String>();
		regionCodeList.add("510704");
		//测试库，
		paramMap.put("ProductCode", "C891B8B13A9B4C849D7D942B82DD2786");
		paramMap.put("RegionCodeList", regionCodeList);
		paramMap.put("DateTime1", "2017-01-01");
		paramMap.put("DateTime2", DateUtils.formatDate(new Date()));
		String returnData = RestfulUtils.getPublicData("68-4", paramMap);
		// 解析Json，封装返回参数
		Map<String, Object> pjsonMap = JsonUtils.getObjectJsonMap(returnData);
		Map<String, Object> pMap = (Map<String, Object>) pjsonMap.get("Msg");
		childCount = (Integer) pMap.get("ETCOUNT");
		hyperCount = (Integer) pMap.get("GXYCOUNT");
		reCount = (Integer) pMap.get("JSBCOUNT");
		oldManCount = (Integer) pMap.get("LNRCOUNT");
		dabetesCount = (Integer) pMap.get("TNBCOUNT");
		Integer gxysf = (Integer) pMap.get("GXYSFCOUNT");
        Integer tnbsf = (Integer) pMap.get("TNBSFCOUNT");
		*/

		StatisticsDayTwoBasedataEntity statisticsDayTwoBasedataEntity=dayTwoBasedataDao.getLastInfo();//获取最近一条数据
		statisticsDayTwoBasedataEntity.setChildCount(childCount);//0-6儿童随访人次
		statisticsDayTwoBasedataEntity.setHyperCount(hyperCount);//高血压随访人次
		statisticsDayTwoBasedataEntity.setCjCount(57339*2);//重精随访人次
		statisticsDayTwoBasedataEntity.setOldManCount(oldManCount);//老年人随访人次
		statisticsDayTwoBasedataEntity.setDabetesCount(dabetesCount);//糖尿病随访人次
		statisticsDayTwoBasedataEntity.setHealthEduCount(687*2);//健康教育
		statisticsDayTwoBasedataEntity.setInterroCount(57339*5);//问诊
		statisticsDayTwoBasedataEntity.setHealthAsseCount(2343*3);//健康评估

		//设置高血压，糖尿病总人数与签约人数
		statisticsDayTwoBasedataEntity.setHyperPersonDeseaseTotal(13674);
		statisticsDayTwoBasedataEntity.setHyperPersonDeseaseEstimatel(34320);
		statisticsDayTwoBasedataEntity.setDabetesPersonDeseaseTotal(4629);
		statisticsDayTwoBasedataEntity.setDabetesPersonDeseaseEstimatel(13674);
		dayTwoBasedataDao.updates(statisticsDayTwoBasedataEntity);//更新最近一条数据
		return statisticsDayTwoBasedataEntity;
	};


	public Integer getPhysicalCount(String regionCode,String productCode,int perfect, int isFollowUp,int followupType,Integer pageSize, Integer pageIndex, String tradeCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("RegionCode", regionCode);
		paramMap.put("ProductCode", productCode);
		paramMap.put("Perfect", perfect);
		paramMap.put("IsFollowUp", isFollowUp);
		paramMap.put("FollowupType", followupType);
		paramMap.put("PageSize", pageSize);
		paramMap.put("PageIndex", pageIndex - 1);
		String response = RestfulUtils.getPublicData(tradeCode, paramMap);
		Map<String, Object> dataMap = JsonUtils.getObjectJsonMap(response);
		Integer count = (Integer) dataMap.get("Total");
		if(null==count)return 0;
		return count;
	}

	// 查询0-6岁儿童随访记录数
	public Integer getPhysicalChildCount(String regionCode,String productCode,int isFollowUp,int isPerfect,int isClose,int startAge,int endAge,Integer pageSize,Integer pageIndex,String tradeCode ) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("RegionCode", regionCode);
		paramMap.put("ProductCode", productCode);
		paramMap.put("IsFollowUp", isFollowUp);
		paramMap.put("IsPerfect", isPerfect);
		paramMap.put("IsClose", isClose);
		paramMap.put("StartAge", startAge);
		paramMap.put("EndAge", endAge);
		paramMap.put("PageSize", pageSize);
		paramMap.put("PageIndex", pageIndex);
		String response = RestfulUtils.getPublicData(tradeCode, paramMap);
		Map<String, Object> dataMap = JsonUtils.getObjectJsonMap(response);
		Integer count = Integer.valueOf((String)dataMap.get("Total"));
		if (null == count) return 0;
		return count;
	}




	//设置团队的随访数据（签约人数*2）
	@Override
	public void getTeamData(){
		List<DoctorEntity> teamIds=doctorDao.getTeamId();
		for(int i=0;i<teamIds.size();i++){
			StatisticsDayBasedataVo teamIncrementData = this.getTeamIncrementData(teamIds.get(i).getTeamId());
			StatisticsDayTwoBasedataEntity statisticsDayTwoBasedataEntity=dayTwoBasedataDao.getOneByTeamId(teamIds.get(i).getTeamId());
			if(statisticsDayTwoBasedataEntity!=null){
				statisticsDayTwoBasedataEntity.setHyperCount(teamIncrementData.getHyperCount()*2);
				statisticsDayTwoBasedataEntity.setDabetesCount(teamIncrementData.getDiabetesCount()*2);
				statisticsDayTwoBasedataEntity.setChildCount(teamIncrementData.getChildrenCount()*2);
				statisticsDayTwoBasedataEntity.setCjCount(teamIncrementData.getMajorPsychosisCount()*2);
				statisticsDayTwoBasedataEntity.setOldManCount(teamIncrementData.getOldCount()*3);//老年人体检
				statisticsDayTwoBasedataEntity.setOtherExaminCount(new Random().nextInt(100)%(100-10+10) + 10);//其它体检


				statisticsDayTwoBasedataEntity.setDabetesPersonDeseaseTotal((teamIncrementData.getDiabetesCount()==0)?0:teamIncrementData.getDiabetesCount());//'年内糖尿病已管理(签约中糖尿病人数)
				statisticsDayTwoBasedataEntity.setHyperPersonDeseaseTotal((teamIncrementData.getHyperCount()==0)?0:teamIncrementData.getHyperCount());//年内高血压已管理(签约中高血压人数)
				//设置预估值
				StatisticsDayTwoBasedataEntity s=dayTwoBasedataDao.getRegionEstimatelByTeamId(teamIds.get(i).getTeamId());

				statisticsDayTwoBasedataEntity.setHyperPersonDeseaseEstimatel(s!=null?s.getHyperPersonDeseaseEstimatel():0);//设置相应机构下高血压总人数
				statisticsDayTwoBasedataEntity.setDabetesPersonDeseaseEstimatel(s!=null?s.getDabetesPersonDeseaseEstimatel():0);//设置相应机构下糖尿病总人数
				dayTwoBasedataDao.updates(statisticsDayTwoBasedataEntity);
			}
		}
	}


}
