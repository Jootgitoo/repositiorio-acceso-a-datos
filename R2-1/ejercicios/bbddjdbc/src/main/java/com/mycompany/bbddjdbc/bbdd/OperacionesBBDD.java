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
    
    private final String driver;
    private final String urlconection;
    
    private static OperacionesBBDD operacionesBBDD; //Variable para crear el patron Singleton
    
    private Connection conexion;
    private PreparedStatement preparedStatement;
    
//------------------------------------------------------------------------------    
    //Constructor
    
    private OperacionesBBDD(){
        driver = "org.sqlite.JDBC";
        urlconection="jdbc:sqlite:./bbdd/ejemplo.db";
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
            
            Class.forName(driver); //carga dinámicamente la clase del driver de la base de datos. driver es una variable que debe contener el nombre completo de la clase del driver
            this.conexion = DriverManager.getConnection(urlconection); //se establece la conexión a la base de datos.
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
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

}
