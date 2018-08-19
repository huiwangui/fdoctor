<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="label" type="java.lang.String" required="false"%>
<button class="btn btn-default btn-sm" onclick="deleteAll()" data-toggle="tooltip" data-placement="top"><i class="fa fa-trash-o"> ${label==null?'删除':label}</i>
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

	function deleteAll(){

		// var url = $(this).attr('data-url');
		  var str="";
		  var ids="";
		  $("#${id} tbody tr td input.i-checks:checkbox").each(function(){
		    if(true == $(this).is(':checked')){
		      str+=$(this).attr("id")+",";
		    }
		  });
		  if(str.substr(str.length-1)== ','){
		    ids = str.substr(0,str.length-1);
		  }
		  if(ids == ""){
			top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
			return;
		  }
			top.layer.confirm('确认要彻底删除数据吗?', {icon: 3, title:'系统提示'}, function(index){
			window.location = "${url}?ids="+ids;
		    top.layer.close(index);
		});
		 

	}
</script>