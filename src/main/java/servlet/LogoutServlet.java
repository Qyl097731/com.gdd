package servlet;

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
 * date: 2020-06-26 12:48
 * copyright(c) 2020 南晓18卓工 邱依良
 */
@WebServlet(name = "LogoutServlet",urlPatterns = "/Logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("msg","退出成功");
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
