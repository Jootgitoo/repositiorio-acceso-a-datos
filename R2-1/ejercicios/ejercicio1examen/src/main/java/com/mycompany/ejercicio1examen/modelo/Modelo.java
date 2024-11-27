/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejercicio1examen.modelo;

import com.mycompany.ejercicio1examen.modelo.Dron;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 27 nov 2024
 */
public class Modelo {
    
     //Atributos
    private final String driver;
    private final String urlconnection;
    private Properties propiedades = null;

    private Connection conexion;
    private PreparedStatement preparedStatement;
    
    //DatabaseMetaData es una interfaz de java que proporciona metodos para obtemer datos sobre la conexión a la BBDD
    private static DatabaseMetaData dbmd;
    
    private static Modelo modelobbdd = null; //Variable para crear el patron Singleton
    
    
//------------------------------------------------------------------------------    
    //Constructor
    
    private Modelo(){
        driver = "oracle.jdbc.driver.OracleDriver";
        urlconnection = "jdbc:oracle:thin:@localhost:1521/Free";
        
    }
    
//------------------------------------------------------------------------------    
    
    //MÉTODOS
    //EJERCICIO 1
    public void insertarDron(Dron dronInsert){
        
        try {
            modelobbdd.utilidadInsert("insert into DRONES values (?,?,?,?,?,?,?,?,?,?,?)", dronInsert.getnSerie(), dronInsert.getMarca(), dronInsert.getModelo(), dronInsert.getPeso(), dronInsert.getPotencia(), dronInsert.geteCinetica(), dronInsert.getAseguradora(), dronInsert.getHoras(), dronInsert.getAutonomia(), dronInsert.getAdquisicion(), dronInsert.getDni());
             
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Codigo de error: " +ex.getErrorCode());
            
            switch(ex.getErrorCode()){
                case 1: //Controlamos que el emp_no no exista
                    System.out.println("El dron ya existe");
                    break;
                case 2291:
                    System.out.println("El operador no existe");
                    break;
                default : 
                    System.out.println("Codigo de error desconocido: " +ex.getErrorCode());
            }
        }
    }
    
    public Optional<ResultSet> utilidadInsert(String insertSQL, Object... params) throws SQLException{ //Estamos creando una especie de Array de objetos
        
        //Se pasa la consulta SQL 'insertSQL' y se indica que se desea devolver las claves generadas
        preparedStatement = conexion.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        
        
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
    
    //--------------------------------------------------------------------------------------------------------------------------
    //EJERCICIO 2
    public void eliminaFilasDronesObsoletos(Date p_fechaAdquisicion, double p_horas){
        try {
            String sentenciaSQL = "DELETE FROM Drones WHERE Adquisicion < ? AND Horas > ?";
            
            modelobbdd.utilidadDelete(sentenciaSQL, p_fechaAdquisicion, p_horas);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int utilidadDelete(String genericSQL, Object... params) throws SQLException{
        return utilidadUpdateQuery(genericSQL, params);
    }
    
    private int utilidadUpdateQuery(String genericSQL, Object... params) throws SQLException{
        preparedStatement = conexion.prepareStatement(genericSQL);
        
        for(int i=0; i<params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        
        return preparedStatement.executeUpdate();
    }
    //------------------------------------------------------------------------------
    //EJERCICIO 3
    public void mostrarFilaDatosDrones(String p_nSerie){
        //Guardamos el resultado de la consulta
        Optional<ResultSet> rs;
        try {
            String selectSQL = "SELECT d.nSerie, d.horas, d.autonomia, d.adquisicion, d.dni, o.nombre, o.correo, o.telefono FROM drones d, operadores o WHERE nserie = ?";
            rs = modelobbdd.utilidadSelect(selectSQL, p_nSerie);
            
            
            if (rs.get().last()){
                rs.get().beforeFirst();
                
                while(rs.get().next()){

                    System.out.print("[ Numero serie: " +rs.get().getString("nSerie"));
                    System.out.print(", Horas: " +rs.get().getDouble("horas"));
                    System.out.print(", Autonomia: " +rs.get().getDouble("autonomia"));
                    System.out.print(", Fecha adquisicion: " +rs.get().getDate("adquisicion"));
                    System.out.print(", DNI: " +rs.get().getString("dni"));
                    System.out.print(", Correo: " +rs.get().getString("correo"));
                    System.out.print(", Telefono: " +rs.get().getInt("telefono") + " ]");
                    System.out.println("");
                }  
            } else {
                System.out.println("El numero de serie no existe");
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    public Optional<ResultSet> utilidadSelect(String querySQL, Object... params) throws SQLException {
        return Optional.of(utilidadExecuteQuery(querySQL, params));
    }
    private ResultSet utilidadExecuteQuery(String querySQL, Object... params) throws SQLException {
        
        // No permitiría el Scroll ni la actualización sobre el ResultSet
        //preparedStatement = conexion.prepareStatement(querySQL);
        
        //Creamos preparedStatement para que el ResultSet sea sensible al desplazamiento y actualizable.
        preparedStatement = conexion.prepareStatement(querySQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        
        return preparedStatement.executeQuery(); 
    }
    //------------------------------------------------------------------------------
    //EJERCICIO 4
    public void mostrarTodosLosDatos(Date p_fecha_inicio_adquisicion, Date p_fecha_fin_adquisicion){
        
        Optional<ResultSet> rs = null;
        
        try {
            
            String sentenciaSql = "SELECT d.* FROM drones d WHERE adquisicion > ? AND adquisicion < ?";
            
            rs = modelobbdd.utilidadSelect(sentenciaSql, p_fecha_inicio_adquisicion, p_fecha_fin_adquisicion);
            
            
            if (rs.get().last() ){ //Si el rs está vacio last devuelve false sino true
                rs.get().beforeFirst();
                
                while(rs.get().next()){
                    System.out.print("[ Numero serie: " +rs.get().getString("nSerie"));
                    System.out.print(", Marca: " +rs.get().getString("marca"));
                    System.out.print(", Modelo: " +rs.get().getString("modelo"));
                    System.out.print(", Peso: " +rs.get().getDouble("peso"));
                    System.out.print(", Potencia: " +rs.get().getInt("potencia"));
                    System.out.print(", Energia cinetica: " +rs.get().getInt("ecinetica"));
                    System.out.print(", Aseguradora: " +rs.get().getString("aseguradora"));
                    System.out.print(", Horas: " +rs.get().getDouble("horas"));
                    System.out.print(", Autonomia: " +rs.get().getDouble("autonomia"));
                    System.out.print(", Fecha adquisicion: " +rs.get().getDate("adquisicion"));
                    System.out.print(", DNI: " +rs.get().getString("dni") + " ]");
                   
                    System.out.println(""); 
                }
            } 
            
        }catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Date convertirFecha(String fecha){
        java.util.Date fechaUtil = null;
        try {
            SimpleDateFormat s = new SimpleDateFormat("DD/MM/YYYY");    
            fechaUtil = s.parse(fecha);
            
        } catch (ParseException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.sql.Date(fechaUtil.getTime());
    }
    
    //------------------------------------------------------------------------------
    //EJERCICIO 5
    public void cursoRenovacion(Date caducidad){
                
        try {
            modelobbdd.utilidadUpdate("UPDATE operadoresrenovacion SET caducidad = ?", caducidad);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int utilidadUpdate(String genericSQL, Object... params) throws SQLException{
        return utilidadUpdateQuery(genericSQL, params);
    }
//------------------------------------------------------------------------------    
    //MÉTODOS EXTRA
    public void abrirConexion(){
        try {
            // Este objeto se utilizará para almacenar las propiedades de conexión a la base de datos, como el nombre de usuario y la contraseña.
            this.propiedades = new Properties();
            
            //a propiedad "user" se establece con el valor "dam2" (que representa el nombre de usuario para la conexión a la base de datos).
            this.propiedades.setProperty("user", "examen");
            
            //La propiedad "password" se establece con el valor "dam2" (que representa la contraseña para la conexión a la base de datos).
            this.propiedades.setProperty("password", "examen");
            
            //La propiedad "bbdd" se establece con el valor free (que representa el SID de la BBDD)
            this.propiedades.setProperty("bbdd", "free");
            
            //Cargamos el driver
            Class.forName(driver);
            
            //Ejecuta la conexion con la bbdd
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            
            dbmd = conexion.getMetaData();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Cierras la conexión a la BBDD
     */
    public void cerrarConexion(){
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Devuelve una instancia de la clase. Sólo una. Patrón Singleton
     * 
     * @return La instancia de la clase
     */
    public static Modelo getInstance(){
        if (modelobbdd == null){
            modelobbdd = new Modelo();
        }
        return modelobbdd;
    }
}
