<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
	<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
	<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->

	<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->
	<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">

	<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<div class="main">
		<div class="clear"></div>
		<div class="operation">
			<a href="javascript:void(0)" class="btg">编辑</a>
			<a href="javascript:void(0)" class="btg">删除</a>
			<a href="javascript:void(0)" class="btg">添加体检</a>
		</div>
		
		<div class="table1">
			<table border="1" cellspacing="" cellpadding="">
				<thead >
					<tr class="bgf1">

						<td>成员姓名 </td>
						<td>性别 </td>
						<td>年龄 </td>
						<td>身份证 </td>
						<td>联系电话 </td>
						<td>健康指导 </td>
						<td>最后体检时间 </td>
						<td>是否完善 </td>
					</tr>
				</thead>
				<tbody>
					<tr>

						<td class="bluecolor">data</td>
						<td>data</td>
						<td>data</td>
						<td>data</td>
						<td>data</td>
						<td>data</td>
						<td class="bluecolor">data</td>
						<td>data</td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>

<script type="text/javascript">

	var oTable=$('#examTable').DataTable({
		"sLoadingRecords":"载入中...",
		"autoWidth":false,
		//"stateSave": true,
		"ordering":false,
		"bPaginate" : true,// 分页按钮
		"pagingType":   "full_numbers",//full_numbers  full simple
		"bFilter" : false,// 搜索栏
		"iDisplayLength" : 10,
		"bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
		"serverSide": true,//打开后台分页
		"oLanguage": {  //对表格国际化
			"sLengthMenu": "每页显示 _MENU_条",
			"sZeroRecords": "没有找到符合条件的数据",
			"sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
			"sInfoEmpty" : "共 _MAX_条",
			"sInfoFiltered": "",
			'zeroRecords': '没有数据',
			'emptyTable': '没有数据',
			"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "上页",
				"sNext": "下页",
				"sLast": "尾页"
			},

		},

		"columns": [

			{ "data": "Name" },
			{ "data": "GenderCode" },
			{ "data": "Age" },
			{ "data": "CardID" },
			{ "data": "Telphone" },
			{ "data": "GUIDANCE" },
			{ "data": "Lasthldate" },
			{ "data": "Sophistication" }
		],
		"filter": false,
		"destroy": true,

	});




</script>


</body>
</html>
