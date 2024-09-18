package com.mycompany.parquedero;

import clases.Vehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svListaParqueo", urlPatterns = {"/svListaParqueo"})
public class svListaParqueo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet svListaParqueo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svListaParqueo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("Entro a cargar las listas en pantalla");

        // Obtener la lista de reservas desde la sesión
        List<Vehiculo> listaMotocilclista = (List<Vehiculo>) session.getAttribute("listaMotocicletas");
        List<Vehiculo> vehiculosLigeros = (List<Vehiculo>) session.getAttribute("vehiculosLigeros");

        // Pasar la lista al JSP
        request.setAttribute("listaReservaMoto", listaMotocilclista);
        request.setAttribute("listaReservaVehiculo", vehiculosLigeros);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
