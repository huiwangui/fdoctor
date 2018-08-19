<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
  
<meta charset="UTF-8">
<title>高血压建档</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/layer.css">

</head>
<body>
	<div class="main">
		<table class="table2" border="1">
			<tr>
				<td>姓名：</td>
				<td ><a href="/" id="choice" class="chosePerson">单击选择居民</a></td>
				<td>性别：</td>
				<td>${GENDER }</td>
				<td>身份证号码：</td>
				<td>${CARD_ID}</td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="number" name="" id="" value="${TELPHONE }"  class="borderb"/></td>
				<td>年龄：</td>
				<td>${AGE }</td>
				<td>个人编码：</td>
				<td>${PersonCode }</td>
			</tr>
			<tr>
				<td>家庭住址：</td>
				<td colspan="5">${residenceAddress }</td>
			</tr>
			<tr>
				<td>家庭遗传史：</td>
				<td colspan="5">${healthHistory }</td>
			</tr>
		</table>
		<table border="1" class="table2 mt10">
			
			<tr>
				<td>血压：</td>
				<td colspan="2"><input   name="" id="RightSbp"  class="borderb w40"/>/<input   name="" id="RightDbp" value=""  class="borderb w40"/>(mmHG)</td>
				<td>高血压分级等级说明：</td>
				<td colspan="2">
					<select name="HyLevel" id="HyLevel">
						<option value="0">不详</option>
						<option value="1">1级高血压（轻度）</option>
						<option value="2">2级高血压（中度）</option>
						<option value="4">3级高血压（重度）</option>
						<option value="8">单纯收缩期高血压</option>
						<option value="16">正常高值</option>
						<option value="32">正常血压</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>危险评估：</td>
				<td colspan="5"><input  name="HyRisk" id="HyRisk"   /></td>
			</tr>
			<tr>
				<td>其他危险因素：</td>
				<td colspan="5">
					<label><input type="checkbox" name="" id="" onclick="dangerInit(this)" value="1" class="a1"/>无</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="2" class="a2"/>收缩压和舒张压水平(1-3级)</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="4" class="a2"/>男性>55岁，女性>50岁</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="8" class="a2"/>烟</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="16" class="a2"/>血脂异常</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="32" class="a2"/>早发心血管家族疾病史</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="64" class="a2"/>腹型肥胖</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="128" class="a2"/>C反应蛋白</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="256" class="a2"/>糖尿病</label> 
					<label><input type="checkbox" name="" id="" onclick="danger(this)" value="512" class="a2"/>缺乏体力活动</label> 
				</td>
			</tr>
			<tr>
				<td>靶器官损害：</td>
				<td colspan="5">
					<label><input type="checkbox" name="b1" id="" onclick="statisticsInit(this)" value="1" class="b1"/>无</label>
					<label><input type="checkbox" name="b2" id="" onclick="statistics(this)" value="2" class="b2"/>心</label>
					<label><input type="checkbox" name="b3" id="" onclick="statistics(this)" value="4" class="b2"/>脑</label>
					<label><input type="checkbox" name="b4" id="" onclick="statistics(this)" value="8" class="b2"/>肾</label>
				</td>
			</tr>
			<tr>
				<td>临床并发症：</td>
				<td colspan="5">
					<label><input type="checkbox" name="" id="" onclick="clinicStatisticsInit(this)" value="1" class="c1"/>无</label>
					<label><input type="checkbox" name="" id="" onclick="clinicStatistics(this)" value="2" class="c2"/>高血压心脏病</label>
					<label><input type="checkbox" name="" id="" onclick="clinicStatistics(this)" value="4" class="c2"/>冠心病</label>
					<label><input type="checkbox" name="" id="" onclick="clinicStatistics(this)" value="8" class="c2"/>高血压脑病</label>
					<label><input type="checkbox" name="" id="" onclick="clinicStatistics(this)" value="16" class="c2"/>脑血管病</label>
					<label><input type="checkbox" name="" id="" onclick="clinicStatistics(this)" value="32" class="c2"/>高血压危象</label>
					<label><input type="checkbox" name="" id="" onclick="clinicStatistics(this)" value="64" class="c2"/>慢性肾功能衰竭</label>
				</td>
			</tr>
			<tr>
				<td>责任医生：</td>
				<td>
				        <a  id="DoctorName" class="chooseDoctor"></a>
					    <input type="hidden" id="DoctorID"/></td>
				</td>
				<td><span class="redcolor">*</span>确诊日期：</td>
				<td>
					<input type="date" name="" id="DiagnosisDate" value=""  class="borderb"/>
					<input type="num" name="" id="fontYear" value=""  class="borderb w20">年前</td>
				<td><span class="redcolor">*</span>建档日期：</td>
				<td><input type="date" name="" id="RecordDate" value=""  class="borderb"/></td>
			</tr>
			
			
		</table>
		
		<div class="clear"></div>
		<div class="operation tc">
			<a href="javascript:void(0)"  class="sibtn">重置</a> 
		    <a id="save" class="sibtn" href="javascript:void(0)">保存</a>
		</div>
	</div>



</body>
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script> 
<script type="text/javascript" src="/fdoctor/statics/js/layer.js" ></script>
<script type="text/javascript">
	$(".a1").click(function(){
		   $(".a2").removeAttr('checked');
		   $(this).attr('checked',true)
		   
	})
	 $(".a2").click(function(){
		   $(".a1").removeAttr('checked')
		   
	})
   $(".b1").click(function(){
	   $(".b2").removeAttr('checked');
	   $(this).attr('checked',true)
	   
   })
    $(".b2").click(function(){
	   $(".b1").removeAttr('checked')
	   
   })
    $(".c1").click(function(){
	   $(".c2").removeAttr('checked')
	   $(this).attr('checked',true)
   })
    $(".c2").click(function(){
	   $(".c1").removeAttr('checked')
	   
   })
   	//选择医生
	$(".chooseDoctor").on('click',function(){
	 layer.open({
	  type: 2,
	  title: '请选择医生',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['700px', '600px'],  
	  content: '/fdoctor/views/mobile/hypertension/chooseDoctor.jsp'  
	});
 
	return false;
   }) 
   

	$(function(){
		if("${NAME }"!=""){
			 
			$("#choice").text('${NAME }')
		} 
		 var dn ="${data.ResponsibilityDoctor}";
		  if(dn!=null&&dn!=""){
			  $("#DoctorName").text(dn); 
		  }else{
			  $("#DoctorName").text("请选择医生");
		  }
		
	})
 	$(".chosePerson").on('click',function(){
 		 layer.open({
		  type: 2,
		  title: '请选择居民',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['700px', '600px'], 
		  content: '/fdoctor/views/mobile/hypertension/hypertensionPeoplesearch.jsp?ProductCode='+encodeURI("${ProductCode}") 
		});
 		 //layer.close(index);
		return false;
 	})
 	
 	function refresh(params){
 		//console.log(params)
 		//alert(param)
 		 //$('#select').attr("href", "/fdoctor/mobile/hypertension/echo?NAME="+params.NAME+"&GENDER="+params.GENDER+"&CARD_ID="+params.CARD_ID+"&ID="+params.ID+"&TELPHONE="+params.TELPHONE+"&AGE="+params.AGE+"&PersonCode="+params.PERSON_CODE);
    	 
 		window.location.href='/fdoctor/mobile/hypertension/echo?NAME='+params.NAME+"&GENDER="+params.GENDER+"&CARD_ID="+params.CARD_ID+"&ID="+params.ID+"&TELPHONE="+params.TELPHONE+"&AGE="+params.AGE+'&PersonCode='+params.PERSON_CODE+'&ProductCode='+"${ProductCode}";
 		
 	}

   //处理靶器官损伤
   var target_organ=1;
   function statistics(tr){
	   if(target_organ==1){
		   target_organ=0;
	   }
	   if(tr.checked){
		   target_organ+=parseInt(tr.value); 
	   }else{
		   target_organ-=parseInt(tr.value);
	   } 
 
   }
   function statisticsInit(tr){	   
	   if(tr.checked){
		   target_organ=1; 
	   } 
 
   }
   //处理临床并发症
   var clinic=1;
   function clinicStatistics(tr){
	   if(clinic==1){
		   clinic=0;
	   }
	   if(tr.checked){
		   clinic+=parseInt(tr.value); 
	   }else{
		   clinic-=parseInt(tr.value);
	   } 
 
   }
   function clinicStatisticsInit(tr){	  
	   if(tr.checked){
		   clinic=1; 
	   } 
 
  }
  //处理危险因素
  var dangerData =1;
  function dangerInit(tr){
	  if(tr.checked){
		   dangerData=1; 
	   } 
 
  }
  function danger(tr){	  
	  if(dangerData==1){
		   dangerData=0;
	   }
	   if(tr.checked){
		   dangerData+=parseInt(tr.value); 
	   }else{
		   dangerData-=parseInt(tr.value);
	   } 
 
  }
  
  
//保存
	$("#save").click(function(){
		   dangerData=dangerData ==0?1:dangerData;  
		   clinic=clinic ==0?1:clinic;  
		   target_organ=target_organ ==0?1:target_organ;
		   var saveParam = {};
		   saveParam.ProductCode = "${ProductCode}"
		   saveParam.BuildType = "高血压"
		   saveParam.PersonTel = $("#PersonTel").val()
		   var CmPerson = {}
		   CmPerson["PersonID"]="${PersonID}"
		   CmPerson["DiseaseKindID"]="52A0328740914BCE86ED10A4D2521816"
		   CmPerson["Status"]="null"
		   CmPerson["DiagnosisDate"]=$("#DiagnosisDate").val()
		   CmPerson["OrgID"]="${BuildOrgID}"
		   CmPerson["DoctorID"]=$("#DoctorID").val();
		   CmPerson["DoctorName"]=$("#DoctorName").text();
		   CmPerson["UserID"]="${PUserID}"
		   CmPerson["UserName"]="${PUserName}"
		   CmPerson["RecordDate"]=$("#RecordDate").val();
		   var ExamBody ={}
		   ExamBody["RightSbp"]=$("#RightSbp").val();
		   ExamBody["RightDbp"]=$("#RightDbp").val();
		   var CmHyLevel ={}
		   CmHyLevel["CmPersonID"] ="${cmHyLevelMap.CmPersonID}"
		   CmHyLevel["HyLevel"] =$("#HyLevel").val();
		   CmHyLevel["OtherRiskFactors"] =dangerData;
		   CmHyLevel["TargetOrganDamage"] =target_organ;
		   CmHyLevel["ClinicalComplications"] =clinic;
		   CmHyLevel["HyRisk"] =$("#HyRisk").val();
		   saveParam.CmPerson=CmPerson;
		   saveParam.ExamBody=ExamBody;
		   saveParam.CmHyLevel=CmHyLevel;
		   if(window.confirm('是否保存？')){
					$.ajax({
					    type: 'POST',
					    url: '/fdoctor/mobile/hypertension/addHypertension' ,
					    data: {
					    	saveParam : JSON.stringify(saveParam),
					    } ,
					    success: function(data){
					    	if(data.code == '200'){
					    		alert('保存成功！');
					    		
					    	}else{
					    		alert('保存失败！');
					    	}
					    }
					});
			        return true;
	      }else{
			        return false;
		 } 
		  // $("#save").attr("href", '/fdoctor/mobile/hypertension/addHypertension?RightSbp='+$("#RightSbp").val()+'&RightDbp='+$("#RightDbp").val()+'&HyLevel='+$("#HyLevel").val()+'&HyRisk='+$("#HyRisk").val()+'&OtherRiskFactors='+dangerData+'&TargetOrganDamage='+target_organ+'&ClinicalComplications='+clinic+'&PersonTel='+"${TELPHONE}"+'&PersonID='+"${PersonID}"+'&DiagnosisDate='+$("#DiagnosisDate").val()+'&ResponsibilityID='+"${ResponsibilityID}"+'&DoctorName='+$("#DoctorName").val()+'&UserID='+"${PUserID}"+'&UserName='+"${PUserName}"+'&CmPersonID='+"${ID}"+'&BuildOrgID='+"${BuildOrgID}");
 
	}) 
</script>
</html>
