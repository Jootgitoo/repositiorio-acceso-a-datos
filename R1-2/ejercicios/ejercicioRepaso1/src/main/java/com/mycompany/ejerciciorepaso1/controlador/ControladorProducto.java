/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.ejerciciorepaso1.controlador;

import com.mycompany.ejerciciorepaso1.modelo.LecturaProductos;
import com.mycompany.ejerciciorepaso1.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 22 oct 2024
 */
public class ControladorProducto implements ActionListener{

    private final InterfazVista vista;
    private final LecturaProductos modelo;
    
    private int id;
    
    public ControladorProducto (InterfazVista vistaInput, LecturaProductos modeloInput) {
    this.vista = vistaInput;
    this.modelo = modeloInput;
    this.vista.setControladorProducto (this);
  }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        switch(evento.getActionCommand()){
            
            case InterfazVista.LEER_PRODUCTO -> {
                
                
                
                this.modelo.leerProducto();
                this.vista.operacionExitosa();
                
            }
            case InterfazVista.BUSCAR_PRODUCTO_ID -> {
                
                this.modelo.buscarProductoId(id);
                this.vista.operacionExitosa();
            }
            
        }
    }
}
