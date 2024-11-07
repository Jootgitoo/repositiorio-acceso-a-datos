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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionOracle {
    
    private final String driver = "oracle.jdbc.driver.OracleDriver";                // Driver
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";   // Cadena de conexión

    private Connection conexion = null;
    private Properties propiedades = null;
             
    /**
     * Establece la conexión a la BBDD Oracle
     */
    public void conexion(){
        try {
            this.propiedades = new Properties();
            this.propiedades.setProperty("user", "dam2");
            this.propiedades.setProperty("password", "dam2");
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Cierra la conexión a la BBDD Oracle
     */
    public void cierraConexion(){
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}