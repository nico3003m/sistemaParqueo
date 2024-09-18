<%-- 
    Document   : index
    Created on : 17/09/2024, 10:05:46 p. m.
    Author     : Nicolas Moreno
--%>

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

        <form action="svListaParqueo" action="POST">
            <p>Revisar vehiculos ingresados</p>
            <input type="submit" value="Registrar" name="lista" />
        </form>

        <p>Actualizar datos de los vehiculos </p>
        <p>Cerrar Operación</p>
    </body>
</html>
