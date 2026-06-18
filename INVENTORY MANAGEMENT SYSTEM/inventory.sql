CREATE DATABASE inventory_system;

USE inventory_system;

CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) UNIQUE,
password VARCHAR(100)
);

CREATE TABLE products(
product_id INT PRIMARY KEY AUTO_INCREMENT,
product_name VARCHAR(100),
category VARCHAR(100),
quantity INT,
price DOUBLE,
supplier_name VARCHAR(100)
);

CREATE TABLE inventory_records(
id INT PRIMARY KEY AUTO_INCREMENT,
product_id INT,
action_type VARCHAR(20),
quantity INT,
record_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);