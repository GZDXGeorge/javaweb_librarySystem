<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/19
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理系统借书记录--管理人员版</title>
    <link rel="stylesheet" type ="text/css" href="http://localhost:8888/hello/net/css/style.css">
    <script type="text/javascript" src="../../jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../jeasyui/jquery.easyui.min.js"></script>
    <script>
        $(function(){　　//初始加载，表格宽度自适应
            $(document).ready(function(){
                fitCoulms();
                // 页面加载完成 关闭窗口
                $("#win").window("close");
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
<!-- 顶部 -->
<div data-options="region:'north',title:'图书馆管理系统其他功能',split:false"
     style="height:0px;">
    <div class="topDiv" style="float:right;width:200px;margin-right:8px; ">
        <a href="http://localhost:8888/hello/jsp/equit.jsp" arget="_top" id="exit" onclick="quit()" >退出</a>|
        <a href="http://localhost:8888/hello/jsp/login.jsp">切换身份</a>|
        <a href="http://localhost:8888/hello/jsp/ResetAccount.jsp">修改个人信息</a>
    </div>
</div>

<!-- 左侧 -->

<div   data-options="region:'west',title:'功能区',split:true" style="width:200px;">
    <ul id="tt" class="easyui-tree">

        <li iconCls="icon-man" state="closed">
            <a href="http://localhost:8888/hello/jsp/specialJsp/userTable.jsp"  style="text-decoration: none;" target="_blank"><span>用户管理</span></a>
        </li>

        <li iconCls="icon-book"  state="closed">
            <a href="http://localhost:8888/hello/jsp/specialJsp/bookTable.jsp"  style="text-decoration: none;"   target="_blank"><span>图书管理</span></a>
        </li>

        <li iconCls="icon-save"  state="closed">
            <span>借书记录管理</span>
            <ul>
                <li iconCls="icon-add">
                    <a href="javascript:void(0)" onclick="rBInsert()" style="text-decoration: none;">
                        <span>增加</span></a>
                </li>
                <li iconCls="icon-remove">
                    <a href="javascript:void(0)" onclick="rBDelete()" style="text-decoration: none;">
                        <span>删除</span></a>
                <li iconCls="icon-search">
                    <a href="javascript:void(0)" onclick="rBSearch()" style="text-decoration: none;">
                        <span>查看</span></a>
                </li>

            </ul>
        </li>

        <li state="closed">
            <a href="http://localhost:8888/hello/jsp/myRB.jsp"   style="text-decoration: none;" target="_blank"><span>个人借书记录管理</span></a>
        </li>
    </ul>
</div>

<!--  中央展示区 -->
<div  id="centerShow" data-options="region:'center',title:'数据区'" style="padding:0px;background:#eee;">

    <div id="tb" style="display:none;">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="rBInsert()">增加</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="rBDelete()">删除</a>

        <input id="ss" class="easyui-searchbox" style="width:350px"
               data-options="searcher:qq,prompt:'Please Input Value',menu:'#mm'"></input>
        <div id="mm" style="width:120px">
            <div data-options="name:'ByBNo',iconCls:'icon-ok'">书籍编号</div>
            <div data-options="name:'ByUNo',iconCls:'icon-ok'">用户编号</div>
            <div data-options="name:'ByRBDate',iconCls:'icon-ok'">借书时间(以'-'分开)</div>
            <div data-options="name:'ByAll',iconCls:'icon-ok'">所有记录</div>
        </div>
    </div>

    <table id="dg"  style="height:100%;width: 100%;"></table>

    <div  title="添加借书记录" id="win"  style="width: 296px; height: 200px;" class="easyui-window"
          data-options="iconCls:'icon-save',modal:true">
        <div style="float:left;">
            <form  method="POST"  name="myForm" id ="ff">
                <table>
                    <tr>
                        <td>用户编号 </td> <td><input class="easyui-textbox" style="width:200px" type="text" name="uNo"  data-options="prompt:'请输入用户编号：10字符内',required:true" ></td>
                    </tr>
                    <tr>
                        <td>图书编号 </td> <td><input class="easyui-textbox" style="width:200px" type="text" name="bNo"  data-options="prompt:'请输入图书编号：10字符内',required:true" ></td>
                    </tr>
                    <tr>
                        <td>借书时间</td> <td><input class="easyui-textbox" style="width:200px" type="text" name="rBDate"  data-options="prompt:'借书时间格式：yyyy-MM-dd'" ></td>
                    </tr>
                </table>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">添加</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">取消</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 底部版权 -->
<div data-options="region:'south',title:'版权信息',split:true" style="height:100px;top:528px; color:red; text-align: center">
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
    function isnull(val) {

        var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格;

        if (str == '' || str == undefined || str == null) {
            return true;
        } else {
            return false;
        }
    }
    function validate(){
        var flag=true;
        var x1 = document.forms["myForm"]["bNo"].value;
        var x2 = document.forms["myForm"]["uNo"].value;
        var x3 = document.forms["myForm"]["rBDate"].value;

        if(x1.length>10){
            alert("图书编号为"+x1+",长度超过10范围");
            flag = false;
        }

        if(x2.length>10){
            alert("用户编号为"+x2+",长度超过10范围");
            flag = false;
        }

        var r = new RegExp(/^\d{4}\-\d{2}\-\d{2}$/);

        if( !isnull(x3) && !r.test(x3)){
            alert("请按照yyyy-mm-dd格式输入时间");
            flag = false;
        }

        if(!flag){
            document.getElementById("myForm").reset();
        }
        return flag;
    }
    function validate1(){
        var flag=true;
        var x1 = $("#ss").textbox('getValue');
        var r = new RegExp(/^\d{4}\-\d{2}\-\d{2}$/);
        if( !isnull(x1) && !r.test(x1)){
            alert("请按照yyyy-mm-dd格式输入时间");
            flag = false;
        }else if(isnull(x1)){
            alert("请按照yyyy-mm-dd格式输入时间");
            flag = false;
        }
        if(!flag){
            document.getElementById("myForm").reset();
        }
        return flag;
    }
    function rBInsert(){
        if(  document.getElementById("centerShow").style.display!='block'){
            rBSearch();
        }
        $('#win').window({
            width:296,
            height:200,
            modal:true,//定义窗口是不是模态（modal）窗口。
            minimizable:false,
            maximizable:false
        });
        $('#win').window('open'); // open a window
    }
    function rBSearch(){
        document.getElementById("centerShow").style.display='block';
        document.getElementById('tb').style.display='block';
        $('#dg').datagrid({
            url:'http://localhost:8888/hello/rBJsonServlet?task=queryByPage',//把json 流放到缓冲区
            fit:true,           //适应框
            pagination:true,    //显示分页行
            pageList: [10,15,20,50],//选择一页显示多少数据
            striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
            rownumbers:true,     //设置为 true，则显示带有行号的列。
            singleSelect:false,  //设置为 true，则只允许选中一行。
            pageSize:10,         //设置一页最多10行数据，页面显示条目数
            toolbar : '#tb', // 工具栏
            columns:[[
                {checkbox:true,field:''},
                {field:'UNo',title:'用户编号',width:100},
                {field:'BNo',title:'书籍编号',width:100},
                {field:'rbDate',title:'借书时间',width:100}
            ]]
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
                            $.get("http://localhost:8888/hello/rBJsonServlet?task=delete",
                                {
                                    "UNo":row[i].UNo,
                                    "BNo":row[i].BNo
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

<script>
    function submitForm(){
        validate();
        $('#ff').form('submit', {
            url: "http://localhost:8888/hello/rBJsonServlet?task=insert",
            success: function (message) {
                var jsonItem = JSON.parse(message);
                var msg = jsonItem.message;
                if(msg =="ok") {
                    // 更新数据
                    $.messager.alert('提示信息', '添加记录成功!');
                    $('#ff').form('clear');
                    $("#dg").datagrid("reload");
                    // 关闭窗口
                    $("#win").window("close");
                }
                else if(msg =="Error"){
                    $.messager.alert('提示信息', '添加记录异常失败!');
                    $('#ff').form('clear');
                    $("#dg").datagrid("reload");
                }
                else if(msg =="Exist"){
                    $.messager.alert('提示信息', '记录编号重复!');
                    $('#ff').form('clear');
                    $("#dg").datagrid("reload");
                }
                else if(msg =="NotExist"){
                    $.messager.alert('提示信息', '用户或图书编号不存在!');
                    $('#ff').form('clear');
                    $("#dg").datagrid("reload");
                }

            }
        });
    }
    function clearForm(){
        //清空
        $('#ff').form('clear');
    }
    function qq(value,name){
        if(name=="ByBNo"){
            document.getElementById("centerShow").style.display='block';
            document.getElementById('tb').style.display='block';
            $('#dg').datagrid({
                url:'http://localhost:8888/hello/rBJsonServlet?task=queryByBNo&bNo='+value,//把json 流放到缓冲区
                fit:true,           //适应框
                pagination:true,    //显示分页行
                pageList: [10,15,20,50],//选择一页显示多少数据
                striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                rownumbers:true,     //设置为 true，则显示带有行号的列。
                singleSelect:false,  //设置为 true，则只允许选中一行。
                //pageSize:10,         //设置一页最多10行数据，页面显示条目数
                toolbar : '#tb', // 工具栏
                columns:[[
                    {checkbox:true,field:''},
                    {field:'UNo',title:'用户编号',width:100},
                    {field:'BNo',title:'书籍编号',width:100},
                    {field:'rbDate',title:'借书时间',width:100}
                ]]
            });
        }
        else if(name=="ByUNo"){
            document.getElementById("centerShow").style.display='block';
            document.getElementById('tb').style.display='block';
            $('#dg').datagrid({
                url:'http://localhost:8888/hello/rBJsonServlet?task=queryByUNo&uNo='+value,//把json 流放到缓冲区
                fit:true,           //适应框
                pagination:true,    //显示分页行
                pageList: [10,15,20,50],//选择一页显示多少数据
                striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                rownumbers:true,     //设置为 true，则显示带有行号的列。
                singleSelect:false,  //设置为 true，则只允许选中一行。
                //pageSize:10,         //设置一页最多10行数据，页面显示条目数
                toolbar : '#tb', // 工具栏
                columns:[[
                    {checkbox:true,field:''},
                    {field:'UNo',title:'用户编号',width:100},
                    {field:'BNo',title:'书籍编号',width:100},
                    {field:'rbDate',title:'借书时间',width:100}
                ]]
            });
        }
        else if(name=="ByRBDate"){
            validate1();
            document.getElementById("centerShow").style.display='block';
            document.getElementById('tb').style.display='block';
            $('#dg').datagrid({
                url:'http://localhost:8888/hello/rBJsonServlet?task=queryByRBDate&rBDate='+value,//把json 流放到缓冲区
                fit:true,           //适应框
                pagination:true,    //显示分页行
                pageList: [10,15,20,50],//选择一页显示多少数据
                striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                rownumbers:true,     //设置为 true，则显示带有行号的列。
                singleSelect:false,  //设置为 true，则只允许选中一行。
                //pageSize:10,         //设置一页最多10行数据，页面显示条目数
                toolbar : '#tb', // 工具栏
                columns:[[
                    {checkbox:true,field:''},
                    {field:'UNo',title:'用户编号',width:100},
                    {field:'BNo',title:'书籍编号',width:100},
                    {field:'rbDate',title:'借书时间',width:100}
                ]]
            });
        }
        else if(name=="ByAll"){
            rBSearch();
        }
    }
</script>

</body>
</html>
