<%-- 
    Document   : index
    Created on : 17/09/2024, 10:05:46 p. m.
    Author     : Nicolas Moreno
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="clases.Vehiculo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Parqueo automotor</h1>

        <h2>Bienenido Juan Rodriguez </h2>
        <form action="svRegistroVehiculo" method="POST">
            <p>Ingresar vehiculo</p>
            <p><label>Placa</label> <input name="placa"></p>
            <p><label>Tipo de vehículo</label><br><select name="tipo" > 
                    <option value="MOTOCICLETA">Motocicleta</option>
                    <option value="VEHICULO_LIGERO">Automovil</option>   </select></p>
            <p><label>Es hibrido</label><br><select name="hibrido" > 
                    <option value="true">Si</option>
                    <option value="false">No</option>   </select></p>
            <p><label>Plaza Asignada</label> <input name="plaza" type="number"></p>
            <h3>
                <%= request.getAttribute("infomacion") != null ? request.getAttribute("infomacion") : ""%>
            </h3>

            <input type="submit" value="Registrar" name="registro" />
        </form>
        <h3>Que accion quiere hacer ?</h3>

        <div class="contenedor">
            <h1>Mostrar lista de reservas</h1>

            <h2>Reservas disponibles para motos</h2>
            <%
                List<Vehiculo> listaMotocilclista = (List<Vehiculo>) session.getAttribute("listaMotocicletas");
                if (listaMotocilclista != null && !listaMotocilclista.isEmpty()) {
                    for (Vehiculo reservaMotos : listaMotocilclista) {
            %>
            <form action="svEliminar" method="POST">
                <div class="contenedorDelete">
                    <p>Placa: <%= reservaMotos.getPlaca()%></p>
                    <p>Tipo: <%= reservaMotos.getTipo()%></p>
                    <p>Fecha ingreso: <%= reservaMotos.getIngreso()%></p>
                </div>
                <div>
                    <p><button type="submit" class="buttonDelete">Eliminar</button></p>
                    <input type="hidden" name="placa" value="<%= reservaMotos.getPlaca()%>">
                    <input type="hidden" name="tipo" value="<%= reservaMotos.getTipo()%>">
                    <hr>
                </div>
            </form>
            <%
                }
            } else {
            %>
            <h3>No hay reservas disponibles.</h3>
            <%
                }
            %>

            <h2>Reservas disponibles para vehículos ligeros</h2>
            <%
                List<Vehiculo> vehiculosLigeros = (List<Vehiculo>) session.getAttribute("vehiculosLigeros");
                if (vehiculosLigeros != null && !vehiculosLigeros.isEmpty()) {
                    for (Vehiculo reservaVehiculosLigeros : vehiculosLigeros) {
            %>
            <form action="svEliminar" method="POST">
                <div class="contenedorDelete">
                    <p>Placa: <%= reservaVehiculosLigeros.getPlaca()%></p>
                    <p>Tipo: <%= reservaVehiculosLigeros.getTipo()%></p>
                    <p>Fecha ingreso: <%= reservaVehiculosLigeros.getIngreso()%></p>
                </div>
                <div>
                    <p><button type="submit" class="buttonDelete">Eliminar</button></p>
                    <input type="hidden" name="placa" value="<%= reservaVehiculosLigeros.getPlaca()%>">
                    <input type="hidden" name="tipo" value="<%= reservaVehiculosLigeros.getTipo()%>">
                    <hr>
                </div>
            </form>
            <%
                }
            } else {
            %>
            <h3>No hay reservas disponibles.</h3>
            <%
                }
            %>

            <!-- Formulario para mostrar la lista de reservas -->
            <form action="svListaParqueo" method="POST">
                <p><button type="submit" name="action" value="showList">Mostrar Lista</button></p>
            </form>
        </div> 
        <div>
            <h1>Actualizar datos de los vehiculos </h1>
            <form action="editarReserva.jsp" method="POST">

                
                <input type="submit" value="Actualizar" name="actualizar" />


            </form>
        </div>
            <div> 
                
            
            </div>
        <p>Cerrar Operación</p>
    </body>
</html>
