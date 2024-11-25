package demoAjax;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AjaxServlet", value = {
        "/api/ajax/demo"
})
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SinhVien sinhVien = new SinhVien("PH0001", "N V A", 20);
        Gson gson = new Gson();
        String data = gson.toJson(sinhVien);
        response.setContentType("application/json");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(data);
        printWriter.flush();
    }
}