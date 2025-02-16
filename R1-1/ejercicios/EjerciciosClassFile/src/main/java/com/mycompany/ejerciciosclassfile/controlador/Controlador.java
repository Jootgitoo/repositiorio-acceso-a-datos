/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciosclassfile.controlador;

import com.mycompany.ejerciciosclassfile.modelo.Archivo;
import com.mycompany.ejerciciosclassfile.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 15 feb 2025
 */
public class Controlador implements ActionListener{
    
    //ATRIBUTOS
    private final InterfazVista vista;
    private final Archivo modelo;
    
    private String ruta;
    private String nombre;
    
//-----------------------------------------------------------------
    //CONSTRUCTOR
    
    public Controlador(InterfazVista vista, Archivo modelo){
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControlador(this);
    }
    
//-----------------------------------------------------------------    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch(e.getActionCommand()){
            case InterfazVista.CREAR_ARCHIVO:{
                this.ruta = this.vista.getRuta();
                this.nombre = this.vista.getNombre();
                
                this.modelo.crearArchivos(ruta, nombre);
                
                this.vista.operacionExitosa();
            }
            case InterfazVista.MOSTRAR_CONTENIDO_DIRECTORIO:{
                this.ruta = this.vista.getRuta();
                
                this.modelo.mostrarContenidoDirectorio(ruta);
                
                this.vista.operacionExitosa();
            }
        }
    }

}
