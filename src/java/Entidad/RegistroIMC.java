/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.math.BigDecimal;

/**
 *
 * @author patoe
 */
public class RegistroIMC    //Clase para manipular los registros de IMC
{
    int id;     //id del registro
    String userName;    //Usuario ala uqe pertenece
    BigDecimal masa;    //Masa registrada dentro del registro
    BigDecimal altura;  //Altura del usuario
    BigDecimal imc;     //IMC calculado
    String fecha;       //Fecha del registrp

    public RegistroIMC()    //Constructores
    {
        super();
    }
    
    public RegistroIMC(String userName, BigDecimal masa, BigDecimal altura, BigDecimal imc, String fecha) {
        this.userName = userName;
        this.masa = masa;
        this.altura = altura;
        this.imc = imc;
        this.fecha = fecha;
    }

    public RegistroIMC(int id, String userName, BigDecimal masa, BigDecimal altura, BigDecimal imc, String fecha) {
        this.id = id;
        this.userName = userName;
        this.masa = masa;
        this.altura = altura;
        this.imc = imc;
        this.fecha = fecha;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getMasa() {
        return masa;
    }

    public void setMasa(BigDecimal masa) {
        this.masa = masa;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public BigDecimal getImc() {
        return imc;
    }

    public void setImc(BigDecimal imc) {
        this.imc = imc;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
