##  ACCDAT | RESULTADOS 1 | CLASE FILE NIO
###   METODO | CREAR ARCHIVO
```java
public void crearArchivo(String rutaInput, String nombreInput) {
    Path directorio = Paths.get(rutaInput);
    Path archivo = directorio.resolve(nombreInput);
    
    try {
        // Crear los directorios si no existen
        if (Files.notExists(directorio)) {
            Files.createDirectories(directorio);
        }
        // Crear el archivo
        if (Files.notExists(archivo)) {
            Files.createFile(archivo);
            System.out.println("Archivo creado exitosamente en: " + archivo.toString());
        } else {
            System.out.println("El archivo ya existe.");
        }
    } catch (IOException e) {
        System.out.println("Error al crear el archivo: " + e.getMessage());
    }
}
```
###   METODO | RENOMBRAR ARCHIVO NIO
```java
public void renombrarArchivoNIO(String rutaInput, String nombreActual, String nombreNuevo) {
    Path archivoActual = Paths.get(rutaInput, nombreActual);
    Path archivoNuevo = archivoActual.resolveSibling(nombreNuevo); // Ruta del nuevo archivo

    try {
      Files.move(archivoActual, archivoNuevo);
      System.out.println("Archivo renombrado exitosamente de " + nombreActual + " a " + nombreNuevo);
    } catch (NoSuchFileException e) {
      System.out.println("El archivo no existe: " + archivoActual.toString());
    }  catch (FileAlreadyExistsException e) {
      System.out.println("Ya existe un archivo con el nombre: " + nombreNuevo);
    } catch (IOException e) {
      System.out.println("Error al renombrar el archivo: " + e.getMessage());
    }
}
```
###   METODO | ELIMINAR ARCHIVO
```java
public void eliminarArchivoODirectorio(String rutaInput, String nombreInput) {
    Path ruta = Paths.get(rutaInput, nombreInput);
        
    try {
      if (Files.isDirectory(ruta)) {
         // Si es un directorio, eliminar los archivos dentro
        Files.list(ruta).forEach(archivo -> {
          try {
            Files.delete(archivo);
          } catch (IOException e) {
            System.out.println("Error al eliminar el archivo: " + archivo.toString() + ". " + e.getMessage());
          }
        });
       // Eliminar el directorio después de vaciarlo
      Files.delete(ruta);
      System.out.println("Directorio y sus archivos eliminados exitosamente.");
      } else if (Files.isRegularFile(ruta)) {
         // Si es un archivo, eliminarlo directamente
        Files.delete(ruta);
        System.out.println("Archivo eliminado exitosamente.");
      } else {
        System.out.println("La ruta especificada no existe.");
      }
    } catch (IOException e) {
      System.out.println("Error al eliminar: " + e.getMessage());
    }
}
```
###   METODO | CREAR CARPETA
```java
public void crearCarpeta (File mainFolder, String newFolderName) {
    File newDirectory = new File (mainFolder, newFolderName);
    newDirectory.mkdir();
}
```
###   METODO | CUSTOM DIR
```java
public ArrayList<String> customDir (File folderPath) {
    ArrayList<String> informacion = new ArrayList<>();
    
    if (folderPath.isDirectory()) {
      informacion.add("Contenido Directorio: ");
      String[] infoRaw = folderPath.list();
      
      for (int i=0; i<infoRaw.length; i++) {
        File content = new File(infoRaw[i]);
        
        String fileName = content.getName();
        informacion.add("Contenido " + (i+1) + ": " + fileName);
      }
    } else if (folderPath.isFile()) {
      informacion.add("Informacion Fichero: ");
      
      String fileName = folderPath.getName();
      String fileSize = Long.toString(folderPath.length());
      informacion.add("Nombre: " + fileName + " | Tamaño: " + fileSize);
    }
    
    return informacion;
}
```
###   METODO | CUSTOM DELETE RECURSIVE
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