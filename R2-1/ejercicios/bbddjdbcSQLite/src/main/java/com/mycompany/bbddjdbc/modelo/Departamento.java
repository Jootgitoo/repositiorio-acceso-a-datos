/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bbddjdbc.modelo;

import com.mycompany.bbddjdbc.bbdd.OperacionesBBDD;
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
     * Método para insertar un departamento
     * @param bbdd que en la que vamos a insertar el dep
     * @throws SQLException 
     */
    public void insertar(OperacionesBBDD bbdd) throws SQLException{
        bbdd.insert("insert into Departamentos values (?,?,?)", this.dept_no, this.dnombre, this.loc);
    }
    
    
    /**
     * Selecciona un registro filtrando por la clave primaria
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     * @param dept_no Número del departamento del cual queremos seleccionar la información 
     */
    public void selectById(OperacionesBBDD bbdd, int n){

        //Guardamos el resultado de la consulta
        Optional<ResultSet> rs;
        try {
            rs = bbdd.select( "SELECT * FROM departamentos WHERE dept_no = ?", n );
            
            //.isPresent --> Compruebo si tiene algo o no. True si tiene un valor presente, False si no tiene nada
        if (rs.isPresent()){
            
                while(rs.get().next()){
                    this.dept_no = (rs.get().getInt("dept_no"));
                    this.dnombre = (rs.get().getString("dnombre"));
                    this.loc = (rs.get().getString("loc"));
                }  
            }    
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    
    /**
     * Selecciona todos los registros de la tabla
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     * @return Registros devueltos
     */
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        
        //Guardamos el resultado de la consulta
        Optional<ResultSet> rs = null;
        
        try {
            rs = bbdd.select("SELECT * FROM departamentos");
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    /**
     * Muestra el contenido del ResultSet
     * @param rs ResultSet del que vamos a extraer el contenido
     */
    public static void mostrarResultSet(Optional<ResultSet> rs){
        try{
            
            if(rs.isPresent()){
                while(rs.get().next()){
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
     * Modifica un departamento
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     */
    public void update(OperacionesBBDD bbdd){
        try {
            bbdd.update("UPDATE departamentos SET dnombre = ?, loc = ? WHERE dept_no = ? ", this.dnombre, this.loc, this.dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Elimina un departamento
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     * @param dept_no Número del departamento que queremos eliminar
     */
    public static void delete(OperacionesBBDD bbdd, int n_dep){
        try {
            bbdd.delete("DELETE FROM departamentos WHERE dept_no = ? ", n_dep);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
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
