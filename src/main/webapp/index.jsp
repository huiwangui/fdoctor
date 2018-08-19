<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	
<script src="${ctxStatic}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript"   src="${ctxStatic}/js/jQuery.Form.js"></script>



<form action="test/upload.do" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /><br /> 
		<input type="submit" value="添加新文件" />
</form>