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

@WebServlet(name = "svSacarVehiculo", urlPatterns = {"/svSacarVehiculo"})
public class svSacarVehiculo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Obtener los datos del formulario
        String placa = request.getParameter("placa");
        System.out.println("Placa recibida para sacar: " + placa);

        // Obtener las listas de vehículos activos de la sesión
        List<Vehiculo> listaMotocicletas = (List<Vehiculo>) session.getAttribute("listaMotocicletas");
        List<Vehiculo> vehiculosLigeros = (List<Vehiculo>) session.getAttribute("vehiculosLigeros");
        List<Double> listaGanancias = (List<Double>) session.getAttribute("listaGanancias");

        // Inicializar lista de ganancias si no existe
        if (listaGanancias == null) {
            listaGanancias = new ArrayList<>();
            session.setAttribute("listaGanancias", listaGanancias);
        }

        // Buscar y eliminar el vehículo de las listas
        Vehiculo vehiculoASacar = null;
        if (listaMotocicletas != null) {
            for (Vehiculo moto : listaMotocicletas) {
                if (moto.getPlaca().equals(placa)) {
                    vehiculoASacar = moto;
                    listaMotocicletas.remove(moto);
                    break;
                }
            }
        }

        if (vehiculoASacar == null && vehiculosLigeros != null) {
            for (Vehiculo vehiculo : vehiculosLigeros) {
                if (vehiculo.getPlaca().equals(placa)) {
                    vehiculoASacar = vehiculo;
                    vehiculosLigeros.remove(vehiculo);
                    break;
                }
            }
        }

        if (vehiculoASacar != null) {
            // Calcular el costo del vehículo y agregar a la lista de ganancias
            System.out.println("fecha salida " + LocalDateTime.now());
            vehiculoASacar.setSalida(LocalDateTime.now());
            double costo = vehiculoASacar.costos();
            listaGanancias.add(costo);
            System.out.println("Costo calculado para la placa " + placa + ": " + costo);

            // Actualizar las listas en la sesión
            session.setAttribute("listaMotocicletas", listaMotocicletas);
            session.setAttribute("vehiculosLigeros", vehiculosLigeros);

            // Redirigir a la página de sacar vehículo
            response.sendRedirect("sacarVehiculo.jsp");
        } else {
            // Enviar mensaje de error si no se encuentra el vehículo
            System.out.println("Vehículo con placa " + placa + " no encontrado.");
            session.setAttribute("error", "Vehículo no encontrado.");
            response.sendRedirect("sacarVehiculo.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para sacar un vehículo del parqueo y calcular el costo.";
    }
}
