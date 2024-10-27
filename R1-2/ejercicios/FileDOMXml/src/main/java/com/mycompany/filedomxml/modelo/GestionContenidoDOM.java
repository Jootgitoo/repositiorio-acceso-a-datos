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
     * Creas un nuevo documento XML con un elemento raiz especificado por parametro
     * @param nombre Nombre del nodo principal (elemento principal)
     */
    public GestionContenidoDOM(String nombre){
        try {
            
            //DocumentBuilderFactory con .newInstance() creamos un documento XML de manera estructurada
            factory = DocumentBuilderFactory.newInstance();
            
            //Sirve para construir el documento XML
            builder = factory.newDocumentBuilder();
            
            //Creas y manipulas un nuevo documento XML
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
        //Al elemento padre le añades (.appendChild) el elemento llamado dato
        elementoPadre.appendChild(dato);
        
        return dato;
    }
    
    
    /**
     * Sirve para crear un nodo, con un texto, y añadirselo a un elemento padre
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
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        transformer.setOutputProperty //Se establece una propiedad de salida (esto sirve para que te "devuelva" el documento
        (OutputKeys.INDENT, //Indicamos que el XML tiene q estar indentado
                "yes"); //Como ponemos yes saldrá identado, para que no lo haga tienes que poner "no"
        
        //Returneamos el transformer
        return transformer;
        
    }
    
    /**
     * Generamos un archivo XML con un nombre especificado
     *  param nombreArchivo nombre del archivo donde se va a guardar la informacion
     */
    public void generarArchivodelDOM(String nombreArchivo){
        try {
            
            //Source --> Un objeto que implemente esta interfaz contiene la información necesaria para actuar como fuente de entrada (fuente XML o instrucciones de transformación).
            
            
            //Creamos un objeto Source utilizando DOM por lo tanto podemos utilizar el documento DOM como fuente de transformacion
            Source source = new DOMSource(this.documento);
            
            //Crea un nuevo archivo (en este caso va a tener la ruta donde se va a guardar, incluyendo el nombre)
            //En nuestro caso ./resources/Empleados.xml
            Result salida = new StreamResult(new File(nombreArchivo));
            
            
            //Generamos el archivo XML en el sistema de archivos con el nombre especificado en nombreArchivo
            preProcess() //Llamamos a preProcess para obtener un Tranformer utilizando el metodo .transform
                    .transform(source, salida); //Nos convierte el Source en un documento Result 
            
        } catch (TransformerException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
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
            preProcess() //Llamamos a preProcess para obtener un Tranformer utilizando el metodo .transform
                    .transform(source, salida); //Nos convierte el Source en un documento Result 
            
        } catch (TransformerException ex) {
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * 
     * @param nombreArchivo 
     */
    public void cargarArchivoEnMemoria(String nombreArchivo) {
        try {
            this.documento = this.builder //Para analiazar el archivo XML
                    .parse //Convierte el contenido del XML en un obteo Document que se almacena en la variable "documento" de la clase
                    (new File(nombreArchivo)); //Crea un objeto File que es el archivo llamado nombreArchivo
            
            
            this.documento.getDocumentElement() //obtenemos el elemento raiz del documento XML
                    .normalize(); //Llamamos a normalize el elemento raíz, que realiza una normalización del documento, eliminando nodos de texto vacíos y fusionando nodos de texto contiguos. Esto asegura que el documento esté en un estado consistente y bien estructurado.
            
        } catch (SAXException | IOException ex){
            Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
            
    /**
     * Consigo el elemento principal (nodo padre) del documento
     * @return 
     */
    public String getElementPrincipal(){
        return this.documento.getDocumentElement().getNodeName();
    }
    
    /**
     * Busca el primer nodo hijo de un elemento que coincide con el nombre de etiqueta proporcionado.
     * Si encuentra un nodo, devuelve su valor; de lo contrario, devuelve null.
     *
     * @param tag Elemento del que va a sacar todos los elementos
     * @param element Elemento padre, Donde nos vamos a meter a buscar entre sus hijos
     * @return 
     */
    private String getTagValue(String tag, Element element){
        
        //Busca los que estén dentro del elemento tag
        NodeList nodeList = element. //Crea una lista de nodos que sean hijos del elemento
                getElementsByTagName(tag) //Saca todos los elementos hijos llamados tag
                .item(0). //Coge el primer valor que coincide con tag
                getChildNodes(); //Coge los nodos hijos (ignora atributos, por ejemplo)
        
        Node node = nodeList.item(0); //Creo un nodo con la informacion del nodo hijo
        
        if (node != null){
            return node.getNodeValue(); //Si nodo esta vacio lo devuelvo
        } else {
            return null; //Si no existe ningun nodo que tenga la tag que buscamos devuelve null
        }       
    }
    
    /**
     * Dentro de un nodo, hay muchos Empleado, pues extraigo la información de uno de ellos
     * @param node
     * @return Devuelve un empleado
     */
    private Empleado getEmpleado(Node node){ //Le estoy pasando un nodo, porque getEmpleados() devuelve una NodeList
        
        //Creamos una instancia empleado donde almacenaremos los datos del empleado en cuestion
        Empleado emple = new Empleado();
        
        //Se verifica si el tipo de nodo es ELEMENT_NODE. 
        //Esto asegura que el nodo es un elemento XML (como un <empleado>), ya que no todos los nodos son elementos 
        //(pueden ser nodos de texto, comentarios, etc.)
        
        if (node.getNodeType() == Node.ELEMENT_NODE){ 
            Element element = (Element) node; //Si lo es, convierte el nodo en un objeto element
            
            //El empleado que he creado antes, lo relleno con los datos del element
            emple.setIdentificador(Long.parseLong(getTagValue("identificador", element)));
            emple.setApellido(getTagValue("apellido", element));
            emple.setDepartamento(Integer.parseInt(getTagValue("departamento", element)));
            emple.setSalario(Double.parseDouble(getTagValue("salario", element)));

        }
        return emple; //Devuelvo el empleado
    }
    
    
    /**
     * De un nodo padre saca una lista con todos los elementos Empleado que tenga
     * @return Devuelve una lista de Empleado
     */
    public List<Empleado> getEmpleados(){
        
        //Me creo mi lista empleados
        List<Empleado> empleList = new ArrayList<Empleado>(); 
        
        //Creo la nodelista donde me devuelve todos los Empleado que haya dentro del nodo padre
        NodeList nodeList = this.documento.getElementsByTagName("Empleado"); 
        
        
        for(int i=0; i<nodeList.getLength(); i++){ //Lo hago tantas veces como longitud de la lista
            empleList.add(getEmpleado(nodeList.item(i))); //Añado cada Empleado a la lista
        }
        
        //Devuelvo la lista
        return empleList;
    }
      
    /**
     * Añadimos la etiqueta carga al nodo Empleado
     */
    public void addCargo(){
      
        //Me devuelve todos los Empleado que haya
        NodeList nodeList = this.documento.getElementsByTagName("Empleado"); 
        
        //Va a añadir un elemento llamado Cargo por cada "cajon" que tenga la nodeList
        for (int i=0; i<nodeList.getLength(); i++){
             
            //De la nodeList en la posicion de i compruebo que lo q haya sea un empleado y lo obtengo como Element donde posteriormente le añadiré el cargo y el valor de cargo
            Element empleadoTemporal = (Element) nodeList.item(i);
            
            
            //Creamos un elemento hijo llamado cargo
            addNodoYTexto("Cargo", "Por especificar", empleadoTemporal);
 
        }

    }  
    
    
    /**
     * Quito un elemento del hijo empleado
     * @param nombreElemento 
     */
    public void quitarElmentoDeEmpleado(String nombreElemento){
        
        //Sacamos la lista de Empleado
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        //Recorro la lista entera
        for(int i=0; i < nodeList.getLength(); i++){
            
            //Cojo el cajón numero i de la lista
            //Casteando para comprobar que estamos cogiendo un Element 
            Element empleElemento = (Element) nodeList.item(i);
            
            //Busco el hijo que quiero eliminar, es decir,
            //Primero obtengo una lista de Empleado que los busco dentro de Empleado
            //Posteriormente dentro de empleado busco nombreElemento (siguiente linea)
            NodeList elementos = empleElemento.getElementsByTagName(nombreElemento);
            
            //Este if solo puede ser 1 o 0
            //1 --> Si contiene el nodo que queremos eliminar
            //0 --> Si no lo contiene
            if(elementos.getLength() > 0){ //Si contiene el elemento que quiero lo borro
                Node elementoABorrar = elementos.item(0);
                empleElemento.removeChild(elementoABorrar);
            }
        }
        //Actualizo el archivo
        generarArchivodelDOM("empleados_actualizados.xml");
    }
        
    
    //Ejercicio 3
    /**
     * Modificamos el salario de un Empleado
     * @param identificador identificador del empleado (forma de encontrar a un empleado en especifico)
     * @param nuevoSalario Salario nuevo que va a tener el empleado
     */
    public void modificarSalarioEmpleado(long identificador, String nuevoSalario){
        
        //Cojo a los Empleado y lo meto a una nodeList
        NodeList nodeList = this.documento.getElementsByTagName("Empleado");
        
        //Boolean para marcar cuando lo hemos encontrado al empleado
        boolean encontrado = false;
        
        //Contador
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
                
                
                if(salarioList.getLength() > 0){ //Si tiene un salario (solo puede tener 1) por eso la Length solo puede ser 1 o 0
                    
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
