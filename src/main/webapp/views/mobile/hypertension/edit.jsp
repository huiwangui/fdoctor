<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head> 
<meta charset="UTF-8"> 
<title>高血压编辑</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/common.css" />
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/layer.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css" />
</head>
<body>


	<div class="main">
		<table class="table2" border="1">
			<tr>
				<td>姓名：</td>
			<!-- 	<td id="Name"><a href="/">单击选择居民</a></td> -->
				<td ><input id="Name"> </td>
				<td>性别：</td>
				<td id="GenderCode"></td>
				<td>身份证号码：</td>
				<td id="CardID"> </td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="number" name="" id="PersonTel"    class="borderb"/></td>
				<td>年龄：</td>
				<td id="BirthDay"> </td>
				<td>个人编码：</td>
				<td id="PersonCode"> </td>
			</tr>
			<tr>
				<td>家庭住址：</td>
				<td colspan="5" id="ResidenceAddress"> </td>
			</tr>
			<tr>
				<td>家庭遗传史：</td>
				<td colspan="5" id="HealthHistory"> </td>
			</tr>
		</table>
		<form action="/fdoctor/mobile/hypertension/updateHypertension">
			<table border="1" class="table2 mt10">
				
				<tr>
					<td>血压：</td>
					<td colspan="2"><input type="number" name="RightSbp" id="RightSbp"    class="borderb w40"/>/<input type="number" name="RightDbp" id="RightDbp"   class="borderb w40"/>(mmHG)</td>
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
					<td colspan="5"><input  name="HyRisk" id="HyRisk"  class="borderb"/>${HyRisk }</td>
				</tr>
				<tr>
					<td>其他危险因素：</td>
					<td colspan="5">
						<label><input type="checkbox" name="" id="dangerFactor1" onclick="dangerInit(this)" value="1" class="a1"/>无</label> 
						<label><input type="checkbox" name="" id="dangerFactor2" onclick="danger(this)" value="2" class="a2"/>收缩压和舒张压水平(1-3级)</label> 
						<label><input type="checkbox" name="" id="dangerFactor3" onclick="danger(this)" value="4" class="a2"/>男性>55岁，女性>50岁</label> 
						<label><input type="checkbox" name="" id="dangerFactor4" onclick="danger(this)" value="8" class="a2"/>烟</label> 
						<label><input type="checkbox" name="" id="dangerFactor5" onclick="danger(this)" value="16" class="a2"/>血脂异常</label> 
						<label><input type="checkbox" name="" id="dangerFactor6" onclick="danger(this)" value="32" class="a2"/>早发心血管家族疾病史</label> 
						<label><input type="checkbox" name="" id="dangerFactor7" onclick="danger(this)" value="64" class="a2"/>腹型肥胖</label> 
						<label><input type="checkbox" name="" id="dangerFactor8" onclick="danger(this)" value="128" class="a2"/>C反应蛋白</label> 
						<label><input type="checkbox" name="" id="dangerFactor9" onclick="danger(this)" value="256" class="a2"/>糖尿病</label> 
						<label><input type="checkbox" name="" id="dangerFactor10" onclick="danger(this)" value="512" class="a2"/>缺乏体力活动</label> 
					</td>
				</tr>
				<tr>
					<td>靶器官损害：</td>
					<td colspan="5">
						<label><input type="checkbox" name="b1" id="targetOrgan1" onclick="statisticsInit(this)" value="1" class="b1"/>无</label>
						<label><input type="checkbox" name="b2" id="targetOrgan2" onclick="statistics(this)" value="2" class="b2"/>心</label>
						<label><input type="checkbox" name="b3" id="targetOrgan3" onclick="statistics(this)" value="4" class="b2"/>脑</label>
						<label><input type="checkbox" name="b4" id="targetOrgan4" onclick="statistics(this)" value="8" class="b2"/>肾</label>
					</td>
				</tr>
				<tr>
					<td>临床并发症：</td>
					<td colspan="5">
						<label><input type="checkbox" name="" id="clinical1" onclick="clinicStatisticsInit(this)" value="1" class="c1"/>无</label>
						<label><input type="checkbox" name="" id="clinical2" onclick="clinicStatistics(this)" value="2" class="c2"/>高血压心脏病</label>
						<label><input type="checkbox" name="" id="clinical3" onclick="clinicStatistics(this)" value="4" class="c2"/>冠心病</label>
						<label><input type="checkbox" name="" id="clinical4" onclick="clinicStatistics(this)" value="8" class="c2"/>高血压脑病</label>
						<label><input type="checkbox" name="" id="clinical5" onclick="clinicStatistics(this)" value="16" class="c2"/>脑血管病</label>
						<label><input type="checkbox" name="" id="clinical6" onclick="clinicStatistics(this)" value="32" class="c2"/>高血压危象</label>
						<label><input type="checkbox" name="" id="clinical7" onclick="clinicStatistics(this)" value="64" class="c2"/>慢性肾功能衰竭</label>
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
						 
						<input type="date"  name="DiagnosisDate" id="DiagnosisDate" value="" onmouseout="time()" class="borderb"/>
						<input type="num" name="" id="fontYear" value="" style="width: 80px;"  class="borderb w20">年前</td>
				        <td><span class="redcolor">*</span>建档日期：</td>
					   <td><input type="date"   name="" id="RecordDate"    class="borderb"/></td>
				</tr>
				
				
			</table>
			
			<div class="clear"></div>
			<div class="operation tc">
				<a href="javascript:void(0)"  class="sibtn">重置</a> 
		        <a id="save" class="sibtn" href="javascript:void(0)">保存</a>
			</div>
		</form>
	</div>
 


</body>
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/layer.js" ></script>
<script type="text/javascript">
function time(){
	var ddate=$("#DiagnosisDate").val();
	var d=new Date();
	var nowy=d.getFullYear();
	var p=new Date(ddate);
	var last=nowy-p.getFullYear();
	if(!isNaN(last)){
		$("#fontYear").val(last);
	}
}
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
  
 $(function(){
	 //血压回显
	  var examBodyMap = "${examBodyMap}";
	  if(examBodyMap!=""){
		  $("#RightSbp").val("${examBodyMap.RightSbp}");
		  $("#RightDbp").val("${examBodyMap.RightDbp}");
	  }
	 //高血压分级等级说明回显
	 var cmHyLevelMap ="${cmHyLevelMap}";
	 if(cmHyLevelMap!=""){
		 $("#HyLevel").val("${HyLevel}")
		 var OtherRiskFactorstr ="${OtherRiskFactors}";
		 var OtherRiskFactorsList = new Array();
		 OtherRiskFactorsList=OtherRiskFactorstr.split(",");
		 for(var i=0;i<OtherRiskFactorsList.length;i++){
			if(1==OtherRiskFactorsList[i]){
				$("#dangerFactor1").attr("checked","true")
			}else if(2==OtherRiskFactorsList[i]){
				$("#dangerFactor2").attr("checked","true")
			}else if(4==OtherRiskFactorsList[i]){
				$("#dangerFactor3").attr("checked","true")
			}else if(8==OtherRiskFactorsList[i]){
				$("#dangerFactor4").attr("checked","true")
			}else if(16==OtherRiskFactorsList[i]){
				$("#dangerFactor5").attr("checked","true")
			}else if(32==OtherRiskFactorsList[i]){
				$("#dangerFactor6").attr("checked","true")
			}else if(64==OtherRiskFactorsList[i]){
				$("#dangerFactor7").attr("checked","true")
			}else if(128==OtherRiskFactorsList[i]){
				$("#dangerFactor8").attr("checked","true")
			}else if(256==OtherRiskFactorsList[i]){
				$("#dangerFactor9").attr("checked","true")
			}else if(512==OtherRiskFactorsList[i]){
				$("#dangerFactor10").attr("checked","true")
			} 
		}
		 var TargetOrganDamage ="${TargetOrganDamage}";
		 var TargetOrganDamageList = new Array();
		 TargetOrganDamageList=TargetOrganDamage.split(",");
		 for(var i=0;i<TargetOrganDamageList.length;i++){
			if(1==TargetOrganDamageList[i]){
				$("#targetOrgan1").attr("checked","true")
			}else if(2==TargetOrganDamageList[i]){
				$("#targetOrgan2").attr("checked","true")
			}else if(4==TargetOrganDamageList[i]){
				$("#targetOrgan3").attr("checked","true")
			}else if(8==TargetOrganDamageList[i]){
				$("#targetOrgan4").attr("checked","true")
			}
		}
		 var ClinicalComplications ="${ClinicalComplications}";
		 var ClinicalComplicationsList = new Array();
		 ClinicalComplicationsList=ClinicalComplications.split(",");
		 for(var i=0;i<ClinicalComplicationsList.length;i++){
			if(1==ClinicalComplicationsList[i]){
				$("#clinical1").attr("checked","true")
			}else if(2==ClinicalComplicationsList[i]){
				$("#clinical2").attr("checked","true")
			}else if(4==ClinicalComplicationsList[i]){
				$("#clinical3").attr("checked","true")
			}else if(8==ClinicalComplicationsList[i]){
				$("#clinical4").attr("checked","true")
			}else if(16==ClinicalComplicationsList[i]){
				$("#clinical5").attr("checked","true")
			}else if(32==ClinicalComplicationsList[i]){
				$("#clinical6").attr("checked","true")
			}else if(64==ClinicalComplicationsList[i]){
				$("#clinical7").attr("checked","true")
			} 
		}
		 
	 }
	  var name="${data.Name }";
	  $("#Name").val(name);
	  var GenderCode =${data.GenderCode }
	  var Gender ;
	  if(GenderCode=="0"){
		  Gender="未知";
	  }else if(GenderCode=="1"){
		  Gender="男";
	  }else if(GenderCode=="2"){
		  Gender="女";
	  }else{
		  Gender="未说明"; 
	  }
	  $("#CardID").text("${data.CardID}");
	  $("#GenderCode").text(Gender);
	  $("#PersonTel").val("${data.PersonTel}");
	  var birth= "${data.BirthDay}";
	  var ages= birth.split("T"); 
	  var birthday=new Date(ages[0].replace(/-/g, "\/")); 
	  var d=new Date(); 
	  var age = d.getFullYear()-birthday.getFullYear();
	  //$("#BirthDay").text(age);
	  $("#BirthDay").text("${list.buildAge}");
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
	  var dn ="${cmPerson.DoctorName}";
	  if(dn!=null&&dn!=""){
		  $("#DoctorName").text(dn); 
	  }else{
		  $("#DoctorName").text("请选择医生");
	  }
	  
	  
	  var DiagnosisDate = "${cmPerson.DiagnosisDate}";
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
	  
	  $("#fontYear").val(lastYear);
	  
	  
	 /*  var timeSpan = DiagnosisDate.replace('Date','').replace('(','').replace(')','').replace(/\//g,'');
	  var d = new Date(parseInt(timeSpan));
	  var nd = new Date();
	  var year =nd.getFullYear()-d.getFullYear();
	  $("#fontYear").val(year); 
	   d= d.getFullYear() + '/' + (d.getMonth() + 1) + '/' + d.getDate();
	  $("#DiagnosisDate").val(d); */
	 
	   ;
	  
	  if("${cmPerson.RecordDate}"!=null &&"${cmPerson.RecordDate}"!=""){
		  var rd = "${cmPerson.RecordDate}";
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
		  var r="";
	  }
	  
	  $("#RecordDate").val(r);
	  
	  
	  
	  
	  
 }) 
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
		   CmPerson["PersonID"]="${cmPerson.PersonID}"
		   CmPerson["DiseaseKindID"]="${cmPerson.DiseaseKindID}"
		   CmPerson["Status"]="${cmPerson.Status}"
		   CmPerson["DiagnosisDate"]="${cmPerson.DiagnosisDate}"
		   CmPerson["OrgID"]="${cmPerson.OrgID}"
		   CmPerson["DoctorID"]=$("#DoctorID").val();
		   CmPerson["DoctorName"]=$("#DoctorName").text();
		   CmPerson["UserID"]="${cmPerson.UserID}"
		   CmPerson["UserName"]="${cmPerson.UserName}"
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
					    url: '/fdoctor/mobile/hypertension/updateHypertension' ,
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
		  // $("#save").attr("href", '/fdoctor/mobile/hypertension/updateHypertension?RightSbp='+$("#RightSbp").val()+'&RightDbp='+$("#RightDbp").val()+'&HyLevel='+$("#HyLevel").val()+'&HyRisk='+$("#HyRisk").val()+'&OtherRiskFactors='+dangerData+'&TargetOrganDamage='+target_organ+'&ClinicalComplications='+clinic+'&PersonTel='+$("#PersonTel").val()+'&PersonID='+"${cmPerson.PersonID}"+'&DiagnosisDate='+$("#DiagnosisDate").val()+'&ResponsibilityID='+"${ResponsibilityID}"+'&DoctorName='+$("#DoctorName").val()+'&UserID='+"${data.PUserID}"+'&UserName='+"${data.PUserName}"+'&CmPersonID='+"${cmPerson.ID}"+'&BuildOrgID='+"${data.BuildOrgID}"+"&RecordDate="+$("#RecordDate").val()+"&DiseaseKindID="+"${cmPerson.DiseaseKindID}"+"&Status="+"${cmPerson.Status}");
 
	}) 
</script>
</html>
