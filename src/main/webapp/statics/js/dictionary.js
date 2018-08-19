/**
 * 加载字典项下拉列表，下拉列表样式使用chosen，便于检索
 * @param dictTypeId 字典类型ID
 * @param selectId select标签ID
 * @return
 */
function setDictList(dictTypeId,selectId)
{
	var params = {};
	params.url="http://192.168.15.6:8180/CMMS/dictionary/dictionaryJson.do?dictTypeId=" + dictTypeId;
	$.ajax({  
		type: "GET",  
		url: "/boco-health-api/commonApi/getUrlData",  
		data:params,  
		dataType:"json", 
		success:function(data){ 
			if(data != ""){
				//抓取select
				$(selectId).chosen("destroy");
				//生成option
				$option = $("<option></option>");
				$option.attr("value","");
				$option.text("");
				selectId.append($option);
				for (var i = 0; i < data.data.length; i++) {
					$option = $("<option></option>");
					$option.attr("value",data.data[i].value);
					$option.text(data.data[i].label);
					selectId.append($option);
				}
				//重新生成chosen，设置chosen属性
				$(selectId).chosen({
					no_results_text: "未查询到信息",//搜索不到内容时，显示的内容
					search_contains: true//开启模糊查询
				});
			}  
		},  
		error:function(data){  
			alert("出现异常，异常原因【" + data + "】!");    
		}
	});
}