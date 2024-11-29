/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectojpa2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class ProyectoJPA2 {
    
    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    static Departamentos departamentos;
    static DepartamentosJpaController departamentosJpaController;
    

    public static void main(String[] args) {

        try {
        
            emfactory = Persistence.createEntityManagerFactory("com.mycompany_proyectoJPA2_jar_1.0-SNAPSHOTPU");

            departamentosJpaController = new DepartamentosJpaController(emfactory);

            Departamentos departamentos = new  Departamentos();

            departamentos.setDeptNo((short) 99);
            departamentos.setDnombre("Pruebas");
            departamentos.setLoc("Madrid");
        
            departamentosJpaController.create(departamentos);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
