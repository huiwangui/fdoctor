package com.boco.common.constants;

public class BusinessConstants {
	/**
	 * 签约标签对应关系
	 * 
	 * */
	public final static Integer SIGN_HYPER =1;//高血压
	public final static Integer SIGN_DIABETES = 3;//糖尿病
	public final static Integer SIGN_CHILD = 6;//儿童
	public final static Integer SIGN_MAJOR =2;//重型精神病
	public final static Integer SIGN_YUNCH =29;//孕产妇
    public final static Integer SIGN_CHENR =5;//普通成人
	public final static Integer SIGN_OLDM =7;//老年人
	/**
	 * 通用
	 */
	public final static String SUCCESS = "success"; //操作成功
	public final static String FAIL = "fail"; //操作失败
	public final static String YES = "1"; //是
	public final static String NO = "0"; //否
	/**
	 * 逻辑删除标识
	 */
	public final static String DEL_FLAG_YES = "1"; //已删除
	public final static String DEL_FLAG_NO = "0"; //未删除
	
	/**
	 * 用户模块
	 */
	public final static String REGISTER_SUCCESS = "register_success";//注册成功
	public final static String AUTHEN_FLAG_YES = "1"; //已认证
	public final static String AUTHEN_FLAG_NO = "0"; //未认证
	public final static String USER_STATUS_NORMAL = "0"; //正常
	public final static String USER_STATUS_DISABLE = "2"; //禁用
	public final static String AUTHEN_RESULT_REPEAT = "repeat"; //禁用
	public final static String USERNAME_IN_SESSION = "username_in_session";
	/**
	 * 短信验证码
	 */
	public final static Integer SMS_CODE_BUSI = 1001;
	public final static String SMS_SEND_ITEM_USER = "user";	//居民端接收
	public final static String SMS_SEND_ITEM_DOC = "doc";	//医生端接收
	
	/**
	 * 推送对象
	 */
	public final static String PUSH_ITEM_USER = "user";
	public final static String PUSH_ITEM_DOC = "doc";
	
	public final static String PUSH_ACTIVITY = "com.boco.familydoc.view.push.HealthCalculatePushActivity";
	
	public final static Integer PUSH_ITEM_DOC_INT = 1;
	public final static Integer PUSH_ITEM_USER_INT = 2;
	
	public final static Integer PUSH_TYPE_SIGN = 1;	//签约推送至居民端
	public final static Integer PUSH_TYPE_SIGN_REQUEST = 2;	//签约请求推送至医生端
	public final static Integer PUSH_TYPE_SIGN_REQUEST_SUCCESS = 3;	//签约请求同意推送至居民端
	public final static Integer PUSH_TYPE_SIGN_REQUEST_FAIL = 4;	//签约请求拒绝推送至居民端
	public final static Integer PUSH_TYPE_ABNORMAL_HYPER = 5;	//异常血压推送至医生端
	public final static Integer PUSH_TYPE_ABNORMAL_BLOODSUGAR = 6;	//异常血糖推送至医生端
	public final static Integer PUSH_TYPE_HEALTH_ASSESSMENT = 7;	//健康评估推送至居民端
	public final static Integer PUSH_TYPE_LABEL_SENG = 8;	//群发模板推送至居民端
	public final static Integer PUSH_TYPE_REPORT_SENG = 11;	//上传报告推送至医生端
	public final static Integer PUSH_TYPE_MESSAGE_SENG= 12;	//预约免疫推送至医生端
	public final static Integer PUSH_TYPE_FOLLOW_SENG= 13;	//预约随访推送至医生端
	public final static Integer PUSH_TYPE_EXMA_SENG= 14;	//预约体检推送至医生端

	/**
	 * 优惠劵营销短信
	 */
	public final static Integer SMS_CODE_BUSI_YES = 1002;

	/**
	 * 常量NUM
	 */
	public final static String ROW_NUM = "rowNum";

	/**
	 * 短信验证 超时时间
	 */
	public final static String SMS_CODE_TIMEOUT = "sms_code_timeout";

	/**
	 * 云片 短信 公司
	 */
	public final static String YP_SMS_COMPANY = "yp_sms_company";

	/**
	 * 短信模板
	 */
	public final static String YP_SMS_TEMPLATE = "yp_sms_template";

	/**
	 * 手机号
	 */
	public final static String APPSTORE_MOBILE = "appstore_mobile";
	public final static String MOBILE_REPEAT = "mobile_repeat";

	/**
	 * 验证码
	 */
	public final static String APPSTORE_CODE = "appstore_code";
	
	/**
	 * 医生登陆url（key值）
	 */
	public final static String RESTFUL_DOCTOR_LOGIN_URL = "doctor_login_url";
	
	/**
	 * 获取医生信息url（key值）
	 */
	public final static String RESTFUL_DOCINFO_URL = "doctor_info_url";
	
	/**
	 * 获取最上级区划代码
	 */
	public final static String TOP_REGION = "top_region";
	
	/**
	 * 评论对象：1为对医院，2为对医生
	 */
	public final static String COMMONT_OBJTYPE_HOSP = "1";
	public final static String COMMONT_OBJTYPE_DOCTOR = "2";
	
	/**
	 * 评价定义：好评、中评、差评对应星数
	 */
	public final static String ALL_EVALUATE = "0,0.5,1,1.5,2,2.5,3,3.5,4,4.5,5";
	public final static String GOOD_EVALUATE = "4.5,5";
	public final static String GENERAL_EVALUATE = "2.5,3,3.5,4";
	public final static String BAD_EVALUATE = "0,0.5,1,1.5,2";
	
	public final static String ALL_EVALUATE_CODE = "0";
	public final static String GOOD_EVALUATE_CODE = "1";
	public final static String GENERAL_EVALUATE_CODE = "2";
	public final static String BAD_EVALUATE_CODE = "3";
	
	/**
	 * 订单状态
	 */
	public final static String PAY_STATUS_HASNOTPAY = "0"; //未付款
	public final static String PAY_STATUS_HASPAYED = "1"; //已付款
	public final static String PAY_STATUS_RETURNING = "2"; //正在退款
	public final static String PAY_STATUS_RETURNED = "3"; //已退款
	public final static String PAY_STATUS_CANCLED = "4"; //已取消
	public final static String PAY_STATUS_NOTCOMMENT = "5"; //未评论
	public final static String PAY_STATUS_HASCOMMENT = "6";//已评论
	
	public final static String SEE_DOC_STATUS_ALL = "0";//全部
	public final static String SEE_DOC_STATUS_HASNOTDOC = "1";//未就诊
	public final static String SEE_DOC_STATUS_HASDOC = "2";//已就诊
	public final static String SEE_DOC_STATUS_CANCLED = "3";//已取消
	
	/**
	 * 签约相关状态
	 */
	public final static String SIGN_STATUS_SIGNED = "1";//签约有效期内
	public final static String SIGN_STATUS_RENEW = "2";//待续约
	public final static String SIGN_STATUS_CANCLED = "3";//已解约
	
	public final static String SIGN_AGREEMENT_ONGOING = "1";//协议进行中
	public final static String SIGN_AGREEMENT_OVERSTAY = "2";//已过期
	public final static String SIGN_AGREEMENT_CANCLE = "3";//已解除
	
	public final static String ERROR_RE_SIGN = "re_sign";
	public final static String SUCCESS_SIGN = "success";
	
	public final static String SIGN_TYPE_DOC = "1";	//签约类型为医生端主动签约
	public final static String SIGN_TYPE_USER = "2";//签约类型为居民端提出申请签约
	
	/**
	 * 签约申请状态
	 */
	public final static String SIGN_REQUEST_READY = "1";	//待确认
	public final static String SIGN_REQUEST_OK = "2";	//已确认
	public final static String SIGN_REQUEST_REFUSED = "3";	//已拒绝
	
	public final static String SIGN_REQUEST_DEAL_WAY_AGREE = "1";	//同意签约
	public final static String SIGN_REQUEST_DEAL_WAY_REFUSE = "2";	//拒绝
	
	/**
	 * 接种相关状态
	 */
	public final static String INO_STATUS_NOTINO = "0"; //未接种
	public final static String INO_STATUS_HASINO = "1"; //已接种
	public final static String INO_STATUS_CANCLED = "-1"; //已取消
	
	/**
	 * 医生放号（接种、体检）相关状态
	 */
	public final static String STATUS_PUNISHED = "1"; //已发布
	public final static String STATUS_TIMEOUT = "2"; //已过期
	
	/**
	 * 体检相关状态
	 */
	public final static String CHECK_STATUS_NOTCHECKED = "0"; //未体检
	public final static String CHECK_STATUS_HASCHECKED = "1"; //已体检
	public final static String CKECK_STATUS_CANCLED = "-1";	//已取消
	public final static String SOURCE_PAYTYPE_SELFPAY = "1";  //自费
	public final static String SOURCE_PAYTYPE_FREE = "2";  //免费
	
	/**
	 * 居民相关状态
	 */
	public final static String RESIDENG_STATUS_SYNADD = "0"; //同步增加
	public final static String RESIDENG_STATUS_SYNDEL = "1"; //同步删除
	public final static String RESIDENG_STATUS_SYNUPDATE = "2"; //同步更改
	public final static String RESIDENG_STATUS_SYNSAME = "3";//同步相同
	public final static String RESIDENG_STATUS_SELFUPDATE = "4";//手动修改
	public final static Integer PERSON_SIGN_STATUS_SIGNED = 1;//居民已签约
	public final static Integer PERSON_SIGN_STATUS_NOT = 0;//居民未签约
	
	public final static String PERSON_NOT_FOUND = "person_not_found";//未找到居民
	
	/**
	 * 血压：00.血压偏低  10.血压正常 11.正常高值  21.轻度偏高  22.中度偏高  23.重度偏高
	 */
	public final static String HYPERTENSION_RESULT_NORMAL = "10";//血压正常
	public final static String HYPERTENSION_RESULT_NORMAL_HIGN = "11";//正常高值
	public final static String HYPERTENSION_RESULT_HIGH_LEVEL1 = "21";	//轻度偏高
	public final static String HYPERTENSION_RESULT_HIGH_LEVEL2 = "22";	//中度偏高
	public final static String HYPERTENSION_RESULT_HIGH_LEVEL3 = "23";	//重度偏高
	public final static String HYPERTENSION_RESULT_LOW = "00";	//血压偏低
	
	public final static String HYPERTENSION_JUDGE_NORMAL = "'10','11'";	//正常血压范围
	public final static String HYPERTENSION_JUDGE_HIGN = "'21','22','23'";	//高血压
	public final static String HYPERTENSION_JUDGE_LOW = "'00'";	//低血压
	
	/**
	 * 血糖相关
	 */
	public final static String BLOOD_SUGER_NORMAL = "10";//血糖正常
	public final static String BLOOD_SUGER_NORMAL_HIGH = "11";//血糖正常偏高
	public final static String BLOOD_SUGER_HIGH_LEVEL1 = "21";	//血糖偏高轻度
	public final static String BLOOD_SUGER_HIGH_LEVEL2 = "22";	//血糖偏高中度
	public final static String BLOOD_SUGER_HIGH_LEVEL3 = "23";	//血糖偏高重度
	public final static String BLOOD_SUGER_LOW = "00";	//血糖偏低
	
	public final static String BLOOD_SUGER_JUDGE_NORMAL = "'10','11'";	//正常血糖范围
	public final static String BLOOD_SUGER_JUDGE_HIGH = "'21','22','23'";	//正常血糖范围
	public final static String BLOOD_SUGER_JUDGE_LOW = "'00'";	//低血糖范围
	
	/**
	 * 体征异常时的医生处理标志：0. 未处理 1.已处理
	 */
	public final static String ABNORMAL_SIGN_DEAL = "1";//已处理
	public final static String ABNORMAL_SIGN_NOT_DEAL = "0";//未处理
	
	/**
	 * 群发模板、标签默认标识
	 */
	public final static String DEFAULT_FLAG_DEFAULT = "1";	//默认
	public final static String DEFAULT_FLAG_NOT = "0";	//不是默认
	
	/**
	 * 健康评估相关状态
	 */
	public final static String HEALTH_ASSESSMENT_STATUS_WAIT = "1";	//待评估
	public final static String HEALTH_ASSESSMENT_STATUS_COMPLETED = "2";	//已评估
	
	public final static String HEALTH_ASSESSMENT_TYPE_HYPER = "1";	//血压异常
	public final static String HEALTH_ASSESSMENT_TYPE_BLOODSUGER = "2";	//血糖异常
	
	/**
	 * json为空、空字符串不序列化
	 */
	public final static String JSON_NOT_EMPTY = "json_not_empty";
	public final static String JSON_ALL = "json_all";
	
	/**
	 * 双向转诊流程状态
	 */
	public final static String REFERRAL_STATUS_APPLY = "1";	//申请
	
}
