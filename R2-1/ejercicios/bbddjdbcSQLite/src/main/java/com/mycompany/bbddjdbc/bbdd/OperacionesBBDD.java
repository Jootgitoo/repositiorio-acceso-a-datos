/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bbddjdbc.bbdd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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


//executeUpdate --> Para ejecutar sentencias que modifican los datos de las tablas
//  Insert, update, delete

//executeQuery --> Para ejecutar sentencias que recuperan datos de las tablas
// Select



public class OperacionesBBDD {
    
    //Atributos
    
    private final String driver;
    private final String urlconection;
    
    private static OperacionesBBDD operacionesBBDD = null; //Variable para crear el patron Singleton
    
    private Connection conexion;
    private PreparedStatement preparedStatement;
    
    private static DatabaseMetaData dbmd;
    
//------------------------------------------------------------------------------    
    //Constructor
    
    private OperacionesBBDD(){
        driver = "org.sqlite.JDBC";
        urlconection="jdbc:sqlite:./bbdd/bbdd-ejercicios.db";
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
    
    /***************************************************************************
     *  EJECUCIÓN DE SENTENCIAS DE MANIPULACIÓN DE DATOS
     **************************************************************************/
    
    /**
     * Ejecuta un Insert con los parámetros indicados
     * 
     * @param insertSQL Insert a ejecutar
     * @param params    Parámetros de la instrucción Insert. Valor que se va a colocar en cada ?
     * @return  Devolverá la Key en caso de que el campo de la clave primaria sea autoincremental
     * @throws SQLException Valor ducplicado o no se ha podido realizar la operación
     */
    public Optional<ResultSet> insert(String insertSQL, Object... params) throws SQLException{ //Estamos creando una especie de Array de objetos
        
        //preparedStatement preparamos la conexion
        preparedStatement = conexion.prepareStatement(insertSQL, //Sentencia sql de tipo insert que queremos ejecutar
                PreparedStatement.RETURN_GENERATED_KEYS); //Indicamos que queremos que devuelva las claves generadas ????
        
        //"insert into Departamentos values (?,?,?)"
        //                                   1,2,3
        //params = [1, "Informatica", "Ciudad Real"]
        //          0,      1,             2
        
        //Asignamos los parametros, es decir coloca en cada ? un valor del array 
        for(int i=0; i<params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        //Ejecutamos la sentencia SQL
        preparedStatement.executeUpdate();
        
        //Devolvemos las claves generadas
        return Optional.of(preparedStatement.getGeneratedKeys());
    }
    
    
    /**
     * Realiza una consulta a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL Consulta SQL de tipo select
     * @param params   Parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    private ResultSet executeQuery(String querySQL, Object... params) throws SQLException{
        preparedStatement = conexion.prepareStatement(querySQL);
        
        for(int i=0; i<params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        return preparedStatement.executeQuery();
    }
    
    
    /**
     * Realiza una consulta select a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL Consulta SQL de tipo select
     * @param params   Parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    public Optional<ResultSet> select(String querySQL, Object... params) throws SQLException{
        
        return Optional.of(executeQuery(querySQL, params));

    }
    
    
    /**
     * Realiza una operación de tipo update, es decir que modifca los datos o los elimina
     *
     * @param genericSQL consulta SQL de tipo update, delete, etc. que modifica los datos
     * @param params     parámetros de la consulta parametrizada
     * @return número de registros afectados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    private int updateQuery(String genericSQL, Object... params) throws SQLException{
        preparedStatement = conexion.prepareStatement(genericSQL);
        
        for(int i=0; i<params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        return preparedStatement.executeUpdate();
    }
    
    
    /**
     * Realiza un update
     *
     * @param updateSQL Operación SQL de tipo update
     * @param params    Parámetros de la instrucción
     * @return Número de registros actualizados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    public int update(String genericSQL, Object... params) throws SQLException{
        return updateQuery(genericSQL, params);
    }
    
    
    /**
     * Realiza un delete
     *
     * @param deleteSQL Operación SQL de tipo delete
     * @param params    Parámetros de la instrucción
     * @return Número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    public int delete(String genericSQL, Object... params) throws SQLException{
        return updateQuery(genericSQL, params);
    }
    
    
     /**************************************************************************
     * SENTENCIAS DE DESCRIPCIÓN
     **************************************************************************/
    
    /**
     * Muestra información sobre la conexión a la bbdd
     */
    public void obtenerInformacionDeConexion() {
                
        try {
            //Nombre del SGBD
            String nombre = dbmd.getDatabaseProductName();
            
            //Driver utilizado:
            String driver = dbmd.getDriverName();
            
            //Dirección para acceder a la bbdd:
            String url = dbmd.getURL();
            
            //Nombre del usuario:
            String usuario = dbmd.getUserName();
            
            System.out.println("Nombre del SGBD:" + nombre);
            System.out.println("Driver:" + driver);
            System.out.println("Url:" + url);
            System.out.println("Usuario:" + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Muestra información de las tablas del usuario conectado a la bbdd
     */
    public void obtenerInformacionDeLasTablas() {
        try {
            ResultSet rs;
            String[] tipos = {"TABLE"};
            
            rs = dbmd.getTables(null, null, null, tipos);
            
            String nombre_usuario;
            String nombre_tabla;
            
            while (rs.next()){
                nombre_usuario = rs.getString("TABLE_SCHEM");
                nombre_tabla = rs.getString("TABLE_NAME");
                
                System.out.println("USUARIO:" +nombre_usuario+ " TABLA:" + nombre_tabla);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muestra información sobre las columnas de una tabla
     * 
     * @param nombreTabla Nombre de la tabla de la cual queremos obtener información de sus columnas
     */
    public void obtenerInformacionDeLasColumnas(String nombreTabla) {
        try {
            ResultSet rs;
            
            rs = dbmd.getColumns(null, null, nombreTabla.toUpperCase(), null);
            
            String nombre_tabla;
            String nombre_columna;
            
            while (rs.next()){
                nombre_tabla = rs.getString("TABLE_NAME");
                nombre_columna = rs.getString("COLUMN_NAME");
                
                System.out.println("TABLA:" +nombre_tabla+ " COLUMN:" + nombre_columna);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obtiene información sobre el ResultSet
     * 
     * @param rs ResultSet sobre el cual queremos obtener información
     */
    public void obtenerInformacionDelResultSet(Optional<ResultSet> rs) {
        
        try {
            
            ResultSetMetaData rsmd = rs.get().getMetaData();
            
            //Obtiene el número de columnas devueltas por la tabla
            int numColumnas = rsmd.getColumnCount();
            
            //Obtiene el nombre de la columna de la posición "i"
            String nombre_columna = rsmd.getColumnName(2);
            
            //Obtiene el tipo de datos de la columna de la posición "i"
            String tipo_columna = rsmd.getColumnTypeName(2);
            
            //Obtiene "0" si la columna de la posición "i" puede contener valores nulos
            int valores_nulos = rsmd.isNullable(2);
            
            //Obtiene el máximo número de caracteres de la columna de la posición "i"
            int tamaño_columna = rsmd.getColumnDisplaySize(2);
            
            System.out.println("Numero de columnas devueltas:" + numColumnas);
            System.out.println("Nombre de la columna 2:" + nombre_columna);
            System.out.println("Tipo de la columna 2:" + tipo_columna);
            System.out.println("Tamaño de la columna 2:" + tamaño_columna);
            System.out.println("Acepta nulos:" + ((valores_nulos==1)?"Si":"No"));
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

}
