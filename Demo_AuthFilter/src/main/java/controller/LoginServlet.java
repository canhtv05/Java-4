package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {
        "/login",
        "/logout",
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/login": {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;
            }
            default: {
                HttpSession session = request.getSession();

                session.invalidate();
                response.sendRedirect("/login");
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("admin") && password.equals("admin") || username.equals("staff") && password.equals("staff")) {
            session.setAttribute("username", username);

            if (username.equals("admin")) {
                session.setAttribute("role", "admin");
                response.sendRedirect("/staff/view");

            } else {
                session.setAttribute("role", "staff");
                response.sendRedirect("/product/view");
            }
        } else {
            request.setAttribute("msg", "An error occurred, please try again");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}