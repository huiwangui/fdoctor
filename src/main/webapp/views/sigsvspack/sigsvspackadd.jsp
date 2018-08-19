<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签约服务包添加</title>
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
<script type="text/javascript">
	 function goBack() {
		document.getElementById("searchForm").action = "${ctx}/sigserverpack/getSvsPackList";
		document.getElementById("searchForm").submit();
	}  
	$(function(){
	
        $("input[type=checkbox]").first().click(
            function(){
                $("input[type=checkbox]").prop("checked",this.checked);
                $("#packPrice").val("");
                if(this.checked){
                	 $("input[type=checkbox]").each(function(index,data){
                		 if(index > 0){
                			 var value = $(data).val();
                			 var now = $("#test"+value).val();
                             var packPrice = $("#packPrice").val();
                             if(!packPrice){
                                    packPrice = 0;
                              }
                              $("#packPrice").val(parseFloat(packPrice)+parseFloat(now))
                		 }
                		 
                	 })
                }
            }
        );
    });
	
	/**新增签约服务包中间关系*/
     function addSvsPack() {
    	var dateprices=document.getElementById("packPrice").value;
    	var userpays=document.getElementById("userPay").value;
    	alert(userpays+"=="+dateprices);
		if(parseFloat(userpays)>parseFloat(dateprices)){
			alert(userpays+"用户支付金额需小于所需价格！"+dateprices);
			return false;
		}
        var boxArray = document.getElementsByName('svsPackDtlIds');
        var total = 0;
        for (var i = 0; i < boxArray.length; i++) {
            if (boxArray[i].checked) {
                total++;
            }
        }
        if (total > 0) {
            if (window.confirm('共选中' + total + '个服务项目，是否要导出？')) {
                var frm = document.forms[0];
                frm.action = "${ctx}/sigserverpack/addSvsPack";
                frm.submit();
                return true;
            } else {
                return false;
            }
        } else {
            window.alert('没有选择任何服务项目！');
            return false;
        }
    } 
	function caculPrice(){
		var price=document.getElemntsByName("culoriginalPrice");
		for(var i=0;i<price.length;i++){
		alert(price[i].value);
		}
	}
	
	function test(value) {
		var packPrice = $("#packPrice").val();
		var now = $("#test"+value).val();
		if(!packPrice){
			packPrice = 0;
		}
		if($("#checkId"+value).is(':checked')) {
			$("#packPrice").val(parseFloat(packPrice)+parseFloat(now))
			
		}else{
			$("#packPrice").val(parseFloat(packPrice)-parseFloat(now))
		}	
	
	}
</script>

</head>
<body style="max-height: 600px; min-height: 500px" class="easyui-layout"
	border="false">

	<div region="north" split="false" style="height: 50px;">
		<div class="top-bar" style="padding: 1px">
			<div class="locate-index">
				位置:<span>签约管理/</span><span>签约服务包管理/</span><span>签约服务包添加</span>
			</div>
		</div>
	</div>

	<div region="center" border="false">
		<form method="post" class="form-inline" id="searchForm" action="" style="padding: 1px">
			<table style="background-color: #F7F7F7; width: 100%">
				<tr>
					<td colspan="3"><label
						style="background-color: #53E0D7; width: 100%">签约服务包基本信息</label></td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label style="font-size: 15px; width: 90px">服务包名称：</label> <input
								type="text" id='packName' name="packName" class="form-control"
								style="width: 100px" value="${param.packName}">
						</div>
					</td>
					<td>
						<div class="form-group">
							<label style="font-size: 15px; width: 130px">居民自付金额(元):</label> <input
								type="text" id="userPay" name="userPay" style="width: 50px"
								id="idCard" class="form-control" value="${param.userPay}">
						</div>
					</td>
					<td>
						<div class="form-group">
							<label style="font-size: 15px; width: 90px">价格(元):</label> <input
								type="text" style="width: 100px" id="packPrice" name="packPrice"  readonly="readonly"
								class="form-control" value="${param.packPrice}">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label style="font-size: 15px; width: 70px">建议人群:</label> <input
								type="text" id="adviceGroup" name="adviceGroup"
								class="form-control" style="width: 100px"
								value="${param.adviceGroup}">
						</div>
					</td>
					<td>
						<div class="form-group">
							<label style="font-size: 15px; width: 90px">目标:</label> <input
								type="text" id="target" name="target" class="form-control"
								style="width: 180px" value="${param.target}">
						</div>
					</td>
					<td>
						<div class="form-group">
							<label style="font-size: 15px; width: 120px">机构分成比例:</label> <input
								type="text" id="orgratio" name="orgratio" class="form-control"
								style="width: 100px" value="${param.orgratio}">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="form-group">
							<label style="font-size: 15px; width: 70px">备注：</label> <input
								type="text" id=“remarks” name="remarks" class="form-control"
								style="width: 180px" value="${param.remarks}">
						</div>
					</td>

				</tr>
			</table>
			<table class="table" cellspacing="0" cellpadding="0" id="checkTable">
				<tr>
					<td colspan="8"><label
						style="background-color: #53E0D7; width: 100%">签约服务包详情</label></td>
				</tr>
				<tr>
					<th><input type="checkbox">&nbsp;</th>
					<th>服务项目列表</th>
					<th>名称</th>
					<th>内容</th>
					<th>原价格</th>
					<th>频次</th>
					<th>服务时间</th>
					<th>执行机构</th>
				</tr>
				<tbody id="tbody-person-record">
					<c:forEach items="${dtsPage.list}" var="dtsp" varStatus="status">
						<tr>
							<td>
							 <input type="checkbox" name="svsPackDtlIds"  id="checkId${dtsp.id}"
								value="${dtsp.id}"  onclick="test(this.value)">&nbsp;${status.count}
							</td>
							<td>${dtsp.serviceItem}</td>
							<td>${dtsp.serviceName }</td>
							<td>${dtsp.serviceDetails }</td>
							<td>
							<input type="hidden"  name="culoriginalPrice" id="test${dtsp.id}" value="${dtsp.originalPrice }">${dtsp.originalPrice }
							
							</td>
							<td><c:if test="${dtsp.frequency==-1}">不限次数</c:if>
                                    <c:if test="${dtsp.frequency!=-1}">${dtsp.frequency}次</c:if></td>
							<td>${dtsp.serviceTime }</td>
							<td>${dtsp.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="wrap" style="text-align: center">
            <button type="button" class="btn btn-blue" onclick="addSvsPack();">确定</button>
          <button type="button" class="btn btn-blue" onclick="caculPrice();">相加</button>
            <button type="button" class="btn btn-blue" onclick="goBack();">返回</button>
        </div>
		</form>
	</div>



</body>


</html>