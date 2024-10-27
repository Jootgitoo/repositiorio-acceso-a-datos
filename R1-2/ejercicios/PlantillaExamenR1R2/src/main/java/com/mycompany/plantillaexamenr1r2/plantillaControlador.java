public class nombre_de_la_clase implements ActionListener {
    
    private final InterfazVista vista;
  
    private final Modelo modelo;

    public ControladorRegistros (InterfazVista inputVista, Modelo inputModelo) {
        this.vista = inputVista;
        this.modelo = inputModelo;
        this.vista.setControlador(this);
    }

    public void actionPerformed (ActionEvent evento) {
        switch (evento.getActionCommand()) {
            case InterfazVista.CONSTANTE_EJEMPLO -> {
                this.vista.operacionExitosa();
            }
        }
    }
}