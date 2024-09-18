package com.mycompany.parquedero;

import clases.Parqueadero;

import clases.Vehiculo;
import enums.TipoVehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svRegistroVehiculo", urlPatterns = {"/svRegistroVehiculo"})
public class svRegistroVehiculo extends HttpServlet {

    private List<Vehiculo> motocicletas = new ArrayList<>();
    private List<Vehiculo> vehiculosLigeros = new ArrayList<>();

    private  int plazasMotos = 6;
    private int countPlazaMotos = 1;
    private int countplazasVehiculos = 1;
    private  int plazasVehiculos = 5;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet svRegistroVehiculo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svRegistroVehiculo at " + request.getContextPath() + "</h1>");
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
        Parqueadero parqueadero = new Parqueadero();
        HttpSession session = request.getSession();
        
        String placa = request.getParameter("placa");
        TipoVehiculo tipo = TipoVehiculo.valueOf(request.getParameter("tipo"));
        boolean esHibrido = Boolean.parseBoolean(request.getParameter("hibrido"));
        LocalDateTime ingreso = LocalDateTime.now();
        int plazaAsignada = Integer.parseInt(request.getParameter("plaza"));
        
         if (placa.isEmpty()  || plazaAsignada < 1) {
            // si algun dato viene vacio nosotros mostramos un mensaje que diga que ingrese todos los valores 
            request.setAttribute("infomacion", "Ingrese toda la informacion");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        System.out.println("datos:" + ingreso );
        Vehiculo vehiculo = new Vehiculo(placa, tipo, esHibrido, ingreso, plazaAsignada);

        if (tipo == TipoVehiculo.MOTOCICLETA) {
            if (countPlazaMotos <= plazasMotos) {
                countPlazaMotos += 1;
                motocicletas.add(vehiculo);
                System.out.println("moticicletas contador" + countPlazaMotos);
                session.setAttribute("listaMotocicletas", motocicletas);
                request.setAttribute("infomacion", "Ingresó correctamente la reserva");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else if (tipo == TipoVehiculo.VEHICULO_LIGERO) {
            if (countplazasVehiculos <= plazasVehiculos) {
                countplazasVehiculos += 1;
                vehiculosLigeros.add(vehiculo);
                session.setAttribute("vehiculosLigeros", vehiculosLigeros);
                request.setAttribute("infomacion", "Ingreso correctamente la reserva");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        request.setAttribute("infomacion", "Plaza llena para :" + tipo);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}
