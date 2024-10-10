/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.mvc.Vista;

import com.mycompany.mvc.Controlador.ControlConversor;
import static com.mycompany.mvc.Vista.InterfazVista.AEUROS;
import static com.mycompany.mvc.Vista.InterfazVista.APESETAS;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author JHM by Jorge Herrera Martín
 * version 1.0
 * Created on 13 sept 2024
 */
public class VentanaConversor extends JFrame implements InterfazVista{
    
    private final JButton convertirApesetas;
    private final JButton convertirAeuros;
    private final JTextField cantidad;
    private final JTextField comision;
    private final JLabel resultado;

    /**
     * Genera la interfaz gráfica
     */
    public VentanaConversor() {
        super("Conversor de Euros y Pesetas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(20, 20));
        
        comision = new JTextField(2);
        cantidad = new JTextField(15);
        
        JPanel panelaux = new JPanel();
        panelaux.add(comision);
        panelPrincipal.add(panelaux, BorderLayout.NORTH);
        
        resultado = new JLabel("Indique una cantidad y pulse uno de los botones");
        JPanel panelaux2 = new JPanel();
        panelaux2.add(resultado);
        panelPrincipal.add(panelaux2, BorderLayout.CENTER);
        
        
        JPanel panelaux3 = new JPanel();
        panelaux3.add(cantidad);
        panelPrincipal.add(panelaux3, BorderLayout.EAST);
        
        
        
        convertirApesetas = new JButton("A pesetas");
        // Le indocamos el ActionCommand para el botón y así luego saber desde que botón se ha llamado
        convertirApesetas.setActionCommand(APESETAS);
        // Le indocamos el ActionCommand para el botón y así luego saber desde que botón se ha llamado
        convertirAeuros = new JButton("A euros");
        convertirAeuros.setActionCommand(AEUROS);
        
     
        JPanel botonera = new JPanel();
        botonera.add(convertirApesetas);
        botonera.add(convertirAeuros);
        panelPrincipal.add(botonera, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal);
    
    }

    @Override
    public void setControlador(ControlConversor c) {
        convertirApesetas.addActionListener(c);
        convertirAeuros.addActionListener(c);
    }

    @Override
    public void arranca() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public double getCantidad() {
        try {
            return Double.parseDouble(cantidad.getText());
        } catch (NumberFormatException e){ //En caso de que el numero tenga un formato incorrecto me lanza la excepción 
            return 0.00;
        }
    }

    @Override
    public void escribeCambio(String s) {
        resultado.setText(s);
    }

    @Override
    public int getComision() {
        try {
            return Integer.parseInt(comision.getText());
        } catch (NumberFormatException e){
            return 0;
        }
    }
}
