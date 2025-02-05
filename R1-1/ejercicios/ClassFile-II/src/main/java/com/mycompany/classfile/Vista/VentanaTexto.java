
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.classfile.Vista;
import com.mycompany.classfile.Controlador.ControladorArchivo;
import com.mycompany.classfile.Controlador.ControladorCarpeta;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on 26 sept 2024
 */
public class VentanaTexto implements InterfazVista {
  private final BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
  private ControladorArchivo controladorArchivo;
  private ControladorCarpeta controladorCarpeta;
  private String ruta;
  
  public VentanaTexto () {
    super();
  }
  
  //------------------------------------------------>
   // metodo leerString | lee una candena de texto terminal =>
  private String leerString () {
    try {
      return in.readLine();
    } catch (IOException e) {
      System.out.println("ERROR! Introduce correctamente la cadena.");
      return null;
    }
  }
  
   // metodo leerOpcion | lee numero por terminal =>
  private int leerOpcion () {
    try {
      String opcion = in.readLine();
      return Integer.parseInt(opcion);
    } catch (IOException | NumberFormatException e) {
      opcionInvalida();
      return 0;
    }
  }
  
  private void mostrarMenu () {
    System.out.println("\n\n");
    System.out.println("Indica la operacion que quieres realizar: ");
    System.out.println(" 1: crear carpeta con la ruta.");
    System.out.println(" 2: crear carpeta pasando ruta padre y nombre.");
    System.out.println(" 3: crear carpeta pasando un File y nombre.");
    System.out.println(" 4: crear archivo con la ruta y nombre.");
    System.out.println(" 5: obtener el contenido de una carpeta.");
    System.out.println(" 6: borrar fichero o ficheros dentro de una carpeta.");
    System.out.println(" 7: renombrar archivo existente.");
    System.out.println(" 8: copiar archivo a nueva ruta.");
    System.out.println(" 9: mover archivo a nueva ruta.");
    System.out.println(" 10: borrar contenido de una carpeta.");
    System.out.println(" 11: Obtener el contenido de una carpeta utilizando la clase NIO");
    System.out.println(" 12: Borrar el contenido de una carpeta utilizando la clase NIO");
    System.out.println("13: Renombramos un archivo utilizando la clase NIO");
    System.out.println("14: Copiamos un archivo utlizando la clase NIO");
      System.out.println("15: Movemos un archivo de una ruta a otra utilizando la clase NIO");


    
    System.out.println("\n 0: salir.");
  }
  
  private void procesarNuevaOpcion () {
    mostrarMenu ();
    int opcion;
    opcion = leerOpcion ();
    
    switch (opcion) {
      case 0 -> {
        System.out.println("\n");
        System.exit(0);
      }
      case 1 -> {
        this.controladorCarpeta.actionPerformed(new ActionEvent(this, opcion, CREAR_CARPETA_CON_RUTA_COMPLETA));
      }
      case 2 -> {
        this.controladorCarpeta.actionPerformed(new ActionEvent(this, opcion, CREAR_CARPETA_CON_RUTA_PADRE_Y_NOMBRE));
      }
      case 3 -> {
        this.controladorCarpeta.actionPerformed(new ActionEvent(this, opcion, CREAR_CARPETA_CON_FILE_Y_NOMBRE));
      }
      case 4 -> {
        this.controladorArchivo.actionPerformed(new ActionEvent(this, opcion, CREAR_ARCHIVO_CON_RUTA_Y_NOMBRE));
      }
      case 5 -> {
        this.controladorCarpeta.actionPerformed(new ActionEvent(this, opcion, OBETENER_CONTENIDO_CARPETA));
      }
      case 6 -> {
        this.controladorCarpeta.actionPerformed(new ActionEvent(this, opcion, BORRAR_FICHEROS_CARPETA));
      }
      case 7 -> {
        this.controladorArchivo.actionPerformed(new ActionEvent(this, opcion, RENOMBRAR_ARCHIVO_EXISTENTE));
      }
      case 8 -> {
        this.controladorArchivo.actionPerformed(new ActionEvent(this, opcion, COPIAR_ARCHIVO_NUEVA_RUTA));
      }
      case 9 -> {
        this.controladorArchivo.actionPerformed(new ActionEvent(this, opcion, MOVER_ARCHIVO_NUEVA_RUTA));
      }
      case 10 -> {
        this.controladorCarpeta.actionPerformed(new ActionEvent(this, opcion, BORRAR_FICHEROS_CARPETA_RECURSIVO));
      }
      case 11 -> {
        this.controladorCarpeta.actionPerformed(new ActionEvent(this, opcion, OBETENER_CONTENIDO_CARPETA_CLASE_NIO));
      }
      case 12 -> {
        this.controladorCarpeta.actionPerformed(new ActionEvent(this, opcion, BORRAR_FICHEROS_CARPETA_CLASE_NIO));
      }
      case 13 -> {
        this.controladorArchivo.actionPerformed(new ActionEvent(this, opcion, RENOMBRAR_ARCHIVO_EXISTENTE_CLASE_NIO));
      }
      case 14 -> {
          this.controladorArchivo.actionPerformed(new ActionEvent(this, opcion, COPIAR_ARCHIVO_CLASE_NIO));
      }
      case 15 -> {
          this.controladorArchivo.actionPerformed(new ActionEvent(this, opcion, MOVER_ARCHIVO_CLASE_NIO));
      }
    }
    
    procesarNuevaOpcion ();
    
  }
  
  private void opcionInvalida () {
    System.out.println("ERROR! Opcion Invalida.");
  }
  
  //------------------------------------------------>
  @Override
  public void setControladorArchivo (ControladorArchivo cA) {
    this.controladorArchivo = cA;
  }
  
  @Override
  public void setControladorCarpeta (ControladorCarpeta cC) {
    this.controladorCarpeta = cC;
  }
  
  @Override
  public void arranca () {
    procesarNuevaOpcion ();
  }
  
  @Override
  public void operacionExitosa () {
    System.out.println("Operacion realizada con Exito!");
  }
  
  @Override
  public void escribeResultado (String cadenaOutput) {
    System.out.println("\n" + cadenaOutput + "\n");
  }
  
  @Override
  public void limpiarCampos () {
    System.out.println("");
  }
  
    //-------------------------------------------|
  @Override
  public String getRuta () {
    System.out.println(" > Introduce la ruta: ");
    return leerString();
  }
  
  @Override
  public String getNombre () {
    System.out.println(" > Introduce el nombre: ");
    return leerString();
  }
  
  @Override
  public String getNuevaRuta () {
    System.out.println(" > Introduce la nueva ruta: ");
    return leerString();
  }
  
  @Override
  public String getNombreBase () {
    System.out.println(" > Introduce el nuevo nombre: ");
    return leerString();
  }
}
