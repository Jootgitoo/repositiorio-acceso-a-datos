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
import java.util.List;
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
import javax.xml.transform.TransformerException;
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
        Universidad uni2 = new Universidad(2, "Lenguas", "Madrid", 8);
        Universidad uni3 = new Universidad(3, "Historia", "Caceres", 9.0);
        Universidad uni4 = new Universidad(2, "Biologia", "Alicante", 10);
        
        ArrayList<Universidad> arrayUni = new ArrayList<Universidad>();
        arrayUni.add(uni1);
        arrayUni.add(uni2);
        arrayUni.add(uni3);
        arrayUni.add(uni4);

        RandomAccessFile randomFile = null;
        StringBuffer bufferStrCarrera = null;
        StringBuffer bufferStrUniversidad = null;
            
        try {    
            
            randomFile = new RandomAccessFile("./ORIGEN/datosUniversidades.dat", "rw");
            
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
        
        List<Universidad> listaUniversidades = new ArrayList<>();

        try {
            //Guardo las universidades que haya en el fichero datosUniversidades.dat en una lista
            RandomAccessFile randomFile = new RandomAccessFile("./origen/datosUniversidades.dat", "r");
            
            while(randomFile.getFilePointer() < randomFile.length()){
                Universidad uni = new Universidad();
                
                uni.setId( randomFile.readInt());
                
                byte[] arrayCarrera = new byte [FicheroUniversidad.LONGITUD_CARRERA];
                randomFile.readFully(arrayCarrera);
                String carreraString = new String(arrayCarrera);
                carreraString = carreraString.replace("\0", "");
                uni.setCarrera(carreraString);
                
                
                byte[] arrayCiudad = new byte [FicheroUniversidad.LONGITUD_CIUDAD];
                randomFile.readFully(arrayCiudad);
                String ciudadString = new String(arrayCiudad);
                ciudadString = ciudadString.replace("\0", "");
                uni.setCiudad(ciudadString);
                
                uni.setNotaCorte( randomFile.readDouble() );
                
                listaUniversidades.add(uni);
                
            }
               
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            //Creo el documento en memoria
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            
            //Creo el nodo principal
            this.documento = (Document) implementation.createDocument(null, "Universidades", null);
            this.documento.setXmlVersion("1.0");

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Universidad u: listaUniversidades){
            
            Element element = addNodo("Universidad");
            
            addNodoYTexto("Identificador", Integer.toString( u.getId() ), element );
            addNodoYTexto("Carrera", u.getCarrera(), element);
            addNodoYTexto("Ciudad", u.getCiudad(), element);
            addNodoYTexto("NotaCorte", Double.toString( u.getNotaCorte() ), element);
            
        }
        //El siguiente metodo genera el archivo XML con la informacion que esta cargada en memoria
        generarArchivodelDOM("./origen/carreras.xml");
        
        //El siguiente metodo muestra el XML que acabamos de generar en consola, pero con indentacion
        mostrarPantalla();

    }   
        
    /**
     * Este método crea un nuevo nodo con el nombre especificado y lo agrega al documento, devolviendo el nodo creado.
     * @param nombreNodo nombre que va a tener el nodo que se va a crear
     * @return devuelve el nodo creado
     */
    private Element addNodo(String nombreNodo){
        
        //Creamos un nodo utilizando el método createElement del objeto documento cuyo nombre es nombreNodo
        Element nodoPrincipal = this.documento.createElement(nombreNodo);
        
        //Añade el nodo creado al documento utilizando el método appendChild
        documento.getDocumentElement().appendChild(nodoPrincipal);
        
        //Devolvemos el nodo creado
        return nodoPrincipal;
    }
    
    /**
     * Sirve para crear un nodo, con un texto, y añadirselo a un elemento padre
     * @param datoEmple Nombre del nodo que vas a crear
     * @param texto Contenido del nodo
     * @param raiz Elemento padre que va a tener
     */
    private void addNodoYTexto (String datoEmple, String texto, Element raiz){
        
        //.crateElement --> creamos un nodo llamado el valor de datoEmple
        Element dato = this.documento.createElement(datoEmple);
        
        //.createTextNode --> Creas el texto que va a tener el nodo
        Text textoDato = this.documento.createTextNode(texto);
        
        //.appendChild --> textoDato se añade como hijo al nodo dato
        dato.appendChild(textoDato);
        
        //.appendChild --> el nodo dato se añade como hijo a raiz
        raiz.appendChild(dato);
    }
    
    /**  
     * Se encarga de convertir un objeto Document en un archivo XML 
     * en el sistema de archivos. 
     * Utiliza la clase Transformer para realizar la transformación
     * y manejar posibles excepciones que puedan surgir durante el proceso 
     * 
     * @param nombreArchivo nombre del archivo donde se va a guardar la informacion
     */
    private void generarArchivodelDOM(String nombreArchivo){
        try {
            
            //Source --> Un objeto que implemente esta interfaz contiene la información necesaria para actuar como fuente de entrada (fuente XML o instrucciones de transformación).

            //Creamos un objeto Source utilizando DOM por lo tanto podemos utilizar el documento DOM como fuente de transformacion
            Source source = new DOMSource(this.documento);
            
            //Crea un nuevo archivo (en este caso va a tener la ruta donde se va a guardar, incluyendo el nombre)
            //En nuestro caso ./resources/Empleados.xml
            Result salida = new StreamResult(new File(nombreArchivo));
            
            
            //Generamos el archivo XML en el sistema de archivos con el nombre especificado en nombreArchivo
            preProcess("no") //Llamamos a preProcess para obtener un Tranformer utilizando el metodo .transform
                    .transform(source, salida); //Nos convierte el Source en un documento Result 
            
        } catch (TransformerException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Mostramos el documento por pantalla
     */
    public void mostrarPantalla(){
        
        try {
            
            //Creamos el documento que vamos a devolver
            Source source = new DOMSource(this.documento);
            
            //Cremos una instancia Result que va a ser el documento modificado (en este caso imprimiremos por pantalla)
            Result salida = new StreamResult(System.out);
            
            //Generamos el archivo XML en el sistema de archivos con el nombre especificado en nombreArchivo
            preProcess("yes") //Llamamos a preProcess para obtener un Tranformer utilizando el metodo .transform
                    .transform(source, salida); //Nos convierte el Source en un documento Result 
            
        } catch (TransformerException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * preprocesa el XML
     * @param indent
     * @return 
     */
    private Transformer preProcess(String indent){
        Transformer transformer =null;
        try {
            //Creo el transformer para luego poder llamarlo en mostrar pantalla
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(MetodosUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        //La linea de abajo da formato al XML
        transformer.setOutputProperty(OutputKeys.INDENT, indent);
        return transformer;
    }
    
}