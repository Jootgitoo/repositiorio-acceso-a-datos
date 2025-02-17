/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.existdb;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ExistDb {

    //ATRIBUTOS
    
    //Variables para la conexión a la bbdd
    private static XQDataSource server;
    private static XQConnection con;
    
//------------------------------------------------------------------------------
    //MÉTODOS
    
    public static void main(String[] args) {
        conecta();
        
        //consulta("/EMPLEADOS");
        //modificacion("update rename /EMPLEADOS/fila_emple as 'EMP_ROW' ");        
        modificacion( "update insert "
                + "<empleado salario= '2340'>"
                    + "<puesto>Técnico</puesto>"
                    + "<nombre>Pedro Fraile</nombre>"
                + "</empleado>"
                + "into /universidad/departamento[codigo = 'MAT1']");
        
        
        //update value /universidad/departamento[/codigo = 'MAT1']/empleado/@salario with xs:integer(.) + 100
        
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
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\n Desconectado con exito");
    }
    
    
    /**
     * Realiza una consulta
     * @param textoConsulta Consulta que se va a realizar 
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
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Realiza una modificacion
     * @param textoDML sentencia de modificacion
     */
    private static void modificacion(String textoDML){
        
        try {
            
            
            XQExpression expresion;
            
            expresion = con.createExpression();
            
            expresion.executeCommand(textoDML);
            
            
        } catch (XQException ex) {
            Logger.getLogger(ExistDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
