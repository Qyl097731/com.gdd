package servlet;

import bean.Cart;
import dao.CartDao;

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
 * date: 2020-06-26 20:51
 * copyright(c) 2020 南晓18卓工 邱依良
 */
@WebServlet(name = "CartsServlet",urlPatterns = "/CartsServlet")
public class CartsServlet extends HttpServlet {
    CartDao cartDao = new CartDao();
    ArrayList<Cart>cartsList = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authority = request.getSession().getAttribute("authority").toString();
        String username = request.getSession().getAttribute("username").toString();
        if(authority.equals("0")){
            cartsList = cartDao.queryCarts();
        }else{
            cartsList = cartDao.queryCartByUsername(username);
        }
        request.setAttribute("cartsList",cartsList);
        request.getRequestDispatcher("shoppingCat.jsp").forward(request,response);
    }

}
