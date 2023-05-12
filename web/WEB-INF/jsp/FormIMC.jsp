<%-- 
    Document   : MenuCalculo
    Created on : May 5, 2023, 10:09:03 AM
    Author     : patoe
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- JSP donde se encuentra el formulario para ingresar el peso para el calculo del IMC -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Principal</title>
    </head>

    <body>
        <h1>Bienvenido: ${nombreUsuario}</h1>
        
        <h1>Calcular IMC actual</h1><br><br>
        <div>
            <form method="post">  
                <input type="text" name="masaCorporal" value=""><br><br>
                <input type="submit" value="calcular">
            </form>
        </div>
    </table>
    </body>
</html>
