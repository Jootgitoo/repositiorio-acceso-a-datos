/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.llamadafuncionfnempleadosqldeveloper;

import com.mycompany.llamadafuncionfnempleadosqldeveloper.bbdd.OperacionesBBDD;
import java.sql.ResultSet;
import java.util.Optional;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class LlamadaFuncionFNEmpleadoSQLDeveloper {

    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstance();
    private static Optional<ResultSet> rs;
    
    
    public static void main(String[] args) {
        bbdd.abrirConexion();
        
        bbdd.llamadaFuncionf_n_empleado(10);
        
        bbdd.cerrarConexion();
    }
}
