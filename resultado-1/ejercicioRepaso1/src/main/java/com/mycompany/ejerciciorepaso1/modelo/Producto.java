package com.mycompany.ejerciciorepaso1.modelo;

import java.io.RandomAccessFile;
import java.util.LinkedList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 22 oct 2024
 */
public class Producto{
    
    //ATRIBUTOS
    private int id;
    private String nombre;
    private double precio;
    private int stock;

//------------------------------------------------------------------------------
    //CONSTRUCTOR

    public Producto(){
        
    }
    
    public Producto(int id, String nombre, double precio, int stock) {
        
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    
    
//------------------------------------------------------------------------------
    //Métodos

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
    
    
    
}


