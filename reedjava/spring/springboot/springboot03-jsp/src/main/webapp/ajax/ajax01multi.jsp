<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">

        $(function () {

            $("#djBtn").click(function () {

                $.post(
                    "ajax011",
                    function (data) {
                        let i = 1;
                        $(data).each(function () {
                            //每一个json对象使用this来表示
                            //append  追加
                            //html 替代
                            $("#tBody").append("<tr><td>" + (i++)
                                + "</td><td>" + this.id
                                + "</td><td>" + this.name
                                + "</td><td>" + this.age
                                + "</td><td>编辑||删除</td></tr>");
                        })
                    },
                    "json"
                )
            })
        })

	</script>
</head>
<body>
<a href="/ajax/ajax00theory.jsp">ajax00基础实现及原理</a><br>
<a href="/ajax/ajax01multi.jsp">ajax01多数据</a><br>
<a href="/ajax/ajax02ProvinceCity.jsp">ajax02省市联动</a><br>
<hr>
<button id="djBtn">点击</button>
<br/>
<br/>

<table align="center" border="1" width="70%" cellpadding="6" cellspacing="0">

	<thead>
	<tr>
		<td>
			序号
		</td>
		<td>
			编号
		</td>
		<td>
			姓名
		</td>
		<td>
			年龄
		</td>
		<td>
			操作
		</td>
	</tr>
	</thead>
	<tbody id="tBody">

	</tbody>
</table>
</body>
</html>





























