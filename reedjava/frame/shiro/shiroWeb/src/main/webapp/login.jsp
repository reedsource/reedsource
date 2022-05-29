<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/login.do" method="post">
	<label>用户名:zs <input type="text" name="userName"/></label><br/>
	<label>密&ensp;&ensp;码:123 <input type="password" name="password"/></label><br/>
	<input type="submit" value="登陆">
</form>
</body>
</html>
