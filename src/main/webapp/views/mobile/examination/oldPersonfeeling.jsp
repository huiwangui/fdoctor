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
				老年感情状态量表测试 <a >X</a>
			</div>
			<div class="content">
				<table width="100%" border="0" class="EditPanel">
					<tbody>
					<tr>
						<td colspan="4" class="HighlightText1">老年抑郁量表&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">1. 你对你的生活基本满意吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_1" value="1" _note="1.  你对你的生活基本满意吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_1" value="0" _note="1.  你对你的生活基本满意吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">2. 你是否丧失了很多你的兴趣和爱好？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_2" value="1" _note="2.  你是否丧失了很多你的兴趣和爱好？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_2" value="0" _note="2.  你是否丧失了很多你的兴趣和爱好？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">3. 你感到生活很空虚吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_3" value="1" _note="3.  你感到生活很空虚吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_3" value="0" _note="3.  你感到生活很空虚吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">4. 你经常感到很无聊吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_4" value="1" _note="4.  你经常感到很无聊吗|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_4" value="0" _note="4.  你经常感到很无聊吗|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">5. 你对未来充满希望吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_5" value="1" _note="5.  你对未来充满希望吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_5" value="0" _note="5.  你对未来充满希望吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">6. 你是否感到烦恼无法摆脱头脑中的想法？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_6" value="1" _note="6.  你是否感到烦恼无法摆脱头脑中的想法？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_6" value="0" _note="6.  你是否感到烦恼无法摆脱头脑中的想法？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">7. 大部分时间你都精神抖擞吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_7" value="1" _note="7.  大部分时间你都精神抖擞吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_7" value="0" _note="7.  大部分时间你都精神抖擞吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">8. 你是否觉得有什么不好的事情要发生而感到很害怕？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_8" value="1" _note="8.  你是否觉得有什么不好的事情要发生而感到很害怕？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_8" value="0" _note="8.  你是否觉得有什么不好的事情要发生而感到很害怕？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">9. 大部分时间你都觉得快乐吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_9" value="1" _note="9.  大部分时间你都觉得快乐吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_9" value="0" _note="9.  大部分时间你都觉得快乐吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">10. 你经常感到无助吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_10" value="1" _note="10. 你经常感到无助吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_10" value="0" _note="10. 你经常感到无助吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">11. 你是否经常感到不安宁或坐立不安？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_11" value="1" _note="11. 你是否经常感到不安宁或坐立不安？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_11" value="0" _note="11. 你是否经常感到不安宁或坐立不安？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">12. 你是否宁愿呆在家里而不愿出去干新鲜事？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_12" value="1" _note="12. 你是否宁愿呆在家里而不愿出去干新鲜事？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_12" value="0" _note="12. 你是否宁愿呆在家里而不愿出去干新鲜事？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">13. 你是否经常担心未来 </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_13" value="1" _note="13. 你是否经常担心未来|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_13" value="0" _note="13. 你是否经常担心未来|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">14. 你是否觉得你的记忆力有问题？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_14" value="1" _note="14. 你是否觉得你的记忆力有问题？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_14" value="0" _note="14. 你是否觉得你的记忆力有问题？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">15. 你是否觉得现在活着很精彩？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_15" value="1" _note="15. 你是否觉得现在活着很精彩？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_15" value="0" _note="15. 你是否觉得现在活着很精彩？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">16. 你是否经常感到垂头丧气无精打采? </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_16" value="1" _note="16. 你是否经常感到垂头丧气无精打采|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_16" value="0" _note="16. 你是否经常感到垂头丧气无精打采|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">17. 你是否感到你现在很没用？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_17" value="1" _note="17. 你是否感到你现在很没用？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_17" value="0" _note="17. 你是否感到你现在很没用？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">18. 你是否为过去的事担心很多？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_18" value="1" _note="18. 你是否为过去的事担心很多？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_18" value="0" _note="18. 你是否为过去的事担心很多？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">19. 你觉得生活很兴奋吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_19" value="1" _note="19. 你觉得生活很兴奋吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_19" value="0" _note="19. 你觉得生活很兴奋吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">20. 你是否觉得学习新鲜事物很困难吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_20" value="1" _note="20. 你是否觉得学习新鲜事物很困难吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_20" value="0" _note="20. 你是否觉得学习新鲜事物很困难吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">21. 你觉得精力充沛吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_21" value="1" _note="21. 你觉得精力充沛吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_21" value="0" _note="21. 你觉得精力充沛吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">22. 你觉得你的现状是毫无希望的吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_22" value="1" _note="22. 你觉得你的现状是毫无希望的吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_22" value="0" _note="22. 你觉得你的现状是毫无希望的吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">23. 你是否觉得大部分人都比你活得好？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_23" value="1" _note="23. 你是否觉得大部分人都比你活得好？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_23" value="0" _note="23. 你是否觉得大部分人都比你活得好？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">24. 你是否经常把小事情都弄得很糟糕？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_24" value="1" _note="24. 你是否经常把小事情都弄得很糟糕？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_24" value="0" _note="24. 你是否经常把小事情都弄得很糟糕？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">25. 你经常有想哭的感觉吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_25" value="1" _note="25. 你经常有想哭的感觉吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_25" value="0" _note="25. 你经常有想哭的感觉吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">26. 你对集中注意力有困难吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_26" value="1" _note="26. 你对集中注意力有困难吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_26" value="0" _note="26. 你对集中注意力有困难吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">27. 你喜欢每天早晨起床的感觉吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_27" value="1" _note="27. 你喜欢每天早晨起床的感觉吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_27" value="0" _note="27. 你喜欢每天早晨起床的感觉吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">28. 你是否宁愿不参加社交活动？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_28" value="1" _note="28. 你是否宁愿不参加社交活动？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_28" value="0" _note="28. 你是否宁愿不参加社交活动？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">29. 你做决定容易吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_29" value="1" _note="29. 你做决定容易吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_29" value="0" _note="29. 你做决定容易吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" class="header">30. 你的头脑还和以前一样清楚吗？ </td>
					</tr>
					<tr>
						<td colspan="4">
							<label>
								<input type="radio" name="ds_30" value="1" _note="30. 你的头脑还和以前一样清楚吗？|radio|1=>是,2=>否">是</label>
							<label>
								<input type="radio" name="ds_30" value="0" _note="30. 你的头脑还和以前一样清楚吗？|radio|1=>是,2=>否">否</label>
							&nbsp; </td>
					</tr>
					<tr>
						<td colspan="4" align="center" class="endbg">
							<input type="submit" value=" 计算结果" onclick="CalReslut();">
					</td></tr>
				</tbody></table>
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
	
				parent.$("#EmotionalStateScore").val(sum);
				var index = parent.layer.getFrameIndex(window.name);
				console.log(index)
				parent.layer.close(index);
				return sum
			}
		</script>
	</body>
</html>
