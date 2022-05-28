<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Title</title>
</head>
<body>
<a href="../jsp/j00jsp.jsp">jsp基础实现及原理</a><br>
我是错误处理页,错误的界面都会到我这
<%
	String msg = exception.getMessage();
%>
<br/>
错误信息为:<%=msg%>
</body>
</html>
