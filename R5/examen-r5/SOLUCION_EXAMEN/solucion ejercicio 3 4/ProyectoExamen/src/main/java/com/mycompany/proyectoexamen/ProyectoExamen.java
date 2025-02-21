/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoexamen;

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
public class ProyectoExamen {
    
    private static XQDataSource server;
    private static XQConnection connection;

    public static void main(String[] args) {
        conecta();
        
        //Ejercicio 3
        //mostrarClientes();
        
        //Ejercicio 4
        //eliminarProductos();
        
        desconecta();
    }
    
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
            Logger.getLogger(ProyectoExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Conectado con exito \n");
    }
    
    private static void desconecta(){
        
        try {
            connection.close();
        } catch (XQException ex) {
            Logger.getLogger(ProyectoExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\n Desconectado con exito");
    }
    
    
    
    public static void mostrarClientes(){
        
        String textoConsulta = "/clientes/clien";
        
        try {
            XQPreparedExpression consulta;
            XQResultSequence resultado;
            
            consulta = connection.prepareExpression(textoConsulta);
            resultado = consulta.executeQuery();
            
            XQResultItem r_item;
            
            while(resultado.next()){
                r_item = (XQResultItem) resultado.getItem();
                System.out.println(r_item.getItemAsString(null));
            }
        } catch (XQException ex) {
            Logger.getLogger(ProyectoExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private static void eliminarProductos(){
        
        String consulta = "update delete /productos/product[@categoria = 'A' and @pvp < 200]";
        
        try {
            
            
            XQExpression expresion;
            
            expresion = connection.createExpression();
            
            expresion.executeCommand(consulta);
            
            
        } catch (XQException ex) {
            Logger.getLogger(ProyectoExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Modificacion realizada con exito");
    }
    
}
