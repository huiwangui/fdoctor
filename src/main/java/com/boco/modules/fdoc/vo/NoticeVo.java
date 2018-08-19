package com.boco.modules.fdoc.vo;

public class NoticeVo {
	String noticebody;//推送内容
	String title;//推送标题
	String extParameters;//扩展属性(json map需要加反斜杠) pushRequest.setAndroidExtParameters("{\"k1\":\"android\",\"k2\":\"v2\"}");
	String healthFileCode;
	String way;//推送方式  ALL 表示全部推送
	String mititle;//小米标题
	String miActivityBody;//小米推送内容
    Long appKey;//医生端或居民端的
    String  target;///推送目标: DEVICE:推送给设备; ACCOUNT:推送给指定帐号,TAG:推送给自定义标签; ALIAS: 按别名推送; ALL: 全推
    String targetValue;//根据Target来设定，如Target=device, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)
    String  deviceType;// 设备类型 ANDROID iOS ALL.
    String pushType;// MESSAGE:表示消息(默认),NOTICE:表示通知
    String androidOpenType; // 点击通知后动作 "APPLICATION" : 打开应用 "ACTIVITY" : 打开AndroidActivity "URL" : 打开URL "NONE" : 无跳转
    String androidNotifyType;//通知的提醒方式 "VIBRATE" : 震动 "SOUND" : 声音 "BOTH" : 声音和震动 NONE : 静音
    String docId;
	String androidActivity;//ACTIVITY 路径
	String androidXiaoMiActivity;//小米ACTIVITY 路径

	public String getAndroidXiaoMiActivity() {
		return androidXiaoMiActivity;
	}

	public void setAndroidXiaoMiActivity(String androidXiaoMiActivity) {
		this.androidXiaoMiActivity = androidXiaoMiActivity;
	}

	public String getAndroidActivity() {
		return androidActivity;
	}

	public void setAndroidActivity(String androidActivity) {
		this.androidActivity = androidActivity;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getAndroidOpenType() {
		return androidOpenType;
	}

	public void setAndroidOpenType(String androidOpenType) {
		this.androidOpenType = androidOpenType;
	}

	public String getAndroidNotifyType() {
		return androidNotifyType;
	}

	public void setAndroidNotifyType(String androidNotifyType) {
		this.androidNotifyType = androidNotifyType;
	}

	public Long getAppKey() {
		return appKey;
	}

	public void setAppKey(Long appKey) {
		this.appKey = appKey;
	}

	public String getMititle() {
		return mititle;
	}

	public void setMititle(String mititle) {
		this.mititle = mititle;
	}

	public String getMiActivityBody() {
		return miActivityBody;
	}

	public void setMiActivityBody(String miActivityBody) {
		this.miActivityBody = miActivityBody;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getHealthFileCode() {
		return healthFileCode;
	}

	public void setHealthFileCode(String healthFileCode) {
		this.healthFileCode = healthFileCode;
	}

	public String getExtParameters() {
		return extParameters;
	}

	public void setExtParameters(String extParameters) {
		this.extParameters = extParameters;
	}

	public String getNoticebody() {
		return noticebody;
	}

	public void setNoticebody(String noticebody) {
		this.noticebody = noticebody;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
