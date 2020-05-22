package servlets;

import model.User;
import service.UserJdbcService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-jdbc")
public class DeleteUserJdbcServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        new UserJdbcService().deleteUser(new User(id));
        resp.sendRedirect("allUser-jdbc.jsp");
        resp.setStatus(200);
    }
}
