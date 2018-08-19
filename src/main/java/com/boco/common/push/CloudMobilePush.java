package com.boco.common.push;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.boco.common.constants.BusinessConstants;
import com.boco.common.utils.JsonUtils;
import com.boco.common.utils.ListUtils;
import com.boco.common.utils.PropertiesUtils;
import com.boco.modules.fdoc.vo.NoticeVo;

public class CloudMobilePush {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private String accessKeyId = PropertiesUtils.getValue("yun_accessKeyId");
	private String accessKeySecret = PropertiesUtils
			.getValue("yun_accessKeySecret");
	private Long user_appKey = Long
			.parseLong(PropertiesUtils.getValue("yun_appKey"));
	private Long doc_appKey = Long
			.parseLong(PropertiesUtils.getValue("yun-douc_appKey"));
	private String target = "ACCOUNT";
	private String androidOpenType = "ACTIVITY";
	private String androidNotifyType = "BOTH";

	IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
			accessKeyId, accessKeySecret);
	DefaultAcsClient client = new DefaultAcsClient(profile);

	/**
	 * 阿里云推送
	 * 
	 * @author Jomo
	 * @param 推送目标对象
	 */

	public void PushAdvance(NoticeVo obj) {

		PushRequest pushRequest = new PushRequest();
		// 安全性比较高的内容建议使用HTTPS
		pushRequest.setProtocol(ProtocolType.HTTPS);
		// 内容较大的请求，使用POST请求
		pushRequest.setMethod(MethodType.POST);
		pushRequest.setAppKey(obj.getAppKey());
		pushRequest.setTarget(obj.getTarget());
		pushRequest.setTargetValue(obj.getTargetValue());// 根据Target来设定，如Target=device,
															// 则对应的值为
															// 设备id1,设备id2.
															// 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
		pushRequest.setDeviceType(obj.getDeviceType());// // 设备类型 ANDROID iOS
														// ALL.

		// 推送配置
		pushRequest.setPushType(obj.getPushType()); // MESSAGE:表示消息(默认),
													// NOTICE:表示通知
		pushRequest.setTitle(obj.getTitle()); // 消息的标题
		pushRequest.setBody(obj.getNoticebody()); // 消息的内容

		pushRequest.setAndroidOpenType(obj.getAndroidOpenType()); // 点击通知后动作
		pushRequest.setAndroidActivity(obj.getAndroidActivity()); //
		pushRequest.setAndroidNotifyType(obj.getAndroidNotifyType()); //
		pushRequest.setAndroidMusic("default"); // Android通知声音
		pushRequest.setAndroidExtParameters(obj.getExtParameters());// pushRequest.setAndroidExtParameters("{\"k1\":\"android\",\"k2\":\"v2\"}");

		pushRequest.setAndroidNotificationBarType(50); // Android自定义通知栏样式，取值：1-100
		pushRequest.setAndroidNotificationBarPriority(2); // Android通知在通知栏展示时排列位置的优先级
															// -2 -1 0 1 2
		pushRequest.setAndroidExtParameters(obj.getExtParameters()); // 设定通知的扩展属性。(注意
																		// :
																		// 该参数要以
																		// json
																		// map
																		// 的格式传入,否则会解析出错)
		pushRequest.setAndroidXiaoMiActivity(obj.getAndroidXiaoMiActivity());// 设置该参数后启动小米托管弹窗功能,
																				// 此处指定通知点击后跳转的Activity（托管弹窗的前提条件：1.
																				// 集成小米辅助通道；2.
																				// StoreOffline参数设为true）
		pushRequest.setAndroidXiaoMiNotifyTitle(obj.getMititle());
		pushRequest.setAndroidXiaoMiNotifyBody(obj.getMiActivityBody());

		/*
		 * // 推送配置: iOS pushRequest.setiOSBadge(5); // iOS应用图标右上角角标
		 * pushRequest.setiOSMusic("default"); // iOS通知声音
		 * pushRequest.setiOSSubtitle("iOS10 subtitle");//iOS10通知副标题的内容
		 * pushRequest
		 * .setiOSNotificationCategory("iOS10 Notification Category");
		 * //指定iOS10通知Category
		 * pushRequest.setiOSMutableContent(true);//是否允许扩展iOS通知内容
		 * pushRequest.setiOSApnsEnv
		 * ("DEV");//iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" :
		 * 表示生产环境 pushRequest.setiOSRemind(true); //
		 * 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通
		 * ），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：离线消息转通知仅适用于生产环境
		 * pushRequest.setiOSRemindBody
		 * ("iOSRemindBody");//iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT &&
		 * iOSRemind为true时有效
		 * pushRequest.setiOSExtParameters("{\"_ENV_\":\"DEV\",\"k2\":\"v2\"}");
		 * //通知的扩展属性(注意 : 该参数要以json map的格式传入,否则会解析出错)
		 */

		pushRequest.setStoreOffline(true); // 离线消息是否保存,若保存,
											// 在推送时候，用户即使不在线，下一次上线则会收到
		PushResponse pushResponse = null;
		try {
			pushResponse = client.getAcsResponse(pushRequest);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		System.out.printf("RequestId: %s, MessageId: %s\n",
				pushResponse.getRequestId(), pushResponse.getMessageId());

	}

	/**
	 * 
	 * androidPush:(安卓端推送)-将IOS也集成进去了. <br/>
	 * 
	 * @author q
	 * @return
	 */
	public String androidPush(List<String> accounts, String title, String body,
			String androidActivity, String item, Map<String, Object> paramMap) {

		List<List<String>> splitList = ListUtils.splitList(accounts, 100);
		//按照100个一组分割为多个list,循环推送
		
		for (List<String> list : splitList) {
			PushRequest pushRequest = new PushRequest();
			// 安全性比较高的内容建议使用HTTPS
			pushRequest.setProtocol(ProtocolType.HTTPS);
			// 内容较大的请求，使用POST请求
			pushRequest.setMethod(MethodType.POST);
			if (BusinessConstants.PUSH_ITEM_USER.equals(item)) {
				pushRequest.setAppKey(this.user_appKey);
			}else {
				pushRequest.setAppKey(this.doc_appKey);
			}
			pushRequest.setTarget(this.target);
			pushRequest.setTargetValue(ListUtils.listToString(list));//根据Target来设定，如Target=device,
			// 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
			//pushRequest.setDeviceType("ANDROID");// // 设备类型 ANDROID iOS ALL.
			pushRequest.setDeviceType("ALL");//修改为全部类型

			// 推送配置
			pushRequest.setPushType("NOTICE"); // MESSAGE:表示消息(默认),
												// NOTICE:表示通知
			pushRequest.setTitle(title); // 消息的标题
			pushRequest.setBody(body); // 消息的内容

			
			//安卓配置
			pushRequest.setAndroidOpenType(this.androidOpenType); // 点击通知后动作
			//pushRequest.setAndroidActivity(androidActivity); //
			pushRequest.setAndroidNotifyType(this.androidNotifyType); //
			pushRequest.setAndroidMusic("default"); // Android通知声音

			pushRequest.setAndroidNotificationBarType(50); // Android自定义通知栏样式，取值：1-100
			pushRequest.setAndroidNotificationBarPriority(2); // Android通知在通知栏展示时排列位置的优先级
			pushRequest
					.setAndroidExtParameters(JsonUtils.getJsonFormat(paramMap)); // 设定通知的扩展属性。(注意:该参数要以jsonmap的格式传入,否则会解析出错)
			pushRequest.setAndroidXiaoMiActivity(androidActivity);// 设置该参数后启动小米托管弹窗功能,
																	// 此处指定通知点击后跳转的Activity（托管弹窗的前提条件：1.
																	// 集成小米辅助通道；2.
																	// StoreOffline参数设为true）
			pushRequest.setAndroidXiaoMiNotifyTitle(title);
			pushRequest.setAndroidXiaoMiNotifyBody(body);
			
			//IOS配置
			pushRequest.setiOSBadge(0); // iOS应用图标右上角角标
			//pushRequest.setiOSSilentNotification(false);//开启静默通知
		    pushRequest.setiOSMusic("default"); // iOS通知声音
		    //pushRequest.setiOSSubtitle(title);//iOS10通知副标题的内容
		    //pushRequest.setiOSNotificationCategory("Category");//指定iOS10通知Category
		    pushRequest.setiOSMutableContent(true);//是否允许扩展iOS通知内容
		    pushRequest.setiOSApnsEnv("DEV");//iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" : 表示生产环境
		    pushRequest.setiOSRemind(true); // 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：离线消息转通知仅适用于生产环境
		    pushRequest.setiOSRemindBody(body);//iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT && iOSRemind为true时有效
		    pushRequest.setiOSExtParameters(JsonUtils.getJsonFormat(paramMap)); //通知的扩展属性(注意 : 该参数要以json map的格式传入,否则会解析出错)
			        
			
			

			pushRequest.setStoreOffline(true); // 离线消息是否保存,若保存,
												// 在推送时候，用户即使不在线，下一次上线则会收到
			PushResponse pushResponse = null;

			try {
				pushResponse = client.getAcsResponse(pushRequest);
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return BusinessConstants.SUCCESS;
	}

}
