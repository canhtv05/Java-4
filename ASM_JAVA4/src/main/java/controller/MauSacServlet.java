package controller;

import com.google.gson.Gson;
import entity.MauSac;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "MauSacServlet", value = {
        "/api/mau-sac/view-all",
})
public class MauSacServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/api/mau-sac/view-all": {
                viewAll(request, response);
                break;
            }
        }
    }

    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<MauSac> list = RepositoryManager.mauSacRepository.getAllWhenIdEqualsTo1();

        String json = gson.toJson(list);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}