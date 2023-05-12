/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entidad.RegistroIMC;
import Entidad.RegistroIMCDBActions;
import Entidad.Usuario;
import Entidad.UsuarioDBActions;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author patoe
 */

@RestController
public class UsuarioController      //Controlador encargado de gestionar todas las funciones relacionadas a los Usuarios del sistema
{
    ModelAndView MAV = new ModelAndView();
    UsuarioDBActions EnlaceDB = new UsuarioDBActions();
    
    
    @GetMapping("/index")       //Carga la vista del Index
    public ModelAndView cargarIndex()
    {
        MAV.setViewName("index");
        return MAV;
    }
    
    @PostMapping("/IngresarSesion")     //Se ingresa al sistema con el Username y Password proporcionados por el usuario
    public ModelAndView IngresarSistema(@RequestParam("nomUsuarioWeb") String userName, @RequestParam("password") String password, ModelMap modelo)
    {
        if(userName.isBlank() || password.isBlank())    //Se valida que todos los campos se encuentren llenos
        {
            modelo.addAttribute("mensajeError","Faltaron datos por ingresarse");
            MAV.setViewName("errorEntradaUsuario");
            return MAV; 
        }
        
        List <Usuario> recuperarUsuario = EnlaceDB.UsuarioPorUserName(userName);    //Se obtiene el usuario de la BD
        
        if(recuperarUsuario.isEmpty())      //Si el usuario ingresado no existe, se manda error
        {
            modelo.addAttribute("mensajeError","No se encontró ningun usuario con el nombre proporcionado");
            MAV.setViewName("errorEntradaUsuario");
            return MAV;
        }
        
        //Se comprueba qu ela contraseña ingresada haya sido la correcta, de lo contrario manda error
        if(!recuperarUsuario.get(0).getPassword().equals(password))
        {
            modelo.addAttribute("mensajeError","Password incorrecto");
            MAV.setViewName("errorEntradaUsuario");
            return MAV;
        }
        
        //Se añade el nombre como atributo de la vista y se redirecciona a FormIMC
        modelo.addAttribute("nombreUsuario", userName);
        return new ModelAndView("redirect:/FormIMC.htm",modelo);
        
    }
    
    @GetMapping("/registrarse")     //Carga la vista para registro de nuevo usuario
    public ModelAndView AccederRegistrarseView()
    {
        MAV.setViewName("registroNuevoUsuario");
        return MAV;
    }
    
    @PostMapping("/registrarse")    //Procesa todo lo necesario para darse de alta en el sistema
    public ModelAndView DarseAlta
            (@RequestParam("nombreReal") String nombre, 
            @RequestParam("apPat") String apellidoPat, 
            @RequestParam("apMat") String apellidoMat, 
            @RequestParam("userName") String userName, 
            @RequestParam("password") String password, 
            @RequestParam("sexo") String sexo, 
            @RequestParam("estatura") String estaturaSTR, 
            @RequestParam("edad") String edadSTR,
            Model modelo)
    {
        /** Pasos Validacion para darse de alta
         * 1° Comprobar que no falte ningun campo por llenar [X]
         * 2° Comprobar que no sea un usuario repetido [X]
         * 3° Comprobar cuestiones especificas por campo:
         *      
         *      3.1° Estatura permitida = mayor o igual a 1 y menor o igual a 2.5 metros [X]
         *      3.2° Edad permitida = mayor o igual que 15 años [X]
         */
        
        //Se comprueba que no haya campos sin valor, si los hay manda error
        if(nombre.isBlank() || apellidoPat.isBlank() || apellidoMat.isBlank() || userName.isBlank() || password.isBlank() || sexo.isBlank() || estaturaSTR.isBlank() || edadSTR.isBlank())
        {
            modelo.addAttribute("mensajeError","Faltaron datos por ingresarse");
            MAV.setViewName("errorEntradaUsuario");
            return MAV; 
        }
        
        //Recupera (si es que hay) un usuario con el mismo Username ingresado para comprobar que no se trate de ingresar uno ya repetido
        List<Usuario> usuarioExiste = EnlaceDB.UsuarioPorUserName(userName);
        
        //En caso de que ya exista un usuario con el nombre solicitado, se manda error
        if(!usuarioExiste.isEmpty())
        {
            modelo.addAttribute("mensajeError","Usuario ya existe en la Base de Datos");
            MAV.setViewName("errorEntradaUsuario");
            return MAV;
        }
        
        else
        {
            try
            {
                //Se parsean los datos al tipo que deben tener
                int edad = Integer.parseInt(edadSTR);
                double estaturaDOB = Double.parseDouble(estaturaSTR);
                
                //La edad no puede ser menor a 15 y la estatura debe estar entre 1 y 2.5 metros, se mandará a error en caso de que alguna de las 2 no cumpla con las condiciones
                if(edad < 15 || estaturaDOB < 1 || estaturaDOB > 2.5)
                {
                    //Tanto la edad como la estatura no son válidas
                    if(edad < 15 && (estaturaDOB < 1 || estaturaDOB > 2.5))
                    {
                        modelo.addAttribute("mensajeError","La edad no puede ser menor a 15 años y solo se permiten alturas de entre 1 - 2.5 metros"); 
                        MAV.setViewName("errorEntradaUsuario");
                        return MAV;
                    }
                    
                    //Solo la edad no es válida
                    if(edad < 15)
                    {
                        modelo.addAttribute("mensajeError","La edad no puede ser menor a 15 años");
                        MAV.setViewName("errorEntradaUsuario");
                        return MAV;
                    }
                    
                    //Solo la estatura no es válida
                    if(estaturaDOB < 1 || estaturaDOB > 2.5)
                    {
                        modelo.addAttribute("mensajeError","Solo se permiten alturas de 1 - 2.5 metros");
                        MAV.setViewName("errorEntradaUsuario");
                        return MAV;
                    }
                }
                
                //Se guarda la estatura en la vriable que debe tener después de redondearse a 2 decimales
                estaturaDOB = Math.round(estaturaDOB*100.0)/100.0;
                BigDecimal estaturaFinal = BigDecimal.valueOf(estaturaDOB);
                
                //El nuevo usuario es generado y se guarda en la base de datos
                Usuario nuevo = new Usuario(nombre, apellidoPat, apellidoMat, userName, password, sexo, estaturaFinal, edad);
                
                return EnlaceDB.AgregarNuevoUsuario(nuevo);
            }
            
            //En caso de que alguno de los valores que deben ser numéricos no lo es, se manda a error
            catch(NumberFormatException a)
            {
                modelo.addAttribute("mensajeError","Alguno de los valores numéricos no pudo transformarse correctamente");
                MAV.setViewName("errorEntradaUsuario");
                return MAV;
            }
        }
    }
}
