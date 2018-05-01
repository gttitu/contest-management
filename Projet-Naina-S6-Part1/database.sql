CREATE DATABASE contestmanagement;

--module setting (Tefy) :

CREATE TABLE Contest (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	description VARCHAR(100),
	finished BOOLEAN,
	dateBegin DATE,
	dateEnd DATE,
	PRIMARY KEY (id)
)Engine=InnoDB;

CREATE TABLE Matter (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	contest INT UNSIGNED,
	description VARCHAR(100),
	coefficient TINYINT UNSIGNED,
	average DECIMAL(10, 3),
	datetimeBegin DATETIME,
	datetimeEnd DATETIME,
	PRIMARY KEY (id),
	FOREIGN KEY(contest) REFERENCES Contest(id)
)Engine=InnoDB;

CREATE TABLE Center (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	contest INT UNSIGNED,
	description VARCHAR(100),
	location VARCHAR(100),
	nbAllowable INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(contest) REFERENCES Contest(id)
)Engine=InnoDB;

CREATE TABLE CenterDetail (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	center INT UNSIGNED,
	nbMen INTEGER,
	nbWomen INTEGER,
	minAge INTEGER,
	maxAge INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(center) REFERENCES Center(id)
)Engine=InnoDB;


--FullText
ALTER TABLE Contest
ADD FULLTEXT contestIndex (description, dateBegin, dateEnd);

ALTER TABLE Matter
ADD FULLTEXT matterIndex (description, coefficient, average, datetimeBegin, datetimeEnd);

ALTER TABLE Center
ADD FULLTEXT centerIndex (description, location, nbAllowable);

ALTER TABLE CenterDetail
ADD FULLTEXT centerDetailIndex (nbMen, nbWomen, minAge, maxAge);

