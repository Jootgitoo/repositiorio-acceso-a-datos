-- Crea un tipo con nombre T_ALUMNO, con 4 atributos, uno de tipo PERSONA y
--tres que indican las notas de la primera, segunda y tercera evaluación.
create or replace type t_alumno as object(
   pers Persona,
    notaPrimeraEva number,
    notaSegundaEva number,
    notaTerceraEva number,
    member procedure calcularNotaMedia,
    constructor function t_alumno (notaPrimeraEva number, notaSegundaEva number, notaTerceraEva number) return self as result
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
