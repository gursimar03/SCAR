-- Create the 'scar' database
CREATE DATABASE IF NOT EXISTS scar;

-- Use the 'scar' database
USE scar;

-- Drop tables if they exist
DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS match_result;
DROP TABLE IF EXISTS arena;
DROP TABLE IF EXISTS weapon;
DROP TABLE IF EXISTS leaderboard;
DROP TABLE IF EXISTS session;
DROP TABLE IF EXISTS user;

-- Create the 'user' table
CREATE TABLE IF NOT EXISTS user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    email_address VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(50) NOT NULL,
    age INT,
    access_level INT DEFAULT 0
);

-- Insert sample data into the 'user' table
INSERT INTO user (email_address, password, username, age, access_level) VALUES
    ('user1@example.com', 'hashed_password1', 'user1', 25, 1),
    ('user2@example.com', 'hashed_password2', 'user2', 30, 0),
    ('user3@example.com', 'hashed_password3', 'user3', 22, 0),
    ('user4@example.com', 'hashed_password4', 'user4', 28, 1),
    ('user5@example.com', 'hashed_password5', 'user5', 35, 0);

-- Create the 'session' table
CREATE TABLE IF NOT EXISTS session (
    session_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    session_data VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Insert sample data into the 'session' table
INSERT INTO session (user_id, session_data) VALUES
    (1, 'session_data_user1'),
    (2, 'session_data_user2'),
    (3, 'session_data_user3');

-- Create the 'leaderboard' table
CREATE TABLE IF NOT EXISTS leaderboard (
    leaderboard_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Insert sample data into the 'leaderboard' table
INSERT INTO leaderboard (user_id) VALUES
    (1),
    (2),
    (3);

-- Create the 'weapon' table
CREATE TABLE IF NOT EXISTS weapon (
    weapon_id INT PRIMARY KEY AUTO_INCREMENT,
    weapon_name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL
);

-- Insert sample data into the 'weapon' table
INSERT INTO weapon (weapon_name, type) VALUES
    ('Weapon1', 'Type1'),
    ('Weapon2', 'Type2'),
    ('Weapon3', 'Type3');


-- Create the 'inventory' table
CREATE TABLE IF NOT EXISTS inventory (
    user_id INT,
    weapon_id INT,
    PRIMARY KEY (user_id, weapon_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (weapon_id) REFERENCES weapon(weapon_id)
);

-- Insert sample data into the 'inventory' table
INSERT INTO inventory (user_id, weapon_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3);

    
-- Create the 'arena' table
CREATE TABLE IF NOT EXISTS arena (
    arena_id INT PRIMARY KEY AUTO_INCREMENT,
    arena_name VARCHAR(50) NOT NULL
);

-- Insert sample data into the 'arena' table
INSERT INTO arena (arena_name) VALUES
    ('Arena1'),
    ('Arena2');


-- Create the 'match_result' table
CREATE TABLE IF NOT EXISTS match_result (
    match_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    weapon_id INT,
    arena_id INT,
    enemies_spotted INT,
    kills INT,
    deaths INT,
    score INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (weapon_id) REFERENCES weapon(weapon_id),
    FOREIGN KEY (arena_id) REFERENCES arena(arena_id)
);

-- Insert sample data into the 'match_result' table
INSERT INTO match_result (user_id, weapon_id, arena_id, enemies_spotted, kills, deaths, score) VALUES
    (1, 1, 1, 10, 5, 2, 30),
    (2, 2, 1, 8, 3, 1, 25),
    (3, 3, 2, 12, 7, 4, 40);
