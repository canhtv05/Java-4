package controller;

import com.google.gson.Gson;
import entity.KhachHang;
import entity.SanPhamChiTiet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.RepositoryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "KhachHangServlet", value = {
        "/api/khach-hang/view-all"
})
public class KhachHangServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/api/khach-hang/view-all": {
                viewAll(request, response);
                break;
            }
        }
    }

    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tenOrMa = request.getParameter("ten-or-sdt");
        List<KhachHang> khachHangList = RepositoryManager.khachHangRepository.getAllByParam(tenOrMa);

        String json = gson.toJson(khachHangList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}