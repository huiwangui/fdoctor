/*
package com.boco.modules.boco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.ws.Response;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;
import org.junit.Test;

import com.boco.common.JunitTestCase;
import com.boco.common.constants.BusinessConstants;
import com.boco.common.push.CloudMobilePush;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.RestfulUtils;
import com.boco.modules.fdoc.dao.ResidentDao;
import com.boco.modules.fdoc.model.HospitalCommentEntity;
import com.boco.modules.fdoc.model.PersonInformation;
import com.boco.modules.fdoc.service.PersonInformationService;
import com.boco.modules.fdoc.vo.NoticeVo;
import com.boco.modules.fdoc.vo.PersonInformationVo;
import com.boco.modules.fdoc.vo.PersonTestVo;
import com.boco.modules.fdoc.vo.ReturnPersonIllnessVo;
import com.boco.sp.domain.cmms.SysOperation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimCustmsgPushRequest;
import com.taobao.api.request.OpenimCustmsgPushRequest.CustMsg;
import com.taobao.api.request.OpenimImmsgPushRequest;
import com.taobao.api.request.OpenimImmsgPushRequest.ImMsg;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimCustmsgPushResponse;
import com.taobao.api.response.OpenimImmsgPushResponse;
import com.taobao.api.response.OpenimUsersGetResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;

public class JsonTest extends JunitTestCase {
	@Resource
	PersonInformationService service;
	@Resource
	ResidentDao residentDao;

	@Test
	public void longTest() {
		System.out.println("000884022B894F5FB16C96E33693170B".length());
	}
	
	@Test
	public void jsonTest() {
		
		PersonTestVo vo = new PersonTestVo();
		vo.setHealthFileCode("000884022B894F5FB16C96E33693170B");
		vo.setAge("43岁");
		vo.setAgeData(43.4);
		vo.setRegionRegistryDetail("四川省 > 攀枝花市 > 仁和区 > 布德镇 > 巴关河村 > 09组");
		vo.setFamilyId("84794469A14149528E2E122AA587A459");
		vo.setPersonCode("51041110620409006201");
		vo.setCustomNumber("510411106204001229");
		vo.setPersonName("周义军");
		vo.setIdCard("510411197308145015");
		vo.setSexChs("男");
		vo.setPhoneNumber("13548204271");
		vo.setRegionCode("51041110620409");
		vo.setRegionDetail("仁和区布德镇巴关河村09组");
		vo.setIfSign(0);
		vo.setState(1);
		vo.setCommunityHospitalCodeChs("布德镇新桥村卫生站");
		residentDao.insertNewPerson(vo);
	}
	
	@Test
	public void insertAll() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 0;
		for (int i = 50; i < 347; i++) {
			map.put("ProductCode", "8CBE1F526BE144419083D25720106D0E");
			map.put("PageSize", 50);
			map.put("RegionCode", "510411106");
			map.put("PageIndex", i);
			
			Client client = ClientBuilder.newClient();
			WebTarget target = client
					.target("http://61.157.75.21:11004/webservice.asmx/HIS_Interface");
			Form form = new Form();
			form.param("TradeCode", "55-12");
			form.param("InputParameter", JsonUtils.getJsonFormat(map));
			javax.ws.rs.core.Response response = target.request().post(
					Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
			String respStr = response.readEntity(String.class);
			
			Document document = DocumentHelper.parseText(respStr);
			Element root = document.getRootElement();
			String data = root.getText();
			//System.out.println(i + "------------------------" + data);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			HashMap jsonMap = mapper.readValue(data, HashMap.class);
			if (jsonMap.get("Msg") != null && jsonMap.get("Msg").getClass() == ArrayList.class) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) jsonMap.get("Msg");
				for (int j = 0; j < list.size(); j++) {
					index ++;
					Map<String, Object> itemMap = list.get(j);
					//System.out.println(itemMap);
					PersonTestVo vo = new PersonTestVo();
					vo.setHealthFileCode((String)itemMap.get("ID"));
					vo.setAge((String)itemMap.get("AGE"));
					if (itemMap.get("AGEDATA") != null && !"".equals(itemMap.get("AGEDATA"))) {
						if (itemMap.get("AGEDATA").getClass() == Double.class) {
							vo.setAgeData((Double)itemMap.get("AGEDATA"));
						}else if(itemMap.get("AGEDATA").getClass() == Integer.class){
							vo.setAgeData(Double.valueOf((int)itemMap.get("AGEDATA")));
						}
					}
					vo.setRegionRegistryDetail((String)itemMap.get("REGION_NAME"));
					vo.setFamilyId((String)itemMap.get("FAMILY_ID"));
					vo.setPersonCode((String)itemMap.get("PERSON_CODE"));
					vo.setCustomNumber((String)itemMap.get("CUSTOM_NUMBER"));
					vo.setPersonName((String)itemMap.get("NAME"));
					vo.setIdCard((String)itemMap.get("CARD_ID"));
					vo.setSexChs((String)itemMap.get("GENDER"));
					vo.setPhoneNumber((String)itemMap.get("TELPHONE"));
					vo.setRegionCode((String)itemMap.get("REGIONCODE"));
					vo.setRegionDetail((String)itemMap.get("ADDRESS"));
					vo.setIfSign(0);
					vo.setState(1);
					vo.setCommunityHospitalCodeChs((String)itemMap.get("BUILDORG"));
					residentDao.insertNewPerson(vo);
					System.out.println(i + "--------------------" + index);
					
				}
			}
		}
	}
	

	@Test
	public void postTest() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		map.put("PageSize", 50);
		map.put("RegionCode", "510411106");
		map.put("PageIndex", 1);
		
		System.out.println(RestfulUtils.getPublicData("55-12", map));
	}
	
	@Test
	public void loginTest() throws DocumentException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ProductCode", PropertiesUtils.getValue("produce_code"));
		map.put("UserName", "bdzwsyjw");
		map.put("Password", "123");
		
		
		System.out.println(RestfulUtils.getPublicData("01", map));
		
	}
	

	@Test
	public void postlocalTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ProductCode", "8CBE1F526BE144419083D25720106D0E");
		map.put("PageSize", 2);
		map.put("PageIndex", 0);
		Client client = ClientBuilder.newClient();
		WebTarget target = client
				.target("http://61.157.75.21:11004/webservice.asmx/HIS_Interface?TradeCode=55-11&InputParameter=InputParameter={ProductCode:8CBE1F526BE144419083D25720106D0E,PageSize: 2,PageIndex: 0 }");
		String response = target.request(MediaType.TEXT_XML).get(String.class);
		System.out.println(response);
	}

	@Test
	public void getTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client
				.target("http://192.168.15.97:8881/CMMS/dictionary/dictionaryJson.do?dictTypeId=4");
		String response = target.request(MediaType.APPLICATION_JSON_TYPE).get(
				String.class);
		System.out.println(response);
	}

	@Test
	public void propertyTest() {
		String appstore_mobile = PropertiesUtils
				.getValue(BusinessConstants.RESTFUL_DOCTOR_LOGIN_URL);
		System.out.println(appstore_mobile);
	}

	@Test
	public void txtTest() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			URL url = classLoader.getResource("agreement.txt");
			String encoding = "utf-8";
			File file = new File(url.getFile());
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
				}
				read.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSendMsg(){
		try {
			TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23562534", "06387807e5086f1c07c68cea1b234517");
			OpenimCustmsgPushRequest req = new OpenimCustmsgPushRequest();
			CustMsg obj1 = new CustMsg();
			obj1.setFromUser("user_sender");
			obj1.setToAppkey("23562534");
			List<String> list = new ArrayList<String>();
			list.add("fdoctor");
			obj1.setToUsers(list);
			obj1.setSummary("客户端最近消息里面显示的消息摘要");
			obj1.setData("push payload");
//			obj1.setAps("{\"alert\":\"ios apns push\"}");
//			obj1.setApnsParam("apns推送的附带数据");
			obj1.setInvisible(0L);
			obj1.setFromNick("sender_nick");
			obj1.setFromTaobao(0L);
			req.setCustmsg(obj1);
			OpenimCustmsgPushResponse rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Test
	public void deleteUser() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23567044", "6cb9d09a4942c8461ecec42c6f12529c");
		OpenimUsersGetRequest req = new OpenimUsersGetRequest();
		req.setUserids("fdoctor2");
		OpenimUsersGetResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
		
//		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23567044", "6cb9d09a4942c8461ecec42c6f12529c");
//		OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
//		List<Userinfos> list2 = new ArrayList<Userinfos>();
//		Userinfos obj3 = new Userinfos();
//		list2.add(obj3);
//		obj3.setUserid("fdoctor2");
//		obj3.setPassword("232d361092744cbd42387f57e54a00d8");
//		req.setUserinfos(list2);
//		OpenimUsersUpdateResponse rsp = client.execute(req);
//		System.out.println(rsp.getBody());
	}
	
	@Test
	public void pushTest() {
		CloudMobilePush push = new CloudMobilePush();
		NoticeVo obj = new NoticeVo();
		obj.setNoticebody("签约居民"+"AAA"+"体征信息出现了异常，请及时处理");
		obj.setTitle("健康管理");
		obj.setMititle("健康管理");
		obj.setMiActivityBody("签约居民:"+"AAA"+"体征信息出现了异常，请及时处理");
		obj.setAppKey(Long.parseLong(PropertiesUtils.getValue("yun-douc_appKey")));
		obj.setTarget("ACCOUNT");
		obj.setTargetValue("50");
		obj.setDeviceType("ANDROID");
		obj.setPushType("NOTICE");
		obj.setAndroidNotifyType("BOTH");
		obj.setAndroidOpenType("ACTIVITY");
		obj.setAndroidActivity("com.boco.huikang.doctor.view.push.PushActivity");
		push.PushAdvance(obj);
	}
	
	
}
*/
