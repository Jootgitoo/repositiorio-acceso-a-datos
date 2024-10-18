<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : empleadosPlantilla.xsl
    Created on : 18 de octubre de 2024, 9:26
    Author     : b15-18m
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   
    <xsl:template match='/'>
        <html><xsl:apply-templates/></html>
    </xsl:template>
    <xsl:template match='Empleados'>
        <head><title>LISTADO DE EMPLEADOS</title></head>
        <body>
            <h1>LISTA DE EMPLEADOS</h1>
            <table border='1'>
                <tr><th>Identificador</th></tr>
                <xsl:apply-templates select='Empleado' />
            </table>
        </body>
    </xsl:template>
    <xsl:template match='Empleado'>
        <tr><xsl:apply-templates /></tr>
    </xsl:template>
    
    <xsl:template match='identificador'>
        <td><xsl:apply-templates /></td>
    </xsl:template>
        
</xsl:stylesheet>
