<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="clases.Vehiculo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Vehículos</title>
    </head>
    <body>
        <h1>Lista de Vehículos Registrados</h1>

        <h2>Motocicletas</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Placa</th>
                    <th>Tipo</th>
                    <th>Híbrido</th>
                    <th>Plaza Asignada</th>
                    <th>Modificar</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Vehiculo> listaMotocicletas = (List<Vehiculo>) session.getAttribute("listaMotocicletas");
                    if (listaMotocicletas != null) {
                        for (Vehiculo moto : listaMotocicletas) {
                %>
                <form action="svEditar" method="POST">
                    <tr>
                        <td><input type="text" name="placa" value="<%= moto.getPlaca() %>" readonly></td>
                        <td>
                            <select name="tipo">
                                <option value="MOTOCICLETA" <%= moto.getTipo().toString().equals("MOTOCICLETA") ? "selected" : "" %>>Motocicleta</option>
                                <option value="VEHICULO_LIGERO" <%= moto.getTipo().toString().equals("VEHICULO_LIGERO") ? "selected" : "" %>>Vehículo Ligero</option>
                            </select>
                        </td>
                        <td>
                            <select name="hibrido">
                                <option value="true" <%= moto.isEsHibirdo() ? "selected" : "" %>>Sí</option>
                                <option value="false" <%= !moto.isEsHibirdo() ? "selected" : "" %>>No</option>
                            </select>
                        </td>
                        <td><input type="number" name="plaza" value="<%= moto.getPlazaAsignada() %>" required></td>
                        <td><button type="submit">Modificar</button></td>
                    </tr>
                </form>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

        <h2>Vehículos Ligeros</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Placa</th>
                    <th>Tipo</th>
                    <th>Híbrido</th>
                    <th>Plaza Asignada</th>
                    <th>Modificar</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Vehiculo> vehiculosLigeros = (List<Vehiculo>) session.getAttribute("vehiculosLigeros");
                    if (vehiculosLigeros != null) {
                        for (Vehiculo vehiculo : vehiculosLigeros) {
                %>
                <form action="svEditar" method="POST">
                    <tr>
                        <td><input type="text" name="placa" value="<%= vehiculo.getPlaca() %>" readonly></td>
                        <td>
                            <select name="tipo">
                                <option value="MOTOCICLETA" <%= vehiculo.getTipo().toString().equals("MOTOCICLETA") ? "selected" : "" %>>Motocicleta</option>
                                <option value="VEHICULO_LIGERO" <%= vehiculo.getTipo().toString().equals("VEHICULO_LIGERO") ? "selected" : "" %>>Vehículo Ligero</option>
                            </select>
                        </td>
                        <td>
                            <select name="hibrido">
                                <option value="true" <%= vehiculo.isEsHibirdo() ? "selected" : "" %>>Sí</option>
                                <option value="false" <%= !vehiculo.isEsHibirdo() ? "selected" : "" %>>No</option>
                            </select>
                        </td>
                        <td><input type="number" name="plaza" value="<%= vehiculo.getPlazaAsignada() %>" required></td>
                        <td><button type="submit">Modificar</button></td>
                    </tr>
                </form>
                <%
                        }
                    }
                %>
            </tbody>
            
         
        </table>
            <form action="index.jsp">
                   <input type="submit" value="Regresar" name="regresar" />
            </form>
    </body>
</html>
