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
<link href="${ctxStatic}/css/style.css"  rel="stylesheet"  type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/index.css">
<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	 $(function(){
	 
	 	var Sys = {};
		var ua = navigator.userAgent.toLowerCase();
		if (window.ActiveXObject)

			Sys.ie = ua.match(/msie ([\d.]+)/)[1]

		else if (document.getBoxObjectFor)

			Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1]

		else if (window.MessageEvent && !document.getBoxObjectFor)

			Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1]

		else if (window.opera)

			Sys.opera = ua.match(/opera.([\d.]+)/)[1]

		else if (window.openDatabase)
			Sys.safari = ua.match(/version\/([\d.]+)/)[1];
			
			
	 	 if (Sys.ie) {
	 	 	$(".left-menu").css("height","530px");
	 	 	$(".myframe").css("min-height","530px");
	 	 }

		
		
	})	 
	
	
	function returnfirst() {
		window.location = window.location;
	}
	
	
</script>
	

</head>
<body  class="easyui-layout "   data-options="fit:true">

	<div class="home-bg">

	<div region="north"    split="fasle">
			<!-- 头部 -->
			<div  class="h-top">
				<div class="nav">
					<div class="h-logo g-left">
						<a href="javascript:;" title=""></a>
					</div>
					<div class="user-nav g-right">
						<label class="admin-ico" style="font-size: 14px"><a class="home" href="#" onclick="returnfirst()"  target="myFrame">回到首页</a></label><a class="userName"  id="userName" href="javascript:return false;">${vo.username }</a>
						<label class="off-ico" style="font-size: 14px"><a class="logOut" href="javascript;">退出</a></label>
					</div>
				</div>
			</div>
		</div>
	 
	 
		<div class="h-content">
			<div class="left-menu g-left" >
				<!-- 用户头像信息&模拟操作台 -->
				<div class="wrap">
					<div class="bordure doc-tab">
						<div class="wrap" style="text-align:center">
							<div class="doc-head"></div>
						</div>
						<div class="wrap">
							<ul>
								<li><a href=""></a></li>
								<li><a href=""></a></li>
								<li><a href=""></a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- 获取菜单列表 -->
				<%@ include file="include/menu.jsp"%>
			</div>

			<div>
				<iframe  id="myFrame" name="myFrame" class="myframe"  src="" 
				 frameborder="0" scrolling="no" onload="Javascript:SetCwinHeight()">
				
				</iframe> 
			</div>
		</div>
	</div>
	<script src="${ctxStatic}/js/index.js"></script>
</body>
</html>