<%@ page import="Service.netUserService.getUserJsonServiceImpl" %><%--
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
    <title>图书管理系统用户--管理人员版</title>
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
            <a href="http://localhost:8888/hello/jsp/equit.jsp" arget="_top" id="exit"  >退出</a>|
            <a href="http://localhost:8888/hello/jsp/login.jsp">切换身份</a>|
            <a href="http://localhost:8888/hello/jsp/ResetAccount.jsp">修改个人信息</a>
        </div>
    </div>

    <!-- 左侧 -->
    <div   data-options="region:'west',title:'功能区',split:true" style="width:200px;">
        <ul id="tt" class="easyui-tree">
            <li iconCls="icon-man" state="closed">
                <span>用户管理</span>
                <ul>
                    <li iconCls="icon-add">
                        <a href="javascript:void(0)"  style="text-decoration: none;"  onclick="userInsert()">
                            <span>增加</span></a>
                    </li>
                    <li  iconCls="icon-remove">
                        <a href="javascript:void(0)"  style="text-decoration: none;" onclick="userDel()">
                        <span>删除</span></a>
                    </li>
                    <li iconCls="icon-search">
                        <a href="javascript:void(0)" onclick="userSearch()" style="text-decoration: none;">
                        <span>查看</span></a>
                    </li>
                    <li iconCls="icon-edit">
                        <a href="javascript:void(0)"   onclick="userUpdate()"  style="text-decoration: none;">
                            <span>修改</span></a>
                    </li>
                </ul>
             </li>

            <li iconCls="icon-book"  state="closed">
                <a href="http://localhost:8888/hello/jsp/specialJsp/bookTable.jsp"  style="text-decoration: none;" target="_blank"><span>图书管理</span></a>
            </li>

            <li iconCls="icon-save"  state="closed">
                <a href="http://localhost:8888/hello/jsp/specialJsp/RBTable.jsp"   style="text-decoration: none;" target="_blank"><span>借书记录管理</span></a>
            </li>

            <li state="closed">
                <a href="http://localhost:8888/hello/jsp/myRB.jsp"   style="text-decoration: none;" target="_blank"><span>个人借书记录管理</span></a>
            </li>
        </ul>
    </div>

    <!--  中央展示区 -->
    <div  id="centerShow" data-options="region:'center',title:'数据区'" style="padding:0px;background:#eee;">

        <div id="tb" style="display:none;">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="userInsert()">增加</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="userDel()">删除</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="userUpdate()">修改</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="userSave()">保存</a>
            <input id="ss" class="easyui-searchbox" style="width:300px"
                   data-options="searcher:qq1,prompt:'Please Input Value',menu:'#mm'"></input>
            <div id="mm" style="width:120px">
                <div data-options="name:'ByUno',iconCls:'icon-ok'">编号</div>
                <div data-options="name:'ByUserName',iconCls:'icon-ok'">用户名</div>
                <div data-options="name:'ByUserJob',iconCls:'icon-ok'">用户权限</div>
                <div data-options="name:'ByUserSex',iconCls:'icon-ok'">用户性别</div>
                <div data-options="name:'ByUserAge',iconCls:'icon-ok'">用户年龄</div>
                <div data-options="name:'ByAllUser',iconCls:'icon-ok'">所有用户</div>
            </div>
        </div>

        <table id="dg"  style="height:100%;width: 100%;"></table>

        <div id="win" class="easyui-window" title="添加用户"
             style="width: 400px; height: 400px;"
             data-options="iconCls:'icon-save',modal:true">
            <div  style="width:100%; border: 0px red solid; " >
                <div style="padding:10px 60px 20px 60px">
                    <form id="ff" name="myForm" method="post">
                        <table cellpadding="3">
                            <tr>
                                <td>账号:</td>
                                <td><input class="easyui-textbox" type="text" name="uNo"
                                           data-options="prompt:'请输入用户编号：10字符内',required:true"></input></td>
                            </tr>

                            <tr>
                                <td>用户名:</td>
                                <td><input class="easyui-textbox" type="text" name="userName"
                                           data-options="prompt:'请输入用户名：20字符内',required:true "></input></td>
                            </tr>

                            <tr>
                                <td>密码:</td>
                                <td><input class="easyui-textbox" type="text" name="userPassword"
                                           data-options="prompt:'请输入用户密码：20字符内',required:true"></input></td>
                            </tr>

                            <tr>
                                <td>用户权限:</td>
                                <td><input class="easyui-textbox" type="text" name="userJob"
                                           data-options="prompt:'请输入common或special',required:true"></input></td>
                            </tr>

                            <tr>
                                <td>性别:</td>
                                <td><input class="easyui-textbox"  type="text" name="usex"
                                           data-options="prompt:'请输入用户性别：男或女',required:true"></input></td>
                            </tr>

                            <tr>
                                <td>年龄:</td>
                                <td><input class="easyui-textbox" type="text" name="uage"
                                           data-options="prompt:'请输入用户年龄',required:true"></input></td>
                            </tr>

                        </table>
                    </form>
                    <div style="text-align:center;padding:5px">
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">添加</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">取消</a>
                    </div>
                </div>
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
        //左侧显示用户功能
        function userSearch(){
            document.getElementById("centerShow").style.display='block';
            document.getElementById('tb').style.display='block';
            $('#dg').datagrid({
                url:'http://localhost:8888/hello/userSearchJsonServlet?task=queryByPage',//把json 流放到缓冲区
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
                    {field:'userName',title:'用户姓名',width:100,editor:'textbox'},
                    {field:'UNo',title:'用户编号',width:100},
                    {field:'userPassword',title:'用户密码',width:120,editor:'textbox'},
                    {field:'uage',title:'用户年龄(岁)',width:100,editor:'numberbox'},
                    {field:'usex',title:'用户性别',width:100,editor:'textbox'},
                    {field:'userJob',title:'用户权限',width:100,align:'right',editor:'textbox'}
                ]],
            });
        };
    </script>

    <script>
        //进入编辑状态 点击修改
        let index = -1;
        //用get
        function userUpdate() {
            //获取选中的行
            var rows  = $('#dg').datagrid("getSelections");
            //获取row对应的index
            if( rows != null ){
                    if(rows.length>1)
                        $.messager.alert('操作错误','请只勾选一行修改的记录');
                    else  if(rows.length==1){
                        var row =  $('#dg').datagrid("getSelected");
                        //将index对应的行编辑状态关闭
                        $('#dg').datagrid("endEdit", index);
                        //进入当前编辑状态：
                        index = $('#dg').datagrid("getRowIndex", row);
                        $('#dg').datagrid("beginEdit", index);
                    }else{
                    $.messager.alert('操作错误','请勾选一行修改的记录');
                }
            }
            else{
                $.messager.alert('操作错误','请勾选一行修改的记录');
            }
        }
        //保存修改的数据
        //用get
        function userSave(){
            //获取选中的行
            let row  = $('#dg').datagrid("getSelected");
            //获取row对应的index
            if( row!= null){
                index = $('#dg').datagrid("getRowIndex",row);
                //将index对应的行编辑状态关闭
                $('#dg').datagrid("endEdit",index);
                //将修改后的数据提交给数据库
               $.get("http://localhost:8888/hello/userSearchJsonServlet?task=update",
                   {
                       "uNo":row.UNo,
                    "userName":row.userName,
                    "userPassword":row.userPassword,
                    "uage":row.uage,
                    "usex":row.usex,
                    "userJob":row.userJob
                   },
                   function (msg){
                        //页面需要刷新
                       $.messager.alert('提示信息', '更新成功!');
                       //页面需要刷新
                       $('#dg').datagrid("reload");
                   }
               );
            }
            else{
                $.messager.alert('操作错误','请勾选一行修改的记录');
            }
        }
        //用get
        function userDel() {
            //getSelected:返回第一个被选中的行或如果没有选中的行则返回null。
            //getChecked:在复选框呗选中的时候返回所有行。（该方法自1.3版开始可用
            //获取选中的行
            if(  document.getElementById("centerShow").style.display!='block'){
                userSearch();
            }
            var row = $('#dg').datagrid("getChecked");
            if(row && row.length>0 ){
                $.messager.confirm('确认信息',
                    '是否确定<span style="color: #ff0000;font-size: 20px;">删除这'+row.length+'条</span>数据',
                    function (r){
                        for(let i = row.length-1;i>=0;i--){
                            if(r){
                                $.get("http://localhost:8888/hello/userSearchJsonServlet?task=delete",
                                    { "uNo":row[i].UNo},
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
        function userInsert(){
            if(  document.getElementById("centerShow").style.display!='block'){
                userSearch();
            }
            $('#win').window({
                width:400,
                height:400,
                modal:true
            });
            $('#win').window('open'); // open a window
        }
    </script>

    <script>
        function validate(){
            var flag=true;

            var x1 = document.forms["myForm"]["userName"].value;
            if(x1.length>20){
                alert("用户名为"+x1+",长度超过20范围");
                flag = false;
            }

            var x2 =document.forms["myForm"]["uNo"].value;
            if(x2.length>10){
                alert("用户编号为"+x2+",长度超过20范围");
                flag = false;
            }

            var x3 = document.forms["myForm"]["userPassword"].value;
            if(x3.length>20){
                alert("密码为"+x3+",长度超过20范围");
                flag = false;
            }

            var x4 = document.forms["myForm"]["uage"].value;
            if(x4 <0|| !isInteger(x4) ){
                alert("年龄为"+x4 +"，该值不在正整数范围内");
                flag = false;
            }

            var x5 = document.forms["myForm"]["usex"].value;
            if( x5!== "男"&& x5!=="女"){
                alert("性别为"+x5+"没有按照要求，请输入男或女");
                flag = false;
            }

            var x6 = document.forms["myForm"]["userJob"].value;
            if( x6!== "common" && x6!=="special"){
                alert("输入的权限为"+x6+"没有按照要求，请输入common或special");
                flag = false;
            }
            if(!flag){
                document.getElementById("myForm").reset();
            }
            return flag;
        }
        function isInteger(obj) {
            return obj%1 === 0
        }
    </script>

    <script>
        function submitForm(){
            validate();
            $('#ff').form('submit', {
                url: "http://localhost:8888/hello/userSearchJsonServlet?task=insert",
                success: function (message) {
                    var jsonItem = JSON.parse(message);
                    var msg = jsonItem.message;
                    if(msg =="ok") {
                        // 更新数据
                        $.messager.alert('提示信息', '添加用户成功!');
                        $('#ff').form('clear');
                        $("#dg").datagrid("reload");
                        // 关闭窗口
                        $("#win").window("close");
                    }
                    else if(msg =="Error"){
                        $.messager.alert('提示信息', '添加用户异常失败!');
                        $('#ff').form('clear');
                        $("#dg").datagrid("reload");
                    }
                    else if(msg =="Exist"){
                        $.messager.alert('提示信息', '用户编号重复!');
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
        function qq1(value,name){
            if(name=="ByUno"){
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    //url:'netUserData.json',
                    url:'http://localhost:8888/hello/userSearchJsonServlet?task=ByUNo&uNo='+value,//把json 流放到缓冲区
                    fit:true,           //适应框
                    pagination:true,    //显示分页行
                    pageSize : 10,// 每页显示的记录条数，默认为10
                    pageList: [10,20,30],//选择一页显示多少数据
                    striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                    rownumbers:true,     //设置为 true，则显示带有行号的列。
                    singleSelect:false,  //设置为 true，则只允许选中一行。
                    pageSize:10,         //设置一页最多10行数据，页面显示条目数
                    toolbar : '#tb', // 工具栏
                    columns:[[
                        {checkbox:true,field:''},
                        {field:'userName',title:'用户姓名',width:100,editor:'textbox'},
                        {field:'UNo',title:'用户编号',width:100},
                        {field:'userPassword',title:'用户密码',width:120,editor:'textbox'},
                        {field:'uage',title:'用户年龄(岁)',width:100,editor:'numberbox'},
                        {field:'usex',title:'用户性别',width:100,editor:'textbox'},
                        {field:'userJob',title:'用户权限',width:100,align:'right',editor:'textbox'}
                    ]],
                });
            }
            else if (name=="ByUserName"){
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    //url:'netUserData.json',
                    url:'http://localhost:8888/hello/userSearchJsonServlet?task=ByName&userName='+value,//把json 流放到缓冲区
                    fit:true,           //适应框
                    pagination:true,    //显示分页行
                    pageSize : 10,// 每页显示的记录条数，默认为10
                    pageList: [10,20,30],//选择一页显示多少数据
                    striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                    rownumbers:true,     //设置为 true，则显示带有行号的列。
                    singleSelect:false,  //设置为 true，则只允许选中一行。
                    pageSize:10,         //设置一页最多10行数据，页面显示条目数
                    toolbar : '#tb', // 工具栏
                    columns:[[
                        {checkbox:true,field:''},
                        {field:'userName',title:'用户姓名',width:100,editor:'textbox'},
                        {field:'UNo',title:'用户编号',width:100},
                        {field:'userPassword',title:'用户密码',width:120,editor:'textbox'},
                        {field:'uage',title:'用户年龄(岁)',width:100,editor:'numberbox'},
                        {field:'usex',title:'用户性别',width:100,editor:'textbox'},
                        {field:'userJob',title:'用户权限',width:100,align:'right',editor:'textbox'}
                    ]],
                });
            }
            else if (name=="ByUserJob"){//ByUserJob
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    //url:'netUserData.json',
                    url:'http://localhost:8888/hello/userSearchJsonServlet?task=ByJob&userJob='+value,//把json 流放到缓冲区
                    fit:true,           //适应框
                    pagination:true,    //显示分页行
                    pageSize : 10,// 每页显示的记录条数，默认为10
                    pageList: [10,20,30],//选择一页显示多少数据
                    striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                    rownumbers:true,     //设置为 true，则显示带有行号的列。
                    singleSelect:false,  //设置为 true，则只允许选中一行。
                    pageSize:10,         //设置一页最多10行数据，页面显示条目数
                    toolbar : '#tb', // 工具栏
                    columns:[[
                        {checkbox:true,field:''},
                        {field:'userName',title:'用户姓名',width:100,editor:'textbox'},
                        {field:'UNo',title:'用户编号',width:100},
                        {field:'userPassword',title:'用户密码',width:120,editor:'textbox'},
                        {field:'uage',title:'用户年龄(岁)',width:100,editor:'numberbox'},
                        {field:'usex',title:'用户性别',width:100,editor:'textbox'},
                        {field:'userJob',title:'用户权限',width:100,align:'right',editor:'textbox'}
                    ]],
                });
            }
            else if (name=="ByUserSex"){//ByUserJob
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    //url:'netUserData.json',
                    url:'http://localhost:8888/hello/userSearchJsonServlet?task=BySex&userSex='+value,//把json 流放到缓冲区
                    fit:true,           //适应框
                    pagination:true,    //显示分页行
                    pageSize : 10,// 每页显示的记录条数，默认为10
                    pageList: [10,20,30],//选择一页显示多少数据
                    striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                    rownumbers:true,     //设置为 true，则显示带有行号的列。
                    singleSelect:false,  //设置为 true，则只允许选中一行。
                    pageSize:10,         //设置一页最多10行数据，页面显示条目数
                    toolbar : '#tb', // 工具栏
                    columns:[[
                        {checkbox:true,field:''},
                        {field:'userName',title:'用户姓名',width:100,editor:'textbox'},
                        {field:'UNo',title:'用户编号',width:100},
                        {field:'userPassword',title:'用户密码',width:120,editor:'textbox'},
                        {field:'uage',title:'用户年龄(岁)',width:100,editor:'numberbox'},
                        {field:'usex',title:'用户性别',width:100,editor:'textbox'},
                        {field:'userJob',title:'用户权限',width:100,align:'right',editor:'textbox'}
                    ]],
                });
            }
            else if (name=="ByUserAge"){//ByUserJob
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    //url:'netUserData.json',
                    url:'http://localhost:8888/hello/userSearchJsonServlet?task=ByAge&userAge='+value,//把json 流放到缓冲区
                    fit:true,           //适应框
                    pagination:true,    //显示分页行
                    pageSize : 10,// 每页显示的记录条数，默认为10
                    pageList: [10,20,30],//选择一页显示多少数据
                    striped:true,       //设置为 true，则把行条纹化。（即奇偶行使用不同背景色）
                    rownumbers:true,     //设置为 true，则显示带有行号的列。
                    singleSelect:false,  //设置为 true，则只允许选中一行。
                    pageSize:10,         //设置一页最多10行数据，页面显示条目数
                    toolbar : '#tb', // 工具栏
                    columns:[[
                        {checkbox:true,field:''},
                        {field:'userName',title:'用户姓名',width:100,editor:'textbox'},
                        {field:'UNo',title:'用户编号',width:100},
                        {field:'userPassword',title:'用户密码',width:120,editor:'textbox'},
                        {field:'uage',title:'用户年龄(岁)',width:100,editor:'numberbox'},
                        {field:'usex',title:'用户性别',width:100,editor:'textbox'},
                        {field:'userJob',title:'用户权限',width:100,align:'right',editor:'textbox'}
                    ]],
                });
            }
            else if(name=="ByAllUser"){
                userSearch();
            }
        }
    </script>

    </body>
</html>
