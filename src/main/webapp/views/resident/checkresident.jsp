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
		//重写分些代码
		function page(n,s){//翻页
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("span.page-size").text(s);
				$("#personPaymentForm").submit();
				return false;
		}
		
		function removeSing(idCard) {
			$.post("${ctx}/queryResident/removeSing",{idCard:idCard},function(data){
				if(data.data.rowNum==1){
					parent.refresh(0);
				}else{
					$.messager.alert('提示','解除签约信息失败','warning');
				}
			},"JSON")
		}
		
		function removetoaddSing(idCard) {
			$.post("${ctx}/queryResident/removetoaddSing",{idCard:idCard},function(data){
				if(data.data.rowNum==1){
					parent.refresh(0);
				}else{
					$.messager.alert('提示','续约签约信息失败','warning');
				}
			},"JSON")
		}
		
		$(function(){
			var  divHeigth = $("#mmm").height();
			$("#chekdiv").height(divHeigth-80)
		})

</script>


</head>
<body class="easyui-layout" style="padding: 1px"  id="mmm">
	<div   style="overflow: auto"  id="chekdiv">
	<form method="post" id="personPaymentForm" class="data-form" action="${ctx}/queryResident/queryResidentInfo">
					<input id="pageNo" name="pageNo"     type="hidden"   value="${page.pageNo}" /> 
					<input id="pageSize" name="pageSize" type="hidden"   value="${page.pageSize}" />
					<table class="table" cellspacing="0" cellpadding="0"   id="checkTable">
						<tr>
							<th>姓名</th>
							<th>性别</th>
							<th>出生日期</th>
							<th>身份证号码</th>
							<th>关联户主</th>
							<th >签约时间</th>
							<th >到期时间</th>
							<th >服务包</th>
							<th >价格(元)</th>
							<th >服务项目</th>
							<th >操作</th>
						</tr>
						<tbody id="tbody-person-record">
							 <c:forEach items="${page.list}" var="p">
									<tr>
										<td>${p.personName }</td>
										<td>
											<c:if test="${p.sex==0}">  
												       男
											</c:if> 
											
											<c:if test="${p.sex==1}">  
												       女
											</c:if> 
											
										</td>
										<td>
											<fmt:formatDate value="${p.dateOfBirth}" type="date" pattern="yyyy-MM-dd"/>
										</td>
										<td>${p.idCard }</td>
										<td>${p.fathername }</td>
										<td>
											<fmt:formatDate value="${p.signTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/> 
										</td>
										
										<td>
											<fmt:formatDate value="${p.endDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/> 
										</td>
										
										<td>${p.packName}</td>
										<td>${p.packPrice}</td>
										<td><a  href="javaScript:void()"  onclick="parent.detailsViews('${p.sigId}')">详情</a></td>
										<td>     
											<a href="#"  class="easyui-linkbutton"  onclick="removeSing('${p.idCard}')">解约</a> 
											<c:if test="${p.isshowdel==true }">
												<a href="#" class="easyui-linkbutton" onclick="removetoaddSing('${p.idCard}')">续约</a>
											</c:if>
											
										 </td>
									</tr>
								</c:forEach> 
						</tbody>
					</table>
					
				</form>
				</div>
				
				 <div  style="height:80px;z-index: 900">
						<!-- 分页信息 -->
					<div id="div-page">
						<table:page page="${page}"></table:page> 
					</div>
				
				</div>
				
</body>
</html>