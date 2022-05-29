<%@ page contentType="text/html;charset=UTF-8" %>
<%--shiro标签导入--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>shiro标签</title>
</head>
<body>
<br>
<a href="${pageContext.request.contextPath}/user/index.do" target="main">主页</a>
<br>
<br>
<h>以下为身份验证,显示与登陆与否相关</h>
<br>
<%--身份验证相关标签--%>
<shiro:guest>普通游客身份</shiro:guest>
<shiro:notAuthenticated>用户是一个未登陆用户 可以 <a href="${pageContext.request.contextPath}/user/userLogin.do">登陆</a></shiro:notAuthenticated>
<shiro:authenticated>已经登陆的用户，显示  欢迎<shiro:principal/>光临</shiro:authenticated>
<%--上面的是这个标签的显示变种, 下面标签中间的文字将被忽略--%>
<br>
<shiro:principal> 输出已经登陆的这个用户的登陆名</shiro:principal>
<shiro:user>实现记住我或则十天免登陆</shiro:user>
<br>
<hr>
<br>
<h>以下为角色验证,显示与登陆后是否有某个角色相关</h>
<br>
<%--登陆之后--%>
<shiro:authenticated>
	<%--拥有某个角色--%>
	<shiro:hasRole name="seller">
		登陆用户，具有seller角色<br/>
	</shiro:hasRole>

	<%--拥有多个角色之一--%>
	<shiro:hasAnyRoles name="admin,manager,seller">
		登陆用户，可能拥有admin,manager,seller角色<br/>
	</shiro:hasAnyRoles>

	<%--没有指定的角色--%>
	<shiro:lacksRole name="admin">
		登陆用户，由于不是管理员身份，因此无法进行本次操作
	</shiro:lacksRole>
</shiro:authenticated>
<br>
<hr>
<br>
<h>以下权限验证,显示与登陆后是否有某个权限相关</h>
<br>
<%--登陆之后--%>
<shiro:authenticated>
<%--登陆用户，如果具有指定的权限，则看到标签内部信息--%>
<shiro:hasPermission name="detail:query">
	登陆用户，拥有 detail:query 权限
</shiro:hasPermission>
<%--没有指定权限名时显示--%>
<shiro:lacksPermission name="detail:query">
	登陆用户，没有 detail:query 权限
</shiro:lacksPermission>
</shiro:authenticated>
</body>
</html>
