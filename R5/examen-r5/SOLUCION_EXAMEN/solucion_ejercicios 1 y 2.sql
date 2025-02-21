-----------------------------------------------------
--NOMBRE Y APELLIDOS: Jorge Herrera Martín
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------


-----------------------------------------------------
--EJERCICIO 1:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------

a) /clientes/clien/nombre/text()
-----------------------------------------------------
b) /detallefacturas/factura[@numero = 11]
-----------------------------------------------------
c) /detallefacturas/factura[@numero = '11']/(/producto/codigo | referencia)
-----------------------------------------------------
d) format-number( sum( /detallefacturas/factura/producto/unidades ), '#')
-----------------------------------------------------
e) /clientes/clien[ contains(tlf, '91') ]
-----------------------------------------------------
f) format-number( round( avg( /detallefacturas/factura/producto/unidades ) ), '#.00')
-----------------------------------------------------




-----------------------------------------------------
--EJERCICIO 2:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------

a)update insert
    <producto descuento = '0.13'>
        <codigo>5</codigo>
        <unidades>6</unidades>
    </producto>
    into /detallefacturas/factura[@numero = '10']
    
-----------------------------------------------------
b)  /detallefacturas/factura[codigo = 'FAC10']
-----------------------------------------------------
c) 
-----------------------------------------------------
d) update delete /detallefacturas/factura[codigo = 'FACT10']/producto[ codigo = '5']
-----------------------------------------------------
e) update rename /detallefacturas/factura/referencia as 'refer'




-----------------------------------------------------
--EJERCICIO 3:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------

 public static void mostrarClientes(){
        
        String textoConsulta = "/clientes/clien";
        
        try {
            XQPreparedExpression consulta;
            XQResultSequence resultado;
            
            consulta = connection.prepareExpression(textoConsulta);
            resultado = consulta.executeQuery();
            
            XQResultItem r_item;
            
            while(resultado.next()){
                r_item = (XQResultItem) resultado.getItem();
                System.out.println(r_item.getItemAsString(null));
            }
        } catch (XQException ex) {
            Logger.getLogger(ProyectoExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

-----------------------------------------------------
--EJERCICIO 4:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------

private static void eliminarProductos(){
        
        String consulta = "update delete /productos/product[@categoria = 'A' and @pvp < 200]";
        
        try {
            
            
            XQExpression expresion;
            
            expresion = connection.createExpression();
            
            expresion.executeCommand(consulta);
            
            
        } catch (XQException ex) {
            Logger.getLogger(ProyectoExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Modificacion realizada con exito");
    }

-----------------------------------------------------
