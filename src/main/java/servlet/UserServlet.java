package servlet;

import bean.Goods;
import bean.User;
import dao.GoodsDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    GoodsDao goodsDao = new GoodsDao();
    ArrayList<Goods>goodsList = null;
    User user;
    String op;
    String authority, username, password,tel;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        authority = request.getParameter("autSelect");
        username = request.getParameter("username");
        password = request.getParameter("password");
        op = request.getParameter("op");
        user = userDao.queryUserByUsername(username, authority);
        //0 登录  1 注册 0卖家 1 买家
        if (op.equals("0")) {
            if (user.getUsername() != null && user.getUsername().equals(username) && user.getAuthority().equals(authority) && user.getPassword().equals(password)) {
                session.setAttribute("username",username);
                session.setAttribute("authority",authority);
                if (authority.equals("0")) {
                    request.setAttribute("msg","登录成功");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else{
                    request.setAttribute("msg","登录成功");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("msg","账号或者密码错误");
                request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            }
        }else {
            tel = request.getParameter("tel");
            if(user.getUsername()!=null){
                request.setAttribute("msg","账号已经存在");
                request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            }else {
                userDao.insertUser(username,password,authority,tel);
                session.setAttribute("username",username);
                session.setAttribute("authority",authority);
                request.setAttribute("msg","注册成功");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
}
