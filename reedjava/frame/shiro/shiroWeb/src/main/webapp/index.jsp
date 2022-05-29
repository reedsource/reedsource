<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
 
<head>   
	 <title>shiro学习教程</title>
</head>
<body>
</body>
<h2 style="border: red;width:70%;margin:0 auto;text-align: center">shiro学习教程</h2>
<table style=" border-style: solid; width:70%;margin:0 auto">
	<caption></caption>
	<tr>
		<th id="#">编号</th>
		<th id="#">界面</th>
		<th id="#">界面说明</th>
	</tr>
	<tr>
		<td>1</td>
		<td><a href="${pageContext.request.contextPath}/user/userLogin.do" target="main">登陆页</a></td>
		<td></td>
	</tr>
	<tr>
		<td>2</td>
		<td><a href="${pageContext.request.contextPath}/user/error.do" target="main">错误页</a></td>
		<td></td>
	</tr>
	<tr>
		<td>3</td>
		<td><a href="${pageContext.request.contextPath}/user/userList.do" target="main">人员列表页</a></td>
		<td>访问时,要求用户必须已经通过登陆验证,请先进入登陆页登陆</td>
	</tr>
	<tr>
		<td>4</td>
		<td><a href="${pageContext.request.contextPath}/user/shiro.do" target="main">登陆信息</a></td>
		<td>未登陆将显示游客及登陆 同时显示shiro相关的标签信息 登录前和登录后展示不同</td>
	</tr>
	<tr>
		<td>5</td>
		<td><a href="${pageContext.request.contextPath}/user/logout.do" target="main">注销</a></td>
		<td>注销当前登录信息</td>
	</tr>
</table>
</html>
