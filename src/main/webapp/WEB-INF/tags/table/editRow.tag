<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="title" type="java.lang.String" required="true"%>
<%@ attribute name="width" type="java.lang.String" required="false"%>
<%@ attribute name="height" type="java.lang.String" required="false"%>
<%@ attribute name="target" type="java.lang.String" required="false"%>
<%@ attribute name="label" type="java.lang.String" required="false"%>
<button class="btn btn-default btn-sm" data-toggle="tooltip" data-placement="left" onclick="edit()" title="修改"><i class="fa fa-file-text-o"></i> ${label==null?'修改':label}</button>
                        </button>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>
<script type="text/javascript">
$(document).ready(function() {
    $('#${id} thead tr th input.i-checks').click(function(){ //全选/全不选
		if($('#${id} thead tr th input.i-checks').is(':checked')){
			$('#${id} tbody tr td input.i-checks:checkbox').prop('checked',true);
		}else{
    	  $('#${id} tbody tr td input.i-checks:checkbox').prop('checked',false);
    	}
	});
    
});

function edit() {

	// var url = $(this).attr('data-url');
	var str = "";
	var ids = "";
	var size = $("#${id} tbody tr td input.i-checks:checked").size();
	if (size == 0) {
		top.layer.alert('请至少选择一条数据!', {icon: 0, title: '警告'});
		return;
	}

	if (size > 1) {
		top.layer.alert('只能选择一条数据!', {icon: 0, title: '警告'});
		return;
	}
	var id = $("#${id} tbody tr td input.i-checks:checkbox:checked").attr("id");
	openModal("${url}?id=" + id);
}
</script>