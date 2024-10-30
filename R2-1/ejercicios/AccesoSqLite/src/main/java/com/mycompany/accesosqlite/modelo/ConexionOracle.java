/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.accesosqlite.modelo;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 30 oct 2024
 */
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionOracle {
    
    Connection conexion = null;
    
    public void Conectar(){
        
        String driver = "Oracle.jdbc.driver.OracleDriver";
        String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";
        
        
        Properties propiedades = new Properties();
        propiedades.setProperty("user", "dam2");
        propiedades.setProperty("password", "dam2");

        
        try {
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection);

        }catch (ClassNotFoundException ex){
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cerrarConexion(){
        
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

}
