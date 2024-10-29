
CREATE TABLE student (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         student_id VARCHAR(20) NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         gender VARCHAR(10) NOT NULL,
                         id_card_number VARCHAR(20) NOT NULL
);
ALTER TABLE student ADD COLUMN password VARCHAR(255) NOT NULL;
CREATE TABLE IF NOT EXISTS courses (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    schedule VARCHAR(255),
    instructor VARCHAR(255),
    description TEXT,
    syllabus TEXT,
    textbook TEXT
    );
