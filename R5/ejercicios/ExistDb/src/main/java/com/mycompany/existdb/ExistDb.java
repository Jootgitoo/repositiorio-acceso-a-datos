/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.existdb;

import java.io.StringWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;
import org.w3c.dom.Node;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */


//UPDATE REPLACE --> PARA MODIFICAR NODOS COMPLETOS
//UPDATE VALUE --> PARA MODIFICAR ATRIBUTOS
public class ExistDb {

    //ATRIBUTOS
    
    //Variables para la conexión a la bbdd
    private static XQDataSource server;
    private static XQConnection connection;
    
    private static TransformerFactory transformerFactory = TransformerFactory.newInstance();
    private static Transformer transformer;
    
    
    
//------------------------------------------------------------------------------
    //MÉTODOS
    
    
    //COMO QUITAR EL XMLNS AL MOSTRAR EL CONSULTA POR PANTALLA
    //COMO HACER QUE SE MUESTREN LAS LETRAS CON TILDES
    //PROBAR A SACAR VALORES DE LOS NODOS Y RETOCARLOS
    
    public static void main(String[] args) {
        conecta();
        
//------------------------------------------------------------------------------------------------------------------
        
        try {
            
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//------------------------------------------------------------------------------------------------------------------
     
        
        //PRACTICA EXTRA
        
        //consulta("/universidad/departamento[codigo = 'MAT1']");
        
        //modificacion("update rename /EMPLEADOS/fila_emple as 'EMP_ROW' "); 
        
//------------------------------------------------------------------------------------------------------------------
                
        //Añade un empleado al departamento que ocupa la posición 2.
        //modificacion( "update insert "
//                + "<empleado salario= '2340'>"
//                    + "<puesto>Técnico</puesto>"
//                    + "<nombre>Pedro Fraile</nombre>"
//                + "</empleado>"
//                + "into /universidad/departamento[codigo = 'MAT1']");

//------------------------------------------------------------------------------------------------------------------

        
        //Actualiza el salario de los empleados del departamento con código MAT1 sumándoles 100€.
//        modificacion("for $em in /universidad/departamento[codigo = 'MAT1']/empleado " +
//                        "let $sal := data($em/@salario) " +
//                        "return update value $em/@salario " +
//                        "with data($sal)+100");
        
        
        
//------------------------------------------------------------------------------------------------------------------
        
        //Crea un método que lea de teclado un departamento y visualice sus empleados.
        //visualizaEmpleados();
        
//------------------------------------------------------------------------------------------------------------------
        
        //Leerá de teclado un departamento, su nombre y su localidad, y deberá 
        //añadirlo al documento. Si el código de departamento existe 
        //visualiza que no se puede insertar porque ya existe.
        //insertarDepartamento();
        
//------------------------------------------------------------------------------------------------------------------

        //Lee de teclado un departamento, y deberá borrarlo si existe, 
        //si no existe visualiza que no se puede borrar porque no existe.
        //borrarDepartamento();
        
//------------------------------------------------------------------------------------------------------------------
       
        //Lee por teclado un departamento entero, su codigo nuevo y su nombre nuevo
        //actualizará los datos si el departamento pasado existe si no un mensaje de error
        modificarDepartamento();
        
//------------------------------------------------------------------------------------------------------------------

        //Obtener todos los apellidos de los empleados que aparecen en el documento
        //consulta("/EMPLEADOS/EMP_ROW/APELLIDO");
        
        desconecta();
    }
    
    
    /**
     * Te conecta con una bbdd de exist
     */
    private static void conecta(){
        server = new ExistXQDataSource(); 
        
        
        XQDataSource server = new ExistXQDataSource();
        try {
            server.setProperty("serverName", "localhost");
            server.setProperty("port", "8080");
            server.setProperty("user", "dam2");
            server.setProperty("password", "dam2");
        
            
            connection = server.getConnection();
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Conectado con exito \n");
    }
    
    
    /**
     * Te desconecta de la bbdd de exits
     */
    private static void desconecta(){
        
        try {
            connection.close();
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\n Desconectado con exito");
    }
    
    
    /**
     * Realiza una consulta y te muestra los datos por pantalla
     * @param textoConsulta Consulta que se va a realizar 
     * 
     */
//    private static void consulta(String textoConsulta){
//        
//        try {
//            XQPreparedExpression consulta;
//            XQResultSequence resultado;
//            
//            consulta = con.prepareExpression(textoConsulta);
//            resultado = consulta.executeQuery();
//            
//            XQResultItem r_item;
//            
//            while(resultado.next()){
//                r_item = (XQResultItem) resultado.getItem();
//                System.out.println(r_item.getItemAsString(null));
//            }
//        } catch (XQException ex) {
//            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    /**
     * Realiza una consulta (SELECT) y te devuelve los datos que haya encontrado
     * @param textoConsulta Consulta que se va a realizar 
     */
    private static XQResultSequence consulta(String textoConsulta){
        
        XQPreparedExpression consulta;
        XQResultSequence resultado = null;
        
        try {
            consulta = connection.prepareExpression(textoConsulta);
            resultado = consulta.executeQuery();
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }
    
    /**
     * Realiza una consulta y te muestra solo el contenido en caso de q quieras /text()
     * @param inputConsulta 
     */
    public void realizarConsulta (String inputConsulta) {
        try {
            XQPreparedExpression xqConsulta = connection.prepareExpression(inputConsulta);
            XQResultSequence xqResultado = xqConsulta.executeQuery();

            XQResultItem resultItem;
            while (xqResultado.next()) {
                resultItem = (XQResultItem) xqResultado.getItem();

                if (resultItem.getItemType().getBaseType() == XQItemType.XQBASETYPE_STRING) {
                    System.out.println(resultItem.getAtomicValue());
                } else {
                    System.out.println(eliminarNamespace(resultItem));
                }
            }
        } catch (XQException ex) {
            ex.printStackTrace();
        }
  }
    
    
    public static void verEmpleadosDeDepartamento(String codDepartamento){
        
        XQPreparedExpression consulta;
        XQResultSequence resultado;
        
        try {
            
            consulta = connection.prepareExpression("/universidad/departamento[codigo = "+codDepartamento+"]/empleado");
            
            resultado = consulta.executeQuery();
            
            XQResultItem r_item;
            
            while(resultado.next()){
                r_item = (XQResultItem) resultado.getItem();
                System.out.println(eliminarNamespace(r_item));
            }
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Realiza una modificacion en la bbdd como INSERT, UPDATE, DELETE
     * @param textoDML sentencia de modificacion
     */
    private static void modificacion(String textoDML){
        
        try {
            
            
            XQExpression expresion;
            
            expresion = connection.createExpression();
            
            expresion.executeCommand(textoDML);
            
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Modificacion realizada con exito");
    }
    
    
    /**
     * Visualizo los empleados.
     *  - El método se encarga de pedirte el codigo del departamento y elabora 
     *    la consulta pidiendo TODOS los empleados
     * 
     *  - Despues llama a consulta(String) para que pasandole la consulta por 
     *    parametro este método lo lleve a cabo la consulta y la muestre 
     *    por pantalla
     */
    public static void visualizaEmpleados(){
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Indica el codigo de departamento que deseas leer: ");
        String codDepart = scanner.nextLine();
        
        String textoConsulta = "/universidad/departamento[codigo = '"+codDepart+"']/empleado";
        
        consulta(textoConsulta);
                   
    }
    
    
    /**
     * Lee por teclado un departamento que posteriormente añadirá a la bbdd
     * Si el departamento existe visualiza un mensaje que no se puede insertar
     */
    public static void insertarDepartamento() {
        
        try {
            
            Scanner scanner = new Scanner(System.in);
            
            //Pido los datos del departamento
            System.out.print("Indica el telefono del departamento: ");
            String telefono = scanner.nextLine();
            
            System.out.print("Indica el tipo del departamento: ");
            String tipo = scanner.nextLine();
            
            System.out.print("Indica el codigo del departamento: ");
            String codigo = scanner.nextLine();
            
            System.out.print("Indica el nombre del departamento: ");
            String nombre = scanner.nextLine();
            
            
            //Consulto si existe un departamento (compruebo el codigo)
            String consultaSelect = "/universidad/departamento[codigo = '"+codigo+"']";
            
            XQResultSequence resultadoConsulta = consulta(consultaSelect);
            
            if(!resultadoConsulta.next() ) { //Si la consulta no devuelve resultados. No existe el departamento que vamos a insertar
                
                //Insertamos el departamento
                String insert = "update insert "
                        + "<departamento telefono = '"+telefono+"' tipo = '"+tipo+"'>"
                            + "<codigo>"+codigo+"</codigo>"
                            + "<nombre>"+nombre+"</nombre>"
                        + "</departamento>"
                        + "into /universidad";
                
                modificacion(insert);
                
                
            } else { //La consulta tiene datos. Existe el departamento
                
                //Error
                System.out.println("El departamento con ese codigo ya existe");
            }
            
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    /**
     * Lee el codigo del departamento. Lo borra si existe, si no manda un mensaje de error
     */
    public static void borrarDepartamento(){
        
        try {
            
            Scanner scanner = new Scanner(System.in);
            
            //Pido el codigo del departamento
            System.out.print("Indica el codigo del departamento: ");
            String codigo = scanner.nextLine();
            
            //Consulto si existe el departamento
            String consultaSelect = "/universidad/departamento[codigo = '"+codigo+"']";
            XQResultSequence resultadoConsulta = consulta(consultaSelect);
            
            if(resultadoConsulta.next()){
                
                String delete = "update delete /universidad/departamento[codigo = '"+codigo+"']";
                
                modificacion(delete);
                
            } else {
                System.out.println("No existe un departamento con ese codigo");
            }
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void modificarDepartamento(){
        
        try {
            
            Scanner scanner = new Scanner(System.in);
            
            //Pido los datos del departamento
            System.out.print("Indica el telefono del departamento: ");
            String telefonoAntiguo = scanner.nextLine();
            
            System.out.print("Indica el tipo del departamento: ");
            String tipoAntiguo = scanner.nextLine();
            
            System.out.print("Indica el codigo del departamento: ");
            String codigoAntiguo = scanner.nextLine();
            
            System.out.print("Indica el nombre del departamento: ");
            String nombreAntiguo = scanner.nextLine();
            
            
            //Consulto si existe el departamento
            String consultaSelect = "/universidad/departamento["
                    + "@telefono = '"+telefonoAntiguo+"'"
                    + " and @tipo = '"+tipoAntiguo+"'"
                    + " and codigo = '"+codigoAntiguo+"'"
                    + " and nombre = '"+nombreAntiguo+"']";
            
            XQResultSequence resultadoConsulta = consulta(consultaSelect);
            
            if(resultadoConsulta.next()){
                
                System.out.print("Indica el codigo nuevo: ");
                String codigoNuevo = scanner.nextLine();
                
                System.out.print("Indica el nombre nuevo: ");
                String nombreNuevo = scanner.nextLine();
                
                String update = "update replace /universidad/departamento[codigo = '"+codigoAntiguo+"']"
                        + "with "
                        + "<departamento telefono = '"+telefonoAntiguo+"' tipo = '"+tipoAntiguo+"'> "
                            + "<codigo>"+codigoNuevo+"</codigo>"
                            + "<nombre>"+nombreNuevo+"</nombre> "
                        + "</departamento>";
                
                modificacion(update);

            }else {
                System.out.println("El departamento a modificar indicado no existe");
            }
            
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String eliminarNamespace(XQResultItem item){
        
        StringWriter writer = new StringWriter();
        
        try {
            
            
            Node node = (Node) item.getNode();
            
            transformer.transform(new DOMSource(node), new StreamResult(writer));
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return writer.toString();
        
    }
    
    
    
}























