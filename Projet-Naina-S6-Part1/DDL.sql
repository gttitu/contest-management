CREATE DATABASE contestmanagement;

--module setting (Tefy) :

CREATE TABLE Contest (
	id INT NOT NULL AUTO_INCREMENT,
	description VARCHAR(100),
	finished BOOLEAN,
	dateBegin DATE,
	dateEnd DATE,
	PRIMARY KEY (id)
)Engine=InnoDB;

CREATE TABLE Matter (
	id INT NOT NULL AUTO_INCREMENT,
	contest INT,
	description VARCHAR(100),
	coefficient INT,
	average DECIMAL(10, 3),
	datetimeBegin DATETIME,
	datetimeEnd DATETIME,
	PRIMARY KEY (id),
	FOREIGN KEY(contest) REFERENCES Contest(id)
)Engine=InnoDB;

CREATE TABLE Center (
	id INT NOT NULL AUTO_INCREMENT,
	contest INT,
	description VARCHAR(100),
	location VARCHAR(100),
	nbAllowable INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(contest) REFERENCES Contest(id)
)Engine=InnoDB;

CREATE TABLE CenterDetail (
	id INT NOT NULL AUTO_INCREMENT,
	center INT,
	nbMen INT,
	nbWomen INT,
	minAge INT,
	maxAge INT,
	PRIMARY KEY (id),
	FOREIGN KEY(center) REFERENCES Center(id)
)Engine=InnoDB;

-- Gimmy

CREATE TABLE Candidate(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idCenter INT NOT NULL,
	FOREIGN KEY (idCenter) REFERENCES Center(id)
)Engine=InnoDB;

CREATE TABLE CandidateDetail(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	idCandidate INT NOT NULL,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	age INT NOT NULL,
	gender INT NOT NULL,
	FOREIGN KEY (idCandidate) REFERENCES Candidate(id)
)Engine=InnoDB;

CREATE TABLE Room(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idCenter INT NOT NULL,
	FOREIGN KEY (idCenter) REFERENCES Center(id)
)Engine=InnoDB;

CREATE TABLE RoomDetail(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idRoom INT NOT NULL,
	idCandidate INT NOT NULL,
	FOREIGN KEY (idRoom) REFERENCES Room(id),
	FOREIGN KEY (idCandidate) REFERENCES Candidate(id)
)Engine=InnoDB;

/* TOAVINA MODULE TABLES */

CREATE TABLE Mark (
	id INT NOT NULL AUTO_INCREMENT,
	candidate INT,
	matter INT,
	markValue DECIMAL(5, 2),
	PRIMARY KEY(id),
	FOREIGN KEY(candidate) REFERENCES Candidate(id),
	FOREIGN KEY(matter) REFERENCES Matter(id)
)Engine=InnoDB;

CREATE TABLE Deliberation (
	id INT NOT NULL AUTO_INCREMENT,
	center INT,
	matter INT,
	markValue DECIMAL(5, 2),
	PRIMARY KEY(id),
	FOREIGN KEY(center) REFERENCES Center(id),
	FOREIGN KEY(matter) REFERENCES Matter(id)
)Engine=InnoDB;

-- FullText
ALTER TABLE CandidateDetail
ADD FULLTEXT candidateDetailIndex (firstname, lastname);

ALTER TABLE Contest
ADD FULLTEXT contestIndex (description);

ALTER TABLE Matter
ADD FULLTEXT matterIndex (description);

ALTER TABLE Center
ADD FULLTEXT centerIndex (description, location);

ALTER TABLE CandidateDetail
ADD FULLTEXT candidateDetailIndex (firstname, lastname);
