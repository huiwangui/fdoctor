<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
 
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/common.css" />
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css" />
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script> 
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/easyui-lang-zh_CN.js"></script>	
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
					区划：<!-- <select name="">
							<option value="" id="tt"></option>
							</select>  -->
							<input  id="tt" name="RegionCode" data-options="url:'/fdoctor/mobile/hypertension/initRegionTree',method:'get'"/>
							<!-- <span class="seat"></span>  -->
					<select name="">
						<option value="">姓名或拼音</option>
						<option value="">身份证</option>
						<option value="">档案号</option>
						<option value="">自定义编号</option>
						<option value="">联系电话</option>
					</select>
					<input type="text" name="KeyValue" id="" value=""  class="name"/>
						<span class="seat"></span>
					 

					<span class="seat"></span>
					随访： <select name="HasFollowup">
						<option value="1">全部</option>
						<option value="2">无</option>
						<option value="3">有</option>
					</select>
				</div>
				<div class="form-group1 mt10" >
					联系电话： <input   name="PhoneTel" id="" value=""class="name"/>
					<span class="seat"></span>
					贫困人口： <select name="IsPoor">
						<option value="">请选择</option>
						<option value="2">是</option>
						<option value="">否</option>
					</select>
					<span class="seat"></span>
					是否结案： <select name="IsClose">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
			</div>
			<div class="search-btn">
				<button id="search" type="button">查询居民</button>
			<!-- 	<a href="/" class="" id="search">查询居民</a> -->
			</div>
		</form>
	 
		
		<div class="clear"></div>
		<div class="operation">
			<a href="javascript:void(0)"  class="btg">编辑</a>
			<a href="javascript:void(0)"  class="btg">删除</a>
			<a href="javascript:void(0)"  class="btg">血压曲线</a>
			<a href="javascript:void(0)"  class="btg">结案</a>
			<a href="javascript:void(0)"  class="btg">高血压建档</a>
			<a href="javascript:void(0)"  class="btg">健康读卡</a>
		</div>
		
		<div class="table1">
			<table border="1" cellspacing="" cellpadding="">
				<thead >
					<tr class="bgf1">
						<td>序号 </td>
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
				<tbody id="tbody">
				 
				</tbody>
			</table>
		</div>
	</div>  
	<script type="text/javascript">
		 
			$(function(){		
			 
				/*  $('#tt').combotree({
					//url:'z_tree.json',
						method:"get",
						url:'/fdoctor/mobile/hypertension/queryHypertension?regionCode='+'510411',
						width:300
						 
				}) */
				 $("#tt").combotree({
					onBeforeExpand : function(node){
						$('#tt').combotree("tree").tree("options").url = "/fdoctor/mobile/hypertension/initRegionTree?regionCode=" + node.id;
					}
				}); 
			
			});
			$("#search").click(function(){
				$.ajax({
				   type: "get",
				   url: "/fdoctor/mobile/hypertension/queryHypertension",
				   data: $('#fdoctor').serialize(),
				   dataType : 'json',
				   success: function(data){
					var msg=eval('('+data+')');
					var list=msg.Msg;
					for(var i=0;i<list.length;i++){
						var doctor =list[i].DOCTOR_NAME==null?"":list[i].DOCTOR_NAME;
						var telephone=list[i].Telphone==null?"":list[i].Telphone;
						var otherChronicOne =list[i].OtherChronicOne==null?"":list[i].OtherChronicOne;
						var otherChronicTwo =list[i].OtherChronicTwo==null?"":list[i].OtherChronicTwo;
						var otherChronic=list[i].OtherChronicOne==null?"":list[i].OtherChronicOne;
						 /*if(otherChronicOne!=null&&otherChronicTwo!=null){
							otherChronic =otherChronicOne+","+otherChronicTwo;
						}else if(otherChronicOne==null&&otherChronicTwo!=null){
							otherChronic = otherChronicTwo;
						}else if(otherChronicOne!=null&&otherChronicTwo==null){
							otherChronic =otherChronicOne;
						}else{
							otherChronic ="";
						} */
						var lastfollowUptime =list[i].LastHlDate==null?"":list[i].LastHlDate;
						var tr=	'<tr>'+
						    '<td><input type="checkbox" name="" id="" value="" /></td>'+
							'<td>'+list[i].NAME+'</td>'+
							'<td>'+list[i].Gender+'</td>'+
							'<td>'+list[i].Age+'</td>'+
							'<td>'+list[i].DIAGNOSIS_DATE+'</td>'+
						 
							'<td>'+doctor+'</td>'+ 
							'<td>'+list[i].RecordDate+'</td>'+
						 
							'<td>'+telephone+'</td>'+
					 
							'<td>'+otherChronic+'</td>'+
							'<td>'+list[i].strHyLevel+'</td>'+
						 
							'<td>'+lastfollowUptime+'</td>'+'</tr>';
						$("#tbody").append(tr);
						
					}
				     
				   }
				}); 
			})
	
	</script>
</body>
</html>
