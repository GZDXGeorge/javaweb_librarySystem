<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/19
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("userName");  //清空Session变量
    session.removeAttribute("userJob");  //清空Session变量
    session.removeAttribute("Uno");  //清空Session变量
    response.sendRedirect("http://localhost:8888/hello/jsp/login.jsp");
%>
