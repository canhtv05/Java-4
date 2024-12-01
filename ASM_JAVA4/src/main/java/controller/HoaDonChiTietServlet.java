package controller;

import com.google.gson.Gson;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.SanPhamChiTiet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HoaDonChiTiet", value = {
        "/api/hoa-don/detail",
        "/api/hoa-don-chi-tiet/create",
})
public class HoaDonChiTietServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/api/hoa-don/detail": {
                detailHoaDon(request, response);
                break;
            }
        }
    }

    private void detailHoaDon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        List<HoaDonChiTiet> hoaDonChiTietList = RepositoryManager.hoaDonChiTietRepository.getAllByIdHoaDon(id);

        String json = gson.toJson(hoaDonChiTietList);
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
            case "/api/hoa-don-chi-tiet/create": {
                createHDCT(request, response);
                break;
            }
        }
    }

    private void createHDCT(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer idSPCT = Integer.parseInt(request.getParameter("id-spct"));
        Integer idHoaDon = Integer.parseInt(request.getParameter("id-hoa-don"));
        Integer soLuong = Integer.parseInt(request.getParameter("so-luong"));
        Double donGia = Double.parseDouble(request.getParameter("don-gia"));

        SanPhamChiTiet sanPhamChiTiet = RepositoryManager.sanPhamChiTietRepository.getOne(idSPCT);
        HoaDon hoaDon = RepositoryManager.hoaDonRepository.getOne(idHoaDon);
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(null, soLuong, donGia, 1,sanPhamChiTiet, hoaDon);

        HoaDonChiTiet insertHoaDonCT = RepositoryManager.hoaDonChiTietRepository.add(hoaDonChiTiet);
        String json = gson.toJson(insertHoaDonCT);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }
}