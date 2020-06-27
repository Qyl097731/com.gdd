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
    <script>
        alert(c)
        $(document).ready(function () {
            $(".page-con ul li a").click(function () {
                if (${empty username}) {
                    if ($(this).prop("href") != "http://localhost:8080/index.jsp") {
                        alert("请登录");
                        $(this).prop("href", "userLogin.jsp")
                    }
                }
            })
        })
    </script>
</head>
<body>
<header>
    <div class="page-nav">
        <div class="page-con">
            <nav>
                <ul>
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="CartsServlet" class="active">购物车</a></li>
                    <li><a href="GoodsShowServlet">商品栏</a></li>
                    <li><a href="personalInfo.jsp">个人中心</a></li>
                </ul>
            </nav>
            <div class="statue">
                <c:if test="${empty authority}">
                    <a href="userLogin.jsp">登录</a>
                </c:if>
                <c:if test="${not empty authority}">
                    <a href="personalInfo.jsp">${username}</a> <a href="Logout">退出</a>
                </c:if>
            </div>
        </div>
    </div>
</header>
<c:if test="${not empty goods}">
    <form action="GoodsServlet" class="form" style="text-align: center;margin:40px auto;border: 1px solid black">
        <input type="text" name="id" value="${goods.id}" hidden>
        id:${goods.id}<br><br>
        商品名称:${goods.goodsName}<input type="text" name="goodsName" value="${goods.goodsName}" hidden>
        <br><br>
        商品价格:${goods.goodsPrice}<input type="text" name="goodsPrice" value="${goods.goodsPrice}" hidden>
        <br><br>
        商品数量:${goods.goodsNum}<input type="text" name="goodsNum" value="${goods.goodsNum-1}" hidden>
        <br><br>
        <input type="text" name="op" value="intoShoppingOrder" hidden id="op">
        <input type="submit" name="submit" value="购买" id="intoShoppingOrder">
    </form>
</c:if>
<c:if test="${not empty cartsList}">
    <div class="result">
        <table style="margin:0 auto;width:800px;border-collapse: collapse; text-align: center ;" border="1px" ;
               cellspacing="20px" ;padding="16px"
               cellpadding="10px">
            <tr>
                <th>序号</th>
                <th>名字</th>
                <th>货品id</th>
                <th>时间</th>
                <th>购买</th>
            </tr>
            <c:forEach items="${cartsList}" var="carts" varStatus="vs">
                <tr>
                    <td>${vs.index+1}</td>
                    <td>${carts.username}</td>
                    <td>${carts.goodsId}</td>
                    <td>${carts.time}</td>
                    <td><a href="GoodsServlet?op=search-id&id=${carts.goodsId}&type=1">购买</a> </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>
