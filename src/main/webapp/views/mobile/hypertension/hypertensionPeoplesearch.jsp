<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>高血压建档获取居民信息</title>
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->
		
	</head>
	<body>
			<div class="div_searchPerson" style="padding:10px">
			<form action=""  class="clearfix">
				<div class="form-group1">
					 区划： <input name="regionCode" id="regionCode"   data-options="url:'/fdoctor/getorg',method:'get'"/>
					</div>
					<div class="form-group1 mt10" >
						查询方式： 
						<select name="KeyCode" id="KeyCode">
							<option value="1">姓名或拼音</option>
							<option value="2">身份证</option>
							<option value="3">档案号</option>
							 
					    </select>
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
						<!-- <td>选择</td> -->
						<td>姓名</td>
						<td>性别</td>
						<td>年龄</td>
						<td>个人编码</td>
						<td>身份证</td>
						<td>住址</td>
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
	/* function selectCurPerson(params){
		alert(params)	
		 
	}; */
    	$(function(){
    		var oTable=null;
    		var index=parent.layer.getFrameIndex(window.name);
    		$('#regionCode').combotree({                     
    	        onBeforeExpand:function(node) {   
    	                $('#regionCode').combotree("tree").tree("options").url = "/fdoctor/getorg?regionCode=" + node.id;  
    	        }  
    	    });   

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
    			 
    				var params=oTable.row('.selected').data();
    				// params =  JSON.stringify(params); 
    				 
    				   
    				// $('#select').attr("href", "/fdoctor/mobile/hypertension/echo?NAME="+params.NAME+"&GENDER="+params.GENDER+"&CARD_ID="+params.CARD_ID+"&ID="+params.ID+"&TELPHONE="+params.TELPHONE+"&AGE="+params.AGE+"&PersonCode="+params.PERSON_CODE);
    				 parent.refresh(params);	
    				 setTimeOut(function(){
    						 parent.layer.close(index);
    					},500);
    	     }); 
    	     
    	    function UrlSearch() {
    	 	   var name,value; 
    	 	   var str=location.href; //取得整个地址栏 	 	  
    	 	   var num=str.indexOf("?") 
    	 	   str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]
    	 	   var arr=str.split("&"); //各个参数放到数组里
    	 	   for(var i=0;i < arr.length;i++){ 
    	 	    num=arr[i].indexOf("="); 
    	 	    if(num>0){ 
    	 	     name=arr[i].substring(0,num);
    	 	    
    	 	     value=arr[i].substr(num+1);
    	 	     
    	 	     this[name]=value;
    	 	     } 
    	 	    } 
    	 	} 
    	 	var param=new UrlSearch(); //实例化
    	      		
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
    			    	 url: "/fdoctor/mobile/hypertension/addIndex",   
    		             data:{ 
    		            	  KeyCode: $("#KeyCode").val(),
    			              KeyValue: $("#KeyValue").val(),   				          
    					      RegionCode:getRegionCode(),
    					      ProductCode:decodeURI(param.ProductCode)
    		             }
    		        }, 
    			 
    				 "columns": [
								  /* {
							       data:   "ID",
							       render: function ( data, type, row ) {
						 
							           return '<a href="#" onclick=selectCurPerson('+oTable.row('.selected').data()+')>选择</a>';
							       },
							       className: "dt-body-center",
		        
		                          },    */ 
		                         
   				                    { "data": "NAME" },
    				                { "data": "GENDER" },
    				                { "data": "AGE" },
    				                { "data": "PERSON_CODE" },
    				                { "data": "CARD_ID" },
    				                { "data": "ADDRESS" }
    				                
    			                ],
    			    "filter": false,
    			    "destroy": true,
    			});
    			
    	 });
    	 
    	});   	


    	  
    
 	
    	 
    			 


    	
    	function getRegionCode(){
			try{
				var t = $("#regionCode").combotree('tree'); // 得到树对象  
				var n = t.tree('getSelected');
				if(n != null){
					return n.id;
				}else{
					return '51';
				}
			}catch(e){
				return '';
			}
		}
    </script>
</html>
