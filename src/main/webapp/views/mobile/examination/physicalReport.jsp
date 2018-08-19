<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>体检报告</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->
<link rel="stylesheet" href="/fdoctor/statics/layui/css/layui.css"/>
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/layui/layui.js"></script>
</head>
<body>
	<div class="main">
		<div class="tc physical_rhd">
			<h1>
				四川省绵阳市培城区卫生院全民预防保健评估和健康指导
			</h1>
			<p >(一半人群末班)</p>
			<p class="mt10">针对本次您的体检结果，我们对您的身体情况进行了评估，并提出以下建议</p>
		</div>
		<div class="table4 mt10">
				<table border="1" class="">
					<tr>
						<td>姓名</td>
						<td>${remap.personName}${remap.NAME}</td>
						<td>性别</td>
						<td>${remap.sex}${remap.GENDER}</td>
						<td>年龄</td>
						<td>${remap.age}${remap.AGE}</td>
						<td>族别</td>
						<td></td>
					</tr>
					<tr>
						<td>身份证</td>
						<td colspan="3">${remap.idCard}${remap.CARD_ID}</td>
						<td>住址</td>
						<td colspan="3">${remap.currentAddress}</td>
					</tr>
					<tr>
						<td>联系电话</td>
						<td colspan="3">${remap.phoneNumber}${remap.PERSON_TEL}</td>
						<td>中医体质辨识</td>
						<td colspan="3">
							<select name="" class="borderb" style="padding: 3px 20px;">
								<option value="">未辨识</option>
								<option value="">和平质</option>
								<option value="">气虚质</option>
								<option value="">阳虚质</option>
								<option value="">阴虚质</option>
								<option value="">痰湿质</option>
								<option value="">湿热 质</option>
								<option value="">血瘀质</option>
								<option value="">气郁质</option>
								<option value="">特禀质</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td rowspan="2">生活方式</td>
						<td>1.饮食习惯：</td>
						<td><span id="Diet"></span></td>
						<td>2.体育运动情况：</td>
						<td><span id="Di"></span></td>
						<td>3.吸烟情况：</td>
						<td><span id="SmokingStatus"></span></td>
						<td>/</td>
					</tr>
					<tr>
						<td>4.饮酒情况：</td>
						<td><span id="DrinkingFrequency"></span></td>
						<td>5.职业病危险因素接触情况：</td>
						<td colspan="3"><span id=""></span></td>
						<td>/</td>
					</tr>
					<tr>
						<td>健康评估</td>
						<td colspan="7">
						<textarea rows="10" cols="" style="width:100%;height:100%;">本次体检重要指标：血压${reports.body.LeftSbp}/${reports.body.LeftDbp}、体质指数${reports.body.Bmi}Kg/㎡、腰围${reports.body.Hip}cm、空腹血糖是${reports.labora.FastingBloodGlucose}mmol/L，血脂：总胆固醇是${reports.labora.TotalCholesterol}mmol/L，低密度脂蛋白胆固醇是${reports.labora.LdlCholesterol}mmol/L，高密度脂蛋白胆固醇是${reports.labora.HdlCholesterol}mmol/L，甘油三酯是${reports.labora.Triglycerides}mmol/L；
 无明确特殊疾病家族史；无明显不良生活习惯；物理查体无特殊；目前无急性病症状。</textarea></td>
					</tr>
					<tr>
						<td>个性化健康指导</td>
						<td colspan="7" cols="7"><textarea rows="10" cols="" style="width:100%;height:100%;"> 特殊建议：
1、饮食建议：坚持一日三餐，进餐定时定量，不要暴饮暴食；早餐吃好，午餐吃饱，晚餐适量。坚持“一多三少”，就是“进食种类多，量少；盐少；油少”。进食种类多、量少:粗、细粮搭配，多次新鲜蔬菜、水果；盐少、油少：每天每人最多吃1啤酒盖盐巴（每人每天少于6g盐），菜起锅时放盐最好，一定要改变“口重”的饮食习惯；炒菜时少用油，少吃猪油及油炸食品；
2、运动建议：养成锻炼良好习惯，坚持规律适宜的有氧锻炼。每天运动累积达到30分钟，每周至少锻炼3-5次；
3、通过饮食和运动使体质指数维持或控制在24Kg/㎡以下；
4、烟、酒建议：坚持不吸烟，同时拒绝二手烟；坚决不大量饮酒；
5、情志调摄：调畅情志，保持心情愉快，无抑郁恼怒；保持充足睡眠；
6、健康监测：请每1-2年常规体检一次；若有疾病请及时到医院正规就医，不随意自主买药服用。
						
						  </textarea></td>
					</tr>
			</table>
			
			<div class="physical_footer mt10">
				<table >
					<tr>
						<td>主检医生</td>
						<td><input type="text" name="" id="" value="" class="borderb"/></td>
						<td>指导医生</td>
						<td><input type="text" name="" id="" value="" class="borderb"/></td>
						<td>评估日期</td>
						<td><input type="date" name="" id="" value="" class="borderb"/></td>
					</tr>
				</table>
			</div>
			<div class="tc mt10">
				温馨提示：本建议只针对本次体检所涵盖的项目，为了全面了解你目前的健康情况，建议必要时作进一步检查。如有疑问，请及时联系您的指导医生。祝您身体健康，生活愉快！
			</div>
		</div>
		
	</div>
<script type="text/javascript">
  //时间列表
  var report=${report}
  console.log(report)
  
  
 // alert(report.lifeStyle.Diet)
  switch(report.lifeStyle.Diet){
  case 1:$('#Diet').text("荤素均衡 ")
  break;
  case 2:$('#Diet').text("荤食为主  ")
  break;
  case 3:$('#Diet').text("素食为主 ")
  break;
  case 4:$('#Diet').text(" 嗜盐 ")
  break;
  case 5:$('#Diet').text(" 嗜油")
  break;
  case 6:$('#Diet').text("嗜糖")
  break;
  default :$('#Diet').text(" ")
  break;
  }
  
  switch(report.lifeStyle.SmokingStatus){
  case 1:$('#SmokingStatus').text("从不吸烟 ")
  break;
  case 2:$('#SmokingStatus').text("已戒烟")
  break;
  case 3:$('#SmokingStatus').text("吸烟")
  break;

  default :$('#SmokingStatus').text(" ")
  break;
  }
  
    switch(report.lifeStyle.DrinkingFrequency){
  case 1:$('#DrinkingFrequency').text("从不 ")
  break;
  case 2:$('#DrinkingFrequency').text("偶尔 ")
  break;
  case 3:$('#DrinkingFrequency').text("经常")
  break;
  case 4:$('#DrinkingFrequency').text("每天")
  break;

  default :$('#DrinkingFrequency').text(" ")
  break;
  } 

 </script>	
	
</body>
</html>
