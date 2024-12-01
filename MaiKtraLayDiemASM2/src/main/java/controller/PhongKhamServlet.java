package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.PhongKhamRepo;

import java.io.IOException;

@WebServlet(name = "PhongKhamServlet", value = {
        "/pk/all"
})
public class PhongKhamServlet extends HttpServlet {
    private PhongKhamRepo repo = new PhongKhamRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if(path.startsWith("/pk/all")) {
            Integer pageNo = 1;
            Integer pageSize = 2;

            if(request.getParameter("page") != null) {
                pageNo = Integer.parseInt(request.getParameter("page"));
                if(pageNo < 1) pageNo = 1;
            }

            request.setAttribute("pk" , repo.getAll(pageNo, pageSize));
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("totalCount", (int)Math.ceil((repo.getTotalCount()) / pageSize));
            request.getRequestDispatcher("/view.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}