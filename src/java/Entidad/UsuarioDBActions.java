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
public class UsuarioDBActions 
{
    Conexion connect = new Conexion();
    JdbcTemplate template = new JdbcTemplate(connect.Conectar());
    ModelAndView MAV = new ModelAndView();
    
    public List<Usuario> UsuarioPorUserName(String username)
    {
        String instruccion = "select * from usuarios where nomUsuarioWeb = ?";
        List datos = convertirListasObjetos(this.template.queryForList(instruccion, username));
        return datos;
    }
    
    public ModelAndView AgregarNuevoUsuario(Usuario u)
    {
        String instruccion = "insert into usuarios(nombreReal, apellidoPat, apellidoMat, nomUsuarioWeb, password, sexo, altura, edad)values(?,?,?,?,?,?,?,?)";
        this.template.update(instruccion, u.getNombreReal(), u.getApellidoPat(), u.getApellidoMat(), u.getNomUsuarioWeb(), u.getPassword(), u.getSexo(), u.getAltura(), u.getEdad());
        return new ModelAndView("redirect:/index.htm"); 
    }
    
    public List<Usuario> convertirListasObjetos(List<Map<String,Object>> lista)
    {
        List<Usuario> devolver = new ArrayList<Usuario>();
        
        for(Map <String,Object> objeto: lista)
        {
            Usuario user = new Usuario((String) objeto.get("nombreReal"),(String) objeto.get("apellidoPat"), (String) objeto.get("apellidoMat"), (String) objeto.get("nomUsuarioWeb"), (String) objeto.get("password"), (String) objeto.get("sexo"),(BigDecimal) objeto.get("altura"),(Integer) objeto.get("edad"));
            devolver.add(user);
        }
        
        return devolver;
    }
}
