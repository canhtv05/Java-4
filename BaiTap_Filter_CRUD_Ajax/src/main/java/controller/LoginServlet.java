package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {
        "/login",
        "/logout"
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        
        if(path.equals("/login")) {
            loginForm(request, response);
        } else if(path.equals("/logout")) {
            logout(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.invalidate();
        response.sendRedirect("/login");
    }

    private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");

        if(taiKhoan.equals("abc") && matKhau.equals("abc")) {
            session.setAttribute("taiKhoan", taiKhoan);
            response.sendRedirect("/gv/view-all");
        } else {
            request.setAttribute("msg","Co loi xay ra, kiem tra tai khoan hoac mat khau");
            request.getRequestDispatcher("/views/login-form.jsp").forward(request, response);
        }
    }
}