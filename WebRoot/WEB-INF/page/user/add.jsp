<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/top.jsp"%>

<%-- <script type="text/javascript">
function loadXMLDoc()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
  	{// code for IE7+, Firefox, Chrome, Opera, Safari
  		xmlhttp=new XMLHttpRequest();
  	}
	else
  	{// code for IE6, IE5
  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}
	xmlhttp.onreadystatechange=function()
  	{
  		if (xmlhttp.readyState==4 && xmlhttp.status==200)
    	{
    		document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    	}
  	}
		xmlhttp.open("POST","<%=request.getContextPath()%>/ajax/ajaxresource.jsp",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("fname=Bill&lname=Gates");
	}
</script> --%>
	<div onmouseover="mOver(this)" onmouseout="mOut(this)"
		style="background-color:green;width:120px;height:20px;padding:40px;color:#ffffff;">把鼠标移到上面</div>

	<form action="<%=request.getContextPath()%>/user/addpic.do" method="POST" enctype="multipart/form-data">  
    <table align="center">	
    <tr><td width="35%">username: </td> <td><input type="text" name="username"/></td>
    <tr><td width="35%">nickname: </td> <td><input type="text" name="nickname"/></td>
    <tr><td width="35%">password: </td> <td><input type="password" name="password"/></td>
    <tr><td width="35%">yourmail: </td> <td><input type="text" name="email"/></td>
    <tr><td><input id="ad" type="button" onclick="addRow()" value="增加文件" /></td>
    	<td><input type="submit" value="添加新用户"/></td>
    </tr>
    <tr><td width="35%">yourfile: </td> <td><span id="pos"><input type="file" name="myfiles"/></span></td></tr>
    
    </table>
	</form>

	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/myScript.js"></script>
</body>
</html>
