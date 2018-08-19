<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
  <meta charset="utf-8">
  <title>高血压随访</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
  <link rel="stylesheet" href="/fdoctor/statics/layui/css/layui.css"/>
  <script type="text/javascript" src="/fdoctor/statics/layui/layui.js"></script>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px; margin-left: 2%; margin-right: 2%">
	  <legend>高血压随访基本信息</legend>
	</fieldset>
	<form class="layui-form layui-form-pane" id="basicForm" action="" style="margin-left: 2%; margin-right: 2%">
		<div class="layui-form-item">
		    <label class="layui-form-label">居民姓名</label>
		    <div class="layui-input-block">
		      <input name="personName" class="layui-input" type="text" value="${personInfo.personName} " disabled>
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">随访日期</label>
		    <div class="layui-input-block">
		      <input class="layui-input" name="FollowUpDate" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">随访方式</label>
		    <div class="layui-input-block">
		      <select name="WayUp" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >门诊</option>
		        <option value="2" >家庭</option>
		        <option value="3" >电话</option>
		      </select>
		    </div>
		  </div>
		    <div class="layui-form-item">
		    <label class="layui-form-label">症状</label>
		    <div class="layui-input-block" id="symptomDiv">
		      <input type="checkbox" value="1" title="无症状">
		      <input type="checkbox" value="2" title="头痛头晕">
		      <input type="checkbox" value="4" title="恶心呕吐">
		      <input type="checkbox" value="8" title="眼花耳鸣">
		      <input type="checkbox" value="16" title="呼吸困难">
		      <input type="checkbox" value="32" title="心悸胸闷">
		      <input type="checkbox" value="64" title="鼻衄">
		      <input type="checkbox" value="128" title="四肢发麻">
		      <input type="checkbox" value="256" title="下肢水肿">
		      <input type="checkbox" value="512" title="其他">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">其他症状</label>
		    <div class="layui-input-block">
		      <input name="SymptomOther" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">其他体征</label>
		    <div class="layui-input-block">
		      <input name="ExamBodyOther" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">目标体重</label>
		    <div class="layui-input-block">
		      <input name="NextWeight" class="layui-input" type="text" placeholder="下次目标体重">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">目标心率</label>
		    <div class="layui-input-block">
		      <input name="NextHeartRate" class="layui-input" type="text" placeholder="下次目标心率">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">目标吸烟量</label>
		    <div class="layui-input-block">
		      <input name="NextSmoking" class="layui-input" type="text" placeholder="下次目标日吸烟量">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">目标饮酒量</label>
		    <div class="layui-input-block">
		      <input name="NextDailyAlcohol" class="layui-input" type="text" placeholder="下次目标日饮酒量">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">摄盐情况</label>
		    <div class="layui-input-block">
		      <select name="SaltIntake" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >轻</option>
		        <option value="2" >中</option>
		        <option value="3" >重</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">目标摄盐</label>
		    <div class="layui-input-block">
		      <select name="NextSaltIntake" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >轻</option>
		        <option value="2" >中</option>
		        <option value="3" >重</option>
		      </select>
		    </div>
		  </div>
		   <div class="layui-form-item">
		    <label class="layui-form-label">下次锻炼次数</label>
		    <div class="layui-input-block">
		      <input name="NextExerciseWeekTimes" class="layui-input" type="text" placeholder="下次每周锻炼次数">
		    </div>
		  </div>
		   <div class="layui-form-item">
		    <label class="layui-form-label">下次锻炼分钟</label>
		    <div class="layui-input-block">
		      <input name="NextExerciseWeekMinute" class="layui-input" type="text" placeholder="下次每周锻炼分钟">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">心理调整</label>
		    <div class="layui-input-block">
		      <select name="PsychologicalAdjustment" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >良好</option>
		        <option value="2" >一般</option>
		        <option value="3" >差</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">遵医行为</label>
		    <div class="layui-input-block">
		      <select name="ComplianceBehavior" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >良好</option>
		        <option value="2" >一般</option>
		        <option value="3" >差</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">服药依从性</label>
		    <div class="layui-input-block">
		      <select name="MedicationCompliance" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >规律</option>
		        <option value="2" >间断</option>
		        <option value="3" >不服药</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">药物不良反应</label>
		    <div class="layui-input-block">
		      <select name="AdverseDrugReactions" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >无</option>
		        <option value="2" >有</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">随访分类</label>
		    <div class="layui-input-block">
		      <select name="FuClassification" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >控制满意</option>
		        <option value="2" >控制不满意</option>
		        <option value="3" >不良反应</option>
		        <option value="4" >并发症</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">随访结局</label>
		    <div class="layui-input-block">
		      <input name="FollowUpRemarks" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">下次随访日期</label>
		    <div class="layui-input-block">
		      <input class="layui-input" name="NextFollowUpDate" placeholder="下次随访日期" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">随访医生</label>
		    <div class="layui-input-block">
		      <input class="layui-input" name="DoctorName" value="${docName}" disabled>
		    </div>
		  </div>
		  <input type="hidden" name="UserID" value="${docId}">
		  <input type="hidden" name="DoctorID" value="${docId}">
		  <input type="hidden" name="PersonID" value="${personInfo.healthFileCode}">
		  <input type="hidden" name="ID">
	</form>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px; margin-left: 2%; margin-right: 2%">
	  <legend>体征信息</legend>
	</fieldset>
	<form class="layui-form layui-form-pane" id="BodyForm" action="" style="margin-left: 2%; margin-right: 2%">
		<input type="hidden" name="ID">
		<div class="layui-form-item">
		    <label class="layui-form-label">体温</label>
		    <div class="layui-input-block">
		      <input name="BodyTemperature" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">脉率</label>
		    <div class="layui-input-block">
		      <input name="PulseRate" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">心率</label>
		    <div class="layui-input-block">
		      <input name="HeartRate" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">呼吸频率</label>
		    <div class="layui-input-block">
		      <input name="RespiratoryRate" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">左侧收缩压</label>
		    <div class="layui-input-block">
		      <input name="LeftSbp" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">左侧舒张压</label>
		    <div class="layui-input-block">
		      <input name="LeftDbp" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">右侧收缩压</label>
		    <div class="layui-input-block">
		      <input name="RightSbp" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">右侧舒张压</label>
		    <div class="layui-input-block">
		      <input name="RightDbp" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">身高</label>
		    <div class="layui-input-block">
		      <input name="Height" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">体重</label>
		    <div class="layui-input-block">
		      <input name="Weight" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">腰围</label>
		    <div class="layui-input-block">
		      <input name="Waistline" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">臀围</label>
		    <div class="layui-input-block">
		      <input name="Hip" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">体质指数</label>
		    <div class="layui-input-block">
		      <input name="Bmi" class="layui-input" type="text">
		    </div>
		  </div>
	</form>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px; margin-left: 2%; margin-right: 2%">
	  <legend>辅助检查</legend>
	</fieldset>
	<form class="layui-form layui-form-pane" id="LaboraForm" action="" style="margin-left: 2%; margin-right: 2%">
		<input type="hidden" name="ID">
		<div class="layui-form-item">
		    <label class="layui-form-label">空腹血糖</label>
		    <div class="layui-input-block">
		      <input name="FastingBloodGlucose" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">餐后血糖</label>
		    <div class="layui-input-block">
		      <input name="PostprandialBloodGlucose" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">随机血糖</label>
		    <div class="layui-input-block">
		      <input name="RandomBloodGlucose" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">血红蛋白</label>
		    <div class="layui-input-block">
		      <input name="Hemoglobin" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">白细胞</label>
		    <div class="layui-input-block">
		      <input name="Leukocyte" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">血小板</label>
		    <div class="layui-input-block">
		      <input name="Platelet" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">血常规其他</label>
		    <div class="layui-input-block">
		      <input name="OtherBlood" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">尿蛋白</label>
		    <div class="layui-input-block">
		      <input name="UrineProtein" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">尿糖</label>
		    <div class="layui-input-block">
		      <input name="Urine" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">尿酮体</label>
		    <div class="layui-input-block">
		      <input name="Ketone" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">尿潜血</label>
		    <div class="layui-input-block">
		      <input name="OccultBloodInUrine" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">尿常规其他</label>
		    <div class="layui-input-block">
		      <input name="OtherUrine" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">尿微量白蛋白</label>
		    <div class="layui-input-block">
		      <input name="UrinaryAlbumin" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">大便潜血</label>
		    <div class="layui-input-block">
		      <select name="FecalOccultBlood" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >阴性</option>
		        <option value="2" >阳性</option>
		      </select>
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">血清谷丙转氨酶</label>
		    <div class="layui-input-block">
		      <input name="SerumAla" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">血清谷草转氨酶</label>
		    <div class="layui-input-block">
		      <input name="SerumAa" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">白蛋白</label>
		    <div class="layui-input-block">
		      <input name="Albumin" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">总胆红素</label>
		    <div class="layui-input-block">
		      <input name="TotalBilirubin" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">结合胆红素</label>
		    <div class="layui-input-block">
		      <input name="Bilirubin" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">血清肌酐</label>
		    <div class="layui-input-block">
		      <input name="SerumCreatinine" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">血尿素氮</label>
		    <div class="layui-input-block">
		      <input name="Bun" class="layui-input" type="text">
		    </div>
		  </div>
		  
		 <div class="layui-form-item">
		    <label class="layui-form-label">血钾浓度</label>
		    <div class="layui-input-block">
		      <input name="PotassiumConcentration" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">血钠浓度</label>
		    <div class="layui-input-block">
		      <input name="SodiumConcentration" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">总胆固醇</label>
		    <div class="layui-input-block">
		      <input name="TotalCholesterol" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">甘油三酯</label>
		    <div class="layui-input-block">
		      <input name="Triglycerides" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">GPT</label>
		    <div class="layui-input-block">
		      <input name="GPT" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">血清低密度脂蛋白胆固醇</label>
		    <div class="layui-input-block">
		      <input name="LdlCholesterol" class="layui-input" type="text" placeholder="血清低密度脂蛋白胆固醇">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">血清高密度脂蛋白胆固醇</label>
		    <div class="layui-input-block">
		      <input name="HdlCholesterol" class="layui-input" type="text" placeholder="血清高密度脂蛋白胆固醇">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">糖化血红蛋白</label>
		    <div class="layui-input-block">
		      <input name="GlycatedHemoglobin" class="layui-input" type="text" placeholder="糖化血红蛋白">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">乙型肝炎表面抗原</label>
		    <div class="layui-input-block">
		      <input name="HepatitisBSurfaceAntigen" class="layui-input" type="text" placeholder="乙型肝炎表面抗原">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">心电图</label>
		    <div class="layui-input-block">
		      <select name="Ecg" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >正常</option>
		        <option value="2" >异常</option>
		      </select>
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">异常说明</label>
		    <div class="layui-input-block">
		      <input name="EcgReason" class="layui-input" type="text" placeholder="心电图异常说明">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">胸部X线片</label>
		    <div class="layui-input-block">
		      <select name="ChestXRay" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >正常</option>
		        <option value="2" >异常</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">异常说明</label>
		    <div class="layui-input-block">
		      <input name="ChestXRayReason" class="layui-input" type="text" placeholder="胸部X线片异常说明">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">B超</label>
		    <div class="layui-input-block">
		      <select name="BUltrasonicWave" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >正常</option>
		        <option value="2" >异常</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">异常说明</label>
		    <div class="layui-input-block">
		      <input name="BUlReason" class="layui-input" type="text" placeholder="B超异常说明">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">宫颈涂片</label>
		    <div class="layui-input-block">
		      <select name="CervicalSmear" lay-filter="">
		        <option value="0">--请选择--</option>
		        <option value="1" >正常</option>
		        <option value="2" >异常</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">异常说明</label>
		    <div class="layui-input-block">
		      <input name="CervicalSmearReason" class="layui-input" type="text" placeholder="宫颈涂片异常说明">
		    </div>
		  </div>
		  <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">辅助检查其他</label>
		    <div class="layui-input-block">
		      <textarea placeholder="辅助检查其他" class="layui-textarea" name="OtherLaboratory" id="OtherLaboratory"></textarea>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">检查日期</label>
		    <div class="layui-input-block">
		      <input class="layui-input" name="ExamDate" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">红细胞</label>
		    <div class="layui-input-block">
		      <input name="Erythrocytes" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">白细胞分类计数</label>
		    <div class="layui-input-block">
		      <input name="DifferentialCount" class="layui-input" type="text" placeholder="白细胞分类计数">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">血转氨酶</label>
		    <div class="layui-input-block">
		      <input name="BloodTransaminase" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">尿比重</label>
		    <div class="layui-input-block">
		      <input name="Sg" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">尿酸碱度</label>
		    <div class="layui-input-block">
		      <input name="Ph" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">淋球菌</label>
		    <div class="layui-input-block">
		      <input name="Ng" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">梅毒螺旋体抗体</label>
		    <div class="layui-input-block">
		      <input name="Tppa" class="layui-input" type="text" placeholder="梅毒螺旋体抗体">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">HIV抗体</label>
		    <div class="layui-input-block">
		      <input name="Hiv" class="layui-input" type="text">
		    </div>
		  </div>
	</form>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px; margin-left: 2%; margin-right: 2%">
	  <legend>生活方式</legend>
	</fieldset>
	<form class="layui-form layui-form-pane" id="LifestyleForm" action="" style="margin-left: 2%; margin-right: 2%">
		<input type="hidden" name="ID">
		<div class="layui-form-item">
		    <label class="layui-form-label">锻炼频率</label>
		    <div class="layui-input-block">
		      <input name="ExerciseFrequency" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">每次锻炼时间</label>
		    <div class="layui-input-block">
		      <input name="EachExerciseTime" class="layui-input" type="text" placeholder="每次锻炼时间">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">每周锻炼次数</label>
		    <div class="layui-input-block">
		      <input name="ExerciseWeekTimes" class="layui-input" type="text" placeholder="每周锻炼次数">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">日吸烟量</label>
		    <div class="layui-input-block">
		      <input name="Smoking" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">日饮酒量</label>
		    <div class="layui-input-block">
		      <input name="DailyAlcoholIntake" class="layui-input" type="text">
		    </div>
		  </div>
	</form>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px; margin-left: 2%; margin-right: 2%">
	  <legend>用药情况</legend>
	</fieldset>
	<form class="layui-form layui-form-pane" id="drugsForm" action="" style="margin-left: 2%; margin-right: 2%">
		<button class="layui-btn" type="button" id="add_drogs_btn">添加用药情况</button>
		<span id="drugSpan">
			
		</span>
	</form>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px; margin-left: 2%; margin-right: 2%">
	  <legend>脏器功能</legend>
	</fieldset>
	<form class="layui-form layui-form-pane" action="" id="OrganForm" style="margin-left: 2%; margin-right: 2%">
		<input type="hidden" name="ID">
		<div class="layui-form-item">
		    <label class="layui-form-label">左眼视力</label>
		    <div class="layui-input-block">
		      <input name="LeftEye" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">右眼视力</label>
		    <div class="layui-input-block">
		      <input name="RightEye" class="layui-input" type="text">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">左眼纠正视力</label>
		    <div class="layui-input-block">
		      <input name="LeftEyeVc" class="layui-input" type="text" placeholder="左眼纠正视力">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">右眼纠正视力</label>
		    <div class="layui-input-block">
		      <input name="RightEyeVc" class="layui-input" type="text" placeholder="右眼纠正视力">
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">听力</label>
		    <div class="layui-input-block">
		      <select name="Hearing" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >听见</option>
		        <option value="2" >听不清或无法听见</option>
		      </select>
		    </div>
		  </div>
		<div class="layui-form-item">
		    <label class="layui-form-label">运动功能</label>
		    <div class="layui-input-block">
		      <select name="Hearing" lay-filter="">
		        <option value="">--请选择--</option>
		        <option value="1" >可顺利完成</option>
		        <option value="2" >无法独立完成其中任何一个动作</option>
		      </select>
		    </div>
		  </div>
	</form>
	<div style="margin-top: 20px; margin-left: 2%; margin-right: 2%">
		<button class="layui-btn" type="button" id="saveButton">保存随访</button>
	</div>
<script type="text/javascript">
//初始化时间控件
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  var start = {
	    min: laydate.now()
	    ,max: '2099-06-16 23:59:59'
	    ,istoday: false
	    ,choose: function(datas){
	      end.min = datas; //开始日选好后，重置结束日的最小日期
	      end.start = datas //将结束日的初始值设定为开始日
	    }
	  };
	  
	  var end = {
	    min: laydate.now()
	    ,max: '2099-06-16 23:59:59'
	    ,istoday: false
	    ,choose: function(datas){
	      start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	  };
	});
layui.define(['layer', 'form'], function(exports){
	  var layer = layui.layer
	  ,form = layui.form();
	  
	  exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});    
//如果为修改，则初始化表单回显
if('${dataJson}' != null && '${dataJson}' != ''){
	var jsonStr = '${dataJson}';
	var dataJson = $.parseJSON(jsonStr);
	console.debug(dataJson);
	//初始化基本信息表单回显
	for(var key in dataJson.Msg.cmHypertension){  
		//输入框回显
		$("#basicForm input[name='"+key+"']").val(dataJson.Msg.cmHypertension[key])
		//下拉框回显
		var $options = $("#basicForm select[name='"+key+"'] option");
		for(var i=0;i<$options.length;i++){
		    var oprion = $($options.get(i));
		    if(oprion.attr("value")==dataJson.Msg.cmHypertension[key]){
		      oprion.attr("selected","selected");
		    }
		 }
    }
	$("#basicForm input[name='DoctorName']").val(dataJson.Msg.cmHypertension.DoctorName)
	$("#basicForm input[name='DoctorID']").val(dataJson.Msg.cmHypertension.DoctorID)
	$("#basicForm input[name='UserID']").val(dataJson.Msg.cmHypertension.UserID)
	//其他症状回显
	for(i=0;i<dataJson.Msg.otherJson.length;i++){
		if(dataJson.Msg.otherJson[i].AttrName=='SymptomOther'){
			$("#basicForm input[name='SymptomOther']").val(dataJson.Msg.otherJson[i].OtherText)
		}
	}
	//控制症状复选框回显
	var strs=dataJson.Msg.cmHypertension.Symptom.split(",");
	var boxs = $("#symptomDiv :checkbox");
	for(var i=0; i<strs.length; i++ ){
		for(var j=0;j<boxs.length;j++){
			if(strs[i] == $(boxs[j]).val()){
				$(boxs[j]).prop("checked", true);
			}
		}
	}
	//初始化体征信息回显
	for(var key in dataJson.Msg.examBody){  
		//输入框回显
		$("#BodyForm input[name='"+key+"']").val(dataJson.Msg.examBody[key])
    }
	
	//初始化辅助检查信息回显
	for(var key in dataJson.Msg.examLaboratory){  
		//输入框回显
		$("#LaboraForm input[name='"+key+"']").val(dataJson.Msg.examLaboratory[key]);
		//下拉框回显
		var $options = $("#LaboraForm select[name='"+key+"'] option");
		for(var i=0;i<$options.length;i++){
		    var oprion = $($options.get(i));
		    if(oprion.attr("value")==dataJson.Msg.examLaboratory[key]){
		      oprion.attr("selected","selected");
		    }
		 }
		//textarea回显
		$("#LaboraForm textarea[name='OtherLaboratory']").val(dataJson.Msg.examLaboratory.OtherLaboratory);
    }
	//初始化生活方式表单回显
	for(var key in dataJson.Msg.examLifestyle){  
		//输入框回显
		$("#LifestyleForm input[name='"+key+"']").val(dataJson.Msg.examLifestyle[key])
    }
	//初始化脏器功能表单回显
	for(var key in dataJson.Msg.examOrgan){  
		//输入框回显
		$("#OrganForm input[name='"+key+"']").val(dataJson.Msg.examOrgan[key]);
		//下拉框回显
		var $options = $("#OrganForm select[name='"+key+"'] option");
		for(var i=0;i<$options.length;i++){
		    var oprion = $($options.get(i));
		    if(oprion.attr("value")==dataJson.Msg.examOrgan[key]){
		      oprion.attr("selected","selected");
		    }
		 }
    }
	//初始化用药情况回显
	for(i=0;i<dataJson.Msg.drugJson.length;i++){
		$("#drugSpan").append('<fieldset class="layui-elem-field"><legend>用药情况<i class="layui-icon"style="cursor: pointer;"onclick="deleteDrug(this)">&#x1006;</i></legend><div class="layui-field-box"><input type="hidden"name="ID"><div class="layui-form-item"><label class="layui-form-label">药名</label><div class="layui-input-block"><input name="DrugName"class="layui-input"type="text"value="'+dataJson.Msg.drugJson[i].Drugs+'"></div></div><div class="layui-form-item"><label class="layui-form-label">每日次数</label><div class="layui-input-block"><input name="DailyTimes"class="layui-input"type="text"value="'+dataJson.Msg.drugJson[i].DailyTimes+'"></div></div><div class="layui-form-item"><label class="layui-form-label">每次剂量</label><div class="layui-input-block"><input name="EachDose"class="layui-input"type="text"value="'+dataJson.Msg.drugJson[i].EachDose+'"></div></div><div class="layui-form-item"><label class="layui-form-label">单位备注</label><div class="layui-input-block"><input name="Remark"class="layui-input"type="text"placeholder="mg,ml,g,片，粒，袋，瓶，支，盒"value="'+dataJson.Msg.drugJson[i].Remark+'"></div></div><div class="layui-form-item"><label class="layui-form-label">备注</label><div class="layui-input-block"><input name="Remark1"class="layui-input"type="text"value="'+dataJson.Msg.drugJson[i].Remark1+'"></div></div></div></fieldset>');
	}
}
//保存事件触发
$("#saveButton").on('click',function(){
	//封装基础随访表单
	var baseData = $("#basicForm").serializeArray();
	var CmHyper = {};
	for(i=0;i<baseData.length;i++){
		CmHyper[baseData[i].name] = baseData[i].value;
	}
	var symptomValue = 0;
	$("#symptomDiv input:checkbox:checked").each(function(){
		symptomValue = symptomValue + Number($(this).val());
	})
	if(symptomValue != 0){
		CmHyper.Symptom=symptomValue;
	}
	
	//封装体征信息表单
	var bodyData = $("#BodyForm").serializeArray();
	var Body = {};
	for(i=0;i<bodyData.length;i++){
		Body[bodyData[i].name] = bodyData[i].value;
	}
	//封装辅助检查表单信息
	var laboraData = $("#LaboraForm").serializeArray();
	var Labora = {};
	for(i=0;i<laboraData.length;i++){
		Labora[laboraData[i].name] = laboraData[i].value;
	}
	if(Labora.Ecg != null && Labora.Ecg != ''){
		Labora.Ecg = Labora.Ecg + '\u0001' + Labora.EcgReason;
	}
	delete Labora.EcgReason;
	
	if(Labora.BUltrasonicWave != null && Labora.BUltrasonicWave != ''){
		Labora.BUltrasonicWave = Labora.BUltrasonicWave + '\u0001' + Labora.BUlReason;
	}
	delete Labora.BUlReason;
	
	if(Labora.ChestXRay != null && Labora.ChestXRay != ''){
		Labora.ChestXRay = Labora.ChestXRay + '\u0001' + Labora.ChestXRayReason;
	}
	delete Labora.ChestXRayReason;
	
	if(Labora.CervicalSmear != null && Labora.CervicalSmear != ''){
		Labora.CervicalSmear = Labora.CervicalSmear + '\u0001' + Labora.CervicalSmearReason;
	}
	delete Labora.CervicalSmearReason;
	
	//封装生活方式表单信息
	var lifestyleData = $("#LifestyleForm").serializeArray();
	var Lifestyle = {};
	for(i=0;i<lifestyleData.length;i++){
		Lifestyle[lifestyleData[i].name] = lifestyleData[i].value;
	}
	//封装脏器功能表单信息
	var OrganData = $("#OrganForm").serializeArray();
	var Organ = {};
	for(i=0;i<OrganData.length;i++){
		Organ[OrganData[i].name] = OrganData[i].value;
	}
	//封装用药信息
	var Drug = [];
	var ID_item = $("#drugsForm input[name='ID']");
	var DrugName_item = $("input[name='DrugName']");
	var DailyTimes_item = $("input[name='DailyTimes']");
	var EachDose_item = $("input[name='EachDose']");
	var Remark_item = $("input[name='Remark']");
	var Remark1_item = $("input[name='Remark1']");
	
	for(var i=0; i<DrugName_item.length; i++){
		var data = {
				ID : $(ID_item[i]).val(),
				CmDrugName : $(DrugName_item[i]).val(),
				DailyTimes : $(DailyTimes_item[i]).val(),
				EachDose : $(EachDose_item[i]).val(),
				Remark : $(Remark_item[i]).val(),
				Remark1 : $(Remark1_item[i]).val()
		}
		Drug.push(data);
	}
	//将所有信息封装为统一json
	var saveParam = {};
	saveParam.ProductCode = "${productCode}";
	saveParam.CmHyper = CmHyper;
	saveParam.Body = Body;
	saveParam.Labora = Labora;
	saveParam.Lifestyle = Lifestyle;
	saveParam.Drug = Drug;
	saveParam.Organ = Organ;
	saveParam.OrgID = "${orgId}";
	saveParam.Other = [];
	var otherMsg = {}
	otherMsg.AttrName = "SymptomOther";
	otherMsg.OtherText = saveParam.CmHyper.SymptomOther;
	saveParam.Other.push(otherMsg);
	delete saveParam.CmHyper.SymptomOther;
	
	if(window.confirm('是否保存？')){
		console.debug(saveParam);
		$.ajax({
		    type: 'POST',
		    url: '/fdoctor/mobile/followup/saveHypertension' ,
		    data: {
		    	dataJson : JSON.stringify(saveParam),
		    	planId : "${planId}"
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
        //alert("取消");
        return false;
    }
});

$("#add_drogs_btn").on('click',function (){
	$("#drugSpan").append('<fieldset class="layui-elem-field"><legend>用药情况<i class="layui-icon"style="cursor: pointer;" onclick="deleteDrug(this)">&#x1006;</i></legend><div class="layui-field-box"><input type="hidden"name="ID"><div class="layui-form-item"><label class="layui-form-label">药名</label><div class="layui-input-block"><input name="DrugName"class="layui-input"type="text"></div></div><div class="layui-form-item"><label class="layui-form-label">每日次数</label><div class="layui-input-block"><input name="DailyTimes"class="layui-input"type="text"></div></div><div class="layui-form-item"><label class="layui-form-label">每次剂量</label><div class="layui-input-block"><input name="EachDose"class="layui-input"type="text"></div></div><div class="layui-form-item"><label class="layui-form-label">单位备注</label><div class="layui-input-block"><input name="Remark"class="layui-input"type="text"placeholder="mg,ml,g,片，粒，袋，瓶，支，盒"></div></div><div class="layui-form-item"><label class="layui-form-label">备注</label><div class="layui-input-block"><input name="Remark1"class="layui-input"type="text"></div></div></div></fieldset>');
});

function deleteDrug(item){
	$(item).parent().parent().remove();
}
</script>
</body>
</html>