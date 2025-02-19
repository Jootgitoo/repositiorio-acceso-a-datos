/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio3examen;

/**
 *
 * @author b15-18m
 */
public class Lanzamientos {
    
    private int id;
    private String fecha;
    private String lugar_lanzamiento;
    private int horas_vuelos_estimadas;
    
    public Lanzamientos(){
        
    }
    
    public Lanzamientos(int id, String fecha, String lugarLanzamiento, int horas_vuelos_estimadas){
        
        this.id = id;
        this.fecha = fecha;
        this.lugar_lanzamiento = lugarLanzamiento;
        this.horas_vuelos_estimadas = horas_vuelos_estimadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar_lanzamiento() {
        return lugar_lanzamiento;
    }

    public void setLugar_lanzamiento(String lugar_lanzamiento) {
        this.lugar_lanzamiento = lugar_lanzamiento;
    }

    public int getHoras_vuelos_estimadas() {
        return horas_vuelos_estimadas;
    }

    public void setHoras_vuelos_estimadas(int horas_vuelos_estimadas) {
        this.horas_vuelos_estimadas = horas_vuelos_estimadas;
    }
    
}
