/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.ejerciciorepaso1.vista;

import com.mycompany.ejerciciorepaso1.controlador.ControladorProducto;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 22 oct 2024
 */
public interface InterfazVista {
    
    static final String LEER_PRODUCTO = "leemos todos los producto que tenga el fichero";
    static final String BUSCAR_PRODUCTO_ID = "buscamos un producto segun el id que pidas";
   
    
    void setControladorProducto (ControladorProducto cP);
    void operacionExitosa();

    String getRuta();
}
