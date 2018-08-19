<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/iframe.css">
<link href="${ctxStatic}/css/table.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/page.js"></script>
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<script type="text/javascript">
function openAddSvsPack(){
    document.getElementById("searchSvsPackForm").action ="http://localhost:8081/fc/cspm/openAddSvsPack";
    document.getElementById("searchSvsPackForm").submit();
}
function openEditSvsPack(){
	 document.getElementById("searchSvsPackForm").action ="http://localhost:8081/fc/cspm/openEditSvsPack";
	 document.getElementById("searchSvsPackForm").submit();
}
</script>



<title>签名服务包查询</title>
</head>
<body>
	<div class="wrap">
		<div class="bordure">
			<ol class="breadcrumb">
				<li>位置:<a href="">签约管理</a>><a href="">签约服务包管理</a></li>
			</ol>
		</div>
		<div class="bordure">
			<form id="searchSvsPackForm" action="${ctx}/sigSvsPk/querysigSvsPkInfo">
				<div class="wrap operate">
					<!-- 区域内元素 -->
					<a href="">新建</a> <a class="input" href="">导入</a> <a class="output"
						href="#" onclick="outputExcel();">导出</a>
				</div>
				<div>
					<table id="table" class="table" style="word-break: break-all"
						border=1>
						<thead>
							<tr>
								<th><input type="checkbox" name="" value="on">全选</th>
								<th>档案编号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>年龄</th>
								<th>身份证号码</th>
								<th>病种</th>
								<th>电话</th>
								<th>建档日期</th>
								<th width="100">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="sigSvsPk">
								<tr>
									<td><input type="checkbox" name="" value="${sigSvsPk.id}"></td>
									<td><a
										href="${ctx}/sigSvsPkInfoDetail/getBasicsigSvsPkInfo?healthFileCode=${sigSvsPk.id}">${sigSvsPk.id}</a></td>
									<td>${sigSvsPk.packName}</td>
									<td>${sigSvsPk.packName}</td>
									<td>${sigSvsPk.id}</td>
									<td>${sigSvsPk.id}</td>
									<td><span class="ic-kid"></span>
									<td>${sigSvsPk.id}</td>
									<td>e</td>
									<td><input type="button" id="svsDtlsBtn" value="服务包明细"
										onclick=""> <input type="button" id="editBtn"
										value="编辑" onclick="openEditSvsPack();"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					<div id="pageDiv">
						<table:page page="${page.list}"></table:page>
					</div>
					<div class="wrap">
						<input type="button" id="addBtn" value="添加" onclick="openAddSvsPack()">
						<input type="button" id="deleteBtn" value="删除" onclick="deleteSvsPacks()">
					</div>
			</form>	</div>
		</div>



</body>
</html>