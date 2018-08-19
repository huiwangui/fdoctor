package com.boco.modules.fdoc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.NumberUtils;
import com.boco.modules.fdoc.dao.DiseaseLabelDao;
import com.boco.modules.fdoc.dao.PersonDeseaseInfoDao;
import com.boco.modules.fdoc.dao.PersonInformationDao;
import com.boco.modules.fdoc.dao.SigServicepackDao;
import com.boco.modules.fdoc.dao.SignDao;
import com.boco.modules.fdoc.model.DiseaseLabelEntity;
import com.boco.modules.fdoc.model.DiseaseLabelPersonRelationEntity;
import com.boco.modules.fdoc.model.PersonDeseaseInfoEntity;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.SigServicepackEntity;
import com.boco.modules.fdoc.model.UserDocSignEntity;
import com.boco.modules.fdoc.service.SignService;
import com.boco.modules.fdoc.vo.DoctorDetailVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.SigServicepackVo;
import com.boco.modules.fdoc.vo.SignVo;
import com.boco.modules.fdoc.vo.UserVo;

@Service
public class SignServiceImpl implements SignService {

	@Resource
	SignDao signDao;
	@Resource
	PersonInformationDao personDao;
	@Resource
	PersonDeseaseInfoDao deseaseInfoDao;
    @Resource
    DiseaseLabelDao diseaseLabelDao;
    @Resource
	SigServicepackDao servicePackDao;
	
	
	/**
	 * 签约.
	 */
	@Override
	@Transactional
	public Map<String, Object> sign(UserDocSignEntity signEntity, List<PersonDeseaseInfoEntity> deseaseInfo) {
		Map<String, Object> returnMap = new HashMap<String, Object>(); 
		//判断是否重复签约
		signEntity.setStatus(BusinessConstants.SIGN_STATUS_SIGNED);
		UserDocSignEntity signRecords = signDao.getSignRecordByPersonId(signEntity.getPersonId());
		if (signRecords != null) {
			returnMap.put("returnMsg", BusinessConstants.ERROR_RE_SIGN);
			return returnMap;	//进行中的签约状态数量>1  说明重复签约
		}
		signEntity.setSignTime(new Date());
		
			//插入签约主表,返回插入记录的主键
			signDao.insert(signEntity);
			
			//新增或修改居民慢病标识
			for (PersonDeseaseInfoEntity desease : deseaseInfo) {
				if (deseaseInfoDao.get(desease.getPersonId()) == null) {
					Date newDate = new Date();
					desease.setCreateTime(newDate);
					desease.setUpdateTime(newDate);
					deseaseInfoDao.insert(desease);
				}else {
					desease.setUpdateTime(new Date());
					deseaseInfoDao.update(desease);
				}
			}
			//找到团队对应的标签ID然后新增个人和标签ID的关系
			for (PersonDeseaseInfoEntity desease : deseaseInfo) {
				DiseaseLabelEntity	labelEntity=new DiseaseLabelEntity();
				labelEntity.setDocTeamId(signEntity.getDocTeamId());
				//儿童
				if(BusinessConstants.YES.equals(desease.getChildFlag())){
					labelEntity.setDiseaseCode(BusinessConstants.SIGN_CHILD);	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//高血压
				if(BusinessConstants.YES.equals(desease.getHyperFlag())){
					labelEntity.setDiseaseCode(BusinessConstants.SIGN_HYPER);	
					System.out.println(JsonUtils.getJsonFormat(labelEntity));
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//重型精神病
				if(BusinessConstants.YES.equals(desease.getMajorPsychosisFlag())){
					labelEntity.setDiseaseCode(BusinessConstants.SIGN_MAJOR);	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//糖尿病
				if(BusinessConstants.YES.equals(desease.getDiabetesFlag())){
					labelEntity.setDiseaseCode(BusinessConstants.SIGN_DIABETES);	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//老年人
				if(BusinessConstants.YES.equals(desease.getOldmFlag())){
					labelEntity.setDiseaseCode(BusinessConstants.SIGN_OLDM);	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//普通成人
				if(BusinessConstants.YES.equals(desease.getAdultFlag())){
					labelEntity.setDiseaseCode(BusinessConstants.SIGN_CHENR);	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//孕产妇
				if(BusinessConstants.YES.equals(desease.getMaternalFlag())){
					labelEntity.setDiseaseCode(BusinessConstants.SIGN_YUNCH);	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//特困户
				if(BusinessConstants.YES.equals(desease.getPoorFlag())){
					labelEntity.setLabelName("特困户");	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//残疾人
				if(BusinessConstants.YES.equals(desease.getDisabledFlag())){
					labelEntity.setLabelName("残疾人");	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//低保户
				if(BusinessConstants.YES.equals(desease.getSubsistenceFlag())){
					labelEntity.setLabelName("低保户");	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//五保户
				if(BusinessConstants.YES.equals(desease.getFiveproFlag())){
					labelEntity.setLabelName("五保户");	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//计生特户
				if(BusinessConstants.YES.equals(desease.getFamilyplanFlag())){
					labelEntity.setLabelName("计生特户");	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//结核病
				if(BusinessConstants.YES.equals(desease.getTuberculosisFlag())){
					labelEntity.setLabelName("结核病");	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
				//流动人口
				if(BusinessConstants.YES.equals(desease.getFloatFlag())){
					labelEntity.setLabelName("流动人口");	
					Integer lableId=diseaseLabelDao.selectlableid(labelEntity);
					DiseaseLabelPersonRelationEntity cc=new DiseaseLabelPersonRelationEntity();
					cc.setLabelId(lableId);
					cc.setPersonId(desease.getPersonId());
					diseaseLabelDao.insertRelation(cc);
				}
			}
			
			//修改居民表签约标识
			PersonInformationEntity person = new PersonInformationEntity();
			person.setPersonId(signEntity.getPersonId());
			person.setIfSign(BusinessConstants.PERSON_SIGN_STATUS_SIGNED);
			signDao.updateSignFlag(person);
			//封装返回map
			returnMap.put("returnMsg", BusinessConstants.SUCCESS_SIGN);
			returnMap.put("signId", signEntity.getId());
			return returnMap;
		
		
	}

	@Override
	public List<PersonInformationVo> getSignedList(SignVo vo) {
		return signDao.getSignedList(vo);
	}

	@Override
	public Integer getSignedCount(SignVo vo) {
		return signDao.getSignedCount(vo);
	}


	@Override
	public SignVo getSignDetail(Integer signId) {
		SignVo signDetail = signDao.getSignDetail(signId);
		//封装所选服务包列表信息
		if(signDetail!=null){
			String values = NumberUtils.bitand(signDetail.getServicePackValue()==null?0:signDetail.getServicePackValue());
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put("values", values);
			List<SigServicepackEntity> packs = servicePackDao.getServicePacksByValues(valuesMap);
			signDetail.setPacks(packs);
		}
		return signDetail;
	}

	@Override
	public SignVo getSignDetailByIdCard(String idCard) {
		SignVo signDetail = signDao.getSignDetailByIdCard(idCard);
		//封装所选服务包列表信息
		if(signDetail!=null){
			String values = NumberUtils.bitand(signDetail.getServicePackValue()==null?0:signDetail.getServicePackValue());
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put("values", values);
			List<SigServicepackEntity> packs = servicePackDao.getServicePacksByValues(valuesMap);
			signDetail.setPacks(packs);
		}
		return signDetail;
		
	}

	@Override
	public List<String> getSignTeamUsers(String personId) {
		return signDao.getSignTeamUsers(personId);
	}

	@Override
	public List<String> getSignFamilyUsers(String userName) {
		return signDao.getSignFamilyUsers(userName);
	}

	@Override
	public List<DoctorDetailVo> getSignDoctorTeamInfo(String personId) {
		return signDao.getSignDoctorTeamInfo(personId);
	}

	@Override
	public SignVo getSignedCountByRegion(String regionCode) {
		//封装查询参数
		PersonInformationVo personVo = new PersonInformationVo();
		SignVo signVo = new SignVo();
		personVo.setRegionCode(regionCode);
		signVo.setRegionCode(regionCode);
		
		Integer personCount = personDao.getPersonCountByRegion(personVo);//查询本地数据库的区域人数

		//调用接口获取区域人数
		/*Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> regionCodeList = new ArrayList<String>();
	    regionCodeList.add(regionCode);
		paramMap.put("ProductCode", "C891B8B13A9B4C849D7D942B82DD2786");
		paramMap.put("RegionCodeList", regionCodeList);
		paramMap.put("DateTime1", "2017-01-01");
		paramMap.put("DateTime2", DateUtils.formatDate(new Date()));
		String returnData = RestfulUtils.getPublicData("68-4", paramMap);
		// 解析Json，封装返回参数
		Map<String, Object> pjsonMap = JsonUtils.getObjectJsonMap(returnData);
		Map<String, Object> pMap = (Map<String, Object>) pjsonMap.get("Msg");
		Integer personCount = (Integer) pMap.get("PERSONCOUNT");*/

		Integer signCount = signDao.getSignedCountByRegion(signVo);
		//Integer familyCount = signDao.getSignedFamilyCountByOrgId(regionCode);
		signVo.setSignedCount(signCount);
		signVo.setPersonCount(personCount);
		//signVo.setFamilyCount(familyCount);
		if (personCount != 0) {
			Double signPer = Double.valueOf(signCount) / Double.valueOf(personCount);
			BigDecimal decimal = new BigDecimal(signPer * 100);
			signVo.setSignPer(decimal.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
		}else {
			signVo.setSignPer(Double.valueOf(0));
		}
		
		return signVo;
	}

	@Override
	public List<UserVo> getSignedUserAccound(SignVo vo) {
		return signDao.getSignedUserAccound(vo);
	}

	@Override
	public Integer getSignedUserAccoundCount(SignVo vo) {
		return signDao.getSignedUserAccoundCount(vo);
	}

	@Override
	public List<PersonInformationVo> getPersonListWithSignInfo(PersonInformationVo vo) {
		return signDao.getPersonListWithSignInfo(vo);
	}

	@Override
	public Integer getPersonCountWithSignInfo(PersonInformationVo vo) {
		return signDao.getPersonCountWithSignInfo(vo);
	}

	@Override
	public SignVo getSimpleSignInfo(String personId) {
		//获取简略简约信息
		SignVo signInfo = signDao.getSimpleSignInfo(personId);
		
		//获取标签信息
		List<DiseaseLabelEntity> labels = diseaseLabelDao.getLabelsByPersonId(personId);
		if (signInfo != null) {
			signInfo.setLabels(labels);
		}
		return signInfo;
	} 
	@Override
	public List<SigServicepackVo> getServicePacksByPersonId(UserDocSignEntity entity) {
		//获取签约信息中的服务包权值
		UserDocSignEntity signEntity = signDao.getSignRecord(entity);
		String values = NumberUtils.bitand(signEntity.getServicePackValue()==null?0:signEntity.getServicePackValue());
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put("values", values);
		//根据服务包权值查询服务包列表
		List<SigServicepackVo> servicePacks = servicePackDao.getServicePacksByPackValue(valuesMap);
		//查询签约年限
		int yearOfSign = signDao.getYearOfSign(signEntity);
		if(servicePacks != null && servicePacks.size()>0){
			//服务包的服务年限就是签约有效期
			for (SigServicepackVo sigServicepackVo : servicePacks) {
				sigServicepackVo.setYearOfSigServicepack(yearOfSign);
			}
		}
		return  servicePacks;
	}

	 
	@Override
	public SignVo getPersonInformationDetailByPersonId(SignVo vo) {
		String status = vo.getStatus(); 
		if("3".equals(status)){
			return signDao.getSurrenderPersonInformationDetailByPersonId(vo);
		}else{
			return signDao.getPersonInformationDetailByPersonId(vo);
		}
	}	
}
