/**
 * @ClassName: SurrenderRequestServiceImpl 
 * Description:
 * @author h
 * @date  2017年11月29日下午3:20:58
 * @version 1.0
 *  
 */
package com.boco.modules.fdoc.service.impl.surrender;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boco.common.utils.DateUtils;
import com.boco.modules.fdoc.dao.DoctorDao;
import com.boco.modules.fdoc.dao.SignDao;
import com.boco.modules.fdoc.dao.expert.ExpertDao;
import com.boco.modules.fdoc.dao.surrender.SurrenderInformationDao;
import com.boco.modules.fdoc.dao.surrender.SurrenderRequestDao;
import com.boco.modules.fdoc.model.UserDocSignEntity;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.service.surrender.SurrenderRequestService;
import com.boco.modules.fdoc.vo.expert.ExpertInformationVo;
import com.boco.modules.fdoc.vo.surrender.SurrenderRequestVo;

/**
 * Tilte: SurrenderRequestServiceImpl 
 * Description:
 * @author h
 * @date  2017年11月29日下午3:20:58
 * @version 1.0
 *  
 */
@Service
public class SurrenderRequestServiceImpl implements SurrenderRequestService {
    @Resource
    SurrenderRequestDao surrenderRequestDao;
    @Resource
    SignDao signDao;
	@Resource
	ExpertDao expertDao;
	@Resource
	DoctorDao doctorDao;
	@Resource
	SurrenderInformationDao surrenderInformationDao;
	@Override
	public int insert(SurrenderRequestEntity entity) {	 
		return surrenderRequestDao.insert(entity);
	} 
	@Override
	public List<SurrenderRequestVo> getSurrenderRequestVoListByPersonIdAndStatus(SurrenderRequestVo vo) {
		//获取状态 status:1:解约审核中    2:已签约   3:未签约    
		String status = vo.getStatus();
		List<SurrenderRequestVo> voList = new ArrayList<>();
		//当处于解约申请中时，查询解约申请、以往的已解约、解约申请被拒绝的记录
		if("1".equals(status)){
		  voList = surrenderRequestDao.getSurrenderRequestVoListByPersonId(vo);
		  
		}else if("2".equals(status)){
			//查询以往的已解约、解约申请被拒绝的记录
			voList = surrenderRequestDao.getNoSignSurrenderRequestVoListByPersonId(vo);
			//查询签约时间
			UserDocSignEntity entity = signDao.getSignRecordByPersonId(vo.getPersonId());
			SurrenderRequestVo requestVo = new SurrenderRequestVo();
			requestVo.setPersonId(entity.getPersonId());
			requestVo.setDocTeamId(entity.getDocTeamId());
			 
			String dateString = DateUtils.formatDate(entity.getSignTime(), "yyyy-MM-dd");
			try {
				//把查询出的签约时间赋值
				requestVo.setSignTime(DateUtils.parseDate(dateString, "yyyy-MM-dd"));
			} catch (ParseException e) {				 
				e.printStackTrace();
			}
			voList.add(requestVo);			 
		}else{
			//查询以往的已解约、解约申请被拒绝的记录
			voList = surrenderRequestDao.getNoSignSurrenderRequestVoListByPersonId(vo);
			 
			
		}
		//处理医生团队及专家
		if(voList!=null&&voList.size()>0){
			  for (SurrenderRequestVo surrenderRequestVo : voList) {
				String s2 = surrenderRequestVo.getStatus();
				//解约申请
				if("1".equals(s2)){
					//获取签约医生团队
					surrenderRequestVo.setDocList(signDao.getSignDoctorTeamInfo(surrenderRequestVo.getPersonId()));
					//获取专家
					ExpertInformationVo evo = new ExpertInformationVo();
					evo.setPersonId(surrenderRequestVo.getPersonId());
					//获取专家
					ExpertInformationVo reVo = expertDao.getExpertInfoOfPersonId(evo);
					if(reVo!=null){
						surrenderRequestVo.setExpertImg(reVo.getImg());
						surrenderRequestVo.setExpertIntroduction(reVo.getIntroduction());
						surrenderRequestVo.setExpertName(reVo.getExpertName());
						surrenderRequestVo.setExpertType("4");
					}
					/*//获取签约医生团队
					surrenderRequestVo.setDocList(doctorDao.getDoctorTeamMemberByTeamId(surrenderRequestVo.getDocTeamId()));
					//获取专家
					ExpertInformationVo reVo = expertDao.getExpertInfoBySurrenderId(surrenderRequestVo.getId());
					if(reVo!=null){
						surrenderRequestVo.setExpertImg(reVo.getImg());
						surrenderRequestVo.setExpertIntroduction(reVo.getIntroduction());
						surrenderRequestVo.setExpertName(reVo.getExpertName());
						surrenderRequestVo.setExpertType("4");
					}*/
				}else{
					surrenderRequestVo.setDocList(doctorDao.getDoctorTeamMemberByTeamId(surrenderRequestVo.getDocTeamId()));
					//获取专家
					ExpertInformationVo reVo = expertDao.getExpertInfoBySurrenderId(surrenderRequestVo.getId());
					if(reVo!=null){
						surrenderRequestVo.setExpertImg(reVo.getImg());
						surrenderRequestVo.setExpertIntroduction(reVo.getIntroduction());
						surrenderRequestVo.setExpertName(reVo.getExpertName());
						surrenderRequestVo.setExpertType("4");
					}
					/*//获取签约医生团队
					surrenderRequestVo.setDocList(signDao.getSignDoctorTeamInfo(surrenderRequestVo.getPersonId()));
					//获取专家
					ExpertInformationVo evo = new ExpertInformationVo();
					evo.setPersonId(surrenderRequestVo.getPersonId());
					//获取专家
					ExpertInformationVo reVo = expertDao.getExpertInfoOfPersonId(evo);
					if(reVo!=null){
						surrenderRequestVo.setExpertImg(reVo.getImg());
						surrenderRequestVo.setExpertIntroduction(reVo.getIntroduction());
						surrenderRequestVo.setExpertName(reVo.getExpertName());
						surrenderRequestVo.setExpertType("4");
					}*/
				}
			}
		  }
		return voList;
	}
 
	@Override
	public int getSurrenderRequestCountByDocTeamId(String docTeamId) {
		 
		return surrenderRequestDao.getSurrenderRequestCountByDocTeamId(docTeamId);
	}
	 
	@Override
	public SurrenderRequestVo getSurrenderDetailByPersonId(SurrenderRequestEntity vo) {
		String status = vo.getStatus();
		SurrenderRequestVo svo =null;
		//已解约的情况单独处理
		if("3".equals(status)){
			svo = surrenderRequestDao.getSurrenderDetailAndServicePackValueByPersonId(vo);
			if(svo!=null){
				svo.setServicePackOfYear(surrenderInformationDao.getYearOfSignByPersonId(vo));
			}
		}else{
			svo = surrenderRequestDao.getSignSurrenderDetailAndServicePackValueByPersonId(vo);
			if(svo!=null){
				svo.setServicePackOfYear(signDao.getYearOfSignByPersonId(vo.getPersonId()));
			}
			 
		}
		
		//处理解约时间及解约申请时间
		if(svo!=null&&svo.getRequestTime()!=null){
			svo.setRequestTime(DateUtils.parseDate(DateUtils.formatDate(svo.getRequestTime(), "yyyy-MM-dd")));
		}
		if(svo!=null&&svo.getAuditTime()!=null){
			svo.setAuditTime(DateUtils.parseDate(DateUtils.formatDate(svo.getAuditTime(), "yyyy-MM-dd")));
		}
		return svo;
	}
 
	@Override
	public int cancelSurrenderDetailByPersonId(SurrenderRequestEntity vo) {
		//先查询是否有解约申请
		SurrenderRequestEntity entity = surrenderRequestDao.getSurrenderRequestByPersonId(vo.getPersonId());
		int flag =0;
		if(entity!=null){
			flag = surrenderRequestDao.cancelSurrenderDetailByPersonId(vo);
		}
		return flag;
	}
	 
	@Override
	public int getSurrenderRequestVoListCountByPersonIdAndStatus(SurrenderRequestVo vo) {
		//获取状态 status:1:解约审核中  3:未签约  2:已签约
		String status = vo.getStatus();
		int count=0;
		if("1".equals(status)){
			  count = surrenderRequestDao.getSurrenderRequestVoListCountByPersonId(vo);
		}else if("2".equals(status)){
			//查询以往的已解约、解约申请被拒绝的记录
			count = surrenderRequestDao.getNoSignSurrenderRequestVoListCountByPersonId(vo);
			//加一条签约记录
			count = count +1;	
		}else{
			
			//查询以往的已解约、解约申请被拒绝的记录
			count = surrenderRequestDao.getNoSignSurrenderRequestVoListCountByPersonId(vo);
		}
		return count;
	}

}
