<%--可以设置本jsp错误的错误页000errorPage  当界面出现错误时 将会跳转到错误页--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="000errorPage.jsp" %>
<%--引入后端实体类--%>
<%@ page import="top.ireed.deal.DealLog" %>
<%@ page import="top.ireed.entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	DealLog.log(basePath);
%>

<%--本处为设置标签页,basePath--%>
<html>
<head>
	<title>Title</title>
	<base href="<%=basePath%>">
</head>
<body>
<a href="/jsp/j00jsp.jsp">jsp基础实现及原理</a><br>
<h3>jsp学习</h3><br><br>

1. 什么是jsp<br>
理解:<br>
jsp是服务器响应页面<br>
jsp是嵌入java代码的html<br>
面试：jsp其实就是servlet（解释这句话在于jsp的运行原理）<br><br>
2. jsp执行原理及存放路径<br>
执行原理:<br>
html:我们通过浏览器将路径定位到服务器中的html页面，服务器找到html页面，响应回浏览器，浏览器拿到了html页面，解释html页面(浏览器是专业解释html页面的)<br>
jsp：我们通过浏览器将路径定位到服务器中的jsp，服务器找到jsp<br>
jsp会先翻译成java文件（全自动）（编译成.class文件），然后通过响应流的形式为浏览器做响应<br><br>
<hr>
<br>
pre标签 在此标签之bai间的代码du会原封不动的呈现出来<br>
<hr>
<br>
jsp目录测试<br><br>
<pre>
使用绝对路径,访问同文件夹下的jsp,搭配basePath变量来使用,basePath变量搭配base标签
http://localhost:8080/jsp/ce001.jsp

加入base标签之后,功能为可以省略掉basePath变量的拼接
省略掉了basePath变量的拼接之后,我们的路径变成了一个貌似于相对路径的路径结构
但是不是相对路径,而是绝对路径
换句话说,加入了base标签后,页面中所有的路径默认前面都会加入base标签配置的绝对路径,相对路径集体失效
注意:
在实际项目开发中,动态的取得绝对路径的方式,常用的有两种
以上我们学习的basePath变量结合base标签的应用为其中的一种方式
将来使用el表达式动态的取得绝对路径为第二种方式
这两种方式在以后的开发中用的都很普遍

以上使用base标签的形式,仅针对于市面上的主流浏览器,一些非主流浏览器或者是某些版本的Eclipse中自带的浏览器是不支持base标签的

在应用方面,加入了base标签之后,我们写的路径都以相对路径形式存在(但是不是相对路径)
所有的路径,都是从WebContent文件夹开始往下顺
总结：加入了base标签之后
路径的统一写法：所有的路径都是从WebContent文件夹往下面引
前面不加/
虽然前面不加/，但是也不是相对路径，只是将<%=basePath%>省略掉了
</pre>

<a href="../jsp/ce001同目录下测试.jsp">
	相对路径下读取同目录下的jsp文件
</a>
<hr>
<a href="../login.jsp">
	相对目录读取根目录下的login.jsp测试页
</a>
<hr>
注意,本处的绝对目录读取,idea和eclipse不同,eclipse路径前需要添加项目名称<br>
<a href="/jsp/ce001同目录下测试.jsp">
	绝对路径读取同目录下的jsp测试页
</a>
<hr>
<a href="/login.jsp">
	绝对路径下读取目录下的jsp测试页
</a>
<hr>
<a href="<%=basePath%>jsp/ce001同目录下测试.jsp">
	basePath结合访问同目录下的jsp测试页
</a>
<hr>


<br><br>jsp编译目录及代码拼接<br><br>

10×10 *号方阵
<%
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
%>
*
<%
	}
%>
<br>
<%
	}
%>

<hr>
<br><br>jsp打印表单<br><br>
可以引入后端实体类<br>
<%
	//创建5个学生存放到集合list中,通过表格table来显示学生
	Student s1 = new Student("001", "张1", 23);
	Student s2 = new Student("002", "张2", 24);
	Student s3 = new Student("003", "张3", 25);
	Student s4 = new Student("004", "张4", 26);
	Student s5 = new Student("005", "张5", 27);

	List<Student> sList = new ArrayList<Student>();
	sList.add(s1);
	sList.add(s2);
	sList.add(s3);
	sList.add(s4);
	sList.add(s5);
%>

<table border="1">
	<tr>
		<td>
			序号
		</td>
		<td>
			编号
		</td>
		<td>姓名</td>
		<td>年龄</td>
	</tr>

	<%
		for (int i = 0; i < sList.size(); i++) {
			Student s = sList.get(i);
	%>
	<tr>
		<td><%=i + 1%>
		</td>
		<td><%=s.getId()%>
		</td>
		<td><%=s.getName()%>
		</td>
		<td><%=s.getAge()%>
		</td>
	</tr>
	<%
		}
	%>
</table>

<hr>
<br><br>jsp读取其它页面内容<br><br>
<%@include file="ce001同目录下测试.jsp" %>
<hr>
<br><br>jsp:forward跳转其它jsp界面<br><br>
本处代码添加多余空格 否则界面进入后 将直接跳转到其它界面<br><br>
< jsp:forward page="ce001同目录下测试.jsp"/>
<br>
<hr>

<hr>
<br><br>jsp域对象设置<br><br>
<pre>
域对象：
pageContext		一个页面   和设置一个局部变量区别不是很大
request			一次请求
session			一次会话
application		整个项目
</pre>
<%
	pageContext.setAttribute("str1", "aaa");
	request.setAttribute("str2", "bbb");
	session.setAttribute("str3", "ccc");
	application.setAttribute("str4", "ddd");
%>
<%
	String str1 = (String) pageContext.getAttribute("str1");
%>
str1:<%=str1%>
<hr>
<br><br>jsp修改其它内置对象<br><br>
下面代码将会把str3 修改为变更了的str3, 注释后将会看到原始的ccc
<%
	//作用域名称,作用域更改值,作用域更改域范围
	pageContext.setAttribute("str3", "变更了的str3", 3);
%>
<%
	pageContext.getSession();
%>
<a href="/jsp/j01jsp域对象读取验证.jsp">设置完成,跳转到验证界面查看</a><br>

<hr>
<br><br>jsp域对象的类型读取方式<br><br>
当只有一个域对象时,想要取得session对象,只能通过这种方式<br>
<%
	pageContext.setAttribute("str1", "更改了域对象str1的值", PageContext.SESSION_SCOPE);
%>
<%
	pageContext.getSession();
%>
将域对象str1的域范围修改为session作用域<br>
<%
	String str11 = (String) pageContext.getAttribute("str1", PageContext.SESSION_SCOPE);
%>
str11:<%=str11%><br/>
<br><br><br>
</body>
</html>
