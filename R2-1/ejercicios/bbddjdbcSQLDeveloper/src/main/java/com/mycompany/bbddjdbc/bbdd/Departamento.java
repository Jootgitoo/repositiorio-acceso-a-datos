/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bbddjdbc.bbdd;

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
 * version 1.0
 * Created on 4 nov 2024
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
    
    
    /**
     * Este procedimiento pide por parametro el numero de un departamento
     * de este departamento me va ha sacar el nombre
     * 
     * 
     * @param bbdd bbdd que contiene el procedimiento que vamos a utilizar
     * @return  devuelve la salida del procedimiento
     */
    public String ejecutarProcedimientoDepartamento(OperacionesBBDD bbdd, int dep){
        
        String salida_return = "";
        
        try {
            //Sentencia para llamar al procedimiento
            String sql = "{call p_nombre_depart (?,?)}";
            //                               input,output
            
            //Preparamos la llamada al procedimiento
            CallableStatement llamada = bbdd.getConexion().prepareCall(sql);
            
            //PARAMETROS DE ENTRADA
            //en la primera (1) ? le añadimos la informacion del departamento que queremos que busque en la bbdd  
            llamada.setInt(1, dep);
            
            //PARAMETROS DE SALIDA
            //en la 2ª ? nos va ha devolver un VARCHAR que va a contener el nombre del departamento cuyo dep = al pasado por parametro
            llamada.registerOutParameter(2, Types.VARCHAR);
            
            //Ejecutamos el procedimiento
            llamada.executeUpdate();
            
            //Guardar la salida
            salida_return = llamada.getString(2);
            
        } catch (SQLException e) {
//            System.out.println ("Ha ocurrido un error:");
//            System.out.println ("Mensaje: " +e.getMessage());
//            System.out.println ("SQL Estado: " +e.getSQLState());
//            System.out.println ("Código de error: " +e.getErrorCode());
//            System.out.println("");   

            switch (e.getErrorCode()){
                case (1403):
                    System.out.println("No existe el departamento con este id");
                    
            }
        }
        
        //Return de la salida del procedimiento
        return salida_return;
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
