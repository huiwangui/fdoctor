<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签约服务包编辑</title>

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
	/**新增相关中间详情*/
	function addSvsPackDtl() {
		
		var boxArray = document.getElementsByName('svsPackDtlIds');
		var total = 0;
		for (var i = 0; i < boxArray.length; i++) {
			if (boxArray[i].checked) {
				total++;
			}
		}
		if (total > 0) {
			if (window.confirm('共选中' + total + '个服务包记录，是否要新增？')) {
				var frm = document.forms["searchForm"];
				frm.action = "${ctx}/sigserverpack/addSvsPackDtl";
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

	<div region="north" split="false" style="height: 960px;">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>签约服务包/</span><span>签约服务包管理/</span><span>签约服务包变价</span>
			</div>
		</div>

		<div id="resident" class="easyui-tabs" fit="true">
			<div title="服务项目列表">
				<form method="post" id="searchForm" action="{ctx}/sigserverpack/getUnSvsPackDetailsList">
                    <input type="hidden" name="sgId" id="sgId" value="${sigId}">
					<input id="pageNo" name="pageNo" type="hidden"
						value="${sdePage.pageNo}" /> <input id="pageSize" name="pageSize"
						type="hidden" value="${sdePage.pageSize}" />
					<table class="table" cellspacing="0" cellpadding="0"
						id="checkTable">
						
						<tr>
							<th><input type="checkbox"></th>
							<th>服务项目列表</th>
							<th>名称</th>
							<th>内容</th>
							<th>原价格</th>
							<th>频次</th>
							<th>服务时间</th>
							<th>执行机构</th>

						</tr>
						<tbody id="tbody-person-record">
							<c:forEach items="${sdePage.list}" var="dtsp" varStatus="status">
								<tr>
									<td><input type="checkbox" name="svsPackDtlIds"
										value="${dtsp.id}">&nbsp;${status.count}</td>
									<td>${dtsp.serviceItem }</td>
									<td>${dtsp.serviceName }</td>
									<td>${dtsp.serviceDetails }</td>
									<td>${dtsp.originalPrice }</td>
									<td><c:if test="${dtsp.frequency==-1}">不限次数</c:if>
                                    <c:if test="${dtsp.frequency!=-1}">${dtsp.frequency}次</c:if></td>
									<td>${dtsp.serviceTime }</td>
									<td>${dtsp.name }</td>

								</tr>
							</c:forEach>
						</tbody>

					</table>
					
					
					<input type="button" value="新增详情服务" class="btn btn-blue" onclick="addSvsPackDtl()"> 
					<input type="button" value="返回" class="btn btn-blue" onclick="history.go(-1)">

				</form>
			</div>

		</div>



	</div>
</body>
</html>