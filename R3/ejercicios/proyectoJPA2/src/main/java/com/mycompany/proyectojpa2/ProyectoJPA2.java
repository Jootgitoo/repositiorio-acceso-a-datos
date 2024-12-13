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
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    static Empleados empleado;
    static DepartamentosJpaController departamentosJpaController;
    

    public static void main(String[] args) {

        try {
            inicializaFactoryController();
            
            DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
            Departamentos departamento = new Departamentos();
            departamento.setDeptNo( (short)77 );
            departamento.setDnombre("BIG DATA");
            departamento.setLoc("TALAVERA");
            departamento.setEmpleadosCollection(null);
            
//            Empleados empleado = new Empleados();
            
//            Collection<Empleados> empleadosCollection = new ArrayList<Empleados>();
//            empleado.setEmpNo( (short)7777 );
//            empleado.setApellido("ROBLES");
//            empleado.setSalario( BigDecimal.valueOf(2000));
//            empleado.setOficio("ANALISTA");
//            empleado.setDir( (short)7839 );
            
//            empleadosCollection.add(empleado);
            
//            departamento.setEmpleadosCollection(empleadosCollection);
            
//            departamentosJpaController.create(departamento);
            
            
            cierraFactoryController();
            
            
//--------------------------------------------------------------------------------------------------------------------------------        
            //inicializarFactory();
            
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


//--------------------------------------------------------------------------------------------------------------------------------
    //CLASSROOM --> OPERACIONES DIRECTAS CON OBJETOS --> PRACTICA EN CASA
            
            //EJERCICIO 1
            //leerUnRegistroRelacionado();

            //EJERCICIO 2
            //borrarDatos();
            
            //EJERCICIO 3
            //modificarSalarioDepartamentoDeEmpleado();

            //EJERCICIO 4
            //borrarEmpleado();
            
            //EJERCICIO 5
            //insertarEmpleados();
            
//--------------------------------------------------------------------------------------------------------------------------------
    //CLASSROOM --> OPERACIONES DIRECTAS CON OBJETOS --> PRACTICA EN CLASE            
            
            subirSalario();
            
            //cierraFactory();


        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
//--------------------------------------------------------------------------------------------------------------------------------
    //MÉTODOS FUERA DEL MAIN
    
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
     * Leo el registro que tenga el id pasado
     * @param deptId id del departamento del cual se va ha leer el registro
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
     * Leo un departamento bloqueandolo, es decir, mientras que yo lo tenga se "bloquea" y nadie puede hacer cambios sobre este departamento
     * @param id id del departamento que va ha leer
     * @return devuelve el departamento leido
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
    
    /**
     * Sirve para modificar los datos de un departamento
     */
    public static void modificarDatos(){
        
        entitymanager.getTransaction().begin();
        
        departamento = entitymanager.find(Departamentos.class, (short)50, LockModeType.PESSIMISTIC_READ );
        
        departamento.setLoc("Madrid");
        esperar();
        entitymanager.getTransaction().commit();
       
    }
    
    
    public static void inicializaFactoryController(){
        emfactory = Persistence.createEntityManagerFactory("com.mycompany_proyectoJPA2_jar_1.0-SNAPSHOTPU");
        
    }
    
    /**
     * Cierro el factory
     */
    public static void cierraFactoryController(){
        emfactory.close();
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------
    //CLASSROOM --> OPERACIONES DIRECTAS CON OBJETOS --> PRACTICA EN CASA
    
    /**
     * Mostramos el nombre del departamento y todos los empleados que contienen ese departamento pedido por teclado
     * 
     * Con esto nos damos cuenta que al leer un departamento este departamento mete en una lista todos los empleados
     * Esto viene en las clase Departamento (creada por el id anteriormente)
     */
    public static void leerUnRegistroRelacionado(){
        try {
            
            Empleados emple;
            
            //Solicitamos el número de departamento
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indica el numero del departamento: ");
            short nDep = Short.parseShort( br.readLine() );
            
            //Leemos ese
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
     * Borro los datos de un departamento pedido por teclado
     */
    // DA ERROR PORQUE HAY EMPLEADOS RELACCIONADOS CON ESTE DEPARTAMENTO
    //SI CREAS UN DEP NUEVO QUE NO TENGA EMPLEADOS Y LO BORRAS FUNCIONA
    public static void borrarDepartamento(){
        
        try {   
            
            //Pedimos el numero del departamento al usuario
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indica el numero del departamento: ");
            short nDep = Short.parseShort( br.readLine() );
          
            //Realizamos la accion en la bbdd
            entitymanager.getTransaction().begin();  
            departamento = entitymanager.find(Departamentos.class, nDep, LockModeType.PESSIMISTIC_READ );
            entitymanager.remove(departamento);
            entitymanager.getTransaction().commit();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Te permite modificar el salario y el departamento de un empleado
     */
    public static void modificarSalarioDepartamentoDeEmpleado(){
     
        try {
            
            //Pedimos el numero del empleado
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indica el numero del empleado: ");
            short nEmple = Short.parseShort( br.readLine() );
            
            
            entitymanager.getTransaction().begin();
            
            empleado = entitymanager.find(Empleados.class, nEmple, LockModeType.PESSIMISTIC_READ);
            
            if (empleado != null){ //Si el empleado es distinto de null, es decir, existe
                
                //Modificamos el salario
                empleado.setSalario( new BigDecimal ("100") );
                
                //Modificamos el departamento
                //Se hace así porque el setDeptNo recibe un departamento por parametro
                Departamentos dep = new Departamentos();
                dep.setDeptNo( (short) 10);
                empleado.setDeptNo(dep);
                
                entitymanager.getTransaction().commit();
                
            } else { //Si el empleado es null, es decir, no existe
                System.out.println("El empleado indicado no existe");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
    /**
     * Borra un empleado pedido por teclado
     */
    public static void borrarEmpleado(){
        
        try {
            //Pedimos el numero del empleado
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Indica el numero del empleado: ");
            short nEmple = Short.parseShort( br.readLine() );
            
            //Abrimos la transaccion
            entitymanager.getTransaction().begin();
            
            //Buscamos el empleado con dicho numero
            empleado = entitymanager.find(Empleados.class, nEmple, LockModeType.PESSIMISTIC_READ);
            
            //Borramos el empleado
            entitymanager.remove(empleado);
            
            //Guardamos el cambio
            entitymanager.getTransaction().commit();
            
        } catch (IOException ex) {
            
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Inserta un empleado pasado pasado por teclado
     */
    public static void insertarEmpleados(){
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            //Pedimos por teclado todos los datos
            System.out.print("Indica el numero del empleado: ");
            short nEmple = Short.parseShort( br.readLine() );
            
            System.out.print("Indica el apellido del empleado: ");
            String apellido = br.readLine();
            
            System.out.print("Indica el oficio del empleado: ");
            String oficio = br.readLine();
            
            System.out.print("Indica el director del empleado: ");
            short dir = Short.parseShort( br.readLine() );
            
            System.out.print("Indica la fecha alta del empleado en formato (DD/MM/YYYY): ");
            Date fechaAlta = convertirFecha( br.readLine() );
            
            System.out.print("Indica el salario del empleado: ");
            String salario = br.readLine();
            
            System.out.print("Indica la comision del empleado: ");
            String comision = br.readLine();
            
            System.out.print("Indica el numero del departamento al que pertenece: ");
            String nDep = br.readLine();
            departamento = new Departamentos();
            departamento.setDeptNo( Short.parseShort(nDep));
            
            //Añadimos los datos al empleado que vamos a insertar
            empleado = new Empleados();
            empleado.setEmpNo(nEmple);
            empleado.setApellido(apellido);
            empleado.setOficio(oficio);
            empleado.setDir(dir);
            empleado.setFechaAlt(fechaAlta);
            empleado.setSalario( new BigDecimal(salario) );
            empleado.setComision( new BigDecimal(comision) );
            empleado.setDeptNo(departamento );
            
            //Realizamos la accion
            entitymanager.getTransaction().begin();
            entitymanager.persist(empleado);
            entitymanager.getTransaction().commit();
           
            
            
        } catch (IOException ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    /**
     * Sirve para convertir un String en un Date
     * @param fecha decha que se va ha convertir en un Date
     * @return la misma fecha pasada por parametro pero de tipo Date
     */
    private static Date convertirFecha(String fecha){
        java.util.Date fechaUtil = null;
        try {
            SimpleDateFormat s = new SimpleDateFormat("DD/MM/YYYY");    
            fechaUtil = s.parse(fecha);
            
        } catch (ParseException ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.sql.Date(fechaUtil.getTime());
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------
    //CLASSROOM --> OPERACIONES DIRECTAS CON OBJETOS --> PRACTICA EN CLASE
    
    /**
     * Subimos el salario de los empleados que pertenezcan a un determinado departamento
     */
    public static void subirSalario(){
        
        Empleados emple;
        try {
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.print("Indica el numero del departamento: ");
            short nDep = Short.parseShort( br.readLine() );
            
            System.out.print("Indica la subida en euros: ");
            String subida = br.readLine();
            
            //Muestro los empleados y el salario de cada uno de un departamento antes de la subida
            leerUnRegistroRelacionado(nDep);
           
            
            //Hacemos el cambio
            //departamento = entitymanager.find(Departamentos.class, nDep);
            
            if (departamento != null){ //Si hay departamentos...
            
            //Coleccion que contiene todos los empleados de ese departamento
            Collection<Empleados> listaEmpleados = departamento.getEmpleadosCollection();
            
            //Creamos un iterator para recorrer la coleccion
            Iterator<Empleados> it = listaEmpleados.iterator();
            
            entitymanager.getTransaction().begin();
            
            while( it.hasNext() ){ //Si el iterator tiene next, es decir, si tiene un empleado
                
                //Leemos el empleado
                emple = it.next(); 
                
                //Cambiamos el salario
                
                emple.setSalario( emple.getSalario().add( new BigDecimal (subida) ) );
            }
            } else {
                System.out.println("No existe el registro");
            }
            
            entitymanager.getTransaction().commit();
            
            //Muestro los empleados y el salario de cada uno de un departamento despues de la subida
            leerUnRegistroRelacionado(nDep);

            
        } catch (IOException ex) {
            
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    

    /**
     * Lee un departamento cuyo numero sea el pasado por parametro (contiene todos sus empleados)
     * @param nDep numero de departamento que se va ha leer
     */
    private static void leerUnRegistroRelacionado(short nDep){
        Empleados emple;
        //Leemos el departamento
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
                System.out.print("--> Nombre EMPLEADO: " +emple.getApellido());
                System.out.print(", SALARIO " +emple.getSalario());
                System.out.println("");
            }
        } else {
            System.out.println("No existe el registro");
        }
    }
    
    
    
}
