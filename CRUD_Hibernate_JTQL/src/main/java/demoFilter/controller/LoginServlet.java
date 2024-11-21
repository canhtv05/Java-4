package demoFilter.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {
        "/demo-filter/login",
        "/demo-filter/logout",
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/demo-filter/login": {
                request.getRequestDispatcher("/demo-filter/login.jsp").forward(request, response);
                break;
            }
            default: {
                // lot vao logout
                HttpSession session = request.getSession();

//                xóa all dữ liệu từ session
                session.invalidate();
                response.sendRedirect("/demo-filter/login");
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("nv") && password.equals("nv") || username.equals("ql") && password.equals("ql")) {
            session.setAttribute("username", username);

            if (username.equals("nv")) {
                session.setAttribute("role", "nv");
                response.sendRedirect("/nhanvien/hienthi");
            } else if (username.equals("ql")) {
                session.setAttribute("role", "ql");
                response.sendRedirect("/quanly/hienthi");
            }
        } else {
            request.setAttribute("message", "sai thong tin dang nhap");
            request.getRequestDispatcher("/demo-filter/login.jsp").forward(request, response);
        }
    }
}