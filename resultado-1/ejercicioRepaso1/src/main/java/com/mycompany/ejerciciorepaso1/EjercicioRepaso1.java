/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciorepaso1;

import com.mycompany.ejerciciorepaso1.controlador.ControladorProducto;
import com.mycompany.ejerciciorepaso1.modelo.LecturaProductos;
import com.mycompany.ejerciciorepaso1.vista.InterfazVista;
import com.mycompany.ejerciciorepaso1.vista.VentanaTexto;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class EjercicioRepaso1 {

    public static void main(String[] args) {
        
       InterfazVista vista = new VentanaTexto();
       
       LecturaProductos modeloLectura = new LecturaProductos("rutaInventada");
       
       ControladorProducto controladorProducto = new ControladorProducto(vista, modeloLectura);
        
    }
}
