<%@ page import="java.util.List" %>
<%@ page import="clases.Vehiculo" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>Resultado Cierre de Operación</title>
</head>
<body>
    <div class="contenedor">
    <h1 class="encabezado">Resultado del Cierre de Operación</h1>
    
    <p>Total de Ganancias: <%= session.getAttribute("gananciasTotales") %> pesos</p>
    
    <!-- Limpiar el mensaje de ganancias totales -->
    <%
        session.removeAttribute("gananciasTotales");
    %>

    <a href="index.jsp">Volver al Inicio</a>
    </div>
</body>
</html>
