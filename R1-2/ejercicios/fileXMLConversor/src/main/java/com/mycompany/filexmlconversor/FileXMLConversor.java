/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.filexmlconversor;

import com.mycompany.filexmlconversor.modelo.Conversor;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class FileXMLConversor {

    public static void main(String[] args) {
        
        Conversor modelo = new Conversor("./archivos/alumnos.xml", "./archivos/alumnosPlantilla.xsl", "./archivos/hojaDestino.html");
        
        modelo.ConvertirAHTML();
        
        
    }   
}
