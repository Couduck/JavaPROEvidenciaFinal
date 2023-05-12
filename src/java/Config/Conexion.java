/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author patoe
 */
public class Conexion
{
    public DriverManagerDataSource Conectar()   //Conexión que hace el programa con la base de datos para obtener los datos cada vez que lo requiere
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/compjavaeidenciafinal");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}
