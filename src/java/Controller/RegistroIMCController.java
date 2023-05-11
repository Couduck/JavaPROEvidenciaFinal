/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Config.Conexion;
import Entidad.RegistroIMC;
import Entidad.RegistroIMCDBActions;
import Entidad.Usuario;
import Entidad.UsuarioDBActions;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author patoe
 */

@RestController
public class RegistroIMCController 
{
    Conexion connect = new Conexion();
    JdbcTemplate template = new JdbcTemplate(connect.Conectar());
    ModelAndView MAV = new ModelAndView();
    UsuarioDBActions EnlaceDBUs = new UsuarioDBActions();
    RegistroIMCDBActions EnlaceDBReg = new RegistroIMCDBActions();
    
    @GetMapping("/FormIMC")
    public ModelAndView mostrarFormIMC(@RequestParam("nombreUsuario") String nombre, Model modelo)
    {
        modelo.addAttribute("nombreUsuario", nombre);
        MAV.setViewName("FormIMC");
        return MAV;
    }
    
    @PostMapping("/FormIMC")
    public ModelAndView calcularIMC(@RequestParam("nombreUsuario") String nombre, @RequestParam("masaCorporal") String pesoSTR, ModelMap modelo)
    {
        try
        {
            if(pesoSTR.isBlank())
            {
                modelo.addAttribute("mensajeError","Faltaron datos por ingresarse");
                MAV.setViewName("errorEntradaUsuario");
                return MAV; 
            }
            
            List<Usuario> recuperarUsuario = EnlaceDBUs.UsuarioPorUserName(nombre);
            Usuario u = recuperarUsuario.get(0);
            
            double estaturaDOB = Double.parseDouble(u.getAltura().toString());
            double pesoDOB = Double.parseDouble(pesoSTR);
            
            if(pesoDOB <= 0)
            {
                modelo.addAttribute("mensajeError","El peso no puede ser igual o menor a 0");
                MAV.setViewName("errorEntradaUsuario");
                return MAV; 
            }
            
            double imcDOB = pesoDOB / Math.pow(estaturaDOB, 2);
            imcDOB = Math.round(imcDOB*100.0)/100.0;
            BigDecimal imc = BigDecimal.valueOf(imcDOB);
            BigDecimal peso = new BigDecimal(pesoSTR);
            
            LocalDateTime hoy = LocalDateTime.now();
            String fecha = hoy.getDayOfMonth() + "-" + hoy.getMonthValue() + "-" + hoy.getYear();
            
            RegistroIMC registroNuevo = new RegistroIMC(nombre, peso, u.getAltura(), imc, fecha);
            EnlaceDBReg.AgregarNuevoRegistro(registroNuevo);
            
            modelo.addAttribute("IMCusuario", imc);
            modelo.addAttribute("nombreUsuario", nombre);
            
            return new ModelAndView("redirect:/resultadoRegistros.htm", modelo);
        }
        
        catch(NumberFormatException b)
        {
            modelo.addAttribute("mensajeError","No se ingreso un valor numerico");
            MAV.setViewName("errorEntradaUsuario");
            return MAV; 
        }
    }
    
    @GetMapping("/resultadoRegistros")
    public ModelAndView mostrarResultadosRegistros(@RequestParam("nombreUsuario") String nombre, @RequestParam("IMCusuario") BigDecimal imc, Model modelo)
    {
        modelo.addAttribute("nombreUsuario", nombre);
        modelo.addAttribute("IMCusuario", imc);
        
        List<RegistroIMC> registrosRecuperados = EnlaceDBReg.ResgistrosPorUsername(nombre);
        
        MAV.addObject("registrosPropios", registrosRecuperados);
        
        MAV.setViewName("resultadoRegistros");
        return MAV;
    }
}
