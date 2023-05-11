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
public class RegistroIMCDBActions 
{
    Conexion connect = new Conexion();
    JdbcTemplate template = new JdbcTemplate(connect.Conectar());
    ModelAndView MAV = new ModelAndView();
    
    public List<RegistroIMC> ResgistrosPorUsername(String username)
    {
        String instruccion = "SELECT * FROM compjavaeidenciafinal.registrosimc WHERE username = ? ORDER BY id DESC";
        List datos = this.template.queryForList(instruccion, username);
        return datos;
    }

    public void AgregarNuevoRegistro(RegistroIMC r)
    {
        String instruccion = "insert into registrosimc(username, masa, altura, imc, fecha)values(?,?,?,?,?)";
        this.template.update(instruccion, r.getUserName(), r.getMasa(), r.getAltura(), r.getImc(), r.getFecha());
    }
}
