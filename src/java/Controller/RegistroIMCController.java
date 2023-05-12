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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author patoe
 */

@RestController
public class RegistroIMCController  //Controlador encargado de gestionar todas las funciones relacionadas a los registros de IMC
{
    Conexion connect = new Conexion();
    JdbcTemplate template = new JdbcTemplate(connect.Conectar());
    ModelAndView MAV = new ModelAndView();
    UsuarioDBActions EnlaceDBUs = new UsuarioDBActions();
    RegistroIMCDBActions EnlaceDBReg = new RegistroIMCDBActions();
    
    @GetMapping("/FormIMC")     //Carga vista para cálculo de IMC
    public ModelAndView mostrarFormIMC(@RequestParam("nombreUsuario") String nombre, Model modelo)
    {
        modelo.addAttribute("nombreUsuario", nombre);
        MAV.setViewName("FormIMC");
        return MAV;
    }
    
    @PostMapping("/FormIMC")    //Realiza el cáluclo de IMC al recibir el peso ingresado por el usuario
    public ModelAndView calcularIMC(@RequestParam("nombreUsuario") String nombre, @RequestParam("masaCorporal") String pesoSTR, ModelMap modelo)
    {
        try
        {
            if(pesoSTR.isBlank())   //Verifica si el campo está vacío, se envía a pantalla de error si lo es
            {
                modelo.addAttribute("mensajeError","Faltaron datos por ingresarse");
                MAV.setViewName("errorEntradaUsuario");
                return MAV; 
            }
            
            //Recupera usuario para obtener altura del usuario para el cálculo
            List<Usuario> recuperarUsuario = EnlaceDBUs.UsuarioPorUserName(nombre);
            Usuario u = recuperarUsuario.get(0);
            
            //Transforma los valores a variables de tipo double para ser utilizadas
            double estaturaDOB = Double.parseDouble(u.getAltura().toString());
            double pesoDOB = Double.parseDouble(pesoSTR);
            
            //Si el peso ingresado es menor a 0 se envía una pantalla de error
            if(pesoDOB <= 0)
            {
                modelo.addAttribute("mensajeError","El peso no puede ser igual o menor a 0");
                MAV.setViewName("errorEntradaUsuario");
                return MAV; 
            }
            
            //Se realiza el cálculo del IMC, redondeandose a 2 decimales y guardandose como BigDecimal para almacenamiento en DB junto con peso
            double imcDOB = pesoDOB / Math.pow(estaturaDOB, 2);
            imcDOB = Math.round(imcDOB*100.0)/100.0;
            BigDecimal imc = BigDecimal.valueOf(imcDOB);
            BigDecimal peso = new BigDecimal(pesoSTR);
            
            //Se obtiene la fecha actual y se guarda como String
            LocalDateTime hoy = LocalDateTime.now();
            String fecha = hoy.getDayOfMonth() + "-" + hoy.getMonthValue() + "-" + hoy.getYear();
            
            //Se crea un objeto de tipo RegistroIMC y se guarda en DB
            RegistroIMC registroNuevo = new RegistroIMC(nombre, peso, u.getAltura(), imc, fecha);
            EnlaceDBReg.AgregarNuevoRegistro(registroNuevo);
            
            //Se guardan las variables como atrbutos del modelo para usarse en otra vista
            modelo.addAttribute("IMCusuario", imc);
            modelo.addAttribute("nombreUsuario", nombre);
            
            //Se cambia a página de Resultados
            return new ModelAndView("redirect:/resultadoRegistros.htm", modelo);
        }
        
        catch(NumberFormatException b)  //Si lo ingresado no es numerico, se manda a error
        {
            modelo.addAttribute("mensajeError","No se ingreso un valor numerico");
            MAV.setViewName("errorEntradaUsuario");
            return MAV; 
        }
    }
    
    @GetMapping("/resultadoRegistros")  //Carga la vista de resultadoRegistros
    public ModelAndView mostrarResultadosRegistros(@RequestParam("nombreUsuario") String nombre, @RequestParam("IMCusuario") BigDecimal imc, Model modelo)
    {
        modelo.addAttribute("nombreUsuario", nombre);
        modelo.addAttribute("IMCusuario", imc);
        
        //Se obtiene de BD los registros del mismo usuario para desplegarse en pantalla
        List<RegistroIMC> registrosRecuperados = EnlaceDBReg.ResgistrosPorUsername(nombre);
        
        MAV.addObject("registrosPropios", registrosRecuperados);
        
        MAV.setViewName("resultadoRegistros");
        return MAV;
    }
}
