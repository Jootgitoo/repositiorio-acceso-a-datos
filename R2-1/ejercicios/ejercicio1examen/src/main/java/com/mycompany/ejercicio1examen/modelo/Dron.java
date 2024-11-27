/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejercicio1examen.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 27 nov 2024
 */
public class Dron {
    //ATRIBUTOS
    private String nSerie;
    private String marca;
    private String modelo;
    private double peso; //Para comprobarlo hay que poner 0, algo
    private int potencia;
    private int eCinetica;
    private String aseguradora;
    private double horas;
    private double autonomia;
    private Date adquisicion;
    private String dni;
   
//--------------------------------------------------------------------------------------------
    
    public Dron(String nSerie, String marca, String modelo, double peso, int potencia, int eCinetica, String aseguradora, double horas, double autonomia, Date adquisicion, String dni) {
        this.nSerie = nSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.potencia = potencia;
        this.eCinetica = eCinetica;
        this.aseguradora = aseguradora;
        this.horas = horas;
        this.autonomia = autonomia;
        this.adquisicion = adquisicion;
        this.dni = dni;
    }
   public void Dron(){
       
   } 

    
//-----------------------------------------------------------------------------------------------------------    
    //MÉTODOS EXTRA

    public String getnSerie() {
        return nSerie;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPeso() {
        return peso;
    }

    public int getPotencia() {
        return potencia;
    }

    public int geteCinetica() {
        return eCinetica;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public double getHoras() {
        return horas;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public Date getAdquisicion() {
        return adquisicion;
    }

    public String getDni() {
        return dni;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void seteCinetica(int eCinetica) {
        this.eCinetica = eCinetica;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    public void setAdquisicion(Date adquisicion) {
        this.adquisicion = adquisicion;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
}
