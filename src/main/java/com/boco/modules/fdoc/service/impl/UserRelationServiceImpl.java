package com.boco.modules.fdoc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.utils.MatcherUtiles;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.dao.PersonInformationDao;
import com.boco.modules.fdoc.dao.UserRealtionDao;
import com.boco.modules.fdoc.dao.surrender.SurrenderRequestDao;
import com.boco.modules.fdoc.exception.UserRelationException;
import com.boco.modules.fdoc.model.PersonInformationEntity;
import com.boco.modules.fdoc.model.UserRealtion;
import com.boco.modules.fdoc.model.surrender.SurrenderRequestEntity;
import com.boco.modules.fdoc.service.UserRelationService;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.UserRelationVo;



@Service
public class UserRelationServiceImpl implements UserRelationService {
   
	@Resource
	UserRealtionDao userRealtionDao;
	@Resource
	PersonInformationDao personInformationDao;
	@Resource
	SurrenderRequestDao surrenderRequestDao;
	
	@Override
	@Transactional
	public String insert(UserRealtion record , PersonInformationVo vo) {
		//查询是否有相同身份证号的
		PersonInformationEntity personInfo=personInformationDao.getPersonInfoByIdcardOnly(vo.getIdCard());
		if(personInfo!=null){//有  则比较姓名
			if(personInfo.getPersonName().equals(vo.getPersonName())){//姓名相同 添加关系
				if("0".equals(String.valueOf(personInfo.getIfSign()))&&personInfo.getJwPersonId()==null){
					personInformationDao.updatePerson(vo);//更新居民信息
				}
				record.setPersonId(personInfo.getPersonId());
				int update=userRealtionDao.updateByUidPersonId(record);
				int num=0;//添加记录数量
				if(update==0){
					 num=userRealtionDao.insert(record);
				}
				
				if(num>0){
					return "SUCCESS";
				}else if(update>0){
					return "SUCCESS";
				}else{
					return "FAIL";
				}
				
			}else {//姓名不同
				return "NOTSAMENAME";
			}
		}else{//没有则新建居民信息
			String personId=UUID.randomUUID().toString().replaceAll("-", "");
			String familyId=UUID.randomUUID().toString().replaceAll("-", "");
			vo.setPersonId(personId);
			vo.setFamilyId(familyId);
			vo.setIfSign(0);
			personInformationDao.insertPerson(vo);
			record.setPersonId(personId);
			int num=userRealtionDao.insert(record);
			if(num>0){
				return "SUCCESS";
			}else{
			    return   "FAIL";
			}
			
		}
	}

	@Override
	@Transactional
	public int updateRelation(PersonInformationVo vo,UserRelationVo userRelationVo) {
		//未签约和未建档之前是可以修改的
		
		int x=0;
		
		if(vo!=null){
			if(StringUtils.isEmpty(vo.getPersonId())){
				throw  new UserRelationException(ApiStatusEnum.PERSONID_NOT_FOUND.getKey(), ApiStatusEnum.PERSONID_NOT_FOUND.getValue());
			}
			if(StringUtils.isNotEmpty(vo.getIdCard())){
				if(!MatcherUtiles.idCardMach(vo.getIdCard())){
					throw  new UserRelationException(ApiStatusEnum.IDCARD_OUTLAW.getKey(), ApiStatusEnum.IDCARD_OUTLAW.getValue());
				}
			}
			if(StringUtils.isNotEmpty(vo.getPhoneNumber())){
				if(!MatcherUtiles.mobileMach(vo.getPhoneNumber())){
					throw  new UserRelationException(ApiStatusEnum.MOBILE_OUTLAW.getKey(), ApiStatusEnum.MOBILE_OUTLAW.getValue());

				}
			}
			
			PersonInformationEntity  entity=personInformationDao.getPersonInformationByPesronId(vo.getPersonId());
			if(entity!=null){
			    if("0".equals(String.valueOf(entity.getIfSign()))&&entity.getJwPersonId()==null){
			    	x+=personInformationDao.updatePerson2(vo);
				}else{
					throw  new UserRelationException(ApiStatusEnum.HAVE_SIGN.getKey(), ApiStatusEnum.HAVE_SIGN.getValue());

				}
				
			}else{
				throw  new UserRelationException(ApiStatusEnum.REQUEST_NOT_FOUND.getKey(), ApiStatusEnum.REQUEST_NOT_FOUND.getValue());

			}
		}
		
		if(StringUtils.isNotEmpty(String.valueOf(userRelationVo.getId()))&&StringUtils.isNotEmpty(userRelationVo.getRelation())){
			UserRealtion record =new UserRealtion();
			record.setRelation(userRelationVo.getRelation());
			record.setId(userRelationVo.getId());
		    x+=userRealtionDao.updateByPrimaryKeySelective(record);
		}
		
		return x;
		
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		//物理删除联系人
		return userRealtionDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<PersonInformationVo> selectAllperson(String uid) {
		//根据personId查找所有的人信息(与他相关的家人)
		List<UserRealtion>  list=userRealtionDao.selectRelations(uid);
		List<PersonInformationVo> voList=new ArrayList<>();
		for (UserRealtion userRealtion : list) {
			PersonInformationVo  vo=personInformationDao.getPersonDetailByPersonId(userRealtion.getPersonId());
			vo.setId(userRealtion.getId());
			vo.setRelation(userRealtion.getRelation());
			//处理签约状态1:解约审核中      3:未签约  2:已签约
			//先判断是否有解约申请
			SurrenderRequestEntity entity = surrenderRequestDao.getSurrenderRequestByPersonId(userRealtion.getPersonId());
			//entity!=null说明有解约请求
			if(entity!=null){
				vo.setStatus("1");
			}else{
				Integer ifSign = vo.getIfSign();
				if(ifSign==0){
					vo.setStatus("3");
				}else{
					vo.setStatus("2");
				}
			}
			voList.add(vo);
		}
		//根据uid查找本人信息
		PersonInformationVo  vo=personInformationDao.getPersonDetailByUId(uid);
		//先判断是否有解约申请
		SurrenderRequestEntity entity = surrenderRequestDao.getSurrenderRequestByPersonId(vo.getPersonId());
		//entity!=null说明有解约请求
		if(entity!=null){
			vo.setStatus("1");
		}else{
			Integer ifSign = vo.getIfSign();
			if(ifSign==0){
				vo.setStatus("3");
			}else{
				vo.setStatus("2");
			}
		}
		voList.add(vo);
		return voList;
	}

	

	
	
	
	
	

}
