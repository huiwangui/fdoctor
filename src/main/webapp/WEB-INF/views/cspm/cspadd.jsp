<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>签约包添加</title>
<script type="text/javascript">
function addSvsPack(){
	 document.getElementById("addSvsPackFrm").action ="http://localhost:8081/fc/cspm/addSvsPack";
	 document.getElementById("addSvsPackFrm").submit();
}
</script>
</head>
<body>
	<div class="bordure">
		<ol class="breadcrumb">
			<li>位置:<a href="">签约管理</a>><a href="">签约服务包管理</a>><a href="">签约服务包添加</a></li>
		</ol>
	</div>
	<form id="addSvsPackFrm" method="post">
		<div>
			<label>服务包名称：</label><input type="text" name="packName" id="packName" value="${param.packName}">
		</div>
		<div>
			<label>居民自付金额：</label><input type="text" name="userPay" id="userPay" value="${param.userPay}">
		</div>
		<div>
			<label>价格:</label><input type="text" name="packPrice" id="packPrice" value="${param.packPrice}">
		</div>
		  <div>
            <label>建议人群：</label><input type="text" name="adviceGroup" id="adviceGroup" value="${param.adviceGroup}">
        </div>
            <div>
            <label>目标：</label><input type="text" name="target" id="target" value="${param.target}">
        </div>
		<div>
			<label>机构分成比例：</label><input type="text" name="orgratio" id="orgratio" value="${param.orgratio}">
		</div>
		<div>
		<label>备注：</label> <input type="text" name="remarks" id="remarks" value="${param.remarks}"> 
		</div>
		<table border=1>
			<tr>
				<th>服务项目列表</th>
				<th>名称</th>
				<th>内容</th>
				<th>原价格</th>
				<th>频次</th>
				<th>服务时间</th>
				<th>执行机构</th>
				<th>操作</th>
			</tr>
			<tr>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td>2</td>
				<td><input type="button" value="删除" onclick=""></td>
			</tr>
		</table>
		<input type="button" value="确定" onclick="addSvsPack();"> <input type="button"
			value="取消">
	</form>

</body>
</html>