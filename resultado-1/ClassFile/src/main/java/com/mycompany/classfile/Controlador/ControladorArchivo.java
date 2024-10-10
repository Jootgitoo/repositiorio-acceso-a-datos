/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.classfile.Controlador;

import com.mycompany.classfile.Modelo.Archivo;
import com.mycompany.classfile.Vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Es la clase encargada de comunicar la vista con el modelo
 * Como esta clase está interesada en procesar un evento de acción
 * entonces debe implementar la interfaz ActionListener
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 20 sept 2024
 */
public class ControladorArchivo implements ActionListener {
  private final InterfazVista vista;
  private final Archivo modelo;
  
  private String ruta;
  private String nombre;
  
  public ControladorArchivo (InterfazVista vistaInput, Archivo modeloInput) {
    this.vista = vistaInput;
    this.modelo = modeloInput;
    this.vista.setControladorArchivo (this);
  }
  
  //------------------------------------------------>
  @Override
  public void actionPerformed (ActionEvent evento) {
    switch (evento.getActionCommand()) {
      case InterfazVista.CREAR_ARCHIVO_CON_RUTA_Y_NOMBRE -> {
        this.ruta = this.vista.getRuta();
        this.nombre = this.vista.getNombre();
        
        this.modelo.crearArchivo(ruta, nombre);
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
      
      case InterfazVista.RENOMBRAR_ARCHIVO_EXISTENTE -> {
        this.nombre = this.vista.getNombreBase();
        String nuevoNombre = this.vista.getNombre();
        
        this.modelo.renombrarArchivo(nombre, nuevoNombre);
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
      
      case InterfazVista.COPIAR_ARCHIVO_NUEVA_RUTA -> {
        this.ruta = this.vista.getRuta();
        String nuevaRuta = this.vista.getNuevaRuta();
        
        this.modelo.copiarArchivo(ruta, nuevaRuta);
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
      
      case InterfazVista.MOVER_ARCHIVO_NUEVA_RUTA -> {
        this.ruta = this.vista.getRuta();
        String nuevaRuta = this.vista.getNuevaRuta();
        
        this.modelo.moverArchivo(ruta, nuevaRuta);
        this.vista.operacionExitosa();
        this.vista.limpiarCampos();
      }
    }
  }
  
  //------------------------------------------------>
  //------------------------------------------------>
  //------------------------------------------------>

}