/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bbddjdbc;

import com.mycompany.bbddjdbc.modelo.Departamento;
import com.mycompany.bbddjdbc.modelo.Empleado;
import com.mycompany.bbddjdbc.bbdd.OperacionesBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para probar el funcionamiento de JDBC con Oracle
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 30 oct 2024
 */
public class Bbddjdbc {
    
    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstance();
    private static Optional<ResultSet> rs;
    
    public static void main(String[] args) throws SQLException {
            
        bbdd.abrirConexion();
        
        /******************************
         * PRUEBAS CON DEPARTAMENTO
         ******************************/
              
        /**
         * CONSULTA DE TODOS LOS REGISTROS
         */
        //Departamento.mostrarResultSet(Departamento.selectAll(bbdd));
              
        /**
         * CONSULTA POR CLAVE PRIMARIA
         */
        //Departamento departamento = new Departamento();        
        //departamento.selectById(bbdd,10);
        //System.out.println(departamento);
        
        /**
         * INSERCIÓN
         */
        //Departamento departamento = new Departamento(60,"TRANSPORTE","MADRID");
        //departamento.insertarDepartamento(bbdd);

        
        
        /**
         * MODIFICACIÓN
         */
        //Departamento departamento = new Departamento(60,"TRANSPORTE","ZARAGOZA");
        //departamento.update(bbdd);
        
        /**
         * BORRADO
         */
        //Departamento.delete(bbdd, 60);
        
        /**
         * Inserto una fila nueva en la bbdd recorriendo un RS
         */
        //Optional<ResultSet> ors; 
        //ors = Empleado.selectAll(bbdd);
        //ResultSet rs = ors.get();
        //Empleado.insertar(rs);
        
        /******************************
         * PRUEBAS CON EMPLEADO
         ******************************/
              
        /**
         * CONSULTA DE TODOS LOS REGISTROS
         */
        //Empleado.mostrarResultSet(Empleado.selectAll(bbdd));
              
        /**
         * CONSULTA POR CLAVE PRIMARIA
         */
        //Empleado empleado = new Empleado();        
        //empleado.selectById(bbdd,7369);
        //System.out.println(empleado);
        
        /**
         * INSERCIÓN
         */
        //Empleado empleado = new Empleado(7888, "GARCIA", "ANALISTA", 7566, "08/11/2024", 2000, 200.50, 10);
        //empleado.insertar(bbdd);
        
        /**
         * MODIFICACIÓN
         */
        //Empleado empleado = new Empleado(7888, "GARCIA", "VENDEDOR", 7566, "08/11/2024", 2000, 200.50, 10);
        //empleado.update(bbdd);
        
        /**
         * BORRADO
         */
        //Empleado.delete(bbdd, 7888);
        
        /**
         * Llamada al metodo obtenerApellidoOficioSalario
         */
        //Empleado empleado = new Empleado();
        //empleado.obtenerApellidoOficioSalario(bbdd, 10);
        
        /**
         * Llamada al metodo empleadoMaxSalario
         */
        //Empleado empleado = new Empleado();
        //empleado.empleadoMaxSalario(bbdd);
        
        
        /**
         * Inserto un nuevo departamento
         */
        //Departamento departamento = new Departamento(15, "Informatica", "Madrid");
        //departamento.insertar(bbdd);
        
        /**
         * Inserto dos empleados que pertenecen al departamento anterior
         * Para añadir fechas en sql tienes que hacerlo de esta forma
         */
        //EMPLEADO 1
//        SimpleDateFormat s = new SimpleDateFormat("DD/MM/YYYY");
//        java.util.Date fechaUtil = null;
//        String fecha = "21/11/2024";
//        try {
//            fechaUtil = s.parse(fecha); //Ya esta el objeto java creado 
//        } catch (ParseException ex) {
//            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
//        Empleado empleado1 = new Empleado(2, "Dominguez", "Carterista", 33, fechaSQL, 3333.33, 14.89, 15);
//        empleado1.insertar(bbdd);
//        System.out.println("Empleado 1 insertado");
//        
//        //EMPLEADO 2 
//        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
//        java.util.Date fechaJava = null;
//        String fecha2 = "22/09/1970";
//        try {
//            fechaJava = sdf.parse(fecha);
//        } catch (ParseException ex) {
//            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        java.sql.Date fechaSQL2 = new java.sql.Date(fechaJava.getTime());
//        Empleado empleado2 = new Empleado(3, "Rayo", "Profesor", 54, fechaSQL2, 3422.90, 11, 15);
//        empleado2.insertar(bbdd);
//        System.out.println("Empleado 2 insertado");
        
        /**
         * Actualizo en +100 el salario de los empleados del departamento 15
         */
        //Empleado empleado = new Empleado();
        //empleado.actualizarSalarioEmpleadosNumero15(bbdd);
            
        
        /**
         * Sube o baja en un porcentaje el salario del empleado
         */
        //Empleado empleado = new Empleado();
        //empleado.porcentajeSueldoSubirBajar(bbdd, 10, 10);
        //empleado.porcentajeSueldoSubirBajar(bbdd, 15, -75);
        
        
        /**
         * Insertamos un empleado si cumple con x condiciones
         */
//        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
//        java.util.Date fechaJava = null;
//        String fecha2 = "23/11/2024";
//        try {
//            fechaJava = sdf.parse(fecha2);
//        } catch (ParseException ex) {
//            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        java.sql.Date fechaSQL2 = new java.sql.Date(fechaJava.getTime());
//        
//        LocalDate fechaActual = LocalDate.now();
//        Empleado empleado = new Empleado(10, "Ramirez", "Payaso", 33, fechaSQL2, 5555, 57, 10);
//        empleado.insertar2(bbdd);


        /**
         * Obtenemos el salario medio y el oficio
         */
        //Empleado empleado = new Empleado();
        //empleado.obtenerSalarioMedioNumeroEmpleados(bbdd, 15);
        
        /**
         * Obtenemos informacion de la conexion
         */
        //bbdd.obtenerInformacionDeConexion();
        
        /**
         * Obtenemos la informacion de las tablas de la bbdd de un usuario
         */
        //bbdd.obtenerInformacionDeLasTablas();
        
        
        /**
         * Obtenemos informacion sobre las columnas de una tabla
         */
        //bbdd.obtenerInformacionDeLasColumnas("EMPLEADOS");
        
        
        /**
         * Obtenemos informacion sobre las claves de una tabla
         */
        //CUIDADO!!! LA TABLA HAY QUE PASARSELA EN MAYUSCULASS
        //bbdd.mostrarInformacionClaves("EMPLEADOS");
        
        
        /**
         * Actualizar salario empleado numero 15
         */
        //Empleado empleado2 = new Empleado(7566, "CASA", "Programador", 7566, 77, 11, 99);
        //empleado2.insertar2(bbdd);
        
        
        /************************************************
         * PRUEBAS LLAMADAS A PROCEDIMIENTOS Y FUNCIONES
         ************************************************/
        //System.out.println("El nombre del departamento es:" + Departamento.pNombreDepart(bbdd, 10));      
        //System.out.println("El nombre del departamento es:" + Departamento.fNombreDepart(bbdd, 10));
        
        
        /************************************************
         * PRUEBAS MOVIMIENTO POR UN RESULTSET
         ************************************************/
        // En OperacionesBBDD.executeQuery con: preparedStatement = conexion.prepareStatement(querySQL); en el método executeQuery
        
        //Optional<ResultSet> rs = Departamento.selectAll(bbdd);      
        //Departamento.mostrarResultSet(rs);
        //try {
        //    rs.get().beforeFirst();   //Al intentar mover el cursor al principio para volver a mostrar el ResultSet daría error
        //} catch (SQLException ex) {
        //    Logger.getLogger(BbddjdbcOracle.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //Departamento.mostrarResultSet(rs);
        
        // Cambiando en OperacionesBBDD.executeQuery: 
        // preparedStatement = conexion.prepareStatement(querySQL); en el método executeQuery
        // por
        //preparedStatement = conexion.prepareStatement(querySQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        
        //Optional<ResultSet> rs = Departamento.selectAll(bbdd);      
        //Departamento.mostrarResultSet(rs);
        //try {
        //    rs.get().beforeFirst(); //Al intentar mover el cursor al principio para volver a mostrar el ResultSet ya NO daría error
        //} catch (SQLException ex) {
        //    Logger.getLogger(BbddjdbcOracle.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //Departamento.mostrarResultSet(rs);
               
        
        /************************************************
         * PRUEBAS SENTENCIAS DE DESCRIPCIÓN
         ************************************************/
        //bbdd.obtenerInformacionDeConexion();
        //bbdd.obtenerInformacionDeLasTablas();
        //bbdd.obtenerInformacionDeLasColumnas("DEPARTAMENTOS");
        //bbdd.obtenerInformacionDelResultSet(Departamento.selectAll(bbdd));
        //bbdd.obtenerNumeroFilasDevueltas(Departamento.selectAll(bbdd));

        
        /************************************************
         * PRUEBAS MODIFICACIÓN UTILIZANDO EL RESUSLTSET
         ************************************************/
        
        //if (bbdd.obtenerInformacionOperacionesResultSet()){
        //     rs = Departamento.selectAll(bbdd);
        
        //    try {
        //        rs.get().beforeFirst();
        //        while(rs.get().next()) {
        //            rs.get().updateString("loc", "SEVILLA");
        //            rs.get().updateRow();
        //        } 
        //    } catch (SQLException ex) {
        //        Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        //    }
        //}
        
       
        
        bbdd.cerrarConexion();       
    }   
}

