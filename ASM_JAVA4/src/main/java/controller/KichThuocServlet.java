package controller;

import com.google.gson.Gson;
import entity.KichThuoc;
import entity.MauSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "KichThuocServlet", value = {
        "/api/kich-thuoc/view-all",
})
public class KichThuocServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/api/kich-thuoc/view-all": {
                viewAll(request, response);
                break;
            }
        }
    }

    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<KichThuoc> list = RepositoryManager.kichThuocRepository.getAllWhenIdEqualsTo1();

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