CREATE DATABASE IF NOT EXISTS student_management_db;
USE student_management_db;

DROP TABLE IF EXISTS marks;
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS students;

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    registration_date DATE DEFAULT (CURRENT_DATE)
) ENGINE=InnoDB;

CREATE TABLE courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    duration_months INT NOT NULL,
    fee DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE enrollments (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    enrollment_date DATE DEFAULT (CURRENT_DATE),
    status VARCHAR(20) DEFAULT 'ACTIVE',
    UNIQUE(student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
) ENGINE=InnoDB;

CREATE TABLE attendance (
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,
    enrollment_id INT NOT NULL,
    attendance_date DATE NOT NULL,
    status ENUM('PRESENT','ABSENT') NOT NULL,
    FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id)
) ENGINE=InnoDB;

CREATE TABLE marks (
    mark_id INT PRIMARY KEY AUTO_INCREMENT,
    enrollment_id INT NOT NULL,
    subject VARCHAR(100) NOT NULL,
    marks INT NOT NULL,
    FOREIGN KEY (enrollment_id) REFERENCES enrollments(enrollment_id)
) ENGINE=InnoDB;

INSERT INTO students (name, email, phone) VALUES
('Mano', 'mano@gmail.com', '9876543210'),
('Naveen', 'naveen@gmail.com', '9876543211'),
('Jegan', 'jegan@gmail.com', '9876543212');

INSERT INTO courses (course_name, duration_months, fee) VALUES
('Java Full Stack', 6, 45000.00),
('Python Full Stack', 6, 42000.00),
('Data Analytics', 4, 30000.00);

INSERT INTO enrollments (student_id, course_id) VALUES
(1, 1),
(2, 1),
(3, 2);

INSERT INTO attendance (enrollment_id, attendance_date, status) VALUES
(1, '2026-09-01', 'PRESENT'),
(1, '2026-09-02', 'PRESENT'),
(1, '2026-09-03', 'ABSENT'),
(2, '2026-09-01', 'PRESENT'),
(2, '2026-09-02', 'PRESENT'),
(3, '2026-09-01', 'ABSENT');

INSERT INTO marks (enrollment_id, subject, marks) VALUES
(1, 'Java', 85),
(1, 'SQL', 90),
(1, 'HTML CSS', 78),
(2, 'Java', 72),
(2, 'SQL', 80),
(2, 'HTML CSS', 75),
(3, 'Python', 88),
(3, 'SQL', 82);

SELECT 'Database created successfully' AS message;
