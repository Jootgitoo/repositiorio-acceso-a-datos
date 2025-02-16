/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jaas.ejercicio3examen.modelo;

import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 * @version 1.0
 * Created on 13 nov 2024
 */
public class Examen {

    Document documento;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    
    public Examen(String nombre) {
        try {
            factory=DocumentBuilderFactory.newInstance(); //factory es con .getInstance
            builder=factory.newDocumentBuilder();//Creamos un constructor de documentos
            
            DOMImplementation implementation = builder.getDOMImplementation();//Se necesita para crear el documento
            this.documento = (Document) implementation.createDocument(null, nombre, null);//Asi se crea el nodo principal
            this.documento.setXmlVersion("1.0");//siempre la version 1.0
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Element addNodo(String nombreNodo){
        //Asi creamos un nodo (un elemento hijo) para empleados
        Element nodoPrincipal = this.documento.createElement(nombreNodo);
        //Lo añado al documento
        documento.getDocumentElement().appendChild(nodoPrincipal);
        
        return nodoPrincipal;
    }
    
    public Element addNodo(String datoEmple, Element elementoPadre){
        Element dato = this.documento.createElement(datoEmple);
        elementoPadre.appendChild(dato);
        return dato;
    }
    
    public void addNodoYTexto(String datoEmple, String texto, Element raiz){
        //Creamos un elemento hijo
        Element dato = this.documento.createElement(datoEmple);
        //Le meto valor al elemento
        Text textoDato= this.documento.createTextNode(texto);
        //Primero al nodo le meto los datos
        dato.appendChild(textoDato);
        //Y luego meto el nodo en el elemento raiz
        raiz.appendChild(dato);
    }
    public void cargarArchivoEnMemoria(String nombreArchivo){
        try {
            //Para leer un archivo XML necesitamos usar el .parse()
            //de esta manera cargamos el archivo en memoria
            this.documento = this.builder.parse(new File(nombreArchivo));
            //Normalizamos el documento, hay que hacerlo
            this.documento.getDocumentElement().normalize();
            //El archivo ya esta cargado en memoria y listo para imprimir ande toque
        } catch (SAXException | IOException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generarArchivodelDOM(String nombreArchivo){
        try {
            
            Source source = new DOMSource(this.documento);
            Result salida = new StreamResult(new File(nombreArchivo));
            preProcess("yes").transform(source, salida);
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Transformer preProcess(String indent){
        Transformer transformer =null;
        try {
            //Creo el transformer para luego poder llamarlo en mostrar pantalla
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        }
        //La linea de abajo da formato al XML
        transformer.setOutputProperty(OutputKeys.INDENT, indent);
        return transformer;
    }
}
