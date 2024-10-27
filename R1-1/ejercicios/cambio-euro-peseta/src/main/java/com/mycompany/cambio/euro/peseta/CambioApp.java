/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.mycompany.cambio.euro.peseta;

import java.util.Scanner;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * @version 1.0
 * created on $(date)
 */
public class CambioApp {

    public static void main(String[] args) {
        
        System.out.println("¿Que operación desea realizar?");
        
        System.out.println("1. Cambiar de euros a pesetas");
        System.out.println("2. Cambiar de pesetas a euros");
        System.out.println("3. Salir");
        
        
        Scanner sc = new Scanner (System.in);
        int operacion = sc.nextInt();
        
        if (operacion == 1){ //Aqui decimo que si elige la primera opción cambiamos de euros a pesetas
            System.out.print("¿Cuantos euros quieres pasar a pesetas?");
            double eurosACambiar = sc.nextDouble();
            double solucion = CambioEuroPeseta.eurosToPeseta(eurosACambiar);
            System.out.println(eurosACambiar + "€ son " + solucion + " pesetas");
            
        } else if (operacion == 2){ //Aqui decimos que si elige la segunda opcion cambiamos de pesetas a euros
            System.out.println("¿Cuantas pesetas quieres pasar a euros?");
            double peseatasACambiar = sc.nextDouble();
            double solucion = CambioEuroPeseta.pesetaToEuro(peseatasACambiar);
            System.out.println(peseatasACambiar + " pesetas son " + solucion + "€");
            
        } else {
            System.exit(-1);
        }
        
        
        sc.close();
    }
}
