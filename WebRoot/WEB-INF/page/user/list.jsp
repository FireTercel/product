<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <base href="<%=basePath%>">
    <title>My JSP 'list.jsp' starting page</title>

  </head>
  
  <body>
    <c:forEach items="${persons}" var="person">  
    <%-- ${person.value.username}----${person.value.nickname}----${person.value.password}----${person.value.email}   --%>
    ${person.value.username}</br>
    ${person.value.nickname}</br>
    ${person.value.password}</br>
    ${person.value.email} </br> 
        <a href="<%=request.getContextPath()%>/user/${person.value.username}">查看</a>  
        <a href="<%=request.getContextPath()%>/user/${person.value.username}/update">编辑</a>  
        <a href="<%=request.getContextPath()%>/user/${person.value.username}/delete">删除</a>  
    <br/>  
	</c:forEach>  
	<br/>  
		<a href="<%=request.getContextPath()%>/user/add">继续添加用户</a> 
		<img alt="上海鲜花港 - 郁金香" src="upload/1411515357892.jpeg">
		
		
  </body>
</html>
