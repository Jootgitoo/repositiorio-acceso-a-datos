/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.filestreamsbytes.modelo.objetos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 4 oct 2024
 */
public class MiObjectOutputStream extends ObjectOutputStream{
    
    public MiObjectOutputStream (OutputStream out) throws IOException{
        super(out);
    }
    
    protected MiObjectOutputStream() throws IOException{
        super();
    }
    
    protected void writeStreamHeader(){
        
    }

}
