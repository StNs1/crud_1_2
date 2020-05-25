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

@WebServlet ("/admin/edit-hibernate")
public class UpdateUserHibernateServlet extends HttpServlet {
    UserHibernateService userHibernateService = UserHibernateService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        userHibernateService.editUser(new User(id, surname, name, password, email, role));
        resp.sendRedirect("/admin/allUser-hibernate");
        resp.setStatus(200);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        User existingUser = userHibernateService.getUserById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/addUser-hibernate.jsp");
        req.setAttribute("user", existingUser);
        dispatcher.forward(req, resp);
    }
}
