/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bbddjdbc;

import com.mycompany.bbddjdbc.bbdd.OperacionesBBDD;
import com.mycompany.bbddjdbc.modelo.Empleado;
import java.sql.SQLException;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class Bbddjdbc {
    
    //Creamos la instancia del patron Singleton
    static OperacionesBBDD bbdd = OperacionesBBDD.getInstance(); 

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
        //departamento.selectById(bbdd,60);
        //System.out.println(departamento);
        
        /**
         * INSERCIÓN
         */
        //Departamento departamento = new Departamento(60,"TRANSPORTE","MADRID");
        //departamento.insertar(bbdd);
        
        /**
         * MODIFICACIÓN
         */
        //Departamento departamento = new Departamento(60,"TRANSPORTE","ZARAGOZA");
        //departamento.update(bbdd);
        
        /**
         * BORRADO
         */
        //Departamento.delete(bbdd, 60);
         
        
        /******************************
         * PRUEBAS CON EMPLEADO
         ******************************/
                     
        /**
         * INSERCIÓN
         */
        //Empleado empleado = new Empleado(7888, "GARCIA", "ANALISTA", 7566, "08/11/2024", 2000, 200.50, 10);
        //empleado.insertar(bbdd);
        
        /**
         * CONSULTA DE TODOS LOS REGISTROS
         */
        //Empleado.mostrarResultSet(Empleado.selectAll(bbdd));
              
        /**
         * CONSULTA POR CLAVE PRIMARIA
         */
        //Empleado empleado = new Empleado();        
        //empleado.selectById(bbdd,7888);
        //System.out.println(empleado);
        
        /**
         * INSERTAR
         */
        //Empleado empleado = new Empleado(7888, "GARCIA", "VENDEDOR", 7566, "08/11/2024", 2000, 200.50, 10);
        //empleado.insertar(bbdd);
        
        /**
         *  MODIFICAR
         */
        //Empleado empleado = new Empleado(7888, "AGUILAR", "PROFESOR", 7777, "21/12/2020", 6666, 1.2, 10);
        //empleado.update(bbdd);
        
        /**
         * BORRADO
         */
        //Empleado.delete(bbdd, 7888);
                 
        /**
         * Obtener apellido oficio y salario
         */
        //Empleado.obtenerApellidoOficioSalario(bbdd, 10);
        
        
        Empleado.actualizarSalarioEmpleadosNumero10(bbdd);
        
        /************************************************
         * PRUEBAS SENTENCIAS DE DESCRIPCIÓN
         ************************************************/
        //bbdd.obtenerInformacionDeConexion();
        //bbdd.obtenerInformacionDeLasTablas();
        //bbdd.obtenerInformacionDeLasColumnas("DEPARTAMENTOS");
        //bbdd.obtenerInformacionDelResultSet(Departamento.selectAll(bbdd));
        
        bbdd.cerrarConexion();
        
    }
    
}
