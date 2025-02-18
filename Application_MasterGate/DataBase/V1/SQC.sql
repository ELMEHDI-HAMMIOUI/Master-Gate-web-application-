--------------------------------------------------------
--  Fichier créé - lundi-octobre-14-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence ETUDIANT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."ETUDIANT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence INFO_ACCADEMIQUE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."INFO_ACCADEMIQUE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence INSCRIPTION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."INSCRIPTION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 201 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence NOTIF_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."NOTIF_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 332 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_ETUDIANT
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SEQ_ETUDIANT"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 35 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SEQ_INFO_ACAD
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SEQ_INFO_ACAD"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 32 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 2 START WITH 45 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_CONDITION
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_CONDITION"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 247 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_CONVOCATION
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_CONVOCATION"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 221 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_FACULTE
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_FACULTE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 50 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_FILIERE
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_FILIERE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 23 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_FILIERE_ADMET_MASTER
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_FILIERE_ADMET_MASTER"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 455 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_INSCRIPTION
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_INSCRIPTION"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_LISTE_ATTENTE
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_LISTE_ATTENTE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 564 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_LISTE_FINALE
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_LISTE_FINALE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1421 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_MASTER
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_MASTER"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 286 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_NOTE_CONCOURS
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_NOTE_CONCOURS"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_PRESELECTION
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_PRESELECTION"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 485 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_PUBLICATION
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_PUBLICATION"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 321 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_RESPONSABLE
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_RESPONSABLE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 22 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence SQC_UNIVERSITE
--------------------------------------------------------

   CREATE SEQUENCE  "MEHDI"."SQC_UNIVERSITE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 23 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
