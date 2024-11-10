/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.cambioeuropesetaconmvc.vista;

import com.mycompany.cambioeuropesetaconmvc.controlador.ControladorEuroPeseta;


/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 9 nov 2024
 */
public interface InterfazVista {
    
    static final String CAMBIAR_DE_EUROS_A_PESETAS = "Cambiar de euros a pesetas";
    static final String CAMBAIR_DE_PESETAS_A_EUROS = "Cambiar de pesetas a euros";
    
    void setControladorCambioEuroPeseta (ControladorEuroPeseta cep);
    double getEuros();
    double getPesetas();
    void arranca();
    void operacionExitosa();
    void limpiarCampos();
}