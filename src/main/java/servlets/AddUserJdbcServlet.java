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

@WebServlet("/new-jdbc")
public class AddUserJdbcServlet extends HttpServlet {
    public static boolean bool = true;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        bool = new UserJdbcService().addUser(new User(surname, name, password, email));
        resp.sendRedirect("allUser-jdbc.jsp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("addUser-jdbc.jsp");
        dispatcher.forward(req, resp);
    }
}