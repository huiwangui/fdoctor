<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>居民签约信息</title>

<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<script src="${ctx}/statics/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/page.js"></script>
<link href="${ctxStatic}/css/table.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
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
<script src="${ctxStatic}/js/page.js"></script>
<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>
<script src="${ctxStatic}/js/ldialog.js"></script>
<style type="text/css">
.tabs-title {
	font-size: 15px
}
</style>

<script type="text/javascript">
	
	var checkIndex = 0;
	$(function(){
		//根据浏览器高度设置ifram的高度
		var goladHeigth = $("#center").height();
		$("#regFrame").height(goladHeigth-35);
		$("#regFrame1").height(goladHeigth-35);
	
		//初始化tab页签
		$('#resident').tabs({
			    border:false,
			    fit:true,
			    onSelect:function(title,index){
			    	checkIndex = index;
			    	$("#personName").val("");
					$("#idCard").val("");
			        if(index == 0){//已签约用户
			        	$("#regFrame").attr("src","${ctx}/queryResident/queryResidentInfo");
			        }else{
			        	$("#regFrame1").attr("src","${ctx}/queryResident/querynoResidentInfo");
			        }
			    }
		});

	})
	
	
	function refresh(index){
		if(index == 0){//已签约用户
        	$("#regFrame").attr("src","${ctx}/queryResident/queryResidentInfo");
        }else{
        	$("#regFrame1").attr("src","${ctx}/queryResident/querynoResidentInfo");
        }
	}


	function addResident() {
		$(document.body).ldialog({
			idFled : 'addresident',
			width : 900,
			height : 500,
			url :'${ctx}/queryResident/addresident.do',
			title : "新增居民信息"
		});
	}
	
	
	function searchResident(isfresh) {
		var personName = $("#personName").val();
		var idCard = $("#idCard").val();
		
		//阻止提交
		if(isfresh== true){
			if(checkIndex == 0){
				$("#regFrame").attr("src","${ctx}/queryResident/queryResidentInfo?personName="+personName + "&idCard="+idCard);
			}else{
				$("#regFrame1").attr("src","${ctx}/queryResident/querynoResidentInfo?personName="+personName + "&idCard="+idCard);
			}
		}else{
			//如果输入了省份证，同时满足18位身份证的情况下
		if(idCard &&  idCard.length == 18){
			//第一步判断 该身份证是居民身份证号码
			var  url = "${ctx}/resident/getResidenceMember";
			var param  = {
				idCard:idCard,
				date:new Date().getTime()
			}
			$.get(url,param,function(data){
				if(data.data.length== 0){
					$.messager.alert('提示','该身份证信息不存在请检查','warning');
					$("#idCard").val("");
				}else{
					//如果存在判断应该显示在签约还是未签约的tab页签   resident/getAllResident
					url = "${ctx}/resident/getAllResident";
					param  = {
						idCard:idCard,
						date:new Date().getTime()
					}
					$.get(url,param,function(data){
						if(data.data.length== 0){
							$("#regFrame1").attr("src","${ctx}/queryResident/querynoResidentInfo?personName="+personName + "&idCard="+idCard);
						}else{
							var residentData = data.data;
							for(var  index   in  residentData){
								var dCard = residentData[index].idCard;
								if(dCard == idCard){
									if(residentData[index].idCard=idCard){
										$('#resident').tabs("select","已签约用户");
										$("#personName").val(personName);
										$("#idCard").val(idCard);
										$("#regFrame").attr("src","${ctx}/queryResident/queryResidentInfo?personName="+personName + "&idCard="+idCard);
									}else{
										$('#resident').tabs("select","未签约用户");
										$("#personName").val(personName);
										$("#idCard").val(idCard);
										$("#regFrame1").attr("src","${ctx}/queryResident/querynoResidentInfo?personName="+personName + "&idCard="+idCard);
									}
									break;
								}
							}
						}
						
					},"JSON")
					
				}
			},"JSON")
			
		}else{
			if(checkIndex == 0){
				$("#regFrame").attr("src","${ctx}/queryResident/queryResidentInfo?personName="+personName + "&idCard="+idCard);
			}else{
				$("#regFrame1").attr("src","${ctx}/queryResident/querynoResidentInfo?personName="+personName + "&idCard="+idCard);
			}
		}
		}
	}
	
	
	function detailsViews(packId){
		$(document.body).ldialog({
			idFled : 'showserviceInfo',
			width : 700,
			height : 400,
			url :"${ctx}/queryResident/obtainaServicepckDatias.do?packId="+packId,
			title : "查看服务包详情信息"
		});
	}
	

	function singservice(idCard){
		$("#addresidentsing").attr("action","${ctx}/queryResident/addresidentsing?idCard="+idCard)
		$("#addresidentsing").submit();
	}
	
	
	
</script>
</head>
<body class="easyui-layout" style="padding: 2px">
	<form action="" id="addresidentsing" method="post"></form>
	<div region="north" split="false" style="height:100px;">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>签约服务包管理/</span><span>编辑</span>
			</div>
		</div>
		<form method="post" class="form-inline" id="searchForm"
			action="${ctx}/queryResident/queryResidentInfo"
			style="margin-bottom: 2px">

			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" /> <input type="hidden" id="outPutType"
				name="outPutType" value="">

			<div class="form-group">
				<label style="font-size:15px">居民姓名:</label> <input type="text"
					id='personName' name="personName" placeholder="请输入居民姓名"
					class="form-control" value="${option.personName}">
			</div>

			<div class="form-group">
				<label style="font-size:15px">身份证:</label> <input type="text"
					style="width: 250px" id="idCard" name="idCard"
					placeholder="请输入居民省份证号码" class="form-control"
					value="${option.idCard}">
			</div>
			<div class="form-group">
				<a href="#" class="easyui-linkbutton" onclick="searchResident()">查询</a>
				<a href="#" class="easyui-linkbutton" onclick="addResident()">新增用户</a>

			</div>
		</form>
	</div>

	<div region="center" border="false" id="center">
		<div id="resident">
			<div title="已签约用户">
				<iframe id="regFrame" name="regFrame" class="regFrame" src=""
					frameborder="0" style="width: 100%" scrolling="auto"></iframe>
			</div>

			<div title="未签约用户" class="framClass">
				<iframe id="regFrame1" name="regFrame1" class="regFrame" src=""
					frameborder="0" style="width: 100%" scrolling="auto"></iframe>
			</div>
		</div>



	</div>
</body>
</html>