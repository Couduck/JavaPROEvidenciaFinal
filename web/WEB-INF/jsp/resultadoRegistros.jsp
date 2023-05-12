<%-- 
    Document   : resultadoRegistros
    Created on : May 9, 2023, 2:54:49 PM
    Author     : patoe
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- JSP para imprimir el IMC actual del usuario juntos con los resgistros que haya tenido con anterioridad -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>

    <body>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <h2> Tu IMC actual es de: </h2>
        <h1>${IMCusuario}</h1><br><br><br>
        
        <h3>Tu trayectoria</h3>
        <table class="table table-light">
        <thead class="thead-light">
            <tr>
                <th>Masa</th>
                <th>Altura</th>
                <th>IMC</th>
                <th>Fecha</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="registro" items="${registrosPropios}">
            <tr>
                <td>${registro.masa}</td>
                <td>${registro.altura}</td>
                <td>${registro.imc}</td>
                <td>${registro.fecha}</td>
            </tr>
            </c:forEach>
        </tbody>
    </body>
</html>
