/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.filestreamscaracteres;

import com.mycompany.filestreamscaracteres.controlador.ControladorLectura;
import com.mycompany.filestreamscaracteres.modelo.Escritura;
import com.mycompany.filestreamscaracteres.modelo.Fichero;
import com.mycompany.filestreamscaracteres.modelo.Lectura;
import com.mycompany.filestreamscaracteres.vista.InterfazVista;
import com.mycompany.filestreamscaracteres.vista.VistaTexto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class FileStreamsCaracteres {

    public static void main(String[] args) {
        
        InterfazVista vista = new VistaTexto();
        Lectura modeloL = new Lectura ("./files/archivo.txt");
        ControladorLectura controladorLectura = new ControladorLectura(vista, modeloL);
    
        vista.arranca();
    }
}
