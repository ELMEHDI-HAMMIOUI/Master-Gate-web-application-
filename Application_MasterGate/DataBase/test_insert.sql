--------------------------------------------------------TEST-------------------------------------------------------------------------------

-- inserer le premier master pour le test
INSERT into master (
    ID_MASTER  ,    
	SPECIALITE ,    
	NOM_COORDINATEUR ,
	D_DEBUT_INSCRIPTION ,
	D_FIN_INSCRIPTION ,  
	D_AFF_PRESELECTION , 
	D_CONCOURS ,         
	D_AFF_RESULTAT_CONCOURS ,
	LIEU_CONCOURS ,          
	INFORMATION ,            
	ID_RESPONSABLE,          
	ID_CONDITION ,           
	DOCS_STRING  
    )   VALUES(
        1  ,
	'Intelligence Artificiel' ,
	'Dr. Dupont' ,
	to_date('20-06-2024','dd-mm-yyyy') ,  
	to_date('28-06-2024','dd-mm-yyyy')  , 
	to_date('02-07-2024','dd-mm-yyyy')  , 
	to_date('05-07-2024','dd-mm-yyyy')  , 
	to_date('10-07-2024','dd-mm-yyyy')  , 
	'Menkes, Zaitoun rue5' ,                           
	'Lors de l''affichage des listes, les etudiants doivent apporter leurs documents et les recues d''inscriptions, pour passer le concours.' , 
	1,   
	1 ,  
	'CNI, Bac, Attestation d''inscription, Frais d''inscriptions, releves de notes.' 
);

INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(1 , 1, 1);
INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(2 , 1, 2);
INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(3 , 1, 3);



-- inserer le deuxieme master pour le test
INSERT into master (
    ID_MASTER  ,    
	SPECIALITE ,    
	NOM_COORDINATEUR ,
	D_DEBUT_INSCRIPTION ,
	D_FIN_INSCRIPTION ,  
	D_AFF_PRESELECTION , 
	D_CONCOURS ,         
	D_AFF_RESULTAT_CONCOURS ,
	LIEU_CONCOURS ,          
	INFORMATION ,            
	ID_RESPONSABLE,          
	ID_CONDITION ,           
	DOCS_STRING  
    )   VALUES(
        2  ,
	'Chimie Oraganique' ,
	'Dr. Benjelloun El Kacimi' ,
	to_date('15-06-2024','dd-mm-yyyy') ,  
	to_date('24-06-2024','dd-mm-yyyy')  , 
	to_date('10-07-2024','dd-mm-yyyy')  , 
	to_date('07-07-2024','dd-mm-yyyy')  , 
	to_date('13-07-2024','dd-mm-yyyy')  , 
	'Rabat Ain Sbaa, rue13 ' ,                           
	'Lors de l''affichage des listes, les etudiants doivent apporter leurs documents et les recues d''inscriptions, pour passer le concours.' , 
	2,   
	2 ,  
	'CNI, Bac, Attestation d''inscription, Frais d''inscriptions, releves de notes, Deug, CV, Demande manuscrite.' 
);

INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(4 , 2, 4);
INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(5 , 2, 5);
INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(6 , 2, 6);



-- inserer le 3eme master pour le test
INSERT into master (
    ID_MASTER  ,    
	SPECIALITE ,    
	NOM_COORDINATEUR ,
	D_DEBUT_INSCRIPTION ,
	D_FIN_INSCRIPTION ,  
	D_AFF_PRESELECTION , 
	D_CONCOURS ,         
	D_AFF_RESULTAT_CONCOURS ,
	LIEU_CONCOURS ,          
	INFORMATION ,            
	ID_RESPONSABLE,          
	ID_CONDITION ,           
	DOCS_STRING  
    )   VALUES(
        3  ,
	'Physique Quantique' ,
	'Dr. Karim Elboukadi' ,
	to_date('25-06-2024','dd-mm-yyyy') ,  
	to_date('05-07-2024','dd-mm-yyyy')  , 
	to_date('10-07-2024','dd-mm-yyyy')  , 
	to_date('19-07-2024','dd-mm-yyyy')  , 
	to_date('23-07-2024','dd-mm-yyyy')  , 
	'Zin Al Abidine, rue99 ' ,                           
	'Lors de l''affichage des listes, les etudiants doivent apporter leurs documents et les recues d''inscriptions, pour passer le concours.' , 
	3,   
	3 ,  
	'CNI, Bac, Attestation d''inscription, Frais d''inscriptions, releves de notes, Deug, CV, Demande manuscrite, License.' 
);

INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(7 , 3, 7);
INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(8 , 3, 8);
INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(9 , 3, 9);



-- inserer le 4eme master pour le test
INSERT into master (
    ID_MASTER  ,    
	SPECIALITE ,    
	NOM_COORDINATEUR ,
	D_DEBUT_INSCRIPTION ,
	D_FIN_INSCRIPTION ,  
	D_AFF_PRESELECTION , 
	D_CONCOURS ,         
	D_AFF_RESULTAT_CONCOURS ,
	LIEU_CONCOURS ,          
	INFORMATION ,            
	ID_RESPONSABLE,          
	ID_CONDITION ,           
	DOCS_STRING  
    )   VALUES(
        4  ,
	'Litterature Greque' ,
	'Dr. Halima Nawfal' ,
	to_date('23-06-2024','dd-mm-yyyy') ,  
	to_date('03-07-2024','dd-mm-yyyy')  , 
	to_date('09-07-2024','dd-mm-yyyy')  , 
	to_date('20-07-2024','dd-mm-yyyy')  , 
	to_date('21-07-2024','dd-mm-yyyy')  , 
	'Quartier El Manar, Rabat, rue3 ' ,                           
	'Lors de l''affichage des listes, les etudiants doivent apporter leurs documents et les recues d''inscriptions, pour passer le concours.' , 
	4,   
	4 ,  
	'CNI, Bac, Attestation d''inscription, Frais d''inscriptions, releves de notes, Deug, CV, Demande manuscrite, License.' 
);

INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(10 , 4, 10);
INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(11 , 4, 11);
INSERT into filiere_admet_master( ID_FAM ,ID_MASTER,ID_FIL_LICENCE )VALUES(12 , 4, 12);


-- Insertion du nouveau master
INSERT into master (
    ID_MASTER,    
    SPECIALITE,    
    NOM_COORDINATEUR,
    D_DEBUT_INSCRIPTION,
    D_FIN_INSCRIPTION,  
    D_AFF_PRESELECTION, 
    D_CONCOURS,         
    D_AFF_RESULTAT_CONCOURS,
    LIEU_CONCOURS,          
    INFORMATION,            
    ID_RESPONSABLE,          
    ID_CONDITION,           
    DOCS_STRING  
) VALUES (
    5,  -- Nouveau ID_MASTER
    'Sciences de l''information',  -- Nouvelle specialité
    'Dr. Ahmed Rachid',  -- Nouveau coordinateur
    to_date('25-06-2024','dd-mm-yyyy'),  -- Nouvelle date de début d'inscription
    to_date('05-07-2024','dd-mm-yyyy'),  -- Nouvelle date de fin d'inscription
    to_date('11-07-2024','dd-mm-yyyy'),  -- Nouvelle date d'affichage de présélection
    to_date('22-07-2024','dd-mm-yyyy'),  -- Nouvelle date de concours
    to_date('23-07-2024','dd-mm-yyyy'),  -- Nouvelle date d'affichage des résultats du concours
    'Faculte des Sciences, Casablanca, rue 4',  -- Nouveau lieu de concours
    'Les etudiants doivent apporter leurs documents et les reçus d''inscriptions pour passer le concours.',  -- Nouvelle information
    5,  -- Nouvel ID_RESPONSABLE
    5,  -- Nouvel ID_CONDITION
    'CNI, Bac, Attestation d''inscription, Frais d''inscriptions, releves de notes, Deug, CV, Demande manuscrite, Licence.'  -- Nouvelle liste de documents
);

-- Insertion des filières admises pour le nouveau master
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(13, 5, 13);
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(14, 5, 14);
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(15, 5, 15);


INSERT into master (
    ID_MASTER,    
    SPECIALITE,    
    NOM_COORDINATEUR,
    D_DEBUT_INSCRIPTION,
    D_FIN_INSCRIPTION,  
    D_AFF_PRESELECTION, 
    D_CONCOURS,         
    D_AFF_RESULTAT_CONCOURS,
    LIEU_CONCOURS,          
    INFORMATION,            
    ID_RESPONSABLE,          
    ID_CONDITION,           
    DOCS_STRING  
) VALUES (
    6,  
    'Ingenierie Logicielle',  
    'Dr. Fatima Zahra',  
    to_date('28-06-2024','dd-mm-yyyy'),  
    to_date('08-07-2024','dd-mm-yyyy'),  
    to_date('15-07-2024','dd-mm-yyyy'),  
    to_date('25-07-2024','dd-mm-yyyy'),  
    to_date('26-07-2024','dd-mm-yyyy'),  
    'Ecole Nationale, Fes, avenue 5',  
    'Apportez tous vos documents et reçus d''inscription pour le concours.',  
    1,  
    3,  
    'CNI, Bac, Attestation d''inscription, Frais d''inscriptions, releves de notes, Licence, CV, Lettre de motivation.'
);

INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(16, 6, 16);
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(17, 6, 17);
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(18, 6, 18);

INSERT into master (
    ID_MASTER,    
    SPECIALITE,    
    NOM_COORDINATEUR,
    D_DEBUT_INSCRIPTION,
    D_FIN_INSCRIPTION,  
    D_AFF_PRESELECTION, 
    D_CONCOURS,         
    D_AFF_RESULTAT_CONCOURS,
    LIEU_CONCOURS,          
    INFORMATION,            
    ID_RESPONSABLE,          
    ID_CONDITION,           
    DOCS_STRING  
) VALUES (
    7,  
    'Gestion des Ressources Humaines',  
    'Dr. Mohammed Amine',  
    to_date('01-07-2024','dd-mm-yyyy'),  
    to_date('11-07-2024','dd-mm-yyyy'),  
    to_date('18-07-2024','dd-mm-yyyy'),  
    to_date('28-07-2024','dd-mm-yyyy'),  
    to_date('29-07-2024','dd-mm-yyyy'),  
    'Institut des Hautes Etudes, Tanger, rue 8',  
    'Les candidats doivent presenter leurs documents et reçus d''inscription lors du concours.',  
    2,  
    7,  
    'CNI, Bac, Attestation d''inscription, Frais d''inscriptions, releves de notes, Licence, CV, Lettre de recommandation.'
);

INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(19, 7, 19);
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(20, 7, 20);
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(21, 7, 21);


INSERT into master (
    ID_MASTER,    
    SPECIALITE,    
    NOM_COORDINATEUR,
    D_DEBUT_INSCRIPTION,
    D_FIN_INSCRIPTION,  
    D_AFF_PRESELECTION, 
    D_CONCOURS,         
    D_AFF_RESULTAT_CONCOURS,
    LIEU_CONCOURS,          
    INFORMATION,            
    ID_RESPONSABLE,          
    ID_CONDITION,           
    DOCS_STRING  
) VALUES (
    8,  
    'Finance et Comptabilite',  
    'Dr. Saida Bennani',  
    to_date('05-07-2024','dd-mm-yyyy'),  
    to_date('15-07-2024','dd-mm-yyyy'),  
    to_date('22-07-2024','dd-mm-yyyy'),  
    to_date('01-08-2024','dd-mm-yyyy'),  
    to_date('02-08-2024','dd-mm-yyyy'),  
    'Faculte des Sciences, Marrakech, rue 3',  
    'Les etudiants doivent fournir tous les documents requis pour passer le concours.',  
    3,  
    8,  
    'CNI, Bac, Attestation d''inscription, Frais d''inscriptions, releves de notes, Licence, CV, Lettre de motivation, Lettre de recommandation.'
);

INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(22, 8, 2);
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(23, 8, 5);
INSERT into filiere_admet_master(ID_FAM, ID_MASTER, ID_FIL_LICENCE) VALUES(24, 8, 7);
