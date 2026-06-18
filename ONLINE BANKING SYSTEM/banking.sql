CREATE DATABASE banking_system;

USE banking_system;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE accounts (
    account_no INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    holder_name VARCHAR(100),
    account_type VARCHAR(20),
    balance DOUBLE,
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_no INT,
    transaction_type VARCHAR(30),
    amount DOUBLE,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);