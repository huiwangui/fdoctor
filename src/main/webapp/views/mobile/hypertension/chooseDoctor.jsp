<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查询医生</title>
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/layer.css">
	</head>
	<body>
			<div class="div_searchPerson" style="padding:10px">
			<form action=""  class="clearfix">
					<div class="form-group1 mt10" >
						姓名或拼音：
						<input type="text" name="KeyValue" id="KeyValue" value=""  class="borderb"/>
						<span class="seat"></span>
						<a id="search" class="sibtn" href="javascript:void(0)">查询 </a>
						<a id="close" class="sibtn" href="javascript:void(0)">关闭 </a>
						<a id="select" class="sibtn" href="javascript:void(0)">选择 </a>
					</div>
				</form>
				<div class="tableSf mt10">
				<table id="examTable" border="1" cellspacing="" cellpadding="">
				<thead >
					<tr class="bgf1">					 
						<td>姓名</td>
						<td>所属机构</td>
					</tr>
				</thead>
				<tbody  >
				 
				</tbody>
			</table>	 
				</div>
			</div>
	</body>
	<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="/fdoctor/statics/js/layer.js" ></script>
    <script type="text/javascript">
	
    	$(function(){
    		var oTable=null; 

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

    	     var id = $(".checkchild:checked").val();
    	     //选择
    	     $('#select').on('click', function () {
    			 
    				var params1=oTable.row('.selected').data();
    				parent.$("#DoctorName").text(params1.docName);
    				parent.$("#DoctorID").val(params1.id);
    			    //setTimeOut(function(){
    			    var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
    				//	},500);
    		 }); 
    	     
    	    $('#close').on('click',function(){
    	    	parent.layer.close(index);
    	    });
    	     
    		$("#search").click(function() {
    			//datatables
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
    			    	 url: "/fdoctor/mobile/hypertension/chooseDoctor",   
    		             data:{ 
    		            	  KeyValue: $("#KeyValue").val()
    		             }
    		        }, 
    			 
    				 "columns": [
								  
		                         
   				                    { "data": "docName" },
    				                { "data": "orgName" }
    				                
    			                ],
    			    "filter": false,
    			    "destroy": true,
    			});
    			
    	 });
    	 
    	});   	

    </script>
</html>
