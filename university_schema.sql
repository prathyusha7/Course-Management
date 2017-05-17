CREATE TABLE STUDENT (name VARCHAR(30) NOT NULL,student_number INTEGER NOT NULL,class CHAR NOT NULL,major CHAR(10),
PRIMARY KEY (student_number) );


CREATE TABLE COURSE ( course_name VARCHAR(30) NOT NULL,
course_number CHAR(8) NOT NULL,
credit_hours INTEGER,
department CHAR(10),
PRIMARY KEY (course_number),
UNIQUE (course_name) );


CREATE TABLE PREREQUISITE ( course_number CHAR(8) NOT NULL,
prerequisite_number CHAR(8) NOT NULL,
PRIMARY KEY (course_number, prerequisite_number),
FOREIGN KEY (course_number) REFERENCES
COURSE (course_number),
FOREIGN KEY (prerequisite_number) REFERENCES
COURSE (course_number) );


CREATE TABLE SECTION ( section_identifier INTEGER NOT NULL,
course_number CHAR(8) NOT NULL,
semester VARCHAR(6) NOT NULL,
year CHAR(4) NOT NULL,
instructor VARCHAR(15),
PRIMARY KEY (section_identifier),
FOREIGN KEY (course_number) REFERENCES
COURSE (course_number) );


CREATE TABLE GRADE_REPORT ( student_number INTEGER NOT NULL,
section_identifier INTEGER NOT NULL,
grade CHAR,
PRIMARY KEY (student_number, section_identifier),
FOREIGN KEY (student_number) REFERENCES
STUDENT (student_number),
FOREIGN KEY (section_identifier) REFERENCES
SECTION (section_identifier) );
