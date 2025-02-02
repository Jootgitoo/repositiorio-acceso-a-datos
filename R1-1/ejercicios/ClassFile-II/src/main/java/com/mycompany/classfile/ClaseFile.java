/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.classfile;

import com.mycompany.classfile.Controlador.ControladorArchivo;
import com.mycompany.classfile.Controlador.ControladorCarpeta;
import com.mycompany.classfile.Modelo.Archivo;
import com.mycompany.classfile.Modelo.Carpeta;
import com.mycompany.classfile.Vista.InterfazVista;
import com.mycompany.classfile.Vista.VentanaGrafica;


//Lo que el usuario elige se le pasa al controlador, el controlador te lleva al modelo que estecoge los datos, y la vista realiza los métodos
/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * Created 
 */
public class ClaseFile {
  public static void main(String[] args) {
    InterfazVista vista = new VentanaGrafica();
    Archivo modeloArchivo = new Archivo ();
    Carpeta modeloCarpeta = new Carpeta ();
    ControladorArchivo controladorArchivo = new ControladorArchivo(vista, modeloArchivo);
    ControladorCarpeta controladorCarpeta = new ControladorCarpeta(vista, modeloCarpeta);
    
    vista.arranca();
  }
}




// .\archivo.txt 
// .\prueba\archivo\hola.txt

