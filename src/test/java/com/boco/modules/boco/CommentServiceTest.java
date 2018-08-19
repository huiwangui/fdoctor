package com.boco.modules.boco;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.boco.common.JunitTestCase;
import com.boco.common.utils.JsonUtils;
import com.boco.modules.fdoc.model.DoctorCommentEntity;
import com.boco.modules.fdoc.model.HospitalCommentEntity;
import com.boco.modules.fdoc.service.DoctorCommentService;
import com.boco.modules.fdoc.service.HospitalCommentService;

public class CommentServiceTest extends JunitTestCase{
	@Resource
	DoctorCommentService doctorCommentService;
	@Resource
	HospitalCommentService hospitalCommentService;
	
	@Test
	public void testInsert(){
//		DoctorCommentEntity doctorCommentEntity = new DoctorCommentEntity();
//		doctorCommentEntity.setContent("哈哈");
//		doctorCommentEntity.setDocId(1);
//		doctorCommentEntity.setObjId(1);
//		doctorCommentEntity.setStars("5");
//		doctorCommentEntity.setType(1);
//		doctorCommentEntity.setUid("1437de0c-5361-444e-b20f-ed8e67458b4e");
//		
//		doctorCommentService.add(doctorCommentEntity);
		HospitalCommentEntity doctorCommentEntity = new HospitalCommentEntity();
		doctorCommentEntity.setContent("哈哈");
		doctorCommentEntity.setDocId(1);
		doctorCommentEntity.setObjId(1);
		doctorCommentEntity.setStars("5");
		doctorCommentEntity.setType(1);
		doctorCommentEntity.setUid("1437de0c-5361-444e-b20f-ed8e67458b4e");
		
		hospitalCommentService.add(doctorCommentEntity);
	}
	
	@Test
	public void testGrade(){
//		DoctorCommentEntity doctorCommentEntity = new DoctorCommentEntity();
//		doctorCommentEntity.setId(1);
//		doctorCommentEntity.setStars("5");
//		doctorCommentService.grade(doctorCommentEntity);
		
		HospitalCommentEntity hospitalCommentEntity = new HospitalCommentEntity();
		hospitalCommentEntity.setId(3);
		hospitalCommentEntity.setStars("2");
		hospitalCommentService.grade(hospitalCommentEntity);
	}
	
	@Test
	public void testGetByDocId(){
//		List<DoctorCommentEntity> doclist = doctorCommentService.getByDocId("1");
//		System.out.println("--------------------------------");
//		for (DoctorCommentEntity doctorCommentEntity : doclist) {
//			System.out.println(doctorCommentEntity);
//		}
//		System.out.println("--------------------------------");
		
		List<HospitalCommentEntity> hosplist = hospitalCommentService.getByDocId("2");
		System.out.println("----------------------------------");
		for (HospitalCommentEntity hospitalCommentEntity : hosplist) {
			System.out.println(hospitalCommentEntity);
		}
		System.out.println("----------------------------------");
	}
	
	@Test
	public void testReply(){
		HospitalCommentEntity doctorCommentEntity = new HospitalCommentEntity();
		doctorCommentEntity.setContent("eeee");
		doctorCommentEntity.setDocId(2);
		doctorCommentEntity.setObjId(2);
		doctorCommentEntity.setType(2);
		doctorCommentEntity.setUid("1437de0c-5361-444e-b20f-ed8e67458b4e");
		doctorCommentEntity.setrUid("1437de0c-5361-444e-b20f-edeag15ag");
		
		hospitalCommentService.reply(doctorCommentEntity);
	}
	
	@Test
	public void testToEntity(){
		String str = "{'uid':'1437de0c-5361-444e-b20f-ed8e67458b4e','objId':'2','docId':'2','content':'评论测试..','type':'1'} ";
		HospitalCommentEntity hospitalCommentEntity = JsonUtils.getObject(str,HospitalCommentEntity.class);
		System.out.println("----------------------------");
		System.out.println(hospitalCommentEntity);
		System.out.println("----------------------------");
		
		
	}
}
