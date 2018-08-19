/**
 * 获得区域
 */
function getRegion(pid,thisElement){
		var params = {};  
        params.url="http://192.168.15.12:8080/CMMS/region/getRegionJsonSvs.do?pid="+pid;
        var nextElement;
        if(pid==0){
        	nextElement = $("#province");
        }else{
        	nextElement = $(thisElement).next();
        }
        nextElement.children().remove();
        nextElement.append("<option>--请选择--</option>");
        $.ajax({  
               type: "GET",  
               url: "/boco-health-api/commonApi/getUrlData",  
               data:params,  
               dataType:"json", 
               success:function(data){  
            	   if(data != ""){
            		   for(var i=0;i<data.length;i++){
            			   var name=data[i].name;
            			   var id=data[i].id;
            			   $option = $("<option></option>");
       					   $option.attr("value",id);
       					   $option.text(name);
       					   nextElement.append($option);
            		   }
            	   }  
               },  
               error:function(data){  
                   alert("出现异常，异常原因【" + data + "】!");    
               }  
            });
        nextElement.selectpicker('refresh');
}