--Realiza un procedimiento almacenado para insertar direcciones en la tabla
--EJEMPLO_TABLA_ANIDADA.
--• El procedimiento recibe como parámetros un identificador y un objeto
--DIRECCION. Debe visualizar un mensaje indicando si se ha insertado o no la
--dirección.
--• Se deben hacer las siguientes comprobaciones y visualizar los mensajes
--correspondientes:
--• Comprobar si el identificador existe, si no existe es un caso de error,
--visualizar el mensaje.
--• Que la tabla anidada no sea null, si no es null hay que hacer un UPDATE
--y no un INSERT.
--• Que la dirección no exista ya en la tabla, si ya existe visualiza que no se
--puede insertar.

CREATE OR REPLACE PROCEDURE insertar_direcciones(
    identificador NUMBER, 
    direccionAInsertar DIRECCION
) AS
    lista_direcciones TABLA_ANIDADA;
    id_existente NUMBER;
BEGIN
    -- Verificar si el identificador existe
    SELECT COUNT(*) INTO id_existente FROM EJEMPLO_TABLA_ANIDADA WHERE ID = identificador;

    IF id_existente = 0 THEN
        DBMS_OUTPUT.PUT_LINE('El ID pasado no existe.');
    ELSE
        -- Obtener la lista de direcciones actual
        BEGIN
            SELECT direc INTO lista_direcciones 
            FROM EJEMPLO_TABLA_ANIDADA 
            WHERE ID = identificador;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                lista_direcciones := TABLA_ANIDADA(); -- Inicializar si no tiene direcciones
        END;

        -- Insertar la nueva dirección
        lista_direcciones.EXTEND; -- Extender la tabla anidada
        lista_direcciones(lista_direcciones.LAST) := direccionAInsertar; -- Agregar la nueva dirección
        
        -- Actualizar la tabla con la nueva lista de direcciones
        UPDATE EJEMPLO_TABLA_ANIDADA 
        SET direc = lista_direcciones 
        WHERE ID = identificador;
        
        DBMS_OUTPUT.PUT_LINE('Dirección insertada correctamente.');
    END IF;
END insertar_direcciones;


--• Obtén el número de direcciones que tiene en cada ciudad el identificador 1.
select count(*) numeroDirecciones, t_direc.ciudad from ejemplo_tabla_anidada, table(direc) t_direc where id = 1 group by t_direc.ciudad;


--Obtén la ciudad con más direcciones que tiene el identificador 1.
select count(*)numeroDirecciones, t_direc.ciudad from ejemplo_tabla_anidada, table(direc) t_direc 
    where id = 1 
    group by id, t_direc.ciudad
    Having count(*) = (SELECT MAX(COUNT(*)) FROM EJEMPLO_TABLA_ANIDADA, TABLE(DIREC) ALIAS_DIRECC
                        WHERE ID=1
                        GROUP BY ID, ALIAS_DIRECC.CIUDAD);



--Realiza un bloque PL/SQL que muestre el nombre de las calles de cada apellido.
select ej_tb_ani.apellidos, t_direc.ciudad from ejemplo_tabla_anidada ej_tb_ani, table(direc) t_direc group by ej_tb_ani.apellidos, t_direc.ciudad;


--Realiza un procedimiento PL/SQL que recibe un identificador y visualiza las calles que tiene.
create or replace procedure visualizar_calles(identificador number)
is
    cursor c1 is select ej_tb_ani.id, t_anidada.calle  from ejemplo_tabla_anidada ej_tb_ani, table(direc) t_anidada where  ej_tb_ani.id = identificador;
begin
    for i in c1 loop
        DBMS_OUTPUT.PUT_LINE(i.CALLE || ' ');
    end loop;
end;

--Crea un bloque que llame al procedimiento.
DECLARE

BEGIN
    VISUALIZAR_CALLES(1);
END;


--Realiza una función PL/SQL que comprueba si existe una dirección en un identificador concreto. 
--La función recibe un identificador y un tipo DIRECCION, devuelve un mensaje indicando si existe o no la dirección.
--Primero se comprobará si existe el identificador, si no existe o si existen varias filas en el mismo se devuelve un mensaje indicándolo.
CREATE OR REPLACE FUNCTION existe_calle(identificador NUMBER, direccionAComprobar VARCHAR2)
RETURN VARCHAR2 
IS
    existeId NUMBER;
    calleEncontrada VARCHAR2(255); -- Ajusta el tamaño según la longitud de la columna calle
BEGIN

    SELECT COUNT(*) INTO existeId FROM ejemplo_tabla_anidada WHERE id = identificador;
    
    IF existeId = 0 THEN
        RETURN 'No existe el ID';
    END IF;
    

    BEGIN
        SELECT t_anid.calle INTO calleEncontrada FROM ejemplo_tabla_anidada ej_tb_anid, table(direc) t_anid  WHERE  ej_tb_anid.id = identificador  AND t_anid.calle = direccionAComprobar;
        
        RETURN 'Sí existe la dirección';
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 'No existe la dirección';
    END;

END existe_calle;

