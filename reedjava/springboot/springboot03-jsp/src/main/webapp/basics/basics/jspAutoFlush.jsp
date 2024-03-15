<%--User: reedsource Date: 2020/9/24 Time: 17:02--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>jsp自动刷新</title>
</head>
<body>

<h2>自动刷新实例</h2>
<%
	out.println("每隔1秒刷新一次界面");
	// 设置每隔1秒自动刷新
	response.setIntHeader("Refresh", 1);
	// 获取当前时间
	Calendar calendar = new GregorianCalendar();
	String am_pm;
	int hour = calendar.get(Calendar.HOUR);
	int minute = calendar.get(Calendar.MINUTE);
	int second = calendar.get(Calendar.SECOND);
	if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
		am_pm = "AM";
	} else {
		am_pm = "PM";
	}
	String CT = hour + ":" + minute + ":" + second + " " + am_pm;
	out.println("当前时间: " + CT + "\n");
%>

</body>
</html>