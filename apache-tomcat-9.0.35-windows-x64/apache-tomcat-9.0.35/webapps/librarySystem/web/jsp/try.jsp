<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/19
  Time: 22:53
  Learn from https://www.bilibili.com/video/BV18z411i7gh?t=23&p=192
  To change this template use File | Settings | File Templates.
  这个代码+src\Servlet\bookServlet\UploadImageServlet.java 2个代码在这个项目中作用，只是练习
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<!--文件上传对表单的要求-->
<!--
    1、表单中的请求提交方式必须是POST
    2、表单中应指定所提交的请求位multipart请求，通过在<form/>标签中添加enctype属性
        其值为multipart/form-data
    3、  表单
-->
<form  method="POST" action="http://localhost:8888/hello/UploadImageServlet" enctype="multipart/form-data">
    编号<input type="text" name="BNO"></br>
    名字<input type="text" name="BNAME"></br>
    照片<input type="file" name="picutreUrl"></br>
    <input type="submit" value="注册">
</form>
</body>
</html>

