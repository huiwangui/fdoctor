<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绩效管理（医生和卫生院管理机构）</title>
<script src="${ctx}/statics/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/page.js"></script>
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/menu.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/body.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
<link href="${ctxStatic}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/css/table.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>
<script src="${ctxStatic}/js/ldialog.js"></script>
<style type="text/css">
.tabs-title {
	font-size: 15px
}
</style>
<style type="text/css">
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>
<script type="text/javascript">
	function submits() {
		document.getElementById("searchForm").submit();
	}
	function complete() {
		document.getElementById("searchForm").action = "${ctx}/sigserverpack/getSvsPackDetailsList?sigSvsPackId="
				+ sigSvsPackId;
		document.getElementById("searchForm").submit();
	}
	function unComplete() {
		document.getElementById("searchForm").submit();
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
</script>
<style type="text/css">
.tabs-title {
    font-size: 15px
}
.Tab th{ border:solid 1px gray}
.circle {
    width: 80px;
    height: 80px;
    position: absolute;
    border-radius: 50%;
    background: #0cc;
    cursor:pointer;
}
 
.left,.right{
    width:80px;
    height:80px;
    position: absolute;
    top: 0px;left: 0px;
}
.pie_left1, .pie_right1,.pie_left2, .pie_right2,.pie_left3, .pie_right3{
    width:80px;
    height:80px;
    position: absolute;
    border-radius: 50%;
    top: 0px;left: 0px;
    background: Silver;
}
.pie_right1,.right,.pie_right2,.pie_right3 {
    clip:rect(0,auto,auto,40px);
}
.pie_left1,.left,.pie_left2,.pie_left3{
    clip:rect(0,40px,auto,0);
}
 
/*
*当top和left取值为auto时，相当于0
*当bottom和right取值为auto时，相当于100%
*/
.mask1,.mask2,.mask3 {
    width: 65px;
    height: 65px;
    border-radius: 50%;
    left: 7.5px;
    top: 7.5px;
    background: #FFF;
    position: absolute;
    text-align: center;
    line-height: 65px;
    font-size: 16px;
}
.progressname {
    width: 75px;
    height: 75px;
    position: absolute;
    text-align: center;
    line-height: 110px;
    font-size: 13px;
}
</style>
</head>
<body class="easyui-layout" style="padding: 2px">
	<form action="" id="addresidentsing" method="post"></form>

	<div region="north" split="false" style="height: 128px;">
		<div class="top-bar">
			<div class="locate-index">
				位置:<span>签约服务包管理/</span><span>编辑</span>
			</div>
		</div>
		<form name="countFrm" id="countFrm" action="" method="post">
		<c:if test="${! empty a}">
            
            <div style="padding-left:125px;">
                <div class="circle" onclick="switch1('完成率')">
                    <div class="pie_left1">
                        <div class="left"></div>
                    </div>
                    <div class="pie_right1">
                        <div class="right"></div>
                    </div>
                    <div class="mask1"><font color="red"><span>${a}</span>%</font></div>
                    <div class="progressname">完成率</div>
                </div>
            </div>
            <div style="padding-left:325px;">
                <div class="circle" onclick="switch1('及时率')">
                    <div class="pie_left2">
                        <div class="left"></div>
                    </div>
                    <div class="pie_right2">
                        <div class="right"></div>
                    </div>
                    <div class="mask2"><font color="red"><span>${b}</span>%</font></div>
                    <div class="progressname">及时率</div>
                </div>
            </div>
            <div style="padding-left:525px;">
                <div class="circle" onclick="switch1('缺项率')">
                    <div class="pie_left3">
                        <div class="left"></div>
                    </div>
                    <div class="pie_right3">
                        <div class="right"></div>
                    </div>
                    <div class="mask3"><font color="red"><span>${c}</span>%</font></div>
                    <div class="progressname">缺项率</div>
                </div>
            </div>
          
            </c:if>
            <c:if test="${ empty a}">
            
            <div style="padding-left:125px;">
                <div class="circle" onclick="switch1('完成率')">
                    <div class="pie_left1">
                        <div class="left"></div>
                    </div>
                    <div class="pie_right1">
                        <div class="right"></div>
                    </div>
                    <div class="mask1"><font color="red"><span>0</span>%</font></div>
                    <div class="progressname">完成率</div>
                </div>
            </div>
            <div style="padding-left:325px;">
                <div class="circle" onclick="switch1('及时率')">
                    <div class="pie_left2">
                        <div class="left"></div>
                    </div>
                    <div class="pie_right2">
                        <div class="right"></div>
                    </div>
                    <div class="mask2"><font color="red"><span>0</span>%</font></div>
                    <div class="progressname">及时率</div>
                </div>
            </div>
            <div style="padding-left:525px;">
                <div class="circle" onclick="switch1('缺项率')">
                    <div class="pie_left3">
                        <div class="left"></div>
                    </div>
                    <div class="pie_right3">
                        <div class="right"></div>
                    </div>
                    <div class="mask3"><font color="red"><span>0</span>%</font></div>
                    <div class="progressname">缺项率</div>
                </div>
            </div>
      
            </c:if>
		</form>
	</div>
	
	<div region="center" border="false">
		<form method="post" class="form-inline" id="searchForm"
			action="${ctx}/pmdh/qryPmDocHosLst" style="margin-bottom: 2px">
			
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" /> <input type="hidden" id="outPutType"
				name="outPutType" value="">

			<div class="form-group">
				<label style="font-size: 15px">居民姓名:</label> <input type="text"
					id='personName' name="personName" placeholder="请输入居民姓名"
					class="form-control" value="${option.personName}">
			</div>

			<div class="form-group">
				<label style="font-size: 15px">身份证:</label> <input type="text"
					style="width: 250px" id="idCard" name="idCard"
					placeholder="请输入居民省份证号码" class="form-control"
					value="${option.idCard}">
			</div>
			<div class="form-group">
				<a href="#" class="easyui-linkbutton" onclick="submits()">查询</a>

			</div>
			<c:if test="${not empty page.list }">
			<table class="hovertable">
				<tr onmouseover="this.style.backgroundColor='#ffff66';"
					onmouseout="this.style.backgroundColor='#d4e3e5';">
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>身份证号</th>
					<th>服务包</th>
					<th>价格</th>
					<th>服务项目</th>
					<th>已服务次数</th>
					<th>应服务次数</th>
					<th>及时服务次数</th>
					<th>未及时服务次数</th>
					<th>完成率</th>
					<th>及时率</th>
					<th>缺项率</th>
					
				</tr>
				<c:forEach items="${page.list}" var="p" varStatus="status">
					<tr onmouseover="this.style.backgroundColor='#ffff66';"
						onmouseout="this.style.backgroundColor='#d4e3e5';">
						<td>${p.personName}</td>
						<td><c:if test="${p.sex==0}">男</c:if> <c:if
								test="${p.sex==1}">女</c:if></td>
						<td>${p.age}岁</td>
						<td>${p.idCard}</td>
						<td>${p.packName}</td>
						<td>${p.packPrice}元</td>
						<td><c:if test="${p.sigId!=0}"><a href="#" class="easyui-linkbutton" onclick="detailsViews(${p.sigId});">明细</a></c:if></td>
						<td>${p.servicecount}次</td>
						<td>${p.total}次</td>
						<td>${p.isTime}次</td>
						<td>${p.noTime}次</td>
						<td><c:if test="${p.rate!=0.0 }">
						<fmt:formatNumber value="${p.rate}" pattern="#.00" />%
						</c:if>
						<c:if test="${p.rate==0.0}">
						0.00%
						</c:if>
						</td>
						<td>
						<c:if test="${p.onTimeRate!=0.0 }">
                        <fmt:formatNumber value="${p.onTimeRate}" pattern="#.00" />%
                        </c:if>
                        <c:if test="${p.onTimeRate==0.0}">
                        0.00%
                        </c:if>
					
						<td>
						<c:if test="${p.missRate!=0.0 }">
                        <fmt:formatNumber value="${p.missRate}" pattern="#.00" />%
                        </c:if>
                        <c:if test="${p.missRate==0.0}">
                        100.00%
                        </c:if>
					
						</tr>
						</c:forEach>
			</table>
			</c:if>
			<div id="div-page">
				<table:page page="${page}"></table:page>
			</div>
		</form>
	
	</div>


</body>
</html>