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
    <script>
        $(document).ready(function () {
            if(${not empty msg}){
                alert('${msg}')
            }
            $("#intoShoppingOrder").click(function () {
                if(${goods.goodsNum<=0}){
                    alert("库存不够");
                    return false;
                }
                $("#op").prop("value","intoShoppingOrder");
            })
            $("#intoShoppingCart").click(function () {
                $("input[name='goodsNum']").prop("value",'${goods.goodsNum}');
                $("#op").prop("value","intoShoppingCart");
            })
            $(".page-con ul li a").click(function () {
                if(${empty username}){
                    if($(this).prop("href")!="http://localhost:8080/index.jsp"){
                        alert("请登录");
                        $(this).prop("href","userLogin.jsp")
                    }
                }
            })
        })
    </script>
    <title>Title</title>
</head>
<body>
<header>
    <div class="page-nav">
        <div class="page-con">
            <nav>
                <ul>
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="CartsServlet">购物车</a></li>
                    <li><a href="GoodsShowServlet" class="active">商品栏</a></li>
                    <li><a href="personalInfo.jsp" >个人中心</a></li>
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
            <input type="text" name="op" value="" hidden id="op">
            <input type="submit" name="submit" value="购买" id="intoShoppingOrder">
            <input type="submit" name="submit" value="加入购物车" id="intoShoppingCart">
        </form>
    </c:if>
    <c:if test="${not empty goodsList && empty goods}">
        <div class="result" style="border: 1px solid black;margin-top: 20px">
            <table style="margin:0 auto;width:800px;border-collapse: collapse; text-align: center ;" border="1px" ;
                   cellspacing="20px" ;padding="16px"
                   cellpadding="10px">
                <tr>
                    <th>序号</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                </tr>
                <c:forEach items="${goodsList}" var="goods" varStatus="vs">
                    <tr onclick="location.href='GoodsServlet?op=search-id&id=${goods.id}'">
                        <td>${vs.index+1}</td>
                        <td>${goods.goodsName}</td>
                        <td>${goods.goodsPrice}</td>
                        <td>${goods.goodsNum}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</header>
</body>
</html>
