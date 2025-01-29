-- Crea un tipo con nombre T_ALUMNO, con 4 atributos, uno de tipo PERSONA y
--tres que indican las notas de la primera, segunda y tercera evaluación.
create or replace type t_alumno as object (
    nota1 number,
    nota2 number,
    nota3 number,
    notaMedia number,
    member procedure calculcar_nota_media(nota1 number, nota2 number, nota3 number),
    constructor function t_alumno (nota1 number, nota2 number, nota3 number)return self as result
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
create table notasMedias (media number);

create or replace type body t_alumno as
    member procedure calculcar_nota_media(nota1 number, nota2 number, nota3 number) is
        begin
            self.nota1 := nota1;
            self.nota2 := nota2;
            self.nota3 := nota3;
            notaMedia := (nota1 + nota2 + nota3 ) / 3;
            insert into notasmedias (media) values (notaMedia);
            commit;
            dbms_output.put_line('Fila insertada');
        end;
        
    constructor function t_alumno (nota1 number, nota2 number, nota3 number)
    return self as result is
        begin
            self.nota1 := nota1;
            self.nota2 := nota2;
            self.nota3 := nota3;
            self.notaMedia := (nota1 + nota2 + nota3) /3;
            return;
        end;    
end;            


--Crea una tabla ALUMNOS2 del tipo T_ALUMNO e inserta objetos en ella
