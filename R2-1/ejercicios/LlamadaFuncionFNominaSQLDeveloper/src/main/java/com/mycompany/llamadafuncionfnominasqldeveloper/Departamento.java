/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.llamadafuncionfnominasqldeveloper;

import com.mycompany.llamadafuncionfnominasqldeveloper.bbdd.OperacionesBBDD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger; 

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 17 nov 2024
 */
public class Departamento {
    
    //ATRIBUTOS
    private int dept_no;
    private String dnombre;
    private String loc;
    
//------------------------------------------------------------------------------
    //CONSTRUCTORES
    
    public Departamento(int dept_no, String dnombre, String loc) {
        this.dept_no = dept_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }
    
    public Departamento(){
        
    }

//------------------------------------------------------------------------------
    //MÉTODOS

 /**************************************************************************
    * EJECUCIÓN DE SENTENCIAS DE MANIPULACIÓN DE DATOS
 **************************************************************************/ 
    
    /**
     * Método para inseertar un departamento en su tabla correspondiente
     * @param bbdd BBDD que se va ha utilizar
     * @throws SQLException 
     */
    public void insertarDepartamento(OperacionesBBDD bbdd) throws SQLException{
        //1. Sentencia sql
        //2. Tantas ? como valores
        //3. Valores que van a corresponder con las ?
        bbdd.insert("insert into Departamentos values (?,?,?)", this.dept_no, this.dnombre, this.loc);
        //                                             1 2 3        1              2            3
    }
    
    
    /**
     * Seleccionamos un departamento que se encuentre en una BBDD según su id
     * @param bbdd BBDD que se va a utilizar
     * @param n Id del departamento que quederemos buscar 
     */
    public void selectById(OperacionesBBDD bbdd, int n){

        //Guardamos el resultado de la consulta
        //ResultSet permite valores nulos
        Optional<ResultSet> rs;
        
        try {
            //Guardamos la salida de la consulta que le pasamos
            rs = bbdd.select( "SELECT * FROM departamentos WHERE dept_no = ?", n );
            
            //.isPresent --> Compruebo si tiene algo o no. True si tiene un valor presente, False si no tiene nada
        if (rs.isPresent()){ //Si la consulta devolvio algo != null...
            
                while(rs.get().next()){ //Recorremos el ResultSet
                    
                    //Extraemos los valores...
                    this.dept_no = (rs.get().getInt("dept_no")); 
                    this.dnombre = (rs.get().getString("dnombre"));
                    this.loc = (rs.get().getString("loc"));
                }  
            }    
        } catch (SQLException e) {

            System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +e.getMessage());
            System.out.println ("SQL Estado: " +e.getSQLState());
            System.out.println ("Código de error: " +e.getErrorCode());
            System.out.println("");
        }      
    }
    
    
    /**
     * Seleccionamos todos los departamentos de una bbdd
     * @param bbdd bbdd de la que vamos a extraer todos los departamentos
     * @return Devuelve un ResultSet con el contenido extraido de laa consulta
     */
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        
        //Guardamos el resultado de la consulta
        Optional<ResultSet> rs = null;
        
        try {
            rs = bbdd.select("SELECT * FROM departamentos"); //Consulta que vamos a realizar
        } catch (SQLException ex) {
           switch (ex.getErrorCode()){
               case 942:
                   System.out.println("No existe la tabla departamentos");
           }
        }
        return rs; //Resultado de la consulta
    }
    
    
    /**
     * Mostramos el resultado que hayamos obtenido de una consulta
     * @param rs El valor que te devuelve una consulta 
     */
    public static void mostrarResultSet(Optional<ResultSet> rs){
        try{
            
            if(rs.isPresent()){ //Si la consulta devolvio algo != null... 
                while(rs.get().next()){
                    
                    //Imprimimos por pantalla los valores
                    System.out.print("Numero departamento: " + rs.get().getInt("dept_no"));
                    System.out.print(", Nombre de departamento: " + rs.get().getString("dnombre"));
                    System.out.print(", Localidad: " + rs.get().getString("loc"));
                    System.out.println("");
                }
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Actualizar un departamento de la bbdd
     * @param bbdd bbdd donde vamos a cambiar el departamento
     */
    public void update(OperacionesBBDD bbdd){
        try {
            //Sentencia sql que se va ha ejecutar
            bbdd.update("UPDATE departamentos SET dnombre = ?, loc = ? WHERE dept_no = ? ", this.dnombre, this.loc, this.dept_no); 
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(ResultSet rs){
        try {
            rs.beforeFirst();
            //Updating the salary of each employee by 5000
            while(rs.next()) {
                //Retrieve by column name
                rs.updateString("dnombre", this.dnombre);
                rs.updateString("loc", this.loc);
                rs.updateRow();
            } } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Borrar un departamento de la Bbdd
     * @param bbdd base de datos que vamos a unar
     * @param n_dep numero de departamento que vamos a borrar
     */
    public static void delete(OperacionesBBDD bbdd, int n_dep){
        try {
            //Sentencia sql
            bbdd.delete("DELETE FROM departamentos WHERE dept_no = ? ", n_dep);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
/**************************************************************************
    * EJECUCIÓN DE PROCEDIMIENTOS Y FUNCIONES
**************************************************************************/
    
    /**
     * Llamada al procedimiento p_nombre_depart, almacenado en la bbdd 
     * 
     * @param bbdd Para realizar la conexión a la bbdd
     * @param dept_no Número del departamento que le pasaremos al procedimiento
     * @return  Nombre del departamento
     */
    public static String pNombreDepart(OperacionesBBDD bbdd, int dept_no) {
        CallableStatement llamada;
        String dnombre = null;
        try {     
            //Vamos a llamar a un procedimiento con la siguiente cabecera
            //PROCEDURE P_NOMBRE_DEPART(NDEPART NUMBER, NOMBRE_DEPART OUT VARCHAR2)
            //Preparamos el string para la llamada:
            String sql = "{call p_nombre_depart (?,?)}"; 
            
            //Creamos un objeto llamando al método prepareCall:
            llamada=bbdd.getConexion().prepareCall(sql);
            
            //Indicamos cuáles son los parámetros de entrada y cuales los de salida
            //Le damos valor al parámetro de entrada:
            llamada.setInt(1, dept_no);
            //Registramos el parámetro de salida de la función:
            llamada.registerOutParameter(2, Types.VARCHAR);
            
            //Realizamos la llamada al procedimiento:
            llamada.executeUpdate();
            
            //Obtenemos el valor del primer parámetro de salida
            dnombre = llamada.getString(2);
                     
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  dnombre;
    }
    
    /**
     * Llamada a la función f_nombre_depart, almacenada en la bbdd 
     * 
     * @param bbdd Para realizar la conexión a la bbdd
     * @param dept_no Número del departamento que le pasaremos a la función
     * @return  Nombre del departamento
     */
    public static String fNombreDepart(OperacionesBBDD bbdd, int dept_no) {
        CallableStatement llamada;
        String dnombre = null;
        try {     
            String sql = "{?=call f_nombre_depart (?)}";
            llamada=bbdd.getConexion().prepareCall(sql);
            llamada.setInt(2, dept_no);
            llamada.registerOutParameter(1, Types.VARCHAR);
            llamada.executeUpdate();
            dnombre = llamada.getString(1);
                     
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  dnombre;
    }
    
//------------------------------------------------------------------------------
    //MÉTODOS EXTRA --> GETTERS, SETTERS, ToString...
    
    @Override
    public String toString(){
        return "Departamento{Numero dept: " +this.dept_no + ", nombre departamento: " + this.dnombre + ", localidad: " +this.loc;
    }
    
    public int getDept_no() {
        return dept_no;
    }

    public String getDnombre() {
        return dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
