-- SQL Script to set up the database for the CRUD application (PostgreSQL)
-- In Postgres, you usually create the database via command line or tool like pgAdmin
-- CREATE DATABASE mrc_teaching;

-- Connect to mrc_teaching database first
-- \c mrc_teaching;

CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    course VARCHAR(100) NOT NULL
);

-- Sample data
INSERT INTO students (name, email, course) VALUES 
('John Doe', 'john@example.com', 'Java Programming'),
('Jane Smith', 'jane@example.com', 'Database Management');
