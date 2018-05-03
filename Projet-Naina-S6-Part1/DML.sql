-- Tefy

INSERT INTO Contest(description, finished, dateBegin, dateEnd) VALUES
	('Concours des magistrats', false, '2018-05-02', '2018-05-06'),
	('Examen en license informatique', false, '2018-05-08', '2018-05-10');
	
INSERT INTO Matter (contest, description, coefficient, average,  datetimeBegin , datetimeEnd) VALUES
	(1, 'Droits', 3, 25, '2018-05-02 08:00', '2018-05-02 11:30'),
	(1, 'Sciences Politiques', 4, 30, '2018-05-03 08:30', '2018-05-03 11:30'),
	(2, 'Programmation', 6, 45, '2018-05-08 09:00', '2018-05-08 13:00'),
	(2, 'Web Design', 6, 45, '2018-05-09 07:30', '2018-05-09 17:00');
	
INSERT INTO Center (contest, description, location , nbAllowable) VALUES
	(1, 'CCI', 'Ivato Antananarivo', 250),
	(1, 'Carlton', 'Anosy Antananarivo', 190),
	(2, 'EPP', 'Andoharanofotsy Antananarivo', 100);
	
INSERT INTO CenterDetail  (center, nbMen, nbWomen , minAge, maxAge ) VALUES
	(1, 125, 125, 18, 50),
	(2, 100, 90, 18, 50),
	(3, 80, 20, 16, 60);
	

-- Gimmy

INSERT INTO Candidate(idCenter) VALUES(1);
INSERT INTO Candidate(idCenter) VALUES(1);
INSERT INTO Candidate(idCenter) VALUES(2);
INSERT INTO Candidate(idCenter) VALUES(3);

INSERT INTO Candidate(idCenter) VALUES(2);

INSERT INTO CandidateDetail(idCandidate, firstname, lastname, age, gender) VALUES(1, 'Margot', 'Delaroc', 21, 0);
INSERT INTO CandidateDetail(idCandidate, firstname, lastname, age, gender) VALUES(2, 'Irving', 'Zisman', 23, 1);
INSERT INTO CandidateDetail(idCandidate, firstname, lastname, age, gender) VALUES(3, 'Elizabeth', 'Smith', 40, 0);
INSERT INTO CandidateDetail(idCandidate, firstname, lastname, age, gender) VALUES(4, 'Jean', 'Renaud', 33, 1);

INSERT INTO Room(idCenter) VALUES(1);
INSERT INTO Room(idCenter) VALUES(1);
INSERT INTO Room(idCenter) VALUES(2);
INSERT INTO Room(idCenter) VALUES(3);

INSERT INTO RoomDetail(idRoom, idCandidate) VALUES(1, 1);
INSERT INTO RoomDetail(idRoom, idCandidate) VALUES(2, 2);
INSERT INTO RoomDetail(idRoom, idCandidate) VALUES(3, 3);
INSERT INTO RoomDetail(idRoom, idCandidate) VALUES(4, 4);
INSERT INTO RoomDetail(idRoom, idCandidate) VALUES(3, 5);

--Toavina
INSERT INTO Mark (candidate, matter, markValue) VALUES
	(1, 1 , 12.5),
	(1, 2 , 15),
	(2, 2 , 16),
	(3, 4 , 9),
	(4, 3, 8);
	
INSERT INTO Deliberation (center, matter, markValue) VALUES	
	(1, 1 , 16),
	(2, 1 , 18),
	(3, 1 , 15),
	(3, 2 , 15);
	
	