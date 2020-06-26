<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/26
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Title</title>
</head>
<body>
<header>
    <div class="page-nav">
        <div class="page-con">
            <nav>
                <ul>
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="ShowShoppingCat" class="active">购物车</a></li>
                    <li><a href="ShowGoods">商品栏</a></li>
                    <li><a href="personalInfo.jsp" class="active">个人中心</a></li>
                </ul>
            </nav>
            <div class="statue">
                <c:if test="${empty authority}">
                    <a href="userLogin.jsp">登录</a>
                </c:if>
                <c:if test="${not empty authority}">
                    <a href="personalInfo.jsp">${username}</a> <a href="Logout" >退出</a>
                </c:if>
            </div>
        </div>
    </div>
</header>
</body>
</html>
