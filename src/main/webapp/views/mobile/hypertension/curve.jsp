<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>高血压血压曲线</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/echarts.min.js" ></script>
</head>


<body>
	<div class="main">
		<!--	搜索开始-->
		<div class="chartSearch">
				选择时间： <input type="date" name="StartTime" id="StartTime"  class="borderb"/> <span>&nbsp;</span><input type="date" name="EndTime" id="EndTime"  class="borderb"/>
			    <input type="hidden" name="PersonID" id="PersonID" value="${personid }" class="borderb"/>
				 <a  id="search" class="sibtn_small">查询</a> 
				<!--  <input  id="search" class="searchp" >查询</input>  -->
	 
		</div>
		<!--	搜索结束-->
		
		<!--血压曲线开始-->
		<div class="" id="charts" style="width:100%;height: 500px; margin-top: 20px;"></div>
		<!--血压曲线结束-->
		
	</div>
</body>
<script type="text/javascript">
	var myChart = echarts.init(document.getElementById('charts'));
	var option = {
	   title: {
	       text: '血压曲线图',
	       left: 'center'
	   },
	   tooltip: {
	       trigger: 'item',
	   },
	   legend: {
	       left: 'left',
	       data: ['舒张压', '收缩压']
	   },
	   xAxis: {
	       type: 'category',
	       name: 'x',
	       splitLine: {show: false},
	       data: ['一', '二', '三', '四', '五', '六', '七']
	   },
	   grid: {
	       left: '3%',
	       right: '4%',
	       bottom: '3%',
	       containLabel: true
	   },
	   yAxis: {
	       type: 'log',
	       name: 'y'
	   },
	   series: [
	       {
	           name: '舒张压',
	           type: 'line',
	   /*    data: [1, 3, 9, 27, 81, 247, 741, 2223, 6669] */ 
	           data: []   
	       },
	       {
	           name: '收缩压',
	           type: 'line',
	    /*       data: [1, 2, 4, 8, 16, 32, 64, 128, 256]  */
	             data: []  
	       }
	   ]
	};
	myChart.setOption(option);

	$("#search").click(function(){
		 
		 $.ajax({
			   type: "get",
			   url: "/fdoctor/mobile/hypertension/queryHypertensionCurve",
			   data: 'StartTime='+$("#StartTime").val()+'&EndTime='+$("#EndTime").val()+'&PersonID='+$("#PersonID").val()+'&ProductCode='+'${ProductCode}',
			   datatype:'json',
			   success: function(msg){
			     var data =eval(msg);
			     console.log(data);
			     var sbps=[];
		    	 var dbps=[];
			     console.log(data[0].key);
		    	/*  if(data[0].key=="key"){
		    		 
		    	 } */
		    	 if(data[0].key!="key"){
		    		 for(var i=0;i<data.length;i++){
			    		 sbps.push(data[i]['Sbp']);
			    		 dbps.push(data[i]['Dbp']);
			    	 }
		    	 }
			    	
			    	 
			      
			     option.series[0].data = sbps;
			     option.series[1].data = dbps;
			     myChart.setOption(option);
			   }
			});
			
		 
	});

	
	
	
</script>
</html>
