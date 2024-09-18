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
        <link rel="stylesheet" href="style.css">
        <title>Parking</title>
    </head>
    <body>
        <div class="encabezado">
            <h1>Parqueo automotor</h1>
            <h2>Bienenido Juan Rodriguez </h2>
        </div>
        <div class="contenedor">
            <form action="svRegistroVehiculo" method="POST">
                <h1><b>Ingresar vehículo</b></h1>
                <p><label>Placa</label><br> <input name="placa" minlength="6" required></p>
                <p><label>Tipo de vehículo</label><br>
                    <select name="tipo" required>
                        <option value="MOTOCICLETA">Motocicleta</option>
                        <option value="VEHICULO_LIGERO">Automóvil</option>
                    </select>
                </p>
                <p><label>Es híbrido</label><br>
                    <select name="hibrido" required>
                        <option value="true">Sí</option>
                        <option value="false">No</option>
                    </select>
                </p>
                <p><label>Plaza Asignada</label><br>
                    <select name="plaza" required>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>

                    </select>
                </p>
                <h3>
                    <%= request.getAttribute("infomacion") != null ? request.getAttribute("infomacion") : ""%>
                </h3>
                <input type="submit" value="Registrar" name="registro" class="registrar" />
            </form>
        </div>


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
        <div class="contenedor">
            <h1>Actualizar datos de los vehiculos </h1>
            <form action="editarReserva.jsp" method="POST">


                <input type="submit" value="Actualizar" name="actualizar"  class="registrar"/>


            </form>
        </div>
        <div class="contenedor"> 
            <h1>Sacar vehiculo del parquedero</h1>
            <!-- Añade esto en index.jsp -->
            <form action="sacarVehiculo.jsp" method="GET">
                <button type="submit" class="verde">Sacar Vehículo</button>
            </form>

        </div>
        <div class="contenedor">
            <h1>Cerrar Operación</h1>
            <!-- Añade esto en index.jsp -->
            <form action="svCerrarOperacion" method="POST">
                <button type="submit">Cerrar Parqueadero</button>
            </form>
        </div>

    </body>
</html>
