<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>签约服务记录</title>
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

</style>
</head>
<body class="easyui-layout" style="padding: 2px">
	<div region="north" split="false" style="height:95px;">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>签约服务记录</span>
			</div>
		</div>
		<form method="post" class="form-inline" id="searchForm" action="${ctx}/signRecord/querySignInfo"
			style="margin-bottom: 2px">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" /> 
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
			<div class="form-group">
				<label style="font-size:15px">姓名:</label> 
				<input type="text" id='personName'  name="personName"   placeholder="请输入姓名" 
					class="form-control"  value="${option.personName}">
			</div>
			<div class="form-group">
				<label style="font-size:15px">身份证号:</label> 
				<input type="text" style="width: 200px" id="idCard"  name="idCard" placeholder="请输入身份证号码"
					class="form-control"  value="${option.idCard}">
			</div>
			<input type="hidden" style="width: 250px" id="docId"  name="docId" placeholder="请输入身份证号码"
					class="form-control"  value="1">
			<div class="form-group">
				<a href="#" class="easyui-linkbutton"
					onclick="querySignInfo()">查询</a> 
			</div>
		</form>
	</div>
	<div region="center" border="false">
		<div id="resident" class="easyui-tabs"  fit="true">
			<div title="签约用户信息">
				<form method="post" id="personForm" class="data-form" action="">
					<input id="pageNo" name="pageNo"     type="hidden"   value="${page.pageNo}" /> 
					<input id="pageSize" name="pageSize" type="hidden"   value="${page.pageSize}" />
					<table class="table" cellspacing="0" cellpadding="0"   id="checkTable">
						<tr>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>身份证号</th>
							<th>签约时间</th>
							<th>签约服务包</th>
							<th>操作</th>
						</tr>
						<tbody id="tbody-person-record">
							 <c:forEach items="${page.list}" var="p">
									<tr>
										<td>${p.personName }</td>
										<td>
											<c:if test="${p.sex==0}">男</c:if> 
											<c:if test="${p.sex==1}">女</c:if> 									
										</td>
										<td>${p.age}</td>
										<td>${p.idCard }</td>
										<td>${p.signTime }</td>
										<td>${p.packName}</td>
										<td>
											<a href="javaScript:doView('${p.resId}','${p.personName}','${p.age}','${p.idCard}','${p.sex}')">查看服务记录</a>
											<a href="javaScript:doAdd('${p.sigId}','${p.packName}','${p.resId}')">新增服务记录</a>
										</td>
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
	
	//新增服务记录信息
	function doAdd(sigId,packName,resId){
		$(document.body).ldialog({
			idFled : 'addRecord',
			width : 700,
			height : 400,
			url :'${ctx}/signRecord/queryServiceDetail?sigId='+sigId+'&packName='+encodeURI(encodeURI(packName))+'&resId='+resId,
			title : "新增服务记录信息"
		});
	}
	
	
	//查询该医生下的签约信息
	function querySignInfo() {
		$("#searchForm").submit();
	}
	
	function doView(resId,personName,age,idCard,sex){
		$(document.body).ldialog({
			idFled : 'viewRecord',
			width : 1000,
			height : 500,
			url :'${ctx}/signRecord/queryRecordInfo?resId='+resId+'&personName='+encodeURI(encodeURI(personName))+'&age='+age+'&idCard='+idCard+'&sex='+sex,
			title : "查看服务记录信息"
		});
	}
	
	
</script>
</html>