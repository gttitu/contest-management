INSERT INTO Contest(description, finished, dateBegin, dateEnd) VALUES
	('Concours des magistrats', false, '2018-05-02', '2018-05-06'),
	('Examen en license informatique', false, '2018-05-08', '2018-05-10');
	('Concours de cuisine', false, '2018-06-20', '2018-06-26');
	
INSERT INTO Matter (contest, description, coefficient, average,  datetimeBegin , datetimeEnd) VALUES
	(1, 'Droits', 3, 25, '2018-05-02 08:00', '2018-05-02 11:30'),
	(1, 'Sciences Politiques', 4, 30, '2018-05-03 08:30', '2018-05-03 11:30'),
	(2, 'Programmation', 6, 45, '2018-05-08 09:00', '2018-05-08 13:00'),
	(2, 'Web Design', 6, 45, '2018-05-09 07:30', '2018-05-09 17:00');
	(3, 'Patisserie', 2, 15, '2018-06-20 08:00', '2018-06-20 16:00');
	(3, 'Techniques de cuisson', 3, 22, '2018-06-21 08:00', '2018-06-21 16:00');
	
INSERT INTO Center (contest, description, location , nbAllowable) VALUES
	(1, 'CCI', 'Ivato Antananarivo', 250),
	(1, 'Carlton', 'Anosy Antananarivo', 190),
	(2, 'EPP', 'Andoharanofotsy Antananarivo', 100);
	(3, 'Hotel Colbert', 'Antananarivo', 60);
	
INSERT INTO CenterDetail  (center, nbMen, nbWomen , minAge, maxAge ) VALUES
	(1, 125, 125, 18, 50),
	(2, 100, 90, 18, 50),
	(3, 80, 20, 16, 60);
	(4, 30, 30, 15, 75);