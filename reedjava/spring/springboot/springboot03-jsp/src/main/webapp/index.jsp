<%--User: reedsource Date: 2020/9/17 Time: 9:46--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/tags/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>jsp学习主页</title>
</head>
<body>
<%--设定宽度80%--%>
<div class="container-fluid p-t-15" style="width: 80%">

	<div class="row">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header"><h4>JSP学习案例</h4></div>
				<br>
				<div class="card-body">
					<h5>基本实例</h5>
					<table class="table table-hover table-bordered">
						<tr>
							<td>所属模块</td>
							<td>子模块</td>
							<td>子模块编号</td>
							<td>案例名称</td>
							<td>描述</td>
						</tr>
						<%--jsp遍历  变量in  实体index--%>
						<c:forEach items="${index}" var="in" varStatus="index">
							<tr>
								<td>${in.modules}</td>
								<td>${in.module}</td>
								<td>${in.moduleId}</td>
									<%--设置打开一个新的页面--%>
								<td><a href="${in.url}" target="_blank">${in.moduleName}</a></td>
								<td>${in.describe}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>