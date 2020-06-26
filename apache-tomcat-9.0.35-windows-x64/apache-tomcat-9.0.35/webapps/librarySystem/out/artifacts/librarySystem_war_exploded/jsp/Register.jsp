<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/6/12
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">

    <link rel="stylesheet" type ="text/css" href="http://localhost:8888/hello/net/css/style.css">
    <script src="http://localhost:8888/hello/net/js/a076d05399.js"></script>
    <title>图书馆管理系统注册</title>
</head>
<body  background= "http://localhost:8888/hello/net/LogeInPhoto/LogeIn.jpg" style=" background-repeat:no-repeat ;
        background-size:100% 100%;
        background-attachment: fixed;">

<img src="http://localhost:8888/hello/net/待选择图片/default.jpg" class="PersonImg"  alt="用户头像" width="150" height="150">
<form  name="myForm"  action="http://localhost:8888/hello/register" method="post">
    <div id ="bigBox"  style="height:430px">
        <h1>Library Register</h1>
        <div class="inputBox">
            <div class="inputText">
                <span class="fa fa-user"></span>
                <input type="text"  name="Uname" placeholder="用户名"  required="required" />
            </div>

            <div class="inputText1">
                <span class="iconfont icon-shenfenjicha"></span>
                <input type="txt" name="Uno" placeholder="编号"  required="required" />
            </div>

            <div class="inputText1">
                <span class="fa fa-lock"></span>
                <input type="password" name="Upassword" placeholder="密码" class="pass-key" required="required"/>
                <span class="iconfont icon-visible" id="eye"></span>
            </div>

            <div class="inputText1">
                <span class="iconfont icon-dengdaiqueren"></span>
                <input type="txt" name="Uage"  placeholder="年龄"  required="required"/>
            </div>

            <div style="flex: auto;">
                <div ><span style="color:white" >性别</span>
                    <select style="width: 220px;text-align: center;" name ="Usex">
                        <option value="男"> 男 </option>
                        <option value="女"> 女 </option>
                    </select>
                </div>

                <div ><span style="color:white" >身份</span>
                    <select style="width: 220px;text-align: center;"  name="Ujob" >
                        <option value="common">普通用户</option>
                        <option value="special">管理员</option>
                    </select>
                </div>
            </div>

        </div>
        <input type="submit" class="inputButton" onclick="return validate()" style="background-image:linear-gradient(to right, #b8cbb8 0%, #b8cbb8 0%, #b465da 0%, #cf6cc9 33%, #ee609c 66%, #ee609c 100%)" value="Register"/>

        <div>
            <div class="pass" style="float:left;"><span style="color:#00FF00">Existing Count?</span>
                <a href="http://localhost:8888/hello/jsp/login.jsp">Login Now</a>
            </div>
            <br>
            <br>
            <div class="pass" style="float:left;"><span style="color:#00FF00">Reset Count?</span>
                <a href="http://localhost:8888/hello/jsp/ResetAccount.jsp">Reset Now</a>
            </div>
        </div>
    </div>
</form>

<script>
    const pass_field = document.querySelector('.pass-key');
    const showBtn = document.querySelector('#eye');
    showBtn.addEventListener('click', function(){
        if(pass_field.type === "password"){
            pass_field.type = "text";
            showBtn.style.color = "#3498db";
        }else{
            pass_field.type = "password";
            showBtn.style.color = "#222";
        }
    });
</script>
<script>
    function validate(){
        var flag=true;

        var x1 = document.forms["myForm"]["Uname"].value;
        if(x1.length>20){
            alert("用户名为"+x1+",长度超过20范围");
            flag = false;
        }

        var x2 =document.forms["myForm"]["Uno"].value;
        if(x2.length>10){
            alert("用户编号为"+x2+",长度超过20范围");
            flag = false;
        }

        var x3 = document.forms["myForm"]["Upassword"].value;
        if(x3.length>20){
            alert("密码为"+x3+",长度超过20范围");
            flag = false;
        }

        var x4 = document.forms["myForm"]["Uage"].value;
        if(x4 <0|| !isInteger(x4) ){
            alert("年龄为"+x4 +"，该值不在正整数范围内");
            flag = false;
        }

        return flag;
    }
    function isInteger(obj) {
        return obj%1 === 0
    }
</script>
</body>
</html>