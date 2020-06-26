package servlet;

import bean.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * projectName:  com.gdd
 * packageName: ${PACKAGE_NAME}
 * date: 2020-06-26 14:09
 * copyright(c) 2020 南晓18卓工 邱依良
 */
@WebServlet(name = "UpdateUserServlet",urlPatterns = "/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    User user;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String authority = request.getSession().getAttribute("authority").toString();
        userDao.updateUser(username,password,tel);
        request.setAttribute("msg","修改成功");
        user = userDao.queryUserByUsername(username,authority);
        request.setAttribute("user",user);
        request.getRequestDispatcher("personalInfo.jsp").forward(request,response);
    }
}
