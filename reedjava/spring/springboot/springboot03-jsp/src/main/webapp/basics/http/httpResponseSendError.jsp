<%--User: reedsource Date: 2020/9/20 Time: 22:46--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>自定义返回状态码</title>
</head>
<body>
<h2>自定义返回状态码</h2>
<%
	// 设置错误代码，并说明原因
	response.sendError(650, "需要身份验证!!!");
%>
</body>
</html>