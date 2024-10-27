##  ACCDAT | RESULTADOS 1 | CLASE FILE
###   METODO | BORRADO RECURSIVO
```java
public void customDeleteRecursive (File folderPath) {
    if (folderPath.isFile()) {
      folderPath.delete();
      System.out.println("Fichero: " + folderPath.getName() + " borrado con exito.");
    } else if (folderPath.isDirectory()) {
      String[] internalInfo = folderPath.list();
      
      for (int i=0; i < internalInfo.length; i++) {
        File tempData = new File(folderPath, internalInfo[i]);
        
        if (!tempData.isDirectory()) {
          if (tempData.delete()) {
            System.out.println("Fichero: " + tempData.getName() + " borrado con exito.");
          } else {
            System.out.println("No se pudo borrar el archivo " + tempData.getName());
          }
        } else {
          customDeleteRecursive(tempData);
        }
      }
      
      if (folderPath.delete()) {
        System.out.println("Directorio: " + folderPath.getName() + " borrado con exito.");
      } else {
        System.out.println("No se pudo borrar el directorio " + folderPath.getName());
      }
      
      System.out.println("Archivos borrados con exito.");
    }
  }
```
###   METODO | COPIAR ARCHIVO
```java
public void copiarArchivo (String rutaOrigenInput, String rutaDestinoInput) {
    File archivoBase = new File (rutaOrigenInput);
    File archivoCopiado = new File (rutaDestinoInput);
    
    try (FileInputStream inputStream = new FileInputStream(archivoBase); FileOutputStream outputStream = new FileOutputStream(archivoCopiado)) {
      
      byte[] tempData = new byte[1024];
      int length;
      
      while ((length = inputStream.read(tempData)) > 0) {
        outputStream.write(tempData, 0, length);
      }
      
      System.out.println("Archivo copiado exitosamente.");
    } catch (IOException e) {
      System.out.println("Error al copiar el archivo: " + archivoBase.getName());
    }
}
```
###   METODO | RENOMBRAR ARCHIVO
```java
public boolean renombrarArchivo(String nombreBaseInput, String nombreNuevoInput) {
    File archivoOriginal = new File(nombreBaseInput);
    if (archivoOriginal.exists()) {
      File archivoRenombrado = new File(archivoOriginal.getParent(), nombreNuevoInput);
      boolean exito = archivoOriginal.renameTo(archivoRenombrado);
      if (exito) {
        System.out.println("Archivo renombrado exitosamente.");
        return true;
      } else {
        System.out.println("No se pudo renombrar el archivo.");
        return false;
      }
    } else {
      System.out.println("El archivo original no existe.");
      return false;
    }
}
```