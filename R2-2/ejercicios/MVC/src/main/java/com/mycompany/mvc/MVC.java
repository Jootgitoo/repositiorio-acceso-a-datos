/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mvc;

import com.mycompany.mvc.Controlador.ControlConversor;
import com.mycompany.mvc.Modelo.ConversorEurosPesetas;
import com.mycompany.mvc.Vista.InterfazVista;
import com.mycompany.mvc.Vista.VentanaConversor;
import com.mycompany.mvc.Vista.VentanaConversorTexto;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class MVC {

    public static void main(String[] args) {
        InterfazVista vista = new VentanaConversor();
        
        ConversorEurosPesetas modelo = new ConversorEurosPesetas();
        
        ControlConversor control = new ControlConversor(vista, modelo);
    }
}
