##  ACCDAT | RESULTADOS 1 | RANDOM ACCESS FILE
###   METODO | READ STRING
```java
private String readString (RandomAccessFile inputFile, int inputLength) throws IOException {
    char[] charArray = new char[inputLength];
    
    for (int i = 0; i < inputLength; i++) {
      charArray[i] = inputFile.readChar();
    }
    
    return new String(charArray).trim();
}
```
###   METODO | MOSTRAR REGISTROS
```java
public void mostrarRegistros () throws IOException {
    try (RandomAccessFile file = new RandomAccessFile(getPath(), "r")) {
      while (file.getFilePointer() < file.length()) {
        long identificador = file.readLong();
        String apellido = readString(file, CARACTERES_APELLIDO);
        int departamento = file.readInt();
        double salario = file.readDouble();
                
        if (identificador != 0) {
          System.out.printf("ID: %d, Apellido: %s, Departamento: %d, Salario: %.2f%n",
          identificador, apellido, departamento, salario);
        }
      }
    }
}
```
###   METODO | LEER OBJETO
```java
public Empleado lecturaEmpleado (int inputId) {
    RandomAccessFile randomFile = null;
    int pos = 0;
    Empleado tempEmpleado = new Empleado();
    byte[] cadena = new byte[super.getLongitudApellido()];
    
    try {
      randomFile = new RandomAccessFile(getPath(), "r");
      pos = (inputId - 1) * super.getLongitudTotal();
      
      if (pos < randomFile.length()) {
        randomFile.seek(pos);
        tempEmpleado.setIdentificador(randomFile.readLong());
        
      }
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        randomFile.close();
        
      } catch (IOException ex) {
        Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    return tempEmpleado;
  }
```
###   METODO | WRITE STRING
```java
private void writeString (RandomAccessFile inputFile, String inputString, int inputLength) throws IOException {
    StringBuffer buffer = new StringBuffer(inputString);
    buffer.setLength(inputLength);
    inputFile.writeChars(buffer.toString());
}
```
###   METODO | MODIFICAR OBJETO
```java
public void modificarApellidoEmpleado(int inputId, String nuevoApellido) throws IOException {
    RandomAccessFile randomFile = null;
    StringBuffer buffer = null;
    
    try {
      randomFile = new RandomAccessFile(getPath(), "rw");
      while (randomFile.getFilePointer() < randomFile.length()) {
        long pos = randomFile.getFilePointer();
        long idActual = randomFile.readLong();
            
        if (idActual == inputId) {
          randomFile.seek(pos + LONGITUD_IDENTIFICADOR);
                
          buffer = new StringBuffer(nuevoApellido);
          buffer.setLength(super.getCaracteresApellido());
          randomFile.writeChars(buffer.toString());
        }
            
        randomFile.skipBytes(LONGITUD_TOTAL - LONGITUD_IDENTIFICADOR);
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Archivo no encontrado", ex);
    } catch (IOException ex) {
      Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Error de E/S", ex);
    } finally {
      if (randomFile != null) {
        try {
          randomFile.close();
        } catch (IOException ex) {
          Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Error al cerrar el archivo", ex);
        }
      }
    }
}
```
###   METODO | ESCRIBIR OBJETO
```java
public void escribirEmpleadoFinalArchivo (Empleado inputEmpleado) {
    RandomAccessFile randomFile = null;
    long pos = 0;
    StringBuffer buffer = null;
    
    try {
      randomFile = new RandomAccessFile(getPath(), "rw");
      
      if (randomFile.length() != 0) {
        pos = randomFile.length();
      }
      
      randomFile.seek(pos);
      randomFile.writeLong((pos/super.getLongitudTotal()) + 1);
      
      buffer = new StringBuffer(inputEmpleado.getApellido());
      buffer.setLength(super.getCaracteresApellido());
      randomFile.writeChars(buffer.toString());
      
      randomFile.writeInt(inputEmpleado.getDepartamento());
      randomFile.writeDouble(inputEmpleado.getSalario());
      
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        randomFile.close();
      } catch (IOException ex) {
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}
```