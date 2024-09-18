<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="clases.Vehiculo" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Duration" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Sacar Vehículo</title>
</head>
<body>
    <h1 class="encabezado">Sacar Vehículo</h1>

    <div class="contenedor">
        
   
    <% 
        
        List<Vehiculo> listaMotocicletas = (List<Vehiculo>) session.getAttribute("listaMotocicletas");
        List<Vehiculo> vehiculosLigeros = (List<Vehiculo>) session.getAttribute("vehiculosLigeros");
        List<Vehiculo> todosLosVehiculos = new ArrayList<>();
        
        if (listaMotocicletas != null) {
            todosLosVehiculos.addAll(listaMotocicletas);
        }
        if (vehiculosLigeros != null) {
            todosLosVehiculos.addAll(vehiculosLigeros);
        }
    %>

    <form action="svSacarVehiculo" method="POST">
        <table border="1">
            <thead>
                <tr>
                    <th>Placa</th>
                    <th>Tipo</th>
                    <th>Fecha de Ingreso</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (todosLosVehiculos != null && !todosLosVehiculos.isEmpty()) {
                        for (Vehiculo vehiculo : todosLosVehiculos) {
                %>
                <tr>
                    <td><%= vehiculo.getPlaca() %></td>
                    <td><%= vehiculo.getTipo() %></td>
                    <td><%= vehiculo.getIngreso() %></td>
                    <td>
                        <button type="submit" name="sacar" value="<%= vehiculo.getPlaca() %>">Sacar</button>
                        <input type="hidden" name="placa" value="<%= vehiculo.getPlaca() %>">
                        <input type="hidden" name="tipo" value="<%= vehiculo.getTipo() %>">
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4">No hay vehículos en el parqueadero.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </form>
 </div>
            
    <br>
    <form action="index.jsp"><button >volver al inicio</button></form>
    
</body>
</html>
