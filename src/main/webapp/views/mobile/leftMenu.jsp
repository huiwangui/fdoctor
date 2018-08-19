<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
	<head>
		<meta charset="UTF-8">
		<title>列表</title>
		<link rel="stylesheet" type="text/css" href="/fdoctor/statics/css/main.css">
		<script type="text/javascript" src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
		<style type="text/css">
			
		</style>
	</head>
	<body>
		<div class="left_menu">
			<ul>
				<li class="health">
					<a href="/" id="aaa">
						<img src="/fdoctor/statics/image/dangan2.png" alt="" />
						<div class="clear"></div>
						<span>健康档案</span>
					</a>
				</li>
				<li class="physical">
					<a href="/">
						<img src="/fdoctor/statics/image/tijian2.png" alt="" />
						<div class="clear"></div>
						<span>居民体检</span>
					</a>
				</li>
				<li class="oldMan">
					<a href="/">
						<img src="/fdoctor/statics/image/laonian2.png" alt="" />
						<div class="clear"></div>
						<span>老年人管理</span>
					</a>
				</li>
				<li class="hypertension">
					<a href="/">
						<img src="/fdoctor/statics/image//aoxuey2.png" alt="" />
						<div class="clear"></div>
						<span>高血压管理</span>
					</a>
				</li>
				<li class="diabetes">
					<a href="/">
						<img src="/fdoctor/statics/image/aoxuey2.png" alt="" />
						<div class="clear"></div>
						<span>糖尿病管理</span>
					</a>
				</li>
				<li class="" style="height: 2px;width: 90%;margin: 0 auto; border-top:1px solid #96c7f0;"></li>
			</ul>
		</div>
		
		
	</body>
	<script type="text/javascript">
		
		// 移动上去之后图片替换路径;
//		
//		$(".left_menu .health").hover(function(){
//			$(this).find('img').prop('src','images/dangan1.png')
//		},function(){
//			$(this).find('img').prop('src','images/dangan2.png')
//		});
//		
//		$(".left_menu .physical").hover(function(){
//			$(this).find('img').prop('src','images/tijian1.png')
//		},function(){
//			$(this).find('img').prop('src','images/tijian2.png')
//		});
//		
//		$(".left_menu .oldMan").hover(function(){
//			$(this).find('img').prop('src','images/laonian1.png')
//		},function(){
//			$(this).find('img').prop('src','images/laonian2.png')
//		});
//		
//		$(".left_menu .diabetes").hover(function(){
//			$(this).find('img').prop('src','images/tangjiaob1.png')
//		},function(){
//			$(this).find('img').prop('src','images/tangjiaob2.png')
//		});
		
		
		
		
		// 左边的菜单高度 胃获取的文档高度;
		$(".left_menu").height($(document).height());
		
		
		//加点击事件;
			var mg1=$('.health a img');
			var mg2=$('.physical a img');
			var mg3=$('.oldMan a img');
			var mg4=$('.hypertension a img');
			var mg5=$('.diabetes a img');
			
			var androidDataJson='${androidDataMap}'
			console.debug(androidDataJson);
			
		$('.health a').on('click',function(){
			$(this).parent().addClass('menu_blue').siblings().removeClass('menu_blue');
			$(this).find('span').addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
			mg1.prop('src','/fdoctor/statics/image/dangan1.png');
			mg2.prop('src','/fdoctor/statics/image/tijian2.png');
			mg3.prop('src','/fdoctor/statics/image/laonian2.png');
			mg4.prop('src','/fdoctor/statics/image/aoxuey2.png');
			mg5.prop('src','/fdoctor/statics/image/tangjiaob2.png');
			
			
			$("#myiframe2",parent.document.body).attr("src","/fdoctor/mobile/healthRecord/healthIndex?androidData="+androidDataJson);
			//$("#myiframe2",parent.document.body).attr("src","http://www.baidu.com");
			
			return false;
		})
		
		$('.physical a').on('click',function(){
			$(this).parent().addClass('menu_blue').siblings().removeClass('menu_blue');
			$(this).find('span').addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
			mg1.prop('src','/fdoctor/statics/image/dangan2.png');
			mg2.prop('src','/fdoctor/statics/image/tijian1.png');
			mg3.prop('src','/fdoctor/statics/image/laonian2.png');
			mg4.prop('src','/fdoctor/statics/image/aoxuey2.png');
			mg5.prop('src','/fdoctor/statics/image/tangjiaob2.png');
			
			$("#myiframe2",parent.document.body).attr("src","/fdoctor/mobile/examination/toExaminationList?androidData="+androidDataJson);
			
			return false;
		});
		
		$('.oldMan a').on('click',function(){
			$(this).parent().addClass('menu_blue').siblings().removeClass('menu_blue');
			$(this).find('span').addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
			mg1.prop('src','/fdoctor/statics/image/dangan2.png');
			mg2.prop('src','/fdoctor/statics/image/tijian2.png');
			mg3.prop('src','/fdoctor/statics/image/laonian1.png');
			mg4.prop('src','/fdoctor/statics/image/aoxuey2.png');
			mg5.prop('src','/fdoctor/statics/image/tangjiaob2.png');
			$("#myiframe2",parent.document.body).attr("src","/fdoctor/mobile/examination/OldList?androidData="+androidDataJson);
			return false;
		});
		
		$('.hypertension a').on('click',function(){
			$(this).parent().addClass('menu_blue').siblings().removeClass('menu_blue');
			$(this).find('span').addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
			mg1.prop('src','/fdoctor/statics/image/dangan2.png');
			mg2.prop('src','/fdoctor/statics/image/tijian2.png');
			mg3.prop('src','/fdoctor/statics/image/laonian2.png');
			mg4.prop('src','/fdoctor/statics/image/gaoxuey1.png');
			mg5.prop('src','/fdoctor/statics/image/tangjiaob2.png');
			$("#myiframe2",parent.document.body).attr("src","/fdoctor/mobile/hypertension/queryindex?androidData="+androidDataJson);

			return false;
		});
		$('.diabetes a').on('click',function(){
			$(this).parent().addClass('menu_blue').siblings().removeClass('menu_blue');
			$(this).find('span').addClass('span_blue').end().parent().siblings().find('span').removeClass('span_blue');
			mg1.prop('src','/fdoctor/statics/image/dangan2.png');
			mg2.prop('src','/fdoctor/statics/image/tijian2.png');
			mg3.prop('src','/fdoctor/statics/image/laonian2.png');
			mg4.prop('src','/fdoctor/statics/image/aoxuey2.png');
			mg5.prop('src','/fdoctor/statics/image/tangjiaob1.png');
			$("#myiframe2",parent.document.body).attr("src","/fdoctor/mobile/diabetes/queryindex?androidData="+androidDataJson);

			return false;
		});
		
		
		
		
	</script>
</html>