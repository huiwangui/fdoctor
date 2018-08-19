<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增服务记录</title>
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
<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>
<script src="${ctxStatic}/js/ldialog.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/My97DatePicker/WdatePicker.js"></script>
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
<body style="max-height: 500px;min-height: 300px" class="easyui-layout"  border="false">
	
	<div region="north" split="false" style="height:50px;">
		<div class="top-bar" style="padding:1px">
			<div class="locate-index">
				位置:<span>签约服务记录/</span><span>新增服务记录</span>
			</div>
		</div>
	</div>
	<div region="center" border="false">
		<form method="post" class="form-inline" id="recordForm" action="" style="padding:1px">
			<table style="background-color: #F7F7F7;width: 100%">
				<tr>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 70px">服务包:</label> 
							<input type="text" id='packName' class="form-control" style="width:140px" value="${packName}" readonly=true>
							<input name='sigId' value="${sigId}" type="hidden">
						</div>
					</td>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 70px">服务项目:</label> 
							<select name="detailsId" class="form-control" id="detailsId">
								<c:forEach items="${option}" var="item" varStatus="status">
									<option value="${item.detailsId}">${item.serviceName}</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
                    		<label label style="font-size:15px;width: 70px">服务时间:</label>
                    		<input type="text" name="serviceData" id="serviceData" style="width:140px" class="form-control" id="birthday"
							onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                		</div>
					</td>
					<td colspan="2">
						<div class="form-group">
							<label style="font-size:15px;width: 70px">服务内容:</label> 
							<input type="text" id="serviceDetails" name="serviceDetails" class="form-control" style="width:240px">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 70px">服务医生:</label> 
							<select name="docId" class="form-control" id="docId" onchange="changeName()">
								<option value="0">-请选择-</option>
								<c:forEach items="${doctor}" var="item" varStatus="status">
									<option value="${item.docId}">${item.docName}</option>
								</c:forEach>
							</select>
							<input name="docName" id="docName" type="hidden">
						</div>
					</td>
					<td colspan="2">
						<div class="form-group">
							<label style="font-size:15px;width: 70px">服务地址:</label> 
							<input type="text" id="serviceAddress" name="serviceAddress" class="form-control" style="width:240px">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 70px">是否及时:</label> 
							<select name="istimely" class="form-control" id="istimely">
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</div>
					</td>
					<td colspan="2">
						<div class="form-group">
							<label style="font-size:15px;width: 70px">描述:</label> 
							<input type="text" id="remarks" name="remarks" class="form-control" style="width:240px">
						</div>
					</td>
				</tr>
			</table>
			<input name='resId' value="${resId}" type="hidden">
		</form>
		<div class="wrap" style="text-align:center">
			<button type="button" class="btn btn-blue" onclick="addRecord()">保存</button>
			<button type="button" class="btn btn-blue" onclick="dialogClose()">取消</button>
		</div>
	</div>



</body>
<script type="text/javascript">

	//选择医生后填充隐藏的医生姓名一栏
	function changeName(){
		var docname = $("#docId").find("option:selected").text();
		$("#docName").val(docname);
	}
	
	//添加服务记录信息
	function addRecord(){
		var docid = $("#docId").val();
		var serviceData = $("#serviceData").val();
		var serviceDetails = $("#serviceDetails").val();
		var serviceAddress = $("#serviceAddress").val();
		if(serviceData==""||serviceData==null){
			alert("请选择服务时间!");
			return;
		}
		if(serviceDetails==""||serviceDetails==null){
			alert("请输入服务内容!");
			return;
		}
		if(docid==0){
			alert("请选择服务医生!");
			return;
		}
		if(serviceAddress==""||serviceAddress==null){
			alert("请输入服务地址!");
			return;
		}
		var paramData = $("#recordForm").serialize();
		$.ajax({
		   url: "${ctx}/signRecord/addSigReocrd",
		   type:"post",
		   data: paramData,
		   dataType:"text",
		   success: function(data){
			   var obj = eval('('+data+')');
			   if(obj.success){
				   alert(obj.result);
				   dialogClose();
			   }else{
				   alert("新增失败，失败原因："+obj.result);
				   doclear();
			   }
		   },
		   error:function(data){
			   alert("出现异常，异常原因【" + data + "】!");    
         	}  
		 });
	}
	
	//清空输入栏
	function doclear(){
		$("#serviceData").val("");
		$("#serviceDetails").val("");
		$("#serviceAddress").val("");
		$("#remarks").val("");
	}
	
	
	

</script>


</html>