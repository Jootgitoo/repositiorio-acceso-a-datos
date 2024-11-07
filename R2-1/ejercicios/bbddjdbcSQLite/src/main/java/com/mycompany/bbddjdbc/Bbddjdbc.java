/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bbddjdbc;

import com.mycompany.bbddjdbc.bbdd.Departamento;
import com.mycompany.bbddjdbc.bbdd.Empleado;
import com.mycompany.bbddjdbc.bbdd.OperacionesBBDD;
import java.sql.SQLException;

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
        
        //Añadimos a la BBDD
//        Departamento departamento = new Departamento(2, "Ventas", "Logroño");
//        departamento.insertarDepartamento(bbdd);
//        
//        departamento = new Departamento(3, "Logistica", "Cueca");
//        departamento.insertarDepartamento(bbdd);
//            Departamento departamento = new Departamento();
//           departamento.selectById(bbdd, 2);
//            System.out.println(departamento);
//
//            departamento.setLoc("Albacete");
//           departamento.update(bbdd);
//           
//           departamento.selectById(bbdd, 2);
//           System.out.println(departamento);

            Departamento.delete(bbdd, 1);

//        departamento.insertarDepartamento(bbdd);        
        //Cerramos la conexion con la BBDD
        bbdd.cerrarConexion();
    }
    
    
//    public static void insertarDatos() throws SQLException{
//        bbdd.insert("insert into Departamento values (?,?,?)", 1, "Informatica", "Ciudad Real");
//    }
    
}
