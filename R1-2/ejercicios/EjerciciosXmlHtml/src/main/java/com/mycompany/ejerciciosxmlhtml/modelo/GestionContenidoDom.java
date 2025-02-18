/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosxmlhtml.modelo;

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
 * @version 1.0
 * created on 18 feb 2025
 */
public class GestionContenidoDom {

    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document documento;
    
    /**
     * Creas un documento XML nuevo
     * @param elementoPadre Nodo raiz/padre que va a tener el documento
     */
    public GestionContenidoDom(String elementoPadre){
        
        try {
            
          //CREACION DEL DOCUMENTO
          
            //Creas el documento
            factory = DocumentBuilderFactory.newInstance();
            
            //Construyes el xml
            builder = factory.newDocumentBuilder();
            
            //Creamos el documento con el elementoPadre como nodo
            DOMImplementation implementation = builder.getDOMImplementation();
            this.documento = (Document) implementation.createDocument(null, elementoPadre, null);
            
            //Eliges la version
            this.documento.setXmlVersion("1.0");
            
          //------------------------------------------------------------------------------------------------
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Añado un nodo al documento y lo devuelvo
     * @param nombreNodo Nombre que va a tener el nodo
     * @return Nodo creado
     */
    public Element addNodo(String nombreNodo){
        
        //Creamos un nodo
        Element nodoPrincipal = this.documento.createElement(nombreNodo);
        
        //Lo añadimos al documento dentro del nodo padre
        documento.getDocumentElement().appendChild(nodoPrincipal);
        
        return nodoPrincipal;
    }
    
    
    /**
     * Añado un nodo (se lo añado al padre)
     * @param datoNodo Nodo que se va a insertar dentro del padre
     * @param elementoPadre Nodo padre
     * @return Elemento que se ha insetado
     */
    public Element addNodo(String datoNodo, Element elementoPadre){
        
        Element dato = this.documento.createElement(datoNodo);
        
        //A elemento padre (DENTRO DE EL) añadimos el elemento(nodo) dato
        elementoPadre.appendChild(dato);
        
        return dato;
    }
    
    
    /**
     * Añado un nodo con un texto a un elemento(nodo) padre
     * @param nodoPadre Nodo padre al que le dentro le vamos a meter el nodo
     * @param nombreNodo Nombre del nodo
     * @param textoNodo Texto del nodo
     */
    public void addNodoYTexto(Element nodoPadre, String nombreNodo, String textoNodo){
        
        //Creo el nodo nuevo
        Element nodoNuevo = this.documento.createElement(nombreNodo);
        
        //Creo el texto
        Text texto = this.documento.createTextNode(textoNodo);
        
        //Añado el texto dentro del nodo
        nodoNuevo.appendChild(texto);
        
        //Añado el nodo nuevo dentro del nodo padre
        nodoPadre.appendChild(nodoNuevo);
        
    }
    
    
    public void mostrarPantalla(){
        
        try {
            
            //Creamos el documento que vamos a devolver
            Source source = new DOMSource(this.documento);
            
            //Cremos una instancia Result que va a ser el documento modificado (en este caso imprimiremos por pantalla)
            Result salida = new StreamResult(System.out);
            
            //Generamos el archivo XML en el sistema de archivos con el nombre especificado en nombreArchivo
            preProcess() //Llamamos a preProcess para obtener un Tranformer utilizando el metodo .transform
                    .transform(source, salida); //Nos convierte el Source en un documento Result 
            
        } catch (TransformerException ex) {
            Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que se utilizará para transformar documentos XML
     * @return 
     */
    private Transformer preProcess(){
        
        //Almacena la instancia del transformador
        Transformer transformer = null;

        
        try {
            
            //Crear un nuevo objeto Transformer
            //Esto sirve para transformar documentos XML a otros formatos
            transformer = TransformerFactory.newInstance().newTransformer();
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        transformer.setOutputProperty //Se establece una propiedad de salida (esto sirve para que te "devuelva" el documento
        (OutputKeys.INDENT, //Indicamos que el XML tiene q estar indentado
                "yes"); //Como ponemos yes saldrá identado, para que no lo haga tienes que poner "no"
        
        //Returneamos el transformer
        return transformer;
        
    }
    
    
    /**
     * Se encarga de generar un archivo con el domcumeno xml que está en memoria
     * @param rutaConNombre Ruta donde se encuentra el archvio 
     */
    public void generarArchivo(String rutaConNombre){
        
        try {
            
            //this.documento que está en memoria se transforma en un "fichero" en memoria
            Source source = new DOMSource(this.documento);
 
            //Guardas el fichero
            Result salida = new StreamResult(new File(rutaConNombre));
            
            //Transforma el source en salida
            preProcess().transform(source, salida);
            
        } catch (TransformerException ex) {
            Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Carga un archivo en memoria
     * @param rutaConNombre ruta donde se va a crear el archivo
     */
    public void cargarArchivoEnMemoria(String rutaConNombre){
        
        try {
            
            //Transformo el documento en un archivo
            this.documento = this.builder.parse(new File(rutaConNombre));
            
            //.normalize --> Estructuramos el documento
            this.documento.getDocumentElement().normalize();
            
        } catch (SAXException ex) {
            Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Consigo el nodo padre del documento
     * @return Devuelve el nodo padre de todo el documento
     */
    public String getElementPrincipal(){
        return this.documento.getDocumentElement().getNodeName();
    }
    
    
    public String getTagValue(String tag, Element element){
        
        //Recojo todos los elementos que sean tag y solo me quedo con el primero (pos 0)
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        
        Node node = nodeList.item(0);
        
        if(node != null){
            return node.getNodeValue();
        } else {
            return null;
        }
        
    }
    
    private Empleado getEmpleado(Node node){
        
        Empleado emple = new Empleado();
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            
            Element element = (Element) node;
            
            //El empleado que he creado antes, lo relleno con los datos del element
            emple.setIdentificador(Long.parseLong(getTagValue("identificador", element)));
            emple.setApellido(getTagValue("apellido", element));
            emple.setDepartamento(Integer.parseInt(getTagValue("departamento", element)));
            emple.setSalario(Double.parseDouble(getTagValue("salario", element)));
        }
        return emple;
    }
    
    
    /**
     * Sacamos una lista con todos los elementos empleados
     * @return 
     */
    public List<Empleado> getEmpleados(){
        
        List<Empleado> listaEmpleados = new ArrayList<Empleado>();
        
        //Creo una nodeList para que me devuelva los nodos Empleado
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        
        for(int i=0; i< nodeList.getLength(); i++){
            listaEmpleados.add(getEmpleado(nodeList.item(i)));
        }
        
        return listaEmpleados;
        
    }
    
    
    /**
     * Añade un cargo a los empleados que haya
     */
    public void addCargo(){
        
        //Me devuelve todos los empleados que haya
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        //Por cada noco que haya en nodeList
        for(int i=0; i< nodeList.getLength(); i++){
            
            Element empleadoTemporal = (Element) nodeList.item(i);
            
            addNodoYTexto(empleadoTemporal, "Cargo", "Por especificar");
            
        }
    }
    
    
    /**
     * Quito un elemendo que haya dentro del nodo empleado
     * @param nombreElemento 
     */
    public void quitarElementoDeEmpleado(String nombreElemento){
        
        //Sacamos todos los empleado que haya en empleados
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        for(int i=0; i< nodeList.getLength(); i++){
            
           Element empleElemento = (Element) nodeList.item(i);
           
           //Por cada empleado objetengo sus elementos
           NodeList elementos = empleElemento.getElementsByTagName(nombreElemento);
            
           
           if(elementos.getLength() > 0){
               Node elementoABorrar = elementos.item(0);
               empleElemento.removeChild(elementoABorrar);
           }
        }
        
        generarArchivo("./ejercicios/empleados_actualizaod.xml");
    }
    
    public void modificarEmpleado(long identificador, String nuevoSalario){
        
        
        NodeList listaEmpleados = this.documento.getElementsByTagName("Empleado");
        
        //Para marcar si he encontrado al empleado que quiero
        boolean encontrado = false;
        
        int cont = 0;
        
        while(!encontrado && cont < listaEmpleados.getLength()){
            //Obtengo un empleado
            Element empleadoTemp = (Element) listaEmpleados.item(cont);
            
            cont++;
            
            //Obtengo el id del empleado 
            String idEmpleTemp = getTagValue("identificador", empleadoTemp);
            
            //Encuentro al empleado
            if(idEmpleTemp != null && Long.parseLong(idEmpleTemp) == identificador){
                encontrado = true;
                
                //Le saco el salario
                NodeList salarioList = empleadoTemp.getElementsByTagName("Salario");
                
                if(salarioList.getLength() > 0){ //Hay salario
                    
                    //Cojo el q haya en la primera posicion y lo cambio por el nuevo
                    salarioList.item(0).setTextContent(nuevoSalario);
                } else {
                    
                    addNodoYTexto(empleadoTemp, "Salario", nuevoSalario);
                }
            }
            
            generarArchivo("./ejercicios/empleados_actualizaod.xml");
        }
    }
}







































