/*
package com.boco.modules.boco;


import javax.annotation.Resource;

import org.junit.Test;

import com.boco.common.JunitTestCase;
import com.boco.modules.fdoc.dao.HospitalDao;
import com.boco.modules.fdoc.model.HospitalEntity;

public class TestHospitalDao extends JunitTestCase{
	@Resource
	HospitalDao dao;
	@Test
	public void testFindList(){
		HospitalEntity hospital = new HospitalEntity();
		hospital.setStatus("0");
		hospital.setCity("成都");
		for(HospitalEntity e : dao.findList(hospital)){
			System.out.println(e.toString());
		}
	}
	@Test
	public void testGet(){
		System.out.println(dao.get(1).getName());
	}
//	@Test
//	public void testGetByEntity(){
//		System.out.println(dao.getByEntity("华西").getCity());
//	}
}
*/
