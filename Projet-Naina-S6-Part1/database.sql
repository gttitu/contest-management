CREATE DATABASE contestmanagement;

--module setting (Tefy) :

CREATE TABLE Contest (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	description VARCHAR(100),
	finished BOOLEAN UNSIGNED,
	PRIMARY KEY (id)
)Engine=InnoDB;

CREATE TABLE Matter (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	idContest INT UNSIGNED,
	description VARCHAR(100),
	coefficient TINYINT UNSIGNED,
	average DECIMAL(10, 3),
	PRIMARY KEY (id),
	FOREIGN KEY(idContest) REFERENCES Contest(id),
)Engine=InnoDB;

CREATE TABLE Center (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	idContest INT UNSIGNED,
	description VARCHAR(100),
	location VARCHAR(100),
	nbAllowable INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(idContest) REFERENCES Contest(id),
)Engine=InnoDB;