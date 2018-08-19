<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>老年人体检详情</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	

<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->

<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="/fdoctor/statics/layui/css/layui.css"/>
  <script type="text/javascript" src="/fdoctor/statics/layui/layui.js"></script>
  <style type="text/css">
 
  
  </style> 
	<div class="main">
		<div class="clear"></div>
		<div class="mt10 mb10">
			历次体检 &nbsp;<select id="HealthDateList">
			<option  >请选择
					</option>
				<c:forEach items="${listMtIdAndExamDate}" var="data">
					<option value="${data.MtID}" examdate="${data.ExamDate}">${data.ExamDate}
					</option>
				</c:forEach>
			</select> <span class="seat"></span> <a id="addex"> <span class="circular">+</span>
				新增体检
			</a> <a id="report"> <span class="circular">+</span> 体检报告
			</a>
		</div>
		
	<div class="personMsg">
			<table>
				<tr>
					<td>上次体检时间</td>
					<td>${upResult.ExamDate}</td>
					<td>体重</td>
					<td>${upResult.body.Weight}</td>
					<td>身高</td>
					<td>${upResult.body.Height}</td>
					<td>体质指数</td>
					<td>${upResult.body.Bmi}</td>
				</tr>
				<tr>
					<td>腰围</td>
					<td>${lupResult.body.Waistline}</td>
					<td>体温</td>
					<td>${upResult.body.BodyTemperature}</td>
					<td>脉率</td>
					<td>${upResult.body.PulseRate}</td>
					<td>呼吸频率</td>
					<td>${upResult.body.RespiratoryRate}</td>
				</tr>
				<tr>
					<td>左侧血压</td>
					<td>${upResult.body.LeftSbp}/${upResult.body.LeftDbp}</td>
					<td>右侧血压</td>
					<td>${upResult.body.RightSbp}/${upResult.body.RightDbp}</td>

				</tr>
			</table>
		</div>

		<div class="table3_hdinfo">
			<table>
				<tr>
					<td>姓名</td>
					<td>${pseonInfo.personName}</td>
					<td>性别</td>
					<td>${pseonInfo.sex}</td>
					<td>体检年龄</td>
					<td>${ExamAge}</td>
					<td>编号</td>
					<td>${pseonInfo.personCode}</td>
				</tr>
			</table>
		</div>
		
		
		<div class="table3">
			<table id="datable" border="1" class="tab_content">
				<tbody><tr>
					<td>体检日期</td> 
					<td colspan="2"><input name="rExamDate" type="date" value=""></td>
					<td>责任医生</td>
					<td colspan="2"><input type="hidden" name="DoctorID" value=""><a><span id="DoctorName"></span></a></td>
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
						<span class="optionMore">25其他<input type="text" name="SymptomOther" id="SymptomOther" value="" class="borderb"></span>
					</div>
					</td>
				</tr>
				
				<form id="bodyForm">
				<tr>
					<td rowspan="9">一<br>般<br>状<br>态 </td>
					<td>体温</td>
					<td><input type="text" name="BodyTemperature" id="BodyTemperature" value="" class="borderb w40"> ℃</td>
					<td>脉率  <a href="file:///C:/" class="bluecolor" id="help1">?</a></td>
					<td><input type="text" name="PulseRate" id="PulseRate" value="" class="borderb w40">次/分钟</td>
					<td>/</td>
				</tr>
				<tr>
					<td rowspan="2">呼吸频率<a href="file:///C:/" class="bluecolor" id="help2">?</a></td>
					<td rowspan="2"><input type="number" name="RespiratoryRate" id="RespiratoryRate" value="" class="borderb w40">次/分钟</td>
					<td rowspan="2">血压  <a href="file:///C:/" class="bluecolor" id="help3">?</a></td>
					<td>左侧</td>
					<td>
						<input type="number" name="LeftSbp" id="LeftSbp" value="" class="borderb w20">/
						<input type="number" name="LeftDbp" id="LeftDbp" value="" class="borderb w20">
						mmHg
					</td>
				</tr>
				<tr>
					<td>右侧</td>
					<td>
						<input type="number" name="RightSbp" id="RightSbp" value="" class="borderb w20">/
						<input type="number" name="RightDbp" id="RightDbp" value="" class="borderb w20">
						mmHg
					</td>
				</tr>
				<tr>
					<td>身高</td>
					<td><input type="number" name="Height" id="Height" value="" class="borderb w40">cm</td>
					<td>体重</td>
					<td><input type="number" name="Weight" id="Weight" value="" class="borderb w40">kg</td>
					<td>/</td>
				</tr>
				<tr>
					<td>腰围</td>
					<td><input type="number" name="Waistline" id="Waistline" value="" class="borderb w40">cm</td>
					<td>体质指数(BMI)  <a href="file:///C:/" class="bluecolor" id="help4">?</a></td>
					<td><input type="number" name="Bmi" id="Bmi" value="" class="borderb w40">kg/m²</td>
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
							<input type="number" name="LifeSkillsScore" id="" value="" class="w40 borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>老年人认知功能 <span class="redcolor">*</span> <a href="file:///C:/" class="bluecolor" id="help5">?</a></td>
					<td colspan="4" id="CognitiveFunction">
						<span class="option">1粗筛阴性</span>
						<span class="option" id="xuanzhe">2粗阳性，简易智力状态检查</span>
						<span class="option">总分
							<input type="number" name="CognitiveFunctionScore" id="" value="" class="w40 borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>老年人感情状态 <span class="redcolor">*</span> <a href="file:///C:/" class="bluecolor" id="help6">?</a></td>
					<td colspan="4" id="EmotionalState">
						<span class="option">1粗筛阴性</span>
						<span class="option" id="xuanzhe2">2粗阳性，老年人抑郁评分检查</span>
						<span class="option">总分
							<input type="number" name="EmotionalStateScore" id="" value="" class="w40 borderb">
						</span>
					</td>
				</tr>
			
				<form id="LifeStyleForm">
				<tr>
					<td rowspan="17"> 生<br>活<br>方<br>式 </td>
					<td rowspan="3">体育锻炼 <a href="file:///C:/" class="bluecolor" id="help7">?</a></td>
					<td>锻炼频率</td>
					<td id="ExerciseFrequency">
						<span class="option">1每天</span>
						<span class="option">2每周一次以上</span>
						<span class="option">3偶尔</span>
						<span class="option">4不锻炼</span>
					</td>
				</tr>
				<tr>
					<td>每次锻炼时间</td>
					<td><input type="number" name="EachExerciseTime" id="EachExerciseTime" value="" class="borderb w40">分钟</td>
					<td>坚持锻炼时间</td>
					<td><input type="number" name="ExerciseTime" id="ExerciseTime" value="" class="borderb w40">年</td>
				</tr>
				<tr>
					<td>锻炼方式</td>
					<td colspan="4"> <input type="text" name="ExerciseMethod" id="ExerciseMethod" value="" class="borderb" style="width: 200px;"></td>
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
						吸烟情况  <a href="file:///C:/" class="bluecolor" id="help8">?</a>
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
					<td colspan="3">平均 <input type="number" name="Smoking" id="Smoking" value="" class="borderb w40">只</td>
				</tr>
				<tr>
					<td>开始吸烟年龄</td>
					<td colspan="3">
						<span class="">
							<input type="number" name="SmokingAge" id="SmokingAge" value="" class="borderb w40">岁
						</span>
						<span class="seat"></span>
						<span class="">
							戒烟年龄
							<input type="number" name="AgeQuit" id="AgeQuit" value="" class="borderb w40">岁
						</span>
					</td>
				</tr>
				
				<tr>
					<td rowspan="5">饮酒情况  <a href="file:///C:/" class="bluecolor" id="help9">?</a></td>
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
						平均 <input type="number" name="DailyAlcoholIntake" id="DailyAlcoholIntake" value="" class="borderb w40">两
					</td>
				</tr>
				<tr>
					<td>是否戒酒</td>
					<td colspan="3" id="AlcoholDiv">
					    
						<span class="option">1未戒酒</span>
						<span class="option">2已戒酒</span>
						戒酒年龄 
						<input type="number" name="AlcoholAge" id="AlcoholAge" value="" class="borderb w20">岁
						
					</td>
				</tr>
				<tr>
					<td>开始饮酒年龄</td>
					<td colspan="1">
						<input type="number" name="AgeStartedDrinking" id="AgeStartedDrinking" value="" class="borderb w20">岁
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
			</tbody></table>	
				
				
			<table class="tab_content" border="1" hidden="">
				<form id="OePostion">
				<tbody><tr>
					<td class="">生<br>活<br>方<br>式</td> 
					<td rowspan="7">职业病危害因素接触史  <a href="file:///C:/" class="bluecolor" id="help10">?</a></td>
					<td colspan="4">
					    <div id="IsOeDiv">
						<span class="option">1无</span>
						<span class="option">2有</span>
						(工种 <input type="text" name="Occupation" id="Occupation" value="" class="borderb">从业时间
						<input type="number" name="WorkingTime" id="WorkingTime" value="" class="w20 borderb">年
						)
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="5">毒物种类</td>
				</tr>
				
				<tr id="tr0" class="row-poison">
					<td colspan="1">
						粉尘 <input type="text" name="PoisonName" id="" value="" class="borderb">
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1无</span>
						<span class="option">2有</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb">
					</td>
				</tr>  
				<tr id="tr1" class="row-poison"> 
					<td colspan="1">
						放射性物质 <input type="text" name="PoisonName" id="" value="" class="borderb">
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1是</span>
						<span class="option">2否</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb">
					</td>
				</tr>
				
				<tr id="tr2" class="row-poison">
					<td colspan="1">
						物理因素 <input type="text" name="PoisonName" id="" value="" class="borderb">
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1是</span>
						<span class="option">2否</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb">
					</td>
				</tr>
				
				<tr id="tr3" class="row-poison">
					<td>
						化学因素 <input type="text" name="PoisonName" id="" value="" class="borderb">
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1是</span>
						<span class="option">2否</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb">
					</td>
				</tr>
				
				<tr id="tr4" class="row-poison">
					<td>
						其他 <input type="text" name="PoisonName" id="" value="" class="borderb">
					</td>
					<td colspan="3">
						防护措施 
						<span class="option">1是</span>
						<span class="option">2否</span>
						<input type="number" name="ProtectionMeasures" id="" value="" class="w20 borderb">
					</td>
				</tr>
				</form>
				<form id="OrganForm">
				<tr>
					<td rowspan="5">脏<br>器<br>功<br>能</td>
					<td rowspan="3">口腔</td>
					<td>口唇</td>
					<td colspan="3" id="Lips-option">
						<span class="option">1红润</span>
						<span class="option">2苍白</span>
						<span class="option">3发绀</span>
						<span class="option">4皲裂</span>
						<span class="option">5疱疹</span>
						<input type="number" name="" id="" value="" class="w20 borderb">
					</td>
				</tr>
				<tr>
					<td rowspan="1">齿列</td>
					<td colspan="3" id="Dentition">
						<table>
							<tbody><tr>
								<td style="width:140px ;">
									<span class=" teeth_choose_no">1正常</span>
								</td>
								<td>
									<span class="teeth_choose">2缺齿</span>
								</td>
								<td>
									<table class="teeth_table">
										<tbody><tr>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody>
															<tr>
															<td value="8">8</td>
															<td value="7">7</td>
															<td value="6">6</td>
															<td value="5">5</td>
															<td value="4">4</td>
															<td value="3">3</td>
															<td value="2">2</td>
															<td value="1">1</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													 </tbody>
													</table>
												</div>
											</td>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="1">1</td>
															<td value="2">2</td>
															<td value="3">3</td>
															<td value="4">4</td>
															<td value="5">5</td>
															<td value="6">6</td>
															<td value="7">7</td>
															<td value="8">8</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="8">8</td>
															<td value="7">7</td>
															<td value="6">6</td>
															<td value="5">5</td>
															<td value="4">4</td>
															<td value="3">3</td>
															<td value="2">2</td>
															<td value="1">1</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="1">1</td>
															<td value="2">2</td>
															<td value="3">3</td>
															<td value="4">4</td>
															<td value="5">5</td>
															<td value="6">6</td>
															<td value="7">7</td>
															<td value="8">8</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
										</tr>
									</tbody>
									</table>
								</td>
						
								<td>
									<span class="teeth_choose">3龋齿</span>
								</td>
								<td>
									<table class="teeth_table">
										<tbody><tr>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody>
															<tr>
															<td value="8">8</td>
															<td value="7">7</td>
															<td value="6">6</td>
															<td value="5">5</td>
															<td value="4">4</td>
															<td value="3">3</td>
															<td value="2">2</td>
															<td value="1">1</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													 </tbody>
													</table>
												</div>
											</td>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="1">1</td>
															<td value="2">2</td>
															<td value="3">3</td>
															<td value="4">4</td>
															<td value="5">5</td>
															<td value="6">6</td>
															<td value="7">7</td>
															<td value="8">8</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="8">8</td>
															<td value="7">7</td>
															<td value="6">6</td>
															<td value="5">5</td>
															<td value="4">4</td>
															<td value="3">3</td>
															<td value="2">2</td>
															<td value="1">1</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="1">1</td>
															<td value="2">2</td>
															<td value="3">3</td>
															<td value="4">4</td>
															<td value="5">5</td>
															<td value="6">6</td>
															<td value="7">7</td>
															<td value="8">8</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
										</tr>
									</tbody>
									</table>
								</td>
								<td>
									<span class="teeth_choose">4义齿(假牙)</span>
								</td>
								
								<td>
									<table class="teeth_table">
										<tbody><tr>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody>
															<tr>
															<td value="8">8</td>
															<td value="7">7</td>
															<td value="6">6</td>
															<td value="5">5</td>
															<td value="4">4</td>
															<td value="3">3</td>
															<td value="2">2</td>
															<td value="1">1</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													 </tbody>
													</table>
												</div>
											</td>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="1">1</td>
															<td value="2">2</td>
															<td value="3">3</td>
															<td value="4">4</td>
															<td value="5">5</td>
															<td value="6">6</td>
															<td value="7">7</td>
															<td value="8">8</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="8">8</td>
															<td value="7">7</td>
															<td value="6">6</td>
															<td value="5">5</td>
															<td value="4">4</td>
															<td value="3">3</td>
															<td value="2">2</td>
															<td value="1">1</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
											<td>
												<input type="text" name="" id="" value="" disabled="true" class="teeth_input">
												<div class="num_table" hidden="">
													<table cellpadding="1">
														<tbody><tr>
															<td value="1">1</td>
															<td value="2">2</td>
															<td value="3">3</td>
															<td value="4">4</td>
															<td value="5">5</td>
															<td value="6">6</td>
															<td value="7">7</td>
															<td value="8">8</td>
															<td class="redcolor hidden_x">X</td>
														</tr>
													</tbody></table>
												</div>
											</td>
										</tr>
									</tbody>
									</table>
								</td>
							</tr>
						</tbody></table>
					</td>
				</tr>
				<tr>
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
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>视力  <a href="file:///C:/" class="bluecolor" id="help11">?</a></td>
					<td colspan="4">
						左眼 <input type="number" name="LeftEye" id="" value="" class="borderb w40">
						右眼 <input type="number" name="RightEye" id="" value="" class="borderb w40">
						(矫正视力：
						左眼 <input type="number" name="LeftEyeVc" id="" value="" class="borderb w40">
						右眼 <input type="number" name="RightEyeVc" id="" value="" class="borderb w40">
						)
					</td>
				</tr>
				<tr>
					<td>听力  <a href="file:///C:/" class="bluecolor" id="help12">?</a></td>
					<td colspan="4" id="Hearing">
						<span class="option">1听见</span>
						<span class="option">2听不清或无法听见</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>运动能力 <a href="file:///C:/" class="bluecolor" id="help13">?</a></td>
					<td colspan="4" id="MotorFunction">
						<span class="option">1可顺利完成</span>
						<span class="option">2无法独立完成气质任何一个动作</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td rowspan="4">
						脏<br>器<br>功<br>能 
					</td>
					<td>眼底 <span class="redcolor">*</span></td>
					<td colspan="4" id="Fundus">
						<span class="option">1正常</span>
						<span class="option">2异常<input type="text" name="FundusReason" id="FundusReason" value="" class="borderb w40"></span>
						
						
						<input type="number" name="" id="" value="" class="w20 borderb">
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
						<span class="option">7其他<input type="text" name="" id="" value="" class="borderb"></span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr id="Sclera">
					<td>巩膜</td>
					<td colspan="4">
						<span class="option">1正常</span>
						<span class="option">2染黄</span>
						<span class="option">3充血</span>
						<span class="option">4其他<input type="text" name="" id="" value="" class="borderb"></span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr id="LymphNodes">
					<td>淋巴结</td>
					<td colspan="4">
						<span class="option">1未触及</span>
						<span class="option">2锁骨上</span>
						<span class="option">3腋窝</span>
						<span class="option">4其他<input type="text" name="" id="" value="" class="borderb"></span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
			
				<tr>
					<td rowspan="20">
						查<br>体 
					</td>
					<td rowspan="3">
						肺
					</td>
					<td>桶状胸：</td>
					<td colspan="3" id="BarrelChest">
						<span class="option">1否</span>
						<span class="option">2是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr id="BreathSounds">
					<td>呼吸音：</td>
					<td colspan="3">
						<span class="option">1正常</span>
						<span class="option">
							2异常
							<input type="text" name="BreathSoundsReason" id="BreathSoundsReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr id="Rale">
					<td>罗音：</td>
					<td colspan="3">
						<span class="option">1无</span>
						<span class="option">2干罗音</span>
						<span class="option">3湿罗音</span>
						<span class="option">4其他<input type="text" name="" id="" value="" class="borderb"></span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td rowspan="2">心脏</td>
					<td>心率：</td>
					<td><input type="number" name="" id="" value="" class="w20 borderb"> 次/分钟</td>
					<td>心律</td>
					<td id="Rhythm">
						<span class="option">1齐</span>
						<span class="option">2不齐</span>
						<span class="option">3绝对不齐</span>
						<input type="number" name="" id="" value="" class="w20 borderb">
					</td>
				</tr>
				<tr>
					<td>杂音：</td>
					<td colspan="3" id="HeartMurmur">
						<span class="option">1无</span>
						<span class="option">2有<input type="text" name="HeartMurmurReason" id="HeartMurmurReason" value="" class=" borderb"> </span>
						<input type="number" name="" id="" value="" class="w20 borderb">
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
							<input type="text" name="AbdominalTendernessReason" id="AbdominalTendernessReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>包块：</td>
					<td colspan="3" id="AbdominalMass">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="AbdominalMassReason" id="AbdominalMassReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>肝大：</td>
					<td colspan="3" id="TheAbdomenLiver">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="TheAbdomenLiverReason" id="TheAbdomenLiverReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>脾大：</td>
					<td colspan="3" id="Splenomegaly">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="SplenomegalyReason" id="SplenomegalyReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>移动性浊音：</td>
					<td colspan="3" id="ShiftingDullness">
						<span class="option">1无</span>
						<span class="option">2有
							<input type="text" name="ShiftingDullnessReason" id="ShiftingDullnessReason" value="" class="borderb">
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
						<input type="number" name="" id="" value="" class="w20 borderb">
					</td>
				</tr>
				<tr>
					<td>足背动脉搏动  <a href="file:///C:/" class="bluecolor" id="help14">?</a></td>
					<td colspan="4" id="DorsalisPedisArteryPulse">
						<span class="option">1未触及</span>
						<span class="option">2触及双侧对称</span>
						<span class="option">3触及左侧弱或消失</span>
						<span class="option">4触及右侧弱或消失</span>
						<input type="number" name="" id="" value="" class="w20 borderb">
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
							<input type="text" name="" id="" value="" class="borderb">
						</span>
						<input type="number" name="" id="" value="" class="w20 borderb">
					</td>
				</tr>
				<tr>
					<td>乳腺  <span class="redcolor">*</span> <a href="file:///C:/" class="bluecolor" id="help15">?</a></td>
					<td colspan="4" id="Breast">
						<span class="option">1未及异常</span>
						<span class="option">2触痛</span>
						<span class="option">3包块</span>
						<span class="option">4前列腺异常</span>
						<span class="option">5其他
							<input type="text" name="" id="" value="" class="borderb">
						</span>
						<input type="number" name="" id="" value="" class="w20 borderb">
					</td>
				</tr>
				</form>
				<tr>
					<td rowspan="5">妇科 <span class="redcolor">*</span></td>
					<td>外阴  <a href="file:///C:/" class="bluecolor" id="help16">?</a></td>
					<td colspan="3" id="Vulva">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="VulvaReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>阴道  <a href="file:///C:/" class="bluecolor" id="help17">?</a></td>
					<td colspan="3" id="Vaginal">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="VaginalReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>宫颈  <a href="file:///C:/" class="bluecolor" id="help18">?</a></td>
					<td colspan="3" id="Cervix">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="CervixReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>宫体  <a href="file:///C:/" class="bluecolor" id="help19">?</a></td>
					<td colspan="3" id="Palace">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="PalaceReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>附件  <a href="file:///C:/" class="bluecolor" id="help20">?</a></td>
					<td colspan="3" id="UterineAdnexa">
						<span class="option">1未见异常</span>
						<span class="option">5其他
							<input type="text" name="" id="UterineAdnexaReason" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>其他 <span class="redcolor">*</span></td>
					<td colspan="4"><input type="text" name="Other" id="" value="" class="borderb"></td>
				</tr>
			</tbody></table>
			
			
			<table class="tab_content" border="1" hidden="">
				<form id="Labor">
				<tbody><tr>
					<td rowspan="21">辅<br>助<br>检<br>查 </td>
					<td rowspan="2">血常规<span class="redcolor">*</span></td>
					<td colspan="1">
						血红蛋白 <a href="file:///C:/" class="bluecolor" id="help21">?</a>
						<input type="number" name="Hemoglobin" id="" value="" class="borderb w40">mol/L
					</td>
					<td colspan="1">
						白细胞 <a href="file:///C:/" class="bluecolor" id="help22">?</a> 
						<input type="number" name="Leukocyte" id="" value="" class="borderb w40">X10 <sup>9</sup>/L
					</td>
					<td colspan="1">
						血小板  <a href="file:///C:/" class="bluecolor" id="help23">?</a>
						<input type="number" name="Platelet" id="" value="" class="borderb w40">
					</td>
					<td>/</td>
				</tr>
				<tr>
					<td colspan="4">
						其他 
						<input type="text" name="OtherBlood" id="" value="" class="borderb">
					</td>
				</tr>
				<tr class="Urines">
					<td rowspan="3">尿常规 <span class="redcolor">*</span> <a href="file:///C:/" class="bluecolor" id="help24">?</a></td>
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
						其他 <input type="text" name="OtherUrine" id="" value="" class="borderb">
					</td>
				</tr>
				<tr>
					<td>空腹血糖(FPG) <span class="redcolor">*</span> <a href="file:///C:/" class="bluecolor" id="help25">?</a></td>
					<td><input type="number" name="FastingBloodGlucose" id="" value="" class="borderb w40">mmol/L</td>
					<td>随机血糖</td>
					<td><input type="number" name="RandomBloodGlucose" id="" value="" class="borderb w40">mmol/L</td>
				</tr>
				<tr>
					<td>心电图<span class="redcolor">*</span></td>
					<td colspan="4" id="Ecg">
						<span class="option">1正常</span>
						<span class="option">2异常 
							<input type="text" name="" id="EcgReason" value="" class="borderb">
						</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>尿微量白蛋白(U-MA)<span class="redcolor">*</span></td>
					<td colspan="4">
						<input type="number" name="UrinaryAlbumin" id="" value="" class="borderb w40">mg/dL
					</td>
				</tr>
				<tr>
					<td>大便潜血(FOB)<span class="redcolor">*</span></td>
					<td colspan="4" id="FecalOccultBlood">
						<span class="option">1阴性</span>
						<span class="option">2阳性</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>糖化血红蛋白(HbA1c)<span class="redcolor">*</span> <a href="file:///C:/" class="bluecolor" id="help26">?</a></td>
					<td colspan="4">
						<input type="number" name="GlycatedHemoglobin" id="" value="" class="w40">%
					</td>
				</tr>
				<tr>
					<td>乙型肝炎表面抗原(HBsAg)<span class="redcolor">*</span></td>
					<td colspan="4" id="HepatitisBSurfaceAntigen">
						<span class="option">1阴性</span>
						<span class="option">2阳性</span>
					</td>
				</tr>
				<tr>
					<td rowspan="3">肝功能<span class="redcolor">*</span></td>
					<td colspan="2">
						血清谷丙转氨酶(ALT)  <a href="file:///C:/" class="bluecolor" id="help27">?</a>
						<input type="number" name="SerumAla" id="" value="" class="borderb w40">U/L
					</td>
					<td colspan="2">
						血清谷草转氨酶(AST)  <a href="file:///C:/" class="bluecolor" id="help28">?</a>
						<input type="number" name="SerumAa" id="" value="" class="borderb w40">U/L
					</td>
				</tr>
				<tr>
					<td colspan="2">
						白蛋白(ALB) 
						<input type="number" name="Albumin" id="" value="" class="borderb w40">g/L
					</td>
					<td colspan="2">
						总胆红素(TBIL)  <a href="file:///C:/" class="bluecolor" id="help29">?</a>
						<input type="number" name="TotalBilirubin" id="" value="" class="borderb w40">μ mol/L
					</td>
				</tr>
				<tr>
					<td colspan="2">
						结合胆红素(SDB) 
						<input type="number" name="Bilirubin" id="" value="" class="borderb w40">μ mol/L
					</td>
					<td colspan="2">/</td>
				</tr>
				<tr>
					<td rowspan="2">肾功能<span class="redcolor">*</span>  <a href="file:///C:/" class="bluecolor" id="help30">?</a></td>
					<td colspan="2">
					血清肌酐(SCR)  <a href="file:///C:/" class="bluecolor" id="help31">?</a>
					<input type="number" name="SerumCreatinine" id="" value="" class="borderb w40">μ mol/L
					</td>
					<td colspan="2">
						血尿素氮  <a href="file:///C:/" class="bluecolor" id="help32">?</a>
						<input type="number" name="Bun" id="" value="" class="borderb w40">mmol/L
					</td>
				</tr>
				<tr>
					<td colspan="2">
					血钾浓度(K+) 
					<input type="number" name="PotassiumConcentration" id="" value="" class="borderb w40">mmol/L
					</td>
					<td colspan="2">
						血钠浓度(Na+)
						<input type="number" name="SodiumConcentration" id="" value="" class="borderb w40">mmol/L
					</td>
				</tr>
				<tr>
					<td rowspan="2">血脂</td>
					<td colspan="2">
						总胆固醇(TC)  <a href="file:///C:/" class="bluecolor" id="help33">?</a>
						<input type="number" name="TotalCholesterol" id="" value="" class="borderb w40">mmol/L
					</td>
					<td colspan="2">
						甘油三酯(TG)  <a href="file:///C:/" class="bluecolor" id="help34">?</a>
						<input type="number" name="Triglycerides" id="" value="" class="borderb w40">mmol/L
					</td>
				</tr>
				<tr>
					<td colspan="2">
						血清低密度脂蛋白胆固醇(LDL-C)
						<input type="number" name="LdlCholesterol" id="" value="" class="borderb w40">mmol/L
					</td>
					<td colspan="2">
						血清高密度脂蛋白胆固醇(HDL-C)
						<input type="number" name="HdlCholesterol" id="" value="" class="borderb w40">mmol/L
					</td>
				</tr>
				<tr>
					<td>胸部X线片<span class="redcolor">*</span></td>
					<td colspan="4" id="ChestXRay">
						<span class="option">1正常</span>
						<span class="option">
							2异常
							<input type="text" name="" id="ChestXRayReason" value="" class="borderb">
						</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>B 超<span class="redcolor">*</span></td>
					<td colspan="4" id="BUltrasonicWave">
						<span class="option">1正常</span>
						<span class="option">
							2异常
							<input type="text" name="" id="BUltrasonicWaveReason" value="" class="borderb">
						</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>宫颈涂片<span class="redcolor">*</span></td>
					<td colspan="4" id="CervicalSmear">
						<span class="option">1正常</span>
						<span class="option">
							2异常
							<input type="text" name="" id="CervicalSmearReason" value="" class="borderb">
						</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>其他<span class="redcolor">*</span> <a href="file:///C:/" class="bluecolor" id="help35">?</a></td>
					<td colspan="4">
						<input type="text" name="OtherLaboratory" id="" value="" class="borderb">
					</td>
				</tr>
				</form>
			
				<form id="ChsCons">
				<tr>
					<td rowspan="9">
						中<br>医<br>体<br>质<br>辨<br>识<br><span class="redcolor">*</span> 
					</td>
					<td>平和质</td>
					<td colspan="4" id="ModerateQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>气虚质</td>
					<td colspan="4" id="QualityDeficiency">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>阳虚质</td>
					<td colspan="4" id="YangQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>阴虚质</td>
					<td colspan="4">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>痰湿质</td>
					<td colspan="4" id="Phlegm">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>湿热质</td>
					<td colspan="4" id="DampHeat">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>血瘀质</td>
					<td colspan="4" id="BloodQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>气郁质</td>
					<td colspan="4" id="QiQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>特秉质</td>
					<td colspan="4" id="TeBingQuality">
						<span class="option">1是</span>
						<span class="option">2倾向是</span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				</form>
			</tbody></table>
			
			<table class="tab_content" border="1" hidden="">
				<form></form>
				<tbody><tr>
					<td rowspan="7">现<br>存<br>主<br>要<br>健<br>康<br>问<br>题</td>
					<td>脑血管疾病</td>
					<td colspan="4" id="Cerebrovascular">
						<span class="option">1未发现</span>
						<span class="option">2缺血性卒中</span>
						<span class="option">3脑出血</span>
						<span class="option">4蛛网膜下腔出血</span>
						<span class="option">5短暂性脑缺血发作</span>
						<span class="option">
							6其他
							<input type="text" name="" id="" value="" class="borderb">
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
							<input type="text" name="" id="" value="" class="borderb">
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
							<input type="text" name="" id="" value="" class="borderb">
						</span>
					</td>
					
				</tr><tr>
					<td>血管疾病</td>
					<td colspan="4" id="Vascular">
						<span class="option">1未发现</span>
						<span class="option">2夹层动脉瘤</span>
						<span class="option">3动脉闭塞性疾病</span>
						<span class="option">
							4其他
							<input type="text" name="" id="" value="" class="borderb">
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
							<input type="text" name="" id="" value="" class="borderb">
						</span>
					</td>
					
				</tr>
				<tr>
					<td>神经系统疾病</td>
					<td colspan="5" id="Nervoussystems">
						<span class="option">1未发现</span>
						<span class="option">2有
							<input type="text" name="" id="" value="" class="borderb">
						</span>
					</td>
				</tr>
				<tr>
					<td>其他系统疾病</td>
					<td colspan="5" id="Others">
						<span class="option">1未发现</span>
						<span class="option">2有
							<input type="text" name="" id="" value="" class="borderb">
						</span>
					</td>
				</tr>
				
			   
				<tr>
					<td rowspan="4" class="inHospital_situation">住<br>院<br>治<br>疗<br>情<br>况</td> 
					<td rowspan="2" class="inHospital_history">住院史</td>
					<td>入/出院日期</td>
					<td>原因</td>
					<td>医疗机构名称</td>
					<td>病案号  <a href="javascript:void(0)" class="add_drug">+</a></td>
				</tr>
				<tr class="add_drugTr">
					<td>
						<input type="date" name="InDate" data-json-key="hosList" data-json-by="HistoryType:1" id="" value="" class="borderb">/
						<input type="date" name="OutDate" data-json-key="hosList" data-json-by="HistoryType:1" id="" value="" class="borderb">
					</td>
					<td>
						<input type="text" name="Reason" id="" data-json-key="hosList" data-json-by="HistoryType:1" value="" class="borderb">
					</td>
					<td>
						<input type="text" name="OrgName" id="" data-json-key="hosList" data-json-by="HistoryType:1" value="" class="borderb">
					</td>
					<td>
						<input type="text" name="MedicalRecordNumber" data-json-key="hosList" data-json-by="HistoryType:1" id="" value="" class="borderb"> <a href="javascript:void(0)" class="delete_drug">X</a>
					</td>
				</tr>
				
			
				<tr>
					<td rowspan="2" class="inBed">家庭病床史</td>
					<td>建/撤床日期</td>
					<td>原因</td>
					<td>医疗机构名称</td>
					<td>病案号<a href="javascript:void(0)" class="add_bed">+</a></td>
				</tr>
				<tr class="inBed_tr">
					<td>
						<input type="date" name="InDate" id="" data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb">/
						<input type="date" name="OutDate" id="" data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb">
					</td>
					<td>
						<input type="text" name="Reason" id="" data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb">
					</td>
					<td>
						<input type="text" name="OrgName" id="" data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb">
					</td>
					<td>
						<input type="text" name="MedicalRecordNumber" id="" data-json-key="hosList" data-json-by="HistoryType:2" value="" class="borderb"> <a href="javascript:void(0)" class="delete_bed">X</a>
					</td>
				</tr>
				
				<form></form>
				<tr>
					<td rowspan="2" class="drug_situation">主要用药 情况</td>
					<td>药物名称</td>
					<td>用法</td>
					<td>用量</td>
					<td>用药时间</td>
					<td>服药依从性<a href="javascript:void(0)" class="add_drug2">+</a> <br>
						
					</td>
				</tr>
				<tr class="drug_situation_tr">
					<td><input type="text" name="DrugName" id="" value="" class="borderb"></td>
					<td><input type="text" name="Usage" id="" value="" class="borderb"></td>
					<td><input type="text" name="Amount" id="" value="" class="borderb"></td>
					<td>
						<input type="text" name="MedicationTime" id="" value="" class="borderb w40">
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
				
				<form></form>
				<tr>
					<td rowspan="2" class="inoculation">
						非免疫 规划预防接种史
					</td>
					<td>名称</td>
					<td>接种日期</td>
					<td colspan="3">接种机构 <a href="javascript:void(0)" class="add_inoculation">+</a></td>
				</tr>
				<tr id="vaccList" class="inoculation_tr">
					<td><input type="text" name="VaccineName" id="" value="" class="borderb"></td>
					<td><input type="date" name="VaccDate" id="" value="" class="borderb"></td>
					<td colspan="3"><input type="text" name="VaccOrgName" id="" value="" class="borderb"> <a href="javascript:void(0)" class="delete_inoculation">X</a></td>
				</tr>
				
				<tr>
					<td rowspan="2" class="health_assess">健康评价</td>
					<td colspan="5" id="Assessment">
						<span class="option">1体检无异常</span>
						<span class="option">2有异常</span>
						
					</td>
				</tr>
				<tr>
					<td colspan="5" class="health_assess_tr">
						结果： <input type="text" name="AssessmentAbnormal" id="" value="" class="borderb"> <a href="javascript:void(0)" class="delete_health_assess">X</a>
					</td>
				</tr>
				<tr>
					<td rowspan="3">健康指导</td>
					<td>建议选项</td>
					<td colspan="5" id="Guidance">
						<span class="option">1 纳入慢性病患者健康管理 </span>
						<span class="option">2 建议复查  </span>
						<span class="option">3 纳入慢性病患者健康管理 </span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>  
					<td>危险因素控制</td>
					<td colspan="4" id="RiskCrtl">
						<span class="option">1戒烟 </span>
						<span class="option">2健康饮酒 </span>
						<span class="option">3饮食 </span>
						<span class="option">4锻炼 </span>
						<span class="option">5减体重 (目标 <input type="number" name="RiskCrtlWeight" id="" value="" class="borderb w40"> KG) </span>
						<span class="option">6建议接种疫苗 <input type="text" name="RiskCrtlVaccine" id="" value="" class="borderb"></span>
						<span class="option">其他 <input type="text" name="RiskCrtlOther" id="" value="" class="borderb"></span>
						<input type="number" name="" id="" value="" class="borderb w20">
					</td>
				</tr>
				<tr>
					<td>健康摘要</td>
					<td colspan="5">
						<textarea id="HealthSummary" name="HealthSummary" rows="" cols="" style="width: 98%;"></textarea>
					</td>
				</tr>
			</tbody></table>
		
		
		
		
		<div class="mt20"></div>
		<div class="paging chartSearch">
            <ul>
              <li class="active"><a href="javascript:void(0)">1</a></li>
           	  <li class=""><a href="javascript:void(0)">2</a></li>
           	  <li class=""><a href="javascript:void(0)">3</a></li>
         	  <li class=""><a href="javascript:void(0)">4</a></li>
              
            </ul>
          </div>
          
          
          
          <div class="footer_btn">
          		<ul>
          			<li>
          			
          			</li>
          			<li>
          			
          			</li>
          			<li>
          			
          			</li>
          		</ul>
          </div>
	</div>

<script type="text/javascript">

var PersonIDStr='${PersonID}'
 //新增体检
 $('#addold').on('click', function () {
		 
	 	 var radte={}
		 var ID='${PersonID}'
	 	 radte['ID']=ID
		 radte=JSON.stringify(radte);
	     if(radte==''||radte==null){
	    	alert("请先选择居民")
	     } else{
		    $('#addold').attr("href", "/fdoctor/mobile/examination/toAddExamination?radte="+radte+"&doctorName="+"${doctorName}");
	     }
});



 
 $(":input").attr("disabled",true);
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
	
//初始化表单回显
if('${listMtIdAndExamDateJson}'!=null&&'${listMtIdAndExamDateJson}'!=''){
var listMtIdAndExamDateJson=${listMtIdAndExamDateJson}
var reExamDate=listMtIdAndExamDateJson[0].ExamDate
$('input[name=rExamDate]').val(reExamDate);
}


//最后一次体检  lastExamination
if('${lastExaminationJson}'!=null&&'${lastExaminationJson}'!=''){
var lastExaminationJson =${lastExaminationJson};
//回显一般状态（不包含老年人）
   for(var key in lastExaminationJson){  
	 //输入框回显
	 $("input[name='"+key+"']").val(lastExaminationJson[key])
   } 
}
//表回显 

	var all_symp = $('#symptomDiv span');//症状
	var all_SmokingStatus=$('#SmokingStatusDiv span.option');//吸烟情况
	var all_DrinkingFrequency=$('#DrinkingFrequencyDiv span.option');//饮酒情况
	var all_Diet=$('#DietDiv span.option');//饮食情况
	var all_Alcohol=$('#AlcoholDiv span.option');//是否戒酒AlcoholTypeDiv
	var all_AlcoholType=$('#AlcoholTypeDiv span.option');//饮酒种类 
	var all_IsOe=$('#IsOeDiv span.option');//是否职业暴露
	//var all_IsProtection=$('#IsProtection span.option');//是否有防护措施  0 未选 1 无 2 有
	var RecordOneJson=${RecordOneJson};
	console.info(RecordOneJson);
	//责任医生姓名
	$('#DoctorName').text(RecordOneJson.master.DoctorName);
	$('input[name=DoctorID]').val(RecordOneJson.master.DoctorID)
	//体征回显
	
	for(var key in RecordOneJson.body){  
			//输入框回显
			$("input[name='"+key+"']").val(RecordOneJson.body[key]);
	    }
	//症状回显  
	var strs = RecordOneJson.master.Symptom.split(",");
	all_symp.each(function(i){
	    var id = String(Math.pow(2, i));//计算出当前症状对应的真实ID
	    if(strs.indexOf(id) > -1){//如果这个ID存在于服务器给的值，则为这个元素添加高亮
	   
	    	all_symp.eq(i).toggleClass('bluecolor');
	    }
	});
	//其他症状回显
	var otherTextObject=RecordOneJson.otherText;
	$("input[name='SymptomOther']").val(otherTextObject.Symptom)
	

	//生活方式回显
	
		for(var key in RecordOneJson.lifeStyle){  
			//输入框回显
			$("input[name='"+key+"']").val(RecordOneJson.lifeStyle[key])
	    }
	     //锻炼频率
	     $('#ExerciseFrequency span').each(function(i){
	    	 if(RecordOneJson.lifeStyle.ExerciseFrequency==i+1){
					$(this).toggleClass('bluecolor');
				}
	    	 
	     }) 
	     //IsDrunkLastYearOp
	      $('#IsDrunkLastYearOp span').each(function(i){
	    	 if(RecordOneJson.lifeStyle.IsDrunkLastYear==i+1){
					$(this).toggleClass('bluecolor');
				}
	    	 
	     })
	     
		//饮食习惯
		all_Diet.each(function(i){
			if(RecordOneJson.lifeStyle.Diet==i+1){
				all_Diet.eq(i).toggleClass('bluecolor');
			}
		});
		//吸烟情况
		
		all_SmokingStatus.each(function(i){
			if(RecordOneJson.lifeStyle.SmokingStatus==i+1){
				$(this).toggleClass('bluecolor');
			}
		});
		//饮酒频率
		all_DrinkingFrequency.each(function(i){
			if(RecordOneJson.lifeStyle.DrinkingFrequency==i+1){
				$(this).toggleClass('bluecolor');
			}
		});
		//是否戒酒
		all_Alcohol.each(function(i){
			if(RecordOneJson.lifeStyle.IsAlcohol==i+1){
				$(this).toggleClass('bluecolor');
			}
		});
		//饮酒种类
		all_AlcoholType.each(function(i){
			if(RecordOneJson.lifeStyle.AlcoholType==i+1){
				$(this).toggleClass('bluecolor');
			}
		});
		//是否职业暴露
		all_IsOe.each(function(i){
			if(RecordOneJson.lifeStyle.IsOe==i+1){
				$(this).toggleClass('bluecolor');
			}
		});
		//是否防护措施
	/*  	all_IsProtection.each(function(i){
			if(RecordOneJson.OePostion.IsProtection==i){
				all_IsProtection.eq(i).toggleClass('bluecolor');
			}
		});  */
		
  //吸烟情况  symptomInt
  $('#SmokingStatus span').each(function(i){
			if(RecordOneJson.lifeStyle.SmokingStatus==i+1){
				$(this).toggleClass('bluecolor');
			}
		});


//获取修改后的症状的值	
	
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

	

  
 
//毒物种类回显
   var json = RecordOneJson.exLifeOeList;
   var findDataById = function(id){
       var ret = null;
       for(var i=0; i<json.length; i++){
           if("tr"+json[i].PoisonKind == id){
               ret = json[i];
           }
       }
       return ret;
   };
   $('.row-poison').each(function(i){
       var that = $(this);
       var id = that.attr('id');
       var name = that.find('input[name=PoisonName]');
       var text = that.find('input[name=ProtectionMeasures]');
       var data = findDataById(id);
       if(data!=null){
       name.val(data.PoisonName);
       text.val(data.ProtectionMeasures);
       //data.IsProtection
         that.find('span').each(function(j){
    	
    	   if(data.IsProtection==j+1){
    		   $(this).toggleClass('bluecolor');
    	   }
    	  
         })
       }
   });
//脏器功能回显
  
   $('#Lips-option span').each(function(i){
	   
	   if(RecordOneJson.organ.Lips==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   }) 
   $('#Dentition span').each(function(i){
	   
	   if(RecordOneJson.organ.Dentition==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   }) 
    $('#Throat span').each(function(i){
	   
	   if(RecordOneJson.organ.Throat==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#Hearing span').each(function(i){
	   
	   if(RecordOneJson.organ.Hearing==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#MotorFunction span').each(function(i){
	   
	   if(RecordOneJson.organ.MotorFunction==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   //查体回显 DorsalisPedisArteryPulse
  
$('#Fundus span').each(function(i){
	   
	   if(RecordOneJson.organ.Fundus==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
   $('#DorsalisPedisArteryPulse span').each(function(i){
	   
	   if(RecordOneJson.body.DorsalisPedisArteryPulse==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#Breast span').each(function(i){
	   
	   if(RecordOneJson.organ.Breast==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
     $('#AbdominalTenderness span').each(function(i){
	   
	   if(RecordOneJson.organ.AbdominalTenderness==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
     $('#AbdominalMass span').each(function(i){
	   
	   if(RecordOneJson.organ.AbdominalMass==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
     $('#TheAbdomenLiver span').each(function(i){
	   
	   if(RecordOneJson.organ.TheAbdomenLiver==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
     $('#Splenomegaly span').each(function(i){
	   
	   if(RecordOneJson.organ.Splenomegaly==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
     $('#ShiftingDullness span').each(function(i){
	   
	   if(RecordOneJson.organ.ShiftingDullness==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
     $('#Dre span').each(function(i){
	   
	   if(RecordOneJson.organ.Dre==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
     $('#Rale span').each(function(i){
	   
	   if(RecordOneJson.organ.Rale==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
     $('#Rhythm span').each(function(i){
	   
	   if(RecordOneJson.organ.Rhythm==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
     $('#HeartMurmur span').each(function(i){
	   
	   if(RecordOneJson.organ.HeartMurmur==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
       $('#LymphNodes span').each(function(i){
	   
	   if(RecordOneJson.organ.LymphNodes==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
       $('#BarrelChest span').each(function(i){
	   
	   if(RecordOneJson.organ.BarrelChest==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
       $('#BreathSounds span').each(function(i){
	   
	   if(RecordOneJson.organ.BreathSounds==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
        $('#Skin span').each(function(i){
	   
	   if(RecordOneJson.organ.Skin==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
        $('#Sclera span').each(function(i){
	   
	   if(RecordOneJson.organ.Sclera==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
         $('#LowerExtremityEdema span').each(function(i){
	   
	   if(RecordOneJson.organ.LowerExtremityEdema==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   //妇科

   $('#UterineAdnexa span').each(function(i){
	   
	   if(RecordOneJson.woman.UterineAdnexa==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
      $('#Vulva span').each(function(i){
	   
	   if(RecordOneJson.woman.Vulva==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
      $('#Vaginal span').each(function(i){
	   
	   if(RecordOneJson.woman.Vaginal==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
      $('#Cervix span').each(function(i){
	   
	   if(RecordOneJson.woman.Cervix==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
      $('#Palace span').each(function(i){
	   
	   if(RecordOneJson.woman.Palace==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   for(var key in RecordOneJson.woman){
	   $("input[name='"+key+"']").val((RecordOneJson.woman[key]))
	   
   }
   for(var key in RecordOneJson.organ){
	   $("input[name='"+key+"']").val((RecordOneJson.organ[key]))
	   
   }
   $('input[name=Other]').val((RecordOneJson.woman.Other))
   //辅助检查
     for(var key in RecordOneJson.labora){  
	   $("option[name='"+key+"']").val(RecordOneJson.labora[key])
	   $("input[name='"+key+"']").val(RecordOneJson.labora[key])
     }
 $('#CervicalSmear span').each(function(i){
	   
	   if(RecordOneJson.labora.CervicalSmear==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#BUltrasonicWave span').each(function(i){
	   
	   if(RecordOneJson.labora.BUltrasonicWave==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#ChestXRay span').each(function(i){
	   
	   if(RecordOneJson.labora.ChestXRay==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#Ecg span').each(function(i){
	   
	   if(RecordOneJson.labora.Ecg==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#HepatitisBSurfaceAntigen span').each(function(i){
	   
	   if(RecordOneJson.labora.HepatitisBSurfaceAntigen==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   //体检中医体质辨识
   $('#ModerateQuality span').each(function(i){
	   
	   if(RecordOneJson.chsCon.ModerateQuality==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#QualityDeficiency span').each(function(i){
	   
	   if(RecordOneJson.chsCon.QualityDeficiency==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#YangQuality span').each(function(i){
	   
	   if(RecordOneJson.chsCon.YangQuality==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#YinQuality span').each(function(i){
	   
	   if(RecordOneJson.chsCon.YinQuality==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#Phlegm span').each(function(i){
	   
	   if(RecordOneJson.chsCon.Phlegm==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#DampHeat span').each(function(i){
	   
	   if(RecordOneJson.chsCon.DampHeat==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#BloodQuality span').each(function(i){
	   
	   if(RecordOneJson.chsCon.BloodQuality==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   $('#QiQuality span').each(function(i){
	   
	   if(RecordOneJson.chsCon.QiQuality==i+1){
		   $(this).eq(i).toggleClass('bluecolor');
		   
	   }
   })
   $('#TeBingQuality span').each(function(i){
	   
	   if(RecordOneJson.chsCon.TeBingQuality==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
 //现存主要健康问题
  $('#Cerebrovascular span').each(function(i){
	   
	   if(RecordOneJson.problems.Cerebrovascular==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#Kidney span').each(function(i){
	   
	   if(RecordOneJson.problems.Kidney==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#Heart span').each(function(i){
	   
	   if(RecordOneJson.problems.Heart==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#Vascular span').each(function(i){
	   
	   if(RecordOneJson.problems.Vascular==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#Eyes span').each(function(i){
	   
	   if(RecordOneJson.problems.Eyes==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#Nervoussystems span').each(function(i){
	   
	   if(RecordOneJson.problems.Nervoussystems==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
    $('#Others span').each(function(i){
	   
	   if(RecordOneJson.problems.Others==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })

    
    
 //主要用药 情况	 drugUseList 
   var durg_tr=$('.drug_situation_tr').eq(0)
   $.each(RecordOneJson.drugUseList,function(i){
	   if(i==0){
		   $('.MedicationCompliance span').each(function(j){
			   if(RecordOneJson.drugUseList[0].MedicationCompliance==j+1){
				  
				   $(this).toggleClass('bluecolor');
			   }
		   });
		   for(var key in RecordOneJson.drugUseList[0]){
			   $('.drug_situation_tr input[name="'+key+'"]').val(RecordOneJson.drugUseList[0][key])
		   }
		 
		   var selectCount = $('.MedicationUnit').eq(0).find('option');  
		   
	        for(var i = 0 ; i<selectCount.length;i++){  
	        	
	            if(selectCount[i].value==RecordOneJson.drugUseList[0].MedicationUnit){  
	                selectCount[i].selected=true;  
	            }  
	        }  
	   }else{
			var rowspan1=parseInt($('.drug_situation').attr("rowspan"));
			$('.drug_situation').attr("rowspan",rowspan1+1);
			//克隆自己添加在后面；
			var add_tr='	<tr  class="drug_situation_tr">'+
			'					<td><input type="text" name="DrugName" id="" value="'+RecordOneJson.drugUseList[i].DrugName+'" class="borderb"/></td>'+
			'					<td><input type="text" name="Usage" id="" value="'+RecordOneJson.drugUseList[i].Usage+'" class="borderb"/></td>'+
			'					<td><input type="text" name="Amount" id="" value="'+RecordOneJson.drugUseList[i].Amount+'" class="borderb"/></td>'+
			'					<td>'+
			'						<input type="" name="MedicationTime" id="" value="'+RecordOneJson.drugUseList[i].MedicationTime+'" class="borderb w40"/>'+
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
			//除了第一个x隐藏 其他的显示出来;
			$('.delete_drug2').show();
			$('.delete_drug2').eq(0).hide();
			
			var selectCount = $('.MedicationUnit').eq(i).find('option')
		        for(var h = 0 ; h<selectCount.length;h++){  
		            if(selectCount[i].value==RecordOneJson.drugUseList[i].MedicationUnit){  
		                selectCount[h].selected=true;  
		            }  
		        } 
			 var spanCount=$('.MedicationCompliance').eq(i).find('span')
			       for(var m = 0 ; h<spanCount.length;m++){  
				   if(RecordOneJson.drugUseList[i].MedicationCompliance==m+1){
					  
					   spanCount[m].toggleClass('bluecolor');
				   }
			   }
			 
	   }
	   
   })
   
   
 //非免疫 规划预防接种史
  
   var vaccList_s=RecordOneJson.vaccList
	   $.each(vaccList_s,function(i){
		  
		  if(i==0){
			     for(var key in vaccList_s[0]){
			        $("#vaccList input[name='"+ key +"']").val(vaccList_s[0][key]);
			      
			     }
		  }else{
			  
			   var rowspan1=parseInt($('.inoculation').attr("rowspan"));
				$('.inoculation').attr("rowspan",rowspan1+1);
				//克隆自己添加在后面；
				var ino_tr='<tr id="vaccList" class="inoculation_tr">'+
				'					<td><input type="text" name="VaccineName" id="" value="'+vaccList_s[i].VaccineName+'" class="borderb"/></td>'+
				'					<td><input type="date" name="VaccDate" id="" value="'+vaccList_s[i].VaccDate+'" class="borderb"/></td>'+
				'					<td colspan="3"><input type="text" name="VaccOrgName" id="" value="'+vaccList_s[i].VaccOrgName+'" class="borderb"/> <a href="javascript:void(0)" onclick="delete_inoculation(this)" >X</a></td>'+
				'				</tr>';
				$(".inoculation_tr").eq(-1).after(ino_tr)
				//除了第一个x隐藏 其他的显示出来;
				$('.delete_inoculation').show();
				$('.delete_inoculation').eq(0).hide();  
			  
			  
			  
		  }
		  
	  })

  
  
    
  //健康评价
      $('#Assessment span').each(function(i){
	   
	   if(RecordOneJson.master.Assessment==i+1){
		   $(this).toggleClass('bluecolor');
		   
	   }
   })
   
   $("input[name='AssessmentAbnormal']").val(RecordOneJson.master.AssessmentAbnormal)
   
  //健康指导  2的幂次
  /* var all_Guidance=$('#Guidance span')
  var all_RiskCrtl=$('#RiskCrtl span')
  var strs2 = RecordOneJson.master.Guidance.split(",");
  var strs3 = RecordOneJson.master.RiskCrtl.split(",");
  all_Guidance.each(function(i){
	    var id = String(Math.pow(2, i));//计算出当前症状对应的真实ID
	    if(strs2.indexOf(id) > -1){//如果这个ID存在于服务器给的值，则为这个元素添加高亮
	   
	    	all_Guidance.eq(i).toggleClass('bluecolor');
	    }
	});
  RiskCrtl.each(function(i){
	    var id = String(Math.pow(2, i));//计算出当前症状对应的真实ID
	    if(strs3.indexOf(id) > -1){//如果这个ID存在于服务器给的值，则为这个元素添加高亮
	   
	    	RiskCrtl.eq(i).toggleClass('bluecolor');
	    }
	});
    $("input[name='RiskCrtlWeight']").val(RecordOneJson.master.RiskCrtlWeight)
     $("input[name='RiskCrtlVaccine']").val(RecordOneJson.master.RiskCrtlVaccine)
      $("input[name='RiskCrtlOther']").val(RecordOneJson.master.RiskCrtlOther)
      $("input[name='HealthSummary']").val(RecordOneJson.master.HealthSummary) */
//健康指导   
var all_Guidance=$('#Guidance span')
var all_RiskCrtl=$('#RiskCrtl span')
all_Guidance.each(function(i){
	
	if(RecordOneJson.master.Guidance==i+1){
		$(this).toggleClass('bluecolor');
	}
	
})
all_RiskCrtl.each(function(i){
	
	if(RecordOneJson.master.RiskCrtl==i+1){
		$(this).toggleClass('bluecolor');
	}
	
})
  for(var key in RecordOneJson.master){
	  $("#RiskCrtl input[name='"+ key +"']").val(RecordOneJson.master[key]);

}
   
    $('#HealthSummary').val(RecordOneJson.master.HealthSummary)
    
//筛查回显  
 $('#Health span').each(function(i){
	   if(RecordOneJson.scaleScore.Health==i+1){
		 $(this).toggleClass('bluecolor');
	   }
   })
  
   $('#LifeSkills span').each(function(i){
	   if(RecordOneJson.scaleScore.LifeSkills==i+1){
		 $(this).toggleClass('bluecolor');
	   }
   })
   
   $('#CognitiveFunction span').each(function(i){
	   if(RecordOneJson.scaleScore.CognitiveFunction==i+1){
		 $(this).toggleClass('bluecolor');
	   }
   })
   
   $('#EmotionalState span').each(function(i){
	   if(RecordOneJson.scaleScore.EmotionalState==i+1){
		 $(this).toggleClass('bluecolor');
	   }
   })
   $('input[name=LifeSkillsScore]').val(RecordOneJson.scaleScore.LifeSkillsScore)
   $('input[name=CognitiveFunctionScore]').val(RecordOneJson.scaleScore.CognitiveFunctionScore)
   $('input[name=EmotionalStateScore]').val(RecordOneJson.scaleScore.EmotionalStateScore)

  //体检住院治疗情况
 $(function(){
	 
    $('input,select').each(function(){
        var that = $(this);
        var data_key = that.attr('data-json-key');	
        var data_by = that.attr('data-json-by');
        var data_sub_key = that.attr('name');
        if(!data_key || !data_by){
            return;
        }
        try{
            var data = eval('RecordOneJson.' + data_key);
            //hoslist
        }catch(e){
            console && console.warn(e);
        }
        if(data.constructor === Array){
            var splits = data_by.split(':');
            var sub_key = splits[0];
            var sub_value = splits[1];
            $.each(data, function(i){
                if(data[i][sub_key] == sub_value){
                    that.val(data[i][data_sub_key]);
                }
                //13579 24680
            });
        }
        
    });
    
  
    
    
    
}); 


  //认知功能 xuanzhe
    $("#xuanzhe").on('click',function(){
   	 
   	 layui.use('layer', function(){
        var layer = layui.layer;
   	 layer.open({
   		  type: 2,
   		  title: '认知功能检查',
   		  shadeClose: true,
   		  shade: 0.8,
   		  area: ['500px', '410px'],
   		  content: '/fdoctor/views/mobile/examination/oldPersoncognize.jsp?doctorName='+'${doctorName}'//iframe的url
   		});
   		return false;
   	 })
    });
    $("#xuanzhe2").on('click',function(){
   	 
   	 layui.use('layer', function(){
        var layer = layui.layer;
   	 layer.open({
   		  type: 2,
   		  title: '情感状态检查',
   		  shadeClose: true,
   		  shade: 0.8,
   		  area: ['500px', '410px'],
   		  content: '/fdoctor/views/mobile/examination/oldPersonfeeling.jsp?doctorName='+'${doctorName}'//iframe的url
   		});
   		return false;
   	 })
    });









//体检报告
$('#report').on('click',function(){
	 var remap=${Json}
	 console.log(remap)
	 var json = eval("("+remap+")");  
	 json.FOLLOW_UP_DATE=$('#HealthDateList option:selected').text().trim()
	 var personInfo=${pseonInfoJson}
	 var pseonInfoJson = eval("("+personInfo+")");
	 pseonInfoJson.FOLLOW_UP_DATE=$('input[name=rExamDate]').val().trim()
	 pseonInfoJson.AGE=json.Age
	 var remap=JSON.stringify(pseonInfoJson)  
   
	 $('#report').attr("href", "/fdoctor/mobile/report/query?remap="+remap+"&doctorName="+"${doctorName}");
	
})   

//日期改变时间
$('#HealthDateList').change(function(){
	 var remap=${Json}
	 var json = eval("("+remap+")"); 
	 json.FOLLOW_UP_DATE=$('#HealthDateList option:selected').text().trim()
	  var radte=JSON.stringify(json)
	 window.location.href="/fdoctor/mobile/examination/updateExamination?radte="+radte+"&doctorName="+"${doctorName}";
});  
 
 //由于加载的layerui 所以。
 layui.use('layer', function(){
  var layer = layui.layer;
  //各种提示;
	$("#help1").on('click',function(){
	layer.tips('脉率正常值参考范围60 ~ 100次。', '#help1', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help2").on('click',function(){
	layer.tips('呼吸频率正常值参考范围16 ~ 20次。', '#help2', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	
	$("#help3").on('click',function(){
	layer.tips('血压正常值参考范围140/90mmHg ~ 90/60mmHg,收缩压或舒张压一边不在正常值范围内既为血压异常。', '#help3', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help4").on('click',function(){
	layer.tips('体质指数=体重（kg）/身高的平方（m2；体质指数正常值参考范围18.5 ~ 23.99kg/m2。', '#help4', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	
	$("#help5").on('click',function(){
	layer.tips('告诉被检查者“我将要说三件物品的名称（如铅笔、卡车、书），请您立刻重复”。过1分钟后请其再次重复。如被检查者无法立即重复或1分钟后无法完整回忆三件物品名称为粗筛阳性，需进一步行“简易智力状态检查量表”检查。', '#help5', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help6").on('click',function(){
	layer.tips('询问被检查者“你经常感到伤心或抑郁吗”或“你的情绪怎么样”。如回答“是”或“我想不是十分好”，为粗筛阳性，需进一步行“老年抑郁量表”检查。', '#help6', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help7").on('click',function(){
	layer.tips('指主动锻炼，即有意识地为强体健身而进行的活动。不包括因工作或其他需要而必须进行的活动，如为上班骑自行车、做强体力工作等。锻炼方式填写最常采用的具体锻炼方式。', '#help7', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help8").on('click',function(){
	layer.tips('“从不吸烟者”不必填写“日吸烟量”、“开始吸烟年龄”、“戒烟年龄”等。', '#help8', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help9").on('click',function(){
	layer.tips('“从不饮酒者”不必填写其他有关饮酒情况项目。“日饮酒量”应折合相当于白酒“××两”。白酒1两折合葡萄酒4两、黄酒半斤、啤酒1瓶、果酒4两。', '#help9', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	
	$("#help10").on('click',function(){
	layer.tips('指因患者职业原因造成的化学品、毒物或射线接触情况。如有，需填写具体化学品、毒物、射线名或填不详。', '#help10', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help11").on('click',function(){
	layer.tips('填写采用对数视力表测量后的具体数值，对佩戴眼镜者，可戴其平时所用眼镜测量矫正视力。', '#help11', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help12").on('click',function(){
	layer.tips('在被检查者耳旁轻声耳语“你叫什么姓名”（注意检查时检查者的脸应在被检查者视线之外），判断被检查者听力状况。', '#help12', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help13").on('click',function(){
	layer.tips('请被检查者完成以下动作：“两手触枕后部”、“捡起这支笔”、“从椅子上站起，行走几步，转身，坐下。”判断被检查者运动功能', '#help13', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help14").on('click',function(){
	layer.tips('糖尿病患者必须进行此项检查。', '#help14', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help15").on('click',function(){
	layer.tips('主要询问乳房是否随月经有周期性疼痛，检查外观有无异常，有无异常泌乳及包块。', '#help15', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help16").on('click',function(){
	layer.tips('记录发育情况及婚产式（未婚、已婚未产或经产式），如有异常情况请具体描述。', '#help16', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help17").on('click',function(){
	layer.tips('记录是否通畅，黏膜情况、分泌物量、色、性状以及有无异味等。', '#help17', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help18").on('click',function(){
	layer.tips('记录大小、质地、有无糜烂、撕裂、息肉、腺囊肿；有无接触性出血、举痛等。', '#help18', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help19").on('click',function(){
	layer.tips('记录位置、大小、质地、活动度；有无压痛等。', '#help19', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		  return false;
	});
	$("#help20").on('click',function(){
	layer.tips('记录有无块物、增厚或压痛；若扪及块物，记录其位置、大小、质地；表面光滑与否、活动度、有无压痛以及与子宫及盆壁关系。左右两侧分别记录。', '#help20', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help21").on('click',function(){
	layer.tips('血红蛋白正常值参考范围110 ~ 160g/l。', '#help21', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help22").on('click',function(){
	layer.tips('白细胞正常值参考范围4.0 ~ 10 X 109/L。', '#help22', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help23").on('click',function(){
	layer.tips('血小板正常值参考范围100 ~ 300 X 109/L。', '#help23', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help24").on('click',function(){
	layer.tips('尿常规中的“尿蛋白、尿糖、尿酮体、尿潜血”可以填写定性检查结果，阴性填“－”，阳性根据检查结果填写“＋”、“＋＋”、“＋＋＋”或“＋＋＋＋”，也可以填写定量检查结果，定量结果需写明计量单位。', '#help24', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help25").on('click',function(){
	layer.tips('老年人健康体检、高血压患者、2型糖尿病患者和重性精神疾病患者年度健康检查时应免费检查的项目；空腹血糖正常值参考范围3.89 ~ 6.11mmol/L。', '#help25', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help26").on('click',function(){
	layer.tips('糖化血红蛋白为糖尿病患者应检查的项目，建议有条件的地区为糖尿病患者提供该项检查。', '#help26', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help27").on('click',function(){
	layer.tips('血清谷丙转氨酶正常值参考范围0 ~ 40U/L。', '#help27', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help28").on('click',function(){
	layer.tips('血清谷草转氨酶正常值参考范围0 ~ 40U/L。', '#help28', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help29").on('click',function(){
	layer.tips('总胆红素正常值参考范围5.1 ~ 28μmol/L。', '#help29', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help30").on('click',function(){
	layer.tips('血钾浓度、血钠浓度为高血压患者年度健康检查时应检查的项目，建议有条件的地区为高血压患者提供该项检查。', '#help30', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help31").on('click',function(){
	layer.tips('血清肌酐正常值参考范围70 ~ 106μmol/L。', '#help31', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help32").on('click',function(){
	layer.tips('血尿素氮正常值参考范围2.86 ~ 8.2 mmol/L。', '#help32', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help33").on('click',function(){
	layer.tips('总胆固醇正常值参考范围0 ~ 5.2 mmol/L。', '#help33', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	$("#help34").on('click',function(){
	layer.tips('甘油三酯正常值参考范围0 ~ 2.3 mmol/L。', '#help34', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	
	$("#help35").on('click',function(){
	layer.tips('表中列出的检查项目以外的辅助检查结果填写在“其他”一栏。', '#help35', {
	  tips: [2, '#2b98ff'],
		  time: 3000
		  });
		return false;
	});
	  
  
  
});             
 
 
</script>
		<script type="text/javascript">
									
										//选择正常牙齿 禁用input;
										$('.teeth_choose_no').on("click",function(){
											//正常加上颜色
											$(this).addClass("bluecolor");
											//其他取消颜色，input框框全部禁用,数字框框隐藏;
											$(".num_table").hide();
											$(".teeth_choose").removeClass('bluecolor');
											$(".teeth_choose").parent().next().find("input").attr('disabled',"true");
										})
										//点击选择牙齿 让input改变；
										$(".teeth_choose").on('click',function(){
											//选取里面的input
											var input=$(this).parent().next().find("input");
											//正常选项颜色取消
											$('.teeth_choose_no').removeClass('bluecolor')
											$(this).toggleClass("bluecolor")
											if(input.is(":disabled")){
												
												input.removeAttr('disabled')
											}else{
												input.attr('disabled',"true");
											}
										});
										
										//显示小框框
										$(".teeth_input").on('focus',function(){
											$(this).next().show();
										});
										
										//点击x关闭当前的小框框
										$(".hidden_x").on('click',function(){
											$(this).parents('.num_table').hide();
										});
										//点击小框框变色 input里面取值;
									$('.num_table tr>td:nth-child(-n+8)').on('click',function(){
											$(this).toggleClass('td_selected');
											var input=$(this).parents('.num_table').siblings();
											var inpVal='';
											
											//循环一次查看
											
											for(i=0;i<8;i++){
												if($(this).parent().find('td').eq(i).hasClass('td_selected')){
													var value=$(this).parent().find('td').eq(i).attr("value");
													inpVal+=value+",";
												}
											}
											input.val(inpVal);
									});
										
										
										//选项卡
										
										$(".chartSearch li").on('click',function(){
											var index=$(this).index();
											$(".tab_content").eq(index).show().siblings('.tab_content').hide();
											$(this).addClass('active').siblings().removeClass('active');
										})
										
								</script>
	</div>
	</body>
</html>
