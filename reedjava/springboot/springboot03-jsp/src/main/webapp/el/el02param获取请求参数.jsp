<%@ page import="top.ireed.deal.DealLog" %>
<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<%
	DealLog.log("一般接收方式");
	String str = request.getParameter("str1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

一般接收方式<br>
str1=<%=str%>
<hr>

el表达式的方式<br>
采用隐含对象param,获取的是参数值<br>
${param.str1}
<hr>

paramValues获取请求参数数组,搭配jstl迭代来使用<br>
${paramValues.str1[1]}<br>
${paramValues.str1[2]}<br>
${paramValues.str1[3]}<br>

<hr>
initParam:获取全局参数
${initParam.encoding}

<br>
<hr>
<a href="/EL01">
	el表达式主界面
</a>
</body>
</html>