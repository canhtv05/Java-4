package demoFilter.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", value = {
        // matches voi all co uri la nhan vien or quanly
        "/nhanvien/*",
        "/quanly/*",
})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null) {
            if (role.equals("ql")) {
                // cho phep nguoi dung vao all path ben tren khai bao o value
                filterChain.doFilter(request, response);
            } else if (role.equals("nv")) {
                String uri = request.getRequestURI();

                if (uri.contains("nhanvien")) {
                    // uri phai la nhan vien thi moi cho truy cap
                    filterChain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher("/demo-filter/403.jsp").forward(request, response);
                }
            }
        } else {
            response.sendRedirect("/demo-filter/login");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
