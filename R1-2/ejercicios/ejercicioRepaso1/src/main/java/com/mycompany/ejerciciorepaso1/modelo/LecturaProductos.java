/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciorepaso1.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 22 oct 2024
 */
public class LecturaProductos extends FicheroProductos{
    
    public LecturaProductos(String ruta){
        super(ruta);
    }
    
//---------------------------------------------------------------
    //Métodos
    
    public LinkedList<Producto> leerProducto() {
        
        LinkedList<Producto> listaProductos = new LinkedList<Producto>();
        
        RandomAccessFile randomFile = null;
        
        try{
            
            randomFile = new RandomAccessFile(getRuta(), "rw");
            
            for (int posicion=0; posicion < randomFile.length(); posicion++){
                
                randomFile.seek(posicion);
                
                //Para leer el apellido
                byte[] arrayNombre= new byte [super.getLONGITUD_NOMBRE()];

                
                Producto productoTemporal = new Producto();
                
                //Leo cada cosa del producto
                productoTemporal.setId(randomFile.readInt());
                
                randomFile.read(arrayNombre);
                productoTemporal.setNombre(new String(arrayNombre));
                
                productoTemporal.setPrecio(randomFile.readDouble());
                
                productoTemporal.setStock(randomFile.readInt());
                
                listaProductos.add(productoTemporal);
                
                
                posicion += super.getLONGITUD_TOTAL() + 1;
            }
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LecturaProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LecturaProductos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(LecturaProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listaProductos;
    }
    
    
    public Producto buscarProductoId(int idProducto){
        
        RandomAccessFile randomFile = null;
        
        Producto producto = new Producto();
        
        long posicion = (idProducto - 1) * super.getLONGITUD_TOTAL();
        
        byte[] arrayId = new byte [super.getLONGITUD_IDENTIFICADOR()];
        
        try {

            randomFile = new RandomAccessFile(getRuta(), "rw");
            
            randomFile.seek(posicion);
            

               if (posicion < randomFile.length() && idProducto == randomFile.readInt()){

                   
                    producto.setId(randomFile.readInt());

                    randomFile.read(arrayId);
                    producto.setNombre(new String (arrayId));

                    producto.setPrecio(randomFile.readDouble());

                    producto.setStock(randomFile.readInt());

                } 

  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LecturaProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LecturaProductos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(LecturaProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        
        
        return producto;
    }

}
