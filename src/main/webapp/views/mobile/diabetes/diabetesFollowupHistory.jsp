<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<title>个人糖尿病随访历史</title>

<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->

<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
</head>
<style type="text/css">
	body{
		background: #f0f3f8;
	}
	
</style>
<body>
	<div class="main">
		<div class="clear"></div>
		<div class="operation">
			<a  id="edit"  class="btg" href="javascript:void(0)">编辑</a>
			<a class="btg" href="javascript:void(0)">删除</a>
			<a id="newf" class="btg" href="javascript:void(0)">添加随访</a>
			<a id="curve" class="btg" href="javascript:void(0)">空腹血糖曲线</a>
		</div>
		
		<div class="table1">
			<table id="examTable" border="1" cellspacing="" cellpadding="">
				<thead >
					<tr class="bgf1">
						<!-- <td>序号</td> -->
						<td>姓名 </td>
						<td>随访时间 </td>
						<td>收缩压 </td>
						<td>舒张压 </td>
						<td>空腹血糖 </td>
						<td>食量(g) </td>
						<td>低血糖</td>
						<td>心理调整</td>
						<td>遵医行为 </td>
						<td>服药 </td>
						<td>随访结局 </td>
						<td>是否完善 </td>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">


$(function(){
	 var oTable=null;
	 oTable=$('#examTable').DataTable({
		  "sLoadingRecords":"载入中...",
		  stateSave: true,
		  "autoWidth": true,
		  "stateSave": true,
		  "ordering":false,
		  "bPaginate" : true,// 分页按钮  
	      "pagingType":   "full_numbers",//full_numbers
	      "bFilter" : false,// 搜索栏  
	      "iDisplayLength" : 10,
	      "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表  
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
	    "processing":     "数据读取中...",		     
	    "serverSide": true,//打开后台分页 
		
	  
	    ajax: {  
	    	 url: "/fdoctor/mobile/diabetes/personalHistoryNext",   
            data:{ 	        		              
          	  PersonID:"${PersonID}",
          	  doctorName:"${doctorName}"
            }
      }, 
	 
		 "columns": [
		              
		                { "data": "PersonName" }, 
		                { "data": "FollowUpDateStr" },
		                { "data": "Sbp" },
		                { "data": "Dbp" },
		                { "data": "FastingBloodGlucose" },
		                { "data": "Staple" },
		                { "data": "LowBloodSugarReactionsStr" },
		                { "data": "PsychologicalAdjustmentStr" },
		                { "data": "ComplianceBehaviorStr"},
		                { "data": "MedicationComplianceStr"},
		                { "data": "FollowUpLiteRemarks"},
		                { "data": "Perfect"}
	                ],
	    "filter": false,
	    "destroy": true,
	});
	 
	//获取选中的某一行值

	 $('#examTable tbody').on( 'click', 'tr', function () {
	  if ( $(this).hasClass('selected') ) {
	      $(this).removeClass('selected');
	  }else {
	  	oTable.$('tr.selected').removeClass('selected');
	      $(this).addClass('selected');
	   }
	  });
	//获取选中的某一个checkbox的值 
	 if ($(".checkchild:checked").length > 1) {   
	 	 alert("一次只能修改一条数据");               
	 	 return;           
	 	 }

	 var id = $(".checkchild:checked").val();
	 
	//糖尿病曲线
	 $('#curve').on('click', function () {
		 var id="${PersonID}";
		$('#curve').attr("href", "/fdoctor/mobile/diabetes/diabetesCurve?personid="+id+"&doctorName="+"${doctorName}");
		
			
    });
	//随访编辑
	 $('#edit').on('click', function () {
		 
			var params=oTable.row('.selected').data();
			rdate=JSON.stringify(params);
			if(rdate==''||rdate==null){
				alert("请选择随访记录")
			}else{
				ID = params.ID;
				PersonID = params.PersonID; 
			 	$('#edit').attr("href", "/fdoctor/mobile/diabetes/updateFollowup?ID="+ID+"&PersonID="+PersonID+"&doctorName="+"${doctorName}"+"&userName="+"${userName}"+"&userId="+"${userId}");
			}
			
	});
	//新建随访
	 $('#newf').on('click', function () {
		 
			var params=oTable.row('.selected').data();
			rdate=JSON.stringify(params);
			if(rdate==''||rdate==null){
				alert("请选择随访记录")
			}else{
				ID = params.ID;
				PersonID = params.PersonID; 
			 	$('#newf').attr("href", "/fdoctor/mobile/diabetes/saveFollowup?ID="+ID+"&PersonID="+PersonID+"&doctorName="+"${doctorName}"+"&userName="+"${userName}"+"&userId="+"${userId}");
			}
			
	});
}) 


</script>
</html>
