<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<%--<%
//传统方式
String str1 = (String)request.getAttribute("str1");
%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

<%--传统方式,可以取到值--%>
<%--str1:<%=str1%>--%>
<h3>EL表达式</h3>
<br>==============EL表达式说明==============<br><br>
主要使用在jsp中<br>
主要功能:获取域中的数据(还可以获取其他数据)<br>
进行简单的计算，对传统方式的替代作用<br>
获取数据<br>
取得参数值<br>
取得全局参数<br>
…<br>
执行运算<br>

语法: $ {数据或运算}

<br><br>==============EL表达式取值解析==============<br><br>
实际是 Str1:$ {requestScope.str1}<br>
如果没有写范围,将会从小向大搜索取值<br>
$ {pageScope|requestScope|sessionScope|applicationScope.属性名}<br>
<br>
取得域中的值,将xxScope隐含对象省略掉是可以的<br>
没有指定域,会从最小的域(页面域)开始搜索<br>
如果没有搜索到,则继续向上一个域搜索,如果一直没有,则一直往上搜索<br>
如果搜索到顶层域(上下文)还没有这个值.则返回空串<br>
<br>
el对于null默认处理成为空串

<br><br>==============EL表达式标准取值==============<br><br>
EL表达式标准取值:${str1}

<br><br>==============EL不同类型数据取值==============<br><br>
取得数组数据 ${strArr[1]}
<hr>
取得集合中的值 ${sList[1]}
<hr>
取得map中的值 ${myMap.str1}
<hr>
取得实体类中的属性值${s.name}
<hr>


<br><br>==============EL内容拼接EL 获取值=============<br><br>
本方法在本处无法测试,<br>
实际使用中 适用于 前端动态获取数据后 拼接数据动态查询后端数据时使用<br>
取得数据 ${'EL'.concat(me)}<br>
取得数据 ${ELis}<br>

<br>
<hr>
<br><br>==============EL数据运算==============<br><br>
a=${a} b=${b}<br><br>
加法运算${a+b}
<hr>
减法运算${a-b}
<hr>
乘法运算${a*b}
<hr>
除法(取余)运算${a/b}
<hr>
取余(取模)运算${a%b}
<hr>
判断一个容器的长度是否为0,为空<br>
判断方式：\${empty 域中的对象名称}<br>
${empty strList}
<hr>

其它的所有的运算的结果都是true或是false

<br><br>==============EL取项目绝对路径==============<br><br>
进入el01取项目绝对路径: ${pageContext.request.contextPath}<br>
在springboot中不需要 无法取值
<br>
.contextPath 内部实现代码 request.getcontextPath 重定向
<br>
el表达式的表现形式是.属性的形式,实际内部的实现方式依然是.get方式
<br>
base标签的优点是方便,但是非主流浏览器不支持
<br>
el方式所有浏览器都支持,但是需要写标签

<br>==============EL发送获取请求参数==============<br><br>
<a href="/el/el02param获取请求参数.jsp?str1=abc&str1=bcd&str1=cde">
	点击跳转到el02发送参数并由jsp界面直接接接收参数
</a>
发送数据 str1=abc&str1=bcd&str1=cde
<hr>
<a href="/EL02">
	请求后端
</a>
<br><br><br>
</body>
</html>