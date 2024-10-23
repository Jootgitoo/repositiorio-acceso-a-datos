##  ACCDAT | RESULTADOS 1 | FILE DOM TO BINARY
###   METODO | INICIALIZAR DOCUMENTBUILDER
```java
private void initializeDocumentBuilder() {
    try {
      this.docFactory = DocumentBuilderFactory.newInstance();
      docFactory.setIgnoringElementContentWhitespace(true);
      this.docBuilder = this.docFactory.newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      Logger.getLogger(BinaryConversor.class.getName()).log(Level.SEVERE, null, ex);
    }
}
```
###   METODO | CARGAR FICHERO XML
```java
private void loadXmlFile() {
    try {
      File xmlFile = new File(getPath()+ "/" + xmlFilename);
      this.document = this.docBuilder.parse(xmlFile);
      this.document.getDocumentElement().normalize();
    } catch (SAXException | IOException ex) {
      Logger.getLogger(BinaryConversor.class.getName()).log(Level.SEVERE, null, ex);
    }
}
```
###   METODO | OBTENER OBJETOS
```java
private String getTagValue (String inputTag, Element inputElement) {
    NodeList nodeList = inputElement.getElementsByTagName(inputTag).item(0).getChildNodes();
    Node dataNode = nodeList.item(0);
    
    if (dataNode != null) {
      return dataNode.getNodeValue();
    } else return null;
}
  
private Producto getProducto (Node inputNode) {
    Producto returnProduct = new Producto();
    
    if (inputNode.getNodeType() == Node.ELEMENT_NODE) {
      Element tempElement = (Element) inputNode;
      
      returnProduct.setId(Long.parseLong(getTagValue("Id", tempElement)));
      returnProduct.setNombre(getTagValue("Nombre", tempElement));
      returnProduct.setPrecio(Double.parseDouble(getTagValue("Precio", tempElement)));
      returnProduct.setCantStock(Integer.parseInt(getTagValue("Stock", tempElement)));
    }
    
    return returnProduct;
}
  
private List<Producto> getProductos () {
    List<Producto> listProducts = new ArrayList<>();
    NodeList nodeList = this.document.getElementsByTagName("Producto");
    
    for (int i=0; i<nodeList.getLength(); i++) {
      listProducts.add(getProducto(nodeList.item(i)));
    }
    
    return listProducts;
}
```
###   METODO | EXPORTAR A BINARY FILE
```java
public void exportDataToBinary () {
    loadXmlFile();
    List<Producto> listProducts = getProductos();
    
    for (Producto tempProduct : listProducts) {
      writer.saveProduct(tempProduct);
    }
}
```
