/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.examenr1jorgeherreramartin.modelo;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 23 oct 2024
 */
public class Universidad {
    
    private int id;
    private String carrera;
    private String ciudad;
    private double notaCorte;

    public Universidad(int id, String carrera, String ciudad, double notaCorte) {
        this.id = id;
        this.carrera = carrera;
        this.ciudad = ciudad;
        this.notaCorte = notaCorte;
    }

    
    
    
    
    
    public int getId() {
        return id;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getCiudad() {
        return ciudad;
    }

    public double getNotaCorte() {
        return notaCorte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setNotaCorte(double notaCorte) {
        this.notaCorte = notaCorte;
    }
    
    

}
