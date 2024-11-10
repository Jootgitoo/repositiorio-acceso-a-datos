/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cambioeuropesetaconmvc;

import com.mycompany.cambioeuropesetaconmvc.controlador.ControladorEuroPeseta;
import com.mycompany.cambioeuropesetaconmvc.modelo.CambioEuroPeseta;
import com.mycompany.cambioeuropesetaconmvc.vista.InterfazVista;
import com.mycompany.cambioeuropesetaconmvc.vista.VentanaTexto;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class CambioEuroPesetaConMVC {

    public static void main(String[] args) {
       
        InterfazVista vista = new VentanaTexto();
        CambioEuroPeseta modelo = new CambioEuroPeseta();
        ControladorEuroPeseta controlador = new ControladorEuroPeseta(vista, modelo);
        
        vista.arranca();
    }
}
