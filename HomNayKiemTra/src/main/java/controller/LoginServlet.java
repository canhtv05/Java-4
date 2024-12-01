package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {
        "/login"
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if(path.contains("login")) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tk = request.getParameter("tk");
        String mk = request.getParameter("mk");
        HttpSession session = request.getSession();

        if(tk.equals("HangNT169") && mk.equals("1234567")) {
            session.setAttribute("tk", tk);
            response.sendRedirect("/song-management/playlists");
        } else {
            request.setAttribute("msg", "mk ko dung");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}