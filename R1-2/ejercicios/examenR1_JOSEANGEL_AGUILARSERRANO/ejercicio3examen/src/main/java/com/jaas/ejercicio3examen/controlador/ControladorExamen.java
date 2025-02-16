/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jaas.ejercicio3examen.controlador;

import com.jaas.ejercicio3examen.modelo.Examen;
import com.jaas.ejercicio3examen.vista.InterfazExamen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.w3c.dom.Element;

/**
 *
 * @author JAAS by Jose Angel Aguilar Serrano
 * @version 1.0
 * Created on 13 nov 2024
 */
public class ControladorExamen implements ActionListener{

    private final InterfazExamen vista;
    private final Examen modelo;
    
    public ControladorExamen(InterfazExamen vista,Examen modelo) {
        this.modelo=modelo;
        this.vista=vista;
        
        this.vista.setControladorE(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        modelo.cargarArchivoEnMemoria("./RESOURCES/listaCompra.xml");
        
        String producto=vista.leerProducto();
        int cantidad = vista.leerCantidad();
        String supermercado=vista.leerSupermercado();
        
        Element elem = modelo.addNodo("producto");
        modelo.addNodoYTexto("nombre", producto, elem);
        modelo.addNodoYTexto("cantidad", Integer.toString(cantidad), elem);
        modelo.addNodoYTexto("supermercado", supermercado, elem);
        modelo.generarArchivodelDOM("./RESOURCES/listaCompra.xml");
    }

}
