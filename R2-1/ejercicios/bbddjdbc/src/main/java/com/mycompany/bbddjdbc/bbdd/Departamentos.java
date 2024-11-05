/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bbddjdbc.bbdd;

import java.sql.SQLException;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 4 nov 2024
 */
public class Departamentos {
    
    private int dept_no;
    private String dnombre;
    private String loc;
    
    //Creamos la instancia del patron Singleton
    static OperacionesBBDD bbdd = OperacionesBBDD.getInstance(); 

    public Departamentos(int dept_no, String dnombre, String loc) {
        this.dept_no = dept_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }

    public void insertarDepartamento() throws SQLException{
        bbdd.insert("insert into Departamentos values (?,?,?)", this.dept_no, this.dnombre, this.loc);
    }

    
    
    public int getDept_no() {
        return dept_no;
    }

    public String getDnombre() {
        return dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

}
