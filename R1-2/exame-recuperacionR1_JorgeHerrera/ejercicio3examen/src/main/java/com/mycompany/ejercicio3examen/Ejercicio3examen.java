/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio3examen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author b15-18m
 */
public class Ejercicio3examen {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    
    public static void ejercicio3examen(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Indica el id: ");
        String id = scanner.nextLine();
        
        System.out.print("Indica la fecha de lanzamiento:");
        String fecha = scanner.nextLine();
        
        System.out.print("Indica el lugar de lanzamiento: ");
        String lugarLanzamiento = scanner.nextLine();
        
        System.out.print("Horas de vuelo estimadas: ");
        String horasVueloEstimadas = scanner.nextLine();
        
        
                
    }
    
    private Lanzamientos getLanzamiento(Node node){
        
        Lanzamientos lanzamiento = new Lanzamientos();
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            
            lanzamiento.setId( Integer.parseInt( getTagValue("id", element) ) );
            lanzamiento.setFecha(getTagValue("fecha_lanzamiento", element));
            lanzamiento.setLugar_lanzamiento(getTagValue("lugar_lanzamiento", element) );
            lanzamiento.setHoras_vuelos_estimadas( Integer.parseInt(getTagValue("horas_vuelo_estimadas", element)) );
        }
        return lanzamiento;
    }
    
    private String getTagValue(String tag, Element element){
        
        //Busca los que estén dentro del elemento tag
        NodeList nodeList = element. //Crea una lista de nodos que sean hijos del elemento
                getElementsByTagName(tag) //Saca todos los elementos hijos llamados tag
                .item(0). //Coge el primer valor que coincide con tag
                getChildNodes(); //Coge los nodos hijos (ignora atributos, por ejemplo)
        
        Node node = nodeList.item(0); //Creo un nodo con la informacion del nodo hijo
        
        if (node != null){
            return node.getNodeValue(); //Si nodo no esta vacio lo devuelvo
        } else {
            return null; //Si no existe ningun nodo que tenga la tag que buscamos devuelve null
        }       
    }
}
