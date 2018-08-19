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
import com.boco.modules.fdoc.dao.ReferralOutpatientDao;
import com.boco.modules.fdoc.model.ReferralOutpatientEntity;
import com.boco.modules.fdoc.service.ReferralOutpatientService;
import com.boco.modules.fdoc.vo.ReferralOutpatientVo;
import com.boco.modules.fdoc.vo.soap.ReferralSoapCommonParamVo;
import com.boco.modules.fdoc.vo.soap.ReferralSoapOutpatientVo;

@Service
public class ReferralOutpatientServiceImpl implements ReferralOutpatientService{
	
	@Resource
	ReferralOutpatientDao referralOutpatientDao;

	@Override
	@Transactional
	public String insertReferralOutpatientApply(ReferralOutpatientEntity entity) throws CommonException {
		
		//设置创建时间、状态
		entity.setCreateTime(new Date());
		entity.setStatus(BusinessConstants.REFERRAL_STATUS_APPLY);
		
		referralOutpatientDao.insert(entity);
		
		//调用soap接口进行同步
		ReferralSoapOutpatientVo soapParam = new ReferralSoapOutpatientVo(entity);
		SoapClient instance = SoapClient.getInstance(); 	//获取客户端对象
		
		String msg = instance.outpatientApply(JsonUtils.getJsonFormat(new ReferralSoapCommonParamVo(ReferralSoapConstans.OUTPATIENT_APPLY_CODE, ReferralSoapConstans.OUTPATIENT_APPLY_NAME)), 
				JsonUtils.getJsonFormat(soapParam));
		
		//解析返回信息
		Map<String, String> msgMap = JsonUtils.getSingleJsonMap(msg);
		if (ReferralSoapConstans.SUCCESS_CODE.equals(msgMap.get("resultCode"))) {
			//调用成功，修改本地数据库对照码
			String referralCode = msgMap.get("referral_code");
			entity.setReferralCode(referralCode);
			referralOutpatientDao.update(entity);
			
			return BusinessConstants.SUCCESS;
		}else {
			//调用失败，抛出异常，回滚数据库
			throw new CommonException();
		}
		
	}

	@Override
	public String updateReferralOutpatientApply(ReferralOutpatientEntity entity) {
		referralOutpatientDao.update(entity);
		return BusinessConstants.SUCCESS;
	}

	@Override
	public ReferralOutpatientEntity getReferralOutpatientInfo(int id) {
		return referralOutpatientDao.getReferralOutpatientInfo(id);
	}

	@Override
	public ReferralOutpatientEntity getReferralOutpatientInfoByCode(
			String referralCode) {
		return referralOutpatientDao.getReferralOutpatientInfoByCode(referralCode);
	}

	@Override
	public String updateReferralOutpatientStatus(
			ReferralOutpatientEntity entity) {
		referralOutpatientDao.updateReferralOutpatientStatus(entity);
		return BusinessConstants.SUCCESS;
	}

	@Override
	public List<ReferralOutpatientVo> getReferralOutpatientListBySign(
			ReferralOutpatientVo vo) {
		return referralOutpatientDao.getReferralOutpatientListBySign(vo);
	}

	@Override
	public Integer getReferralOutpatientCountBySign(ReferralOutpatientVo vo) {
		return referralOutpatientDao.getReferralOutpatientCountBySign(vo);
	}

}
