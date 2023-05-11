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
public class UsuarioController 
{
    ModelAndView MAV = new ModelAndView();
    UsuarioDBActions EnlaceDB = new UsuarioDBActions();
    
    
    @GetMapping("/index")
    public ModelAndView metodoFantasma()
    {
        MAV.setViewName("index");
        return MAV;
    }
    
    @PostMapping("/IngresarSesion")
    public ModelAndView IngresarSistema(@RequestParam("nomUsuarioWeb") String userName, @RequestParam("password") String password, ModelMap modelo)
    {
        if(userName.isBlank() || password.isBlank())
        {
            modelo.addAttribute("mensajeError","Faltaron datos por ingresarse");
            MAV.setViewName("errorEntradaUsuario");
            return MAV; 
        }
        
        List <Usuario> recuperarUsuario = EnlaceDB.UsuarioPorUserName(userName);
        
        if(recuperarUsuario.isEmpty())
        {
            modelo.addAttribute("mensajeError","No se encontró ningun usuario con el nombre proporcionado");
            MAV.setViewName("errorEntradaUsuario");
            return MAV;
        }
        
        if(!recuperarUsuario.get(0).getPassword().equals(password))
        {
            modelo.addAttribute("mensajeError","Password incorrecto");
            MAV.setViewName("errorEntradaUsuario");
            return MAV;
        }
        
        modelo.addAttribute("nombreUsuario", userName);
        return new ModelAndView("redirect:/FormIMC.htm",modelo);
        
    }
    
    @GetMapping("/registrarse")
    public ModelAndView AccederRegistrarseView()
    {
        MAV.setViewName("registroNuevoUsuario");
        return MAV;
    }
    
    @PostMapping("/registrarse")
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
        
        if(nombre.isBlank() || apellidoPat.isBlank() || apellidoMat.isBlank() || userName.isBlank() || password.isBlank() || sexo.isBlank() || estaturaSTR.isBlank() || edadSTR.isBlank())
        {
            modelo.addAttribute("mensajeError","Faltaron datos por ingresarse");
            MAV.setViewName("errorEntradaUsuario");
            return MAV; 
        }
        
        List<Usuario> usuarioExiste = EnlaceDB.UsuarioPorUserName(userName);
        
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
                int edad = Integer.parseInt(edadSTR);
                double estaturaDOB = Double.parseDouble(estaturaSTR);
                
                if(edad < 15 || estaturaDOB < 1 || estaturaDOB > 2.5)
                {
                    if(edad < 15 && (estaturaDOB < 1 || estaturaDOB > 2.5))
                    {
                        modelo.addAttribute("mensajeError","La edad no puede ser menor a 15 años y solo se permiten alturas de entre 1 - 2.5 metros"); 
                        MAV.setViewName("errorEntradaUsuario");
                        return MAV;
                    }
                    
                    if(edad < 15)
                    {
                        modelo.addAttribute("mensajeError","La edad no puede ser menor a 15 años");
                        MAV.setViewName("errorEntradaUsuario");
                        return MAV;
                    }
                    
                    if(estaturaDOB < 1 || estaturaDOB > 2.5)
                    {
                        modelo.addAttribute("mensajeError","Solo se permiten alturas de 1 - 2.5 metros");
                        MAV.setViewName("errorEntradaUsuario");
                        return MAV;
                    }
                }
                
                estaturaDOB = Math.round(estaturaDOB*100.0)/100.0;
                BigDecimal estaturaFinal = BigDecimal.valueOf(estaturaDOB);
                
                Usuario nuevo = new Usuario(nombre, apellidoPat, apellidoMat, userName, password, sexo, estaturaFinal, edad);
                
                return EnlaceDB.AgregarNuevoUsuario(nuevo);
            }
            
            catch(NumberFormatException a)
            {
                modelo.addAttribute("mensajeError","Alguno de los valores numéricos no pudo transformarse correctamente");
                MAV.setViewName("errorEntradaUsuario");
                return MAV;
            }
        }
    }
}
