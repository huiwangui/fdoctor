<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>签约服务包详情</title>

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
    function addSigSvsPack() {
        document.getElementById("searchForm").action = "${ctx}/sigserverpack/openAddSigSvsPack";
        document.getElementById("searchForm").submit();
    }

    function viewSvsPackDts(sigSvsPackId) {
        document.getElementById("searchForm").action = "${ctx}/sigserverpack/getSvsPackDetailsList?sigSvsPackId="
                + sigSvsPackId;
        document.getElementById("searchForm").submit();
    }
    function openEditSigSvsPack(id) {
        document.getElementById("searchForm").action = "${ctx}/sigserverpack/openEditSigSvsPack?id="
                + id;
        document.getElementById("searchForm").submit();
    }

    $(function() {
        $("input[type=checkbox]").first().click(function() {
            $("input[type=checkbox]").prop("checked", this.checked);
        });
    });
    /**逻辑删除签约服务包及相关中间详情*/
    function deleteSigSvsPackOrDtl() {
        var boxArray = document.getElementsByName('sigSvsPackIds');
        var total = 0;
        for (var i = 0; i < boxArray.length; i++) {
            if (boxArray[i].checked) {
                total++;
            }
        }
        if (total > 0) {
            if (window.confirm('共选中' + total + '个服务包记录，是否要删除？')) {
                var frm = document.forms[0];
                frm.action = "${ctx}/sigserverpack/deletSigSvsPackOrDtl";
                frm.submit();
                return true;
            } else {
                return false;
            }
        } else {
            window.alert('没有选择任何服务包记录！');
            return false;
        }
    }
    function goBack() {
        document.getElementById("searchForm").action = "${ctx}/sigserverpack/getSvsPackList";
        document.getElementById("searchForm").submit();
    }  
    
</script>
</head>
<body class="easyui-layout" style="padding: 2px">
    <div region="center" split="false" style="height: 200px;">
        <div class="top-bar">
            <div class="locate-index">
                位置:<span>签约管理/</span><span>签约服务包管理</span>
            </div>
        </div>
        <div id="resident" class="easyui-tabs" fit="true">
            <div title="签约服务包详情">
                <form method="post" id="searchForm"   class="data-form">
                    <input id="pageNo" name="pageNo" type="hidden"
                        value="${page.pageNo}" /> <input id="pageSize" name="pageSize"
                        type="hidden" value="${page.pageSize}" />
                    <table class="table" cellspacing="0" cellpadding="0"
                        id="checkTable">
                        <tr>

                            <th>服务项目列表</th>
                            <th>名称</th>
                            <th>内容</th>
                            <th>原价格</th>
                            <th>频次</th>
                            <th>服务时间</th>
                            <th>执行机构</th>
                        </tr>
                        <tbody id="tbody-person-record">
                            <c:forEach items="${page.list}" var="p">
                                <tr>

                                    <td>${p.serviceItem }</td>
                                    <td>${p.serviceName }</td>
                                    <td>${p.serviceDetails }</td>
                                    <td>${p.originalPrice }</td>
                                    <td><c:if test="${p.frequency==-1}">不限次数</c:if>
                                    <c:if test="${p.frequency!=-1}">${p.frequency}次</c:if>
                                    </td>
                                    <td>${p.serviceTime }</td>
                                    <td>${p.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!-- 分页信息 -->
                    <div id="div-page">
                        <table:page page="${page}"></table:page>
                    </div>
        <input type="button" value="返回" class="btn btn-blue" onclick="goBack();">
                </form>
            </div>

        </div>





    </div>
</body>
</html>