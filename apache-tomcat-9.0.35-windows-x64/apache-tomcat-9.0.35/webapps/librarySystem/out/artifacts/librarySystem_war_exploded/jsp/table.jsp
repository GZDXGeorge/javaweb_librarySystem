<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/14
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理系统</title>
    <link rel="stylesheet" type ="text/css" href="http://localhost:8888/hello/net/css/style.css">
    <script type="text/javascript" src="../jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../jeasyui/jquery.easyui.min.js"></script>


</head>
    <body class="easyui-layout">
    <!-- 顶部 -->
    <div data-options="region:'north',title:'图书馆管理系统其他功能',split:false"
         style="height:0px; ">
<%--        这里是一个图片暂时不用 --%>
<%--        <img src="http://localhost:8888/hello/net/LogeInPhoto/library.jpg"--%>
<%--             style=" background-repeat: no-repeat;--%>
<%--            width: auto;height: auto;--%>
<%--            max-width: 100%;--%>
<%--            max-height: 100%;"/>--%>
        <div class="topDiv" style="float:right ;width:220px; ">
            <a href="#">退出</a>|
            <a href="http://localhost:8888/hello/jsp/login.jsp">切换身份</a>|
            <a href="http://localhost:8888/hello/jsp/ResetAccount.jsp">修改个人信息</a>
        </div>
    </div>

    <!-- 左侧 -->

    <div data-options="region:'west',title:'功能区',split:true" style="width:200px;">
<%--        <div id="aa" class="easyui-accordion" style="width:102%;height:100%" >--%>

<%--            <div title="学生记录" data-options="iconCls:'icon-man'" style="overflow:auto; padding:10px;">--%>
<%--                --%>
<%--            </div>--%>

<%--            <div title="藏书记录" data-options="iconCls:'icon-search',selected:true" style="padding:10px;">--%>
<%--                content2--%>
<%--            </div>--%>

<%--            <div title="借书记录"  data-options="iconCls:' icon-save',selected:true" style="padding:10px;">--%>
<%--                content3--%>
<%--            </div>--%>

<%--        </div>--%>
    <div id="sm" data-toggle="topjui-sidemenu" data-options="data:data">
    </div>
    </div>

    <!--  中央展示区 -->
    <div data-options="region:'center',title:'数据区'" style="padding:5px;background:#eee;">
        <table id="dg"></table>

    </div>
    <!-- 底部版权 -->
    <div data-options="region:'south',title:'版权信息',split:true" style="height:100px;top:528px; color:red; ">
            <h style="font-size: 25px; line-height: 30px; height: 30px;">欢迎使用图书馆管理系统</h>
            <p>开发人员：广大菜鸟博主</p>
            <hr />
            <h2>系统环境</h2>
            <p>系统环境：Windows</p>
            <p>开发工具：intellij idea</p>
            <p>Java版本：JDK 1.8</p>
            <p>服务器：tomcat 9.0</p>
            <p>数据库：oracle</p>
            <p>系统采用技术： Servlet+Jsp+Jdbc+easyui框架</p>
    </div>
    <script>
        $(function(){
            $('#dg').datagrid({
                url:'netUserData.json',
                fit:true,           //适应框
                pagination:true,    //显示分页行
                striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                rownumbers:true,     //设置为 true，则显示带有行号的列。
                singleSelect:true,  //设置为 true，则只允许选中一行。
                sortName:'用户编号',
                sortOrder:'desc',
                pageSize:10,         //设置一页最多10行数据，页面显示条目数
                pageList:[10,20,30,40],
                columns:[[
                    {field:'userName',title:'用户姓名',width:100},
                    {field:'uNo',title:'用户编号',width:100},
                    {field:'userPassword',title:'用户密码',width:120},
                    {field:'userAge',title:'用户年龄',width:100},
                    {field:'userSex',title:'用户性别',width:100},
                    {field:'userJob',title:'用户权限',width:100,align:'right'}
                ]]
            })
        });
    </script>
</body>
</html>
