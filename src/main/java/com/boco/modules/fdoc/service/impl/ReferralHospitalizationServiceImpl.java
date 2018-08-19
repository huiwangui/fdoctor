package com.boco.modules.fdoc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.constants.BusinessConstants;
import com.boco.common.constants.ReferralSoapConstans;
import com.boco.common.exception.CommonException;
import com.boco.common.soapClient.SoapClient;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.dao.ReferralHospitalizationDao;
import com.boco.modules.fdoc.model.ReferralHospitalizationEntity;
import com.boco.modules.fdoc.service.ReferralHospitalizationService;
import com.boco.modules.fdoc.vo.ReferralHospitalizationVo;
import com.boco.modules.fdoc.vo.soap.ReferralSoapCommonParamVo;
import com.boco.modules.fdoc.vo.soap.ReferralSoapHospitalzationVo;

@Service
public class ReferralHospitalizationServiceImpl implements ReferralHospitalizationService{	 
	@Resource
	ReferralHospitalizationDao referralHospitalizationDao;

	@Override
	@Transactional
	public String insertReferralHospitalizationApply(ReferralHospitalizationEntity entity) {
		
		//设置创建时间、状态
		entity.setCreateTime(new Date());
		entity.setApplyDate(new Date());
		entity.setStatus(BusinessConstants.REFERRAL_STATUS_APPLY);
		
		referralHospitalizationDao.insert(entity);
		
		//调用soap接口进行同步
		ReferralSoapHospitalzationVo soapParam = new ReferralSoapHospitalzationVo(entity);
		SoapClient instance = SoapClient.getInstance(); 	//获取客户端对象
		
		String msg = instance.hospitalizationApply(JsonUtils.getJsonFormat(new ReferralSoapCommonParamVo(ReferralSoapConstans.INPATIENT_APPLY_CODE, ReferralSoapConstans.INPATIENT_APPLY_NAME)), 
				JsonUtils.getJsonFormat(soapParam));
		
		//解析返回信息
		Map<String, String> msgMap = JsonUtils.getSingleJsonMap(msg);
		if (ReferralSoapConstans.SUCCESS_CODE.equals(msgMap.get("resultCode"))) {
			//调用成功，修改本地数据库对照码
			String referralCode = msgMap.get("referral_code");
			entity.setReferralCode(referralCode);
			referralHospitalizationDao.update(entity);
			
			return BusinessConstants.SUCCESS;
		}else {
			//调用失败，抛出异常，回滚数据库
			throw new CommonException();
		}
	}

	@Override
	public String updateReferralHospitalizationApply(ReferralHospitalizationEntity entity) {
		referralHospitalizationDao.update(entity);
		return BusinessConstants.SUCCESS;
	}

	@Override
	public ReferralHospitalizationEntity getReferralHospitalizationInfo(int id) {		 
		return referralHospitalizationDao.getReferralHospitalizationInfo(id);
	}

	@Override
	public ReferralHospitalizationEntity getReferralHospitalizationInfoByCode(
			String referralCode) {
		return referralHospitalizationDao.getReferralHospitalizationInfoByCode(referralCode);
	}

	@Override
	public String updateReferralHospitalizationStatus(
			ReferralHospitalizationEntity entity) {
		referralHospitalizationDao.updateReferralHospitalizationStatus(entity);
		return BusinessConstants.SUCCESS;
	}

	@Override
	public List<ReferralHospitalizationVo> getReferralHospitalizationListBySign(
			ReferralHospitalizationVo vo) {
		return referralHospitalizationDao.getReferralHospitalizationListBySign(vo);
	}

	@Override
	public Integer getReferralHospitalizationCountBySign(ReferralHospitalizationVo vo) {
		return referralHospitalizationDao.getReferralHospitalizationCountBySign(vo);
	}

}
