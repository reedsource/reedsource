<%--User: reedsource Date: 2020/9/19 Time: 20:42--%>
<%--pageEncoding="UTF-8"属性用来解决中文编码问题--%>
<%--JSP指令
<%@ page ... %>	定义页面的依赖属性，比如脚本语言、error页面、缓存需求等等
<%@ include ... %>	包含其他文件
<%@ taglib ... %>	引入标签库的定义，可以是自定义标签
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<%--对应pageEncoding--%>
	<meta charset="utf-8">
	<title>jsp基本语法原理</title>
</head>
<body>
Hello World!<br/>
<%--声明变量--%>
<%! int a = 100; %>
<br/>
JSP注释<br/>
不同情况下使用注释的语法规则<br/>
<\%-- 注释 --%> JSP注释，注释内容不会被发送至浏览器甚至不会被编译<br/>
<\!-- 注释 --> HTML注释，通过浏览器查看网页源代码时可以看见注释内容<br/>
<\% 代表静态 <\%常量<br/>
%\> 代表静态 %> 常量<br/>
\' 在属性中使用的单引号<br/>
\" 在属性中使用的双引号<br/>
<br/>
<%--jsp的代码写在<%代码片段%>中--%>
<%
	//打印方法
	out.println("你的IP地址是 " + request.getRemoteAddr());
	out.println("变量a是 " + a);
%>
jsp表达式<br/>
JSP表达式中包含的脚本语言表达式，先被转化成String，然后插入到表达式出现的地方<br/>
由于表达式的值会被转化成String，所以您可以在一个文本行中使用表达式而不用去管它是否是HTML标签。<br/>
表达式元素中可以包含任何符合Java语言规范的表达式，但是不能使用分号来结束表达式。<br/>

今天的日期是: <%= (new java.util.Date()).toString()%>
<br>
<br>
<h3>IF...ELSE 实例</h3>
<%! int day = 3; %>
<% if (day == 1 | day == 7) { %>
<p>今天是周末</p>
<% } else { %>
<p>今天不是周末</p>
<% } %>

<h3>SWITCH...CASE 实例</h3>
<%
	switch (day) {
		case 0:
			out.println("星期天");
			break;
		case 1:
			out.println("星期一");
			break;
		case 2:
			out.println("星期二");
			break;
		case 3:
			out.println("星期三");
			break;
		case 4:
			out.println("星期四");
			break;
		case 5:
			out.println("星期五");
			break;
		default:
			out.println("星期六");
	}
%>
<br>
<br>
<h3>For 循环实例</h3>
<%--<%!--%>
<%! int fontSize; %>
<%for (fontSize = 1; fontSize <= 3; fontSize++) { %>
<span style="color: green">
	<%= fontSize %>While 循环实例
</span><br/>
<%}%>

<h3>While 循环实例</h3>
本处使用while不断累加,刷新页面后,变量大于10,就看不到显示了
<%! int fontSize1 = 0; %>
<%while (fontSize1 <= 10) { %>
<span style="color: green">
	<%= fontSize1 %>While 循环实例
</span><br/>
<%
	fontSize1++;
%>
<%}%>
</body>
</html>