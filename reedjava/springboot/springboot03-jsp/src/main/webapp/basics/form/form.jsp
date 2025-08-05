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
	<title>jspFrom表单</title>
</head>
<body>

<div style="margin-top: 100px;" class="container">
	<h1>jsp请求发送数据</h1><br>
	<div class="row">
		<div class="col-xs-12 col-sm-4">
			<h3>get请求提交数据</h3><br>
			<%--自动 包装--%>
			<form action="./basics/form/main.jsp" method="GET">
				<div class="form-group">
					<label for="getName">名称</label>
					<input type="text" class="form-control" id="getName" name="getName" placeholder="请输入名称">
				</div>
				<div class="form-group">
					<label for="getUrl">url</label>
					<input type="text" class="form-control" id="getUrl" name="getUrl" placeholder="请输入url">
				</div>
				<input type="submit" class=" btn btn-default" value="提交"/>
			</form>
		</div>
		<div class="col-xs-12 col-sm-4">
			<h3>post请求提交数据</h3><br>
			<%--自动 包装--%>
			<form action="./basics/form/main.jsp" method="post">
				<div class="form-group">
					<label for="post_Name">名称</label>
					<input type="text" class="form-control" id="post_Name" name="post_Name" placeholder="请输入名称">
				</div>
				<div class="form-group">
					<label for="post_Url">url</label>
					<input type="text" class="form-control" id="post_Url" name="post_Url" placeholder="请输入url">
				</div>
				<input type="submit" class=" btn btn-default" value="提交"/>
			</form>
		</div>
		<div class="col-xs-12 col-sm-4">
			<h3>Checkbox复选框POST提交数据</h3><br>
			<%--自动 包装--%>
			<form action="./basics/form/main.jsp" method="POST" target="_blank">
				<label>
					<input type="checkbox" name="google" checked="checked"/>
					Google
				</label>
				<label>
					<input type="checkbox" name="runoob"/>
					菜鸟教程
				</label>
				<label>
					<input type="checkbox" name="taobao" checked="checked"/>
					淘宝
				</label>
				<input type="submit" value="选择网站"/>
			</form>
		</div>
	</div>
</div>

</body>
</html>