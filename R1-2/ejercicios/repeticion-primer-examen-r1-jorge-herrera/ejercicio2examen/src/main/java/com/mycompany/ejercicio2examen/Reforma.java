/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejercicio2examen;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 13 nov 2024
 */
public class Reforma {
    
    private int id;
    private String descripcion;
    private String direccion;
    private double coste;
    
    public Reforma(){
        
    }
    
    public Reforma(int id, String descripcion, String direccion, double coste){
        this.id = id;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.coste = coste;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getCoste() {
        return coste;
    }

    
    
}
