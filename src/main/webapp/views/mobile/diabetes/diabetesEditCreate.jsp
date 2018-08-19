<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
  
<meta charset="UTF-8">
  
<title>糖尿病建档</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/layer.css">
<style type="text/css">
	body{
		background: #f0f3f8;
	}
	
</style>
</head>


<body>


	<div class="main">
		<table class="table2" border="1">
			<tr>
				<td>姓名：</td>
			 	<td><a href="javascript:void(0)" id="personName" class="peopleSearch">单击选择居民</a></td>
				<td>性别：</td>
				<td><span id="personGender" >${personGender}</span></td>
				<td>身份证号码：</td>
				<td><span id="CardID" >${CardID}</span></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="number" name="" id="personTel" value="${personTel }"  class="borderb"/></td>
				<td>年龄：</td>
				<td><span id="buildAge" >${buildAge}</span></td>
				<td>个人编码：</td>
				<td><span id="PersonCode" v>${PersonCode }</span></td>
			</tr>
			<tr>
				<td>家庭住址：</td>
				<td colspan="5"><span id="ResidenceAddress" >${ResidenceAddress}</span></td>
			</tr>
			<tr>
				<td>家庭遗传史：</td>
				<td colspan="5"><span id="HealthHistory" >${HealthHistory }</span></td>
			</tr>
			<tr>
				<td>责任医生：</td>
				<td><input type="hidden" name="" id="OrgID" value="${OrgID}" />
				<a href="javascript:void(0)"  id="DoctorName" class="docSearch">单击选择医生</a>
					<input type="hidden" id="DoctorID" >
			    <!-- <input id="DoctorName"> --></td>
				<td><span class="redcolor">*</span>确诊日期：</td>
				<td>
					<input type="date"  name="" id="DiagnosisDate" value=""  class="borderb" onmouseout="time()"/>
					<input type="num" name="" id="lastYear" value="" style="width: 80px;"  class="borderb w20">年前</td>
				<td><span class="redcolor">*</span>建档日期：</td>
				<td><input type="date" name="" id="RecordDate" value=""  class="borderb"/></td>
			</tr>
				
				
		</table>
			
			<div class="clear"></div>
			<div class="operation tc">
				<a href="javascript:void(0)"  class="sibtn">重置</a> 
				<a id="saveE" href="javascript:void(0)" class="sibtn">保存</a>
			</div>
	</div>
 



</body>
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script> 
<script type="text/javascript" src="/fdoctor/statics/js/layer.js" ></script>
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
		if("${personName }"!=""){
			 
			$("#personName").text('${personName}')
			
			 
		} 
		 
	});
  
	 $(".peopleSearch").on('click',function(){
		 layer.open({
			  type: 2,
			  title: '查询人员信息',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['800px', '620px'],
			  content: '/fdoctor/views/mobile/diabetes/diabetesPeopleSearch.jsp?doctorName='+encodeURI("${doctorName}")//iframe的url
			});
			return false;
	 });
	 
	 function refresh(params){
			window.location.href='/fdoctor/mobile/diabetes/searchOne?NAME='+params.NAME+"&GENDER="+params.GENDER+"&CARD_ID="+params.CARD_ID+"&ID="+params.ID+"&TELPHONE="+params.TELPHONE+"&AGE="+params.AGE+'&PersonCode='+params.PERSON_CODE+'&doctorName='+"${doctorName}";
			
		}
	 
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
	 
	 
	 $("#saveE").click(function(){
		 if(window.confirm("是否新建档案？")){
			$.ajax({
				 type:"POST",
				 url:"/fdoctor/mobile/diabetes/saveNew",
				 data:{
					 personTel:$("#personTel").val(),
					 PersonID:"${PersonID}",
					 DiseaseKindID:"${DiseaseKindID}",
					 Status:"${Status}",
					 DiagnosisDate:$("#DiagnosisDate").val(),
					 OrgID:"${OrgID}",
					 DoctorID:$("#DoctorID").val(),
					 DoctorName:(($("#DoctorName").text()=="单击选择医生")?"":$("#DoctorName").text()),
					 UserID:"${userId}",
					 UserName:"${userName}",
					 RecordDate:$("#RecordDate").val(),
					 doctorName:"${doctorName}"
					},
			 		success: function(data){
			    	if(data.code == '200'){
			    		alert('保存成功！');
			    		
			    	}else{
			    		alert('已存在档案！');
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
