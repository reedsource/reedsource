<%--User: reedsource Date: 2020/9/22 Time: 14:57--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.net.*" %>
<%
	// 编码，解决中文乱码
	// cookie
	String setCookieName = URLEncoder.encode(request.getParameter("setCookieName"), "utf-8");
	// cookie值
	String setCookieValue = URLEncoder.encode(request.getParameter("setCookieValue"), "utf-8");

	// 设置 name 和 url cookie
	Cookie setCookie = new Cookie(setCookieName, setCookieValue);

	// 设置cookie过期时间为24小时。
	setCookie.setMaxAge(60 * 60 * 24);

	// 在响应头部添加cookie
	response.addCookie(setCookie);
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>设置cookie值</title>
</head>
<body>
<h1>设置 Cookie</h1>

<ul>
	<li><p><b>添加的cookie名称:</b>
		<%= setCookieName%>
	</p></li>
	<li><p><b>添加的cookie值:</b>
		<%= setCookieValue%>
	</p></li>
</ul>
</body>
</html>