<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>新建转诊单记录</title>
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/easyui.css"> <!--引入CSS样式-->	
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/icon.css"> <!--Icon引入-->
	</head>
	<body>
		<h1 class="tc" style="border-bottom: 1px dashed #333;">双向转诊单 </h1>
		<h3 class="tc">存根</h3>
		<form action="" method="" class="mt20 transfer_form">
			<div class="clearfix">
				<label>
					患者姓名 ： <input type="text" name="personName" id="personName" class="borderb"/>
				</label>
				<label>
					性别 ： <input type="text" name="personGender"   value=""  class="borderb"/>
				</label>
				<label>
					年龄 ： <input type="text" name="personAge" value="" class="borderb"/>
				</label>
				<label>
					档案编号 ： <input type="text" name="personCode" id="personCode" value="" class="borderb"/>
				</label>
				<label>
					家庭住址 ： <input type="text" name="ResidenceAddress" id="ResidenceAddress" value="" class="borderb"/>
				</label>
				<label>
					联系电话 ： <input type="text" name="personTel" id="personTel" value="" class="borderb"/>
				</label>
				<label>
					于 <input type="text" name="syear" id="syear" value="" class="w40 borderb"/>年
					 <input type="text" name="smonth" id="smonth" value="" class="w20 borderb"/>月
					  <input type="text" name="sday" id="sday" value="" class="w20 borderb"/>日
				</label>
				因病情需要，转入
				<label>
					 <input type="text" name="shosp" id="shosp"  class="borderb"/>单位
				</label>
				<label>
					 <input type="text" name="" id="" value="" class="borderb"/>科室
				</label>
				<label>
					 <input type="text" name="" id="" value="" class="borderb"/>接诊医生
				</label>
			</div>
			<div class="clearfix">
				<label class="fr">
					转诊医生(签字)： <input type="text" name="sdoctor" id="sdoctor" value="" class="borderb"/>
				</label>
			</div>
			<div class="clearfix">
				<label class="fr">
					<input type="date" name="" id="" value="" class="borderb"/>
				</label>
			</div>
		</form>
		
		<h1 class="tc" style="border-bottom: 1px dashed #333;">双向转诊(转出)单</h1>
		<form action="" class="transfer_form">
			<div class="">
				<label>
					<input type="text" name="ahosp" id=""ahosp value="" class="borderb"/>(机构名称)：
				</label>
			</div>
			<div class="">
				<label style="text-indent: 2em;">
					现有患者 ： <input type="text" name="personName" id="" value=""  class="borderb"/>
				</label>
				<label>
					性别 ： <input type="text" name="personGender" id="" value=""  class="borderb"/>
				</label>
				<label>
					年龄 ： <input type="text" name="personAge" id="" value="" class="borderb"/>
				</label>
				因病情需要，需转入贵单位，请予以接诊。
				<div class="">
					<label>
						初步印象： 
						<textarea name="" rows="" cols="100"></textarea>
					</label>
				</div>
				<div class="">
					<label>
						主要现病史（转出原因）：
						<textarea name="sreason" id="sreason" rows="" cols="100"></textarea>
					</label>
				</div>
				<div class="">
					<label>
						主要既往史：
						<textarea name="" rows="" cols="100"></textarea>
					</label>
				</div>
				<div class="">
					<label>
						治疗经过：
						<textarea name="" rows="" cols="100"></textarea>
					</label>
				</div>
			</div>
			<div class="clearfix">
				<label class="fr">
					转诊医生（签字）： <input type="text" name="adoctor" id="adoctor" value="" class="borderb"/>
				</label>
			</div>
			<div class="clearfix">
				<label class="fr">
					联系电话： <input type="text" name="" id="" value="" class="borderb"/>
				</label>
			</div>
			<div class="clearfix">
				<label class="fr">
					<input type="date" name="" id="" value="" class="borderb"/>
				</label>
			</div>
		</form>
		<div class="operation tc">
			<a id="save" href="javascript:void(0)" class="sibtn">保存</a>
		</div>
	</body>
<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/fdoctor/statics/js/layer.js" ></script>
<script type="text/javascript">
$(function(){
	var index=parent.layer.getFrameIndex(window.name);
	$("#save").on('click',function(){
		 var shosp =$("#shosp").val();//传出医院
		 var sreason = $("#sreason").val();	 //转出原因
		 var person ={};
		 person.shosp=shosp;
		 person.sreason=sreason;
		 parent.aparam(person);
		
		 parent.layer.close(index);//关闭子页面
			
		 
	});
	function UrlSearch() 
	{
	   var name,value; 
	   var str=location.href; //取得整个地址栏
	  
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
	var param=new UrlSearch(); //实例化
	 $("input[name='personName']").attr("value", decodeURI(param.Name));
	 $("input[name='personGender']").attr("value", decodeURI(param.Gender));
	 $("input[name='personAge']").attr("value", decodeURI(param.age));
	 $("#ResidenceAddress").val(decodeURI(param.ResidenceAddress));
	 $("#personTel").val(decodeURI(param.PersonTel));
	// $("#personName").val(decodeURI(param.Name))
	 $("#personCode").val(param.PersonCode)
	 
})

</script>
	
</html>
