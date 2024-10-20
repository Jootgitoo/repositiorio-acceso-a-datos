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
    
//------------------------------------------------------------------------------    
    //CONSTRUCTOR
    
    /**
     * 
     * @param nombre Nombre del nodo principal
     */
    public GestionContenidoDOM(String nombre){
        try {
            
            //DocumentBuilderFactory con .newInstance() creamos un documento XML de manera estructurada
            factory = DocumentBuilderFactory.newInstance();
            
            //Sirve para construir el documento XML
            builder = factory.newDocumentBuilder();
            
            //Creas un nuevo documento XML
            DOMImplementation implementation = builder.getDOMImplementation();
            
            //Crea un documento XML 
            this.documento = (Document) implementation.createDocument(null, //Indica que no hay espacio de nombres
                    nombre, //Elemento raiz del documento
                    null ); //No hay elemento doctype definido
            
            //Establece la version del documento XML
            this.documento.setXmlVersion("1.0");
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//------------------------------------------------------------------------------    
    //MÉTODOS
    
    /**
     * Este método crea un nuevo nodo con el nombre especificado y lo agrega al documento, devolviendo el nodo creado.
     * @param nombreNodo nombre que va a tener el nodo que se va a crear
     * @return devuelve el nodo creado
     */
    public Element addNodo(String nombreNodo){
        
        //Creamos un nodo utilizando el método createElement del objeto documento cuyo nombre es nombreNodo
        Element nodoPrincipal = this.documento.createElement(nombreNodo);
        
        //Añade el nodo creado al documento utilizando el método appendChild
        documento.getDocumentElement().appendChild(nodoPrincipal);
        
        //Devolvemos el nodo creado
        return nodoPrincipal;
    }
    
    
    /**
     * Este método crea un nodo y lo añade dentro de un nodo padre
     * @param datoEmple dato del empleado que se va a añadir al nodo
     * @param elementoPadre Elemeto padre que va a tener el nodo 
     * @return Devuelve el nodo con el dato añadido
     */
    public Element addNodo(String datoEmple, Element elementoPadre){
        
        //.crateElement --> creamos un nodo llamado el valor de datoEmple
        Element dato = this.documento.createElement(datoEmple);
        
        //.appendChild --> añade el nodo creado al documento
        elementoPadre.appendChild(dato);
        
        return dato;
    }
    
    
    /**
     * Sirve para crear un nodo, con un texto, y un elemento padre
     * @param datoEmple Nombre del nodo que vas a crear
     * @param texto Contenido del nodo
     * @param raiz Elemento padre que va a tener
     */
    public void addNodoYTexto (String datoEmple, String texto, Element raiz){
        
        //.crateElement --> creamos un nodo llamado el valor de datoEmple
        Element dato = this.documento.createElement(datoEmple);
        
        //.createTextNode --> Creas el texto que va a tener el nodo
        Text textoDato = this.documento.createTextNode(texto);
        
        //.appendChild --> textoDato se añade como hijo al nodo dato
        dato.appendChild(textoDato);
        
        //.appendChild --> el nodo dato se añade como hijo a raiz
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
      
    //Ejercicio 1
    public void addCargo(){
      
        //Me devuelve todos los Empleado que haya
        NodeList nodeList = this.documento.getElementsByTagName("Empleado"); //Creo una nodelista donde cojo todos los element (nodos)
        
        //Va a añadir un elemento llamado Cargo 
        for (int i=0; i<nodeList.getLength(); i++){
             
            //De la nodeList en la posicion de i compruebo que lo q haya sea un empleado y lo obtengo como Element donde posteriormente le añadiré el cargo y el valor de cargo
            Element empleadoTemporal = (Element) nodeList.item(i);
            
            
            //Creamos un elemento hijo llamado cargo
        
            addNodoYTexto("Cargo", "Por especificar", empleadoTemporal);
 
        }

    }  
    
    
    //Ejercicio 2
    public void quitarElmentoDeEmpleado(String nombreElemento){
        //Sacamos la lista
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        //rrecorro todos los nodos y cojo el nodo actual
        for(int i=0; i < nodeList.getLength(); i++){
            
            Element empleElemento = (Element) nodeList.item(i);
            
            //Busco el hijo que quiero eliminar
            NodeList elementos = empleElemento.getElementsByTagName(nombreElemento);
            
            if(elementos.getLength() > 0){ //Si existe lo borro
                Node elementoABorrar = elementos.item(0);
                empleElemento.removeChild(elementoABorrar);
            }
        }
        //Actualizo el archivo
        generarArchivodelDOM("empleados_actualizados.xml");
    }
        
    
    //Ejercicio 3
    /**
     * 
     * @param identificador 
     * @param nuevoSalario 
     */
    public void modificarSalarioEmpleado(long identificador, String nuevoSalario){
        
        //Cojo a los Empleado y lo meto a una nodeList
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        boolean encontrado = false;
        int i = 0;

        
        while(!encontrado && i < nodeList.getLength()){ //Mientras q encontrado sea false y la i mas pequeña que la longitud de la nodeList
            
            //Creamos un empleado temporal
            Element empleadoTemp = (Element) nodeList.item(i);
            
            //Ampliamos la i para que busque en el siguiente empleado si es q este no es el que buscamos
            i++;
                
            //Saco el valor del id del empleado 
            String idEmpleTemp = getTagValue("identificador", empleadoTemp);
            
            //compruebo id. Con esto se si este es el empleado que buscamos, ya que le hemos pasado por parametro el id (identificador)
            if(idEmpleTemp != null && Long.parseLong(idEmpleTemp) == identificador){
                
                //Con esto decimos que hemos encontrado el id y el programa no volverá a entrar en el while
                encontrado = true;
                
                //Dentro del empleadoTemporal (empleado el cual quiero cambiar el salario) busco el salario y lo meto en una NodeList(puesto que es lo que devuelve el método
                //Estamos diciendo guardame en una NodeList todo los Salario que tenga el empleadoTemp
                NodeList salarioList = empleadoTemp.getElementsByTagName("Salario");
                
                //
                if(salarioList.getLength() > 0){ //Si tiene un salario
                    
                    //Obtengo de la nodeList la posicion 0 (en este caso la nodeList solo va a tener 1 elemento que va ser en la pos 0 y es el salario
                    salarioList.item(0)
                            .setTextContent(nuevoSalario); //Modificas el CONTENIDO que tenga la etiqueta en la pos 0 (en este caso modifica el salario por salarioNuevo)
                    
                } else { //Si no existe el nodo 
                    
                    //Añado el nodo Salario (<Salario> </Salario> y el meto el valor nuevoSalario)
                    addNodoYTexto("Salario", nuevoSalario, empleadoTemp);
                    
                }
                
                //lo guardo en el archivo todo
                generarArchivodelDOM("empleados_actualizados.xml");
 
            }
         
        }
        
        //Aqui no ha encontrado el empleado
        System.out.println("No encuentro al empleado");
        
    }
           
   
}
