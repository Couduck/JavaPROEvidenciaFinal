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
public class Usuario        //Clase para manipular los Usuarios
{
    int id;     //Id del usuario
    String nombreReal;  //Nombre real del usuario
    String apellidoPat; //Apellido paterno del usuario
    String apellidoMat; //Apellido materno
    String nomUsuarioWeb;   //Nombre con el que usuario se dará de alta en el sitio
    String password;    //Contraseña del nombre de usuario
    String sexo;        //Sexo biológico del usuario
    BigDecimal altura;  //Altura del usario
    int edad;   //Edad del usuario
    
    //Constructores
    public Usuario()
    {
        super();
    }

    public Usuario(int id, String nombreReal, String apellidoPat, String apellidoMat, String nomUsuarioWeb, String password, String sexo, BigDecimal altura, int edad) {
        this.id = id;
        this.nombreReal = nombreReal;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.nomUsuarioWeb = nomUsuarioWeb;
        this.password = password;
        this.sexo = sexo;
        this.altura = altura;
        this.edad = edad;
    }
    
    public Usuario(String nombreReal, String apellidoPat, String apellidoMat, String nomUsuarioWeb, String password, String sexo, BigDecimal altura, int edad) 
    {
        this.nombreReal = nombreReal;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.nomUsuarioWeb = nomUsuarioWeb;
        this.password = password;
        this.sexo = sexo;
        this.altura = altura;
        this.edad = edad;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public String getNomUsuarioWeb() {
        return nomUsuarioWeb;
    }

    public void setNomUsuarioWeb(String nomUsuarioWeb) {
        this.nomUsuarioWeb = nomUsuarioWeb;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    
}
