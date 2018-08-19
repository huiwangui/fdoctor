<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>居民档案浏览</title>
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
 
	</head>
	<body>
		<div class="personMsg_box">
			<table   cellpadding="" >
				<tr>
					<td>姓名：</td>
					<td>${data.Name }</td>
					<td>身高：</td>
					<td id="selfHeight"> </td>
					<td>血压：</td>
					<td id="selfRightSbp">  </td>
					<td>健康档案号：</td>
					<td>${data.PersonCode }</td>
					<td rowspan="4"><img src="" alt="" />...</td>
				</tr>
				<tr>
					<td>性别：</td>
					<td>${Gender }</td>
					<td>体重：</td>
					<td id="selfWeight"></td>
					<td>血糖：</td>
					<td id="selfFastingBloodGlucose"></td>
					<td>身份证号码：</td>
					<td>${data.CardID }</td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td>${year }岁</td>
					<td>视力：</td>
					<td id="selfLeftEye"></td>
					<td>过敏史：</td>
					<td>${DrugAllergyHistorystr }</td>
					<td>联系电话：</td>
					<td>${data.PersonTel }</td>
				</tr>
				<tr>
					<td>地址：</td>
					<td colspan="3">${data.ResidenceAddress }</td>
					<td>既往病史：</td>
					<td colspan="3">
						 ${cmData }
					</td>
				</tr>
			</table>
		</div>
		
		<div class="prb_nav">
			<span>
				<select name="">
					<option value="">卫生服务活动</option>
					<option value="">疾病与健康为题</option>
					<option value="">生命周期</option>
					<option value="">检查检验</option>
					<option value="">最近就诊记录</option>
				</select>
			</span>
			<span>
				<a href="#">体检</a>
				<a href="#">随访</a>
				<a href="#">门诊</a>
				<a href="#">住院</a>
			</span>
			<span>
				<select id="followId"></select>
			</span>
		</div>
		
	 	<div class="broswer_table content1">
			<div class="tr" style="width: 98%;">
				体检机构：${RecordOneJson.hosList[0].OrgName}
			</div>
			<table border="1" >
				<tr>
					<td>体检日期 </td> 
					<td colspan="3">${listMtIdAndExamDate[1].ExamDate}</td>
					<td>责任医生</td>
					<td>${RecordOneJson.labora.DoctorName}</td>
				</tr>
				<tr>
					<td>症状 </td>
					<td colspan="5">${Symptom}</td>
				</tr>
				<tr>
					<td rowspan="3">一<br />般<br />状<br />态 </td> 
					<td>体温</td>
					<td>${lastExamination['BodyTemperature']}</td>
					<td>脉率</td>
					<td>${lastExamination['PulseRate']}</td>
					<td>/</td>
				</tr>
				<tr>
					<td>呼吸频率</td>
					<td>${lastExamination['RespiratoryRate']}</td>
					<td>血压 </td>
					<td>${lastExamination['LeftSbp']}/${lastExamination['LeftDbp']}</td>
					<td>/</td>
				</tr>
				<tr>
					<td>身高 </td>
					<td>${lastExamination['Height']}</td>
					<td>体重  </td>
					<td>${lastExamination['Weight']}</td>
					<td>${lastExamination['RightSbp']}/${lastExamination['RightDbp']}</td>
				</tr>
				<tr>
					<td rowspan="5">脏<br />器<br />功<br />能 </td>
					<td rowspan="2">口腔 </td>
					<td>口唇  </td>
					<td colspan="3">${Lips}</td>
				</tr>
				<tr>
					<td>咽部</td>
					<td colspan="3">${Throa}</td>
				</tr>
				<tr>
					<td>听力</td>
					<td colspan="4">${Hearing}</td>
				</tr>
				<tr>
					<td>视力</td>
					<td colspan="4">${MotorFunction}</td>
				</tr>
				<tr>
					<td>运动能力</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td rowspan="14">查<br />体</td> 
					<td>皮肤</td>
					<td colspan="4">${Skin}</td>
				</tr>
				<tr>
					<td>巩膜</td>
					<td colspan="4">${Sclera}</td>
				</tr>
				<tr>
					<td>淋巴结 </td>
					<td colspan="4">${LymphNodes}</td>
				</tr>
				<tr>
					<td rowspan="3">肺</td>
					<td>桶状胸</td>
					<td colspan="3">${BarrelChest}</td>
				</tr>
				<tr>
					<td>呼吸音 </td>
					<td colspan="3">${BreathSounds}</td>
				</tr>
				<tr>
					<td>罗音 </td>
					<td colspan="3">${Rale}</td>
				</tr>
				<tr>
					<td rowspan="2">心脏</td>
					<td>心律</td>
					<td colspan="3">${Rhythm}</td>
				</tr>
				<tr>
					<td>杂音</td>
					<td colspan="3">${HeartMurmur}</td>
				</tr>
				<tr>
					<td rowspan="5">腹部</td>
					<td>压痛</td>
					<td colspan="3">${AbdominalTenderness}</td>
				</tr>
				<tr>
					<td>包块</td>
					<td colspan="3">${AbdominalMass}</td>
				</tr>
				<tr>
					<td>肝大</td>
					<td colspan="3">${TheAbdomenLiver}</td>
				</tr>
				<tr>
					<td>脾大</td>
					<td colspan="3">${Splenomegaly}</td>
				</tr>
				<tr>
					<td>移动性浊音</td>
					<td colspan="3">${ShiftingDullness}</td>
				</tr>
				<tr>
					<td>其他</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td rowspan="23">辅<br />助<br />检<br />查</td>
					<td rowspan="2">血常规</td>
					<td>血红蛋白</td>
					<td>${RecordOneJson.labora.Hemoglobin}</td>
					<td>白细胞</td>
					<td>${RecordOneJson.labora.Leukocyte}</td>
				</tr>
				<tr>
					<td>血小板</td>
					<td>${RecordOneJson.labora.Platelet}</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td rowspan="2">尿常规</td>
					<td>尿蛋白</td>
					<td>${RecordOneJson.labora.UrineProtein}</td>
					<td>尿糖(GLU)</td>
					<td>${RecordOneJson.labora.Urine}</td>
				</tr>
				<tr>
					<td>尿胴体(KET)</td>
					<td>${RecordOneJson.labora.Ketone}</td>
					<td>尿潜血(BLO)</td>
					<td>${RecordOneJson.labora.OccultBloodInUrine}</td>
				</tr>
				<tr>
					<td>血糖</td>
					<td>空肚血糖</td>
					<td>${RecordOneJson.labora.FastingBloodGlucose}</td>
					<td>随机血糖</td>
					<td>${RecordOneJson.labora.RandomBloodGlucose}</td>
				</tr>
				<tr>
					<td>心电图</td>
					<td colspan="4">${Ecg}</td>
				</tr>
				<tr>
					<td>尿微量白蛋白</td>
					<td colspan="4">${RecordOneJson.labora.UrinaryAlbumin}</td>
				</tr>
				<tr>
					<td>大便潜血</td>
					<td colspan="4">${FecalOccultBlood}</td>
				</tr>
				<tr>
					<td>糖化血红蛋白</td>
					<td colspan="4">${RecordOneJson.labora.GlycatedHemoglobin}</td>
				</tr>
				<tr>
					<td>心电图</td>
					<td colspan="4">${Ecg}</td>
				</tr>
				<tr>
					<td>乙型肝炎抗原面</td>
					<td colspan="4">${HepatitisBSurfaceAntigen}</td>
				</tr>
				<tr>
					<td rowspan="3">肝功能</td>
					<td>血清谷丙转氨酶(ALT)</td>
					<td>${RecordOneJson.labora.SerumAla}</td>
					<td>血清谷草转氨酶(AST)</td>
					<td>${RecordOneJson.labora.SerumAa}</td>
				</tr>
				<tr>
					<td>白蛋白(ALB)</td>
					<td>${RecordOneJson.labora.Albumin}</td>
					<td> 总胆红素(TBIL)</td>
					<td>${RecordOneJson.labora.TotalBilirubin}</td>
				</tr>
				<tr>
					<td>结合胆红素(SDB)</td>
					<td>${RecordOneJson.labora.Bilirubin}</td>
					<td>/</td>
					<td>/</td>
				</tr>
				<tr>
					<td rowspan="2">肾功能</td>
					<td>血清肌酐(SCR)</td>
					<td>${RecordOneJson.labora.SerumCreatinine}</td>
					<td>血尿素氮</td>
					<td>${RecordOneJson.labora.Bun}</td>
				</tr>
				<tr>
					<td>血钾浓度(K+)</td>
					<td>${RecordOneJson.labora.PotassiumConcentration}</td>
					<td>血钠浓度(Na+)</td>
					<td>${RecordOneJson.labora.SodiumConcentration}</td>
				</tr>
				<tr>
					<td rowspan="4">血脂</td>
					<td>总胆固醇(TC)</td>
					<td colspan="3">${RecordOneJson.labora.TotalCholesterol}</td>
				</tr>
				<tr>
					<td>甘油三酯(TG)</td>
					<td colspan="3">${RecordOneJson.labora.Triglycerides}</td>
				</tr>
				<tr>
					<td>血清低密度脂蛋白胆固醇(LDL-C)</td>
					<td colspan="3">${RecordOneJson.labora.LdlCholesterol}</td>
				</tr>
				<tr>
					<td>血清高密度脂蛋白胆固醇(HDL-C)</td>
					<td colspan="3">${RecordOneJson.labora.HdlCholesterol}</td>
				</tr>
				<tr>
					<td>胸部X线片</td>
					<td colspan="4">${ChestXRay}</td>
				</tr>
				<tr>
					<td>B超</td>
					<td colspan="4">${BUltrasonicWave}</td>
				</tr>
				<tr>
					<td>宫颈涂片</td>
					<td colspan="4">${CervicalSmear}</td>
				</tr>
				<tr>
					<td>健康评价</td>
					<td colspan="5">${Assessment}</td>
				</tr>
				<tr>
					<td>健康指导</td>
					<td colspan="5">${Guidance}</td>
				</tr>
				<tr>
					<td>危险因素控制</td>
					<td colspan="5">${RiskCrtl}</td>
				</tr>
				<tr>
					<td>健康摘要</td>
					<td colspan="5">${RecordOneJson.master.HealthSummary}</td>
				</tr> 
			</table>
		</div>
		
		
		<div class="content1" hidden="">
			<div class="tr" style="width: 98%;">
				随访机构：
			</div>
			<table border="1">
				<tr>
					<td>随访时间</td>
					<td colspan="2" id="FollowUpDate"></td>
					<td>随访方式</td>
					<td id="WayUp"></td>
				</tr>
				<tr>
					<td>症状</td>
					<td colspan="4" id="symptom"></td>
				</tr>
				<tr>
				
					<td rowspan="3">体征</td>
					<td>血压(mmHg)</td>
					<td id="RightSbp"></td>
					<td>体重(kg)</td>
					<td id="Weight"></td>
				</tr>
				<tr>
					<td>身高(cm)</td>
					<td id="Height"></td>
					<td>体质指数</td>
					<td id="Bmi"></td>
				</tr>
				<tr>
					<td>心率</td>
					<td id="HeartRate"></td>
                    <td></td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="3">生活方式</td>
					<td >日吸烟量(支)</td>
					<td id="Smoking"></td>
					<td>日饮酒量(两)</td>
					<td id="DailyAlcoholIntake"></td>
				</tr>
				<tr>
					<td>运动</td>
					<td id="ExerciseWeekTimes"></td>
					<td>摄盐情况</td>
					<td id="SaltIntake"></td>
				</tr>
				<tr>
					<td >心理调整</td>
					<td id="PsychologicalAdjustment"> </td>
					<td>遵医行为</td>
					<td id="ComplianceBehavior"></td>
				</tr>
				<tr>
					<td>服药依从性</td>
					<td colspan="2" id="MedicationCompliance"></td>
					<td>药物不良反应</td>
					<td id="AdverseDrugReactions"></td>
				</tr>
				<tr>
					<td>此次随访分类</td>
					<td colspan="4" id="FuClassification"></td>
				</tr>
				<tr>
					<td>用药情况</td>
					<td colspan="4" id="drugJson"></td>
				</tr>
				<tr>
					<td>随风结局</td>
					<td colspan="4" id="FollowUpRemarks"></td>
				</tr>
				<tr>
					<td>下次随访时间</td>
					<td colspan="2" id="NextFollowUpDate"></td>
					<td>随访医生</td>
					<td id="DoctorName"></td>
				</tr>
			</table>
		</div>
		
		
		<div class="content1" hidden="">
			<div class="tr" style="width: 98%;">
				门诊机构：
			</div>
			<table border="1">
				<tr>
					<td colspan="2">门诊处方信息</td>
				</tr>
				<tr>
					<td style="width: 200px;">诊断：</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td>主诉：</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td>现病史：</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td>辅助检查：</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td>皮试：</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td>处置：</td>
					<td colspan="4"></td>
				</tr>
			</table>
		</div>
		
		
		<div class="content1" hidden="">
			<div class="tr" style="width: 98%;">
				门诊机构：
			</div>
			<h1 class="tc">
				住院摘要
			</h1>
			<table border="1">
				<tr>
					<td>健康档案号</td>
					<td></td>
					<td>住院号</td>
					<td></td>
				</tr>
				<tr>
					<td>生日</td>
					<td></td>
					<td>身份证号</td>
					<td></td>
				</tr>
				<tr>
					<td>联系电话</td>
					<td></td>
					<td>家庭住址</td>
					<td></td>
				</tr>
				<tr>
					<td>住院机构</td>
					<td></td>
					<td>入院科室</td>
					<td></td>
				</tr>
				<tr>
					<td>入院日期</td>
					<td></td>
					<td>入院原因</td>
					<td></td>
				</tr>
				<tr>
					<td>责任医生</td>
					<td></td>
					<td>出院日期</td>
					<td></td>
				</tr>
				<tr>
					<td>入院病情</td>
					<td></td>
					<td>转归状态</td>
					<td></td>
				</tr>
				<tr>
					<td>入院诊断</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td>出院诊断</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td>住院总金额</td>
					<td></td>
					<td>结算方式</td>
					<td></td>
				</tr>
			</table>
			
		</div>
	</body>
	<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">

	 $(function(){
			//随访处理
			var followupstr ='${followlist}';
			var followlist=JSON.parse(followupstr);
			if(followlist!=""&&followlist!=null){
				$("#followId").html("");
		    	for(i=0;i<followlist.length;i++){
		    		var option='<option value='+followlist[i].ID+' >'+followlist[i].FollowUpDateStr+'</option>'
		    		$("#followId").append(option);
		    	}
		    }
		 $("#followId").change(function(){
			  $.ajax({
				    type: "GET",
					url: "/fdoctor/mobile/healthRecord/personRecordbrowseFollowup",
					data: "ID="+$("#followId").val()+"&ProductCode="+'${ProductCode}',
					dataType:'json',
					success: function(msg){
						//随访时间回显	 	
						 var FollowUpDate = msg.followData.cmHypertension.FollowUpDate ;
						 var timeSpan = FollowUpDate.replace('/Date','').replace('(','').replace(')/','').replace(/\//g,'');
						 var d = new Date(parseInt(timeSpan));
						 d= d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
						 $("#FollowUpDate").text(d);	
						 //下次随访时间回显
						 var NextFollowUpDate = msg.followData.cmHypertension.NextFollowUpDate;
						 var ntimeSpan = NextFollowUpDate.replace('/Date','').replace('(','').replace(')/','').replace(/\//g,'');
						 var nd = new Date(parseInt(ntimeSpan));
						 nd= nd.getFullYear() + '-' + (nd.getMonth() + 1) + '-' + nd.getDate();
						 $("#NextFollowUpDate").text(nd);	
						 //随访方式回显
						 var WayUp = msg.followData.cmHypertension.WayUp  ;
						 if("1"==WayUp){
							 $("#WayUp").text("门诊")
						 }else if("2"==WayUp){
							 $("#WayUp").text("家庭")
						 }else if("3"==WayUp){
							 $("#WayUp").text("电话")
						 }
						 //症状回显
						 $("#symptom").text(msg.symptom)
						 //血压回显
						 $("#RightSbp").text(msg.followData.examBody.RightSbp +"/"+msg.followData.examBody.RightDbp )
						 $("#selfRightSbp").text(msg.followData.examBody.RightSbp +"/"+msg.followData.examBody.RightDbp+"mmHg" )
						//体重回显
						 $("#Weight").text(msg.followData.examBody.Weight)
						 $("#selfWeight").text(msg.followData.examBody.Weight+"kg")
						 //血糖回显
						  $("#selfFastingBloodGlucose").text(msg.followData.examLaboratory.FastingBloodGlucose)
						  //视力回显
						   $("#selfLeftEye").text(msg.followData.examOrgan.LeftEye+"/"+msg.followData.examOrgan.RightEye)
						 //身高回显
						 $("#Height").text(msg.followData.examBody.Height);
						 $("#selfHeight").text(msg.followData.examBody.Height+"cm")
						 //体质指数回显
						 $("#Bmi").text(msg.followData.examBody.Bmi)
						 //心率回显
						  $("#HeartRate").text(msg.followData.examBody.HeartRate)
						 //日吸烟量回显
						 $("#Smoking").text(msg.followData.examLifestyle.Smoking )
						 //日饮酒量回显
						 $("#DailyAlcoholIntake").text(msg.followData.examLifestyle.DailyAlcoholIntake )
						 //运动回显
						 var ExerciseWeekTimes= msg.followData.examLifestyle.ExerciseWeekTimes +'次/周 '+  msg.followData.examLifestyle.EachExerciseTime+' 分钟/次';
						 $("#ExerciseWeekTimes").text(ExerciseWeekTimes )
						 //用药情况回显drugJson
						 var drugJsonList =msg.followData.drugJson;
						 if(drugJsonList.length>0){
							 for(var i=0;i<drugJsonList.length;i++){
								 $("#drugJson").append("药品名称:"+drugJsonList[i].DrugName).append("每日:"+drugJsonList[i].DailyTimes+"次").append("每次:"+drugJsonList[i].EachDose+drugJsonList[i].Remark)
							 }
						 }
						 //随访结局回显
						 $("#FollowUpRemarks").text(msg.followData.cmHypertension.FollowUpRemarks )
						 //随访医生回显
						 $("#DoctorName").text(msg.followData.cmHypertension.DoctorName  )
						 //摄盐情况回显
						 var SaltIntake = msg.followData.cmHypertension.SaltIntake ;
						 if("1"==SaltIntake){
							 $("#SaltIntake").text("轻")
						 }else if("2"==SaltIntake){
							 $("#SaltIntake").text("中")
						 }else if("3"==SaltIntake){
							 $("#SaltIntake").text("重")
						 }
						 //心理调整
						 var PsychologicalAdjustment = msg.followData.cmHypertension.PsychologicalAdjustment ;
						 if("1"==PsychologicalAdjustment){
							 $("#PsychologicalAdjustment").text("良好")
						 }else if("2"==PsychologicalAdjustment){
							 $("#PsychologicalAdjustment").text("一般")
						 }else if("3"==PsychologicalAdjustment){
							 $("#PsychologicalAdjustment").text("差")
						 }
						 //遵医行为
						 var ComplianceBehavior = msg.followData.cmHypertension.ComplianceBehavior ;
						 if("1"==ComplianceBehavior){
							 $("#ComplianceBehavior").text("良好")
						 }else if("2"==ComplianceBehavior){
							 $("#ComplianceBehavior").text("一般")
						 }else if("3"==ComplianceBehavior){
							 $("#ComplianceBehavior").text("差")
						 }	
						 //服药依从性
						 var MedicationCompliance = msg.followData.cmHypertension.MedicationCompliance ;
						 if("1"==MedicationCompliance){
							 $("#MedicationCompliance").text("规律")
						 }else if("2"==MedicationCompliance){
							 $("#MedicationCompliance").text("间断")
						 }else if("3"==MedicationCompliance){
							 $("#MedicationCompliance").text("不服药")
						 }	
						 //此次随访分类
						 var FuClassification = msg.followData.cmHypertension.FuClassification ;
						 if("1"==FuClassification){
							 $("#FuClassification").text("控制满意")
						 }else if("2"==FuClassification){
							 $("#FuClassification").text("控制不满意")
						 }else if("3"==FuClassification){
							 $("#FuClassification").text("不良反应")
						 }else if("4"==FuClassification){
							 $("#FuClassification").text("并发症")
						 }
					}   
				})   
			 //window.location.href="/fdoctor/mobile/healthRecord/personRecordbrowseFollow?ID="+$("#followId").val();
		 });
		//随访时间回显	 	
		/*  var FollowUpDate = '${followData.cmHypertension.FollowUpDate }';
		 var timeSpan = FollowUpDate.replace('/Date','').replace('(','').replace(')/','').replace(/\//g,'');
		 var d = new Date(parseInt(timeSpan));
		 d= d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
		 $("#FollowUpDate").text(d);	
		 //下次随访时间回显
		 var NextFollowUpDate = '${followData.cmHypertension.NextFollowUpDate }';
		 var ntimeSpan = NextFollowUpDate.replace('/Date','').replace('(','').replace(')/','').replace(/\//g,'');
		 var nd = new Date(parseInt(ntimeSpan));
		 nd= nd.getFullYear() + '-' + (nd.getMonth() + 1) + '-' + nd.getDate();
		 $("#NextFollowUpDate").text(nd);	
		 //随访方式回显
		 var WayUp = '${followData.cmHypertension.WayUp }';
		 if("1"==WayUp){
			 $("#WayUp").text("门诊")
		 }else if("2"==WayUp){
			 $("#WayUp").text("家庭")
		 }else if("3"==WayUp){
			 $("#WayUp").text("电话")
		 }
		 //症状回显
		 $("#symptom").text('${symptom}')
		 //摄盐情况回显
		 var SaltIntake = '${followData.cmHypertension.SaltIntake }';
		 if("1"==SaltIntake){
			 $("#SaltIntake").text("轻")
		 }else if("2"==SaltIntake){
			 $("#SaltIntake").text("中")
		 }else if("3"==SaltIntake){
			 $("#SaltIntake").text("重")
		 }
		 //心理调整
		 var PsychologicalAdjustment = '${followData.cmHypertension.PsychologicalAdjustment }';
		 if("1"==PsychologicalAdjustment){
			 $("#PsychologicalAdjustment").text("良好")
		 }else if("2"==PsychologicalAdjustment){
			 $("#PsychologicalAdjustment").text("一般")
		 }else if("3"==PsychologicalAdjustment){
			 $("#PsychologicalAdjustment").text("差")
		 }
		 //遵医行为
		 var ComplianceBehavior = '${followData.cmHypertension.ComplianceBehavior }';
		 if("1"==ComplianceBehavior){
			 $("#ComplianceBehavior").text("良好")
		 }else if("2"==ComplianceBehavior){
			 $("#ComplianceBehavior").text("一般")
		 }else if("3"==ComplianceBehavior){
			 $("#ComplianceBehavior").text("差")
		 }	
		 //服药依从性
		 var MedicationCompliance = '${followData.cmHypertension.MedicationCompliance }';
		 if("1"==MedicationCompliance){
			 $("#MedicationCompliance").text("规律")
		 }else if("2"==MedicationCompliance){
			 $("#MedicationCompliance").text("间断")
		 }else if("3"==MedicationCompliance){
			 $("#MedicationCompliance").text("不服药")
		 }	
		 //此次随访分类
		 var FuClassification = '${followData.cmHypertension.FuClassification }';
		 if("1"==FuClassification){
			 $("#FuClassification").text("控制满意")
		 }else if("2"==FuClassification){
			 $("#FuClassification").text("控制不满意")
		 }else if("3"==FuClassification){
			 $("#FuClassification").text("不良反应")
		 }else if("4"==FuClassification){
			 $("#FuClassification").text("并发症")
		 }	  */
	 })
		
 
	
	 
		$('.prb_nav a').on('click',function(){
			var index=$(this).index();
			$('.content1').eq(index).show().siblings('.content1').hide();
			return false;
		})
		
	</script>
</html>
