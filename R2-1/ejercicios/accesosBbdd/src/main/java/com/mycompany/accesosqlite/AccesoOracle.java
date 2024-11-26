/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.accesosqlite;

import com.mycompany.accesosqlite.modelo.Conexion;
import com.mycompany.accesosqlite.modelo.ConexionOracle;

/**
 *
 * @author JHM by Jorge Herrera Martín
 */
public class AccesoOracle {

    public static void main(String[] args) {
        
        ConexionOracle conecta = new ConexionOracle();
        
        conecta.Conectar();
        conecta.cerrarConexion();
    }
}
