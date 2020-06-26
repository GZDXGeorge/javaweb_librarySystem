<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/12
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <script type="text/javascript" src="../jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../jeasyui/jquery.easyui.min.js"></script>
    <meta http-equiv="refresh" content ="5;url=http://localhost:8888/hello/jsp/showBookList.jsp">
    <script>
        var i = 5;
        function startTime(){
            i=i-1;
            document.getElementById("time").innerHTML=i+"秒后自动打开探索界面";
            setTimeout('startTime()',1000);
        }
    </script>
</head>
<body  onload="startTime()">

    <h1>还有<span id="time"></span></h1>
<%
    String username = (String) session.getAttribute("userName");
    if (username == null) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        out.print("<script>alert('用户不存在，请检查重新登录!');window.location.href='http://localhost:8888/hello/jsp/login.jsp'</script>\"");
    }
%> 欢迎用户<%=username%>访问！！！
<p>
    <a href ="http://localhost:8888/hello/jsp/showBookList.jsp" style="text-decoration: none">开启探索之路</a>
</p>
<p>
    <a href ="http://localhost:8888/hello/jsp/commonRB.jsp" style="text-decoration: none">查看我的行囊之路</a>
</p>

</body>

</html>
