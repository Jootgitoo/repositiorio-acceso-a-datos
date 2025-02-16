/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosaccesoaleatorio.modelo;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 16 feb 2025
 */
public class Empleado {
    
    //ATRIBUTOS
    
    private long identificador;
    private String apellido;
    private int departamento;
    private double salario;
    
//----------------------------------------------------------
    //CONSTRUCTORES
    public Empleado(){
        
    }
    
    public Empleado(long identificador, String apellido, int departamento, double salario){
        
        this.identificador = identificador;
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
        
    }
//----------------------------------------------------------
    //MÉTODOS

    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    

}
