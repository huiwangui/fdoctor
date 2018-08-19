<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>糖尿病随访首页</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->

<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
<!--  <script type="text/javascript" src="/fdoctor/statics/js/examination/query.js"></script>-->

<script type="text/javascript">

</script>
<style type="text/css">
	body{
		background: #f0f3f8;
	}
	
</style>
</head>
<body>
	<div class="main">
		<!--********************表单开始**************************-->
		<form  id="searchForm" action="" >
			<div class="search-form">
				<div class="form-group1">
					区划： <input name="regionCode" id="regionCode" class="easyui-combotree form-control" data-options="url:'/fdoctor/getorg',method:'get'" style="width: 20%;">
							<span class="seat"></span>
					随访日期
					<input type="date" name="StartFollowupDate" id="StartFollowupDate" value=""  class=""/>-
					<input type="date" name="EndFollowupDate" id="EndFollowupDate" value=""  class=""/>
					<span class="seat"></span>
					是否随访： <select name="IsFollowup">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</div>
				<div class="form-group1 mt10" >
					
					<select name="KeyValueType">
						<option value="1">姓名或拼音</option>
						<option value="2">身份证</option>
						<option value="3">档案号</option>
					</select>
					<input type="text" name="KeyValue" id="KeyValue" value=""  class="name"/>
					<span class="seat"></span>
					随访方式： <select name="FollowupType">
						<option value="1">门诊</option>
						<option value="2">家庭</option>
						<option value="4">电话</option>
						<option value="-1">全部</option>
					</select>
					<span class="seat"></span>
					是否完善： <select name="Perfect">
						<option value="0">是</option>
						<option value="1">否</option>
					</select>
				</div>
			</div>
			<div class="search-btn">
				<a id="search" class="" href="javascript:void(0)">查询居民</a> 
			</div>
		</form>
		<!--********************表单结束**************************-->
		
		<div class="clear"></div>
		<div class="operation">
			<a  id="edit" href="javascript:void(0)" class="btg">编辑</a>
			<a id="pHistory" href="javascript:void(0)" class="btg">查询个人记录</a>
			<a href="javascript:void(0)" class="btg">删除</a>
			<a id="newf" href="javascript:void(0)" class="btg">添加随访</a>
		</div>
		<div class="table1">
			<table id="examTable" border="1" class="table table-striped table-bordered table-hover">
				<thead >
					<tr class="bgf1">
						<!--  <td>序号 </td>-->
						<td>姓名 </td>
						<td>随访时间 </td>
						<td>方式</td>
						<td>症状 </td>		
						<td>收缩压 </td>
						<td>舒张压 </td>
						<td>空腹血糖(mmol/L) </td>
						<td>食量(g) </td>
						<td>心理调整</td>
						<td>遵医行为 </td>
						<td>服药</td>
						<td>药物反应</td>
						<td>低血糖</td>
						<td>随访结局 </td>
						<td>随访分类 </td>
						<td>随访医生 </td>
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
	
	$('select').prop('selectedIndex', 0);

	
      // 获取选中的某一行值
      
	 $('#examTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
        	oTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
   
     
	 $('#pHistory').on('click', function () {
		 
			var params=oTable.row('.selected').data();
			radte=JSON.stringify(params);
			if(radte==''||radte==null){
				alert("请先选择居民")
	 		} else{ 
				PersonID = params.PersonID; 
			 	$('#pHistory').attr("href", "/fdoctor/mobile/diabetes/personalHistory?PersonID="+PersonID+"&doctorName="+'${doctorName}'+"&userName="+"${userName}"+"&userId="+"${userId}");
	 		}
			
		} );
    
	 $('#edit').on('click', function () {
		 
			var params=oTable.row('.selected').data();
			radte=JSON.stringify(params);
			if(radte==''||radte==null){
				alert("请先选择居民")
	 		} else{ 
				ID = params.ID;
				PersonID = params.PersonID; 
				 $('#edit').attr("href", "/fdoctor/mobile/diabetes/updateFollowup?ID="+ID+"&PersonID="+PersonID+"&doctorName="+"${doctorName}"+"&userName="+"${userName}"+"&userId="+"${userId}");
	 		}
			
		} );
	 $('#newf').on('click', function () {
		 
			var params=oTable.row('.selected').data();
			radte=JSON.stringify(params);
			if(radte==''||radte==null){
				alert("请先选择居民")
	 		} else{ 
				ID = params.ID;
				PersonID = params.PersonID; 
			 	$('#newf').attr("href", "/fdoctor/mobile/diabetes/saveFollowup?ID="+ID+"&PersonID="+PersonID+"&doctorName="+"${doctorName}"+"&userName="+"${userName}"+"&userId="+"${userId}");
	 		}
			
		} );
   
     //区划获取
	$('#regionCode').combotree({                     
	    onBeforeExpand:function(node) {   
	     $('#regionCode').combotree("tree").tree("options").url = "/fdoctor/getorg?regionCode=" + node.id;  
	    }  
	 }); 
	//	查询点击事件
	$("#search").click(function() {
		//datatables
		 oTable=$('#examTable').DataTable({
			  "sLoadingRecords":"载入中...",
			  stateSave: true,
			  "autoWidth":false,
			  "stateSave": true,
              "ordering":false,
			  "bPaginate" : true,// 分页按钮  
		      "pagingType":   "full_numbers",//full_numbers  full simple
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
		    // "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json",
		     "processing":     "读取中...",
		    // "processing": true, //打开数据加载时的等待效果  
		    //  "sProcessing": "&lt;img src=’./loading.gif’ /&gt;", 
		    "serverSide": true,//打开后台分页 
			
		  
		    ajax: {  
		    	 url: "/fdoctor/mobile/diabetes/followupList",   
	              data:{ 
	              KeyValueType: $('select[name=KeyValueType]').val(),
	   		      KeyValue: $('#KeyValue').val(),
	   		   	  StartFollowupDate: $('#StartFollowupDate').val(),
	   		      EndFollowupDate:$('#EndFollowupDate').val(),
	   		      IsFollowup: $('select[name=IsFollowup]').val(),
	   		      FollowupType: $('select[name=FollowupType]').val(),
	   		      Perfect: $('select[name=Perfect]').val(),
			      RegionCode:getRegionCode(),
			      doctorName:"${doctorName}"
	              }
	        }, 
		 
		    "columns": [
		   			{ "data": "PersonName" },
		            { "data": "FollowUpDateStr" },
		            { "data": "WayUpStr" },
		            { "data": "SymptomLiteStr" },
		            { "data": "Sbp" },
		            { "data": "Dbp" },
		            { "data": "FastingBloodGlucose" },
		            { "data": "Staple" },
		            { "data": "PsychologicalAdjustmentStr" },
		            { "data": "ComplianceBehaviorStr" },
		            { "data": "MedicationComplianceStr" },
		            { "data": "AdverseDrugReactionsStr" },
		            { "data": "LowBloodSugarReactionsStr" },
		            { "data": "FollowUpLiteRemarks" },
		            { "data": "FuClassificationStr" },
		            { "data": "DoctorName" },
		            { "data": "Perfect" }
		            ],
		    "filter": false,
		    "destroy": true,
		    
		});
 		
		
 })
 
});

//获取选中区划节点的id
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

function getDocInfo(docName){
	
  alert("传入成功"+docName);
	
	
}


</script>
</html>
    