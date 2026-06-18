CREATE DATABASE quiz_app;

USE quiz_app;

CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) UNIQUE,
password VARCHAR(100)
);

CREATE TABLE questions(
id INT PRIMARY KEY AUTO_INCREMENT,
question TEXT,
option1 VARCHAR(100),
option2 VARCHAR(100),
option3 VARCHAR(100),
option4 VARCHAR(100),
correct_answer INT
);

CREATE TABLE results(
id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
score INT,
total_questions INT,
percentage DOUBLE,
quiz_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);