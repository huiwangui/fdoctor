<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>高血压管理首页</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->

<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
  <style type="text/css">
	body{
		background: #f0f3f8;
	}
</style>

</head>
<body>
	 <div class="main">
		 
		<form  id="fdoctor">
			<div class="search-form">
				<div class="form-group1">
					区划： <input name="regionCode" id="regionCode"   data-options="url:'/fdoctor/getorg',method:'get'"  />
						查询(姓名，拼音，编号，身份证,自定义编码)： 
						<input type="text" name="KeyValue" id="KeyValue"    class="border_bottom1"/>		 
						<span class="seat"></span>
					 

					<span class="seat"></span>
					随访： <select name="HasFollowup" id="HasFollowup">
						<option value="1">全部</option>
						<option value="2">无</option>
						<option value="3">有</option>
					</select>
				</div>
				<div class="form-group1 mt10" >
					联系电话： <input   name="PhoneTel" id="PhoneTel"  class="name"/>
					<span class="seat"></span>
					贫困人口： <select name="IsPoor" id="IsPoor">
						<option value="">请选择</option>
						<option value="1">否</option>
						<option value="2">是</option>
						
					</select>
					<span class="seat"></span>
					是否结案： <select name="IsClose" id="IsClose">
						<option value="0">否</option>
						<option value="1">是</option>
					
					</select>
				</div>
			</div>
			<div class="search-btn">
			 <a  class="" id="search" href="javascript:void(0)">查询居民</a>  
			</div>
		</form>
	 
		
		<div class="clear"></div>
		<div class="operation">
			<a id="edit"  href="javascript:void(0)"  class="btg">编辑</a>
			<!-- <a href="/"  href="javascript:void(0)"  class="btg">删除</a> -->
			<a id="curve"  href="javascript:void(0)"  class="btg">血压曲线</a>
			<a   href="javascript:void(0)"  class="btg">结案</a>	 
		    <a  id="add" href="javascript:void(0)" class="btg">高血压建档</a> 
			<a  id="health" href="javascript:void(0)" onclick="readHealthCard()" class="btg">读健康卡</a>
			<a id="change" href="javascript:void(0)" class="btg">切换到高血压随访</a>
		</div>
		
		<div class="table1">
			<table id="examTable" border="1" cellspacing="" cellpadding="">
				<thead >
					<tr class="bgf1">
						 
						<td>姓名 </td>
						<td>性别 </td>
						<td>年龄 </td>
						<td>确诊时间 </td>
						<td>责任医生 </td>
						<td>建档时间 </td>
						<td>联系电话 </td>
						<td>其他慢病 </td>
						<td>建档级别 </td>
						<td>最后一次随访时间</td>
					</tr>
				</thead>
				<tbody  >
				 
				</tbody>
			</table>
		</div>
	</div>  
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
                             url: '/fdoctor/mobile/hypertension/readindex',
                             data: {
	                             PersonID :androidDataJson.personId,
	                             userName :androidDataJson.userName
                             }
                         },
                        "columns": [
                                    { "data": "NAME" },
        			                { "data": "Gender" },
        			                { "data": "Age" },
        			                { "data": "DIAGNOSIS_DATE" },
        			                { "data": "DOCTOR_NAME" },
        			                { "data": "RecordDate" },
        			                { "data": "Telphone" },
        			                { "data": "OtherChronic" },
        			                { "data": 'strHyLevel'},
        			                { "data": 'LastHlDate'}
                        ],
                        "filter": false,
                        "destroy": true,
				 })
     }
 
     //高血压编辑
	 $('#edit').on('click', function () {		 
			var params=oTable.row('.selected').data();
			$('#edit').attr("href", "/fdoctor/mobile/hypertension/queryBaseInfomation?id="+params.ID+"&personid="+params.PERSON_ID+"&userName="+androidDataJson.userName);
		
			
     });
	 //高血压曲线
	 $('#curve').on('click', function () {
		 
		var params=oTable.row('.selected').data();
		$('#curve').attr("href", "/fdoctor/mobile/hypertension/curve?personid="+params.PERSON_ID+"&userName="+androidDataJson.userName);
		
			
    });
	 
	 //读健康卡
/* 	 $('#health').on('click', function () {
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
		    	 url: "/fdoctor/mobile/hypertension/read",   
	             data:{ 	        		              	              
				      RegionCode:getRegionCode()
				   //   KeyValue:"111111" ///默认情况
	             }
	        }, 
		 
			 "columns": [
			                { "data": "NAME" },
			                { "data": "Gender" },
			                { "data": "Age" },
			                { "data": "DIAGNOSIS_DATE" },
			                { "data": "DOCTOR_NAME" },
			                { "data": "RecordDate" },
			                { "data": "Telphone" },
			                { "data": "OtherChronic" },
			                { "data": 'strHyLevel'},
			                { "data": 'LastHlDate'}
		                ],
		    "filter": false,
		    "destroy": true,
		});
 
		 
     }); */

/* 	$('#regionCode').combotree({                     
	    onBeforeExpand:function(node) {   
	     $('#regionCode').combotree("tree").tree("options").url = "/fdoctor/getorg?regionCode=" + node.id;  
	    }  
	 });   */
	 
     
      // 获取选中的某一个checkbox的值

     if ($(".checkchild:checked").length > 1) {   
    	 alert("一次只能修改一条数据");               
    	 return;           
    	 }

     var id = $(".checkchild:checked").val();

	$('#regionCode').combotree({                     
	    onBeforeExpand:function(node) {   
	     $('#regionCode').combotree("tree").tree("options").url = "/fdoctor/getorg?regionCode=" + node.id;  
	    }  
	 }); 
	//增加高血压
	$("#add").click(function() {
		$('#add').attr("href",'/fdoctor/mobile/hypertension/add?userName='+androidDataJson.userName)
	})
	//切换高血压随访
	$("#change").click(function() {
		$('#change').attr("href","/fdoctor/mobile/hypertension/followUpQuery?userName="+androidDataJson.userName)
	})
	
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
		    	 url: "/fdoctor/mobile/hypertension/index",   
	             data:{ 	        		              
		              KeyValue: $("#KeyValue").val(),
			          HasFollowup: $("#HasFollowup").val(),
			          PhoneTel:$("#PhoneTel").val(),
			          IsPoor: $("#IsPoor").val(),
			          IsClose: $("#IsClose").val(),
				      RegionCode:getRegionCode(),
				      userName:androidDataJson.userName
	             }
	        }, 
		 
			 "columns": [
			                { "data": "NAME" },
			                { "data": "Gender" },
			                { "data": "Age" },
			                { "data": "DIAGNOSIS_DATE" },
			                { "data": "DOCTOR_NAME" },
			                { "data": "RecordDate" },
			                { "data": "Telphone" },
			                { "data": "OtherChronic" },
			                { "data": 'strHyLevel'},
			                { "data": 'LastHlDate'}
		                ],
		    "filter": false,
		    "destroy": true,
		});
		
 })
		 
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

//读健康卡
function readHealthCard(){
	//result('510101195201013938')
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
	         url: '/fdoctor/mobile/hypertension/read',
	         data: {
	        	 idCard :idCard,
	        	 userName :androidDataJson.userName
	         }
	     },
	    "columns": [
	                { "data": "NAME" },
	                { "data": "Gender" },
	                { "data": "Age" },
	                { "data": "DIAGNOSIS_DATE" },
	                { "data": "DOCTOR_NAME" },
	                { "data": "RecordDate" },
	                { "data": "Telphone" },
	                { "data": "OtherChronic" },
	                { "data": 'strHyLevel'},
	                { "data": 'LastHlDate'}
	        
	    ],
	    "filter": false,
	    "destroy": true,

	})
	
}


</script>
</body>
</html>
