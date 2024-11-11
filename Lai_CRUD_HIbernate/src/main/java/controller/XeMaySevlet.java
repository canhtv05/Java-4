package controller;

import entity.XeMay;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.XeMayRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "XeMaySevlet", value = {
        "/xe-may/view",
        "/xe-may/view-add",
        "/xe-may/view-update",
        "/xe-may/delete",

        "/xe-may/add",
        "/xe-may/update"
})
public class XeMaySevlet extends HttpServlet {
    private XeMayRepo repo = new XeMayRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/xe-may/view": {
                listView(request, response);
                break;
            }
            case "/xe-may/view-add": {
                viewAdd(request, response);
                break;
            }
            case "/xe-may/view-update": {
                viewUpdate(request, response);
                break;
            }
            case "/xe-may/delete": {
                delete(request, response);
                break;
            }
        }
    }

    private void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<XeMay> list = repo.getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/view/listView.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        XeMay xm = repo.findOne(id);
        repo.delete(xm);
        response.sendRedirect("/xe-may/view");
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        XeMay xm = repo.findOne(id);
        request.setAttribute("xm", xm);
        request.getRequestDispatcher("/view/viewUpdate.jsp").forward(request, response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/viewAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/xe-may/add": {
                add(request, response);
                break;
            }
            case "/xe-may/update": {
                update(request, response);
                break;
            }
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String ten = request.getParameter("ten");
        Double gia = Double.parseDouble(request.getParameter("gia"));

        repo.update(new XeMay(id, ten, gia));
        response.sendRedirect("/xe-may/view");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        Double gia = Double.parseDouble(request.getParameter("gia"));
        XeMay xm = new XeMay(null, ten, gia);
        repo.add(xm);
        response.sendRedirect("/xe-may/view");
    }
}