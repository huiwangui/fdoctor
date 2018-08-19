<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../../include/taglib.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css"
	href="/fdoctor/statics/css/main.css">
<link rel="stylesheet" type="text/css"
	href="/fdoctor/statics/css/easyui.css">
<!--引入CSS样式-->

<link rel="stylesheet" type="text/css"
	href="/fdoctor/statics/css/icon.css">
<!--Icon引入-->

<script type="text/javascript"
	src="/fdoctor/statics/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="/fdoctor/statics/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="/fdoctor/statics/layui/css/layui.css" />
<script type="text/javascript" src="/fdoctor/statics/layui/layui.js"></script>
	</head>
	<body>
		<div class="dialog_box">
			<div class="header">
				老年人认知功能测试表 <a href="/">X</a>
			</div>
			<div class="content">
				<table border="0" class="EditPanel" style="">
					<tbody><tr>
						<td colspan="5" class="header">1. 时间定向力 *注 日期和星期差一天可算正确。 </td>
					</tr>
					<tr>
						<td colspan="5">问：今天是？哪一年？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_1_1" value="1" _note="今天是？哪一年|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_1_1" value="0" _note="今天是？哪一年|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：今天是？季节？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_1_2" value="1" _note="今天是？季节|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_1_2" value="0" _note="今天是？季节|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：今天是？月份？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_1_3" value="1" _note="今天是？月份|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_1_3" value="0" _note="今天是？月份|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：今天是？日期？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_1_4" value="1" _note="今天是？日期|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_1_4" value="0" _note="今天是？日期|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：今天是？星期几？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_1_5" value="1" _note="今天是？星期几|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_1_5" value="0" _note="今天是？星期几|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">2. 地点定向力 </td>
					</tr>
					<tr>
						<td colspan="5">问：我们现在在哪里？ 国家？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_2_1" value="1" _note="问：我们现在在哪里？ 国家|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_2_1" value="0" _note="问：我们现在在哪里？ 国家|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：我们现在在哪里？ 城市？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_2_2" value="1" _note="问：我们现在在哪里？ 城市|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_2_2" value="0" _note="问：我们现在在哪里？ 城市|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：我们现在在哪里？ 城市的哪一部分？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_2_3" value="1" _note="问：我们现在在哪里？ 城市的哪一部分|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_2_3" value="0" _note="问：我们现在在哪里？ 城市的哪一部分|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：我们现在在哪里？ 建筑物？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_2_4" value="1" _note="问：我们现在在哪里？ 建筑物|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_2_4" value="0" _note="问：我们现在在哪里？ 建筑物|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：我们现在在哪里？ 建筑物？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_2_5" value="1" _note="问：我们现在在哪里？ 第几层|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_2_5" value="0" _note="问：我们现在在哪里？ 第几层|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">3. 即刻回忆 说：仔细听。我要说三个词，请在我说完以后重复。准备好了吗？ </td>
					</tr>
					<tr>
						<td colspan="5">球（停一秒钟） </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_3_1" value="1" _note="说：仔细听。我要说三个词，请在我说完以后重复。准备好了吗？三个词是：球（停一秒钟）|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_3_1" value="0" _note="说：仔细听。我要说三个词，请在我说完以后重复。准备好了吗？三个词是：球（停一秒钟）|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">旗子（停一秒钟） </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_3_2" value="1" _note="说：仔细听。我要说三个词，请在我说完以后重复。准备好了吗？三个词是：旗子（停一秒钟）|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_3_2" value="0" _note="说：仔细听。我要说三个词，请在我说完以后重复。准备好了吗？三个词是：旗子（停一秒钟）|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">树（停一秒钟） </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_3_3" value="1" _note="说：仔细听。我要说三个词，请在我说完以后重复。准备好了吗？三个词是：树（停一秒钟）|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_3_3" value="0" _note="说：仔细听。我要说三个词，请在我说完以后重复。准备好了吗？三个词是：树（停一秒钟）|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">4. 注意力与计算力 问：从100减去7，顺序往下减，直至我让你停止。* 注 不能用笔算 </td>
					</tr>
					<tr>
						<td colspan="5">100减7等于？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_4_1" value="1" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_4_1" value="0" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">93减7等于？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_4_2" value="1" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_4_2" value="0" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">86减7等于？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_4_3" value="1" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_4_3" value="0" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">79减7等于？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_4_4" value="1" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_4_4" value="0" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">72减7等于？ </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_4_5" value="1" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_4_5" value="0" _note="4. 注意力与计算力问：从100减去7，顺序往下减，直至我让你停止。100减7等于|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">5. 回忆那三个词问：我刚才让你记住的三个词是什么？ </td>
					</tr>
					<tr>
						<td colspan="5">球 </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_5_1" value="1" _note="5. 回忆那三个词问：我刚才让你记住的三个词是什么？|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_5_1" value="0" _note="5. 回忆那三个词问：我刚才让你记住的三个词是什么？|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">旗子 </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_5_2" value="1" _note="5. 回忆那三个词问：我刚才让你记住的三个词是什么？|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_5_2" value="0" _note="5. 回忆那三个词问：我刚才让你记住的三个词是什么？|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">树 </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_5_3" value="1" _note="5. 回忆那三个词问：我刚才让你记住的三个词是什么？|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_5_3" value="0" _note="5. 回忆那三个词问：我刚才让你记住的三个词是什么？|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">6. 命名 </td>
					</tr>
					<tr>
						<td colspan="5">问：这是什么？（展示铅笔） </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_6_1" value="1" _note="6. 命名问：这是什么？（展示铅笔）|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_6_1" value="0" _note="6. 命名问：这是什么？（展示铅笔）|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">问：这是什么？（展示手表） </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_6_2" value="1" _note="6. 命名问：这是什么？（展示手表）|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_6_2" value="0" _note="6. 命名问：这是什么？（展示手表）|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">7. 语言重复 *注 只许说一遍，只有正确、咬字清楚 </td>
					</tr>
					<tr>
						<td colspan="5">说：我现在让你重复我说的话。准备好了吗？"瑞雪兆丰年" </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_7_1" value="1" _note="7. 语言重复说：我现在让你重复我说的话。准备好了吗？瑞雪兆丰年|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_7_1" value="0" _note="7. 语言重复说：我现在让你重复我说的话。准备好了吗？瑞雪兆丰年|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">8. 理解力 *注 操作要求次序正确 </td>
					</tr>
					<tr>
						<td colspan="5">说：仔细听并按照我说的做。左手拿着这张纸 </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_8_1" value="1" _note="8. 理解力说：仔细听并按照我说的做。左手拿着这张纸|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_8_1" value="0" _note="8. 理解力说：仔细听并按照我说的做。左手拿着这张纸|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">说：仔细听并按照我说的做。把它对折 </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_8_2" value="1" _note="8. 理解力说：仔细听并按照我说的做。把它对折|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_8_2" value="0" _note="8. 理解力说：仔细听并按照我说的做。把它对折|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5">说：仔细听并按照我说的做。把它放在您的右腿上 </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_8_3" value="1" _note="8. 理解力说：仔细听并按照我说的做。把它放在您的右腿上|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_8_3" value="0" _note="8. 理解力说：仔细听并按照我说的做。把它放在您的右腿上|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">9. 阅读 </td>
					</tr>
					<tr>
						<td colspan="5">说：读下面的句子，并按照做。 "闭上你的眼睛" </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_9_1" value="1" _note="9. 阅读说：读下面的句子，并按照做。（1）  闭上你的眼睛。|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_9_1" value="0" _note="9. 阅读说：读下面的句子，并按照做。（1）  闭上你的眼睛。|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">10. 写 *注 句子必须有主语、谓语，且有意义 </td>
					</tr>
					<tr>
						<td colspan="5">说：写一个句子。 </td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_10_1" value="1" _note="10. 写说：写一个句子。|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_10_1" value="0" _note="10. 写说：写一个句子。|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" class="header">11. 画画 *注 只有绘出两个五边形的图案，交叉处形成1个小四边形，才算对 </td>
					</tr>
					<tr>
						<td colspan="5">说：照右图画。<img src="images/mmse.jpg">
						</td>
					</tr>
					<tr>
						<td colspan="5">
							<label>
								<input type="radio" name="mmse_11_1" value="1" _note="11. 画画说：照下图画。|radio|1=>正确,0=>错误">正确</label>
							<label>
								<input type="radio" name="mmse_11_1" value="0" _note="11. 画画说：照下图画。|radio|1=>正确,0=>错误">错误</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="5" align="center" class="endbg">
							<input type="button" value=" 计算结果" onclick="CalReslut();" size="15">
					</td></tr>
				</tbody>
				
				</table>
			</div>
		</div>
		<script type="text/javascript">
			$('.header a').on('click',function(){
				$(this).parents('.dialog_box').hide();
				return false;
			})
			
		//计算结果
		 CalReslut=function(){
				var sum=0;
				var checkRadios=$('input:radio:checked');
				checkRadios.each(function(){
					if($(this).val()==1){
						var num=parseInt($(this).val())
						sum=sum+num
					}
				})
	
				parent.$("#CognitiveFunctionScore").val(sum);
				var index = parent.layer.getFrameIndex(window.name);
				console.log(index)
				parent.layer.close(index);
				return sum
			}
		 
		 
	
		</script>
	</body>
		
		
			
		
	
</html>
