# String a Integer

```java
Integer entero = Integer.valueOf(cadena);
// o
int entero = Integer.parseInt(cadena);
```

_Ejemplo:_
```java
Integer a = Integer.valueOf("900");
// o
int b = Integer.parseInt("900");
```
------
# Integer a String

```java
String cadena = Integer.toString(entero);
// o
String cadena = String.valueOf(entero);
```

_Ejemplo:_
```java
String importe = Integer.toString(900);
// o
int entero = 900;
String mensaje = String.valueOf(entero);
```
------
# Char a String

```java
String cadena = Character.toString(char);
```

_Ejemplo:_
```java
char codigo = 'A';
String cadena = Character.toString(codigo);
```
-------
# String a char

```java
char caracter = cadena.charAt(0); //Solo primer caracter
```

_Ejemplo:_

```java
String codigo= "E";
char caracter = cadena.charAt(0);
```
---
# String a Double

```java
double doble = Double.parseDouble(cadena);
```

_Ejemplo:_
```java
double doble = Double.parseDouble("900.1");
```
---
# Double a String

```java
String cadena = String.valueOf(doble);
```

_Ejemplo:_
```java
double totalDoble = 900.5;
String totalString = String.valueOf(doble);
```
---
# String a Float

```java
float flotante = Float.parseFloat(cadena);
```

_Ejemplo:_
```java
float importe = Float.parseFloat("900.5");
```
---
# Float a String

```java
String cadena = Float.toString(flotante);
```

_Ejemplo:_
```java
String total = Float.toString(900.1f);
```
-----
# String a Boolean

```java
Boolean boolean = Boolean.valueOf(cadena);
// o
boolean boolean = Boolean.parseBoolean(cadena);
```

_Ejemplo:_
```java
Boolean boolean = Boolean.valueOf("true");
// o
boolean boolean = Boolean.parseBoolean("false");
```
------
# Boolean a String

```java
String cadena = String.valueOf(b);
// o
String cadena = Boolean.toString(b);
```

_Ejemplo:_
```java
boolean b = true;
String cadena = String.valueOf(b);
// o
boolean b = false;
String cadena = Boolean.toString(b);
``````