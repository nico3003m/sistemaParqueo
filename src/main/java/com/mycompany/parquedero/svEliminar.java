
package com.mycompany.parquedero;

import clases.Vehiculo;
import enums.TipoVehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svEliminar", urlPatterns = {"/svEliminar"})
public class svEliminar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet svEliminar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svEliminar at " + request.getContextPath() + "</h1>");
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

        System.out.println("doPost en svEliminar fue llamado.");
        
        // Instancia de la sesión
        HttpSession session = request.getSession();
        
        // Obtención de parámetros
        String placa = request.getParameter("placa");
        String tipoStr = request.getParameter("tipo");
        
        if (placa == null || tipoStr == null) {
            System.out.println("Parámetros nulos.");
            request.setAttribute("error", "Parámetros incompletos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        
        TipoVehiculo tipo;
        try {
            tipo = TipoVehiculo.valueOf(tipoStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de vehículo no válido.");
            request.setAttribute("error", "Tipo de vehículo no válido.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Obtención de listas desde la sesión
        List<Vehiculo> listaMotocicletas = (List<Vehiculo>) session.getAttribute("listaMotocicletas");
        List<Vehiculo> vehiculosLigeros = (List<Vehiculo>) session.getAttribute("vehiculosLigeros");

        if (listaMotocicletas == null && vehiculosLigeros == null) {
            System.out.println("Ambas listas de reservas son nulas.");
            request.setAttribute("error", "Las listas de reservas son nulas.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        Vehiculo reservaEliminar = null;
        if (tipo == TipoVehiculo.MOTOCICLETA && listaMotocicletas != null) {
            for (Vehiculo reservaMotos : listaMotocicletas) {
                if (placa.equals(reservaMotos.getPlaca())) {
                    reservaEliminar = reservaMotos;
                    break;
                }
            }
            if (reservaEliminar != null) {
                listaMotocicletas.remove(reservaEliminar);
                session.setAttribute("listaMotocicletas", listaMotocicletas);
                System.out.println("Reserva de motocicleta eliminada.");
            } else {
                System.out.println("Reserva de motocicleta no encontrada.");
                request.setAttribute("error", "Reserva de motocicleta no encontrada.");
            }
        } else if (tipo == TipoVehiculo.VEHICULO_LIGERO && vehiculosLigeros != null) {
            for (Vehiculo reservaVehiculo : vehiculosLigeros) {
                if (placa.equals(reservaVehiculo.getPlaca())) {
                    reservaEliminar = reservaVehiculo;
                    break;
                }
            }
            if (reservaEliminar != null) {
                vehiculosLigeros.remove(reservaEliminar);
                session.setAttribute("vehiculosLigeros", vehiculosLigeros);
                System.out.println("Reserva de vehículo ligero eliminada.");
            } else {
                System.out.println("Reserva de vehículo ligero no encontrada.");
                request.setAttribute("error", "Reserva de vehículo ligero no encontrada.");
            }
        } else {
            System.out.println("La lista de reservas es nula.");
            request.setAttribute("error", "La lista de reservas es nula.");
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
