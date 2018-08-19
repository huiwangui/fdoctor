<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> --%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="table" tagdir="/WEB-INF/tags/table" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/statics"/>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<!--<![endif]-->
<html lang="en">
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>家庭医生</title>
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/menu.css">
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/body.css">
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/easyui.css">
<link rel="stylesheet" type="text/css"   href="${ctxStatic}/css/icon.css">
<link href="${ctxStatic}/css/style.css"  rel="stylesheet"  type="text/css" />
<link href="${ctxStatic}/css/common.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script src="${ctxStatic}/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function(){
	
			alert(1111);
	})
	function GetBytes(str){
    var len = str.length;
    var bytes = len;
    for(var i=0; i<len; i++){
        if (str.charCodeAt(i) > 255) bytes++;
    }
     return bytes;
}
alert(GetBytes("你好,as"));
</script>


</head>
<body  class="easyui-layout" data-options="true">
	 <div region="north" border="false"  split="fasle" style="height:120px;">
	 	<div class="nav">
			<div class="nav-logo g-left">
			<h1 align="center">
           		<span>欢迎登录家庭医生子系统111</span>
        	</h1>	</div>
			<div class="login-box g-right">
				<a class="home" href=""  
					target="myFrame">回到首页</a> 
					<a class="userName"  id="userName" href="javascript:return false;">${vo.username }</a>
				<a class="logOut" href="javascript;">退出</a>
			</div>
		</div>
	 </div>
	
	<%-- 
	<!-- 内容 -->
	<div class="wrap">
		<div class="mainSection">
			<!-- left-menu -->
			<div id="menu" class="menu g-left bordure">
				<div class="menu-topBar">
					功能菜单 <span class="stripe"></span>
				</div>
				<!-- 获取菜单列表 -->
				<%@ include file="include/menu.jsp"%>
			</div>
			<!-- right&content -->
			<div class="wrap">
				<div class="g-mainContent">

					<div class="column">
						<iframe id="myFrame" name="myFrame" class="myFrame"
							src="contents.jsp" frameborder="0"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div> --%>
	<script src="${pageContext.request.contextPath}/statics/js/index.js"></script>
</body>
</html>