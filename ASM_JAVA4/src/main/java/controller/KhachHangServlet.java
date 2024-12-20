package controller;

import com.google.gson.Gson;
import entity.KhachHang;
import entity.SanPhamChiTiet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "KhachHangServlet", value = {
        "/api/khach-hang/view-all",
        "/api/khach-hang/update",
        "/api/khach-hang/add",
        "/api/khach-hang/update-trang-thai",
})
public class KhachHangServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/api/khach-hang/view-all": {
                viewAll(request, response);
                break;
            }
        }
    }

    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tenOrMa = request.getParameter("ten-or-sdt");
        List<KhachHang> khachHangList = RepositoryManager.khachHangRepository.getAllByParam(tenOrMa);

        String json = gson.toJson(khachHangList);
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
            case "/api/khach-hang/update": {
                update(request, response);
                break;
            }
            case "/api/khach-hang/add": {
                add(request, response);
                break;
            }
            case "/api/khach-hang/update-trang-thai": {
                updateTrangThai(request, response);
                break;
            }
        }
    }

    private void updateTrangThai(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer trangThai = Integer.parseInt(request.getParameter("trang-thai"));

        KhachHang khachHang = RepositoryManager.khachHangRepository.updateTrangThai(id, trangThai);
        String json = gson.toJson(khachHang);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        String sdt = request.getParameter("sdt");
        String maKH = request.getParameter("ma-kh");

        KhachHang khachHang = RepositoryManager.khachHangRepository.add(new KhachHang(null, ten, sdt, maKH, 1));
        String json = gson.toJson(khachHang);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer idKH = Integer.valueOf(request.getParameter("id-kh"));
        String ten = request.getParameter("ten");
        String sdt = request.getParameter("sdt");
        String maKH = request.getParameter("ma-kh");

        KhachHang khachHangEntity = new KhachHang(idKH, ten, sdt, maKH, 1);
        KhachHang khachHang = RepositoryManager.khachHangRepository.update(khachHangEntity);
        String json = gson.toJson(khachHang);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }
}