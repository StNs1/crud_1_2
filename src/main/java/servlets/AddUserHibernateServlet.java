package servlets;

import model.User;
import service.UserHibernateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/new-hibernate")
public class AddUserHibernateServlet extends HttpServlet {
    public static boolean bool = true;
    UserHibernateService userHibernateService = UserHibernateService.getInstance();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        bool = userHibernateService.addUser(new User(surname, name, password, email, role));
        resp.sendRedirect("allUser-hibernate.jsp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("addUser-hibernate.jsp");
        dispatcher.forward(req, resp);
    }
}
