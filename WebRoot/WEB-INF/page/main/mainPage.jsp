<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>My JSP 'main.jsp' starting page</title>
<style type="text/css">
table {
	font-family: "宋体";
	width: 400px;
	border-collapse: collapse;
}

table td {
	font-size: 16px;
	border: 1px solid #000000;
	padding: 3px 7px 2px 7px;
}

.t1 {
	font-size: 20px;
	text-align: center;
	font-weight: bold;
}
</style>
<script language="javascript">
	function addRow() {
		addElement = document
				.createElement("<input type='file' name='upload' size='30'/>");
		document.getElementById("pos").appendChild(addElement);
	}
</script>

</head>

<body>
	Main page.
	<br>
	<div class="t1">请您选择文件，进行上传</div>
		<br />				
	<form action="" method="post" enctype="multipart/form-data">

		<table align="center">		 	
			<tr>
				<td width="35%">选择文件：</td>
				<td><span id="pos">
					  <input type="file" name="upload" size="30" />
					</span>				
				</td>
			</tr>
			<tr>
				<td><input type="button" onclick="addRow();" value="增加文件" /></td>
				<td><input type="submit" value="上传" /></td>
			</tr>
		</table>
	</form>
	

</body>
</html>
