package servlets;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/new")
public class AddUserServlet extends HttpServlet {
    public static boolean bool = true;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        bool = new UserService().addUser(new User(surname, name, password, email));
        if (bool) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
            resp.setStatus(200);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("addUser.jsp");
        dispatcher.forward(req, resp);
    }
}
