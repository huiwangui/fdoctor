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
<script type="text/javascript" src="${ctxStatic}/js/My97DatePicker/WdatePicker.js"></script>

<style type="text/css">
.tabs-title {
	font-size: 15px
}
</style>

<script type="text/javascript">
		function searchResident() {
				var beginTime = $("#beginTime").val();
				var endTime = $("#endTime").val();
				if(beginTime ){
					$("#sbeginTime").val(beginTime+" 00:00:00");
				}
				if(endTime){
					$("#sendTime").val(endTime + " 23:59:59");
				}
				$("#searchForm").submit();
		
		}
		
		
		$(function() {
				$(".merge").each(function(index, element){
					if(index == 0){
						current_td = $(this);
						k=1;  
					}else  if($(this).text()==current_td.text()){  
						k++;  
						$(this).remove();  
						current_td.attr("rowspan",k);  
						current_td.css("vertical-align","middle");  
					}
				
				});
				
				var  totalSum = 0;
				$(".totalmoney").each(function(index,data){
						var tempTotal = parseFloat($(data).html());
						totalSum = totalSum + tempTotal;
				})
				$("#hasData").html(totalSum);
				
				
		
		})
		
		
		function changeTime(){
			$("#endTime").attr("disabled",false);
		}
		
	
</script>
</head>
<body class="easyui-layout" >
	
	<!-- 头部界面 -->
	<div region="north" split="false" style="height:100px;">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>签约服务资金结算</span>
			</div>
		</div>
		<form method="post" class="form-inline"  style="margin-bottom: 2px">
			<div class="form-group">
				<label style="font-size:15px">补偿月份:</label> 
				<input type="text" id='beginTime'  class="form-control" value="${begindata}"
				
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '%y-%M-%d'})"  onchange="changeTime()">
				----
				<input type="text" id='endTime'    class="form-control" value="${enddata}"
				
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '%y-%M-%d',minDate:'#F{$dp.$D(\'beginTime\')}'})"   disabled="disabled">
			</div>
			<div class="form-group">
				<a href="#" class="easyui-linkbutton" onclick="searchResident()">查询</a>
			</div>
		</form>
	</div>

	<div region="center" title="资金详情">
		<form method="post" id="searchForm" class="data-form"
			action="${ctx}/moneymanger/capitalTotalMangerByLeader.do">
			
			 <input type="hidden"   name="beginTime"  id="sbeginTime">
			 <input type="hidden"   name="endTime"   id="sendTime">	
				
			<table class="table" cellspacing="0" cellpadding="0" id="checkTable">
				<tr>
					<th>结算月份</th>
					<th>签约包</th>
					<th>人数(个)</th>
					<th>自付(元)</th>
					<th>补偿(元)</th>
					<th>分成比例</th>
					<th>机构所得额度(元)</th>
					<!-- <th>提交申请</th> -->
				</tr>
				<tbody id="tbody-person-record">
					<c:forEach items="${pageList}" var="p">
						<tr>
							<td  class="merge">${p.countTime}</td>
							<td>
								${p.packName}
							</td>
							<td>${p.totalSig}</td>
							<td>${p.totalUserPay}</td>
							<td>${p.totalCompensate }</td>
							<td>${p.orgratio}%</td>
							<td  class="totalmoney">${p.totalOrgin}</td>
						</tr>
					</c:forEach>
					<tr>
							<td >应得总额(元)</td>
							<td colspan="6"  id="hasData"></td>
					</tr>
				</tbody>
			</table>

		</form>
	</div>
</body>
</html>