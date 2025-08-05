<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt" %>
<!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql" %>
<!--常用函数标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn" %>
<!--支持EL表达式，不设的话，EL表达式不会解析-->
<%@ page isELIgnored="false" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Title</title>
</head>
<body>
JSP标准标签库（JSTL）学习<br><br>
JSP标准标签库（JSTL）是一个JSP标签集合，它封装了JSP应用的通用核心功能。<br>
JSTL支持通用的、结构化的任务，比如迭代，条件判断，XML文档操作，国际化标签，SQL标签。<br>
除了这些，它还提供了一个框架来使用集成JSTL的自定义标签。<br>
根据JSTL标签所提供的功能，可以将其分为5个类别。<br>
核心标签<br>
格式化标签<br>
SQL 标签<br>
XML 标签<br>
JSTL 函数<br><br>


核心掌握标签<br>
<hr>
c:if判断标签<br>

<c:if test="${5>3}">
	5大于3
</c:if>

<br><br>

<c:if test="${5>=3}">5大于等于3</c:if>

<hr>

c:forEach遍历标签<br>
<br>
items:要遍历的集合<br>
注意:要搭配el表达式取得域中的集合<br>
var:表示每一次遍历出来的变量<br>
在使用s的时候,一定要将s放到el表达式中<br>
begin:表示设置取值的开始下标<br>
end:表示设置取值的结尾下标<br>
step:表示取值的步长<br>
varStatus:表示当前变量的状态<br>
我们在实际项目开发中应用到这个状态的一个属性 count,表示取得序号<br>
必须搭配el表达式来使用<br><br>

本处读取后端设置的域中的list数据<br><br>

<c:forEach items="${sList}" var="s" varStatus="vs">
	${vs.count}-------${s}<br/>
</c:forEach>
<hr>

c:set 往域中设置值标签<br><br>
相当于 pageContext.setAttribute("count",4)<br>
本处设置一个域对象值为4<br>

<c:set var="count" value="4"/>
<hr>

c:choose c:when c:otherwise 分支标签<br><br>
本处判断上面设置的域对象是否在分支中<br>
<c:choose>
	<c:when test="${count==1}">
		计数1
	</c:when>
	<c:when test="${count==2}">
		计数2
	</c:when>
	<c:when test="${count==3}">
		计数3
	</c:when>
	<c:otherwise>
		不在范围内
	</c:otherwise>
</c:choose>
</body>
</html>
