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
                    <li><a href="ShowShoppingCat">购物车</a></li>
                    <li><a href="ShowGoods" class="active">商品栏</a></li>
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
        <form action="GoodsServlet" class="form">
            <input type="text" name="id" value="${goods.id}" hidden>
            id:${goods.id}<br>
            商品名称:<input type="text" name="goodsName" value="${goods.goodsName}">
            <br>
            商品价格:<input type="text" name="goodsPrice" value="${goods.goodsPrice}">
            <br>
            商品数量:<input type="text" name="goodsNum" value="${goods.goodsNum-1}">
            <br>
            <input type="text" name="op" value="update" hidden>
            <input type="submit" name="submit" value="修改">
        </form>
    </c:if>
    <c:if test="${not empty goodsList && empty goods}">
        <div class="result">
            <table style="margin:0 auto;width:800px;border-collapse: collapse; text-align: center ;" border="1px" ;
                   cellspacing="20px" ;padding="16px"
                   cellpadding="10px">
                <tr>
                    <th>序号</th>
                    <th>零食名称</th>
                    <th>价格</th>
                    <th>加入购物车</th>
                    <th>购买</th>
                </tr>
                <c:forEach items="${goodsList}" var="goods" varStatus="vs">
                    <tr>
                        <td>${vs.index+1}</td>
                        <td>${goods.goodsName}</td>
                        <td>${goods.goodsPrice}</td>
                        <td>${goods.goodsNum}</td>
                        <td><a href="GoodsServlet?op=search-id&id=${goods.id}" contenteditable="${0==goods.goodsNum}">加入购物车</a></td>
                        <td><a href="GoodsServlet?op=search-id&id=${goods.id}" contenteditable="${0==goods.goodsNum}">购买</a></td>


                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</header>
</body>
</html>
