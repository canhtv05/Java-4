package controller;

import com.google.gson.Gson;
import entity.KhachHang;
import entity.SanPham;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SanPhamServlet", value = {
        "/api/sp/view-all",
        "/api/sp/update",
        "/api/sp/add",
        "/api/sp/update-trang-thai",
})
public class SanPhamServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/api/sp/view-all": {
                viewAll(request, response);
                break;
            }
        }
    }

    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tenOrMa = request.getParameter("ten-or-ma");
        List<SanPham> sanPhamList = RepositoryManager.sanPhamRepository.getAllByParam(tenOrMa);
        String json = gson.toJson(sanPhamList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/api/sp/update": {
                update(request, response);
                break;
            }
            case "/api/sp/add": {
                add(request, response);
                break;
            }
            case "/api/sp/update-trang-thai": {
                updateTrangThai(request, response);
                break;
            }
        }
    }

    private void updateTrangThai(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer trangThai = Integer.parseInt(request.getParameter("trang-thai"));

        SanPham sanPham = RepositoryManager.sanPhamRepository.updateTrangThai(id, trangThai);
        String json = gson.toJson(sanPham);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maSP = request.getParameter("ma-sp");
        String tenSP = request.getParameter("ten-sp");

        SanPham sanPham = RepositoryManager.sanPhamRepository.add(new SanPham(null, maSP, tenSP, 1));
        String json = gson.toJson(sanPham);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String maSP = request.getParameter("ma-sp");
        String tenSP = request.getParameter("ten-sp");

        SanPham sanPham = new SanPham(id, maSP, tenSP, 1);
        SanPham sanPhamUpdate = RepositoryManager.sanPhamRepository.update(sanPham);
        String json = gson.toJson(sanPhamUpdate);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }
}