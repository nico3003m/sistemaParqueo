package com.mycompany.parquedero;

import clases.Vehiculo;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svCerrarOperacion", urlPatterns = {"/svCerrarOperacion"})
public class svCerrarOperacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Recuperar las listas de vehículos sacados y vehículos en el parqueadero
        List<Vehiculo> listaSacados = (List<Vehiculo>) session.getAttribute("vehiculosSacados");
        List<Vehiculo> listaMotocicletas = (List<Vehiculo>) session.getAttribute("listaMotocicletas");
        List<Vehiculo> vehiculosLigeros = (List<Vehiculo>) session.getAttribute("vehiculosLigeros");

        double gananciasTotales = 0;

        // Calcular ganancias de los vehículos sacados
        if (listaSacados != null) {
            for (Vehiculo vehiculo : listaSacados) {
                gananciasTotales += vehiculo.costos();
            }
        }

        // Forzar la salida de los vehículos que aún están en el parqueadero
        if (listaMotocicletas != null) {
            for (Vehiculo vehiculo : listaMotocicletas) {
                vehiculo.setSalida(LocalDateTime.now());
                gananciasTotales += vehiculo.costos();
            }
            listaMotocicletas.clear(); // Limpiar la lista de motocicletas
        }

        if (vehiculosLigeros != null) {
            for (Vehiculo vehiculo : vehiculosLigeros) {
                vehiculo.setSalida(LocalDateTime.now());
                gananciasTotales += vehiculo.costos();
            }
            vehiculosLigeros.clear(); // Limpiar la lista de vehículos ligeros
        }

        // Limpiar la lista de vehículos sacados
        if (listaSacados != null) {
            listaSacados.clear();
        }

        // Guardar el total de ganancias en la sesión y redirigir al JSP
        session.setAttribute("gananciasTotales", gananciasTotales);
        response.sendRedirect("cerrarOperacion.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para cerrar operación y calcular ganancias";
    }
}
