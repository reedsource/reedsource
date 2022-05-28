<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
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
                //使用一个长的内容,将下拉框的宽度先确定,防止因为城市长度的问题导致下拉框的长度不稳定
                $("#pid").html("<option>-- 请选择 --</option>");
                $.post(
                    "ajax021",
                    function (data) {
                        $(data).each(function () {

                            $("#pid").append("<option value='" + this + "'>" + this + "</option>");
                        })
                    },
                    "json"
                )
            });

            $("#pid").change(function () {

                $("#cid").html("<option>-- 请选择 --</option>");

                var pid = $("#pid").val();

                $.post(
                    "ajax022",
                    {"province": pid},
                    function (data) {
                        $(data).each(function () {
                            $("#cid").append("<option value='" + this + "'>" + this + "</option>");
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

<button id="djBtn">点击</button>
&nbsp;&nbsp;&nbsp;&nbsp;

<select id="pid">
	<option>-- 请选择 --</option>
</select>

&nbsp;&nbsp;

<select id="cid">
	<option>-- 请选择 --</option>
</select>

<hr>

项目说明 使用jQuery实现省市的2级异步联动(访问数据库)
<br>
一对多关系,一个省份里面有多个城市<br>
如何将两张表建立关系,建立外键,在哪建立外键表示在哪维护关系<br>
以下两张表,哪张表来维护关系,永远是由多的一方来维护关系<br>
</body>
</html>





