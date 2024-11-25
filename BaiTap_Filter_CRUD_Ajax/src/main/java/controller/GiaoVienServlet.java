package controller;

import com.google.gson.Gson;
import entity.GiaoVien;
import entity.TruongHoc;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.GiaoVienRepository;
import repository.TruongHocRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GiaoVienServlet", value = {
        "/gv/view-all",
        "/gv/view-update",
        "/gv/detail",
        "/gv/delete",
        "/gv/api/all-res",
        "/gv/add",
        "/gv/update"
})
public class GiaoVienServlet extends HttpServlet {

    private GiaoVienRepository giaoVienRepository = new GiaoVienRepository();
    private TruongHocRepository truongHocRepository = new TruongHocRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path  =request.getServletPath();

        switch (path) {
            case "/gv/view-all": {
                viewAll(request, response);
                break;
            }
            case "/gv/view-update": {
                viewUpdate(request, response);
                break;
            }
            case "/gv/delete": {
                delete(request, response);
                break;
            }
            case "/gv/detail": {
                viewDetail(request, response);
                break;
            }
            case "/gv/api/all-res": {
                viewApiConsole(request, response);
                break;
            }
        }
    }

    private void viewApiConsole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GiaoVien> list = giaoVienRepository.getAll();
        Gson gson = new Gson();
        String data = gson.toJson(list);
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        writer.println(data);
        writer.flush();
    }

    private void viewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer idTruongHoc = Integer.parseInt(request.getParameter("id_truong_hoc"));
        request.setAttribute("gv", giaoVienRepository.getOne(id));
        request.setAttribute("th", truongHocRepository.getOne(idTruongHoc));
        request.getRequestDispatcher("/views/view-detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        giaoVienRepository.delete(id);
        response.sendRedirect("/gv/view-all");
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("gv", giaoVienRepository.getOne(id));
        request.setAttribute("th", truongHocRepository.getAll());
        request.getRequestDispatcher("/views/view-update.jsp").forward(request, response);
    }

    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("gv", giaoVienRepository.getAll());
        request.setAttribute("th", truongHocRepository.getAll());
        request.getRequestDispatcher("/views/view-all.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path  =request.getServletPath();

        switch (path) {
            case "/gv/add": {
                add(request, response);
                break;
            }
            case "/gv/update": {
                update(request, response);
                break;
            }
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        Integer tuoi = Integer.parseInt(request.getParameter("tuoi"));
        Double luong = Double.parseDouble(request.getParameter("luong"));
        Integer idTruongHoc = Integer.parseInt(request.getParameter("truongHoc"));

        giaoVienRepository.add(new GiaoVien(null, ten, tuoi, luong, truongHocRepository.getOne(idTruongHoc)));
        response.sendRedirect("/gv/view-all");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        Integer tuoi = Integer.parseInt(request.getParameter("tuoi"));
        Double luong = Double.parseDouble(request.getParameter("luong"));
        Integer idTruongHoc = Integer.parseInt(request.getParameter("truongHoc"));

        GiaoVien giaoVien = new GiaoVien(id, ten, tuoi, luong, truongHocRepository.getOne(idTruongHoc));
        giaoVienRepository.update(giaoVien);
        response.sendRedirect("/gv/view-all");
    }
}