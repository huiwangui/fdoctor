<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加居民信息</title>
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/menu.css">
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/body.css">
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/easyui.css">
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/icon.css">
<link href="${ctxStatic}/css/style.css"  rel="stylesheet"  type="text/css" />
<link href="${ctxStatic}/css/common.css" rel="stylesheet" type="text/css" />

<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>
<script src="${ctxStatic}/js/ldialog.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/easyui-lang-zh_CN.js"></script>
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

	$(function() {
		$(".showOrhide").css("display","none");
	});


	/**
		验证居民身份证号码信息
	*/
	function validationCard(idCard){
		if(idCard  &&  idCard.length == 18){
			var  url = "${ctx}/resident/getResidenceMember";
			var param  = {
				idCard:idCard,
				date:new Date().getTime()
			}
			$.get(url,param,function(data){
				if(data.data.length  > 0){
					$.messager.alert('提示','该身份证信息已经存在,请验证后输入','warning');
					$("#idCard").val("");
				}
			},"JSON")
		}else{
				$.messager.alert('提示','该身份证信息未输入或者位数不正确','warning');
				$("#idCard").val("");
		}
	}
	
	
	function selectPResideng(idCard) {
		if(idCard  &&  idCard.length == 18){
			var  url = "${ctx}/resident/getResidenceMaster?data="+ new Date().getTime();
			var param  = {
				idCard:idCard
			}
			$.get(url,param,function(data){
				
				if(!data.data ){
					$.messager.alert('提示','该身份证信息不是户主身份信息,请检查','warning');
				}else{
					$("#pIdCard").val(data.data.idCard);
					$("#pname").val(data.data.personName);
					$("#pPhoneNum").val(data.data.phoneNumber);
					$("#faddress").val(data.data.householdaddress);
					//开始设置新增人员管理户主的信心
					$("#masterIdCard").val(data.data.idCard);
				}
			},"JSON")
		}else{
				$.messager.alert('提示','该身份证信息未输入或者位数不正确','warning');
				$("#pIdCard").val("");
		}
	}
	
	//验证手机号码
	function checkPhone(){ 
	    var phone = $("#phoneNumber").val();
	    if(!(/^1[34578]\d{9}$/.test(phone))){ 
	        $.messager.alert("提示","手机号码有误，请重填","warning");
	        $("#phoneNumber").val("");
	    } 
	}
	
	/*提交表单前进行验证**/
	function validationFrom() {
		//获取省身份证
		var idCard = $("#idCard").val();
		//获取姓名
		var personName = $("#personName").val();
		//获取生日
		var dateOfBirth = $("#dateOfBirth").val();
		//获取联系电话
		var phoneNumber = $("#phoneNumber").val();
		
		if(!personName || !idCard  ||  !dateOfBirth || !phoneNumber){
			$.messager.alert('提示','必填信息未填写完整,请检查','warning');
			return  false;
		}
		
		var masterIdCard = $("#masterIdCard").val();
		//证明添加用户是户主本人
		if(!masterIdCard){
			$("#masterIdCard").val(idCard);
		}
		
		return  true;
	}
	
	//判断是否选中户主栏
	function showtrData(value){
		$("#masterIdCard").val("");
		$("#pIdCard").val("");
		$("#pname").val("");
		$("#pPhoneNum").val("");
		$("#faddress").val("");
		if(value  != 0){
			$(".showOrhide").css("display","");
		}else{
			$(".showOrhide").css("display","none");
		}
	}
	
	
	//提交保存居民信息
	function submitFrom() {
		var  flag = validationFrom();
		
		if(flag){
			var paramData = $("#addResigin").serialize();
			var url  = "${ctx}/queryResident/addRsident";
			$.ajax({
			   url: url,
			   type:"post",
			   data: paramData,
			   dataType:"JSON",
			   success: function(data){
			   		if(data.code==200){
			   			//成功以后 刷新父页面
			   			closeWin();
			   			parent.searchResident(true);
			   		}else{
			   			//添加错误，提示错误信息
			   			$.messager.alert('提示',data.msg,'warning');
			   		}
			   }
			 });
		}

	}
	
	function closeWin(){
		parent.$("#s_addresident").window("close");
	}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加居民信息</title>
</head>
<body style="min-height: 300px" class="easyui-layout"  border="false"  fit="true">
	
	<div region="north" split="false" style="height:50px;">
		<div class="top-bar" style="padding:1px">
			<div class="locate-index">
				位置:<span>添加居民信息/</span><span>新增</span>
			</div>
		</div>
	</div>

	<div region="center" border="false">
		<form method="post" class="form-inline" id="addResigin" action="${ctx}/queryResident/addRsident" style="padding:1px"  onsubmit=" return validationFrom()">
			<!-- 设置户主标识 -->
			<input  type="hidden"    name="masterIdCard"  id="masterIdCard"/>
			<table style="background-color: #F7F7F7;width: 100%">
				<tr>
					<td colspan="3"><label
						style="background-color: #53E0D7;width: 100%">个人基本信息</label></td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 60px"><font color="red" size="5">*</font>姓名:</label> <input
								type="text" id='personName'  name = "personName" class="form-control" style="width:100px">
						</div></td>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 70px">性别:</label> 
							<!-- 0本人 1父亲 2 母亲 3 祖父 4 祖母 5 夫妻 6 其它 -->
							<select name="sex"  id="sex" class="form-control"  >
								<!-- <option value="-1">-请选择-</option>
								<option value="0">-本人-</option>
								<option value="1">-父亲-</option>
								<option value="2">-母亲-</option>
								<option value="3">-祖父-</option>
								<option value="4">-祖母-</option>
								<option value="5">-夫妻-</option>
								<option value="6">-其它-</option> -->
								<option value="0">-男-</option>
								<option value="1">-女-</option>
								
							</select>
							
							
						</div></td>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 90px"><font color="red" size="5">*</font>出生日期:</label> 
							<input id="dateOfBirth" name="dateOfBirth" class="form-control" 
							 type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '%y-%M-%d'})" />
						</div></td>
				</tr>


				<tr>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 40px">证件:</label> 
							<select name="type"  id="type" class="form-control"   style="width: 100px">
								<!-- <option value="-1">-请选择-</option> -->
								<option value="0">-身份证-</option>
								<!-- <option value="1">-父亲-</option>
								<option value="2">-母亲-</option>
								<option value="3">-祖父-</option>
								<option value="4">-祖母-</option>
								<option value="5">-夫妻-</option>
								<option value="6">-其它-</option> -->
							</select>
						</div></td>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 100px"><font color="red" size="5">*</font>身份证号码:</label> 
							<input  type="text" id='idCard'  name="idCard" class="form-control" style="width:180px"  onblur="validationCard(this.value)" >
						</div></td>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 90px"><font color="red" size="5">*</font>联系电话:</label> <input
								type="text"  name="phoneNumber"  id="phoneNumber"  class="form-control" style="width:100px"  onblur="checkPhone(this.value)">
						</div></td>
				</tr>


				<tr>
					<td colspan="2">
						<div class="form-group">
							<label style="font-size:15px;width: 70px">单位名称:</label> <input
								type="text" name="workunits"  id="workunits"  class="form-control" style="width:180px">
						</div></td>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 90px">户籍所在地:</label> <input
								type="text" name="householdaddress"  id="householdaddress"  class="form-control" style="width:180px">
						</div></td>
					<td></td>
				</tr>

				<tr>
					<td>
						<div class="form-group">
							<label style="font-size:15px;width: 100px">常住地址户籍:</label> 
							<select name="permanent"  id="permanent" class="form-control"   style="width: 60px">
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</div>
					</td>
					<td colspan="2">
						<div class="form-group">
							<label style="font-size:15px;width: 90px">现住址:</label> <input
								type="text" name="nowaddress"  id="nowaddress"  class="form-control" style="width:240px">
						</div></td>
				</tr>
				
				<tr>
					<td colspan="3">
						<div class="form-group">
							<label style="font-size:15px;width: 120px">本人与户主关系</label> 
								<select name="parentHouseRelation"  id="parentHouseRelation"  name="parentHouseRelation" class="form-control"  
								 style="width: 100px"  onchange="showtrData(this.value)">
									<option value="0">-本人-</option>
									<option value="1">-父亲-</option>
									<option value="2">-母亲-</option>
									<option value="3">-祖父-</option>
									<option value="4">-祖母-</option>
									<option value="5">-夫妻-</option>
									<option value="6">-其它-</option>
								</select>
							
						</div>
					</td>
				</tr>

				
					<!-- 以下户主信息，不能手动填写，只能通过身份证号码从数据库查询   时间原因暂时直接输入证件号进行匹配-->
					<tr  class="showOrhide">
						<td colspan="3"><label
							style="background-color: #53E0D7;width: 100%">家庭基本信息</label>
						</td>
					</tr>


					<tr  class="showOrhide">
						<td>
							<div class="form-group">
								<label style="font-size:15px;width: 70px">户主姓名:</label> <input
									type="text" id='pname'  id='pname'  class="form-control" style="width:100px"  readonly="readonly">
							</div>
						</td>
						<td>
							<div class="form-group">
								<label style="font-size:15px;width: 100px">户主身份证号:</label> <input
									type="text" id='pIdCard'  name="pIdCard" class="form-control" style="width:160px"  onblur="selectPResideng(this.value)">
							</div></td>
						<td>
							<div class="form-group">
								<label style="font-size:15px;width: 90px">联系电话:</label> <input
									type="text" id='pPhoneNum'  name ='pPhoneNum' class="form-control" style="width:100px"  readonly="readonly">
							</div></td>
					</tr>
	
					<tr  class="showOrhide">
						<td colspan="3">
							<div class="form-group">
								<label style="font-size:15px;width: 90px">家庭地址:</label> <input
									type="text" id='faddress' class="form-control" style="width:180px"  readonly="readonly">
							</div></td>
					</tr>
					
				
				
				
			</table>
			<div class="wrap" style="text-align:center">
						<button type="button" class="btn btn-blue"  onclick="submitFrom()">确定</button>
						<button type="button" class="btn btn-blue" onclick="closeWin()">取消</button>
			</div>
		</form>
		
	</div>



</body>


</html>