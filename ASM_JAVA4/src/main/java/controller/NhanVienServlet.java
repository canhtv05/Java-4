package controller;

import com.google.gson.Gson;
import entity.ErrorMessage;
import entity.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "NhanVienServlet", value = {
        "/api/nv/view-all",
        "/api/nv/login",
        "/api/nv/logout",
        "/api/nv/my-hoa-don",
        "/api/nv/current-user"
})
public class NhanVienServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/api/nv/view-all": {
                viewAll(request, response);
                break;
            }
            case "/api/nv/current-user": {
                getCurrentUser(request, response);
                break;
            }
            case "/api/nv/my-hoa-don": {
                getMyHoaDon(request, response);
                break;
            }
        }
    }

    private void getMyHoaDon(HttpServletRequest request, HttpServletResponse response) {

    }

    private void getCurrentUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        NhanVien currentUser = null;
        if (session != null) {
            currentUser = (NhanVien) session.getAttribute("currentUser");
        }

        String json = null;

        if (currentUser == null) {
            json = gson.toJson(new ErrorMessage(401, "Please login first", false));
        } else {
            json = gson.toJson(currentUser);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }


    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<NhanVien> list = RepositoryManager.nhanVienRepository.getAll();

        String data = gson.toJson(list);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(data);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/api/nv/login": {
                handleLogin(request, response);
                break;
            }
            case "/api/nv/logout": {
                handleLogout(request, response);
                break;
            }
        }

    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("currentUser");
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        NhanVien nv = RepositoryManager.nhanVienRepository.login(username, password);

        PrintWriter writer = response.getWriter();

        if (nv != null) {
            session.setAttribute("currentUser", nv);
            session.setMaxInactiveInterval(30 * 60);
            String json = gson.toJson(nv);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            writer.print(json);
        } else {
            ErrorMessage errorMessage = new ErrorMessage(401, "Invalid username or password", false);
            String json = gson.toJson(errorMessage);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            writer.println(json);
        }
        writer.flush();
    }
}