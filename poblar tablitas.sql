-- Active: 1706852612791@@127.0.0.1@3306@PROJECT_JAVA

-- READ THE COMMENTS IN THIS DOCUMENT, THE TRIGGERS HAVING ORDER TO IMPLEMENT

----------------------------------------------------------------------------

INSERT INTO DEPARTMENTS (name_department)
VALUES
('Facultad de Ingeniería'),
('Facultad de Ciencias de la Salud'),
('Facultad de Ciencias Sociales'),
('Facultad de Artes y Humanidades'),
('Facultad de Ciencias Exactas y Naturales'),
('Facultad de Educación'),
('Facultad de Economía y Negocios'),
('Facultad de Derecho'),
('Facultad de validaciones'),
('Facultad de Comunicación'),
('Facultad de Psicología');

----------------------------------------------------------------------------------------------------------------------------------

INSERT INTO PEOPLE (dni_type, dni_number, p_name, s_name, p_lastName, s_lastName, residing_city, address_home, phone, born_date, sex, rol, id_department)
VALUES
(2, '123456789', 'Juan', 'Carlos', 'González', 'López', 'Bogotá', 'Calle 123', '1234567890', '1990-05-15', 1, 1, 1),
(2, '234567890', 'María', 'Isabel', 'Hernández', 'Martínez', 'Medellín', 'Carrera 456', '0987654321', '1992-08-20', 2, 2, NULL),
(2, '345678901', 'Luis', 'Fernando', 'Rodríguez', 'Gómez', 'Cali', 'Avenida 789', '1357924680', '1995-03-10', 2, 1, 5),
(2, '109854216', 'Rut', 'Victoria', 'Ortega', 'Velasco', 'Bucaramanga', 'Avenida -789', '1357924680', '1995-03-10', 2, 1, 9),
(2, '189547896', 'Luis', 'Ernesto', 'Corredor', 'Jimenez', 'Floridablanca', 'Calle 789', '1357924680', '1997-03-10', 1, 2, NULL),
(2, '456789012', 'Ana', 'María', 'López', 'Sánchez', 'Barranquilla', 'Calle 1011', '2468013579', '1988-11-25', 2, 2, NULL),
(2, '567890123', 'Carlos', 'Alberto', 'Martínez', 'Ramírez', 'Cartagena', 'Carrera 1213', '9876543210', '1998-09-30', 1, 1, 7),
(2, '678901234', 'Laura', 'Patricia', 'Gómez', 'Vargas', 'Pereira', 'Calle 1415', '0123456789', '1987-07-05', 2, 2, NULL),
(2, '789012345', 'Santiago', 'Andrés', 'Sánchez', 'González', 'Bucaramanga', 'Avenida 1617', '9870123456', '1993-12-12', 1, 2, NULL),
(2, '890123456', 'Mónica', 'Beatriz', 'Ramírez', 'Herrera', 'Manizales', 'Carrera 1819', '6543210987', '1991-04-18', 2, 2, NULL),
(2, '901234567', 'Pedro', 'Pablo', 'Díaz', 'López', 'Cúcuta', 'Calle 2021', '7890123456', '1989-06-22', 1, 2, NULL),
(2, '012345678', 'Juliana', 'Fernanda', 'García', 'Martínez', 'Villavicencio', 'Avenida 2223', '2109876543', '1996-02-28', 2, 2, NULL),
(2, '123452789', 'Juan', 'Mateo', 'González', 'López', 'Bogotá', 'Calle 25', '1234567890', '1990-05-15', 1, 2, NULL),
(2, '123451789', 'Oracio', 'Carlos', 'González', 'López', 'Bogotá', 'Calle 13', '1234567890', '1992-05-15', 1, 2, NULL),
(2, '123451389', 'Critobal', 'Alfonso', 'López', 'López', 'Bogotá', 'Calle 823', '1234567890', '1990-05-15', 1, 2, NULL),
(2, '143456789', 'Jeraldin', 'Soraya', 'Martinez', 'López', 'Bogotá', 'Calle 4123', '1234567890', '1990-05-15', 2, 2, NULL),
(2, '164456789', 'Dionisio', NULL, 'Salamanca', 'López', 'Bogotá', 'Calle 128', '1234567890', '1990-05-15', 1, 2, NULL),
(2, '123676789', 'Ximena', NULL, 'Rios', 'Martinez', 'Bogotá', 'Calle 122', '1234567890', '1990-05-15', 2, 2, NULL),
(2, '123457689', 'Clara', NULL, 'González', 'Salamanca', 'Bogotá', 'Calle 623', '1234567890', '1990-05-15', 2, 2, NULL),
(2, '123455489', 'Arturo', NULL, 'González', 'Martinez', 'Bogotá', 'Calle 83', '1234567890', '1990-05-15', 1, 2, NULL),
(2, '123458889', 'Camila', 'Rosio', 'Martinez', 'Salamanca', 'Bogotá', 'Calle 93', '1234567890', '1990-05-15', 2, 2, NULL),
(2, '123450989', 'Camilo', 'Andres', 'González', 'López', 'Bogotá', 'Calle 154', '1234567890', '1990-05-15', 1, 2, NULL);

-----------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO PROGRAMS (name_program, level_program, id_department)
VALUES
('Ingeniería Informática', 1, 1),
('Medicina Veterinaria', 1, 3),
('Ciencias de la validación', 1, 9),
('Administración de Empresas', 1, 2),
('Derecho Internacional', 1, 3),
('Psicología Clínica', 1, 2),
('Ingeniería Eléctrica', 1, 4),
('Arquitectura', 1, 5),
('Ciencias de la Computación', 1, 5),
('Economía', 1, 7),
('Biología Marina', 1, 7);

---------------------------------------------------------------------------------

-- | IMPORTANT!!! |
-- v              v

-- Before to insert this data, create trigger C_CODE_PERIOD, just this trigger

INSERT INTO PERIODS (year_period, semester)
VALUES
("2016", 01),
("2016", 02),
("2017", 01),
("2017", 02),
("2018", 01),
("2018", 02),
("2019", 01),
("2019", 02),
("2020", 01),
("2020", 02),
("2021", 01),
("2021", 02),
("2022", 01),
("2022", 02),
("2023", 01),
("2023", 02),
("2024", 01),
("2024", 02);

------------------------------------------------------------------------------

-- | IMPORTANT!!! |
-- v              v

-- After insert Fees data, create triggers VAL_PERIOD and VAL_PERIOD_UPDT

INSERT INTO FEES (credit_value, id_period, id_program)
VALUES
(1501000, 11, 1),
(1540000, 12, 1),
(1450000, 13, 1),
(1400000, 14, 1),
(1300000, 15, 1),
(1500000, 16, 1),
(1501000, 11, 2),
(1540000, 12, 2),
(1450000, 13, 2),
(1400000, 14, 2),
(1300000, 15, 2),
(1500000, 16, 2),
(1501000, 11, 3),
(1540000, 12, 3),
(1450000, 13, 3),
(1400000, 14, 3),
(1300000, 15, 3),
(1500000, 16, 3),
(1501000, 11, 4),
(1540000, 12, 4),
(1450000, 13, 4),
(1400000, 14, 4),
(1300000, 15, 4),
(1500000, 16, 4),
(1501000, 11, 5),
(1540000, 12, 5),
(1450000, 13, 5),
(1400000, 14, 5),
(1300000, 15, 5),
(1500000, 16, 5),
(980500,17,1),
(1200000,17,2),
(1100000,17,3),
(900000,17,4),
(950000,17,5),
(980500,17,6),
(980500,17,7),
(1500000,17,8),
(1600000,17,9),
(1350800,17,10),
(980500,17,11);

--------------------------------------------------------

INSERT INTO COURSES (name_course, course_guide)
VALUES
('Introducción a la Programación', 'https://www.example.com/guia_curso_1'),
('Matemáticas Avanzadas', 'https://www.example.com/guia_curso_2'),
('Literatura Universal', 'https://www.example.com/guia_curso_3'),
('Economía Internacional', 'https://www.example.com/guia_curso_4'),
('Derecho Constitucional', 'https://www.example.com/guia_curso_5'),
('Física Cuántica', 'https://www.example.com/guia_curso_6'),
('Historia del Arte', 'https://www.example.com/guia_curso_7'),
('Psicología del Desarrollo', 'https://www.example.com/guia_curso_8'),
('Inglés Avanzado', 'https://www.example.com/guia_curso_9'),
('Marketing Estratégico', 'https://www.example.com/guia_curso_10'),
('Biología Molecular', 'https://www.example.com/guia_curso_11'),
('Diseño Gráfico', 'https://www.example.com/guia_curso_12'),
('Filosofía Política', 'https://www.example.com/guia_curso_13'),
('Gestión de Proyectos', 'https://www.example.com/guia_curso_14'),
('Programación Web', 'https://www.example.com/guia_curso_15'),
('Estadística Aplicada', 'https://www.example.com/guia_curso_16'),
('Sociología Contemporánea', 'https://www.example.com/guia_curso_17'),
('Anatomía Humana', 'https://www.example.com/guia_curso_18'),
('Diseño de Interiores', 'https://www.example.com/guia_curso_19'),
('Validación', 'https://www.example.com/guia_curso_20'),
('Literatura Latinoamericana', 'https://www.example.com/guia_curso_21');

-------------------------------------------------------------------------------

INSERT INTO BUILDINGS (reference_building)
VALUES
('Torre A'),
('Edificio Principal'),
('Torre B'),
('Edificio Norte'),
('Edificio Sur');

----------------------------------------------------------------------------

INSERT INTO FLOORS (id_building)
VALUES
(1), (1), (1),
(2), (2), (2),
(3), (3), (3),
(4), (4), (4),
(5), (5), (5);

------------------------------------------------------------

INSERT INTO CLASS_ROOMS (reference_class_room, quota, id_floor)
VALUES
('A101', 20, 1),
('B201', 18, 2),
('C301', 22, 3),
('D401', 17, 4),
('E501', 24, 5),
('F601', 21, 6),
('G701', 19, 7),
('H801', 23, 8),
('I901', 16, 9),
('J1001', 25, 10);

------------------------------------------------------------------------
-- esperar arreglo

-- to create 2022, 2023 registers, change your local date (YEAR AND MONTH)

-- 2022 1

INSERT INTO SUBJECTS (capacity, credits, id_period, id_person, id_course, id_program)
VALUES 
(15,5,13,3,2,8),
(18,3,13,3,4,9),
(20,3,13,4,3,3);

INSERT INTO SCHEDULES (day_schedule, begin_hour, end_hour, id_class_room, id_subject)
VALUES 
(6,"08:00","10:00",2,1),
(7,"10:00","12:00",1,2),
(1,"14:00","16:00",3,3);

-- 2022 2

INSERT INTO SUBJECTS (capacity, credits, id_period, id_person, id_course, id_program)
VALUES 
(15,4,14,3,7,8),
(10,2,14,7,21,10),
(25,2,14,4,15,3);

INSERT INTO SCHEDULES (day_schedule, begin_hour, end_hour, id_class_room, id_subject)
VALUES 
(2,"16:00","18:00",1,4),
(3,"08:00","10:00",2,5),
(4,"10:00","12:00",10,6);

-- 2023 1

INSERT INTO SUBJECTS (capacity, credits, id_period, id_person, id_course, id_program)
VALUES 
(15,5,15,3,2,8),
(18,3,15,3,4,9),
(20,3,15,4,3,3);

INSERT INTO SCHEDULES (day_schedule, begin_hour, end_hour, id_class_room, id_subject)
VALUES 
(5,"14:00","16:00",2,7),
(6,"16:00","18:00",1,8),
(7,"08:00","10:00",3,9);

-- 2023 2

INSERT INTO SUBJECTS (capacity, credits, id_period, id_person, id_course, id_program)
VALUES 
(15,4,16,3,7,8),
(10,2,16,7,21,10),
(25,2,16,4,15,3);

INSERT INTO SCHEDULES (day_schedule, begin_hour, end_hour, id_class_room, id_subject)
VALUES 
(1,"10:00","12:00",1,10),
(2,"14:00","16:00",2,11),
(3,"16:00","18:00",10,12);


-- Insert data 2024 1

INSERT INTO SUBJECTS (capacity, credits, id_period, id_person, id_course, id_program)
VALUES 
(15,5,17,3,2,8),
(18,3,17,3,4,9),
(20,3,17,4,3,3);

INSERT INTO SCHEDULES (day_schedule, begin_hour, end_hour, id_class_room, id_subject)
VALUES 
(4,"14:00","16:00",2,13),
(5,"16:00","18:00",1,14),
(6,"08:00","10:00",3,15);

----------------------------------------------------------------------------------------------

-- Insert enrollments

INSERT INTO ENROLLMENTS (id_schedule, id_person)
VALUES 
(13,2),
(13,5),
(13,6),
(13,8),
(13,9),
(13,10),
(13,11),
(13,12),
(13,13),
(13,14),
(13,15),
(13,16),
(13,17),
(13,18),
(14,2),
(14,5),
(14,6),
(14,8),
(14,9),
(14,10),
(14,11),
(14,12),
(14,13),
(14,14),
(14,15),
(14,16),
(14,17),
(14,18),
(15,2),
(15,5),
(15,6),
(15,8),
(15,9),
(15,10),
(15,11),
(15,12),
(15,13),
(15,14),
(15,15),
(15,16),
(15,17),
(15,18);