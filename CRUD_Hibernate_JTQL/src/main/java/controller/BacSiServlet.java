package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.BacSi;
import model.PhongKham;
import repository.BacSiRepo;
import repository.PhongKhamRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BacSiServlet", value = {
        "/bac-si/hien-thi",
        "/bac-si/delete",
        "/bac-si/add",
        "/bac-si/update",
        "/bac-si/view-update",
})
public class BacSiServlet extends HttpServlet {
    private BacSiRepo bacSiRepo = new BacSiRepo();
    private PhongKhamRepo phongKhamRepo = new PhongKhamRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/bac-si/hien-thi": {
                viewBacSi(request, response);
                break;
            }
            case "/bac-si/delete": {
                delBacSi(request, response);
                break;
            }
            case "/bac-si/view-update": {
                viewUpdateBacSi(request, response);
                break;
            }

        }
    }

    private void viewUpdateBacSi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        BacSi bacSi = bacSiRepo.getOne(id);

        List<PhongKham> pk = phongKhamRepo.getAll();
        request.setAttribute("bs", bacSi);
        request.setAttribute("pk", pk);
        request.getRequestDispatcher("/view/updateBacSi.jsp").forward(request, response);
    }

    private void delBacSi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        bacSiRepo.delete(new BacSi(id, null, null, null, null));
        response.sendRedirect("/bac-si/hien-thi");
    }

    private void viewBacSi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bs", bacSiRepo.getAll());
        request.setAttribute("pk", phongKhamRepo.getAll());
        request.getRequestDispatcher("/view/viewBacSi.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/bac-si/add": {
                addBacSi(request, response);
                break;
            }
            case "/bac-si/update": {
                updateBacSi(request, response);
                break;
            }
        }
    }

    private void updateBacSi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        Double luong = Double.parseDouble(request.getParameter("luong"));

        Integer phongKhamId = Integer.parseInt(request.getParameter("phongKham"));
        PhongKham pk =phongKhamRepo.getOne(phongKhamId);
        BacSi bs = new BacSi(id, ten, diaChi, luong, pk);

        bacSiRepo.update(bs);
        response.sendRedirect("/bac-si/hien-thi");
    }

    private void addBacSi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        Double luong = Double.parseDouble(request.getParameter("luong"));
        Integer phongKhamId = Integer.parseInt(request.getParameter("phongKham"));
        PhongKham pk = phongKhamRepo.getOne(phongKhamId);

        BacSi bs = new BacSi(null, ten, diaChi, luong, pk);

        bacSiRepo.add(bs);
        response.sendRedirect("/bac-si/hien-thi");
    }
}