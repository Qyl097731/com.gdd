package servlet;

import bean.Order;
import dao.OrderDao;

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
 * date: 2020-06-26 20:46
 * copyright(c) 2020 南晓18卓工 邱依良
 */
@WebServlet(name = "OrderServlet",urlPatterns = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    OrderDao orderDao = new OrderDao();
    ArrayList<Order>ordersList = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authority = request.getSession().getAttribute("authority").toString();
        String username = request.getSession().getAttribute("username").toString();
        if(authority.equals("0")){
            ordersList = orderDao.queryOrders();
        }else{
            ordersList = orderDao.queryOrderByUsername(username);
        }
        request.setAttribute("ordersList",ordersList);
        request.getRequestDispatcher("personalInfo.jsp").forward(request,response);
    }
}
