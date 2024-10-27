public interface InterfazVista {
    static final String CONSTANTE_EJEMPLO = "esto es una constante de ejemplo";
	
    public void setControlador(Controlador inputControlador);
    public void arranca();
    public void operacionExitosa();
    public void escribeResultado(String cadenaTexto);
    public String getRuta();
}