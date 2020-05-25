package filter;

import dao.UserDaoFactory;
import model.User;
import service.UserHibernateService;
import service.UserJdbcService;
import servlets.LoginServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

//@WebFilter(urlPatterns = {"/admin/new-hibernate", "/admin/new-jdbc", "/admin/edit-hibernate", "/admin/edit-jdbc", "/admin/allUser-hibernate", "/admin/allUser-jdbc", "/admin/delete-hibernate", "/admin/delete-jdbc"})
@WebFilter(urlPatterns = {"/login", "/user/userPage", "/admin/*"})
public class AuthFilter implements Filter {
    UserHibernateService userHibernateService = UserHibernateService.getInstance();
    UserJdbcService userJdbcService = UserJdbcService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        final HttpSession httpSession = req.getSession();
        String role = (String) httpSession.getAttribute("role");
        User user = new User();
        if (UserDaoFactory.getUserDao().equals("hibernate")) {
            user = userHibernateService.getUserByEmail(email);
        } else if (UserDaoFactory.getUserDao().equals("jdbc")) {
            user = userJdbcService.getUserByEmail(email);
        }
        if (nonNull(httpSession.getAttribute("email")) && nonNull(httpSession.getAttribute("password"))) {
            if (role.equals("admin")) {
                req.getRequestDispatcher(req.getRequestURI()).forward(req, resp);
            } else {
                req.setAttribute("user", user);
                req.getRequestDispatcher("/user/userPage").forward(req, resp);
            }
        } else if (userHibernateService.isExistUser(email, password) || userJdbcService.isExistUser(email, password)) {
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("password", password);
            req.getRequestDispatcher("/login").forward(req, resp);
        } else {
            resp.sendRedirect("/index.jsp");
        }
    }
        /*if (nonNull(httpSession.getAttribute("email")) && nonNull(httpSession.getAttribute("password"))) {
            String role = (String) httpSession.getAttribute("role");
            move(req, resp, role, email);
        } else if (userHibernateService.isExistUser(email, password) || userJdbcService.isExistUser(email, password)) {
            String role = null;
            if (UserDaoFactory.getUserDao().equals("hibernate")) {
                role = userHibernateService.getUserRole(email);
            } else if (UserDaoFactory.getUserDao().equals("jdbc")) {
                role = userJdbcService.getUserRole(email);
            }
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("role", role);
            move(req,resp,role, email);
        } else {
            move(req, resp, "unknown", email);
        }*/

    @Override
    public void destroy() {

    }

   /* private void move(HttpServletRequest req, HttpServletResponse resp, String role, String email) throws ServletException, IOException {
        if (role.equals("admin")){
            if (UserDaoFactory.getUserDao().equals("hibernate")) {
                req.getRequestDispatcher(req.getRequestURI()).forward(req,resp);
            } else if (UserDaoFactory.getUserDao().equals("jdbc")) {
                RequestDispatcher dispatcher = req.getRequestDispatcher(req.getRequestURI());
                dispatcher.forward(req, resp);
            }
        } else if (role.equals("user")) {
            User user = UserHibernateService.getInstance().getUserByEmail(email);
            req.setAttribute("user", user);
            resp.sendRedirect("/user/userPage");
            //req.getRequestDispatcher("/user").forward(req, resp);
        } else {
            resp.sendRedirect("/index.jsp");
        }
    }*/
}
