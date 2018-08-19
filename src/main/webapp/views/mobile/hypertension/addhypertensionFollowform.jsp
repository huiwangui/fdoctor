<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>高血压增加随访</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/layer.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->
 
</head>
<body>
	<div class="main">
		<h1>高血压患者随访服务记录表</h1>
		<p><span>填表说明</span><span>(绿色边框为下次目标)</span></p>
		<table class="">
			<tr>
				<td class="bold">姓名：</td>
				<td>${baseData.Name}</td>
				<td class="bold">性别：</td>
				<td>${Gender}</td>
				<td class="bold"> 年龄： </td> 
				<td>${age}岁</td>
			</tr>
			<tr>
				<td class="bold">电话：</td>
				<td>${PersonTel}</td>
				<td class="bold">既往病史：</td>
				<td>${cmData}</td>
				<td class="bold">家族史：</td>
				<td>${FamilyHistory}</td>
			</tr>
			<tr>
				<td class="bold">上次随访时间：</td> 
				<td>${followUpList.lastHyFollowup.FOLLOWUPDATE } </td>
				<td class="bold">上次随访结局：</td>
				<td colspan="3">data</td>
			</tr>
			<tr>
				<td class="bold">上次随访血压：</td> 
				<td>${followUpList.ExamBody.RightSbp }/${followUpList.ExamBody.RightDbp } </td>
				<td class="bold">上次随访心率：</td>
				<td>${followUpList.ExamBody.HeartRate }</td>
				 
			</tr>
			<tr> 
				<td rowspan="2" class="bold">上次随访设定目标值：</td>
				<td class="bold">日吸烟量：</td>
				<td>${followUpList.lastHyFollowup.NEXTSMOKING } </td>
				<td class="bold">日饮酒量：</td>
				<td>${followUpList.lastHyFollowup.NEXTDAILYALCOHOL }</td>
				
			</tr>
			<tr>
				<td class="bold">运动：</td>
				<td>${followUpList.lastHyFollowup.NEXTEXERCISEWEEKTIMES } 次/周   ${followUpList.lastHyFollowup.NEXTEXERCISEWEEKMINUTE }分钟/次 </td>
				<td class="bold">体重：</td>
				<td> ${followUpList.lastHyFollowup.NEXTWEIGHT }(kg)</td>
				<td class="bold">心率：</td>
				<td>${followUpList.lastHyFollowup.NEXTHEARTRATE }</td>
			</tr>
		</table>
		<form>
		<table border="1"  class="table2 mt20" >
			<tr>
				<td>随访时间</td>
				<td colspan="2"><input   name="FollowUpDate" id="FollowUpDate"  value="${followUpDate }" /></td>
				<td>随访方式</td>
				<td><a onClick="followUp(this)" value="1">1 门诊  </a> 
				<a onClick="followUp(this)" value="2">2 家庭  </a> 
				<a onClick="followUp(this)" value="3">3 电话</a> 
					<span class="fr"><input type="number" name="WayUp" id="WayUp" value="${data.cmHypertension.WayUp }"   class="w20"/></span>
				</td>
			</tr>
			<tr>
				<td>症状</td>
				<td colspan="5" class="tl">
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="1" value="1">1无症状</a>      
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="2" value="2">2头痛头晕 </a>
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="3" value="4">3恶心呕吐</a>
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="4" value="8">4眼花耳鸣 </a>
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="5" value="16">5呼吸困难 </a>
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="6" value="32">6心悸胸闷 </a>
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="7" value="64">7鼻衄</a>
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="8" value="128">8四肢发麻</a>
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="9" value="256">9下肢水肿 </a>
				<a onClick="ChangSelect(this)" href="javascript:void(0)" id="10" value="512">10其他</a>
				 
				 <input type="text" name="SymptomOther" id="SymptomOther" />
					<span class="fr">
						<input type="number" name="Symptom0" id="Symptom0" value=""  class="w20"/>/
						<input type="number" name="Symptom1" id="Symptom1" value="" class="w20"/> /
						<input type="number" name="Symptom2" id="Symptom2" value="" class="w20"/> /
						<input type="number" name="Symptom3" id="Symptom3" value="" class="w20"/> /
						<input type="number" name="Symptom4" id="Symptom4" value="" class="w20"/> /
						<input type="number" name="Symptom5" id="Symptom5" value="" class="w20"/> /
						<input type="number" name="Symptom6" id="Symptom6" value="" class="w20"/> /
						<input type="number" name="Symptom7" id="Symptom7" value="" class="w20"/> /
						<input type="number" name="Symptom8" id="Symptom8" value="" class="w20"/> /
						<input type="number" name="Symptom9" id="Symptom9" value="" class="w20"/> 
						 
					</span>
				</td>
			</tr>
			
			
			
			<tr>
				<td rowspan="6">体征</td>
				<td>血压(mmHg)</td>
				<td><input   name="RightSbp" id="RightSbp" value="${data.examBody.RightSbp }"  class="w40"/>/<input   name="RightDbp" id="RightDbp" value="${data.examBody.RightDbp }" class="w40"/></td>
				<td>体重(kg)</td>
				<td>
					<input  name="Weight" id="Weight" value="${data.examBody.Weight}" class="w40"/>/<input   name="NextWeight" id="NextWeight" value="${data.cmHypertension.NextWeight}" class="w40 borderGreen"/>
				</td>
			</tr>
			<tr>
				<td>身高(cm)</td>
				<td><input type="number" id="Height" name="Height" value="${data.examBody.Height}" class="w40"/></td>
				<td>体质指数<span class="bluecolor">?</span></td>
				<td><input type="number" name="Bmi" id="Bmi" value="${data.examBody.Bmi}" class="w40"/> (小鱼18.5为过轻，大于等于25为超重)</td>
			</tr>
			<tr>
				<td>心率</td>
				<td>
					<input type="number" name="HeartRate" id="HeartRate" value="${data.examBody.HeartRate}" class="w40"/>
					<td>/</td>
					<td>/</td>
				</td>
			</tr>
			<tr >
				<td rowspan='3'>其他</td>
				<td>视力</td>
				<td colspan="3">左眼 <input type="number" name="LeftEye" id="LeftEye" value="${data.examOrgan.LeftEye }"  class="w40"/>
						 右眼 <input type="number" name="RightEye" id="RightEye" value="${data.examOrgan.RightEye }"  class="w40"/> 
						 (矫正视力 左眼 <input type="number" name="LeftEyeVc" id="LeftEyeVc" value="${data.examOrgan.LeftEyeVc }"  class="w40"/>
						 右眼 <input type="number" name="RightEyeVc" id="RightEyeVc" value="${data.examOrgan.RightEyeVc }"  class="w40"/> )
				</td>
			</tr> 
			<tr>
				<td>听力</td>
				<td colspan="3"><a onClick="listening(this)" value="1" class="option">1 听得见  </a><a onClick="listening(this)" value="2" class="option"> 2 听不清或无法听见 </a> 
					<span class="fr"><input type="number" name="Hearing" id="Hearing" value="${data.examOrgan.Hearing }"  class="w20"/> </span>
				</td>
			</tr>
			<tr>
				<td>运动能力</td>
				<td colspan="3"><a onClick="exercise(this)" value="1" class="option">1 可顺利完成  </a><a onClick="exercise(this)" value="2" class="option"> 2 无法独立完成其中任何一个动作 </a>   
					<span class="fr"><input type="number" name="MotorFunction" id="MotorFunction" value="${data.examOrgan.MotorFunction }"  class="w20"/> </span>
				</td>
			</tr>
			<tr>
				<td rowspan="4">
					生活方式  <span class="bluecolor">?</span>
				</td>
				<td>
					日吸烟量(支) <span class="bluecolor">?</span>
				</td>
				<td>
					<input type="number" name="Smoking" id="Smoking" value="${data.examLifestyle.Smoking }" class="w40"/>/<input type="number" name="NextSmoking" id="NextSmoking" value="${data.cmHypertension.NextSmoking }" class="w40 borderGreen"/>
				</td>
				<td>日饮酒量(两) <span class="bluecolor">?</span></td>
				<td>
					<input type="number" name="DailyAlcoholIntake" id="DailyAlcoholIntake" value="${data.examLifestyle.DailyAlcoholIntake}" class="w40"/>/<input type="number" name="NextDailyAlcohol" id="NextDailyAlcohol" value="${data.cmHypertension.NextDailyAlcohol }" class="w40 borderGreen"/>
				</td>
			</tr>
			<tr>
				<td rowspan="2">运动 <span class="bluecolor">?</span></td>
				<td>
					<input type="number" name="ExerciseWeekTimes" id="ExerciseWeekTimes" value="${data.examLifestyle.ExerciseWeekTimes }"  class="w40"/> 次/周 下次 <input type="number" name="NextExerciseWeekTimes" id="NextExerciseWeekTimes" value="${data.cmHypertension.NextExerciseWeekTimes }"  class="w40 borderGreen"/>次/周
				</td>
				<td rowspan="2">摄盐情况 <span class="bluecolor">?</span></td>
				<td rowspan="2">
				<a onClick="frontSalt(this)" value="1">1轻  </a><a onClick="frontSalt(this)" value="2">2中  </a><a onClick="frontSalt(this)" value="3">3重/ </a><a onClick="backSalt(this)" value="1">1轻  </a><a onClick="backSalt(this)" value="2">2中  </a><a onClick="backSalt(this)" value="3">3重</a> 
					<span class="fr"> <input type="number" name="SaltIntake" id="SaltIntake" value="${data.cmHypertension.SaltIntake }" class="w20"/>/<input type="number" name="NextSaltIntake" id="NextSaltIntake" value="${data.cmHypertension.NextSaltIntake }" class="w20"/></span>
				</td>
			</tr>
			<tr>
				<td>
					<input type="number" name="EachExerciseTime" id="EachExerciseTime" value="${data.examLifestyle.EachExerciseTime }"  class="w40"/> 分钟/次 下次 <input type="number" name="NextExerciseWeekMinute" id="NextExerciseWeekMinute" value="${data.cmHypertension.NextExerciseWeekMinute }" class="w40 borderGreen" />分钟/次
				</td>
			</tr>
			<tr>
				 
				<td>心理调整 <span class="redcolor">?</span></td>
				<td colspan="">
				    <a onClick="adjust(this)" value="1">1 良好  </a>
				    <a onClick="adjust(this)" value="2">2 一般  </a>
				    <a onClick="adjust(this)" value="3">3 差</a>
					<span class=""> <input type="number" name="PsychologicalAdjustment" id="PsychologicalAdjustment" value="${data.cmHypertension.PsychologicalAdjustment }" class="w20"/></span>
				</td>
				<td>遵医行为 <span class="redcolor">?</span></td>
				<td colspan="">
				    <a onClick="medicalCompliance(this)" value="1">1 良好  </a>
				    <a onClick="medicalCompliance(this)" value="2">2 一般  </a>
				    <a onClick="medicalCompliance(this)" value="3">3 差</a>
					 
					<span class=""> <input type="number" name="ComplianceBehavior" id="ComplianceBehavior" value="${data.cmHypertension.ComplianceBehavior }" class="w20"/></span>
				</td>
			   
		<form   id="LaboraForm" >
		 	<tr>
				<td>辅助检查 <span class="bluecolor">?</span><span class="redcolor">*</span></td>
				<td>
					<select name="" class="assist_check">
						<option value="">---请选择---</option>
					</select>
				</td>
				<td colspan="4" class="assist_td">
					<fieldset id="" class="xiong" data-to="0">
						<legend>
							<a href="javascript:void(0)">删除X</a>
							胸部X线片：
							
						</legend>
						胸部X线片：
						<select name="ChestXRay" id="ChestXRay">
						<option>请选择</option>
								<option value="1" id="cr1" >正常</option>
								<option value="2" id="cr2" >异常</option>								 
							<!-- 	<option value="2"  selected="true" >异常</option>	 -->							 
						</select>
						异常信息:<input type="text" name="ChestXRayReason" id="ChestXRayReason"  /><%-- value="${data.examLaboratory.ChestXRay }" --%>
					</fieldset>
					
					<fieldset id="" data-to="1">
						<legend>
							<a href="">删除X</a>
							肾功能 :
						</legend>
						血清肌酐：<input type="text" name="SerumCreatinine" id="SerumCreatinine" value="${data.examLaboratory.SerumCreatinine }" />μmol/L 
						血尿素氮：<input type="text" name="Bun" id="Bun" value="${data.examLaboratory.Bun }" />mmol/L  <br />
						L血钾浓度：<input type="text" name="PotassiumConcentration" id="PotassiumConcentration" value="${data.examLaboratory.PotassiumConcentration }" />mmol/L 
						L血钠浓度：<input type="text" name="SodiumConcentration" id="SodiumConcentration" value="${data.examLaboratory.SodiumConcentration }" />mmol/L 
					</fieldset>
					<fieldset id="" data-to="2">
						<legend>
							<a href="/">删除X</a>
							血常规：
						</legend>
						血红蛋白：<input type="text" name="Hemoglobin" id="Hemoglobin" value="${data.examLaboratory.Hemoglobin}" />g/L
						白细胞：<input type="text" name="Leukocyte" id="Leukocyte" value="${data.examLaboratory.Leukocyte }" />×10^9/L <br />
						血小板：<input type="text" name="Platelet" id="Platelet" value="${data.examLaboratory.Platelet }" />×10^9/L
						其他：<input type="text" name="OtherBlood" id="OtherBlood" value="${data.examLaboratory.OtherBlood }" />
						
					</fieldset>
					<fieldset id="" data-to="3">
						<legend>
							<a href="/">删除X</a>
							心电图：
						</legend>
						心电图：<%-- <input type="text" name="Ecg" id="Ecg" value="${data.examLaboratory.Ecg }" /> --%>
						<select name="Ecg" id="Ecg">
							<option value="1" id="ecg1">正常</option>
							<option value="2" id="ecg2">异常</option>
						</select>
						异常信息:<input type="text" name="EcgReason" id="EcgReason"   />
					</fieldset>
					<fieldset id="" data-to="4">
						<legend>
							<a href="/">删除X</a>
							尿常规：
						</legend>
						尿蛋白：<input type="text" name="UrineProtein" id="UrineProtein" value="${data.examLaboratory.UrineProtein }" />
						尿糖：<input type="text" name="Urine" id="Urine" value="${data.examLaboratory.Urine }" /><br />
						尿酮体：<input type="text" name="Ketone" id="Ketone" value="${data.examLaboratory.Ketone }" />
						尿潜血：<input type="text" name="OccultBloodInUrine" id="OccultBloodInUrine" value="${data.examLaboratory.OccultBloodInUrine }" /><br />
						其他：<input type="text" name="OtherUrine" id="OtherUrine" value="${data.examLaboratory.OtherUrine }" />
					</fieldset>
					<fieldset id="" data-to="5">
						<legend>
							<a href="/">删除X</a>
							宫颈涂片：
						</legend>
						宫颈涂片：<%-- <input type="text" name="CervicalSmear" id="CervicalSmear" value="${data.examLaboratory.CervicalSmear }" /> --%>
						<select name="CervicalSmear" id="CervicalSmear">
							<option value="0" id="cs0">未选择</option>
							<option value="1" id="cs1">正常</option>
							<option value="2" id="cs2">异常</option>
						</select>
						异常信息:<input type="text" name="CervicalSmearReason" id="CervicalSmearReason"  />
					</fieldset>
					<fieldset id="" data-to="6">
						<legend>
							<a href="/">删除X</a>
							糖化血红蛋白：
						</legend>
						糖化血红蛋白：<input type="text" name="GlycatedHemoglobin" id="GlycatedHemoglobin" value="${data.examLaboratory.GlycatedHemoglobin }" />%
					</fieldset>
					
					<fieldset id="" data-to="7">
						<legend>
							<a href="/">删除X</a>
							空腹血糖：
						</legend>
						空腹血糖：<input type="text" name="FastingBloodGlucose" id="FastingBloodGlucose" value="${data.examLaboratory.FastingBloodGlucose }" />mmol/L
					</fieldset>
					
					
					<fieldset id="" data-to="8">
						<legend>
							<a href="/">删除X</a>
							尿微量白蛋白：
						</legend>
						尿微量白蛋白：<input type="text" name="UrinaryAlbumin" id="UrinaryAlbumin" value="${data.examLaboratory.UrinaryAlbumin }" />mg/dL
					</fieldset>
					 
					<fieldset id="" data-to="9">
						<legend>
							<a href="/">删除X</a>
							乙型肝炎表面抗原：
						</legend>
						乙型肝炎表面抗原：<input type="text" name="HepatitisBSurfaceAntigen" id="HepatitisBSurfaceAntigen" value="${data.examLaboratory.HepatitisBSurfaceAntigen }" />
					</fieldset>

					<fieldset id="" data-to="10">
						<legend>
							<a href="/">删除X</a>
							肝功能：
						</legend>
						血清谷丙转氨酶：<input type="text" name="SerumAla" id="SerumAla" value="${data.examLaboratory.SerumAla }" />U/L
						血清谷草转氨酶：<input type="text" name="SerumAa" id="SerumAa" value="${data.examLaboratory.SerumAa }" />U/L <br />
						白蛋白：<input type="text" name="Albumin" id="Albumin" value="${data.examLaboratory.Albumin }" />g/L
						总胆红素：<input type="text" name="TotalBilirubin" id="TotalBilirubin" value="${data.examLaboratory.TotalBilirubin }" />μmol/L <br />
						结合胆红素：<input type="text" name="Bilirubin" id="Bilirubin" value="${data.examLaboratory.Bilirubin }" />μmol/L
					</fieldset>
					<fieldset id="" data-to="11">
						<legend> 
							<a href="/">删除X</a>
							B超：
						</legend>
						B超：<%-- <input type="text" name="BUltrasonicWave" id="BUltrasonicWave" value="${data.examLaboratory.BUltrasonicWave }" /> --%>
						<select name="BUltrasonicWave" id="BUltrasonicWave">
							<option value="1" id="bw1">正常</option>
							<option value="2" id="bw2">异常</option>
						</select>
						异常信息:<input type="text" name="BUltrasonicWaveReason" id="BUltrasonicWaveReason" />
					</fieldset>
					<fieldset id="" data-to="12">
						<legend>
							<a href="/">删除X</a>
							大便潜血：
						</legend>
						大便潜血：<%-- <input type="text" name="FecalOccultBlood" id="FecalOccultBlood" value="${data.examLaboratory.FecalOccultBlood }" />请填写1或2，1是阴性2是阳性 --%>
						<select name="FecalOccultBlood" id="FecalOccultBlood">
							<option value="1" id="fob1">阴性</option>
							<option value="2" id="fob2">阳性</option>
						</select>
					</fieldset>
					<fieldset id="" data-to="13">
						<legend>
							<a href="/">删除X</a>
							血脂：
						</legend>
						总胆固醇：<input type="text" name="TotalCholesterol" id="TotalCholesterol" value="${data.examLaboratory.TotalCholesterol }" />
						甘油三酯：<input type="text" name="Triglycerides" id="Triglycerides" value="${data.examLaboratory.Triglycerides }" /> <br />
						血清低密度脂蛋白胆固醇：<input type="text" name="LdlCholesterol" id="LdlCholesterol" value="${data.examLaboratory.LdlCholesterol }" />
						血清高密度脂蛋白胆固醇：<input type="text" name="HdlCholesterol" id="HdlCholesterol" value="${data.examLaboratory.HdlCholesterol }" />
					</fieldset>
					
					<fieldset id="" data-to="14">
						<legend>
							<a href="/">删除X</a>
							其他：
						</legend>
						其他：<input type="text" name="OtherLaboratory" id="OtherLaboratory" value="${data.examLaboratory.OtherLaboratory }" />
					</fieldset>
				</td>
			</tr>
		</form>
			
			
			
			<tr>
				<td>服药依从性  <span class="bluecolor">?</span></td>
				<td>
				    <a onClick="takeMedicine(this)" value="1">1 规律   </a>
				    <a onClick="takeMedicine(this)" value="2">2 间断  </a>
				    <a onClick="takeMedicine(this)" value="3">3 不服药 </a>   <span class="fr"><input type="number" name="MedicationCompliance" id="MedicationCompliance" value="${data.cmHypertension.MedicationCompliance }" class="w20"/></span></td>
				<td>药物不良反应 <span class="bluecolor">?</span></td>
				<td colspan="3"> <a onClick="untowardEffect(this)" value="1">1 无   </a>
				    <a onClick="untowardEffect(this)" value="2">2 有  </a> <input type="text" name="AdverseDrugReactionsReason" id="AdverseDrugReactionsReason" value=""  class="borderb"/>
				<span class="fr"><input type="number" name="AdverseDrugReactions" id="AdverseDrugReactions" value="${data.cmHypertension.AdverseDrugReactions }"  class="w20"/></span>
				</td>
			</tr>
			<tr>
				<td>此次随访分类 <span class="bluecolor">?</span></td>
				<td colspan="2">
				    <a onClick="followUpClassify(this)" value="1">1 控制满意  </a>
				    <a onClick="followUpClassify(this)" value="2">2 控制不满意  </a>
				    <a onClick="followUpClassify(this)" value="3">3 不良反应  </a>
				    <a onClick="followUpClassify(this)" value="4">4 并发症 </a>
					 
					<span class="fr"><input type="number" name="FuClassification" id="FuClassification" value="${data.cmHypertension.FuClassification }"  class="w20"/></span>
				</td>
				<td>/</td>
				<td>/</td>
			</tr>
			<tr >
				<td rowspan="1">用药情况  <span class="bluecolor">?</span> <br /><a href="javascript:void(0)" class="add_drug">增加</a></td>
				<!--增加药品的td-->
				<td colspan="4" class="add_drug_td">				 
				</td> 
			</tr>
			<tr>
				<td rowspan="2">
					转诊 <span class="bluecolor">?</span>  <br />
					<a id="transferTreatment" class="chosePerson">转诊单</a>
				</td>
				<td>原因</td>
				<td colspan="3"><input id="personReason"  /> </td>
			</tr>
			<tr>
				<td>机构及科别</td>
				<td colspan="3"><input id="ahosp"  /></td>
			</tr>
			<tr>
				<td>随访结局</td>
				<td colspan="4"><input  name="FollowUpRemarks" id="FollowUpRemarks" value="${data.cmHypertension.FollowUpRemarks }"  /></td>
			</tr>
			
			<tr>
				<td>下次随访时间</td>
				<td ><input  name="NextFollowUpDate" id="NextFollowUpDate"   /> </td>
				<td>随访医生</td>
				<td><a  id="DoctorName" class="chooseDoctor" href="javascript:void(0)">单击选择医生</a>
				    <input type="hidden" id="DoctorID"/></td>
				<td>/</td>
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
$(".chooseDoctor").on('click',function(){
	 layer.open({
	  type: 2,
	  title: '请选择医生',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['700px', '600px'],  
	  content: '/fdoctor/views/mobile/hypertension/chooseDoctor.jsp '  
	});
 
	return false;
}) 
 
  
 
 

//保存
$("#save").click(function(){
	var saveParam = {};
	saveParam.ProductCode = "${ProductCode}"
	var CmHyper = {};
	var Body = {};
	//var OrgID="1"
	var Other = [];
	var otherMsg = {}
	var Labora = {}
	var Organ = {}
	var Transout = {}
	var Lifestyle = {}
	Transout["TranoutReasons"]=$("#personReason").val();
	Transout["TargetOrgName"]=$("#ahosp").val();
	saveParam.Transout=Transout;
	//saveParam['OrgID']=OrgID
 	/* Labora["ID"]="${data.examLaboratory.ID}"; */
 	Labora["ChestXRay"]=$("#ChestXRay").val()+ '\u0001'+$("#ChestXRayReason").val();
	Labora["SerumCreatinine"]=$("#SerumCreatinine").val();
	Labora["Bun"]=$("#Bun").val(); 
	Labora["PotassiumConcentration"]=$("#PotassiumConcentration").val(); 
	Labora["SodiumConcentration"]=$("#SodiumConcentration").val(); 
	Labora["Hemoglobin"]=$("#Hemoglobin").val(); 
	Labora["Leukocyte"]=$("#Leukocyte").val(); 
	Labora["Platelet"]=$("#Platelet").val(); 
	Labora["OtherBlood"]=$("#OtherBlood").val(); 
	Labora["Ecg"]=$("#Ecg").val()+ '\u0001'+$("#EcgReason").val(); 
	Labora["UrineProtein"]=$("#UrineProtein").val(); 
	Labora["Urine"]=$("#Urine").val(); 
	Labora["Ketone"]=$("#Ketone").val(); 
	Labora["OccultBloodInUrine"]=$("#OccultBloodInUrine").val(); 
	Labora["OtherUrine"]=$("#OtherUrine").val(); 
	Labora["CervicalSmear"]=$("#CervicalSmear").val()+ '\u0001'+$("#CervicalSmearReason").val(); 
	Labora["GlycatedHemoglobin"]=$("#GlycatedHemoglobin").val(); 
	Labora["FastingBloodGlucose"]=$("#FastingBloodGlucose").val(); 
	Labora["UrinaryAlbumin"]=$("#UrinaryAlbumin").val(); 
	Labora["HepatitisBSurfaceAntigen"]=$("#HepatitisBSurfaceAntigen").val(); 
	Labora["SerumAla"]=$("#SerumAla").val(); 
	Labora["SerumAa"]=$("#SerumAa").val(); 
	Labora["Albumin"]=$("#Albumin").val(); 
	Labora["TotalBilirubin"]=$("#TotalBilirubin").val(); 
	Labora["Bilirubin"]=$("#Bilirubin").val(); 
	Labora["BUltrasonicWave"]=$("#BUltrasonicWave").val()+ '\u0001'+$("#BUltrasonicWaveReason").val(); 
	Labora["FecalOccultBlood"]=$("#FecalOccultBlood").val(); 
	Labora["TotalCholesterol"]=$("#TotalCholesterol").val(); 
	Labora["Triglycerides"]=$("#Triglycerides").val(); 
	Labora["LdlCholesterol"]=$("#LdlCholesterol").val(); 
	Labora["HdlCholesterol"]=$("#HdlCholesterol").val(); 
	Labora["OtherLaboratory"]=$("#OtherLaboratory").val(); 
 
	
	var SymptomOther=$("#SymptomOther").val();
	if(SymptomOther!=null&&SymptomOther!=""){
		var otherMsg = {}
		otherMsg.AttrName = "SymptomOther";
		otherMsg.OtherText = $("#SymptomOther").val();
		Other.push(otherMsg);
	}
	var AdverseDrugReactionsReason =$("#AdverseDrugReactionsReason").val();
	if(AdverseDrugReactionsReason!=null&&AdverseDrugReactionsReason!=""){
		var otherMsg = {}
		otherMsg.AttrName = "AdverseDrugReactionsReason";
		otherMsg.OtherText = $("#AdverseDrugReactionsReason").val();
		Other.push(otherMsg);
	}
	var ChestXRayReason=$("#ChestXRayReason").val();
	if(ChestXRayReason!=null&&ChestXRayReason!=""){
		var otherMsg = {}
		otherMsg.AttrName = "ChestXRayReason";
		otherMsg.OtherText = $("#ChestXRayReason").val();
		Other.push(otherMsg);
	}
	var EcgReason =$("#EcgReason").val();
	if(EcgReason!=null&&EcgReason!=""){
		var otherMsg = {}
		otherMsg.AttrName = "EcgReason";
		otherMsg.OtherText = $("#EcgReason").val();
		Other.push(otherMsg);
	}
	var CervicalSmearReason=$("#CervicalSmearReason").val();
	if(CervicalSmearReason!=null&&CervicalSmearReason!=""){
		var otherMsg = {}
		otherMsg.AttrName = "CervicalSmearReason";
		otherMsg.OtherText = $("#CervicalSmearReason").val();
		Other.push(otherMsg);
	}
	var BUltrasonicWaveReason=$("#BUltrasonicWaveReason").val();
	if(BUltrasonicWaveReason!=null&&BUltrasonicWaveReason!=""){
		var otherMsg = {}
		otherMsg.AttrName = "BUltrasonicWaveReason";
		otherMsg.OtherText = $("#BUltrasonicWaveReason").val();
		Other.push(otherMsg);
	}
	
	Body["RightSbp"]=$("#RightSbp").val();
	Body["RightDbp"]=$("#RightDbp").val();
	Body["Weight"]=$("#Weight").val();
	Body["Height"]=$("#Height").val();
	Body["Bmi"]=$("#Bmi").val();
	Body["HeartRate"]=$("#HeartRate").val();
	
	Organ["LeftEye"]=$("#LeftEye").val();
	Organ["RightEye"]=$("#RightEye").val();
	Organ["LeftEyeVc"]=$("#LeftEyeVc").val();
	Organ["RightEyeVc"]=$("#RightEyeVc").val();
	Organ["Hearing"]=$("#Hearing").val();
	Organ["MotorFunction"]=$("#MotorFunction").val();
	
	
	//Lifestyle["ID"]="${data.examLifestyle.ID}";
	Lifestyle["Smoking"]=$("#Smoking").val();
	Lifestyle["DailyAlcoholIntake"]=$("#DailyAlcoholIntake").val();
	Lifestyle["ExerciseWeekTimes"]=$("#ExerciseWeekTimes").val();
	Lifestyle["EachExerciseTime"]=$("#EachExerciseTime").val();
	
	CmHyper["ID"] =""; 
	CmHyper["PersonID"] ="${baseData.ID}";
	CmHyper["FollowUpDate"] = $("#FollowUpDate").val();
	CmHyper["WayUp"] = parseInt($("#WayUp").val());//$("#WayUp").val();
	CmHyper["NextWeight"] = $("#NextWeight").val();//parseFloat($("#NextWeight").val());//
	CmHyper["NextSmoking"] = $("#NextSmoking").val();
	CmHyper["NextDailyAlcohol"] = $("#NextDailyAlcohol").val();
	CmHyper["NextExerciseWeekTimes"] = $("#NextExerciseWeekTimes").val();
	CmHyper["NextExerciseWeekMinute"] = $("#NextExerciseWeekMinute").val();
	CmHyper["SaltIntake"] =parseInt($("#SaltIntake").val());  //$("#SaltIntake").val();
	CmHyper["NextSaltIntake"] =parseInt($("#NextSaltIntake").val());// $("#NextSaltIntake").val();
	CmHyper["PsychologicalAdjustment"] =parseInt($("#PsychologicalAdjustment").val());  //$("#PsychologicalAdjustment").val();
	CmHyper["ComplianceBehavior"] =parseInt($("#ComplianceBehavior").val()); //$("#ComplianceBehavior").val();
	CmHyper["MedicationCompliance"] =parseInt($("#MedicationCompliance").val());// $("#MedicationCompliance").val();
	CmHyper["AdverseDrugReactions"] =parseInt($("#AdverseDrugReactions").val());// $("#AdverseDrugReactions").val();
	CmHyper["FuClassification"] =parseInt($("#FuClassification").val());// $("#FuClassification").val();
	CmHyper["FollowUpRemarks"] = $("#FollowUpRemarks").val();
	CmHyper["NextFollowUpDate"] = $("#NextFollowUpDate").val();
	CmHyper["DoctorName"] = $("#DoctorName").text();
	CmHyper["DoctorID"] =  $("#DoctorID").val() ;//必传参数
	//CmHyper["UserID"] =  $("#DoctorID").val() ;//有待更改
	CmHyper["UserID"] = '${UserID}';
 
	
	var SymptomSum=0;
	var s0=$("#Symptom0").val()
	if(s0!=null&&s0!=""){
		SymptomSum+=1;
	}
	var s1=$("#Symptom1").val()
	if(s1!=null&&s1!=""){
		SymptomSum+=2;
	}
	var s2=$("#Symptom2").val()
	if(s2!=null&&s2!=""){
		SymptomSum+=4;
	}
	var s3=$("#Symptom3").val()
	if(s3!=null&&s3!=""){
		SymptomSum+=8;
	}
	var s4=$("#Symptom4").val()
	if(s4!=null&&s4!=""){
		SymptomSum+=16;
	}
	var s5=$("#Symptom5").val()
	if(s5!=null&&s5!=""){
		SymptomSum+=32;
	}
	
	var s6=$("#Symptom6").val()
	if(s6!=null&&s6!=""){
		SymptomSum+=64;
	}
	var s7=$("#Symptom7").val()
	if(s7!=null&&s7!=""){
		SymptomSum+=128;
	}
	var s8=$("#Symptom8").val()
	if(s8!=null&&s8!=""){
		SymptomSum+=256;
	}
	var s9=$("#Symptom9").val()
	if(s9!=null&&s9!=""){
		SymptomSum+=512;
	}
	var Drug =[];
	
	CmHyper["Symptom"] = SymptomSum;
	//alert(SymptomSum)
	saveParam.CmHyper=CmHyper;
	saveParam.Other=Other;
	saveParam.Body=Body;
	saveParam.Labora=Labora;
	saveParam.Lifestyle=Lifestyle;
	//saveParam.Drug=Drug;
	saveParam.Organ=Organ;
	var drugName = $("input[name='DrugName']");
    var dailyTimes = $("input[name='DailyTimes']");
    var EachDose = $("input[name='EachDose']");
    var Remark = $("select[name='Remark']");  
   
    var Remark1 = $("input[name='Remark1']");
    for(var i=0;i<drugName.length;i++){
    	var sonDrug ={};
    	sonDrug.CmDrugID="null";
    	sonDrug.CmDrugName=drugName[i].value;
    	sonDrug.DailyTimes=dailyTimes[i].value;
    	sonDrug.EachDose=EachDose[i].value;
    	var index = Remark[i].selectedIndex
    	sonDrug.Remark= Remark[i].options[index].value;
    	sonDrug.Remark1=Remark1[i].value;
    	Drug.push(sonDrug);
    }
   // $('#testSelect option:selected') .val()
	saveParam.Drug=Drug;
	/* $('#save').attr("href", "/fdoctor/mobile/hypertension/updateHypertensionFollowUpRecord?saveParam="+JSON.stringify(saveParam)); */

	 if(window.confirm('是否保存？')){
		console.debug(saveParam);
		$.ajax({
		    type: 'POST',
		    url: '/fdoctor/mobile/hypertension/updateHypertensionFollowUpRecord' ,
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
})
//处理此次随访分类
function followUpClassify(tr){
	var value=tr.getAttribute('value');
	$("#FuClassification").val(value)
}
//处理药物不良反应
function untowardEffect(tr){
	var value=tr.getAttribute('value');
	$("#AdverseDrugReactions").val(value)
}
//听力处理
function listening(tr){
	var value=tr.getAttribute('value');
	$("#Hearing").val(value)
}
//运动能力处理
function exercise(tr){
	var value=tr.getAttribute('value');
	$("#MotorFunction").val(value)
}
//处理服药依从性
function takeMedicine(tr){
	var value=tr.getAttribute('value');
	$("#MedicationCompliance").val(value)
}
//处理遵医行为
function medicalCompliance(tr){
	var value=tr.getAttribute('value');
	$("#ComplianceBehavior").val(value)		
}
//随访方式处理
function followUp(tr){
	var value=tr.getAttribute('value');
	$("#WayUp").val(value)	
	 	
}
//心理调整
function adjust(tr){
	var value=tr.getAttribute('value');
	$("#PsychologicalAdjustment").val(value)	
}
//摄盐处理
function frontSalt(tr){
	var value=tr.getAttribute('value');
	$("#SaltIntake").val(value)
}
function backSalt(tr){
	var value=tr.getAttribute('value');
	$("#NextSaltIntake").val(value)
}
//症状处理
function ChangSelect(tr){
	//alert(tr.getAttribute('value'))
	var value=tr.getAttribute('value');
	//先判断先前是否选中
	var id =tr.getAttribute('id');
	 
	if(!($("#"+id).data('abc'))){
		if(value==1){
			$("#"+id).data('abc','1')
			/* var id =tr.getAttribute('id');
			$("#"+id).data('abc',"1"); */
			 
			$("#Symptom"+0).val(1)
			$("#Symptom"+1).val("")
			$("#Symptom"+2).val("")
			$("#Symptom"+3).val("")
			$("#Symptom"+4).val("")
			$("#Symptom"+5).val("")
			$("#Symptom"+6).val("")
			$("#Symptom"+7).val("")
			$("#Symptom"+8).val("")
			$("#Symptom"+9).val("")
			 
		}else if(value==2){
			$("#"+id).data('abc','2')
			$("#Symptom"+1).val(2)
			$("#Symptom"+0).val("")
		}else if(value==4){
			$("#"+id).data('abc','3')
			$("#Symptom"+2).val(3)
			$("#Symptom"+0).val("")
		}else if(value==8){
			$("#"+id).data('abc','4')
			$("#Symptom"+3).val(4)
			$("#Symptom"+0).val("")
		}else if(value==16){
			$("#"+id).data('abc','5')
			$("#Symptom"+4).val(5)
			$("#Symptom"+0).val("")
		}else if(value==32){
			$("#"+id).data('abc','6')
			$("#Symptom"+5).val(6)
			$("#Symptom"+0).val("")
		}else if(value==64){
			$("#"+id).data('abc','7')
			$("#Symptom"+6).val(7)
			$("#Symptom"+0).val("")
		}else if(value==128){
			$("#"+id).data('abc','8')
			$("#Symptom"+7).val(8)
			$("#Symptom"+0).val("")
		}else if(value==256){
			$("#"+id).data('abc','9')
			$("#Symptom"+8).val(9)
			$("#Symptom"+0).val("")
		}else if(value==512){
			$("#"+id).data('abc','10')
			$("#Symptom"+9).val(10)
			$("#Symptom"+0).val("")
		}  
	}else{
		if(value==1){
			//var id =tr.getAttribute('id');
			$("#"+id).data('abc','')
			 
			$("#Symptom"+0).val("")
			 
			 
		}else if(value==2){
			$("#"+id).data('abc','')
			$("#Symptom"+1).val("")
		}else if(value==4){
			$("#"+id).data('abc','')
			$("#Symptom"+2).val("")
		}else if(value==8){
			$("#"+id).data('abc','')
			$("#Symptom"+3).val("")
		}else if(value==16){
			$("#"+id).data('abc','')
			$("#Symptom"+4).val("")
		}else if(value==32){
			$("#"+id).data('abc','')
			$("#Symptom"+5).val("")
		}else if(value==64){
			$("#"+id).data('abc','')
			$("#Symptom"+6).val("")
		}else if(value==128){
			$("#"+id).data('abc','')
			$("#Symptom"+7).val("")
		}else if(value==256){
			$("#"+id).data('abc','')
			$("#Symptom"+8).val("")
		}else if(value==512){
			$("#"+id).data('abc','')
			$("#Symptom"+9).val("")
		}  
	} 
	
}

	 
	
	$(function(){
		 /*  var FollowUpDate = "${data.cmHypertension.FollowUpDate }";
		  var timeSpan = FollowUpDate.replace('/Date','').replace('(','').replace(')/','').replace(/\//g,'');
		  var d = new Date(parseInt(timeSpan));
		   d= d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
		  $("#FollowUpDate").val(d);
		  
		  var NextFollowUpDate= "${data.cmHypertension.NextFollowUpDate }";
		  var nexttimeSpan = NextFollowUpDate.replace('/Date','').replace('(','').replace(')/','').replace(/\//g,'');
		  var nd = new Date(parseInt(nexttimeSpan));
		   nd= nd.getFullYear() + '-' + (nd.getMonth() + 1) + '-' + nd.getDate();
		  $("#NextFollowUpDate").val(nd); */
		   
		 
		
		 
	})
	
 
	 
	 
	 

    
    //增加药品

	$(".add_drug").on('click',function(){
		var addrug='<div class=""><input type="hidden" name="ID" value="">'+
		'						药品名称： <input type="text" name="DrugName"   value="" />'+
		'						每日： <input type="text" name="DailyTimes"   value="" class="w20"/> 次'+
		'				'+
		'						每次：<input type="text" name="EachDose"   value="" class="w20"/> '+
		'						<select name="Remark">'+
		'							<option value="mg"  >mg</option>'+
		'							<option value="ml" >ml</option>'+
		'							<option value="g"  >g</option>'+
		'							<option value="片" >片</option>'+
		'							<option value="粒"  >粒</option>'+
		'							<option value="袋"  >袋</option>'+
		'							<option value="瓶"  >瓶</option>'+
		'							<option value="支"  >支</option>'+
		'							<option value="盒"  >盒</option>'+
		'							<option value="U"  >U</option>'+
		'						</select>'+
		'						<input type="text" name="Remark1"  value=""  class="borderb"/ > '+
		'						<a href="javascript:void(0)"  class="delete_drug" onclick="delete_drug(this)"> 删除</a><br />'+
		'					</div>';
					$(".add_drug_td").append(addrug);
		/*
		var addrug='<div class="">'+
'						药品名称： <input type="text" name="" id="" value="" />'+
'						每日： <input type="number" name="" id="" value="" class="w20"/> 次'+
'				'+
'						每次：<input type="number" name="" id="" value="" class="w20"/> '+
'						<select name="">'+
'							<option value="">盒</option>'+
'							<option value="">支</option>'+
'							<option value="">片</option>'+
'						</select>'+
'						<input type="text" name="" id="" value=""  class="borderb"/ > '+
'						<a href="javascript:void(0)"  class="delete_drug" onclick="delete_drug(this)"> 删除</a><br />'+
'					</div>';
			$(".add_drug_td").append(addrug);
	*/		
	});
    
   /*  var drugName = $("input[name='DrugName']");
    var dailyTimes = $("input[name='DailyTimes']");
    var EachDose = $("input[name='EachDose']");
    var Remark = $("input[name='Remark']");
    var Remark1 = $("input[name='Remark1']"); */
	//减少药品
	function delete_drug(x){
		$(x).parent('div').remove();
	}
		var options=['胸部X线片','肾功能*','血常规*','心电图','尿常规','宫颈涂片','糖化血红蛋白','空腹血糖','尿微量白蛋白','乙型肝炎表面抗原','肝功能*','B超','大便潜血','血脂*','其他']
		//添加 select 下拉框;每个下拉框增加value值
		for(var i=0;i<options.length;i++){
			$(".assist_check").append($("<option>").text(options[i]).attr("value",i));
		}
		
		
		
		
		$(".assist_check").on('change',function(){
			//索取选中下拉框的value值 ;
			var val =$('.assist_check  option:selected').val();
			
			//遍历检查科目，让value与 data-to相等的做显示;
			$('.assist_td fieldset').each(function(i){
				if($(this).data('to')==val){
					$(this).show()
				}
			})
		});
		
		$('.assist_td fieldset a').on('click',function(){
			$(this).parents('fieldset').hide();
			return false;
		})
		
		//转诊处理
		$(".chosePerson").on('click',function(){
	 		 layer.open({
			  type: 2,
			  title: '新建转诊单记录',
			  shadeClose: true,
			  shade: 0.8,
			 /*  area: ['600px', '400px'], */
			  area: ['800px', '600px'],
			  /* content: 'hypertensionPeoplesearch.jsp'  *//* //iframe的url */
			  content: '/fdoctor/views/mobile/hypertension/transferTreatment.jsp?Name='+encodeURI("${baseData.Name}")+'&PersonCode='+"${baseData.PersonCode}"+'&Gender='+encodeURI("${Gender}")+"&age="+encodeURI("${age}")+"&ResidenceAddress="+"${baseData.ResidenceAddress}"+"&PersonTel="+encodeURI("${PersonTel}") /* //iframe的url */
			});
	 		 //layer.close(index);
			return false;
 	  })
 	  function aparam(person){
			$("#ahosp").val(person.shosp)
			$("#personReason").val(person.sreason)
		}
 	  
	
</script>
</html>
