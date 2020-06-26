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

    <title>图书馆管理系统登录</title>
</head>
<body   background= "http://localhost:8888/hello/net/LogeInPhoto/LogeIn3.jpg"  style=" background-repeat:no-repeat ;
            background-size:100% 100%;
            background-attachment: fixed;">
<%
    /*
    读取名为uname的cookie，如果为空，显示 ""空字符串
    如果不为空，显示cookie的值 */
    String uNo = "";
    // 获取所有的cookie
    Cookie[] cs = request.getCookies();
    // 如果存在cookie
    if (cs != null) {
        // 循环遍历所有的cookie
        for (Cookie c : cs) {
            // 找到名字为uNo的cookie
            if ("uNo".equals(c.getName())) {
                // 把这个cookie的值给uname这个变量
                uNo = c.getValue();
            }
        }
    }
%>

<img src="http://localhost:8888/hello/net/待选择图片/default.jpg" class="PersonImg" alt="用户头像" width="90" height="90">
<div id ="bigBox" >
    <h1>Library Loge In</h1>
    <form   name="myForm"  action="http://localhost:8888/hello/login" method="post">
        <div class="inputBox">
            <div class="inputText">
                <span class="fa fa-user"></span>
                <input type="text"  name="Uname" placeholder="用户名"  required="required"  />
            </div>

            <div class="inputText">
                <span class="iconfont icon-shenfenjicha"></span>
                <input type="text" name="Uno" placeholder="编号" required="required" value="<%=uNo%>"/>
            </div>

            <div class="inputText1">
                <span class="fa fa-lock"></span>
                <input type="password" class="pass-key" name="Upassword" placeholder="密码" required="required"/>
                <span class="iconfont icon-visible" id="eye"></span>
            </div>

            <div>
                  <span style="background: linear-gradient(to right, red, blue);
                     -webkit-background-clip: text;color: transparent;
                    font-weight:bold;">验证码</span>

                <input type="text" name="verifyCode" style="vertical-align:middle;">
                <img id="verifyCodeImage" style=" border-radius:0;" src= "http://localhost:8888/hello/verifyCode?time=" + new Date().getTime();  style="vertical-align:middle;">
                <br/>

                <buton  onclick ="javascript:_change()">
                    <a  style="float:right">
                    <span style="font-weight:bold;color: #00FF00;">看不清，换一张</span></a>
                </buton>
            </div>
            <br>
        </div>
        <input type="submit" class="inputButton" onclick="return validate()" value="Login"/>
    </form>
    <div >
        <div style="color:#990066 ;float:left; font-weight:bold;">Or login with</div>

        <div class="pass">
            <a href= "http://localhost:8888/hello/jsp/ResetAccount.jsp">Forgot Password?</a>
        </div>

    </div>
    <br><br>

    <div class="links" >
        <div class="QQ">
            <i  class="iconfont icon-QQ">
                <a href="https://im.qq.com/pcqq/">
                    <span>QQ</span>
                </a>
            </i>
        </div>

        <div class="WECHAT">
            <i  class="iconfont icon-weixin">
                <a href ="http://pc.zhhainiao.com/shadu/mgr3/f13.shtml?sfrom=166&keyID=22989">
                    <span>Wechat</span></a>
            </i>
        </div>

        <div class="EMAIL">
            <i  class="iconfont icon-youxiang">
                <a href ="https://mail.qq.com/">
                    <span>Email</span></a>
            </i>
        </div>
    </div>

    <div class="pass" style="float:left;"><span style="color:#990033;font-weight:bold;">Don't have account?</span>
        <a href="http://localhost:8888/hello/jsp/Register.jsp">Signup Now</a>
    </div>
</div>
<script>
    const pass_field = document.querySelector('.pass-key');
    const showBtn = document.querySelector('#eye');
    showBtn.addEventListener('click', function(){
        if(pass_field.type === "password"){
            pass_field.type = "text";
            showBtn.style.color = "#3498db";
        }
        else{
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
<script   type="text/javascript">
    function  _change(){
        // 得到img元素
        let img = document.getElementById("verifyCodeImage");
        img.src = "http://localhost:8888/hello/verifyCode?time=" + new Date().getTime();  <!-- 项目名/url-pattern-->
        //   当一个<img>的src改变时，页面会自动刷新这个<img>
    }
</script>

</body>
</html>