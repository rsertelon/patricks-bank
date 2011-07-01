USE patricksbank;

DROP TABLE IF EXISTS compte;
CREATE TABLE compte (
	idCompte int NOT NULL AUTO_INCREMENT,
	libelle varchar(64) NOT NULL,
	montant double NOT NULL,
	numero varchar(64) NOT NULL,
	PRIMARY KEY (idCompte)
);

DROP TABLE IF EXISTS lien_compte_utilisateur;
CREATE TABLE lien_compte_utilisateur(
	idCompte_fk int NOT NULL,
	idUtilisateur_fk int NOT NULL,
	FOREIGN KEY (idCompte_fk) REFERENCES Compte(idCompte),
	FOREIGN KEY (idUtilisateur_fk) REFERENCES Utilisateur(idUtilisateur)
);

DROP TABLE IF EXISTS operation;
CREATE TABLE operation(
	idOperation INTEGER NOT NULL AUTO_INCREMENT,
	idCompte INTEGER NOT NULL,
	libelle varchar(64),
	dateOperation date NOT NULL,
	montant double NOT NULL,
	typeOperation varchar(64) NOT NULL,
	PRIMARY KEY (idOperation),
	FOREIGN KEY (idCompte) REFERENCES Compte(idCompte)
);


DROP TABLE IF EXISTS utilisateur;
CREATE TABLE utilisateur (
	idUtilisateur INTEGER NOT NULL AUTO_INCREMENT,
	login VARCHAR(64) NOT NULL,
	password VARCHAR(64) NOT NULL,
	nom VARCHAR(64) NOT NULL,
	prenom VARCHAR(64) NOT NULL,
	locale VARCHAR(8),
	PRIMARY KEY (idUtilisateur)
);

DROP TABLE IF EXISTS role;
CREATE TABLE role (
	idRole INTEGER NOT NULL AUTO_INCREMENT,
	role VARCHAR(64) NOT NULL,
	PRIMARY KEY (idRole)
);

DROP TABLE IF EXISTS lien_role_utilisateur;
CREATE TABLE lien_role_utilisateur (
	idUtilisateur_fk INTEGER NOT NULL,
	idRole_fk INTEGER NOT NULL,
	FOREIGN KEY (idRole_fk) REFERENCES Role(idRole),
	FOREIGN KEY (idUtilisateur_fk) REFERENCES Utilisateur(idUtilisateur)
);

DROP TABLE IF EXISTS virement;
CREATE TABLE virement(
	idOperation INTEGER NOT NULL AUTO_INCREMENT,
	idCompteSource INTEGER NOT NULL,
	idCompteCible INTEGER NOT NULL,
	dateVirement DATETIME NOT NULL,
	montant double NOT NULL,
	PRIMARY KEY (idOperation),
	FOREIGN KEY (idCompteSource, idCompteCible) REFERENCES Compte(idCompteSource, idCompteCible)
);

truncate table compte;
insert into compte(idCompte,libelle, montant, numero) values (1, "Compte courant", 623.56, "993 045 71 789");
insert into compte(idCompte,libelle, montant, numero) values (2, "Livret Jeune", -63.2, "993 045 71 759");
insert into compte(idCompte,libelle, montant, numero) values (3, "Livret A", 1560.00, "993 545 71 789");
insert into compte(idCompte,libelle, montant, numero) values (4, "Compte Courant", 1000.00, "993 545 71 790");

truncate table utilisateur;
INSERT INTO utilisateur(idUtilisateur,login, password, nom, prenom, locale) values (1, "admin", "097d39aa40a33687f8440dcde070c0e89337827d", "admin", "admin", "en");
INSERT INTO utilisateur(idUtilisateur,login, password, nom, prenom, locale) values (2, "user", "481462f304f9769948e7d681af18bd44ce394e64", "Landelle", "Stephane", "fr");
INSERT INTO utilisateur(idUtilisateur,login, password, nom, prenom, locale) values (3, "baptiste", "372c95e4e87fa60dd67df7221348a4bd09e6f0f0","Pagnier", "Baptiste", "en");

truncate table role;
INSERT INTO role values (1, "ROLE_ADMIN");
INSERT INTO role values (2, "ROLE_USER");

truncate table lien_compte_utilisateur;
insert into lien_compte_utilisateur values (1, 2);
insert into lien_compte_utilisateur values (2, 2);
insert into lien_compte_utilisateur values (3, 2);
insert into lien_compte_utilisateur values (1, 1);
insert into lien_compte_utilisateur values (2, 3);
insert into lien_compte_utilisateur values (4, 3);

truncate table operation;
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (100, 1, "Casino", '2011-04-12', -15,"PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (102, 1, "Casino", '2011-04-16', -15,"CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (103, 1, "Casino", '2011-04-05', -30,"RETRAIT" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (104, 1, "Casino", '2011-04-05', -15,"PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (105, 1, "Casino", '2011-04-04', -15,"PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (106, 1, "Casino", '2011-04-09', -15,"PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (1, 1, "Casino", '2011-04-12', -15,"PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (3, 1, "Mammuth", '2010-11-14', -45,"CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (4, 1, "Auchan", '2010-10-15', -52,"PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (5, 1, "PatricksBank", '2011-12-16', 400,"REMISE_CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (6, 1, "Casino", '2011-08-12', -65, "CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (8, 1, "PatricksBank", '2011-01-30', -20, "RETRAIT" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (9, 1, "PatricksBank", '2011-11-07', 300, "REMISE_CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (10, 1, "Papa", '2011-03-01', 152.25, "REMISE_CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (12, 1, "Grand-papa", '2011-02-28', 152.00, "REMISE_CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (13, 1, "Grand-maman", '2011-02-31', 12.00, "REMISE_CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (14, 1, "Papa", '2011-02-01', 152.25, "REMISE_CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (16, 1, "Grand-papa", '2011-02-24', 152.00, "REMISE_CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (17, 1, "Grand-maman", '2011-02-12', 152.45, "REMISE_CHEQUE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (18, 1, "Casino Supermarche", '2011-02-15', -12.45, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (19, 1, "Kebab Mania", '2011-04-12', -12.74, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (20, 1, "GAP", '2011-04-05', -105, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (21, 1, "GFPIE", '2011-04-5', -50, "RETRAIT" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (22, 1, "Capico Superstar", '2011-04-07', -152.45, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (23, 1, "RATP", '2011-02-04', -10.85, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (24, 1, "RATP", '2011-02-07', -15.45, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (25, 1, "SNCF", '2011-02-12', -45.00, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (26, 1, "Salvetat", '2011-02-25', -12.45, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (27, 2, "PatricksBank", '2011-02-04', -78.00, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (28, 1, "BouyguesTelecom", '2011-04-28', -23.99, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (29, 2, "EDF", '2011-02-14', -78.41, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (30, 1, "Head First Company", '2011-04-11', -98.99, "PAIEMENT_CARTE" );
insert into operation(idOperation, idCompte, libelle, dateOperation, montant, typeOperation) values (31, 1, "Head First Company", '2010-12-11', -98.99, "CHEQUE" );


truncate table lien_role_utilisateur;
insert into lien_role_utilisateur values(1, 1);
insert into lien_role_utilisateur values(1, 2);
insert into lien_role_utilisateur values(2, 2);
insert into lien_role_utilisateur values(3, 2);
