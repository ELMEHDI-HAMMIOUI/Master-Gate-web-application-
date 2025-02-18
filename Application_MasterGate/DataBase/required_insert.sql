    -----Universites---- id_universite , nom                                  ,ville,surnom   ,web_site,              ,logo  
    INSERT INTO Universite VALUES (1, 'Universite Mohamed-V'                 ,'Rabat'      ,'UM5'   ,'http://www.um5.ac.ma',  'http://www.um5.ac.ma/um5/sites/default/files/um5-wide.png');
    INSERT INTO Universite VALUES (2, 'Universite Hassan-II'                 ,'Casablanca' ,'UnivH2','https://www.univh2c.ma', 'https://www.univh2c.ma/wp-content/uploads/2021/12/logo.jpg');
    INSERT INTO Universite VALUES (3, 'Universite Sidi Mohamed Ben Abdellah' ,'Fes'        ,'USMBA' ,'http://www.usmba.ac.ma','https://www.usmba.ac.ma/~usmba2/wp-content/uploads/2019/06/usmba_90h.png');
    INSERT INTO Universite VALUES (4, 'Universite Mohammed-I'                ,'Oujda'      ,'UMP'   ,'http://www.ump.ma', 'https://www.ump.ma/assets/logo.webp');
    INSERT INTO Universite VALUES (5, 'Universite Cadi Ayyad'                ,'Marrakech'  ,'UCA'   ,'http://www.uca.ma', 'https://www.uca.ma/public/website/theme-3/images/logo-dark.png');
    INSERT INTO Universite VALUES (6, 'Universite Moulay Smail'              ,'Meknes'     ,'UMI'   ,'http://www.umi.ac.ma', 'https://www.umi.ac.ma/wp-content/themes/umi/images/logo.png');
    INSERT INTO Universite VALUES (7, 'Universite Abdelmalek Essaadi'        ,'Tanger'     ,'UAE'   ,'http://www.uae.ma', 'https://www.uae.ac.ma/sites/default/files/logo.png');
    INSERT INTO Universite VALUES (8, 'Universite Chouaib Doukkali'          ,'El Jadida'  , 'UCD'  ,'http://www.ucd.ac.ma', 'https://www.ucd.ac.ma/wp-content/uploads/2021/10/logo_ucd-140x81.png');
    INSERT INTO Universite VALUES (9, 'Universite Ibn Tofail'                ,'Kenitra'    , 'UIT'  ,'http://www.uit.ac.ma', 'https://www.uit.ac.ma/wp-content/uploads/2024/03/cropped-Ibn-Tofail-University_Logo_Vertical_Full-Color-and-white-01-120x120.png');
    INSERT INTO Universite VALUES (10,'Universite Ibn Zohr'                  ,'Agadir'     , 'UIZ'  ,'http://www.uiz.ac.ma', 'https://upload.wikimedia.org/wikipedia/commons/b/b0/Logo-UIZ.jpg');
    INSERT INTO Universite VALUES (11,'Universite Hassan-I'                  ,'Settat'     ,'UH1'   ,'http://www.uh1.ac.ma', 'https://www.uh1.ac.ma/wp-content/uploads/2021/04/loogo.png');
    INSERT INTO Universite VALUES (12,'Universite Sultan Moulay Slimane'     ,'Beni Mellal','USMS'  ,'http://www.usms.ac.ma', 'https://www.usms.ac.ma/sites/default/files/logo_usms_v.fw_.png');

    -----faculte----id_faculte,nom                                                                         ,surnom  ,logo   ,id_universite 
    INSERT INTO faculte VALUES (1,  'Faculte des sciences - Agadir'                                                   ,'FSA'    ,NULL   ,10);
    INSERT INTO faculte VALUES (2,  'Faculte des sciences juridiques, economiques et sociales-Agadir - Agadir'        ,'FSJES'  ,NULL   ,10);
    INSERT INTO faculte VALUES (3,  'Faculte des sciences juridiques, economiques et sociales-Ait-Melloul - Ait Melloul','FSJESAM', NULL,10);
    INSERT INTO faculte VALUES (4,  'Faculte des Sciences Appliquees - Ait Melloul'                                   ,'FSA'    ,NULL   ,10);
    INSERT INTO faculte VALUES (5,  'Faculte des Langues  Arts et Sciences Humaines - Ait Melloul'                    ,'FLASH'  ,NULL   ,10) ;                                  
    INSERT INTO faculte VALUES (6,  'Faculte des Lettres et des Sciences Humaines - Agadir'                           ,'FLSHA'  ,NULL   ,10);
    INSERT INTO faculte VALUES (7, 'Faculte Chariaa - Ait Melloul'                                                   ,'FCAM'   ,NULL   ,10);
    INSERT INTO faculte VALUES (8, 'Faculte Chariaa - Smara'                                                         ,'FCS'    ,NULL   ,10);

    INSERT INTO faculte VALUES (9, 'Faculte des Sciences - Casablanca'                                               ,'FSK'    ,NULL   , 9);
    INSERT INTO faculte VALUES (10, 'Faculte des Lettres et Sciences Humaines - Casablanca'                           ,'FLSHK'  ,NULL   , 9);
    INSERT INTO faculte VALUES (11, 'Faculte des sciences economique, Juridiques et Sociales - Casablanca'           ,'FSJES'  ,NULL   , 9);

    INSERT INTO faculte VALUES (12, 'Faculte des sciences juridiques, economiques et sociales - Settat'              ,'FSJES'  ,NULL   ,11);
    INSERT INTO faculte VALUES (13, 'Faculte des sciences et techniques - Settat'                                    ,'FST'    ,NULL   ,11);

    INSERT INTO faculte VALUES (14, 'Faculte des Lettres et des Sciences Humaines - El Jadida'                       ,'FLSH'   ,NULL   , 8);
    INSERT INTO faculte VALUES (15, 'Faculte des Sciences - El Jadida'                                               ,'FSEJ'   ,NULL   , 8);
    INSERT INTO faculte VALUES (16, 'Faculte des sciences juridiques, economiques et sociales - El Jadida'           ,'FSJESEJ',NULL   , 8);

    INSERT INTO faculte VALUES (17, 'Faculte des sciences juridiques, economiques et sociales - Souissi-Rabat'       ,'FSJES'  ,NULL   , 1);
    INSERT INTO faculte VALUES (18, 'Faculte des sciences juridiques, economiques et sociales - Sale '               ,'FSJES'  ,NULL   , 1);
    INSERT INTO faculte VALUES (19, 'Faculte des sciences juridiques, economiques et sociales - Agdal-Rabat'         ,'FSJES'  ,NULL   , 1);
    INSERT INTO faculte VALUES (20, 'Faculte des Lettres et des Sciences Humaines - Rabat'                           ,'FLSH'   ,NULL   , 1);
    INSERT INTO faculte VALUES (21, 'Faculte des Sciences Economiques - Rabat'                                       ,'FSE'    ,NULL   , 1);
    INSERT INTO faculte VALUES (22, 'Faculte des Sciences - Rabat'                                                   ,'FSR'    ,NULL   , 1);

    INSERT INTO faculte VALUES (23, 'Faculte des Sciences - Ben MSik Casablanca'                                     ,'FSB'    ,NULL   , 2);
    INSERT INTO faculte VALUES (24, 'Faculte des Sciences - Ain Chock Casablanca'                                    ,'FSAC'   ,NULL   , 2);
    INSERT INTO faculte VALUES (25, 'Faculte des Sciences Juridiques, Economiques et Sociales - Mohammedia'          ,'FSJESM' ,NULL   , 2);
    INSERT INTO faculte VALUES (26, 'Faculte des Sciences Juridiques, Economiques et Sociales - Ain Sebâa Casablanca','FSJESAS',NULL   , 2);
    INSERT INTO faculte VALUES (27, 'Faculte des Sciences Juridiques, Economiques et Sociales - Ain Chock Casablanca','FSJESAC',NULL   , 2);
    INSERT INTO faculte VALUES (28, 'Faculte des Lettres et Sciences Humaines - Mohammedia'                          ,'FLSHM'  ,NULL   , 2);
    INSERT INTO faculte VALUES (29, 'Faculte des Lettres et Sciences Humaines - Ben MSik Casablanca'                 ,'FLSHB'  ,NULL   , 2);
    INSERT INTO faculte VALUES (30, 'Faculte des Lettres et Sciences Humaines - Ain Chock Casablanca'                ,'FLSHAC' ,NULL   , 2);
    INSERT INTO faculte VALUES (31, 'Faculte de Sciences et Techniques - Mohammedia'                                 ,'FST'    ,NULL   , 2);

    INSERT INTO faculte VALUES (32, 'Faculte des Sciences Fes'                                                       ,'FSF'    ,NULL   , 3);
    INSERT INTO faculte VALUES (33, 'Faculte des Lettres et des Sciences Humaines - Fes-Sais'                        ,'FLSHF'  ,NULL   , 3);
    INSERT INTO faculte VALUES (34, 'Faculte des Sciences Juridiques, economiques et Sociales - Fes-Dhar El Mehraz'  ,'SJESF'  ,NULL   , 3);

    INSERT INTO faculte VALUES (35, 'Faculte des Sciences Juridiques economiques et Sociales - Oujda'                ,'SJESO'  ,NULL   , 4);
    INSERT INTO faculte VALUES (36, 'Faculte des Sciences - Oujda'                                                   ,'FSO'    ,NULL   , 4);
    INSERT INTO faculte VALUES (37, 'Faculte des Lettres et Sciences Humaines - Oujda'                               ,'FLSHO'  ,NULL   , 4);
    INSERT INTO faculte VALUES (38, 'Faculte des Sciences et Techniques - Alhoceima'                                 ,'FSTAH'  ,NULL   , 4);

    INSERT INTO faculte VALUES (39, 'Faculte des sciences - Semlalia'                                                ,'FSS'    ,NULL   , 5);
    INSERT INTO faculte VALUES (40, 'Faculte des lettres et des sciences humaines'                                   ,'FLSHCA' ,NULL   , 5);
    INSERT INTO faculte VALUES (41, 'Faculte des sciences et techniques'                                             ,'FSTCA'  ,NULL   , 5);

    INSERT INTO faculte VALUES (42, 'Faculte des Sciences - Meknes'                                                  ,'FSM'    ,NULL   , 6);
    INSERT INTO faculte VALUES (43, 'Faculte des Sciences Juridiques, economiques et Sociales - Meknes'              ,'FSJESM' ,NULL   , 6);
    INSERT INTO faculte VALUES (44, 'Faculte des Sciences et Techniques - Errachidia'                                ,'FSTE'   ,NULL   , 6);

    INSERT INTO faculte VALUES (45, 'Faculte des lettres et des sciences humaines - Martil'                          ,'FLSHM'  ,NULL   , 7);
    INSERT INTO faculte VALUES (46, 'Faculte des sciences - Tanger '                                                 ,'FS'     ,NULL   , 7);
    INSERT INTO faculte VALUES (47, 'Faculte des sciences juridiques, economiques et sociales - Tanger'              ,'FSJEST' ,NULL   , 7);
    INSERT INTO faculte VALUES (48 ,'Faculte des sciences et techniques - Tanger'                                    ,'FSTT'   ,NULL   , 7);

    INSERT INTO faculte VALUES ( 49,'Faculte des lettres et des sciences humaines - Beni Mellal'                     ,'FLSHBM' ,NULL   , 12);
    INSERT INTO faculte VALUES ( 50,'Faculte des sciences et techniques - Beni Mellal'                               ,'FSTBM'  ,NULL   , 12);


-- set the faculty-logo to be the same as the universite logo
UPDATE (
SELECT logo_fac, logo_uni FROM faculte JOIN universite USING(id_universite)
)
SET logo_fac = logo_uni;


---------------------------id_filiere,Nom	,surnom	
INSERT INTO FILIERE_LICENCE  VALUES  (1,'Sciences Mathematiques et applications','SMA');
INSERT INTO FILIERE_LICENCE  VALUES  (2,'Sciences Mathematiques et informatique','SMI');
INSERT INTO FILIERE_LICENCE  VALUES  (3,'Sciences de la matiere Chimie','SMC');
INSERT INTO FILIERE_LICENCE  VALUES  (4,'Sciences de la matiere Physique','SMP');
INSERT INTO FILIERE_LICENCE  VALUES  (5,'Sciences de la terre et de l univers','STU');
INSERT INTO FILIERE_LICENCE  VALUES  (6,'GeniSciences de la vie','SV');
INSERT INTO FILIERE_LICENCE  VALUES  (7,'Droit Arabe','DA');
INSERT INTO FILIERE_LICENCE  VALUES  (8,'Droit Francais','DF');
INSERT INTO FILIERE_LICENCE  VALUES  (9,'Economie et Gestion','EG');
INSERT INTO FILIERE_LICENCE  VALUES  (10 ,'Biologie vegetale appliquee','BVA');
INSERT INTO FILIERE_LICENCE  VALUES  (11 ,'Chimie fondamentale et applique','CFA');
INSERT INTO FILIERE_LICENCE  VALUES  ( 12,'Geoscience appliquee','GA');
INSERT INTO FILIERE_LICENCE  VALUES  ( 13,'Mathematiques appliquees','MA');
INSERT INTO FILIERE_LICENCE  VALUES  (14 ,'Etudes islamiques','EI');
INSERT INTO FILIERE_LICENCE  VALUES  (15 ,'Histoire et civilisation','HC');
INSERT INTO FILIERE_LICENCE  VALUES  (16 ,'Geographie','GEO');
INSERT INTO FILIERE_LICENCE  VALUES  (17 ,'Etudes francaises','EF');
INSERT INTO FILIERE_LICENCE  VALUES  (18 ,'Etudes arabes','EAR');
INSERT INTO FILIERE_LICENCE  VALUES  (19 ,'Etudes anglaises','EAN');
INSERT INTO FILIERE_LICENCE  VALUES  (20 ,'Tourisme et communication','TC');
INSERT INTO FILIERE_LICENCE  VALUES  (21 ,'Sociologie ','SOCIO');
INSERT INTO FILIERE_LICENCE  VALUES  (22 ,'Phylosophie','PHILO');

--Responsable---- id_Coordinateur , email ,          password,  id_faculter 
 INSERT INTO Responsable VALUES (1,'admin','admin'   ,1);
 INSERT INTO Responsable VALUES (2,'tacos@gmail.com','123'   ,9);
 INSERT INTO Responsable VALUES (3,'3bas@gmail.com','1236'   ,12);
 INSERT INTO Responsable VALUES (4,'ahmed@gmail.com','78dcdq'   ,14);
 INSERT INTO Responsable VALUES (5,'abdulah@gmail.com','xlopce82'   ,17);


--------------------id_condition ,max_age, max_annee_etude,	 note_min_semestre	,note_seuil,	id_master 
 INSERT INTO Condition VALUES (1,  23, 3, 14.0, 12.0);
 INSERT INTO Condition VALUES (2,  22, 5, 13.5, 11.5);
 INSERT INTO Condition VALUES (3,  21, 6, 14.0, 12.0);
 INSERT INTO Condition VALUES (4,  22, 4, 14.5, 12.5);
 INSERT INTO Condition VALUES (5,  25, 3, 14.0, 12.0);
 INSERT INTO Condition VALUES (6,  25, 3, 14.5, 12.0);
 INSERT INTO Condition VALUES (7,  24, 4, 14.0, 12.0);
 INSERT INTO Condition VALUES (8,  22, 5, 13.5, 11.5);
 INSERT INTO Condition VALUES (9,  20, 3, 14.0, 12.0);
 INSERT INTO Condition VALUES (10, 24, 4, 14.5, 12.50);





