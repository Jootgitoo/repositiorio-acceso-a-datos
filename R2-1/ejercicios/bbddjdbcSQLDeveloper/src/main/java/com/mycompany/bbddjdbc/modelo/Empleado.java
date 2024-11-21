/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bbddjdbc.modelo;

import com.mycompany.bbddjdbc.bbdd.OperacionesBBDD;
import com.mycompany.bbddjdbc.modelo.Departamento;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**************************************************************************
        * EJECUCIÓN DE SENTENCIAS DE MANIPULACIÓN DE DATOS
    **************************************************************************/
    
    /**
     * Inserción de un empleado
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     */
    public void insertar(OperacionesBBDD bbdd){
        try {          
            bbdd.insert("insert into empleados values (?,?,?,?,?,?,?,?)" ,this.emp_no, this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no);
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }
    
    
    /**
     * Selecciona un registro filtrando por la clave primaria
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     * @param emp_no Número del empleado del cual queremos seleccionar la información 
     */
    public void selectById(OperacionesBBDD bbdd, int emp_no){

        //Guardamos el resultado de la consulta
        Optional<ResultSet> rs;
        try {
            rs = bbdd.select("SELECT * FROM empleados WHERE emp_no = ?", emp_no );
            
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
            rs = bbdd.select("SELECT * FROM empleados");
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    /**
     * Método que hace una select a la tabla Empleados que obtiene el apellido oficio y salario
     * @param bbdd bbdd donde vamos a hacer la select
     * @param ndep numeor del departamento que queremos que tenga los empleados que extraigamos 
     * @return 
     */
    public static void obtenerApellidoOficioSalario(OperacionesBBDD bbdd, int ndep){
        
        Optional<ResultSet> rs = null;
        
        try {
            
            String sentenciaSql = "SELECT apellido, oficio, salario from Empleados WHERE dept_no = "+ndep;
            
            rs = bbdd.select(sentenciaSql);
            
            if (rs.isPresent()){
                while(rs.get().next()){
                    System.out.print("Apellido: " +rs.get().getString("apellido"));
                    System.out.print(", Oficio: " + rs.get().getString("oficio"));
                    System.out.print(", Salario: " + rs.get().getDouble("salario"));
                    System.out.println("");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * Método que hace una select extrayendo el apellido y el salario de la tabla
     * empleados y el nombre de departamento de la tabla departamento de la
     * persona que tenga el salario maximo
     * @param bbdd BBDD donde vamos a ejecutar la sentencia
     */
    public static void empleadoMaxSalario(OperacionesBBDD bbdd){
        
        Optional<ResultSet> rs = null;

        try {
            
            String sentenciaSql = "SELECT e.apellido, e.salario, d.dnombre FROM empleados e, departamentos d WHERE e.dept_no = d.dept_no AND e.salario = (SELECT MAX(salario) FROM empleados)";
            
            rs = bbdd.select(sentenciaSql);
            
            if (rs.isPresent()){
                while(rs.get().next()){
                    System.out.print("Apellido: " +rs.get().getString("apellido"));
                    System.out.print(", Salario: " + rs.get().getDouble("salario"));
                    System.out.print(", Nombre departamento: " +rs.get().getString("dnombre"));
                    System.out.println("");
                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Método que actualiza en +100 el salario de los empleados cuyo departamento
     * es 15
     * 
     * @param bbdd base de datos donde se va ha ejecutar la sentencia
     */
    public void actualizarSalarioEmpleadosNumero15(OperacionesBBDD bbdd){
        int registrosActualizados;
        
        String sentenciaSQL = "UPDATE Empleados SET salario = salario + 100 WHERE dept_no = 15";
        
        try {
            registrosActualizados = bbdd.update(sentenciaSQL);
            
            System.out.println("Empleados actualizados con exito. Filas actualizadas: " +registrosActualizados);
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que sube o baja el sueldo en un porcentaje
     * @param bbdd base de datos donde se va a ejecutar el update
     * @param nDep departamento de los empleados que se van ha actualizar el salario
     * @param porcentaje Porcentaje que va a bajar o subir el sueldo
     * @param subir  true --> El porcentaje sube el sueldo, false --> el porcentaje baja el sueldo
     * 
     * 
     * LAS SENTENCIAS DE SUBIDA Y BAJADA ESTÁN MAL
     */
    public void porcentajeSueldoSubirBajar(OperacionesBBDD bbdd, int nDep, int porcentaje, boolean subir){
        
        int registrosActualizados;
        String sentenciaSQL;
        
        if (subir){
            
            try {
                sentenciaSQL = "UPDATE Empleados SET salario = salario * (1 + "+porcentaje+"/ 100) WHERE dept_no = "+nDep;
                registrosActualizados = bbdd.update(sentenciaSQL);
                System.out.println("Salario subido con exito. Filas actualizadas: " +registrosActualizados);
            } catch (SQLException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            try {
                sentenciaSQL = "UPDATE Empleados SET salario = salario * (1 - "+porcentaje+"/ 100) WHERE dept_no = "+nDep;
                registrosActualizados = bbdd.update(sentenciaSQL);
                System.out.println("Salario bajado con exito. Filas actualizadas: " +registrosActualizados);
            } catch (SQLException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     * Muestra los datos del ResultSet
     * 
     * @param rs ResultSet del cual queremos mostrar los datos
     */
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
    
    /**
     * Modifica un empleado
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     */
    public void update(OperacionesBBDD bbdd){
        try {
            bbdd.update("UPDATE empleados SET apellido = ?, oficio = ?, dir = ?, fecha_alt = ?, salario = ?, comision = ?, dept_no =  WHERE emp_no = ? ", this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.emp_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Elimina un empleado
     * 
     * @param bbdd Clase para las operaciones con la bbdd
     * @param emp_no Número del empleado que queremos eliminar
     */
    public static void delete(OperacionesBBDD bbdd, int emp_no){
        try {
            bbdd.delete("DELETE FROM empleados WHERE emp_no = ? ", emp_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//------------------------------------------------------------------------------    
    //MÉTODOS EXTRA --> GETTERS, SETTERS, ToString...

    private Date convertirFecha(String fecha){
        java.util.Date fechaUtil = null;
        try {
            SimpleDateFormat s = new SimpleDateFormat("DD/MM/YYYY");    
            fechaUtil = s.parse(fecha);
            
        } catch (ParseException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.sql.Date(fechaUtil.getTime());
    }
    
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
