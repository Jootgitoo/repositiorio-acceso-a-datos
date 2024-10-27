public class nombre_de_la_clase implements InterfazVista {
    
	private final BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
	private Controlador controladorGeneral;
	private String ruta;

	public nombre_de_la_clase () {
            super();
	}

	private String leerString () {
            try {
                return in.readLine();
                
            } catch (IOException e) {
                System.out.println("ERROR! Introduce correctamente la cadena.");
                return null;
            }
	}

	private int leerOpcion () {
            try {
                String opcion = in.readLine();
                return Integer.parseInt(opcion);

            } catch (IOException | NumberFormatException e) {
                opcionInvalida();
                return 0;
            }
	}

	private void procesarNuevaOperacion () {
            mostrarMenu();
            int opcion;
	    
            opcion = leerOpcion();
	    
            switch (opcion) {
                case 0 -> {
                    System.out.println("\n");
                    System.exit(0);
                }
	      
                case 1 -> {
                  this.controlRegistros.actionPerformed(new ActionEvent(this, opcion, LEER_EMPLEADO));
                }
            }

	  procesarNuevaOperacion();
	}

	@Override
	public void arranca() {
	  procesarNuevaOperacion();
	}

	@Override
	public void operacionExitosa () {
	  System.out.println("Operacion realizada con Exito!");
	}

	@Override
	public void escribeResultado (String inputCadena) {
	  System.out.println(inputCadena);
	}

	@Override
	public void setControladorRegistros (ControladorRegistros cR) {
	  this.controlRegistros = cR;
	}

	@Override
	public String getRuta () {
	  System.out.println(" > Introduce la ruta: ");
	  return leerString();
	}
}