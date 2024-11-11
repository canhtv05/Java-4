package controller;

import entity.DanhMuc;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.DanhMucRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DanhMucServlet", value = {
        "/danh-muc/view",
        "/danh-muc/delete",
        "/danh-muc/view-add",
        "/danh-muc/view-update",

        "/danh-muc/add",
        "/danh-muc/update",
})
public class DanhMucServlet extends HttpServlet {
    private DanhMucRepo repo = new DanhMucRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/danh-muc/view": {
                viewList(request, response);
                break;
            }
            case "/danh-muc/view-add": {
                viewAdd(request, response);
                break;
            }
            case "/danh-muc/view-update": {
                viewUpdate(request, response);
                break;
            }
            case "/danh-muc/delete": {
                delete(request, response);
                break;
            }
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        DanhMuc dm = new DanhMuc(id, null, null);
        repo.delete(dm);
        response.sendRedirect("/danh-muc/view");
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        DanhMuc danhMuc = repo.getOne(id);
        request.setAttribute("dm", danhMuc);
        request.getRequestDispatcher("/view/viewUpdate.jsp").forward(request, response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/viewAdd.jsp").forward(request, response);
    }

    private void viewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DanhMuc> list = repo.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/view/viewList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/danh-muc/update": {
                update(request, response);
                break;
            }
            case "/danh-muc/add": {
                add(request, response);
                break;
            }
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        Integer soLuong = Integer.parseInt(request.getParameter("soLuong"));
        DanhMuc danhMuc = new DanhMuc(null, ten, soLuong);
        repo.add(danhMuc);
        response.sendRedirect("/danh-muc/view");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        Integer soLuong = Integer.parseInt(request.getParameter("soLuong"));
        DanhMuc danhMuc = new DanhMuc(id, ten, soLuong);
        repo.update(danhMuc);
        response.sendRedirect("/danh-muc/view");
    }
}