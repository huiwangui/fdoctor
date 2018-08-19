<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签约服务包编辑</title>

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
td {
	border: 1px #D1D1D1 solid
}

</style>

<script type="text/javascript">

$(function() {
    $("input[type=checkbox]").first().click(function() {
        $("input[type=checkbox]").prop("checked", this.checked);
    });
});
   
function deleteSvsPackRln(sigId,detailsId){
	document.getElementById("searchForm").action = "${ctx}/sigserverpack/deleteSvsPackRln?sigId="+sigId+"&detailsId="+detailsId;
    document.getElementById("searchForm").submit();
}
function openUnSvsPackDetailsList(sigId){
    document.getElementById("searchForm").action = "${ctx}/sigserverpack/getUnSvsPackDetailsList?sigId="+sigId;
    document.getElementById("searchForm").submit();
}
function deleteSvsPackRlns(){
	document.getElementById("searchForm").action = "${ctx}/sigserverpack/deleteSvsPackRln?sigId="+sigId+"&detailsId="+detailsId;
    document.getElementById("searchForm").submit();
}
    
</script>
<style type="text/css">
  .mybtn{
    border-right: #7b9ebd 1px solid;
    padding-right: 2px;
    border-top: #7b9ebd 1px solid;
    padding-left: 2px;
    font-size: 12px;
    FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,  StartColorStr=#ffffff,  EndColorStr=#cecfde);
    border-left: #7b9ebd 1px solid;
    cursor: hand;
    color: black;
    padding-top: 2px;
    border-bottom: #7b9ebd 1px solid;
  
  }
</style>
</head>
  <body class="easyui-layout" style="padding: 2px">

    <div region="north" split="false" style="height:200px;">
        <div class="top-bar">
            <div class="locate-index">
                位置:<span>签约服务包/</span><span>签约服务包管理/</span><span>签约服务包变价</span>
            </div>
        </div>
        <form method="post" class="form-inline" id="searchForm" action=""
            style="margin-bottom: 2px">
            <table style="background-color: #F7F7F7; width: 100%">
                <tr>
                    <td colspan="3"><label
                        style="background-color: #53E0D7; width: 100%">签约服务包基本信息</label></td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label style="font-size: 15px; width: 90px">服务包名称：</label> <input
                                type="text" id='packName' name="packName" class="form-control"
                                style="width: 100px" value="${packName}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label style="font-size: 15px; width: 140px">居民自付金额(元):</label> <input
                                type="text" id="userPay" name="userPay" style="width: 100px"
                                id="idCard" class="form-control" value="${userPay}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label style="font-size: 15px; width: 90px">价格(元):</label> <input
                                type="text" style="width: 150px" id="packPrice" name="packPrice"
                                class="form-control" value="${packPrice}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label style="font-size: 15px; width: 70px">建议人群:</label> <input
                                type="text" id="adviceGroup" name="adviceGroup"
                                class="form-control" style="width: 100px"
                                value="${adviceGroup}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label style="font-size: 15px; width: 90px">目标:</label> <input
                                type="text" id="target" name="target" class="form-control"
                                style="width: 180px" value="${target}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label style="font-size: 15px; width: 120px">机构分成比例(%):</label> <input
                                type="text" id="orgratio" name="orgratio" class="form-control"
                                style="width: 100px" value="${orgratio}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="form-group">
                            <label style="font-size: 15px; width: 70px">备注：</label> <input
                                type="text" id=“remarks” name="remarks" class="form-control"
                                style="width: 180px" value="${remarks}">
                        </div>
                    </td>

                </tr>
            </table>
        </form>
    </div>

    <div region="center" border="false">
        <div id="resident" class="easyui-tabs"  fit="true">
            <div title="服务项目列表">
              <form method="post" id="searchForm"  class="data-form">
                <input id="pageNo" name="pageNo" type="hidden"
                        value="${dtsPage.pageNo}" />
                         <input id="pageSize" name="pageSize"
                        type="hidden" value="${dtsPage.pageSize}" />
                    <table class="table" cellspacing="0" cellpadding="0"
                        id="checkTable">
                        <thead>服务包明细</thead>
                        <tr>
                           
                            <th>服务项目列表</th>
                            <th>名称</th>
                            <th>内容</th>
                            <th>原价格</th>
                            <th>频次</th>
                            <th>服务时间</th>
                            <th>执行机构</th>
                            <th>操作</th>
                        </tr>
                        <tbody id="tbody-person-record">
                            <c:forEach items="${dtsPage.list}" var="dtsp" varStatus="status">
                                <tr>
                               
                                    <td>${dtsp.serviceItem } </td>
                                    <td>${dtsp.serviceName } </td>
                                    <td>${dtsp.serviceDetails } </td>
                                    <td>${dtsp.originalPrice } </td>
                                    <td><c:if test="${dtsp.frequency==-1}">不限次数</c:if>
                                    <c:if test="${dtsp.frequency!=-1}">${dtsp.frequency}次</c:if></td>
                                    <td>${dtsp.serviceTime } </td>
                                    <td>${dtsp.serviceOrgName}</td>
                                   
                                    <td><input type="button" value="删除"  class="btn btn-blue"
                                    onclick="deleteSvsPackRln(document.getElementById('sigId').value,'${dtsp.id}');">
                                    
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
               
                    <input type="button" class="btn btn-blue"  value="新增服务项目列表" onclick="openUnSvsPackDetailsList(document.getElementById('sigId').value);" class="mybtn">
                <input type="button" class="btn btn-blue" value="返回" onclick="history.go(-1)">
                   <input type="hidden" name="sigId" id="sigId" value="${id}">
                </form>
            </div>
           
        </div>



    </div>
</body>
</html>