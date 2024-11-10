/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bbddjdbc.bbdd;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 5 nov 2024
 */
public class Empleado {

//ESTOS MÉTODOS ESTÁN EXPLICADOS EN LA CLASE DEPARTAMENTO    
    
    //ATRIBUTOS
    private int emp_no;
    private String apellido;
    private String oficio;
    private int dir;
    private Date fecha_alt;
    private double salario;
    private double comision;
    private int dept_no;

//------------------------------------------------------------------------------
    //CONSTRUCTORES
    
    public Empleado(int emp_no, String apellido, String oficio, int dir, Date fecha_alt, double salario, double comision, int dept_no){
        
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dept_no = dept_no;
    }
    
    public Empleado(){
        
    }

//------------------------------------------------------------------------------
    //MÉTODOS
    
    public void insertarEmpleado(OperacionesBBDD bbdd) throws SQLException{
        bbdd.insert("insert into EMPLEADOS values (?,?,?,?,?,?,?,?)", this.emp_no,  this.apellido, this.oficio, this.dir,  this.fecha_alt,  this.salario, this.comision, this.dept_no );
    }
    
    public void selectById(OperacionesBBDD bbdd, int n){

        //Guardamos el resultado de la consulta
        Optional<ResultSet> rs;
        try {
            rs = bbdd.select( "SELECT * FROM empleados WHERE emp_no = ?", n );
            
            //.isPresent --> Compruebo si tiene algo o no. True si tiene un valor presente, False si no tiene nada
        if (rs.isPresent()){
            
                while(rs.get().next()){
                    
                    this.emp_no = (rs.get().getInt("emp_no"));
                    this.apellido = (rs.get().getString("apellido"));
                    this.oficio = (rs.get().getString("oficio"));
                    this.dir = (rs.get().getInt("dir"));
                    this.fecha_alt = (rs.get().getDate("fecha_alt"));
                    this.salario = (rs.get().getDouble("salario"));
                    this.comision = (rs.get().getDouble("comision"));
                    this.dept_no = (rs.get().getInt("dept_no"));
                    
                }  
            }    
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        
        //Guardamos el resultado de la consulta
        Optional<ResultSet> rs = null;
        
        try {
            rs = bbdd.select("SELECT * FROM empleados");
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public static void mostrarResultSet(Optional<ResultSet> rs){
        try{
            
            if(rs.isPresent()){
                while(rs.get().next()){
                    System.out.print("Numero empleado: " + rs.get().getInt("emp_no"));
                    System.out.print(", Apellido: " + rs.get().getString("apellido"));
                    System.out.print(", Oficio: " + rs.get().getString("oficio"));
                    System.out.println(", dir: " + rs.get().getInt("dir"));
                    System.out.println(", fecha alta: " + rs.get().getDate("fecha_alt"));
                    System.out.println(", salario: " + rs.get().getDouble("salario"));
                    System.out.println(", comision " + rs.get().getDouble("comision"));
                    System.out.println(", numero departamento " + rs.get().getInt("dept_no"));
                    System.out.println("");
                }
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(OperacionesBBDD bbdd){
        try {
            bbdd.update("UPDATE empleados SET apellido = ?, oficio = ?, dir = ?, fecha_alt = ?, salario = ?, comision = ?, dept_no =  WHERE emp_no = ? ", this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.emp_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void delete(OperacionesBBDD bbdd, int emp_no){
        try {
            bbdd.delete("DELETE FROM empleados WHERE emp_no = ? ", emp_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//------------------------------------------------------------------------------    
    //MÉTODOS EXTRA --> GETTERS, SETTERS, ToString...

    @Override
    public String toString(){
        return "Empleado{Numero emple: " +this.emp_no + ", apellido: " + this.apellido + ", oficio: " +this.oficio + ", dir: " +this.dir + ", fecha de alta " +this.fecha_alt+ ", salario: " +this.salario+ ", comision:" +this.comision + ", numero de departamento: " +this.dept_no;
    }
    
    public int getEmp_no() {
        return emp_no;
    }

    public String getApellido() {
        return apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public int getDir() {
        return dir;
    }

    public Date getFecha_alt() {
        return fecha_alt;
    }

    public double getSalario() {
        return salario;
    }

    public double getComision() {
        return comision;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setFecha_alt(Date fecha_alt) {
        this.fecha_alt = fecha_alt;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }
    
    
    
    
}
