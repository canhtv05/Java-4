package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PhongKham;
import repository.PhongKhamRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PhongKhamSevlet", value = {
        "/phongKham/view",
        "/phongKham/view-add",
        "/phongKham/view-update",
        "/phongKham/detail",
        "/phongKham/delete",
        "/phongKham/search",
        "/phongKham/paging",

        "/phongKham/add",
        "/phongKham/update",
})
public class PhongKhamSevlet extends HttpServlet {
    private PhongKhamRepo repo = new PhongKhamRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/phongKham/view": {
                listView(request, response);
                break;
            }
            case "/phongKham/view-add": {
                viewAdd(request, response);
                break;
            }
            case "/phongKham/view-update": {
                viewUpdate(request, response);
                break;
            }
            case "/phongKham/detail": {
                detail(request, response);
                break;
            }
            case "/phongKham/delete": {
                deletePhongKham(request, response);
                break;
            }
            case "/phongKham/search": {
                search(request, response);
                break;
            }
            case "/phongKham/paging": {
                paging(request, response);
                break;
            }
        }
    }

    private void paging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = 1;
        Integer pageSize = 2;

        if(request.getParameter("page") != null) {
            pageNo = Integer.parseInt(request.getParameter("page"));
            if (pageNo < 1) pageNo = 1;
        }
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("list", repo.paging(pageNo, pageSize));
        request.getRequestDispatcher("/view/listView.jsp").forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ten = request.getParameter("ten");
        request.setAttribute("list", repo.search(ten));
        request.getRequestDispatcher("/view/listView.jsp").forward(request, response);
    }

    private void deletePhongKham(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        repo.delete(new PhongKham(id, null, null));
        response.sendRedirect("/phongKham/view");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        PhongKham phongKham = repo.getOne(id);
        request.setAttribute("pk", phongKham);
        request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        PhongKham phongKham = repo.getOne(id);
        request.setAttribute("pk", phongKham);
        request.getRequestDispatcher("/view/viewUpdate.jsp").forward(request, response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/viewAdd.jsp").forward(request, response);
    }

    private void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PhongKham> list = repo.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/view/listView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/phongKham/update": {
                updatePhongKham(request, response);
                break;
            }
            case "/phongKham/add": {
                addPhongKham(request, response);
                break;
            }
        }
    }

    private void updatePhongKham(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PhongKham pk = getDataToForm(request);
        repo.add(pk);
        response.sendRedirect("/phongKham/view");
    }

    private void addPhongKham(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = (request.getParameter("ten"));
        String soNha = request.getParameter("soNha");
        PhongKham pk = new PhongKham(null, ten, soNha);
        repo.add(pk);
        response.sendRedirect("/phongKham/view");
    }

    private PhongKham getDataToForm(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ten = (request.getParameter("ten"));
        String soNha = request.getParameter("soNha");
        return new PhongKham(id, ten, soNha);
    }


}