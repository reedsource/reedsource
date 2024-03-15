<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="000errorPage.jsp" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Title</title>
</head>
<body>
<a href="../jsp/j00jsp.jsp">jsp基础实现及原理</a><br>
<%
	String str1 = (String) pageContext.getAttribute("str1");
	String str2 = (String) request.getAttribute("str2");
	String str3 = (String) session.getAttribute("str3");
	String str4 = (String) application.getAttribute("str4");

%>
我将看到那些jsp有那些域可以被读取<br>
str1:<%=str1%><br/>
str2:<%=str2%><br/>
str3:<%=str3%><br/>
str4:<%=str4%><br/>
</body>
</html>
