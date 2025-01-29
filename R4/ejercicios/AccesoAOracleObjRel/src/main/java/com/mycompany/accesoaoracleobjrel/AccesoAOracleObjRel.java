/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.accesoaoracleobjrel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class AccesoAOracleObjRel {

    //ATRIBUTOS
    static String driver;
    static Connection conexion;
    
//--------------------------------------------------------------------------------------------------------------------
    //MÉTODOS
    
    public static void main(String[] args) {
        abrirConexion();
        
        //insertarPrepared();
        
        //realizaConsulta();
        
        //deletedPrepared();
        
        modifyPrepared();
        
        cerrarConexion();
    }
    
    
    /**
     * Abre la conexion con la bbdd
     */
    private static void abrirConexion(){
        try {
            driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";
            
            Properties propiedades = new Properties();
             
            propiedades.setProperty("user", "dam2");
            propiedades.setProperty("password", "dam2");

            
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AccesoAOracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
            }

            conexion = DriverManager.getConnection(urlconnection, propiedades);
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoAOracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Cierra la conexion con la bbdd
     */
    private static void cerrarConexion(){
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoAOracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    /**
     * Insertamos un alumno por defecto
     */
    private static void insertarPrepared(){
        
        try {
            String sql = "INSERT INTO alumnos VALUES (new Persona (?, ?, new Direccion(?,?,?), ?))";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            java.sql.Date fecha = Date.valueOf("2023-12-13");
            
            sentencia.setInt(1, 67);
            sentencia.setString(2, "Ana Gómez");
            sentencia.setString(3, "Calatrava");
            sentencia.setString(4, "Ciudad Real");
            sentencia.setInt(5, 13003);
            sentencia.setDate(6, fecha);
            
            sentencia.executeUpdate();
            sentencia.close();

                    
        } catch (SQLException ex) {
            Logger.getLogger(AccesoAOracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * Realizamos una consulta de un alumno
     */
    private static void realizaConsulta(){
        
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rset;
            
            rset = stmt.executeQuery("select codigo, nombre, direc from alumnos");
            
            while(rset.next()){
                String codigo = rset.getString(1);
                String nombre = rset.getString(2);

                java.sql.Struct objeto = (java.sql.Struct) rset.getObject(3);
                
                
                String ciudad = "";
                
                if(objeto != null){
                    Object[] atributos = objeto.getAttributes();
                    
                    ciudad = (String) atributos[1];
                }

                System.out.println("Código: " +codigo+ " Nombre: " +nombre+ " Direcion: " +ciudad);

            }
            rset.close();
            stmt.close();
                    
                 
        } catch (SQLException ex) {
            Logger.getLogger(AccesoAOracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /**
     * Modifica un alumno de la bbdd
     */
    private static void modifyPrepared(){
       
        try {
            String sql = "update alumnos set nombre = ? where codigo = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setString(1, "Jorgito Jorge");
            sentencia.setInt(2, 1);
            
            sentencia.executeUpdate();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoAOracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    /**
     * Borra un alumno de la bbdd con el codigo que le pases
     */
    private static void deletedPrepared(){
        
        try {
            String sql = "Delete from alumnos where codigo = ?";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            //CAMBIAR EL CODIGO ANTES DE EJECUTAR
            sentencia.setInt(1, 5);
            
            sentencia.executeUpdate();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoAOracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
