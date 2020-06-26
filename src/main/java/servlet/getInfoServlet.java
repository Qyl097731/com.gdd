package servlet;

import bean.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * projectName:  com.gdd
 * packageName: ${PACKAGE_NAME}
 * date: 2020-06-26 13:40
 * copyright(c) 2020 南晓18卓工 邱依良
 */
@WebServlet(name = "getInfoServlet",urlPatterns = "/getInfo")
public class getInfoServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    User user;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        user = userDao.queryUserByUsername(session.getAttribute("username").toString(),session.getAttribute("authority").toString());
        request.setAttribute("user",user);
        request.getRequestDispatcher("personalInfo.jsp").forward(request,response);
    }
}
