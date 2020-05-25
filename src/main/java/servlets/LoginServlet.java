package servlets;

import dao.UserDaoFactory;
import model.User;
import service.UserHibernateService;
import service.UserJdbcService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserHibernateService userHibernateService = UserHibernateService.getInstance();
    UserJdbcService userJdbcService = UserJdbcService.getInstance();
    public static String email;
    public static String password;
    public static User user;
    public static String role;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession httpSession = req.getSession();
        email = (String) httpSession.getAttribute("email");
        password = (String) httpSession.getAttribute("password");
        if (UserDaoFactory.getUserDao().equals("hibernate")) {
            user = userHibernateService.getUserByEmail(email);
            role = user.getRole();
            httpSession.setAttribute("role", user.getRole());
            req.setAttribute("user", user);
            if (userHibernateService.getUserRole(email).equals("admin")) {
                resp.sendRedirect("/admin/allUser-hibernate");
            } else {
                resp.sendRedirect("/user/userPage");
            }
        } else {
            user = userJdbcService.getUserByEmail(email);
            role = user.getRole();
            httpSession.setAttribute("role", user.getRole());
            req.setAttribute("user", user);
            if (userJdbcService.getUserRole(req.getParameter("email")).equals("admin")) {
                resp.sendRedirect("/admin/allUser-jdbc");
            } else {
                resp.sendRedirect("/user/userPage");
            }
        }
    }
}
