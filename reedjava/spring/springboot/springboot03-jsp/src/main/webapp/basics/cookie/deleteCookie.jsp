<%@ page import="java.net.URLDecoder" %><%--User: reedsource Date: 2020/9/22 Time: 15:23--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>删除cookie</title>
</head>
<body>
<%
	String cookie_Name = request.getParameter("cookieName");
%>
<ul>
	<li><b>预删除的cookie的名称为:</b>
		<%= cookie_Name%>
	</li>
</ul>

<%
	Cookie cookie;
	Cookie[] cookies;
	// 获取当前域名下的cookies，是一个数组
	cookies = request.getCookies();
	if (cookies != null) {
		out.println("<h2> 列出当前全部 Cookie 名与值,删除的cookie在界面刷新后消失</h2>");
		for (Cookie cookie1 : cookies) {
			cookie = cookie1;
			if ((cookie.getName()).compareTo(cookie_Name) == 0) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				out.print("<b>删除 Cookie: " + cookie.getName() + "<b><br/>");
			}
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