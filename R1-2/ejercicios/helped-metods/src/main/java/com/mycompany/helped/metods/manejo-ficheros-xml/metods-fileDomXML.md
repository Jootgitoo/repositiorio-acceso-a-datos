##  ACCDAT | RESULTADOS 1 | FILE DOM XML
###   METODO | CREAR ARCHIVO EN MEMORIA
```java
public GestionContenidoDOM (String inputNombre) {
    try {
      this.docFactory = DocumentBuilderFactory.newInstance();
      docFactory.setIgnoringElementContentWhitespace(true);
      this.docBuilder = this.docFactory.newDocumentBuilder();
      
      DOMImplementation domImplementation = this.docBuilder.getDOMImplementation();
      this.documento = (Document) domImplementation.createDocument(null, inputNombre, null);
      this.documento.setXmlVersion("1.0");
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    System.out.println(" > documento '" + inputNombre + "' creado en memoria.");
}
```
###   METODO | PRE-PROCESAR ARCHIVO
```java
private Transformer preProcess (String inputIndent) {
    Transformer domTransformer = null;
    
    try {
      domTransformer = TransformerFactory.newInstance().newTransformer();
      domTransformer.setOutputProperty(OutputKeys.INDENT, inputIndent);
    } catch (TransformerConfigurationException ex) {
      Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return domTransformer;
}
```
###   METODO | AGREGAR NODOS
```java
public Element addNodo (String inputNombreNodo) {
    Element mainNodo = this.documento.createElement(inputNombreNodo);
    this.documento.getDocumentElement().appendChild(mainNodo);
    return mainNodo;
}
  
   // metodo "addNodo" | agrega un nodo hijo a otro nodo ->
public Element addNodo (String inputDatoEmple, Element nodoPadre) {
    Element dato = this.documento.createElement(inputDatoEmple);
    nodoPadre.appendChild(dato);
    
    return dato;
}
  
   // metodo "addNodoTexto" | agrega a un nodo un valor ->
public void addNodoTexto (String inputDatoEmple, String inputTexto, Element nodoRoot) {
    Element dato = this.documento.createElement(inputDatoEmple);
    Text textoData = this.documento.createTextNode(inputTexto);
    
    dato.appendChild(textoData);
	
    nodoRoot.appendChild(dato);
}
```
###   METODO | GENERAR FICHERO REAL
```java
public void generateFileFromDOM (String inputFilename, String inputIndent) {
    try {
      Source domSource = new DOMSource(this.documento);
      Result domResult = new StreamResult(new File(inputFilename));
      
      preProcess(inputIndent).transform(domSource, domResult);
    } catch (TransformerException ex) {
      Logger.getLogger(GestionContenidoDOM.class.getName()).log(Level.SEVERE, null, ex);
    }
}
```
