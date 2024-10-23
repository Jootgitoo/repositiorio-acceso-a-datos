##  ACCDAT | RESULTADOS 1 | FILE DOM CONVERSOR
###   METODO | CREAR CONVERSOR
```java
private Source dataOrigin = null;
private Source stylePage = null;
private FileOutputStream htmlPage = null;

public Conversor (String inputDataOrigin, String inputStylePage, String inputHTMLOutput) {
    try {
      this.dataOrigin = new StreamSource(inputDataOrigin);
      this.stylePage = new StreamSource(inputStylePage);
      this.htmlPage = new FileOutputStream(new File(inputHTMLOutput));
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
    }
}
```
###   METODO | CONVERTIR XML A HTML
```java
public void ConvertToHTML () {
    try {
      Result returnResult = new StreamResult(this.htmlPage);
      
      Transformer transformer = TransformerFactory.newInstance().newTransformer(this.stylePage);
      transformer.transform(this.dataOrigin, returnResult);
    } catch (TransformerConfigurationException ex) {
      Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TransformerException ex) {
      Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        this.htmlPage.close();
      } catch (IOException ex) {
        Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}
```
