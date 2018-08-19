<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>居民签约信息</title>

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
	function addResident() {
		$(document.body).ldialog({
			idFled : 'addresident',
			width : 900,
			height : 500,
			url :'${ctx}/queryResident/addresident.do',
			title : "新增居民信息"
		});
	}
	
	
	function searchResident() {
		$("#searchForm").submit();
	}
	
	
	
	
	$(function(){
	
	
	})
	
	
	
</script>
</head>
	<body  style="min-height: 300px" class="easyui-layout"  border="false"  fit="true">
		<form  class="data-form" >
			<table class="table" cellspacing="0" cellpadding="0"  >
						<tr>
							<th>服务项目</th>
							<th>服务名称</th>
							<th>服务内容</th>
							<th>服务时间</th>
							<th>服务机构</th>
						</tr>
						<tbody id="tbody-person-record">
							 <c:forEach items="${pageList}" var="p">
									<tr>
										<td>${p.serviceItem }</td>
										<td>${p.serviceName }</td>
										<td>${p.serviceDetails}</td>
										<td>${p.serviceTime}</td>
										<td>${p.serviceOrgName}</td>
									</tr>
								</c:forEach> 
						</tbody>
					</table>
				</form>
	</body>
</html>