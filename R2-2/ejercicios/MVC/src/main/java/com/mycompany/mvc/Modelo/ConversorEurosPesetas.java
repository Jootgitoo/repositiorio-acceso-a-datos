/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.mvc.Modelo;

/**
 * Clase para utilizar el conversor de euros a pesetas
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 13 sept 2024
 */
public class ConversorEurosPesetas extends Conversor{

    public ConversorEurosPesetas(){
        super(166.386);
    }
    
    public double eurosApesetas(double cantidad, int comision){
        return eurosAmoneda(cantidad, comision);
    }
    
    public double pesetasAeuros(double cantidad, int comision){
        return monedaAeuro(cantidad, comision);
    }
}
