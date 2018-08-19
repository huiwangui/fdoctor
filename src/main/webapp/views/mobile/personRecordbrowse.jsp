<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->

<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
	</head>
	<body >
	
		<div class="personMsg_box">
			<table   cellpadding="" >
				<tr>
					<td>姓名：</td>
					<td>data</td>
					<td>身高：</td>
					<td>data cm</td>
					<td>血压：</td>
					<td>data /mmHg</td>
					<td>健康档案号：</td>
					<td>data</td>
					<td rowspan="4"><img src="" alt="" />...</td>
				</tr>
				<tr>
					<td>性别：</td>
					<td>data</td>
					<td>体重：</td>
					<td>data</td>
					<td>血糖：</td>
					<td>data</td>
					<td>身份证号码：</td>
					<td>data</td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td>data</td>
					<td>视力：</td>
					<td>data</td>
					<td>过敏史：</td>
					<td>data</td>
					<td>联系电话：</td>
					<td>data</td>
				</tr>
				<tr>
					<td>地址：</td>
					<td colspan="3"></td>
					<td>既往病史：</td>
					<td colspan="3">
						<div class="dispmb">糖</div>
						<div class="dispmb">精</div>
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
				<a href="/">体检</a>
				<a href="/">随访</a>
				<a href="/">门诊</a>
				<a href="/">住院</a>
			</span>
			<span>
				<input type="date" name="" id="" value="" />
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
				随访机构：dataasdasfasfsadfsd
			</div>
			<table border="1">
				<tr>
					<td>随访时间</td>
					<td colspan="2">data</td>
					<td>随访方式</td>
					<td>data</td>
				</tr>
				<tr>
					<td>症状</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td>体征</td>
					<td>血压(mmHg)</td>
					<td>data</td>
					<td>体重(kg)</td>
					<td>data</td>
				</tr>
				<tr>
					<td>身高(cm)</td>
					<td>data</td>
					<td>体质指数</td>
					<td>data</td>
				</tr>
				<tr>
					<td rowspan="3">日吸烟量(支)</td>
					<td>data</td>
					<td>日饮酒量(两)</td>
					<td>data</td>
				</tr>
				<tr>
					<td>运动</td>
					<td>data</td>
					<td>摄盐情况</td>
					<td>data</td>
				</tr>
				<tr>
					<td>心理调整</td>
					<td>data</td>
					<td>遵医行为</td>
					<td>data</td>
				</tr>
				<tr>
					<td>服药依从性</td>
					<td colspan="2">data</td>
					<td>药物不良反应</td>
					<td>data</td>
				</tr>
				<tr>
					<td>此次随访分类</td>
					<td colspan="4">data</td>
				</tr>
				<tr>
					<td>用药情况</td>
					<td colspan="4">data</td>
				</tr>
				<tr>
					<td>随风结局</td>
					<td colspan="4">data</td>
				</tr>
				<tr>
					<td>下次随访时间</td>
					<td colspan="2">data</td>
					<td>随访医生</td>
					<td>data</td>
				</tr>
			</table>
		</div>
		
		
		<div class="content1" hidden="">
			<div class="tr" style="width: 98%;">
				门诊机构：dataasdasfasfsadfsd
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
				门诊机构：dataasdasfasfsadfsd
			</div>
			<h1 class="tc">
				住院摘要
			</h1>
			<table border="1">
				<tr>
					<td>健康档案号</td>
					<td>data</td>
					<td>住院号</td>
					<td>data</td>
				</tr>
				<tr>
					<td>生日</td>
					<td>data</td>
					<td>身份证号</td>
					<td>data</td>
				</tr>
				<tr>
					<td>联系电话</td>
					<td>data</td>
					<td>家庭住址</td>
					<td>data</td>
				</tr>
				<tr>
					<td>住院机构</td>
					<td>data</td>
					<td>入院科室</td>
					<td>data</td>
				</tr>
				<tr>
					<td>入院日期</td>
					<td>data</td>
					<td>入院原因</td>
					<td>data</td>
				</tr>
				<tr>
					<td>责任医生</td>
					<td>data</td>
					<td>出院日期</td>
					<td>data</td>
				</tr>
				<tr>
					<td>入院病情</td>
					<td>data</td>
					<td>转归状态</td>
					<td>data</td>
				</tr>
				<tr>
					<td>入院诊断</td>
					<td colspan="3">data</td>
				</tr>
				<tr>
					<td>出院诊断</td>
					<td colspan="3">data</td>
				</tr>
				<tr>
					<td>住院总金额</td>
					<td>data</td>
					<td>结算方式</td>
					<td>data</td>
				</tr>
			</table>
			
		</div>
	</body>
	<script type="text/javascript">
	var radte={}
	radte['ID']="CD8B44CC9AB7411BA693C4EE8524D39F"
		$('.prb_nav a').on('click',function(){
			var index=$(this).index();
			$('.content1').eq(index).show().siblings('.content1').hide();
			return false;
		})
		
		$('.content1').eq(0).trigger('click')
		
		function examination(){
		console.log(2222)
		$.ajax({
		    type: 'GET',
		    url: 'browseExamination',
		    data: {
		    	radte:JSON.stringify(radte),
		    	ID:"CD8B44CC9AB7411BA693C4EE8524D39F"
		    } 
		})
		
	    }	
		
	</script>
</html>
