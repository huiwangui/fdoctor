<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看服务记录</title>
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

td {
	border: 1px #D1D1D1 solid
}

.form-group {
	margin-bottom: 10px
}

.l-btn-left {
	background-color: #53E0D7;
	width: 100px
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增服务记录</title>
</head>
<body class="easyui-layout" style="padding: 2px">
	<div region="north" split="false" style="height:140px;">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>签约服务记录/</span><span>查看服务记录</span>
			</div>
		</div>
		<form method="post" class="form-inline" id="searchForm" action="${ctx}/signRecord/queryRecordInfo"
			style="margin-bottom: 2px">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" /> 
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
			<div class="form-group">
				<label style="font-size:15px">姓名:</label> 
				<input type="text" id='personName'  name="personName" readonly=true
					class="form-control"  value="${personName}" style="width:80px;">
				<label style="font-size:15px">性别:</label> 
				<input type="text" style="width: 80px" id="sex"  name="sex" readonly=true
					class="form-control"  value="${sex}">
				<label style="font-size:15px">年龄:</label> 
				<input type="text" style="width: 80px" id="age"  name="age" readonly=true
					class="form-control"  value="${age}">	
				<label style="font-size:15px">身份证号:</label> 
				<input type="text" style="width: 180px" id="idCard"  name="idCard" readonly=true
					class="form-control"  value="${idCard}">
			</div>
			<input id="resId" name="resId" type="hidden" value="${resId}" />
		</form>
	</div>
	<div region="center" border="false">
		<div id="resident" class="easyui-tabs"  fit="true">
			<div title="服务记录信息">
				<form method="post" id="recordForm" class="data-form" action="">
					<input id="pageNo" name="pageNo"     type="hidden"   value="${page.pageNo}" /> 
					<input id="pageSize" name="pageSize" type="hidden"   value="${page.pageSize}" />
					<table class="table" cellspacing="0" cellpadding="0"   id="checkTable">
						<tr>
							<th>服务项目</th>
							<th>服务次数</th>
							<th>服务内容</th>
						</tr>
						<tbody id="tbody-record">
							 <c:forEach items="${page.list}" var="p">
								<tr>
									<td>${p.serviceName}</td>
									<td>${p.count}</td>
									<td>${p.serviceDetails}</td>
								</tr>
							</c:forEach> 
						</tbody>
					</table>
					<!-- 分页信息 -->
					<div id="div-page">
						<table:page page="${page}"></table:page> 
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	

</script>


</html>