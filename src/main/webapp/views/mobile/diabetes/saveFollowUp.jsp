<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>糖尿病随访表新建</title>
<link rel="stylesheet" type="text/css"
	href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css"
	href="/fdoctor/statics/css/easyui.css">
<!--引入CSS样式-->
<link rel="stylesheet" type="text/css"
	href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css"
	href="/fdoctor/statics/css/icon.css">
<link rel="stylesheet" type="text/css"
	href="/fdoctor/statics/css/layer.css">
<!--Icon引入-->

<script type="text/javascript"
	src="/fdoctor/statics/js/jquery-2.1.4.min.js"" ></script>
<script type="text/javascript"
	src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/layer.js"></script>
<style type="text/css">
	body{
		background: #f0f3f8;
	}
	
</style>
</head>

<body>
	<div class="main">
	
	 	<div class="dia_hdtable">
			<h1 class="tc">2型糖尿病患者随访服务记录表</h1>
			<table class="mt10">
				<tr>
					<td class="bold">姓名：</td>
					<td>${Name}</td>
					<td class="bold">性别：</td>
					<td>${Gender}</td>
					<td class="bold">年龄：</td>
					<td>${age}岁</td>
				</tr>
				<tr>
					<td class="bold">电话：</td>
					<td>${PersonTel}</td>
					<td class="bold">既往病史：</td>
					<td colspan="3"><span id="disHistory"></span></td>
				</tr>
				<tr>
					<td class="bold">家族史：</td>
					<td>父亲:<span id="mother"></span></td>
					<td>母亲:<span id="father"></span></td>
					<td colspan="2">兄弟姐妹:<span id="sisbro"></span></td>
					<td>子女:<span id="son"></span></td>
				</tr>
				<tr>
					<td class="bold">上次随访时间：</td>
					<td><span id="lastDate"></span></td>
					<td class="bold">上次随访结局：</td>
					<td colspan="3"><span id="lPerfect"></span></td>
				</tr>
				<tr>
					<td class="bold">上次随访血压：</td>
					<td><span id="lRightSbp"></span>/<span id="lRightDbp"></span></td>
					<td class="bold">上次随访心率：</td>
					<td><span id="lHeartRate"></span></td>
				</tr>
				<tr>
					<td rowspan="2" class="bold">上次随访设定目标值：</td>
					<td class="bold">日吸烟量：</td>
					<td><span id="lNEXTSMOKING"></span></td>
					<td class="bold">日饮酒量：</td>
					<td><span id="lNEXTDAILYALCOHOL"></span></td>
	
				</tr>
				<tr>
					<td class="bold">运动：</td>
					<td><span id="lNEXTEXERCISEWEEKTIMES"></span>次/周&nbsp;<span
						id="lNEXTEXERCISEWEEKMINUT">分钟/次</span></td>
					<td class="bold">体重：</td>
					<td><span id="lWeight"></span>(kg)</td>
					<td class="bold">主食：</td>
					<td><span id="lNEXTSTAPLE"></span>(克)</td>
				</tr>
			</table>
		</div>
		<p class="tr mt10 ">
			<span style="color: #ef5a57;">填表说明</span><span style="color: #ef5a57;">(绿色边框为下次目标)</span>
		</p>
		<form>
			<table border="1" class="table2 mt20">
				<tr>
					<td>随访时间</td>
					<td colspan="2"><input type="date" name="FollowUpDate"
						id="FollowUpDate" value="${followUpDate}" class="borderb"/></td>
					<td>随访方式</td>
					<td><a onClick="followUp(this)" value="1" class="option">1 门诊 </a> <a
						onClick="followUp(this)" value="2" class="option">2 家庭 </a> <a
						onClick="followUp(this)" value="3" class="option">3 电话</a> <span class="fr"><input
							type="number" name="WayUp" id="WayUp" value="" class="w20" /></span></td>
				</tr>
				<tr>
					<td>症状</td>
					<td colspan="5" class="tl"><a onClick="ChangSelect(this)" class="Noption"
						href="javascript:void(0)" id="1" value="1">1无症状</a> <a
						onClick="ChangSelect(this)" href="javascript:void(0)" id="2" class="optionMore"
						value="2">2头痛头晕 </a> <a onClick="ChangSelect(this)"
						href="javascript:void(0)" id="3" value="4" class="optionMore">3恶心呕吐</a> <a
						onClick="ChangSelect(this)" href="javascript:void(0)" id="4" class="optionMore"
						value="8">4眼花耳鸣 </a> <a onClick="ChangSelect(this)"
						href="javascript:void(0)" id="5" value="16" class="optionMore">5呼吸困难 </a> <a
						onClick="ChangSelect(this)" href="javascript:void(0)" id="6" class="optionMore"
						value="32">6心悸胸闷 </a> <a onClick="ChangSelect(this)"
						href="javascript:void(0)" id="7" value="64" class="optionMore">7鼻衄</a> <a
						onClick="ChangSelect(this)" href="javascript:void(0)" id="8" class="optionMore"
						value="128">8四肢发麻</a> <a onClick="ChangSelect(this)"
						href="javascript:void(0)" id="9" value="256" class="optionMore">9下肢水肿 </a> <a
						onClick="ChangSelect(this)" href="javascript:void(0)" id="10" class="optionMore"
						value="512">10其他</a> <input type="text" name="SymptomOther"
						id="SymptomOther"  class="border_bottom1"/>
						<div class="clear" style="margin-bottom: 5px;"></div>
						 <span class="fr"> <input
							type="number" name="Symptom0" id="Symptom0" value="" class="w20" />/
							<input type="number" name="Symptom1" id="Symptom1" value=""
							class="w20" /> / <input type="number" name="Symptom2"
							id="Symptom2" value="" class="w20" /> / <input type="number"
							name="Symptom3" id="Symptom3" value="" class="w20" /> / <input
							type="number" name="Symptom4" id="Symptom4" value="" class="w20" />
							/ <input type="number" name="Symptom5" id="Symptom5" value=""
							class="w20" /> / <input type="number" name="Symptom6"
							id="Symptom6" value="" class="w20" /> / <input type="number"
							name="Symptom7" id="Symptom7" value="" class="w20" /> / <input
							type="number" name="Symptom8" id="Symptom8" value="" class="w20" />
							/ <input type="number" name="Symptom9" id="Symptom9" value=""
							class="w20" />

					</span></td>
				</tr>
				<tr>
					<td rowspan="6">体征</td>
					<td>血压(mmHg)</td>
					<td><input name="RightSbp" id="RightSbp" value="" class="w40 borderb" />/<input
						name="RightDbp" id="RightDbp" value="" class="w40 borderb" /></td>
					<td>体重(kg)</td>
					<td><input name="Weight" id="Weight" value="" class="w40 borderb" />/<input
						name="NextWeight" id="NextWeight" value="" class="w40 borderGreen" /></td>
				</tr>
				<tr>
					<td>身高(cm)</td> 
					<td><input id="Height" name="Height" value="" class="w40 borderb" /></td>
					<td>体质指数 <span class="bluecolor" id="help1">?</span></td>
					<td><input name="Bmi" id="Bmi" value="" class="w40 borderb" />
						(小于18.5为过轻，大于等于25为超重)</td>
				</tr>
				<td>足背动脉搏动</td>
				<td colspan=""><a onClick="pulsation(this)" value="1" class="option">1 未触及 
				</a> <a onClick="pulsation(this)" value="2" class="option">2 触及</a> <span class="fr"><input
						type="number" name="DorsalisPedisArteryPulse"
						id="DorsalisPedisArteryPulse" value="}" class="w20" /></span></td>
				<td></td>
				<td></td>
				<tr>
					<td rowspan='3'>其他</td>
					<td>视力</td>
					<td colspan="3">左眼 <input type="number" name="LeftEye"
						id="LeftEye" value="" class="w40 borderb" /> 右眼 <input type="number"
						name="RightEye" id="RightEye" value="" class="w40 borderb" /> (矫正视力 左眼 <input
						type="number" name="LeftEyeVc" id="LeftEyeVc" value="" class="w40 borderb" />
						右眼 <input type="number" name="RightEyeVc" id="RightEyeVc" value=""
						class="w40 borderb" /> )
					</td>
				</tr>
				<tr>
					<td>听力</td>
					<td colspan="3"><a onClick="listening(this)" value="1" class="option">1
							听得见 </a><a onClick="listening(this)" value="2" class="option"> 2 听不清或无法听见 </a> <span
						class="fr"><input type="number" name="Hearing" id="Hearing"
							value="" class="w20" /> </span></td>
				</tr>
				<tr>
					<td>运动能力</td>
					<td colspan="3"><a onClick="exercise(this)" value="1" class="option">1
							可顺利完成 </a><a onClick="exercise(this)" value="2" class="option"> 2 无法独立完成其中任何一个动作
					</a> <span class="fr"><input type="number" name="MotorFunction"
							id="MotorFunction" value="" class="w20" /> </span></td>
				</tr>
				<tr>
					<td rowspan="4">生活方式 <span class="bluecolor" id="help2">?</span>
					</td>
					<td>日吸烟量(支) <span class="bluecolor" id="help3">?</span>
					</td>
					<td><input type="number" name="Smoking" id="Smoking" value=""
						class="w40 borderb" />/<input type="number" name="NextSmoking"
						id="NextSmoking" value="" class="w40 borderGreen" /></td>
					<td>日饮酒量(两) <span class="bluecolor" id="help4">?</span></td>
					<td><input type="number" name="DailyAlcoholIntake"
						id="DailyAlcoholIntake" value="" class="w40 borderb" />/<input
						type="number" name="NextDailyAlcohol" id="NextDailyAlcohol"
						value="" class="w40 borderGreen" /></td>
				</tr>
				<tr>
					<td rowspan="2">运动 <span class="bluecolor" id="help5">?</span></td>
					<td><input type="number" name="ExerciseWeekTimes"
						id="ExerciseWeekTimes" value="" class="w40 borderb" /> 次/周 下次 <input
						type="number" name="NextExerciseWeekTimes"
						id="NextExerciseWeekTimes" value="" class="w40 borderGreen" />次/周</td>
					<td>主食(克/天) <span class="bluecolor" id="help6">?</span></td>
					<td><input type="number" name="Staple" id="Staple" value=""
						class="w40 borderb" />/<input type="number" name="NextStaple"
						id="NextStaple" value="" class="w40 borderGreen" /></td>
				</tr>
				<tr>
					<td><input type="number" name="EachExerciseTime"
						id="EachExerciseTime" value="" class="w40 borderb" /> 分钟/次 下次 <input
						type="number" name="NextExerciseWeekMinute"
						id="NextExerciseWeekMinute" value="" class="w40 borderGreen" />分钟/次</td>
				</tr>
				<tr>

					<td>心理调整 <span class="bluecolor" id="help7">?</span></td>
					<td colspan=""><a onClick="adjust(this)" value="1" class="option">1 良好 </a> <a
						onClick="adjust(this)" value="2" class="option">2 一般 </a> <a
						onClick="adjust(this)" value="3" class="option">3 差</a> <!-- <span class="option">良好</span>
					<span class="option">一般</span>
					<span class="option">差</span> --> <span class=""> <input
							type="number" name="PsychologicalAdjustment"
							id="PsychologicalAdjustment" value="" class="w20" /></span></td>
					<td>遵医行为 <span class="bluecolor" id="help8">?</span></td>
					<td colspan=""><a onClick="medicalCompliance(this)" value="1" class="option">1
							良好 </a> <a onClick="medicalCompliance(this)" value="2" class="option">2 一般 </a> <a
						onClick="medicalCompliance(this)" value="3" class="option">3 差</a> <!-- <span class="option">良好</span>
					<span class="option">一般</span>
					<span class="option">差</span> --> <span class=""> <input
							type="number" name="ComplianceBehavior" id="ComplianceBehavior"
							value="" class="w20" /></span></td>


				</tr>
				<tr>
					<td rowspan="2">辅助检查 <span class="bluecolor" id="help9">?</span><span
						class="redcolor">*</span></td>
					<td>空腹血糖</td>
					<td><input type="number" name="FastingBloodGlucose"
						id="FastingBloodGlucose" value="" class="borderb w40" />mmol/L</td>
					<td>餐后血糖</td>
					<td><input type="number" name="PostprandialBloodGlucose"
						id="PostprandialBloodGlucose" value="" class="borderb w40" />mmol/L</td>
				</tr>
				<tr>
					<td>其他检查<span class="redcolor">*</span>
					</td>
					<td colspan="4" class="assist_td"><select name=""
						class="assist_check">
							<option value="">---请选择---</option>
					</select>
						<fieldset id="" class="xiong" data-to="0">
							<legend>
								<a href="javascript:void(0)">删除X</a> 胸部X线片：
							</legend>
							胸部X线片： <select name="ChestXRay" id="ChestXRay">
								<option>请选择</option>
								<option value="1" id="cr1">正常</option>
								<option value="2" id="cr2">异常</option>
							</select> 异常信息：<input type="text" name="ChestXRayReason"
								id="ChestXRayReason" />
						</fieldset>

						<fieldset id="" data-to="1">
							<legend>
								<a href="">删除X</a> 肾功能 :
							</legend>
							血清肌酐：<input type="text" name="SerumCreatinine"
								id="SerumCreatinine" value="" />μmol/L 血尿素氮：<input type="text" 
								name="Bun" id="Bun" value="" />mmol/L <br /> 血钾浓度：<input
								type="text" name="PotassiumConcentration"
								id="PotassiumConcentration" value="" />mmol/L 血钠浓度：<input
								type="text" name="SodiumConcentration" id="SodiumConcentration"
								value="" />mmol/L
						</fieldset>
						<fieldset id="" data-to="2">
							<legend>
								<a href="/">删除X</a> 血常规：
							</legend>
							血红蛋白：<input type="text" name="Hemoglobin" id="Hemoglobin"
								value="" />g/L 白细胞：<input type="text" name="Leukocyte"
								id="Leukocyte" value="" />×10^9/L <br /> 血小板：<input
								type="text" name="Platelet" id="Platelet" value="" />×10^9/L
							其他：<input type="text" name="OtherBlood" id="OtherBlood" value="" />

						</fieldset>
						<fieldset id="" data-to="3">
							<legend>
								<a href="/">删除X</a> 心电图：
							</legend>
							心电图： <select name="Ecg" id="Ecg">
								<option value="1" id="ecg1">正常</option>
								<option value="2" id="ecg2">异常</option>
							</select> 异常信息:<input type="text" name="EcgReason" id="EcgReason" />
						</fieldset>
						<fieldset id="" data-to="4">
							<legend>
								<a href="/">删除X</a> 尿常规：
							</legend>
							尿蛋白：<input type="text" name="UrineProtein" id="UrineProtein"
								value="" /> 尿糖：<input type="text" name="Urine" id="Urine"
								value="" /><br /> 尿酮体：<input type="text" name="Ketone"
								id="Ketone" value="" /> 尿潜血：<input type="text"
								name="OccultBloodInUrine" id="OccultBloodInUrine" value="" /><br />
							其他：<input type="text" name="OtherUrine" id="OtherUrine" value="" />
						</fieldset>
						<fieldset id="" data-to="5">
							<legend>
								<a href="/">删除X</a> 宫颈涂片：
							</legend>
							宫颈涂片： <select name="CervicalSmear" id="CervicalSmear">
								<option value="0" id="cs0">未选择</option>
								<option value="1" id="cs1">正常</option>
								<option value="2" id="cs2">异常</option>
							</select> 异常信息:<input type="text" name="CervicalSmearReason"
								id="CervicalSmearReason" />
						</fieldset>
						<fieldset id="" data-to="6">
							<legend>
								<a href="/">删除X</a> 糖化血红蛋白：
							</legend>
							糖化血红蛋白：<input type="text" name="GlycatedHemoglobin"
								id="GlycatedHemoglobin" value="" />%
						</fieldset>

						<fieldset id="" data-to="7">
							<legend>
								<a href="/">删除X</a> 尿微量白蛋白：
							</legend>
							尿微量白蛋白：<input type="text" name="GlycatedHemoglobin"
								id="GlycatedHemoglobin" value="" />mg/dL
						</fieldset>

						<fieldset id="" data-to="8">
							<legend>
								<a href="/">删除X</a> 乙型肝炎表面抗原：
							</legend>
							乙型肝炎表面抗原：<input type="text" name="HepatitisBSurfaceAntigen"
								id="HepatitisBSurfaceAntigen" value="" />
						</fieldset>

						<fieldset id="" data-to="9">
							<legend>
								<a href="/">删除X</a> 肝功能：
							</legend>
							血清谷丙转氨酶：<input type="text" name="SerumAla" id="SerumAla" value="" />U/L
							血清谷草转氨酶：<input type="text" name="SerumAa" id="SerumAa" value="" />U/L
							<br /> 白蛋白：<input type="text" name="Albumin" id="Albumin"
								value="" />g/L 总胆红素：<input type="text" name="TotalBilirubin"
								id="TotalBilirubin" value="" />μmol/L <br /> 结合胆红素：<input
								type="text" name="Bilirubin" id="Bilirubin" value="" />μmol/L
						</fieldset>
						<fieldset id="" data-to="10">
							<legend>
								<a href="/">删除X</a> B超：
							</legend>
							B超： <select name="BUltrasonicWave" id="BUltrasonicWave">
								<option value="1" id="bw1">正常</option>
								<option value="2" id="bw2">异常</option>
							</select> 异常信息:<input type="text" name="BUltrasonicWaveReason"
								id="BUltrasonicWaveReason" />
						</fieldset>
						<fieldset id="" data-to="11">
							<legend>
								<a href="/">删除X</a> 大便潜血：
							</legend>
							大便潜血： <select name="FecalOccultBlood" id="FecalOccultBlood">
								<option value="1" id="fob1">阴性</option>
								<option value="2" id="fob2">阳性</option>
							</select>
						</fieldset>
						<fieldset id="" data-to="12">
							<legend>
								<a href="/">删除X</a> 血脂：
							</legend>
							总胆固醇：<input type="text" name="TotalCholesterol"
								id="TotalCholesterol" value="" /> 甘油三酯：<input type="text"
								name="Triglycerides" id="Triglycerides" value="" /> <br />
							血清低密度脂蛋白胆固醇：<input type="text" name="LdlCholesterol"
								id="LdlCholesterol" value="" /> 血清高密度脂蛋白胆固醇：<input type="text"
								name="HdlCholesterol" id="HdlCholesterol" value="" />
						</fieldset>

						<fieldset id="" data-to="13">
							<legend>
								<a href="/">删除X</a> 其他：
							</legend>
							其他：<input type="text" name="OtherLaboratory" id="OtherLaboratory"
								value="" />
						</fieldset></td>
				</tr>
				<tr>
					<td>服药依从性 <span class="bluecolor" id="help10">?</span></td>
					<td colspan="2"><a onClick="takeMedicine(this)" value="1"  class="option">1
							规律 </a> <a onClick="takeMedicine(this)" value="2" class="option">2 间断 </a> <a
						onClick="takeMedicine(this)" value="3" class="option">3 不服药 </a> <span class="fr">
							<input type="number" name="MedicationCompliance"
							id="MedicationCompliance" value="" class="w20" />
					</span></td>
					<td colspan="1">药物不良反应 <span class="bluecolor" id="help11">?</span></td>
					<td colspan="2"><a onClick="untowardEffect(this)" value="1"  class="option">1
							无 </a> <a onClick="untowardEffect(this)" value="2"  class="option">2 有 </a> <input
						type="text" name="" id="" value="" class="borderb" /> <span
						class="fr"><input type="number" name="AdverseDrugReactions"
							id="AdverseDrugReactions" value="" class="w20" /></span></td>
				</tr>
				<tr>
					<td>低血糖反应 <span class="bluecolor" id="help12">?</span></td>
					<td colspan="5"><a onClick="LowBlood(this)" value="1" class="option">1 无
					</a> <a onClick="LowBlood(this)" value="2" class="option">2 偶尔 </a> <a
						onClick="LowBlood(this)" value="3" class="option">3 频繁 </a> <span class="fr"><input
							type="number" name="LowBloodSugarReactions"
							id="LowBloodSugarReactions" value="" class="w20" /></span></td>
				</tr>
				<tr>
					<td>此次随访分类 <span class="bluecolor" id="help13">?</span></td>
					<td colspan="5"><a onClick="followUpClassify(this)" value="1" class="option">1
							控制满意 </a> <a onClick="followUpClassify(this)" value="2" class="option">2 控制不满意 </a>
						<a onClick="followUpClassify(this)" value="3" class="option">3 不良反应 </a> <a
						onClick="followUpClassify(this)" value="4" class="option">4 并发症 </a> <span
						class="fr"><input type="number" name="FuClassification"
							id="FuClassification" value="" class="w20" /></span></td>
				</tr>
				<tr>
					<td rowspan="1">用药情况 <span class="bluecolor" id="help14">?</span> <br />
						<a href="javascript:void(0)" class="add_drug">增加</a></td>
					<!--增加药品的td-->
					<td colspan="4" class="add_drug_td"></td>
				</tr>
				<tr>
					<td rowspan="1">胰岛素 <br /> <a
						href="javascript:void(0)" class="add_insulin">增加</a></td>
					<!--增加药品的td-->
					<td colspan="4" class="add_insulin_td"></td>
				</tr>
				<tr>
					<td rowspan="2">转诊 <span class="bluecolor" id="help15">?</span> <br /> <a href="javascript:void(0)"
						id="transferTreatment" class="chosePerson">转诊单</a>
					</td>
					<td>原因</td>
					<td colspan="3">
						<textarea name="" rows="" cols="" style="width: 96%; height: 100%;border:0;" id="TranoutReasons" value="${data.transout.TranoutReasons}">
							
						</textarea>
					</td>
				</tr>
				<tr>
					<td>机构及科别</td>
					<td colspan="3">
					<!-- <input id="TargetOrgName"> -->
						<textarea name="" rows="" cols="" style="width: 96%; height: 100%;border:0;" id="TargetOrgName" value="${data.transout.TargetOrgName}">
							
						</textarea>
					</td>
				</tr>
				<tr rowspan="3">
					<td>随访结局</td>
					<td colspan="4"><textarea  style="width: 96%; height: 100%;border:0;" name="FollowUpRemarks"
							id="FollowUpRemarks" value=""></textarea></td>
				</tr>

				<tr>
					<td>下次随访时间 <span class="bluecolor" id="help16">?</span></td>
					<td colspan="2"><input type="date" id="NextFollowUpDate"
						name="NextFollowUpDate" value=""  class="borderb"/></td>
					<td>随访医生 <span class="bluecolor" id="help17">?</span></td>
					<td><a href="javascript:void(0)" id="DoctorName" class="docSearch">单击选择医生</a> <input
						type="hidden" id="DoctorID"></td>

				</tr>
			</table>
			<div class="clear"></div>
			<div class="operation tc">
				<a href="/fdoctor/mobile/diabetes/fuindex" class="sibtn">返回随访页面</a>
				<!-- <button  type="button"  >重置</button> -->
				<a id="save" href="javascript:void(0)" class="sibtn">保存</a>
				<!-- <button  type="button" id="save">保存</button> -->
			</div>
		</form>
	</div>

</body>
<script type="text/javascript">
	
	//处理此次随访分类
	function followUpClassify(tr) {
		var value = tr.getAttribute('value');
		$("#FuClassification").val(value)
	}

	//处理药物不良反应
	function untowardEffect(tr) {
		var value = tr.getAttribute('value');
		$("#AdverseDrugReactions").val(value)
	}
	//听力处理
	function listening(tr) {
		var value = tr.getAttribute('value');
		$("#Hearing").val(value)
	}
	//运动能力处理
	function exercise(tr) {
		var value = tr.getAttribute('value');
		$("#MotorFunction").val(value)
	}
	//处理服药依从性
	function takeMedicine(tr) {
		var value = tr.getAttribute('value');
		$("#MedicationCompliance").val(value)
	}
	//处理遵医行为
	function medicalCompliance(tr) {
		var value = tr.getAttribute('value');
		$("#ComplianceBehavior").val(value)
	}
	//随访方式处理
	function followUp(tr) {
		var value = tr.getAttribute('value');
		$("#WayUp").val(value)

	}
	//足背动脉搏动处理
	function pulsation(tr) {
		var value = tr.getAttribute('value');
		$("#DorsalisPedisArteryPulse").val(value)
	}
	//低血糖反应处理
	function LowBlood(tr) {
		var value = tr.getAttribute('value');
		$("#LowBloodSugarReactions").val(value)
	}
	//心理调整
	function adjust(tr) {
		var value = tr.getAttribute('value');
		$("#PsychologicalAdjustment").val(value)
	}

	//症状处理
	function ChangSelect(tr) {
		//alert(tr.getAttribute('value'))
		var value = tr.getAttribute('value');
		//先判断先前是否选中
		var id = tr.getAttribute('id');
		//状态回显
		if (!($("#" + id).data('abc'))) {
			if (value == 1) {
				$("#" + id).data('abc', '1')
				/* var id =tr.getAttribute('id');
				$("#"+id).data('abc',"1"); */

				$("#Symptom" + 0).val(1)
				$("#Symptom" + 1).val("")
				$("#Symptom" + 2).val("")
				$("#Symptom" + 3).val("")
				$("#Symptom" + 4).val("")
				$("#Symptom" + 5).val("")
				$("#Symptom" + 6).val("")
				$("#Symptom" + 7).val("")
				$("#Symptom" + 8).val("")
				$("#Symptom" + 9).val("")

			} else if (value == 2) {
				$("#" + id).data('abc', '2')
				$("#Symptom" + 1).val(2)
				$("#Symptom" + 0).val("")
			} else if (value == 4) {
				$("#" + id).data('abc', '3')
				$("#Symptom" + 2).val(3)
				$("#Symptom" + 0).val("")
			} else if (value == 8) {
				$("#" + id).data('abc', '4')
				$("#Symptom" + 3).val(4)
				$("#Symptom" + 0).val("")
			} else if (value == 16) {
				$("#" + id).data('abc', '5')
				$("#Symptom" + 4).val(5)
				$("#Symptom" + 0).val("")
			} else if (value == 32) {
				$("#" + id).data('abc', '6')
				$("#Symptom" + 5).val(6)
				$("#Symptom" + 0).val("")
			} else if (value == 64) {
				$("#" + id).data('abc', '7')
				$("#Symptom" + 6).val(7)
				$("#Symptom" + 0).val("")
			} else if (value == 128) {
				$("#" + id).data('abc', '8')
				$("#Symptom" + 7).val(8)
				$("#Symptom" + 0).val("")
			} else if (value == 256) {
				$("#" + id).data('abc', '9')
				$("#Symptom" + 8).val(9)
				$("#Symptom" + 0).val("")
			} else if (value == 512) {
				$("#" + id).data('abc', '10')
				$("#Symptom" + 9).val(10)
				$("#Symptom" + 0).val("")
			}
		} else {
			if (value == 1) {
				//var id =tr.getAttribute('id');
				$("#" + id).data('abc', '')

				$("#Symptom" + 0).val("")

			} else if (value == 2) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 1).val("")
			} else if (value == 4) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 2).val("")
			} else if (value == 8) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 3).val("")
			} else if (value == 16) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 4).val("")
			} else if (value == 32) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 5).val("")
			} else if (value == 64) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 6).val("")
			} else if (value == 128) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 7).val("")
			} else if (value == 256) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 8).val("")
			} else if (value == 512) {
				$("#" + id).data('abc', '')
				$("#Symptom" + 9).val("")
			}
		}

	}
	
	//隐藏选中的 fielst;
	
	
	
	$(".assist_td fieldset a").on('click',function(){
		$(this).parents('fieldset').hide();
		return false;
	}); 
	//增加药品
	$(".add_drug")
			.on(
					'click',
					function() {
						var addrug = '<div class=""><input type="hidden" name="CmDrugID" value="">'
								+ '						药品名称： <input type="text" name="Drugs"  value="" />'
								+ '						每日： <input type="text" name="DailyTimes" value="" class="w20"/> 次'
								+ '				'
								+ '						每次：<input type="text" name="EachDose" value="" class="w20"/> '
								+ '						<select name="Remark"  >'
								+ '							<option value="mg">mg</option>'
								+ '							<option value="ml">ml</option>'
								+ '							<option value="g">g</option>'
								+ '							<option value="片">片</option>'
								+ '							<option value="粒">粒</option>'
								+ '							<option value="袋">袋</option>'
								+ '							<option value="瓶">瓶</option>'
								+ '							<option value="支">支</option>'
								+ '							<option value="盒">盒</option>'
								+ '							<option value="U">U</option>'
								+ '						</select>'
								+ '						<input type="text" name="Remark1"  value=""  class="borderb"/ > '
								+ '						<a href="javascript:void(0)"  class="delete_drug" onclick="delete_drug(this)"> 删除</a><br />'
								+ '					</div>';
						$(".add_drug_td").append(addrug);

					});
	//增加胰岛素药品
	$(".add_insulin")
			.on(
					'click',
					function() {
						var insulin = '<div class=""><input type="hidden" name="iCmDrugID"  value="">'
								+ '						药品名称： <input type="text" name="iDrugs" value="" />'
								+ '						每日： <input type="text" name="iDailyTimes" value="" class="w20"/> 次'
								+ '				'
								+ '						每次：<input type="text" name="iEachDose" value="" class="w20"/> '
								+ '						<select name="iRemark" >'
								+ '							<option value="mg">mg</option>'
								+ '							<option value="ml">ml</option>'
								+ '							<option value="g">g</option>'
								+ '							<option value="片">片</option>'
								+ '							<option value="粒">粒</option>'
								+ '							<option value="袋">袋</option>'
								+ '							<option value="瓶">瓶</option>'
								+ '							<option value="支">支</option>'
								+ '							<option value="盒">盒</option>'
								+ '							<option value="U">U</option>'
								+ '						</select>'
								+ '						<input type="text" name="iRemark1"  value=""  class="borderb"/ > '
								+ '						<a href="javascript:void(0)"  class="delete_idrug" onclick="delete_idrug(this) "> 删除</a><br />'
								+ '					</div>';
						$(".add_insulin_td").append(insulin);

					});
	//减少药品
	function delete_drug(x) {
		$(x).parent('div').remove();
	}
	//减速胰岛素
	function delete_idrug(x) {
		$(x).parent('div').remove();
	}
	
	
	var options = [ '胸部X线片', '肾功能', '血常规', '心电图', '尿常规', '宫颈涂片', '糖化血红蛋白',
			'尿微量白蛋白', '乙型肝炎表面抗原', '肝功能', 'B超', '大便潜血', '其他', '血脂' ]
	//添加 select 下拉框；
	for (var i = 0; i < options.length; i++) {
		$(".assist_check").append($("<option>").text(options[i]));
	}

	$(".assist_check").on('change', function() {

		var index = $('.assist_check  option:selected').index() - 1;
		$('.assist_td fieldset').eq(index).show();
	})

	//页面数据处理回显
	$(function() {
		//既往史
		$("#disHistory").text("${disHisString}");
		//家族史
		var minfo = diseaseType("${motherinfo}");
		var finfo = diseaseType("${fatherinfo}");
		var sbinfo = diseaseType("${sisbroinfo}");
		var sinfo = diseaseType("${soninfo}");
		$("#mother").text(minfo);
		$("#father").text(finfo);
		$("#sisbro").text(sbinfo);
		$("#son").text(sinfo);
		$("#lastDate").text("${lfupMap.FOLLOWUPDATE}");
		$("#lperfect").text("");
		$("#lRightSbp").text("${exambodyMap.RightSbp}");
		$("#lRightDbp").text("${exambodyMap.RightDbp}");
		$("#lHeartRate").text("${exambodyMap.HeartRate}");

		$("#lNEXTSMOKING").text("${lfupMap.NEXTSMOKING}");
		$("#lNEXTDAILYALCOHOL").text("${lfupMap.NEXTDAILYALCOHOL}");
		$("#lNEXTEXERCISEWEEKTIMES").text("${lfupMap.NEXTEXERCISEWEEKTIMES}");
		$("#lNEXTEXERCISEWEEKMINUT").text("${lfupMap.NEXTEXERCISEWEEKMINUT}");
		$("#lWeight").text("${exambodyMap.Weight}");
		$("#lNEXTSTAPLE").text("${lfupMap.NEXTSTAPLE}");
		function diseaseType(disease) {
			if (disease == "1") {
				return "无";
			} else if (disease == "2") {
				return "高血压";
			} else if (disease == "4") {
				return "糖尿病";
			} else if (disease == "8") {
				return "冠心病";
			} else if (disease == "16") {
				return "慢性阻塞性肺疾病";
			} else if (disease == "32") {
				return "恶性肿瘤";
			} else if (disease == "64") {
				return "脑卒中";
			} else if (disease == "128") {
				return "重性精神病";
			} else if (disease == "256") {
				return "结核病";
			} else if (disease == "512") {
				return "肝炎";
			} else if (disease == "1024") {
				return "先天畸形";
			} else if (disease == "2048") {
				return "其它";
			} else {
				return "";

			}
		}
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
	$("#save")
			.on(
					'click',
					function() {
						var saveParam = {};
						var CmDiab = {};
						var Body = {};
						var Labora = {};
						var Lifestyle = {};
						var Drug = [];
						var Insulindrug = [];
						var Organ = {};
						var Other = [];
						var transout = {};
						var otherMsg = {};
						
						//症状
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

						saveParam.Other = Other;
						
						
						transout["TargetOrgName"] = $("#TargetOrgName").val();
						transout["TranoutReasons"] = $("#TranoutReasons").val();
						saveParam.transout = transout;

						Body["RightSbp"] = $("#RightSbp").val();
						Body["RightDbp"] = $("#RightDbp").val();
						Body["Weight"] = $("#Weight").val();
						Body["Height"] = $("#Height").val();
						Body["Bmi"] = $("#Bmi").val();
						Body["DorsalisPedisArteryPulse"] = $(
								"DorsalisPedisArteryPulse").val();
						saveParam.Body = Body;

						Organ["LeftEye"] = $("#LeftEye").val();
						Organ["RightEye"] = $("#RightEye").val();
						Organ["LeftEyeVc"] = $("#LeftEyeVc").val();
						Organ["RightEyeVc"] = $("#RightEyeVc").val();
						Organ["Hearing"] = $("#Hearing").val();
						Organ["MotorFunction"] = $("#MotorFunction").val();
						saveParam.Organ = Organ;

						Lifestyle["Smoking"] = $("#Smoking").val();
						Lifestyle["DailyAlcoholIntake"] = $(
								"#DailyAlcoholIntake").val();
						Lifestyle["ExerciseWeekTimes"] = $("#ExerciseWeekTimes")
								.val();
						Lifestyle["EachExerciseTime"] = $("#EachExerciseTime")
								.val();
						saveParam.Lifestyle = Lifestyle;

						CmDiab["ID"] = "";//新建需要留空
						CmDiab["PersonID"] = "${PersonID }";//************
						CmDiab["FollowUpDate"] = $("#FollowUpDate").val();
						CmDiab["LowBloodSugarReactions"] = $(
								"#LowBloodSugarReactions").val();
						CmDiab["WayUp"] = $("#WayUp").val();
						CmDiab["NextWeight"] = $("#NextWeight").val();
						CmDiab["NextSmoking"] = $("#NextSmoking").val();
						CmDiab["NextDailyAlcohol"] = $("#NextDailyAlcohol")
								.val();
						CmDiab["NextExerciseWeekTimes"] = $(
								"#NextExerciseWeekTimes").val();
						CmDiab["NextExerciseWeekMinute"] = $(
								"#NextExerciseWeekMinute").val();
						CmDiab["Staple"] = $("#Staple").val();
						CmDiab["NextStaple"] = $("#NextStaple").val();
						CmDiab["PsychologicalAdjustment"] = $(
								"#PsychologicalAdjustment").val();
						CmDiab["ComplianceBehavior"] = $("#ComplianceBehavior")
								.val();
						CmDiab["MedicationCompliance"] = $(
								"#MedicationCompliance").val();
						CmDiab["AdverseDrugReactions"] = $(
								"#AdverseDrugReactions").val();
						CmDiab["FuClassification"] = $("#FuClassification")
								.val();
						CmDiab["FollowUpRemarks"] = $("#FollowUpRemarks").val();
						CmDiab["NextFollowUpDate"] = $("#NextFollowUpDate")
								.val();
						CmDiab["DoctorName"] = (($("#DoctorName").text()=="单击选择医生")?"":$("#DoctorName").text());
						CmDiab["UserID"] = "${userId}";//**********
						CmDiab["DoctorID"] = $("#DoctorID").val();//**********
						var SymptomSum = 0;
						var s0 = $("#Symptom0").val()
						if (s0 != null && s0 != "") {
							SymptomSum += 1;
						}
						var s1 = $("#Symptom1").val()
						if (s1 != null && s1 != "") {
							SymptomSum += 2;
						}
						var s2 = $("#Symptom2").val()
						if (s2 != null || s2 != "") {
							SymptomSum += 4;
						}
						var s3 = $("#Symptom3").val()
						if (s3 != null && s3 != "") {
							SymptomSum += 8;
						}
						var s4 = $("#Symptom4").val()
						if (s4 != null && s4 != "") {
							SymptomSum += 16;
						}
						var s5 = $("#Symptom5").val()
						if (s5 != null && s5 != "") {
							SymptomSum += 32;
						}

						var s6 = $("#Symptom6").val()
						if (s6 != null && s6 != "") {
							SymptomSum += 64;
						}
						var s7 = $("#Symptom7").val()
						if (s7 != null && s7 != "") {
							SymptomSum += 128;
						}
						var s8 = $("#Symptom8").val()
						if (s8 != null && s8 != "") {
							SymptomSum += 256;
						}
						var s9 = $("#Symptom9").val()
						if (s9 != null && s9 != "") {
							SymptomSum += 512;
						}
						CmDiab["Symptom"] = SymptomSum;
						saveParam.CmDiab = CmDiab;

						Labora["FastingBloodGlucose"] = $(
								"#FastingBloodGlucose").val();
						Labora["PostprandialBloodGlucose"] = $(
								"#PostprandialBloodGlucose").val();
						Labora["Hemoglobin"] = $("#Hemoglobin").val();
						Labora["Leukocyte"] = $("#Leukocyte").val();
						Labora["Platelet"] = $("#Platelet").val();
						Labora["OtherBlood"] = $("#OtherBlood").val();
						Labora["UrineProtein"] = $("#UrineProtein").val();
						Labora["Urine"] = $("#Urine").val();
						Labora["Ketone"] = $("#Ketone").val();
						Labora["OccultBloodInUrine"] = $("#OccultBloodInUrine")
								.val();
						Labora["OtherUrine"] = $("#OtherUrine").val();
						Labora["UrinaryAlbumin"] = $("#UrinaryAlbumin").val();
						Labora["FecalOccultBlood"] = $("#FecalOccultBlood")
								.val();
						Labora["SerumAla"] = $("#SerumAla").val();
						Labora["SerumAa"] = $("#SerumAa").val();
						Labora["Albumin"] = $("#Albumin").val();
						Labora["TotalBilirubin"] = $("#TotalBilirubin").val();
						Labora["Bilirubin"] = $("#Bilirubin").val();
						Labora["Bilirubin"] = $("#Bilirubin").val();
						Labora["Bun"] = $("#Bun").val();
						Labora["PotassiumConcentration"] = $(
								"#PotassiumConcentration").val();
						Labora["SodiumConcentration"] = $(
								"#SodiumConcentration").val();
						Labora["TotalCholesterol"] = $("#TotalCholesterol")
								.val();
						Labora["Triglycerides"] = $("#Triglycerides").val();
						Labora["LdlCholesterol"] = $("#LdlCholesterol").val();
						Labora["HdlCholesterol"] = $("#HdlCholesterol").val();
						Labora["GlycatedHemoglobin"] = $("#GlycatedHemoglobin")
								.val();
						Labora["HepatitisBSurfaceAntigen"] = $(
								"#HepatitisBSurfaceAntigen").val();
						Labora["Ecg"] = $("#Ecg").val();
						Labora["ChestXRay"] = $("#ChestXRay").val();
						Labora["BUltrasonicWave"] = $("#BUltrasonicWave").val();
						Labora["CervicalSmear"] = $("#CervicalSmear").val();
						saveParam.Labora = Labora;

						//处理药品等信息
						var DrugList = $("input[name='Drugs']");
						var DailyTimesList = $("input[name='DailyTimes']");
						var EachDoseList = $("input[name='EachDose']");
						var RemarkList = $("select[name='Remark']");
						var Remark1List = $("input[name='Remark1']");
						for (i = 0; i < DrugList.length; i++) {
							var yao = {};
							yao.CmDrugID = "null";
							yao.CmDrugName = DrugList[i].value;
							yao.DailyTimes = DailyTimesList[i].value;
							yao.EachDose = EachDoseList[i].value;
							var index = RemarkList[i].selectedIndex;
							yao.Remark = RemarkList[i].options[index].value;
							yao.Remark1 = Remark1List[i].value;
							Drug.push(yao);
						}
						saveParam.Drug = Drug;
						//胰岛素
						var iDrugList = $("input[name='iDrugs']");
						var iDailyTimesList = $("input[name='iDailyTimes']");
						var iEachDoseList = $("input[name='iEachDose']");
						var iRemarkList = $("select[name='iRemark']");
						var iRemark1List = $("input[name='iRemark1']");
						for (i = 0; i < iDrugList.length; i++) {
							var dao = {};
							dao.CmDrugID = "null";
							dao.CmDrugName = iDrugList[i].value;
							dao.DailyTimes = iDailyTimesList[i].value;
							dao.EachDose = iEachDoseList[i].value;
							var index1 = iRemarkList[i].selectedIndex;
							dao.Remark = iRemarkList[i].options[index1].value;
							dao.Remark1 = iRemark1List[i].value;
							Insulindrug.push(dao);
						}
						saveParam.Insulindrug = Insulindrug;

						//保存
						if (window.confirm('是否保存？')) {
							console.debug(saveParam);
							$.ajax({
								type : 'POST',
								url : '/fdoctor/mobile/diabetes/updateSave',
								data : {
									saveParam : JSON.stringify(saveParam),
									doctorName:"${doctorName}"
								},
								success : function(data) {
									if (data.code == '200') {
										alert('保存成功！');

									} else {
										alert('保存失败！');
									}
								}
							});
							return true;
						} else {
							return false;
						}
					})
		$(".chosePerson")
			.on(
					'click',
					function() {
						layer
								.open({
									type : 2,
									title : '新建转诊单记录',
									shadeClose : true,
									shade : 0.8,
									/*  area: ['600px', '400px'], */
									area : [ '800px', '600px' ],
									/* content: 'hypertensionPeoplesearch.jsp'  *//* //iframe的url */
									content : '/fdoctor/views/mobile/diabetes/transferTreatment.jsp?Name='
											+ encodeURI("${Name}")
											+ '&PersonCode='
											+ "${baseData.PersonCode}"
											+ '&Gender='
											+ encodeURI("${Gender}")
											+ "&age="
											+ encodeURI("${age}")
											+ "&ResidenceAddress="
											+ "${baseData.ResidenceAddress}"
											+ "&PersonTel="
											+ encodeURI("${PersonTel}") 
											+ "&doctorName="
											+ "${doctorName}"/* //iframe的url */
								});
						//layer.close(index);
						return false;
					});
	
			
	$("#help1").on('click',function(){
		layer.tips('体质指数=体重（kg）/身高的平方（m2），体重和体质指数斜线前填写目前情况，斜线后下填写下次随访时应调整到的目标。如果是超重或是肥胖的高血压患者，要求每次随访时测量体重并指导患者控制体重；正常体重人群可每年测量一次体重及体质指数。如有其他阳性体征，请填写在“其他”一栏', '#help1', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help2").on('click',function(){
		layer.tips('在询问患者生活方式时，同时对患者进行生活方式指导，与患者共同制定下次随访目标。', '#help2', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	
	$("#help3").on('click',function(){
		layer.tips('斜线前填写目前吸烟量，不吸烟填“0”，吸烟者写出每天的吸烟量“××支”，斜线后填写吸烟者下次随访目标吸烟量“××支”。', '#help3', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help4").on('click',function(){
		layer.tips('斜线前填写目前饮酒量，不饮酒填“0”，饮酒者写出每天的饮酒量相当于白酒“××两”，斜线后填写饮酒者下次随访目标饮酒量相当于白酒“××两”。白酒1两相当于葡萄酒4两，黄酒半斤，啤酒1瓶，果酒4两。', '#help4', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help5").on('click',function(){
		layer.tips('填写每周几次，每次多少分钟。即“××次／周，××分钟／次”。横线上填写目前情况，横线下填写下次随访时应达到的目标。', '#help5', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help6").on('click',function(){
		layer.tips('根据患者的实际情况估算主食（米饭、面食、饼干等淀粉类食物）的摄入量。为每天各餐的合计量。', '#help6', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help7").on('click',function(){
		layer.tips('根据医生印象选择对应的选项。', '#help7', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help8").on('click',function(){
		layer.tips('指患者是否遵照医生的指导去改善生活方式。', '#help8', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help9").on('click',function(){
		layer.tips('为患者进行空腹血糖检查，记录检查结果。若患者在上次随访到此次随访之间到各医疗机构进行过糖化血红蛋白或其他辅助检查，应如实记录。', '#help9', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help10").on('click',function(){
		layer.tips('“规律”为按医嘱服药，“间断”为未按医嘱服药，频次或数量不足，“不服药”即为医生开了处方，但患者未使用此药。', '#help10', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help11").on('click',function(){
		layer.tips('如果患者服用的降压药物有明显的药物不良反应，具体描述哪种药物，何种不良反应。', '#help11', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help12").on('click',function(){
		layer.tips('根据上次随访到此次随访之间患者出现的低血糖反应情况。', '#help12', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help13").on('click',function(){
		layer.tips('根据此次随访时的分类结果，由随访医生在4种分类结果中选择一项在“□”中填上相应的数字。“控制满意”意为血压控制满意，无其他异常、“控制不满意”意为血压控制不满意，无其他异常、“不良反应”意为存在药物不良反应、“并发症”意为出现新的并发症或并发症出现异常。如果患者同时并存几种情况，填写最严重的一种情况，同时结合上次随访情况确定患者下次随访时间，并告知患者。', '#help13', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help14").on('click',function(){
		layer.tips('根据患者整体情况，为患者开具处方，并填写在表格中，写明用法、用量。', '#help14', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help15").on('click',function(){
		layer.tips('如果转诊要写明转诊的医疗机构及科室类别，如××市人民医院心内科，并在原因一栏写明转诊原因。', '#help15', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help16").on('click',function(){
		layer.tips('根据患者此次随访分类，确定下次随访日期，并告知患者。', '#help16', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	$("#help17").on('click',function(){
		layer.tips('随访完毕，核查无误后随访医生签署其姓名。', '#help17', {
		  tips: [2, '#2b98ff'],
			  time: 3000
			  });
			return false;
	});
	
				
	
	
	
	
					
					
</script>
</html>
