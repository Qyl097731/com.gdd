<%@page contentType="text/html;charset=UTF-8" language="java" isErrorPage="false" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + path + "/";
%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script src="src/unslider.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">

    <script>
        $(document).ready(function () {
            $(".btnSelect button:eq(1)").click(function () {
                $(this).addClass("active").siblings().removeClass("active");
                $("#login").css("display", "none");
                $("#register").css("display", "block");
            })
            $(".btnSelect button:eq(0)").click(function () {
                $(this).addClass("active").siblings().removeClass("active");
                $("#login").css("display", "block");
                $("#register").css("display", "none");
            })
            if(${not empty msg}){
                alert('${msg}');
            }
        })
        function loginCheck(form) {
            var username = form.username.value;
            var password = form.password.value;
            if(username == null || username == "" ){
                $(".usernameError-l").text("　　请输入账号");
                return false;
            }else if(password == null || password == "") {
                $(".passwordError-l").text("　　请输入密码");
                return false;
            }
            return true;
        }
        function registerCheck(form) {
            var username = form.username.value;
            var password = form.password.value;
            if(username == null || username == "" ){
                $(".usernameError-r").text("　　请输入账号");
                return false;
            }else if(password == null || password == "") {
                $(".passwordError-r").text("　　请输入密码");
                return false;
            }
            return true;
        }

    </script>
</head>
<body>
<div class="content">
    <div class="btnSelect">
        <button class="active">登录</button>
        <button>注册</button>
    </div>
    <div id="login" >
        <form action="UserServlet" onsubmit="return loginCheck(this)">
            <input type="text" hidden value="0" name="op">
            <div class="autSelect">
                身份：<select name="autSelect">
                <option value="0" selected="selected">卖家</option>
                <option value="1">买家</option>
            </select>
            </div>
            <label for="username-l">
                账号：<input type="text" name="username" id="username-l" placeholder="账号&nbsp;username">
            </label>
            <span class="usernameError-l"></span>
            <label for="password-l">
                密码：<input type="password" name="password" id="password-l" placeholder="密码&nbsp;password">
            </label>
            <span class="passwordError-l"></span>
            <label for="submit-l" class="submitLab">
                <input type="submit" name="submit" id="submit-l" value="登录"/>
            </label>
        </form>
    </div>
    <div id="register" style="display: none">
        <form action="UserServlet" onsubmit="return registerCheck(this)">
            <input type="text" hidden value="1" name="op">
            <div class="autSelect">
                身份：<select name="autSelect">
                <option value="0" selected="selected">卖家</option>
                <option value="1">买家</option>
            </select>
            </div>
            <label for="username-r">
                账号：<input type="text" name="username" id="username-r" placeholder="账号&nbsp;username">
            </label>
            <span class="usernameError-r"></span>
            <label for="password-r">
                密码：<input type="password" name="password" id="password-r" placeholder="密码&nbsp;password">
            </label>
            <label for="tel-r">
                密码：<input type="text" name="tel" id="tel-r" placeholder="电话&nbsp;tel">
            </label>
            <span class="passwordError-r"></span>
            <label for="submit-r" class="submitLab">
                <input type="submit" name="submit" id="submit-r" value="注册"/>
            </label>
        </form>
    </div>
</div>
</body>
</html>
