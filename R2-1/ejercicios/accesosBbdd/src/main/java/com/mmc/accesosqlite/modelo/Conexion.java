/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.accesosqlite.modelo;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 30 oct 2024
 *
 */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    Connection conexion = null;
    
    public void conectar() {
        
        String driver = "org.sqlite.JDBC";
        String urlconnection = "jdbc:sqlite:./bbdd/ejemplo.db";
        
        try{
            
            Class.forName(driver);
            conexion = DriverManager.getConnection(urlconnection);
            
        } catch(ClassNotFoundException ex){
            
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cerrarConexion(){
        
        try {
            this.conexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    

}
