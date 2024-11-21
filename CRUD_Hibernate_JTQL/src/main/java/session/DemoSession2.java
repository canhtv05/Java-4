package session;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "DemoSession2", value = "/DemoSession2")
public class DemoSession2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext application = getServletContext();


        String sessionMess = (String) session.getAttribute("session");
//        String applicationMess = (String) application.getAttribute("application");

        request.setAttribute("session", sessionMess);
//        request.setAttribute("application", applicationMess);
        request.getRequestDispatcher("/view/session.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}