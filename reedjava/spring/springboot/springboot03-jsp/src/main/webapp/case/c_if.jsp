<%--
  User: reedbook
  Date: 2021-01-21
  Time: 9:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Title</title>
</head>
<body>

<%--如果为空显示---%>
<jsp:useBean id="msg" scope="request"/>
<c:if test="${empty msg or msg == '' }">为空显示</c:if>
<c:if test="${not empty msg and msg != '' }">不为空显示</c:if>

</body>
</html>
