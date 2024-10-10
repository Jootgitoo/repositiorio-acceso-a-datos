/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.mvc.Vista;

import com.mycompany.mvc.Controlador.ControlConversor;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 13 sept 2024
 */
public interface InterfazVista {

    void setControlador(ControlConversor c);
    
    void arranca();
    
    double getCantidad();
    
    void escribeCambio(String s);
    
    static final String AEUROS = "Pesetas a Euros";
    
    static final String APESETAS = "Euros a Pesetas";

    public int getComision();
}
