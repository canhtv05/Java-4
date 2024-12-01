package controller;

import com.google.gson.Gson;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "HoaDonServlet", value = {
        "/api/hoa-don/view-all",
        "/api/hoa-don/by-me",
        "/api/hoa-don/by-me-one",
        "/api/hoa-don/create",
})
public class HoaDonServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/api/hoa-don/view-all": {
                viewALl(request, response);
                break;
            }
            case "/api/hoa-don/by-me": {
                allHoaDonByMe(request, response);
                break;
            }
            case "/api/hoa-don/by-me-one": {
                oneHoaDonByMe(request, response);
                break;
            }
        }
    }

    private void oneHoaDonByMe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer idNV = Integer.parseInt(request.getParameter("id-nv"));
        Integer idHD = Integer.parseInt(request.getParameter("id-hd"));

        HoaDon hoaDon = RepositoryManager.hoaDonRepository.getOneHoaDonByIdNhanVien(idNV, idHD);

        String json = gson.toJson(hoaDon);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    private void allHoaDonByMe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer idNV = Integer.parseInt(request.getParameter("id"));
        List<HoaDon> list = RepositoryManager.hoaDonRepository.getAllHoaDonByIdNhanVien(idNV);

        String json = gson.toJson(list);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    private void viewALl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<HoaDon> list = RepositoryManager.hoaDonRepository.getAll();

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
        String path = request.getServletPath();
        
        switch (path) {
            case "/api/hoa-don/create": {
                createHoaDon(request, response);
                break;
            }
        }
    }

    private void createHoaDon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idKHParam = request.getParameter("id-kh");
        String idNVParam = request.getParameter("id-nv");

        System.out.println(idKHParam + " " + idNVParam);

        if (idKHParam == null || idKHParam.isEmpty() || idNVParam == null || idNVParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing parameters: id-kh or id-nv");
            return;
        }

        System.out.println(idKHParam + " " + idNVParam);

        Integer idKH = Integer.parseInt(idKHParam);
        Integer idNV = Integer.parseInt(idNVParam);

        LocalDate getDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatDate = formatter.format(getDate);

        KhachHang khachHang= RepositoryManager.khachHangRepository.getOne(idKH);
        NhanVien nhanVien = RepositoryManager.nhanVienRepository.getOne(idNV);

        HoaDon hoaDon = RepositoryManager.hoaDonRepository.add(new HoaDon(null, formatDate, 1, khachHang, nhanVien));

        System.out.println(hoaDon.toString());

        String json = gson.toJson(hoaDon);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }
}