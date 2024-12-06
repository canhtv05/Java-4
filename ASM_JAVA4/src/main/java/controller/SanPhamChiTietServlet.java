package controller;

import com.google.gson.Gson;
import entity.KichThuoc;
import entity.MauSac;
import entity.SanPham;
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
        "/api/spct/update-dongia-soluong"
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
            case "/api/spct/update-dongia-soluong": {
                updateDonGiaSoLuong(request, response);
                break;
            }
        }
    }

    private void updateDonGiaSoLuong(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Double donGia = Double.parseDouble(request.getParameter("donGia"));
        Integer soLuong = Integer.parseInt(request.getParameter("soLuong"));

        System.out.println("sl " + soLuong +  "dg " + donGia);

        SanPhamChiTiet sanPhamChiTiet = RepositoryManager.sanPhamChiTietRepository.updateSoLuongVaDonGia(id, soLuong, donGia);
        String json = gson.toJson(sanPhamChiTiet);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }

    private void createSPCT(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maSPCT = request.getParameter("ma-spct");
        Integer idMauSac = Integer.parseInt(request.getParameter("id-ms"));
        Integer idKichThuoc = Integer.parseInt(request.getParameter("id-kt"));
        Integer idSP = Integer.parseInt(request.getParameter("id-sp"));
        Integer soLuong = Integer.parseInt(request.getParameter("so-luong"));
        Double donGia = Double.parseDouble(request.getParameter("don-gia"));

        MauSac ms = RepositoryManager.mauSacRepository.getOne(idMauSac);
        KichThuoc kt = RepositoryManager.kichThuocRepository.getOne(idKichThuoc);
        SanPham sp = RepositoryManager.sanPhamRepository.getOne(idSP);

        SanPhamChiTiet sanPhamChiTiet = RepositoryManager.sanPhamChiTietRepository.add(new SanPhamChiTiet(null,
                maSPCT, soLuong, donGia, 1, ms, kt, sp));

        String json = gson.toJson(sanPhamChiTiet);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }
}