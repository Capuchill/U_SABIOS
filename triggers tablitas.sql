-- Active: 1706852612791@@127.0.0.1@3306@PROJECT_JAVA

-- Create trigger to create period code

CREATE TRIGGER C_CODE_PERIOD
BEFORE INSERT ON PERIODS
FOR EACH ROW
BEGIN
    -- Validate if exist period
    DECLARE VAL_DUP INT;

    SELECT COUNT(id_period) INTO VAL_DUP FROM PERIODS WHERE year_period = NEW.year_period AND semester = NEW.semester;

    IF VAL_DUP > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "The inserted period already exist!";
    END IF;

    -- Create period code
    SET NEW.code_period = CONCAT(NEW.year_period, "-", LPAD(NEW.semester,2,"0"));


END;

DROP TRIGGER C_CODE_PERIOD;

--------------------------------------------------------------------------------------------------------------

-- Validate period before to insert in fees

CREATE TRIGGER VAL_PERIOD
BEFORE INSERT ON FEES
FOR EACH ROW

BEGIN
    DECLARE PERIOD_TODAY INT;
    DECLARE VALIDAT INT;
    DECLARE DATE_TODAY DATE;
    DECLARE DATE_MONTH DATE;
    DECLARE amount_found INT;
    SET DATE_TODAY = NOW();

    -- Validate current period

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;

    -- Validate if repeat fees

    SELECT DISTINCT COUNT(id_period) INTO amount_found FROM FEES WHERE id_period = NEW.id_period AND id_program = NEW.id_program;

    -- Validate that fees is on current date

    SELECT COUNT(id_period) INTO VALIDAT FROM PERIODS WHERE id_period = NEW.id_period AND year_period != DATE_FORMAT(DATE_TODAY, "%Y") OR id_period = NEW.id_period AND year_period = DATE_FORMAT(DATE_TODAY, "%Y") AND semester != PERIOD_TODAY;

    IF amount_found > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Don't it can't repeat fees per program in same period!";
    
    ELSEIF VALIDAT > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Don't it can't establish fees out up date";
    END IF;

END;

DROP TRIGGER VAL_PERIOD;

--------------------------------------------------------------------------------------------------------------

-- Validate period before to update in fees

CREATE TRIGGER VAL_PERIOD_UPDT
BEFORE UPDATE ON FEES
FOR EACH ROW

BEGIN
    DECLARE PERIOD_TODAY INT;
    DECLARE VALIDAT INT;
    DECLARE DATE_TODAY DATE;
    DECLARE DATE_MONTH DATE;
    DECLARE amount_found INT;
    SET DATE_TODAY = NOW();

    -- Validate current period

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;

    -- Validate if repeat fees

    SELECT DISTINCT COUNT(id_period) INTO amount_found FROM FEES WHERE id_period = NEW.id_period AND id_program = NEW.id_program;

    -- Validate that fees is on current date

    SELECT COUNT(id_period) INTO VALIDAT FROM PERIODS WHERE id_period = NEW.id_period AND year_period != DATE_FORMAT(DATE_TODAY, "%Y") OR id_period = NEW.id_period AND year_period = DATE_FORMAT(DATE_TODAY, "%Y") AND semester != PERIOD_TODAY;

    IF amount_found > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Don't it can't repeat fees per program in same period!";
    
    ELSEIF VALIDAT > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Don't it can't establish fees out up date";
    END IF;

END;

DROP TRIGGER VAL_PERIOD_UPDT;

-------------------------------------------------------------------------------------------------------------------------------------------------

-- Validate insert subject

CREATE TRIGGER VAL_SUBJECT
BEFORE INSERT ON SUBJECTS
FOR EACH ROW
BEGIN

    DECLARE COD_SUB TEXT;
    DECLARE NAME_COURS TEXT;
    DECLARE COD_PERIOD TEXT;
    DECLARE VALIDAT INT;
    DECLARE DATE_TODAY DATE;
    DECLARE PERIOD_TODAY INT;
    SET DATE_TODAY = NOW();

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;

    SELECT name_course INTO NAME_COURS FROM COURSES WHERE id_course = NEW.id_course;
    SELECT code_period INTO COD_PERIOD FROM PERIODS WHERE id_period = NEW.id_period AND semester = PERIOD_TODAY AND year_period = DATE_FORMAT(DATE_TODAY, "%Y");
    
    IF COD_PERIOD IS NULL THEN
        SIGNAL SQLSTATE "46000" SET MESSAGE_TEXT = "No se puede crear una asignatura fuera de la fecha actual";
    END IF;

    SET COD_SUB = CONCAT("PR",LPAD(NEW.id_program,2,"0"),"-CU",LPAD(NEW.id_course,2,"0"),"-PE",COD_PERIOD,"-",NAME_COURS);

    SELECT COUNT(id_subject) INTO VALIDAT FROM SUBJECTS WHERE name_subject = COD_SUB;

    IF VALIDAT > 0 THEN
        SIGNAL SQLSTATE "46000" SET MESSAGE_TEXT = "Ya existe!";
    
    ELSE
        SET NEW.name_subject = COD_SUB;
    END IF;

END;

DROP TRIGGER VAL_SUBJECT;

-----------------------------------------------------------------------------------------------------------------------------

CREATE TRIGGER validar_asig_profe
BEFORE INSERT ON SUBJECTS
FOR EACH ROW
BEGIN

    DECLARE dep_profe INT;
    DECLARE dep_pro_asig INT;
    DECLARE rol_t TEXT;

    SELECT id_department INTO dep_pro_asig FROM PROGRAMS WHERE id_program = NEW.id_program;
   
    SELECT id_department INTO dep_profe FROM PEOPLE WHERE id_person = NEW.id_person;

    SELECT rol into rol_t FROM PEOPLE WHERE id_person = NEW.id_person;

    IF rol_t != "Teacher" THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "No puedes ingresar a asignatura el id de alguien que no sea profesor";
    END IF;

    IF dep_profe != dep_pro_asig THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "El profesor no pertenece al departamento de el programa de esta materia";
    END IF;

END;

DROP TRIGGER validar_asig_profe;

----------------------------------------------------------------------------------------------------------------------

-- Validate insert schedules

CREATE TRIGGER VAL_SCHEDULES
BEFORE INSERT ON SCHEDULES
FOR EACH ROW
BEGIN

    DECLARE VALIDAT INT;
    DECLARE CAPACITY_SUBJ INT;
    DECLARE CAPACITY_ROOM INT;
    DECLARE MESSAGE_CAPACITY TEXT;

    IF NEW.begin_hour < "00:00" OR NEW.end_hour >= "24:00" THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Rango de hora entre 00:00:00 y 23:59:59";
    END IF;

    SELECT capacity INTO CAPACITY_SUBJ FROM SUBJECTS WHERE id_subject = NEW.id_subject;
    SELECT quota INTO CAPACITY_ROOM FROM CLASS_ROOMS WHERE id_class_room = NEW.id_class_room;

    IF CAPACITY_SUBJ > CAPACITY_ROOM THEN
        SET MESSAGE_CAPACITY = CONCAT("La cantidad de estudiantes superan al cupo del salon en ", (CAPACITY_SUBJ - CAPACITY_ROOM), " estudiantes.");
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = MESSAGE_CAPACITY;
    END IF;

    SELECT COUNT(id_schedule) INTO VALIDAT FROM SCHEDULES WHERE id_subject = NEW.id_subject AND id_class_room = NEW.id_class_room AND day_schedule = NEW.day_schedule AND (NEW.begin_hour BETWEEN begin_hour AND SUBTIME(end_hour,"00:00:01")) OR id_subject = NEW.id_subject AND id_class_room = NEW.id_class_room AND day_schedule = NEW.day_schedule AND (NEW.end_hour BETWEEN begin_hour AND SUBTIME(end_hour,"00:00:01"));

    IF NEW.end_hour < NEW.begin_hour THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "La hora inicial no puede ser menor a la final, use hora militar.";
    END IF;
    IF VALIDAT > 0 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Éste horario no está disponible";
    END IF;

END;

DROP TRIGGER VAL_SCHEDULES;

------------------------------------------------------------------------------------------------------

-- Enroll validation

CREATE TRIGGER VAL_ENROLL
BEFORE INSERT ON ENROLLMENTS
FOR EACH ROW
BEGIN

    DECLARE VAL_SCH_STUD INT;
    DECLARE MESS TEXT;
    DECLARE PERIOD_TODAY INT;
    DECLARE VALIDAT INT;
    DECLARE MSG TEXT;
    DECLARE DATE_TODAY DATE;
    DECLARE VAL_CANT_SUBJ INT;
    DECLARE CAPACITY_SUBJ INT;
    DECLARE AMOUNT_SHED_ENROLL INT;
    DECLARE PRICE DOUBLE(10,2);
    DECLARE STDT BOOLEAN;
    SET DATE_TODAY = NOW();

    -- Validate if he/she is student

    SELECT IF(rol = "Student", TRUE, FALSE) INTO STDT FROM PEOPLE WHERE id_person = NEW.id_person;

    IF NOT STDT THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Just available for students!";
    END IF;

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;

    SELECT COUNT(SB.id_period) INTO VALIDAT FROM `SCHEDULES` S 
    INNER JOIN `SUBJECTS` SB ON (S.id_subject = SB.id_subject)
    WHERE S.id_schedule = NEW.id_schedule AND SB.name_subject LIKE CONCAT("%-PE",DATE_FORMAT(DATE_TODAY,"%Y"),"-",LPAD(PERIOD_TODAY,2,"0"),"-%");

    SELECT COUNT(id_schedule) INTO AMOUNT_SHED_ENROLL FROM `ENROLLMENTS` WHERE id_schedule = NEW.id_schedule;

    SELECT SB.capacity INTO CAPACITY_SUBJ FROM `SCHEDULES` S 
    INNER JOIN `SUBJECTS` SB ON (S.id_subject = SB.id_subject)
    GROUP BY S.id_schedule
    HAVING S.id_schedule = NEW.id_schedule;

    IF AMOUNT_SHED_ENROLL >= CAPACITY_SUBJ THEN
        SET MSG = CONCAT("Subject capacity exceeded!");
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = MSG;
    END IF;

    IF VALIDAT = 0 THEN
        SET MSG = CONCAT("Don't it can't enroll in a period that is't current!");
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = MSG;
    END IF;

    -- VALIDATING OF STUDENT SCHEDULES
    SELECT COUNT(id_schedule) INTO VAL_SCH_STUD FROM ENROLLMENTS WHERE id_person = NEW.id_person AND id_schedule = NEW.id_schedule;

    IF VAL_SCH_STUD >= 1 THEN
        SET MESS = CONCAT("You can't enroll same subject!");
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = MESS;
    END IF;

    SELECT (F.credit_value * SB.credits) INTO PRICE FROM SCHEDULES SC
    INNER JOIN SUBJECTS SB ON (SC.id_subject = SB.id_subject)
    INNER JOIN FEES F ON (SB.id_period = F.id_period)
    WHERE SC.id_schedule = NEW.id_schedule AND SB.id_program = F.id_program;

    SET NEW.price = PRICE;

END;

DROP TRIGGER VAL_ENROLL;

------------------------------------------------------------------------------------------------

CREATE TRIGGER VAL_TEACH
BEFORE INSERT ON SCHEDULES
FOR EACH ROW
BEGIN

    DECLARE ID_TEACH INT;
    DECLARE CANT INT;
    DECLARE CANT_MPMH INT;
    DECLARE CANT_DPMHMS INT;
    DECLARE DATE_TODAY DATE;
    DECLARE PERIOD_TODAY INT;
    SET DATE_TODAY = NOW();

    IF DATE_FORMAT(DATE_TODAY, "%m") > 0 AND DATE_FORMAT(DATE_TODAY, "%m") <= 6 THEN
        SET PERIOD_TODAY = 1;
    ELSE 
        SET PERIOD_TODAY = 2;
    END IF;


    SELECT id_person INTO ID_TEACH FROM SUBJECTS WHERE id_subject = new.id_subject;

    SELECT COUNT(SB.id_person) INTO CANT_MPMH FROM SCHEDULES S
    INNER JOIN SUBJECTS SB ON (S.id_subject = SB.id_subject)
    WHERE SB.id_person = ID_TEACH AND S.day_schedule = NEW.day_schedule AND (NEW.begin_hour BETWEEN S.begin_hour AND SUBTIME(S.end_hour,"00:00:01")) OR SB.id_person = ID_TEACH AND day_schedule = NEW.day_schedule AND (NEW.end_hour BETWEEN S.begin_hour AND SUBTIME(S.end_hour,"00:00:01"));

    SELECT id_person INTO ID_TEACH FROM SUBJECTS WHERE id_subject = new.id_subject;

    SELECT COUNT(SB.id_person) INTO CANT FROM `SCHEDULES` S
    INNER JOIN `SUBJECTS` SB ON (S.id_subject = SB.id_subject)
    WHERE SB.id_person = ID_TEACH AND S.day_schedule = NEW.day_schedule AND (SB.name_subject LIKE CONCAT("%-PE",DATE_FORMAT(DATE_TODAY,"%Y"),"-",LPAD(PERIOD_TODAY,2,"0"))) AND (NEW.begin_hour BETWEEN S.begin_hour AND SUBTIME(S.end_hour,"00:00:01")) OR day_schedule = NEW.day_schedule AND (SB.name_subject LIKE CONCAT("%-PE",DATE_FORMAT(DATE_TODAY,"%Y"),"-",LPAD(PERIOD_TODAY,2,"0"))) AND (NEW.end_hour BETWEEN S.begin_hour AND SUBTIME(S.end_hour,"00:00:01"));

    IF CANT >= 1 THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "COJA OFICIO QUE ESTE YA TIENE, SE CRUZAN LOS HORARIOS";
    END IF;

END;

DROP TRIGGER VAL_TEACH

-----------------------------------------------------------------------------------------------------------------------------------------------------------------