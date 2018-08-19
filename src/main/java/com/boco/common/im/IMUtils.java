package com.boco.common.im;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.boco.common.constants.BusinessConstants;
import com.boco.common.enums.ApiStatusEnum;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.common.utils.StringUtils;
import com.boco.modules.fdoc.model.DoctorUserEntity;
import com.boco.modules.fdoc.model.UserEntity;
import com.boco.modules.fdoc.model.expert.ExpertAccountEntity;
import com.boco.modules.fdoc.vo.UserVo;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersGetResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;

public class IMUtils {
	private static String url = PropertiesUtils.getValue("im_url");
	private static String doc_appKey = PropertiesUtils.getValue("im_appkey");
	private static String doc_appSecret = PropertiesUtils.getValue("im_appSecret");
	private static String user_appKey = PropertiesUtils.getValue("im_user_appkey");
	private static String user_appSecret = PropertiesUtils.getValue("im_user_appSecret");

	@SuppressWarnings("unchecked")
	public static Integer expertRegister(Map<String, String> userMap) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			String userName = userMap.get("userName");

			TaobaoClient client = new DefaultTaobaoClient(url, doc_appKey,
					doc_appSecret);
			OpenimUsersGetRequest req = new OpenimUsersGetRequest();
			req.setUserids(userName);
			OpenimUsersGetResponse rsp = client.execute(req);
			Map<String, Object> imMap = (Map<String, Object>) mapper.readValue(
					rsp.getBody(), HashMap.class).get(
					"openim_users_get_response");
			if (imMap == null) {
				return ApiStatusEnum.FAIL.getKey();
			}
			Map<String, Object> userinfosMap = (Map<String, Object>) imMap
					.get("userinfos");
			List<Map<String, Object>> userinfoList = (List<Map<String, Object>>) userinfosMap
					.get("userinfos");
			if (userinfoList == null || userinfoList.size() == 0) {
				OpenimUsersAddRequest addreq = new OpenimUsersAddRequest();
				List<Userinfos> list = new ArrayList<Userinfos>();
				Userinfos obj3 = new Userinfos();
				obj3.setUserid(userName);
				//obj3.setNick(userMap.get("docName"));
				obj3.setPassword(userMap.get("password"));
				if (userMap.get("img") != null
						&& !"".equals(userMap.get("img"))) {
					obj3.setIconUrl(userMap.get("img"));
				}
				list.add(obj3);
				addreq.setUserinfos(list);
				OpenimUsersAddResponse addrsp = client.execute(addreq);
			}
			return ApiStatusEnum.SUCCESS.getKey();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiStatusEnum.FAIL.getKey();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Integer doctorRegister(Map<String, String> userMap) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			String userName = userMap.get("userName");

			TaobaoClient client = new DefaultTaobaoClient(url, doc_appKey,
					doc_appSecret);
			OpenimUsersGetRequest req = new OpenimUsersGetRequest();
			req.setUserids(userName);
			OpenimUsersGetResponse rsp = client.execute(req);
			Map<String, Object> imMap = (Map<String, Object>) mapper.readValue(
					rsp.getBody(), HashMap.class).get(
					"openim_users_get_response");
			if (imMap == null) {
				return ApiStatusEnum.FAIL.getKey();
			}
			Map<String, Object> userinfosMap = (Map<String, Object>) imMap
					.get("userinfos");
			List<Map<String, Object>> userinfoList = (List<Map<String, Object>>) userinfosMap
					.get("userinfos");
			if (userinfoList == null || userinfoList.size() == 0) {
				OpenimUsersAddRequest addreq = new OpenimUsersAddRequest();
				List<Userinfos> list = new ArrayList<Userinfos>();
				Userinfos obj3 = new Userinfos();
				obj3.setUserid(userName);
				obj3.setNick(userMap.get("docName"));
				obj3.setPassword(userMap.get("password"));
				if (userMap.get("photo") != null
						&& !"".equals(userMap.get("photo"))) {
					obj3.setIconUrl(userMap.get("photo"));
				}
				list.add(obj3);
				addreq.setUserinfos(list);
				OpenimUsersAddResponse addrsp = client.execute(addreq);
			}
			return ApiStatusEnum.SUCCESS.getKey();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiStatusEnum.FAIL.getKey();
		}
	}

	@SuppressWarnings("unchecked")
	public static Integer userRegister(Map<String, String> userMap) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			String userName = userMap.get("userName");

			TaobaoClient client = new DefaultTaobaoClient(url, user_appKey,
					user_appSecret);
			OpenimUsersGetRequest req = new OpenimUsersGetRequest();
			req.setUserids(userName);
			OpenimUsersGetResponse rsp = client.execute(req);
			Map<String, Object> imMap = (Map<String, Object>) mapper.readValue(
					rsp.getBody(), HashMap.class).get(
					"openim_users_get_response");
			if (imMap == null) {
				return ApiStatusEnum.FAIL.getKey();
			}
			Map<String, Object> userinfosMap = (Map<String, Object>) imMap
					.get("userinfos");
			List<Map<String, Object>> userinfoList = (List<Map<String, Object>>) userinfosMap
					.get("userinfos");
			if (userinfoList == null || userinfoList.size() == 0) {
				OpenimUsersAddRequest addreq = new OpenimUsersAddRequest();
				List<Userinfos> list = new ArrayList<Userinfos>();
				Userinfos obj3 = new Userinfos();
				obj3.setUserid(userName);
				obj3.setNick("u_" + userName);
				obj3.setPassword(userMap.get("password"));
				if (userMap.get("photo") != null
						&& !"".equals(userMap.get("photo"))) {
					obj3.setIconUrl(userMap.get("photo"));
				}
				list.add(obj3);
				addreq.setUserinfos(list);
				OpenimUsersAddResponse addrsp = client.execute(addreq);
			}
			return ApiStatusEnum.SUCCESS.getKey();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiStatusEnum.FAIL.getKey();
		}
	}
	
	public static Integer changeUser(UserVo user){
		try {
			TaobaoClient client = new DefaultTaobaoClient(url, user_appKey,
					user_appSecret);
			OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
			List<Userinfos> list2 = new ArrayList<Userinfos>();
			Userinfos obj3 = new Userinfos();
			list2.add(obj3);
			if (StringUtils.isNotEmpty(user.getPersonName())) {
				obj3.setNick(user.getPersonName());
			}
			if (StringUtils.isNotEmpty(user.getImg())) {
				obj3.setIconUrl(PropertiesUtils.getValue("im_head_img_url_start") + user.getImg());
			}
			if (StringUtils.isNotEmpty(user.getPassword())) {
				obj3.setPassword(user.getPassword());
			}
			
			obj3.setUserid(user.getUid());
			req.setUserinfos(list2);
			OpenimUsersUpdateResponse rsp = client.execute(req);
			return ApiStatusEnum.SUCCESS.getKey();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiStatusEnum.FAIL.getKey();
		}
		
	}
	
	public static Integer changeDocUser(DoctorUserEntity entity){
		try {
			TaobaoClient client = new DefaultTaobaoClient(url, doc_appKey,
					doc_appSecret);
			OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
			List<Userinfos> list2 = new ArrayList<Userinfos>();
			Userinfos obj3 = new Userinfos();
			list2.add(obj3);
			if (StringUtils.isNotEmpty(entity.getImg())) {
				obj3.setIconUrl(PropertiesUtils.getValue("im_head_img_url_start") + entity.getImg());
			}
			if (StringUtils.isNotEmpty(entity.getPassword())) {
				obj3.setPassword(entity.getPassword());
			}
			
			obj3.setUserid(entity.getUserName());
			req.setUserinfos(list2);
			OpenimUsersUpdateResponse rsp = client.execute(req);
			return ApiStatusEnum.SUCCESS.getKey();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiStatusEnum.FAIL.getKey();
		}
		
	}
	public static Integer changeExpertUser(ExpertAccountEntity entity){
		try {
			TaobaoClient client = new DefaultTaobaoClient(url, doc_appKey,
					doc_appSecret);
			OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
			List<Userinfos> list2 = new ArrayList<Userinfos>();
			Userinfos obj3 = new Userinfos();
			list2.add(obj3);
			/*if (StringUtils.isNotEmpty(entity.getImg())) {
				obj3.setIconUrl(PropertiesUtils.getValue("im_head_img_url_start") + entity.getImg());
			}*/
			if (StringUtils.isNotEmpty(entity.getPassword())) {
				obj3.setPassword(entity.getPassword());
			}
			
			obj3.setUserid(entity.getUserName());
			req.setUserinfos(list2);
			OpenimUsersUpdateResponse rsp = client.execute(req);
			return ApiStatusEnum.SUCCESS.getKey();
		} catch (Exception e) {
			e.printStackTrace();
			return ApiStatusEnum.FAIL.getKey();
		}
		
	}
}
