/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.llamadafuncionfnominasqldeveloper;

import com.mycompany.llamadafuncionfnominasqldeveloper.bbdd.OperacionesBBDD;
import java.sql.ResultSet;
import java.util.Optional;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class LlamadaFuncionFNominaSQLDeveloper {

    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstance();
    private static Optional<ResultSet> rs;
    
    
    public static void main(String[] args) {
        bbdd.abrirConexion();
        
        bbdd.llamadaFuncionf_nomina(1000, 500, 20);
        
        bbdd.cerrarConexion();
    }
}
