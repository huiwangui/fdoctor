<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>个人高血压随访历史</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->
</head>
<body>
	<div class="main">
		<div class="clear"></div>
		<div class="operation">
			<a id="edit" href="javascript:void(0)"  class="btg">编辑</a>
			<a  href="javascript:void(0)"  class="btg">删除</a>
			<a id="add" href="javascript:void(0)"  class="btg">添加随访</a>
			<a id="personCurve" href="javascript:void(0)"  class="btg">血压曲线</a>
		</div>
		
		<div class="table1">
			<table border="1" cellspacing="" cellpadding="" id="examTable">
				<thead >
					<tr class="bgf1">
					 
						<td>姓名 </td>
						<td>随访时间 </td>
						<td>状态 </td>
						<td>收缩压 </td>
						<td>舒张压 </td>
						<td>摄盐 </td>
						<td>心理调整 </td>
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
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">

 
 $(function(){
	 var oTable=null;
	 $('select').prop('selectedIndex', 0);
    // 获取选中的某一行值
    
	 $('#examTable tbody').on( 'click', 'tr', function () {
      if ( $(this).hasClass('selected') ) {
          $(this).removeClass('selected');
      }else {
      	oTable.$('tr.selected').removeClass('selected');
          $(this).addClass('selected');
       }
      });
	 
    // 获取选中的某一个checkbox的值
     if ($(".checkchild:checked").length > 1) {   
    	 alert("一次只能修改一条数据");               
    	 return;           
    }
    
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
	    	 url: "/fdoctor/mobile/hypertension/queryFollowUpHistory",   
             data:{ 	        		              
            	 PersonID:"${PersonID}",
            	 ProductCode:"${ProductCode}"
             }
       }, 
	 
		 "columns": [	              
		                { "data": "PersonName" }, 
		                { "data": "FollowUpDateStr" },
		                { "data": "SymptomStr" },		                
		                { "data": "Sbp" },
		                { "data": "Dbp" },
		                { "data": "SaltIntakeStr" },
		                { "data": "PsychologicalAdjustmentStr" },
		                { "data": "ComplianceBehaviorStr" },
		                { "data": "MedicationComplianceStr" },
		                { "data": 'FollowUpLiteRemarks'},
		                { "data": 'Sophistication'}
	                ],
	    "filter": false,
	    "destroy": true,
	});
	 //高血压随访编辑
	 $('#edit').on('click', function () {		 
			var params=oTable.row('.selected').data();	   
			 $('#edit').attr("href", "/fdoctor/mobile/hypertension/editFollowup?ID="+params.ID+"&PersonName="+params.PersonName+"&doctorName="+"${doctorName}");		
     }); 
	 //高血压增加随访
	 $('#add').on('click', function () {
			 $('#add').attr("href", "/fdoctor/mobile/hypertension/addFollowupShow?PersonID="+"${PersonID}"+"&doctorName="+"${doctorName}");		
     }); 
	//个人高血压曲线
	 $('#personCurve').on('click', function () {	 
		var params=oTable.row('.selected').data();
		$('#personCurve').attr("href", "/fdoctor/mobile/hypertension/personCurve?personid="+params.PersonID+"&ProductCode="+"${ProductCode}");		
	  });
 }) 
</script>
</html>
