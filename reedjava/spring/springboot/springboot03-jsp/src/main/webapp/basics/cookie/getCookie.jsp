<%@ page import="java.net.URLDecoder" %><%--User: reedsource Date: 2020/9/22 Time: 15:13--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>读取Cookie</title>
</head>
<body>
<%
	Cookie cookie;
	Cookie[] cookies;
	// 获取 cookies 的数据,是一个数组
	cookies = request.getCookies();
	if (cookies != null) {
		out.println("<h2> 查找 Cookie 名与值</h2>");
		for (Cookie cookie1 : cookies) {
			cookie = cookie1;
			out.print("参数名 : " + cookie.getName());
			out.print("<br>");
			out.print("参数值: " + URLDecoder.decode(cookie.getValue(), "utf-8") + " <br>");
			out.print("------------------------------------<br>");
		}
	} else {
		out.println("<h2>没有发现 Cookie</h2>");
	}
%>
</body>
</html>