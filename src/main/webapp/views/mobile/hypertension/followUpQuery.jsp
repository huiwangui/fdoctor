<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"> 
<title>高血压随访查询</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->


<body>
 
	<div class="main">
		
		<!--********************表单开始**************************-->
		<form action="" >
			<div class="search-form">
				<div class="form-group1">
					区划： <input name="regionCode" id="regionCode"   data-options="url:'/fdoctor/getorg',method:'get'"/>  
							<span class="seat"></span>
					随访日期： <input type="date" name="StartFollowupDate" id="StartFollowupDate"   /> <input type="date" name="EndFollowupDate" id="EndFollowupDate" />
						<span class="seat"></span>
					是否随访： 
					<select name="IsFollowup">
						<option value="1">是</option>
						<option value="2">否</option>
					 
					</select>
				</div>
				<div class="form-group1 mt10" >
					
					<select name="KeyValueType" id="KeyValueType">
						<option value="1">姓名</option>
						<option value="2">身份证号</option>
						<option value="3">健康档案号</option>
						 
					</select>
					<input type="text" name="KeyValue" id="KeyValue"    class="name"/>
					<span class="seat"></span>
					随访方式： <select name="FollowupType" id="FollowupType">
						<option value="1">门诊</option>
						<option value="2">家庭</option>
						<option value="4">电话</option>
					</select>
					<span class="seat"></span>
					是否完善： <select name="Perfect" id="Perfect">
						<option value="0">是</option>
						<option value="1">否</option>
					</select>
				</div>
			</div>
			<div class="search-btn">
				<a   id="search" href="javascript:void(0)">查询居民</a>
			</div>
		</form>
		<!--********************表单结束**************************-->
		
		<div class="clear"></div>
		<div class="operation">
			<a id="edit" href="javascript:void(0)"  class="btg">编辑</a>
			<a  href="javascript:void(0)"  class="btg">删除</a>
			<a id="add" href="javascript:void(0)"  class="btg">添加随访</a>
			<a  id="person" href="javascript:void(0)"  class="btg">个人高血压随访历史</a>
		</div>
		
		<div class="table1">
			<table id="examTable" border="1" cellspacing="" cellpadding="">
				<thead >
					<tr class="bgf1">
						<!-- <td>序号 </td> -->
						<td>姓名 </td>
						<td>随访时间 </td>
						<td>状态 </td>
						<td>收缩压 </td>
						<td>舒张压 </td>
						<td>摄盐 </td>
						<td>心理调整 </td>
						<td>遵医行为 </td>
						<td>服药</td>
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
 
      //高血压随访编辑
	 $('#edit').on('click', function () {		 
			var params=oTable.row('.selected').data();  
			 $('#edit').attr("href", "/fdoctor/mobile/hypertension/editFollowup?ID="+params.ID+"&PersonName="+params.PersonName+"&doctorName="+'${doctorName}');		
     }); 
	 //高血压增加随访
	 $('#add').on('click', function () {	 
			var params=oTable.row('.selected').data(); 
			$('#add').attr("href", "/fdoctor/mobile/hypertension/addFollowupShow?PersonID="+params.PersonID+"&doctorName="+'${doctorName}');
		
			
     }); 
	 

     
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
		    	 url: "/fdoctor/mobile/hypertension/queryHypertensionFollowUp",   
	             data:{ 	        		              
	            	  KeyValueType: $("#KeyValueType").val(),
	            	  KeyValue: $("#KeyValue").val(),
	            	  StartFollowupDate:$("#StartFollowupDate").val(),
	            	  EndFollowupDate: $("#EndFollowupDate").val(),
	            	  IsFollowup: $("#IsFollowup").val(),
	            	  Perfect: $("#Perfect").val(),
	            	  FollowupType: $("#FollowupType").val(),	            	  
				      RegionCode:getRegionCode(),
				      doctorName :'${doctorName}'
	             }
	        }, 
		 
			 "columns": [
			              /* {
					       data:   "PersonName",
					       render: function ( data, type, row ) {
					    	      
					               return '<a onclick="aclick()">'+data+'</a>';
					          
					       },
					       className: "dt-body-center",
					        
					      },  */
			             
			             
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
		
 })
 
//查询个人高血压记录列表 
 
 $("#person").click(function() {
	     var params=oTable.row('.selected').data();		 
	     $('#person').attr("href", "/fdoctor/mobile/hypertension/queryPersonFollowUpHistory?PersonID="+params.PersonID+"&doctorName="+'${doctorName}');   //params.ID
		
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

//获取身份证号
function getIdCard(idCard){
	return  idCard;
}

</script>
</html>
