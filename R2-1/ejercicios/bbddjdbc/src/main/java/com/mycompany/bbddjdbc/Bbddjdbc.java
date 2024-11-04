/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bbddjdbc;

import com.mycompany.bbddjdbc.bbdd.Departamentos;
import static com.mycompany.bbddjdbc.bbdd.Departamentos.insertarDepartamento;
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
        
        bbdd.abrirConexion();
        insertarDepartamento();
        bbdd.cerrarConexion();
    }
    
    
//    public static void insertarDatos() throws SQLException{
//        bbdd.insert("insert into Departamentos values (?,?,?)", 1, "Informatica", "Ciudad Real");
//    }
    
}
