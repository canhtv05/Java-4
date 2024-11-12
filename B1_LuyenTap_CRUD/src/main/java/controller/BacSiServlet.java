package controller;

import entity.BacSi;
import entity.PhongKham;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repo.BacSiRepo;
import repo.PhongKhamRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BacSiServlet", value = {
        "/bs/view",
        "/bs/add",
        "/bs/view-update",
        "/bs/update",
        "/bs/delete"
})
public class BacSiServlet extends HttpServlet {
    private PhongKhamRepo repo = new PhongKhamRepo();
    private BacSiRepo bsRepo = new BacSiRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/bs/view": {
                listView(request, response);
                break;
            }
            case "/bs/delete": {
                deleteBS(request, response);
                break;
            }
            case "/bs/view-update": {
                viewUpdateBS(request, response);
                break;
            }
        }
    }

    private void viewUpdateBS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("bs", bsRepo.getOne(id));
        request.setAttribute("pk", repo.getAll());
        request.getRequestDispatcher("/views/viewUpdateBS.jsp").forward(request, response);
    }

    private void deleteBS(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        bsRepo.delete(new BacSi(id, null,null, null,null));
        response.sendRedirect("/bs/view");
    }

    private void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BacSi> list = bsRepo.getAll();
        request.setAttribute("bs", list);
        request.setAttribute("pk", repo.getAll());
        request.getRequestDispatcher("/views/viewBS.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/bs/add": {
                addPK(request, response);
                break;
            }
            case "/bs/update": {
                updatePK(request, response);
                break;
            }
        }
    }

    private void updatePK(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        Double luong = Double.parseDouble(request.getParameter("luong"));

        Integer idPK = Integer.parseInt(request.getParameter("phongKham"));

        PhongKham pk = repo.getOne(idPK);

        BacSi bs = new BacSi(id, ten, diaChi, luong, pk);


        bsRepo.update(bs);
        response.sendRedirect("/pk/view");
    }

    private void addPK(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        Double luong = Double.parseDouble(request.getParameter("luong"));

        Integer idPK = Integer.parseInt(request.getParameter("phongKham"));

        PhongKham pk = repo.getOne(idPK);

        bsRepo.add( new BacSi(null, ten, diaChi, luong, pk));
        response.sendRedirect("/pk/view");
    }
}