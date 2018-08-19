<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>糖尿病曲线</title>
<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/echarts.min.js" ></script>
</head>
<style type="text/css">
	body{
		background: #f0f3f8;
	}
	
</style>


<body>
	<div class="main">
		<!--	搜索开始-->
		<div class="chartSearch">
			<form action="">
				选择时间： <input type="date" name="StartTime" id="StartTime" value="" class="borderb"/> <span>&nbsp;</span><input type="date" name="EndTime" id="EndTime" value="" class="borderb"/>
				<input type="hidden" id="PersonID" name="PersonID" value="${personid}" class="borderb">
				<a id="search" class="sibtn_small" href="javascript:void(0)"> 查询</a>
				
			</form>
		</div>
		<!--	搜索结束-->
		
		<!--血压曲线开始-->
		<div class="" id="charts" style="width:100%;height: 500px; margin-top: 20px;"></div>
		<!--血压曲线结束-->
		
	</div>
</body>
<script type="text/javascript">
 var myChart = echarts.init(document.getElementById('charts'));
		option = {
		    title: {
		        text: '空腹血糖曲线图',
		        left: 'center'
		    },
		    tooltip: {
		        trigger: 'item',
		    },
		    legend: {
		        left: 'left',
		        data: ['空腹血糖', '餐后血糖']
		    },
		    xAxis: {
		        type: 'category',
		        name: '日期',
		        splitLine: {show: false},
		        data:[]
		        //data: ['一', '二', '三', '四', '五', '六', '七']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    yAxis: {
		        type: 'log',
		        name: '血糖（mmol/L）'
		    },
		    series: [
		        {
		            name: '空腹血糖',
		            type: 'line',
		            data: []
		        },
		        {
		            name: '餐后血糖',
		            type: 'line',
		            data: []
		        },
		    ]
		};
	 myChart.setOption(option);
	
$("#search").click(function(){
		 
		 $.ajax({
			   type: "get",
			   url: "/fdoctor/mobile/diabetes/queryDiabetesCurve",
			   data: 'StartTime='+$("#StartTime").val()+'&EndTime='+$("#EndTime").val()+'&PersonID='+$("#PersonID").val()+'&doctorName='+'${doctorName}',
			   datatype:'json',
			   success: function(msg){
			     var data =eval(msg);
			     console.log(data);
			     var datas=[];
			     var dates=[];
			     //console.log(data[0].key);
		    	 if(data[0].key!="key"){
		    		 if(data.length>7){
		    			 for(var i=data.length-7;i<data.length;i++){
				    		 datas.push(data[i]['Data']);
				    		 dates.push(data[i]['Date'])
				    	 }
		    		 }
		    		 else{
		    			 for(var i=0;i<data.length;i++){
				    		 datas.push(data[i]['Data']);
				    		 dates.push(data[i]['Date'])
				    	 }
		    		 }
		    		 
		    	 }
			    	
			    	 
			      
			     option.series[0].data = datas;
			     option.xAxis.data=dates;
			     myChart.setOption(option);
			   }
			});
			
		 
	});
	
</script>
</html>
