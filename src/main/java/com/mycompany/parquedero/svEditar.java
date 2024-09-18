package com.mycompany.parquedero;

import clases.Vehiculo;
import enums.TipoVehiculo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svEditar", urlPatterns = {"/svEditar"})
public class svEditar extends HttpServlet {

    @Override
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Obtener los datos del formulario
        String placa = request.getParameter("placa");
        TipoVehiculo tipoNuevo = TipoVehiculo.valueOf(request.getParameter("tipo"));
        boolean esHibrido = Boolean.parseBoolean(request.getParameter("hibrido"));
        int plaza = Integer.parseInt(request.getParameter("plaza"));

        // Obtener las listas de vehículos de la sesión
        List<Vehiculo> listaMotocicletas = (List<Vehiculo>) session.getAttribute("listaMotocicletas");
        List<Vehiculo> vehiculosLigeros = (List<Vehiculo>) session.getAttribute("vehiculosLigeros");
        Vehiculo vehiculoAActualizar = null;

        // Buscar el vehículo a actualizar
        if (listaMotocicletas != null) {
            for (Vehiculo moto : listaMotocicletas) {
                if (moto.getPlaca().equals(placa)) {
                    vehiculoAActualizar = moto;
                    listaMotocicletas.remove(moto);
                    break;
                }
            }
        }

        if (vehiculoAActualizar == null && vehiculosLigeros != null) {
            for (Vehiculo vehiculo : vehiculosLigeros) {
                if (vehiculo.getPlaca().equals(placa)) {
                    vehiculoAActualizar = vehiculo;
                    vehiculosLigeros.remove(vehiculo);
                    break;
                }
            }
        }

        if (vehiculoAActualizar != null) {
            // Actualizar los datos del vehículo
            vehiculoAActualizar.setTipo(tipoNuevo);
            vehiculoAActualizar.setEsHibirdo(esHibrido);
            vehiculoAActualizar.setPlazaAsignada(plaza);

            // Agregar el vehículo a la nueva lista según el tipo actualizado
            if (tipoNuevo == TipoVehiculo.MOTOCICLETA) {
                if (listaMotocicletas == null) {
                    listaMotocicletas = new ArrayList<>();
                }
                listaMotocicletas.add(vehiculoAActualizar);
                session.setAttribute("listaMotocicletas", listaMotocicletas);
            } else if (tipoNuevo == TipoVehiculo.VEHICULO_LIGERO) {
                if (vehiculosLigeros == null) {
                    vehiculosLigeros = new ArrayList<>();
                }
                vehiculosLigeros.add(vehiculoAActualizar);
                session.setAttribute("vehiculosLigeros", vehiculosLigeros);
            }

            // Redirigir a la página de éxito
            session.setAttribute("mensaje", "Vehículo actualizado correctamente.");
            response.sendRedirect("index.jsp");
        } else {
            // Enviar un mensaje de error si no se encuentra el vehículo
            session.setAttribute("error", "Vehículo no encontrado.");
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para editar información de un vehículo";
    }
}
