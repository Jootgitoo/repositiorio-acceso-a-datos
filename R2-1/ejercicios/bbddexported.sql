--------------------------------------------------------
-- Archivo creado  - lunes-noviembre-11-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table DEPARTAMENTOS
--------------------------------------------------------

  CREATE TABLE "DAM2"."DEPARTAMENTOS" 
   (	"DEPT_NO" NUMBER(2,0), 
	"DNOMBRE" VARCHAR2(15), 
	"LOC" VARCHAR2(15)
   ) ;
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
   ) ;
REM INSERTING into DAM2.DEPARTAMENTOS
SET DEFINE OFF;
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('10','CONTABILIDAD','SEVILLA');
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('20','INVESTIGACIÓN','MADRID');
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('30','VENTAS','BARCELONA');
Insert into DAM2.DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('40','PRODUCCIÓN','BILBAO');
REM INSERTING into DAM2.EMPLEADOS
SET DEFINE OFF;
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7369','SANCHEZ','EMPLEADO','7902',to_date('17/12/90','DD/MM/RR'),'1040',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7499','ARROYO','VENDEDOR','7698',to_date('20/02/90','DD/MM/RR'),'1500','390','30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7521','SALA','VENDEDOR','7698',to_date('22/02/91','DD/MM/RR'),'1625','650','30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7566','JIMENEZ','DIRECTOR','7839',to_date('02/04/91','DD/MM/RR'),'2900',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7654','MARTIN','VENDEDOR','7698',to_date('29/09/91','DD/MM/RR'),'1600','1020','30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7698','NEGRO','DIRECTOR','7839',to_date('01/05/91','DD/MM/RR'),'3005',null,'30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7782','CEREZO','DIRECTOR','7839',to_date('09/06/91','DD/MM/RR'),'2885',null,'10');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7788','GIL','ANALISTA','7566',to_date('09/11/91','DD/MM/RR'),'3000',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7839','REY','PRESIDENTE',null,to_date('17/11/91','DD/MM/RR'),'4100',null,'10');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7844','TOVAR','VENDEDOR','7698',to_date('08/09/91','DD/MM/RR'),'1350','0','30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7876','ALONSO','EMPLEADO','7788',to_date('23/09/91','DD/MM/RR'),'1430',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7900','JIMENO','EMPLEADO','7698',to_date('03/12/91','DD/MM/RR'),'1335',null,'30');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7902','FERNANDEZ','ANALISTA','7566',to_date('03/12/91','DD/MM/RR'),'3000',null,'20');
Insert into DAM2.EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7934','MUÑOZ','EMPLEADO','7782',to_date('23/01/92','DD/MM/RR'),'1690',null,'10');
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
--  Constraints for Table DEPARTAMENTOS
--------------------------------------------------------

  ALTER TABLE "DAM2"."DEPARTAMENTOS" MODIFY ("DEPT_NO" NOT NULL ENABLE);
  ALTER TABLE "DAM2"."DEPARTAMENTOS" ADD PRIMARY KEY ("DEPT_NO")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table EMPLEADOS
--------------------------------------------------------

  ALTER TABLE "DAM2"."EMPLEADOS" MODIFY ("EMP_NO" NOT NULL ENABLE);
  ALTER TABLE "DAM2"."EMPLEADOS" ADD PRIMARY KEY ("EMP_NO")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLEADOS
--------------------------------------------------------

  ALTER TABLE "DAM2"."EMPLEADOS" ADD CONSTRAINT "FK_DEP" FOREIGN KEY ("DEPT_NO")
	  REFERENCES "DAM2"."DEPARTAMENTOS" ("DEPT_NO") ENABLE;
