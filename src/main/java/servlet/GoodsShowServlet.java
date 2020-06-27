package servlet;

import bean.Goods;
import dao.GoodsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * projectName:  com.gdd
 * packageName: ${PACKAGE_NAME}
 * date: 2020-06-26 19:53
 * copyright(c) 2020 南晓18卓工 邱依良
 */
@WebServlet(name = "GoodsShowServlet",urlPatterns = "/GoodsShowServlet")
public class GoodsShowServlet extends HttpServlet {
    GoodsDao goodsDao = new GoodsDao();
    ArrayList<Goods>goodsList = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goodsList = goodsDao.queryAllGoods();
        request.setAttribute("goodsList",goodsList);
        request.getRequestDispatcher("goods.jsp").forward(request,response);
    }
}
