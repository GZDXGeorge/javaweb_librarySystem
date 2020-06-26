<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/19
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理系统书籍--管理人员版</title>
    <link rel="stylesheet" type ="text/css" href="http://localhost:8888/hello/net/css/style.css">
    <script type="text/javascript" src="../../jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../jeasyui/jquery.easyui.min.js"></script>
    <script>
        $(function(){　　//初始加载，表格宽度自适应
            $(document).ready(function(){
                fitCoulms();
                // 页面加载完成 关闭窗口
                $("#win").window("close");
                $("#win1").window("close");
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
            <a href="http://localhost:8888/hello/jsp/equit.jsp" arget="_top" id="exit" >退出</a>|
            <a href="http://localhost:8888/hello/jsp/login.jsp">切换身份</a>|
            <a href="http://localhost:8888/hello/jsp/ResetAccount.jsp">修改个人信息</a>
        </div>
    </div>

    <!-- 左侧 -->
    <div   data-options="region:'west',title:'功能区',split:true" style="width:200px;">
        <ul id="tt" class="easyui-tree">
            <li iconCls="icon-man" state="closed">
                <a href="http://localhost:8888/hello/jsp/specialJsp/userTable.jsp"  style="text-decoration: none;" target="_blank">
                    <span>用户管理</span></a>
            </li>

            <li iconCls="icon-book"  state="closed">
                <span>图书管理</span>
                <ul>
                    <li iconCls="icon-add">
                        <a href="javascript:void(0)"  style="text-decoration: none;"  onclick="bookInsert()">
                            <span>增加</span></a></li>

                    <li iconCls="icon-remove">
                        <a href="javascript:void(0)"  style="text-decoration: none;"  onclick="bookDel()">
                            <span>删除</span></a></li>

                    <li iconCls="icon-search">
                        <a href="javascript:void(0)" onclick="bookSearch()" style="text-decoration: none;">
                            <span>查看</span></a>
                    </li>

                    <li iconCls="icon-edit">
                        <a href="javascript:void(0)" onclick="bookUpdate()" style="text-decoration: none;">
                            <span>修改</span></a>
                    </li>

                </ul>
            </li>

            <li iconCls="icon-save"  state="closed">
                <a href="http://localhost:8888/hello/jsp/specialJsp/RBTable.jsp"  style="text-decoration: none;"  target="_blank">
                    <span>借书记录管理</span></a>
            </li>

            <li state="closed">
                <a href="http://localhost:8888/hello/jsp/myRB.jsp"   style="text-decoration: none;" target="_blank"><span>个人借书记录管理</span></a>
            </li>
        </ul>
    </div>

    <!--  中央展示区 -->
    <div  id="centerShow" data-options="region:'center',title:'数据区'" style="padding:0px;background:#eee;">

        <div id="tb" style="display:none;">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="bookInsert()">增加</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="bookDel()">删除</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="bookUpdate()">修改</a>
            <input id="ss" class="easyui-searchbox" style="width:300px"
                   data-options="searcher:qq1,prompt:'Please Input Value',menu:'#mm'"></input>
            <div id="mm" style="width:120px">
                <div data-options="name:'ByBNo',iconCls:'icon-ok'">编号</div>
                <div data-options="name:'ByBName',iconCls:'icon-ok'">书名</div>
                <div data-options="name:'ByBAuthor',iconCls:'icon-ok'">作者</div>
                <div data-options="name:'ByBPrice',iconCls:'icon-ok'">价格</div>
                <div data-options="name:'ByBPress',iconCls:'icon-ok'">出版社</div>
                <div data-options="name:'ByAllBook',iconCls:'icon-ok'">所有图书</div>
            </div>
        </div>

        <table id="dg"  style="height:100%;width: 100%;"></table>

        <div  title="添加图书" id="win" class="easyui-window" style="width: 530px; height: 300px;"
             data-options="iconCls:'icon-save',modal:true">
                <div style="float:left;">
                    <form  method="POST"  name="myForm" id ="ff" enctype="multipart/form-data">
                        <table >
                            <tr>
                                 <td>编号 </td> <td><input class="easyui-textbox" style="width:200px" type="text" name="BNo"  data-options="prompt:'请输入图书编号：10字符内',required:true" ></td>
                            </tr>
                            <tr>
                                <td>名字 </td> <td> <input class="easyui-textbox"  style="width:200px" type="text" name="BName" data-options="prompt:'请输入图书名字：50字符内',required:true" ></td>
                            </tr>
                             <tr>
                                <td>作者 </td> <td> <input  class="easyui-textbox"  style="width:200px" type="text" name="BAuthor" data-options="prompt:'请输入图书作者：50字符内',required:true" ></td>
                             </tr>
                            <tr>
                                <td>出版社</td> <td><input class="easyui-textbox"  style="width:200px" type="text" name="BPress" data-options="prompt:'请输入出版社：50字符内',required:true" ></td>
                            </tr>
                            <tr>
                                <td>价格</td>  <td><input class="easyui-textbox"   style="width:200px" type="text" name="BPrice" data-options="prompt:'请输入价格',required:true" ></td>
                            </tr>
                            <tr>
                                <td>照片</td>
                                <td><input name="picutreUrl" id="file"  style="width:200px" value="上传文件"
                                                 class="easyui-filebox"  accept="image/*" data-options="
                                  onChange: function(value){
                                      var f = $(this).next().find('input[type=file]')[0];
                                      if (f.files && f.files[0]){
                                          var reader = new FileReader();
                                          reader.onload = function(e){
                                              $('#image').attr('src', e.target.result);
                                         }
                                         reader.readAsDataURL(f.files[0]);
                                     }
                                 }"></td>
                            </tr>
                        </table>
                        <div style="text-align:center;padding:5px">
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">添加</a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">取消</a>
                        </div>
                    </form>
                </div>
                <div style="float:right; width: 250px;height: 300px; " >
                    <img src="#" alt="预览图片" id="image" style="height: 100%;width: 100%">
                </div>
        </div>

        <div  title="修改图书" id="win1" class="easyui-window" style="width: 530px; height: 300px;"
              data-options="iconCls:'icon-save',modal:true">
            <div style="float:left;">
                <form  method="POST"  name="myForm1" id ="ff1" enctype="multipart/form-data">
                    <table >
                        <tr>
                            <td>编号 </td> <td><input class="easyui-textbox" style="width:200px" type="text" name="BNo" id="bno1" readonly></td>
                        </tr>
                        <tr>
                            <td>名字 </td> <td> <input class="easyui-textbox" id="bname1" style="width:200px" type="text" name="BName" ></td>
                        </tr>
                        <tr>
                            <td>作者 </td> <td> <input  class="easyui-textbox" id="bauthor1" style="width:200px" type="text" name="BAuthor"></td>
                        </tr>
                        <tr>
                            <td>出版社</td> <td><input class="easyui-textbox" id="bpress1" style="width:200px" type="text" name="BPress" ></td>
                        </tr>
                        <tr>
                            <td>价格</td>  <td><input class="easyui-textbox"  id="bprice1" style="width:200px" type="text" name="BPrice"></td>
                        </tr>
                        <tr>
                            <td>照片</td>
                            <td><input name="picutreUrl1" id="file1"  style="width:200px" value="修改上传文件"
                                       class="easyui-filebox"  accept="image/*" data-options="
                                  onChange: function(value){
                                      var f = $(this).next().find('input[type=file]')[0];
                                      if (f.files && f.files[0]){
                                          var reader = new FileReader();
                                          reader.onload = function(e){
                                              $('#image1').attr('src', e.target.result);
                                         }
                                         reader.readAsDataURL(f.files[0]);
                                     }
                                 }"></td>
                        </tr>
                    </table>
                    <div style="text-align:center;padding:5px">
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm1()">修改</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm1()">取消</a>
                    </div>
                </form>
            </div>
            <div style="float:right; width: 250px;height: 300px; " >
                <img src="#" alt="预览图片" id="image1" style="height: 100%;width: 100%">
            </div>
        </div>

    </div>

    <!-- 底部版权 -->
    <div data-options="region:'south',title:'版权信息',sfplit:true" style="height:100px;top:528px; color:red; text-align: center">
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
        function validate(){
            var flag=true;

            var x1 = document.forms["myForm"]["BNo"].value;
            if(x1.length>10){
                alert("图书编号为"+x1+",长度超过10范围");
                flag = false;
            }

            var x2 =document.forms["myForm"]["BName"].value;
            if(x2.length>50){
                alert("图书名字为"+x2+",长度超过50范围");
                flag = false;
            }

            var x3 = document.forms["myForm"]["BAuthor"].value;
            if(x3.length>50){
                alert("图书作者为"+x3+",长度超过50范围");
                flag = false;
            }

            var x4 = document.forms["myForm"]["BPress"].value;
            if(x4.length>50){
                alert("图书出版社为"+x4+",长度超过50范围");
                flag = false;
            }

            var x5 = document.forms["myForm"]["BPrice"].value;
            if(x5 <=0 || isNaN(x5) ){
                alert("图书价格为"+x5 +"，该值不在正数范围内");
                flag = false;
            }

            if(!flag){
                document.getElementById("myForm").reset();
            }
            return flag;
        }
        function validate1(){
            var flag=true;
            var x2 =document.forms["myForm1"]["BName"].value;
            if(x2.length>80){
                alert("图书名字为"+x2+",长度超过80范围");
                flag = false;
            }

            var x3 = document.forms["myForm1"]["BAuthor"].value;
            if(x3.length>50){
                alert("图书作者为"+x3+",长度超过50范围");
                flag = false;
            }

            var x4 = document.forms["myForm1"]["BPress"].value;
            if(x4.length>50){
                alert("图书出版社为"+x4+",长度超过50范围");
                flag = false;
            }

            var x5 = document.forms["myForm1"]["BPrice"].value;
            if(x5 <=0 || isNaN(x5) ){
                alert("图书价格为"+x5 +"，该值不在正数范围内");
                flag = false;
            }

            if(!flag){
                document.getElementById("myForm1").reset();
            }
            return flag;
        }
        function clearForm(){
            //清空
            $('#ff').form('clear');
        }
        function clearForm1(){
            //清空
            $('#ff1').form('clear');
        }
        function submitForm(){
            validate();
            $('#ff').form('submit', {
                url: "http://localhost:8888/hello/bookJsonServlet?task=insert",
                contentType: false, //不设置内容类型
                processData: false, //不处理数据
                success: function (message) {
                    let jsonItem = JSON.parse(message);
                    let msg = jsonItem.message;
                    if(msg =="ok") {
                        // 更新数据
                        $.messager.alert('提示信息', '添加书籍成功!');
                        $('#ff').form('clear');
                        $("#dg").datagrid("reload");
                        // 关闭窗口
                        $("#win").window("close");
                        document.getElementById("image").src="#";
                    }
                    else if(msg =="Error"){
                        $.messager.alert('提示信息', '添加书籍异常失败!');
                        $('#ff').form('clear');
                        $("#dg").datagrid("reload");
                    }
                    else if(msg =="Exist"){
                        $.messager.alert('提示信息', '书籍编号重复!');
                        $('#ff').form('clear');
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }
        function submitForm1(){
            validate1();
            $('#ff1').form('submit', {
                url: "http://localhost:8888/hello/bookJsonServlet?task=update",
                contentType: false, //不设置内容类型
                processData: false, //不处理数据
                success: function (message) {
                    let jsonItem = JSON.parse(message);
                    let msg = jsonItem.message;
                    if(msg =="ok") {
                        // 更新数据
                        $.messager.alert('提示信息', '修改书籍成功!');
                        $('#ff1').form('clear');
                        $("#dg").datagrid("reload");
                        // 关闭窗口
                        $("#win1").window("close");
                        document.getElementById("image1").src="#";
                    }
                    else if(msg =="Error"){
                        $.messager.alert('提示信息', '修改书籍异常失败!');
                        $('#ff1').form('clear');
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }
    </script>

    <!--左侧功能区-->
    <script>
        //左侧显示用户功能
        function bookSearch(){
            document.getElementById("centerShow").style.display='block';
            document.getElementById('tb').style.display='block';
            $('#dg').datagrid({
                url:'http://localhost:8888/hello/bookJsonServlet?task=queryByPage',//把json 流放到缓冲区
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
                    {field:'BName',title:'图书名字',width:100},
                    {field:'BNo',title:'书籍编号',width:100,editor:'textbox'},
                    {field:'BAuthor',title:'书籍作者',width:100},
                    {field:'BPress',title:'出版社',width:120},
                    {field:'BPrice',title:'书籍价格',width:100,editor:'textbox'},
                    {field:'picutreUrl',title:'图片',width:120},
                ]],
                onClickCell:function(index,field,value){
                    if(value.startsWith("net/bookImage/") && value.match("net(/\\S+)"))
                        window.open("http://localhost:8888/hello/"+value);
                }
            });
        };
        function bookInsert(){
            if(  document.getElementById("centerShow").style.display!='block'){
                 bookSearch();
            }
            $('#win').window({
                width:600,
                height:340,
                modal:true
            });
            $('#win').window('open'); // open a window
        }
        function bookDel(){
            //getSelected:返回第一个被选中的行或如果没有选中的行则返回null。
            //getChecked:在复选框呗选中的时候返回所有行。（该方法自1.3版开始可用
            //获取选中的行
            if(  document.getElementById("centerShow").style.display!='block'){
                bookSearch();
            }
            var row = $('#dg').datagrid("getChecked");
            if(row && row.length>0 ){
                $.messager.confirm('确认信息',
                    '是否确定<span style="color: #ff0000;font-size: 20px;">删除这'+row.length+'条</span>数据',
                    function (r){
                        for(let i = row.length-1;i>=0;i--){
                            if(r){
                                $.get("http://localhost:8888/hello/bookJsonServlet?task=delete",
                                    { "BNo":row[i].BNo},
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
        function bookUpdate(){
            if(  document.getElementById("centerShow").style.display!='block'){
                bookSearch();
            }
            //获取选中的行
            var rows  = $('#dg').datagrid("getSelections");
            //获取row对应的index
            if( rows != null ){
                if(rows.length>1)
                    $.messager.alert('操作错误','请只勾选一行修改的记录');
                else  if(rows.length==1){
                    $('#win1').window({
                        width:600,
                        height:340,
                        modal:true
                    });
                    $('#win1').window('open'); // open a window

                    let row  = $('#dg').datagrid("getSelected");

                    let img = document.getElementById("image1");
                    img.src = "http://localhost:8888/hello/"+row.picutreUrl ;

                    $("#bno1").textbox("setValue",row.BNo);
                    $("#bname1").textbox("setValue",row.BName);
                    $("#bauthor1").textbox("setValue",row.BAuthor);
                    $("#bpress1").textbox("setValue",row.BPress);
                    $("#bprice1").textbox("setValue",row.BPrice);
                }
                else{
                    $.messager.alert('操作错误','请勾选一行修改的记录');
                }
            }
            else{
                $.messager.alert('操作错误','请勾选一行修改的记录');
            }
        }
    </script>

    <script>
        function changeSpecialCharToUrl(value){//不支持%符号
            while(value.indexOf('+') >= 0)
                value = value.replace('+','%2B');
            while(value.indexOf('?') >= 0)
                value = value.replace('?','%3F');
            while(value.indexOf(' ') >= 0)
                value = value.replace(' ',' %20');
            while(value.indexOf('/') >= 0)
                value = value.replace('/',' %2F');
            while(value.indexOf('#') >= 0)
                value = value.replace('#','%23');
            while(value.indexOf('&') >= 0)
                value = value.replace('&',' %26');
            while(value.indexOf('=') >= 0)
                value = value.replace('=','%3D');
            return value;
        }
        function qq1(value,name){
            if(name=="ByBNo"){
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    url:'http://localhost:8888/hello/bookJsonServlet?task=queryByBNo&bNo='+value,//把json 流放到缓冲区
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
                        {field:'BName',title:'图书名字',width:100},
                        {field:'BNo',title:'书籍编号',width:100,editor:'textbox'},
                        {field:'BAuthor',title:'书籍作者',width:100},
                        {field:'BPress',title:'出版社',width:120},
                        {field:'BPrice',title:'书籍价格',width:100,editor:'textbox'},
                        {field:'picutreUrl',title:'图片',width:120},
                    ]],
                    onClickCell:function(index,field,value){
                        if(value.startsWith("net/bookImage/") && value.match("net(/\\S+)"))
                            window.open("http://localhost:8888/hello/"+value);
                    }
                });
            }
            else if(name=="ByBName"){
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                value =  changeSpecialCharToUrl(value);//处理+等encodeURI不能处理的特殊字符
                $('#dg').datagrid({
                    url:'http://localhost:8888/hello/bookJsonServlet?task=queryByBName&&bName='+ value,//把json 流放到缓冲区
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
                        {field:'BName',title:'图书名字',width:100},
                        {field:'BNo',title:'书籍编号',width:100,editor:'textbox'},
                        {field:'BAuthor',title:'书籍作者',width:100},
                        {field:'BPress',title:'出版社',width:120},
                        {field:'BPrice',title:'书籍价格',width:100,editor:'textbox'},
                        {field:'picutreUrl',title:'图片',width:120},
                    ]],
                    onClickCell:function(index,field,value){
                        if(value.startsWith("net/bookImage/") && value.match("net(/\\S+)"))
                            window.open("http://localhost:8888/hello/"+value);
                    }
                });
            }
            else if(name=="ByBAuthor"){
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    url:'http://localhost:8888/hello/bookJsonServlet?task=queryByBAuthor&bAuthor='+value,//把json 流放到缓冲区
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
                        {field:'BName',title:'图书名字',width:100},
                        {field:'BNo',title:'书籍编号',width:100,editor:'textbox'},
                        {field:'BAuthor',title:'书籍作者',width:100},
                        {field:'BPress',title:'出版社',width:120},
                        {field:'BPrice',title:'书籍价格',width:100,editor:'textbox'},
                        {field:'picutreUrl',title:'图片',width:120},
                    ]],
                    onClickCell:function(index,field,value){
                        if(value.startsWith("net/bookImage/") && value.match("net(/\\S+)"))
                            window.open("http://localhost:8888/hello/"+value);
                    }
                });
            }
            else if(name=="ByBPrice"){
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    url:'http://localhost:8888/hello/bookJsonServlet?task=queryByBPrice&bPrice='+value,//把json 流放到缓冲区
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
                        {field:'BName',title:'图书名字',width:100},
                        {field:'BNo',title:'书籍编号',width:100,editor:'textbox'},
                        {field:'BAuthor',title:'书籍作者',width:100},
                        {field:'BPress',title:'出版社',width:120},
                        {field:'BPrice',title:'书籍价格',width:100,editor:'textbox'},
                        {field:'picutreUrl',title:'图片',width:120},
                    ]],
                    onClickCell:function(index,field,value){
                        if(value.startsWith("net/bookImage/") && value.match("net(/\\S+)"))
                            window.open("http://localhost:8888/hello/"+value);
                    }
                });
            }
            else if(name=="ByBPress"){
                document.getElementById("centerShow").style.display='block';
                document.getElementById('tb').style.display='block';
                $('#dg').datagrid({
                    url:'http://localhost:8888/hello/bookJsonServlet?task=queryByBPress&bPress='+value,//把json 流放到缓冲区
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
                        {field:'BName',title:'图书名字',width:100},
                        {field:'BNo',title:'书籍编号',width:100,editor:'textbox'},
                        {field:'BAuthor',title:'书籍作者',width:100},
                        {field:'BPress',title:'出版社',width:120},
                        {field:'BPrice',title:'书籍价格',width:100,editor:'textbox'},
                        {field:'picutreUrl',title:'图片',width:120},
                    ]],
                    onClickCell:function(index,field,value){
                        if(value.startsWith("net/bookImage/") && value.match("net(/\\S+)"))
                            window.open("http://localhost:8888/hello/"+value);
                    }
                });
            }
            else if(name=="ByAllBook"){
                bookSearch();
            }
        }
    </script>

</body>
</html>

