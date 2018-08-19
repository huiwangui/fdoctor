<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签约服务包管理</title>

<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<script src="${ctx}/statics/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/page.js"></script>
<link href="${ctxStatic}/css/table.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/menu.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/body.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
<link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/css/common.css" rel="stylesheet"
	type="text/css" />
<script src="${ctxStatic}/js/page.js"></script>
<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>
<script src="${ctxStatic}/js/ldialog.js"></script>
<style type="text/css">
.tabs-title {
	font-size: 15px
}
</style>

<script type="text/javascript">
	$(function() {
		$("input[type=checkbox]").first().click(function() {
			$("input[type=checkbox]").prop("checked", this.checked);
		});
	});
	function addSigSvsPack() {
		document.getElementById("searchForm").action = "${ctx}/sigserverpack/openAddSigSvsPack";
		document.getElementById("searchForm").submit();
	}

	function viewSvsPackDts(sigSvsPackId) {
		document.getElementById("searchForm").action = "${ctx}/sigserverpack/getSvsPackDetailsList?sigSvsPackId="
				+ sigSvsPackId;
		document.getElementById("searchForm").submit();
	}
	function openEditSigSvsPack(id) {
		document.getElementById("searchForm").action = "${ctx}/sigserverpack/openEditSigSvsPack?id="
				+ id;
		document.getElementById("searchForm").submit();
	}

	/**逻辑删除签约服务包及相关中间详情*/
	function deleteSigSvsPackOrDtl() {
		var boxArray = document.getElementsByName('sigSvsPackIds');
		var total = 0;
		for (var i = 0; i < boxArray.length; i++) {
			if (boxArray[i].checked) {
				total++;
			}
		}
		if (total > 0) {
			if (window.confirm('共选中' + total + '个服务包记录，是否要删除？')) {
				var frm = document.forms[0];
				frm.action = "${ctx}/sigserverpack/deletSigSvsPackOrDtl";
				frm.submit();
				
				return true;
			} else {
				return false;
			}
		} else {
			window.alert('没有选择任何服务包记录！');
			return false;
		}
	}
</script>
</head>
<body class="easyui-layout" style="padding: 2px">
	<div region="center" split="false" style="height: 200px;">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>签约管理/</span><span>签约服务包管理</span>
			</div>
		</div>
		<div id="resident" class="easyui-tabs" fit="true">
			<div title="签约服务包管理">
				<form method="post" id="searchForm" class="data-form" action="${ctx}/sigserverpack/getSvsPackList">
					<input id="pageNo" name="pageNo" type="hidden"
						value="${page.pageNo}" /> <input id="pageSize" name="pageSize"
						type="hidden" value="${page.pageSize}" />
					<table class="table" cellspacing="0" cellpadding="0"
						id="checkTable">
						<tr>
							<th><input type="checkbox" name="" value="on"><!-- 全选 --></th>
							<th>名称</th>
							<th>价格(元)</th>
							<th>居民自付金额(元)</th>
							<th>机构分成比例(%)</th>
							<th>建议人群</th>
							<th>目标</th>
							<th>操作</th>
						</tr>
						<tbody id="tbody-person-record">
							<c:forEach items="${page.list}" var="p" varStatus="status">
								<tr>
									<td><input type="checkbox" name="sigSvsPackIds"
										value="${p.id}">&nbsp;<%-- ${status.count} --%></td>
									<td>${p.packName}<!-- <b></b> --></td>
									<td>${p.packPrice}</td>
									<td>${p.userPay }</td>
									<td>${p.orgratio }</td>
									<td>${p.adviceGroup}</td>
									<td>${p.target}</td>

									<td><input type="button" class="btn btn-blue" 
									value="服务包明细" onclick="viewSvsPackDts('${p.id}');">
										
										 <input  type="button" class="btn btn-blue" value="编辑"
										onclick="openEditSigSvsPack('${p.id}')"></td>


								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 分页信息 -->
					<div id="div-page">
						<table:page page="${page}"></table:page>
					</div>
					<br>
					
						<input type="button" value="添加" class="btn btn-blue"
							onclick="addSigSvsPack();"> <input type="button"
							value="删除" class="btn btn-blue"
							onclick="deleteSigSvsPackOrDtl();">
					
				</form>

			</div>

		</div>
	</div>
</body>
</html>