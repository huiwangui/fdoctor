<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
  
<meta charset="UTF-8">
  
<title>糖尿病编辑</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/common.css" />
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css" />
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/layer.css">
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script> 
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/easyui-lang-zh_CN.js"></script>	
<script type="text/javascript" src="/fdoctor/statics/js/layer.js" ></script>

</head>


<body>


	<div class="main">
		<table class="table2" border="1">
			<tr>
				<td>姓名：</td>
			<!-- 	<td id="Name"><a href="/">单击选择居民</a></td> -->
				<td ><span id="personName"/> </td>
				<td>性别：</td>
				<td><span id="personGender"/></td>
				<td>身份证号码：</td>
				<td><span id="CardID"/></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="number" name="" id="personTel" value=""  class="borderb"/></td>
				<td>年龄：</td>
				<td><span id="buildAge"/></td>
				<td>个人编码：</td>
				<td><span id="PersonCode"/></td>
			</tr>
			<tr>
				<td>家庭住址：</td>
				<td colspan="5"><span id="ResidenceAddress"/></td>
			</tr>
			<tr>
				<td>家庭遗传史：</td>
				<td colspan="5"><span id="HealthHistory"/></td>
			</tr>
			<tr>
				<td>责任医生：</td>
				<td><!-- <input type="hidden" name="" id="OrgID" value="${cmMap.OrgID}" /> -->
				<a href="javascript:void(0)" id="DoctorName" class="docSearch"></a>
					<input type="hidden" id="DoctorID" value="${cmMap.DoctorID}"></td>
				<td><span class="redcolor">*</span>确诊日期：</td>
				<td>
				<!-- <input type="date" name="" id="DiagnosisDate" value=""  class="borderb"/> -->
					<input type="date"  name="" id="DiagnosisDate" value=""  class="borderb" onmouseout="time()"/>
					<input type="num" name="" id="lastYear" value="" style="width: 80px;"  class="borderb w20">年前</td>
				<td><span class="redcolor">*</span>建档日期：</td>
				<!-- <td><input type="date" name="" id="RecordDate" value=""  class="borderb"/></td> -->
				<td><input type="date" name="" id="RecordDate" value=""  class="borderb"/></td>
			</tr>
				
				
		</table>
			
			<div class="clear"></div>
			<div class="operation tc">
				<a href="javascript:void(0)"  class="sibtn">重置</a> 
				<a id="updateE" href="javascript:void(0)" class="sibtn">修改</a>
			</div>
	</div>
 


</body>
<script type="text/javascript">
function time(){
	//var ddate=document.getElementById("DiagnosisDate");
	var ddate=$("#DiagnosisDate").val();var d=new Date();
	var nowy=d.getFullYear();
	var p=new Date(ddate);
	var last=nowy-p.getFullYear();
	if(!isNaN(last)){
		$("#lastYear").val(last);
	}
}
  
 $(function(){
	 //页面回显
	  var name="${msgMap.personName }";
	  $("#personName").text(name);
	  //var GenderCode =${data.GenderCode }
	  //var Gender ;
	  //if(GenderCode=="0"){
	// 	  Gender="未知";
	 // }else if(GenderCode=="1"){
	//	  Gender="男";
	 // }else if(GenderCode=="2"){
	//	  Gender="女";
	  //}else{
	//	  Gender="未说明"; 
	  //}
	  $("#CardID").text("${data.CardID}");
	  $("#personGender").text("${msgMap.personGender}");
	  $("#personTel").val("${msgMap.personTel}");
	  //var birth= "${data.BirthDay}";
	  //var ages= birth.split("T"); 
	  // var birthday=new Date(ages[0].replace(/-/g, "\/")); 
	  //var age = d.getFullYear()-birthday.getFullYear();
	  // if(isNaN(age)){
	  //  age="未知";
	  // }
	  var d=new Date();
	  var nowy=d.getFullYear();
	  
	  $("#buildAge").text("${msgMap.buildAge }");
	  $("#PersonCode").text("${data.PersonCode}");
	  $("#ResidenceAddress").text("${data.ResidenceAddress}");
	  var HealthHistorys ="${data.HealthHistory}";
	  var health ;
	  if(HealthHistorys!="[]"){
		  for(var i=1;i<HealthHistorys.length;i++){
			  if(HealthHistorys[i].RecordType=="4"){
				  health+=HealthHistorys[i].Name+",";
			  }
		  }
		  $("#HealthHistory").text(health);
	  };
	  if("${cmMap.DoctorName}"!=null &&"${cmMap.DoctorName}"!=""){
		  $("#DoctorName").text("${cmMap.DoctorName}");
	  }else{
		  $("#DoctorName").text("单击选择医生");
	  }
	  
	  var DiagnosisDate = "${cmMap.DiagnosisDate}";
	  var timeSpan = DiagnosisDate.replace('Date','').replace('(','').replace(')','').replace(/\//g,'');
	  var d = new Date(parseInt(timeSpan));
	  var pp=d.getFullYear();
	  
	  if(d.getMonth()<9){
		  var dmonth = ("0" + (d.getMonth() + 1)).slice(-2);
	  }else{
		  var dmonth=d.getMonth() + 1;
	  }
	  if(d.getDate()<10){
		  var dday=("0" + d.getDate()).slice(-2);
	  }else{
		  var dday=d.getDate();
	  }
	  d= d.getFullYear() + '-' + dmonth + '-' +dday;  
	  
	  $("#DiagnosisDate").val(d);
	  
	  var nowt=new Date();
	  if(pp!='' && pp!=NaN && pp!=null){
		  var lastYear=nowt.getFullYear()-pp;
	  }
	  
	  $("#lastYear").val(lastYear);
	  if("${cmMap.RecordDate}"!=null &&"${cmMap.RecordDate}"!=""){
		  var rd = "${cmMap.RecordDate}";
		  var ts = rd.replace('Date','').replace('(','').replace(')','').replace(/\//g,'');
		  var r = new Date(parseInt(ts));
		  if(r.getMonth()<9){
			  var rmonth = ("0" + (r.getMonth() + 1)).slice(-2);
		  }else{
			  var rmonth=r.getMonth() + 1;
		  }
		  if(r.getDate()<10){
			  var rday=r.getDate();
		  }else{
			  var rday=("0" + r.getDate()).slice(-2);
		  }
		  r= r.getFullYear() + '-' + rmonth + '-' +rday;  
		  console.log(r);
	  }else{
		  var r="未知";
	  }
	  
	  $("#RecordDate").val(r);
	  
 }) 
 $(".docSearch").on('click',function(){
		 layer.open({
			  type: 2,
			  title: '查询医生列表',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['500px', '410px'],
			  content: '/fdoctor/views/mobile/diabetes/diabetesDocSearch.jsp?ORGID='//iframe的url
			});
			return false;
	 });
 $("#updateE").click(function(){
	 if(window.confirm("是否修改？")){
		$.ajax({
			 type:"POST",
			 url:"/fdoctor/mobile/diabetes/updateEdit",
			 data:{
				 ID:"${cmMap.ID}",
				 personTel:$("#personTel").val(),
				 PersonID:"${cmMap.PersonID}",
				 DiseaseKindID:"${cmMap.DiseaseKindID}",
				 Status:"${cmMap.Status}",
				 DiagnosisDate:$("#DiagnosisDate").val(),
				 OrgID:"${cmMap.OrgID}",
				 DoctorID:$("#DoctorID").val(),
				 DoctorName:(($("#DoctorName").text()=="单击选择医生")?"":$("#DoctorName").text()) ,
				 UserID:"${userId}",
				 UserName:"${userName}",
				 RecordDate:$("#RecordDate").val(),
				 doctorName:"${doctorName}"
				},
		 		success: function(data){
		    	if(data.code == '200'){
		    		alert('修改成功！');
		    		
		    	}else{
		    		alert('修改失败');
		    	}
		    }
		 });
	 	return true;
	 }else{
		 return false;
	 }
	 
 });
</script>
</html>
