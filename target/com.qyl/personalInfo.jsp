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
        function checkInsertInfo(form) {
            var goodsName = form.goodsName.value;
            var goodsPrice = form.goodsPrice.value;
            var goodsNum = form.goodsNum.value;
            if (goodsNum == null || goodsNum == "" || goodsPrice == null || goodsPrice == "" || goodsName == null || goodsName == "") {
                alert("信息不全");
                return false;
            } else {
                return true;
            }
        }

        function searchCheck(form) {
            var id = form.id.value;
            if (id == null || id == "") {
                alert("信息不全");
                return false;
            } else {
                return true;
            }
        }

        $(document).ready(function () {
            $(".fun li:eq(0)").click(function () {
                location.href = ("getInfo");
            })
            $(".fun li:eq(1)").click(function () {
                // location.href=("getInfo");
            })
            if (${not empty msg}) {
                alert('${msg}');
            }
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
    <title>Title</title>
</head>
<body>
<header>
    <div class="page-nav" style="border-bottom: 1px solid black">
        <div class="page-con">
            <nav>
                <ul>
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="ShowShoppingCat">购物车</a></li>
                    <li><a href="ShowGoods">商品栏</a></li>
                    <li><a href="personalInfo.jsp" class="active">个人中心</a></li>
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
<div class="page-content;" style="display: flex;height: 100%">
    <div class="fun" style="border-right: 1px black solid;width: 200px;height: 100%;">
        <ul style="display: flex;flex-wrap: wrap;width: 115px;height:50%;padding-left:50px ;padding-top: 150px  ;justify-content: space-between;justify-items: center">
            <li>修改个人信息</li>
            <li>查询订单</li>
            <c:if test="${authority=='0'}">
                <li onclick="$('.result .form,table').hide();$('.result form:eq(0)').show()">按照id查询订单</li>
                <li onclick="location.href=('GoodsServlet?op=search');">下架商品</li>
                <li onclick="$('.result .form,table').hide();$('.result form:eq(1)').show();">插入商品</li>
                <li onclick="location.href=('GoodsServlet?op=insert-b')">批量插入商品</li>
                <li onclick="location.href=('GoodsServlet?op=search');">修改商品</li>
            </c:if>
        </ul>
    </div>
    <div class="result" style="width: 1000px;float: right">
        <c:if test="${not empty msg}">
            <div class="form">
                账号:${user.username}
                密码:${user.password}
                电话:${user.tel}
            </div>
        </c:if>
        <c:if test="${not empty user and empty msg}">
            <div class="result-1">
                <form action="UpdateUser" class="form">
                    账号:${user.username}
                    <input type="text" name="username" value="${user.username}" id="username" hidden>
                    密码:<input type="text" name="password" value="${user.password}" id="password">
                    电话:<input type="text" name="tel" value="${user.tel}" id="tel">
                    <input type="submit" value="提交" id="submit">
                </form>
            </div>
        </c:if>
        <div class="page-up" style="float: right;height: 200px">
            <form action="GoodsServlet" onsubmit="return searchCheck(this)" method="post" hidden class="form">
                商品Id:<input type="text" name="id">
                <br>
                <input type="text" name="op" value="search-id" hidden>
                <input type="submit" name="submit" value="查询商品">
                <br>
            </form>
            <form action="GoodsServlet" onsubmit="return checkInsertInfo(this)" hidden class="form">
                商品名称:<input type="text" name="goodsName">
                <br>
                商品价格:<input type="text" name="goodsPrice">
                <br>
                商品数量:<input type="text" name="goodsNum">
                <br>
                <input type="text" name="op" value="insert" hidden>
                <input type="submit" name="submit" value="插入商品">
            </form>
            <c:if test="${not empty goods}">
                <form action="GoodsServlet" onsubmit="return checkInsertInfo(this)" class="form">
                    <input type="text" name="id" value="${goods.id}" hidden>
                    id:${goods.id}<br>
                    商品名称:<input type="text" name="goodsName" value="${goods.goodsName}">
                    <br>
                    商品价格:<input type="text" name="goodsPrice" value="${goods.goodsPrice}">
                    <br>
                    商品数量:<input type="text" name="goodsNum" value="${goods.goodsNum}">
                    <br>
                    <input type="text" name="op" value="update" hidden>
                    <input type="submit" name="submit" value="修改">
                </form>
            </c:if>
        </div>
        <c:if test="${not empty goodsList  and empty goods}">
            <div class="result">
                <table style="margin:0 auto;width:800px;border-collapse: collapse; text-align: center ;" border="1px" ;
                       cellspacing="20px" ;padding="16px"
                       cellpadding="10px">
                    <tr>
                        <th>序号</th>
                        <th>衣服名称</th>
                        <th>价格</th>
                        <th>库存</th>
                        <th>下架</th>
                        <th>修改</th>
                    </tr>
                    <c:forEach items="${goodsList}" var="goods" varStatus="vs">
                        <tr>
                            <td>${vs.index+1}</td>
                            <td>${goods.goodsName}</td>
                            <td>${goods.goodsPrice}</td>
                            <td>${goods.goodsNum}</td>
                            <td><a href="GoodsServlet?op=delete&id=${goods.id}">下架</a></td>
                            <td><a href="GoodsServlet?op=search-id&id=${goods.id}">修改</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
