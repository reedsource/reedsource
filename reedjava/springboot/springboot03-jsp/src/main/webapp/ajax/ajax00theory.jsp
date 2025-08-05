<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
        function yuanli() {

            //1.创建核心对象xmlhttp
            //下面的所有的技术的实现,都是调用xmlhttp核心对象实现的
            //约定俗成的
            let xmlhttp;
            //创建xmlhttp对象的流程
            // 判断浏览器,code for IE7+, Firefox, Chrome, Opera, Safari
            if (window.XMLHttpRequest) {
                xmlhttp = new XMLHttpRequest();
                // code for IE6, IE5
            } else {
                //其它主流,新的浏览器
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }

            //2.创建回调函数
            //ajax为后台发送数据,先执行后台,再执行函数的函数方法体
            //xmlhttp.onreadystatechange:
            xmlhttp.onreadystatechange = function () {
                /*
                    回调函数的执行条件
                    xmlhttp.readyState:表示得到请求状态码 ,码值为4,表示请求成功
                    xmlhttp.status:表示得到响应的状态码,码值为200,表示响应成功
                    请求成功并且响应成功之后,才执行该函数体
                */
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    //xmlhttp.responseText:得到后台传递回来的返回值 接收的是 out.print(值)
                    var data = xmlhttp.responseText;
                    document.getElementById("msg").innerHTML = data;
                }
            };

            //3.设置请求信息
            /* xmlhttp.open:表示设置发送请求的基本信息
                参数1:设置请求方式 GET/POST
                参数2:设置请求路径
                参数3:设置同步还是异步请求

                    true:异步请求
                    我们的ajax请求和下面的alert彼此之间不互相影响
                    两根线程,一根负责执行ajax,一根执行alert操作

                    false:同步请求
                    必须在上面的代码执行完毕之后,再执行下面的代码
                    ajax执行完毕后,才执行下面的alert操作
                    一根线程,依次执行

                    在实际项目开发中,我们使用异步操作比较多,在特殊需求下,我们也会使用到同步操作 */
            //xmlhttp.open("post", "aj0010.do", false);

            //可以添加时间戳,防止浏览器缓存数据
            xmlhttp.open("post", "ajax00?str1=第一个数据&str2=第二个数据&str3=" + new Date().getTime(), false);


            //该代码在open方法和send方法中间,主要是post方式使用
            //以form表单的形式发送数据

            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            //4.发送请求
            //发送请求
            xmlhttp.send("str1=第一数据追加aaaaa&str2=第二数据追加bbbbbb");

            //在实际开发项目中,一般使用异步操作
            //但是特殊情况下,例如需要一步一步验证的时候,也需要使用同步操作
        }


        function jian() {
            //注意:只有返回值类型没有后缀,
            //data的值是后台返回的,类型由后台决定,是ajax内置的不需要定义
            $.post(
                "ajax00",
                "str1=abc&str2=bcd",
                function (data) {
                    $("#msg").html(data);
                },
                "text"
            )
        }

        function biao() {
            $.ajax({
                //请求方式 get/post
                type: "post",
                //请求路径
                //跨域写法 url : "http://localhost:8088/ky1/ky/myServlet1.do",
                url: "ajax00",
                //接收返回值的类型   text:普通文本   json:json格式的文本
                dataType: "text",
                //json格式{"str1":"aaa","str2":"bbb","str3":"ccc"}
                data: "str1=eee&str2=fff",
                success: function (data) {	//回调函数  data:回调函数的返回值
                    $("#msg1").html(data);
                }
            })
        }

        //将data界面返回
        function data() {
            $.ajax({
                type: "get",
                url: "data",
                //接收字节流的格式
                //因为data.jsp文件包含两行数据,虽然看不到,但有两个换行
                //接收josn格式,不受两行换行的影响
                dataType: "text",
                success: function (data) {	//回调函数  data:回调函数的返回值
                    alert(data);
                }
            })
        }
	</script>
</head>
<body>
<a href="/ajax/ajax00theory.jsp">ajax00基础实现及原理</a><br>
<a href="/ajax/ajax01multi.jsp">ajax01多数据</a><br>
<a href="/ajax/ajax02ProvinceCity.jsp">ajax02省市联动</a><br>
<hr>
在实际项目开发中,对于json有大量的应用,尤其是应用在ajax上.<br>
使用json能为后台(servlet)传递参数<br>
后台以json格式的字符串响应值给前端<br>
<br>
1. 以json格式传递参数<br>
2. 以json格式接收参数（拼接json的方式）<br>
（1）接收单个值<br>
（2）接收多个值<br>
（3）接收单个对象<br>
（4）接收多个对象<br>
（5）接收集合<br>
<hr>
ajax依赖jquery-1.8.3.min.js<br>

<br>
<hr>
<button onclick="yuanli()">ajax实现</button>
<button onclick="jian()">极简ajax案例</button>
<div id="msg" style="width: 400px; height: 200px; background-color: pink"></div>
<hr>
<button onclick="biao()">标准ajax案例</button>
<div id="msg1" style="width: 400px; height: 200px; background-color: pink"></div>
<hr>
<button onclick="data()">发送数据到其它jsp界面,并将jsp界面内容全部返回到当前</button>
</body>
</html>





























