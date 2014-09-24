<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>

  </head>
  
  <body>
    This is my JSP page. <br>
    <form action="<%=request.getContextPath()%>/user/add" method="POST" enctype="multipart/form-data">  
    username: <input type="text" name="username"/><br/>  
    nickname: <input type="text" name="nickname"/><br/>  
    password: <input type="password" name="password"/><br/>  
    yourmail: <input type="text" name="email"/><br/>  
    yourfile: <input type="file" name="myfiles"/><br/>  
    yourfile: <input type="file" name="myfiles"/><br/>  
    yourfile: <input type="file" name="myfiles"/><br/>  
    <input type="submit" value="添加新用户"/>  
</form>  
  </body>
</html>
