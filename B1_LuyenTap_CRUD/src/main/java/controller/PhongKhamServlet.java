package controller;

import entity.PhongKham;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repo.PhongKhamRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PhongKhamServlet", value = {
        "/pk/view",
        "/pk/add",
        "/pk/view-update",
        "/pk/update",
        "/pk/delete"
})
public class PhongKhamServlet extends HttpServlet {
    private PhongKhamRepo repo = new PhongKhamRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/pk/view": {
                listView(request, response);
                break;
            }
            case "/pk/delete": {
                deletePK(request, response);
                break;
            }
            case "/pk/view-update": {
                viewUpdatePK(request, response);
                break;
            }
        }
    }

    private void viewUpdatePK(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("pk", repo.getOne(id));
        request.getRequestDispatcher("/views/viewUpdatePK.jsp").forward(request, response);
    }

    private void deletePK(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        repo.delete(new PhongKham(id, null,null));
        response.sendRedirect("/pk/view");
    }

    private void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PhongKham> list = repo.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/viewPK.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/pk/add": {
                addPK(request, response);
                break;
            }
            case "/pk/update": {
                updatePK(request, response);
                break;
            }
        }
    }

    private void updatePK(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        Integer soNha = Integer.parseInt(request.getParameter("soNha"));

        repo.update(new PhongKham(id, ten, soNha));
        response.sendRedirect("/pk/view");
    }

    private void addPK(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        Integer soNha = Integer.parseInt(request.getParameter("soNha"));

        repo.add(new PhongKham(null, ten, soNha));
        response.sendRedirect("/pk/view");
    }
}