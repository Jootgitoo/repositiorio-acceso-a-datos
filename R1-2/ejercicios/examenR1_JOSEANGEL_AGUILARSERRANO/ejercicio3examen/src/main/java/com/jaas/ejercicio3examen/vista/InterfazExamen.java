/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.jaas.ejercicio3examen.vista;

import com.jaas.ejercicio3examen.controlador.ControladorExamen;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 * @version 1.0
 * Created on 13 nov 2024
 */
public interface InterfazExamen {
    void arranca();
    void setControladorE(ControladorExamen controlador);

    public String leerProducto();

    public int leerCantidad();

    public String leerSupermercado();
    
    static final String MODIFICARLISTA = "Añade productos a la lista de la compra";
}
