<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/top.jsp"%>
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
	

<%@ include file="/common/bottom.jsp"%>