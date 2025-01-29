/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectojpa2;

import com.mycompany.proyectojpa2.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
import java.util.List;
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
            
            /*******************************************************************
             * Ejemplos con Jpa controller
             *******************************************************************/
            //inicializaFactoryController();
            
            //insertaDepartamentoConEmpleado();
            
            //borrarDepartamentoController();
            
            //listarDepartamentos();
            
            //listarDepartamentosPorTramos();
            
            //contarNumeroDepartamentos();
            
            //listarUnDepartamento();
            
            //modificarDepartamento(10);
            
            //cierraFactoryController();
            
            
            /*******************************************************************
             * Ejemplos con JPQL
             *******************************************************************/
            inicializarFactory();
            
            /**-----------------------------------------------------------------
             * PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL
            --------------------------------------------------------------------*/
            consultaSimple();
            //consultaVariosCampos();
            
            
            /**---------------------------------------------------------------------------------------
             * PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL ALMACENADAS EN LAS CLASES DE PERSISTENCIA
            ------------------------------------------------------------------------------------------*/
            //consultaAlmacenada();
            //consultaAlmacenadaConParametros(10);
            
            //consultaConCriteriaQuery();
            
            //consultaConCriteriaQueryVariosCampos();
            
            //modificarDatosConJPQL();
            
            //borrarDatosConJPQL();
            
            cierraFactory();
            
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
            
            //subirSalario();
            
            //cierraFactory();


        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
//--------------------------------------------------------------------------------------------------------------------------------
    //Ejemplos JPQL
    public static void consultaSimple(){
        
        Query query = entitymanager.createQuery("Select UPPER(d.dnombre) from Departamentos d");
        
        List<String>list = query.getResultList();
        
        for(String e:list){
            System.out.println("Nombre departamento: " +e);
        }
        
    }
        

    public static void consultaVariosCampos(){
        TypedQuery<Object[]> query = entitymanager.createQuery("Select d.dnombre, d.loc FROM Departamentos d", Object[].class);
        
        List<Object[]>list = query.getResultList();
        
        for(Object[] e:list){
            System.out.println("Departamento");
            System.out.println("Nombre departamento: " +e[0]);
            System.out.println("Localidad: " +e[1]);
            System.out.println("------------------------------------------------------------");
        }
    }
    
//--------------------------------------------------------------------------------------------------------------------------------    
    //EJEMPLOS UTILIZANDO LOS JPACONTROLLER
    public static void inicializaFactoryController(){
        emfactory = Persistence.createEntityManagerFactory("com.mycompany_proyectoJPA2_jar_1.0-SNAPSHOTPU");
        
    }
    
    /**
     * Cierro el factory
     */
    public static void cierraFactoryController(){
        emfactory.close();
    }
    
//    public static void insertaDepartamento(){
//        Departamentos
//    }
    
    public static void insertaDepartamentoConEmpleado(){
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
        
        Departamentos departamento = new Departamentos();
        departamento.setDeptNo( (short)99 );
        departamento.setDnombre("BIG DATA");
        departamento.setLoc("TOLEDO");
        
        
        empleado = new Empleados( (short)7521 );
        
        List<Empleados>empleadosCollection = new ArrayList<Empleados>();
        empleadosCollection.add(empleado);
        
        departamento.setEmpleadosCollection(empleadosCollection);
        
        
        try {
            departamentosJpaController.create(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void borrarDepartamentoController(){
        
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);

        
        try {            
            departamentosJpaController.destroy( (short)99 );
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void listarDepartamentos(){
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
        
        List<Departamentos> departamentosListado;
        
        departamentosListado = departamentosJpaController.findDepartamentosEntities();
        
        for (Departamentos d: departamentosListado){
            System.out.println("Nombre dpto: "+ d.getDnombre());
        }
    }
    
    
    public static void listarDepartamentoConEmpleados(int inputIdDept){
        
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
        
        departamento = departamentosJpaController.findDepartamentos( (short)inputIdDept );
        
        Collection<Empleados>listaEmpleados = departamento.getEmpleadosCollection();
        
        for (Empleados e: listaEmpleados){
            System.out.println("Apellido emple: "+ e.getApellido());
        }
    }
    
    
    public static void listarDepartamentosPorTramos(){
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
        List<Departamentos> departamentosListado;

        
        System.out.println("TODOS LOS DEPARTAMENTOS");
        listarDepartamentos();
        System.out.println("--------------------------------------------------------------");
        
        //***********************************************************************************
        System.out.println("Trae 3 registros empezando en la pos 0");
        
        // 3 = NUMERO DE REGISTROS MAXIMO
        // 0 = POS PARRTIR DE LA CUAL LO QUEIRO
        departamentosListado = departamentosJpaController.findDepartamentosEntities(3, 0);
        
        for (Departamentos d: departamentosListado){
            System.out.println("Nombre dpto: "+ d.getDnombre());
        }
        System.out.println("--------------------------------------------------------------");
        //****************************************************************************************
        System.out.println("Trae 3 registros empezando en la pos 1");
        
        // 3 = NUMERO DE REGISTROS MAXIMO
        // 1 = POS PARRTIR DE LA CUAL LO QUEIRO
        departamentosListado = departamentosJpaController.findDepartamentosEntities(3, 1);
        
        for (Departamentos d: departamentosListado){
            System.out.println("Nombre dpto: "+ d.getDnombre());
        }
        System.out.println("--------------------------------------------------------------");

    }
    
    
    public static void contarNumeroDepartamentos(){
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
        
        int nElementos = departamentosJpaController.getDepartamentosCount();
        
        System.out.println("Nº de departamentos: " +nElementos);

    }
    
    
    public static void listarUnDepartamento(){
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
        
        Departamentos departamento = departamentosJpaController.findDepartamentos( (short)10 );
        
        System.out.println(departamento.getDnombre());

    }
    
    public static void modificarDepartamento(int id){
        DepartamentosJpaController departamentosJpaController = new DepartamentosJpaController(emfactory);
        
        Departamentos departamento = departamentosJpaController.findDepartamentos( (short)id );
        
        departamento.setDeptNo( (short)id );
        departamento.setDnombre("CONTABILIDAD");
        departamento.setLoc("MADRID");
        
        try {
            departamentosJpaController.edit(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoJPA2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
//--------------------------------------------------------------------------------------------------------------------------------
    //PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL ALMACENADAS EN LAS CLASES DE PERSISTENCIA
    //Da error
    public static void consultaAlmacenada(){
        
        Query query = entitymanager.createQuery("Departamentos.findAll");
        
        List<Departamentos>list = query.getResultList();
        
        for(Departamentos e:list){
            System.out.println("Nombre departamento: " +e.getDnombre());
        }
        
    }
    
    public static void consultaAlmacenadaConParametros(int deptNoP){
        
        Query query = entitymanager.createQuery("Departamentos.findByDeptNo");
        
        //"deptNo" --> Tiene que ser igual que el que aparezca en la quiery de Departamentos
        query.setParameter("deptNo", deptNoP);
        
        List<Departamentos>list = query.getResultList();
        
        for(Departamentos e:list){
            System.out.println("Nombre departamento: " +e.getDnombre());
        }
        
    }
    
//--------------------------------------------------------------------------------------------------------------------------------
    //PRUEBAS DE LECTURA UTILIZANDO CONSULTAS JPQL CON CRITERIAQUERY
    
    /**
     * Select d from Departamentos d
     */
    public static void consultaConCriteriaQuery(){
        CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
        
        CriteriaQuery<Departamentos> query = cb.createQuery(Departamentos.class);
        
        //
        Root<Departamentos> c = query.from(Departamentos.class); //Especificamos el from
        
        query.select(c); //Indicamos los campos a selleccionar. Como hemos puesto c queremos todos los campos del departamento
        
        List<Departamentos> list = entitymanager.createQuery(query).getResultList();
        
        for(Departamentos e:list){
            System.out.println("Nombre del departamento: " +e.getDnombre());
        }
    }
    
    /**
     * Select d.dnombre, d.loc from Departamentos d
     */
    public static void consultaConCriteriaQueryVariosCampos(){
        CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
        
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        
        
        Root<Departamentos> c = query.from(Departamentos.class); //Especificamos el from
        
        query.select( cb.array(c.get("dnombre"), c.get("loc") ) ); //Indicamos los campos a selleccionar
        
        List<Object[]> list = entitymanager.createQuery(query).getResultList();
        
        for(Object[] e:list){
            System.out.println("-------------------------------------------------------");
            System.out.println("Nombre del departamento: " +e[0]);
            System.out.println("Nombre de la localidad: " +e[1]);
            System.out.println("-------------------------------------------------------");
        }
    }
    
//--------------------------------------------------------------------------------------------------------------------------------
    //PRUEBAS DE MODIFICACION Y BORRADO UTILIZANDO JPQL
    
    /**
     * Modifico un departamento
     */
    public static void modificarDatosConJPQL(){
        Query query = entitymanager.createQuery("UPDATE Departamentos d SET d.dnombre = :valorNuevo WHERE d.deptNo = :deptNoV");
        
        
        query.setParameter("valorNuevo", "PRUEBAS");
        query.setParameter("deptNoV", (short)10 );
        
        entitymanager.getTransaction().begin();
        int updateCount = query.executeUpdate(); //Numero de filas modificadas
        entitymanager.getTransaction().commit();
    }
    
    /**
     * Borro un departamento 
     */
    //No lo borra de la bbdd
    public static void borrarDatosConJPQL(){
        Query query = entitymanager.createQuery("DELETE from Departamentos d WHERE d.deptNo = :deptNoV");
        
        
        query.setParameter("deptNoV", (short)99 );
        
        entitymanager.getTransaction().begin();
        int deleteCount = query.executeUpdate(); //Numero de filas modificadas
        System.out.println("Filas modificadas: " + deleteCount);
        entitymanager.getTransaction().commit();
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
