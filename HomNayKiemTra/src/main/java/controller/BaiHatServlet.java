package controller;

import entity.BaiHat;
import entity.CaSi;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.BaiHatRepo;
import repository.CaSiRepo;

import java.io.IOException;

@WebServlet(name = "BaiHatServlet", value = {
        "/song-management/playlists",
        "/song-management/playlists/paging",
        "/song-management/add",
        "/song-management/detail"
})
public class BaiHatServlet extends HttpServlet {
    private BaiHatRepo baiHatRepo = new BaiHatRepo();
    private CaSiRepo caSiRepo = new CaSiRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/song-management/playlists": {
                viewAll(request, response);
                break;
            }
            case "/song-management/detail": {
                detail(request, response);
                break;
            }
            case "/song-management/playlists/paging": {
                paging(request, response);
                break;
            }
        }
    }

    private void paging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = 1;
        Integer pageSize = 5;

        if(request.getParameter("page") != null) {
            pageNo = Integer.parseInt(request.getParameter("page"));
            if(pageNo < 1) pageNo = 1;
        }

        System.out.println(pageNo);
        int total = (int)Math.ceil(baiHatRepo.total() / pageSize);
        System.out.println(total);

        request.setAttribute("bh", baiHatRepo.paging(pageNo, pageSize));
        request.setAttribute("cs", caSiRepo.getAll());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("total", total);
        request.getRequestDispatcher("/playlist.jsp").forward(request, response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        BaiHat bh = baiHatRepo.getOne(id);

        request.setAttribute("bh", bh);
        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }

    private void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bh", baiHatRepo.getAll());
        request.setAttribute("cs", caSiRepo.getAll());
        request.getRequestDispatcher("/playlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
            case "/song-management/add": {
                add(request, response);
                break;
            }

        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tenBH = request.getParameter("ten");
        Integer tl = Integer.parseInt(request.getParameter("thoiLuong"));
        Double gia = Double.parseDouble(request.getParameter("gia"));
        String ngayRaMat = request.getParameter("ngayRaMat");
        Integer idCaSi = Integer.parseInt(request.getParameter("caSi"));
        CaSi caSi = caSiRepo.getOne(idCaSi);

        baiHatRepo.add(new BaiHat(null,tenBH,gia, tl, ngayRaMat, caSi));
        response.sendRedirect("/song-management/playlists");
    }
}