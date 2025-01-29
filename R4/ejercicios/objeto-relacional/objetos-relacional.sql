-- Crea un tipo con nombre T_ALUMNO, con 4 atributos, uno de tipo PERSONA y
--tres que indican las notas de la primera, segunda y tercera evaluación.
create or replace type t_alumno as object (
    pers Persona,
    nota1 number,
    nota2 number,
    nota3 number,
    member function calculcar_nota_media return number
    );

--Después crea un bloque PL/SQL e inicializa un objeto de ese tipo.
declare 
    dir direccion;
    p persona;
    alumno t_alumno;
begin
    dir := new direccion ('C/Alcornoque 10', 'Cordoba', 50002);
    p := new persona (2, 'Jorge', dir, SYSDATE);
    alumno := new t_alumno(p, 7, 8, 9);
end;


--Crea un método y el cuerpo del mismo en el tipo T_ALUMNO que devuelva la
--nota media del alumno.
create or replace type body t_alumno as
    member function calculcar_nota_media return number is
        begin
           return (nota1 + nota2 + nota3 ) /3;
        end calculcar_nota_media;
end;           


--Crea la tabla ALUMNOS2 del tipo T_ALUMNO e inserta objetos en ella.
create table alumnos2 of t_alumno;
--• Al menos 2 alumnos que sean de CIUDAD REAL
insert into alumnos2 values (Persona(1, 'Jorge', Direccion('C/Paloma 6', 'Ciudad Real', 13005), sysdate ), 8.8, 9, 5);
insert into alumnos2 values (Persona(2, 'Mateo', Direccion('C/Fiesta 7', 'Ciudad Real', 13005), sysdate ), 4.5, 6, 2);
--• Al menos 2 alumnos que sean de GUADALAJARA
insert into alumnos2 values (Persona(3, 'Juan', Direccion('C/Palomino 56', 'Guadalajara', 14009), sysdate ), 8.4, 6, 5);
insert into alumnos2 values (Persona(4, 'Jota', Direccion('C/Futbol 43', 'Guadalajara', 14009), sysdate ), 4, 6, 6);


--Realiza las siguientes consultas:
--• Muestra el nombre de los alumnos y la nota media
select a.pers.nombre, a.calculcar_nota_media() from alumnos2 a;

--• Muestra alumnos de GUADALAJARA con una nota media mayor de 6
select a.pers.nombre from alumnos2 a where a.calculcar_nota_media() > 6 and a.pers.direc.ciudad = 'Guadalajara';

--• Muestra el nombre del alumno con más nota media
select a.pers.nombre, a.calculcar_nota_media()  from alumnos2 a where a.calculcar_nota_media() = ( Select Max(al.calculcar_nota_media()) From alumnos2 al );


--Realiza una modificación de una de las notas de un alumno.
alter type t_alumno add attribute notaMedia number(4,2) cascade;

Update alumnos2 al set al.notaMedia = 10 where al.pers.nombre = 'Jorge';
--• Realiza la modificación de los datos personales de uno de los alumnos.
Update alumnos2 al set al.pers.nombre = 'Rigoberto' where al.pers.nombre = 'Juan';
--• Verifica que los datos se han modificado.
select * from alumnos2;


--Elimina aquellos alumnos que son de CIUDAD REAL.
Delete from alumnos2 al where al.pers.direc.ciudad = 'Ciudad Real';

--• Verifica que se han eliminado.
select * from alumnos2;