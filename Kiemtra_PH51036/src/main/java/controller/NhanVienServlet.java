package controller;

import entity.NhanVien;
import entity.PhongBan;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.NhanVienRepo;
import repository.PhongBanRepo;

import java.io.IOException;

@WebServlet(name = "NhanVienServlet", value = {
        "/nv/view",
        "/nv/delete",
        "/nv/update",
        "/nv/view-update",
        "/nv/add",
        "/nv/view/paging",
})
public class NhanVienServlet extends HttpServlet {
    private PhongBanRepo phongBanRepo = new PhongBanRepo();
    private NhanVienRepo nhanVienRepo = new NhanVienRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/nv/view": {
                listView(request, response);
                break;
            }
            case "/nv/delete": {
                delete(request, response);
                break;
            }
            case "/nv/view-update": {
                viewUpdate(request, response);
                break;
            }
            case "/nv/view/paging": {
                pagingNhanVien(request, response);
                break;
            }
        }
    }

    private void pagingNhanVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = 1;
        Integer pageSize = 3;

        if(request.getParameter("page") != null) {
            pageNo = Integer.parseInt(request.getParameter("page"));
            if(pageNo < 1) pageNo = 1;
        }

        request.setAttribute("nv", nhanVienRepo.paging((pageNo - 1) * pageSize, pageSize));
        request.setAttribute("pb", phongBanRepo.getAll());
        request.setAttribute("pageNo", pageNo);
        request.getRequestDispatcher("/listView.jsp").forward(request, response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("nv", nhanVienRepo.getOne(id));
        request.setAttribute("pb", phongBanRepo.getAll());
        request.getRequestDispatcher("/viewUpdate.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        nhanVienRepo.delete(id);
        response.sendRedirect("/nv/view");
    }

    private void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nv", nhanVienRepo.getAll());
        request.setAttribute("pb", phongBanRepo.getAll());
        request.getRequestDispatcher("/listView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/nv/add": {
                add(request, response);
                break;
            }
            case "/nv/update": {
             update(request, response);
                break;
            }
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        Integer tuoi = Integer.parseInt(request.getParameter("tuoi"));
        Double luong = Double.parseDouble(request.getParameter("luong"));
        Integer idPhongban = Integer.parseInt(request.getParameter("phongBan"));

        PhongBan phongBan = phongBanRepo.getOne(idPhongban);

        nhanVienRepo.update(new NhanVien(id, ten, tuoi, luong, phongBan));
        response.sendRedirect("/nv/view");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        Integer tuoi = Integer.parseInt(request.getParameter("tuoi"));
        Double luong = Double.parseDouble(request.getParameter("luong"));
        Integer idPhongban = Integer.parseInt(request.getParameter("phongBan"));

        PhongBan phongBan = phongBanRepo.getOne(idPhongban);

        nhanVienRepo.add(new NhanVien(null, ten, tuoi, luong, phongBan));
        response.sendRedirect("/nv/view");
    }
}