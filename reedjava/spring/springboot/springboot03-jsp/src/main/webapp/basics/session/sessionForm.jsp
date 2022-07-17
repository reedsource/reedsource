<%@ page import="java.util.Date" %><%--User: reedsource Date: 2020/9/22 Time: 15:51--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	// 获取session创建时间
	Date createTime = new Date(session.getCreationTime());
	// 获取最后访问页面的时间
	Date lastAccessTime = new Date(session.getLastAccessedTime());

	String title = "再次访问菜鸟教程实例";
	Integer visitCount = new Integer(0);
	String visitCountKey = "visitCount";
	String userIDKey = "userID";
	String userID = "a000001";

	// 检测网页是否有新的访问用户
	if (session.isNew()) {
		title = "访问菜鸟教程实例";
		session.setAttribute(userIDKey, userID);
		session.setAttribute(visitCountKey, visitCount);
	} else {
		visitCount = (Integer) session.getAttribute(visitCountKey);
		if (visitCount == null) {
			visitCount = 0;
		}
		visitCount += 1;
		userID = (String) session.getAttribute(userIDKey);
		session.setAttribute(visitCountKey, visitCount);
	}
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>session主页</title>
</head>
<body>
<h1>Session 跟踪 <%out.print(title); %></h1>

<table border="1" align="center">
	<tr bgcolor="#949494">
		<th>Session 信息</th>
		<th>值</th>
	</tr>
	<tr>
		<td>id</td>
		<td><% out.print(session.getId()); %></td>
	</tr>
	<tr>
		<td>创建时间</td>
		<td><% out.print(createTime); %></td>
	</tr>
	<tr>
		<td>最后访问时间</td>
		<td><% out.print(lastAccessTime); %></td>
	</tr>
	<tr>
		<td>用户 ID</td>
		<td><% out.print(userID); %></td>
	</tr>
	<tr>
		<td>访问次数</td>
		<td><% out.print(visitCount); %></td>
	</tr>
</table>
</body>
</html>