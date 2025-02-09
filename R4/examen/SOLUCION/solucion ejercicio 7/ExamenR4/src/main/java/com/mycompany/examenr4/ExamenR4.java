/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examenr4;

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
public class ExamenR4 {

    //ATRIBUTOS
    static String driver;
    static Connection conexion;

//--------------------------------------------------------------------------------------------------------------------
    //MÉTODOS
    public static void main(String[] args) {
        abrirConexion();

        //insertaVuelosEjemplo();
        //modificarPiloto(1, "22222222B", 50, 50);
        visualizarVuelo(1);
        
        cerrarConexion();
    }

    /**
     * Abre la conexion con la bbdd
     */
    private static void abrirConexion() {
        try {
            driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";
            
            Properties propiedades = new Properties();
            
            propiedades.setProperty("user", "dam2");
            propiedades.setProperty("password", "dam2");
            
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ExamenR4.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            conexion = DriverManager.getConnection(urlconnection, propiedades);
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamenR4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cierra la conexion con la bbdd
     */
    private static void cerrarConexion() {
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExamenR4.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    private static void insertaVuelosEjemplo() {
        
        try {
            String sql = "INSERT INTO t_vuelos_mercancias VALUES (?, ?, new Piloto(?, ?, ?), ?, ? )";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setInt(1, 1);
            sentencia.setString(2, "Iberia");
            sentencia.setString(3, "11111111A");
            sentencia.setInt(4, 5);
            sentencia.setInt(5, 500);
            sentencia.setInt(6, 500);
            sentencia.setInt(7, 40);
            
            sentencia.executeUpdate();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamenR4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void modificarPiloto(int idVuelo, String nuevoDni, int nuevasHorasVuelo, int nuevasHorasVueloAnual){
        
        try {
            
            String sql = "UPDATE t_vuelos_mercancias vMer SET vMer.piloto_avion.dni = ?, vMer.piloto_avion.horas_de_vuelo = ?, vMer.piloto_avion.horas_de_vuelo_anual = ? WHERE vMer.idVuelo = ?";
            
        
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setString(1, nuevoDni);
            sentencia.setInt(2, nuevasHorasVuelo);
            sentencia.setInt(3, nuevasHorasVueloAnual);
            sentencia.setInt(4, idVuelo);
            
            sentencia = conexion.prepareStatement(sql);
            
            sentencia.executeUpdate();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExamenR4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    private static void visualizarVuelo(int idVuelo){
        
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rset;
            
            rset = stmt.executeQuery("Select tvm.idvuelo, tvm.piloto_avion.dni from t_vuelos_mercancias tvm where idVuelo = " +idVuelo);
            
            while( rset.next() ){
                
               int idVueloExtraido = rset.getInt(1);
               String dni = rset.getString(2);
               
               
                System.out.println("Id vuelo: " +idVueloExtraido+ ", dni: " +dni+ ", coste transporte: ");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ExamenR4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}