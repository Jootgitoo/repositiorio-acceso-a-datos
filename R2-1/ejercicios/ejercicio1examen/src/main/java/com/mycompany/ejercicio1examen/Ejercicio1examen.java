/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio1examen;

import com.mycompany.ejercicio1examen.modelo.Dron;
import com.mycompany.ejercicio1examen.modelo.Modelo;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class Ejercicio1examen {

    
    private static Modelo modelobbdd = Modelo.getInstance();
    private static Optional<ResultSet> rs;
    
    public static void main(String[] args) {
        
        modelobbdd.abrirConexion();
            
        //Ejercicio1
//        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
//        java.util.Date fechaJava = null;
//        String fecha = "23/11/2024";
//        try {
//            fechaJava = sdf.parse(fecha);
//        } catch (ParseException ex) {
//            Logger.getLogger(Ejercicio1examen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        java.sql.Date fechaSQL = new java.sql.Date(fechaJava.getTime());
//        Dron dron = new Dron("456DFG", "ASUS", "A1", 0.8, 150, 50, "Aseguradora Ramon", 1.5, 2, fechaSQL, "11111111A");
//        modelobbdd.insertarDron(dron);
        

        //Ejercicio 2
//        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
//        java.util.Date fechaJava = null;
//        String fecha = "28/09/2024";
//        try {
//            fechaJava = sdf.parse(fecha);
//         } catch (ParseException ex) {
//            Logger.getLogger(Ejercicio1examen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        java.sql.Date fechaSQL = new java.sql.Date(fechaJava.getTime());
//        modelobbdd.eliminaFilasDronesObsoletos(fechaSQL, 1);
//        


        
        //Ejercicio 3
        //modelobbdd.mostrarFilaDatosDrones("123");

        //Ejercicio 4
//        Date fecha1 = Modelo.convertirFecha("12/01/2023");
//        java.sql.Date fechaSQL1 = new java.sql.Date(fecha1.getTime());
//        
//        Date fecha2 = Modelo.convertirFecha("21/09/2024");
//        java.sql.Date fechaSQL2 = new java.sql.Date(fecha2.getTime());
//        modelobbdd.mostrarTodosLosDatos(fechaSQL1, fechaSQL2);
//        


        //Ejercicio 5
        Date fecha = Modelo.convertirFecha("27/11/2025");
        java.sql.Date fechaSQL1 = new java.sql.Date(fecha.getTime());
        modelobbdd.cursoRenovacion(fechaSQL1);
       
        modelobbdd.cerrarConexion();
        
    }
}
