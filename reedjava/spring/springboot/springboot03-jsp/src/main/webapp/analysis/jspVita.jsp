<%@ page import="top.ireed.deal.DealLog" %><%--User: reedsource Date: 2020/9/19 Time: 19:06--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>jsp生命周期</title>
</head>
<body>
<%!
	/**
	 * 静态区 编译时,相当于创建了三个类变量
	 *
	 * jsp初始化计数
	 */
	private int initVar = 0;

	/**
	 * jsp请求计数
	 */
	private int serviceVar = 0;
	/**
	 * jsp销毁计数
	 */
	private int destroyVar = 0;
%>

<%!
	/**
	 * 重写jspInIt方法
	 */
	@Override
	public void jspInit() {
		initVar++;
		DealLog.log("jspInit(): JSP被初始化了" + initVar + "次");
	}

	/**
	 * 重写jspDestroy方法
	 */
	@Override
	public void jspDestroy() {
		destroyVar++;
		DealLog.log("jspDestroy(): JSP被销毁了" + destroyVar + "次");
	}
%>

<%--实际相当于响应方法请求,每次进入界面就会调用本方法--%>
<%
	serviceVar++;
	DealLog.log("_jspService(): JSP共响应了" + serviceVar + "次请求");

	//本处赋值 相当于addAttribute("content1", content1) 将信息发往前端
	String content1 = "初始化次数 : " + initVar;
	String content2 = "响应客户请求次数 : " + serviceVar;
	String content3 = "销毁次数 : " + destroyVar;
%>
<h1>JSP 生命周期测试实例</h1>
<p><%=content1 %>
</p>
<p><%=content2 %>
</p>
<p><%=content3 %>
</p>

</body>
</html>