package servlets;

import model.User;
import service.UserHibernateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete-hibernate")
public class DeleteUserHibernateServlet extends HttpServlet {
    UserHibernateService userHibernateService = UserHibernateService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        userHibernateService.deleteUser(new User(id));
        resp.sendRedirect("/admin/allUser-hibernate");
        resp.setStatus(200);
    }
}
