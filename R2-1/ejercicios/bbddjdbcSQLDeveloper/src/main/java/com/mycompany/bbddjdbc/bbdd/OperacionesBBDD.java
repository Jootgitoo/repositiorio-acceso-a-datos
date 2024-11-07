/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bbddjdbc.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 4 nov 2024
 */
public class OperacionesBBDD {
    
    //Atributos
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";
    
    private static OperacionesBBDD operacionesBBDD; //Variable para crear el patron Singleton
    
    private Connection conexion;
    private PreparedStatement preparedStatement;
    private Properties propiedades;
    
//------------------------------------------------------------------------------    
    //Constructor
    
    private OperacionesBBDD(){
        
    }
    
//------------------------------------------------------------------------------    
    //MÉTODOS
    
    /**
     * Para seguir el patron Singleton
     * @return 
     */
    public static OperacionesBBDD getInstance(){
        if (operacionesBBDD == null){
            operacionesBBDD = new OperacionesBBDD();
        }
        return operacionesBBDD;
    }
    
    /**
     * Abres la conexión a la BBDD
     */
    public void abrirConexion(){
        try {
            this.propiedades = new Properties();
            this.propiedades.setProperty("user", "dam2");
            this.propiedades.setProperty("password", "dam2");
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Cierras la conexión a la BBDD
     */
    public void cerrarConexion(){
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * <ResultSet> el resulto de haber ejecutado una opcion
     * @param insertSQL sentencia sql qu em la van a pasar por parametro
     * @return
     * @throws SQLException 
     */
    public Optional<ResultSet> insert(String insertSQL, Object... params) throws SQLException{ //Estamos creando una especie de Array de objetos
        
        preparedStatement = conexion.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        
        //"insert into Departamentos values (?,?,?)"
        //                                   1,2,3
        //params = [1, "Informatica", "Ciudad Real"]
        //          0,      1,             2
        
        for(int i=0; i<params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        preparedStatement.executeUpdate();
        
        return Optional.of(preparedStatement.getGeneratedKeys());
    }
    
    private ResultSet executeQuery(String querySQL, Object... params) throws SQLException{
        preparedStatement = conexion.prepareStatement(querySQL);
        
        for(int i=0; i<params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        return preparedStatement.executeQuery();
    }
    
    public Optional<ResultSet> select(String querySQL, Object... params) throws SQLException{
        
        return Optional.of(executeQuery(querySQL, params));

    }
    
    //Sirve para ejecutar el update y el delete
    private int updateQuery(String genericSQL, Object... params) throws SQLException{
        preparedStatement = conexion.prepareStatement(genericSQL);
        
        for(int i=0; i<params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        return preparedStatement.executeUpdate();
    }
    
    public int update(String genericSQL, Object... params) throws SQLException{
        return updateQuery(genericSQL, params);
    }
    
    public int delete(String genericSQL, Object... params) throws SQLException{
        return updateQuery(genericSQL, params);
    }

}
