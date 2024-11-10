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
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/Free";
    
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
            // Este objeto se utilizará para almacenar las propiedades de conexión a la base de datos, como el nombre de usuario y la contraseña.
            this.propiedades = new Properties();
            
            //a propiedad "user" se establece con el valor "dam2" (que representa el nombre de usuario para la conexión a la base de datos).
            this.propiedades.setProperty("user", "dam2");
            
            //La propiedad "password" se establece con el valor "dam2" (que representa la contraseña para la conexión a la base de datos).
            this.propiedades.setProperty("password", "dam2");
            
            //Cargamos el driver
            Class.forName(driver);
            
            //Ejecuta la conexion con la bbdd
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
     * El método insert se utiliza para insertar un nuevo registro en la bbdd
     * utilizando una consulta SQL preparada. 
     * Acepta una consulta SQL y un número variable de parámetros que se asignan
     * a los marcadores de posición en la consulta. Después de ejecutar la inserción,
     * devuelve un Optional<ResultSet> que puede contener las claves generadas 
     * (como el ID del nuevo registro). Esto es útil para operaciones donde se 
     * necesita saber el identificador del registro recién insertado.
     * 
     * 
     * <ResultSet> guarda el resultado de haber ejecutado una opcion
     * @param insertSQL una cadena que contiene la consulta SQL de inserción
     * @param params parametros que se van a utilizar en la sentencia sql
     * @return Devolveremos el resultado de la sentencia sql
     * @throws SQLException 
     */
    public Optional<ResultSet> insert(String insertSQL, Object... params) throws SQLException{ //Estamos creando una especie de Array de objetos
        
        //Se pasa la consulta SQL 'insertSQL' y se indica que se desea devolver las claves generadas
        preparedStatement = conexion.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        
        //insertSQL: "insert into Departamentos values (?,?,?)"
        //                                              1,2,3
        //params:  [1, "Informatica", "Ciudad Real"]
        //          0,      1,             2
        
        //Recorremos el array de parametros
        for(int i=0; i<params.length; i++){
            //Con esto asignamos en la ? = i+1 (por q empieza en 1) el valor q haya en el array de params en la pos 0
            preparedStatement.setObject(i+1, params[i]);
        }
        
        //Ejecutamos la consulta 
        preparedStatement.executeUpdate();
        
        //Devuelve un Optional que contiene el ResultSet de las claves generadas
        return Optional.of(preparedStatement.getGeneratedKeys());
    }
    
    
    /**
     * 
     * @param querySQL consulta sql que se desea ejecutar
     * @param params parametros que se van a utilizar en la sentencia sql
     * @return
     * @throws SQLException 
     */
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
    
    public Connection getConexion(){
        return conexion;
    }
    

}
