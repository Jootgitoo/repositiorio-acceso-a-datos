/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filexmlconversor.modelo;

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
            this.origenDatos = new StreamSource(origenDatos);
            this.hojaEstilos = new StreamSource (hojaEstilos);
            this.pagHTML = new FileOutputStream(new File(htmlDestino));
            
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ConvertirAHTML(){
        try {
            Result result = new StreamResult(pagHTML);
            
            //Creamos el transformer con el TransformerFactory
            Transformer transformer = TransformerFactory.newInstance().newTransformer(this.hojaEstilos);
            
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
