/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bbddjdbc;

import com.mycompany.bbddjdbc.bbdd.Departamentos;
import com.mycompany.bbddjdbc.bbdd.Empleados;
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
        
        Departamentos dep = new Departamentos(1, "Tecnologia", "Ciudad Real");
        Empleados emple = new Empleados(1, "Herrera", "Ingeniero", 10, "05/11/2024", 13000, 1000, 5);
        
        //Abrimos la conexion con la BBDD
        bbdd.abrirConexion();
        
        //Añadimos a la BBDD
        dep.insertarDepartamento();
        emple.insertarEmpleado();
        
        //Cerramos la conexion con la BBDD
        bbdd.cerrarConexion();
    }
    
    
//    public static void insertarDatos() throws SQLException{
//        bbdd.insert("insert into Departamentos values (?,?,?)", 1, "Informatica", "Ciudad Real");
//    }
    
}
