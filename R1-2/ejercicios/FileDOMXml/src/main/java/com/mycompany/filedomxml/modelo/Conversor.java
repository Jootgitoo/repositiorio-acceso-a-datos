/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filedomxml.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 18 oct 2024
 */
public class Conversor {

    private Source origenDatos = null;
    private Source hojaEstilos = null;
    private FileOutputStream pagHTML = null;
    
    public Conversor(String origenDatos, String hojaEstilos, String htmlDestino){
        
        try {
            
            //Datos que convertiremos a HTML
            this.origenDatos = new StreamSource(origenDatos);
            
            //Hoja de estilos que aplicaremos durante la conversion
            this.hojaEstilos = new StreamSource (hojaEstilos);
            
            //Se utilizará para escribir resultado HTML en un archivo
            this.pagHTML = new FileOutputStream(new File(htmlDestino));
            
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ConvertirAHTML(){
        try {
            
            //Especificamos el archivo de salida
            Result result = new StreamResult(pagHTML);
            
            //Creamos el transformer con el TransformerFactory
            //Se crea un objeto Transformer basado en la hoja de estilos proporcionada.
            Transformer transformer = TransformerFactory.newInstance().newTransformer(this.hojaEstilos);
            
            //Pasamos los datos de origenDatos a result
            transformer.transform(this.origenDatos, result);
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally{
            
            try {
                this.pagHTML.close();
            } catch (IOException ex) {
                Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}