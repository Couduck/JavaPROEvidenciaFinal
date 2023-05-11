<%-- 
    Document   : registroNuevoUsuario
    Created on : May 5, 2023, 10:14:42 AM
    Author     : patoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Registrarse</h2>
            <form method="post">
                Nombre completo:<br>
                    <input type="text" name="nombreReal" value=""><br><br>
                Apellido Paterno:<br>
                    <input type="text" name="apPat" value=""><br><br>
                Apellido Materno:<br>
                    <input type="text" name="apMat" value=""><br><br>
                Nombre de Usuario:<br>
                <input type="text" name="userName" value=""><br><br>
                Password:<br>
                    <input type="text" name="password" value=""><br><br>
                Sexo:<br>
                    <select name="sexo">
                        <option value="M" selected>Masculino</option>
                        <option value="F" selected>Femenino</option>
                    </select><br><br>
                Estatura:<br>
                    <input type="text" name="estatura" value=""><br><br>
                Edad:<br>
                    <input type="text" name="edad" value=""><br><br>
                <input type="submit" value="Registrarse"><br><br>
            </form>
    </body>
</html>
