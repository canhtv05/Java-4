package controller;

import entity.DieuHoa;
import entity.LoaiDieuHoa;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.DieuHoaRepo;
import repository.LoaiDieuHoaRepo;

import java.io.IOException;

@WebServlet(name = "DieuHoaServlet", value = {
        "/dh/all",
        "/dh/add",
        "/dh/delete",
        "/dh/view-update",
        "/dh/update"
})
public class DieuHoaServlet extends HttpServlet {
    private DieuHoaRepo dieuHoaRepo = new DieuHoaRepo();
    private LoaiDieuHoaRepo loaiDieuHoaRepo = new LoaiDieuHoaRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/dh/all": {
                list(request, response);
                break;
            }
            case "/dh/delete": {
                delete(request, response);
                break;
            }
            case "/dh/view-update": {
                viewUpdate(request, response);
                break;
            }
        }
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("dh", dieuHoaRepo.getOne(id));
        request.setAttribute("ldh", loaiDieuHoaRepo.getAll());
        request.getRequestDispatcher("/views/viewUpdate.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        dieuHoaRepo.delete(id);
        response.sendRedirect("/dh/all");
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dh", dieuHoaRepo.getAll());
        request.setAttribute("ldh", loaiDieuHoaRepo.getAll());
        request.getRequestDispatcher("/views/listAirConditioner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/dh/add": {
                add(request, response);
                break;
            }
            case "/dh/update": {
                update(request, response);
                break;
            }
        }
    }

    private DieuHoa getDataFromForm(HttpServletRequest request, Integer id) {
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        Integer idAirConditioner = Integer.parseInt(request.getParameter("brand"));

        LoaiDieuHoa loaiDieuHoa = loaiDieuHoaRepo.getOne(idAirConditioner);
        return new DieuHoa(id, name, price, quantity, loaiDieuHoa);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        DieuHoa dh = getDataFromForm(request, id);

        dieuHoaRepo.update(dh);
        response.sendRedirect("/dh/all");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DieuHoa dh = getDataFromForm(request, null);

        dieuHoaRepo.add(dh);
        response.sendRedirect("/dh/all");
    }
}