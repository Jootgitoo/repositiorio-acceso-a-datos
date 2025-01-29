--Crea un supertipo con nombre T_NOTICIA, con 5 campos:
--• Id_noticia (NUMBER(10))
--• Fecha (DATE)
--• Titulo (VARCHAR2(30)
--• Descripcion (VARCHAR2(100))
--• Pais (VARCHAR2(40))
--• Añade al supertipo:
--• Una función final (F_DIAS_NOTICIA) que muestre los días que han
--transcurrido desde la fecha de la noticia hasta la fecha actual.
--• Una función (F_NOTICIA_DONDE) que se pueda sobreescribir que muestre
--la localización de la noticia (atributo país).
create or replace type t_noticia as object(
    id_noticia number(10),
    fecha Date,
    titulo varchar2(30),
    descripcion varchar2(100),
    pais varchar2(40),
    
    final member function f_dias_noticia return number,
    member function f_noticia_donde return varchar2
    
)not final;

--Creamos el body (no lo pide pero lo necesitamos)
create or replace type body t_noticia as
    final member function f_dias_noticia return number is
        numeroDias number;
        begin
            numeroDias := trunc( sysdate - fecha );
            return numeroDias;
        end;

    member function f_noticia_donde return varchar2 is
        begin
            return pais;
        end;
end;


--Crea un subtipo de T_NOTICIA con nombre T_NOTICIA_LOCAL, con 1 campo:
--• Localidad (VARCHAR2(100))
--• Sobreescribe la función F_NOTICIA_DONDE para que muestre el campo
--localidad del subtipo.
create or replace type t_noticia_local under t_noticia(
    localidad varchar2(100),
    overriding member function f_noticia_donde return varchar2
);


--Escribe un bloque anónimo que:
--• Declare un objeto de tipo T_NOTICIA.
--• Rellene el objeto con datos.
--• Muestre todos los datos de los atributos del objeto.
--• Muestre el resultado de llamar a las funciones F_DIAS_NOTICIA y F_NOTICIA_DONDE
set serveroutput on;

DECLARE
    n1 t_noticia := t_noticia( 1, Date '2025-01-01', 'Noticia 1', 'Descripcion noticia uno', 'Spain' );
    
    n_dias number;
    n_localidad varchar(100);
BEGIN
    
    DBMS_OUTPUT.PUT_LINE('ID: ' || n1.ID_NOTICIA);
    DBMS_OUTPUT.PUT_LINE('Fecha: ' || TO_CHAR(n1.FECHA, 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Título: ' || n1.TITULO);
    DBMS_OUTPUT.PUT_LINE('Descripción: ' || n1.DESCRIPCION);
    DBMS_OUTPUT.PUT_LINE('País: ' || n1.PAIS);
    
    n_dias := n1.f_dias_noticia();
    n_localidad := n1.f_noticia_donde();
    
    DBMS_OUTPUT.PUT_LINE('Días desde la noticia: ' || n_dias);
    DBMS_OUTPUT.PUT_LINE('Ubicación de la noticia: ' || n_localidad);
END;


--Escribe un bloque anónimo que:
--• Declare un objeto de tipo T_NOTICIA_LOCAL.
--• Rellene el objeto con datos.
--• Muestre todos los datos de los atributos del objeto.
--• Muestre el resultado de llamar a las funciones F_DIAS_NOTICIA y F_NOTICIA_DONDE
create or replace TYPE BODY T_NOTICIA_LOCAL AS
    OVERRIDING MEMBER FUNCTION F_NOTICIA_DONDE RETURN VARCHAR2 IS
    
    BEGIN
        RETURN LOCALIDAD;
    END;
END;


DECLARE
    n1 t_noticia_local := t_noticia_local( 2, Date '2025-01-01', 'Noticia 2', 'Descripcion noticia dos', 'Spain', 'Ciudad Real' );

    
    n_dias number;
    n_localidad varchar2(100);
begin
    DBMS_OUTPUT.PUT_LINE('Localidad: ' || n1.localidad);
    
    n_dias := n1.f_dias_noticia();
    n_localidad := n1.f_noticia_donde();

    
    DBMS_OUTPUT.PUT_LINE('Días desde la noticia: ' || n_dias);
    DBMS_OUTPUT.PUT_LINE('Ubicación de la noticia: ' || n_localidad);
END;


--Crea una tabla (TABLA_T_NOTICIA_LOCAL) de objetos T_NOTICIA_LOCAL.
create table tabla_t_noticia_local of t_noticia_local

--• Introduce datos en la tabla mediante insert.
insert into tabla_t_noticia_local values (1, sysdate, 'Noticia 1', 'Descripcion noticia uno', 'Spain', 'Ciudad Real');

--• Crea un bloque anónimo que inserte varios objetos en la tabla TABLA_T_NOTICIA_LOCAL.
DECLARE
    n1 t_noticia_local := t_noticia_local(2, sysdate, 'Noticia 2', 'Descripcion noticia dos', 'Spain', 'Daimiel');
    n2 t_noticia_local := t_noticia_local(3, sysdate, 'Noticia 3', 'Descripcion noticia tres', 'Spain', 'Puertollano');
BEGIN
    INSERT INTO tabla_t_noticia_local VALUES ( n1 );

    INSERT INTO tabla_t_noticia_local VALUES ( n2 );

    COMMIT;
END;
--• Realiza una select que muestre los datos.
select * from tabla_t_noticia_local