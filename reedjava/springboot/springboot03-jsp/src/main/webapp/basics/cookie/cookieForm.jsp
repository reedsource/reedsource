<%--User: reedsource Date: 2020/9/22 Time: 8:35--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/tags/taglib.jsp" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="<%=basePath%>">
	<title>jsp设置cookie</title>
</head>
<body>

<div style="margin-top: 100px;" class="container">
	<h1>cookie控制演示</h1><br>
	<div class="row">
		<div class="col-xs-12 col-sm-4">
			<h3>设置cookie值</h3><br>
			<%--自动 包装--%>
			<form action="./basics/cookie/setCookie.jsp" target="_blank" method="GET">
				<div class="form-group">
					<label for="setCookieName">想要添加的cookie名称</label>
					<input type="text" class="form-control" id="setCookieName" name="setCookieName" placeholder="请输入名称">
				</div>
				<div class="form-group">
					<label for="setCookieValue">想要添加的cookie值</label>
					<input type="text" class="form-control" id="setCookieValue" name="setCookieValue"
					       placeholder="请输入网址">
				</div>
				<input type="submit" class=" btn btn-default" value="提交"/>
			</form>
		</div>

		<div class="col-xs-12 col-sm-4">
			<a href="./basics/cookie/getCookie.jsp" target="_blank">
				<h3>查看当前浏览器缓存的全部cookie</h3>
			</a>
		</div>

		<div class="col-xs-12 col-sm-4">
			<h3>删除cookie值</h3><br>
			<%--自动 包装--%>
			<form action="./basics/cookie/deleteCookie.jsp" target="_blank" method="GET">
				<div class="form-group">
					<label for="getName">删除的cookie名称:</label>
					<input type="text" class="form-control" id="cookieName" name="cookieName"
					       placeholder="请输入需要删除的cookie名称">
				</div>
				<input type="submit" class=" btn btn-default" value="提交"/>
			</form>
		</div>
	</div>
</div>
</body>
</html>