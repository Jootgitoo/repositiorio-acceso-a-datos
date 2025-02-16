/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jaas.ejercicio2examen;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 * @version 1.0
 * Created on 13 nov 2024
 */
public class Reforma {
    private int IDReforma;
    private String descripcion;
    private String direccion;
    private double coste;

    public Reforma(int IDReforma, String descripcion, String direccion, double coste) {
        this.IDReforma = IDReforma;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.coste = coste;
    }

    public int getIDReforma() {
        return IDReforma;
    }

    public void setIDReforma(int IDReforma) {
        this.IDReforma = IDReforma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    @Override
    public String toString() {
        return "Reforma{" + "IDReforma=" + IDReforma + ", descripcion=" + descripcion + ", direccion=" + direccion + ", coste=" + coste + '}';
    }
    
    
    
    
}
