<%-- 
    Document   : errorEntradaUsuario
    Created on : May 4, 2023, 11:49:07 PM
    Author     : patoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- JSP donde se muestran los mensajes de error cada vez que ocurre alguno al ingresar información -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error Entrada Usuario</h1><br>
        
        <h2>${requestScope.mensajeError}</h2>
    </body>
</html>
