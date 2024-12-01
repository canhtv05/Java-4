package controller;

import com.google.gson.Gson;
import entity.SanPhamChiTiet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SanPhamChiTietServlet", value = {
        "/api/spct/view-all",
        "/api/spct/one-product",
        "/api/spct/create",
})
public class SanPhamChiTietServlet extends HttpServlet {
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/api/spct/view-all": {
                viewAll(request, response);
                break;
            }
            case "/api/spct/one-product": {
                getOneProduct(request, response);
                break;
            }
        }
    }

    private void getOneProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        SanPhamChiTiet sanPhamChiTiet = RepositoryManager.sanPhamChiTietRepository.getOne(id);

        String json = gson.toJson(sanPhamChiTiet);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tenOrMa = request.getParameter("ten-or-ma");
        List<SanPhamChiTiet> sanPhamChiTietList = RepositoryManager.sanPhamChiTietRepository.getAllByParam(tenOrMa);

        String json = gson.toJson(sanPhamChiTietList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();
        
        switch (path) {
            case "/api/spct/create": {
                createSPCT(request, response);
                break;
            }
        }
    }

    private void createSPCT(HttpServletRequest request, HttpServletResponse response) {
    }
}