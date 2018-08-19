<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>体检详情</title>

<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	

<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->

<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="/fdoctor/statics/layui/css/layui.css"/>
  <script type="text/javascript" src="/fdoctor/statics/layui/layui.js"></script>
 <!--  <style type="text/css">
  .delete_drug:nth-child(1){
  	display:none;
  }
  </style> -->
</head>

<body>
	<div class="main">
		
		<div class="clear"></div>
		<div class="mt10 mb10">
			历次体检 &nbsp;<select id="HealthDateList">
					<c:forEach items="${listMtIdAndExamDate}" var="data" > 	
					<option value="${data.MtID}" examdate="${data.ExamDate}">${data.ExamDate} </option>
				    </c:forEach>
					</select>
			<span class="seat"></span>
			<a href="/"> <span class="circular">+</span> 新增体检</a>
		</div>
		
		<div class="personMsg">
			<table>
				<tr>
					<td>上次体检时间</td>
					<td>${listMtIdAndExamDate[1].ExamDate}</td>
					<td>体重</td>
					<td>${lastExamination['Weight']}</td>
					<td>身高</td>
					<td>${lastExamination['Height']}</td>
					<td>体质指数</td>
					<td>${lastExamination['Bmi']}</td>
				</tr>
				<tr>
					<td>腰围</td>
					<td>${lastExamination['Waistline']}</td>
					<td>体温</td>
					<td>${lastExamination['BodyTemperature']}</td>
					<td>脉率</td>
					<td>${lastExamination['PulseRate']}</td>
					<td>呼吸频率</td>
					<td>${lastExamination['RespiratoryRate']}</td>
				</tr>
				<tr>
					<td>左侧血压</td>
					<td>${lastExamination['LeftSbp']}/${lastExamination['LeftDbp']}</td>
					<td>右侧血压</td>
					<td>${lastExamination['RightSbp']}/${lastExamination['RightDbp']}</td>
					
				</tr>
			</table>
		</div>
		
		<div class="table3_hdinfo">
			<table>
					<tr>
						<td>姓名</td>
						<td>${remap.Name}</td>
						<td>性别</td>
						<td>${remap.GenderCode}</td>
						<td>体检年龄</td>
						<td>${remap.Age}</td>
						<td>编号</td>
						<td>${remap.PersonCode}</td>
					</tr>
			</table>
		</div>
		
		
		<div class="table3">
			<table id="datable" border="1">
			    
				<tr>
					<td>体检日期</td> 
					<td colspan="2"><input  name="rExamDate" type="date"  value=""></input></td>
					<td >责任医生</td>
					<td colspan="2"><input type="hidden" name="DoctorID" value=""><span id="docName"></span></td>
				</tr>
				<tr class="bgf1">
					<td class="bold">内容</td>
					<td colspan="5" class="bold">体检项目</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="5">
						<div id="symptomDiv">
						<span class="Noption">1无症状</span>
						<span class="optionMore">2头痛</span>
						<span class="optionMore">3头晕</span>
						<span class="optionMore">4心悸</span>
						<span class="optionMore">5胸闷</span>
						<span class="optionMore">6胸痛</span>
						<span class="optionMore">7慢性咳嗽</span>
						<span class="optionMore">8咳痰</span>
						<span class="optionMore">9呼吸困难</span>
						<span class="optionMore">10多饮</span>
						<span class="optionMore">11多尿</span>
						<span class="optionMore">12体重下降</span>
						<span class="optionMore">13乏力</span>
						<span class="optionMore">14关节肿痛</span>
						<span class="optionMore">15视力模糊</span>
						<span class="optionMore">16手脚麻木</span>
						<span class="optionMore">17尿急</span>
						<span class="optionMore">18尿痛</span>
						<span class="optionMore">19便秘</span>
						<span class="optionMore">20腹泻</span>
						<span class="optionMore">21恶心呕吐</span>
						<span class="optionMore">22烟花</span>
						<span class="optionMore">23耳鸣</span>
						<span class="optionMore">24乳房胀痛</span>
						<span class="optionMore">25其他<input type="text" name="SymptomOther" id="SymptomOther" value=""  class="borderb"/></span>
					</div>
					</td>
				</tr>
				
				<form id="bodyForm">
				<tr>
					<td rowspan="9">一<br />般<br />状<br />态 </td>
					<td>体温</td>
					<td><input type="text" name="BodyTemperature" id="BodyTemperature" value="" class="borderb w40"/> ℃</td>
					<td>脉率 <span class="bluecolor">?</span></td>
					<td><input type="text" name="PulseRate" id="PulseRate" value="" class="borderb w40"/>次/分钟</td>
					<td>/</td>
				</tr>
				<tr>
					<td rowspan="2">呼吸频率</td>
					<td rowspan="2"><input type="number" name="RespiratoryRate" id="RespiratoryRate" value="" class="borderb w40"/>次/分钟</td>
					<td rowspan="2">血压 <span class="bluecolor">?</span></td>
					<td>左侧</td>
					<td>
						<input type="number" name="LeftSbp" id="LeftSbp" value=""  class="borderb w20"/>/
						<input type="number" name="LeftDbp" id="LeftDbp" value=""  class="borderb w20"/>
						mmHg
					</td>
				</tr>
				<tr>
					<td>右侧</td>
					<td>
						<input type="number" name="RightSbp" id="RightSbp" value=""  class="borderb w20"/>/
						<input type="number" name="RightDbp" id="RightDbp" value=""  class="borderb w20"/>
						mmHg
					</td>
				</tr>
				<tr>
					<td>身高</td>
					<td><input type="number" name="Height" id="Height" value=""  class="borderb w40"/>cm</td>
					<td>体重</td>
					<td><input type="number" name="Weight" id="Weight" value=""  class="borderb w40"/>kg</td>
					<td>/</td>
				</tr>
				<tr>
					<td>腰围</td>
					<td><input type="number" name="Waistline" id="Waistline" value=""  class="borderb w40"/>cm</td>
					<td>体质指数(BMI) <span class="bluecolor">?</span></td>
					<td><input type="number" name="Bmi" id="Bmi" value=""  class="borderb w40"/>kg/m²</td>
					<td>/</td>
				</tr>
				</form>
				<tr>
					<td>老年人健康状态自我评估 <span class="redcolor">*</span></td>
					<td colspan="4" id="Health">
						<span class="option">1满意</span>
						<span class="option">2基本满意</span>
						<span class="option">3说不清楚</span>
						<span class="option">4不太满意</span>
						<span class="option">5不满意</span>
					</td>
				</tr>
				<tr>
					<td>老年人生活自理能力自我评估 <span class="redcolor">*</span></td>
					<td colspan="4" id="LifeSkills">  
						<span class="option">1可自理(0～3分)</span>
						<span class="option">2轻度依赖(4～8分)</span>
						<span class="option">3中度依赖(9～18分)</span>
						<span class="option">4不能自理(≥19分)</span>
						<span class="option">总分
							<input type="number" name="LifeSkillsScore" id="" value=""  class="w40 borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>老年人认知功能 <span class="redcolor">*</span></td>
					<td colspan="4" id="CognitiveFunction">
						<span class="option">1粗筛阴性</span>
						<span class="option">2粗阳性，简易智力状态检查</span>
						<span class="option">总分
							<input type="number" name="CognitiveFunctionScore" id="" value=""  class="w40 borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>老年人非感情状态 <span class="redcolor">*</span></td>
					<td colspan="4" id="EmotionalState">
						<span class="option">1粗筛阴性</span>
						<span class="option">2粗阳性，老年人抑郁评分检查</span>
						<span class="option">总分
							<input type="number" name="EmotionalStateScore" id="" value=""  class="w40 borderb"/>
						</span>
					</td>
				</tr>
				<form id="LifeStyleForm">
				<tr>
					<td rowspan="19"> 生<br />活<br />方<br />式 </td>
					<td rowspan="3">体育锻炼</td>
					<td>锻炼频率</td>
					<td id="ExerciseFrequency">
						<span class="option" >1每天</span>
						<span class="option">2每周一次以上</span>
						<span class="option">3偶尔</span>
						<span class="option">4不锻炼</span>
					</td>
				</tr>
				<tr>
					<td>每次锻炼时间</td>
					<td><input type="number" name="EachExerciseTime" id="EachExerciseTime" value="" class="borderb w40" />分钟</td>
					<td>坚持锻炼时间</td>
					<td><input type="number" name="ExerciseTime" id="ExerciseTime" value=""  class="borderb w40"/>年</td>
				</tr>
				<tr>
					<td>锻炼方式</td>
					<td colspan="4"> <input type="text" name="ExerciseMethod" id="ExerciseMethod" value="" class="borderb" style="width: 200px;"/></td>
				</tr>
				
				<tr>
					<td>饮食习惯</td>
					<td colspan="5">
					    <div id="DietDiv">
						<span class="option">1荤素均衡</span>
						<span class="option">2荤食为主</span>
						<span class="option">3素食为主</span>
						<span class="option">4嗜盐</span>
						<span class="option">4嗜油</span>
						<span class="option">6嗜糖</span>
						</div>
					</td>
				</tr>
				<tr>	
					<td rowspan="3">
						吸烟情况 <span class="bluecolor">?</span>
					</td>
					<td>吸烟状况</td>
					<td colspan="3">
					    <div id="SmokingStatus">
						<span class="option">1从不吸烟</span>
						<span class="option">2已戒烟</span>
						<span class="option">3吸烟</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>日吸烟量</td>
					<td colspan="3">平均 <input type="number" name="Smoking" id="Smoking" value=""  class="borderb w40"/>只</td>
				</tr>
				<tr>
					<td>开始吸烟年龄</td>
					<td colspan="3">
						<span class="">
							<input type="number" name="SmokingAge" id="SmokingAge" value="" class="borderb w40"/>岁
						</span>
						<span class="seat"></span>
						<span class="">
							戒烟年龄
							<input type="number" name="AgeQuit" id="AgeQuit" value="" class="borderb w40"/>岁
						</span>
					</td>
				</tr>
				
				<tr>
					<td rowspan="5">饮酒情况 <span class="bluecolor">?</span></td>
					<td>饮酒频率</td>
					<td colspan="3" id="DrinkingFrequencyDiv">
					    
						<span class="option">1从不</span>
						<span class="option">2偶尔</span>
						<span class="option">3经常</span>
						<span class="option">4每天</span>
						
					</td>
				</tr>
				<tr>	
					<td>日饮酒量</td>
					<td colspan="3">
						平均 <input type="number" name="DailyAlcoholIntake" id="DailyAlcoholIntake" value="" class="borderb w40"/>两
					</td>
				</tr>
				<tr>
					<td>是否戒酒</td>
					<td colspan="3" id="AlcoholDiv">
					    
						<span class="option">1未戒酒</span>
						<span class="option">2已戒酒</span>
						戒酒年龄 
						<input type="number" name="AlcoholAge" id="AlcoholAge" value=""  class="borderb w20"/>岁
						
					</td>
				</tr>
				<tr>
					<td>开始饮酒年龄</td>
					<td colspan="1">
						<input type="number" name="AgeStartedDrinking" id="AgeStartedDrinking" value="" class="borderb w20"/>岁
					</td>
					<td>近一年是否醉酒</td>
					<td id="IsDrunkLastYearOp">
							<span class="option">1是</span>
							<span class="option">2否</span>
					</td>
				</tr>
				<tr>
					<td>饮酒种类</td>
					<td colspan="3">
					        <div id="AlcoholTypeDiv">
							<span class="option">1白酒</span>
							<span class="option">2啤酒</span>
							<span class="option">3红酒</span>
							<span class="option">4黄酒</span>
							<span class="option">5其他</span>
							</div>
					</td>
				</tr>
				</form>
				<form id="OePostion">
				<tr>
					<td rowspan="7">职业病危害因素接触史 <span class="bluecolor">?</span></td>
					<td colspan="4">
					    <div id="IsOeDiv">
						<span class="option">1无</span>
						<span class="option">2有</span>
						(工种 <input type="text" name="Occupation" id="Occupation" value="" class="borderb"/>从业时间
						<input type="number" name="WorkingTime" id="WorkingTime" value="" class="w20 borderb"/>年
						)
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="5">毒物种类</td>
				</tr>
				
				<tr id="tr0" class="row-poison">
					<td colspan="1">
						粉尘 <input type="text" name="PoisonName" id="" value="" class="borderb"/>
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1无</span>
						<span class="option">2有</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb"/>
					</td>
				</tr>  
				<tr id="tr1" class="row-poison"> 
					<td colspan="1">
						放射性物质 <input type="text" name="PoisonName" id="" value="" class="borderb"/>
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1是</span>
						<span class="option">2否</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb"/>
					</td>
				</tr>
				
				<tr id="tr2" class="row-poison">
					<td colspan="1">
						物理因素 <input type="text" name="PoisonName" id="" value="" class="borderb"/>
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1是</span>
						<span class="option">2否</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb"/>
					</td>
				</tr>
				
				<tr id="tr3" class="row-poison">
					<td >
						化学因素 <input type="text" name="PoisonName" id="" value="" class="borderb"/>
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1是</span>
						<span class="option">2否</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb"/>
					</td>
				</tr>
				
				<tr id="tr4" class="row-poison">
					<td >
						其他 <input type="text" name="PoisonName" id="" value="" class="borderb"/>
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1是</span>
						<span class="option">2否</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb"/>
					</td>
				</tr>
				</form>
				<form id="OrganForm">
				<tr>
					<td rowspan="6">脏<br />器<br />功<br />能</td>
					<td rowspan="3">口腔</td>
					<td>口唇</td>
					<td colspan="3"  id="Lips-option" >
						<span class="option">1红润</span>
						<span class="option">2苍白</span>
						<span class="option">3发绀</span>
						<span class="option">4皲裂</span>
						<span class="option">5疱疹</span>
						<input type="number" name="" id="" value="" class="w20 borderb"/>
					</td>
				</tr>
				<tr >
					<td>齿列</td>
					<td colspan="3" id="Dentition">
						<span class="option">1正常</span>
						<span class="option">2缺齿</span>
						<span class="option">3龋齿</span>
						<span class="option">4义齿(假牙)</span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr >
					<td>咽部</td>
					<td colspan="3" id="Throat">
						<span class="option">1</span>
						<span class="option">2</span>
						<span class="option">3</span>
						<span class="option">4</span>
						<span class="option">5</span>
						<span class="option">6</span>
						<span class="option">7</span>
						<span class="option">8</span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr >
					<td>视力 <span class="bluecolor">?</span></td>
					<td colspan="4">
						左眼 <input type="number" name="LeftEye" id="" value="" class="borderb w40"/>
						右眼 <input type="number" name="RightEye" id="" value="" class="borderb w40"/>
						(矫正视力：
						左眼 <input type="number" name="LeftEyeVc" id="" value="" class="borderb w40"/>
						右眼 <input type="number" name="RightEyeVc" id="" value="" class="borderb w40"/>
						)
					</td>
				</tr>
				<tr >
					<td>听力 <span class="bluecolor">?</span></td>
					<td colspan="4" id="Hearing">
						<span class="option">1听见</span>
						<span class="option">2听不清或无法听见</span>
						<input type="number" name="" id="" value="" class="borderb w20" />
					</td>
				</tr>
				<tr>
					<td>运动能力<span class="bluecolor">?</span></td>
					<td colspan="4" id="MotorFunction">
						<span class="option">1可顺利完成</span>
						<span class="option">2无法独立完成气质任何一个动作</span>
						<input type="number" name="" id="" value="" class="borderb w20" />
					</td>
				</tr>
				<tr >
					<td rowspan="4">
						脏<br />器<br />功<br />能 
					</td>
					<td>眼底 <span class="redcolor">*</span></td>
					<td colspan="4" id="Fundus">
						<span class="option">1正常</span>
						<span class="option">2异常<input type="text" name="FundusReason" id="FundusReason" value=""  class="borderb w40"/></span>
						
						
						<input type="number" name="" id="" value="" class="w20 borderb"/>
					</td>
				</tr>
				<tr id="Skin">
					<td>皮肤</td>
					<td colspan="4">
						<span class="option">1正常</span>
						<span class="option">2潮红</span>
						<span class="option">3苍白</span>
						<span class="option">4发绀</span>
						<span class="option">5染黄</span>
						<span class="option">6色素沉着</span>
						<span class="option">7其他<input type="text" name="" id="" value=""  class="borderb"/></span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr id="Sclera">
					<td>巩膜</td>
					<td colspan="4">
						<span class="option">1正常</span>
						<span class="option">2染黄</span>
						<span class="option">3充血</span>
						<span class="option">4其他<input type="text" name="" id="" value=""  class="borderb"/></span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr id="LymphNodes">
					<td>淋巴结</td>
					<td colspan="4">
						<span class="option">1未触及</span>
						<span class="option">2锁骨上</span>
						<span class="option">3腋窝</span>
						<span class="option">4其他<input type="text" name="" id="" value=""  class="borderb"/></span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td rowspan="20">
						查<br />体 
					</td>
					<td rowspan="3">
						肺
					</td>
					<td>桶状胸：</td>
					<td colspan="3" id="BarrelChest">
						<span class="option">1否</span>
						<span class="option">2是</span>
						<input type="number" name="" id="" value="" class="borderb w20" />
					</td>
				</tr>
				<tr id="BreathSounds">
					<td>呼吸音：</td>
					<td colspan="3">
						<span class="option">1正常</span>
						<span class="option">
							2异常
							<input type="text" name="BreathSoundsReason" id="BreathSoundsReason" value="" class="borderb" />
						</span>
					</td>
				</tr>
				<tr id="Rale">
					<td>罗音：</td>
					<td colspan="3">
						<span class="option">1无</span>
						<span class="option">2干罗音</span>
						<span class="option">3湿罗音</span>
						<span class="option">4其他<input type="text" name="" id="" value="" class="borderb" /></span>
						<input type="number" name="" id="" value="" class="borderb w20" />
					</td>
				</tr>
				<tr >
					<td rowspan="2">心脏</td>
					<td>心率：</td>
					<td><input type="number" name="" id="" value=""  class="w20 borderb"/> 次/分钟</td>
					<td>心律</td>
					<td id="Rhythm">
						<span class="option">1齐</span>
						<span class="option">2不齐</span>
						<span class="option">3绝对不齐</span>
						<input type="number" name="" id="" value=""  class="w20 borderb"/>
					</td>
				</tr>
				<tr >
					<td>杂音：</td>
					<td colspan="3"  id="HeartMurmur">
						<span class="option">1无</span>
						<span class="option">2有<input type="text" name="HeartMurmurReason" id="HeartMurmurReason" value=""  class=" borderb"/> </span>
						<input type="number" name="" id="" value=""  class="w20 borderb"/>
					</td>
				</tr>
				<tr>
					<td rowspan="5">
						腹部
					</td>
					<td>压痛：</td>
					<td colspan="3" id="AbdominalTenderness">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="AbdominalTendernessReason" id="AbdominalTendernessReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>包块：</td>
					<td colspan="3" id="AbdominalMass">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="AbdominalMassReason" id="AbdominalMassReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>肝大：</td>
					<td colspan="3" id="TheAbdomenLiver">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="TheAbdomenLiverReason" id="TheAbdomenLiverReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>脾大：</td>
					<td colspan="3" id="Splenomegaly">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="SplenomegalyReason" id="SplenomegalyReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>移动性浊音：</td>
					<td colspan="3" id="ShiftingDullness">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="ShiftingDullnessReason" id="ShiftingDullnessReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>	
					<td>下肢水肿</td>
					<td colspan="4" id="LowerExtremityEdema">
						<span class="option">1无</span>
						<span class="option">2单侧</span>
						<span class="option">3双侧不对称</span>
						<span class="option">4双侧对称</span>
						<input type="number" name="" id="" value=""  class="w20 borderb"/>
					</td>
				</tr>
				<tr>
					<td>足背动脉搏动 <span class="bluecolor">?</span></td>
					<td colspan="4" id="DorsalisPedisArteryPulse">
						<span class="option">1未触及</span>
						<span class="option">2触及双侧对称</span>
						<span class="option">3触及左侧弱或消失</span>
						<span class="option">4触及右侧弱或消失</span>
						<input type="number" name="" id="" value=""  class="w20 borderb"/>
					</td>
				</tr>
				<tr>
					<td>肛门指诊 <span class="redcolor">*</span></td>
					<td colspan="4" id="Dre">
						<span class="option">1未及异常</span>
						<span class="option">2触痛</span>
						<span class="option">3包块</span>
						<span class="option">4前列腺异常</span>
						<span class="option">5其他
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
						<input type="number" name="" id="" value=""  class="w20 borderb"/>
					</td>
				</tr>
				<tr>
					<td>乳腺  <span class="redcolor">*</span><span class="bluecolor">?</span></td>
					<td colspan="4" id="Breast">
						<span class="option">1未及异常</span>
						<span class="option">2触痛</span>
						<span class="option">3包块</span>
						<span class="option">4前列腺异常</span>
						<span class="option">5其他
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
						<input type="number" name="" id="" value=""  class="w20 borderb"/>
					</td>
				</tr>
				</form>
				<tr>
					<td rowspan="5">妇科 <span class="redcolor">*</span></td>
					<td>外阴 <span class="bluecolor">?</span></td>
					<td colspan="3" id="Vulva">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="VulvaReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>阴道 <span class="bluecolor">?</span></td>
					<td colspan="3" id="Vaginal">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="VaginalReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>宫颈 <span class="bluecolor">?</span></td>
					<td colspan="3" id="Cervix">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="CervixReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>宫体 <span class="bluecolor">?</span></td>
					<td colspan="3" id="Palace">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="PalaceReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>附件 <span class="bluecolor">?</span></td>
					<td colspan="3" id="UterineAdnexa">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="UterineAdnexaReason" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>其他 <span class="redcolor">*</span></td>
					<td colspan="4"><input type="text" name="Other" id="" value="" class="borderb"/></td>
				</tr>
				<form id="Labor">
				<tr>
					<td rowspan="21">辅<br />助<br />检<br />查 </td>
					<td rowspan="2">血常规<span class="redcolor">*</span></td>
					<td colspan="1">
						血红蛋白<span class="bluecolor">?</span>
						<input type="number" name="Hemoglobin" id="" value="" class="borderb w40"/>mol/L
					</td>
					<td colspan="1">
						白细胞<span class="bluecolor">?</span> 
						<input type="number" name="Leukocyte" id="" value="" class="borderb w40"/>X10 <sup>9</sup>/L
					</td>
					<td colspan="1">
						血小板 <span class="bluecolor">?</span>
						<input type="number" name="Platelet" id="" value="" class="borderb w40"/>
					</td>
					<td>/</td>
				</tr>
				<tr>
					<td colspan="4">
						其他 
						<input type="text" name="OtherBlood" id="" value="" class="borderb"/>
					</td>
				</tr>
				<tr class="Urines">
					<td rowspan="3">尿常规 <span class="redcolor">*</span><span class="bluecolor">?</span></td>
					<td colspan="1">
						尿蛋白(PRO) 
						<select name="UrineProtein" class="borderb">
							<option value="">+++++</option>
							<option value="">++++</option>
							<option value="">+++</option>
							<option value="">++</option>
						</select>
					</td>
					<td colspan="1">
						尿糖(GLU) 
						<select name="Urine" class="borderb">
							<option value="">+++++</option>
							<option value="">++++</option>
							<option value="">+++</option>
							<option value="">++</option>
						</select>
					</td>
					<td colspan="1">
						尿酮体(GLU) 
						<select name="Ketone" class="borderb">
							<option value="">+++++</option>
							<option value="">++++</option>
							<option value="">+++</option>
							<option value="">++</option>
						</select>
					</td>
					<td colspan="1">
						尿潜血(BLO) 
						<select name="OccultBloodInUrine" class="borderb">
							<option value="">+++++</option>
							<option value="">++++</option>
							<option value="">+++</option>
							<option value="">++</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						其他 <input type="text" name="OtherUrine" id="" value=""  class="borderb"/>
					</td>
				</tr>
				<tr>
					<td>空腹血糖(FPG) <span class="redcolor">*</span><span class="bluecolor">?</span></td>
					<td><input type="number" name="FastingBloodGlucose" id="" value="" class="borderb w40"/>mmol/L</td>
					<td>随机血糖</td>
					<td><input type="number" name="RandomBloodGlucose" id="" value="" class="borderb w40"/>mmol/L</td>
				</tr>
				<tr>
					<td>心电图<span class="redcolor">*</span></td>
					<td colspan="4" id="Ecg">
						<span class="option">1正常</span>
						<span class="option">2异常 
							<input type="text" name="" id="EcgReason" value=""  class="borderb"/>
						</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>尿微量白蛋白(U-MA)<span class="redcolor">*</span></td>
					<td colspan="4">
						<input type="number" name="UrinaryAlbumin" id="" value="" class="borderb w40"/>mg/dL
					</td>
				</tr>
				<tr>
					<td>大便潜血(FOB)<span class="redcolor">*</span></td>
					<td colspan="4" id="FecalOccultBlood">
						<span class="option">1阴性</span>
						<span class="option">2阳性</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>糖化血红蛋白(HbA1c)<span class="redcolor">*</span></span><span class="bluecolor">?</span></td>
					<td colspan="4">
						<input type="number" name="GlycatedHemoglobin" id="" value="" class="w40"/>%
					</td>
				</tr>
				<tr>
					<td>乙型肝炎表面抗原(HBsAg)<span class="redcolor">*</span></span></td>
					<td colspan="4" id="HepatitisBSurfaceAntigen">
						<span class="option">1阴性</span>
						<span class="option">2阳性</span>
					</td>
				</tr>
				<tr>
					<td rowspan="3">肝功能<span class="redcolor">*</span></td>
					<td colspan="2">
						血清谷丙转氨酶(ALT) <span class="bluecolor">?</span>
						<input type="number" name="SerumAla" id="" value="" class="borderb w40"/>U/L
					</td>
					<td colspan="2">
						血清谷草转氨酶(AST) <span class="bluecolor">?</span>
						<input type="number" name="SerumAa" id="" value="" class="borderb w40"/>U/L
					</td>
				</tr>
				<tr>
					<td colspan="2">
						白蛋白(ALB) 
						<input type="number" name="Albumin" id="" value="" class="borderb w40"/>g/L
					</td>
					<td colspan="2">
						总胆红素(TBIL) <span class="bluecolor">?</span>
						<input type="number" name="TotalBilirubin" id="" value="" class="borderb w40"/>μ mol/L
					</td>
				</tr>
				<tr>
					<td colspan="2">
						结合胆红素(SDB) 
						<input type="number" name="Bilirubin" id="" value="" class="borderb w40"/>μ mol/L
					</td>
					<td colspan="2">/</td>
				</tr>
				<tr>
					<td rowspan="2">肾功能<span class="redcolor">*</span> <span class="bluecolor">?</span></td>
					<td colspan="2">
					血清肌酐(SCR) <span class="bluecolor">?</span>
					<input type="number" name="SerumCreatinine" id="" value="" class="borderb w40"/>μ mol/L
					</td>
					<td colspan="2">
						血尿素氮 <span class="bluecolor">?</span>
						<input type="number" name="Bun" id="" value="" class="borderb w40"/>mmol/L
					</td>
				</tr>
				<tr>
					<td colspan="2">
					血钾浓度(K+) 
					<input type="number" name="PotassiumConcentration" id="" value="" class="borderb w40"/>mmol/L
					</td>
					<td colspan="2">
						血钠浓度(Na+)
						<input type="number" name="SodiumConcentration" id="" value="" class="borderb w40"/>mmol/L
					</td>
				</tr>
				<tr>
					<td rowspan="2">血脂</td>
					<td colspan="2">
						总胆固醇(TC) <span class="bluecolor">?</span>
						<input type="number" name="TotalCholesterol" id="" value="" class="borderb w40"/>mmol/L
					</td>
					<td colspan="2">
						甘油三酯(TG) <span class="bluecolor">?</span>
						<input type="number" name="Triglycerides" id="" value="" class="borderb w40"/>mmol/L
					</td>
				</tr>
				<tr>
					<td colspan="2">
						血清低密度脂蛋白胆固醇(LDL-C)
						<input type="number" name="LdlCholesterol" id="" value="" class="borderb w40"/>mmol/L
					</td>
					<td colspan="2">
						血清高密度脂蛋白胆固醇(HDL-C)
						<input type="number" name="HdlCholesterol" id="" value="" class="borderb w40"/>mmol/L
					</td>
				</tr>
				<tr>
					<td>胸部X线片<span class="redcolor">*</span></td>
					<td colspan="4" id="ChestXRay">
						<span class="option">1正常</span>
						<span class="option">
							2异常
							<input type="text" name="" id="ChestXRayReason" value=""  class="borderb"/>
						</span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>B 超<span class="redcolor">*</span></td>
					<td colspan="4" id="BUltrasonicWave">
						<span class="option">1正常</span>
						<span class="option">
							2异常
							<input type="text" name="" id="BUltrasonicWaveReason" value=""  class="borderb"/>
						</span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>宫颈涂片<span class="redcolor">*</span></td>
					<td colspan="4" id="CervicalSmear">
						<span class="option">1正常</span>
						<span class="option">
							2异常
							<input type="text" name="" id="CervicalSmearReason" value=""  class="borderb"/>
						</span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>其他<span class="redcolor">*</span><span class="bluecolor">?</span></td>
					<td colspan="4">
						<input type="text" name="OtherLaboratory" id="" value=""  class="borderb"/>
					</td>
				</tr>
				</form>
				<form id="ChsCons">
				<tr>
					<td rowspan="9">
						中<br />医<br />体<br />质<br />辨<br />识<br /><span class="redcolor">*</span> 
					</td>
					<td>平和质</td>
					<td colspan="4" id="ModerateQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>气虚质</td>
					<td colspan="4" id="QualityDeficiency">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>阳虚质</td>
					<td colspan="4" id="YangQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>阴虚质</td id="YinQuality">
					<td colspan="4">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>痰湿质</td>
					<td colspan="4" id="Phlegm">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>湿热质</td>
					<td colspan="4" id="DampHeat">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>血瘀质</td>
					<td colspan="4" id="BloodQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>气郁质</td>
					<td colspan="4" id="QiQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>特秉质</td>
					<td colspan="4" id="TeBingQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				</form>
				<form>
				<tr>
					<td rowspan="7">现<br />存<br />主<br />要<br />健<br />康<br />问<br />题</td>
					<td>脑血管疾病</td>
					<td colspan="4" id="Cerebrovascular">
						<span class="option">1未发现</span>
						<span class="option">2缺血性卒中</span>
						<span class="option">3脑出血</span>
						<span class="option">4蛛网膜下腔出血</span>
						<span class="option">5短暂性脑缺血发作</span>
						<span class="option">
							6其他
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>肾脏疾病</td>
					<td colspan="4" id="Kidney">
						<span class="option">1未发现</span>
						<span class="option">2糖尿病肾病</span>
						<span class="option">3肾功能衰竭</span>
						<span class="option">4急性肾炎</span>
						<span class="option">5慢性肾炎</span>
						<span class="option">
							6其他
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>心脏疾病</td>
					<td colspan="4" id="Heart">
						<span class="option">1未发现</span>
						<span class="option">2心肌梗死</span>
						<span class="option">3心绞痛</span>
						<span class="option">4冠状动脉血运重建</span>
						<span class="option">5充血性心力衰竭</span>
						<span class="option">6心前区疼痛</span>
						<span class="option">
							7其他
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
					</td>
				<tr>
					<td>血管疾病</td>
					<td colspan="4" id="Vascular">
						<span class="option">1未发现</span>
						<span class="option">2夹层动脉瘤</span>
						<span class="option">3动脉闭塞性疾病</span>
						<span class="option">
							4其他
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>眼部疾病</td>
					<td colspan="4" id="Eyes">
						<span class="option">1未发现</span>
						<span class="option">2视网膜出血或渗出</span>
						<span class="option">3视乳头水肿</span>7
						<span class="option">4白内障</span>
						<span class="option">
							5其他
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>神经系统疾病</td>
					<td colspan="5" id="Nervoussystems">
						<span class="option">1未发现</span>
						<span class="option">2有
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				<tr>
					<td>其他系统疾病</td>
					<td colspan="5" id="Others">
						<span class="option">1未发现</span>
						<span class="option">2有
							<input type="text" name="" id="" value="" class="borderb"/>
						</span>
					</td>
				</tr>
				</form>
			   
				<tr>
					<td rowspan="4" class="inHospital_situation">住<br />院<br />治<br />疗<br />情<br />况</td> 
					<td rowspan="2" class="inHospital_history" >住院史</td>
					<td>入/出院日期</td>
					<td>原因</td>
					<td>医疗机构名称</td>
					<td>病案号  <a href="javascript:void(0)" class="add_drug">+</a></td>
				</tr>
				
				<tr  class="add_drugTr">
					<td>
						<input type="date" name="InDate" data-json-key="hosList" data-json-by="HistoryType:1" id="" value="" class="borderb"/>/
						<input type="date" name="OutDate" data-json-key="hosList" data-json-by="HistoryType:1" id="" value="" class="borderb"/>
					</td>
					<td>
						<input type="text" name="Reason" id=""  data-json-key="hosList" data-json-by="HistoryType:1" value="" class="borderb"/>
					</td>
					<td>
						<input type="text" name="OrgName" id="" data-json-key="hosList" data-json-by="HistoryType:1"  value="" class="borderb"/>
					</td>
					<td>
						<input type="text" name="MedicalRecordNumber" data-json-key="hosList" data-json-by="HistoryType:1" id="" value="" class="borderb"/> <a href="javascript:void(0)" class="delete_drug">X</a>
					</td>
				</tr>
				
			
				<tr>
					<td rowspan="2" class="inBed">家庭病床史</td>
					<td>建/撤床日期</td>
					<td>原因</td>
					<td>医疗机构名称</td>
					<td >病案号<a href="javascript:void(0)" class="add_bed">+</a></td>
				</tr>
				<tr  class="inBed_tr">
					<td>
						<input type="date" name="InDate" id="" data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb"/>/
						<input type="date" name="OutDate" id=""  data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb"/>
					</td>
					<td>
						<input type="text" name="Reason" id="" data-json-key="hosList" data-json-by="HistoryType:2"  value="" class="borderb"/>
					</td>
					<td>
						<input type="text" name="OrgName" id=""  data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb"/>
					</td>
					<td>
						<input type="text" name="MedicalRecordNumber" id=""  data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb"/> <a href="javascript:void(0)" class="delete_bed">X</a>
					</td>
				</tr>
				
				<form>
				<tr>
					<td rowspan="2" class="drug_situation">主要用药 情况</td>
					<td>药物名称</td>
					<td>用法</td>
					<td>用量</td>
					<td>用药时间</td>
					<td >服药依从性<a href="javascript:void(0)" class="add_drug2">+</a> <br />
						
					</td>
				</tr>
				<tr  class="drug_situation_tr">
					<td><input type="text" name="DrugName" id="" value="" class="borderb"/></td>
					<td><input type="text" name="Usage" id="" value="" class="borderb"/></td>
					<td><input type="text" name="Amount" id="" value="" class="borderb"/></td>
					<td>
						<input type="text" name="MedicationTime" id="" value="" class="borderb w40"/>
						<select class="MedicationUnit">
							<option value="1">天</option>
							<option value="2">月</option>
							<option value="3">年</option>
						</select>
					</td>
					<td class="MedicationCompliance">
					    <span class="option">1规律</span>
						<span class="option">2间断</span>
						<span class="option">3不服药</span>
						<a href="javascript:void(0)" class="delete_drug2" onclick="delete_drug2(this)">X</a>
					</td>
				</tr>
				</form>
				<form>
				<tr>
					<td rowspan="2"  class="inoculation">
						非免疫 规划预防接种史
					</td>
					<td>名称</td>
					<td>接种日期</td>
					<td colspan="3">接种机构 <a href="javascript:void(0)" class="add_inoculation">+</a></td>
				</tr>
				<tr id="vaccList" class="inoculation_tr">
					<td><input type="text" name="VaccineName" id="" value="" class="borderb"/></td>
					<td><input type="date" name="VaccDate" id="" value="" class="borderb"/></td>
					<td colspan="3"><input type="text" name="VaccOrgName" id="" value="" class="borderb"/> <a href="javascript:void(0)" class="delete_inoculation">X</a></td>
				</tr>
				</form>
				<tr>
					<td rowspan="2" class="health_assess">健康评价</td>
					<td colspan="5" id="Assessment">
						<span class="option">1体检无异常</span>
						<span class="option">2有异常</span>
						
					</td>
				</tr>
				<tr>
					<td colspan="5" class="health_assess_tr">
						结果： <input type="text" name="AssessmentAbnormal" id="" value="" class="borderb"/> <a href="javascript:void(0)" class="delete_health_assess">X</a>
					</td>
				</tr>
				<tr>
					<td rowspan="3">健康指导</td>
					<td>建议选项</td>
					<td colspan="5" id="Guidance">
						<span class="option">1 纳入慢性病患者健康管理 </span>
						<span class="option">2 建议复查  </span>
						<span class="option">3 纳入慢性病患者健康管理 </span>
						<input type="number" name="" id="" value="" class="borderb w20"/>
					</td>
				</tr>
				<tr>  
					<td>危险因素控制</td>
					<td colspan="4" id="RiskCrtl">
						<span class="option">1戒烟 </span>
						<span class="option">2健康饮酒 </span>
						<span class="option">3饮食 </span>
						<span class="option">4锻炼 </span>
						<span class="option">5减体重 (目标 <input type="number" name="RiskCrtlWeight" id="" value=""  class="borderb w40"/> KG) </span>
						<span class="option">6建议接种疫苗 <input type="text" name="RiskCrtlVaccine" id="" value=""  class="borderb"/></span>
						<span class="option">其他 <input type="text" name="RiskCrtlOther" id="" value=""  class="borderb"/></span>
						<input type="number" name="" id="" value=""  class="borderb w20"/>
					</td>
				</tr>
				<tr>
					<td>健康摘要</td>
					<td colspan="5">
						<textarea id="HealthSummary" name="HealthSummary" rows="" cols=""  style="width: 98%;"></textarea>
					</td>
				</tr>
			</table>
		
		
		
		
		<div class="mt20"></div>
		<div class="paging chartSearch">
            <ul>
            <li class="info">共6页，本页为第1页</li>
              <li class="disabled prev"><span>上一页</span></li>
              <li class="active"><span>1</span></li>
              <li><span>...</span></li>
              <li><a href="#">7</a></li>
              <li><a href="#">8</a></li>
              <li><a href="#">9</a></li>
              <li><span>...</span></li>
              <li><a href="#">10</a></li>
              <li class="next"><a href="#">下一页</a></li>
            </ul>
          </div>
          
          
          
          <div class="footer_btn">
          		<ul>
          			<li>
          				<a id="saveButton">保存</a>
          			</li>
          			<li>
          				<a href="/">保存并转健康教育</a>
          			</li>
          			<li>
          				<a href="/">删除</a>
          			</li>
          		</ul>
          </div>
	</div>
</body>
<script type="text/javascript">

var PersonIDStr='${PersonID}'

$('.optionMore').on('click',function(){
	var op=$(this);
	op.toggleClass('bluecolor');
	$(this).siblings('.Noption').removeClass('bluecolor');
});
$(".Noption").on('click',function(){
	$(this).addClass('bluecolor').siblings().removeClass('bluecolor');
});
$(".option").on('click',function(){
	$(this).addClass('bluecolor').siblings().removeClass('bluecolor');
})
 
function getFieldSpanValue(sss){
    var fliedvalue=0;
   
    $.each(sss,function(i){
		if($(this).hasClass('bluecolor')){
			fliedvalue=i+1;
		}
	});
    
	return fliedvalue;
}

//点击添加；

$('.delete_drug').eq(0).hide();
$('.add_drug').on('click',function(){
	
	//住院史,和住院情况的高度+1;
	var rowspan1=parseInt($('.inHospital_situation').attr("rowspan"));
	var rowspan2=parseInt($('.inHospital_history').attr("rowspan"));
	$('.inHospital_situation').attr("rowspan",rowspan1+1);
	$('.inHospital_history').attr("rowspan",rowspan2+1);
	//克隆自己添加在后面；
	var add_tr='<tr class="add_drugTr">'+
	'					<td>'+
	'						<input type="date" name="" id="" value="" class="borderb"/>/'+
	'						<input type="date" name="" id="" value="" class="borderb"/>'+
	'					</td>'+
	'					<td>'+
	'						<input type="text" name="" id="" value="" class="borderb"/>'+
	'					</td>'+
	'					<td>'+
	'						<input type="text" name="" id="" value="" class="borderb"/>'+
	'					</td>'+
	'					<td>'+
	'						<input type="text" name="" id="" value="" class="borderb"/> <a href="javascript:void(0)" onclick="delete_drug(this);">X</a>'+
	'					</td>'+
	'				</tr>';
	

	/* var add_tr=$(".add_drugTr").eq(-1).clone(true);
	add_tr.find("input").val(""); */
	$(".add_drugTr").eq(-1).after(add_tr);
	//除了第一个x隐藏 其他的显示出来;
	$('.delete_drug').show();
	$('.delete_drug').eq(0).hide();
	
	
});
//点击删除；
function delete_drug(x){
	
	//rowspan -1；
	var rowspan1=parseInt($('.inHospital_situation').attr("rowspan"));
	var rowspan2=parseInt($('.inHospital_history').attr("rowspan"));
	$('.inHospital_situation').attr("rowspan",rowspan1-1);
	$('.inHospital_history').attr("rowspan",rowspan2-1);
	//让他这一行消失；
	$(x).parents('tr').remove();
	
	return false;
}

//病床添加删除
$('.delete_bed').eq(0).hide();
$('.add_bed').on('click',function(){
	//住院史,和住院情况的高度+1;
	var rowspan1=parseInt($('.inHospital_situation').attr("rowspan"));
	var rowspan3=parseInt($('.inBed').attr("rowspan"));
	$('.inHospital_situation').attr("rowspan",rowspan1+1);
	$('.inBed').attr("rowspan",rowspan3+1);
	//克隆自己添加在后面；
	//$(".inBed_tr").eq(-1).after($(".inBed_tr").eq(-1).clone(true));
	//$(".inBed_tr").eq(-1).find('input').val("")
	var add_tr='<tr class="inBed_tr">'+
	'					<td>'+
	'						<input type="date" name="" id="" value="" class="borderb"/>/'+
	'						<input type="date" name="" id="" value="" class="borderb"/>'+
	'					</td>'+
	'					<td>'+
	'						<input type="text" name="" id="" value="" class="borderb"/>'+
	'					</td>'+
	'					<td>'+
	'						<input type="text" name="" id="" value="" class="borderb"/>'+
	'					</td>'+
	'					<td>'+
	'						<input type="text" name="" id="" value="" class="borderb"/> <a href="javascript:void(0)" onclick="delete_bed(this);">X</a>'+
	'					</td>'+
	'				</tr>';

	$(".inBed_tr").eq(-1).after(add_tr);
	//除了第一个x隐藏 其他的显示出来;
	$('.delete_bed').show();
	$('.delete_bed').eq(0).hide();
	return false;
});
//点击删除；
function delete_bed(x){
	//rowspan -1；
	var rowspan1=parseInt($('.inHospital_situation').attr("rowspan"));
	var rowspan3=parseInt($('.inBed').attr("rowspan"));
	$('.inHospital_situation').attr("rowspan",rowspan1-1);
	$('.inBed').attr("rowspan",rowspan3-1);
	//让他这一行消失；
	$(x).parents('tr').remove();
	return false;
}


//药品情况添加删除
$('.delete_drug2').eq(0).hide();
$('.add_drug2').on('click',function(){
	//高度+1;
	var rowspan1=parseInt($('.drug_situation').attr("rowspan"));
	$('.drug_situation').attr("rowspan",rowspan1+1);
	//克隆自己添加在后面；
	var add_tr='	<tr  class="drug_situation_tr">'+
			'					<td><input type="text" name="DrugName" id="" value="" class="borderb"/></td>'+
			'					<td><input type="text" name="Usage" id="" value="" class="borderb"/></td>'+
			'					<td><input type="text" name="Amount" id="" value="" class="borderb"/></td>'+
			'					<td>'+
			'						<input type="text" name="MedicationTime" id="" value="" class="borderb w40"/>'+
			'						<select class="MedicationUnit">'+
			'							<option value="1">天</option>'+
			'							<option value="2">月</option>'+
			'							<option value="3">年</option>'+
			'						</select>'+
			'					</td>'+
			'					<td class="MedicationCompliance">'+
			'					    <span class="option">1规律</span>'+
			'						<span class="option">2间断</span>'+
			'						<span class="option">3不服药</span>'+
			'						<a href="javascript:void(0)" onclick="delete_drug2(this)">X</a>'+
			'					</td>'+
			'				</tr>';
			$(".drug_situation_tr").eq(-1).after(add_tr);
	//$(".drug_situation_tr").eq(-1).after($(".drug_situation_tr").eq(-1).clone(true));
	$(".drug_situation_tr").eq(-1).find('input').val("")
	$(".drug_situation_tr").eq(-1).find('span')
	//除了第一个x隐藏 其他的显示出来;
	$('.delete_drug2').show();
	$('.delete_drug2').eq(0).hide();
	return false;
});
//点击删除；
function delete_drug2(x){
	
	//rowspan -1；
	var rowspan1=parseInt($('.drug_situation').attr("rowspan"));
	$('.drug_situation').attr("rowspan",rowspan1-1);
	//让他这一行消失；
	$(x).parents('tr').remove();
	return false;
}

//接种添加删除
$('.delete_inoculation').eq(0).hide();	
$('.add_inoculation').on('click',function(){
	//住院史,和住院情况的高度+1;
	var rowspan1=parseInt($('.inoculation').attr("rowspan"));
	$('.inoculation').attr("rowspan",rowspan1+1);
	//克隆自己添加在后面；
	$(".inoculation_tr").eq(-1).after($(".inoculation_tr").eq(-1).clone(true));
	$(".inoculation_tr").eq(-1).find('input').val("")
	//除了第一个x隐藏 其他的显示出来;
	$('.delete_inoculation').show();
	$('.delete_inoculation').eq(0).hide();
	return false;
});
//点击删除；
$('.delete_inoculation').on('click',function(){
	//rowspan -1；
	var rowspan1=parseInt($('.inoculation').attr("rowspan"));
	$('.inoculation').attr("rowspan",rowspan1-1);
	//让他这一行消失；
	$(this).parents('tr').remove();
	return false;
});

//健康评价 添加删除
	
$('.add_health_assess').on('click',function(){
	//住院史,和住院情况的高度+1;
	var rowspan1=parseInt($('.health_assess').attr("rowspan"));
	$('.health_assess').attr("rowspan",rowspan1+1);
	//克隆自己添加在后面；
	$(".health_assess_tr").eq(-1).after($(".health_assess_tr").eq(-1).clone(true));
	//除了第一个x隐藏 其他的显示出来;
	$('.delete_health_assess').show();
	$('.delete_health_assess').eq(0).hide();
	return false;
});
//点击删除；
$('.delete_health_assess').on('click',function(){
	//rowspan -1；
	var rowspan1=parseInt($('.health_assess').attr("rowspan"));
	$('.health_assess').attr("rowspan",rowspan1-1);
	//让他这一行消失；
	$(this).parents('tr').remove();
	return false;
});
	

var all_symp = $('#symptomDiv span');//症状
var all_SmokingStatus=$('#SmokingStatusDiv span.option');//吸烟情况
var all_DrinkingFrequency=$('#DrinkingFrequencyDiv span.option');//饮酒情况
var all_Diet=$('#DietDiv span.option');//饮食情况
var all_Alcohol=$('#AlcoholDiv span.option');//是否戒酒AlcoholTypeDiv
var all_AlcoholType=$('#AlcoholTypeDiv span.option');//饮酒种类 
var all_IsOe=$('#IsOeDiv span.option');//是否职业暴露
var all_Guidance=$('#Guidance span')
var all_RiskCrtl=$('#RiskCrtl span')


function getBlueIds(sss){
	var blueId=0;//初始化症状的值
	sss.each(function(i){
		var id = Math.pow(2, i);
		
		if($(this).hasClass('bluecolor')){
			if(i==0){
				blueId=1
				return
			}else{
				blueId=blueId+id;
			}
		}
	});
	
	return blueId;
}

//体征封装


$("#saveButton").on('click',function(){


var Bodydate=$('#bodyForm').serializeArray();
var Body={}
for(i=0;i<Bodydate.length;i++){
	Body[Bodydate[i].name] = Bodydate[i].value;
}
Body['DorsalisPedisArteryPulse']=getFieldSpanValue($('#DorsalisPedisArteryPulse span'))
//生活方式封装
var lifestyleData=$('#LifeStyleForm').serializeArray();
var Diet=getFieldSpanValue(all_Diet);
var SmokingStatus=getFieldSpanValue(all_SmokingStatus);
var DrinkingFrequency=getFieldSpanValue(all_DrinkingFrequency);
var IsAlcohol=getFieldSpanValue(all_Alcohol);
var all_IsDrunkLastYearOp=$('#IsDrunkLastYearOp span')
var IsDrunkLastYear=getFieldSpanValue(all_IsDrunkLastYearOp);
var AlcoholType=getFieldSpanValue(all_AlcoholType);
var IsOe=getFieldSpanValue(all_IsOe); 
var LifeStyle = {};
for(i=0;i<lifestyleData.length;i++){
	LifeStyle[lifestyleData[i].name] = lifestyleData[i].value;
}
LifeStyle['Diet']=Diet;
LifeStyle['SmokingStatus']=SmokingStatus;
LifeStyle['DrinkingFrequency']=DrinkingFrequency;
LifeStyle['IsAlcohol']=IsAlcohol;
LifeStyle['IsDrunkLastYear']=IsDrunkLastYear;
LifeStyle['AlcoholType']=AlcoholType;
LifeStyle['IsOe']=IsOe;

//职业病危害 封装
//var OePostionDate=$('#OePostionForm').serializeArray();
var OePostion =[];
$('.row-poison').each(function(i){
       var that = $(this);
       var name = that.find('input[name=PoisonName]');
       var text = that.find('input[name=ProtectionMeasures]');
       var s=that.find('span');
       var data={
    		   PoisonName:   name.val(),
               ProtectionMeasures: text.val(),
               PoisonKind:i,
               IsProtection:getFieldSpanValue(s)
               
       };
      OePostion.push(data)
      
 });  
// 脏器功能封装  Organ  todo 字符分割处理 other
 var all_Lips=$('#Lips-option span.option')
var all_Dentition=$('#Dentition span.option')
var all_Throat=$('#Throat span.option')
var all_Hearing=$('#Hearing span.option')
var all_Fundus=$('#Fundus span.option')
var all_Skin=$('#Skin span.option')
var all_Sclera=$('#Sclera span.option')
var all_LymphNodes=$('#LymphNodes span.option')
var all_BarrelChest=$('#BarrelChest span.option')
var all_BreathSounds=$('#BreathSounds span.option')
 var Lips=getFieldSpanValue(all_Lips)
var Dentition=getFieldSpanValue(all_Dentition)
var Throat=getFieldSpanValue(all_Throat)
var Hearing=getFieldSpanValue(all_Hearing)
var Fundus=getFieldSpanValue(all_Fundus)
var Skin=getFieldSpanValue(all_Skin)
var Sclera=getFieldSpanValue(all_Sclera)
var LymphNodes=getFieldSpanValue(all_LymphNodes)
var BarrelChest=getFieldSpanValue(all_BarrelChest)
var BreathSounds=getFieldSpanValue(all_BreathSounds)

var all_Rale=$('#Rale span.option')
var all_Rhythm=$('#Rhythm span.option')
var all_HeartMurmur=$('#HeartMurmur span.option')
var all_AbdominalTenderness=$('#AbdominalTenderness span.option')
var all_AbdominalMass=$('#AbdominalMass span.option')
var all_TheAbdomenLiver=$('#TheAbdomenLiver span.option')
var all_ShiftingDullness=$('#ShiftingDullness span.option')
var all_LowerExtremityEdema=$('#LowerExtremityEdema span.option')
var all_Dre=$('#Dre span.option')
var all_Breast=$('#Breast span.option')
var Rale=getFieldSpanValue(all_Rale)
var Rhythm=getFieldSpanValue(all_Rhythm)
var HeartMurmur=getFieldSpanValue(all_HeartMurmur)
var AbdominalTenderness=getFieldSpanValue(all_AbdominalTenderness)
var AbdominalMass=getFieldSpanValue(all_AbdominalMass)
var TheAbdomenLiver=getFieldSpanValue(all_TheAbdomenLiver)
var ShiftingDullness=getFieldSpanValue(all_ShiftingDullness)
var LowerExtremityEdema=getFieldSpanValue(all_LowerExtremityEdema)
var Dre=getFieldSpanValue(all_Dre)
var Breast=getFieldSpanValue(all_Breast)

var OrganDate=$('#OrganForm').serializeArray();
var Organ= {};
for(i=0;i<OrganDate.length;i++){
	Organ[OrganDate[i].name] = OrganDate[i].value;
} 
Organ['Lips']=Lips;
Organ['Dentition']=Dentition;
Organ['Throat']=Throat;
Organ['Hearing']=Hearing;
Organ['Fundus']=Fundus+"|"+$('#FundusReason').val();
Organ['Skin']=Skin;
Organ['Sclera']=Sclera;
Organ['LymphNodes']=LymphNodes;
Organ['BarrelChest']=BarrelChest;
Organ['BreathSounds']=BreathSounds+"|"+$('#BreathSoundsReason').val();
Organ['Rale']=Rale;
Organ['Rhythm']=Rhythm;
Organ['HeartMurmur']=HeartMurmur+"|"+$('#HeartMurmurReason').val();
Organ['AbdominalTenderness']=AbdominalTenderness+"|"+$('#AbdominalTendernessReason').val();
Organ['AbdominalMass']=AbdominalMass+"|"+$('#AbdominalMassReason').val();
Organ['TheAbdomenLiver']=TheAbdomenLiver+"|"+$('#TheAbdomenLiverReason').val();
Organ['ShiftingDullness']=ShiftingDullness+"|"+$('#ShiftingDullnessReason').val();
Organ['LowerExtremityEdema']=LowerExtremityEdema;
Organ['SplenomegalyReason']=getFieldSpanValue($('#Splenomegaly span.option'))+"|"+$('#SplenomegalyReason').val()
Organ['Dre']=Dre;
Organ['Breast']=Breast; 
Organ['MotorFunction']=getFieldSpanValue($('#MotorFunction span'))
//妇女 检查 封装
var Woman={}
var Vaginal=getFieldSpanValue($('#Vaginal span.option'))+"|"+$('#VaginalReason').val()
var UterineAdnexa=getFieldSpanValue($('#UterineAdnexa span.option'))+"|"+$('#UterineAdnexaReason').val()
var Vulva=getFieldSpanValue($('#Vulva span.option'))+"|"+$('#VulvaReason').val()
var Cervix=getFieldSpanValue($('#Cervix span.option'))+"|"+$('#CervixReason').val()
var Palace=getFieldSpanValue($('#Palace span.option'))+"|"+$('#PalaceReason').val()
Woman['Vaginal']=Vaginal;
Woman['UterineAdnexa']=UterineAdnexa;
Woman['Vulva']=Vulva;
Woman['Cervix']=Cervix;
Woman['Palace']=Palace;

//辅助检查 Labor
var LaborDate=$('#Labor').serializeArray();
var Labor= {};
for(i=0;i<LaborDate.length;i++){
	Labor[LaborDate[i].name] = LaborDate[i].value;
}
var FecalOccultBlood=getFieldSpanValue($('#FecalOccultBlood span.option'))
var Ecg =getFieldSpanValue($('#Ecg span.option'))+"|"+$('#EcgReason').val()
var ChestXRay =getFieldSpanValue($('#ChestXRay span.option'))+"|"+$('#ChestXRayReason').val()
var  BUltrasonicWave =getFieldSpanValue($('#BUltrasonicWave span.option'))+"|"+$('BUltrasonicWaveReason').val()
var  CervicalSmear =getFieldSpanValue($('#CervicalSmear span.option'))+"|"+$('#CervicalSmearReason').val()
Labor['FecalOccultBlood']=FecalOccultBlood;
Labor['ChestXRay']=ChestXRay;
Labor['BUltrasonicWave']=BUltrasonicWave;
Labor['CervicalSmear']=CervicalSmear;
Labor['Ecg']=Ecg;
//中医体质封装  ChsCons

var ChsCons={}
var ModerateQuality=getFieldSpanValue($('#ModerateQuality span.option'))
var QualityDeficiency=getFieldSpanValue($('#QualityDeficiency span.option'))
var YangQuality=getFieldSpanValue($('#YangQuality span.option'))
var YinQuality=getFieldSpanValue($('#YinQuality span.option'))
var Phlegm=getFieldSpanValue($('#Phlegm span.option'))
var DampHeat=getFieldSpanValue($('#DampHeat span.option'))
var QiQuality=getFieldSpanValue($('#QiQuality span.option'))
var TeBingQuality=getFieldSpanValue($('#TeBingQuality span.option'))
ChsCons['ModerateQuality']=ModerateQuality;
ChsCons['QualityDeficiency']=QualityDeficiency;
ChsCons['YangQuality']=YangQuality;
ChsCons['YinQuality']=YinQuality;
ChsCons['Phlegm']=Phlegm;
ChsCons['DampHeat']=DampHeat;
ChsCons['QiQuality']=QiQuality;
ChsCons['TeBingQuality']=TeBingQuality;


//现存主要健康问题 封装
var Problems={}
var Heart=getFieldSpanValue($('#Heart span.option'))
var Others=getFieldSpanValue($('#Others span.option'))
var Nervoussystems=getFieldSpanValue($('#Nervoussystems span.option'))
var Kidney=getFieldSpanValue($('#Kidney span.option'))
var Cerebrovascular=getFieldSpanValue($('#Cerebrovascular span.option'))
var Vascular=getFieldSpanValue($('#Vascular span.option'))
var Eyes=getFieldSpanValue($('#Eyes span.option'))


Problems['Heart']=Heart;
Problems['Others']=Others;
Problems['Nervoussystems']=Nervoussystems;
Problems['Kidney']=Kidney;
Problems['Cerebrovascular']=Cerebrovascular;
Problems['Vascular']=Vascular;
Problems['Eyes']=Eyes; 

//Hospt 住院治疗情况封装

 var Hospt=[]
 $('.add_drugTr').each(function(i){
       var that = $(this);
       var nameInDate = that.find('input[name=InDate]');
       var nameOutDate = that.find('input[name=OutDate]');
       var nameReason = that.find('input[name=Reason]');
       var nameOrgName = that.find('input[name=OrgName]');
       var nameMedicalRecord = that.find('input[name=MedicalRecordNumber]');
       
       var datas={
    		   HistoryType:1,
    		   InDate:$(nameInDate[i]).val(),
    		   OutDate:$(nameOutDate[i]).val(),
    		   OrgName:$(nameOrgName[i]).val(),
               Reason:$(nameReason[i]).val(),
               MedicalRecordNumber:$(nameMedicalRecord[i]).val()
       };
       Hospt.push(datas)
      
     });  
 $('.inBed_tr').each(function(i){
     var that = $(this);
     var nameInDate = that.find('input[name=InDate]');
     var nameOutDate = that.find('input[name=OutDate]');
     var nameReason = that.find('input[name=Reason]');
     var nameOrgName = that.find('input[name=OrgName]');
     var nameMedicalRecord = that.find('input[name=MedicalRecordNumber]');
     
     var datai={
  		   HistoryType:2,
  		   InDate:$(nameInDate[i]).val(),
		   OutDate:$(nameOutDate[i]).val(),
		   OrgName:$(nameOrgName[i]).val(),
           Reason:$(nameReason[i]).val(),
           MedicalRecordNumber:$(nameMedicalRecord[i]).val()
     };
     Hospt.push(datai)
    
   }); 
     console.log(11111)
     console.log(Hospt)
   //主要用药 情况封装    todo span的值
     var Drug = [];
    	var DrugName_item = $("input[name='DrugName']");
    	var Usage_item = $("input[name='Usage']");
    	var Amount_item = $("input[name='Amount']");
    	var MedicationTime_item = $("input[name='MedicationTime']");
    	var MedicationUnit_item = $("input[name='MedicationUnit']");
    	
    	for(var i=0; i<DrugName_item.length; i++){
    		var data = {
    				DrugName:$(DrugName_item[i]).val(),
    				Usage:$(Usage_item[i]).val(),
    				Amount :$(Amount_item[i]).val(),
    				MedicationTime:$(MedicationTime_item[i]).val(),
    				MedicationUnit:$(MedicationUnit_item[i]).val(),
    				MedicationCompliance : getFieldSpanValue($('.MedicationCompliance').eq(i))
    		}
    		Drug.push(data);
    		
    	}
    	
 //非免疫 规划预防接种史 封装 Vacc
    	var Vacc = [];
    	var VaccineName_item = $("input[name='VaccineName']");
    	var VaccDate_item = $("input[name='VaccDate']");
    	var VaccOrgName_item = $("input[name='VaccOrgName']");
    	
    	
    	for(var i=0; i<VaccineName_item.length; i++){
    		var data = {
    				VaccineName:$(VaccineName_item[i]).val(),
    				VaccDate:$(VaccDate_item[i]).val(),
    				VaccOrgName :$(VaccOrgName_item[i]).val(),
    				
    		}
    		Vacc.push(data);
    		
    	}
   //筛查得分封装
  var ScaleScore ={}
   
  
   ScaleScore['LifeSkillsScore']=$('input[name=LifeSkillsScore]').val()
   ScaleScore['CognitiveFunctionScore']=$('input[name=CognitiveFunctionScore]').val()
   ScaleScore['EmotionalStateScore']=$('input[name=EmotionalStateScore]').val()
   
   ScaleScore['Health']=getFieldSpanValue($('#LifeSkills span'))
   ScaleScore['LifeSkills']=getFieldSpanValue($('#LifeSkills span'))
   ScaleScore['CognitiveFunction']=getFieldSpanValue($('#CognitiveFunction span'))
   ScaleScore['EmotionalState']=getFieldSpanValue($('#EmotionalState span'))
   
   
  //健康评价 封装  master里   "IsStandard": false,??????
  var Master={}
  var Assessment=getFieldSpanValue($('#Assessment span'));
  var AssessmentAbnormal=$('input[name=AssessmentAbnormal]').val();
  Master['Assessment']=Assessment
  Master['AssessmentAbnormal']=AssessmentAbnormal
  //主表内容封装
  
  var Symptom=getBlueIds(all_symp);
  var Guidance=getFieldSpanValue(all_Guidance)
  var RiskCrtl=getFieldSpanValue(all_RiskCrtl)
  var RiskCrtlWeight=$('input[name=RiskCrtlWeight]').val();
  var RiskCrtlVaccine=$('input[name=RiskCrtlVaccine]').val();
  var HealthSummary=$('#HealthSummary').val()
  var RiskCrtlOther=$('input[name=RiskCrtlOther]').val();
  console.log("nihao Symptom")
  alert(Symptom)
  Master['Symptom']=Symptom
  Master['Guidance']=Guidance
  Master['RiskCrtl']=RiskCrtl
  Master['RiskCrtlWeight']=RiskCrtlWeight
  Master['RiskCrtlVaccine']=RiskCrtlVaccine
  Master['HealthSummary']=HealthSummary
  Master['RiskCrtlOther']=RiskCrtlOther
  //"UserID": "DB42E8423067C945A5BE45F5FAEB06C4",
  Master['IsStandard']=false//todo
  Master['UserID']="DB42E8423067C945A5BE45F5FAEB06C4"//todo
  
  
  var DoctorID=$('input[name=DoctorID]').val()
  var DoctorName=$('#docName').text()
  var UserID//todo
  var UserName//todo
  
  var ExamDate=$('input[name=rExamDate]').val()
 
  Master['DoctorID']=DoctorID
  Master['DoctorName']=DoctorName
  Master['PersonID']=PersonIDStr
  Master['ExamDate']=ExamDate
  
  
  
//将所有信息封装为统一json
	var saveParam = {};
	saveParam.ProductCode = "${ProductCode}";//todo
	saveParam.Master = Master;
	saveParam.LifeStyle = LifeStyle;
	saveParam.Labor = Labor;
	saveParam.Body = Body;
	saveParam.Drug = Drug;
	saveParam.Organ = Organ;
	saveParam.Vacc = Vacc;
	
	saveParam.OrgID = "AB5EC46E84F34EFD82673A55D0F97972";//todo
	saveParam.DataSouceCode = "01";//todo
	//saveParam.MtID =""
	//saveParam.ans1 ="[]"//todo
	//saveParam.ans2 ="[]"//todo
	//saveParam.ans3 ="[]"//todo
	saveParam.ScaleScore =ScaleScore
	
	// "OrgID": "AB5EC46E84F34EFD82673A55D0F97972",
	// "DataSouceCode": "01"
	//"MtID": "",
	//"ans1": "[]",
	
	saveParam.Problems = Problems;
	
	saveParam.ChsCons = ChsCons;
	saveParam.Hospt = Hospt;
	saveParam.OePostion = OePostion;
	saveParam.Woman = Woman;
	saveParam.Other = [];//todo
	var otherMsg = {}//todo
	//otherMsg.AttrName = "SymptomOther";
	//otherMsg.OtherText = saveParam.CmHyper.SymptomOther;
	saveParam.Other.push(otherMsg);
  
	if(window.confirm('是否保存？')){
		console.debug(saveParam);
		$.ajax({
		    type: 'POST',
		    url: '/fdoctor/mobile/examination/saveExamination' ,
		    data: {
		    	dataJson : JSON.stringify(saveParam),
		    	userName:"${userName}"
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
  
  
   
} );
 
 
   
 
</script>
</html>
