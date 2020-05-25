package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        final HttpSession httpSession = req.getSession();
        String role = (String) httpSession.getAttribute("role");
        if (nonNull(httpSession.getAttribute("email")) && nonNull(httpSession.getAttribute("password"))) {
            if (role != null) {
                if (role.equals("admin")) {
                    req.getRequestDispatcher(req.getRequestURI()).forward(req, resp);
                } else {
                    resp.sendRedirect("/user/userPage");
                }
            }
        } else {
            resp.sendRedirect("/user/userPage");
        }
    }

    @Override
    public void destroy() {

    }
}
