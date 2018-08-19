<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加居民信息</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/menu.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/body.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
<link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/css/common.css" rel="stylesheet"
	type="text/css" />

<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>
<script src="${ctxStatic}/js/ldialog.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/uploadPreview.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jQuery.Form.js"></script>
<style type="text/css">
.tabs-title {
	font-size: 15px
}

td {
	border: 1px #D1D1D1 solid
}

.form-group {
	margin-bottom: 10px
}

.l-btn-left {
	background-color: #53E0D7;
	width: 100px
}
</style>

<script type="text/javascript">

			$(function(){
					//选择checkbox 下面每个checkbox进行默认选择
					 $("input[type=checkbox]").first().click(function() {
			            $(".ucheck").prop("checked", this.checked);
			        });
			        $("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120 });
			})
			
			
			function sunmitSig() {
			
			
              
			
			
			
				var sigData = [];
			
				//首先判断 是否选中了 没有签约的用户信息  ucheck
				var chkSize = $("input:checkbox[name=idcard]:checked").size();
				if(chkSize == 0){
					$.messager.alert('提示','未选择需要签约的居民信息','warning');
				}
				var  data = $("#checkSing:checked").val();
				if(!data){
					$.messager.alert('提示','请选择签约即视为同意家庭医生服务协议','warning');
				}
				
				var  url = "${ctx}/queryResident/saveResingImag.do";
				$("#uploadImg").ajaxForm({
						url:url,
						 dataType:"JSON",
						success: function (returndata) {
	                        if(returndata.code==200){
	                       	 //对签约的居民判断是否已经选择了对应的服务包信息
								var  checkData = $("input:checkbox[name=idcard]:checked");
								$(checkData).each(function(index,data){
									var id = $(this).val();
									var  checked = $("#"+id).val();
									
									var  docCheck =  $("#doc"+id).val();
									
									if(checked == -1  ||  docCheck== -1){
										$.messager.alert('提示','选择居民有未选择签约包或者医生信息','warning');
									}else{
									
									
										sigData.push({
											'docUid': docCheck,
											'idCard':id,
											'signImgHead':docCheck,
											 'signImgHead': returndata.msg,
											'status':'1',
											'isNoticed':'0',
											'sigId':checked
										})
									}
								})
	                        
	                        //所有数据满足可以进行提交
							var param = JSON.stringify(sigData);
				   			//调用保存签约合同信息
				   			url = "${ctx}/queryResident/saveResingService.do";
							$.ajax({
							   url: url,
							   type:"post",
							   data: param,
							   dataType:"JSON",
							   contentType : 'application/json;charset=utf-8',
							   success: function(data){
							   		if(data.code==200){
							   			//成功以后 刷新父页面
							   			url = "${ctx}/queryResident/residentInfo";
							   			window.location= url;
							   		}else{
							   			$.messager.alert('提示','保存签约服务信息失败','warning');
							   		}
							   }
						 	});
						 	
				   		}else{
				   			$.messager.alert('提示',data.msg,'warning');
				   		}
	                    }
				}); 
				
			/* 	$.ajax({
				   url: url,
				   type:"post",
				   data: $("#uploadImg").serialize(),
				   dataType:"JSON",
				   contentType:'multipart/form-data',
				   success: function(data){
				   		
				   }
			 	}); */
			}
			
			var  addIndex = 0;
			function addImag() {
				addIndex++;
				var  html = "<div><img id='ImgPr"+addIndex+"'/></div><input  type='file'  name='files'  id='up"+addIndex+"'>";
				$("#addimg").append(html);
				$("#up"+addIndex).uploadPreview({ Img: "ImgPr"+addIndex, Width: 120, Height: 120 });
				
			}
		
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>居民签约信息添加</title>
</head>
<body style="min-height: 300px" class="easyui-layout" border="false"
	fit="true">


	<div region="north" split="false" style="height:50px;">
		<div class="top-bar" style="padding:1px">
			<div class="locate-index">
				位置:<span>签约服务合同管理/</span><span>签约</span>
			</div>
		</div>
	</div>

	<div region="center" border="false">
		<div class="form-group">
			<label style="font-size:15px">户主姓名:</label> <input type="text"
				tyle="width: 50px" class="form-control" disabled="disabled"
				value="${fresidengVo.personName}">
		</div>
		<div class="form-group">
			<label style="font-size:15px">身份证:</label> <input type="text"
				style="width: 200px" class="form-control"
				value="${fresidengVo.idCard}" disabled="disabled">
		</div>
		<div class="form-group">
			<label style="font-size:15px">家庭住址:</label> <input type="text"
				style="width: 200px" class="form-control"
				value="${fresidengVo.householdaddress}" disabled="disabled">
		</div>
		<!-- <form method="post" id="personPaymentForm" class="data-form"
			action=""> -->
		<div style="height: 300px;overflow: auto" class="data-form">
			<table class="table" cellspacing="0" cellpadding="0"
				id="nocheckTable" style="">
				<tr>
					<th><input type="checkbox">
					</th>
					<th>姓名</th>
					<th>性别</th>
					<th>身份证号码</th>
					<th>与户主关系</th>
					<th>服务包选择</th>
					<th>选择医生</th>
				</tr>
				<tbody id="tbody-person-record">
					<c:forEach items="${residenceMember}" var="p">
						<tr>
							<td><c:if test="${p.hasSigned==false ||  p.status==2}">
									<input type="checkbox" name="idcard" class="ucheck"
										value="${p.idCard }">
								</c:if></td>
							<td>${p.personName }</td>
							<td><c:if test="${p.sex==0}">  
									       男
								</c:if> <c:if test="${p.sex==1}">  
									       女
								</c:if></td>
							<td>${p.idCard }</td>
							
							<td>
								<!-- '0本人 1父亲 2 母亲 3 祖父 4 祖母 5 夫妻 6 其它', -->
								<c:if test="${p.parentHouseRelation==0}">
								本人
								</c:if>
								<c:if test="${p.parentHouseRelation==1}">
								父亲
								</c:if><c:if test="${p.parentHouseRelation==2}">
								母亲
								</c:if><c:if test="${p.parentHouseRelation==3}">
								祖父
								</c:if><c:if test="${p.parentHouseRelation==4}">
								祖母
								</c:if><c:if test="${p.parentHouseRelation==5}">
								夫妻
								</c:if><c:if test="${p.parentHouseRelation==6}">
								其它
								</c:if>
							
							
							</td>
							<td>
								<c:if test="${p.hasSigned==false ||  p.status==2}">
									<select id="${p.idCard}">
										<option value="-1">-请选择-</option>
										<c:forEach items="${allServicepack}" var="rs">
											<option value="${rs.id}"
												<c:if test="${rs.id==p.packId}">selected</c:if>>${rs.packName}</option>
										</c:forEach>
									</select>
								</c:if> 
								
								<c:if test="${p.hasSigned==true  &&  p.status!=2}">
									<c:forEach items="${allServicepack}" var="rs">
										<c:if test="${rs.id==p.packId}">
											${rs.packName}
										</c:if>
									</c:forEach>
								</c:if>
							</td>
							
							<td>
								<c:if test="${p.hasSigned==false||  p.status==2}">
									<select id="doc${p.idCard}">
										<option value="-1">-请选择-</option>
										<c:forEach items="${allFamdoctorInfo}" var="fa">
											<option value="${fa.uid}">${fa.name}</option>
										</c:forEach>
									</select>
								</c:if> 
								<%-- <c:if test="${p.hasSigned==true}">
									<c:forEach items="${allFamdoctorInfo}" var="fa">
										<c:if test="${rs.uid==p.packId}">
											${rs.packName}
										</c:if>
									</c:forEach>
								</c:if> --%>
							</td>
							
							
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div>
			<span>上传证件和合同</span>
		</div>

		<!-- 通过SpringMVC 上传图片 -->
		<form action="${ctx}/queryResident/saveResingImag.do" method="post"
			id="uploadImg">
			<div id="addimg">
				<div>
					<img id="ImgPr" />
				</div>
				<input type="file" name="files" id="up">
			</div>


			<div>
				<a href="#" class="easyui-linkbutton" onclick="addImag()">增加图片</a> <span><input
					type="radio" id="checkSing" checked="checked" value="1" />签约即视为同意家庭医生服务协议</span>
			</div>

			<input type="submit"  class="btn btn-blue" value="确认签约" onclick="sunmitSig()" />
			<!-- <a href="#" class="easyui-linkbutton"
						onclick="sunmitSig()">确认签约</a>  -->
		</form>
	</div>



</body>


</html>