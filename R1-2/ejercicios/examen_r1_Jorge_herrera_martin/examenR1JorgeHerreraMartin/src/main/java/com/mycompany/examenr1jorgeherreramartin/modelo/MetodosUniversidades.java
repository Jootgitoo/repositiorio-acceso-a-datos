/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.examenr1jorgeherreramartin.modelo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 23 oct 2024
 */
public class MetodosUniversidades extends FicheroUniversidad{
    
    //ATRIBUTOS
    Document documento;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
//------------------------------------------------------------------------------    
    //CONSTRUCTOR
    
    public MetodosUniversidades(){
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            
            this.documento = (Document) implementation.createDocument(null, "Universidades.xml", null);
            this.documento.setXmlVersion("1.0");
            
        } catch (ParserConfigurationException ex) {
            
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
//------------------------------------------------------------------------------   
    //MÉTODOS
    
//******************************************************************************
        //EJERCICIO 1
//******************************************************************************
    
    /**
     * Borra un fichero o todo el contenido de la carpeta (depende el parametro)
     * @param folderPath fichero o carpeta que va a borrar
     */
    private boolean borrarCarpeta (File folderPath) {
        
        boolean exito = false;
        
        if (folderPath.isFile()) { //Si el objeto File pasado por parametro es un fichero
            
            //.delete() --> Borra el fichero
            folderPath.delete();
            
            System.out.println("Fichero: " + folderPath.getName() + " borrado con exito.");
            exito = true;
            
        } else if (folderPath.isDirectory()) { //Si el objeto File pasado es un firectorio
            
            //
            String[] internalInfo = folderPath.list();

            for (int i=0; i<internalInfo.length; i++) {
                File tempData = new File(folderPath, internalInfo[i]);

                if (!tempData.isDirectory()) {
                    if (tempData.delete()) {
                        System.out.println("Fichero: " + tempData.getName() + " borrado con exito.");
                        exito = true;
                    } else {
                        System.out.println("No se pudo borrar el archivo " + tempData.getName());
                        exito = false;
                    }
                }
            }
      
            if (folderPath.delete()) {
                System.out.println("Directorio: " + folderPath.getName() + " borrado con exito.");
                exito = true;
            } else {
                System.out.println("No se pudo borrar el directorio " + folderPath.getName());
                exito = false;
            }

                System.out.println("Archivos borrados con exito.");
                exito = true;
        }
        return exito;
    }
  
    
    public boolean crearEstructuraDeCarpetas () {
     
        boolean exito = false;
        
        File fichero1 = new File("./origen");
        File fichero2 = new File("./destino");
        
        boolean exitoFichero1 = borrarCarpeta(fichero1);
        boolean exitoFichero2 = borrarCarpeta(fichero2);
        
        if (exitoFichero1 == true && exitoFichero2 == true){
            //Creo las dos carpetas
            fichero1.mkdir();
            fichero2.mkdir();
            exito = true;
        }
        return exito;
    }
    
//******************************************************************************
        //EJERCICIO 2
//****************************************************************************** 
    
    public boolean altaDatosCarrerasUniversitarias() {
        
        
        Universidad uni1 = new Universidad(1, "Tecnología", "Ciudad Real", 5.5);
        Universidad uni2 = new Universidad(2, "Lenguas", "Madrir", 8);

        ArrayList<Universidad> arrayUni = new ArrayList<Universidad>();
        arrayUni.add(uni1);
        arrayUni.add(uni2);

        RandomAccessFile randomFile = null;
        StringBuffer bufferStrCarrera = null;
        StringBuffer bufferStrUniversidad = null;
            
        try {    
            
            randomFile = new RandomAccessFile("./ORIGEN/datosUniversidaes.dat", "rw");
            
            boolean encontrado = false;
            int pos = 0;
            
            for (Universidad u : arrayUni){
                
                //Coloca el puntero en la posicion que toque según el id
                pos = ((u.getId() - 1) * FicheroUniversidad.LONGITUD_REGISTRO );
                
                randomFile.seek(pos); //Me posiciono en el valor de pos
                
                randomFile.writeInt(u.getId());
                
                bufferStrCarrera = new StringBuffer(u.getCarrera());
                randomFile.writeChars(bufferStrCarrera.toString());
                
                bufferStrUniversidad =  new StringBuffer(u.getCiudad());
                randomFile.writeChars(bufferStrUniversidad.toString());
                
                randomFile.writeDouble(u.getNotaCorte());
            }
            return true;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
       
//******************************************************************************
        //EJERCICIO 3
//******************************************************************************     
    
    public void generarXMLCarrerasUniversitarias(){
        
        DataInputStream inFile = null; 

        try {
            
            inFile = new DataInputStream (new FileInputStream("./origen/datosUniversidades.dat"));
            
            
            
.readIn
            
            
            File tempFile = fileReader.read();
            
            int pos = 0;
            
            randomFile = new RandomAccessFile("./ORIGEN/datosUniversidaes.dat", "rw");
            
            Element nodoPrincipal = this.documento.createElement("Universidades");
            documento.getDocumentElement().appendChild(nodoPrincipal);
            
            for (int i=0; i< randomFile.length(); i++){
                
                Element nodoUniversidad = this.documento.createElement("Universiad");
                documento.getDocumentElement().appendChild(nodoPrincipal);
                
                Universidad u = new Universidad();
                
                
                Element dato = this.documento.createElement("Id");
                Text textoDato = this.documento.createTextNode(Integer.toString(u.getId()));
                dato.appendChild(textoDato);
                
                documento.appendChild(nodoUniversidad);
                
                
                
                pos += FicheroUniversidad.LONGITUD_REGISTRO - 1;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
/*  public boolean ModificaCarreraUniversitaria(int id, String nuevaCiudad) throws IOException{
        
        RandomAccessFile ficheroAleatorio = null;
        
       
        try {
            ficheroAleatorio = new RandomAccessFile ("./ORIGEN/datosUniversidades.dat", "rw");
            
            int pos = 0;
            
            boolean encontrado = false;
            
            while (!encontrado && pos < ficheroAleatorio.length()){
                
                if(id == ficheroAleatorio.readInt()){
                    encontrado = true;
                    
                    pos = (id - 1) * super.getLONGITUD_FICHERO();
                    
                    ficheroAleatorio.seek(pos);

                    ficheroAleatorio.
                    
                }
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
*/
}
    
    
    
    
    
    

