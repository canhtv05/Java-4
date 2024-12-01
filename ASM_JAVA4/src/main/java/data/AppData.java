package data;

import entity.NhanVien;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AppData {
    private static final AppData instance = new AppData();

    private AppData() {}

    public static AppData getInstance() {
        return instance;
    }

    private NhanVien currentUser;

    public NhanVien getNhanVienData() {
        return currentUser;
    }

    public NhanVien getNhanVien(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (NhanVien) session.getAttribute("NhanVien");
    }

    public boolean hasLogged(HttpServletRequest request) {
        NhanVien nhanVien = getNhanVien(request);

        return nhanVien != null;
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("NhanVien");
        currentUser = null;
    }
}
