<%@ page import="DB.book.book" %>
<%@ page import="java.util.List" %>
<%@ page import="Service.bookService.bookSearchServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/24
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>图书展示</title>
    <link rel="stylesheet" type ="text/css" href="http://localhost:8888/hello/net/css/style.css">
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../jeasyui/jquery.easyui.min.js"></script>

    <script>
            var pageNow=1;
            var pageSize=3;
            var totalPage;
            $(function(){
                getBook(pageNow,pageSize);
                $("#nextPage").click(
                    function () {
                        let pageNew =pageNow +1;
                        if(pageNew>totalPage)
                            pageNow = 1;
                        else
                            pageNow =pageNew;
                       getBook(pageNow,pageSize);
                    }
                );

                $("#previewPage").click(
                    function () {
                        let pageNew =pageNow -1;
                        if(pageNew<=0)
                            pageNow = totalPage;
                        else
                            pageNow =pageNew;
                        getBook(pageNow,pageSize);
                    }
                );

                $("#selectSize").change(
                    function () {
                        var options = $("#selectSize option:selected");　　　　//获取选中项
                        pageSize = options.val()　　　　　　　　　　　　　//获取选中项的值
                        if(pageSize*pageNow>totalPage)
                            pageNow =1;
                        getBook(pageNow,pageSize);
                    }
                );
            })
            function getBook(parmas1,params2){
               $.ajax({
                   url: "http://localhost:8888/hello/showBookServlet?task=queryByPage",
                   type: "post",
                   dataType: "json",
                   data:{
                       "pageNow":parmas1,
                       "pageSize":params2
                   },//请求数据
                   success:  //展示数据
                       function (data) {
                           $("#dd").empty();
                           $.each(data.bookList,
                               function(i,d) {
                                   //追加到table中
                                   $("#dd").append(
                                       "<li  style='height:290px;width:200px;text-align:center;float:left;"
                                       + "list-style-type :none;margin-left: 20px;margin-right: 10px;padding:0;border: black;' >"
                                       + "<a href='http://localhost:8888/hello/jsp/selectBook.jsp?BNo=" +d.BNo + "' style='text-decoration: none'>"
                                       + "<br>"
                                       + "<img src='"
                                       + "http://localhost:8888/hello/" + d.picutreUrl + "' width='170' height='170' style='display: inline-block;'>"
                                       + "<p style='color: blue;font-size:large;'>" + d.BName + "</p>"
                                       + "<p style='color: blue;font-size:large;'>" + d.BAuthor + "</p>"
                                       + "</a>"
                                       + "</li>"
                                   );
                               }
                           );
                           pageNow = data.pageNow;
                           pageSize = data.pageSize;
                           totalPage = data.totalPage;
                           $("#showPage").html(data.pageNow);//当前页
                           $("#showPageSize").html(data.pageSize);//每页显示
                           $("#showTotalPage").html(data.totalPage);//总共页数

                       },
                   error: function(msg){
                       alert("ajax连接异常："+msg);
                   }
               });
           }
            function qq(value,name) {
                if (name == "ByBName") {
                    $.ajax({
                        url: "http://localhost:8888/hello/showBookServlet?task=queryByBName",
                        type: "post",
                        dataType: "json",
                        data: {
                            bName:value
                        },//请求数据
                        success:  //展示数据
                            function (data) {
                                $("#dd").empty();
                                $.each(data.bookList,
                                    function (i, d) {
                                        //追加到table中
                                        $("#dd").append(
                                            "<li  style='height:290px;width:200px;text-align:center;float:left;"
                                            + "list-style-type :none;margin-left: 20px;margin-right: 10px;padding:0;border: black;' >"
                                            + "<a href='http://localhost:8888/hello/jsp/selectBook.jsp?BNo=" + d.BNo + "' style='text-decoration: none'>"
                                            + "<br>"
                                            + "<img src='"
                                            + "http://localhost:8888/hello/" + d.picutreUrl + "' width='170' height='170' style='display: inline-block;'>"
                                            + "<p style='color: blue;font-size:large;'>" + d.BName + "</p>"
                                            + "<p style='color: blue;font-size:large;'>" + d.BAuthor + "</p>"
                                            + "</a>"
                                            + "</li>"
                                        );
                                    }
                                );
                                pageNow = data.pageNow;
                                pageSize = data.pageSize;
                                totalPage = data.totalPage;
                                $("#showPage").html(data.pageNow);//当前页
                                $("#showPageSize").html(data.pageSize);//每页显示
                                $("#showTotalPage").html(data.totalPage);//总共页数

                            },
                        error: function (msg) {
                            alert("ajax连接异常：" + msg);
                        }
                    });
                }
                else  if (name == "ByAllBook") {
                    $.ajax({
                        url: "http://localhost:8888/hello/showBookServlet?task=queryAll",
                        type: "post",
                        dataType: "json",
                        data:{},//请求数据
                        success:  //展示数据
                            function (data) {
                                $("#dd").empty();
                                $.each(data.bookList,
                                    function(i,d) {
                                        //追加到table中
                                        $("#dd").append(
                                            "<li  style='height:290px;width:200px;text-align:center;float:left;"
                                            + "list-style-type :none;margin-left: 20px;margin-right: 10px;padding:0;border: black;' >"
                                            + "<a href='http://localhost:8888/hello/jsp/selectBook.jsp?BNo=" +d.BNo + "' style='text-decoration: none'>"
                                            + "<br>"
                                            + "<img src='"
                                            + "http://localhost:8888/hello/" + d.picutreUrl + "' width='170' height='170' style='display: inline-block;'>"
                                            + "<p style='color: blue;font-size:large;'>" + d.BName + "</p>"
                                            + "<p style='color: blue;font-size:large;'>" + d.BAuthor + "</p>"
                                            + "</a>"
                                            + "</li>"
                                        );
                                    }
                                );
                                pageNow = data.pageNow;
                                pageSize = data.pageSize;
                                totalPage = data.totalPage;
                                $("#showPage").html(data.pageNow);//当前页
                                $("#showPageSize").html(data.pageSize);//每页显示
                                $("#showTotalPage").html(data.totalPage);//总共页数

                            },
                        error: function(msg){
                            alert("ajax连接异常："+msg);
                        }
                    });
                }
                else if (name == "ByBAuthor") {
                    $.ajax({
                        url: "http://localhost:8888/hello/showBookServlet?task=queryByBAuthor",
                        type: "post",
                        dataType: "json",
                        data: {
                            bAuthor:value
                        },//请求数据
                        success:  //展示数据
                            function (data) {
                                $("#dd").empty();
                                $.each(data.bookList,
                                    function (i, d) {
                                        //追加到table中
                                        $("#dd").append(
                                            "<li  style='height:290px;width:200px;text-align:center;float:left;"
                                            + "list-style-type :none;margin-left: 20px;margin-right: 10px;padding:0;border: black;' >"
                                            + "<a href='http://localhost:8888/hello/jsp/selectBook.jsp?BNo=" + d.BNo + "' style='text-decoration: none'>"
                                            + "<br>"
                                            + "<img src='"
                                            + "http://localhost:8888/hello/" + d.picutreUrl + "' width='170' height='170' style='display: inline-block;'>"
                                            + "<p style='color: blue;font-size:large;'>" + d.BName + "</p>"
                                            + "<p style='color: blue;font-size:large;'>" + d.BAuthor + "</p>"
                                            + "</a>"
                                            + "</li>"
                                        );
                                    }
                                );
                                pageNow = data.pageNow;
                                pageSize = data.pageSize;
                                totalPage = data.totalPage;
                                $("#showPage").html(data.pageNow);//当前页
                                $("#showPageSize").html(data.pageSize);//每页显示
                                $("#showTotalPage").html(data.totalPage);//总共页数

                            },
                        error: function (msg) {
                            alert("ajax连接异常：" + msg);
                        }
                    });
                }
            }

    </script>
</head>

<body  background= "http://localhost:8888/hello/net/LogeInPhoto/reading.jpg"  style=" background-repeat:no-repeat ;
            background-size:100% 100%;
            background-attachment: fixed;">

    <div style=" text-align: center;">

        <input id="ss" class="easyui-searchbox" style="width:300px"
               data-options="searcher:qq,prompt:'Please Input Value',menu:'#mm'"></input>

        <div id="mm" style="width:100px">
            <div data-options="name:'ByBName',iconCls:'icon-ok'">书名</div>
            <div data-options="name:'ByBAuthor',iconCls:'icon-ok'">作者</div>
            <div data-options="name:'ByAllBook',iconCls:'icon-ok'">所有图书</div>
        </div>
        <a href="http://localhost:8888/hello/showBookServlet?task=searchJob"><button>查看自己的借书情况</button></a>

        <br>
        <div id="dd"  style="width:700px;background:whitesmoke;opacity:0.8; display: inline-block;overflow: hidden; text-align: center;margin-top:60px;">
        </div>
    </div>

    <div style=" text-align: center;">
        <label> <span style="font-size:medium">页面显示大小</span></label>
       <select id="selectSize">
           <option value="3" >3</option>
           <option value="6" >6</option>
           <option value="9" >9</option>
           <option value="12" >12</option>
           <option value="27" >27</option>
           <option value="81" >81</option>
       </select>
        <input  type="button" id="previewPage" value="上一页">
        <input  type="button" id="nextPage" value="下一页">
        <span>
            <span style="font-size:medium">当前为第</span>
            <span id="showPage" style="font-size:medium;font-size:medium;color:red"></span>
            <span style="font-size:medium">页/</span>
            <span style="font-size:medium">每页显示</span>
            <span id="showPageSize" style="font-size:medium;font-size:medium;color:red"></span>
            <span style="font-size:medium">条记录/</span>
            <span style="font-size:medium">总共</span>
            <span id="showTotalPage" style="font-size:medium;font-size:medium;color:red"></span>
            <span style="font-size:medium">页记录</span>
        </span>
        <pr/>
    </div>


</body>
</html>



