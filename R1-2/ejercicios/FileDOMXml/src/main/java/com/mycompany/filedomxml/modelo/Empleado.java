/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filedomxml.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 16 oct 2024
 */
public class Empleado {
    //ATRIBUTOS
    private long identificador;
    private String apellido;
    private int departamento;
    private double salario;
//------------------------------------------------------------------------------
    //CONSTRUCTORES
    public Empleado(){
        
    }

    public Empleado(String apellido, int departamento, double salario) {
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }

    public Empleado(long identificador, String apellido, int departamento, double salario) {
        this.identificador = identificador;
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }
    
//------------------------------------------------------------------------------
    //MÉTODOS
    
    //Geters y Setters
    public long getIdentificador() {
        return identificador;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDepartamento() {
        return departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    
    @Override
    public String toString() {
        return "Empleado{" + "identificador=" + identificador + ", apellido=" + apellido + ", departamento=" + departamento + ", salario=" + salario + '}';
    }
    
}
