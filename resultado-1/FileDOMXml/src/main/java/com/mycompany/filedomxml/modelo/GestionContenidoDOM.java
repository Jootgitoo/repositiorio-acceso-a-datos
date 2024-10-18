/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filedomxml.modelo;

import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 16 oct 2024
 */
public class GestionContenidoDOM {
    //ATRIBUTOS 
    Document documento;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    
    //CONSTRUCTOR
    /**
     * 
     * @param nombre Nombre del nodo principal
     */
    public GestionContenidoDOM(String nombre){
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            
            DOMImplementation implementation = builder.getDOMImplementation();
            this.documento = (Document) implementation.createDocument(null, nombre, null );
            this.documento.setXmlVersion("1.0");
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //MÉTODOS
    
    /**
     * Este método crea un nuevo nodo con el nombre especificado y lo agrega al documento, devolviendo el nodo creado.
     * @param nombreNodo nombre que va a tener el nodo que se va a crear
     * @return 
     */
    public Element addNodo(String nombreNodo){
        
        //Creamos un nodo utilizando el método createElement del objeto documento cuyo nombre es nombreNodo
        Element nodoPrincipal = this.documento.createElement(nombreNodo);
        
        //Añade el nodo creado al documento utilizando el método appendChild
        documento.getDocumentElement().appendChild(nodoPrincipal);
        
        return nodoPrincipal;
    }
    
    public Element addNodo(String datoEmple, Element elementoPadre){
        Element dato = this.documento.createElement(datoEmple);
        elementoPadre.appendChild(dato);
        return dato;
    }
    
    public void addNodoYTexto (String datoEmple, String texto, Element raiz){
        Element dato = this.documento.createElement(datoEmple);
        Text textoDato = this.documento.createTextNode(texto);
        dato.appendChild(textoDato);
        raiz.appendChild(dato);
    }
    
    private Transformer preProcess(){
        
        Transformer transformer = null;

        
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
        
    }
    
    
    public void generarArchivodelDOM(String nombreArchivo){
        try {
            Source source = new DOMSource(this.documento);
            Result salida = new StreamResult(new File(nombreArchivo));
            
            preProcess().transform(source, salida);
            
        } catch (TransformerException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarPantalla(){
        
        try {
            Source source = new DOMSource(this.documento);
            Result salida = new StreamResult(System.out);
            
            preProcess().transform(source, salida);
            
        } catch (TransformerException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cargarArchivoEnMemoria(String nombreArchivo) {
        try {
            this.documento = this.builder.parse(new File(nombreArchivo));
            this.documento .getDocumentElement().normalize();
            
        } catch (SAXException | IOException ex){
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
            
    
    public String getElementPrincipal(){
        return this.documento.getDocumentElement().getNodeName();
    }
    
    /**
     * Va a devolver el valor de un elemento que nosotros queramos
     * @param tag
     * @param element
     * @return 
     */
    private String getTagValue(String tag, Element element){
        
        //Busca los que estén dentro del elemento tag
        NodeList nodeList = element. //Crea una lista de nodos que sean hijos del elemento
                getElementsByTagName(tag) //Saca todos los elementos contenidos en el padre y que tienen tag como elemento hijo
                .item(0). //Coge el primer valor que coincide con tag
                getChildNodes(); //Coje los nodos hijos (ignora atributos, por ejemplo)
        
        Node node = nodeList.item(0); //Creo un nodo con la informacion del nodo hijo
        
        if (node != null){
            return node.getNodeValue(); //Si nodo esta vacio lo devuelvo
        } else {
            return null; //Si no existe ningun nodo que tenga la tag que buscamos devuelve null
        }       
    }
    
    /**
     * 
     * @param node
     * @return 
     */
    private Empleado getEmpleado(Node node){ //Le estoy pasando un nodo, porque getEmpleados() devuelve una NodeList
        
        //ESto es asi porque el metodo .getElementsByTagName devuelve una NodeList
        Empleado emple = new Empleado();
        
        if (node.getNodeType() == Node.ELEMENT_NODE){ //Vernificamos si el nodo es de tipo element (no tiene por que serlo)
            Element element = (Element) node; //Si lo es, convierte el nodo en un objeto element
            
            //El empleado que he creado antes, lo relleno con los datos del element
            emple.setIdentificador(Long.parseLong(getTagValue("identificador", element)));
            emple.setApellido(getTagValue("apellido", element));
            emple.setDepartamento(Integer.parseInt(getTagValue("departamento", element)));
            emple.setSalario(Double.parseDouble(getTagValue("salario", element)));
        }
        return emple; //Devuelvo el empleado
    }
    
    
    public List<Empleado> getEmpleados(){
        List<Empleado> empleList = new ArrayList<Empleado>(); //Me creo mi lista empleados
        //Me devuelve todos los Empleado que haya
        NodeList nodeList = this.documento.getElementsByTagName("Empleado"); //Creo la nodelista donde cojo todos los element (nodos)
        
        for(int i=0; i<nodeList.getLength(); i++){
            empleList.add(getEmpleado(nodeList.item(i))); //Añado los nodos a la lista de empleados
        }
        return empleList;
    }
      
    
    public void addElemento(String raiz){

        //TERMINAR ESTE MÉTODO
        
        //Me devuelve todos los Empleado que haya
        NodeList nodeList = this.documento.getElementsByTagName("Empleado"); //Creo la nodelista donde cojo todos los element (nodos)
        
        for (int i=0; i<nodeList.getLength(); i++){
            
           
            //Creamos un elemento hijo llamado cargo
            Element elemento = documento.createElement("cargo");
            
            Text textoDato = this.documento.createTextNode("Por especificar");
            
            elemento.appendChild(textoDato);
            raiz.appendChild(raiz);
            
        }
        

    }  
        
        
           
    
    
    
    //remove.Chair
            
}
