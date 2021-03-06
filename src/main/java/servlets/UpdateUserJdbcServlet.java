package servlets;

import model.User;
import service.UserJdbcService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-jdbc")
public class UpdateUserJdbcServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        new UserJdbcService().editUser(new User(id, surname, name, password, email));
        resp.sendRedirect("allUser-jdbc.jsp");
        resp.setStatus(200);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        User existingUser = new UserJdbcService().getUserById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("addUser-jdbc.jsp");
        req.setAttribute("user", existingUser);
        dispatcher.forward(req, resp);
    }
}
