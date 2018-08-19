<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>健康档案查询首页</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->
 <style type="text/css">
	body{
		background: #f0f3f8;
	}
</style>
</head>
<body>
	<div class="main">
		
		<!--********************表单开始**************************-->
		<form action="" >
			<div class="search-form">
				<div class="form-group1">
					区划： <input name="regionCode" id="regionCode"   data-options="url:'/fdoctor/getorg',method:'get'"/>
					居民状态：
					 <select name="IsStatus" id="IsStatus">
						<option value="0">活动</option>
						<option value="1">迁出 </option>
						<option value="2">死亡</option>
						<option value="99">已删除</option>
						<option value="3">其他</option>
					</select>
				</div>
				<div class="form-group1 mt10" >
					性别：
					<select name="Gender" id="Gender">
						<option value="">请选择 </option>
						<option value="">未知</option>
						<option value="1">男</option>
						<option value="2">女 </option>
					</select>
					<span class="seat"></span>
					 <select name="KeyCode" id="KeyCode">
						<option value="1">姓名或拼音</option>
						<option value="2">身份证</option>
						<option value="3">档案号</option>
						<option value="4">自定义编码</option>
						<option value="5">联系电话</option>
					</select>
					<input type="text" name="KeyValue" id="KeyValue"   class="border_bottom1"/>
					<span class="seat"></span>
				</div>
			</div>
			<div class="search-btn">
				<a   id="search" href="javascript:void(0)">查询居民</a>
			</div>
		</form>
		<!--********************表单结束**************************-->
		
		<div class="clear"></div>
		<div class="operation">
			<a id="editPeople" href="javascript:void(0)"  class="btg">编辑居民档案</a>
			<a id="editFamily" href="javascript:void(0)"  class="btg">编辑家庭档案</a>
			<a href="javascript:void(0)" onclick="readHealthCard()"  class="btg">读健康卡</a>
			<a  id="personInfo" href="javascript:void(0)" class="btg">居民档案浏览</a>
		</div>
		
		<div class="table1">
			<table border="1" id="examTable" cellspacing="" cellpadding="">
				<thead >
					<tr class="bgf1">
						<td>姓名 </td>
						<td>管理病种 </td>
						<td>性别 </td>
						<td>年龄 </td>
						<td>联系电话</td>
						<td>状态</td>
						<td>是否完善</td>
			 	        <td>最后体检时间  </td>   
						<td>最近体检是否完善</td>
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
<script type="text/javascript" src="/fdoctor/statics/layui/layui.js"></script>
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

	     var id = $(".checkchild:checked").val();
	     
	     
	 	//身份证回显安卓有传数据
	 	 var androidDataJson=${androidDataMap}
	      if(androidDataJson.personId=="123")
	      {
	     	 
	      }else{
	 		 //列表显示
	 		oTable=$('#examTable').DataTable({
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
	                          ajax:{
	                              url: '/fdoctor/mobile/healthRecord/readindex',
	                              data: {
	                                  PersonID :androidDataJson.personId,
	                                  userName :androidDataJson.userName
	                              }
	                          },
	                         "columns": [
	                                     { "data": "NAME" },
	       				              /*   { "data": "" },
	       				                { "data": "CardId" }, */
	       				                { "data": "TJ" },
	       				                { "data": "GENDER" },
	       				                { "data": "AGE" },
	       				                { "data": "TELPHONE" },
	       				                { "data": "HRSTATUS" },
	       				             /*    { "data": 'CUSTOM_NUMBER'}, */
	       				                { "data": "SOPHISTICATION"},
	       				                { "data": "LASTTIME"}, 
	       				                { "data": "HMPERFECT"}
	                         ],
	                         "filter": false,
	                         "destroy": true,
	 				 })
	      }
	     //编辑家庭档案
		 $('#editFamily').on('click', function () {			 
				var params=oTable.row('.selected').data();			 
				console.log(params.ID)				   
			    $('#editFamily').attr("href", "/fdoctor/mobile/healthRecord/queryFamilyInfomation?ID="+params.ID+"&FamilyId="+params.FAMILY_ID+"&userName="+androidDataJson.userName);
			
				
	     });
	     //点击居民档案浏览
	      $('#personInfo').on('click', function () {			 
				var params=oTable.row('.selected').data();	
				var param ={};
				param['personId']=params.ID;
			    $('#personInfo').attr("href", "/fdoctor/mobile/healthRecord/personRecordbrowse?androidData="+JSON.stringify(param)+"&userName="+androidDataJson.userName);
			
				
	     });
		 //编辑居民档案
		 $('#editPeople').on('click', function () {			 
				var params=oTable.row('.selected').data();			 			   
			    $('#editPeople').attr("href", "/fdoctor/mobile/healthRecord/queryPeopleInfomation?ID="+params.ID+"&FamilyId="+params.FAMILY_ID+"&userName="+androidDataJson.userName);
			
				
	     });
		
		$('#regionCode').combotree({                     
		    onBeforeExpand:function(node) {   
		     $('#regionCode').combotree("tree").tree("options").url = "/fdoctor/getorg?regionCode=" + node.id;  
		    }  
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
			    	 url: "/fdoctor/mobile/healthRecord/index",   
		             data:{ 	        		              
		            	  IsStatus:document.getElementById("IsStatus").value,
		            	  Gender:document.getElementById("Gender").value,
		            	  KeyCode: document.getElementById("KeyCode").value,
		            	  KeyValue: $("#KeyValue").val(),				          
					      RegionCode:getRegionCode(),
					      userName:androidDataJson.userName
		             }
		        }, 
			 
				 "columns": [
				                { "data": "NAME" },
				              /*   { "data": "" },
				                { "data": "CardId" }, */
				                { "data": "TJ" },
				                { "data": "GENDER" },
				                { "data": "AGE" },
				                { "data": "TELPHONE" },
				                { "data": "HRSTATUS" },
				             /*    { "data": 'CUSTOM_NUMBER'}, */
				                { "data": "SOPHISTICATION"},
				                { "data": "LASTTIME"}, 
				                { "data": "HMPERFECT"}
			                ],
			    "filter": false,
			    "destroy": true,
			});
		 })
	})

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

	
	
	//读健康卡
	function readHealthCard(){
		//result('510411198405253020') 
		window.control.getInfo();

	}
	//安卓端的方法，返回idCard
	function result(idCard){
		 
		oTable=$('#examTable').DataTable({
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
		     ajax:{
		         url: '/fdoctor/mobile/healthRecord/read',
		         data: {
		         	idCard :idCard,
		         	userName:androidDataJson.userName
		         	
		         }
		     },
		    "columns": [
		                    { "data": "NAME" },		              
			                { "data": "TJ" },
			                { "data": "GENDER" },
			                { "data": "AGE" },
			                { "data": "TELPHONE" },
			                { "data": "HRSTATUS" },		             
			                { "data": "SOPHISTICATION"},
			                { "data": "LASTTIME"}, 
			                { "data": "HMPERFECT"}
		        
		    ],
		    "filter": false,
		    "destroy": true,

		})
		
	}	
	
	
	
	
	
</script>
</html>
