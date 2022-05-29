<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/user/index.do" target="main">主页</a>
<br>
<table style="border: 2px;align-items: center">
    <tr>
        <td>用户编号</td>
        <td>用户姓名</td>
        <td>用户密码</td>
        <td>性别</td>
        <td>出生日期</td>
        <td>籍贯</td>
    </tr>

    <tr>
        <td>1109</td>
        <td>SMITH</td>
        <td>123</td>
        <td>男</td>
        <td>1996-09-12</td>
        <td>黑龙江</td>
    </tr>
</table>
</body>
</html>
