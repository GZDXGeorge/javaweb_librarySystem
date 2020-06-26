<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/23
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的借书情况(内部版)</title>
    <link rel="stylesheet" type ="text/css" href="http://localhost:8888/hello/net/css/style.css">
    <script type="text/javascript" src="../jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../jeasyui/jquery.easyui.min.js"></script>
    <script>
        $(function(){　　//初始加载，表格宽度自适应
            $(document).ready(function(){
                fitCoulms();
            });　　//浏览器窗口大小变化后，表格宽度自适应
            $(window).resize(function(){
                fitCoulms();
            });
        })
        //表格宽度自适应，这里的#dg是datagrid表格生成的div标签
        function fitCoulms(){
            $('#dg').datagrid({
                fitColumns:true
            });
        }
    </script>
</head>

<body class="easyui-layout">

<!-- 左侧 -->

<div   data-options="region:'west',title:'功能区',split:true" style="width:200px;">
    <ul id="tt" class="easyui-tree">

        <li iconCls="icon-man" state="closed">
            <a href="http://localhost:8888/hello/jsp/specialJsp/userTable.jsp"  style="text-decoration: none;" target="_blank">
                <span>用户管理</span></a>
        </li>

        <li iconCls="icon-book"  state="closed">
            <a href="http://localhost:8888/hello/jsp/specialJsp/bookTable.jsp"  style="text-decoration: none;" target="_blank"><span>图书管理</span></a>
        </li>

        <li iconCls="icon-save"  state="closed">
            <a href="http://localhost:8888/hello/jsp/specialJsp/RBTable.jsp"   style="text-decoration: none;" target="_blank"><span>借书记录管理</span></a>
        </li>

        <li   state="closed">
            <span>个人借书记录管理</span>
            <ul>
                <li iconCls="icon-remove">
                    <a href="javascript:void(0)" onclick="rBDelete()" style="text-decoration: none;">
                        <span>还书</span></a>
                <li iconCls="icon-search">
                    <a href="javascript:void(0)" onclick="rBSearch()" style="text-decoration: none;">
                        <span>查看</span></a>
                </li>

            </ul>
        </li>

    </ul>
</div>
    <!-- 顶部 -->
    <div data-options="region:'north',title:'图书馆管理系统其他功能',split:false"
         style="height:0px;">
        <div class="topDiv" style="float:right;width:200px;margin-right:8px; ">
            <a href="http://localhost:8888/hello/jsp/equit.jsp" arget="_top" id="exit" onclick="quit()" >退出</a>|
            <a href="http://localhost:8888/hello/jsp/login.jsp">切换身份</a>|
            <a href="http://localhost:8888/hello/jsp/ResetAccount.jsp">修改个人信息</a>
        </div>
    </div>

    <!--  中央展示区 -->
    <div  id="centerShow" data-options="region:'center',title:'数据区'" style="padding:0px;background:#eee;">
        <table id="dg"  style="height:100%;width: 100%;"></table>
    </div>

<script>
    function rBSearch(){
        document.getElementById("centerShow").style.display='block';
        $('#dg').datagrid({
            url:'http://localhost:8888/hello/myRBServlet?task=queryByPage',//把json 流放到缓冲区
            fit:true,           //适应框
            pagination:true,    //显示分页行
            pageList: [2,4,8,16,32,50],//选择一页显示多少数据
            striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
            rownumbers:true,     //设置为 true，则显示带有行号的列。
            singleSelect:false,  //设置为 true，则只允许选中一行。
            pageSize:2,         //设置一页最多10行数据，页面显示条目数
            toolbar : '#tb', // 工具栏
            columns:[[
                {checkbox:true,field:''},
                {field:'rbDate',title:'借书时间',width:100},
                {field:'bNo',title:'书籍编号',width:100},
                {field:'bName',title:'图书名字',width:100},
                {field:'bAuthor',title:'书籍作者',width:100},
                {field:'picutreUrl',title:'图片',width:120},
            ]],
            onClickCell:function(index,field,value){
                if(value.startsWith("net/bookImage/") && value.match("net(/\\S+)"))
                    window.open("http://localhost:8888/hello/"+value);
            }
        });
    };

    function rBDelete(){
        //getSelected:返回第一个被选中的行或如果没有选中的行则返回null。
        //getChecked:在复选框呗选中的时候返回所有行。（该方法自1.3版开始可用
        //获取选中的行
        if(  document.getElementById("centerShow").style.display!='block'){
            rBSearch();
        }
        var row = $('#dg').datagrid("getChecked");
        if(row && row.length>0 ){
            $.messager.confirm('确认信息',
                '是否确定<span style="color: #ff0000;font-size: 20px;">删除这'+row.length+'条</span>数据',
                function (r){
                    for(let i = row.length-1;i>=0;i--){
                        if(r){
                            $.get("http://localhost:8888/hello/myRBServlet?task=delete",
                                {
                                    "BNo":row[i].bNo
                                },
                                function(result){
                                    $('#dg').datagrid("reload");//刷新页面  reload：删除之后会在当前页面  当删除本页所有数据之后会跳到前一个页面
                                }
                            )
                        }
                    }
                }
            );
        }
        else{
            $.messager.alert('操作错误', '请勾选一行修改的记录');
        }
    }

</script>

</body>
</html>

