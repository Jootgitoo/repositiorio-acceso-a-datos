/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examenr1jorgeherreramartin;

import com.mycompany.examenr1jorgeherreramartin.modelo.MetodosUniversidades;
import com.mycompany.examenr1jorgeherreramartin.modelo.Universidad;
import java.io.IOException;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class ExamenR1JorgeHerreraMartin {

    public static void main(String[] args) throws IOException {
        
        
        MetodosUniversidades u = new MetodosUniversidades();
        
        boolean exito1 = u.altaDatosCarrerasUniversitarias();
        System.out.println(exito1);

        
    }
}
