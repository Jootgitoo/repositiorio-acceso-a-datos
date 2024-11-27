--------------------------------------------------------
-- Archivo creado  - martes-noviembre-26-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table DEPARTAMENTOS
--------------------------------------------------------

  CREATE TABLE "DAM2"."DEPARTAMENTOS" 
   (	"DEPT_NO" NUMBER(2,0), 
	"DNOMBRE" VARCHAR2(15), 
	"LOC" VARCHAR2(15)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EMPLEADOS
--------------------------------------------------------

  CREATE TABLE "DAM2"."EMPLEADOS" 
   (	"EMP_NO" NUMBER(4,0), 
	"APELLIDO" VARCHAR2(10), 
	"OFICIO" VARCHAR2(10), 
	"DIR" NUMBER(4,0), 
	"FECHA_ALT" DATE, 
	"SALARIO" NUMBER(6,2), 
	"COMISION" NUMBER(6,2), 
	"DEPT_NO" NUMBER(2,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into DAM2.DEPARTAMENTOS
SET DEFINE OFF;
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('10','CONTABILIDAD','SEVILLA');
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('20','INVESTIGACIÓN','MADRID');
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('30','VENTAS','BARCELONA');
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('40','PRODUCCIÓN','BILBAO');
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('15','Informatica','Madrid');
REM INSERTING into DAM2.EMPLEADOS
SET DEFINE OFF;
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7369','SANCHEZ','EMPLEADO','7902',to_date('17/12/90','DD/MM/RR'),'2000',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7499','ARROYO','VENDEDOR','7698',to_date('20/02/90','DD/MM/RR'),'1500','390','30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7521','SALA','VENDEDOR','7698',to_date('22/02/91','DD/MM/RR'),'1625','650','30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7566','JIMENEZ','DIRECTOR','7839',to_date('02/04/91','DD/MM/RR'),'3860',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7654','MARTIN','VENDEDOR','7698',to_date('29/09/91','DD/MM/RR'),'1600','1020','30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7698','NEGRO','DIRECTOR','7839',to_date('01/05/91','DD/MM/RR'),'3005',null,'30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7782','CEREZO','DIRECTOR','7839',to_date('09/06/91','DD/MM/RR'),'6350,5',null,'10');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7788','GIL','ANALISTA','7566',to_date('09/11/91','DD/MM/RR'),'3960',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7839','REY','PRESIDENTE',null,to_date('17/11/91','DD/MM/RR'),'7930',null,'10');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7844','TOVAR','VENDEDOR','7698',to_date('08/09/91','DD/MM/RR'),'1350','0','30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7876','ALONSO','EMPLEADO','7788',to_date('23/09/91','DD/MM/RR'),'2390',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7900','JIMENO','EMPLEADO','7698',to_date('03/12/91','DD/MM/RR'),'1335',null,'30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7902','FERNANDEZ','ANALISTA','7566',to_date('03/12/91','DD/MM/RR'),'3960',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7934','MUÑOZ','EMPLEADO','7782',to_date('23/01/92','DD/MM/RR'),'4797',null,'10');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('1','Herrera','Informatic','555',to_date('02/02/00','DD/MM/RR'),'2795,43','1500,8','10');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('2','Dominguez','Carterista','33',to_date('01/01/24','DD/MM/RR'),'113,41','14,89','15');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('3','Rayo','Profesor','54',to_date('01/01/24','DD/MM/RR'),'113,76','11','15');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('33','Campos',null,null,null,'1123,46',null,null);
--------------------------------------------------------
--  DDL for Procedure P_NOMBRE_DEPART
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "DAM2"."P_NOMBRE_DEPART" (
ndepart IN NUMBER,
nombre_depart OUT VARCHAR2)
AS
BEGIN

SELECT dnombre INTO nombre_depart
FROM departamentos
WHERE dept_no = ndepart;

END;



/
--------------------------------------------------------
--  DDL for Procedure P_SUBIDA_SAL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "DAM2"."P_SUBIDA_SAL" (
    no_dep IN NUMBER,
    subida IN NUMBER,
    salarioActualizado OUT NUMBER)
AS
BEGIN
    UPDATE EMPLEADOS
    SET salario = salario + subida
    WHERE dept_no = no_dep;

    COMMIT;

END p_subida_sal;



/
--------------------------------------------------------
--  DDL for Function F_N_EMPLEADO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "DAM2"."F_N_EMPLEADO" (
    no_dept IN NUMBER)
    
RETURN NUMBER
AS 
    v_num_empleados NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_num_empleados
    FROM EMPLEADOS
    WHERE dept_no = no_dept;

RETURN v_num_empleados;

END F_N_EMPLEADO;



/
--------------------------------------------------------
--  DDL for Function F_NOMINA
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "DAM2"."F_NOMINA" (
    p_salario NUMBER,
    p_comision NUMBER,
    p_irpf NUMBER
)

RETURN NUMBER

AS 
    v_salario_bruto NUMBER;
    v_salario_neto NUMBER;
BEGIN
    -- Calcular el salario bruto 
    v_salario_bruto := p_salario + p_comision;

    -- Calcular el salario neto
    v_salario_neto := v_salario_bruto - (v_salario_bruto * (p_irpf / 100));

    -- Devolver el salario neto
  RETURN v_salario_neto;

END F_NOMINA;



/
--------------------------------------------------------
--  Constraints for Table DEPARTAMENTOS
--------------------------------------------------------

  ALTER TABLE "DAM2"."DEPARTAMENTOS" MODIFY ("DEPT_NO" NOT NULL ENABLE);
  ALTER TABLE "DAM2"."DEPARTAMENTOS" ADD PRIMARY KEY ("DEPT_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table EMPLEADOS
--------------------------------------------------------

  ALTER TABLE "DAM2"."EMPLEADOS" MODIFY ("EMP_NO" NOT NULL ENABLE);
  ALTER TABLE "DAM2"."EMPLEADOS" ADD PRIMARY KEY ("EMP_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLEADOS
--------------------------------------------------------

  ALTER TABLE "DAM2"."EMPLEADOS" ADD CONSTRAINT "FK_DEP" FOREIGN KEY ("DEPT_NO")
	  REFERENCES "DAM2"."DEPARTAMENTOS" ("DEPT_NO") ENABLE;
