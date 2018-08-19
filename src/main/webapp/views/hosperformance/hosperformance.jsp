<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>绩效管理（卫计委行政管理机构）</title>
<script src="${ctx}/statics/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/page.js"></script>
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/menu.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/body.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
<link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/css/table.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>
<script src="${ctxStatic}/js/ldialog.js"></script>
<style type="text/css">
.tabs-title {
	font-size: 15px
}
.Tab th{ border:solid 1px gray}
.circle {
	width: 80px;
	height: 80px;
	position: absolute;
	border-radius: 50%;
	background: #0cc;
	cursor:pointer;
}
 
.left,.right{
	width:80px;
	height:80px;
	position: absolute;
	top: 0px;left: 0px;
}
.pie_left1, .pie_right1,.pie_left2, .pie_right2,.pie_left3, .pie_right3{
	width:80px;
	height:80px;
	position: absolute;
	border-radius: 50%;
	top: 0px;left: 0px;
	background: Silver;
}
.pie_right1,.right,.pie_right2,.pie_right3 {
	clip:rect(0,auto,auto,40px);
}
.pie_left1,.left,.pie_left2,.pie_left3{
	clip:rect(0,40px,auto,0);
}
 
/*
*当top和left取值为auto时，相当于0
*当bottom和right取值为auto时，相当于100%
*/
.mask1,.mask2,.mask3 {
	width: 65px;
	height: 65px;
	border-radius: 50%;
	left: 7.5px;
	top: 7.5px;
	background: #FFF;
	position: absolute;
	text-align: center;
	line-height: 65px;
	font-size: 16px;
}
.progressname {
	width: 75px;
	height: 75px;
	position: absolute;
	text-align: center;
	line-height: 110px;
	font-size: 13px;
}
</style>
</head>
<body class="easyui-layout" style="padding: 2px">
	<div region="north" split="false" style="height:140px;">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>绩效管理（卫计委行政管理机构）</span>
			</div>
		</div>
		<form method="post" class="form-inline" id="searchForm" action=""
			style="margin-bottom: 2px">
			
			<div style="padding-left:125px;">
				<div class="circle" onclick="switch1('${page.name1}')">
					<div class="pie_left1">
						<div class="left"></div>
					</div>
					<div class="pie_right1">
						<div class="right"></div>
					</div>
					<div class="mask1"><font color="red"><span>${page.num1}</span>%</font></div>
					<div class="progressname">${page.name1}</div>
				</div>
			</div>
			<div style="padding-left:325px;">
				<div class="circle" onclick="switch1('${page.name2}')">
					<div class="pie_left2">
						<div class="left"></div>
					</div>
					<div class="pie_right2">
						<div class="right"></div>
					</div>
					<div class="mask2"><font color="red"><span>${page.num2}</span>%</font></div>
					<div class="progressname">${page.name2}</div>
				</div>
			</div>
			<div style="padding-left:525px;">
				<div class="circle" onclick="switch1('${page.name3}')">
					<div class="pie_left3">
						<div class="left"></div>
					</div>
					<div class="pie_right3">
						<div class="right"></div>
					</div>
					<div class="mask3"><font color="red"><span>${page.num3}</span>%</font></div>
					<div class="progressname">${page.name3}</div>
				</div>
			</div>
		</form>
	</div>
	<div region="center" border="false">
		<div id="performance" class="easyui-tabs"  fit="true">
			<div title="完成率" id="full">
				<form method="post" id="performanceForm" class="data-form" action="">
					
					<table class="table" cellspacing="0" cellpadding="0"   id="checkTable">
						<tr id="trhead1">
							<th>医疗机构</th>
							<th>签约人数</th>
						</tr>
						<tbody id="tbody-performance">
							<c:forEach items="${page.performance}" var="p">
								<tr>
									<td>${p.hosName}</td>
									<td>${p.sigCount}人</td>
									<c:forEach items="${p.list}" var="pack">
										<td><a href="javaScript:doView('${p.hosId}','${pack.sigId}',1)">${pack.num}人</a></td>
									</c:forEach>
									<td>${p.fullCount}</td>
									<td>${p.serCount}</td>
									<td>${p.progress1}</td>
								</tr>
							</c:forEach> 
						</tbody>
					</table>
					
				</form>
				<table class="table Tab" cellspacing="0" cellpadding="0" id="residentTable" style="width:600px;">
					<tbody id="tbody-resident1"></tbody>
				</table>
			</div>
			<div title="及时性" id="time">
				<form method="post" id="performanceForm" class="data-form" action="">
					
					<table class="table" cellspacing="0" cellpadding="0"   id="checkTable">
						<tr id="trhead2">
							<th>医疗机构</th>
							<th>签约人数</th>
						</tr>
						<tbody id="tbody-performance">
							<c:forEach items="${page.performance}" var="p">
								<tr>
									<td>${p.hosName}</td>
									<td>${p.sigCount}人</td>
									<c:forEach items="${p.list}" var="pack">
										<td><a href="javaScript:doView('${p.hosId}','${pack.sigId}',2)">${pack.num}人</a></td>
									</c:forEach>
									<td>${p.timeCount}</td>
									<td>${p.serCount}</td>
									<td>${p.progress2}</td>
								</tr>
							</c:forEach> 
						</tbody>
					</table>
					
				</form>
				<table class="table Tab" cellspacing="0" cellpadding="0" id="residentTable" style="width:700px;">
					<tbody id="tbody-resident2"></tbody>
				</table>
			</div>
			
			<div title="缺项率" id="out">
				<form method="post" id="performanceForm" class="data-form" action="">
					
					<table class="table" cellspacing="0" cellpadding="0"   id="checkTable">
						<tr id="trhead3">
							<th>医疗机构</th>
							<th>签约人数</th>
						</tr>
						<tbody id="tbody-performance">
							<c:forEach items="${page.performance}" var="p">
								<tr>
									<td>${p.hosName}</td>
									<td>${p.sigCount}人</td>
									<c:forEach items="${p.list}" var="pack">
										<td><a href="javaScript:doView('${p.hosId}','${pack.sigId}',3)">${pack.num}人</a></td>
									</c:forEach>
									<td>${p.fullItem}</td>
									<td>${p.items}</td>
									<td>${p.progress3}</td>
								</tr>
							</c:forEach> 
						</tbody>
					</table>
					
				</form>
				<table class="table Tab" cellspacing="0" cellpadding="0" id="residentTable" style="width:600px;">
					<tbody id="tbody-resident3"></tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
	$(function(){
		initial();
		initialProgress();
	});
	
	//加载表格表头项
	function initial(){
		$.ajax({
			url: "${ctx}/hosperformance/querySigPacks",
			type:"post",
			data: '',
			dataType:"text",
			success: function(data){
				var obj = eval('('+data+')');
				if(obj.success){
					var sigPacks1 = $("#trhead1");
					var sigPacks2 = $("#trhead2");
					var sigPacks3 = $("#trhead3");
					for(var i in obj.result){
						sigPacks1.append("<th>"+obj.result[i].packName+"</th>");
						sigPacks2.append("<th>"+obj.result[i].packName+"</th>");
						sigPacks3.append("<th>"+obj.result[i].packName+"</th>");
					}
					sigPacks1.append("<th>机构已提供服务次数</th><th>机构应服务次数</th><th>进度条</th>");
					sigPacks2.append("<th>机构及时服务次数</th><th>机构应服务次数</th><th>进度条</th>");
					sigPacks3.append("<th>机构已提供服务项</th><th>机构应服务项</th><th>进度条</th>");
				}else{
					alert("查询服务包失败，失败原因："+obj.result);
				}
			},
			error:function(data){
				alert("出现异常，异常原因【" + data + "】!");    
	        }  
		});
	}
	
	//加载扇形进度条
	function initialProgress(){
		if( $(".mask1 span").text() <= 50 ){
			$(".pie_right1").css("transform","rotate("+($(".mask1 span").text()*3.6)+"deg)");
		}else{
			$(".pie_right1").css("transform","rotate(180deg)");
			$(".pie_left1").css("transform","rotate("+(($(".mask1 span").text()-50)*3.6)+"deg)");
		}
		if( $(".mask2 span").text() <= 50 ){
			$(".pie_right2").css("transform","rotate("+($(".mask2 span").text()*3.6)+"deg)");
		}else{
			$(".pie_right2").css("transform","rotate(180deg)");
			$(".pie_left2").css("transform","rotate("+(($(".mask2 span").text()-50)*3.6)+"deg)");
		}
		if( $(".mask3 span").text() <= 50 ){
			$(".pie_right3").css("transform","rotate("+($(".mask3 span").text()*3.6)+"deg)");
		}else{
			$(".pie_right3").css("transform","rotate(180deg)");
			$(".pie_left3").css("transform","rotate("+(($(".mask3 span").text()-50)*3.6)+"deg)");
		}
	}
	
	//查看该签约包下的居民
	function doView(hosId,sigId,num){
		$.ajax({
			url: "${ctx}/hosperformance/queryResident",
			type:"post",
			data: 'hosId='+hosId+'&sigId='+sigId,
			dataType:"text",
			success: function(data){
				var obj = eval('('+data+')');
				if(obj.success){
					$("#tbody-resident"+num).html("");
					if(obj.result.length>0){
						$("#tbody-resident"+num).append("<tr><th>姓名</th><th>性别</th><th>年龄</th><th>身份证号</th></tr>");
						for(var i in obj.result){
							$("#tbody-resident"+num).append("<tr><th>"+obj.result[i].personName+"</th><th>"+obj.result[i].sex+"</th><th>"+obj.result[i].age+"</th><th>"+obj.result[i].idCard+"</th></tr>");
						}
					}
				}else{
					$("#tbody-resident"+num).html("");
					alert("查询该签约包下的居民失败，失败原因："+obj.result);
				}
			},
			error:function(data){
				alert("出现异常，异常原因【" + data + "】!");    
	        }  
		});
	}
	
	//点击扇形图切换表格
	function switch1(name){
		if(name=="完成率"){
			$(".tabs-inner")[0].click();
		}
		if(name=="及时性"){
			$(".tabs-inner")[1].click();
		}
		if(name=="缺项率"){
			$(".tabs-inner")[2].click();
		}
		
	}
	
</script>
</html>