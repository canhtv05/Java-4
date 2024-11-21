package controller;

import enity.HoaDon;
import enity.HoaDonChiTiet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.HoaDonChiTietRepo;
import repository.HoaDonRepo;

import java.io.IOException;

@WebServlet(name = "HoaDonChiTietServlet", value = {
        "/hdct/all",
        "/hdct/delete",
        "/hdct/add",
        "/hdct/view-update",
        "/hdct/update"
})
public class HoaDonChiTietServlet extends HttpServlet {
    private HoaDonRepo hoaDonRepo = new HoaDonRepo();
    private HoaDonChiTietRepo hoaDonChiTietRepo = new HoaDonChiTietRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/hdct/all": {
                list(request, response);
                break;
            }
            case "/hdct/delete": {
                delete(request, response);
                break;
            }
            case "/hdct/view-update": {
                viewUpdate(request, response);
                break;
            }
        }
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("hd", hoaDonRepo.getAll());
        request.setAttribute("hdct", hoaDonChiTietRepo.getOne(id));
        request.getRequestDispatcher("/viewUpdate.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        hoaDonChiTietRepo.delete(id);
        response.sendRedirect("/hdct/all");
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hd", hoaDonRepo.getAll());
        request.setAttribute("hdct", hoaDonChiTietRepo.getAll());

        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/hdct/add": {
                add(request, response);
                break;
            }
            case "/hdct/update": {
                update(request, response);
                break;
            }
        }
    }

    private HoaDonChiTiet getDataToForm(HttpServletRequest request, Integer id) {
        String tenSP = request.getParameter("ten");
        Integer soLuong = Integer.parseInt(request.getParameter("soLuong"));
        Double gia = Double.parseDouble(request.getParameter("gia"));
        String ghiChu = request.getParameter("ghiChu");

        HoaDon hdct = hoaDonRepo.getOne(Integer.parseInt(request.getParameter("hoaDon")));

        return new HoaDonChiTiet(id, tenSP, soLuong, gia, ghiChu, hdct);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        hoaDonChiTietRepo.update(getDataToForm(request, id));
        response.sendRedirect("/hdct/all");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        hoaDonChiTietRepo.add(getDataToForm(request, null));
        response.sendRedirect("/hdct/all");
    }
}