/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejerciciospractica;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class EjerciciosPractica {

    //Variables para la conexión a la bbdd
    private static XQDataSource server;
    private static XQConnection con;
    
    private static TransformerFactory transformerFactory = TransformerFactory.newInstance();
    private static Transformer transformer;
    
    
    public static void main(String[] args) {
        conecta();
        
        try {
            
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(EjerciciosPractica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Obtén todos los apellidos de los empleados que aparecen en el documento.
        //consulta("/EMPLEADOS/EMP_ROW/APELLIDO");
        
        //Muestra los números de empleado (EMP_NO) de aquellos cuyo salario (SALARIO) sea mayor o igual que 2000.
        //consulta("data(/EMPLEADOS/EMP_ROW[SALARIO <= '7782']/(EMP_NO | SALARIO))");
        
        //Muesta el EMP_NO y el SALARIO del empleado con el salario mas alto
        //consulta("/EMPLEADOS/EMP_ROW[ MAX(SALARIO) ]/(EMP_NO | SALARIO)");
        
        //Muestra la media del salario de los empleados
        //consulta("format-number( avg(/EMPLEADOS/EMP_ROW/SALARIO), '#.00')");
        
        //Selecciona el apellido de todos aquellos que no tengan comision
        //consulta("/EMPLEADOS/EMP_ROW[ not(COMISION) ]/(COMISION | APELLIDO)");
        
        //Muestra el nombre de los departamentos que están en la localidad “MADRID” o “BARCELONA”.
        //consulta("/departamentos/DEP_ROW[ LOC = 'MADRID' or LOC = 'BARCELONA' ]/DNOMBRE");
        
        //Muestra el EMP_NO y el OFICIO de los empleados cuyo salario esté entre 1500 y 2000 (incluidos).
        //consulta("/EMPLEADOS/EMP_ROW[ SALARIO >= '1500' and SALARIO <= '2000']/(EMP_NO | OFICIO)");
        
        //Numero total de empleados
        //consulta("count(/EMPLEADOS/EMP_ROW)");
        
        //El salario promedio de todos los empleados que tengan la etiqueta <SALARIO>
        //consulta("format-number(avg(/EMPLEADOS/EMP_ROW/SALARIO), '#.00')");
        
        //Salario minimo
        //consulta("min(/EMPLEADOS/EMP_ROW/SALARIO)");
        //consulta("format-number(min(/EMPLEADOS/EMP_ROW/SALARIO), '#.00')");
        
        //Para cada empleado que tenga fecha de alta, muestra su EMP_NO, el APELLIDO y 
        //el año de la fecha de alta.
        //consulta("/EMPLEADOS/EMP_ROW[FECHA_ALT]/(EMP_NO, APELLIDO, year-from-date(FECHA_ALT))");
        
        //Muestra el APELLIDO de aquellos empleados cuyo nombre empiece por “MA” (por ejemplo, “MARTIN”).
        //consulta("/EMPLEADOS/EMP_ROW[ starts-with(APELLIDO, 'MA') ]/APELLIDO");
        
        //De todos los empleados que tengan salario superior a 1400, transforma su apellido a mayúsculas.
        //consulta("/EMPLEADOS/EMP_ROW[SALARIO > 1400]/upper-case(APELLIDO)");
        
        //De todos los empleados que tengan salario superior a 1400, transforma su apellido a minusculas.
        //consulta("/EMPLEADOS/EMP_ROW[SALARIO > 1400]/lower-case(APELLIDO)");
        
        //Selecciona los nombres de los titulares de cuentas de tipo “AHORRO” 
        //cuya saldohaber sea mayor que 10.000 y cuya saldodebe sea igual a 0.
        //consulta("/sucursales/sucursal/cuenta[@tipo = 'AHORRO' and saldohaber > 10000 and saldodebe = 0]/nombre");
        
        //Obtén el número de teléfono de las sucursales que estén en la población “Talavera”.
        //consulta("/sucursales/sucursal[poblacion = 'Talavera']/cuenta/numero");
        
        //Muestra el nodo completo de todas las cuentas (tipo “AHORRO” y tipo “PENSIONES”)
        //que correspondan a un titular cuyo nombre contenga el texto “María”.
        //consulta("/sucursales/sucursal/cuenta[(@tipo = 'AHORRO' or @tipo = 'PENSIONES') and contains(nombre, 'María')]");
        
        //Muestra los nombres y puestos de los empleados que tengan el puesto “Profesor” en cualquiera de los departamentos
        //consulta("/universidad/departamento/empleado[puesto = 'Profesor']/(nombre | puesto)");
        
        //Selecciona el director de la zona cuyo cod_zona sea 30, y concatena el texto "DIRECTOR: " antes de su nombre.
        //consulta("/zonas/zona[cod_zona = '30']/concat('Director: ', director )");
        
        
        //Selecciona el padre (parent) del nodo <OFICIO> de cualquier empleado.
        //consulta("/EMPLEADOS/EMP_ROW[OFICIO]");
        
        
        //Inserta un nuevo empleado al final del documento, con la siguiente estructura (puedes inventarte datos)
//        modificacion("update insert"
//                + "<EMP_ROW>"
//                    + "<EMP_NO>9999</EMP_NO>"
//                    + "<APELLIDO>NUEVO</APELLIDO>"
//                    + "<OFICIO>PRACTICAS</OFICIO>"
//                    + "<DIR>7839</DIR>"
//                    + "<FECHA_ALT>1992-05-22</FECHA_ALT>"
//                    + "<SALARIO>1100</SALARIO>"
//                    + "<DEPT_NO>10</DEPT_NO>"
//                + "</EMP_ROW>"
//                + "into /EMPLEADOS");
        
        consulta("/EMPLEADOS/EMP_ROW[EMP_NO = 9999]/EMP_NO");
        
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
        
            
            con = server.getConnection();
        } catch (XQException ex) {
            Logger.getLogger(EjerciciosPractica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Conectado con exito \n");
    }
    
    
    /**
     * Te desconecta de la bbdd de exits
     */
    private static void desconecta(){
        
        try {
            con.close();
        } catch (XQException ex) {
            Logger.getLogger(EjerciciosPractica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\n Desconectado con exito");
    }
    
    
    /**
     * Realiza una consulta y te muestra los datos por pantalla
     * @param textoConsulta Consulta que se va a realizar 
     * 
     */
    private static void consulta(String textoConsulta){
        
        try {
            XQPreparedExpression consulta;
            XQResultSequence resultado;
            
            consulta = con.prepareExpression(textoConsulta);
            resultado = consulta.executeQuery();
            
            XQResultItem r_item;
            
            while(resultado.next()){
                r_item = (XQResultItem) resultado.getItem();
                System.out.println(r_item.getItemAsString(null));
            }
        } catch (XQException ex) {
            Logger.getLogger(EjerciciosPractica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Realiza una modificacion en la bbdd como INSERT, UPDATE, DELETE
     * @param textoDML sentencia de modificacion
     */
    private static void modificacion(String textoDML){
        
        try {
            
            
            XQExpression expresion;
            
            expresion = con.createExpression();
            
            expresion.executeCommand(textoDML);
            
            
        } catch (XQException ex) {
            Logger.getLogger(EjerciciosPractica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Modificacion realizada con exito");
    }
}
