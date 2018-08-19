<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>家庭医生</title>
		<style type="text/css">
			body{margin:0; padding:0;}
			.myiframe1{
				float: left;
			}
			.myiframe2{
				float: right;
			}
		</style>
		<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
	</head>
	<body>
		<div class="clearfix">
 
		<!-- 	<iframe src="/fdoctor/views/mobile/leftMenu.html" width="120" height=""  frameborder="0" scrolling="no" id="myiframe1" class="myiframe1"></iframe>
			<iframe src="/fdoctor/mobile/healthRecord/healthIndex"    width="" height="" class="myiframe2"  frameborder="0" scrolling="no" id="myiframe2"></iframe>
			<iframe src="/fdoctor/mobile/healthRecord/healthIndex" id="iframe"  width="" height="" class="myiframe2"  frameborder="0" scrolling="no" id="myiframe2"></iframe>
  -->
			<iframe src="" width="120" height=""  frameborder="0" scrolling="no" id="myiframe1" class="myiframe1"></iframe>
			<iframe src="/fdoctor/mobile/healthRecord/healthIndex"    width="" height="" class="myiframe2"  frameborder="0"  id="myiframe2" style="overflow-y: auto;overflow-x: hidden; "></iframe>
 
		</div>
		<script type="text/javascript">
			var ifm1= document.getElementById("myiframe1");
			ifm1.height=document.documentElement.clientHeight;
			var ifm2=document.getElementById("myiframe2");
			ifm2.height=document.documentElement.clientHeight;
			ifm2.width=document.documentElement.clientWidth-140;
			window.onresize = function(){
	               ifm2.width=document.documentElement.clientWidth-140;
	        }
			
			/*  function UrlSearch() 
			{
			   var name,value; 
			   var str=location.href; //取得整个地址栏
			   alert(str)
			   var num=str.indexOf("?") 
			   str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]

			   var arr=str.split("&"); //各个参数放到数组里
			   for(var i=0;i < arr.length;i++){ 
			    num=arr[i].indexOf("="); 
			    if(num>0){ 
			     name=arr[i].substring(0,num);
			     value=arr[i].substr(num+1);
			     this[name]=value;
			     } 
			    } 
			} 
			var Req=new UrlSearch(); //实例化
		    var List=JSON.parse(Req); 
			alert(List["status"]);
			  */
			  $(function(){
				  var androidDataJson='${androidDataMap}'
				  /* //alert(androidDataJson) */
				  $('#myiframe1').attr('src','/fdoctor/mobile/healthRecord/showMenu?androidDataMap='+'${androidDataMap}');
				  var List=JSON.parse(androidDataJson); 
				  if(List.status==1){
					  //健康档案
					 
					  $("#myiframe2").attr("src","/fdoctor/mobile/healthRecord/healthIndex?androidData="+androidDataJson);
					  
					  $('.myiframe1').on('load',function(){
						  var mg1=$(".myiframe1").contents().find(".health a img");
						  var mg2=$(".myiframe1").contents().find(".physical a img");
						  var mg3=$(".myiframe1").contents().find(".oldMan a img");
						  var mg4=$(".myiframe1").contents().find(".hypertension a img");
						  var mg5=$(".myiframe1").contents().find(".diabetes a img");
						  $(".myiframe1").contents().find(".headth").addClass('menu_blue').siblings().removeClass('menu_blue');
						  $(".myiframe1").contents().find(".headth").addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
						  	mg1.prop('src','/fdoctor/statics/image/dangan1.png');
							mg2.prop('src','/fdoctor/statics/image/tijian2.png');
							mg3.prop('src','/fdoctor/statics/image/laonian2.png');
							mg4.prop('src','/fdoctor/statics/image/aoxuey2.png');
							mg5.prop('src','/fdoctor/statics/image/tangjiaob2.png');
							
					  })
					  
					  
					  
				  		
				  }else if(List.status==2){
					  //居民体检
					    $("#myiframe2").attr("src","/fdoctor/mobile/examination/toExaminationList?androidData="+androidDataJson)
					   	$('.myiframe1').on('load',function(){
				   		  var mg1=$(".myiframe1").contents().find(".health a img");
						  var mg2=$(".myiframe1").contents().find(".physical a img");
						  var mg3=$(".myiframe1").contents().find(".oldMan a img");
						  var mg4=$(".myiframe1").contents().find(".hypertension a img");
						  var mg5=$(".myiframe1").contents().find(".diabetes a img");
						  $(".myiframe1").contents().find(".physical").addClass('menu_blue').siblings().removeClass('menu_blue');
						  $(".myiframe1").contents().find(".physical").addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
						  	mg1.prop('src','/fdoctor/statics/image/dangan2.png');
							mg2.prop('src','/fdoctor/statics/image/tijian1.png');
							mg3.prop('src','/fdoctor/statics/image/laonian2.png');
							mg4.prop('src','/fdoctor/statics/image/aoxuey2.png');
							mg5.prop('src','/fdoctor/statics/image/tangjiaob2.png');
					  })
				  
				  }else if(List.status==3){
					  //老年人管理
					  $("#myiframe2").attr("src","/fdoctor/mobile/examination/OldList?androidData="+androidDataJson)
				   		$('.myiframe1').on('load',function(){
			   			  var mg1=$(".myiframe1").contents().find(".health a img");
						  var mg2=$(".myiframe1").contents().find(".physical a img");
						  var mg3=$(".myiframe1").contents().find(".oldMan a img");
						  var mg4=$(".myiframe1").contents().find(".hypertension a img");
						  var mg5=$(".myiframe1").contents().find(".diabetes a img");
						  $(".myiframe1").contents().find(".oldMan").addClass('menu_blue').siblings().removeClass('menu_blue');
						  $(".myiframe1").contents().find(".oldMan").addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
						  	mg1.prop('src','/fdoctor/statics/image/dangan2.png');
							mg2.prop('src','/fdoctor/statics/image/tijian2.png');
							mg3.prop('src','/fdoctor/statics/image/laonian1.png');
							mg4.prop('src','/fdoctor/statics/image/aoxuey2.png');
							mg5.prop('src','/fdoctor/statics/image/tangjiaob2.png');
					  })
				  
				  
				  }else if(List.status==4){
					  //高血压管理
					  	
					    $("#myiframe2").attr("src","/fdoctor/mobile/hypertension/queryindex?androidData="+androidDataJson)
				 		$('.myiframe1').on('load',function(){
				 		  var mg1=$(".myiframe1").contents().find(".health a img");
						  var mg2=$(".myiframe1").contents().find(".physical a img");
						  var mg3=$(".myiframe1").contents().find(".oldMan a img");
						  var mg4=$(".myiframe1").contents().find(".hypertension a img");
						  var  mg5=$(".myiframe1").contents().find(".diabetes a img");
						  $(".myiframe1").contents().find(".hypertension").addClass('menu_blue').siblings().removeClass('menu_blue');
						  $(".myiframe1").contents().find(".hypertension").addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
						 	mg1.prop('src','/fdoctor/statics/image/dangan2.png');
							mg2.prop('src','/fdoctor/statics/image/tijian2.png');
							mg3.prop('src','/fdoctor/statics/image/laonian2.png');
							mg4.prop('src','/fdoctor/statics/image/gaoxuey1.png');
							mg5.prop('src','/fdoctor/statics/image/tangjiaob2.png');
					   })
				  
				  
				  
				  
				  }else if(List.status==5){
					  //糖尿病管理
					    $("#myiframe2").attr("src","/fdoctor/mobile/diabetes/queryindex?androidData="+androidDataJson)
				  		$('.myiframe1').on('load',function(){
			  			  var mg1=$(".myiframe1").contents().find(".health a img");
						  var mg2=$(".myiframe1").contents().find(".physical a img");
						  var mg3=$(".myiframe1").contents().find(".oldMan a img");
						  var mg4=$(".myiframe1").contents().find(".hypertension a img");
						  var mg5=$(".myiframe1").contents().find(".diabetes a img");
						  $(".myiframe1").contents().find(".diabetes").addClass('menu_blue').siblings().removeClass('menu_blue');
						  $(".myiframe1").contents().find(".diabetes").addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
						  	mg1.prop('src','/fdoctor/statics/image/dangan2.png');
							mg2.prop('src','/fdoctor/statics/image/tijian2.png');
							mg3.prop('src','/fdoctor/statics/image/laonian2.png');
							mg4.prop('src','/fdoctor/statics/image/aoxuey2.png');
							mg5.prop('src','/fdoctor/statics/image/tangjiaob1.png');
					  })
				  
				  
				  }
				
			  })
 
	   
	    
 
		</script>
		
		 
		
	</body>
</html>