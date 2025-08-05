<%--项目使用的tags标签--%>
<%--以下引用自POM taglibs--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<!--支持EL表达式，不设的话，EL表达式不会解析-->

<%--存储信息的变量 要存储的值 要修改的属性所属的对象target="<string>"  要修改的属性property="<string>" 	var属性的作用域scope="page"--%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<%--项目使用的tld标签--%>
<%--来源于自定义--%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>

<%--引用项目通用js css等 需要注意引用顺序 被依赖的文件需要放在前面--%>
<link type="text/css" href="/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/bootstrap3.3.7/js/jquery.min.js"></script>
<script type="text/javascript" src="/bootstrap3.3.7/js/bootstrap.min.js"></script>
