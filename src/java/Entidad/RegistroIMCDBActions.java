/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import Config.Conexion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author patoe
 */
public class RegistroIMCDBActions   //Operaciones CRUD necesarias dentro de la tabla de registros de IMC
{
    Conexion connect = new Conexion();      //Objeto Conexión para establecer el enlace con la BD
    JdbcTemplate template = new JdbcTemplate(connect.Conectar());   //Template de Spring MVC para llevar a cabo operaciones CRUD en una base de tipo MySQL
    ModelAndView MAV = new ModelAndView();  //Vista que se regresará en caso de ser necesario
    
    public List<RegistroIMC> ResgistrosPorUsername(String username)     //Obtiene todos los registros pertenecientes a un mismo Usuario
    {
        String instruccion = "SELECT * FROM compjavaeidenciafinal.registrosimc WHERE username = ? ORDER BY id DESC";
        List datos = this.template.queryForList(instruccion, username);
        return datos;
    }

    public void AgregarNuevoRegistro(RegistroIMC r)     //Agrega un nuevo registro en la tabla de Registros de IMC
    {
        String instruccion = "insert into registrosimc(username, masa, altura, imc, fecha)values(?,?,?,?,?)";
        this.template.update(instruccion, r.getUserName(), r.getMasa(), r.getAltura(), r.getImc(), r.getFecha());
    }
}
