##  ACCDAT | RESULTADOS 1 | CLASE FILE STREAMS
###   METODO | LEER FICHERO LINEA
```java
public StringBuffer leerFicheroLinea () {
    StringBuffer outputContent = new StringBuffer();
    
    try {
      File inputFile = new File(this.path);
      BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile));
      String line;
      
      while ((line = bufferReader.readLine()) != null) {
        outputContent.append(line);
        outputContent.append(System.lineSeparator());
      }
      
      bufferReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return outputContent;
}
```
###   METODO | ESCRIBIR FICHERO LINEA
```java
public void escribirFicheroLinea (String inputLine) {
    try {
      File outputFile = new File(this.path);
      
      if (!outputFile.exists()) {
        outputFile.createNewFile();
      }
      
      FileWriter writerOut = new FileWriter(outputFile, true);
      
      writerOut.write(inputLine);
      writerOut.write(System.lineSeparator());
      
      writerOut.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
```
###   METODO | DES/ENCRIPTAR FICHERO
```java
private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
private static final String CIFRADO = "4bcd3fgh1jklmzopqp5@7vwxy6";

 // metodo "encriptarFichero" =>
public void encriptarFichero (String inputPath) {
    this.path = inputPath;
    File inputFile = new File(inputPath);
    String parentPath = inputFile.getParent();
    
    String encriptContent = encriptarContenido(inputFile).toString();
    
    File encriptFile = new File(parentPath, "encripted.txt");
    this.path = encriptFile.getAbsolutePath();
    escribirFicheroLinea(encriptContent);
}
  
  // metodo "desencriptarFichero" =>
public void desencriptarFichero (String inputPath) {
    this.path = inputPath;
    File inputFile = new File(inputPath);
    
    String parentPath = inputFile.getParent();
    String disencriptContent = desencriptarContenido(inputFile).toString();
    
    File disencriptFile = new File(parentPath, "disencript.txt");
    this.path = disencriptFile.getAbsolutePath();
    escribirFicheroLinea(disencriptContent);
}
  
  // metodo "encriptarContenido" =>
public StringBuffer encriptarContenido (File inputFile) {
    this.path = inputFile.getAbsolutePath();
    
    StringBuffer rawContentBuffer = leerFicheroCaracter();
    StringBuffer encriptContent = new StringBuffer();
      
    String rawContent = rawContentBuffer.toString();
    rawContent = rawContent.toLowerCase();
      
    for (char c : rawContent.toCharArray()) {
      int indice = ALFABETO.indexOf(c);
        
      if (indice != -1) {
        encriptContent.append(CIFRADO.charAt(indice));
      } else {
        encriptContent.append(c);
      }
    }
    
    return encriptContent;
}
  
  // metodo "desencriptarContenido" =>
public StringBuffer desencriptarContenido (File inputFile) {
    this.path = inputFile.getAbsolutePath();
    
    StringBuffer encriptContentBuffer = leerFicheroCaracter();
    String encriptContent = encriptContentBuffer.toString();
    encriptContent = encriptContent.toLowerCase();
    
    StringBuffer disencriptContent = new StringBuffer();
    
    for (char c : encriptContent.toCharArray()) {
      int indice = CIFRADO.indexOf(c);
      
      if (indice != -1) {
        disencriptContent.append(ALFABETO.charAt(indice));
      } else {
        disencriptContent.append(c);
      }
    }
    
    return disencriptContent;
}
```
