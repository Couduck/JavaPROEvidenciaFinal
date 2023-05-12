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
public class UsuarioDBActions       //Operaciones CRUD necesarias dentro de la tabla de Usuarios
{
    Conexion connect = new Conexion();      //Objeto Conexión para establecer el enlace con la BD
    JdbcTemplate template = new JdbcTemplate(connect.Conectar());   //Template de Spring MVC para llevar a cabo operaciones CRUD en una base de tipo MySQL
    ModelAndView MAV = new ModelAndView();  //Vista que se regresará en caso de ser necesario
    
    public List<Usuario> UsuarioPorUserName(String username)    //Busca y regresa un usuario que coincida con el Username proporcionado
    {
        String instruccion = "select * from usuarios where nomUsuarioWeb = ?";
        List datos = convertirListasObjetos(this.template.queryForList(instruccion, username));
        return datos;
    }
    
    public ModelAndView AgregarNuevoUsuario(Usuario u)      //Agrega a la tabla de usuarios un nuevo registro
    {
        String instruccion = "insert into usuarios(nombreReal, apellidoPat, apellidoMat, nomUsuarioWeb, password, sexo, altura, edad)values(?,?,?,?,?,?,?,?)";
        this.template.update(instruccion, u.getNombreReal(), u.getApellidoPat(), u.getApellidoMat(), u.getNomUsuarioWeb(), u.getPassword(), u.getSexo(), u.getAltura(), u.getEdad());
        return new ModelAndView("redirect:/index.htm"); 
    }
    
    public List<Usuario> convertirListasObjetos(List<Map<String,Object>> lista)     //Transforma el resultado de this.template.queryForList (Un objeto de tipo List<Map<String,Object>>) a un objeto de tipo List<Usuario>
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
