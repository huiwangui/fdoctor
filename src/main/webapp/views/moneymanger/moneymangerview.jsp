<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资金管理</title>

<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<link href="${ctxStatic}/css/table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/menu.css">
<link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/body.css">
<link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/easyui.css">
<link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/icon.css">
<link href="${ctxStatic}/css/style.css" rel="stylesheet"  type="text/css" />
<link href="${ctxStatic}/css/common.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<script src="${ctxStatic}/js/page.js"></script>
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>

<style type="text/css">
.tabs-title {
	font-size: 15px
}
</style>

<style type="text/css" media=print>
	.noprint{display : none }
</style>

<script type="text/javascript">
		function searchResident() {
				var personName = $("#spersonName").val();
				var idCard = $("#sidCard").val();
				
				if(personName || idCard){
					$("#personName").val(personName);
					$("#idCard").val(idCard);
				}
				
				$("#searchForm").submit();
		
		}
		
		function changeTr(value) {
			
			 if(isckeck){
			 	var trdata = $(value).parents("tr");
			 	var bb = $(trdata).children().get(0);
			 	$("#showTable").append("<tr>")
			 	$(trdata).children().each(function(index,data){
			 		if(index > 0)
			 			$("#showTable").append("<td>"+$(data).html() + "</td>")
			 	})
			 	$("#showTable").append("</tr>")
			} 
			
		}
		function printHtml(){
			var chkSize = $("input:checkbox:checked").size();
			if(chkSize == 0){
				$.messager.alert('提示','未选择需要签约的居民信息','warning');
				return;
			}else{
				var  checkData = $("input:checkbox:checked");
				$(checkData).each(function(iindex,idata){
					var trdata = $(idata).parents("tr");
					var  trData = "<tr>"
					$(trdata).children().each(function(index,data){
			 			if(index > 0){
			 				trData = trData + "<td>"+$(data).html() + "</td>";
			 			}
			 				
			 		})
			 		trData = trData + "</tr>";
					$("#showTable").append(trData)
				})
			}
		
			//将选择的信息进行处理
			window.print();
		}
		
		$(function(){
				//选择checkbox 下面每个checkbox进行默认选择
				 $("input[type=checkbox]").first().click(function() {
		            $("input[type=checkbox]").prop("checked", this.checked);
		        });
		        
		        
		})
	
</script>
</head>
<body class="easyui-layout" >
	
	
	
	
	<!-- 头部界面 -->
	<div region="north" split="false" style="height:100px;"  class="noprint">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>资金管理</span>
			</div>
		</div>
		<form method="post" class="form-inline"  style="margin-bottom: 2px">
			<div class="form-group">
				<label style="font-size:15px">居民姓名:</label> <input type="text"
					id='spersonName'  placeholder="请输入居民姓名"
					class="form-control" value="${param.personName}">
			</div>

			<div class="form-group">
				<label style="font-size:15px">身份证:</label> <input type="text"
					style="width: 250px" id="sidCard" 
					placeholder="请输入居民省份证号码" class="form-control"
					value="${param.idCard}">
			</div>
			<div class="form-group">
				<a href="#" class="easyui-linkbutton" onclick="searchResident()">查询</a>
			</div>
		</form>
	</div>

	<div region="center"  class="noprint">
		<div class="locate-index  top-bar">
				资金明细
		</div>
		<form method="post" id="searchForm" class="data-form"
			action="${ctx}/moneymanger/moneyMangerInfoList.do">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			
			
			 <input type="hidden"   name="personName"  id="personName">
			 <input type="hidden"   name="idCard"   id="idCard">	
				
			<table class="table" cellspacing="0" cellpadding="0" id="checkTable">
				<tr>
					<th><input type="checkbox"></th>
					<th>姓名</th>
					<th>性别</th>
					<th>年齡(岁)</th>
					<th>身份证号码</th>
					<th>服务包</th>
					<th>签约时间</th>
					<th>居民自付(元)</th>
					<th>新农合补偿(元)</th>
				</tr>
				<tbody id="tbody-person-record">
					<c:forEach items="${page.list}" var="p">
						<tr>
							<td class="test">
								<input type="checkbox" name="idcard" class="checkPeople"
										value="${p.idCard }"  >
							</td>
							<td>${p.personName }</td>
							<td>
								<c:if test="${p.sex==0}">  
													       男
								</c:if> 
								<c:if test="${p.sex==1}">  
									       女
								</c:if>
							</td>
							<td>${p.age}</td>
							<td>${p.idCard}</td>
							<td>${p.packName }</td>
							<td><fmt:formatDate value="${p.signTime}" type="date"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${p.userPay}</td>
							<td>${p.compensate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</form>
		<div style="height:80px;z-index: 900">
				<!-- 分页信息 -->
				<div id="div-page">
					<table:page page="${page}"></table:page>
				</div>
				<button type="button" class="btn btn-blue"  onclick="printHtml()">打印支付资金凭证</button>
		</div>
	</div>

	<form class="data-form">
	<table  id="showTable"   class="table" cellspacing="0" cellpadding="0" >
			<tr>
					<th>姓名</th>
					<th>性别</th>
					<th>年齡(岁)</th>
					<th>身份证号码</th>
					<th>服务包</th>
					<th>签约时间</th>
					<th>居民自付(元)</th>
					<th>新农合补偿(元)</th>
				</tr>
			
	</table>
	</form>

</body>
</html>