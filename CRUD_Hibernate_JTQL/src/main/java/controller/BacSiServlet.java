package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.BacSiRepo;
import repository.PhongKhamRepo;

import java.io.IOException;

@WebServlet(name = "BacSiServlet", value = {
        "/bac-si/hien-thi",
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
        }
    }

    private void viewBacSi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bs",bacSiRepo.getAll());
        request.setAttribute("pk",phongKhamRepo.getAll());
        request.getRequestDispatcher("/view/viewBacSi.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}