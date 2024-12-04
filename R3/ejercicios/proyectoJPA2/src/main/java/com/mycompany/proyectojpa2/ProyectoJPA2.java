/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectojpa2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class ProyectoJPA2 {
    
    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    static Departamentos departamento;
    static DepartamentosJpaController departamentosJpaController;
    

    public static void main(String[] args) {

        try {
        
            inicializarFactory();
            
//            emfactory = Persistence.createEntityManagerFactory("com.mycompany_proyectoJPA2_jar_1.0-SNAPSHOTPU");
//
//            departamentosJpaController = new DepartamentosJpaController(emfactory);
//
//            Departamentos departamentos = new  Departamentos();
//
//            departamentos.setDeptNo((short) 99);
//            departamentos.setDnombre("Pruebas");
//            departamentos.setLoc("Madrid");
//        
//            departamentosJpaController.create(departamentos);

            
            leerUnRegistroRelacionado();

            cierraFactory();


        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Inicializo el factory
     */
    public static void inicializarFactory(){
        emfactory = Persistence.createEntityManagerFactory("com.mycompany_proyectoJPA2_jar_1.0-SNAPSHOTPU");
        entitymanager = emfactory.createEntityManager();
    }
    
    /**
     * Cierro el factory
     */
    public static void cierraFactory(){
        entitymanager.close();
        emfactory.close();
    }
    
    
    /**
     * Leo un registro de la bbdd
     */
    public static void leerUnRegistro(short deptId){
        
        
        departamento = entitymanager.find(Departamentos.class, deptId );
        
        if(departamento != null){
            System.out.println("Dept NAME: " +departamento.getDnombre());
        } else {
            System.out.println("NO existe el registro");
        }
    }
    
    
    /**
     * Leo un registro que me lo bloquea, es decir, me impide hacerle cualquier cambio 
     */
    public Departamentos findDepartamentosBloqueando(Short id){
        
        entitymanager.getTransaction().begin();
        
        departamento = entitymanager.find(Departamentos.class, id, LockModeType.PESSIMISTIC_READ);
        
        entitymanager.getTransaction().commit();
        
        return departamento;
    }
    
    
    /**
     * Método para que nuestro programa se quede esperando hasta que pulses enter
     */
    public static void esperar(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Pulsa Enter para continuar...");
        
        try {
            
            String sTexto = br.readLine();
            
        } catch (IOException ex) {
            
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Recargo los datos cargados. Sirve por si yo tengo un dato y lo modificas este metodo hace un "refresh" de ese departa
     */
    public static void recargardesdeBbdd(){
        entitymanager.getTransaction().begin();
        entitymanager.refresh(departamento);
        entitymanager.getTransaction().commit();
        System.out.println("Recargando datos...");
    }
    
    
    /**
     * Mostramos el nombre del departamento y todos los empleados que contienen ese departamento
     * 
     * Con esto nos damos cuenta que al leer un departamento este departamento mete en una lista todos los empleados
     * Esto viene en las clase Departamento (creadas por el id anteriormente)-
     */
    public static void leerUnRegistroRelacionado(){
        try {
            Empleados emple;
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.print("Indica el numero del departamento: ");
            
            short nDep = Short.parseShort( br.readLine() );
            
            
            
            //Leemos el departamento 10
            departamento = entitymanager.find(Departamentos.class, nDep );
            
            
            if (departamento != null){ //Si hay departamentos...
                
                //Imprime el nombre del departamento
                System.out.println("Nombre DEPARTAMENTO: " +departamento.getDnombre()); 
                
                //Coleccion que contiene todos los empleados de ese departamento
                Collection<Empleados> listaEmpleados = departamento.getEmpleadosCollection();
                
                //Creamos un iterator para recorrer la coleccion
                Iterator<Empleados> it = listaEmpleados.iterator();
                
                while( it.hasNext() ){ //Si el iterator tiene next, es decir, si tiene un empleado
                    
                    //Leemos el empleado
                    emple = it.next();
                    
                    //Imprimimos el nombre del empleado
                    System.out.println("--> Nombre EMPLEADO: " +emple.getApellido());
                }
            } else {
                System.out.println("No existe el registro");
            }
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Insertamos un departamento
     * @param deptNo numero de departamento
     * @param dNombre nombre del departamento
     * @param dLocalidad  localidad del departamento a insertar
     */
    public static void insertarDatos(short deptNo, String dNombre, String dLocalidad){
        Departamentos departamento;
        
        departamento = new Departamentos();
        departamento.setDeptNo( deptNo );
        departamento.setDnombre(dNombre);
        departamento.setLoc( dLocalidad );
        
        entitymanager.getTransaction().begin();
        entitymanager.persist(departamento);
        entitymanager.getTransaction().commit();
    }
    
    
    public static void modificarDatos(){
        
        entitymanager.getTransaction().begin();
        
        departamento = entitymanager.find(Departamentos.class, (short)50, LockModeType.PESSIMISTIC_READ );
        
        departamento.setLoc("Madrid");
        esperar();
        entitymanager.getTransaction().commit();
       
    }
    
    
    public static void borrarDatos(){
        
        try {   
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Indica el numero del departamento: ");
            
            short nDep = Short.parseShort( br.readLine() );
            
            
            entitymanager.getTransaction().begin();
            
            departamento = entitymanager.find(Departamentos.class, (short)50, LockModeType.PESSIMISTIC_READ );
            
            entitymanager.remove(departamento);
            esperar();
            entitymanager.getTransaction().commit();
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
