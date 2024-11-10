/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.examenr1jorgeherreramartin.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 23 oct 2024
 */
public class MetodosUniversidades extends FicheroUniversidad{


    public void MetodosUniversidades(){
        
    }
    
    //MÉTODOS
    public void CrearEstructuraDeCarpetas (String rutaFichero1, String rutaFichero2) {
     
        File fichero1 = new File(rutaFichero1);
        File fichero2 = new File(rutaFichero2);
        
        //Eliminamos una carpeta carpeta
        if (fichero1.isFile()) { //Si el objeto File pasado por parametro es un fichero
            
            //.delete() --> Borra el fichero
            fichero1.delete();
            
            System.out.println("Fichero: " + fichero1.getName() + " borrado con exito.");
            
        } else if (fichero1.isDirectory()) { //Si el objeto File pasado es un firectorio
            
            //
            String[] internalInfo = fichero1.list();

            for (int i=0; i<internalInfo.length; i++) {
                File tempData = new File(rutaFichero1, internalInfo[i]);

                if (!tempData.isDirectory()) {
                    if (tempData.delete()) {
                        System.out.println("Fichero: " + tempData.getName() + " borrado con exito.");
                    } else {
                        System.out.println("No se pudo borrar el archivo " + tempData.getName());
                    }
                }
            }
      
        
            if (fichero1.delete()) {
                System.out.println("Directorio: " + fichero1.getName() + " borrado con exito.");
            } else {
                System.out.println("No se pudo borrar el directorio " + fichero1.getName());
            }

                System.out.println("Archivos borrados con exito.");
        }
        
        //Eliminamos la segunda carpeta
        if (fichero2.isFile()) { //Si el objeto File pasado por parametro es un fichero
            
            //.delete() --> Borra el fichero
            fichero2.delete();
            
            System.out.println("Fichero: " + fichero2.getName() + " borrado con exito.");
            
        } else if (fichero2.isDirectory()) { //Si el objeto File pasado es un firectorio
            
            //
            String[] internalInfo = fichero2.list();

            for (int i=0; i<internalInfo.length; i++) {
                File tempData = new File(rutaFichero2, internalInfo[i]);

                if (!tempData.isDirectory()) {
                    if (tempData.delete()) {
                        System.out.println("Fichero: " + tempData.getName() + " borrado con exito.");
                    } else {
                        System.out.println("No se pudo borrar el archivo " + tempData.getName());
                    }
                }
            }
      
            if (fichero2.delete()) {
              System.out.println("Directorio: " + fichero2.getName() + " borrado con exito.");
            } else {
              System.out.println("No se pudo borrar el directorio " + fichero2.getName());
            }

            System.out.println("Archivos borrados con exito.");
        }
        

        //Creo las dos carpetas
        fichero1.mkdir();
        fichero2.mkdir();
   
    }
    
    
    public boolean AltaDatosCarrerasUniversitarias(Universidad objUniversidad) throws IOException{
        
        RandomAccessFile randomFile = null;
        StringBuffer bufferStrCarrera = null;
        StringBuffer bufferStrUniversidad = null;
        
        try {

            randomFile = new RandomAccessFile("./ORIGEN/datosUniversidaes.dat", "rw");
            
            boolean encontrado = false;
            int pos = 0;
            
            while (!encontrado){
                
                if(pos > randomFile.length()){
                    return false;
                }
                
                randomFile.seek(pos);
                int idLeido = randomFile.readInt();
                
                if (idLeido == objUniversidad.getId()){
                    encontrado = true;
                } else {
                    pos += super.getLONGITUD_FICHERO() - super.getLONGITUD_IDENTIFICADOR();
                }
            }
            
            //Si llega aqui es q lo ha encontrado
            pos = super.getLONGITUD_FICHERO() * (super.getLONGITUD_IDENTIFICADOR() - 1);
        
            randomFile.seek(pos);
            
            randomFile.writeInt(objUniversidad.getId());
            
            bufferStrCarrera = new StringBuffer(objUniversidad.getCarrera());
            randomFile.writeChars(bufferStrCarrera.toString());
            
            bufferStrUniversidad =  new StringBuffer(objUniversidad.getCiudad());
            randomFile.writeChars(bufferStrUniversidad.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try{
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
        return true;
    }
       
    
    private Transformer preProcessYes(){
        
        //Almacena la instancia del transformador
        Transformer transformer = null;

        
        try {
            
            //Crear un nuevo objeto Transformer
            //Esto sirve para transformar documentos XML a otros formatos
            transformer = TransformerFactory.newInstance().newTransformer();
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        transformer.setOutputProperty //Se establece una propiedad de salida (esto sirve para que te "devuelva" el documento
        (OutputKeys.INDENT, //Indicamos que el XML tiene q estar indentado
                "yes"); //Como ponemos yes saldrá identado, para que no lo haga tienes que poner "no"
        
        //Returneamos el transformer
        return transformer;
        
    }
    private Transformer preProcessNo(){
        
        //Almacena la instancia del transformador
        Transformer transformer = null;

        
        try {
            
            //Crear un nuevo objeto Transformer
            //Esto sirve para transformar documentos XML a otros formatos
            transformer = TransformerFactory.newInstance().newTransformer();
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        transformer.setOutputProperty //Se establece una propiedad de salida (esto sirve para que te "devuelva" el documento
        (OutputKeys.INDENT, //Indicamos que el XML tiene q estar indentado
                "no"); //Como ponemos yes saldrá identado, para que no lo haga tienes que poner "no"
        
        //Returneamos el transformer
        return transformer;
        
    }
    
    public void GenerarXMLCarrerasUniversitarias(){
        
            RandomAccessFile randomFile = null;
            DocumentBuilderFactory docFactory = null;
            DocumentBuilder docBuilder = null;
            DOMImplementation domImplementation = null;
            Document documento;
            
               
        try {
            
            randomFile = new RandomAccessFile("./ORIGEN/datosUniversidades.dat", "rw");
            
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            domImplementation = docBuilder.getDOMImplementation();
            documento = (Document) domImplementation.createDocument(null, "documnetoTemporal", null);
            documento.setXmlVersion("1.0");
            
            String nombreArchivo = "./Origen/carreras.xml";
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
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
    
    
    
    
    
    

