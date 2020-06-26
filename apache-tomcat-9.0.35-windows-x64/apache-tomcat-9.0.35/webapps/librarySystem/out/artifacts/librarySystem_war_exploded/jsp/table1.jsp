<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/14
  Time: 7:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE  html public "-/w3c/dtd html 4.01 transitional/en" >
<html>
<head>
    <title>easyUi</title>
    <link type="stylesheet" href="http://localhost:8888/hello/net/css/easyUi/themes/default/easyui.css"/>
    <link type="stylesheet" href="http://localhost:8888/hello/net/css/easyUi/themes/icon.css"/>
    <link type="stylesheet" href="http://localhost:8888/hello/net/css/easyUi/demo/demo.css"/>
    <script src="http://localhost:8888/hello/net/css/easyUi/jquery.min.js"></script>
    <script src="http://localhost:8888/hello/net/css/easyUi/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="http://localhost:8888/hello/net/css/easyUi/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
<div id="cc" class="easyui-layout" style="width:600px;height:400px;">
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
    <div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>
    <div data-options="region:'west',title:'West',split:true" style="width:100px;"></div>
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>
</div>
</body>
</html>



