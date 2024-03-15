<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.Enumeration" %><%--User: reedsource Date: 2020/9/22 Time: 8:40--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/tags/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>jsp接收界面</title>
</head>
<body>

<div style="margin-top: 100px;" class="container">
	<h1>jsp请求</h1><br>
	<div class="row">
		<div class="col-xs-12 col-sm-4">
			<h3>使用 GET 方法读取数据</h3><br>
			<ul>
				<li>
					<p>
						<b>站点名:</b>
						<%= request.getParameter("getName")%>
					</p>
				</li>
				<li>
					<p>
						<b>网址:</b>
						<%= request.getParameter("getUrl")%>
					</p>
				</li>
			</ul>
		</div>
		<div class="col-xs-12 col-sm-4">
			<h3>使用 POST 方法读取数据</h3><br>
			<ul>
				<li>
					<p>
						<b>如果出现接收编码问题:</b>
						<br>
						<b>本处因为发送页面已经设定编码为UTF8,导致本地强转为乱码</b>
						<%
							// 如果解决中文乱码的问题
							String post_Name = request.getParameter("post_Name");
							if (post_Name != null) {
								post_Name = new String((post_Name).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
							}
						%>
						<%=post_Name%>
					</p>
				</li>
				<li>
					<p>
						<b>站点名:</b>
						<%= request.getParameter("post_Name")%>
					</p>
				</li>
				<li>
					<p>
						<b>网址:</b>
						<%= request.getParameter("post_Url")%>
					</p>
				</li>
			</ul>
		</div>

		<div class="col-xs-12 col-sm-4">
			<h3>从Checkbox复选框中读取数据</h3><br>
			<ul>
				<li><p><b>Google 是否选中:</b><%= request.getParameter("google")%>
				</p></li>
				<li><p><b>菜鸟教程是否选中:</b><%= request.getParameter("runoob")%>
				</p></li>
				<li><p><b>淘宝是否选中:</b><%= request.getParameter("taobao")%>
				</p></li>
			</ul>
		</div>
	</div>
</div>

<div style="margin-top: 100px;" class="container ">
	<h1>读取本次提交表单所有参数</h1>
	<table width="100%" border="0" align="center" class="table table-hover">
		<tr>
			<th>参数名</th>
			<th>参数值</th>
		</tr>
		<%
			Enumeration paramNames = request.getParameterNames();

			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				out.print("<tr><td>" + paramName + "</td>\n");
				String paramValue = request.getParameter(paramName);
				out.println("<td> " + paramValue + "</td></tr>\n");
			}
		%>
	</table>
</div>

</body>
</html>