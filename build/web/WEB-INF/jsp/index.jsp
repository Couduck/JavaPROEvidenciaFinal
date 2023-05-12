<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<!-- JSP para ingresar a la sesión de un usuario guardao en el sistema -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <div>
            <h2>Inicio de Sesion</h2>
            <form action="IngresarSesion" method="post">
                Nombre de usuario:<br>
                    <input type="text" name="nomUsuarioWeb" value=""><br><br>
                Password:<br>
                    <input type="text" name="password" value=""><br><br>
                <input type="submit" value="Ingresar"><br><br>
            </form>
        </div>
        
        <a href="registrarse">Registrarse</a>
        
    </body>
</html>
