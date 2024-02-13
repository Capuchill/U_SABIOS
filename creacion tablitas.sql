-- Active: 1706852612791@@127.0.0.1@3306@PROJECT_JAVA

-- Create Departments table

CREATE TABLE IF NOT EXISTS DEPARTMENTS (
    id_department INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    name_department VARCHAR(100)
);

-- Create people table

CREATE TABLE IF NOT EXISTS PEOPLE (
    id_person INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    dni_type ENUM ("TI", "DNI", "FOREIGN_DNI", "PASSPORT"),
    dni_number BIGINT UNIQUE NOT NULL,
    p_name VARCHAR(100) NOT NULL,
    s_name VARCHAR(100),
    p_lastName VARCHAR(100) NOT NULL,
    s_lastName VARCHAR(100),
    residing_city VARCHAR(100),
    address_home VARCHAR(100),
    phone VARCHAR(100),
    born_date DATE,
    sex ENUM ("Male", "Female"),
    rol ENUM ("Teacher", "Student"),
    id_department INT,
    FOREIGN KEY (id_department) REFERENCES DEPARTMENTS(id_department)
);

-- Create Programs table

CREATE TABLE IF NOT EXISTS PROGRAMS (
    id_program INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    name_program VARCHAR(100),
    level_program ENUM("Undergraduate", "Postgraduate"),
    id_department int,
    FOREIGN KEY (id_department) REFERENCES DEPARTMENTS(id_department)
);

-- Create Period table

CREATE TABLE IF NOT EXISTS PERIODS (
    id_period INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    code_period VARCHAR(7),
    year_period YEAR,
    semester INT
);

-- Create Fees table

CREATE TABLE IF NOT EXISTS FEES (
    id_fee INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    credit_value DOUBLE(10,2),
    id_period INT,
    id_program INT,
    FOREIGN KEY (id_period) REFERENCES PERIODS(id_period),
    FOREIGN KEY (id_program) REFERENCES PROGRAMS(id_program)
);

-- Create courses table

CREATE TABLE IF NOT EXISTS COURSES (
    id_course INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    name_course VARCHAR(100),
    course_guide VARCHAR(100)
);

-- Create Buildings table

CREATE TABLE IF NOT EXISTS BUILDINGS (
    id_building INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    reference_building VARCHAR(100)
);

-- Create Floors table

CREATE TABLE IF NOT EXISTS FLOORS (
    id_floor INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    id_building INT,
    FOREIGN KEY (id_building) REFERENCES BUILDINGS(id_building)
);

-- Create Class rooms table

CREATE TABLE IF NOT EXISTS CLASS_ROOMS (
    id_class_room INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    reference_class_room VARCHAR(100),
    quota INT,
    id_floor INT,
    FOREIGN KEY (id_floor) REFERENCES FLOORS(id_floor)
);

-- Create Subjects table

CREATE TABLE IF NOT EXISTS SUBJECTS (
    id_subject INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    name_subject VARCHAR(100),
    capacity INT,
    credits INT,
    id_period INT,
    id_person INT,
    id_course INT,
    id_program INT,
    FOREIGN KEY (id_period) REFERENCES PERIODS(id_period),
    FOREIGN KEY (id_person) REFERENCES PEOPLE(id_person),
    FOREIGN KEY (id_course) REFERENCES COURSES(id_course),
    FOREIGN KEY (id_program) REFERENCES PROGRAMS(id_program)
);

-- Create Schedules table

CREATE TABLE IF NOT EXISTS SCHEDULES (
    id_schedule INT AUTO_INCREMENT UNIQUE NOT NULL PRIMARY KEY,
    day_schedule ENUM("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"),
    begin_hour TIME,
    end_hour TIME,
    id_class_room INT,
    id_subject INT,
    FOREIGN KEY (id_class_room) REFERENCES CLASS_ROOMS(id_class_room),
    FOREIGN KEY (id_subject) REFERENCES SUBJECTS(id_subject)
);

-- Create Enrollments table

CREATE TABLE IF NOT EXISTS ENROLLMENTS (
    id_schedule INT,
    id_person INT,
    price DOUBLE,
    FOREIGN KEY (id_schedule) REFERENCES SCHEDULES(id_schedule),
    FOREIGN KEY (id_person) REFERENCES PEOPLE(id_person)
);