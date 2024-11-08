/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bbddjdbc;

import com.mycompany.bbddjdbc.bbdd.Departamento;
import com.mycompany.bbddjdbc.bbdd.Empleado;
import com.mycompany.bbddjdbc.bbdd.OperacionesBBDD;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class Bbddjdbc {
    
    //Creamos la instancia del patron Singleton
    static OperacionesBBDD bbdd = OperacionesBBDD.getInstance(); 

    public static void main(String[] args) throws SQLException {
        
        //Abrimos la conexion con la BBDD
        bbdd.abrirConexion();

//        Departamento dep = new Departamento();
//        dep.selectById(bbdd, 10);
//        
        
//        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
//        java.util.Date fecha = null;
//        try {
//            fecha = s.parse("02/02/2000");
//        } catch (ParseException ex) {
//            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
//        
//        Empleado e = new Empleado(1, "Herrera", "Informatic", 555, fechaSql, 150.33, 1500.80, 10);
//        e.insertarEmpleado(bbdd);
//        e.selectById(bbdd, 1);
//        System.out.println(e);

        Departamento d = new Departamento();
        
        String salida = d.ejecutarProcedimientoDeoartamento(bbdd);
        System.out.println(salida);
        
        bbdd.cerrarConexion();
    }

    
}
