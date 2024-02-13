-- Active: 1706852612791@@127.0.0.1@3306@PRUEBAJAVA
-----------------------------------------------------------------------------------------------------------------

INSERT INTO SCHEDULES (day_schedule, begin_hour, end_hour, id_class_room, id_subject)
VALUES (1,"8:00","10:00",2,5);



-----------------------------------------------------------

INSERT INTO ENROLLMENTS (id_schedule, id_person)
VALUES 
(27,16),
(27,17),
(27,18);
--------------------------------------------------------------

INSERT INTO SCHEDULES (day_schedule, begin_hour, end_hour, id_class_room, id_subject)
VALUES (1,"8:00","10:00",2,5);

-----------------------------------------------------------------------------------------

INSERT INTO SUBJECTS (capacity, credits, id_period, id_person, id_course, id_program)
VALUES (15,5,1,4,5,4);

------------------------------------------------------------------------------------------

SELECT NOW(), dni_number FROM `PEOPLE`;

SELECT * FROM SCHEDULES SC
INNER JOIN SUBJECTS SB ON (SC.id_subject = SB.id_subject);

INSERT INTO `FEES` (credit_value, id_period, id_program) VALUES (980500,18,2)

--------------------------------------------------------------------------------------------

SELECT F.credit_value FROM `ENROLLMENTS` E
INNER JOIN `SCHEDULES` SC ON (E.id_schedule = SC.id_schedule)
INNER JOIN `SUBJECTS` SB ON (SC.id_subject = SB.id_subject)The period inserted exist!
INNER JOIN `FEES` F ON (SB.id_period = F.id_period)
WHERE E.id_schedule = 2 AND SB.id_program = F.id_program


UPDATE PERIODS SET semester = 2 WHERE id_period = 17

INSERT INTO `PERIODS` (year_period, semester) VALUES ("2024", 2);

SELECT dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM PEOPLE WHERE dni_number = 123451389;

SELECT id_person, dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex FROM PEOPLE WHERE rol = "Student"