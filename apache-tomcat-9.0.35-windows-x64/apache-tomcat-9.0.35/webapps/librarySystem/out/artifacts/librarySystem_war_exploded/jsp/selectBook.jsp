<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/25
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍信息</title>
    <link rel="stylesheet" type ="text/css" href="http://localhost:8888/hello/net/css/style.css">
    <script type="text/javascript" src="../jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="../jeasyui/jquery.easyui.min.js"></script>
</head>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="DB.book.book" %>
<%@ page import="Service.bookService.bookSearchServiceImpl" %>
<%
    String BNo = (String)request.getParameter("BNo");//直接传递
    book b = new bookSearchServiceImpl().getBookJsonByBno(BNo);
    String BName = b.getBName();
    String BAuthor = b.getBAuthor();
    String BPress  = b.getBPress();
    double BPrice = b.getBPrice();
    String src = "http://localhost:8888/hello/"+b.getPicutreUrl();
%>
<body background= "http://localhost:8888/hello/net/LogeInPhoto/lend.jpg"  style=" background-repeat:no-repeat ;
            background-size:100% 100%;
            background-attachment: fixed;">
    <div id ="bigBox">
        <form  method="POST"  name="myForm1" id ="ff" >
            <p style="color:black;font-size:x-large;"><strong>书籍信息</strong></p>
            <div style="float:left">
                <table >
                    <tr>
                        <td><strong>编号 </strong></td> <td><input value="<%=BNo%>" class="easyui-textbox" style="width:200px" type="text" name="BNo"  id="bno1" readonly></td>
                    </tr>
                    <tr>
                        <td><strong>名字 </strong></td> <td> <input value="<%=BName%>" class="easyui-textbox" id="bname1" style="width:200px" type="text" name="BName" readonly></td>
                    </tr>
                    <tr>
                        <td><strong>作者 </strong></td> <td> <input  value="<%=BAuthor%>" class="easyui-textbox" id="bauthor1" style="width:200px" type="text" name="BAuthor"  readonly></td>
                    </tr>
                    <tr>
                        <td><strong>出版社</strong></td> <td><input value="<%=BPress%>" class="easyui-textbox" id="bpress1" style="width:200px" type="text" name="BPress" readonly></td>
                    </tr>
                    <tr>
                        <td><strong>价格</strong></td>  <td><input  value="<%=BPrice%>" class="easyui-textbox"  id="bprice1" style="width:200px" type="text" name="BPrice" readonly></td>
                    </tr>
                </table>
            </div>
            <div style="float:left; width: 250px;height: 300px; ">
                <p style="color:#990066;font-size:large;"><strong>图片展示</strong></p>
                <img src="<%=src%>" alt="预览图片" id="image1" style="height: 100%;width: 100%;">
            </div>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">确认借书</a>
                <a href="http://localhost:8888/hello/jsp/showBookList.jsp" class="easyui-linkbutton">取消借书</a>
            </div>
        </form>
    </div>
</div>
<script>
    function submitForm(){
        $('#ff').form('submit', {
            url: "http://localhost:8888/hello/showBookServlet?task=insert",
            success: function (message) {
                var jsonItem = JSON.parse(message);
                var msg = jsonItem.message;
                if(msg =="ok") {
                    // 更新数据
                    $.messager.alert('提示信息', '借书成功!');
                    let url1='http://localhost:8888/hello/jsp/showBookList.jsp';
                    setTimeout("top.location.href = '" + url1 + "'",3000);
                }
                else if(msg =="Error"){
                    $.messager.alert('提示信息', '借书异常失败!');
                }
                else if(msg =="Exist"){
                    $.messager.alert('提示信息', '书被借走了!');
                }

            }
        });
    }
</script>
</body>
</html>
